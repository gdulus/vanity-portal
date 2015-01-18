<li class="article">
    <h4>
        <g:link controller="result"
                action="showPreview"
                params="${[id: article.id, title: article.title.encodeAsPrettyUrl()]}">
            ${article.title}
        </g:link>

    </h4>

    <div class="date"><g:formatDate format="yyyy-MM-dd" date="${article.publicationDate}"/></div>

    <p>
        ${article.shortBody}
    </p>

    <div class="source">
        <g:message code="portal.searchResult.readRest"/>
        <a href="${article.url}" target="_blank"><g:message code="${article.source.target.name()}"/></a>

        <div class="tags">
            <g:each in="${article.publicTags}" var="articleTag">
                <g:link controller="search"
                        action="searchByTag"
                        params="${[tagName: articleTag.normalizedName]}">${articleTag.name}
                </g:link>
            </g:each>
        </div>
    </div>
</li>