package exception;

import org.json.simple.JSONObject;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by smehta on 7/30/14.
 */
public class ItemNotFoundException extends WebApplicationException {

    private static final String message = "Item not found for a given id";

    public ItemNotFoundException() {
        super(Response.status(400).entity(message).type(MediaType.TEXT_PLAIN_TYPE).build());
    }

}
