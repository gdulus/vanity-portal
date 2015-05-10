package vanity.portal.top

import vanity.portal.AbstractController

class TopController extends AbstractController {

    def topService

    def newestArticles(final Integer offset) {
        def model = topService.buildNewestArticlesModel(offset ?: 0)
        return getModelOrNotFound(model, response)
    }

    def mostPopularArticles(final Integer offset) {
        def model = topService.buildMostPopularArticlesModel(offset ?: 0)
        return getModelOrNotFound(model, response)
    }

    def mostPopularTags() {
        def model = topService.buildMostPopularTagsModel()
        return getModelOrNotFound(model, response)
    }
}
