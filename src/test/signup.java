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

@Path("/signup")
public class signup {
	@Context
	private UriInfo uriInfo;

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response registrarUsuario(JsonObject json){
		try{
			DataBase db = new DataBase();


			boolean active = rs.getBoolean("active");//DUDA
			String email = rs.getString("email");
			String username = rs.getString("username");
			String password = rs.getString("password");
			String gender = rs.getString("gender");
			int age = rs.getInt("age");
			String name = rs.getString("name");
			String surname = rs.getString("surname");
			String text_intro = rs.getString("text_intro");
			Blob photo = rs.getBlob("photo");
			String country = rs.getString("country");
			String location = rs.getString("location");
			
			String sql=" INSERT INTO Usuario VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
            PreparedStatement ps = db.conn.prepareStatement(sql);
            ps.setString(1,username);
            ps.setString(2,password);
			ps.setString(3,name);
			ps.setString(4,surname);
			ps.setInt(5,age);
			ps.setString(6,text_intro);
			ps.setString(7,email);
			ps.setBlob(8,photo);
			ps.setString(9,location);
			ps.setString(10,country);
			ps.setString(11,gender);
			ps.setBoolean(12,active);
            ResultSet rs = ps.executeQuery();

			Login login = new Login();//cambiar el nombre a la clase al igual pero tiene los mismo parametros
			login.setUsername(username);
			login.setLogged(rs.next());//duda

			Gson gson = new Gson();
            String json = gson.toJson(login);

			db.closeConn();
            return Response.status(Response.Status.OK).entity(json).build();

		}catch(Exception e){
			return Response.status(Response.Status.NOT_FOUND).entity("no es posible registrar").header("Content-Location", uriInfo.getAbsolutePath()).build();
		}
	}
}
