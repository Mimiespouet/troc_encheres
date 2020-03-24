package fr.eni.serdaigle.bll;

import fr.eni.serdaigle.dal.DAOFactory;
import fr.eni.serdaigle.exception.BusinessException;
import fr.eni.serdaigle.bo.ArticleVendu;
import fr.eni.serdaigle.dal.ArticleDAO;

public class ArticleManager {
	private ArticleDAO articleDAO;
	

	public ArticleManager() {
		articleDAO = DAOFactory.getArticleDAO();
	}

	public ArticleVendu ajouterArticle(String pseudo, String nom, String prenom, String email, String telephone,
			String rue, String codePostal, String ville, String motDePasse)
			throws BusinessException {
		BusinessException be = new BusinessException();
		// valider les champs


		ArticleVendu article = null;

		if (!be.hasErreurs()) {
			article = new ArticleVendu();
			// faire les sets
			

			articleDAO.insert(article);
		} else {
			throw be;
		}
		return article;
	}

}
