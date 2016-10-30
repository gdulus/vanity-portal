package vanity.portal.utils

import vanity.portal.validation.exceptions.CustomValidationException

class ValidationUtils {

    public static void notNull(final Object value, final String field, final Class context) {
        if (value == null) {
            throw new CustomValidationException(context, [(field): 'null'])
        }
    }
}
