package vanity.portal.tracking

import org.codehaus.groovy.grails.web.servlet.mvc.GrailsWebRequest
import org.codehaus.groovy.grails.web.util.WebUtils
import org.springframework.stereotype.Component

import javax.servlet.http.HttpServletRequest

@Component
class IPProvider {

    public String get() {
        GrailsWebRequest webUtils = WebUtils.retrieveGrailsWebRequest()
        HttpServletRequest request = webUtils.getCurrentRequest()
        return request.getRemoteAddr() ?: request.getHeader("X-Forwarded-For") ?: request.getHeader("Client-IP")
    }

}
