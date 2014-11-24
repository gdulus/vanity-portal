package vanity.portal.command

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.jcabi.http.Request
import com.jcabi.http.request.JdkRequest
import groovy.transform.CompileStatic
import vanity.portal.domain.Article
import vanity.portal.domain.PopularTag
import vanity.portal.domain.Tag
import vanity.portal.ms.ServiceEndpointsRepository

import java.lang.reflect.Type

public class PopularArticlesCommand extends AbstractCommand<List<Article>> {

    private final Integer max

    private final Integer dateOffset

    PopularArticlesCommand(ServiceEndpointsRepository serviceRepository, Integer max, Integer dateOffset) {
        super(serviceRepository, 'ms.news.articles')
        this.max = max
        this.dateOffset = dateOffset
    }

    @Override
    protected List<Article> run() {
        String body = new JdkRequest(serviceRepository.getEndpoint('article', 'popular'))
            .uri()
            .queryParam('max', max)
            .queryParam('dateOffset', dateOffset)
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
