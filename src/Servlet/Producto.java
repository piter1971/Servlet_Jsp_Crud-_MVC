package Servlet;

import java.util.Date;

public class Producto {
	
	
	private String cArt;
	private String seccionArt;
	private String nomArt;
	private Double precioArt;
	private Date fechaArt;
	private String importArt;
	private String paisArt;
	
	public Producto(String cArt, String seccionArt, String nomArt, Double precioArt, Date fechaArt, String importArt,String paisArt) {		
		this.cArt = cArt;
		this.seccionArt = seccionArt;
		this.nomArt = nomArt;
		this.precioArt = precioArt;
		this.fechaArt = fechaArt;
		this.importArt = importArt;
		this.paisArt = paisArt;
	}
	
	

	public Producto(String seccionArt, String nomArt, Double precioArt, Date fechaArt, String importArt,String paisArt) {		
		this.seccionArt = seccionArt;
		this.nomArt = nomArt;
		this.precioArt = precioArt;
		this.fechaArt = fechaArt;
		this.importArt = importArt;
		this.paisArt = paisArt;
	}
	
	



	public Producto(String seccionArt) {
		
		this.seccionArt = seccionArt;
	}



	public String getcArt() {
		return cArt;
	}

	public void setcArt(String cArt) {
		this.cArt = cArt;
	}

	public String getSeccionArt() {
		return seccionArt;
	}

	public void setSeccionArt(String seccionArt) {
		this.seccionArt = seccionArt;
	}

	public String getNomArt() {
		return nomArt;
	}

	public void setNomArt(String nomArt) {
		this.nomArt = nomArt;
	}

	public Double getPrecioArt() {
		return precioArt;
	}

	public void setPrecioArt(Double precioArt) {
		this.precioArt = precioArt;
	}

	public Date getFechaArt() {
		return fechaArt;
	}

	public void setFechaArt(Date fechaArt) {
		this.fechaArt = fechaArt;
	}

	public String getImportArt() {
		return importArt;
	}

	public void setImportArt(String importArt) {
		this.importArt = importArt;
	}

	public String getPaisArt() {
		return paisArt;
	}

	public void setPaisArt(String paisArt) {
		this.paisArt = paisArt;
	}

	@Override
	public String toString() {
		return "Producto [cArt=" + cArt + ", seccionArt=" + seccionArt + ", nomArt=" + nomArt + ", precioArt="
				+ precioArt + ", fechaArt=" + fechaArt + ", importArt=" + importArt + ", paisArt=" + paisArt + "]";
	}
	
	
	

}
