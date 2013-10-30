package vanity.portal

class SearchWidgetTagLib {

    static namespace = 'v'

    @Lazy
    String apiURL = g.createLink(controller: 'api', action: 'searchByTerm')

    @Lazy
    String showURL = g.createLink(controller: 'result', action: 'showByTerm')

    @Lazy
    String searchPlaceholder = g.message(code: 'portal.searchForm.searchPlaceholder')

    @Lazy
    String searchButton = g.message(code: 'portal.searchForm.searchPlaceholder')

    def searchWidget = { attrs ->
        out << """
            <form role="search" id="search" target="${apiURL}">
                <input autocomplete="off" type="search" class="input-xxlarge" placeholder="${searchPlaceholder}"/>
                    <a href="${showURL}" class="btn btn-primary btn-large">${searchButton}</a>
                </g:link>
            </form>
        """
    }

}
