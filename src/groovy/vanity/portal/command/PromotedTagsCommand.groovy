package vanity.portal.command

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.jcabi.http.Request
import com.jcabi.http.request.JdkRequest
import vanity.portal.domain.Tag
import vanity.portal.ms.ServiceEndpointsRepository

import java.lang.reflect.Type

public class PromotedTagsCommand extends AbstractCommand<List<Tag>> {

    private final Integer max

    PromotedTagsCommand(ServiceEndpointsRepository serviceRepository, Integer max) {
        super(serviceRepository, 'ms.news.tags')
        this.max = max
    }

    @Override
    protected List<Tag> run() {
        String body = new JdkRequest(serviceRepository.getEndpoint('tag', 'promoted'))
            .uri()
            .queryParam('max', max)
            .back()
            .method(Request.GET)
            .fetch().body()
        Type listType = new TypeToken<ArrayList<Tag>>() {}.getType();
        return new Gson().fromJson(body, listType)
    }

    @Override
    protected List<Tag> getFallback() {
        return Collections.emptyList()
    }

    @Override
    protected String getCacheKey() {
        return 'ms.news.tags.promoted'
    }
}
