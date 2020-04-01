/**
 * 
 */
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
	private static final String SELECT_VENTE_REMPORTE = "SELECT av.no_article, av.nom_article, av.prix_initial, r.rue as rue_retrait, r.code_postal as CPO_retrait, r.ville as ville_retrait, vendeur.no_utilisateur as vendeur_id, vendeur.pseudo as vendeur_pseudo, vendeur.telephone, vendeur.rue as rue_vendeur, vendeur.code_postal as CPO_vendeur, vendeur.ville as ville_vendeur, u.no_utilisateur, u.pseudo as pseudo_max, MAX(e.montant_enchere) as val_max FROM ENCHERES e JOIN ARTICLES_VENDUS av ON e.no_article = av.no_article JOIN UTILISATEURS u ON av.no_acheteur = u.no_utilisateur JOIN UTILISATEURS vendeur ON av.no_vendeur = vendeur.no_utilisateur JOIN RETRAITS r ON r.no_article = av.no_article GROUP BY av.no_article, u.no_utilisateur, u.pseudo WHERE av.no_article = ?;";;
	private static final String SELECT_AVEC_MEILLEURE_OFFRE = "SELECT TOP 1\r\n" + 
			"	vme.enchere_max, vme.pseudo as acheteur_pseudo, vme.no_utilisateur as acheteur_id, vme.email as acheteur_email,\r\n" + 
			"	av.nom_article, av.no_article, av.description, av.prix_initial, av.date_fin_encheres, c.no_categorie, c.libelle,\r\n" + 
			"	r.rue as r_rue,	r.ville as r_ville,	r.code_postal as r_code_postal,	vendeur.pseudo as vendeur_pseudo,\r\n" + 
			"	vendeur.no_utilisateur as vendeur_id, vendeur.rue as vendeur_rue, vendeur.ville as vendeur_ville, vendeur.code_postal as vendeur_code_postal,\r\n" + 
			"	vendeur.telephone as vendeur_telephone\r\n" +
			"	FROM ARTICLES_VENDUS av\r\n" + 
			"	LEFT JOIN RETRAITS r ON av.no_article = r.no_article\r\n" + 
			"	JOIN UTILISATEURS vendeur ON av.no_vendeur = vendeur.no_utilisateur \r\n" + 
			"	JOIN CATEGORIES c ON c.no_categorie = av.no_categorie \r\n" + 
			"	JOIN \r\n" + 
			"	(SELECT \r\n" + 
			"			MAX(e.montant_enchere) as enchere_max, av.no_article, u.pseudo,	u.no_utilisateur, u.email\r\n" + 
			"		    FROM ENCHERES e \r\n" + 
			"			JOIN ARTICLES_VENDUS av ON e.no_article = av.no_article\r\n" + 
			"			JOIN UTILISATEURS u ON u.no_utilisateur = e.no_utilisateur\r\n" + 
			"		  GROUP BY 	av.no_article,u.pseudo,u.no_utilisateur)\r\n" + 
			"	vme ON vme.no_article = av.no_article\r\n" + 
			"	WHERE av.no_article = ? ORDER BY vme.enchere_max DESC;";
	private static final String SELECT_ALL_ENCHERES_EN_COURS = "SELECT DISTINCT a.nom_article, a.date_fin_encheres, a.prix_initial, u.pseudo, r.rue, r.ville, vme.val_max FROM ARTICLES_VENDUS a JOIN UTILISATEURS u ON u.no_utilisateur = a.no_vendeur LEFT JOIN ENCHERES e ON e.no_article = a.no_article LEFT JOIN RETRAITS r ON a.no_article = r.no_article LEFT JOIN (SELECT MAX(e.montant_enchere) as val_max, av.no_article FROM ENCHERES e JOIN ARTICLES_VENDUS av ON e.no_article = av.no_article GROUP BY av.no_article) vme ON vme.no_article = a.no_article WHERE a.date_debut_encheres < GETDATE();";
	
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
	 * @see fr.eni.serdaigle.dal.EnchereDAO#selectById(int)
	 */
	@Override
	public Enchere select(int noArticle) throws BusinessException {
		BusinessException be = new BusinessException();
		try (Connection cnx = ConnectionProvider.getConnection();
			PreparedStatement psmt = cnx.prepareStatement(SELECT_AVEC_MEILLEURE_OFFRE);) {
			psmt.setInt(1, noArticle);
			ResultSet rs = psmt.executeQuery();
			Enchere enchere = null;
			if (rs.next()) {
				enchere = Mapping.mappingDetailEnchereSelonArticle(rs);
			}
			rs.close();
			psmt.close();
			return enchere;
		}catch(SQLException e){
			e.printStackTrace();
			be.ajouterErreur(CodesResultatDAL.SELECT_MAX_ENCHERE_ECHEC);
			throw be;
		}
	}
	
	/**
	 * {@inheritDoc}
	 * @see fr.eni.serdaigle.dal.EnchereDAO#selectVenteRemporte(fr.eni.serdaigle.bo.Enchere)
	 */
	@Override
	public Enchere selectVenteRemporte(int noArticle) throws BusinessException {
		try (Connection cnx = ConnectionProvider.getConnection();
				PreparedStatement psmt = cnx.prepareStatement(SELECT_VENTE_REMPORTE);) {
			psmt.setInt(1, noArticle);
			ResultSet rs = psmt.executeQuery();
			Enchere enchereRemporte = null;
			if (rs.next()) {
				enchereRemporte = Mapping.mappingEnchereRemporte(rs);
			}
			rs.close();
			psmt.close();
			return enchereRemporte;
		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException be = new BusinessException();
			be.ajouterErreur(CodesResultatDAL.SELECT_VENTE_REMPORTE_ECHEC);
			throw be;
		}
	}

	/**
	 * {@inheritDoc}
	 * @see fr.eni.serdaigle.dal.EnchereDAO#selectAllEnCours()
	 */
	@Override
	public List<Enchere> selectAllEnCours() throws BusinessException {
		BusinessException be = new BusinessException();
		List<Enchere> listeEnchere = new ArrayList<Enchere>();
		Enchere enchere = null;
		try (Connection cnx = ConnectionProvider.getConnection(); Statement smt = cnx.createStatement();) {
				ResultSet rs = smt.executeQuery(SELECT_ALL_ENCHERES_EN_COURS);
				while (rs.next()) {
					enchere = Mapping.mappingEnchere(rs);
					listeEnchere.add(enchere);
				}
				rs.close();
				smt.close();
			}catch(SQLException e){
				be.ajouterErreur(CodesResultatDAL.SELECT_ALL_ENCHERE_ECHEC);
				throw be;
			}
		return listeEnchere;
	}

}
