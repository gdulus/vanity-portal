package vanity.portal.i18n

import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.support.AbstractMessageSource

import java.text.MessageFormat

@Slf4j
class DatabaseMessageSource extends AbstractMessageSource {

    private static final Locale PL_LOCALE = new Locale('pl', 'PL')

    @Autowired
    PortalMessageService messageService

    @Override
    protected MessageFormat resolveCode(final String code, final Locale locale) {
        MessageDto dto = messageService.findByCodeAndLocale(code, PL_LOCALE)

        if (messageService.shouldBeEvicted(dto)) {
            messageService.cacheEvict(code, PL_LOCALE)
            dto = messageService.findByCodeAndLocale(code, PL_LOCALE)
        }

        return dto ? new MessageFormat(dto.message, PL_LOCALE) : new MessageFormat(code, locale)
    }
}
