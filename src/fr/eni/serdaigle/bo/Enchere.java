package fr.eni.serdaigle.bo;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Classe en charge de l'objet Enchere
 * @author Max
 * @version Trocencheres - v1.0
 * @date 28 mars 2020
 */
public class Enchere implements Serializable {
	private LocalDateTime dateEnchere;
	private int montantEnchere;
	private Utilisateur utilisateur;
	private ArticleVendu article;

	/**
	 * Constructeur par défaut
	 */
	public Enchere() {
	}
	
	/**
	 * Constructeur 
	 * @param montantEnchere
	 * @param utilisateur
	 * @param article
	 */
	public Enchere(int montantEnchere, Utilisateur utilisateur, ArticleVendu article) {
		this();
		this.montantEnchere = montantEnchere;
		this.utilisateur = utilisateur;
		this.article = article;
	}

	/**
	 * Constructeur pour servlet Encherir
	 * @param dateEnchere
	 * @param montantEnchere
	 * @param utilisateur
	 * @param article
	 */
	public Enchere(LocalDateTime dateEnchere, int montantEnchere, Utilisateur utilisateur, ArticleVendu article) {
		this(montantEnchere, utilisateur, article);
		this.dateEnchere = dateEnchere;
	}

	public LocalDateTime getDateEnchere() {
		return dateEnchere;
	}

	public void setDateEnchere(LocalDateTime dateEnchere) {
		this.dateEnchere = dateEnchere;
	}

	public int getMontantEnchere() {
		return montantEnchere;
	}

	public void setMontantEnchere(int montantEnchere) {
		this.montantEnchere = montantEnchere;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	public ArticleVendu getArticle() {
		return article;
	}

	public void setArticle(ArticleVendu article) {
		this.article = article;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((article == null) ? 0 : article.hashCode());
		result = prime * result + ((dateEnchere == null) ? 0 : dateEnchere.hashCode());
		result = prime * result + montantEnchere;
		result = prime * result + ((utilisateur == null) ? 0 : utilisateur.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Enchere other = (Enchere) obj;
		if (article == null) {
			if (other.article != null)
				return false;
		} else if (!article.equals(other.article))
			return false;
		if (dateEnchere == null) {
			if (other.dateEnchere != null)
				return false;
		} else if (!dateEnchere.equals(other.dateEnchere))
			return false;
		if (montantEnchere != other.montantEnchere)
			return false;
		if (utilisateur == null) {
			if (other.utilisateur != null)
				return false;
		} else if (!utilisateur.equals(other.utilisateur))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Enchere [dateEnchere=" + dateEnchere + ", montantEnchere=" + montantEnchere + ", utilisateur="
				+ utilisateur + ", article=" + article + "]";
	}
}