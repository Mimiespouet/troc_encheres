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

}