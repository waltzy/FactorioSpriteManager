package com.graymatter.spritemanager.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.graymatter.spritemanager.Project;
import com.graymatter.spritemanager.entities.ManagedSprite;
import com.graymatter.spritemanager.exceptions.ProjectSetupException;

public class CreateManagedEntity extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textItemNameField;
	private JTextField textModAssetPath;
	private JTextField textWorkingAssetPath;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			CreateManagedEntity dialog = new CreateManagedEntity();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public CreateManagedEntity(JFrame frame, boolean bool){
		super(frame, bool);
		initUI();
	}
	
	public CreateManagedEntity(){
		initUI();
	}
	
	ProjectEditorWindow editorWindow;
	
	private Project project; 
	/**
	 * Create the dialog.
	 */
	public void initUI() {
		
		UIUtils.setIcon(this);
		
		editorWindow = (ProjectEditorWindow) getParent();
		project = editorWindow.getProject();
		
		final JFileChooser modGraphicsFileChooser = new JFileChooser(project.getGraphicsDirectory().getAbsolutePath());
		modGraphicsFileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		System.out.println("PROJECTS GRAPHICS FILE IS: "+project.getGraphicsDirectory());
		System.out.println("JCHOOSER SET TO "+modGraphicsFileChooser.getCurrentDirectory().getAbsolutePath());
		
		final JFileChooser workingFileDir = new JFileChooser(project.getWorkingDirectory().getAbsolutePath());
		workingFileDir.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		
		setTitle("Create New ManagedSprite");
		setBounds(100, 100, 713, 162);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 0, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblName = new JLabel("Name");
			GridBagConstraints gbc_lblName = new GridBagConstraints();
			gbc_lblName.anchor = GridBagConstraints.EAST;
			gbc_lblName.insets = new Insets(0, 0, 5, 5);
			gbc_lblName.gridx = 0;
			gbc_lblName.gridy = 0;
			contentPanel.add(lblName, gbc_lblName);
		}
		{
			textItemNameField = new JTextField();
			textItemNameField.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent arg0) {
					ProjectEditorWindow pew = (ProjectEditorWindow) CreateManagedEntity.this.getParent();
					try {
						textModAssetPath.setText(pew.getProject().getRelativeToMod(project.getGraphicsDirectory().getAbsolutePath()+"\\"+textItemNameField.getText()));
						textWorkingAssetPath.setText(pew.getProject().getRelativetoWorkingDirectory(project.getWorkingDirectory().getAbsolutePath()+"\\"+textItemNameField.getText()));
					} catch (ProjectSetupException e) {
						/* C'est Impossible! */
						UIUtils.showError(e, CreateManagedEntity.this);
					}
					
				}
			});
			GridBagConstraints gbc_textItemNameField = new GridBagConstraints();
			gbc_textItemNameField.gridwidth = 2;
			gbc_textItemNameField.insets = new Insets(0, 0, 5, 5);
			gbc_textItemNameField.fill = GridBagConstraints.HORIZONTAL;
			gbc_textItemNameField.gridx = 1;
			gbc_textItemNameField.gridy = 0;
			contentPanel.add(textItemNameField, gbc_textItemNameField);
			textItemNameField.setColumns(10);
		}
		{
			JLabel lblModAssetPath = new JLabel("Mod Graphics Path");
			GridBagConstraints gbc_lblModAssetPath = new GridBagConstraints();
			gbc_lblModAssetPath.anchor = GridBagConstraints.EAST;
			gbc_lblModAssetPath.insets = new Insets(0, 0, 5, 5);
			gbc_lblModAssetPath.gridx = 0;
			gbc_lblModAssetPath.gridy = 1;
			contentPanel.add(lblModAssetPath, gbc_lblModAssetPath);
		}
		{
			textModAssetPath = new JTextField();
			textModAssetPath.setEditable(false);
			GridBagConstraints gbc_textModAssetPath = new GridBagConstraints();
			gbc_textModAssetPath.insets = new Insets(0, 0, 5, 5);
			gbc_textModAssetPath.fill = GridBagConstraints.HORIZONTAL;
			gbc_textModAssetPath.gridx = 1;
			gbc_textModAssetPath.gridy = 1;
			contentPanel.add(textModAssetPath, gbc_textModAssetPath);
			textModAssetPath.setColumns(10);
		}
		{
			JButton btnModAssetPath = new JButton("Change");
			btnModAssetPath.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					modGraphicsFileChooser.setDialogTitle("Select Managed Sprite Output Graphics Folder");
					int returnVal = modGraphicsFileChooser.showOpenDialog(CreateManagedEntity.this);
			        if (returnVal == JFileChooser.APPROVE_OPTION) {
			            File selectedFolder = modGraphicsFileChooser.getSelectedFile();
			            
						try {
							String path = project.getRelativeToMod(selectedFolder.getPath());
							textModAssetPath.setText(path);
						} catch (ProjectSetupException e1) {
							UIUtils.showError(e1, CreateManagedEntity.this);
						}
			            
			        }
			        
				}
			});
			GridBagConstraints gbc_btnModAssetPath = new GridBagConstraints();
			gbc_btnModAssetPath.insets = new Insets(0, 0, 5, 0);
			gbc_btnModAssetPath.gridx = 2;
			gbc_btnModAssetPath.gridy = 1;
			contentPanel.add(btnModAssetPath, gbc_btnModAssetPath);
		}
		{
			JLabel lblWorkingAssetPath = new JLabel("Working Asset Path");
			GridBagConstraints gbc_lblWorkingAssetPath = new GridBagConstraints();
			gbc_lblWorkingAssetPath.anchor = GridBagConstraints.EAST;
			gbc_lblWorkingAssetPath.insets = new Insets(0, 0, 0, 5);
			gbc_lblWorkingAssetPath.gridx = 0;
			gbc_lblWorkingAssetPath.gridy = 2;
			contentPanel.add(lblWorkingAssetPath, gbc_lblWorkingAssetPath);
		}
		{
			textWorkingAssetPath = new JTextField();
			textWorkingAssetPath.setEditable(false);
			GridBagConstraints gbc_textWorkingAssetPath = new GridBagConstraints();
			gbc_textWorkingAssetPath.insets = new Insets(0, 0, 0, 5);
			gbc_textWorkingAssetPath.fill = GridBagConstraints.HORIZONTAL;
			gbc_textWorkingAssetPath.gridx = 1;
			gbc_textWorkingAssetPath.gridy = 2;
			contentPanel.add(textWorkingAssetPath, gbc_textWorkingAssetPath);
			textWorkingAssetPath.setColumns(10);
		}
		{
			JButton btnWorkingAssetPath = new JButton("Change");
			btnWorkingAssetPath.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					
					workingFileDir.setDialogTitle("Select Managed Sprite Working Folder (Render Output)");
					int returnVal = workingFileDir.showOpenDialog(CreateManagedEntity.this);
			        if (returnVal == JFileChooser.APPROVE_OPTION) {
			            File selectedFolder = workingFileDir.getSelectedFile();
			            
						try {
							String path = project.getRelativetoWorkingDirectory(selectedFolder.getPath());
							textWorkingAssetPath.setText(path);
						} catch (ProjectSetupException e1) {
							UIUtils.showError(e1, CreateManagedEntity.this);
						}
			            
			        }
					
				}
			});
			GridBagConstraints gbc_btnWorkingAssetPath = new GridBagConstraints();
			gbc_btnWorkingAssetPath.gridx = 2;
			gbc_btnWorkingAssetPath.gridy = 2;
			contentPanel.add(btnWorkingAssetPath, gbc_btnWorkingAssetPath);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						ManagedSprite ms = new ManagedSprite();
						ms.setItemName(textItemNameField.getText());
						try {
							ms.setLuaLibPath(project.getRelativeToMod(project.getGraphicsDefinitionLib().getAbsolutePath()+"\\"+ms.getItemName()+".lua"));
						} catch (ProjectSetupException e2) {
							UIUtils.showError(e2, CreateManagedEntity.this);
							e2.printStackTrace();
						}
						ms.setManagedSpriteName(ms.getItemName());
						ms.setModAssetPath(textModAssetPath.getText());
						ms.setWorkingAssetPath(textWorkingAssetPath.getText());
						
						try {
							project.getManagedEntities().addEntity(ms);
							project.saveManagedSprites();
							CreateManagedEntity.this.editorWindow.updateManagedSprites();
							CreateManagedEntity.this.dispose();
							
						} catch (ProjectSetupException e1) {
							UIUtils.showError(e1, CreateManagedEntity.this);
							e1.printStackTrace();
						}
						
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						CreateManagedEntity.this.dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}
