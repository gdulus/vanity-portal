package vanity.portal.security

import org.springframework.beans.factory.annotation.Autowired
import vanity.portal.security.exceptions.AccountLockedSecurityException
import vanity.portal.security.exceptions.PasswordInvalidSecurityException
import vanity.portal.security.exceptions.UserNotExistsSecurityException
import vanity.portal.security.token.UserToken
import vanity.portal.user.UserActivityService
import vanity.portal.user.UserService
import vanity.user.User
import vanity.user.UserActivityType

class AuthService {

    static transactional = false

    UserActivityService userActivityService

    @Autowired
    UserService userService

    @Autowired
    TokenProvider tokenProvider

    def passwordEncoder

    public AuthDto auth(final String token) {
        if (!token) {
            throw new SecurityException('Token empty or null')
        }

        try {
            UserToken userToken = tokenProvider.encodeUserToken(token)
            User user = userService.read(userToken.id)

            if (!user) {
                throw new SecurityException("User with id ${userToken.id} doesn't exists")
            }

            if (!user.enabled || user.accountLocked) {
                throw new SecurityException("User ${user} is disabled or password is locked")
            }

            return AuthDto.build(tokenProvider.decodeAsUserToken(user), user)

        } catch (IllegalStateException exp) {
            throw new SecurityException("Token ${token} is invalid")
        } catch (Throwable exp) {
            throw new SecurityException("There was security exception: ${exp.message}")
        }
    }

    public AuthDto auth(final String username, final String password) {
        User user = userService.findByUsername(username)

        if (!user) {
            throw new UserNotExistsSecurityException("User ${username} doesn't exits")
        }

        if (!passwordEncoder.isPasswordValid(user.password, password, null)) {
            throw new PasswordInvalidSecurityException("Invalid password for user ${user}")
        }

        if (!user.enabled || user.accountLocked) {
            throw new AccountLockedSecurityException("User ${user} is disabled or password is locked")
        }

        userActivityService.create(user, UserActivityType.LOG_IN)
        return AuthDto.build(tokenProvider.decodeAsUserToken(user), user)

    }

    public String buildToken(final User user) {
        return tokenProvider.decodeAsUserToken(user)
    }

}
