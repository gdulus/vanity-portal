package vanity.portal.command

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.jcabi.http.Request
import com.jcabi.http.request.JdkRequest
import vanity.portal.domain.PopularTag
import vanity.portal.domain.Tag
import vanity.portal.ms.ServiceEndpointsRepository

import java.lang.reflect.Type

public class PopularTagsCommand extends AbstractCommand<List<PopularTag>> {

    private final Integer max

    private final Integer dateOffset

    PopularTagsCommand(ServiceEndpointsRepository serviceRepository, Integer max, Integer dateOffset) {
        super(serviceRepository, 'ms.news.tags')
        this.max = max
        this.dateOffset = dateOffset
    }

    @Override
    protected List<PopularTag> run() {
        String body = new JdkRequest(serviceRepository.getEndpoint('tag', 'popular'))
            .uri()
            .queryParam('max', max)
            .queryParam('dateOffset', dateOffset)
            .back()
            .method(Request.GET)
            .fetch().body()
        Type listType = new TypeToken<ArrayList<PopularTag>>() {}.getType();
        return new Gson().fromJson(body, listType)
    }

    @Override
    protected List<Tag> getFallback() {
        return Collections.emptyList()
    }

}
