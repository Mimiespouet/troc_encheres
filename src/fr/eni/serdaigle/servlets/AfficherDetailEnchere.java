package fr.eni.serdaigle.servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

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
		// récupération de la session		
		HttpSession session =  request.getSession();
		
		// initialisation et récupération de l'utilisateur de la session		
		Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");
		
		// initialisation de la date à comparer avec la date de fin d'enchère		
		LocalDateTime date = LocalDateTime.now();
		
		// initilisation et récupération du numéro d'article en paramètre pour la requête select		
		int noArticle = Integer.parseInt(request.getParameter("noArticle"));
		
		// initilisation de l'enchère a null pour éviter exception		
		Enchere enchere = null;
		
		// initilisation de l'enchère manager pour la requête select avec seter de l'attribut enchère et son objet enchère
		try {			
			enchere = emger.select(noArticle);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		request.setAttribute("enchere",enchere);
		
		// si la date est avant la date de fin d'enchère de l'article de l'enchère		
		if (date.isBefore(enchere.getArticle().getDateFinEncheres())) {
			// et si l'utilisateur n'est pas connecté			
			if (utilisateur==null) {
				// alors renvoie sur la page enchérir avec le bouton enchérir bloqué				
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/encherir.jsp");
				rd.forward(request, response);
			} else {
				// sinon si le numéro d'utilisateur est connecté mais n'est pas le vendeur			
				if (utilisateur.getNoUtilisateur()!=enchere.getArticle().getVendeur().getNoUtilisateur()) {
					// alors renvoie sur la page enchérir avec le bouton enchérir accessible					
					RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/encherir.jsp");
					rd.forward(request, response);
				// sinon si l'utilisateur est connecté et qu'il est le vendeur					
				} else if (utilisateur.getNoUtilisateur()==enchere.getArticle().getVendeur().getNoUtilisateur()){
					// alors renvoie sur la page détail de ma vente
					RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/detailMaVente.jsp");
					rd.forward(request, response);
				}
			}
		// sinon si la date est après la date de fin d'enchère de l'article de l'enchère			
		} else if (date.isAfter(enchere.getArticle().getDateFinEncheres())){
			// et si l'utilisateur n'est pas connecté
			if (utilisateur==null) {
				// alors renvoie à l'accueil				
				RequestDispatcher rd = request.getRequestDispatcher("accueil");
				rd.forward(request, response);
			} else {
				// sinon si l'utilisateur est l'acheteur			
				if (utilisateur.getNoUtilisateur()==enchere.getArticle().getAcheteur().getNoUtilisateur()) {
					// alors renvoie à la page acquisition de l'article enchéri					
					RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/acquisition.jsp");
					rd.forward(request, response);
				// sinon si l'utilisa n'est pas l'acheteur	
				} else if (utilisateur.getNoUtilisateur()==enchere.getArticle().getVendeur().getNoUtilisateur()){
					// alors renvoie à la page pour enchérir sur l'article				
					RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/detailMaVenteFinEnchere.jsp"); 
					rd.forward(request, response);
				} else if (utilisateur.getNoUtilisateur()!=enchere.getArticle().getAcheteur().getNoUtilisateur()){
				// alors renvoie à la page pour enchérir sur l'article				
				RequestDispatcher rd = request.getRequestDispatcher("accueil");
				rd.forward(request, response);
				}
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
