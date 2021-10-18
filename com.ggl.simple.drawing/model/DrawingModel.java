package com.ggl.simple.drawing.model;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

public class DrawingModel {

	private ColorType currentColorType;

	private List<DrawingShape> shapes;

	private Point pressedPoint, currentPoint;

	private ShapeType currentShapeType;

	public DrawingModel() {
		this.shapes = new ArrayList<>();
		initalize();
	}

	public void initalize() {
		this.shapes.clear();
		this.currentColorType = ColorType.BLACK;
		this.currentShapeType = ShapeType.CIRCLE;
	}

	public void addShape(DrawingShape drawingShape) {
		this.shapes.add(drawingShape);
	}

	public void removeShape(Point point) {
		for (int index = (shapes.size() - 1); index >= 0; index--) {
			DrawingShape drawingShape = shapes.get(index);
			Rectangle r = new Rectangle(drawingShape.getX(), drawingShape.getY(), 
					drawingShape.getWidth(), drawingShape.getHeight());
			if (r.contains(point)) {
				this.shapes.remove(index);
			}
		}
	}

	public boolean isShape(Point point) {
		for (DrawingShape drawingShape : shapes) {
			Rectangle r = new Rectangle(drawingShape.getX(), drawingShape.getY(), 
					drawingShape.getWidth(), drawingShape.getHeight());
			if (r.contains(point)) {
				return true;
			}
		}

		return false;
	}

	public void setShapes(List<DrawingShape> shapes) {
		this.shapes = shapes;
	}

	public List<DrawingShape> getShapes() {
		return shapes;
	}

	public ShapeType getCurrentShapeType() {
		return currentShapeType;
	}

	public void setCurrentShapeType(ShapeType currentShapeType) {
		this.currentShapeType = currentShapeType;
	}

	public ColorType getCurrentColorType() {
		return currentColorType;
	}

	public void setCurrentColorType(ColorType currentColorType) {
		this.currentColorType = currentColorType;
	}

	public Point getPressedPoint() {
		return pressedPoint;
	}

	public void setPressedPoint(Point pressedPoint) {
		this.pressedPoint = pressedPoint;
	}

	public Point getCurrentPoint() {
		return currentPoint;
	}

	public void setCurrentPoint(Point currentPoint) {
		this.currentPoint = currentPoint;
	}

}
