package com.graymatter.spritemanager.ui;

import java.awt.Component;
import java.awt.Window;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class UIUtils {

	public static void setIcon(Window window){
		System.out.println("Settign icon image");

		try {
			window.setIconImage(ImageIO.read( ClassLoader.getSystemResource( "factedit.png" ) ));
		} catch (IOException e) {
			System.err.println("shit");
			e.printStackTrace();
		}
		System.out.println("Done");
	}
	
	public static void showError(Exception e, Component c){
		JOptionPane.showMessageDialog(c,
				e.getClass().getName(),
			    e.getMessage(),
			    JOptionPane.ERROR_MESSAGE);
		e.printStackTrace();
	}
	
}
