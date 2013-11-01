package vanity.portal.search

import org.codehaus.groovy.grails.commons.GrailsApplication
import org.springframework.transaction.annotation.Transactional
import vanity.article.Article
import vanity.article.ArticleService
import vanity.article.Tag
import vanity.article.TagService
import vanity.celebrity.Celebrity
import vanity.celebrity.CelebrityService
import vanity.search.SearchEngineQueryExecutor
import vanity.search.SearchResult
import vanity.tracking.ClickService
import vanity.utils.ConfigUtils

class SearchService {

    ClickService clickService

    ArticleService articleService

    TagService tagService

    CelebrityService celebrityService

    SearchEngineQueryExecutor searchEngineQueryExecutor

    GrailsApplication grailsApplication

    @Transactional(readOnly = true)
    public SearchByTagViewModel buildSearchByTagModel(final String hash, final Integer startElement) {
        Tag tag = tagService.readByHash(hash)

        if (!tag) {
            return null
        }

        Integer maxArticles = ConfigUtils.$as(grailsApplication.config.portal.search.page.articles.max, Integer)
        SearchResult searchResult = searchEngineQueryExecutor.findArticles(tag.name, startElement, maxArticles)

        if (!searchResult.items) {
            return null
        }

        clickService.create(tag)
        List<Article> articles = articleService.findByHashCodes(searchResult.items*.id)
        Celebrity celebrity = celebrityService.findByTag(tag)
        return new SearchByTagViewModel(tag: tag, articles: articles, celebrity: celebrity, start: searchResult.start, numFound: searchResult.numFound)
    }

    @Transactional(readOnly = true)
    public SearchByTermViewModel buildSearchByTermModel(final String term, final Integer startElement) {
        if (!term) {
            return null
        }

        Integer maxArticles = ConfigUtils.$as(grailsApplication.config.portal.search.page.articles.max, Integer)
        SearchResult searchResult = searchEngineQueryExecutor.findArticles(term, startElement, maxArticles)

        if (!searchResult.items) {
            return null
        }

        List<Article> articles = articleService.findByHashCodes(searchResult.items*.id)
        return new SearchByTermViewModel(term: term, articles: articles, start: searchResult.start, numFound: searchResult.numFound)
    }
}
