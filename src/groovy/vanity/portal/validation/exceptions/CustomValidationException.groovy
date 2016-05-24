package vanity.portal.validation.exceptions

import groovy.transform.ToString

@ToString
class CustomValidationException extends RuntimeException {

    final Class<?> context

    final Map<String, String> reject

    CustomValidationException(Class<?> context, Map<String, String> reject) {
        this.context = context
        this.reject = reject
    }
}
