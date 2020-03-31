package fr.eni.serdaigle.dal.jdbc;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.serdaigle.bll.ArticleManager;
import fr.eni.serdaigle.bll.EnchereManager;
import fr.eni.serdaigle.bll.UtilisateurManager;
import fr.eni.serdaigle.bo.ArticleVendu;
import fr.eni.serdaigle.bo.Categorie;
import fr.eni.serdaigle.bo.Enchere;
import fr.eni.serdaigle.bo.Utilisateur;

/**
 * Servlet implementation class Accueil
 */
@WebServlet("/testJDBCinsertEnchere")
public class TestJDBCInsertEnchere extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Categorie categorie = new Categorie(2, "Informatique");
		
	
		try {
			UtilisateurManager utm = new UtilisateurManager();
			Utilisateur vendeur = utm.selectionnerUtilisateur("mimies");
			Utilisateur acheteur = utm.selectionnerUtilisateur("delphetitoun");
			
			
			ArticleVendu av = new ArticleVendu("nomArticle", "description", LocalDateTime.now(),LocalDateTime.now().plusMinutes(5), 100,vendeur, categorie);
			ArticleManager mger = new ArticleManager();
			mger.ajouterArticle(av);	
			
			Enchere e1 = new Enchere(LocalDateTime.now(), 100, acheteur, av);
			
			e1.setDateEnchere(LocalDateTime.now());
			e1.setMontantEnchere(100);
			
			EnchereManager mgr = new EnchereManager();
			mgr.ajouterEnchere(e1);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	//	RequestDispatcher rd = request.getRequestDispatcher("/");
	//	rd.forward(request, response);	

	}
		
		
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
 */

}
