package test;

import java.io.StringReader;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.naming.NamingException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Path("/user/{username}")

public class Username {
    @Context
    private UriInfo uriInfo;

    @GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response datosUsuario(@PathParam("username") String username) throws NamingException, SQLException{
        try{
            DataBase db = new DataBase();
            String res="{";
            String sql= "";
            PreparedStatement ps = db.conn.prepareStatement(sql);
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();

            if(rs.next()){ //16 atributos (4listas, 
                    

        }
		return null;
    }
   
}
