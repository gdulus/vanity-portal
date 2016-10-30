package vanity.portal.rest.commands

import vanity.location.City
import vanity.user.Profile

class UpdateUserCommand extends AbstractCommand {

    final String avatar

    final Date dateOfBirth

    final City city

    UpdateUserCommand(final Map requestData) {
        this.avatar = requestData.avatar
        this.dateOfBirth = parseDate(Profile, 'birthday', (String) requestData.birthday)
        this.city = City.load(parsLong(Profile, 'city', (String) requestData.city))
    }
}
