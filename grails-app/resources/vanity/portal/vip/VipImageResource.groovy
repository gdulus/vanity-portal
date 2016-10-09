package vanity.portal.vip

import groovy.util.logging.Slf4j
import vanity.portal.rest.AbstractResource
import vanity.portal.rest.RestConst

import javax.ws.rs.HeaderParam
import javax.ws.rs.PUT
import javax.ws.rs.Path
import javax.ws.rs.QueryParam
import javax.ws.rs.core.Response

@Slf4j
@Path('/api/vip/image')
class VipImageResource extends AbstractResource {

    @PUT
    public Response put(@HeaderParam(RestConst.X_AUTH_TOKEN) String token, @QueryParam('id') final Long id) {
        $(token) {

        }
    }

}