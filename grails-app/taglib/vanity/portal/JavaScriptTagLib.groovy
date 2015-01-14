package vanity.portal

import org.codehaus.groovy.grails.web.mapping.LinkGenerator

class JavaScriptTagLib {

    static namespace = 'v'

    LinkGenerator grailsLinkGenerator

    def jsBaseConfig = { attrs ->
        String contextPath = grailsLinkGenerator.contextPath
        String serverBaseURL = grailsLinkGenerator.serverBaseURL
        out << g.javascript(attrs) {
            """
            var V = V || {};
            V.Config = {contextPath:'${contextPath}', baseURL:'${serverBaseURL}'};
            """
        }
    }

}
