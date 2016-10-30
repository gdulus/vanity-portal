package vanity.portal.javascript

import vanity.portal.i18n.PortalMessageService

class I18nController {

    private static final Locale PL_LOCALE = new Locale('pl', 'PL')

    private static final String MESSAGE_PREFIX = 'social'

    PortalMessageService portalMessageService

    def social() {
        [messages: portalMessageService.findAllByPrefix(MESSAGE_PREFIX, PL_LOCALE)]
    }

}
