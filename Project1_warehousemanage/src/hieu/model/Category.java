package hieu.model;

import java.io.Serializable;

public class Category implements Serializable{
	private String nameCategory;
	private int idCategory;
	private int status;
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
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
