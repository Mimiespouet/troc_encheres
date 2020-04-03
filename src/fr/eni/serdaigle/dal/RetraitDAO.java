/**
 * 
 */
package fr.eni.serdaigle.dal;


import fr.eni.serdaigle.bo.Retrait;
import fr.eni.serdaigle.exception.BusinessException;

/**
 * Classe en charge de gérer les retraits en BDD
 * @author serdaigle
 * @version Trocencheres - v1.0
 * @date 1 avril 2020
 */
public interface RetraitDAO {
	
	/**
	 * Méthode en charge d'insérer un nouveau retrait en BDD
	 * @param retrait
	 * @throws BusinessException
	 */
	int insert(Retrait retrait) throws BusinessException;
	
	
}
