package com.ggl.simple.drawing.model;

import java.awt.Color;

public enum ColorType {
	BLACK (Color.BLACK, "Black"),
	RED (Color.RED, "Red"),
	GREEN (Color.GREEN, "Green");
	
	private final Color color;
	
	private final String label;
	
    ColorType(Color color, String label) {
        this.color = color;
        this.label = label;
    }

	public Color getColor() {
		return color;
	}

	public String getLabel() {
		return label;
	}
    
}
