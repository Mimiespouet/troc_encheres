package fr.eni.serdaigle.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import fr.eni.serdaigle.bo.ArticleVendu;
import fr.eni.serdaigle.bo.Categorie;
import fr.eni.serdaigle.bo.Enchere;
import fr.eni.serdaigle.bo.Retrait;
import fr.eni.serdaigle.bo.Utilisateur;
import fr.eni.serdaigle.dal.ArticleDAO;
import fr.eni.serdaigle.dal.CodesResultatDAL;
import fr.eni.serdaigle.dal.ConnectionProvider;
import fr.eni.serdaigle.exception.BusinessException;

public class ArticleDAOJdbcImpl implements ArticleDAO {

	private static final String INSERT = "INSERT INTO ARTICLES_VENDUS(nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, no_vendeur, no_categorie) VALUES (?,?,?,?,?,?,?);";
	private static final String UPDATE = "UPDATE ARTICLES_VENDUS SET nom_article=?, description=?, date_debut_encheres=?, date_fin_encheres=?, prix_initial=?, no_vendeur=?, no_categorie=? WHERE no_article=?;";
	private static final String SELECT_BY_ID = "SELECT av.nom_article, av.no_article,c.no_categorie,c.libelle as cate_libelle, av.description, av.no_vendeur, av.prix_initial, av.date_debut_encheres, av.date_fin_encheres, r.rue as retrait_rue, r.ville as retrait_ville, r.code_postal as retrait_cp, u.rue, u.ville, u.code_postal FROM ARTICLES_VENDUS av JOIN CATEGORIES c ON c.no_categorie = av.no_categorie LEFT JOIN RETRAITS r ON av.no_article = r.no_article JOIN UTILISATEURS u ON u.no_utilisateur = av.no_vendeur WHERE av.no_article = ?";
	private static final String INSERT_RETRAIT = "INSERT INTO RETRAITS (no_article,rue, code_postal, ville) VALUES (?,?,?,?);";
	/*
	 * SELECT av.* FROM ARTICLES_VENDUS av WHERE av.no_categorie = ? AND
	 * av.nom_article LIKE ? AND SYSDATETIME() BETWEEN date_debut_encheres AND
	 * date_fin_encheres --les encheres en cours "open" form
	 */

	/**
	 * {@inheritDoc}
	 * 
	 * @see fr.eni.serdaigle.dal.ArticleDAO#insertArticle(fr.eni.serdaigle.bo.ArticleVendu)
	 */
	public int insertArticle(ArticleVendu article) throws BusinessException {
		int noArticle = 0;
		BusinessException be = new BusinessException();
		try (Connection cnx = ConnectionProvider.getConnection();
				PreparedStatement psmt = cnx.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);) {
			psmt.setString(1, article.getNomArticle());
			psmt.setString(2, article.getDescription());
			psmt.setTimestamp(3, Timestamp.valueOf(article.getDateDebutEncheres()));
			psmt.setTimestamp(4, Timestamp.valueOf(article.getDateFinEncheres()));
			psmt.setInt(5, article.getPrixInitial());
			psmt.setInt(6, article.getVendeur().getNoUtilisateur());
			psmt.setInt(7, article.getCategorie().getNoCategorie());

			int nombreArticleInsere = psmt.executeUpdate();
			if (nombreArticleInsere == 1) {
				ResultSet rs = psmt.getGeneratedKeys();
				if (rs.next()) {
					noArticle = rs.getInt(1);
					article.setNoArticle(noArticle);
				} else {
					be.ajouterErreur(CodesResultatDAL.INSERT_ARTICLE_ECHEC);
				}
			}
			psmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return noArticle;

	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see fr.eni.serdaigle.dal.ArticleDAO#insertArticleRetrait(fr.eni.serdaigle.bo.ArticleVendu)
	 */
	@Override
	public int insertArticleRetrait(ArticleVendu article) throws BusinessException {
		Connection cnx = null;
		BusinessException be = new BusinessException();
		int noArticle = 0;
		try {
			cnx = ConnectionProvider.getConnection();
			cnx.setAutoCommit(false);
			// Pour prendre la main sur la transaction
			PreparedStatement psmt = cnx.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
			psmt.setString(1, article.getNomArticle());
			psmt.setString(2, article.getDescription());
			psmt.setTimestamp(3, Timestamp.valueOf(article.getDateDebutEncheres()));
			psmt.setTimestamp(4, Timestamp.valueOf(article.getDateFinEncheres()));
			psmt.setInt(5, article.getPrixInitial());
			psmt.setInt(6, article.getVendeur().getNoUtilisateur());
			psmt.setInt(7, article.getCategorie().getNoCategorie());

			int nombreArticleInsere = psmt.executeUpdate();
			if (nombreArticleInsere == 1) {
				ResultSet rs = psmt.getGeneratedKeys();
				if (rs.next()) {
					noArticle = rs.getInt(1);
					article.setNoArticle(noArticle);
				}
				rs.close();

			} else {
				be.ajouterErreur(CodesResultatDAL.INSERT_ARTICLE_ECHEC);
			}
			psmt.close();

			psmt = cnx.prepareStatement(INSERT_RETRAIT);
			psmt.setInt(1, noArticle);
			psmt.setString(2, article.getRetrait().getRue());
			psmt.setString(3, article.getRetrait().getCodePostal());
			psmt.setString(4, article.getRetrait().getVille());
			psmt.executeUpdate();
			psmt.close();

			cnx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			be.ajouterErreur(CodesResultatDAL.INSERT_RETRAIT_ECHEC);
			try {
				cnx.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
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
		return noArticle;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see fr.eni.serdaigle.dal.ArticleDAO#update(fr.eni.serdaigle.bo.ArticleVendu)
	 */
	public void update(ArticleVendu article) throws BusinessException {

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
			psmt.setInt(6, article.getVendeur().getNoUtilisateur());
			psmt.setInt(7, article.getCategorie().getNoCategorie());
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
	 * 
	 * @see fr.eni.serdaigle.dal.ArticleDAO#select(int)
	 */
	public ArticleVendu select(int noArticle) throws BusinessException {
		BusinessException be = new BusinessException();
		try (Connection cnx = ConnectionProvider.getConnection();
				PreparedStatement psmt = cnx.prepareStatement(SELECT_BY_ID);) {
			psmt.setInt(1, noArticle);
			ResultSet rs = psmt.executeQuery();
			ArticleVendu article = new ArticleVendu();
			Retrait retrait = new Retrait();
			Categorie categorie = new Categorie();

			if (rs.next()) {
				
				article.setNoArticle(rs.getInt("no_article"));
				article.setNomArticle(rs.getString("nom_article"));
				article.setDescription(rs.getString("description"));
				article.setDateDebutEncheres(rs.getTimestamp("date_debut_encheres").toLocalDateTime());
				article.setDateFinEncheres(rs.getTimestamp("date_fin_encheres").toLocalDateTime());
				article.setPrixInitial(rs.getInt("prix_initial"));

				categorie.setNoCategorie(rs.getInt("no_categorie"));
				categorie.setLibelle(rs.getString("cate_libelle"));
				article.setCategorie(categorie);

				retrait.setRue(rs.getString("retrait_rue"));
				retrait.setVille(rs.getString("retrait_ville"));
				retrait.setCodePostal(rs.getString("retrait_cp"));
				article.setRetrait(retrait);
			}
			rs.close();
			psmt.close();
			return article;
		} catch (SQLException e) {
			e.printStackTrace();
			// code resultat DAL à changer !!
			be.ajouterErreur(CodesResultatDAL.LOGIN_INCORRECT);

			throw be;
		}

	}

}