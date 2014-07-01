package vanity

import groovy.util.logging.Slf4j

@Slf4j
class RequestFilters {

    def filters = {
        all(controller: '*', action: '*') {
            before = {
                String cleanURI = request.forwardURI
                // need to be done on development machine, in other case redirection is broken
                if (request.contextPath) {
                    cleanURI = cleanURI.replace("${request.contextPath}/", '')
                }
                // no / at the end allowed
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
