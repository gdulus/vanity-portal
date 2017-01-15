package vanity.portal.result

import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import vanity.article.Article
import vanity.portal.AbstractController

@Slf4j
class ResultController extends AbstractController {

    def resultService

    @Value('${grails.serverURL}')
    def serverURL

    def showById(final Long id) {
        Article article = resultService.articleService.read(id)
        log.info('Requested article by id', id)

        if (!article) {
            response.sendError(HttpStatus.NOT_FOUND.value())
            return
        }

        String url = createLink(action: 'showPreview', absolute: true, params: [id: id, title: article.title.encodeAsPrettyUrl()])
        log.info('Redirecting request by id {} to full url {} (serverURL = {})', id, url, serverURL)
        redirect(url: url)
    }

    def showPreview(final Long id) {
        def model = resultService.buildShowPreview(id, "${serverURL}${request.forwardURI}")
        return getModelOrNotFound(model, response)
    }

}
