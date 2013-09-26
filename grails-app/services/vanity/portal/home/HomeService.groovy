package vanity.portal.home

class HomeService {

    static transactional = false

    TopArticlesService topArticlesService

    TopTagsService topTagsService

    public MainViewModel buildMainModel() {
        return new MainViewModel(
            promotedTags: topTagsService.getPromotedTags(),
            hottestTags: topTagsService.getHottestTags(),
            newestArticles: topArticlesService.getNewestArticles(),
            hottestArticles: topArticlesService.getHottestArticles(),
        )
    }
}
