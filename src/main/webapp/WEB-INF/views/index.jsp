<!doctype html>
<html lang="en" ng-app="budgetApp">
<head>
<link rel="stylesheet" href="/${ context }/resources/css/app.css">
<link rel="stylesheet" href="/${ context }/resources/css/bootstrap.min.css">
<link rel="stylesheet" href="/${ context }/resources/css/bootstrap-responsive.min.css">
<script src="/${ context }/resources/js/angular/angular.min.js"></script>
<script src="/${ context }/resources/js/angular/angular-route.js"></script>
<script src="/${ context }/resources/js/angular/angular-resource.js"></script>
<%-- <script src="/${ context }/resources/js/angular/angular-animate.js"></script> --%>
<script src="/${ context }/resources/js/angular/app.js"></script>
<script src="/${ context }/resources/js/angular/period.js"></script>
<%-- <script src="/${ context }/resources/js/angular/controllers.js"></script> --%>
<script src="/${ context }/resources/js/angular/filters.js"></script>
<%-- <script src="/${ context }/resources/js/angular/services.js"></script> --%>
</head>
<body>

	<ul>
		<li><a href="#/period/list"/>Accueil</a></li>
		<li><a href="#/period/create"/>Ajouter période</a></li>
	</ul>
 
  	<div ng-view></div>
 
</body>
</html>
