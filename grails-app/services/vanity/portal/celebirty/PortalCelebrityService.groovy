package vanity.portal.celebirty

import grails.transaction.Transactional
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.multipart.MultipartFile
import vanity.celebrity.Celebrity
import vanity.celebrity.CelebrityImage
import vanity.celebrity.CelebrityImageStatus
import vanity.celebrity.CelebrityService
import vanity.media.ImageService
import vanity.portal.user.UserActivityService
import vanity.portal.utils.ValidationUtils
import vanity.portal.validation.exceptions.CustomValidationException
import vanity.user.User
import vanity.user.UserActivityType

@Slf4j
class PortalCelebrityService {

    @Autowired
    ImageService imageService

    @Autowired
    UserActivityService userActivityService

    @Autowired
    CelebrityService celebrityService

    @Transactional
    public CelebrityImage saveImage(final Long userId, final Long celebrityId, final MultipartFile image) {
        ValidationUtils.notNull(image, 'image', CelebrityImage)
        ValidationUtils.notNull(celebrityId, 'vipId', CelebrityImage)

        User user = User.read(userId)
        Celebrity celebrity = celebrityService.findByTag(celebrityId)

        ValidationUtils.notNull(user, 'user', CelebrityImage)
        ValidationUtils.notNull(celebrity, 'celebrity', CelebrityImage)

        CelebrityImage celebrityImage = new CelebrityImage()
        celebrityImage.fileName = storeImage(celebrity, image)
        celebrityImage.author = user
        celebrityImage.celebrity = celebrity
        celebrityImage.state = CelebrityImageStatus.UPLOADED
        celebrityImage.save()
        userActivityService.create(user, UserActivityType.CELEBRITY_IMAGE_UPLOADED)
        return celebrityImage
    }

    private String storeImage(final Celebrity celebrity, final MultipartFile image) {
        try {
            return imageService.store(celebrity, image)
        } catch (Throwable exception) {
            log.warn('There was an error while saving image: {}', exception)
            throw new CustomValidationException(CelebrityImage, [image: 'invalid'])
        }
    }
}
