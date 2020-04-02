/**
 * 
 */
package fr.eni.serdaigle.dal;

import java.util.List;

import fr.eni.serdaigle.bo.ArticleVendu;
import fr.eni.serdaigle.bo.Enchere;
import fr.eni.serdaigle.bo.Utilisateur;
import fr.eni.serdaigle.exception.BusinessException;

/**
 * Classe en charge de
 * @author cracb
 * @version ProjetTrocEncheres - v1.0
 * @date 27 mars 2020
 */
public interface EnchereDAO {
	
	void insert(Enchere enchere) throws BusinessException;
	
	/**
	 * Méthode en charge de récupérer toutes les encheres en cours pour affichage sur la page d'accueil
	 * @return Liste Enchere
	 * @throws BusinessException
	 */
	List<Enchere> selectAllEnCours() throws BusinessException;

	/**
	 * Méthode en charge de
	 * @param noArticle
	 * @return
	 * @throws BusinessException
	 */
	Enchere select(int noArticle) throws BusinessException;
	
	/**
	 * Méthode en charge de remporter une enchère
	 * @param noArticle
	 * @return l'enchère remporté
	 * @throws BusinessException
	 */
	Enchere selectVenteRemporte(int noArticle) throws BusinessException;


	
	/**
	 * Méthode en charge de
	 * @param noUtilisateur, noArticle
	 * @throws BusinessException
	 */
	Enchere selectByUtilisateur(int noUtilisateur,int noArticle) throws BusinessException;

	/**
	 * Méthode en charge de
	 * @param enchere
	 * @throws BusinessException
	 */
	void updateEnchere(Enchere enchere) throws BusinessException;

}
