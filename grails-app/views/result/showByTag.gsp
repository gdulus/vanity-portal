<html>
<head>
    <title></title>
    <meta name="layout" content="result"/>
    <r:require module="showByTag"/>
</head>

<body>
<g:if test="${viewModel.celebrity}">
    <section>
        <h1>${viewModel.celebrity.firstName} ${viewModel.celebrity.lastName}</h1>
        ${viewModel.celebrity.description}
    </section>
</g:if>
<section>
    <h2>
        <g:message code="portal.searchResult.foundArticles"/>
    </h2>
    <g:each in="${viewModel.articles}" var="article">
        <article>
            <h3><g:link action="showArticle" params="${[hash: article.hash,
                    tag: viewModel.tag.name.encodeAsPrettyUrl(),
                    title: article.title.encodeAsPrettyUrl()]}">${article.title}</g:link></h3>

            <p>${article.shortBody}</p>
            <ul>
                <g:each in="${article.tags}" var="articleTag">
                    <li>
                        <g:link class="label label-success"
                                action="showByTag"
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
    <v:paginate controller="result"
                action="showByTag"
                params="[tagName: viewModel.tag.name.encodeAsPrettyUrl(), hash: viewModel.tag.hash]"
                startParamName="startElement"
                start="${viewModel.start}"
                total="${viewModel.numFound}"/>
</section>
</body>
</html>