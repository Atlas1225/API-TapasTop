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
	public Response registrarUsuario(JsonObject json) throws NamingException, SQLException{
	
			DataBase db = new DataBase();


			boolean active = json.getBoolean("active");
			String email = json.getString("email");
			String username = json.getString("username");
			String password = json.getString("password");
			String gender = json.getString("gender");
			int age = json.getInt("age");
			String name = json.getString("name");
			String text_intro = json.getString("text_intro");
			String photo =json.getString("photo");
			String country = json.getString("country");
			String location = json.getString("location");
			
			String sql=" INSERT INTO Usuario VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
            PreparedStatement ps = db.conn.prepareStatement(sql);
            ps.setString(1,username);
            ps.setString(2,password);
			ps.setString(3,name);
			ps.setInt(4,age);
			ps.setString(5,text_intro);
			ps.setString(6,email);
			ps.setString(7,photo);
			ps.setString(8,location);
			ps.setString(9,country);
			ps.setString(10,gender);
			ps.setBoolean(11,active);
            int rs = ps.executeUpdate();

			db.closeConn();
            return Response.status(Response.Status.OK).entity(rs==1).build();
		
			
	}
}
