package vanity.portal

import grails.converters.JSON
import vanity.portal.utils.JSONUtils
import vanity.tracking.ClickService

import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.QueryParam

@Path('/api/tracking')
class TrackingResource {

    ClickService clickService

    @GET
    @Produces('application/json')
    @Path('tag')
    JSON tag(@QueryParam('id') final Long id) {
        clickService.createForTag(id)
        return JSONUtils.EMPTY
    }

    @GET
    @Produces('application/json')
    @Path('article')
    JSON article(@QueryParam('id') final Long id) {
        clickService.createForArticle(id)
        return JSONUtils.EMPTY
    }

}
