package ws;

import dtos.EmailDTO;
import dtos.UserDTO;
import ejbs.EmailBean;
import ejbs.MessageBean;
import ejbs.UsersBean;
import entities.Message;
import entities.User;
import exceptions.MyConstraintViolationException;
import exceptions.MyEntityNotFoundException;
import exceptions.MyNoRecipientException;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/users")
@Produces({MediaType.APPLICATION_JSON}) // injects header “Content-Type: application/json”
@Consumes({MediaType.APPLICATION_JSON}) // injects header “Accept: application/json”
public class UserService {
    @EJB
    private EmailBean emailBean;

    @EJB
    private MessageBean messageBean;

    @EJB
    private UsersBean usersBean;

    public static UserDTO toDTO(User user) {
        return new UserDTO(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                user.getEmail()
        );
    }

    @GET
    @Path("{username}")
    public Response getUserByID(@PathParam("username")String username){
        /*User user = usersBean.findByUsername(username);
        try{
            return Response.status(Response.Status.OK).entity(toDTO(user)).build();
        } catch (Exception e) {
            throw new EJBException("Erro ao encontrar projeto", e);
        }*/
        return Response.status(Response.Status.OK).build();
    }

    @POST
    @Path("email/send")
    public Response sendEmailToUser(EmailDTO email) throws MyNoRecipientException, MyConstraintViolationException, MyEntityNotFoundException {
        String recepientes = "";
        int counter = 0;
        Message message = messageBean.create(email.getSubject(), email.getMessage());
        for (UserDTO userDTO:email.getRecepientes()) {
            messageBean.addMessageToUser(message.getCode(), userDTO.getId());
            if(counter == 0){
                recepientes = recepientes + userDTO.getEmail();
            }
            else{
                recepientes = recepientes + ", " + userDTO.getEmail();
            }
            counter++;
        }
        emailBean.send(recepientes, email.getSubject(), email.getMessage());
        return Response.status(Response.Status.OK).entity("E-mail sent").build();
    }


}
