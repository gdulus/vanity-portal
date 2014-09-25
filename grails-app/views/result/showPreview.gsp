<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title><v:articleTitle article="${viewModel.article}"/></title>
    <meta name="description" content="${v.articleDescription(article: viewModel.article)}"/>
    <meta name="robots" content="noindex"/>
    <meta name="layout" content="list"/>
    <asset:stylesheet href="vanity/showPreview.css"/>
</head>

<body>

<div id="articles" class="row">
    <div class="col-md-10 col-md-offset-1">
        <ul>
            <li class="article">
                <h4>
                    <g:link controller="result"
                            action="showArticle"
                            target="_blank"
                            params="${[id: viewModel.article.id, title: viewModel.article.title.encodeAsPrettyUrl()]}">
                        ${viewModel.article.title}
                    </g:link>

                </h4>

                <div class="date"><g:formatDate format="yyyy-MM-dd" date="${viewModel.article.publicationDate}"/></div>

                <p>
                    ${viewModel.article.shortBody}
                </p>

                <div class="source">
                    <g:message code="portal.searchResult.readRest"/>
                    <a href="${viewModel.article.url}" target="_blank"><g:message code="${viewModel.article.source.target.name()}"/></a>

                    <div class="tags">
                        <g:each in="${viewModel.article.publicTags}" var="articleTag">
                            <g:link controller="search"
                                    action="searchByTag"
                                    params="${[tagName: articleTag.normalizedName]}">
                                <span>
                                    ${articleTag.name}
                                </span>
                            </g:link>
                        </g:each>
                    </div>
                </div>
            </li>
        </ul>
    </div>
</div>

<div class="row">
    <div class="col-md-10 col-md-offset-1">
        <div class="fb-comments" data-width="100%" data-href="${viewModel.currentPage}" data-numposts="5" data-colorscheme="light"></div>
    </div>
</div>

<g:if test="${viewModel.other}">
    <div class="row">
        <div class="col-md-10 col-md-offset-1">
            <div id="other">
                <h5><g:message code="portal.result.preview.other"/></h5>
                <ul>
                    <g:each in="${viewModel.other}" var="otherArticle">
                        <li>
                            <h6>
                                <g:link controller="result"
                                        action="showPreview"
                                        params="${[id: otherArticle.id, title: otherArticle.title.encodeAsPrettyUrl()]}">
                                    ${otherArticle.title}
                                </g:link>

                            </h6>

                            <p>
                                ${otherArticle.getShortBody(200)}
                            </p>
                            <hr/>
                        </li>
                    </g:each>
                </ul>
            </div>
        </div>
    </div>
</g:if>

<content tag="javascript">
    <tracking:article bean="${viewModel.article}"/>
</content>

</body>
</html>