<!DOCTYPE html>
<html lang="pl">
    <head>
        <meta charset="utf-8">
        <title><g:message code="portal.title" /></title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="">
        <title><g:layoutTitle default="Vanity cms"/></title>
        <g:layoutHead/>
        <r:layoutResources />
    </head>
    <body>
        <div id="wrap">
            <header>
                <h1><a href="${createLink(controller: 'home')}">TaniLans</a></h1>
                <form role="search" target="${createLink(controller: 'search', action: 'ajaxSearchResults')}">
                    <input autocomplete="off" id="search-field" type="search" class="input-xxlarge" placeholder="${g.message(code:'portal.searchForm.searchPlaceholder')}"/>
                    <button type="submit" class="btn btn-primary"><g:message code="portal.searchForm.searchButton" /></button>
                </form>

                <div class="clearfix"></div>
                <nav>
                    <ul>
                        <li><a href="#"><g:message code="portal.menu.newestArticles" /></a></li>
                        <li><a href="#"><g:message code="portal.menu.mostPopularCelebrities" /></a></li>
                        <li><a href="#"><g:message code="portal.menu.mostImportantArticles" /></a></li>
                    </ul>
                </nav>

                <div class="clearfix"></div>
            </header>
            <div role="main">
                <g:layoutBody/>
            </div>
            <div id="push"></div>
        </div>

        <footer id="footer">
            <div class="container">
                <div class="row credit">
                    <div class="span2 offset2">
                        <h6 class="vanity"><g:message code="portal.credits" args="[new Date().format('yyyy')]" /></h6>
                    </div>
                    <div class="span6 menu text-right">
                        <a href="#" class="muted"><g:message code="portal.footer.aboutUs" /></a>
                        <a href="#" class="muted"><g:message code="portal.footer.regulations" /></a>
                        <a href="#" class="muted"><g:message code="portal.footer.contact" /></a>
                    </div>
                </div>
            </div>
        </footer>
        <r:layoutResources />
    </body>
</html>
