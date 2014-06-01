package vanity.portal.result

import org.springframework.transaction.annotation.Transactional
import vanity.article.Article
import vanity.article.ArticleService
import vanity.article.Tag
import vanity.stats.PopularityService
import vanity.tracking.ClickService

@Transactional(readOnly = true)
class ResultService {

    ClickService clickService

    ArticleService articleService

    PopularityService popularityService

    public ShowPreviewViewModel buildShowPreview(final Long id, final String currentPage) {
        Article article = articleService.read(id)

        if (!article) {
            return null
        }

        clickService.create(article)
        Set<Article> other = [] as LinkedHashSet<Article>
        Map<Tag, List<Article>> otherPopular = popularityService.findOtherPopular(article, 3, 100)
        otherPopular.values().each { other += it }
        return new ShowPreviewViewModel(currentPage: currentPage, article: article, other: other.sort { it.publicationDate })
    }

    public ShowArticleViewModel buildShowArticleModel(final Long id) {
        Article article = articleService.read(id)

        if (!article) {
            return null
        }

        clickService.create(article)
        return new ShowArticleViewModel(article: article)
    }

}
