package hieu.model;

public class Category {
	private String nameCategory;
	private int idCategory;
	
	public String getNameCategory() {
		return nameCategory;
	}
	public void setNameCategory(String nameCategory) {
		this.nameCategory = nameCategory;
	}
	public int getIdCategory() {
		return idCategory;
	}
	public void setIdCategory(int idCategory) {
		this.idCategory = idCategory;
	}
	@Override
	public String toString() {
		return this.nameCategory;
	}
	
	
}
