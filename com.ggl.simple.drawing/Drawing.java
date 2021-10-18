package com.ggl.simple.drawing;

import javax.swing.SwingUtilities;

import com.ggl.simple.drawing.model.DrawingModel;
import com.ggl.simple.drawing.view.DrawingFrame;

public class Drawing implements Runnable {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Drawing());
	}

	@Override
	public void run() {
		new DrawingFrame(new DrawingModel());
	}

}
