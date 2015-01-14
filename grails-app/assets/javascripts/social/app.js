var V = V || {};
V.Social = V.Social || {};

/**
 * Utils
 */
V.Social.templatePath = function(path){
    return V.Config.contextPath + '/social/' + path
};

/**
 * Init module
 */
V.Social.app = angular.module('Social.app', ['ngRoute', 'Social.controllers']);

/**
 * Routing
 */
V.Social.app.config(['$routeProvider', '$locationProvider',
    function ($routeProvider, $locationProvider) {
        $routeProvider.
            when('/', {
                templateUrl: V.Social.templatePath('comments')
            }).
            when('/auth', {
                templateUrl: V.Social.templatePath('auth'),
                controller: 'LoginController'
            }).
            otherwise({
                redirectTo: '/'
            });
    }]);

