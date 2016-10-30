package vanity.portal.user

import grails.plugin.springsecurity.SpringSecurityService
import grails.transaction.Transactional
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.transaction.support.TransactionSynchronizationAdapter
import org.springframework.transaction.support.TransactionSynchronizationManager
import vanity.portal.notification.EmailSender
import vanity.portal.security.TokenProvider
import vanity.portal.security.exceptions.UserNotExistsSecurityException
import vanity.portal.security.token.RegistrationToken
import vanity.user.*

class UserService {

    SpringSecurityService springSecurityService

    UserActivityService userActivityService

    @Autowired
    EmailSender emailSender

    @Autowired
    TokenProvider tokenProvider

    @Transactional
    public User create(final String username, final String password, final @DelegatesTo(Profile) Closure profileBinder) {
        // create user profile object
        Profile profile = new Profile()
        profile.with profileBinder
        profile.save(failOnError: true)
        // create user object
        User user = new User(username: username, password: springSecurityService.encodePassword(password), profile: profile, enabled: false)
        user.save(failOnError: true)
        // apply specific role
        UserRole.create(user, Role.findByAuthority(Authority.ROLE_PORTAL_USER))
        // track an action on the user
        userActivityService.create(user, UserActivityType.CREATE_ACCOUNT)
        // send registration email
        TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronizationAdapter() {
            @Override
            public void afterCommit() {
                emailSender.sendUserRegistrationEmail(user, tokenProvider.decodeAsRegistrationToken(user))
            }
        })
        return user
    }

    @Transactional
    public User activate(final String registrationToken) {
        RegistrationToken token = tokenProvider.encodeRegistrationToken(registrationToken)
        User user = User.get(token.id)
        user.enabled = true
        user.save(failOnError: true)
        userActivityService.create(user, UserActivityType.ACCOUNT_ENABLED)
        return user
    }

    @Transactional
    public User update(final Long userId, final @DelegatesTo(Profile) Closure profileBinder) {
        User user = User.get(userId)

        if (!user) {
            throw new UserNotExistsSecurityException(userId)
        }

        user.profile.with profileBinder
        user.save(failOnError: true)
        // track an action on the user
        userActivityService.create(user, UserActivityType.UPDATE_ACCOUNT)
        return user
    }

    public User findByUsername(final String username) {
        return User.findByUsername(username)
    }

    public User read(final Long id) {
        return User.read(id)
    }

}
