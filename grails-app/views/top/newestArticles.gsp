<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title><g:message code="portal.top.newestArticles.title"/></title>
    <meta name="description" content="${message(code: 'portal.top.newestArticles.description')}"/>
    <meta name="layout" content="list"/>
    <asset:stylesheet href="vanity/list.css"/>
</head>

<body>

<div id="summary" class="row">
    <div class="col-md-10 col-md-offset-1">
        <h2><g:message code="portal.top.topArticles"/></h2>
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
                           total="${viewModel.total}"/>
    </div>
</div>
</body>
</html>