package com.erm.project.jexcel.model;

/**
 * 
 * @author erafaelmanuel
 *
 */
public enum Version {
	
	XLS("xls"), XLXS("xlsx");
	
	/**
	 * 
	 */
	private String version;
	
	/**
	 * 
	 * @param version
	 */
	Version(String version) {
		this.version = version;
	}

	/**
	 * 
	 * @return
	 */
	public String getVersion() {
		return version;
	}
}
