<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Créer compte</title>
<!-- Bootstrap core CSS -->
<link
	href="${pageContext.request.contextPath}/vendor/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">

<!-- Custom styles for this template -->
<link href="${pageContext.request.contextPath}/css/4-col-portfolio.css"
	rel="stylesheet">
</head>
<body>
	<div class="container-fluid">
			<%@ include file="entete.html"%>

		<div class="col-lg-6 col-sm-12">
	
			<h3 class="my-4 col-lg-12 col-sm-12">${utilisateur.pseudo}</h3>
	
	 
          
	            <form action="" method="post">
	   
	                
	                <label for="pseudo">Pseudo :</label>
	                <p>${utilisateur.pseudo}<p>
					<br>
	
	                <label for="name">Nom :</label>
	                <p>${utilisateur.nom}</p>
					<br>
	
					<label for="firstname">Prénom :</label>
	             	<p>${utilisateur.prenom}</p>
					<br>
					
					<label for="mail">Email :</label>
	                <p>${utilisateur.email}</p>
					<br>
					
					<label for="tel">Téléphone :</label>
	                <p>${utilisateur.telephone}</p>
					<br>
					
					<label for="address">Rue :</label>
	                <p>${utilisateur.rue}</p>
					<br>
					
					<label for="cpo">Code Postal :</label>
	                <p>${utilisateur.codePostal}</p>
					<br>
					
					<label for="city">Ville :</label>
	                <p>${utilisateur.ville}</p>
					<br>
					
					</form>
				</div>	
				<div class="col-lg-6 col-sm-12">
						
						<a id="modifProfil" href="modifierProfil" class="btn btn-primary">Modifier</a>
	        	</div>
		<%@ include file="piedDePage.html" %>
	</div>	
	
</body>
</html>