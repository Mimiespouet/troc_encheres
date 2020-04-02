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

import fr.eni.serdaigle.bll.EnchereManager;
import fr.eni.serdaigle.bo.ArticleVendu;
import fr.eni.serdaigle.bo.Enchere;
import fr.eni.serdaigle.bo.Utilisateur;
import fr.eni.serdaigle.exception.BusinessException;

/**
 * Servlet implementation class Encherir
 */
@WebServlet("/Encherir")
public class Encherir extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher rd = request.getRequestDispatcher("afficherDetailEnchere");
		rd.forward(request, response);	
		}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Utilisateur acheteur = (Utilisateur) session.getAttribute("utilisateur");
		
		int noArticle = Integer.parseInt(request.getParameter("noArticle"));
		int montantEnchere = Integer.parseInt(request.getParameter("proposition"));
		
		// Initialisation variables
		LocalDateTime dateEnchere = LocalDateTime.now();
		
		try {
			EnchereManager emger = new EnchereManager();
			ArticleVendu article = new ArticleVendu();
			article.setNoArticle(noArticle);
			
			//Construction de l'objet et requete d'insertion
			Enchere enchere = new Enchere(dateEnchere, montantEnchere, acheteur, article);
			emger.ajouterEnchere(enchere);
			
			
			// Redirection 
			request.setAttribute("success", "L'enchère a bien été prise en compte");
			request.setAttribute("noArticle", noArticle);
			request.setAttribute("enchere", enchere);
			
			RequestDispatcher rd = request.getRequestDispatcher("afficherDetailEnchere");
			rd.forward(request, response);

		} catch (BusinessException be) {
			System.out.println(be.getMessage());
			request.setAttribute("error", be.getMessage());
			doGet(request, response);
		}

	}

}
