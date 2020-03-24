package fr.eni.serdaigle.bll;

import fr.eni.serdaigle.dal.DAOFactory;
import fr.eni.serdaigle.dal.UtilisateurDAO;

import fr.eni.serdaigle.exception.BusinessException;
import fr.eni.serdaigle.bo.Utilisateur;

public class UtilisateurManager {
	private UtilisateurDAO utilisateurDAO;
	private static final int CHAMPS_VARCHAR_30 = 30;
	private static final int CHAMPS_VARCHAR_20 = 20;
	private static final int CHAMPS_VARCHAR_15 = 15;
	private static final int CHAMPS_VARCHAR_10 = 10;

	public UtilisateurManager() {
		utilisateurDAO = DAOFactory.getUtilisateurDAO();
	}

	public Utilisateur ajouterUtilisateur(String pseudo, String nom, String prenom, String email, String telephone,
			String rue, String codePostal, String ville, String motDePasse)
			throws BusinessException {
		BusinessException be = new BusinessException();
		// valider les champs

		validerChamps(pseudo, CHAMPS_VARCHAR_30, be);
		validerChamps(nom, CHAMPS_VARCHAR_30, be);
		validerChamps(prenom, CHAMPS_VARCHAR_30, be);
		validerChamps(email, CHAMPS_VARCHAR_20, be);
		validerChamps(telephone, CHAMPS_VARCHAR_15, be);
		validerChamps(rue, CHAMPS_VARCHAR_30, be);
		validerChamps(codePostal, CHAMPS_VARCHAR_10, be);
		validerChamps(ville, CHAMPS_VARCHAR_30, be);
		validerChamps(motDePasse, CHAMPS_VARCHAR_30, be);

		Utilisateur utilisateur = null;

		if (!be.hasErreurs()) {
			utilisateur = new Utilisateur();

			utilisateur.setPseudo(pseudo);
			utilisateur.setNom(nom);
			utilisateur.setPrenom(prenom);
			utilisateur.setEmail(email);
			utilisateur.setTelephone(telephone);
			utilisateur.setRue(rue);
			utilisateur.setCodePostal(codePostal);
			utilisateur.setVille(ville);
			utilisateur.setMotDePasse(motDePasse);

			utilisateurDAO.insert(utilisateur);
		} else {
			throw be;
		}
		return utilisateur;
	}

	public Utilisateur selectionnerConnexion(String identifiant, String password) throws BusinessException {
		return utilisateurDAO.selectConnexion(identifiant, password);
	}

	public Utilisateur selectionnerUtilisateur(String pseudo) throws BusinessException {
		return utilisateurDAO.selectPseudo(pseudo);
	}

	public Utilisateur validerChamps(String champs, int varchar, BusinessException be) {
		if (champs == null || champs.equals("")) {
			be.ajouterErreur(CodesResultatBLL.CHAMP_OBLIGATOIRE);
		}
		if (champs.length() > varchar) {
			be.ajouterErreur(CodesResultatBLL.CHAMP_TAILLE_DEPASSEE);
		}

		return null;
	}

}
