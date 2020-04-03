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
			
			<h3 class="my-4 col-lg-12 col-sm-12">Vous avez remporté l'enchère</h3>
			
			
	                <p>Nom de l'article : ${enchere.article.nomArticle}</p>
					<br>

					<p>Meilleure offre : ${enchere.montantEnchere}</p>
					<br>
					
					<p>Mise à prix : ${enchere.article.prixInitial}</p>
					<br>

					<p>Retrait :</p> 
					   <c:choose>
					        <c:when test="${enchere.article.retrait != null }">
					             <div>
					                 <p>${enchere.article.retrait.rue}</p>
					                 <p>${enchere.article.retrait.codePostal} ${enchere.article.retrait.ville}</p>
					             </div>
					        </c:when>
					        <c:otherwise>
					             <div>
					                 <p>${enchere.article.vendeur.rue}</p>
					                 <p>${enchere.article.vendeur.codePostal}  ${enchere.article.vendeur.ville}</p>
					             </div>
					        </c:otherwise>
					      </c:choose>
									
					<p>Vendeur de l'article :</p>
	                <p>${enchere.article.vendeur.pseudo}</p>
	                <p>Tel :</p>
	                <p>${enchere.article.vendeur.telephone}</p> 
					<br>
					
				<div class="col-lg-6 col-sm-12">
					<a id="retraitOK" href="accueil" class="btn btn-primary">Retrait effectué</a>
				</div>
		
		</div>
		<%@ include file="piedDePage.html" %>
	</div>	
	
	
</body>
</html>