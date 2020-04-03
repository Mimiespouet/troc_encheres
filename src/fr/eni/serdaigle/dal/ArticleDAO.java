package fr.eni.serdaigle.dal;

import fr.eni.serdaigle.bo.ArticleVendu;
import fr.eni.serdaigle.exception.BusinessException;

public interface ArticleDAO {
	int insertArticleRetrait(ArticleVendu article) throws BusinessException;
	
	int insertArticle(ArticleVendu article) throws BusinessException;
	
	void update(ArticleVendu article) throws BusinessException;

	/**
	 * Méthode en charge de retourner un article par son numéro
	 * @param noArticle
	 * @return ArticleVendu
	 */
	public ArticleVendu select(int noArticle) throws BusinessException;
}
