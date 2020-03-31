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
import fr.eni.serdaigle.bll.CategorieManager;
import fr.eni.serdaigle.bll.UtilisateurManager;
import fr.eni.serdaigle.bo.ArticleVendu;
import fr.eni.serdaigle.bo.Categorie;
import fr.eni.serdaigle.bo.Enchere;
import fr.eni.serdaigle.bo.Utilisateur;
import fr.eni.serdaigle.exception.BusinessException;

@WebServlet("/testJDBCCategorie")
public class TestJDBCCategorie extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		List<Categorie> listeCategorie=new ArrayList<Categorie>();
		
		try {
			CategorieManager cmger = new CategorieManager();
			listeCategorie = cmger.selectAll();
			System.out.println(listeCategorie);		
		} catch (BusinessException e) {
			e.printStackTrace();
		}
//		RequestDispatcher rd = req.getRequestDispatcher("test.jsp");
//		rd.forward(req, resp);
	}
}
