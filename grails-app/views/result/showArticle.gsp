<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title><g:message code="portal.credits" args="[viewModel.article.title]"/></title>
    <meta name="layout" content="result"/>
    <asset:stylesheet href="vanity/result.css"/>
</head>
<body>
<iframe id="result" frameborder="0" src="${viewModel.article.url}"></iframe>
</body>
</html>