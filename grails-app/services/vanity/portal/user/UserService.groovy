package vanity.portal.user

import grails.plugin.springsecurity.SpringSecurityService
import grails.transaction.Transactional
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.transaction.support.TransactionSynchronizationAdapter
import org.springframework.transaction.support.TransactionSynchronizationManager
import vanity.portal.notification.EmailSender
import vanity.user.*

class UserService {

    SpringSecurityService springSecurityService

    UserActivityService userActivityService

    @Autowired
    EmailSender emailSender

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
                emailSender.sendUserRegistrationEmail(user)
            }
        })
        return user
    }

    @Transactional
    public User update(final Long id, final @DelegatesTo(Profile) Closure profileBinder) {
        User user = User.get(id)
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
