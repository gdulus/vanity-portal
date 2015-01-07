<nav id="main-menu">
    <div class="container main-section">
        <div class="row">
            <div class="col-md-10 col-md-offset-1">
                <div class="row">
                    <div class="col-xs-2 col-md-3">
                        <button id="sub-menu-button" type="button" class="dropdown-toggle pull-left">
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                        </button>
                        <g:link controller="home" action="main" class="logo  pull-left"><asset:image src="logo-white.jpg"/></g:link>
                    </div>

                    <div id="search" class="col-xs-8 col-md-9">
                        <form action="${createLink(uri: '/api/search')}" method="get">
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
                        </form>
                    </div>

                </div>
            </div>
        </div>
    </div>
</nav>

<div id="sub-menu" class="hidden">
    <div class="container">
        <div class="row">
            <div class="col-md-10 col-md-offset-1">
                <ul class="row">
                    <li class="col-md-4"><v:menuItem controller="top" action="newestArticles" code="portal.menu.newestArticles"/></li>
                    <li class="col-md-4"><v:menuItem controller="top" action="mostPopularArticles" code="portal.menu.mostImportantArticles"/></li>
                    <li class="col-md-4"><v:menuItem controller="top" action="mostPopularTags" code="portal.menu.mostPopularCelebrities"/></li>
                </ul>
            </div>
        </div>
    </div>
</div>




