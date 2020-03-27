package fr.eni.serdaigle.servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.serdaigle.bll.ArticleManager;
import fr.eni.serdaigle.bll.CategorieManager;
import fr.eni.serdaigle.bo.Categorie;
import fr.eni.serdaigle.exception.BusinessException;


@WebServlet("/vendreArticle")
public class VendreUnArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
    public void init() throws ServletException {

        List<Categorie> listeCategories = new ArrayList<Categorie>();

        try {
            CategorieManager categorieManager = new CategorieManager();
            listeCategories = categorieManager.selectionnerToutesLesCategories();

        } catch (BusinessException e) {
            e.printStackTrace();
        }

        this.getServletContext().setAttribute("categories", listeCategories);
        super.init();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/vendreUnArticle.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ArticleManager mger = new ArticleManager();
		String nomArticle = request.getParameter("nomArticle");
		String description = request.getParameter("description");
		LocalDate dateDebutEncheres = null;
		LocalDate dateFinEncheres = null;
		int prixInitial = Integer.valueOf(request.getParameter("prixInitial"));
		int prixVente = Integer.valueOf(request.getParameter("prixVente"));
		int vendeur = Integer.valueOf(request.getParameter("vendeur"));
		int acheteur = Integer.valueOf(request.getParameter("acheteur"));
		// int categorie = ;
		List<Integer> listeCodesErreur = new ArrayList<>();
		try {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			dateDebutEncheres = LocalDate.parse(request.getParameter("dateDebutEncheres"), dtf);

		} catch (DateTimeParseException e) {
			e.printStackTrace();
			listeCodesErreur.add(CodesResultatServlets.FORMAT_DATE_DEBUT_ERREUR);
		}
		try {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			dateFinEncheres = LocalDate.parse(request.getParameter("dateFinEncheres"), dtf);

		} catch (DateTimeParseException e) {
			e.printStackTrace();
			listeCodesErreur.add(CodesResultatServlets.FORMAT_DATE_FIN_ERREUR);
		}
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/vendreunarticle.jsp");
		rd.forward(request, response);
	}

}
