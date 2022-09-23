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

@Path("/test")
public class miRecurso {
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response sayPlainTextHello() throws NamingException, SQLException {
		DataBase db = new DataBase();
		String sql= "select id from prueba where id = ?;";
		PreparedStatement ps = db.conn.prepareStatement(sql);
		ps.setInt(1, 100);
		ResultSet rs = ps.executeQuery();
		rs.next();
		String res = rs.getString("id");
		db.closeConn();
		JsonReader response= Json.createReader(
				new StringReader("{\"id\":"+res+"}"));
		JsonObject object= response.readObject();
		response.close();
		return Response.status(Response.Status.OK).entity(object).build();
		
	}
}
