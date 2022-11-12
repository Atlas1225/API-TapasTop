package test;
import java.io.StringReader;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.naming.NamingException;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.google.gson.Gson;

import javax.ws.rs.core.Context;
import java.util.Date;
import com.google.gson.Gson;

import interfaces.Coordinates;
import interfaces.Degustacion;
import interfaces.Local;

@Path("/user/{username}/tasting")
public class tasting {
	 @Context
	    private UriInfo uriInfo;
		
		@POST
		@Consumes(MediaType.APPLICATION_JSON)
		public Response calificarDegustacion(@PathParam("username") String username,JsonObject json) throws SQLException, NamingException, ParseException{
				DataBase db = new DataBase();
				Gson gson = new Gson();
				Degustacion de=gson.fromJson(json.toString(), Degustacion.class);
				String sql1="INSERT IGNORE INTO Local VALUES(?,?,?,?,?);";
				PreparedStatement ps1 = db.conn.prepareStatement(sql1);
				ps1.setInt(1,de.getLocal_pointer().getId());
				ps1.setString(2, de.getLocal_pointer().getCoordinates().getLat() + "|"+ de.getLocal_pointer().getCoordinates().getLng());
				ps1.setString(3,de.getLocal_pointer().getLocal_name());
				ps1.setString(4,de.getLocal_pointer().getLocal_address());
				ps1.setString(5,de.getLocal_pointer().getLocal_photo());
				ps1.executeUpdate();
				
				String sql2=" INSERT INTO Degustacion VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
            	PreparedStatement ps2 = db.conn.prepareStatement(sql2);
            	int id = (int)Math.floor( Math.random()*1200);
				ps2.setInt(1,id);
				ps2.setInt(2,de.getLocal_pointer().getId());
				ps2.setString(3, de.getAuthor());
				ps2.setString(4, de.getPhoto());
				ps2.setString(5,de.getOrigin());
				ps2.setString(6,de.getQualifier_taste());
				ps2.setString(7,de.getdish_name());
				ps2.setString(8,de.getType());
				ps2.setInt(9,de.getRating());
				ps2.setString(10,de.getDescription());
				java.sql.Date sqlDate = new java.sql.Date(new java.util.Date().getTime());
				ps2.setDate(11, sqlDate);
				int rs2 = ps2.executeUpdate();
				
				
				
				db.closeConn();
            	return Response.status(Response.Status.OK).entity("{tastingCreated:"+(rs2==1)+"}").build();

			
		}
}
