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
	
	
	public static void usuario(){
		Response post = target.path("user").path("Manolito23").request().accept(MediaType.APPLICATION_JSON).get(Response.class);
			show(post);
	}
	public static void  login(String username, String password) {
		String query = "{\"username\": \""+username+"\", \n \"password\":\""+password+"\"\n }";
		Response post= target.path("login").request().accept(MediaType.APPLICATION_JSON).post(Entity.json(query));
		show(post);
	}
	public static void  signup(boolean active, String email, String username, String password, String gender, int age, String name, String surname, String text_intro,Blob photo,
			String country,String location) {
		String query = "{\"active\": \""+active+"\", \n \"email\":\""+email+"\" , \n \"username\":\""+username+"\"  , \n \"password\":\""+password+"\"  ,"
				+ " \n \"gender\":\""+gender+"\" , \n \"age\":\""+age+"\"  , \n \"name\":\""+name+"\" , \n \"surname\":\""+surname+"\"  , \n \"text_intro\":\""+text_intro+"\" , \n \"photo\":\""+photo+"\"  , \n \"country\":\""+country+"\" , \n \"location\":\""+location+"\" \n }";
		Response post= target.path("login").request().accept(MediaType.APPLICATION_JSON).post(Entity.json(query));
		show(post);
	}
	
	public static void main(String args[]) {
		usuario();
//		login("Manolito23","locuelo01");
//		Blob photo= null;
//		//signup(true,"hola@w","hola","truski","male",20,"hola","adios","asdfghjk",photo,"adf","adsfa");
//		login("Manolito23", "locuelo01");
//		signup(true,"hola@w","hola","truski","male",20,"hola","adios","asdfghjk",photo,"adf","adsfa");
		
	}
		
}
