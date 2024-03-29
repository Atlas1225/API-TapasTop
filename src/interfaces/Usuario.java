package interfaces;


import java.sql.Blob;
import java.util.ArrayList;



public class Usuario {
    private boolean active;
    private String gender;
    private String email;
    private String username;
    private String password;
    private String text_intro;
    private String name;
    private String surname;
    private int age;
    private String country;
    private String location;
    private String photo;
    private ArrayList<Usuario> friends = new ArrayList<>();
    private String friend;
    private ArrayList<Degustacion> fav_foods = new ArrayList<>();
    private ArrayList<Degustacion> tasting_list = new ArrayList<>();
    private ArrayList<Galardon> award_list = new ArrayList<>();
    
    public Usuario(String gender, String email,String username,String password, 
    		int age){
    	this.active=false;
    	this.gender=gender;
    	this.email=email;
    	this.username=username;
    	this.password=password;
    	this.age=age;
    	
    }
    public String getUsuario() {
    	return this.friend;
    }
    public boolean isActive() {
		return this.active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getText_intro() {
		return this.text_intro;
	}

	public void setText_intro(String text_intro) {
		this.text_intro = text_intro;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return this.surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public int getAge() {
		return this.age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getLocation() {
		return this.location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getPhoto() {
		return this.photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}
	
	public ArrayList<Usuario> getFriends() {
		return this.friends;
	}

	public void setFriends(ArrayList<Usuario> friends) {
		this.friends = friends;
	}

	public ArrayList<Degustacion> getFav_foods() {
		return this.fav_foods;
	}

	public void setFav_foods(ArrayList<Degustacion> fav_foods) {
		this.fav_foods = fav_foods;
	}

	public ArrayList<Degustacion> getTasting_list() {
		return this.tasting_list;
	}

	public void setTasting_list(ArrayList<Degustacion> tasting_list) {
		this.tasting_list = tasting_list;
	}

	public ArrayList<Galardon> getAward_list() {
		return this.award_list;
	}

	public void setAward_list(ArrayList<Galardon> award_list) {
		this.award_list = award_list;
	}
    
}
