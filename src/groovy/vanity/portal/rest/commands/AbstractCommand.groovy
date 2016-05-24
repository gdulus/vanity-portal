package vanity.portal.rest.commands

import groovy.util.logging.Slf4j
import vanity.portal.validation.exceptions.CustomValidationException

@Slf4j
class AbstractCommand {

    protected Date parseDate(final Class<?> context, final String fieldName, final String rawData) {
        this.<Date> parse(context, fieldName) { Date.parse('yyyy-MM-dd', rawData) }
    }

    protected Long parsLong(final Class<?> context, final String fieldName, final String rawData) {
        this.<Long> parse(context, fieldName) { Long.valueOf(rawData) }
    }

    private <T> T parse(final Class<?> context, final String fieldName, final Closure worker) {
        try {
            return (T) worker.call()
        } catch (Throwable exp) {
            log.error("There was an error while parsing data", exp)
            throw new CustomValidationException(context, [(fieldName): 'format.error'])
        }
    }
}
