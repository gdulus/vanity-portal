package vanity.portal.result

import org.springframework.http.HttpStatus

class ResultController {

    def resultService

    def showArticle(final Long id) {
        def model = resultService.buildShowArticleModel(id)

        if (!model) {
            response.sendError(HttpStatus.NOT_FOUND.value())
            return
        }

        return [viewModel: model]
    }

}
