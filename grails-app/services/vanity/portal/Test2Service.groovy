package vanity.portal

import groovy.util.logging.Slf4j

@Slf4j
class Test2Service {

    def sessionFactory

    def serviceMethod() {
        log.info(sessionFactory.currentSession.transaction.toString())
    }
}
