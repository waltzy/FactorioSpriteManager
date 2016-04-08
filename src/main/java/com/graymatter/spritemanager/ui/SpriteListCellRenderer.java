package com.graymatter.spritemanager.ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.filechooser.FileSystemView;

import com.graymatter.spritemanager.Sprite;

/** A FileListCellRenderer for a File. */
public class SpriteListCellRenderer extends DefaultListCellRenderer {

	public static final int ICON_MAX_WIDTH = 40;
	private static final long serialVersionUID = -7799441088157759804L;
	private FileSystemView fileSystemView;
	private JLabel label;
	private Color textSelectionColor = Color.BLACK;
	private Color backgroundSelectionColor = Color.CYAN;
	private Color textNonSelectionColor = Color.BLACK;
	private Color backgroundNonSelectionColor = Color.WHITE;

	SpriteListCellRenderer() {
		label = new JLabel();
		label.setOpaque(true);
		fileSystemView = FileSystemView.getFileSystemView();
	}

	@Override
	public Component getListCellRendererComponent(@SuppressWarnings("rawtypes") JList list, Object value, int index,
			boolean selected, boolean expanded) {

		
		label.setText(fileSystemView.getSystemDisplayName(((Sprite)value).getFile()));
		label.setIcon(new ImageIcon(((Sprite)value).getIcon()));;

		if (selected) {
			label.setBackground(backgroundSelectionColor);
			label.setForeground(textSelectionColor);
		} else {
			label.setBackground(backgroundNonSelectionColor);
			label.setForeground(textNonSelectionColor);
		}

		return label;
	}
}
