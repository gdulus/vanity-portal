package vanity.portal.result

import org.springframework.transaction.annotation.Transactional
import vanity.article.Article
import vanity.article.ArticleService
import vanity.article.Tag
import vanity.article.TagService

@Transactional(readOnly = true)
class ResultService {

    ArticleService articleService

    TagService tagService

    public ShowPreviewViewModel buildShowPreview(final Long id, final String currentPage) {
        Article article = articleService.read(id)

        if (!article) {
            return null
        }

        List<Tag> tags = tagService.findAllRootParentsByArticle(article)

        if (!tags) {
            return new ShowPreviewViewModel(currentPage: currentPage, article: article)
        }

        Set<Article> other = tags?.sum { Tag tag -> articleService.findAllNewsetByTagAndDate(tag, article.publicationDate, 3) } as LinkedHashSet<Article>
        other.remove(article)
        return new ShowPreviewViewModel(currentPage: currentPage, article: article, other: other)
    }

    public ShowArticleViewModel buildShowArticleModel(final Long id) {
        Article article = articleService.read(id)

        if (!article) {
            return null
        }

        return new ShowArticleViewModel(article: article)
    }

}
