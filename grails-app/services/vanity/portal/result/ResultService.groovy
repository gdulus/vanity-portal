package vanity.portal.result

import org.springframework.transaction.annotation.Transactional
import vanity.article.*
import vanity.celebrity.Celebrity
import vanity.celebrity.CelebrityService
import vanity.result.ShowArticleViewModel
import vanity.result.ShowTagViewModel
import vanity.search.ArticleSearchResult
import vanity.search.SearchEngineQueryExecutor

class ResultService {

    ArticleClickService articleClickService

    ArticleService articleService

    TagService tagService

    CelebrityService celebrityService

    SearchEngineQueryExecutor searchEngineQueryExecutor

    @Transactional(readOnly = true)
    public ShowArticleViewModel buildShowArticleModel(final String hash) {
        Article article = articleService.findByHashCode(hash)

        if (!article){
            return null
        }

        articleClickService.createAsync(article)
        return new ShowArticleViewModel(article: article)
    }

    @Transactional(readOnly = true)
    public ShowTagViewModel buildShowTagModel(final String hash) {
        Tag tag = tagService.readByHash(hash)

        if (!tag) {
            return null
        }

        List<ArticleSearchResult> searchResult = searchEngineQueryExecutor.getArticlesByTagName(tag.name)

        if (!searchResult) {
            return null
        }

        List<String> searchResultArticleHashes = searchResult.collect { it.id }
        List<Article> articles = articleService.findByHashCodes(searchResultArticleHashes)
        Celebrity celebrity = celebrityService.findByTag(tag)

        return new ShowTagViewModel(tag: tag, articles: articles, celebirty: celebrity)
    }

}
