/**
 * 
 */
package fr.eni.serdaigle.dal.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

import fr.eni.serdaigle.bo.ArticleVendu;
import fr.eni.serdaigle.bo.Categorie;
import fr.eni.serdaigle.bo.Enchere;
import fr.eni.serdaigle.bo.Retrait;
import fr.eni.serdaigle.bo.Utilisateur;

/**
 * Classe en charge de gérer les mapping
 * @author Max
 * @version Trocencheres - v1.0
 * @date 30 mars 2020
 */
public class Mapping {
	
	/**
	 * Méthode en charge de mapper un Utilisateur
	 * @param rs
	 * @return Utilisateur
	 * @throws SQLException
	 */
	//A REFAIRE AVEC LE CONSTRUCTEUR
	public static Utilisateur mappingUtilisateur(ResultSet rs) throws SQLException {
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
	
	/**
	 * Méthode en charge de mapper l'objet ArticleVendu pour l'affichage sur l'accueil
	 * @param rs, Categorie, Utilisateur (vendeur)
	 * @return ArticleVendu
	 * @throws SQLException
	 */
	public static ArticleVendu mappingArticleVendu(ResultSet rs, Categorie categorie, Utilisateur vendeur) throws SQLException {
		int noArticle = rs.getInt("noArticle");
		String nomArticle = rs.getString("nomArticle");
		String description = rs.getString("description");
		LocalDateTime dateDebutEncheres = rs.getTimestamp("date_debut_encheres").toLocalDateTime();
		LocalDateTime dateFinEncheres = rs.getTimestamp("date_fin_encheres").toLocalDateTime();
		int prixInitial = rs.getInt("prix_initial");
		ArticleVendu article = new ArticleVendu(noArticle, nomArticle, description, dateDebutEncheres, dateFinEncheres, prixInitial, vendeur, categorie);
		return article;
	}
	

	/**
	 * Méthode en charge du mapping de l'objet ArticleVendu pour l'affichage sur afficherDetailEnchere
	 * @param rs
	 * @return ArticleVendu
	 * @throws SQLException
	 */
	public static Enchere mappingDetailEnchereSelonArticle(ResultSet rs) throws SQLException {
		LocalDateTime dateEnchere = rs.getTimestamp("dateEnchere").toLocalDateTime();
		int montantEnchere = rs.getInt("enchere_max");

		//récupération de ArticleVendu
		ArticleVendu article = new ArticleVendu();
		article.setNoArticle(rs.getInt("noArticle"));
        article.setNomArticle(rs.getString("nomArticle"));
        article.setDescription(rs.getString("description"));
        article.setDateFinEncheres(rs.getTimestamp("date_fin_encheres").toLocalDateTime());
        article.setPrixInitial(rs.getInt("prix_initial"));

        //récupération de Catégorie
        Categorie categorie = Mapping.mappingCategorie(rs);
       
        //récupération du vendeur
        Utilisateur vendeur = new Utilisateur();
        vendeur.setPseudo(rs.getString("vendeur_pseudo"));
        vendeur.setNoUtilisateur(rs.getInt("vendeur_id"));
        
        //récupération de l'acheteur
        Utilisateur acheteur = new Utilisateur();
        acheteur.setPseudo(rs.getString("acheteur_pseudo"));
        acheteur.setNoUtilisateur(rs.getInt("acheteur_id"));
       
        Enchere enchereConsulte = new Enchere(dateEnchere,montantEnchere,acheteur,article);
        return enchereConsulte;
    }
	
	/**
	 * Méthode en charge de mapper l'objet Categorie
	 * @param rs
	 * @return Categorie
	 * @throws SQLException
	 */
	public static Categorie mappingCategorie(ResultSet rs) throws SQLException {
		Categorie categorie = new Categorie(rs.getInt("no_categorie"),rs.getString("libelle"));
		return categorie;
	}
	/**
	 * Méthode en charge du mapping de l'objet Enchere pour l'affichage sur la page acquisition.jsp
	 * @param rs
	 * @return ArticleVendu
	 * @throws SQLException
	 */
	public static Enchere mappingEnchereRemporte(ResultSet rs) throws SQLException {
		int meilleurOffre = rs.getInt("val_max"); 
		
		
//      récupération d' ArticleVendu        
		ArticleVendu article = new ArticleVendu();
		article.setNoArticle(rs.getInt("noArticle"));
        article.setNomArticle(rs.getString("nomArticle"));
        article.setPrixInitial(rs.getInt("prix_initial"));
  
        
//      récupération de retrait
        Retrait retrait = new Retrait();
        String adresse_retrait = (rs.getString("adresse_retrait"));
        

//        récupération du vendeur
        Utilisateur vendeur = new Utilisateur();
        vendeur.setPseudo(rs.getString("vendeur_pseudo"));
        vendeur.setTelephone(rs.getString("vendeur_telephone"));
        
       
        Enchere enchereRemporte = new Enchere(meilleurOffre,vendeur,article);
        return enchereRemporte;
       

    }
	
}


