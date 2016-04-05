package com.graymatter.spritemanager.ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Image;
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
    public Component getListCellRendererComponent(
            JList list,
            Object value,
            int index,
            boolean selected,
            boolean expanded) {

        File file = ((Sprite)value).getFile();
        try {
			label.setIcon(new ImageIcon(ImageIO.read(file).getScaledInstance(40, 40, Image.SCALE_FAST)));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        label.setText(fileSystemView.getSystemDisplayName(file));
        label.setToolTipText(file.getPath());

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
