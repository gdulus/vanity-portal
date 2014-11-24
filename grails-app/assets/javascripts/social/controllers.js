var V = V || {};
V.Social = V.Social || {};
V.Social.controllers = angular.module('Social.controllers', []);

V.Social.controllers.controller('MainController', ['$scope', function ($scope) {
}]);

V.Social.controllers.controller('ListController', ['$scope', '$http', function ($scope, $http) {
    $scope.comments = [];

    $scope.loadMore = function () {
        $http({
            url: "http://localhost:8080/comments/xxxx",
            method: "GET"
        }).success(function (data, status, headers, config) {
            angular.forEach(data, function (value, key) {
                $scope.comments.push(value);
            });
            $scope.more = true;
        }).error(function (data, status, headers, config) {
            $scope.status = status;
        });
    };

    $scope.loadMore();

}]);

V.Social.controllers.controller('FormController', ['$scope', '$location', function ($scope, $location) {
    $scope.submitComment = function () {
        V.Logger.info('Submit comment');
        $location.url('/auth');
    };
}]);

V.Social.controllers.controller('LoginController', ['$scope', function ($scope) {
    $scope.submit = function () {
        V.Logger.info('Login');
    };

    $scope.fbLogin = function () {
        V.Logger.info('Login FB');
    };
}]);

V.Social.controllers.controller('RegisterController', ['$scope', function ($scope) {
    $scope.submit = function () {
        V.Logger.info('Register');
    };
}]);

