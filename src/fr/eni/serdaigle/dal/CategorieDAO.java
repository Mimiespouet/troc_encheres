/**
 * 
 */
package fr.eni.serdaigle.dal;

import java.util.List;

import fr.eni.serdaigle.bo.Categorie;
import fr.eni.serdaigle.exception.BusinessException;

/**
 * Classe en charge de gérer les catégorie en BDD
 * @author Max
 * @version Trocencheres - v1.0
 * @date 26 mars 2020
 */
public interface CategorieDAO {
	/**
	 * Méthode en charge de filtrer les encheres en cours par le libelle de sa catégorie
	 * @param libelle
	 * @return Categorie
	 * @throws BusinessException
	 */
	Categorie selectByLibelle(String libelle) throws BusinessException;
	
	/**
	 * Méthode en charge de sélectionner toutes les catégories présentes en BDD
	 * @return List Categorie 
	 * @throws BusinessException
	 */
	List<Categorie> selectAll() throws BusinessException;

	/**
	 * Méthode en charge d'insérer une nouvelle catégorie en BDD
	 * @param article
	 * @throws BusinessException
	 */
	void insert(Categorie categorie) throws BusinessException;

	
}




