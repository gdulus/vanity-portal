package vanity.portal.i18n

import grails.plugin.cache.CacheEvict
import grails.plugin.cache.Cacheable
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Value
import vanity.i18n.Message

@Slf4j
class PortalMessageService {

    static transactional = false

    @Value('${grails.cache.message.ttl}')
    private int ttl

    public List<Message> findAllByPrefix(final String prefix, final Locale locale) {
        log.info('Searching for all by prefix {} and locale {}', prefix, locale)
        return Message.findAllByCodeIlikeAndLocale(prefix, locale)
    }

    @Cacheable(value = 'message', key = '#code + #locale.language + #locale.country')
    public MessageDto findByCodeAndLocale(final String code, final Locale locale) {
        log.info('Searching for message {} and locale {}', code, locale)
        Message message = Message.findByCodeAndLocale(code, locale)

        if (!message) {
            return null
        }

        return new MessageDto(message: message.text, foundDate: new Date().time)
    }

    @CacheEvict(value = 'message', key = '#code + #locale.language + #locale.country')
    public void cacheEvict(final String code, final Locale locale) {
        log.info('Clearing cache for message {} and locale {}', code, locale)
    }

    public boolean shouldBeEvicted(final MessageDto dto) {
        return dto && new Date().time - dto.foundDate >= ttl
    }

}
