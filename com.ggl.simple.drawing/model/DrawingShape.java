package com.ggl.simple.drawing.model;

public class DrawingShape {

	private final int x, y, width, height;

	private final ColorType colorType;

	private final ShapeType shapeType;

	public DrawingShape(int x, int y, int width, int height, 
			ColorType colorType, ShapeType shapeType) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.colorType = colorType;
		this.shapeType = shapeType;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public ShapeType getShapeType() {
		return shapeType;
	}

	public ColorType getColorType() {
		return colorType;
	}

}
