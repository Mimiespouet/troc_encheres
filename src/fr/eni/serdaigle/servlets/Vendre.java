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
import fr.eni.serdaigle.bo.Retrait;
import fr.eni.serdaigle.bo.Utilisateur;
import fr.eni.serdaigle.exception.GeneralException;

/**
 * Servlet implementation class Accueil
 */
/**
 * Classe en charge de renvoyer les informations concernant un nouvel article mis en vente (vendreUnArticle.jsp | afficherDetailEnchere.jsp)
 * @author serdaigle
 * @version troc_encheres - v1.0
 * @date 31 mars 2020
 */
@WebServlet("/vendreArticle")
public class Vendre extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * {@inheritDoc}
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Utilisateur vendeur = (Utilisateur) session.getAttribute("utilisateur");
		request.setAttribute("vendeur", vendeur);
		CategorieManager catMger = new CategorieManager();
		List<Categorie> listeCategorie = new ArrayList<Categorie>();
		
		//Recuperation des categories pour les afficher dans le menu deroulant
				try {
					listeCategorie = catMger.selectAll();
					request.setAttribute("listeCategorie", listeCategorie);
				} catch (GeneralException ex) {
					request.setAttribute("error", ex.getMessage());
				}
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/vendreUnArticle.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// Récupération de l'utilisateur en session qui est le vendeur
		HttpSession session = request.getSession();
		Utilisateur vendeur = (Utilisateur) session.getAttribute("utilisateur");
		
		// Récupération des données du formulaire
		String nomArticle = request.getParameter("nom").trim();
		String description = request.getParameter("description").trim();
		String dateDebutEncheresStr = request.getParameter("dateDebut");
		String dateFinEncheresStr = request.getParameter("dateFin");
		int noCategorie = Integer.parseInt(request.getParameter("noCategorie"));
		int noArticle;
		
		// Initialisation variables
		LocalDateTime dateFinEncheres = null;
		LocalDateTime dateDebutEncheres = null;
		int prixInitial = 0;
		
		try {
			ArticleManager artMger = new ArticleManager();
			GeneralException be = new GeneralException();
			
			//Conversion pour les dates
			try {
				DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
				dateDebutEncheres = LocalDateTime.parse(dateDebutEncheresStr, formatter);
				dateFinEncheres = LocalDateTime.parse(dateFinEncheresStr, formatter);
			} catch (DateTimeParseException e) {
				e.printStackTrace();
				request.setAttribute("error", "Erreur de saisie dans les données Date de début et/ou fin enchère");
			}
			
			//Conversion pour les entiers
			try {
				prixInitial = Integer.parseInt(request.getParameter("prixInitial").trim());
			} catch (NumberFormatException e) {
				e.printStackTrace();
				request.setAttribute("error", "Erreur de saisie dans les données de type numérique");
			}
			
			// récupere les 3 champs de RETRAIT, puis construction des objets
			String rue = request.getParameter("rue").trim();
			String codePostal = request.getParameter("codePostal").trim();
			String ville = request.getParameter("ville").trim();
			Retrait retrait = new Retrait(rue, codePostal, ville);
			Categorie categorie = new Categorie();
			categorie.setNoCategorie(noCategorie);
			ArticleVendu art = new ArticleVendu(nomArticle, description, dateDebutEncheres, dateFinEncheres, prixInitial, vendeur, categorie);
			
			//Test si l'adresse de retrait a été remplie puis insert en fonction
			if(retrait.getRue().isEmpty() || retrait.getCodePostal().isEmpty() || retrait.getVille().isEmpty()) {
				noArticle = artMger.ajouterArticle(art);
			}else {
				art.setRetrait(retrait);
				noArticle = artMger.ajouterArticleRetrait(art);
			}
			
			//Test erreur
			if (noArticle==0) {
				request.setAttribute("error", "Erreur lors de l'insertion de l'article");
				doGet(request, response);
			}else {
				request.setAttribute("success", "L'article a bien été mis en vente");
				request.setAttribute("noArticle", noArticle);
				RequestDispatcher rd = request.getRequestDispatcher("afficherDetailEnchere");
				rd.forward(request, response);
			}
		} catch (GeneralException be) {
			System.out.println(be.getMessage());
			request.setAttribute("error", be.getMessage());
			doGet(request, response);
		}

	}

}
