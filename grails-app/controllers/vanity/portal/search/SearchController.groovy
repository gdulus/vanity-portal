package vanity.portal.search

import grails.web.RequestParameter
import org.springframework.http.HttpStatus

class SearchController {

    def searchService

    def searchByTag(final String hash, final Integer startElement) {
        def model = searchService.buildSearchByTagModel(hash, startElement)

        if (!model) {
            response.sendError(HttpStatus.NOT_FOUND.value())
            return
        }

        return [viewModel: model]
    }

    def searchByTerm(@RequestParameter('q') final String term, final Integer startElement) {
        def model = searchService.buildSearchByTermModel(term, startElement)

        if (!model) {
            response.sendError(HttpStatus.NOT_FOUND.value())
            return
        }

        return [viewModel: model]
    }

}
