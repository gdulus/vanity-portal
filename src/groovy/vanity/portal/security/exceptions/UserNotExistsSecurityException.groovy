package vanity.portal.security.exceptions

class UserNotExistsSecurityException extends SecurityException {

    UserNotExistsSecurityException(Long id) {
        this("Could not find user with id ${id}")
    }

    UserNotExistsSecurityException(String var1) {
        super(var1)
    }
}
