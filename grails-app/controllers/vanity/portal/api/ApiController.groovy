package vanity.portal.api

import grails.converters.JSON
import org.codehaus.groovy.grails.web.mapping.LinkGenerator
import vanity.search.SearchEngineQueryExecutor
import vanity.search.SearchResult

class ApiController {

    SearchEngineQueryExecutor searchEngineQueryExecutor

    LinkGenerator grailsLinkGenerator

    def searchByTerm(final String term) {
        def articles = searchEngineQueryExecutor.getArticlesByQuery(term)
        def links = articles.collect { getAsSearchByTermPosition(it) }
        render(links as JSON)
    }

    private def getAsSearchByTermPosition(SearchResult.ArticleSearchResult result) {
        def params = [hash: result.id, title: result.title.encodeAsPrettyUrl()]
        def link = grailsLinkGenerator.link(controller: 'result', action: 'showArticle', params: params, absolute: true)
        return [link: link, label: result.title]
    }

}
