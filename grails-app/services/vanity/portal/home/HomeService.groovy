package vanity.portal.home

import org.codehaus.groovy.grails.commons.GrailsApplication
import org.springframework.transaction.annotation.Transactional
import vanity.portal.top.TopArticlesService
import vanity.portal.top.TopTagsService
import vanity.utils.ConfigUtils

@Transactional(readOnly = true)
class HomeService {

    TopArticlesService topArticlesService

    TopTagsService topTagsService

    GrailsApplication grailsApplication

    public MainViewModel buildMainModel() {
        Integer maxNewestArticles = ConfigUtils.$as(grailsApplication.config.portal.mainPage.newestArticles.max, Integer)
        Integer maxHottestArticles = ConfigUtils.$as(grailsApplication.config.portal.mainPage.hottestArticles.max, Integer)
        Date hottestArticlesFromDate = (new Date() - ConfigUtils.$as(grailsApplication.config.portal.mainPage.hottestArticles.dateWindow, Integer))

        return new MainViewModel(
            promotedTags: topTagsService.getPromotedTags(),
            hottestTags: topTagsService.getHottestTags(),
            newestArticles: topArticlesService.getNewestArticles(maxNewestArticles, 0),
            hottestArticles: topArticlesService.getHottestArticles(hottestArticlesFromDate, maxHottestArticles),
        )
    }
}
