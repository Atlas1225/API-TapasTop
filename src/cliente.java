import java.net.URI;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

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
		Response post = target.path("user").path("Carlos35").request().accept(MediaType.APPLICATION_JSON).get(Response.class);
			show(post);}
	public static void main(String args[]) {
		usuario();
	}
		
}
