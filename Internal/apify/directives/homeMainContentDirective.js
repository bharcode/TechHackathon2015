routingExample.directive('mainContent', function() {
    return {
        restrict: 'A',
        scope: {
            maindesp: "="
        },
        template: '<div class="row featurette" ng-repeat="desp in maindesp" style="border-bottom:1px solid #ccc;padding-bottom:20px;"' +
            '<div>' +
            '<div class="col-md-7">' +
            '<h2 class="featurette-heading">{{desp.header}}</h2>' +
            '<p class="lead">{{desp.content}}<a href="#/devsuite/plan">{{desp.anchorHref}}</a></p>' +
            '</div>' +
            '<div class="col-md-5">' +
            '<div class="project_common project_bg_1">' +
            '<h2 align="center">{{desp.viz}}</h2>' +
            '</div>' +
            '</div>' +
            '</div></div>'

    }
});