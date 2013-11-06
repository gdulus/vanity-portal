package vanity.portal.top

import org.codehaus.groovy.grails.commons.GrailsApplication
import org.springframework.transaction.annotation.Transactional
import vanity.article.Article
import vanity.article.ArticleService
import vanity.article.Tag
import vanity.stats.PopularityDTO
import vanity.stats.PopularityService
import vanity.utils.ConfigUtils

@Transactional(readOnly = true)
class TopService {

    PopularityService popularityService

    TopArticlesService topArticlesService

    ArticleService articleService

    GrailsApplication grailsApplication

    public TopArticlesViewModel buildNewestArticlesModel(final Integer offset) {
        Integer max = ConfigUtils.$as(grailsApplication.config.portal.top.articles.max, Integer)
        List<Article> articles = topArticlesService.getNewestArticles(max, offset)

        if (!articles) {
            return null
        }

        return new TopArticlesViewModel(articles: articles, total: articleService.count())
    }

    public TopArticlesViewModel buildMostPopularArticlesModel(final Integer offset) {
        Integer max = ConfigUtils.$as(grailsApplication.config.portal.top.articles.max, Integer)
        List<Article> articles = popularityService.getTopArticles(max, offset)

        if (!articles) {
            return null
        }

        return new TopArticlesViewModel(articles: articles, total: popularityService.countTopArticles())
    }

    public TopTagsViewModel buildMostPopularTagsModel() {
        Integer max = ConfigUtils.$as(grailsApplication.config.portal.top.tags.max, Integer)
        List<PopularityDTO> popularTags = popularityService.findTopTags(max)

        if (!popularTags) {
            return null
        }

        Integer maxRank = popularTags*.rank.max()
        List<PopularTagDTO> popularTagDTOs = popularTags.collect { new PopularTagDTO(Tag.read(it.elementId), it.rank, maxRank) } sort { it.tag.name }
        return new TopTagsViewModel(tags: popularTagDTOs)
    }

}
