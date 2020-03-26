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

	public UtilisateurManager() {
		utilisateurDAO = DAOFactory.getUtilisateurDAO();
	}

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

	public Utilisateur selectionnerConnexion(String identifiant, String password) throws BusinessException {
		return utilisateurDAO.selectConnexion(identifiant, password);
	}

	public Utilisateur selectionnerUtilisateur(String pseudo) throws BusinessException {
		return utilisateurDAO.selectPseudo(pseudo);
	}

//	public Utilisateur supprimerUtilisateur(Utilisateur utilisateur) throws BusinessException {
//		return utilisateurDAO.selectPseudo(utilisateur.getNoUtilisateur());
//	}

	public Utilisateur validerChamps(String champs, int varchar, BusinessException be) {
		if (champs == null || champs.equals("")) {
			be.ajouterErreur(CodesResultatBLL.CHAMP_OBLIGATOIRE);
		}
		if (champs.length() > varchar) {
			be.ajouterErreur(CodesResultatBLL.CHAMP_TAILLE_DEPASSEE);
		}

		return null;
	}

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

	public void supprimerUtilisateur(Utilisateur utilisateur) throws BusinessException {
		utilisateurDAO.delete(utilisateur);
	}
}
