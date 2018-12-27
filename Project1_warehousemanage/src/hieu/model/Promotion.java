package hieu.model;

public class Promotion {
	private int idPromo;
	private String startDay;
	private String endDay;
	private int promoValue;
	private int status;

	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getIdPromo() {
		return idPromo;
	}
	public void setIdPromo(int idPromo) {
		this.idPromo = idPromo;
	}
	public String getStartDay() {
		return startDay;
	}
	public void setStartDay(String startDay) {
		this.startDay = startDay;
	}
	public String getEndDay() {
		return endDay;
	}
	public void setEndDay(String endDay) {
		this.endDay = endDay;
	}
	public int getPromoValue() {
		return promoValue;
	}
	public void setPromoValue(int promoValue) {
		this.promoValue = promoValue;
	}

}
