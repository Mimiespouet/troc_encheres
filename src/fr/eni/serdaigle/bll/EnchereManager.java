package fr.eni.serdaigle.bll;

import java.util.List;

import fr.eni.serdaigle.bo.Enchere;
import fr.eni.serdaigle.dal.DAOFactory;
import fr.eni.serdaigle.dal.EnchereDAO;
import fr.eni.serdaigle.exception.BusinessException;

public class EnchereManager {
	private EnchereDAO enchereDAO;

	public EnchereManager() {
		enchereDAO = DAOFactory.getEnchereDAO();
	}

	public void ajouterEnchere(Enchere enchere)
			throws BusinessException {
		BusinessException be = new BusinessException();

//valider les champs
		validerDateEnchere(enchere, be);
		validerMontantEnchere(enchere, be);
	
		
		if (!be.hasErreurs()) {
			enchereDAO.insert(enchere);
		} else {
			throw be;
		}

	}
	
	public Enchere select(int noArticle) throws BusinessException {
		return enchereDAO.select(noArticle);

	}
	
	public Enchere selectVenteRemporte(int noArticle) throws BusinessException{
		return enchereDAO.selectVenteRemporte(noArticle);
	}
	
	
	public void updateEnchere(Enchere enchere) throws BusinessException{
		BusinessException be = new BusinessException();
		if (!be.hasErreurs()) {
			enchereDAO.updateEnchere(enchere);
		} else {
			throw be;
		}
	}
	
	public Enchere selectByUtilisateur(int noUtilisateur,int noArticle) throws BusinessException{
		return enchereDAO.selectByUtilisateur(noUtilisateur,noArticle);
	}
	

	public List<Enchere> selectAllEnCours(String categorie, String recherche) throws BusinessException{
		return enchereDAO.selectAllEnCours(categorie, recherche);
	}
	
	public List<Enchere> selectMesEncheres(String categorie, String recherche, int noUtilisateur) throws BusinessException {
		return enchereDAO.selectMesEncheres(categorie, recherche, noUtilisateur);
	}
	
	public List<Enchere> selectMesVentes(String categorie, String recherche, int noUtilisateur) throws BusinessException {
		return enchereDAO.selectMesVentes(categorie, recherche, noUtilisateur);
	}
	
	public List<Enchere> selectMesVentesNonDebutees(String categorie, String recherche, int noUtilisateur) throws BusinessException {
		return enchereDAO.selectMesVentesNonDebutees(categorie, recherche, noUtilisateur);
	}
	
	public List<Enchere> selectMesVentesTerminees(String categorie, String recherche, int noUtilisateur) throws BusinessException {
		return enchereDAO.selectMesVentesTerminees(categorie, recherche, noUtilisateur);
	}
	
	
	
	public void validerMontantEnchere(Enchere enchere, BusinessException be) {
		if (enchere.getMontantEnchere() == 0) {
			be.ajouterErreur(CodesResultatBLL.PRIX_ERREUR);
		}
	}
	
	private void validerDateEnchere(Enchere enchere, BusinessException be) {
		if(enchere.getDateEnchere().isAfter(enchere.getArticle().getDateFinEncheres()) 
				|| enchere.getDateEnchere().isBefore(enchere.getArticle().getDateDebutEncheres()) ){
			be.ajouterErreur(CodesResultatBLL.DATE_ERREUR);
		}
	}

}