<html>
<head>
    <title><g:message code="portal.home.title"/></title>
    <meta name="description" content="${message(code: 'portal.home.description')}"/>
    <meta name="layout" content="home"/>
    <asset:stylesheet href="vanity/main.css"/>
</head>

<body>

<g:if test="${viewModel.hottestTags}">
    <div id="celebrities-popular" class="row">
        <div class="col-md-10 col-md-offset-1">
            <h2><g:message code="portal.mainPage.mostPopularSectionTitle"/></h2>

            <p class="tag-container">
                <g:each in="${viewModel.hottestTags}" var="hottestTag">
                    <g:link controller="search"
                            action="searchByTag"
                            params="[tagName: hottestTag.tag.normalizedName]"
                            class="tag${hottestTag.rank}">
                        ${hottestTag.tag.name}
                    </g:link>
                </g:each>
            </p>
        </div>
    </div>
</g:if>

<g:if test="${viewModel.promotedTags}">
    <div id="celebrities-promoted" class="row">
        <div class="navbar navbar-default col-md-10 col-md-offset-1">
            <div>
                <div class="container-fluid">
                    <div class="navbar-header">
                        <button type="button" class="navbar-toggle" data-toggle="collapse"
                                data-target="#celebrities-promoted .navbar-collapse">
                            <span class="sr-only">Toggle navigation</span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                        </button>

                        <h2 class="navbar-brand"><g:message code="portal.mainPage.ourIconsSectionTitle"/></h2>
                    </div>

                    <div class="navbar-collapse collapse">
                        <ul class="nav navbar-nav">
                            <g:each in="${viewModel.promotedTags}" var="tag">
                                <li>
                                <g:link controller="search"
                                        action="searchByTag"
                                        params="[tagName: tag.normalizedName]"
                                        title="${tag.name}">
                                    ${tag.name}
                                </g:link>
                            </g:each>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
</g:if>


<div id="articles" class="row">
    <div class="col-md-10 col-md-offset-1">
        <div class="row">
            <div class="col-md-6">
                <div class="column">
                    <h2><g:message code="portal.mainPage.newestArticlesSectionTitle"/></h2>
                    <g:if test="${viewModel.newestArticles}">
                        <ol class="positions">
                            <g:each in="${viewModel.newestArticles}" var="article">
                                <li>
                                    <g:link controller="result"
                                            action="showPreview"
                                            params="${[id: article.id, title: article.title.encodeAsPrettyUrl()]}">
                                        ${article.title}
                                    </g:link>
                                </li>
                            </g:each>
                        </ol>
                    </g:if>
                </div>
            </div>

            <div class="col-md-6">
                <div class="column">
                    <h2><g:message code="portal.mainPage.mostImportantArticlesSectionTitle"/></h2>
                    <g:if test="${viewModel.hottestArticles}">
                        <ol class="positions">
                            <g:each in="${viewModel.hottestArticles}" var="article">
                                <li>
                                    <g:link controller="result"
                                            action="showPreview"
                                            params="${[id: article.id, title: article.title.encodeAsPrettyUrl()]}">
                                        ${article.title}
                                    </g:link>
                                </li>
                            </g:each>
                        </ol>
                    </g:if>
                </div>
            </div>
        </div>
    </div>
</div>

<content tag="javascript">
    <asset:javascript src="vanity/home.js"/>
</content>

</body>
</html>