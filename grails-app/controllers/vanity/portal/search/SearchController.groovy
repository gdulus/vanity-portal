package vanity.portal.search

import grails.web.RequestParameter
import org.springframework.http.HttpStatus

class SearchController {

    def searchService

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

}
