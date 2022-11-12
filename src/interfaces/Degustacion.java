package interfaces;

import java.util.Date;

public class Degustacion {
    private String author;//username
    private String qualifier_taste;
    private int rating;
    private String photo;
    private Date date;
    private String description;
    private Local local_pointer;
    private String origin;
    private String dish_name;
	private String type;

	
    
    public String getAuthor() {
		return this.author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getQualifier_taste() {
		return this.qualifier_taste;
	}

	public void setQualifier_taste(String qualifier_taste) {
		this.qualifier_taste = qualifier_taste;
	}

	public int getRating() {
		return this.rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getPhoto() {
		return this.photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Local getLocal_pointer() {
		return this.local_pointer;
	}

	public void setLocal_pointer(Local local_pointer) {
		this.local_pointer = local_pointer;
	}

	public String getOrigin() {
		return this.origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getdish_name() {
		return this.dish_name;
	}

	public void setdish_name(String local_dishName) {
		this.dish_name = local_dishName;
	}
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}


}
