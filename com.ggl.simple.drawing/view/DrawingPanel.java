package com.ggl.simple.drawing.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import com.ggl.simple.drawing.controller.DrawingListener;
import com.ggl.simple.drawing.model.DrawingModel;
import com.ggl.simple.drawing.model.DrawingShape;

public class DrawingPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private boolean isOutline;
	
	private final DrawingModel model;
	
	public DrawingPanel(DrawingFrame view, DrawingModel model) {
		this.model = model;
		this.isOutline = false;
		this.setBackground(Color.WHITE);
		this.setPreferredSize(new Dimension(786, 481));
		
		DrawingListener listener = new DrawingListener(view, model);
		this.addMouseListener(listener);
		this.addMouseMotionListener(listener);
	}
	
	public void setOutline(boolean isOutline) {
		this.isOutline = isOutline;
	}

	@Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        for (DrawingShape drawingShape : model.getShapes()) {
        	g.setColor(drawingShape.getColorType().getColor());
        	int x = drawingShape.getX();
        	int y = drawingShape.getY();
        	int width = drawingShape.getWidth();
        	int height = drawingShape.getHeight();
        	
			switch (drawingShape.getShapeType()) {
				case CIRCLE: 
				case OVAL: {
					g.fillOval(x, y, width, height);
					break;
				}
				case RECTANGLE: {
					g.fillRect(x, y, width, height);
					break;
				}
			}
        }
        
        if (isOutline) {
        	g.setColor(Color.BLUE);
        	int x = Math.min(model.getPressedPoint().x, 
        			model.getCurrentPoint().x);
        	int y = Math.min(model.getPressedPoint().y, 
        			model.getCurrentPoint().y);
        	int width = Math.abs(model.getPressedPoint().x - 
        			model.getCurrentPoint().x);
        	int height = Math.abs(model.getPressedPoint().y - 
        			model.getCurrentPoint().y);
        	g.drawRect(x, y, width, height);
        }
    }
	
}
