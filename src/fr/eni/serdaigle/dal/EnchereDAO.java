/**
 * 
 */
package fr.eni.serdaigle.dal;

import java.util.List;

import fr.eni.serdaigle.bo.ArticleVendu;
import fr.eni.serdaigle.bo.Enchere;
import fr.eni.serdaigle.exception.BusinessException;

/**
 * Classe en charge de
 * @author cracb
 * @version ProjetTrocEncheres - v1.0
 * @date 27 mars 2020
 */
public interface EnchereDAO {
	
	void insert(Enchere enchere) throws BusinessException;
	
	List<Enchere> select(Enchere enchere) throws BusinessException;

	/**
	 * Méthode en charge de
	 * @param noArticle
	 * @return
	 * @throws BusinessException
	 */
	Enchere select(int noArticle) throws BusinessException;
	
	/**
	 * Méthode en charge de remporter une enchère
	 * @param noArticle
	 * @return l'enchère remporté
	 * @throws BusinessException
	 */
	Enchere selectVenteRemporte(int noArticle) throws BusinessException;
}
