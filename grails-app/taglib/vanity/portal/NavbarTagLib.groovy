package vanity.portal

import org.apache.commons.lang.StringUtils

class NavbarTagLib {

    static namespace = 'v'

    def menuItem = { attrs, body ->
        String code = attrs.remove('code')
        String controller = attrs.remove('controller')
        String action = attrs.remove('action')
        String cssClass = controllerName == controller && actionName == action ? 'active' : StringUtils.EMPTY
        out << g.link(controller: controller, action: action, class: cssClass) {
            return g.message(code: code)
        }
    }

}
