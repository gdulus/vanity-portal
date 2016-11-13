package vanity.portal

import org.apache.commons.lang.Validate
import vanity.celebrity.CelebrityImage
import vanity.media.ImageService

class ImageTagLib {

    static namespace = 'image'

    ImageService imageService

    def celebrity = { attrs, body ->
        CelebrityImage image = attrs.remove('src')
        Integer size = attrs.remove('size').toString().toInteger()
        String cssClass = attrs.remove('class')
        Validate.notNull(image)
        Validate.notNull(size)

        cssClass = cssClass ? """ class="${cssClass}" """ : null
        String path = imageService.getPath(image, size)
        out << """<img src="${path}" title="${image.celebrity.fullName}" alt="${image.celebrity.fullName}" ${cssClass}/>"""
    }

}
