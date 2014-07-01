package vanity

import groovy.util.logging.Slf4j

@Slf4j
class RequestFilters {

    def filters = {
        all(controller: '*', action: '*') {
            before = {
                String cleanURI = request.forwardURI.replace("${request.contextPath}/", '')
                log.info('forward uri = {}, context path = {}, clean uri = {}', request.forwardURI, request.contextPath, cleanURI)

                if (cleanURI.endsWith('/')) {
                    log.info('Redirecting {} due to trailing slash', cleanURI)
                    String uri = "/${cleanURI.split('/').grep().join('/')}"
                    redirect(uri: uri)
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
