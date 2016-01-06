package vanity.portal.location

import grails.converters.JSON
import groovy.util.logging.Slf4j
import vanity.location.VoivodeshipService
import vanity.portal.rest.AbstractResource

import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.QueryParam
import javax.ws.rs.core.Response

@Slf4j
@Path('/api/voivodeship')
class VoivodeshipResource extends AbstractResource {

    VoivodeshipService voivodeshipService

    @GET
    public Response list(@QueryParam('countryId') final Long countryId) {
        def result = voivodeshipService.findByCountry(countryId).collect { [id: it.id, name: it.name] }
        return Response.ok(result as JSON).build()
    }

}
