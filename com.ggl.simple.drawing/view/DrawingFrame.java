package com.ggl.simple.drawing.view;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import com.ggl.simple.drawing.model.DrawingModel;

public class DrawingFrame {
	
	private final ControlPanel controlPanel;
	
	private final DrawingPanel drawingPanel;
	
	private JFrame frame;

	public DrawingFrame(DrawingModel model) {
		this.controlPanel = new ControlPanel(this, model);
		this.drawingPanel = new DrawingPanel(this, model);
		createAndShowGUI();
	}
	
	private void createAndShowGUI() {
		frame = new JFrame("Drawing GUI");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.add(controlPanel.getPanel(), BorderLayout.NORTH);
		frame.add(drawingPanel, BorderLayout.CENTER);
		
		frame.pack();
		frame.setLocationByPlatform(true);
		frame.setVisible(true);
		
		System.out.println("Frame size: " + frame.getSize());
	}
	
	public JFrame getFrame() {
		return frame;
	}
	
	public void setOutline(boolean isOutline) {
		drawingPanel.setOutline(isOutline);
	}
	
	public void repaint() {
		drawingPanel.repaint();
	}
	
	public void updateDisplayPanel() {
		controlPanel.updateDisplayPanel();
	}

}
