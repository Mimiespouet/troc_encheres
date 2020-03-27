/**
 * 
 */
package fr.eni.serdaigle.dal;

import java.util.List;

import fr.eni.serdaigle.bo.Enchere;
import fr.eni.serdaigle.exception.BusinessException;

/**
 * Classe en charge de
 * @author cracb
 * @version ProjetTrocEncheres - v1.0
 * @date 27 mars 2020
 */
public interface EnchereDAO {
	List<Enchere> select(Enchere enchere) throws BusinessException;
	
}
