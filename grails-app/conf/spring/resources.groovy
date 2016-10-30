import grails.util.Environment
import vanity.portal.i18n.DatabaseMessageSource
import vanity.portal.request.DefaultRequestWorker
import vanity.portal.request.DevelopmentRequestWorker

beans = {

    xmlns context: "http://www.springframework.org/schema/context"
    xmlns task: "http://www.springframework.org/schema/task"

    context.'component-scan'('base-package': 'vanity.portal')
    task.'annotation-driven'('proxy-target-class': true, 'mode': 'proxy')

    if (Environment.isDevelopmentMode()) {
        requestWorker(DevelopmentRequestWorker)
    } else {
        requestWorker(DefaultRequestWorker)
    }

    messageSource(DatabaseMessageSource)

}
