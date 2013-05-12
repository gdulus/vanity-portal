package vanity.portal.page

import org.springframework.transaction.annotation.Transactional
import vanity.article.Article
import vanity.article.Status

class ShowTagService {

    static transactional = false

    @Transactional(readOnly = true)
    public List<Article> findRelatedArticles(final Long tagId){
        if (!tagId){
            return Collections.emptyList()
        }

        return Article.executeQuery('''
            select
                a
            from
                Article a
            left join
                a.tags t
            where
                t.id = :tagId
                and t.status in :tagOpenStatuses
            order by
                a.source.priority desc,
                a.publicationDate desc
            ''',
            [
                tagId:tagId,
                tagOpenStatuses: Status.Tag.OPEN_STATUSES,
            ]
        )

    }
}
