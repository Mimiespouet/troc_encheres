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
import fr.eni.serdaigle.bll.ArticleManager;
import fr.eni.serdaigle.bo.ArticleVendu;
import fr.eni.serdaigle.exception.BusinessException;

@WebServlet("/validerVente")
public class ValiderVente extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ValiderVente() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/detailVente.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		LocalDateTime dateFinEncheres = null;
		LocalDateTime dateDebutEncheres = null;
		int prixInitial = 0;
		int prixVente = 0;
		int noVendeur = 0;
		int noAcheteur = 0;

		try {

			HttpSession session = request.getSession();
			ArticleVendu articleVendu = (ArticleVendu) session.getAttribute("articleVendu");
			ArticleManager mger = new ArticleManager();

			BusinessException be = new BusinessException();

			String nomArticle = request.getParameter("nom").trim();
			String description = request.getParameter("description").trim();

			try {

				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

				String dateDebutEncheresStr = request.getParameter("dateDebut");
				String dateFinEncheresStr = request.getParameter("dateFin");

				dateDebutEncheres = LocalDateTime.parse(dateDebutEncheresStr, formatter);
				dateFinEncheres = LocalDateTime.parse(dateFinEncheresStr, formatter);

				System.out.println(dateDebutEncheres);
				System.out.println(dateFinEncheres);

			} catch (DateTimeParseException e) {
				request.setAttribute("error", "Erreur de saisie dans les données Date de début et/ou fin enchère");
			}

			try {
				prixInitial = Integer.parseInt(request.getParameter("prixInitial").trim());
				prixVente = Integer.parseInt(request.getParameter("prixVente").trim());
				noVendeur = Integer.parseInt(request.getParameter("noVendeur").trim());
				noAcheteur = Integer.parseInt(request.getParameter("noAcheteur").trim());

			} catch (NumberFormatException e) {
				request.setAttribute("error", "Erreur de saisie dans les données de type numérique");
			}

//			Categorie categorie = mger.selectionnerCategories(request.getParameter("noCategorie"));
//			noCategorie = categorie.getNoCategorie();

			ArticleVendu art = new ArticleVendu(nomArticle, description, dateDebutEncheres, dateFinEncheres,
					prixInitial, prixVente, noVendeur, noAcheteur, 1);

			mger.ajouterArticle(art);
			request.setAttribute("success",
					"L'article a bien été mis en vente et l'enchère commencera à la date prévue");
			doGet(request, response);

		} catch (BusinessException ex) {
			request.setAttribute("error", ex.getMessage());
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/vendreUnArticle.jsp");
			rd.forward(request, response);
		}

	}

}
