package interfaces;

public class Comida {
    private String dish_name;
    private String type;
    
    public String getDish_name() {
		return this.dish_name;
	}

	public void setDish_name(String dish_name) {
		this.dish_name = dish_name;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
