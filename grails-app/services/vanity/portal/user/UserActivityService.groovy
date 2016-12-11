package vanity.portal.user

import groovy.util.logging.Slf4j
import org.apache.commons.lang.Validate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.transaction.annotation.Transactional
import vanity.portal.tracking.IPProvider
import vanity.stats.UserActivity
import vanity.user.User
import vanity.user.UserActivityType

@Slf4j
class UserActivityService {

    @Autowired
    IPProvider ipProvider

    @Transactional
    public boolean create(final User user, UserActivityType type) {
        Validate.notNull(user)
        Validate.notNull(type)

        String ip = ipProvider.get()
        UserActivity activity = new UserActivity(user: user, actionType: type, ip: ip)

        if (activity.save()) {
            log.info('Created activity {} for user {} and IP {}', type, user, ip)
            return true
        } else {
            log.warn('There was an error while creating user action: {}', user.errors)
            return false
        }
    }

    public UserActivityDto get(final User user) {
        int emailConfirmedCount = UserActivity.countByUserAndActionType(user, UserActivityType.ACCOUNT_ENABLED)
        int logInCount = UserActivity.countByUserAndActionType(user, UserActivityType.LOG_IN)
        return new UserActivityDto(emailConfirmedCount: emailConfirmedCount, logInCount: logInCount)
    }

}
