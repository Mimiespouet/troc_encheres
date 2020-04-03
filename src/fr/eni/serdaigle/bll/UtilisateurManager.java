package fr.eni.serdaigle.bll;

import fr.eni.serdaigle.bo.Utilisateur;
import fr.eni.serdaigle.dal.DAOFactory;
import fr.eni.serdaigle.dal.UtilisateurDAO;
import fr.eni.serdaigle.exception.BusinessException;

public class UtilisateurManager {
	private UtilisateurDAO utilisateurDAO;
	private static final int CHAMPS_VARCHAR_30 = 30;
	private static final int CHAMPS_VARCHAR_20 = 20;
	private static final int CHAMPS_VARCHAR_15 = 15;
	private static final int CHAMPS_VARCHAR_10 = 10;

	/**
	 * Constructeur par défaut
	 */
	public UtilisateurManager() {
		utilisateurDAO = DAOFactory.getUtilisateurDAO();
	}

	/**
	 * Méthode en charge de permettre l'ajout (l'inscription) d'un utilisateur
	 * @param utilisateur
	 * @throws BusinessException
	 */
	public void ajouterUtilisateur(Utilisateur utilisateur) throws BusinessException {
		BusinessException be = new BusinessException();
		// valider les champs

		validerChamps(utilisateur.getPseudo(), CHAMPS_VARCHAR_30, be);
		validerChamps(utilisateur.getNom(), CHAMPS_VARCHAR_30, be);
		validerChamps(utilisateur.getPrenom(), CHAMPS_VARCHAR_30, be);
		validerChamps(utilisateur.getEmail(), CHAMPS_VARCHAR_20, be);
		validerChamps(utilisateur.getTelephone(), CHAMPS_VARCHAR_15, be);
		validerChamps(utilisateur.getRue(), CHAMPS_VARCHAR_30, be);
		validerChamps(utilisateur.getCodePostal(), CHAMPS_VARCHAR_10, be);
		validerChamps(utilisateur.getVille(), CHAMPS_VARCHAR_30, be);
		validerChamps(utilisateur.getMotDePasse(), CHAMPS_VARCHAR_30, be);

		if (!be.hasErreurs()) {
			utilisateurDAO.insert(utilisateur);
		} else {
			throw be;
		}
	}

	/**
	 * Méthode en charge de permettre la connexion selon le pseudo et le mot de passe de l'utilisateur
	 * @param identifiant
	 * @param password
	 * @return utilisateurDAO.selectConnexion(identifiant, password)
	 * @throws BusinessException
	 */
	public Utilisateur selectionnerConnexion(String identifiant, String password) throws BusinessException {
		return utilisateurDAO.selectConnexion(identifiant, password);
	}

	/**
	 * Méthode en charge de selectionner un utilisateur selon son pseudo
	 * @param pseudo
	 * @return utilisateurDAO.selectPseudo(pseudo)
	 * @throws BusinessException
	 */
	public Utilisateur selectionnerUtilisateur(String pseudo) throws BusinessException {
		return utilisateurDAO.selectPseudo(pseudo);
	}

	/**
	 * Méthode en charge de valider les champs de formulaire en remplissant tout correctement
	 * @param champs
	 * @param varchar
	 * @param be
	 * @return null
	 */
	public Utilisateur validerChamps(String champs, int varchar, BusinessException be) {
		if (champs == null || champs.equals("")) {
			be.ajouterErreur(CodesResultatBLL.CHAMP_OBLIGATOIRE);
		}
		if (champs.length() > varchar) {
			be.ajouterErreur(CodesResultatBLL.CHAMP_TAILLE_DEPASSEE);
		}

		return null;
	}

	/**
	 * Méthode en charge de modifier son profil 
	 * @param utilisateur
	 * @throws BusinessException
	 */
	public void modifierUtilisateur(Utilisateur utilisateur) throws BusinessException {
		BusinessException be = new BusinessException();
		// valider les champs

		validerChamps(utilisateur.getPseudo(), CHAMPS_VARCHAR_30, be);
		validerChamps(utilisateur.getNom(), CHAMPS_VARCHAR_30, be);
		validerChamps(utilisateur.getPrenom(), CHAMPS_VARCHAR_30, be);
		validerChamps(utilisateur.getEmail(), CHAMPS_VARCHAR_20, be);
		validerChamps(utilisateur.getTelephone(), CHAMPS_VARCHAR_15, be);
		validerChamps(utilisateur.getRue(), CHAMPS_VARCHAR_30, be);
		validerChamps(utilisateur.getCodePostal(), CHAMPS_VARCHAR_10, be);
		validerChamps(utilisateur.getVille(), CHAMPS_VARCHAR_30, be);
		validerChamps(utilisateur.getMotDePasse(), CHAMPS_VARCHAR_30, be);

		if (!be.hasErreurs()) {
			utilisateurDAO.update(utilisateur);
		} else {
			throw be;
		}
	}

	/**
	 * Méthode en charge de supprimer l'utilisateur 
	 * @param utilisateur
	 * @throws BusinessException
	 */
	public void supprimerUtilisateur(Utilisateur utilisateur) throws BusinessException {
		utilisateurDAO.delete(utilisateur);
	}
}
