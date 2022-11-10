package test;

import java.io.ByteArrayOutputStream;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonWriter;

import interfaces.Usuario;

@Path("/user/{username}")

public class Username {
    @Context
    private UriInfo uriInfo;

    @GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response datosUsuario(@PathParam("username") String username) throws NamingException, SQLException{
        try{
        	DataBase db = new DataBase();
            String sql= "SELECT * FROM Usuario Where username = ?;";
            PreparedStatement ps = db.conn.prepareStatement(sql);
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			rs.next();			
//			Aquí ya tenemos el usuario
			Usuario user= new Usuario(rs.getString("gender"),rs.getString("email"),rs.getString("username"),
			rs.getString("password"),rs.getInt("age"));
			String sql2= "SELECT username_usuario2 FROM agrega where username_usuario1 = ?;";
			PreparedStatement ps2 = db.conn.prepareStatement(sql2);
			ps2.setString(1, username);
			ResultSet rs2 = ps2.executeQuery();
			ArrayList<Usuario> friends = new ArrayList<>();
			while(rs2.next()) {
				String user1="SELECT * FROM Usuario where username = ?;";
				PreparedStatement ps3 = db.conn.prepareStatement(user1);
				ps3.setString(1, rs2.getString("username_usuario2"));
				ResultSet rs3 = ps3.executeQuery();
				rs3.next();
				Usuario user2 = new Usuario(rs3.getString("gender"),rs3.getString("email"),rs3.getString("username"),
						rs3.getString("password"),rs3.getInt("age"));				
				friends.add(user2);
			}
			user.setFriends(friends);
			
			Gson gson = new Gson();
			String json= gson.toJson(user);

			System.out.println(json);
			
			
			db.closeConn();
			return Response.status(Response.Status.OK).entity(json).build();
			
            
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        	return Response.status(Response.Status.NOT_FOUND).entity("usuario no existente").header("Content-Location", uriInfo.getAbsolutePath()).build();
        }

       
    }
   
}

