package com.graymatter.spritemanager.ui;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;

import com.graymatter.spritemanager.Project;
import com.graymatter.spritemanager.WorkspaceManager;
import com.graymatter.spritemanager.entities.Workspace;
import com.graymatter.spritemanager.exceptions.ProjectSetupException;

public class WorkspaceDialouge extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5972597430624170912L;
	private JComboBox<Workspace> comboBox;
	
	public WorkspaceDialouge() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		
		UIUtils.setIcon(this);
		
		setBounds(200, 200, 610 , 97);
		
		final WorkspaceDialouge that = this;
		setTitle("Select Workspace (Your Mod Folder)");
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 28, 61, 83, 0};
		gridBagLayout.rowHeights = new int[] {23, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		JButton btnNew = new JButton("Open New");
		btnNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("opening ");
				OpenNewWorkspaceDialouge dialog = new OpenNewWorkspaceDialouge(that, true);
				dialog.setVisible(true);
			}
		});
		
		Component verticalStrut = Box.createVerticalStrut(20);
		GridBagConstraints gbc_verticalStrut = new GridBagConstraints();
		gbc_verticalStrut.insets = new Insets(0, 0, 5, 5);
		gbc_verticalStrut.gridx = 0;
		gbc_verticalStrut.gridy = 0;
		getContentPane().add(verticalStrut, gbc_verticalStrut);
		
		comboBox = new JComboBox<Workspace>();
		comboBox.setMaximumRowCount(100000);
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.anchor = GridBagConstraints.EAST;
		gbc_comboBox.insets = new Insets(5, 5, 5, 5);
		gbc_comboBox.gridx = 1;
		gbc_comboBox.gridy = 0;
		getContentPane().add(comboBox, gbc_comboBox);
		
		JButton btnSelect = new JButton("Select");
		btnSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (comboBox.getSelectedItem() == null) UIUtils.showError(new Exception("No Workspace Selected"), WorkspaceDialouge.this);
				try {
					Project project = WorkspaceManager.getInstance().getProject((Workspace)comboBox.getSelectedItem());
					System.out.println("Selected "+project.getWorkingDirectory().getAbsolutePath());
					ProjectEditorWindow pew = new ProjectEditorWindow();
					pew.setProject(project);
					pew.setVisible(true);
					WorkspaceDialouge.this.dispose();
				} catch (ProjectSetupException e1) {
					UIUtils.showError(e1, WorkspaceDialouge.this);
				}
			}
		});
		GridBagConstraints gbc_btnSelect = new GridBagConstraints();
		gbc_btnSelect.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnSelect.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnSelect.insets = new Insets(5, 0, 5, 5);
		gbc_btnSelect.gridx = 2;
		gbc_btnSelect.gridy = 0;
		getContentPane().add(btnSelect, gbc_btnSelect);
		GridBagConstraints gbc_btnNew = new GridBagConstraints();
		gbc_btnNew.insets = new Insets(5, 0, 5, 0);
		gbc_btnNew.anchor = GridBagConstraints.WEST;
		gbc_btnNew.gridx = 3;
		gbc_btnNew.gridy = 0;
		getContentPane().add(btnNew, gbc_btnNew);
		
		Component horizontalStrut = Box.createHorizontalStrut(400);
		GridBagConstraints gbc_horizontalStrut = new GridBagConstraints();
		gbc_horizontalStrut.insets = new Insets(0, 0, 5, 5);
		gbc_horizontalStrut.gridx = 1;
		gbc_horizontalStrut.gridy = 1;
		getContentPane().add(horizontalStrut, gbc_horizontalStrut);
		
		Component horizontalStrut_1 = Box.createHorizontalStrut(70);
		GridBagConstraints gbc_horizontalStrut_1 = new GridBagConstraints();
		gbc_horizontalStrut_1.insets = new Insets(0, 0, 5, 5);
		gbc_horizontalStrut_1.gridx = 2;
		gbc_horizontalStrut_1.gridy = 1;
		getContentPane().add(horizontalStrut_1, gbc_horizontalStrut_1);
		
		Component horizontalStrut_2 = Box.createHorizontalStrut(100);
		GridBagConstraints gbc_horizontalStrut_2 = new GridBagConstraints();
		gbc_horizontalStrut_2.insets = new Insets(0, 0, 5, 0);
		gbc_horizontalStrut_2.gridx = 3;
		gbc_horizontalStrut_2.gridy = 1;
		getContentPane().add(horizontalStrut_2, gbc_horizontalStrut_2);
		pack();
		updateWorkspaces();
	}

	public void updateWorkspaces() {
		comboBox.removeAllItems();
		try {
			for (Workspace w : WorkspaceManager.getInstance().getWorkspaceList()){
				comboBox.addItem(w);
			}
		} catch (ProjectSetupException e) {
			UIUtils.showError(e, this);
		}

	}

}
