package vanity.portal

import groovy.util.logging.Slf4j

@Slf4j
class TestController {

    Test1Service test1Service

    def index() {
        log.info('Test action')
        test1Service.serviceMethod()
        render(text: 'xxxxx')
    }
}
