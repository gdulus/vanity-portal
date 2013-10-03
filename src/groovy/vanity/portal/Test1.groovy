package vanity.portal

import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Slf4j
@Component
class Test1 {

    @Autowired
    Test2Service test2Service

    def myMethod() {
        log.info('Test1#test2Service')
        test2Service.serviceMethod()
    }
}
