package edu.ucsd.som.stip.beans;

import java.io.Serializable;

public class StipDiffIFISGA implements Serializable {

	private static final long serialVersionUID = -6958767882479383973L;
	
	public StipDiffIFISGA(Object[] stipDiffIFISGA) {
		super();
		this.quarterYear = stipDiffIFISGA[0].toString();
		this.fund =  stipDiffIFISGA[1].toString();
		this.ifisTotal =Double.valueOf(stipDiffIFISGA[2].toString());
		this.gaTotal =  Double.valueOf(stipDiffIFISGA[3].toString());
		this.difference = Double.valueOf(stipDiffIFISGA[4].toString());
	}
	
	
	
	private String quarterYear;
	private String fund;
	private double ifisTotal;
	private double gaTotal;
	private double difference;
	
	public String getQuarterYear() {
		return quarterYear;
	}
	public void setQuarterYear(String quarterYear) {
		this.quarterYear = quarterYear;
	}
	public String getFund() {
		return fund;
	}
	public void setFund(String fund) {
		this.fund = fund;
	}
	public double getIfisTotal() {
		return ifisTotal;
	}
	public void setIfisTotal(double ifisTotal) {
		this.ifisTotal = ifisTotal;
	}
	public double getGaTotal() {
		return gaTotal;
	}
	public void setGaTotal(double gaTotal) {
		this.gaTotal = gaTotal;
	}
	public double getDifference() {
		return difference;
	}
	public void setDifference(double difference) {
		this.difference = difference;
	}



}
