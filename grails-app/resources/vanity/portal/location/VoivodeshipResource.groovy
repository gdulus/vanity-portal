package vanity.portal.location

import grails.converters.JSON
import groovy.util.logging.Slf4j
import vanity.location.VoivodeshipService
import vanity.portal.rest.AbstractResource
import vanity.portal.rest.RestConst

import javax.ws.rs.GET
import javax.ws.rs.HeaderParam
import javax.ws.rs.Path
import javax.ws.rs.core.Response

@Slf4j
@Path('/api/voivodeship')
class VoivodeshipResource extends AbstractResource {

    private static final Long PL_COUNTRY_ID = 1851073

    VoivodeshipService voivodeshipService

    @GET
    public Response list(@HeaderParam(RestConst.X_AUTH_TOKEN) String token) {
        def result = voivodeshipService.findByCountry(PL_COUNTRY_ID).collect { [id: it.id, nane: it.name] }
        return Response.ok(result as JSON).build()
    }

}
