<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title></title>
    <meta name="layout" content="result"/>
    <r:require module="mostPopularTags"/>
</head>

<body>
<h2><g:message code="portal.top.mostPopularTags"/></h2>

<p class="tag-cloud">
    <g:each in="${viewModel.tags}" var="hottestTag">
        <a href="${createLink(controller: 'search', action: 'searchByTag', params: [tagName: hottestTag.tag.normalizedName])}"
           class="tag scale${hottestTag.rank}" title="${hottestTag.tag.name}">${hottestTag.tag.name}</a>
    </g:each>
</p>

</body>
</html>