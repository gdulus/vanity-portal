package vanity.portal

import groovy.util.logging.Slf4j

@Slf4j
class Test1Service {

    Test1 test1

    Test2Service test2Service

    def sessionFactory

    def serviceMethod() {
        log.info('current transaction')
        log.info(sessionFactory.currentSession.transaction.toString())
        log.info('calling test2Service through wrapper')
        test1.myMethod()
        log.info('calling test2Service directly')
        test2Service.serviceMethod()
    }
}
