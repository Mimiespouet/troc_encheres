package fr.eni.serdaigle.bll;

import fr.eni.serdaigle.dal.DAOFactory;
import fr.eni.serdaigle.exception.BusinessException;

import java.time.LocalDate;

import fr.eni.serdaigle.bo.ArticleVendu;
import fr.eni.serdaigle.dal.ArticleDAO;

public class ArticleManager {
	private ArticleDAO articleDAO;
	private static final int CHAMPS_VARCHAR_30 = 30;
	private static final int CHAMPS_VARCHAR_300 = 300;
	

	public ArticleManager() {
		articleDAO = DAOFactory.getArticleDAO();
	}

	public ArticleVendu ajouterArticle(String nomArticle, String description, LocalDate dateDebutEncheres,
			LocalDate dateFinEncheres, int prixInitial, int prixVente, int noUtilisateur, int noCategorie)
			throws BusinessException {
		BusinessException be = new BusinessException();

//valider les champs
		validerNomArticle(nomArticle, CHAMPS_VARCHAR_30, be);
		validerDescriptionArticle(description, CHAMPS_VARCHAR_300, be);
		validerDateEncheres(dateDebutEncheres, dateFinEncheres, be);
		validerPrix(prixInitial, be);

		ArticleVendu article = null;
		
		if (!be.hasErreurs()) {
			article = new ArticleVendu();

			article.setNomArticle(nomArticle);
			article.setDescription(description);
			article.setDateDebutEncheres(dateDebutEncheres);
			article.setDateFinEncheres(dateFinEncheres);
			article.setPrixInitial(prixInitial);
			article.setPrixVente(article.getPrixInitial());
			article.setNoUtilisateur(noUtilisateur);
			article.setNoCategorie(noCategorie);
			
			articleDAO.insert(article);
		} else {
			throw be;
		}
		return article;
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
	
	private void validerDateEncheres(LocalDate dateDebutEncheres, LocalDate dateFinEncheres, BusinessException be) {
		if(dateDebutEncheres == null || dateFinEncheres == null || dateDebutEncheres.isAfter(dateFinEncheres) ) {
			be.ajouterErreur(CodesResultatBLL.DATE_ERREUR);
		}
	}
	
	public void validerPrix(int prixInitial, BusinessException be) {
		if (prixInitial == 0) {
			be.ajouterErreur(CodesResultatBLL.PRIX_ERREUR);
		}
	}

}