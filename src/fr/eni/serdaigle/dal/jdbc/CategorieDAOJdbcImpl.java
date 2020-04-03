package fr.eni.serdaigle.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import fr.eni.serdaigle.bo.ArticleVendu;
import fr.eni.serdaigle.bo.Categorie;
import fr.eni.serdaigle.bo.Enchere;
import fr.eni.serdaigle.bo.Utilisateur;
import fr.eni.serdaigle.dal.ArticleDAO;
import fr.eni.serdaigle.dal.CategorieDAO;
import fr.eni.serdaigle.dal.CodesResultatDAL;
import fr.eni.serdaigle.dal.ConnectionProvider;
import fr.eni.serdaigle.exception.BusinessException;

public class CategorieDAOJdbcImpl implements CategorieDAO {

	private static final String INSERT = "INSERT INTO CATEGORIE(libelle) VALUES (?);";
	private static final String SELECT_ALL = "SELECT no_categorie, libelle FROM CATEGORIES;";
	private static final String SELECT_BY_LIBELLE = "SELECT no_categorie, libelle FROM CATEGORIES WHERE libelle LIKE ?;";
		
	/**
	 * {@inheritDoc}
	 * @see fr.eni.serdaigle.dal.CategorieDAO#insert(fr.eni.serdaigle.bo.Categorie)
	 */
	@Override
	public void insert(Categorie categorie) throws BusinessException {
		Connection cnx = null;
		BusinessException be = new BusinessException();
		try {
			cnx = ConnectionProvider.getConnection();
			PreparedStatement psmt = cnx.prepareStatement(INSERT);
			psmt.setString(1, categorie.getLibelle());
			psmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			be.ajouterErreur(CodesResultatDAL.INSERT_CATEGORIE_ECHEC);
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
	 * @see fr.eni.serdaigle.dal.CategorieDAO#selectAll()
	 */
	public List<Categorie> selectAll() throws BusinessException {
		Connection cnx = null;
		BusinessException be = new BusinessException();
		List<Categorie> listeCategorie = new ArrayList<Categorie>();
		try {
			cnx = ConnectionProvider.getConnection();
			Statement smt = cnx.createStatement();
			ResultSet rs = smt.executeQuery(SELECT_ALL);
			while (rs.next()) {
				Categorie categorie = Mapping.mappingCategorie(rs);
				listeCategorie.add(categorie);
			}
			smt.close();
		} catch (SQLException e) {
			be.ajouterErreur(CodesResultatDAL.SELECT_CATEGORIE_ECHEC);
		} finally {
			try {
				cnx.close();
			} catch (SQLException e) {
				be.ajouterErreur(CodesResultatDAL.SELECT_CATEGORIE_ECHEC);
			}
			if (be.hasErreurs()) {
				throw be;
			}
		}
		return listeCategorie;
	}

	/**
	 * {@inheritDoc}
	 * @see fr.eni.serdaigle.dal.CategorieDAO#selectByLibelle(java.lang.String)
	 */
	@Override
	public Categorie selectByLibelle(String libelle) throws BusinessException {
		BusinessException be = new BusinessException();
		Categorie categorie = null;
		try (Connection cnx = ConnectionProvider.getConnection();
			PreparedStatement psmt = cnx.prepareStatement(SELECT_BY_LIBELLE);){
			psmt.setString(1, "%"+libelle+"%");
			ResultSet rs = psmt.executeQuery();
			if (rs.next()) {
					categorie = Mapping.mappingCategorie(rs);
				}
		} catch (Exception e) {
			e.printStackTrace();
			be.ajouterErreur(CodesResultatDAL.SELECT_CATEGORIE_ECHEC);
		}
		return categorie;
	}	

}