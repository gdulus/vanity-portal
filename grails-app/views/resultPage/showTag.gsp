<html>
<head>
    <title></title>
    <meta name="layout" content="portal"/>
    <r:require module="mainPage"/>
</head>
<body>
    <g:each in="${articles}" var="article">
        ${article.title}<br />

    </g:each>

</body>
</html>