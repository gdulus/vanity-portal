</body>
</html>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title></title>
    <meta name="layout" content="result"/>
    <r:require module="mostPopularArticles"/>
</head>

<body>
<h2><g:message code="portal.top.hottestArticles"/></h2>

<g:each in="${viewModel.articles}" var="article">
    <article>
        <h3><g:link controller="result"
                    action="showArticle"
                    params="${[hash: article.hash, title: article.title.encodeAsPrettyUrl()]}">${article.title}</g:link></h3>

        <p>${article.shortBody}</p>
        <ul>
            <g:each in="${article.tags}" var="articleTag">
                <li>
                    <g:link class="label label-success"
                            controller="search"
                            action="searchByTag"
                            params="${[hash: articleTag.hash, tagName: articleTag.name.encodeAsPrettyUrl()]}">${articleTag.name}
                    </g:link>
                </li>
            </g:each>
        </ul>
        <span><g:message code="portal.searchResult.readRest"/>
            <a href="${article.url}" target="_blank"><g:message code="${article.source.target.name()}"/></a>
        </span>
    </article>
</g:each>

<g:paginate next="${g.message(code: 'portal.paginate.next')}"
            prev="${g.message(code: 'portal.paginate.prev')}"
            total="${viewModel.total}"/>

</body>
</html>