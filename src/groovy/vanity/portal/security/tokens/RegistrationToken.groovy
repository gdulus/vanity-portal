package vanity.portal.security.tokens

import org.apache.commons.lang.Validate
import vanity.user.User

class RegistrationToken {

    final Long id;

    private RegistrationToken(Long id) {
        this.id = id
    }

    public static RegistrationToken buildFromUser(final User principal) {
        return new RegistrationToken(principal.id);
    }

    public static RegistrationToken buildFromSerializedString(final String string) {
        Validate.isTrue(string?.isLong())
        Long id = string.toLong();
        return new RegistrationToken(id);
    }

    public String serialize() {
        return id
    }

}
