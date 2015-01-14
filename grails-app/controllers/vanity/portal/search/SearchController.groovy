package vanity.portal.search

import grails.web.RequestParameter
import vanity.portal.commons.ControllerMixin

@Mixin(ControllerMixin)
class SearchController {

    def searchService

    def searchByTag(final String tagName, final Integer offset) {
        try {
            def model = searchService.buildSearchByTagModel(tagName, offset)
            return getModelOrNotFound(model)
        } catch (NotARootTagException exp) {
            redirect(action: 'searchByTag', params: [tagName: exp.relatedRoot.normalizedName])
        }
    }

    def searchByTerm(@RequestParameter('q') final String term, final Integer offset) {
        def model = searchService.buildSearchByTermModel(term, offset)
        return getModelOrNotFound(model)
    }

}
