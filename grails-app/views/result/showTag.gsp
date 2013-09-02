<html>
<head>
    <title></title>
    <meta name="layout" content="result"/>
    <r:require module="showTags"/>
</head>
<body>
    <g:if test="${viewModel.celebirty}">
        <section>
            <h1>${viewModel.celebirty.firstName} ${viewModel.celebirty.lastName}</h1>
            ${viewModel.celebirty.description}
        </section>
    </g:if>
    <section>
        <h2>
            <g:message code="portal.searchResult.foundArticles" />
        </h2>
        <g:each in="${viewModel.articles}" var="article">
            <article>
                <h3><g:link action="showArticle" params="${[hash:article.hash,
                                                            tag: viewModel.tag.name.encodeAsPrettyUrl(),
                                                            title:article.title.encodeAsPrettyUrl()]}">${article.title}</g:link></h3>
                <p>${article.shortBody}</p>
                <ul>
                    <g:each in="${article.tags}" var="articleTag">
                        <li><g:link class="label label-success" action="showTag" params="${[hash: articleTag.hash,
                                                                                            tagName: articleTag.name.encodeAsPrettyUrl()]}">${articleTag.name}</g:link></li>
                    </g:each>
                </ul>
                <span><g:message code="portal.searchResult.readRest" /> <a href="${article.url}" target="_blank"><g:message code="${article.source.target.name()}"/></a></span>
            </article>
        </g:each>
    </section>
</body>
</html>