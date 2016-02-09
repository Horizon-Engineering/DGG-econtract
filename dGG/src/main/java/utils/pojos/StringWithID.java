package utils.pojos;

public class StringWithID {

	private String name;
	private String id;

	public String getName() {
		return name.toString();
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getItemID() {
		return id;
	}

	public void setItemID(String nn) {
		this.id = nn;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return name.toString();
	}
}
