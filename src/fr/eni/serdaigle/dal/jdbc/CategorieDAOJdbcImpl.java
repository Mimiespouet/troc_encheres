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
	private static final String SELECT_ALL = "SELECT no_categorie, libelle FROM CATEGORIES";
	private static final String SELECT_BY_LIBELLE = "SELECT c.no_categorie, c.libelle, a.date_debut_encheres, a.date_fin_encheres, a.description, a.etat_vente, a.no_acheteur, a.no_article, a.no_categorie, a.no_vendeur, a.nom_article, a.prix_initial, a.prix_vente, u.administrateur, u.code_postal, u.credit, u.email, u.mot_de_passe, u.no_utilisateur, u.nom, u.prenom, u.pseudo, u.rue, u.telephone, u.ville FROM CATEGORIES c INNER JOIN ARTICLES_VENDUS a ON c.no_categorie = a.no_categorie INNER JOIN UTILISATEURS u ON u.no_utilisateur = a.etat_vente WHERE libelle=?;";
		
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
			System.out.println("1");
			while (rs.next()) {
				Categorie categorie = Mapping.mappingCategorie(rs);
				System.out.println("2");
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
		Connection cnx = null;
		BusinessException be = new BusinessException();
		try {
			cnx = ConnectionProvider.getConnection();
			PreparedStatement psmt = cnx.prepareStatement(SELECT_BY_LIBELLE);
			psmt.setString(1, libelle);
			ResultSet rs = psmt.executeQuery();
			Categorie categorie = null;
			int compteur = 0;
			while (rs.next()) {
				// on crée l'objet catégorie au premier passage de la boucle - compteur
				if(compteur==0) {
					categorie = Mapping.mappingCategorie(rs);;
				}
				compteur++;
				Utilisateur vendeur = Mapping.mappingUtilisateur(rs);
				ArticleVendu article = Mapping.mappingArticleVendu(rs, categorie, vendeur);
				categorie.getListeArticle().add(article);
				
			}
			psmt.close();
			return categorie;
			
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
		return null;
	}	

}