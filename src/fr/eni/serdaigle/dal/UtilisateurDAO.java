package fr.eni.serdaigle.dal;

import java.util.List;

import fr.eni.serdaigle.bo.Utilisateur;
import fr.eni.serdaigle.exception.GeneralException;

/**
 * Classe en charge de gérer les utilisateur en BDD
 * @author serdaigle
 * @version troc_encheres - v1.0
 * @date 26 mars 2020
 */
public interface UtilisateurDAO {
	/**
	 * Méthode en charge d'inserer un nouvel utilisateur
	 * @param utilisateur
	 * @throws GeneralException
	 */
	void insert(Utilisateur utilisateur) throws GeneralException;
	
	/**
	 * Méthode en charge de supprimer un utilisateur
	 * @param utilisateur
	 * @throws GeneralException
	 */
	void delete(Utilisateur utilisateur) throws GeneralException;
	
	/**
	 * Méthode en charge de mettre à jour un utilisateur
	 * @param utilisateur
	 * @throws GeneralException
	 */
	void update(Utilisateur utilisateur) throws GeneralException;

	/**
	 * Méthode en charge de retourner plusieurs utilisateurs
	 * @return List<Utilisateur> select()
	 * @throws GeneralException
	 */
	List<Utilisateur> select() throws GeneralException;
	
	/**
	 * Méthode en charge de récupérer le pseudo et le mot de passe de l'utilisateur pour la connexion à son compte
	 * @param identifiant
	 * @param password
	 * @return pseudo, mot de passe
	 * @throws GeneralException
	 */
	Utilisateur selectConnexion(String identifiant, String password) throws GeneralException;
	
	/**
	 * Méthode en charge de selectionner un utilisateur par son pseudo
	 * @param pseudo
	 * @return pseudo, mot de passe
	 * @throws GeneralException
	 */
	Utilisateur selectPseudo(String pseudo) throws GeneralException;

}
