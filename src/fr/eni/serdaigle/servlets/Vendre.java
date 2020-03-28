package fr.eni.serdaigle.servlets;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import fr.eni.serdaigle.bll.ArticleManager;
import fr.eni.serdaigle.bo.ArticleVendu;
import fr.eni.serdaigle.bo.Categorie;
import fr.eni.serdaigle.bo.Utilisateur;
import fr.eni.serdaigle.exception.BusinessException;

@WebServlet("/vendreArticle")
public class Vendre extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/vendreUnArticle.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Récupération de l'utilisateur en session qui est le vendeur
		HttpSession session = request.getSession();
		Utilisateur vendeur = (Utilisateur) session.getAttribute("utilisateur");
		
		// Récupération des données du forulaire
		String nomArticle = request.getParameter("nom").trim();
		String description = request.getParameter("description").trim();
		String dateDebutEncheresStr = request.getParameter("dateDebut");
		String dateFinEncheresStr = request.getParameter("dateFin");
		String categorieStr = request.getParameter("categorie");
		
		// ***Pour le test uniquement, à supprimer une fois requete selectbylibelle de Categorie opérationelle***
		Categorie categorie = new Categorie(2,"Informatique");
		
		// Initialisation variables
		LocalDateTime dateFinEncheres = null;
		LocalDateTime dateDebutEncheres = null;
		int prixInitial = 0;
		
		try {
			ArticleManager artMger = new ArticleManager();
			// ***A utiliser une fois le categoriemanager operationnel***	
			// CategorieManager catMger = new CategorieManager();
			BusinessException be = new BusinessException();
			
			//Conversion pour les dates
			try {
				DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
				System.out.println("formatter cree");
				dateDebutEncheres = LocalDateTime.parse(dateDebutEncheresStr, formatter);
				dateFinEncheres = LocalDateTime.parse(dateFinEncheresStr, formatter);
				System.out.println(dateDebutEncheres);
				System.out.println(dateFinEncheres);
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
			
			// ***A utiliser une fois le categoriemanager operationnel***
			//Categorie categorie = catMger.selectionnerCategorie(categorieStr);
			
			//Construction de l'objet et requete d'insertion
			ArticleVendu art = new ArticleVendu(nomArticle, description, dateDebutEncheres, dateFinEncheres, prixInitial, vendeur, categorie, false);
			artMger.ajouterArticle(art);
			
			
			// Redirection à changer
			request.setAttribute("success", "L'article a bien été mis en vente");
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/.jsp");
			rd.forward(request, response);

		} catch (BusinessException be) {
			be.printStackTrace();
			request.setAttribute("error", be.getMessage());
			doGet(request, response);
		}

	}

}
