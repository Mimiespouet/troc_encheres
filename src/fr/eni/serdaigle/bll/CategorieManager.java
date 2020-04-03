package fr.eni.serdaigle.bll;

import fr.eni.serdaigle.dal.DAOFactory;
import fr.eni.serdaigle.exception.GeneralException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import fr.eni.serdaigle.bo.ArticleVendu;
import fr.eni.serdaigle.bo.Categorie;
import fr.eni.serdaigle.dal.ArticleDAO;
import fr.eni.serdaigle.dal.CategorieDAO;

/**
 * Classe en charge de gérer l'application des requêtes (CategorieDAO) sur les catégories
 * @author serdaigle
 * @version troc_encheres - v1.0
 * @date 28 mars 2020
 */
public class CategorieManager {
	private CategorieDAO categorieDAO;
	private static final int CHAMPS_VARCHAR_30 = 30;
	
	/**
	 * Constructeur
	 */
	public CategorieManager() {
		categorieDAO = DAOFactory.getCategorieDAO();
	}
	
	/**
	 * Méthode en charge de retourner toutes les catégories afin de les afficher dans le menu déroulant
	 * @return List Categorie
	 * @throws GeneralException
	 */
	public List<Categorie> selectAll() throws GeneralException{
		List<Categorie> listeCategorie = new ArrayList<Categorie>();
		listeCategorie = categorieDAO.selectAll();
		return listeCategorie;
	}
	
}