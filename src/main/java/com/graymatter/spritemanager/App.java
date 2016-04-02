package com.graymatter.spritemanager;

import java.awt.EventQueue;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.graymatter.spritemanager.ui.WorkspaceDialouge;

/**
 * Hello world!
 *
 */
public class App 
{
	public App(){
		StartUI();
	}
	
	private WorkspaceDialouge mw;
	
	private void StartUI(){
		
		
        EventQueue.invokeLater(new Runnable() {
			
			public void run() {
				mw = new WorkspaceDialouge();
				mw.setVisible(true);
				//mw.updateImage();
			}
		});
		
	}
	
    public static void main( String[] args ) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException
    {
    	UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    	new App();
    }
}
