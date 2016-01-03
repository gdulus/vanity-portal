package vanity.portal.security

import vanity.user.User

class AuthDto {

    final String token

    final Long userId

    private AuthDto(String token, Long userId) {
        this.token = token
        this.userId = userId
    }

    public static AuthDto build(final String token, final User user) {
        new AuthDto(token, user.id)
    }
}
