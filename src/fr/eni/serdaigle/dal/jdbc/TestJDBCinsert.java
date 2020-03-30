package fr.eni.serdaigle.dal.jdbc;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.serdaigle.bll.ArticleManager;
import fr.eni.serdaigle.bll.UtilisateurManager;
import fr.eni.serdaigle.bo.ArticleVendu;
import fr.eni.serdaigle.bo.Categorie;
import fr.eni.serdaigle.bo.Enchere;
import fr.eni.serdaigle.bo.Utilisateur;
import fr.eni.serdaigle.exception.BusinessException;

@WebServlet("/testJDBC")
public class TestJDBCinsert extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Categorie categorie = new Categorie(2, "Informatique");
		List<Enchere> listeEnchere=new ArrayList<Enchere>();
		
		try {
			UtilisateurManager umger = new UtilisateurManager();
			Utilisateur vendeur = umger.selectionnerUtilisateur("Mimies");
			Utilisateur acheteur = umger.selectionnerUtilisateur("Delphetitoun");
			ArticleVendu a1 = new ArticleVendu(10, "nomArticle", "description", LocalDateTime.now(), LocalDateTime.now().plusMinutes(5), 60, 100, vendeur, acheteur, categorie, false, listeEnchere);
			ArticleManager mger = new ArticleManager();
			mger.ajouterArticle(a1);		
		} catch (BusinessException e) {
			e.printStackTrace();
		}
//		RequestDispatcher rd = req.getRequestDispatcher("test.jsp");
//		rd.forward(req, resp);
	}
}
