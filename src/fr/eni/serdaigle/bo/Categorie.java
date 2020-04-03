package fr.eni.serdaigle.bo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

 
/**
 * Classe en charge de
 * @author Max
 * @version troc_encheres - v1.0
 * @date 28 mars 2020
 */
public class Categorie implements Serializable {
	private int noCategorie;
	private String libelle;
	private List<ArticleVendu> listeArticle;

	/**
	 * Constructeur par défaut
	 */
	public Categorie()  {
		this.listeArticle = new ArrayList<ArticleVendu>();
	}

	/**
	 * Constructeur
	 * @param noCategorie
	 * @param libelle
	 */
	public Categorie(int noCategorie, String libelle) {
		this();
		this.noCategorie = noCategorie;
		this.libelle = libelle;
	}

	public int getNoCategorie() {
		return noCategorie;
	}

	public void setNoCategorie(int noCategorie) {
		this.noCategorie = noCategorie;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public List<ArticleVendu> getListeArticle() {
		return listeArticle;
	}

	public void setListeArticle(List<ArticleVendu> listeArticle) {
		this.listeArticle = listeArticle;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((libelle == null) ? 0 : libelle.hashCode());
		result = prime * result + noCategorie;
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
		Categorie other = (Categorie) obj;
		if (libelle == null) {
			if (other.libelle != null)
				return false;
		} else if (!libelle.equals(other.libelle))
			return false;
		if (noCategorie != other.noCategorie)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Categorie [noCategorie=" + noCategorie + ", libelle=" + libelle + ", listeArticle=" + listeArticle
				+ "]";
	}

}