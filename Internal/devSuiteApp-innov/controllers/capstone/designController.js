routingExample.controller('capstoneDesignCtrl', ['$scope', '$location',
    function($scope, $location, $modal, $log) {
        routingExample.controller('capstoneNavCtrl', ['$scope', '$location',
            function($scope, $location) {
                $scope.breadcrumbs = [];
                $scope.menu = capStoneMenuData;
            }
        ]);
        $scope.submenu = capStoneSubMenuData;
        $scope.modalimgData;
        $scope.modalImgDataPre = "/img/modalImg/model_";

        $scope.open = function($modal, modalContent) {
            $scope.modalimgData = $scope.modalImgDataPre + modalContent;
            var modalInstance = $modal.open({
                templateUrl: '/views/modelContent.html',
                controller: 'ModalInstanceCtrl',
                size: 'lg',
                resolve: {
                    /* items: function () {
          return $scope.items;
        },*/
                    modalimgData: function() {
                        return $scope.modalimgData;
                    }
                }
            });

            modalInstance.result.then(function(selectedItem) {
                $scope.selected = selectedItem;
            }, function() {
                $log.info('Modal dismissed at: ' + new Date());
            });
        };

    }
]);

routingExample.controller('ModalInstanceCtrl', function($scope, $modalInstance, modalimgData) {
    $scope.modalimgData = modalimgData;
    //  $scope.items = items;
    $scope.selected = {
        // item: $scope.items[0]
    };

    $scope.ok = function() {
        //  $modalInstance.close($scope.selected.item);
        $modalInstance.close();
    };

    $scope.cancel = function() {
        $modalInstance.dismiss('cancel');
    };
});


routingExample.controller('capstoneDocCtrl', function($scope) {
    $scope.pdfUrl = '/resources/capstone_design.pdf';
});

