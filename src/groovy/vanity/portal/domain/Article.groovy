package vanity.portal.domain

import org.apache.commons.lang.StringUtils

class Article {

    private static final int PREVIEW_MAX_LENGTH = 500

    public Long id

    public String title

    public String body

    public String sourceName

    public String url

    public Long publicationDate

    public List<Tag> rootTags

    String getShortBody(final int max = PREVIEW_MAX_LENGTH) {
        return StringUtils.abbreviate(body, max)
    }

    Date getPublicationDate() {
        return new Date(this.@publicationDate)
    }

}
