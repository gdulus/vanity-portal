package vanity.portal

import org.springframework.transaction.annotation.Transactional
import vanity.article.Article
import vanity.article.ArticleService
import vanity.article.Tag
import vanity.search.ArticleSearchResult
import vanity.search.SearchEngineQueryExecutor

class SearchService {

    SearchEngineQueryExecutor searchEngineQueryExecutor

    ArticleService articleService

    @Transactional(readOnly = true)
    public List<Article> findRelatedArticles(final Tag tag){
        if (!tag){
            return Collections.emptyList()
        }

        List<ArticleSearchResult> searchResult = searchEngineQueryExecutor.getArticles(tag.name)
        List<String> searchResultArticleHashes = searchResult.collect {it.id}
        return articleService.findByHashCodes(searchResultArticleHashes)
    }
}
