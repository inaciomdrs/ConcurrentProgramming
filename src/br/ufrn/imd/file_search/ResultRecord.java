package br.ufrn.imd.file_search;

public class ResultRecord {

	private String filename;
	private String location;
	
	public ResultRecord(String filename, String location) {
		this.filename = filename;
		this.location = location;
	}

	public String getFilename() {
		return filename;
	}

	public String getLocation() {
		return location;
	}
	
	
	
}
