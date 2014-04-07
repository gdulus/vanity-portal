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
    <asset:stylesheet href="vanity/list.css"/>
    <g:layoutHead/>
</head>

<body>

<div id="header">
    <div class="container">
        <div class="row">
            <div class="logo col-md-3 col-md-offset-1">
                <h1><g:link controller="home"><asset:image src="logo-small.jpg" alt="Tani Lans"
                                                           class="img-responsive"/></g:link></h1>
            </div>

            <div id="search" class="col-md-7">
                <form action="${createLink(uri: '/api/search')}" method="get">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="input-group">
                                <input type="search" class="form-control"
                                       placeholder="${g.message(code: 'portal.searchForm.searchPlaceholder')}"/>
                                <span class="input-group-btn">
                                    <input class="btn btn-default" type="submit"
                                           value="${g.message(code: 'portal.searchForm.searchButton')}"/>
                                </span>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<div id="main-menu">
    <div class="container">
        <div class="navbar navbar-default" role="navigation">
            <div class="container">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse"
                            data-target="#main-menu .navbar-collapse">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                </div>

                <div class="navbar-collapse collapse">
                    <ul class="nav navbar-nav">
                        <li><g:link controller="top" action="newestArticles"><g:message
                                code="portal.menu.newestArticles"/></g:link></li>
                        <li><g:link controller="top" action="mostPopularArticles"><g:message
                                code="portal.menu.mostImportantArticles"/></g:link></li>
                        <li><g:link controller="top" action="mostPopularTags"><g:message
                                code="portal.menu.mostPopularCelebrities"/></g:link></li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>

<div id="main" class="container">
    <g:layoutBody/>
</div>

<div id="footer">
    <div class="container">
        <div class="col-md-10 col-md-offset-1">
            <ul>
                <li><a href="#" class="muted"><g:message code="portal.footer.aboutUs"/></a></li>
                <li><a href="#" class="muted"><g:message code="portal.footer.regulations"/></a></li>
                <li><a href="#" class="muted"><g:message code="portal.footer.contact"/></a></li>
            </ul>
        </div>
    </div>
</div>

<%--
    JS assets
--%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js" type="text/javascript"></script>
<asset:javascript src="vanity/results.js"/>
</body>
</html>