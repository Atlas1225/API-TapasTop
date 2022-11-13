package test;

import java.io.ByteArrayOutputStream;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.sql.Blob;
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

import interfaces.Coordinates;
import interfaces.Degustacion;
import interfaces.Galardon;
import interfaces.Local;
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
			user.setText_intro(rs.getString("text_intro"));
			user.setPhoto(rs.getString("photo"));
			user.setCountry(rs.getString("country"));
			user.setLocation(rs.getString("location"));
			user.setName(rs.getString("name"));
			String sql2= "SELECT username_usuario2 FROM agrega where username_usuario1 = ?;";
			PreparedStatement ps2 = db.conn.prepareStatement(sql2);
			ps2.setString(1, username);
			ResultSet rs2 = ps2.executeQuery();
//			Array de amigos
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
//			Array de tasting_list(degustaciones)
			String sql3="Select * from local  l\r\n"
					+ "inner join Degustacion d on d.id_local = l.id \r\n"
					+ "where d.username_usuario = ?\r\n"
					+ "Order by d.rating DESC;";
			PreparedStatement ps3 = db.conn.prepareStatement(sql3);
			ps3.setString(1, username);
			ResultSet rs3 = ps3.executeQuery();
			ArrayList<Degustacion> tasting_list = new ArrayList<>();
			ArrayList<Degustacion> fav_foods= new ArrayList<Degustacion>();
			int contador=0;
			while(rs3.next()) {
				Degustacion degustacion= new Degustacion();
				Local local1= new Local();
				Coordinates coordinates= new Coordinates();
				String cord= rs3.getString("coordinates");
				String [] result = cord.split("\\|");
				Float lat= Float.parseFloat(result[0]);  
				Float lng= Float.parseFloat(result[1]);  
				coordinates.setLat(lat);
				coordinates.setLng(lng);
				local1.setId(rs3.getInt("id_local"));
				local1.setLocal_address(rs3.getString("local_address"));
				local1.setCoordinates(coordinates);
				local1.setLocal_name(rs3.getString("local_name"));
				local1.setLocal_photo(rs3.getString("local_photo"));
				degustacion.setAuthor(rs3.getString("username_usuario"));
				degustacion.setLocal_pointer(local1);
				degustacion.setDate(rs3.getDate("date"));
				degustacion.setDescription(rs3.getString("description"));
				degustacion.setOrigin(rs3.getString("origin"));
				degustacion.setPhoto(rs3.getString("photo"));
				degustacion.setQualifier_taste(rs3.getString("qualifier_taste"));
				degustacion.setRating(rs3.getInt("rating"));
				degustacion.setdish_name(rs3.getString("dish_name"));
				degustacion.setType(rs3.getString("type"));
				tasting_list.add(degustacion);
				if(contador<3) {
					fav_foods.add(degustacion);
				}
				contador++;
			}
			user.setTasting_list(tasting_list);
			user.setFav_foods(fav_foods);
//			Array de award_list(galardones)
			String sql4= "select * From obtiene where username_usuario= ?;";
			PreparedStatement ps4 = db.conn.prepareStatement(sql4);
			ps4.setString(1, username);
			ResultSet rs4 = ps4.executeQuery();
			ArrayList<Galardon> award_list = new ArrayList<>();
			while(rs4.next()) {
				String galar="select * from galardon where id=?;";
				PreparedStatement ps5 = db.conn.prepareStatement(galar);
				ps5.setString(1, rs4.getString("id_galardon"));
				ResultSet rs5 = ps5.executeQuery();	
				rs5.next();
				Galardon galardon= new Galardon();
				galardon.setId(rs5.getString("id"));
				galardon.setLevel(rs5.getInt("level"));
				galardon.setPhoto(rs5.getString("image"));
				award_list.add(galardon);
				
			}
			user.setAward_list(award_list);
		
			
			
			Gson gson = new Gson();
			String json= gson.toJson(user);
			
			
			db.closeConn();
			return Response.status(Response.Status.OK).entity(json).build();
			
            
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        	return Response.status(Response.Status.NOT_FOUND).entity("usuario no existente").header("Content-Location", uriInfo.getAbsolutePath()).build();
        }
    }

              
        @PUT
      	@Consumes(MediaType.APPLICATION_JSON)
      	public Response modificarUser(@PathParam("username") String nombre , JsonObject json) throws SQLException, NamingException{
       		DataBase db = new DataBase();
       	
     		String username = json.getString("username");
     		String name = json.getString("name");
     		String password = json.getString("password");
     		String email = json.getString("email");
     		String text_intro = json.getString("text_intro");
     		String image = json.getString("photo");
     		String country = json.getString("country");
     		
     		String sql =" UPDATE Usuario SET username= ? , password = ?, name = ?, email = ?, text_intro = ?, photo = ?, country = ? WHERE username = ?;";
     		PreparedStatement ps = db.conn.prepareStatement(sql);
     		ps.setString(1, username);
     		ps.setString(2, password);
     		ps.setString(3, name);
     		ps.setString(4, email);
     		ps.setString(5, text_intro);
     		ps.setString(6, image);
     		ps.setString(7, country);
     		ps.setString(8,nombre);
     		int rs = ps.executeUpdate();
     		
      		return Response.status(Response.Status.OK).entity("{\"modified\":"+(rs==1)+"}").build();
      	}
      
    
   
}

