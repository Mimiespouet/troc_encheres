package fr.eni.serdaigle.bll;

import fr.eni.serdaigle.dal.DAOFactory;
import fr.eni.serdaigle.exception.BusinessException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import fr.eni.serdaigle.bo.ArticleVendu;
import fr.eni.serdaigle.bo.Enchere;
import fr.eni.serdaigle.bo.Utilisateur;
import fr.eni.serdaigle.dal.ArticleDAO;

/**
 * Classe en charge de gérer l'application des requêtes (ArticleDAO) sur les article
 * @author serdaigle
 * @version troc_encheres - v1.0
 * @date 28 mars 2020
 */
public class ArticleManager {
	private ArticleDAO articleDAO;
	private static final int CHAMPS_VARCHAR_15 = 15;
	private static final int CHAMPS_VARCHAR_30 = 30;
	private static final int CHAMPS_VARCHAR_300 = 300;

	/**
	 * Constructeur par défaut
	 */
	public ArticleManager() {
		articleDAO = DAOFactory.getArticleDAO();
	}

	/**
	 * Méthode en charge de sélectionner un article selon son numéro
	 * @param noArticle
	 * @return articleDAO.select(noArticle)
	 * @throws BusinessException
	 */
	public ArticleVendu select(int noArticle) throws BusinessException {
		return articleDAO.select(noArticle);
	}

	/**
	 * Méthode en charge d'ajouter un article seul
	 * @param article
	 * @throws BusinessException
	 */
	public int ajouterArticle(ArticleVendu article) throws BusinessException {
		BusinessException be = new BusinessException();

		//valider les champs
		validerNomArticle(article.getNomArticle(), CHAMPS_VARCHAR_30, be);
		validerDescriptionArticle(article.getDescription(), CHAMPS_VARCHAR_300, be);
		validerDateEncheres(article.getDateDebutEncheres(), article.getDateFinEncheres(), be);
	
		if (!be.hasErreurs()) {
			return articleDAO.insertArticle(article);
		} else {
			throw be;
		}

	}

	/**
	 * Méthode en charge de modifier un article seul
	 * @param article
	 * @throws BusinessException
	 */
	public void modifierArticle(ArticleVendu article) throws BusinessException {
		BusinessException be = new BusinessException();
		// valider les champs
		validerModifArticle(article.getDateDebutEncheres(), LocalDateTime.now(), be);
		validerNomArticle(article.getNomArticle(), CHAMPS_VARCHAR_30, be);
		validerDescriptionArticle(article.getDescription(), CHAMPS_VARCHAR_300, be);
		validerDateEncheres(article.getDateDebutEncheres(), article.getDateFinEncheres(), be);

		if (!be.hasErreurs()) {
			articleDAO.update(article);
		} else {
			throw be;
		}
	}

	/**
	 * Méthode en charge de valider le nom de l'Article sans dépasser le nombre de caractères
	 * @param nomArticle
	 * @param varchar
	 * @param be
	 */
	public void validerNomArticle(String nomArticle, int varchar, BusinessException be) {
		if (nomArticle == null || nomArticle.equals("")) {
			be.ajouterErreur(CodesResultatBLL.CHAMP_NOM_ARTICLE_OBLIGATOIRE);
		}
		if (nomArticle.length() > varchar) {
			be.ajouterErreur(CodesResultatBLL.CHAMP_NOM_ARTICLE_TAILLE_DEPASSEE);
		}

	}

	/**
	 * Méthode en charge de valider la description de l'Article sans dépasser le nombre de caractères
	 * @param description
	 * @param varchar
	 * @param be
	 */
	public void validerDescriptionArticle(String description, int varchar, BusinessException be) {
		if (description == null || description.equals("")) {
			be.ajouterErreur(CodesResultatBLL.CHAMP_DESCRIPTION_ARTICLE_OBLIGATOIRE);
		}
		if (description.length() > varchar) {
			be.ajouterErreur(CodesResultatBLL.CHAMP_DESCRIPTION_ARTICLE_TAILLE_DEPASSEE);
		}

	}

	/**
	 * Méthode en charge de valider l'adresse de retrait de l'Article sans dépasser le nombre de caractères
	 * @param rue
	 * @param codePostal
	 * @param ville
	 * @param varchar1
	 * @param varchar2
	 * @param be
	 */
	public void validerAdresseRetrait(String rue, String codePostal, String ville, int varchar1, int varchar2,
			BusinessException be) {
		if (rue.length() > varchar1) {
			be.ajouterErreur(CodesResultatBLL.CHAMP_NOM_ARTICLE_TAILLE_DEPASSEE);
		}
		if (codePostal.length() > varchar2) {
			be.ajouterErreur(CodesResultatBLL.CHAMP_NOM_ARTICLE_TAILLE_DEPASSEE);
		}
		if (ville.length() > varchar1) {
			be.ajouterErreur(CodesResultatBLL.CHAMP_NOM_ARTICLE_TAILLE_DEPASSEE);
		}

	}

	/**
	 * Méthode en charge de valider les dates d'enchères de l'Article
	 * @param dateDebutEncheres
	 * @param dateFinEncheres
	 * @param be
	 */
	private void validerDateEncheres(LocalDateTime dateDebutEncheres, LocalDateTime dateFinEncheres,
			BusinessException be) {
		if (dateDebutEncheres.isAfter(dateFinEncheres)) {
			be.ajouterErreur(CodesResultatBLL.DATE_ERREUR);
		}
	}

	/**
	 * Méthode en charge de valider les dates d'enchères lors de la modification de l'Article
	 * @param dateDebutEnchere
	 * @param dateNow
	 * @param be
	 */
	public void validerModifArticle(LocalDateTime dateDebutEnchere, LocalDateTime dateNow, BusinessException be) {
		if (dateNow.isAfter(dateDebutEnchere)) {
			be.ajouterErreur(CodesResultatBLL.DATE_MODIFICATION_DEPASSE);
		}
	}
	
	/**
	 * Méthode en charge d'ajouter un article et un retrait
	 * @param article
	 * @throws BusinessException
	 */
	public int ajouterArticleRetrait(ArticleVendu article) throws BusinessException {
		BusinessException be = new BusinessException();
		// valider les champs
		validerNomArticle(article.getNomArticle(), CHAMPS_VARCHAR_30, be);
		validerDescriptionArticle(article.getDescription(), CHAMPS_VARCHAR_300, be);
		validerDateEncheres(article.getDateDebutEncheres(), article.getDateFinEncheres(), be);
		validerAdresseRetrait(article.getRetrait().getRue(), article.getRetrait().getRue(),
				article.getRetrait().getRue(), CHAMPS_VARCHAR_30, CHAMPS_VARCHAR_15, be);

		if (!be.hasErreurs()) {
			return articleDAO.insertArticleRetrait(article);
		} else {
			throw be;
		}

	}

}
