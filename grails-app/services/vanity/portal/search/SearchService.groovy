package vanity.portal.search

import groovy.util.logging.Slf4j
import org.codehaus.groovy.grails.commons.GrailsApplication
import org.springframework.transaction.annotation.Transactional
import vanity.article.Article
import vanity.article.ArticleService
import vanity.article.Tag
import vanity.article.TagService
import vanity.celebrity.Celebrity
import vanity.celebrity.CelebrityImage
import vanity.celebrity.CelebrityImageService
import vanity.celebrity.CelebrityService
import vanity.search.SearchEngineQueryExecutor
import vanity.search.SearchResult
import vanity.utils.ConfigUtils

@Slf4j
@Transactional(readOnly = true)
class SearchService {

    ArticleService articleService

    TagService tagService

    CelebrityService celebrityService

    CelebrityImageService celebrityImageService

    SearchEngineQueryExecutor searchEngineQueryExecutor

    GrailsApplication grailsApplication

    public SearchByTagViewModel buildSearchByTagModel(final String tagName, final Integer startElement) {
        Tag tag = tagService.findByNormalizedName(tagName)

        if (!tag || !tag.indexable()) {
            log.info('No tag found for name {}', tagName)
            return null
        }
        // not a root tag, try to find related root
        if (!tag.root) {
            List<Tag> rootTags = tagService.findAllRootParents(tag)

            if (rootTags.size() == 1) {
                throw new NotARootTagException(rootTags.first())
            } else {
                log.info('More than one root tag found {} for tag {}', rootTags, tagName)
                return null
            }
        }
        // trigger search
        Integer maxArticles = ConfigUtils.$as(grailsApplication.config.portal.search.page.articles.max, Integer)
        SearchResult searchResult = searchEngineQueryExecutor.findArticlesByTag(tag.name, startElement, maxArticles)

        if (!searchResult.items) {
            return null
        }

        List<Article> articles = articleService.findAllByIds(searchResult.items*.id)
        Celebrity celebrity = celebrityService.findByTag(tag)
        CelebrityImage celebrityImage = celebrityImageService.getMain(celebrity)

        return new SearchByTagViewModel(
                tag: tag,
                articles: articles,
                celebrity: celebrity,
                celebrityImage: celebrityImage,
                start: searchResult.start,
                numFound: searchResult.numFound
        )
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
}
