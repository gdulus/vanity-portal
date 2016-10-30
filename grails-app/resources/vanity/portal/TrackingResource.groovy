package vanity.portal

import grails.converters.JSON
import vanity.portal.utils.JSONUtils
import vanity.tracking.ClickService

import javax.ws.rs.FormParam
import javax.ws.rs.POST
import javax.ws.rs.Path
import javax.ws.rs.Produces

@Path('/api/tracking')
class TrackingResource {

    ClickService clickService

    @POST
    @Produces('application/json')
    @Path('tag')
    JSON tag(@FormParam('id') final Long id) {
        clickService.createForTag(id)
        return JSONUtils.EMPTY
    }

    @POST
    @Produces('application/json')
    @Path('article')
    JSON article(@FormParam('id') final Long id) {
        clickService.createForArticle(id)
        return JSONUtils.EMPTY
    }

}
