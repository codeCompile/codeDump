'use strict';

// Declare app level module which depends on views, and components
angular.module('myApp', [
  'ui.router',
  'ngRoute',
  'myApp.mainModule',  
  'myApp.searchView',  
  'myApp.version'  
]).run([
        "$state",
         function($state){
            $state.go('main');
         }
     ])
.config(['$locationProvider', '$routeProvider', function($locationProvider, $routeProvider) {
  //$locationProvider.hashPrefix('!');
 $routeProvider.when('/about', {
    templateUrl: 'about.html'
  })
  .when('/', {
      templateUrl: 'mainContent.html',
      controller: 'mainController'
    })
  .when('/main', {
      templateUrl: 'mainContent.html',
      controller: 'mainController'
    })
    .when('/contact', {
      templateUrl: 'contact.html'
    });
     
  //$routeProvider.otherwise({redirectTo: '/main'});
}])
.config(function($stateProvider, $urlRouterProvider){
    // For any unmatched url, send to /route1
    //$urlRouterProvider.otherwise("/main")
      
     $stateProvider
        .state('main', {
            url: "/main",
           //abstract:true ,
            views: {
               "searchView": {
                    templateUrl: 'rightNav/rightNav.html',
                    controller: 'rightNavController'
                }
            }
        })
    });
