package vanity.portal

import org.apache.commons.lang.Validate
import org.springframework.beans.factory.annotation.Value
import vanity.celebrity.Celebrity

class ImageTagLib {

    static namespace = 'v'

    @Value('${files.celebrity.uri}')
    public String celebrityImageURI

    def celebrityImg = { attrs ->
        Celebrity celebrity = attrs.remove('bean')
        String cssClass = attrs.remove('class')
        Validate.notNull(celebrity)
        out << g.img(uri: (celebrityImageURI + celebrity.avatar.name), class: cssClass)
    }

}
