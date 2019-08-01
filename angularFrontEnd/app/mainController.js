'use strict';

angular.module('myApp.mainModule', ['ui.router'])

// .config(['$routeProvider', function($routeProvider) {
//   $routeProvider.when('/view1', {
//     templateUrl: 'view1/view1.html'
//   });
// }])

.service('remoteService3', function ($http) {
           var url = 'http://localhost:5000/company/all';
    return {
        get: function (inputStr) {
            return $http.get(url, { params: { "input": inputStr } }).then(function (resp) {
                return resp.data; 
            });
        }
    };
})
.controller('mainController', function ($scope, remoteService3) {
      /*$scope.currentActiveCompanies = ["1","2","3"];
    remoteService3.get('s').then(function (data) {
        $scope.currentActiveCompanies = data;
    }); */
  
  var arrayWithIds = [1, 2, 3, 4, 5, 6, 7]
$scope.currentActiveCompanies = [];

for (var j = 0; j < 10000; j++) {
     remoteService3.get('s').then(function (data) {
         /*console.debug("Call no: ", j , data);*/
    
         $scope.currentActiveCompanies.push(data);   
           console.log(" Call no : ", j);           
    },function(error) {
        console.log(error);        
    });               
}
});
