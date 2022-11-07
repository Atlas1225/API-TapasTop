// package test;
// import java.io.StringReader;
// import java.sql.Blob;
// import java.sql.PreparedStatement;
// import java.sql.ResultSet;
// import java.sql.SQLException;
// import java.util.ArrayList;
// import java.util.List;

// import javax.json.Json;
// import javax.json.JsonObject;
// import javax.json.JsonReader;
// import javax.naming.NamingException;
// import javax.ws.rs.Consumes;
// import javax.ws.rs.DefaultValue;
// import javax.ws.rs.GET;
// import javax.ws.rs.PUT;
// import javax.ws.rs.Path;
// import javax.ws.rs.PathParam;
// import javax.ws.rs.Produces;
// import javax.ws.rs.QueryParam;
// import javax.ws.rs.core.MediaType;
// import javax.ws.rs.core.Response;
// import javax.ws.rs.core.UriInfo;
// import com.google.gson.reflect.TypeToken;
// import com.google.gson.Gson;

// import javax.ws.rs.core.Context;
// import interfaces.*;
// @Path("/local")
// public class local {
// 	 @Context
// 	 private UriInfo uriInfo;

// 	@GET
// 	@Produces(MediaType.APPLICATION_JSON)
// 		public Response listarLocales(){
// 			try{
// 	            DataBase db = new DataBase();
// 	            String sql= "select * from local;";
// 	            PreparedStatement ps = db.conn.prepareStatement(sql);
// 				ResultSet rs = ps.executeQuery();
// 				int i = 1;
// 				List<Local>locales= new ArrayList<>();
// 				while(rs.next()){
// 					//ponerlo en json y juntarlos todos en uno
// 					Local local= new Local();
// 					local.setId(rs.getString("id"));
// 					local.setLocal_address(rs.getString("locccal_address"));
// 					local.setLocal_name(rs.getString("local_name"));
// 					local.setLocal_photo(rs.getBlob("local_photo"));

// 					//no estamos seguros de que este bien
// 					Object coordinate = rs.getObject(i);
// 					Coordinates coor = (Coordinates) coordinate;
// 					local.setCoordinates(coor);
// 					//dudas aqui
// 					ArrayList<Comida> list_food = (ArrayList<Comida>) rs.getObject(ArrayList.class);
// 					local.setLocal_foodList(list_food);
// 					ArrayList<Bebida> list_drink = (ArrayList<Bebida>) rs.getObject(ArrayList.class);
// 					local.setLocal_drinkList(list_drink);

// 					locales.add(local);
					
// 					i++;
// 				}
// 				Gson gson = new Gson();
// 				Type type = new TypeToken<List<Local>>() {}.getType();
// 				String json = gson.toJson(locales,type);
				
// 				JsonReader response= Json.createReader(new StringReader(json));
// 				JsonObject object= response.readObject();


// 				return Response.status(Response.Status.OK).entity("hola").header("Content-Location", uriInfo.getAbsolutePath()).build();
// 			}catch(Exception e){
// 				return Response.status(Response.Status.NOT_FOUND).entity("locales-no-encontrados").header("Content-Location", uriInfo.getAbsolutePath()).build();
// 			}

// 		}
	
// 	// @GET
	// @Produces(MediaType.APPLICATION_JSON)
	// public Response findLocal(@QueryParam("pattern") @DefaultValue("") String pattern){
	// 	try{
	// 		DataBase db = new DataBase();
    //         String sql= "SELECT * FROM Local WHERE local_name LIKE ?%;";
    //         PreparedStatement ps = db.conn.prepareStatement(sql);
	// 		if(!pattern.equals(""))ps.setString(1,pattern);
	// 		ResultSet rs = ps.executeQuery();

	// 		int i = 1;
	// 		List<Local>locales= new ArrayList<>();
	// 		while(rs.next()){
	// 			//ponerlo en json y juntarlos todos en uno
	// 			Local local= new Local();
	// 			local.setId(rs.getString("id"));
	// 			local.setLocal_address(rs.getString("local_address"));
	// 			local.setLocal_name(rs.getString("local_name"));
	// 			local.setLocal_photo(rs.getBlob("local_photo"));

	// 			//no estamos seguros de que este bien
	// 			Object coordinate = rs.getObject(i);
	// 			Coordinates coor = (Coordinates) coordinate;
	// 			local.setCoordinates(coor);

	// 			//dudas aqui
	// 			ArrayList<Comida> list_food = (ArrayList<Comida>) rs.getObject(ArrayList.class)
	// 			local.setLocal_foodList(list_food);
	// 			ArrayList<Bebida> list_drink = (ArrayList<Bebida>) rs.getObject(ArrayList.class)
	// 			local.setLocal_drinkList(list_drink);

	// 			locales.add(local);
				
	// 			i++;
	// 		}
	// 		Gson gson = new Gson();
	// 		Type type = new TypeToken<List<Local>>() {}.getType();
	// 		String json = gson.toJson(locales,type);
			
	// 		JsonReader response= Json.createReader(new StringReader(json));
	// 		JsonObject object= response.readObject();


	// 		return Response.status(Response.Status.OK).entity(object).header("Content-Location", uriInfo.getAbsolutePath()).build();
	// 	}catch(Exception e){
	// 		return Response.status(Response.Status.NOT_FOUND).entity("locales-no-encontrados").header("Content-Location", uriInfo.getAbsolutePath()).build();

	// 	}

	// }
	// @PUT
// 	@Consumes(MediaType.APPLICATION_JSON)
// 	public Response modificarUser(@PathParam("username") String nombre , JsonObject json){
// //		DataBase db = new DataBase();

		
//		String username = json.getString("username");
//		String name = json.getString("name");
//		String password = json.getString("password");
//		String email = json.getString("email");
//		String text_intro = json.getString("text_intro");
//		Blob image = json.getBlob("photo");
//		String country = json.getString("country");
//		Coordinates coord = json.getCoordinates("coordinates");//esto esta mal seguro
//		
//		String sql =" UPDATE Usuario SET username= ? , password = ?, name = ?, email = ?, text_intro = ?, image = ?, country = ?, coordinates = ? WHERE username = ?;";
//		PreparedStatement ps = db.conn.prepareStatement(sql);
//		ps = db.conn.prepareStatement(sql);
//
//		ps.setString(1, username);
//		ps.setString(2, password);
//		ps.setString(3, name);
//		ps.setString(4, email);

// 		return null;
// 	}
// }
// // }
