package vanity.portal.result

import org.springframework.http.HttpStatus

class ResultController {

    def resultService

    def showArticle(final String hash) {
        def model = resultService.buildShowArticleModel(hash)

        if (!model) {
            return response.setStatus(HttpStatus.NOT_FOUND.value())
        }

        return [viewModel: model]
    }

    def showTag(final String hash, final Integer startElement) {
        def model = resultService.buildShowTagModel(hash, startElement)

        if (!model) {
            return response.setStatus(HttpStatus.NOT_FOUND.value())
        }

        return [viewModel: model]
    }
}
