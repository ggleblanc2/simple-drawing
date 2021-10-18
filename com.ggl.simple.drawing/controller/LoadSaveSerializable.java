package com.ggl.simple.drawing.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JFileChooser;

import com.ggl.simple.drawing.model.ColorType;
import com.ggl.simple.drawing.model.DrawingModel;
import com.ggl.simple.drawing.model.DrawingShape;
import com.ggl.simple.drawing.model.ShapeType;
import com.ggl.simple.drawing.view.DrawingFrame;

public class LoadSaveSerializable {
	
	private final DrawingFrame view;
	
	private final DrawingModel model;
	
	public LoadSaveSerializable(DrawingFrame view, DrawingModel model) {
		this.view = view;
		this.model = model;
	}

	public void saveModel() {
		JFileChooser fc = new JFileChooser();
		int returnVal = fc.showSaveDialog(view.getFrame());

		if (returnVal == JFileChooser.APPROVE_OPTION) {
			try {
				FileOutputStream fileOut = 
						new FileOutputStream(fc.getSelectedFile());
				ObjectOutputStream out = 
						new ObjectOutputStream(fileOut);
				writeModel(out);
				out.close();
				fileOut.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void writeModel(ObjectOutputStream out) throws IOException {
		out.writeObject(model.getCurrentColorType());
		out.writeObject(model.getCurrentShapeType());
		out.writeInt(model.getShapes().size());
		for (DrawingShape drawingShape : model.getShapes()) {
			out.writeInt(drawingShape.getX());
			out.writeInt(drawingShape.getY());
			out.writeInt(drawingShape.getWidth());
			out.writeInt(drawingShape.getHeight());
			out.writeObject(drawingShape.getColorType());
			out.writeObject(drawingShape.getShapeType());
		}
	}
	
	public void loadModel() {
		JFileChooser fc = new JFileChooser();
		int returnVal = fc.showOpenDialog(view.getFrame());

		if (returnVal == JFileChooser.APPROVE_OPTION) {
			 try {
				FileInputStream fileIn = 
						new FileInputStream(fc.getSelectedFile());
				 ObjectInputStream in = 
						 new ObjectInputStream(fileIn);
				 updateModel(in);
				 in.close();
				 fileIn.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void updateModel(ObjectInputStream in) 
			throws IOException, ClassNotFoundException {
		model.initalize();
		model.setCurrentColorType((ColorType) in.readObject());
		model.setCurrentShapeType((ShapeType) in.readObject());
		int limit = in.readInt();
		for (int index = 0; index < limit; index++) {
			int x = in.readInt();
			int y = in.readInt();
			int width = in.readInt();
			int height = in.readInt();
			ColorType colorType = (ColorType) in.readObject();
			ShapeType shapeType = (ShapeType) in.readObject();
			DrawingShape drawingShape = new DrawingShape(x, y, 
					width, height, colorType, shapeType);
			model.addShape(drawingShape);
		}
	}

}
