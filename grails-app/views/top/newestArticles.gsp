<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title><g:message code="portal.top.topArticles"/></title>
    <meta name="layout" content="list"/>
</head>

<body>
<div id="summary" class="row">
    <div class="col-md-10 col-md-offset-1">
        <h2><g:message code="portal.top.topArticles"/></h2>
    </div>
</div>

<g:each in="${viewModel.articles}" var="article">
    <div id="articles" class="row">
        <div class="col-md-10 col-md-offset-1">
            <ul>
                <li class="article">
                    <h4>
                        <g:link controller="result"
                                action="showArticle"
                                params="${[id: article.id, title: article.title.encodeAsPrettyUrl()]}">${article.title}</g:link>

                    </h4>

                    <div class="date"><g:formatDate format="yyyy-MM-dd" date="${article.publicationDate}"/></div>

                    <p>
                        ${article.shortBody}
                    </p>

                    <div class="source">
                        <g:message code="portal.searchResult.readRest"/>
                        <a href="${article.url}" target="_blank"><g:message code="${article.source.target.name()}"/></a>

                        <div class="tags">
                            <g:each in="${article.tags}" var="articleTag">
                                <g:link controller="search"
                                        action="searchByTag"
                                        params="${[tagName: articleTag.normalizedName]}">${articleTag.name}</g:link>
                            </g:each>
                        </div>
                </li>
            </ul>
        </div>
    </div>
</g:each>

<div id="pagination" class="row">
    <div class="col-md-10 col-md-offset-1">
        <v:paginateDefault next="${g.message(code: 'portal.paginate.next')}"
                           prev="${g.message(code: 'portal.paginate.prev')}"
                           total="${viewModel.total}"/>
    </div>
</div>
</body>
</html>