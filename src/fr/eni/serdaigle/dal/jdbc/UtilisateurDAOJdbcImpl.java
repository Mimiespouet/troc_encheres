package fr.eni.serdaigle.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import fr.eni.serdaigle.bo.Utilisateur;
import fr.eni.serdaigle.dal.CodesResultatDAL;
import fr.eni.serdaigle.dal.ConnectionProvider;
import fr.eni.serdaigle.dal.UtilisateurDAO;
import fr.eni.serdaigle.exception.BusinessException;

public class UtilisateurDAOJdbcImpl implements UtilisateurDAO {

	private static final String INSERT = "INSERT INTO UTILISATEURS(pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe) VALUES (?,?,?,?,?,?,?,?,?);";

	private static final String SELECT_CONNEXION = "SELECT no_utilisateur, pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur FROM UTILISATEURS WHERE (pseudo=? and mot_de_passe =?) or(email=? and mot_de_passe=?);";
	private static final String SELECT_BY_ID = "";
	private static final String SELECT_BY_PSEUDO = "SELECT no_utilisateur, pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur FROM UTILISATEURS WHERE pseudo=?";
	private static final String SELECT_ALL = "";
	private static final String DELETE = "DELETE FROM UTILISATEURS WHERE no_utilisateur=?;";
	private static final String UPDATE = "UPDATE UTILISATEURS SET pseudo=?, nom=?, prenom=?, email=?, telephone=?, rue=?, code_postal=?, ville=?, mot_de_passe=? WHERE no_utilisateur=? ;";

	@Override
	public void insert(Utilisateur utilisateur) throws BusinessException {
		Connection cnx = null;
		BusinessException be = new BusinessException();
		try {
			cnx = ConnectionProvider.getConnection();
			// Pour prendre la main sur la transaction
			PreparedStatement psmt = cnx.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
			psmt.setString(1, utilisateur.getPseudo());
			psmt.setString(2, utilisateur.getNom());
			psmt.setString(3, utilisateur.getPrenom());
			psmt.setString(4, utilisateur.getEmail());
			psmt.setString(5, utilisateur.getTelephone());
			psmt.setString(6, utilisateur.getRue());
			psmt.setString(7, utilisateur.getCodePostal());
			psmt.setString(8, utilisateur.getVille());
			psmt.setString(9, utilisateur.getMotDePasse());
			psmt.executeUpdate();
			psmt.close();

		} catch (Exception e) {
			e.printStackTrace();
			be.ajouterErreur(CodesResultatDAL.INSERT_USER_ECHEC);
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

	@Override
	public List<Utilisateur> select() throws BusinessException {
		return null;
	}

	@Override
	public Utilisateur selectConnexion(String identifiant, String password) throws BusinessException {
		Utilisateur resultat = null;
		BusinessException be = new BusinessException();
		try (Connection cnx = ConnectionProvider.getConnection();
				PreparedStatement psmt = cnx.prepareStatement(SELECT_CONNEXION);) {
			psmt.setString(1, identifiant);
			psmt.setString(2, password);
			psmt.setString(3, identifiant);
			psmt.setString(4, password);
			ResultSet rs = psmt.executeQuery();
			if (rs.next()) {
				resultat = mappingUtilisateur(rs);
			}
			rs.close();
			psmt.close();
		} catch (SQLException e) {
			be.ajouterErreur(CodesResultatDAL.SELECT_LOGIN_ECHEC);
			throw be;
		}
		return resultat;
		
	}

	public Utilisateur selectPseudo(String pseudo) throws BusinessException {
		try (Connection cnx = ConnectionProvider.getConnection();
				PreparedStatement psmt = cnx.prepareStatement(SELECT_BY_PSEUDO);) {
			psmt.setString(1, pseudo);
			ResultSet rs = psmt.executeQuery();
			Utilisateur utilisateur = null;
			if (rs.next()) {
				utilisateur = mappingUtilisateur(rs);
			}
			rs.close();
			psmt.close();
			return utilisateur;
		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException be = new BusinessException();
			be.ajouterErreur(CodesResultatDAL.SELECT_LOGIN_ECHEC);
			throw be;
		}

	}
	
	@Override
	public void update(Utilisateur utilisateur) throws BusinessException {
		Connection cnx = null;
		BusinessException be = new BusinessException();
		try {
			cnx = ConnectionProvider.getConnection();
			// Pour prendre la main sur la transaction
			PreparedStatement psmt = cnx.prepareStatement(UPDATE);
			psmt.setString(1, utilisateur.getPseudo());
			System.out.println("pseudo dans jdbcimplc " + utilisateur.getPseudo());
			psmt.setString(2, utilisateur.getNom());
			psmt.setString(3, utilisateur.getPrenom());
			psmt.setString(4, utilisateur.getEmail());
			psmt.setString(5, utilisateur.getTelephone());
			psmt.setString(6, utilisateur.getRue());
			psmt.setString(7, utilisateur.getCodePostal());
			psmt.setString(8, utilisateur.getVille());
			psmt.setString(9, utilisateur.getMotDePasse());
			psmt.setInt(10, utilisateur.getNoUtilisateur());
			psmt.executeUpdate();
			psmt.close();

		} catch (Exception e) {
			e.printStackTrace();
			be.ajouterErreur(CodesResultatDAL.UPDATE_UTILISATEUR_ECHEC);
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

	@Override
	public void delete(Utilisateur utilisateur) throws BusinessException {
		try (Connection cnx = ConnectionProvider.getConnection();
				PreparedStatement psmt = cnx.prepareStatement(DELETE);) {
			psmt.setInt(1, utilisateur.getNoUtilisateur());
			psmt.executeUpdate();
			psmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException be = new BusinessException();
			be.ajouterErreur(CodesResultatDAL.DELETE_USER);
			throw be;
		}
	}

	private Utilisateur mappingUtilisateur(ResultSet rs) throws SQLException {
		Utilisateur utilisateur = new Utilisateur();
		utilisateur.setNoUtilisateur(rs.getInt("no_utilisateur"));
		utilisateur.setPseudo(rs.getString("pseudo"));
		utilisateur.setNom(rs.getString("nom"));
		utilisateur.setPrenom(rs.getString("prenom"));
		utilisateur.setEmail(rs.getString("email"));
		utilisateur.setTelephone(rs.getString("telephone"));
		utilisateur.setRue(rs.getString("rue"));
		utilisateur.setCodePostal(rs.getString("code_postal"));
		utilisateur.setVille(rs.getString("ville"));
		utilisateur.setMotDePasse(rs.getString("mot_de_passe"));
		utilisateur.setCredit(rs.getInt("credit"));
		if (rs.getInt("administrateur") == 1) {
			utilisateur.setAdministrateur(true);
		} else {
			utilisateur.setAdministrateur(false);
		}
		return utilisateur;
	}
}

