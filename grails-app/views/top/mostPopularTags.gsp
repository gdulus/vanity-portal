<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title><g:message code="portal.top.mostPopularTags"/></title>
    <meta name="layout" content="list"/>
    <asset:stylesheet href="vanity/list.css"/>
</head>

<body>

<div id="summary" class="row">
    <div class="col-md-10 col-md-offset-1">
        <h2><g:message code="portal.top.mostPopularTags"/></h2>
    </div>
</div>

<div id="articles" class="row">
    <div class="col-md-10 col-md-offset-1">
        <p class="tag-container">
            <g:each in="${viewModel.tags}" var="hottestTag">
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

</body>
</html>

