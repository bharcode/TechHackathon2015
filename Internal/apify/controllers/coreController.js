routingExample.controller('CorePlatformsDespCtrl', function($scope) {
    $scope.maindesp = coreMainDespData;
});

routingExample.controller('CoreUtilityDespCtrl', function($scope) {
    $scope.maindesp = coreUtilityDespData;
});

routingExample.controller('CoreReserchDespCtrl', function($scope) {
    $scope.maindesp = coreReserchDespData;
});

routingExample.controller('CoreWorkshopDespCtrl', function($scope) {
    $scope.maindesp = coreWorkshopDespData;
});

routingExample.controller('CoreTeamDespCtrl', function($scope) {
    $scope.maindesp = coreTeamDespData;
});

routingExample.controller('HomeNavCtrl', ['$scope', '$location',
    function($scope, $location) {
        $scope.breadcrumbs = [];
        $scope.menu = homeMenuData;
    }
]);