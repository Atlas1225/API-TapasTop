 package test;
  import java.io.StringReader;
  import java.sql.Blob;
  import java.sql.PreparedStatement;
  import java.sql.ResultSet;
  import java.sql.SQLException;
  import java.util.ArrayList;
  import java.util.List;

  import javax.json.Json;
  import javax.json.JsonObject;
  import javax.json.JsonReader;
  import javax.naming.NamingException;
  import javax.ws.rs.Consumes;
  import javax.ws.rs.DefaultValue;
  import javax.ws.rs.GET;
  import javax.ws.rs.PUT;
  import javax.ws.rs.Path;
  import javax.ws.rs.PathParam;
  import javax.ws.rs.Produces;
  import javax.ws.rs.QueryParam;
  import javax.ws.rs.core.MediaType;
  import javax.ws.rs.core.Response;
  import javax.ws.rs.core.UriInfo;
  import com.google.gson.reflect.TypeToken;
  import com.google.gson.Gson;

  import javax.ws.rs.core.Context;
  import interfaces.*;
  @Path("/local")
  public class local {
  	 @Context
  	 private UriInfo uriInfo;

  	  @GET
	  @Produces(MediaType.APPLICATION_JSON)
	  public Response findLocal(@QueryParam("pattern") @DefaultValue("") String pattern){
	 	try{
	  		DataBase db = new DataBase();
            String sql= "SELECT * FROM Local WHERE local_name LIKE ?;";
            PreparedStatement ps = db.conn.prepareStatement(sql);
            ps.setString(1, pattern+"%");
	  		ResultSet rs = ps.executeQuery();
	  		Local local;
	  		List<Local> locales= new ArrayList<>();
	  		while(rs.next()){
		       local=new Local();
		       local.setId(rs.getInt("id"));
		       local.setLocal_address(rs.getString("local_address"));
		       local.setLocal_name(rs.getString("local_name"));
		       local.setLocal_photo(rs.getString("local_photo"));
		       Coordinates coord= new Coordinates();
		       String cor= rs.getString("coordinates");
		       String [] split = cor.split("\\|");
		       coord.setLat(Float.parseFloat(split[0]));
		       coord.setLng(Float.parseFloat(split[1]));
		       local.setCoordinates(coord);
		      
		       String sql2 ="Select * from local  l\r\n"
						+ "inner join Degustacion d on d.id_local = l.id \r\n"
						+ "where d.username_usuario = ?\r\n"
						+ "Order by d.rating DESC;";
		       PreparedStatement ps1 = db.conn.prepareStatement(sql2);
		       ps1.setInt(1, local.getId());
		       ResultSet rs1 = ps1.executeQuery();
		       Degustacion degustacion;
		       while(rs1.next()) {
		    	   degustacion = new Degustacion();
		    	   Local local1= new Local();
		    	   Coordinates coordinates= new Coordinates();
		    	   String cord= rs1.getString("coordinates");
		    	   String [] result = cord.split("\\|");
		    	   Float lat= Float.parseFloat(result[0]);  
		    	   Float lng= Float.parseFloat(result[1]);  
		    	   coordinates.setLat(lat);
		    	   coordinates.setLng(lng);
		    	   local1.setId(rs1.getInt("id_local"));
		    	   local1.setLocal_address(rs1.getString("local_address"));
		    	   local1.setCoordinates(coordinates);
		    	   local1.setLocal_name(rs1.getString("local_name"));
		    	   local1.setLocal_photo(rs1.getString("local_photo"));
		    	   degustacion.setAuthor(rs1.getString("username_usuario"));
					degustacion.setLocal_pointer(local1);
					degustacion.setDate(rs1.getDate("date"));
					degustacion.setDescription(rs1.getString("description"));
					degustacion.setOrigin(rs1.getString("origin"));
					degustacion.setPhoto(rs1.getString("photo"));
					degustacion.setQualifier_taste(rs1.getString("qualifier_taste"));
					degustacion.setRating(rs1.getInt("rating"));
					degustacion.setdish_name(rs1.getString("dish_name"));
					degustacion.setType(rs1.getString("type"));
					local.getLocal_tastingList().add(degustacion);
		       }
		    	   
		       
		       locales.add(local);
        	}
        	
        	Gson gson = new Gson();
        	String result = gson.toJson(locales);

	  		return Response.status(Response.Status.OK).entity(result).build();
	  	}catch(Exception e){
	  		System.out.println(e);
	  		return Response.status(Response.Status.NOT_FOUND).entity("locales-no-encontrados").header("Content-Location", uriInfo.getAbsolutePath()).build();

	  	}

	  }
	  
  }
 
/*
	  		Gson gson = new Gson();
	  		Type type = new TypeToken<List<Local>>() {}.getType();
	  		String json = gson.toJson(locales,type);
			
	  		JsonReader response= Json.createReader(new StringReader(json));
	  		JsonObject object= response.readObject();*/