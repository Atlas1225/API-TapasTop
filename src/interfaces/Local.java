package interfaces;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.sql.Blob;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonWriter;


public class Local {
    private String id;
    private String local_address;
    private String local_name;
    private Blob local_photo;
    private ArrayList<Comida> local_foodList=new ArrayList<Comida>();
    private ArrayList<Bebida> local_drinkList = new ArrayList<Bebida>();
    private Coordinates coordinates;

    public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLocal_address() {
		return this.local_address;
	}

	public void setLocal_address(String local_adress) {
		this.local_address = local_adress;
	}

	public String getLocal_name() {
		return this.local_name;
	}

	public void setLocal_name(String local_name) {
		this.local_name = local_name;
	}

	public Blob getLocal_photo() {
		return this.local_photo;
	}

	public void setLocal_photo(Blob local_photo) {
		this.local_photo = local_photo;
	}

	public ArrayList<Comida> getLocal_foodList() {
		return this.local_foodList;
	}

	public ArrayList<Bebida> getLocal_drinkList() {
		return this.local_drinkList;
	}

	public Coordinates getCoordinates() {
		return this.coordinates;
	}

	public void setCoordinates(Coordinates coordinates) {
		this.coordinates = coordinates;
	} 
	public static void main(String []args) throws IOException {
		ByteArrayOutputStream baos=new ByteArrayOutputStream();     
		OutputStreamWriter outputStreamWriter=new OutputStreamWriter(baos,"UTF-8");
		Local local= new Local();
		
		local.setId("abc");
        local.setLocal_address("Calle Tupu Tamadre");
        local.setLocal_name("BarPuto");
        Comida a = new Comida();
        Comida c = new Comida();
        c.setDish_name("Rabazo de Toro");
        c.setType("Long Chicken");
        a.setDish_name("Semen de ballena");
        a.setType("bebida");
        local.getLocal_foodList().add(a);
		local.getLocal_foodList().add(c);

        Bebida b= new Bebida();
        b.setDrink_name("batido");
        b.setType("Fresa");
        Coordinates coord = new Coordinates();
        coord.setLat(1234);
        coord.setLon(04321);
        local.getLocal_drinkList().add(b);
       	local.setLocal_photo(null);
        local.setCoordinates(coord);
        
		JsonWriter prueba= new JsonWriter(outputStreamWriter);
		prueba.setIndent("\t");

		Gson gson = new Gson();
		System.out.println(gson.toJson(local));
		Local local2;
		local2=gson.fromJson(gson.toJson(local), Local.class);
		System.out.println(local2.getId());
		
		
	}
    

}
