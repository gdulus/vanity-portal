<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title><g:message code="portal.searchResult.foundArticles.title" args="[viewModel.term]"/></title>
    <meta name="description" content="${message(code: 'portal.searchResult.foundArticles.description')}"/>
    <meta name="robots" content="noindex"/>
    <meta name="layout" content="list"/>
    <asset:stylesheet href="vanity/list.css"/>
</head>

<body>
<div id="summary" class="row">
    <div class="col-md-10 col-md-offset-1">
        <h2>
            <g:message code="portal.searchResult.foundArticles.h1" args="[viewModel.term]"/>
        </h2>
    </div>
</div>

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
                           params="[q: viewModel.term]"
                           total="${viewModel.numFound}"/>
    </div>
</div>

</body>
</html>

