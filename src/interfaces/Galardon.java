package interfaces;


import java.awt.image.BufferedImage;
import java.sql.Blob;


public class Galardon {
	private String id;
	private int level;
	private Blob photo;
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getLevel() {
		return this.level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public Blob getPhoto() {
		return this.photo;
	}

	public void setPhoto(Blob photo) {
		this.photo = photo;
	}

}