package com.graymatter.spritemanager.ui;

import java.awt.Component;
import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.graymatter.spritemanager.Project;
import com.graymatter.spritemanager.Sprite;
import com.graymatter.spritemanager.entities.ManagedSprite;
import com.graymatter.spritemanager.entities.SpriteType;
import com.graymatter.spritemanager.exceptions.ProjectSetupException;
import com.graymatter.spritemanager.exceptions.SpriteBuilderException;
import com.graymatter.spritemanager.workers.SpriteBuilder;

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

	private Project project;
	private JList<ManagedSprite> managedSpritesList;
	private JList stripesList;
	private ImagePanel spriteViewerPanel;
	private JList<Sprite> tilesList;
	private JPanel managedSpriteDetails;
	private JPanel tilesPanel;
	private JPanel stripesPanel;
	private JPanel consolePanel;
	private JPanel tileViewPanel;
	private JPanel spriteListPanel;
	private JTextArea consoleTextArea;
	
	JFileChooser modGraphicsFileChooser;
	JFileChooser workingFileDir;
	public void setProject(Project project) {
		this.project = project;		
		managedSpriteSelected(false);
		updateManagedSprites();
		//new ConsoleOutputStream(consoleTextArea);
		modDirtextField.setText(project.getProjectDirectory().getAbsolutePath());
		workingDirTextField.setText(project.getWorkingDirectory().getAbsolutePath());
		
		
		modGraphicsFileChooser = new JFileChooser(project.getGraphicsDirectory().getAbsolutePath());
		modGraphicsFileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		
		workingFileDir = new JFileChooser(project.getWorkingDirectory().getAbsolutePath());
		workingFileDir.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		
	}
	
	public void updateManagedSprites(){
		
		DefaultListModel<ManagedSprite> model = (DefaultListModel<ManagedSprite>) managedSpritesList.getModel();
		model.removeAllElements();
		for (ManagedSprite me :  project.getManagedEntities().getSprites()){
			model.addElement(me);
		}
	}
	
	private ManagedSprite selectedSprite;
	private JComboBox<SpriteType> spriteTypeComboBox;
	
	private JTextField modAssetPathField;
	private JTextField workingAssetPathField;
	private JTextField luaLibPathField;
	private JTextField itemNameField;
	private JTextField filePatternText;
	private JTextField managedspriteName;
	private JButton btnDelete;
	private JTextField workingDirTextField;
	private JTextField modDirtextField;
	private JProgressBar progressBar;
	
	public void updateManagedSpriteDetails() {
		if (selectedSprite!=null){
			modAssetPathField.setText(selectedSprite.getModAssetPath());
			workingAssetPathField.setText(selectedSprite.getWorkingAssetPath());
			luaLibPathField.setText(selectedSprite.getLuaLibPath());
			itemNameField.setText(selectedSprite.getItemName());
			managedspriteName.setText(selectedSprite.getManagedSpriteName());
			filePatternText.setText(selectedSprite.getFilePattern());
			spriteTypeComboBox.setSelectedItem(selectedSprite.getType());
			spriteViewerPanel.setImage(null);
			clearTilesList();
			
		} else {
			
			clearManagedSpriteDetails();
			
		}
		
		
	}
	
	public void clearManagedSpriteDetails(){
		modAssetPathField.setText("");
		workingAssetPathField.setText("");
		luaLibPathField.setText("");
		itemNameField.setText("");
		managedspriteName.setText("");
		filePatternText.setText("");
		spriteTypeComboBox.setSelectedItem(null);
		spriteViewerPanel.setImage(null);
		clearTilesList();
		
	}
	
	/**
	 * Create the frame.
	 */
	public ProjectEditorWindow() {
		

		
		UIUtils.setIcon(this);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1024 , 768);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 59, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		spriteListPanel = new JPanel();
		spriteListPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
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
		gbc_managedSpritesMenuPanel.insets = new Insets(5, 5, 5, 0);
		gbc_managedSpritesMenuPanel.gridx = 0;
		gbc_managedSpritesMenuPanel.gridy = 0;
		spriteListPanel.add(managedSpritesMenuPanel, gbc_managedSpritesMenuPanel);
		managedSpritesMenuPanel.setLayout(new BoxLayout(managedSpritesMenuPanel, BoxLayout.X_AXIS));
		
		JLabel lblNewLabel = new JLabel("Managed Sprites");
		managedSpritesMenuPanel.add(lblNewLabel);
		
		Component horizontalGlue = Box.createHorizontalGlue();
		managedSpritesMenuPanel.add(horizontalGlue);
		
		JButton btnNew = new JButton("New");
		btnNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CreateManagedEntity dialog = new CreateManagedEntity(ProjectEditorWindow.this, true);
				dialog.setVisible(true);
			}
		});
		managedSpritesMenuPanel.add(btnNew);
		
		btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				project.getManagedEntities().removeEntity(selectedSprite);
				selectedSprite = null;
				updateManagedSprites();
				updateManagedSpriteDetails();
				managedSpriteSelected(false);
				btnDelete.setEnabled(false);
				try {
					project.saveManagedSprites();
				} catch (ProjectSetupException e1) {
					UIUtils.showError(e1, ProjectEditorWindow.this);
					e1.printStackTrace();
				}
			}
		});
		btnDelete.setEnabled(false);
		managedSpritesMenuPanel.add(btnDelete);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_2 = new GridBagConstraints();
		gbc_scrollPane_2.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_2.gridx = 0;
		gbc_scrollPane_2.gridy = 1;
		spriteListPanel.add(scrollPane_2, gbc_scrollPane_2);
		
		managedSpritesList = new JList<ManagedSprite>();
		scrollPane_2.setViewportView(managedSpritesList);
		managedSpritesList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				managedSpriteSelected(true);
				selectedSprite = managedSpritesList.getSelectedValue();
				updateManagedSpriteDetails();
				btnDelete.setEnabled(true);
				
			}

		});
		managedSpritesList.setModel(new DefaultListModel<ManagedSprite>());
		managedSpritesList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		managedSpriteDetails = new JPanel();
		managedSpriteDetails.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		GridBagConstraints gbc_managedSpriteDetails = new GridBagConstraints();
		gbc_managedSpriteDetails.insets = new Insets(0, 0, 5, 5);
		gbc_managedSpriteDetails.fill = GridBagConstraints.BOTH;
		gbc_managedSpriteDetails.gridx = 1;
		gbc_managedSpriteDetails.gridy = 0;
		contentPane.add(managedSpriteDetails, gbc_managedSpriteDetails);
		GridBagLayout gbl_managedSpriteDetails = new GridBagLayout();
		gbl_managedSpriteDetails.columnWidths = new int[]{0, 0};
		gbl_managedSpriteDetails.rowHeights = new int[]{0, 0, 0};
		gbl_managedSpriteDetails.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_managedSpriteDetails.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		managedSpriteDetails.setLayout(gbl_managedSpriteDetails);
		
		JPanel detailsTitlePanel = new JPanel();
		GridBagConstraints gbc_detailsTitlePanel = new GridBagConstraints();
		gbc_detailsTitlePanel.insets = new Insets(0, 0, 5, 0);
		gbc_detailsTitlePanel.fill = GridBagConstraints.BOTH;
		gbc_detailsTitlePanel.gridx = 0;
		gbc_detailsTitlePanel.gridy = 0;
		managedSpriteDetails.add(detailsTitlePanel, gbc_detailsTitlePanel);
		
		JLabel lblManagedSprieDetails = new JLabel("Managed Sprie Details");
		detailsTitlePanel.add(lblManagedSprieDetails);
		
		JPanel panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.insets = new Insets(5, 5, 5, 5);
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 1;
		managedSpriteDetails.add(panel_1, gbc_panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{};
		gbl_panel_1.rowHeights = new int[] {};
		gbl_panel_1.columnWeights = new double[]{};
		gbl_panel_1.rowWeights = new double[]{};
		panel_1.setLayout(gbl_panel_1);
		
		JLabel lblManagedSpriteName = new JLabel("Managed Sprite Name");
		GridBagConstraints gbc_lblManagedSpriteName = new GridBagConstraints();
		gbc_lblManagedSpriteName.anchor = GridBagConstraints.EAST;
		gbc_lblManagedSpriteName.insets = new Insets(5, 5, 5, 5);
		gbc_lblManagedSpriteName.gridx = 0;
		gbc_lblManagedSpriteName.gridy = 0;
		panel_1.add(lblManagedSpriteName, gbc_lblManagedSpriteName);
		
		managedspriteName = new JTextField();
		managedspriteName.setEditable(false);
		managedspriteName.setColumns(10);
		GridBagConstraints gbc_managedspriteName = new GridBagConstraints();
		gbc_managedspriteName.gridwidth = 2;
		gbc_managedspriteName.insets = new Insets(5, 5, 5, 0);
		gbc_managedspriteName.fill = GridBagConstraints.HORIZONTAL;
		gbc_managedspriteName.gridx = 1;
		gbc_managedspriteName.gridy = 0;
		panel_1.add(managedspriteName, gbc_managedspriteName);
		
		JLabel lblType = new JLabel("Sprite Type");
		GridBagConstraints gbc_lblType = new GridBagConstraints();
		gbc_lblType.insets = new Insets(5, 5, 5, 5);
		gbc_lblType.anchor = GridBagConstraints.EAST;
		gbc_lblType.gridx = 0;
		gbc_lblType.gridy = 1;
		panel_1.add(lblType, gbc_lblType);
		
		spriteTypeComboBox = new JComboBox<SpriteType>();
		spriteTypeComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getActionCommand().equals("comboBoxChanged")){
					if (spriteTypeComboBox.getSelectedItem()==null) return;
					selectedSprite.setType((SpriteType) spriteTypeComboBox.getSelectedItem());
					try {
						project.saveManagedSprites();
					} catch (ProjectSetupException e1) {
						UIUtils.showError(e1, ProjectEditorWindow.this);
					}
				}
			}
		});
		spriteTypeComboBox.setModel(new DefaultComboBoxModel(SpriteType.values()));
		GridBagConstraints gbc_spriteTypeComboBox = new GridBagConstraints();
		gbc_spriteTypeComboBox.gridwidth = 2;
		gbc_spriteTypeComboBox.insets = new Insets(5, 5, 5, 0);
		gbc_spriteTypeComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_spriteTypeComboBox.gridx = 1;
		gbc_spriteTypeComboBox.gridy = 1;
		panel_1.add(spriteTypeComboBox, gbc_spriteTypeComboBox);
		
		JLabel lblProjectWorkingDir = new JLabel("Project Working Dir");
		lblProjectWorkingDir.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_lblProjectWorkingDir = new GridBagConstraints();
		gbc_lblProjectWorkingDir.anchor = GridBagConstraints.EAST;
		gbc_lblProjectWorkingDir.insets = new Insets(5, 5, 5, 5);
		gbc_lblProjectWorkingDir.gridx = 0;
		gbc_lblProjectWorkingDir.gridy = 2;
		panel_1.add(lblProjectWorkingDir, gbc_lblProjectWorkingDir);
		
		workingDirTextField = new JTextField();
		workingDirTextField.setEditable(false);
		GridBagConstraints gbc_workingDirTextField = new GridBagConstraints();
		gbc_workingDirTextField.gridwidth = 2;
		gbc_workingDirTextField.insets = new Insets(5, 5, 5, 0);
		gbc_workingDirTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_workingDirTextField.gridx = 1;
		gbc_workingDirTextField.gridy = 2;
		panel_1.add(workingDirTextField, gbc_workingDirTextField);
		workingDirTextField.setColumns(10);
		
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 2;
		gbc_panel.gridy = 3;
		panel_1.add(panel, gbc_panel);
		
		JButton btnChangeUpdate = new JButton("Change");
		panel.add(btnChangeUpdate);
		
		JButton btnNewButton = new JButton("Open");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Desktop.getDesktop().open(selectedSprite.getFullyQualifiedWorkingFile(project));
				} catch (ProjectSetupException | IOException e1) {
					UIUtils.showError(e1, ProjectEditorWindow.this);
					e1.printStackTrace();
				}
			}
		});
		panel.add(btnNewButton);
		btnChangeUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				workingFileDir.setDialogTitle("Select Managed Sprite Output Graphics Folder");
				int returnVal = workingFileDir.showOpenDialog(ProjectEditorWindow.this);
		        if (returnVal == JFileChooser.APPROVE_OPTION) {
		            File selectedFolder = workingFileDir.getSelectedFile();
		            
					try {
						String path = project.getRelativetoWorkingDirectory(selectedFolder.getPath());
						selectedSprite.setWorkingAssetPath(path);
						updateManagedSpriteDetails();
						project.saveManagedSprites();
					} catch (ProjectSetupException e1) {
						UIUtils.showError(e1, ProjectEditorWindow.this);
					}
		            
		        }
				
				
			}
		});
		
		JLabel lblModDir = new JLabel("Mod Dir");
		lblModDir.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_lblModDir = new GridBagConstraints();
		gbc_lblModDir.anchor = GridBagConstraints.EAST;
		gbc_lblModDir.insets = new Insets(5, 5, 5, 5);
		gbc_lblModDir.gridx = 0;
		gbc_lblModDir.gridy = 4;
		panel_1.add(lblModDir, gbc_lblModDir);
		
		modDirtextField = new JTextField();
		modDirtextField.setEditable(false);
		GridBagConstraints gbc_modDirtextField = new GridBagConstraints();
		gbc_modDirtextField.gridwidth = 2;
		gbc_modDirtextField.insets = new Insets(5, 5, 5, 0);
		gbc_modDirtextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_modDirtextField.gridx = 1;
		gbc_modDirtextField.gridy = 4;
		panel_1.add(modDirtextField, gbc_modDirtextField);
		modDirtextField.setColumns(10);
		
		JLabel lblModassetpath = new JLabel("Mod Asset Path");
		GridBagConstraints gbc_lblModassetpath = new GridBagConstraints();
		gbc_lblModassetpath.anchor = GridBagConstraints.EAST;
		gbc_lblModassetpath.insets = new Insets(5, 5, 5, 5);
		gbc_lblModassetpath.gridx = 0;
		gbc_lblModassetpath.gridy = 5;
		panel_1.add(lblModassetpath, gbc_lblModassetpath);
		
		modAssetPathField = new JTextField();
		modAssetPathField.setEditable(false);
		GridBagConstraints gbc_modAssetPathField = new GridBagConstraints();
		gbc_modAssetPathField.insets = new Insets(5, 5, 5, 5);
		gbc_modAssetPathField.fill = GridBagConstraints.HORIZONTAL;
		gbc_modAssetPathField.gridx = 1;
		gbc_modAssetPathField.gridy = 5;
		panel_1.add(modAssetPathField, gbc_modAssetPathField);
		modAssetPathField.setColumns(10);
		
		JLabel lblWorkingAssetPath = new JLabel("Working Asset Path");
		GridBagConstraints gbc_lblWorkingAssetPath = new GridBagConstraints();
		gbc_lblWorkingAssetPath.anchor = GridBagConstraints.EAST;
		gbc_lblWorkingAssetPath.insets = new Insets(5, 5, 5, 5);
		gbc_lblWorkingAssetPath.gridx = 0;
		gbc_lblWorkingAssetPath.gridy = 3;
		panel_1.add(lblWorkingAssetPath, gbc_lblWorkingAssetPath);
		
		workingAssetPathField = new JTextField();
		workingAssetPathField.setEditable(false);
		workingAssetPathField.setColumns(10);
		GridBagConstraints gbc_workingAssetPathField = new GridBagConstraints();
		gbc_workingAssetPathField.insets = new Insets(5, 5, 5, 5);
		gbc_workingAssetPathField.fill = GridBagConstraints.HORIZONTAL;
		gbc_workingAssetPathField.gridx = 1;
		gbc_workingAssetPathField.gridy = 3;
		panel_1.add(workingAssetPathField, gbc_workingAssetPathField);
		
		JPanel panel_4 = new JPanel();
		GridBagConstraints gbc_panel_4 = new GridBagConstraints();
		gbc_panel_4.insets = new Insets(0, 0, 5, 0);
		gbc_panel_4.fill = GridBagConstraints.BOTH;
		gbc_panel_4.gridx = 2;
		gbc_panel_4.gridy = 5;
		panel_1.add(panel_4, gbc_panel_4);
		
		JButton button = new JButton("Change");
		panel_4.add(button);
		
		JButton btnNewButton_1 = new JButton("Open");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					Desktop.getDesktop().open(selectedSprite.getFullyQualifiedModGraphicsAssetFile(project));
				} catch (ProjectSetupException | IOException e1) {
					UIUtils.showError(e1, ProjectEditorWindow.this);
					e1.printStackTrace();
				}
				
			}
		});
		panel_4.add(btnNewButton_1);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				

				modGraphicsFileChooser.setDialogTitle("Select Managed Sprite Output Graphics Folder");
				int returnVal = modGraphicsFileChooser.showOpenDialog(ProjectEditorWindow.this);
		        if (returnVal == JFileChooser.APPROVE_OPTION) {
		            File selectedFolder = modGraphicsFileChooser.getSelectedFile();
		            
					try {
						String path = project.getRelativeToMod(selectedFolder.getPath());
						selectedSprite.setModAssetPath(path);
						updateManagedSpriteDetails();
						project.saveManagedSprites();
					} catch (ProjectSetupException e1) {
						UIUtils.showError(e1, ProjectEditorWindow.this);
					}
		            
		        }
				
				
				
				
			}
		});
		
		JLabel lblLuaLibPath = new JLabel("LUA lib Path");
		GridBagConstraints gbc_lblLuaLibPath = new GridBagConstraints();
		gbc_lblLuaLibPath.anchor = GridBagConstraints.EAST;
		gbc_lblLuaLibPath.insets = new Insets(5, 5, 5, 5);
		gbc_lblLuaLibPath.gridx = 0;
		gbc_lblLuaLibPath.gridy = 6;
		panel_1.add(lblLuaLibPath, gbc_lblLuaLibPath);
		
		luaLibPathField = new JTextField();
		luaLibPathField.setEditable(false);
		luaLibPathField.setColumns(10);
		GridBagConstraints gbc_luaLibPathField = new GridBagConstraints();
		gbc_luaLibPathField.gridwidth = 2;
		gbc_luaLibPathField.insets = new Insets(5, 5, 5, 0);
		gbc_luaLibPathField.fill = GridBagConstraints.HORIZONTAL;
		gbc_luaLibPathField.gridx = 1;
		gbc_luaLibPathField.gridy = 6;
		panel_1.add(luaLibPathField, gbc_luaLibPathField);
		
		JLabel lblItemName = new JLabel("Item Name");
		GridBagConstraints gbc_lblItemName = new GridBagConstraints();
		gbc_lblItemName.anchor = GridBagConstraints.EAST;
		gbc_lblItemName.insets = new Insets(5, 5, 5, 5);
		gbc_lblItemName.gridx = 0;
		gbc_lblItemName.gridy = 7;
		panel_1.add(lblItemName, gbc_lblItemName);
		
		itemNameField = new JTextField();
		itemNameField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				try {
					project.saveManagedSprites();
				} catch (ProjectSetupException e) {
					UIUtils.showError(e, ProjectEditorWindow.this);
					e.printStackTrace();
				}
			}
		});
		itemNameField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				selectedSprite.setItemName(itemNameField.getText());
			}
		});
		itemNameField.setColumns(10);
		GridBagConstraints gbc_itemNameField = new GridBagConstraints();
		gbc_itemNameField.gridwidth = 2;
		gbc_itemNameField.insets = new Insets(5, 5, 5, 0);
		gbc_itemNameField.fill = GridBagConstraints.HORIZONTAL;
		gbc_itemNameField.gridx = 1;
		gbc_itemNameField.gridy = 7;
		panel_1.add(itemNameField, gbc_itemNameField);
		
		JSeparator separator = new JSeparator();
		GridBagConstraints gbc_separator = new GridBagConstraints();
		gbc_separator.insets = new Insets(5, 5, 5, 0);
		gbc_separator.gridwidth = 3;
		gbc_separator.gridx = 0;
		gbc_separator.gridy = 8;
		panel_1.add(separator, gbc_separator);
		
		JLabel lblFilePattern = new JLabel("File Pattern");
		lblFilePattern.setToolTipText("<html>For files with filename myRenderoutputfile001.png the corresponding pattern would be <br/><br/>myRenderoutputfile%03d.png<br/><br/><br/>%d = any integer and %03d = any ineger padded to length 3 with zeros.<br/><br/><br/>The number of leading zeros must be specified for fiel detection to work. only integers can currently be used.</html>");
		GridBagConstraints gbc_lblFilePattern = new GridBagConstraints();
		gbc_lblFilePattern.anchor = GridBagConstraints.EAST;
		gbc_lblFilePattern.insets = new Insets(0, 5, 5, 5);
		gbc_lblFilePattern.gridx = 0;
		gbc_lblFilePattern.gridy = 9;
		panel_1.add(lblFilePattern, gbc_lblFilePattern);
		
		filePatternText = new JTextField();
		filePatternText.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				try {
					project.saveManagedSprites();
				} catch (ProjectSetupException e1) {
					UIUtils.showError(e1, ProjectEditorWindow.this);
					e1.printStackTrace();
				}
			}
		});
		filePatternText.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				selectedSprite.setFilePattern(filePatternText.getText());
			}
		});
		GridBagConstraints gbc_filePatternText = new GridBagConstraints();
		gbc_filePatternText.gridwidth = 2;
		gbc_filePatternText.insets = new Insets(0, 5, 5, 0);
		gbc_filePatternText.fill = GridBagConstraints.HORIZONTAL;
		gbc_filePatternText.gridx = 1;
		gbc_filePatternText.gridy = 9;
		panel_1.add(filePatternText, gbc_filePatternText);
		filePatternText.setColumns(10);
		
		progressBar = new JProgressBar();
		GridBagConstraints gbc_progressBar = new GridBagConstraints();
		gbc_progressBar.fill = GridBagConstraints.HORIZONTAL;
		gbc_progressBar.gridwidth = 3;
		gbc_progressBar.insets = new Insets(5, 5, 5, 5);
		gbc_progressBar.gridx = 0;
		gbc_progressBar.gridy = 10;
		panel_1.add(progressBar, gbc_progressBar);
		
		Component verticalStrut = Box.createVerticalStrut(20);
		GridBagConstraints gbc_verticalStrut = new GridBagConstraints();
		gbc_verticalStrut.insets = new Insets(0, 0, 5, 0);
		gbc_verticalStrut.gridx = 2;
		gbc_verticalStrut.gridy = 10;
		panel_1.add(verticalStrut, gbc_verticalStrut);
		
		JPanel panel_9 = new JPanel();
		GridBagConstraints gbc_panel_9 = new GridBagConstraints();
		gbc_panel_9.gridwidth = 3;
		gbc_panel_9.fill = GridBagConstraints.BOTH;
		gbc_panel_9.gridx = 0;
		gbc_panel_9.gridy = 11;
		panel_1.add(panel_9, gbc_panel_9);
		panel_9.setLayout(new BoxLayout(panel_9, BoxLayout.X_AXIS));
		
		Component horizontalGlue_1 = Box.createHorizontalGlue();
		panel_9.add(horizontalGlue_1);
		
		JButton btnRescanSprites = new JButton("Rescan Sprites");
		btnRescanSprites.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					SpriteBuilder sb = new SpriteBuilder(selectedSprite.getFullyQualifiedWorkingPath(project), selectedSprite.getFilePattern(), ProjectEditorWindow.this);
					
					sb.addPropertyChangeListener(new PropertyChangeListener() {
						
						@Override
						public void propertyChange(PropertyChangeEvent evt) {
							 if ("progress".equals(evt.getPropertyName())) {
				                 progressBar.setValue((Integer)evt.getNewValue());
				             }
						}
					});
					sb.execute();
				} catch (SpriteBuilderException e1) {
					UIUtils.showError(e1, ProjectEditorWindow.this);
					e1.printStackTrace();
				}
				
				
				
			}
		});
		panel_9.add(btnRescanSprites);
		
		Component horizontalStrut = Box.createHorizontalStrut(20);
		panel_9.add(horizontalStrut);
		
		JButton btnGenerateStripes = new JButton("Generate Stripes");
		btnGenerateStripes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (tiles==null) UIUtils.showError(new Exception("No tiles loaded"), ProjectEditorWindow.this);
				
				
				
			}
		});
		panel_9.add(btnGenerateStripes);
		
		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		panel_9.add(horizontalStrut_1);
		
		JButton btnSaveToMod = new JButton("Save to mod");
		panel_9.add(btnSaveToMod);
		
		Component horizontalGlue_2 = Box.createHorizontalGlue();
		panel_9.add(horizontalGlue_2);
		
		tilesPanel = new JPanel();
		tilesPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		GridBagConstraints gbc_tilesPanel = new GridBagConstraints();
		gbc_tilesPanel.gridheight = 2;
		gbc_tilesPanel.insets = new Insets(5, 5, 5, 5);
		gbc_tilesPanel.fill = GridBagConstraints.BOTH;
		gbc_tilesPanel.gridx = 2;
		gbc_tilesPanel.gridy = 0;
		contentPane.add(tilesPanel, gbc_tilesPanel);
		GridBagLayout gbl_tilesPanel = new GridBagLayout();
		gbl_tilesPanel.columnWidths = new int[]{0, 0};
		gbl_tilesPanel.rowHeights = new int[]{0, 0, 0};
		gbl_tilesPanel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_tilesPanel.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		tilesPanel.setLayout(gbl_tilesPanel);
		
		JPanel panel_2 = new JPanel();
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.insets = new Insets(0, 0, 5, 0);
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.gridx = 0;
		gbc_panel_2.gridy = 0;
		tilesPanel.add(panel_2, gbc_panel_2);
		
		JLabel lblTiles = new JLabel("Tiles");
		panel_2.add(lblTiles);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
		gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_1.gridx = 0;
		gbc_scrollPane_1.gridy = 1;
		tilesPanel.add(scrollPane_1, gbc_scrollPane_1);
		
		tilesList = new JList<Sprite>();
		tilesList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				if (tilesList.getSelectedValue()==null) return;
				try {
					spriteViewerPanel.setImage(ImageIO.read(tilesList.getSelectedValue().getFile()));
				} catch (IOException e) {
					UIUtils.showError(e, ProjectEditorWindow.this);
					e.printStackTrace();
				}
			}
		});
		scrollPane_1.setViewportView(tilesList);
		tilesList.setModel(new DefaultListModel<Sprite>());
		tilesList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		SpriteListCellRenderer flcr = new SpriteListCellRenderer();
		tilesList.setCellRenderer(flcr);
		
		stripesPanel = new JPanel();
		stripesPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		GridBagConstraints gbc_stripesPanel = new GridBagConstraints();
		gbc_stripesPanel.gridheight = 2;
		gbc_stripesPanel.insets = new Insets(0, 0, 0, 5);
		gbc_stripesPanel.fill = GridBagConstraints.BOTH;
		gbc_stripesPanel.gridx = 0;
		gbc_stripesPanel.gridy = 1;
		contentPane.add(stripesPanel, gbc_stripesPanel);
		GridBagLayout gbl_stripesPanel = new GridBagLayout();
		gbl_stripesPanel.columnWidths = new int[]{0, 0};
		gbl_stripesPanel.rowHeights = new int[]{0, 0, 0};
		gbl_stripesPanel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_stripesPanel.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		stripesPanel.setLayout(gbl_stripesPanel);
		
		JPanel panel_3 = new JPanel();
		GridBagConstraints gbc_panel_3 = new GridBagConstraints();
		gbc_panel_3.insets = new Insets(0, 0, 5, 0);
		gbc_panel_3.fill = GridBagConstraints.BOTH;
		gbc_panel_3.gridx = 0;
		gbc_panel_3.gridy = 0;
		stripesPanel.add(panel_3, gbc_panel_3);
		
		JLabel lblStripes = new JLabel("Stripes");
		panel_3.add(lblStripes);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_3 = new GridBagConstraints();
		gbc_scrollPane_3.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_3.gridx = 0;
		gbc_scrollPane_3.gridy = 1;
		stripesPanel.add(scrollPane_3, gbc_scrollPane_3);
		
		stripesList = new JList();
		scrollPane_3.setViewportView(stripesList);
		
		consolePanel = new JPanel();
		consolePanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		GridBagConstraints gbc_consolePanel = new GridBagConstraints();
		gbc_consolePanel.gridheight = 2;
		gbc_consolePanel.insets = new Insets(0, 0, 0, 5);
		gbc_consolePanel.fill = GridBagConstraints.BOTH;
		gbc_consolePanel.gridx = 1;
		gbc_consolePanel.gridy = 1;
		contentPane.add(consolePanel, gbc_consolePanel);
		GridBagLayout gbl_consolePanel = new GridBagLayout();
		gbl_consolePanel.columnWidths = new int[]{0, 0};
		gbl_consolePanel.rowHeights = new int[]{0, 0, 0};
		gbl_consolePanel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_consolePanel.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		consolePanel.setLayout(gbl_consolePanel);
		
		JPanel panel_6 = new JPanel();
		GridBagConstraints gbc_panel_6 = new GridBagConstraints();
		gbc_panel_6.insets = new Insets(0, 0, 5, 0);
		gbc_panel_6.fill = GridBagConstraints.BOTH;
		gbc_panel_6.gridx = 0;
		gbc_panel_6.gridy = 0;
		consolePanel.add(panel_6, gbc_panel_6);
		
		JLabel lblConsole = new JLabel("Console");
		panel_6.add(lblConsole);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 1;
		consolePanel.add(scrollPane, gbc_scrollPane);
		
		consoleTextArea = new JTextArea();
		consoleTextArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
		scrollPane.setViewportView(consoleTextArea);
		
		tileViewPanel = new JPanel();
		tileViewPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		GridBagConstraints gbc_tileViewPanel = new GridBagConstraints();
		gbc_tileViewPanel.fill = GridBagConstraints.BOTH;
		gbc_tileViewPanel.gridx = 2;
		gbc_tileViewPanel.gridy = 2;
		contentPane.add(tileViewPanel, gbc_tileViewPanel);
		GridBagLayout gbl_tileViewPanel = new GridBagLayout();
		gbl_tileViewPanel.columnWidths = new int[]{0, 0};
		gbl_tileViewPanel.rowHeights = new int[]{0, 0, 0, 0};
		gbl_tileViewPanel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_tileViewPanel.rowWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		tileViewPanel.setLayout(gbl_tileViewPanel);
		
		JPanel panel_7 = new JPanel();
		GridBagConstraints gbc_panel_7 = new GridBagConstraints();
		gbc_panel_7.insets = new Insets(0, 0, 5, 0);
		gbc_panel_7.fill = GridBagConstraints.BOTH;
		gbc_panel_7.gridx = 0;
		gbc_panel_7.gridy = 0;
		tileViewPanel.add(panel_7, gbc_panel_7);
		
		JLabel lblSprite = new JLabel("Sprite");
		panel_7.add(lblSprite);
		
		Component horizontalStrut_2 = Box.createHorizontalStrut(200);
		GridBagConstraints gbc_horizontalStrut_2 = new GridBagConstraints();
		gbc_horizontalStrut_2.insets = new Insets(0, 0, 5, 0);
		gbc_horizontalStrut_2.gridx = 0;
		gbc_horizontalStrut_2.gridy = 1;
		tileViewPanel.add(horizontalStrut_2, gbc_horizontalStrut_2);
		
		JScrollPane scrollPane_4 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_4 = new GridBagConstraints();
		gbc_scrollPane_4.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_4.gridx = 0;
		gbc_scrollPane_4.gridy = 2;
		tileViewPanel.add(scrollPane_4, gbc_scrollPane_4);
		
		spriteViewerPanel = new ImagePanel();
		scrollPane_4.setViewportView(spriteViewerPanel);
		
		

		
	}
	
	public void managedSpriteSelected(boolean val){
		UIUtils.setPanelEnabled(managedSpriteDetails, val);;
		UIUtils.setPanelEnabled(tilesPanel, val);;
		UIUtils.setPanelEnabled(stripesPanel, val);;
		UIUtils.setPanelEnabled(tileViewPanel, val);;
	}

	public Project getProject() {
		return project;
	}

	private List<Sprite> tiles;
	
	public void setTiles(List<Sprite> outSprites) {
		this.tiles = outSprites;
		updateTilesList();
	}

	public void updateTilesList() {
		clearTilesList();
		DefaultListModel<Sprite> lm = (DefaultListModel<Sprite>) tilesList.getModel();
		for (Sprite sprite : tiles) {
			
			lm.addElement(sprite);
			
		}
	}
	
	public void clearTilesList(){
		DefaultListModel<Sprite> lm = (DefaultListModel<Sprite>) tilesList.getModel();
		lm.removeAllElements();
	}
	
	



}
