function AppConstants()
{
    //var baseUrl = 'http://akimotoj.azurewebsites.net/aristoweb/';
    var baseUrl = 'http://localhost:8080/aristoweb/';
    var serviceUrlForCreatePost = baseUrl + 'api/createpost';    
    return {
    baseUrl:baseUrl,
    serviceUrlForCreatePost:serviceUrlForCreatePost
    }
    
}
AppConstants.Instance = new AppConstants();

angular.module('myApp').factory('sharedService', function($rootScope) {
   
     var DATAUPDATEDMESSAGE = 'dataupdate';

    var newUpdatePublish = function(blogDataFlag, blogData) {
        $rootScope.$broadcast(DATAUPDATEDMESSAGE,
            {
                blogDataFlag: blogDataFlag,
                blogData: blogData
            });
    };
 
    var onUpdateSubscribe = function($scope, handler) {
        $scope.$on(DATAUPDATEDMESSAGE, function(event, message){
            // note that the handler is passed the problem domain parameters 
            handler(message.blogDataFlag, message.blogData);
        });
    };
   
    return {
        newUpdatePublish: newUpdatePublish,
        onUpdateSubscribe:onUpdateSubscribe
    }
});  