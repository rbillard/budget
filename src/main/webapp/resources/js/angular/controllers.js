var budgetControllers = angular.module('budgetControllers', []);
 
//budgetControllers.controller('BudgetListCtrl', ['$scope', '$http',
//  function ($scope, $http) {
//    $http.get('/budget/periods').success(function(data) {
//      $scope.periods = data;
//    });
// 
//    $scope.orderProp = 'dateDebut';
//  }]);

budgetControllers.controller('BudgetListCtrl', ['$scope', 'BudgetSrv',
    function ($scope, BudgetSrv) {
   
  	$scope.periods = BudgetSrv.query();
  	
      $scope.orderProp = 'dateDebut';
}]);
 
//budgetControllers.controller('BudgetDetailCtrl', ['$scope', '$routeParams', '$http',
//  function($scope, $routeParams, $http) {
//    $http.get('/budget/period/' + $routeParams.periodId).success(function(data) {
//        $scope.period = data;
//        $scope.mainImageUrl = data.img[0];
//      });
//    
//    $scope.setImage = function(imageUrl) {
//        $scope.mainImageUrl = imageUrl;
//      }
//    
//  }]);


budgetControllers.controller('BudgetDetailCtrl', ['$scope', '$routeParams', 'BudgetSrv',
  function($scope, $routeParams, BudgetSrv) {
	$scope.period = BudgetSrv.get({periodId: $routeParams.periodId}, function(period) {
		console.log(period);
		console.log(period.img);
		console.log(period.img[0]);
	    $scope.mainImageUrl = period.img[0];
	  });
	
	$scope.setImage = function(imageUrl) {
		$scope.mainImageUrl = imageUrl;
	}
	
}]);
