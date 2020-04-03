<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Accueil</title>
<!-- Bootstrap core CSS -->
<link
	href="${pageContext.request.contextPath}/vendor/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">

<link href="${pageContext.request.contextPath}/css/style.css"
	rel="stylesheet">
</head>
<body>
	<div class="container-fluid">
			<%@ include file="entete.html"%>
		<p>${success}</p>
		<c:if test="${error != null}">
			<div class="col-lg-6 col-sm-12">
				<div class="alert alert-danger" role="alert">
					<p>${errorLogin}</p>
				</div>
			</div>
		</c:if>

		<div class="col-lg-6 col-sm-12">
			<c:if test="${utilisateur == null}">
				<a href="connexion">S'inscrire - se connecter</a>
			</c:if>
		</div>

		<c:if test="${utilisateur != null}">
			<div class="col-lg-12 col-sm-12 d-flex justify-content-around">
				<a href="vendreArticle">Vendre un article</a> <a href="profil">Mon
					profil</a> <a href="logout">Déconnexion</a>
			</div>
			<div class="col-lg-6 col-sm-12">
				<p>${utilisateur.pseudo} est connecté(e)</p>
			</div>
		</c:if>

		<!-- Page Heading -->
		<form action="accueil" method="post">
			<c:if test="${utilisateur != null}">
				<div class="col-lg-6 col-sm-12">
					<h3 class="my-4 col-lg-12 col-sm-12">Filtres :</h3>
					<p>Achats</p>
					<input type="radio" id="encheresOuvertes" name="filtre" value="encheresOuvertes"> <label for="encheresOuvertes">enchères ouvertes</label> 
					<input type="radio" id="mesEncheres" name="filtre" value="mesEncheres"> <label for="mesEncheres">mes enchères en cours</label> 
					<!-- <input type="radio" id="encheresRemportees" name="filtre" value="encheresRemportees"> <label for="encheresRemportees">mes enchères remportées</label> -->
					<br>
					<p>Ventes</p> 
					<input type="radio" id="mesVentes" name="filtre" value="mesVentes"> <label for="mesVentes">mes ventes en cours</label> 
					<input type="radio" id="ventesNonDebutees" name="filtre" value="ventesNonDebutees"> <label for="ventesNonDebutees">ventes non débutées</label> 
					<input type="radio" id="ventesTerminees" name="filtre" value="ventesTerminees"> <label for="ventesTerminees">ventes terminées</label> 
				</div>
			</c:if>			
			<label for="categorie">Catégories : </label> 
			<select id="categorie" name="categorie" size="1">
				<option value="" selected="selected">Toutes</option>
				<c:forEach var="categorie" items="${listeCategorie}">
					<option value="${categorie.libelle}">${categorie.libelle}</option>
				</c:forEach>
			</select> 
			<br> 
			<input class="col-lg-2 col-sm-6" type="text" placeholder="Le nom de l'article contient" id="nomArticleContient" name="nomArticleContient"> 
			<br>
			<button class="col-lg-2 col-sm-12" id="research" name="research">Rechercher</button>
			<br>
		</form>
		
		<c:forEach var="enchere" items="${listeEnchere}">
			<div class="col-lg-5 col-sm-12 card">
				<div class="card-body">
					<c:choose>
						<c:when test="${utilisateur != null}">
							<a href="afficherDetailEnchere?noArticle=${enchere.article.noArticle}">${enchere.article.nomArticle}</a>
						</c:when>
						<c:otherwise>
							${enchere.article.nomArticle}
						</c:otherwise>
					</c:choose>
					<c:choose>
						<c:when test="${enchere.montantEnchere != 0}">
							<p>Prix : ${enchere.montantEnchere}</p>
						</c:when>
						<c:otherwise>
							<p>Prix : ${enchere.article.prixInitial}</p>
						</c:otherwise>
					</c:choose>
					<p>
						Fin de l'enchère :
						<c:forTokens var="token"
							items="${enchere.article.dateFinEncheres}" delims="T">
							<c:out value="${token}" />
						</c:forTokens>
					</p>

					<c:choose>
						<c:when test="${enchere.article.retrait.rue != null}">
							<p>Retrait : ${enchere.article.retrait.rue}
								${enchere.article.retrait.codePostal}
								${enchere.article.retrait.ville}</p>
						</c:when>
						<c:otherwise>
							<p>Retrait : ${enchere.article.vendeur.rue}
								${enchere.article.vendeur.codePostal}
								${enchere.article.vendeur.ville}</p>
						</c:otherwise>
					</c:choose>
					<p>Vendeur : ${enchere.article.vendeur.pseudo}</p>
				</div>

			</div>
		</c:forEach>
		<%@ include file="piedDePage.html"%>
	</div>
	<script src="${pageContext.request.contextPath}/jquery/jquery.min.js"></script>
</body>
</html>