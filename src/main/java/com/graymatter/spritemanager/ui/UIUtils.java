package com.graymatter.spritemanager.ui;

import java.awt.Component;
import java.awt.Container;
import java.awt.Window;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class UIUtils {

	public static void setIcon(Window window) {
		System.out.println("Settign icon image");

		try {
			window.setIconImage(ImageIO.read(ClassLoader.getSystemResource("factedit.png")));
		} catch (IOException e) {
			System.err.println("shit");
			e.printStackTrace();
		}
		System.out.println("Done");
	}

	public static void showError(Exception e, Component c) {
		JOptionPane.showMessageDialog(c, e.getMessage(), e.getClass().getName(), JOptionPane.ERROR_MESSAGE);
		e.printStackTrace();
	}

	public static Component[] getComponents(Component container) {
		ArrayList<Component> list = null;

		try {
			list = new ArrayList<Component>(Arrays.asList(((Container) container).getComponents()));
			for (int index = 0; index < list.size(); index++) {
				for (Component currentComponent : getComponents(list.get(index))) {
					list.add(currentComponent);
				}
			}
		} catch (ClassCastException e) {
			list = new ArrayList<Component>();
		}

		return list.toArray(new Component[list.size()]);
	}

	public static void setPanelEnabled(Component cmp, boolean bool){
		for(Component component : getComponents(cmp)) {
		    component.setEnabled(bool);
		}
	}
	
}
