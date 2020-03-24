package fr.eni.serdaigle.dal;

import fr.eni.serdaigle.bo.ArticleVendu;
import fr.eni.serdaigle.exception.BusinessException;

public interface ArticleDAO {
	void insert(ArticleVendu article) throws BusinessException;


}
