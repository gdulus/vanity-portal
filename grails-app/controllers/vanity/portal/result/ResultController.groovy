package vanity.portal.result

import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import vanity.article.Article

@Slf4j
class ResultController {

    def resultService

    @Value('${grails.serverURL}')
    def serverURL

    def showById(final Long id) {
        Article article = resultService.articleService.read(id)

        if (!article) {
            response.sendError(HttpStatus.NOT_FOUND.value())
            return
        }

        String url = createLink(action: 'showPreview', absolute: true, params: [id: id, title: article.title.encodeAsPrettyUrl()])
        log.info('Redirecting request by id {} to full url {}', id, url)
        redirect(url: url, permanent: true)
    }

    def showPreview(final Long id) {
        def model = resultService.buildShowPreview(id, "${serverURL}/${request.forwardURI}")

        if (!model) {
            response.sendError(HttpStatus.NOT_FOUND.value())
            return
        }

        return [viewModel: model]
    }

    def showArticle(final Long id) {
        def model = resultService.buildShowArticleModel(id)

        if (!model) {
            response.sendError(HttpStatus.NOT_FOUND.value())
            return
        }

        return [viewModel: model]
    }

}
