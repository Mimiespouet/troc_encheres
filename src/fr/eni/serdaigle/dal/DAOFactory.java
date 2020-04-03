package fr.eni.serdaigle.dal;

import fr.eni.serdaigle.dal.jdbc.ArticleDAOJdbcImpl;
import fr.eni.serdaigle.dal.jdbc.CategorieDAOJdbcImpl;
import fr.eni.serdaigle.dal.jdbc.EnchereDAOJdbcImpl;
import fr.eni.serdaigle.dal.jdbc.UtilisateurDAOJdbcImpl;

/**
 * Classe en charge de retourner les objets Utilisateur, l'Article, la Catéorie et l'Enchere interagissant avec la BDD 
 * @author serdaigle
 * @version troc_encheres - v1.0
 * @date 26 mars 2020
 */
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
	
	public static CategorieDAO getCategorieDAO() {
		return new CategorieDAOJdbcImpl();
	}
		
}