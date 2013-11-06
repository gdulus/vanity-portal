package vanity.portal.result

import org.springframework.transaction.annotation.Transactional
import vanity.article.Article
import vanity.article.ArticleService
import vanity.tracking.ClickService

@Transactional(readOnly = true)
class ResultService {

    ClickService clickService

    ArticleService articleService

    public ShowArticleViewModel buildShowArticleModel(final String hash) {
        Article article = articleService.findByHashCode(hash)

        if (!article) {
            return null
        }

        clickService.create(article)
        return new ShowArticleViewModel(article: article)
    }

}
