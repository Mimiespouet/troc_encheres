package fr.eni.serdaigle.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import fr.eni.serdaigle.bo.ArticleVendu;
import fr.eni.serdaigle.dal.ArticleDAO;
import fr.eni.serdaigle.dal.CodesResultatDAL;
import fr.eni.serdaigle.dal.ConnectionProvider;
import fr.eni.serdaigle.exception.BusinessException;

public class ArticleDAOJdbcImpl implements ArticleDAO{

	private static final String INSERT = "INSERT INTO ARTICLES_VENDUS(nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, no_vendeur, no_categorie) VALUES (?,?,?,?,?,?,?,?);";
	private static final String UPDATE = "UPDATE ARTICLES_VENDUS SET nom_article=?, description=?, date_debut_encheres=?, date_fin_encheres=?, prix_initial=?, prix_vente=?, no_vendeur=?, no_categorie=? WHERE no_article=?;";
/*
 * 	SELECT av.* FROM ARTICLES_VENDUS av
 * WHERE av.no_categorie = ? AND av.nom_article LIKE ?
 * AND SYSDATETIME() BETWEEN date_debut_encheres AND date_fin_encheres --les encheres en cours "open" form
 */
	
	@Override
	public void insert(ArticleVendu article) throws BusinessException {
		Connection cnx = null;
		BusinessException be = new BusinessException();
		try {
			cnx = ConnectionProvider.getConnection();
			PreparedStatement psmt = cnx.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
			psmt.setString(1, article.getNomArticle());
			psmt.setString(2, article.getDescription());
			psmt.setTimestamp(3, Timestamp.valueOf(article.getDateDebutEncheres()));
			psmt.setTimestamp(4, Timestamp.valueOf(article.getDateFinEncheres()));
			psmt.setInt(5, article.getPrixInitial());
			psmt.setInt(6, article.getPrixVente());
			psmt.setInt(7, article.getVendeur().getNoUtilisateur());
			psmt.setInt(8, article.getCategorie().getNoCategorie());

			int nombreArticleInsere = psmt.executeUpdate();
			if (nombreArticleInsere == 1) {
				ResultSet rs = psmt.getGeneratedKeys();
				if (rs.next()) {
					article.setNoArticle(rs.getInt(1));
				}
				rs.close();

			} else {
				be.ajouterErreur(CodesResultatDAL.INSERT_ARTICLE_ECHEC);
			}
			psmt.close();

		} catch (Exception e) {
			e.printStackTrace();
			be.ajouterErreur(CodesResultatDAL.INSERT_ARTICLE_ECHEC);
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
	
	
	public void update(ArticleVendu article) throws BusinessException{
		
		Connection cnx = null;
		BusinessException be = new BusinessException();
		try {
			cnx = ConnectionProvider.getConnection();
			PreparedStatement psmt = cnx.prepareStatement(UPDATE);
			psmt.setString(1, article.getNomArticle());
			psmt.setString(2, article.getDescription());
			psmt.setTimestamp(3, Timestamp.valueOf(article.getDateDebutEncheres()));
			psmt.setTimestamp(4, Timestamp.valueOf(article.getDateFinEncheres()));
			psmt.setInt(5, article.getPrixInitial());
			psmt.setInt(6, article.getPrixVente());
			psmt.setInt(7, article.getVendeur().getNoUtilisateur());
			psmt.setInt(8, article.getCategorie().getNoCategorie());
			psmt.executeUpdate();
			psmt.close();

		} catch (Exception e) {
			e.printStackTrace();
			be.ajouterErreur(CodesResultatDAL.UPDATE_ARTICLE_ECHEC);
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
	 * @see fr.eni.serdaigle.dal.ArticleDAO#rechercherArticles(java.lang.String, java.lang.String, java.lang.String[])
	 */
	@Override
	public List<ArticleVendu> rechercherArticles(String categorie, String nomArticle, String[] checkedEtat)
			throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}
	
}