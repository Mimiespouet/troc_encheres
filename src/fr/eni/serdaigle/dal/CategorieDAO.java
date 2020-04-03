/**
 * 
 */
package fr.eni.serdaigle.dal;

import java.util.List;

import fr.eni.serdaigle.bo.Categorie;
import fr.eni.serdaigle.exception.GeneralException;

/**
 * Classe en charge de gérer les catégories en BDD
 * @author Max
 * @version Trocencheres - v1.0
 * @date 26 mars 2020
 */
public interface CategorieDAO {	
	/**
	 * Méthode en charge de sélectionner toutes les catégories présentes en BDD
	 * @return List Categorie 
	 * @throws GeneralException
	 */
	List<Categorie> selectAll() throws GeneralException;

	/**
	 * Méthode en charge d'insérer une nouvelle catégorie en BDD par l'administrateur
	 * @param article
	 * @throws GeneralException
	 */
	void insert(Categorie categorie) throws GeneralException;
}




