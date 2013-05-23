package vanity.portal

import grails.converters.JSON
import org.codehaus.groovy.grails.web.mapping.LinkGenerator
import vanity.search.ArticleSearchResult
import vanity.search.SearchEngineQueryExecutor

class SearchController {

    SearchEngineQueryExecutor searchEngineQueryExecutor

    LinkGenerator grailsLinkGenerator

    def ajaxSearchResults(String term) {
        def articles = searchEngineQueryExecutor.getArticles(term)
        def links = articles.collect {getAsArticleLink(it)}
        render(links as JSON)
    }

    private def getAsArticleLink(ArticleSearchResult result){
        return [
            value: grailsLinkGenerator.link(controller: 'resultPage',
                                            action: 'showArticle',
                                            params: getAsArticleLinkParams(result)),
            label: result.title
        ]
    }

    private def getAsArticleLinkParams(ArticleSearchResult result){
        return [
            id:result.id,
            title: result.title.encodeAsPrettyUrl(),
            contentSourceTarget: result.contentSourceTarget
        ]
    }
}
