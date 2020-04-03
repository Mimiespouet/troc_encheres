package fr.eni.serdaigle.dal;

import fr.eni.serdaigle.bo.ArticleVendu;
import fr.eni.serdaigle.exception.BusinessException;

/**
 * Classe en charge de gérer les Articles en vente
 * @author Jean-François
 * @version troc_encheres - v1.0
 * @date 26 mars 2020
 */
public interface ArticleDAO {
	/**
	 * Méthode en charge d'inserer un article avec une adresse de retrait
	 * @param article
	 * @return article
	 * @throws BusinessException
	 */
	int insertArticleRetrait(ArticleVendu article) throws BusinessException;
	
	/**
	 * Méthode en charge d'insérer un article
	 * @param article
	 * @return article
	 * @throws BusinessException
	 */
	int insertArticle(ArticleVendu article) throws BusinessException;
	
	/**
	 * Méthode en charge de mettre à jour un article
	 * @param article
	 * @throws BusinessException
	 */
	void update(ArticleVendu article) throws BusinessException;

	/**
	 * Méthode en charge de retourner un article par son numéro
	 * @param noArticle
	 * @return ArticleVendu
	 */
	public ArticleVendu select(int noArticle) throws BusinessException;
}
