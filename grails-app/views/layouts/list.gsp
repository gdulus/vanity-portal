<!DOCTYPE html>
<html lang="pl">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="description" content="">
    <title><g:layoutTitle/></title>
    <asset:stylesheet href="libs/slick/slick.css"/>
    <asset:stylesheet href="libs/slick/slick-theme.css"/>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css" type="text/css"/>
    <link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Archivo+Narrow:400,700&subset=latin,latin-ext" type="text/css"/>
    <link href='https://fonts.googleapis.com/css?family=Roboto:300,400,700' rel='stylesheet' type='text/css'>
    <link href='https://fonts.googleapis.com/css?family=Oswald:400,300&subset=latin-ext' rel='stylesheet' type='text/css'>
    <link href='https://fonts.googleapis.com/css?family=Open+Sans&subset=latin,latin-ext' rel='stylesheet' type='text/css'>
    <link href='https://fonts.googleapis.com/css?family=Roboto+Slab:400,300&subset=latin,latin-ext' rel='stylesheet' type='text/css'>
    <g:layoutHead/>
</head>

<body>
<div id="social"></div>
<g:render template="/commons/navbar"/>
<div id="main" class="container"><g:layoutBody/></div>
<g:render template="/commons/footer"/>

%{-- JS assets --}%
<asset:javascript src="libs/jquery-1.12.0.js"/>
<asset:javascript src="libs/slick.min.js"/>
<script src="//cdn.rawgit.com/noelboss/featherlight/1.3.5/release/featherlight.min.js" type="text/javascript" charset="utf-8"></script>
<script src="http://netdna.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js" type="text/javascript" charset="utf-8"></script>
<asset:javascript src="vanity/results.js"/>
<v:withFeature name="social">
    <asset:javascript src="social/compiled/app.js"/>
    <script>social.core.init();</script>
</v:withFeature>
<g:pageProperty name="page.javascript"/>
</body>
</html>