<!doctype html>
<html lang="en" ng-app="budgetApp">
<head>

	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    
    <title>Budget</title>
    
    <link rel="shortcut icon" href="/${ context }/resources/img/favicon.png">
    
	<link rel="stylesheet" href="/${ context }/resources/css/app.css">
	<link rel="stylesheet" href="/${ context }/resources/css/bootstrap/bootstrap.min.css">
	<link rel="stylesheet" href="/${ context }/resources/css/bootstrap/bootstrap-theme.min.css">
	
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
	<script src="/${ context }/resources/js/angular/period-budget.js"></script>
	
</head>

<body>

	<div class="navbar navbar-inverse navbar-static-top">
		<div class="navbar-header">
          <a class="navbar-brand" href="#/period/list"">Budget</a>
        </div>
		<div class="collapse navbar-collapse">
          <ul class="nav navbar-nav">
			<li><a href="#/period/list"/>P�riodes</a></li>
			<li><a href="#/period/create"/>Ajouter p�riode</a></li>
			<li><a href="#/budget/list"/>Budgets</a></li>
			<li><a href="#/budget/create"/>Ajouter budget</a></li>
          </ul>
        </div>
	</div>

  	<div ng-view></div>
 
</body>
</html>
