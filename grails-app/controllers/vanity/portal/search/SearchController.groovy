package vanity.portal.search

import grails.converters.JSON
import grails.web.RequestParameter
import org.codehaus.groovy.grails.web.mapping.LinkGenerator
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import vanity.search.SearchEngineQueryExecutor
import vanity.search.SearchResult

class SearchController {

    SearchService searchService

    SearchEngineQueryExecutor searchEngineQueryExecutor

    LinkGenerator grailsLinkGenerator

    @Value('${portal.search.box.articles.max}')
    Integer maxArticles

    @Value('${portal.search.box.tags.max}')
    Integer maxTags

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

    def api(final String term) {
        SearchResult articles = searchEngineQueryExecutor.findArticles(term, 0, maxArticles)
        List<Map<String, String>> articleLinks = articles.items.collect { getAsArticleLink(it) }

        SearchResult tags = searchEngineQueryExecutor.findTags(term, 0, maxTags)
        List<Map<String, String>> tagLinks = tags.items.collect { getAsTagLink(it) }
        render([tags: tagLinks, articles: articleLinks] as JSON)
    }

    private Map<String, String> getAsArticleLink(final SearchResult.SearchResultItem resultItem) {
        Map<String, ?> params = [id: resultItem.id, title: resultItem.description.encodeAsPrettyUrl()]
        String link = grailsLinkGenerator.link(controller: 'result', action: 'showPreview', params: params)
        return [link: link, label: resultItem.description]
    }

    private Map<String, String> getAsTagLink(final SearchResult.SearchResultItem resultItem) {
        Map<String, ?> params = [tagName: resultItem.description.encodeAsPrettyUrl()]
        String link = grailsLinkGenerator.link(controller: 'search', action: 'searchByTag', params: params)
        return [link: link, label: resultItem.description]
    }

}
