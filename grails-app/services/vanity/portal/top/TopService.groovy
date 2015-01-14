package vanity.portal.top

import org.codehaus.groovy.grails.commons.GrailsApplication
import org.springframework.beans.factory.annotation.Value
import org.springframework.transaction.annotation.Transactional
import vanity.article.Article
import vanity.article.Tag
import vanity.portal.command.NewestArticlesCommand
import vanity.portal.domain.ArticlePage
import vanity.portal.ms.ServiceEndpointsRepository
import vanity.stats.PopularityDTO
import vanity.stats.PopularityService
import vanity.utils.ConfigUtils

@Transactional(readOnly = true)
class TopService {

    ServiceEndpointsRepository serviceEndpointsRepository

    @Value('${portal.top.articles.max}')
    Integer topArticlesMax

    PopularityService popularityService

    GrailsApplication grailsApplication

    public TopArticlesViewModel buildNewestArticlesModel(final Integer offset) {
        ArticlePage newestArticles = new NewestArticlesCommand(serviceEndpointsRepository, offset, topArticlesMax).execute()

        if (newestArticles.isEmpty()) {
            return null
        }

        return new TopArticlesViewModel(articles: newestArticles.content, total: newestArticles.totalElements)
    }

    public TopArticlesViewModel buildMostPopularArticlesModel(final Integer offset) {
        Date from = new Date() - ConfigUtils.$as(grailsApplication.config.portal.top.articles.days, Integer)
        Integer max = ConfigUtils.$as(grailsApplication.config.portal.top.articles.max, Integer)
        List<Article> articles = popularityService.getTopArticlesFromDate(from, max, offset)

        if (!articles) {
            return null
        }

        return new TopArticlesViewModel(articles: articles, total: popularityService.countTopArticlesFromDate(from))
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
