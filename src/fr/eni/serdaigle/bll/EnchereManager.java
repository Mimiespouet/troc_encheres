package fr.eni.serdaigle.bll;

import java.util.List;

import fr.eni.serdaigle.bo.Enchere;
import fr.eni.serdaigle.dal.DAOFactory;
import fr.eni.serdaigle.dal.EnchereDAO;
import fr.eni.serdaigle.exception.GeneralException;

/**
 * Classe en charge de gérer l'application des requêtes (EnchereDAO) sur les enchères
 * @author serdaigle
 * @version troc_encheres - v1.0
 * @date 28 mars 2020
 */
public class EnchereManager {
	private EnchereDAO enchereDAO;

	/**
	 * Constructeur par défaut
	 */
	public EnchereManager() {
		enchereDAO = DAOFactory.getEnchereDAO();
	}

	/**
	 * Méthode en charge d'ajouter une enchère
	 * @param enchere
	 * @throws GeneralException
	 */
	public void ajouterEnchere(Enchere enchere)
			throws GeneralException {
		GeneralException be = new GeneralException();

		//valider les champs
		validerDateEnchere(enchere, be);
		validerMontantEnchere(enchere, be);
	
		if (!be.hasErreurs()) {
			enchereDAO.insert(enchere);
		} else {
			throw be;
		}

	}
	
	/**
	 * Méthode en charge de selectionner une enchère selon le numéro d'article
	 * @param noArticle
	 * @return enchereDAO.select(noArticle)
	 * @throws GeneralException
	 */
	public Enchere select(int noArticle) throws GeneralException {
		return enchereDAO.select(noArticle);

	}
	
	/**
	 * Méthode en charge de selectionner une vente remportée selon le numéro d'article
	 * @param noArticle
	 * @return enchereDAO.selectVenteRemporte(noArticle)
	 * @throws GeneralException
	 */
	public Enchere selectVenteRemporte(int noArticle) throws GeneralException{
		return enchereDAO.selectVenteRemporte(noArticle);
	}
	
	/**
	 * Méthode en charge de mettre à jour une enchère
	 * @param enchere
	 * @throws GeneralException
	 */
	public void updateEnchere(Enchere enchere) throws GeneralException{
		GeneralException be = new GeneralException();
		if (!be.hasErreurs()) {
			enchereDAO.updateEnchere(enchere);
		} else {
			throw be;
		}
	}
	
	/**
	 * Méthode en charge de selectionner une enchère selon le numéro d'utilisateur et de l'article
	 * @param noUtilisateur
	 * @param noArticle
	 * @return enchereDAO.selectByUtilisateur(noUtilisateur,noArticle)
	 * @throws GeneralException
	 */
	public Enchere selectByUtilisateur(int noUtilisateur,int noArticle) throws GeneralException{
		return enchereDAO.selectByUtilisateur(noUtilisateur,noArticle);
	}
	
	/**
	 * Méthode en charge de selectionner toutes les enchères en cours selon les catégories
	 * @param categorie
	 * @param recherche
	 * @return enchereDAO.selectAllEnCours(categorie, recherche)
	 * @throws GeneralException
	 */
	public List<Enchere> selectAllEnCours(String categorie, String recherche) throws GeneralException{
		return enchereDAO.selectAllEnCours(categorie, recherche);
	}
	
	/**
	 * Méthode en charge de permettre à un utilisateur de consulter toutes ses enchères
	 * @param categorie
	 * @param recherche
	 * @param noUtilisateur
	 * @return enchereDAO.selectMesEncheres(categorie, recherche, noUtilisateur)
	 * @throws GeneralException
	 */
	public List<Enchere> selectMesEncheres(String categorie, String recherche, int noUtilisateur) throws GeneralException {
		return enchereDAO.selectMesEncheres(categorie, recherche, noUtilisateur);
	}
	
	/**
	 * Méthode en charge de permettre à un utilisateur de consulter toutes ses ventes
	 * @param categorie
	 * @param recherche
	 * @param noUtilisateur
	 * @return
	 * @throws GeneralException
	 */
	public List<Enchere> selectMesVentes(String categorie, String recherche, int noUtilisateur) throws GeneralException {
		return enchereDAO.selectMesVentes(categorie, recherche, noUtilisateur);
	}
	
	/**
	 * Méthode en charge de permettre  permettre à un utilisateur de selectionner ses ventes non débutées
	 * @param categorie
	 * @param recherche
	 * @param noUtilisateur
	 * @return enchereDAO.selectMesVentesNonDebutees(categorie, recherche, noUtilisateur)
	 * @throws GeneralException
	 */
	public List<Enchere> selectMesVentesNonDebutees(String categorie, String recherche, int noUtilisateur) throws GeneralException {
		return enchereDAO.selectMesVentesNonDebutees(categorie, recherche, noUtilisateur);
	}
	
	/**
	 * Méthode en charge de permettre à un utilisateur de selectionner ses ventes terminées
	 * @param categorie
	 * @param recherche
	 * @param noUtilisateur
	 * @return enchereDAO.selectMesVentesTerminees(categorie, recherche, noUtilisateur)
	 * @throws GeneralException
	 */
	public List<Enchere> selectMesVentesTerminees(String categorie, String recherche, int noUtilisateur) throws GeneralException {
		return enchereDAO.selectMesVentesTerminees(categorie, recherche, noUtilisateur);
	}
	
	/**
	 * Méthode en charge de valider un montant d'enchère s'il n'est pas négatif
	 * @param enchere
	 * @param be
	 */
	public void validerMontantEnchere(Enchere enchere, GeneralException be) {
		if (enchere.getMontantEnchere() == 0) {
			be.ajouterErreur(CodesResultatBLL.PRIX_ERREUR);
		}
	}
	
	/**
	 * Méthode en charge de valider la date d'une enchère (pas de date de fin avant la date de début)
	 * @param enchere
	 * @param be
	 */
	private void validerDateEnchere(Enchere enchere, GeneralException be) {
		if(enchere.getDateEnchere().isAfter(enchere.getArticle().getDateFinEncheres()) 
				|| enchere.getDateEnchere().isBefore(enchere.getArticle().getDateDebutEncheres()) ){
			be.ajouterErreur(CodesResultatBLL.DATE_ERREUR);
		}
	}

}