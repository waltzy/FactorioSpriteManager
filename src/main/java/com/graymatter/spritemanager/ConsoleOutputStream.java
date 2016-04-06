package com.graymatter.spritemanager;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

import javax.swing.JTextArea;

import com.graymatter.spritemanager.util.TeePrintStream;

public class ConsoleOutputStream extends OutputStream {

	 private JTextArea textArea;
     
	    public ConsoleOutputStream(JTextArea textArea) {
	        this.textArea = textArea;
	        PrintStream printStream = new PrintStream(this);
	        try {
				System.setErr(new TeePrintStream(printStream, System.err));
				System.setOut(new TeePrintStream(printStream, System.out));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	     
	    @Override
	    public void write(int b) throws IOException {
	        // redirects data to the text area
	        textArea.append(String.valueOf((char)b));
	        // scrolls the text area to the end of data
	        textArea.setCaretPosition(textArea.getDocument().getLength());
	    }

}
