<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title><g:message code="portal.searchResult.foundArticles.title" args="[viewModel.tag.name]"/></title>
    <meta name="layout" content="list"/>
    <asset:stylesheet href="vanity/list.css"/>
</head>

<body>
<div id="summary" class="row">
    <div class="col-md-10 col-md-offset-1">
        <h2><g:message code="portal.searchResult.foundArticles" args="[viewModel.tag.name]"/></h2>
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

</body>
</html>

