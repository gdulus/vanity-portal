package vanity.portal.security

import vanity.user.User

class UserToken {

    private static final String SEPARATOR = "|";

    final Long id;

    final Long timeStamp;

    private UserToken(Long id, Date timeStamp) {
        this(id, timeStamp.getTime());
    }

    private UserToken(Long id, Long timeStamp) {
        this.id = id;
        this.timeStamp = timeStamp;
    }

    public static UserToken buildFromUser(final User principal) {
        return new UserToken(principal.id, new Date());
    }

    public static UserToken buildFromSerializedString(final String string) {
        String[] parts = string.tokenize(SEPARATOR)

        if (!parts || parts.size() != 2) {
            throw new IllegalArgumentException("Token invalidated");
        }

        Long id = parts[0].toLong();
        Long timeStamp = Long.valueOf(parts[1]);
        return new UserToken(id, timeStamp);
    }

    public String serialize() {
        return (id + SEPARATOR + timeStamp);
    }

    public boolean isValid(final Long timeToLive) {
        return (new Date().getTime() - timeStamp <= timeToLive);
    }

}
