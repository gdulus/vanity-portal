<nav id="main-menu">
    <div class="container main-section">
        <div class="row">
            <div class="col-md-12 navabar">
                <button id="sub-menu-button" type="button" class="dropdown-toggle pull-left menu mobile">
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>

                <g:link controller="home" action="main" class="logo pull-left">
                    <asset:image src="logo-header-full.png"/>
                </g:link>

                <button id="search-button" type="button" class="pull-right search icon">
                    <span class="glyphicon glyphicon-search"></span>
                </button>

                <v:withFeature name="social">
                    <button id="user-button" type="button" class="pull-right user icon">
                        <span class="glyphicon glyphicon-user"></span>
                    </button>
                </v:withFeature>

                <button type="button" class="pull-left menu-item desktop">
                    <h2>celebryci <span class="glyphicon glyphicon-menu-down"></span></h2>
                    <ul class="navigation pull-right">
                        <li><v:menuItem controller="top" action="mostPopularTags" code="portal.menu.mostPopularCelebrities"/></li>
                    </ul>
                </button>

                <button type="button" class="pull-left menu-item desktop">
                    <h2>plotki  <span class="glyphicon glyphicon-menu-down"></span></h2>
                    <ul class="navigation pull-right">
                        <li><v:menuItem controller="top" action="mostPopularArticles" code="portal.menu.mostImportantArticles"/></li>
                        <li><v:menuItem controller="top" action="newestArticles" code="portal.menu.newestArticles"/></li>
                    </ul>
                </button>

            </div>
        </div>
    </div>
</nav>

<div id="search-menu" class="hidden">
    <div class="container">
        <div class="row">
            <div class="col-md-10 col-md-offset-1 search">
                <form action="${createLink(uri: '/api/search')}" method="get">
                    <div class="input-group">
                        <input type="search" class="form-control"
                               placeholder="${g.message(code: 'portal.searchForm.searchPlaceholder')}"/>
                        <span class="input-group-btn">
                            <input class="btn btn-default "
                                   type="submit"
                                   href="${createLink(controller: 'search', action: 'searchByTerm')}"
                                   value="${g.message(code: 'portal.searchForm.searchButton')}"/>
                        </span>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<div id="sub-menu" class="mobile navigation hidden">
    <ul>
        <li><h2>celebryci <span class="glyphicon glyphicon-menu-down"></span></h2></li>
        <li><v:menuItem controller="top" action="mostPopularTags" code="portal.menu.mostPopularCelebrities"/></li>
        <li><h2>plotki <span class="glyphicon glyphicon-menu-down"></span></h2></li>
        <li><v:menuItem controller="top" action="mostPopularArticles" code="portal.menu.mostImportantArticles"/></li>
        <li><v:menuItem controller="top" action="newestArticles" code="portal.menu.newestArticles"/></li>
        <li><h2>centrala <span class="glyphicon glyphicon-menu-down"></span></h2></li>
        <li><g:link controller="info" action="contact" class="muted"><g:message code="portal.footer.contact"/></g:link></li>
    </ul>
</div>



