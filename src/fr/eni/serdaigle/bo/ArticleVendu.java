package fr.eni.serdaigle.bo;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe en charge de l'objet Article
 * @author Max
 * @version Trocencheres - v1.0
 * @date 28 mars 2020
 */
public class ArticleVendu implements Serializable {
	private int noArticle;
	private String nomArticle;
	private String description;
	private LocalDateTime dateDebutEncheres;
	private LocalDateTime dateFinEncheres;
	private int prixInitial;
	private int prixVente;
	private Utilisateur vendeur;
	private Utilisateur acheteur;
	private Categorie categorie;
	private boolean etatVente;
	private List<Enchere> listeEnchere;

	/**
	 * Constructeur
	 */
	public ArticleVendu() {
		this.listeEnchere = new ArrayList<Enchere>();
	}

	/**
	 * Constructeur pour la servlet Vendre
	 * @param nomArticle
	 * @param description
	 * @param dateDebutEncheres
	 * @param dateFinEncheres
	 * @param prixInitial
	 * @param vendeur
	 * @param categorie
	 * @param etatVente
	 */
	public ArticleVendu(String nomArticle, String description, LocalDateTime dateDebutEncheres,
			LocalDateTime dateFinEncheres, int prixInitial, Utilisateur vendeur, Categorie categorie,
			boolean etatVente) {
		this();
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFinEncheres;
		this.prixInitial = prixInitial;
		this.vendeur = vendeur;
		this.categorie = categorie;
		this.etatVente = etatVente;
	}
	
	/**
	 * Constructeur pour l'affichage en page d'accueil
	 * @param noArticle
	 * @param nomArticle
	 * @param description
	 * @param dateDebutEncheres
	 * @param dateFinEncheres
	 * @param prixInitial
	 * @param vendeur
	 * @param categorie
	 * @param etatVente
	 */
	public ArticleVendu(int noArticle, String nomArticle, String description, LocalDateTime dateDebutEncheres,
			LocalDateTime dateFinEncheres, int prixInitial, Utilisateur vendeur, Categorie categorie,
			boolean etatVente) {
		this(nomArticle, description, dateDebutEncheres, dateFinEncheres, prixInitial, vendeur, categorie, etatVente);
		this.noArticle = noArticle;
	}

	/**
	 * Constructeur complet
	 * @param noArticle
	 * @param nomArticle
	 * @param description
	 * @param dateDebutEncheres
	 * @param dateFinEncheres
	 * @param prixInitial
	 * @param prixVente
	 * @param vendeur
	 * @param acheteur
	 * @param categorie
	 * @param etatVente
	 * @param listeEnchere
	 */
	public ArticleVendu(int noArticle, String nomArticle, String description, LocalDateTime dateDebutEncheres,
			LocalDateTime dateFinEncheres, int prixInitial, int prixVente, Utilisateur vendeur, Utilisateur acheteur,
			Categorie categorie, boolean etatVente, List<Enchere> listeEnchere) {
		this(noArticle, nomArticle, description, dateDebutEncheres, dateFinEncheres, prixInitial, vendeur, categorie, etatVente);
		this.prixVente = prixVente;
		this.acheteur = acheteur;
		this.listeEnchere = listeEnchere;
	}

	public int getNoArticle() {
		return noArticle;
	}

	public void setNoArticle(int noArticle) {
		this.noArticle = noArticle;
	}

	public String getNomArticle() {
		return nomArticle;
	}

	public void setNomArticle(String nomArticle) {
		this.nomArticle = nomArticle;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDateTime getDateDebutEncheres() {
		return dateDebutEncheres;
	}

	public void setDateDebutEncheres(LocalDateTime dateDebutEncheres) {
		this.dateDebutEncheres = dateDebutEncheres;
	}

	public LocalDateTime getDateFinEncheres() {
		return dateFinEncheres;
	}

	public void setDateFinEncheres(LocalDateTime dateFinEncheres) {
		this.dateFinEncheres = dateFinEncheres;
	}

	public int getPrixInitial() {
		return prixInitial;
	}

	public void setPrixInitial(int prixInitial) {
		this.prixInitial = prixInitial;
	}

	public int getPrixVente() {
		return prixVente;
	}

	public void setPrixVente(int prixVente) {
		this.prixVente = prixVente;
	}

	public Utilisateur getVendeur() {
		return vendeur;
	}

	public void setVendeur(Utilisateur vendeur) {
		this.vendeur = vendeur;
	}

	public Utilisateur getAcheteur() {
		return acheteur;
	}

	public void setAcheteur(Utilisateur acheteur) {
		this.acheteur = acheteur;
	}

	public Categorie getCategorie() {
		return categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	public boolean isEtatVente() {
		return etatVente;
	}

	public void setEtatVente(boolean etatVente) {
		this.etatVente = etatVente;
	}

	public List<Enchere> getListeEnchere() {
		return listeEnchere;
	}

	public void setListeEnchere(List<Enchere> listeEnchere) {
		this.listeEnchere = listeEnchere;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + noArticle;
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
		ArticleVendu other = (ArticleVendu) obj;
		if (noArticle != other.noArticle)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ArticleVendu [noArticle=" + noArticle + ", nomArticle=" + nomArticle + ", description=" + description
				+ ", dateDebutEncheres=" + dateDebutEncheres + ", dateFinEncheres=" + dateFinEncheres + ", prixInitial="
				+ prixInitial + ", prixVente=" + prixVente + ", vendeur=" + vendeur + ", acheteur=" + acheteur
				+ ", categorie=" + categorie + ", etatVente=" + etatVente + ", listeEnchere=" + listeEnchere + "]";
	}

}
