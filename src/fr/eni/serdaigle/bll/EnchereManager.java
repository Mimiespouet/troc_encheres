package fr.eni.serdaigle.bll;

import java.time.LocalDateTime;

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
		validerDateEnchere(enchere.getDateEnchere(), be);
		validerMontantEnchere(enchere.getMontantEnchere(), be);
	
		
		if (!be.hasErreurs()) {
			enchereDAO.insert(enchere);
		} else {
			throw be;
		}

	}
	private void validerDateEnchere(LocalDateTime dateEnchere, BusinessException be) {
		if(dateEnchere == null) {
			be.ajouterErreur(CodesResultatBLL.DATE_ERREUR);
		}
	}
	
	

	public void validerMontantEnchere(int prixInitial, BusinessException be) {
		if (prixInitial == 0) {
			be.ajouterErreur(CodesResultatBLL.PRIX_ERREUR);
		}
	}

}