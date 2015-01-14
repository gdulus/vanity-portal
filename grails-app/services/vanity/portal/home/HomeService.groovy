package vanity.portal.home

import org.springframework.beans.factory.annotation.Value
import vanity.portal.command.NewestArticlesCommand
import vanity.portal.command.PopularArticlesCommand
import vanity.portal.command.PopularTagsCommand
import vanity.portal.command.PromotedTagsCommand
import vanity.portal.domain.Article
import vanity.portal.domain.ArticlePage
import vanity.portal.domain.PopularTag
import vanity.portal.domain.Tag
import vanity.portal.ms.ServiceEndpointsRepository

import java.util.concurrent.Future

class HomeService {

    static transactional = false

    ServiceEndpointsRepository serviceEndpointsRepository

    @Value('${portal.mainPage.promotedTags.max}')
    Integer maxPromotedTags

    @Value('${portal.mainPage.popularTags.max}')
    Integer maxPopularTags

    @Value('${portal.mainPage.popularTags.dateWindow}')
    Integer popularTagsDateWindow

    @Value('${portal.mainPage.newestArticles.max}')
    Integer maxNewestArticles

    @Value('${portal.mainPage.hottestArticles.max}')
    Integer maxPopularArticles

    @Value('${portal.mainPage.hottestArticles.dateWindow}')
    Integer popularArticlesDateWindow

    public MainViewModel buildMainModel() {
        Future<List<Tag>> promotedTagsCommand = new PromotedTagsCommand(serviceEndpointsRepository, maxPromotedTags).queue()
        Future<List<PopularTag>> popularTagsCommand = new PopularTagsCommand(serviceEndpointsRepository, maxPopularTags, popularTagsDateWindow).queue()
        Future<ArticlePage> newestArticles = new NewestArticlesCommand(serviceEndpointsRepository, 0, maxNewestArticles).queue()
        Future<List<Article>> popularArticles = new PopularArticlesCommand(serviceEndpointsRepository, maxPopularArticles, popularArticlesDateWindow).queue()

        return new MainViewModel(
            promotedTags: promotedTagsCommand.get(),
            popularTags: popularTagsCommand.get(),
            newestArticles: newestArticles.get(),
            hottestArticles: popularArticles.get()
        )
    }

}
