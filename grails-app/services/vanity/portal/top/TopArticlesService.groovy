package vanity.portal.top

import org.springframework.transaction.annotation.Transactional
import vanity.article.Article
import vanity.stats.PopularityService

class TopArticlesService {

    PopularityService popularityService

    @Transactional(readOnly = true)
    public List<Article> getNewestArticles(final Integer max, final Integer offset) {
        return Article.listOrderByPublicationDate(max: max, offset: offset, order: 'desc')
    }

    @Transactional(readOnly = true)
    public List<Article> getHottestArticles(final Date fromDate, final Integer max) {
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
