package fr.eni.serdaigle.bll;

import fr.eni.serdaigle.dal.DAOFactory;
import fr.eni.serdaigle.exception.BusinessException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import fr.eni.serdaigle.bo.ArticleVendu;
import fr.eni.serdaigle.dal.ArticleDAO;

public class ArticleManager {
	private ArticleDAO articleDAO;
	private static final int CHAMPS_VARCHAR_30 = 30;
	private static final int CHAMPS_VARCHAR_300 = 300;

	

	public ArticleManager() {
		articleDAO = DAOFactory.getArticleDAO();
	}
	
	public List<ArticleVendu> rechercherArticles(String categorie,String nomArticle, String[] checkedEtat)
			throws BusinessException {
		BusinessException be = new BusinessException();
		
		return null;
	}

	public void ajouterArticle(ArticleVendu article)
			throws BusinessException {
		BusinessException be = new BusinessException();

//valider les champs
		validerNomArticle(article.getNomArticle(), CHAMPS_VARCHAR_30, be);
		validerDescriptionArticle(article.getDescription(), CHAMPS_VARCHAR_300, be);
		validerDateEncheres(article.getDateDebutEncheres(), article.getDateFinEncheres(), be);
		validerPrix(article.getPrixInitial(), be);
		
		if (!be.hasErreurs()) {
			articleDAO.insert(article);
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
	
	private void validerDateEncheres(LocalDateTime dateDebutEncheres, LocalDateTime dateFinEncheres, BusinessException be) {
		if(dateDebutEncheres.isAfter(dateFinEncheres)) {
			be.ajouterErreur(CodesResultatBLL.DATE_ERREUR);
		}
	}
	
	public void validerPrix(int prixInitial, BusinessException be) {
		if (prixInitial < 0) {
			be.ajouterErreur(CodesResultatBLL.PRIX_ERREUR);
		}
	}

}