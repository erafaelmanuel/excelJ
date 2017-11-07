package io.ermdev.excelj.core;

public enum Version {
	
	XLS("xls"), XLSX("xlsx");

	private String val;

	Version(String val) {
		this.val = val;
	}

	@Override
	public String toString() {
		return val;
	}
}
