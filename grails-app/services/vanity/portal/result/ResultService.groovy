package vanity.portal.result

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

class ResultService {

    ClickService clickService

    ArticleService articleService

    TagService tagService

    CelebrityService celebrityService

    SearchEngineQueryExecutor searchEngineQueryExecutor

    GrailsApplication grailsApplication

    @Transactional(readOnly = true)
    public ShowArticleViewModel buildShowArticleModel(final String hash) {
        Article article = articleService.findByHashCode(hash)

        if (!article) {
            return null
        }

        clickService.create(article)
        return new ShowArticleViewModel(article: article)
    }

    @Transactional(readOnly = true)
    public ShowByTagViewModel buildShowByTagModel(final String hash, final Integer startElement) {
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
        return new ShowByTagViewModel(tag: tag, articles: articles, celebrity: celebrity, start: searchResult.start, numFound: searchResult.numFound)
    }

    @Transactional(readOnly = true)
    public ShowByTagViewModel buildShowByTermModel(final String phrase, final Integer startElement) {

    }
}
