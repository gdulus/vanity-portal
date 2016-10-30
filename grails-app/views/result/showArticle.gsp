<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title><v:articleTitle article="${viewModel.article}"/></title>
    <meta name="description" content="${v.articleDescription(article: viewModel.article)}"/>
    <meta name="robots" content="noindex"/>
    <meta name="layout" content="result"/>
    <asset:stylesheet href="vanity/showArticle.css"/>
</head>

<body>
<iframe id="result" frameborder="0" src="${viewModel.article.url}"></iframe>
<content tag="javascript">
    <tracking:article bean="${viewModel.article}"/>
</content>
</body>
</html>