package vanity.portal.utils

import grails.converters.JSON
import org.springframework.validation.Errors

class JSONUtils {

    public static final JSON EMPTY = [:] as JSON

    public static JSON convert(final Class context, final Errors errors) {
        errors.fieldErrors.collectEntries { [(it.field): "${context.name}.${it.field}.${it.code}".toLowerCase()] } as JSON
    }

}
