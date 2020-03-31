/**
 * 
 */
package fr.eni.serdaigle.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import fr.eni.serdaigle.bo.ArticleVendu;
import fr.eni.serdaigle.bo.Categorie;
import fr.eni.serdaigle.bo.Enchere;
import fr.eni.serdaigle.bo.Utilisateur;
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
	private static final String SELECT_AVEC_MEILLEURE_OFFRE = "SELECT av.nom_article, av.description,\r\n" + 
			"	c.no_categorie as no_categorie,	c.libelle as cate_libelle, acheteur.pseudo as acheteur_pseudo,\r\n" + 
			"	acheteur.no_utilisateur as acheteur_id,	av.prix_initial, av.date_fin_encheres, r.rue,\r\n" + 
			"	r.ville, r.code_postal,	vendeur.pseudo as vendeur_pseudo, vendeur.no_utilisateur as vendeur_id,\r\n" + 
			"	vme.pseudo_max as the_best,	vme.val_max FROM ARTICLES_VENDUS av \r\n" + 
			"	JOIN RETRAITS r ON av.no_article = r.no_article \r\n" + 
			"	JOIN UTILISATEURS vendeur ON av.no_vendeur = vendeur.no_utilisateur\r\n" + 
			"	JOIN UTILISATEURS acheteur ON av.no_acheteur = acheteur.no_utilisateur\r\n" + 
			"	JOIN CATEGORIES c ON c.no_categorie = av.no_categorie\r\n" + 
			"	JOIN (SELECT av.no_article,	u.no_utilisateur, u.pseudo as pseudo_max, MAX(e.montant_enchere) as val_max\r\n" + 
			"	FROM ENCHERES e JOIN ARTICLES_VENDUS av ON e.no_article = av.no_article\r\n" + 
			"	JOIN UTILISATEURS u ON av.no_acheteur = u.no_utilisateur\r\n" + 
			"	GROUP BY av.no_article, u.no_utilisateur, u.pseudo) vme ON vme.no_article = av.no_article\r\n" +
			"	WHERE av.no_article = ?";
	
	
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
	public ArticleVendu select(int noArticle) throws BusinessException {
		BusinessException be = new BusinessException();
		try (Connection cnx = ConnectionProvider.getConnection();
			PreparedStatement psmt = cnx.prepareStatement(SELECT_AVEC_MEILLEURE_OFFRE);) {
			psmt.setInt(1, noArticle);
			ResultSet rs = psmt.executeQuery();
			ArticleVendu articleConsulte = null;
			if (rs.next()) {
				articleConsulte = Mapping.mappingArticleVenduDetailEnchere(rs);
			}
			rs.close();
			psmt.close();
			return articleConsulte;
		}catch(SQLException e){
			e.printStackTrace();
			BusinessException be = new BusinessException();
			be.ajouterErreur(CodesResultatDAL.SELECT_ECHEC);
			throw be;
		}
	}

}
