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
 * Classe en charge de gérer les enchères sur les articles mis en vente en BDD
 * @author serdaigle
 * @version ProjetTrocEncheres - v1.0
 * @date 27 mars 2020
 */
public interface EnchereDAO {
	
	/**
	 * Méthode en charge d'inserer une nouvelle enchère
	 * @param enchere
	 * @throws BusinessException
	 */
	void insert(Enchere enchere) throws BusinessException;
	
	/**
	 * Méthode en charge de récupérer toutes les encheres en cours pour affichage sur la page d'accueil
	 * @return Liste Enchere
	 * @throws BusinessException
	 */
	List<Enchere> selectAllEnCours(String categorie, String recherche) throws BusinessException;

	/**
	 * Méthode en charge de retourner un article 
	 * @param noArticle
	 * @return numero article
	 * @throws BusinessException
	 */
	Enchere select(int noArticle) throws BusinessException;
	
	/**
	 * Méthode en charge de retourner une enchère remportée
	 * @param noArticle
	 * @return l'enchère remporté
	 * @throws BusinessException
	 */
	Enchere selectVenteRemporte(int noArticle) throws BusinessException;


	/**
	 * Méthode en charge de selectionner les enchères sur lesquelles l'utilisateur a deja encherit
	 * @param categorie
	 * @param recherche
	 * @param noUtilisateur
	 * @return Liste Enchere
	 * @throws BusinessException
	 */
	List<Enchere> selectMesEncheres(String categorie, String recherche, int noUtilisateur) throws BusinessException;
	
	/**
	 * Méthode en charge de selectionner les ventes non débutées d'un utilisateur
	 * @param categorie
	 * @param recherche
	 * @param noUtilisateur
	 * @return Liste Enchere
	 * @throws BusinessException
	 */
	List<Enchere> selectMesVentesNonDebutees(String categorie, String recherche, int noUtilisateur)
			throws BusinessException;
	
	/**
	 * Méthode en charge de selectionner les ventes en cours d'un utilisateur
	 * @param categorie
	 * @param recherche
	 * @param noUtilisateur
	 * @return categorie, recherche, numéro d'utilisateur
	 * @throws BusinessException
	 */
	List<Enchere> selectMesVentes(String categorie, String recherche, int noUtilisateur) throws BusinessException;
	
	/**
	 * Méthode en charge de selectionner les ventes terminées d'un utilisateur
	 * @param categorie
	 * @param recherche
	 * @param noUtilisateur
	 * @return categorie, recherche, numéro d'utilisateur
	 * @throws BusinessException
	 */
	List<Enchere> selectMesVentesTerminees(String categorie, String recherche, int noUtilisateur)
			throws BusinessException;
	
	/**
	 * Méthode en charge de selectionner une enchère selon le numéro d'utilisateur et le numéro d'article
	 * @param noUtilisateur, noArticle
	 * @throws BusinessException
	 */
	Enchere selectByUtilisateur(int noUtilisateur,int noArticle) throws BusinessException;

	/**
	 * Méthode en charge de mettre à jour une enchere
	 * @param enchere
	 * @throws BusinessException
	 */
	void updateEnchere(Enchere enchere) throws BusinessException;

	

	

	

}
