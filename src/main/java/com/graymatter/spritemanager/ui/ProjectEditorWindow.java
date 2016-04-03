package com.graymatter.spritemanager.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JList;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import javax.swing.JButton;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.BoxLayout;

public class ProjectEditorWindow extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProjectEditorWindow frame = new ProjectEditorWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ProjectEditorWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1024 , 768);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JPanel spriteListPanel = new JPanel();
		GridBagConstraints gbc_spriteListPanel = new GridBagConstraints();
		gbc_spriteListPanel.insets = new Insets(0, 0, 5, 5);
		gbc_spriteListPanel.fill = GridBagConstraints.BOTH;
		gbc_spriteListPanel.gridx = 0;
		gbc_spriteListPanel.gridy = 0;
		contentPane.add(spriteListPanel, gbc_spriteListPanel);
		GridBagLayout gbl_spriteListPanel = new GridBagLayout();
		gbl_spriteListPanel.columnWidths = new int[]{0, 0};
		gbl_spriteListPanel.rowHeights = new int[]{0, 0, 0};
		gbl_spriteListPanel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_spriteListPanel.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		spriteListPanel.setLayout(gbl_spriteListPanel);
		
		JPanel managedSpritesMenuPanel = new JPanel();
		GridBagConstraints gbc_managedSpritesMenuPanel = new GridBagConstraints();
		gbc_managedSpritesMenuPanel.fill = GridBagConstraints.HORIZONTAL;
		gbc_managedSpritesMenuPanel.anchor = GridBagConstraints.NORTH;
		gbc_managedSpritesMenuPanel.insets = new Insets(5, 0, 5, 5);
		gbc_managedSpritesMenuPanel.gridx = 0;
		gbc_managedSpritesMenuPanel.gridy = 0;
		spriteListPanel.add(managedSpritesMenuPanel, gbc_managedSpritesMenuPanel);
		managedSpritesMenuPanel.setLayout(new BoxLayout(managedSpritesMenuPanel, BoxLayout.X_AXIS));
		
		JLabel lblNewLabel = new JLabel("Managed Sprites");
		managedSpritesMenuPanel.add(lblNewLabel);
		
		Component horizontalGlue = Box.createHorizontalGlue();
		managedSpritesMenuPanel.add(horizontalGlue);
		
		JButton btnNew = new JButton("New");
		managedSpritesMenuPanel.add(btnNew);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setEnabled(false);
		managedSpritesMenuPanel.add(btnDelete);
		
		JList list = new JList();
		GridBagConstraints gbc_list = new GridBagConstraints();
		gbc_list.fill = GridBagConstraints.BOTH;
		gbc_list.gridx = 0;
		gbc_list.gridy = 1;
		spriteListPanel.add(list, gbc_list);
		
		JPanel managedSpriteDetails = new JPanel();
		GridBagConstraints gbc_managedSpriteDetails = new GridBagConstraints();
		gbc_managedSpriteDetails.insets = new Insets(0, 0, 5, 5);
		gbc_managedSpriteDetails.fill = GridBagConstraints.BOTH;
		gbc_managedSpriteDetails.gridx = 1;
		gbc_managedSpriteDetails.gridy = 0;
		contentPane.add(managedSpriteDetails, gbc_managedSpriteDetails);
		
		JList tilesPanel = new JList();
		GridBagConstraints gbc_tilesPanel = new GridBagConstraints();
		gbc_tilesPanel.insets = new Insets(0, 0, 5, 0);
		gbc_tilesPanel.fill = GridBagConstraints.BOTH;
		gbc_tilesPanel.gridx = 2;
		gbc_tilesPanel.gridy = 0;
		contentPane.add(tilesPanel, gbc_tilesPanel);
		
		JPanel stripesPanel = new JPanel();
		GridBagConstraints gbc_stripesPanel = new GridBagConstraints();
		gbc_stripesPanel.insets = new Insets(0, 0, 0, 5);
		gbc_stripesPanel.fill = GridBagConstraints.BOTH;
		gbc_stripesPanel.gridx = 0;
		gbc_stripesPanel.gridy = 1;
		contentPane.add(stripesPanel, gbc_stripesPanel);
		
		JTextArea console = new JTextArea();
		GridBagConstraints gbc_console = new GridBagConstraints();
		gbc_console.insets = new Insets(0, 0, 0, 5);
		gbc_console.fill = GridBagConstraints.BOTH;
		gbc_console.gridx = 1;
		gbc_console.gridy = 1;
		contentPane.add(console, gbc_console);
		
		JPanel tileViewPanel = new JPanel();
		GridBagConstraints gbc_tileViewPanel = new GridBagConstraints();
		gbc_tileViewPanel.fill = GridBagConstraints.BOTH;
		gbc_tileViewPanel.gridx = 2;
		gbc_tileViewPanel.gridy = 1;
		contentPane.add(tileViewPanel, gbc_tileViewPanel);
	}

}
