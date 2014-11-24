package vanity.portal.search

import grails.converters.JSON
import grails.web.RequestParameter
import org.codehaus.groovy.grails.commons.GrailsApplication
import org.codehaus.groovy.grails.web.mapping.LinkGenerator
import org.springframework.http.HttpStatus
import vanity.search.SearchEngineQueryExecutor

class SearchController {

    SearchService searchService

    def searchByTag(final String tagName, final Integer offset) {
        def model = searchService.buildSearchByTagModel(tagName, offset)

        if (!model) {
            response.sendError(HttpStatus.NOT_FOUND.value())
            return
        }

        return [viewModel: model]
    }

    def searchByTerm(@RequestParameter('q') final String term, final Integer offset) {
        def model = searchService.buildSearchByTermModel(term, offset)

        if (!model) {
            response.sendError(HttpStatus.NOT_FOUND.value())
            return
        }

        return [viewModel: model]
    }

    def api(final String term) {
        render(searchService.buildApiModel(term) as JSON)
    }

}
