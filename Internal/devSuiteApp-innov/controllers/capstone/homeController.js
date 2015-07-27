routingExample.controller('CapstoneCarouselDemoCtrl', function($scope) {
    $scope.myInterval = 5000;
    $scope.slides = capStoneCarouselData;

});

routingExample.controller('CapstoneNavCtrl', ['$scope', '$location',
    function($scope, $location) {
        $scope.breadcrumbs = [];
        $scope.menu = capStoneMenuData;
    }
]);

routingExample.controller('CapstoneMainDespCtrl', function($scope) {
    $scope.maindesp = capStoneMainDespData;
});