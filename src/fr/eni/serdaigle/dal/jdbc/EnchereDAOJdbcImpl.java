/**
 * 
 */
package fr.eni.serdaigle.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import fr.eni.serdaigle.bo.Enchere;
import fr.eni.serdaigle.dal.CodesResultatDAL;
import fr.eni.serdaigle.dal.ConnectionProvider;
import fr.eni.serdaigle.dal.EnchereDAO;
import fr.eni.serdaigle.exception.BusinessException;

/**
 * Classe en charge de
 * @author cracb
 * @version ProjetTrocEncheres - v1.0
 * @date 27 mars 2020
 */
public class EnchereDAOJdbcImpl implements EnchereDAO{
	private static final String INSERT = "INSERT INTO ENCHERES(no_utilisateur,no_article,date_enchere,montant_enchere) VALUES (?,?,?,?);";
	private static final String SELECT_BY = "SELECT FROM ENCHERES WHERE";
	
	
	/**
	 * {@inheritDoc}
	 * @see fr.eni.serdaigle.dal.EnchereDAO#insert(fr.eni.serdaigle.bo.Enchere)
	 */
	@Override
	public void insert(Enchere enchere) throws BusinessException {
		Connection cnx = null;
		BusinessException be = new BusinessException();
		try {
			cnx = ConnectionProvider.getConnection();
			PreparedStatement psmt = cnx.prepareStatement(INSERT);
			psmt.setInt(1, enchere.getUtilisateur().getNoUtilisateur());
			psmt.setInt(2, enchere.getArticle().getNoArticle());
			psmt.setTimestamp(3, Timestamp.valueOf(enchere.getDateEnchere()));
			psmt.setInt(4, enchere.getMontantEnchere());
			psmt.executeUpdate();
			psmt.close();


		} catch (Exception e) {
			e.printStackTrace();
			be.ajouterErreur(CodesResultatDAL.INSERT_ENCHERE_ECHEC);
		} finally {
			try {
				cnx.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if (be.hasErreurs()) {
				throw be;
			}
		}

	}
	
	
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
