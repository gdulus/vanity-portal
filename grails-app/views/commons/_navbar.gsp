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
                        <li>
                            <v:menuItem controller="top" action="newestArticles" code="portal.menu.newestArticles"/>
                        </li>
                        <li>
                            <v:menuItem controller="top" action="mostPopularArticles" code="portal.menu.mostImportantArticles"/>
                        </li>
                        <li>
                            <v:menuItem controller="top" action="mostPopularTags" code="portal.menu.mostPopularCelebrities"/>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>