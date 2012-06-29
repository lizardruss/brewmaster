'use strict';

function MashCalculator($scope, $http) {
    $http.get('model/mashtun.json').success(function(data) {
        $scope.mashtun = data;
    });

    $http.get('model/mashstep.json').success(function(data) {
        $scope.mashstep = data;
    });
}
MashCalculator.$inject = [$scope];