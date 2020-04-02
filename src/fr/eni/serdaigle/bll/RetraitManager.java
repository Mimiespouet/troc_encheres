/**
 * 
 */
package fr.eni.serdaigle.bll;

import fr.eni.serdaigle.bo.Retrait;
import fr.eni.serdaigle.dal.DAOFactory;
import fr.eni.serdaigle.dal.RetraitDAO;
import fr.eni.serdaigle.exception.BusinessException;

/**
 * Classe en charge de
 * @author delphe
 * @version TrocEncheres - v1.0
 * @date Apr 2, 2020
 */
public class RetraitManager {
	private RetraitDAO retraitDAO;
	private static final int CHAMPS_VARCHAR_15 = 15;
	private static final int CHAMPS_VARCHAR_30 = 30;
	
	
	/**
	 * Constructeur
	 */
	public RetraitManager() {
		retraitDAO = DAOFactory.getRetraitDAO();
	}

	
	public void ajouterRetrait(Retrait retrait) throws BusinessException {
		BusinessException be = new BusinessException();
		
		//valider les champs
		//validerArticle(retrait.getArticle().getNoArticle(),be);    ????
		validerRue(retrait.getRue(), CHAMPS_VARCHAR_30, be);
		validerCodePostal(retrait.getCodePostal(), CHAMPS_VARCHAR_15, be);
		validerVille(retrait.getVille(), CHAMPS_VARCHAR_30,  be);
	}
	
	
	public void validerRue(String rue, int varchar, BusinessException be) {
		if (rue == null || rue.equals("")) {
			be.ajouterErreur(CodesResultatBLL.CHAMP_RUE_OBLIGATOIRE);
		}
		if (rue.length() > varchar) {
			be.ajouterErreur(CodesResultatBLL.CHAMP_RUE_TAILLE_DEPASSEE);
		}

	}
	
	public void validerCodePostal(String cpo, int varchar, BusinessException be) {
		if (cpo == null || cpo.equals("")) {
			be.ajouterErreur(CodesResultatBLL.CHAMP_CPO_OBLIGATOIRE);
		}
		if (cpo.length() > varchar) {
			be.ajouterErreur(CodesResultatBLL.CHAMP_CPO_TAILLE_DEPASSEE);
		}

	}
	
	public void validerVille(String ville, int varchar, BusinessException be) {
		if (ville == null || ville.equals("")) {
			be.ajouterErreur(CodesResultatBLL.CHAMP_VILLE_OBLIGATOIRE);
		}
		if (ville.length() > varchar) {
			be.ajouterErreur(CodesResultatBLL.CHAMP_VILLE_TAILLE_DEPASSEE);
		}

	}
	
}
