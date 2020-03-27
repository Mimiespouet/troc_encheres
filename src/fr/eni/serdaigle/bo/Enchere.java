package fr.eni.serdaigle.bo;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Enchere implements Serializable {
	private LocalDateTime dateEnchere;
	private int montantEnchere;
	private int noUtilisateur;
	private int noArticle;

	public Enchere() {
	}

	public Enchere(LocalDateTime dateEnchere, int montantEnchere, int noUtilisateur, int noArticle) {
		this();
		this.dateEnchere = dateEnchere;
		this.montantEnchere = montantEnchere;
		this.noUtilisateur = noUtilisateur;
		this.noArticle = noArticle;
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

	public int getNo_utilisateur() {
		return noUtilisateur;
	}

	public void setNoUtilisateur(int noUtilisateur) {
		this.noUtilisateur = noUtilisateur;
	}

	public int getNo_article() {
		return noArticle;
	}

	public void setNoArticle(int noArticle) {
		this.noArticle = noArticle;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + noArticle;
		result = prime * result + noUtilisateur;
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
		if (noArticle != other.noArticle)
			return false;
		if (noUtilisateur != other.noUtilisateur)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Enchere [dateEnchere=" + dateEnchere + ", montantEnchere=" + montantEnchere + ", noUtilisateur="
				+ noUtilisateur + ", noArticle=" + noArticle + "]";
	}

}