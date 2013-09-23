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
        def tags = searchEngineQueryExecutor.getTagsByQuery(term)
        def articleLinks = articles.collect { getAsArticleLink(it) }
        def tagLinks = tags.collect { getAsTagLink(it) }
        render([tags: tagLinks, articles: articleLinks] as JSON)
    }

    private def getAsArticleLink(SearchResult.ArticleSearchResult result) {
        def params = [hash: result.id, title: result.title.encodeAsPrettyUrl()]
        def link = grailsLinkGenerator.link(controller: 'result', action: 'showArticle', params: params, absolute: true)
        return [link: link, label: result.title]
    }

    private def getAsTagLink(SearchResult.TagSearchResult result) {
        def params = [hash: result.id, tagName: result.name.encodeAsPrettyUrl()]
        def link = grailsLinkGenerator.link(controller: 'result', action: 'showTag', params: params, absolute: true)
        return [link: link, label: result.name]
    }

}
