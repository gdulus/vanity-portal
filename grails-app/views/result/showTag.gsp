<html>
<head>
    <title></title>
    <meta name="layout" content="result"/>
    <r:require module="showTags"/>
</head>
<body>
    <section>
        <h1>${celebirty.firstName} ${celebirty.lastName}</h1>
        ${celebirty.description}
    </section>
    <section>
        <h2>
            <g:message code="portal.searchResult.foundArticles" />
        </h2>
        <g:each in="${articles}" var="article">
            <article>
                <h3><g:link action="showArticle" params="${[hash:article.hash,
                                                            tag: showTagPrettyUrl,
                                                            title:article.title.encodeAsPrettyUrl()]}">${article.title}</g:link></h3>
                <p>${article.shortBody}</p>
                <ul>
                    <g:each in="${article.tags}" var="tag">
                        <li><g:link class="label label-success" action="showTag" params="${[hash: tag.hash, tagName: tag.name.encodeAsPrettyUrl()]}">${tag.name}</g:link></li>
                    </g:each>
                </ul>
                <span>Czytaj całość na <a href="${article.url}"><g:message code="${article.source.target.name()}"/></a></span>
            </article>
        </g:each>
    </section>
</body>
</html>