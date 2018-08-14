package com.capgemini.types;

public class PositionTO {
	
	private Long id;
	private String position;
	
	public PositionTO(Long id, String position) {
		super();
		this.id=id;
		this.setPosition(position);
	}

	public PositionTO(String position) {
		super();
		this.setPosition(position);
	}

	public Long getId() {
		return id;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}
	
//	public static PositionTOBuilder builder(){
//		return new PositionTOBuilder();
//	}
//	
//	public static class PositionTOBuilder {
//		
//	}
	
}
