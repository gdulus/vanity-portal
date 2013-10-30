package vanity.portal.result

import grails.web.RequestParameter
import org.springframework.http.HttpStatus

class ResultController {

    def resultService

    def showArticle(final String hash) {
        def model = resultService.buildShowArticleModel(hash)

        if (!model) {
            response.sendError(HttpStatus.NOT_FOUND.value())
            return
        }

        return [viewModel: model]
    }

    def showByTag(final String hash, final Integer startElement) {
        def model = resultService.buildShowByTagModel(hash, startElement)

        if (!model) {
            response.sendError(HttpStatus.NOT_FOUND.value())
            return
        }

        return [viewModel: model]
    }

    def showByTerm(@RequestParameter('q') final String term, final Integer startElement) {
        def model = resultService.buildShowByTermModel(term, startElement)

        if (!model) {
            response.sendError(HttpStatus.NOT_FOUND.value())
            return
        }

        return [viewModel: model]
    }

}
