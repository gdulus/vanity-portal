package vanity.portal.request

import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired

@Slf4j
class RequestFilters {

    @Autowired
    RequestWorker requestWorker

    def filters = {
        all(controller: '*', action: '*') {
            before = {
                if (requestWorker.isUrlInvalid(request)) {
                    log.info('Redirecting {} due to trailing slash', request.forwardURI)
                    redirect(uri: requestWorker.correctInvalidUrl(request))
                    return false
                }
            }

            after = { Map model ->
            }

            afterView = { Exception e ->
            }
        }
    }
}
