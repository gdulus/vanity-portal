package vanity.portal.result

import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus

class ResultController {

    def resultService

    @Value('${grails.serverURL}')
    def serverURL

    def showPreview(final Long id) {
        def model = resultService.buildShowPreview(id, serverURL + request.forwardURI)

        if (!model) {
            response.sendError(HttpStatus.NOT_FOUND.value())
            return
        }

        return [viewModel: model]
    }

    def showArticle(final Long id) {
        def model = resultService.buildShowArticleModel(id)

        if (!model) {
            response.sendError(HttpStatus.NOT_FOUND.value())
            return
        }

        return [viewModel: model]
    }

}
