package fr.eni.serdaigle.servlets;

import java.io.IOException;
import java.time.LocalDateTime;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.serdaigle.bll.ArticleManager;
import fr.eni.serdaigle.bll.EnchereManager;
import fr.eni.serdaigle.bo.ArticleVendu;
import fr.eni.serdaigle.bo.Enchere;
import fr.eni.serdaigle.bo.Utilisateur;
import fr.eni.serdaigle.exception.BusinessException;

/**
 * Servlet implementation class Encherir
 */
/**
 * Classe en charge de renvoyer la nouvelle enchère selon l'article (encherir.jsp)
 * @author serdaigle
 * @version troc_encheres - v1.0
 * @date 1 avril 2020
 */
@WebServlet("/encherir")
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
		
		LocalDateTime dateEnchere = LocalDateTime.now();
		EnchereManager eMger = new EnchereManager();
		ArticleManager aMger = new ArticleManager();
		
		try {
			Enchere enchereCheck = eMger.selectByUtilisateur(acheteur.getNoUtilisateur(),noArticle);
			Enchere enchereEnCours = null;
			
			//verification qu'il n'y a pas déja une enchere de l'utilisateur	
			if (enchereCheck==null) {
				
				ArticleVendu article = aMger.select(noArticle);
				enchereEnCours = new Enchere(dateEnchere, montantEnchere, acheteur, article);
				eMger.ajouterEnchere(enchereEnCours);
				
			} else {
				enchereEnCours = new Enchere(dateEnchere, montantEnchere, acheteur, enchereCheck.getArticle());
				eMger.updateEnchere(enchereEnCours);
			}
			
			request.setAttribute("success", "L'enchère a bien été prise en compte");
			request.setAttribute("noArticle", noArticle);
			request.setAttribute("enchere", enchereEnCours);
			
			RequestDispatcher rd = request.getRequestDispatcher("afficherDetailEnchere");
			rd.forward(request, response);

		} catch (BusinessException be) {
			System.out.println(be.getMessage());
			request.setAttribute("error", be.getMessage());
			doGet(request, response);
		}

	}

}
