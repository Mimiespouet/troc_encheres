/**
 * 
 */
package fr.eni.serdaigle.dal.jdbc;

import java.util.List;

import fr.eni.serdaigle.bo.Enchere;
import fr.eni.serdaigle.dal.EnchereDAO;
import fr.eni.serdaigle.exception.BusinessException;

/**
 * Classe en charge de
 * @author cracb
 * @version ProjetTrocEncheres - v1.0
 * @date 27 mars 2020
 */
public class EnchereDAOJdbcImpl implements EnchereDAO{
	private static final String SELECT_BY = "SELECT FROM ENCHERES WHERE";
	
	
	/**
	 * {@inheritDoc}
	 * @see fr.eni.serdaigle.dal.EnchereDAO#select(fr.eni.serdaigle.bo.Enchere)
	 */
	@Override
	public List<Enchere> select(Enchere enchere) throws BusinessException {
		return null;
	}

	/**
	 * {@inheritDoc}
	 * @see fr.eni.serdaigle.dal.EnchereDAO#selectById(int)
	 */
	@Override
	public Enchere selectBy(int ?) throws BusinessException {
		return null;
	}

}
