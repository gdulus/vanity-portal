package vanity.portal.ms

import org.springframework.stereotype.Repository

import javax.annotation.PostConstruct

@Repository
class ServiceEndpointsRepository {

    private Map<String, String> endpointsURLs = [:]

    @PostConstruct
    public void init() {
        endpointsURLs['tag'] = 'http://localhost:8101'
        endpointsURLs['article'] = 'http://localhost:8101'
    }

    public String getEndpoint(final String resource, final String value) {
        return "${endpointsURLs[resource]}/${resource}/${value}"
    }

}
