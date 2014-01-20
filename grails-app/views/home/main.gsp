<html>
<head>
    <title></title>
    <meta name="layout" content="home"/>
    <r:require module="home"/>
</head>

<body>
<!--
        page header
    -->
<div class="page-header top row">
    <div class="span12">
        <a href="./" class="logo offset3"><g:message code="portal.title"/></a>

        <div class="menu">
            <g:link controller="top" action="newestArticles"><g:message code="portal.menu.newestArticles"/></g:link>
            <g:link controller="top" action="mostPopularArticles"><g:message
                    code="portal.menu.mostImportantArticles"/></g:link>
            <g:link controller="top" action="mostPopularTags"><g:message
                    code="portal.menu.mostPopularCelebrities"/></g:link>
        </div>
    </div>
</div>
<!--
    search box
-->
<div class="search row">
    <div class="span12 search">
        <v:searchWidget/>
    </div>
</div>
<!--
    popular tags
-->
<g:if test="${viewModel.hottestTags}">
    <div class="section popular row">
        <div class="span8  offset2">
            <h4 class="title muted"><g:message code="portal.mainPage.mostPopularSectionTitle"/></h4>

            <p class="tag-cloud">
                <g:each in="${viewModel.hottestTags}" var="hottestTag">
                    <a href="${createLink(controller: 'search', action: 'searchByTag', params: [tagName: hottestTag.tag.normalizedName])}"
                       class="tag scale${hottestTag.rank}" title="${hottestTag.tag.name}">${hottestTag.tag.name}</a>
                </g:each>
            </p>
        </div>
    </div>
</g:if>
<!--
  promoted tags
-->
<g:if test="${viewModel.promotedTags}">
    <div class="section icons row">
        <div class="span8  offset2">
            <h4 class="title muted"><g:message code="portal.mainPage.ourIconsSectionTitle"/></h4>

            <p>
                <g:each in="${viewModel.promotedTags}" var="tag">
                    <a href="${createLink(controller: 'search', action: 'searchByTag', params: [tagName: tag.normalizedName])}"
                       class="tag-scale1" title="${tag.name}">${tag.name}</a>
                </g:each>
            </p>
        </div>
    </div>
</g:if>
<!--
  news
-->
<div class="section news row">
    <div class="span4  offset2">
        <h4 class="title muted"><g:message code="portal.mainPage.newestArticlesSectionTitle"/></h4>
        <g:if test="${viewModel.newestArticles}">
            <ol>
                <g:each in="${viewModel.newestArticles}" var="article">
                    <li>
                        <g:link controller="result" action="showArticle"
                                params="${[id: article.id, title: article.title.encodeAsPrettyUrl()]}">${article.title}</g:link>
                    </li>
                </g:each>
            </ol>
        </g:if>
    </div>

    <div class="span4">
        <h4 class="title muted"><g:message code="portal.mainPage.mostImportantArticlesSectionTitle"/></h4>
        <g:if test="${viewModel.hottestArticles}">
            <ol>
                <g:each in="${viewModel.hottestArticles}" var="article">
                    <li>
                        <g:link controller="result" action="showArticle"
                                params="${[id: article.id, title: article.title.encodeAsPrettyUrl()]}">${article.title}</g:link>
                    </li>
                </g:each>
            </ol>
        </g:if>
    </div>
</div>

</body>
</html>