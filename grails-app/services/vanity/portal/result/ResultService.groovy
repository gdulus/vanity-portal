package vanity.portal.result

import org.springframework.transaction.annotation.Transactional
import vanity.article.Article
import vanity.article.ArticleService
import vanity.article.Tag
import vanity.article.TagService
import vanity.celebrity.Celebrity
import vanity.celebrity.CelebrityService
import vanity.result.ShowArticleViewModel
import vanity.result.ShowTagViewModel
import vanity.search.SearchEngineQueryExecutor
import vanity.search.SearchResult
import vanity.tracking.ClickService

class ResultService {

    ClickService clickService

    ArticleService articleService

    TagService tagService

    CelebrityService celebrityService

    SearchEngineQueryExecutor searchEngineQueryExecutor

    @Transactional(readOnly = true)
    public ShowArticleViewModel buildShowArticleModel(final String hash) {
        Article article = articleService.findByHashCode(hash)

        if (!article) {
            return null
        }

        clickService.create(article)
        return new ShowArticleViewModel(article: article)
    }

    @Transactional(readOnly = true)
    public ShowTagViewModel buildShowTagModel(final String hash) {
        Tag tag = tagService.readByHash(hash)

        if (!tag) {
            return null
        }

        List<SearchResult.ArticleSearchResult> searchResult = searchEngineQueryExecutor.getArticlesByTagName(tag.name)

        if (!searchResult) {
            return null
        }

        clickService.create(tag)
        List<String> searchResultArticleHashes = searchResult.collect { it.id }
        List<Article> articles = articleService.findByHashCodes(searchResultArticleHashes)
        Celebrity celebrity = celebrityService.findByTag(tag)
        return new ShowTagViewModel(tag: tag, articles: articles, celebirty: celebrity)
    }

}
