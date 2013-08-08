package vanity.portal

import grails.converters.JSON
import org.codehaus.groovy.grails.web.mapping.LinkGenerator
import vanity.search.ArticleSearchResult
import vanity.search.SearchEngineQueryExecutor

class ApiController {

    SearchEngineQueryExecutor searchEngineQueryExecutor

    LinkGenerator grailsLinkGenerator

    def searchByTerm(final String term) {
        def articles = searchEngineQueryExecutor.getArticles(term)
        def links = articles.collect {getAsSearchByTermPosition(it)}
        render(links as JSON)
    }

    private def getAsSearchByTermPosition(ArticleSearchResult result){
        def params = [id:result.id, title: result.title.encodeAsPrettyUrl(), contentSourceTarget: result.contentSourceTarget]
        def link = grailsLinkGenerator.link(controller: 'result', action: 'showArticle', params: params)
        return [value:link,  label: result.title]
    }

}
