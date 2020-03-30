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

<!-- Custom styles for this template -->
<link href="${pageContext.request.contextPath}/css/4-col-portfolio.css"
	rel="stylesheet">

</head>
<body>

	<!-- ${request.getAttribute} -->

	<div id="container">
		
			<div class="col-lg-6 col-sm-12">
				<%@ include file="entete.html"%>
			</div>
			
			<div class="col-lg-6 col-sm-12">
				<c:if test="${utilisateur == null}"> 
					<a href="connexion">S'inscrire - se connecter</a>
				</c:if>	
			</div>
			
		
			<div class="col-lg-6 col-sm-12">
				<c:if test="${utilisateur != null}">
					<a href="vendreArticle">Vendre un article</a> 
					<a href="profil">Mon profil</a> 
					<a href="logout">Déconnexion</a>
				</c:if>
			</div>
			<c:if test="${utilisateur != null}">
				<div class="col-lg-6 col-sm-12">
					<p>${utilisateur.pseudo} est connectée</p>
				</div>
			</c:if>
			

			<!-- Page Heading -->
			
			<h3 class="my-4 col-lg-12 col-sm-12">Filtres :</h3>
			
				
				<div class="col-lg-6 col-sm-12">
					<c:if test="${utilisateur != null}"> 
					
						<input type="radio" id="filtre" name="filtre" value="buy" checked> 
						<label for="filtre">Achats<br></label>
							
							<input type="checkbox" id="buy" name="buy" value="open"> 
							<label for="buy">enchères ouvertes</label> <br>
							
							<input type="checkbox" id="buy" name="buy" value="current"> 
							<label for="buy">mes enchères en cours</label><br>
							
							<input type="checkbox" id="buy" name="buy" value="win"> 
							<label for="buy">mes enchères remportées</label><br>
						
						<input type="radio" id="filtre" name="filtre" value="sales"> 
						<label for="filtre">Mes ventes<br></label>
							
							<input type="checkbox" id="sales" name="sales" value="currentsales"> 
							<label for="sales">mes ventes en cours</label><br> 
							
							<input type="checkbox" id="sales" name="sales" value="upcommingsales"> 
							<label for="sales">ventes non débutées</label><br>
							
							<input type="checkbox" id="sales" name="sales" value="closesales"> 
							<label for="sales">ventes terminées</label><br>
					</c:if>	
				</div>
				
			<div class="col-lg-6 col-sm-12">


				<form action="" method="post">


					<label for="categorie">Catégories : </label> 
					<select id="categorie" name="categorie" size="1">
						<optgroup label="Categories">
							<option value="toutes" selected="selected">Toutes</option>
							<option value="Informatique">Informatique</option>
					        <option value="Livres">Livres</option>
					        <option value="Nourriture">Nourriture</option>
					        <option value="Vêtements">Vêtements</option>

						</optgroup>
					</select> <br> 
					
					<input type="text" placeholder="Le nom de l'article contient" id="nomArticle" name="nomArticle"> <br>


					<button class="col-lg-2 h-25" id="research" name="research">Rechercher</button>

					<br>


				</form>

			</div>
	
	</div>




		<%@ include file="piedDePage.html"%>
</body>
</html>