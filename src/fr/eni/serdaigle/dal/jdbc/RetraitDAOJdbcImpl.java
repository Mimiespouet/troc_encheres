package fr.eni.serdaigle.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;

import fr.eni.serdaigle.bo.Retrait;
import fr.eni.serdaigle.dal.CodesResultatDAL;
import fr.eni.serdaigle.dal.ConnectionProvider;
import fr.eni.serdaigle.dal.RetraitDAO;
import fr.eni.serdaigle.exception.BusinessException;

public class RetraitDAOJdbcImpl implements RetraitDAO {

	private static final String INSERT = "INSERT INTO RETRAITS (no_article,rue, code_postal, ville) VALUES (?,?,?,?);";
	//A REVOIR faire une transaction avec l'insert

	
	
	
	/**
	 * {@inheritDoc}
	 * @see fr.eni.serdaigle.dal.RetraitDAO#insert(fr.eni.serdaigle.bo.Retrait)
	 */
	@Override
	public int insert(Retrait retrait) throws BusinessException {
	//démarre la transaction
		BusinessException be = new BusinessException();
		try (Connection cnx = ConnectionProvider.getConnection();
			PreparedStatement psmt = cnx.prepareStatement(INSERT);) {
			psmt.setInt(1, retrait.getArticle().getNoArticle());
			psmt.setString(2, retrait.getRue());
			psmt.setString(3, retrait.getCodePostal());
			psmt.setString(4, retrait.getVille());
			psmt.executeUpdate();
			psmt.close();

		} catch (Exception e) {
			e.printStackTrace();
			be.ajouterErreur(CodesResultatDAL.INSERT_RETRAIT_ECHEC);
		}
		
		return 0;// A MODIFIER : generated keys
		
	}

	
	
	
}