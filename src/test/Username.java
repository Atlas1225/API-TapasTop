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

import interfaces.Degustacion;
import interfaces.Galardon;
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
//			Array de amigos
			ArrayList<String> friends = new ArrayList<>();
			while(rs2.next()) {
				String user1="SELECT * FROM Usuario where username = ?;";
				PreparedStatement ps3 = db.conn.prepareStatement(user1);
				ps3.setString(1, rs2.getString("username_usuario2"));
				ResultSet rs3 = ps3.executeQuery();
				rs3.next();
//				Usuario user2 = new Usuario(rs3.getString("gender"),rs3.getString("email"),rs3.getString("username"),
//						rs3.getString("password"),rs3.getInt("age"));
				String username1= rs3.getString("username");
				friends.add(username1);
			}			
			user.setFriends(friends);
//			Array de tasting_list(degustaciones)
			String sql3="select * FROM Degustacion WHERE username_usuario= ?;";
			PreparedStatement ps3 = db.conn.prepareStatement(sql3);
			ps3.setString(1, username);
			ResultSet rs3 = ps3.executeQuery();
			ArrayList<Degustacion> tasting_list = new ArrayList<>();
			while(rs3.next()) {
				Degustacion degustacion= new Degustacion();
				degustacion.setAuthor(rs3.getString("username_usuario"));
				degustacion.setLocal_pointer(rs3.getInt("id_local"));
				degustacion.setDate(rs3.getDate("date"));
				degustacion.setDescription(rs3.getString("description"));
				degustacion.setOrigin(rs3.getString("origin"));
				degustacion.setPhoto(rs3.getBlob("photo"));
				degustacion.setQualifier_taste(rs3.getString("qualifier_taste"));
				degustacion.setRating(rs3.getInt("rating"));
				tasting_list.add(degustacion);
			}
			user.setTasting_list(tasting_list);
			
//			Array de award_list(galardones)
			String sql4= "select * From obtiene where username_usuario= ?;";
			PreparedStatement ps4 = db.conn.prepareStatement(sql4);
			ps4.setString(1, username);
			ResultSet rs4 = ps4.executeQuery();
			ArrayList<Galardon> award_list = new ArrayList<>();
			while(rs4.next()) {
				String galar="select * from galardon where id=?;";
				PreparedStatement ps5 = db.conn.prepareStatement(galar);
				ps5.setInt(1, rs4.getInt("id_galardon"));
				ResultSet rs5 = ps5.executeQuery();	
				rs5.next();
				Galardon galardon= new Galardon();
				galardon.setId(rs5.getInt("id"));
				galardon.setLevel(rs5.getInt("level"));
				galardon.setPhoto(rs5.getBlob("image"));
				award_list.add(galardon);
				
			}
			user.setAward_list(award_list);
			
			
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

