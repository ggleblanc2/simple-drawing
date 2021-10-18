package com.ggl.simple.drawing.model;

public enum ShapeType {
	CIRCLE ("Circle"), 
	RECTANGLE ("Rectangle"), 
	OVAL ("Oval");
	
	private final String label;
	
    ShapeType(String label) {
        this.label = label;
    }

	public String getLabel() {
		return label;
	}
	
}
