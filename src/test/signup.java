package test;
import java.io.StringReader;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.naming.NamingException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.google.gson.Gson;

import interfaces.Login;

import javax.ws.rs.core.Context;

@Path("/signup")
public class signup {
	@Context
	private UriInfo uriInfo;

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response registrarUsuario(JsonObject json){
		try{
			DataBase db = new DataBase();


			boolean active = json.getBoolean("active");//DUDA
			String email = json.getString("email");
			String username = json.getString("username");
			String password = json.getString("password");
			String gender = json.getString("gender");
			int age = json.getInt("age");
			String name = json.getString("name");
			String surname = json.getString("surname");
			String text_intro = json.getString("text_intro");
			String photo =json.getString("photo");//cambio de Blob a String
			String country = json.getString("country");
			String location = json.getString("location");
			
			String sql=" INSERT INTO Usuario VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
            PreparedStatement ps = db.conn.prepareStatement(sql);
            ps.setString(1,username);
            ps.setString(2,password);
			ps.setString(3,name);
			ps.setString(4,surname);
			ps.setInt(5,age);
			ps.setString(6,text_intro);
			ps.setString(7,email);
			ps.setString(8,photo);
			ps.setString(9,location);
			ps.setString(10,country);
			ps.setString(11,gender);
			ps.setBoolean(12,active);
            ResultSet rs = ps.executeQuery();

			Login login = new Login();//cambiar el nombre a la clase al igual pero tiene los mismo parametros
			login.setUsername(username);
			//comprobacion de que esta dentro
			String sql2="select username from Usuario where email = ?;";
			PreparedStatement ps2 = db.conn.prepareStatement(sql2);
			ps2.setString(1,email);
			ResultSet rs2 = ps2.executeQuery();
			login.setLogged(rs2.next());//duda

			Gson gson = new Gson();
            String json1 = gson.toJson(login);

			db.closeConn();
            return Response.status(Response.Status.OK).entity(json1).build();

		}catch(Exception e){
			return Response.status(Response.Status.NOT_FOUND).entity("no es posible registrar").header("Content-Location", uriInfo.getAbsolutePath()).build();
		}
	}
}
