<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Afficher le détail d'un enchère</title>
<!-- Bootstrap core CSS -->
<link
	href="${pageContext.request.contextPath}/vendor/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">

<link href="${pageContext.request.contextPath}/css/style.css"
	rel="stylesheet">
</head>
<body>
	<div id="container">
		
		<div class="container-fluid">
			<%@ include file="entete.html"%>
		<c:if test="${error != null}">
					<div class="col-lg-6 col-sm-12">
						<div class="alert alert-danger" role="alert">
							<p>${errorLogin}</p>
						</div>
					</div>
		</c:if>
		<div class="col-lg-6 col-sm-12">
			
			<h3 class="my-4 col-lg-12 col-sm-12">Détail vente</h3>
	           
	                <p>Nom de l'article : ${enchere.article.nomArticle}</p>
					<br>
	
	                <p>Description : ${enchere.article.description}</p>
					<br>
					
					<p>Catégorie : ${enchere.article.categorie.libelle}</p>
					<br>
	
					<p>Meilleure offre : ${enchere.montantEnchere}</p>
					<br>
					
					<p>Mise à prix : ${enchere.article.prixInitial}</p>
					<br>
					
					<p>Date de début de l'enchère : ${enchere.article.dateDebutEncheres}</p>
					<br>
					
					<p>Date de fin de l'enchère : ${enchere.article.dateFinEncheres}</p>
					<br>
					
					<label for="adress">Adresse de retrait :</label>
	                
	                <c:choose>
					        <c:when test="${enchere.article.retrait=null}">
								 </div>
					                 <p>${enchere.article.retrait.rue}</p> 
					                 <p>${enchere.article.retrait.codePostal} ${enchere.article.retrait.ville}</p>
					             </div>
					        </c:when>
					        <c:otherwise>
					             <div>
					                 <p>${enchere.article.vendeur.rue}</p> 
					                 <p> ${enchere.article.vendeur.codePostal}  ${enchere.article.vendeur.ville}</p>
					             </div>
					        </c:otherwise>
					 </c:choose>
					
					<p>Vendeur de l'article : ${enchere.article.vendeur.pseudo}</p>
					<br>	

		<c:if test="${utilisateur!=null}"> 
		
			<form action="encherir" method="post">
				<label for="proposition">Ma proposition :</label>
	                <input type="number"id="proposition" name="proposition" value="${enchere.montantEnchere + 1}" min="${enchere.montantEnchere + 1}">
					<br> 
				<input type="hidden" name="noArticle" value="${enchere.article.noArticle}">
				<div class="col-lg-6 col-sm-12">
					<input type="submit" name="nvEnchere" value="Enchérir">
				</div> 
			</form>
		</c:if>	
				
		<div class="col-lg-6 col-sm-12">
			<a id="retour" href="accueil" class="btn btn-primary">Retour</a>
		</div>
			
		<%@ include file="piedDePage.html" %>
	</div>	
</body>
</html>