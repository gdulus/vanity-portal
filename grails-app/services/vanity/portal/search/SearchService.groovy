package vanity.portal.search

import org.codehaus.groovy.grails.commons.GrailsApplication
import org.codehaus.groovy.grails.web.mapping.LinkGenerator
import org.springframework.beans.factory.annotation.Value
import org.springframework.transaction.annotation.Transactional
import vanity.article.Article
import vanity.article.ArticleService
import vanity.article.Tag
import vanity.article.TagService
import vanity.celebrity.Celebrity
import vanity.celebrity.CelebrityService
import vanity.search.SearchEngineQueryExecutor
import vanity.search.SearchResult
import vanity.utils.ConfigUtils

@Transactional(readOnly = true)
class SearchService {

    static transactional = false

    ArticleService articleService

    TagService tagService

    CelebrityService celebrityService

    SearchEngineQueryExecutor searchEngineQueryExecutor

    GrailsApplication grailsApplication

    LinkGenerator grailsLinkGenerator

    @Value('${portal.search.box.articles.max}')
    Integer boxMaxArticles

    @Value('${portal.search.box.tags.max}')
    Integer boxMaxTags

    public SearchByTagViewModel buildSearchByTagModel(final String tagName, final Integer startElement) {
        Tag tag = tagService.findByTagName(tagName)

        if (!tag) {
            return null
        }

        Integer maxArticles = ConfigUtils.$as(grailsApplication.config.portal.search.page.articles.max, Integer)
        SearchResult searchResult = searchEngineQueryExecutor.findArticlesByTag(tag.name, startElement, maxArticles)

        if (!searchResult.items) {
            return null
        }

        List<Article> articles = articleService.findAllByIds(searchResult.items*.id)
        Celebrity celebrity = celebrityService.findByTag(tag)
        return new SearchByTagViewModel(tag: tag, articles: articles, celebrity: celebrity, start: searchResult.start, numFound: searchResult.numFound)
    }

    public SearchByTermViewModel buildSearchByTermModel(final String term, final Integer startElement) {
        if (!term) {
            return null
        }

        Integer maxArticles = ConfigUtils.$as(grailsApplication.config.portal.search.page.articles.max, Integer)
        SearchResult searchResult = searchEngineQueryExecutor.findArticles(term, startElement, maxArticles)

        if (!searchResult.items) {
            return null
        }

        List<Article> articles = articleService.findAllByIds(searchResult.items*.id)
        return new SearchByTermViewModel(term: term, articles: articles, start: searchResult.start, numFound: searchResult.numFound)
    }

    public Map<String, ?> buildApiModel(final String term) {
        SearchResult articles = searchEngineQueryExecutor.findArticles(term, 0, boxMaxArticles)
        List<Map<String, String>> articleLinks = articles.items.collect { getAsArticleLink(it) }

        SearchResult tags = searchEngineQueryExecutor.findTags(term, 0, boxMaxTags)
        List<Map<String, String>> tagLinks = tags.items.collect { getAsTagLink(it) }
        [tags: tagLinks, articles: articleLinks]
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
