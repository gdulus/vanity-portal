package vanity.portal

import org.apache.commons.lang.Validate
import org.springframework.beans.factory.annotation.Value
import vanity.article.Article
import vanity.article.Tag

class TrackingTagLib {

    static namespace = 'tracking'

    @Value('${api.url}')
    public String apiURL

    @Value('${api.tracking.article}')
    public String articleTrackingURI

    @Value('${api.tracking.tag}')
    public String tagTrackingURI

    def article = { attrs ->
        Article article = attrs.remove('bean')
        Validate.notNull(article)
        init(articleTrackingURI, article.id)
    }

    def tag = { attrs ->
        Tag tag = attrs.remove('bean')
        Validate.notNull(article)
        init(tagTrackingURI, tag.id)
    }

    private void init(String uri, Long id) {
        String path = "${apiURL}/${uri}/${id}"
        out << g.javascript([:]) { "V.Tracking.init('${path}')" }
    }

}
