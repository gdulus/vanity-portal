package vanity.portal.result

import vanity.article.Article

class ShowPreviewViewModel {

    String currentPage

    Article article

    Set<Article> other

    Set<Article> getOther() {
        return other ?: Collections.emptySet()
    }
}
