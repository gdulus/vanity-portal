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
            <a href="#"><g:message code="portal.menu.newestArticles"/></a>
            <a href="#"><g:message code="portal.menu.mostPopularCelebrities"/></a>
            <a href="#"><g:message code="portal.menu.mostImportantArticles"/></a>
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
                    <a href="${createLink(controller: 'search', action: 'searchByTag', params: [hash: hottestTag.tag.hash, tagName: hottestTag.tag.name.encodeAsPrettyUrl()])}"
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
                    <a href="${createLink(controller: 'search', action: 'searchByTag', params: [hash: tag.hash, tagName: tag.name.encodeAsPrettyUrl()])}"
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
                        <g:link controller="search" action="showArticle"
                                params="${[hash: article.hash, title: article.title.encodeAsPrettyUrl()]}">${article.title}</g:link>
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
                        <g:link controller="search" action="showArticle"
                                params="${[hash: article.hash, title: article.title.encodeAsPrettyUrl()]}">${article.title}</g:link>
                    </li>
                </g:each>
            </ol>
        </g:if>
    </div>
</div>

</body>
</html>