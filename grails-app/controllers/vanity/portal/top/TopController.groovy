package vanity.portal.top

import grails.util.Mixin
import vanity.portal.commons.ControllerMixin

@Mixin(ControllerMixin)
class TopController {

    def topService

    def newestArticles(final Integer offset) {
        def model = topService.buildNewestArticlesModel(offset ?: 0)
        return getModelOrNotFound(model)
    }

    def mostPopularArticles(final Integer offset) {
        def model = topService.buildMostPopularArticlesModel(offset ?: 0)
        return getModelOrNotFound(model)
    }

    def mostPopularTags() {
        def model = topService.buildMostPopularTagsModel()
        return getModelOrNotFound(model)
    }
}
