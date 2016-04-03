package com.graymatter.spritemanager.ui;

import java.awt.Component;

import javax.swing.JOptionPane;

public class UIUtils {

	public static void showError(Exception e, Component c){
		JOptionPane.showMessageDialog(c,
				e.getClass().getName(),
			    e.getMessage(),
			    JOptionPane.ERROR_MESSAGE);
		e.printStackTrace();
	}
	
}
