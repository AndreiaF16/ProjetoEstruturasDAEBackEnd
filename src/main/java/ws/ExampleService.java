package ws;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("test")
public class ExampleService {

    @GET
    public Response teste(){
        return Response.ok().build();
    }
}
