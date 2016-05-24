package vanity.portal.utils

import grails.converters.JSON
import org.springframework.validation.Errors
import vanity.portal.validation.exceptions.CustomValidationException

class JSONUtils {

    public static final JSON EMPTY = [:] as JSON

    public static JSON convert(final Class context, final Errors errors) {
        errors.fieldErrors.collectEntries { [(it.field): "${context.name}.${it.field}.${it.code}".toLowerCase()] } as JSON
    }

    public static JSON convert(final CustomValidationException exp) {
        exp.reject.collectEntries { [(it.key): "${exp.context.name}.${it.key}.${it.value}".toLowerCase()] }
    }

}
