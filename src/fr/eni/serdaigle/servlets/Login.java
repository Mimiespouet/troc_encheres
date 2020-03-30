package fr.eni.serdaigle.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import fr.eni.serdaigle.bll.UtilisateurManager;
import fr.eni.serdaigle.bo.Utilisateur;

/**
 * Servlet implementation class accueil
 */
@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UtilisateurManager mger; 
	//private Utilisateur utilisateur;
	
<<<<<<< HEAD
	



	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Cookie pseudo;
		Cookie mdp;

		HttpSession session = request.getSession();
		session.setAttribute("utilisateur", utilisateur);
		boolean etatCheckBox = request.getParameter("remember") != null;

		if (etatCheckBox == true) {
			pseudo = new Cookie("username", utilisateur.getPseudo());
			mdp = new Cookie("password", utilisateur.getMotDePasse());
			pseudo.setMaxAge(60 * 60 * 24 * 365);
			mdp.setMaxAge(60 * 60 * 24 * 365);
			response.addCookie(pseudo);
			response.addCookie(mdp);
		} else {
			pseudo = new Cookie("username", "");
			mdp = new Cookie("password", "");
			pseudo.setMaxAge(0);
			mdp.setMaxAge(0);
			response.addCookie(pseudo);
			response.addCookie(mdp);
			etatCheckBox = false;

		}
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/index.jsp");
		rd.forward(request, response);
=======
	/**
	 * {@inheritDoc}
	 * @see javax.servlet.GenericServlet#init()
	 */
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config); 
		mger = new UtilisateurManager();
		
>>>>>>> refs/remotes/origin/master
	}
	
//	/**
//	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
//	 */
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		HttpSession session = request.getSession();
//		session.setAttribute("utilisateur", utilisateur);
//		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/index.jsp");
//		rd.forward(request, response);
//	}

<<<<<<< HEAD
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
=======
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
>>>>>>> refs/remotes/origin/master
		try {
			String username = request.getParameter("username");
			String password = request.getParameter("password");

			// A modifier
			Utilisateur utilisateurTest = mger.selectionnerConnexion(username, password);
<<<<<<< HEAD
			System.out.println(utilisateurTest);
			if (utilisateurTest == null) {
				request.setAttribute("errorLogin", "Erreur de saisie Login / MDP, veuillez r�essayer");
=======
			System.err.println(utilisateurTest); //voir pour enlever mdp de toString de Utilisateur bo
			if (utilisateurTest==null) {
				request.setAttribute("errorLogin", "Erreur de saisie Login / MDP, veuillez réessayer");
>>>>>>> refs/remotes/origin/master
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/connexion.jsp");
				rd.forward(request, response);
<<<<<<< HEAD
			} else {
				utilisateur = utilisateurTest;
				doGet(request, response);
=======
			}else {
				HttpSession session = request.getSession();
				session.setAttribute("utilisateur", utilisateurTest);
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/index.jsp");
				rd.forward(request, response);
>>>>>>> refs/remotes/origin/master
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
}
