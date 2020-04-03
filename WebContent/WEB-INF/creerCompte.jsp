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
	
			<h3 class="my-4 col-lg-12 col-sm-12">Créer un compte</h3>
			
			<p>${error}</p>
          
            <form action="inscription" method="post">
   
                
                <label for="pseudo">Pseudo :</label>
                <input type="text" id="pseudo" name="pseudo" required>
				<br>

                <label for="nom">Nom :</label>
                <input type="text" id="nom" name="nom" required>
				<br>

				<label for="prenom">Prénom :</label>
                <input type="text" id="prenom" name="prenom" required>
				<br>
				
				<label for="mail">Email :</label>
                <input type="email" id="mail" name="mail" required>
				<br>
				
				<label for="tel">Téléphone :</label>
                <input type="tel" id="tel" name="tel">
				<br>
				
				<label for="rue">Rue :</label>
                <input type="text" id="rue" name="rue" required>
				<br>
				
				<label for="cpo">Code Postal :</label>
                <input type="text"id="cpo" name="cpo" required>
				<br>
				
				<label for="ville">Ville :</label>
                <input type="text" id="ville" name="ville" required>
				<br>
				
				<label for="password">Mot de passe :</label>
                <input type="password" id="password" name="password" required>
				<br>
				
				<label for="checkPassword">Confirmation :</label>
                <input type="password" id="checkPassword" name="checkPassword" required>
				<br>
				
				<div class="col-lg-6 col-sm-12">
				<button id="nvCompte" name="nvCompte">Créer</button>
            	 </div>
			</form>
		</div>	
				
		
            <div class="col-lg-6 col-sm-12">
            <a id="annuler" href="accueil" class="btn btn-primary">Annuler</a>
            	
           </div>
           
        <%@ include file="piedDePage.html" %>
	</div>
	<script src="${pageContext.request.contextPath}/jquery/jquery.min.js"></script>
</body>
</html>