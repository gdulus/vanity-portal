package vanity.portal.user

import vanity.user.User

class UserDto {

    Long id

    String username

    String email

    String gender

    Boolean emailConfirmed

    Boolean firstLogin

    public static UserDto build(final User user, final UserActivityDto userActivityDto) {
        return new UserDto(
                id: user.id,
                username: user.username,
                email: user.profile.email,
                emailConfirmed: userActivityDto.emailConfirmedCount != 0,
                firstLogin: userActivityDto.logInCount <= 1,
                gender: user.profile.gender.name()
        )
    }
}
