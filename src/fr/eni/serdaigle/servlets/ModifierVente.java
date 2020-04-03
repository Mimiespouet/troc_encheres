package fr.eni.serdaigle.servlets;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import fr.eni.serdaigle.bll.ArticleManager;
import fr.eni.serdaigle.bll.CategorieManager;
import fr.eni.serdaigle.bo.ArticleVendu;
import fr.eni.serdaigle.bo.Categorie;
import fr.eni.serdaigle.bo.Utilisateur;
import fr.eni.serdaigle.exception.BusinessException;

/**
 * Servlet implementation class modifArticle
 */
/**
 * Classe en charge de renvoyer les informations mises à jour concernant un article mis en vente (modificationVente.jsp)
 * @author serdaigle
 * @version troc_encheres - v1.0
 * @date 2 avril 2020
 */
@WebServlet("/modifierVente")
public class ModifierVente extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");
		CategorieManager catMger = new CategorieManager();
		List<Categorie> listeCategorie = new ArrayList<Categorie>();
		int noArticle = Integer.parseInt(request.getParameter("noArticle"));
		ArticleManager mger = new ArticleManager();
		ArticleVendu article = null;
		
		//Recuperation des categories pour les afficher dans le menu deroulant
		try {
			listeCategorie = catMger.selectAll();
			request.setAttribute("listeCategorie", listeCategorie);
		} catch (BusinessException ex) {
			request.setAttribute("error", ex.getMessage());
		}
		
		//Recuperation de l'article a afficher
		try {
			article = mger.select(noArticle);
			request.setAttribute("articleVendu", article);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/modificationVente.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Récupération de l'utilisateur en session qui est le vendeur
		HttpSession session = request.getSession();
		Utilisateur vendeur = (Utilisateur) session.getAttribute("utilisateur");

		// Récupération des données du formulaire
		String nomArticle = request.getParameter("nom");
		String description = request.getParameter("description");
		String dateDebutEncheresStr = request.getParameter("dateDebut");
		String dateFinEncheresStr = request.getParameter("dateFin");
		System.out.println("categorie" + request.getParameter("noCategorie"));
		int noCategorie = Integer.parseInt(request.getParameter("noCategorie"));
		int noArticle = Integer.parseInt(request.getParameter("noArticle"));

		// Initialisation variables
		LocalDateTime dateFinEncheres = null;
		LocalDateTime dateDebutEncheres = null;
		int prixInitial = 0;
		try {
			ArticleManager artMger = new ArticleManager();
			BusinessException be = new BusinessException();
			
			// Conversion pour les dates
			try {
				DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
				dateDebutEncheres = LocalDateTime.parse(dateDebutEncheresStr, formatter);
				dateFinEncheres = LocalDateTime.parse(dateFinEncheresStr, formatter);
			} catch (DateTimeParseException e) {
				e.printStackTrace();
				request.setAttribute("error", "Erreur de saisie dans les données Date de début et/ou fin enchère");
			}
			
			// Conversion pour les entiers
			try {
				prixInitial = Integer.parseInt(request.getParameter("prixInitial").trim());
			} catch (NumberFormatException e) {
				e.printStackTrace();
				request.setAttribute("error", "Erreur de saisie dans les données de type numérique");
			}
			
			Categorie categorie = new Categorie();
			categorie.setNoCategorie(noCategorie);

			// Construction de l'objet et requete d'update
			ArticleVendu art = new ArticleVendu(nomArticle, description, dateDebutEncheres, dateFinEncheres,
					prixInitial, vendeur, categorie);
			art.setNoArticle(noArticle);
			artMger.modifierArticle(art);
			request.setAttribute("success", "La vente a bien été modifié");
			RequestDispatcher rd = request.getRequestDispatcher("/afficherDetailEnchere");
			rd.forward(request, response);
		} catch (BusinessException be) {
			request.setAttribute("error", be.getMessage());
			doGet(request, response);
		}
	}
}
