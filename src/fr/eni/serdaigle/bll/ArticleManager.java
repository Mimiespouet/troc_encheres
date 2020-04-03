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

public class ArticleManager {
	private ArticleDAO articleDAO;
	private static final int CHAMPS_VARCHAR_15 = 15;
	private static final int CHAMPS_VARCHAR_30 = 30;
	private static final int CHAMPS_VARCHAR_300 = 300;

	public ArticleManager() {
		articleDAO = DAOFactory.getArticleDAO();
	}

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

	public void validerNomArticle(String nomArticle, int varchar, BusinessException be) {
		if (nomArticle == null || nomArticle.equals("")) {
			be.ajouterErreur(CodesResultatBLL.CHAMP_NOM_ARTICLE_OBLIGATOIRE);
		}
		if (nomArticle.length() > varchar) {
			be.ajouterErreur(CodesResultatBLL.CHAMP_NOM_ARTICLE_TAILLE_DEPASSEE);
		}

	}

	public void validerDescriptionArticle(String description, int varchar, BusinessException be) {
		if (description == null || description.equals("")) {
			be.ajouterErreur(CodesResultatBLL.CHAMP_DESCRIPTION_ARTICLE_OBLIGATOIRE);
		}
		if (description.length() > varchar) {
			be.ajouterErreur(CodesResultatBLL.CHAMP_DESCRIPTION_ARTICLE_TAILLE_DEPASSEE);
		}

	}

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

	private void validerDateEncheres(LocalDateTime dateDebutEncheres, LocalDateTime dateFinEncheres,
			BusinessException be) {
		if (dateDebutEncheres.isAfter(dateFinEncheres)) {
			be.ajouterErreur(CodesResultatBLL.DATE_ERREUR);
		}
	}

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
