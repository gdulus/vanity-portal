package vanity.portal

import grails.converters.JSON
import org.codehaus.groovy.grails.web.mapping.LinkGenerator
import org.springframework.beans.factory.annotation.Value
import vanity.search.SearchEngineQueryExecutor
import vanity.search.SearchResult

import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.QueryParam

@Path('/api/search')
class SearchResource {

    SearchEngineQueryExecutor searchEngineQueryExecutor

    LinkGenerator grailsLinkGenerator

    @Value('${portal.search.box.articles.max}')
    Integer maxArticles

    @Value('${portal.search.box.tags.max}')
    Integer maxTags

    @GET
    @Produces('application/json')
    JSON result(@QueryParam('term') final String term) {
        SearchResult articles = searchEngineQueryExecutor.findArticles(term, 0, maxArticles)
        List<Map<String, String>> articleLinks = articles.items.collect { getAsArticleLink(it) }

        SearchResult tags = searchEngineQueryExecutor.findTags(term, 0, maxTags)
        List<Map<String, String>> tagLinks = tags.items.collect { getAsTagLink(it) }
        return [tags: tagLinks, articles: articleLinks] as JSON
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
