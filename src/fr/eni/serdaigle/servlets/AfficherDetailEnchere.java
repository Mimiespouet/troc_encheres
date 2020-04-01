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

import fr.eni.serdaigle.bll.EnchereManager;
import fr.eni.serdaigle.bo.Enchere;
import fr.eni.serdaigle.bo.Utilisateur;
import fr.eni.serdaigle.exception.BusinessException;

/**
 * Servlet implementation class AfficherDetailEnchere
 */
@WebServlet("/afficherDetailEnchere")
public class AfficherDetailEnchere extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private EnchereManager emger;
	/**
	 * {@inheritDoc}
	 * @see javax.servlet.GenericServlet#init()
	 */
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config); 
		emger = new EnchereManager();
		
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session =  request.getSession();
		Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");
		int noArticle = Integer.parseInt(request.getParameter("noArticle"));
		Enchere enchere = null;
		try {
			enchere = emger.select(noArticle);
			
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		request.setAttribute("enchere",enchere);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/detailMaVente.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
