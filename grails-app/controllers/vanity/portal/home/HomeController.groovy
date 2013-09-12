package vanity.portal.home

class HomeController {

    def topTagsService

    def topArticlesService

    def index() {
        [
            promotedTags: topTagsService.getPromotedTags(),
            newestArticles: topArticlesService.getNewestArticles(),
            hottestArticles: topArticlesService.getHottestArticles()
        ]
    }
}
