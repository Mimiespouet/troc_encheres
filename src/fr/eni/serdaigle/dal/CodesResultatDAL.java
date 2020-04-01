package fr.eni.serdaigle.dal;

/**
 * Les codes disponibles sont entre 10000 et 19999
 */
public abstract class CodesResultatDAL {

	/**
	 * Echec général quand erreur à l'insertion
	 */
	public static final int SELECT_LOGIN_ECHEC = 10000;
	
	public static final int INSERT_USER_ECHEC = 10001;
	
	public static final int INSERT_ARTICLE_ECHEC = 10002;
	
	public static final int UPDATE_UTILISATEUR_ECHEC = 10003;

	public static final int DELETE_USER = 10004;
	
	public static final int INSERT_CATEGORIE_ECHEC = 10005;
	
	public static final int LOGIN_INCORRECT = 10006;
	
	public static final int INSERT_ENCHERE_ECHEC = 10007;
	
	public static final int SELECT_CATEGORIE_ECHEC = 10008;
	
	public static final int UPDATE_ARTICLE_ECHEC = 10009;
	
	public static final int SELECT_MAX_ENCHERE_ECHEC = 10010;
	
	public static final int SELECT_VENTE_REMPORTE_ECHEC = 10011;

	public static final int SELECT_ALL_ENCHERE_ECHEC = 10012;
}
