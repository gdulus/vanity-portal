package vanity.portal.result

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

}
