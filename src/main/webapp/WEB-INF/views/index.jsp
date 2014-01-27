<!doctype html>
<html lang="en" ng-app="budgetApp">
<head>
<link rel="stylesheet" href="/${ context }/resources/css/app.css">
<link rel="stylesheet" href="/${ context }/resources/css/bootstrap.min.css">
<link rel="stylesheet" href="/${ context }/resources/css/bootstrap-responsive.min.css">

<link rel="stylesheet" href="/${ context }/resources/css/jquery/jquery-ui.min.css">

<script src="/${ context }/resources/js/jquery/jquery-1.10.2.min.js"></script>
<script src="/${ context }/resources/js/jquery/jquery-ui.min.js"></script>

<script src="/${ context }/resources/js/angular/constants.js"></script>
<script src="/${ context }/resources/js/angular/angular.min.js"></script>
<script src="/${ context }/resources/js/angular/angular-route.js"></script>
<script src="/${ context }/resources/js/angular/angular-resource.js"></script>
<script src="/${ context }/resources/js/angular/app.js"></script>
<script src="/${ context }/resources/js/angular/common.js"></script>
<script src="/${ context }/resources/js/angular/period.js"></script>
<script src="/${ context }/resources/js/angular/budget.js"></script>
<script src="/${ context }/resources/js/angular/operation.js"></script>
</head>
<body>

	<ul>
		<li>TODO i18n</li>
		<li><a href="#/period/list"/>Périodes</a></li>
		<li><a href="#/period/create"/>Ajouter période</a></li>
		<li><a href="#/budget/list"/>Budgets</a></li>
		<li><a href="#/budget/create"/>Ajouter budget</a></li>
	</ul>
 
  	<div ng-view></div>
 
</body>
</html>
