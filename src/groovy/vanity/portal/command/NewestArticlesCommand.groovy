package vanity.portal.command

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.jcabi.http.Request
import com.jcabi.http.request.JdkRequest
import vanity.portal.domain.ArticlePage
import vanity.portal.ms.ServiceEndpointsRepository

import java.lang.reflect.Type

public class NewestArticlesCommand extends AbstractCommand<ArticlePage> {

    private final Integer offset

    private final Integer max

    NewestArticlesCommand(ServiceEndpointsRepository serviceRepository, Integer offset, Integer max) {
        super(serviceRepository, 'ms.news.articles')
        this.offset = offset
        this.max = max
    }

    @Override
    protected ArticlePage run() {
        String body = new JdkRequest(serviceRepository.getEndpoint('article', 'newest'))
            .uri()
            .queryParam('page', offset)
            .queryParam('size', max)
            .back()
            .method(Request.GET)
            .fetch().body()
        Type type = new TypeToken<ArticlePage>() {}.getType();
        return new Gson().fromJson(body, type)
    }

    @Override
    protected ArticlePage getFallback() {
        return ArticlePage.EMPTY
    }

}
