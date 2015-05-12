package edu.ucsd.som.stip.services;

public class UploadRepository {
	
	public static final String quarterListNotRev="select distinct qrt from journal where reversed=0 and qrt in (:inList)";

}
