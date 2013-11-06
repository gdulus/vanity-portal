package vanity.portal.top

import org.springframework.http.HttpStatus
import vanity.portal.paginate.PaginateCmd

class TopController {

    def topService

    def newestArticles(final Integer offset) {
        def model = topService.buildNewestArticlesModel(offset ?: 0)

        if (!model) {
            response.sendError(HttpStatus.NOT_FOUND.value())
            return
        }

        return [viewModel: model]
    }

    def mostPopularArticles(final Integer offset) {
        def model = topService.buildMostPopularArticlesModel(offset ?: 0)

        if (!model) {
            response.sendError(HttpStatus.NOT_FOUND.value())
            return
        }

        return [viewModel: model]
    }

    def mostPopularTags() {
        def model = topService.buildMostPopularTagsModel()

        if (!model) {
            response.sendError(HttpStatus.NOT_FOUND.value())
            return
        }

        return [viewModel: model]
    }
}
