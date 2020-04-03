package fr.eni.serdaigle.dal;

import fr.eni.serdaigle.bo.ArticleVendu;
import fr.eni.serdaigle.exception.GeneralException;

/**
 * Classe en charge de gérer les Articles en vente en BDD
 * @author serdaigle
 * @version troc_encheres - v1.0
 * @date 26 mars 2020
 */
public interface ArticleDAO {
	/**
	 * Méthode en charge d'inserer un nouvel article avec une adresse de retrait
	 * @param article
	 * @return article
	 * @throws GeneralException
	 */
	int insertArticleRetrait(ArticleVendu article) throws GeneralException;
	
	/**
	 * Méthode en charge d'insérer un nouvel article
	 * @param article
	 * @return article
	 * @throws GeneralException
	 */
	int insertArticle(ArticleVendu article) throws GeneralException;
	
	/**
	 * Méthode en charge de mettre à jour/modifier un article
	 * @param article
	 * @throws GeneralException
	 */
	void update(ArticleVendu article) throws GeneralException;

	/**
	 * Méthode en charge de retourner un article
	 * @param noArticle
	 * @return ArticleVendu
	 */
	public ArticleVendu select(int noArticle) throws GeneralException;
}
