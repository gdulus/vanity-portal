package vanity.portal.home

import org.codehaus.groovy.grails.commons.GrailsApplication
import org.springframework.transaction.annotation.Transactional
import vanity.article.Article
import vanity.stats.PopularityService
import vanity.utils.ConfigUtils

class TopArticlesService {

    GrailsApplication grailsApplication

    PopularityService popularityService

    @Transactional(readOnly = true)
    public List<Article> getNewestArticles() {
        return Article.executeQuery('''
            from
                Article a
            order by
                a.publicationDate desc
        ''',
            [
                max: grailsApplication.config.portal.mainPage.newestArticles.max
            ]
        )
    }

    @Transactional(readOnly = true)
    public List<Article> getHottestArticles() {
        Date fromDate = (new Date() - ConfigUtils.$as(grailsApplication.config.portal.mainPage.hottestArticles.dateWindow, Integer))
        Integer max = ConfigUtils.$as(grailsApplication.config.portal.mainPage.hottestArticles.max, Integer)
        List<Article> popular = popularityService.getTopArticlesFromDate(fromDate, max).collect { Article.read(it.elementId) }

        if (popular.size() == max) {
            return popular
        }

        List<Article> rest = Article.executeQuery('''
            from
                Article a
            where
                a.publicationDate >= :fromDate
                and a.id not in (:popular)
            order by
                a.publicationDate desc
        ''',
            [
                max: (max - popular.size()),
                fromDate: fromDate,
                popular: popular*.id ?: [-1L]
            ]
        )

        return (popular + rest)
    }
}
