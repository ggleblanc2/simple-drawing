package com.ggl.simple.drawing.controller;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;

import com.ggl.simple.drawing.model.DrawingModel;
import com.ggl.simple.drawing.model.DrawingShape;
import com.ggl.simple.drawing.model.ShapeType;
import com.ggl.simple.drawing.view.DrawingFrame;

public class DrawingListener extends MouseAdapter {
	
	private final DrawingFrame view;
	
	private final DrawingModel model;

	public DrawingListener(DrawingFrame view, DrawingModel model) {
		this.view = view;
		this.model = model;
	}

	@Override
	public void mousePressed(MouseEvent event) {
		if (model.isShape(event.getPoint())) {
			int response = JOptionPane.showConfirmDialog(view.getFrame(),
					"Do you want to delete the shape you clicked on?", 
					"Delete Shape", JOptionPane.YES_NO_OPTION);
			if (response == JOptionPane.YES_OPTION) {
				model.removeShape(event.getPoint());
				view.setOutline(false);
				view.repaint();
			} 
		} else {
			model.setPressedPoint(event.getPoint());
			view.setOutline(true);
		}
	}

	@Override
	public void mouseReleased(MouseEvent event) {
		model.addShape(createDrawingShape(event.getPoint()));
		view.setOutline(false);
		view.repaint();
	}

	private DrawingShape createDrawingShape(Point releasedPoint) {
		Point pressedPoint = model.getPressedPoint();
		int x = Math.min(releasedPoint.x, pressedPoint.x);
		int y = Math.min(releasedPoint.y, pressedPoint.y);
		int width = Math.abs(releasedPoint.x - pressedPoint.x);
		int height = Math.abs(releasedPoint.y - pressedPoint.y);
		
		if (model.getCurrentShapeType() == ShapeType.CIRCLE) {
			int diameter = Math.min(width, height);
			int radius = diameter / 2;
			width = radius + radius;
			height = width;
		}
		
		return new DrawingShape(x, y, width, height, 
				model.getCurrentColorType(), model.getCurrentShapeType());
	}
	
	@Override
	public void mouseMoved(MouseEvent event) {
		view.setOutline(false);
	}

	@Override
	public void mouseDragged(MouseEvent event) {
		model.setCurrentPoint(event.getPoint());
		view.repaint();
	}
	
}
