package fr.eni.serdaigle.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.serdaigle.bll.CategorieManager;
import fr.eni.serdaigle.bll.EnchereManager;
import fr.eni.serdaigle.bo.Categorie;
import fr.eni.serdaigle.bo.Enchere;
import fr.eni.serdaigle.exception.BusinessException;

/**
 * Servlet implementation class Accueil
 */
@WebServlet("/accueil")
public class Accueil extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		CategorieManager catMger = new CategorieManager();
		EnchereManager enchMger = new EnchereManager();
		List<Categorie> listeCategorie = new ArrayList<Categorie>();
		List<Enchere> listeEnchere = new ArrayList<Enchere>();
		try {
			
			// Recuperation de la liste des categories en BDD pour le select html
			listeCategorie = catMger.selectAll();
			request.setAttribute("listeCategorie", listeCategorie);
			
			// Recuperation de la liste des encheres en cours
			listeEnchere = enchMger.selectAllEnCours();
			System.out.println(listeEnchere);
			
			request.setAttribute("listeEnchere", listeEnchere);
		} catch (BusinessException be) {
			request.setAttribute("error", be.getMessage());
		}
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/index.jsp");
		rd.forward(request, response);	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
