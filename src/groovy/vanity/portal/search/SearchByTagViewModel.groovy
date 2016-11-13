package vanity.portal.search

import vanity.article.Article
import vanity.article.Tag
import vanity.celebrity.Celebrity
import vanity.celebrity.CelebrityImage

class SearchByTagViewModel {

    Tag tag

    Celebrity celebrity

    CelebrityImage celebrityImage

    List<Article> articles

    Long numFound

    Long start

}
