package vanity.portal.user

import org.apache.commons.lang.Validate
import vanity.user.User

class UserDto {

    Long id

    String username

    String email

    String gender

    Long city

    Long voivodeship

    Long country

    String avatar

    String birthday

    Boolean emailConfirmed

    Boolean firstLogin

    public static UserDto build(final User user, final UserActivityDto userActivityDto) {
        Validate.notNull(user)
        return new UserDto(
                id: user.id,
                username: user.username,
                email: user.profile.email,
                gender: user.profile.gender.name(),
                city: user.profile.city?.id,
                voivodeship: user.profile?.city?.voivodeship?.id,
                country: user.profile?.city?.voivodeship?.coutry?.id,
                avatar: user.profile.avatar,
                birthday: user.profile.dateOfBirth?.format('yyyy-MM-dd'),
                emailConfirmed: userActivityDto.emailConfirmedCount != 0,
                firstLogin: userActivityDto.logInCount <= 1,
        )
    }

    public static UserDto build(final User user) {
        return build(user, UserActivityDto.NULL_DTO)
    }
}
