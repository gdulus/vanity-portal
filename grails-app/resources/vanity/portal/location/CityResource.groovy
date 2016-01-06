package vanity.portal.location

import grails.converters.JSON
import groovy.util.logging.Slf4j
import vanity.location.CityService
import vanity.portal.rest.AbstractResource

import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.QueryParam
import javax.ws.rs.core.Response

@Slf4j
@Path('/api/city')
class CityResource extends AbstractResource {

    CityService cityService

    @GET
    public Response list(@QueryParam('voivodeshipId') final Long voivodeshipId) {
        def result = cityService.findByVoivodeship(voivodeshipId).collect { [id: it.id, name: it.name] }
        return Response.ok(result as JSON).build()
    }

}
