routingExample.directive('teamMainContent', function() {
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
            '<div class="project_common_1 project_bg_1_1" style="padding:20px">' +
            '<img style="border-radius:50%;width:140px;height:140px;" src= {{desp.viz}}></img>' +
            '</div>' +
            '</div>' +
            '</div></div>'

    }
});