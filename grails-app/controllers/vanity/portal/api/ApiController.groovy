package vanity.portal.api

import grails.converters.JSON
import org.codehaus.groovy.grails.commons.GrailsApplication
import org.codehaus.groovy.grails.web.mapping.LinkGenerator
import vanity.search.SearchEngineQueryExecutor
import vanity.search.SearchResult
import vanity.search.SearchResult.SearchResultItem
import vanity.utils.ConfigUtils

class ApiController {

    SearchEngineQueryExecutor searchEngineQueryExecutor

    LinkGenerator grailsLinkGenerator

    GrailsApplication grailsApplication

    def searchByTerm(final String term) {
        Integer maxArticles = ConfigUtils.$as(grailsApplication.config.portal.search.box.articles.max, Integer)
        SearchResult articles = searchEngineQueryExecutor.findArticles(term, 0, maxArticles)
        def articleLinks = articles.items.collect { getAsArticleLink(it) }

        Integer maxTags = ConfigUtils.$as(grailsApplication.config.portal.search.box.tags.max, Integer)
        SearchResult tags = searchEngineQueryExecutor.findTags(term, 0, maxTags)
        def tagLinks = tags.items.collect { getAsTagLink(it) }

        render([tags: tagLinks, articles: articleLinks] as JSON)
    }

    private Map<String, String> getAsArticleLink(final SearchResultItem resultItem) {
        Map<String, ?> params = [hash: resultItem.id, title: resultItem.description.encodeAsPrettyUrl()]
        String link = grailsLinkGenerator.link(controller: 'search', action: 'showArticle', params: params, absolute: true)
        return [link: link, label: resultItem.description]
    }

    private Map<String, String> getAsTagLink(final SearchResultItem resultItem) {
        Map<String, ?> params = [hash: resultItem.id, tagName: resultItem.description.encodeAsPrettyUrl(), startElement: 0]
        String link = grailsLinkGenerator.link(controller: 'search', action: 'searchByTag', params: params, absolute: true)
        return [link: link, label: resultItem.description]
    }

}
