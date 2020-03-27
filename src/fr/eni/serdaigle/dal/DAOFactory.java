package fr.eni.serdaigle.dal;

import fr.eni.serdaigle.dal.jdbc.ArticleDAOJdbcImpl;
import fr.eni.serdaigle.dal.jdbc.EnchereDAOJdbcImpl;
import fr.eni.serdaigle.dal.jdbc.UtilisateurDAOJdbcImpl;

public abstract class DAOFactory {
	public static UtilisateurDAO getUtilisateurDAO() {
		return new UtilisateurDAOJdbcImpl();
	}
	
	public static ArticleDAO getArticleDAO() {
		return new ArticleDAOJdbcImpl();
	}
	
	public static EnchereDAO getEnchereDAO() {
		return new EnchereDAOJdbcImpl();
	}
}