package fr.eni.serdaigle.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.serdaigle.bll.ArticleManager;
import fr.eni.serdaigle.bo.Utilisateur;

/**
 * Servlet implementation class Recherche
 */
@WebServlet("/recherche")
public class Recherche extends HttpServlet {
	private static final long serialVersionUID = 1L;
     private ArticleManager mger;
	/**
	 * {@inheritDoc}
	 * @see javax.servlet.GenericServlet#init()
	 */
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config); 
		mger = new ArticleManager();
		
	}
//
//	/**
//	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
//	 */
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		response.getWriter().append("Served at: ").append(request.getContextPath());
//	}

	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session =  request.getSession();
		Utilisateur utilisateurConnecte = (Utilisateur) session.getAttribute("utilisateur");
		String categorie = request.getParameter("categories");
		String nomArticle = request.getParameter("nomArticle");
		String[] checkedEtat;
		if (utilisateurConnecte==null) {
			/*
			 * En tant qu’utilisateur non connecté, je peux lister les enchères en cours. Je
			 * peux filtrer ma recherche par catégorie, et par nom d’article (l’article est
			 * affiché si il contient le critère saisi) – Pour consulter le détail des
			 * enchères, l’utilisateur doit se connecter.
			 */
			checkedEtat = new String[1];
			checkedEtat[0] = "open";
			
			
		} else {
			/*
			 * tester valeur radio button pour savoir lequel je récup
			 */
			String filter = request.getParameter("filtre");
				checkedEtat = request.getParameterValues(filter);
		
			/*
			 * En tant qu’utilisateur connecté, je peux lister les enchères en cours, les
			 * enchères auxquelles je participe, c’est-à-dire celles sur lesquelles j’ai
			 * fait au moins une offre, et mes enchères gagnées. Je peux aussi sélectionner
			 * mes ventes, non commencées, en cours ou terminées.
			 */
		}
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/index.jsp");
		rd.forward(request, response);
	
	}

}
