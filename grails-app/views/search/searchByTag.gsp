<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title><g:message code="portal.searchResult.foundTag.title" args="[viewModel.tag.name]"/></title>
    <meta name="description" content="${message(code: 'portal.searchResult.foundTag.description', args: [viewModel.tag.name])}"/>
    <meta name="layout" content="list"/>
    <asset:stylesheet href="vanity/list.css"/>
</head>

<body>

<div id="social" class="row" ng-app="Social.app">
    <div class="col-md-10 col-md-offset-1 stream" ng-view ng-controller="MainController"></div>
</div>

<div id="summary" class="row">
    <div class="col-md-10 col-md-offset-1">
        <h2><g:message code="portal.searchResult.foundTag.h1" args="[viewModel.tag.name]"/></h2>
    </div>
</div>

<g:if test="${viewModel.celebrity}">
    <div id="cellebrity" class="row">
        <div class="col-md-10 col-md-offset-1">
            <div class="wrapper">
                <div class="row">
                    <g:if test="${viewModel.celebrity.hasImage()}">
                        <div class="col-md-3 picture">
                            <v:celebrityImg bean="${viewModel.celebrity}" class="img-responsive"/>
                        </div>

                        <div class="col-md-9 description">
                            <h3>${viewModel.celebrity.fullName}</h3>

                            <p>${viewModel.celebrity.description}</p>
                        </div>
                    </g:if>
                    <g:else>
                        <div class="col-md-12 description">
                            <h3>${viewModel.celebrity.fullName}</h3>

                            <p>${viewModel.celebrity.description}</p>
                        </div>
                    </g:else>
                </div>
            </div>
        </div>
    </div>
</g:if>

<div id="articles" class="row">
    <div class="col-md-10 col-md-offset-1">
        <ul>
            <g:each in="${viewModel.articles}" var="article">
                <g:render template="/commons/article" model="[article: article]"/>
            </g:each>
        </ul>
    </div>
</div>

<div id="pagination" class="row">
    <div class="col-md-10 col-md-offset-1">
        <v:paginateDefault next="${g.message(code: 'portal.paginate.next')}"
                           prev="${g.message(code: 'portal.paginate.prev')}"
                           params="[tagName: viewModel.tag.normalizedName]"
                           total="${viewModel.numFound}"/>
    </div>
</div>

<content tag="javascript">
    <tracking:tag bean="${viewModel.tag}"/>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.2.26/angular.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.2.26/angular-route.js"></script>
    <asset:javascript src="social/init.js" />
</content>

</body>
</html>

