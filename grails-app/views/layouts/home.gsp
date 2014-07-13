        <!DOCTYPE html>
<html lang="pl">
<head>
    <title><g:layoutTitle/></title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <link rel="stylesheet" href="http://netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css" type="text/css"/>
    <link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Archivo+Narrow:400,700&subset=latin,latin-ext" type="text/css"/>
    <g:layoutHead/>
</head>

<body>

<div id="header">
    <div class="logo">
        <h1>
        <g:link controller="home" action="main">
            <asset:image src="logo-main.jpg" class="img-responsive"/>
        </g:link>
        </h1>
    </div>
</div>

<div id="main-menu">
    <div class="container">
        <div class="navbar navbar-default" role="navigation">
            <div class="container-fluid">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse"
                            data-target="#main-menu .navbar-collapse">
                        <span class="sr-only"><g:message code="portal.toggleNavigation"/></span>
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

<div id="search" class="container" role="search">
    <form action="${createLink(uri: '/api/search')}" method="get">
        <div class="row">
            <div class="col-md-8 col-md-offset-2">
                <div class="input-group">
                    <input type="search" class="form-control"
                           placeholder="${g.message(code: 'portal.searchForm.searchPlaceholder')}"/>
                    <span class="input-group-btn">
                        <input class="btn btn-default"
                               type="submit"
                               href="${createLink(controller: 'search', action: 'searchByTerm')}"
                               value="${g.message(code: 'portal.searchForm.searchButton')}"/>
                    </span>
                </div>
            </div>
        </div>
    </form>
</div>

<div id="main" class="container" role="main">
    <g:layoutBody/>
</div>

<g:render template="/commons/footer"/>

<%--
    JS assets
--%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js" type="text/javascript"></script>
<script src="http://netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
<g:pageProperty name="page.javascript"/>

</body>
</html>