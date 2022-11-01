package interfaces;

import java.awt.image.BufferedImage;
import java.sql.Blob;
import java.util.Date;

public class Degustacion {
    private Usuario author;
    private String qualifier_taste;
    private int rating;
    private Blob photo;
    private Date date;
    private String description;
    private Local local_pointer;
    private String origin;
    private String local_dishName;
    
    public Usuario getAuthor() {
		return this.author;
	}

	public void setAuthor(Usuario author) {
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

	public Blob getPhoto() {
		return this.photo;
	}

	public void setPhoto(Blob photo) {
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

	public String getLocal_dishName() {
		return this.local_dishName;
	}

	public void setLocal_dishName(String local_dishName) {
		this.local_dishName = local_dishName;
	}

}
