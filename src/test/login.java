package test;
import java.io.StringReader;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.naming.NamingException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Context;
import interfaces.Login;
@Path("/login")
public class login {
    @Context
    private UriInfo uriInfo;    

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    //duda de si se pasa JsonObject o QueryParam
    public Response loginUser(JsonObject json){
        try{
            DataBase db = new DataBase();

            String username = json.getString("username");
            String password = json.getString("password");

            String sql=" Select username FROM Usuario where username = ? and password = ?;";
            PreparedStatement ps = db.conn.prepareStatement(sql);
            ps.setString(1,username);
            ps.setString(2,password);
            ResultSet rs = ps.executeQuery();

            Login login = new Login();
            login.setUsername(username);
            login.setLogged(rs.next());

            Gson gson = new Gson();
            String json = gson.toJson(login);

            db.closeConn();
            return Response.status(Response.Status.OK).entity(json).build();

        }catch(Exception e){
            return Response.status(Response.Status.NOT_FOUND).entity("usuario-no-encontrado").header("Content-Location", uriInfo.getAbsolutePath()).build();
        }

        
    }


}
