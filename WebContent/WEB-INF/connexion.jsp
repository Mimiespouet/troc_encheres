<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Session</title>
<!-- Bootstrap core CSS -->
<link
	href="${pageContext.request.contextPath}/vendor/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">

<!-- Custom styles for this template -->
<link href="${pageContext.request.contextPath}/css/4-col-portfolio.css"
	rel="stylesheet">
</head>
<body>
	<div id="container">
		
			<div class="col-lg-6 col-sm-12">
				<%@ include file="entete.html"%>
			</div>

			<div class="col-lg-6 col-sm-12">

				<p>${success}</p>
				<form action="login" method="post">
				
				<c:if test="${errorLogin != null}"> 
	
						<div class="col-lg-6 col-sm-4">
							<div class="alert alert-danger" role="alert">
							  <p>${errorLogin}</p>
							</div>
						</div>
				</c:if>
						<label for="username"><b>Identifiant : </b></label> 
						<input type="text" placeholder="Entrer votre identifiant" id="username" name="username" required> <br> 
					
						<label for="password"><b>Mot de passe :</b></label> 
						<input type="password" placeholder="Entrer votre mot de passe" id="password" name="password" required> <br>


						<button id="connexion" name="connexion">Connexion</button>


						<input type="checkbox" id="remember" name="remember" value="remember"> 
						<label for="remember"><b>Se souvenir de moi</b></label> 
						<a id="ForgotPassword" href="">Mot de passe oublié</a> <br>

				</form>
				
				<a id="creaCompte" href="inscription" class="btn btn-primary">Créer un compte</a>
				
			</div>
		</div>



		<%@ include file="piedDePage.html"%>
</body>
</html>