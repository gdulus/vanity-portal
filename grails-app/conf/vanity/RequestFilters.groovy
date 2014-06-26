package vanity

import groovy.util.logging.Slf4j

@Slf4j
class RequestFilters {

    def filters = {
        all(controller: '*', action: '*') {
            before = {
                String cleanURI = request.forwardURI.replace("${request.getContextPath()}/", '')

                if (cleanURI.endsWith('/')) {
                    log.info('Redirecting {} due to trailing slash', cleanURI)
                    redirect(uri: "/${cleanURI.split('/').grep().join('/')}")
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
