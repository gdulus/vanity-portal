<!DOCTYPE html>
<html lang="pl">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="description" content="">
    <title><g:layoutTitle/></title>
    <link rel="stylesheet" href="http://netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css" type="text/css"/>
    <link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Archivo+Narrow:400,700&subset=latin,latin-ext"
          type="text/css"/>
    <link href='https://fonts.googleapis.com/css?family=Roboto:300' rel='stylesheet' type='text/css'>
    <link href='https://fonts.googleapis.com/css?family=Oswald:400,300&subset=latin-ext' rel='stylesheet' type='text/css'>
    <link href='https://fonts.googleapis.com/css?family=Open+Sans&subset=latin,latin-ext' rel='stylesheet' type='text/css'>
    <link href='https://fonts.googleapis.com/css?family=Roboto+Slab:400,300&subset=latin,latin-ext' rel='stylesheet' type='text/css'>
    <g:layoutHead/>

</head>

<body>

<g:render template="/commons/navbar"/>
<g:layoutBody/>

<%--
    JS assets
--%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js" type="text/javascript"></script>
<script src="http://netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
<asset:javascript src="vanity/results.js"/>
<g:pageProperty name="page.javascript"/>

</body>
</html>