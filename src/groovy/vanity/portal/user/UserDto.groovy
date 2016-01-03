package vanity.portal.user

import org.apache.commons.lang.Validate
import vanity.user.User

class UserDto {

    Long id

    String username

    String email

    String gender

    Boolean emailConfirmed

    Boolean firstLogin

    public static UserDto build(final User user, final UserActivityDto userActivityDto) {
        Validate.notNull(user)
        return new UserDto(
                id: user.id,
                username: user.username,
                email: user.profile.email,
                emailConfirmed: userActivityDto.emailConfirmedCount != 0,
                firstLogin: userActivityDto.logInCount <= 1,
                gender: user.profile.gender.name()
        )
    }

    public static UserDto build(final User user) {
        return build(user, UserActivityDto.NULL_DTO)
    }
}
