const index = angular.module('app', ['ngAnimate', 'angular-growl']);

index.config(function (growlProvider) {
    growlProvider.onlyUniqueMessages(false);
    growlProvider.globalDisableCountDown(true);
    growlProvider.globalTimeToLive({
        info: 5000,
        success: 5000,
        warning: 15000,
        error: 15000
    });
});

index.controller('controller', function ($scope, $http, $interval, growl) {

    $scope.param = {
        url: '',
        startBlock: 0,
        endBlock: 0
    };

    $scope.start = function () {
        $http.post('api/start', $scope.param).then(function (response) {
            growl.success('Download has been started.');
        }, function (error) {
            growl.error(error);
        });
    };

    $scope.model = {
        blocks: []
    };

    $scope.load = function () {
        $http.get('api/load').then(function (response) {
            $scope.model = response.data;
        }, function (error) {
            growl.error(error);
        });
    };

    $scope.load();

    $interval($scope.load, 5 * 1000);

});
