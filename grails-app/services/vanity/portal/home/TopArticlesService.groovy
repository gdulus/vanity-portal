package vanity.portal.home

import org.codehaus.groovy.grails.commons.GrailsApplication
import org.springframework.transaction.annotation.Transactional
import vanity.article.Article
import vanity.article.Tag

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
                a.publicationDate DESC
        ''',
            [max: grailsApplication.config.portal.mainPage.newestArticles.max]
        )

        return ids.collect { Article.read(it) }
    }

    @Transactional(readOnly = true)
    public List<Tag> getHottestArtciles() {
    }
}
