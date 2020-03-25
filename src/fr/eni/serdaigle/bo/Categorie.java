package fr.eni.serdaigle.bo;

import java.io.Serializable;

public class Categorie implements Serializable {
	private int noCategorie;
	private String libelle;

	public Categorie()  {

	}

	public Categorie(int noCategorie, String libelle) {
		super();
		this.noCategorie = noCategorie;
		this.libelle = libelle;
	}

	public int getNo_categorie() {
		return noCategorie;
	}

	public void setNo_categorie(int noCategorie) {
		this.noCategorie = noCategorie;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + noCategorie;
		return result;
	}

	@Override
	public String toString() {
		return "Categorie [noCategorie=" + noCategorie + ", libelle=" + libelle + "]";
	}
	
}