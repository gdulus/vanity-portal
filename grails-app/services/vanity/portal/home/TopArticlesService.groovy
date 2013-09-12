package vanity.portal.home

import org.codehaus.groovy.grails.commons.GrailsApplication
import org.springframework.transaction.annotation.Transactional
import vanity.article.Article

class TopArticlesService {

    GrailsApplication grailsApplication

    @Transactional(readOnly = true)
    public List<Article> getNewestArticles() {
        List<Long> ids = (List<Long>) Article.executeQuery('''
            select
                a.id
            from
                Article a
            order by
                a.publicationDate desc
        ''',
            [
                max: grailsApplication.config.portal.mainPage.newestArticles.max
            ]
        )

        return ids.collect { Article.read(it) }
    }

    @Transactional(readOnly = true)
    public List<Article> getHottestArticles() {
        List<Long> ids = (List<Long>) Article.executeQuery('''
            select
                a.id
            from
                Article a
            where
                publicationDate >= :dateWindow
            order by
                a.rank desc ,
                a.publicationDate desc
        ''',
            [
                max: grailsApplication.config.portal.mainPage.hottestArticles.max,
                dateWindow: (new Date() - (int)grailsApplication.config.portal.mainPage.hottestArticles.dateWindow)
            ]
        )

        return ids.collect { Article.read(it) }
    }
}
