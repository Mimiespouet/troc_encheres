package fr.eni.serdaigle.dal;

import java.util.List;

import fr.eni.serdaigle.bo.ArticleVendu;
import fr.eni.serdaigle.exception.BusinessException;

public interface ArticleDAO {
	void insert(ArticleVendu article) throws BusinessException;

	public List<ArticleVendu> rechercherArticles(String categorie,String nomArticle, String[] checkedEtat)
			throws BusinessException;
}
