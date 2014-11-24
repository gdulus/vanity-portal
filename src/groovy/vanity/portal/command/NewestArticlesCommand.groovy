package vanity.portal.command

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.jcabi.http.Request
import com.jcabi.http.request.JdkRequest
import vanity.portal.domain.Article
import vanity.portal.domain.PopularTag
import vanity.portal.ms.ServiceEndpointsRepository

import java.lang.reflect.Type

public class NewestArticlesCommand extends AbstractCommand<List<Article>> {

    private final Integer max

    NewestArticlesCommand(ServiceEndpointsRepository serviceRepository, Integer max) {
        super(serviceRepository, 'ms.news.articles')
        this.max = max
    }

    @Override
    protected List<PopularTag> run() {
        String body = new JdkRequest(serviceRepository.getEndpoint('article', 'newest'))
            .uri()
            .queryParam('max', max)
            .back()
            .method(Request.GET)
            .fetch().body()
        Type listType = new TypeToken<ArrayList<Article>>() {}.getType();
        return new Gson().fromJson(body, listType)
    }

    @Override
    protected List<Article> getFallback() {
        return Collections.emptyList()
    }

}
