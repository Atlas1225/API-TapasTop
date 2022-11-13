import java.net.URI;
import java.sql.Blob;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.client.Entity;

import org.glassfish.jersey.client.ClientConfig;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonWriter;

public class cliente {
	static ClientConfig config = new ClientConfig();
	static Client client = ClientBuilder.newClient(config);
	static WebTarget target = client.target(getBaseURI());
	static URI getBaseURI() {return UriBuilder.fromUri("http://localhost:8080/TapasTop/rest/").build();};
	public static void show(Response post){
		System.out.println(post.getStatus()+ " " + post.getStatusInfo());
	    System.out.println(post.getHeaders());
	    System.out.println(post.readEntity(String.class));}
	
	
	public static void usuario(String user){
		Response post = target.path("user").path(user).request().accept(MediaType.APPLICATION_JSON).get(Response.class);
			show(post);
	}
	public static void  login(String username, String password) {
		String query = "{\"username\": \""+username+"\", \n \"password\":\""+password+"\"\n }";
		Response post= target.path("login").request().accept(MediaType.APPLICATION_JSON).post(Entity.json(query));
		show(post);
	}
	public static void  signup(boolean active, String email, String username, String password, String gender, int age, String name, String text_intro, String photo,
			String country,String location) {
		String query = "{\"active\": "+active+", \n \"email\":\""+email+"\" , \n \"username\":\""+username+"\"  , \n \"password\":\""+password+"\"  ,"
				+ " \n \"gender\":\""+gender+"\" , \n \"age\":"+age+"  , \n \"name\":\""+name+"\"  , \n \"text_intro\":\""+text_intro+"\" , \n \"photo\":\""+photo+"\"  , \n \"country\":\""+country+"\" , \n \"location\":\""+location+"\" \n }";
		Response post= target.path("signup").request().accept(MediaType.APPLICATION_JSON).post(Entity.json(query));
		show(post);
	}
	public static void  calificar(String username,String deg ) {
		String query = deg;
		Response post= target.path("user").path(username).path("tasting").request().accept(MediaType.APPLICATION_JSON).post(Entity.json(query));
		show(post);
	}
	public static void buscarLocal(String pattern) {
		Response post = target.path("local").queryParam("pattern", pattern).request().accept(MediaType.APPLICATION_JSON).get(Response.class);
		show(post);
	}
	public static void modificar(String nombre, String query) {
        Response post = target.path("user").path(nombre).request().accept(MediaType.APPLICATION_JSON).put(Entity.json(query));
        show(post);}
	
	
	public static void main(String args[]) {
//		usuario("Carlos35");
//		String json="{\r\n"
//				+ "  \"author\": \"Manolito23\",\r\n"
//				+ "  \"qualifier_taste\": \"dulce\",\r\n"
//				+ "  \"rating\": 3,\r\n"
//				+ "  \"photo\": \"\",\r\n"
//				+ "  \"date\": \"2020-12-12\",\r\n"
//				+ "  \"description\": \"Strfsad\",\r\n"
//				+ "  \"local_pointer\": {\r\n"
//				+ "    \"id\": 15,\r\n"
//				+ "    \"local_address\": \"Calle de puta Nº35\",\r\n"
//				+ "    \"local_name\": \"djnjhbfeahfkvsj\",\r\n"
//				+ "    \"local_photo\": \"\",\r\n"
//				+ "    \"local_tastingList\": [],\r\n"
//				+ "    \"coordinates\": {\r\n"
//				+ "      \"lat\": 20.0,\r\n"
//				+ "      \"lng\": 3.5\r\n"
//				+ "    }\r\n"
//				+ "  },\r\n"
//				+ "  \"origin\": \"Sda\",\r\n"
//				+ "  \"dish_name\": \"patata\",\r\n"
//				+ "  \"type\": \"carne\"\r\n"
//				+ "}";
//		calificar("Manolito23",json);
//		login("Manolito23","locuelo01");
//		Blob photo= null;
		signup(false, "t1@faker.com", "Altami", "12345", "male", 21, "Altami", "text", "", "Portugal", "Centro, Madrid");
//		login("Manolito23", "locuelo01");
//		signup(true,"hola@w","hola","truski","male",20,"hola","adios","asdfghjk",photo,"adf","adsfa");
//		buscarLocal("Won");
//		String query= "{\"username\": \"Carlos35\", \"name\": \"Carlos\","
//				+ " \"password\": \"pepito\", \"email\": \"Carlos@gmail.com\", \"text_intro\": \"text\", \"photo\": \"https://img.a.transfermarkt.technology/portrait/big/329145-1568022060.jpg\", \"country\": \"España\"				";
//		modificar("Carlos35",query);
  }
		
}
