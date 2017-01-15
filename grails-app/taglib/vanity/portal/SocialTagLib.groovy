package vanity.portal

import grails.converters.JSON
import vanity.celebrity.Celebrity
import vanity.portal.i18n.PortalMessageService

class SocialTagLib {

    private static final Locale PL_LOCALE = new Locale('pl', 'PL')

    static namespace = 'social'

    PortalMessageService portalMessageService

    def vipData = { attrs ->
        Object viewModel = attrs.remove('viewModel')
        if (viewModel.hasProperty('celebrity') && viewModel.celebrity) {
            Celebrity celebrity = viewModel.celebrity
            out << asJSONString('SOCIAL_VIP_DATA', [id: celebrity.id, name: celebrity.fullName])
        } else {
            out << asJSONString('SOCIAL_VIP_DATA', Collections.emptyMap())
        }
    }

    def i18n = { attrs ->
        out << asJSONString('SOCIAL_I18N', portalMessageService.findAllByPrefix(namespace, PL_LOCALE).collectEntries {
            [(it.code): it.message]
        })
    }

    private String asJSONString(String globalVariable, def data) {
        "${globalVariable} = ${data as JSON};"
    }

}
