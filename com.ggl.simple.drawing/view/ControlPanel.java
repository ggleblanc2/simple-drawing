package com.ggl.simple.drawing.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.ggl.simple.drawing.controller.LoadSaveSerializable;
import com.ggl.simple.drawing.model.ColorType;
import com.ggl.simple.drawing.model.DrawingModel;
import com.ggl.simple.drawing.model.ShapeType;

public class ControlPanel {
	
	private final DrawingFrame view;
	
	private final DrawingModel model;
	
	private JLabel colorLabel, shapeLabel;
	
	private JPanel panel;

	public ControlPanel(DrawingFrame view, DrawingModel model) {
		this.view = view;
		this.model = model;
		this.panel = createControlPanel();
	}
	
	private JPanel createControlPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(createButtonPanel(), BorderLayout.NORTH);
		panel.add(createDisplayPanel(), BorderLayout.SOUTH);
		return panel;
	}
	
	private JPanel createButtonPanel() {
		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		
		JButton buttonBlack = new JButton(ColorType.BLACK.getLabel());
		panel.add(buttonBlack);
		
        JButton buttonRed = new JButton(ColorType.RED.getLabel());
        panel.add(buttonRed);
        
        JButton buttonGreen = new JButton(ColorType.GREEN.getLabel());
        panel.add(buttonGreen);
        panel.add(Box.createHorizontalStrut(10));
        
        JButton buttonCircle = new JButton(ShapeType.CIRCLE.getLabel());
        panel.add(buttonCircle);
        
        JButton buttonOval = new JButton(ShapeType.OVAL.getLabel());
        panel.add(buttonOval);
        
        JButton buttonRectangle = new JButton(ShapeType.RECTANGLE.getLabel());
        panel.add(buttonRectangle);
        panel.add(Box.createHorizontalStrut(10));
        
        JButton buttonClear = new JButton("Clear");
        panel.add(buttonClear);
        
        JButton buttonSave = new JButton("Save");
        panel.add(buttonSave);
        
        JButton buttonLoad = new JButton("Load");
        panel.add(buttonLoad);
        
        buttonBlack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                model.setCurrentColorType(ColorType.BLACK);
                updateDisplayPanel();
            }
        });
        
        buttonRed.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                model.setCurrentColorType(ColorType.RED);
                updateDisplayPanel();
            }
        });
        
        buttonGreen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                model.setCurrentColorType(ColorType.GREEN);
                updateDisplayPanel();
            }
        });
        
        buttonCircle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                model.setCurrentShapeType(ShapeType.CIRCLE);
                updateDisplayPanel();
            }
        });
        
        buttonOval.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                model.setCurrentShapeType(ShapeType.OVAL);
                updateDisplayPanel();
            }
        });
        
        buttonRectangle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                model.setCurrentShapeType(ShapeType.RECTANGLE);
                updateDisplayPanel();
            }
        });
        
        buttonClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                model.initalize();
                updateDisplayPanel();
                view.repaint();
            }
        });
        
        LoadSaveSerializable loadSave = new LoadSaveSerializable(view, model);
        
		buttonSave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				loadSave.saveModel();
			}
		});
		
		buttonLoad.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				loadSave.loadModel();
				updateDisplayPanel();
				view.repaint();
			}
		});
        
        Dimension c = getLargestDimension(
        		buttonBlack, buttonRed, buttonGreen);
        buttonBlack.setPreferredSize(c);
        buttonRed.setPreferredSize(c);
        buttonGreen.setPreferredSize(c);
        
        Dimension s = getLargestDimension(
        		buttonRectangle, buttonOval, buttonCircle);
        buttonRectangle.setPreferredSize(s);
        buttonOval.setPreferredSize(s);
        buttonCircle.setPreferredSize(s);
        
        Dimension o = getLargestDimension(
        		buttonClear, buttonSave, buttonLoad);
        buttonClear.setPreferredSize(o);
        buttonSave.setPreferredSize(o);
        buttonLoad.setPreferredSize(o);
		
		return panel;
	}
	
	private Dimension getLargestDimension(JButton... buttons) {
		int maximumWidth = buttons[0].getPreferredSize().width;
		int maximumHeight = buttons[0].getPreferredSize().height;
		
		for (int index = 1; index < buttons.length; index++) {
			maximumWidth = Math.max(maximumWidth, 
					buttons[index].getPreferredSize().width);
			maximumHeight = Math.max(maximumHeight, 
					buttons[index].getPreferredSize().height);
		}
		
		return new Dimension(maximumWidth + 2, maximumHeight);
	}
	
	private JPanel createDisplayPanel() {
		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		
		JLabel label = new JLabel("Color:");
		panel.add(label);
		
		colorLabel = new JLabel();
		panel.add(colorLabel);
		panel.add(Box.createHorizontalStrut(40));
		
		label = new JLabel("Shape:");
		panel.add(label);
		
		shapeLabel = new JLabel();
		panel.add(shapeLabel);
		
		updateDisplayPanel();
		
		return panel;
	}
	
	public void updateDisplayPanel() {
		this.colorLabel.setText(model.getCurrentColorType().getLabel());
		this.shapeLabel.setText(model.getCurrentShapeType().getLabel());
	}

	public JPanel getPanel() {
		return panel;
	}

}
