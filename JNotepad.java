//
// Name: Ortiz, Matthew
// Project: # 3
// Due: 3-12-18
// Course: CS-245-01-w18
//
// Description:
// A brief notepad

import java.awt.*;
import java.awt.event.*;
import java.awt.print.PrinterException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.UndoManager;


public class JNotepad{
	String coppy = ""; boolean t = false; String cSave = ""; Boolean saved; Boolean isSaved=false;
	String saveLocation; String k;
	JFrame frame = new JFrame("Notepad");
	JTextArea text = new JTextArea();
	JMenuBar menu = new JMenuBar();
	JMenu file = new JMenu("File");
	JMenu edit = new JMenu("Edit");
	JMenu format = new JMenu("Format");
	JMenu view = new JMenu("View");
	JMenu help = new JMenu("Help");
	JPopupMenu pop = new JPopupMenu();
	int savedIndex = 0;

		public static void main(String args[]){
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
			JNotepad note = new JNotepad();
			note.createFrame();
			note.fileBar();
			note.editBar();
			note.formatBar();
			note.view();
			note.helpBar();
			note.addToFrame();
		}});}
		
public void createFrame(){
	//Frame basics
	frame.setLayout(new BorderLayout());
	frame.setSize(600, 500);
	ImageIcon icon = new ImageIcon("JNotepad.png");
	frame.setIconImage(icon.getImage());
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setLocationRelativeTo(null);
	frame.setVisible(true);
	
	file.setMnemonic('F');
	edit.setMnemonic('E');
	format.setMnemonic('o');
	view.setMnemonic('V');
	help.setMnemonic('H');
	

	
}
	
public void fileBar(){

	JMenuItem neww = new JMenuItem("New", 'N');
    neww.addActionListener ( new ActionListener()  
    {  
        public void actionPerformed( ActionEvent e )  
        {  
        	JFrame f= new JFrame();  
        	JButton yes = new JButton("Yes");
        	JButton no = new JButton("No");
        	JLabel lAbout = new JLabel("Would you like to make a new File?",SwingConstants.CENTER);
 	        JDialog dAbout = new JDialog(f , "New File", true);  
 	        dAbout.setDefaultCloseOperation(dAbout.DISPOSE_ON_CLOSE);
 	        dAbout.setLayout( new BorderLayout() );  
 	        dAbout.getRootPane().setDefaultButton(no);
 	        yes.addActionListener ( new ActionListener()  
 	        {  
 	            public void actionPerformed( ActionEvent e )  
 	            {  		
 	            		dAbout.dispose();
 	                	checkSave();
 	                	if(saved == true){}
 	                	else{
 	                		JFrame f= new JFrame();  
 	                    	JButton yes = new JButton("Yes");
 	                    	JButton no = new JButton("No");
 	                    	JLabel lAbout = new JLabel("Would you like to save File?",SwingConstants.CENTER);
 	             	        JDialog dAbout = new JDialog(f , "Save?", true);  
 	             	        dAbout.setDefaultCloseOperation(dAbout.DISPOSE_ON_CLOSE);
 	             	        dAbout.setLayout( new BorderLayout() );  
 	             	        dAbout.getRootPane().setDefaultButton(no);
 	             	        yes.addActionListener ( new ActionListener()  
 	             	        {  
 	             	            public void actionPerformed( ActionEvent e )  
 	             	            {  
 	             	            	if(isSaved == true){
 	             	           		PrintWriter out = null;
 	             	           		File n = new File(saveLocation,k);
 	             	       			try {out = new PrintWriter(n);} catch (FileNotFoundException e1){e1.printStackTrace();}
 	             	               	String a = text.getText();
 	             	               	String[] b = a.split("");
 	             	           		for(int j = 0;j<b.length;j++){out.print(b[j]);}
 	             	           		out.flush();
 	             	           		out.close();
 	             	           		cSave = text.getText();
 	             	           	}
 	             	           	else{
 	             	           		PrintWriter out = null;
 	             	               	JFileChooser fc = new JFileChooser();
 	             	               	int result = fc.showSaveDialog(frame);
 	             	               	if (result == JFileChooser.APPROVE_OPTION){
 	             	               	File sf = fc.getCurrentDirectory();
 	             	               	saveLocation = sf.getPath();
 	             	               	k = sf.getName()+".txt";
 	             	               File nf = new File(sf,k);
 	             	       			try {out = new PrintWriter(nf);} catch (FileNotFoundException e1){e1.printStackTrace();}
 	             	               	String a = text.getText();
 	             	               	String[] b = a.split("");
 	             	           		for(int j = 0;j<b.length;j++){out.print(b[j]);}
 	             	           		out.flush();
 	             	           		out.close();}
 	             	               	isSaved = true;
 	             	               	cSave = text.getText();
 	             	           	}
 	             	            f.dispose();
 	             	            dAbout.dispose();
 	             	            text.setText("");
 	             	            }  
 	             	        });  
 	             	        
 	             	       no.addActionListener ( new ActionListener()  
 	            	        {  
 	            	            public void actionPerformed( ActionEvent e )  
 	            	            {  
 	            	            	dAbout.dispose();
 	            	            	text.setText("");
 	            	            }  
 	            	        });
 	             	        dAbout.add(lAbout, BorderLayout.CENTER);
 	             	        JPanel l = new JPanel(new FlowLayout());
 	             	        l.add(yes);
 	             	        l.add(no);
 	             	        dAbout.add(l,BorderLayout.SOUTH);   
 	             	        dAbout.setSize(300,100);  
 	             	        dAbout.setLocationRelativeTo(null);
 	             	        dAbout.setVisible(true);  
 	                    	
 	                    }  	
 	                	}
 	        });  
 	        
 	       no.addActionListener ( new ActionListener()  
	        {  
	            public void actionPerformed( ActionEvent e )  
	            {  
	            	dAbout.dispose();
	            }  
	        });
 	        dAbout.add(lAbout, BorderLayout.CENTER);
 	        JPanel l = new JPanel(new FlowLayout());
 	        l.add(yes);
 	        l.add(no);
 	        dAbout.add(l,BorderLayout.SOUTH);   
 	        dAbout.setSize(300,100);  
 	        dAbout.setLocationRelativeTo(null);
 	        dAbout.setVisible(true);  
        	
        }  
    });  
	
	JMenuItem open = new JMenuItem("Open...");
    open.addActionListener ( new ActionListener()  
    {  
        public void actionPerformed( ActionEvent e )  
        {  
        	checkSave();
        	if(saved == true){}
        	else{
        		JFrame f= new JFrame();  
            	JButton yes = new JButton("Yes");
            	JButton no = new JButton("No");
            	JLabel lAbout = new JLabel("Would you like to save File?",SwingConstants.CENTER);
     	        JDialog dAbout = new JDialog(f , "Save?", true);  
     	        dAbout.setDefaultCloseOperation(dAbout.DISPOSE_ON_CLOSE);
     	        dAbout.setLayout( new BorderLayout() );  
     	        dAbout.getRootPane().setDefaultButton(no);
     	        yes.addActionListener ( new ActionListener()  
     	        {  
     	            public void actionPerformed( ActionEvent e )  
     	            {  
     	            	if(isSaved == true){
     	           		PrintWriter out = null;
     	           		File n = new File(saveLocation,k);
     	       			try {out = new PrintWriter(n);} catch (FileNotFoundException e1){e1.printStackTrace();}
     	               	String a = text.getText();
     	               	String[] b = a.split("");
     	           		for(int j = 0;j<b.length;j++){out.print(b[j]);}
     	           		out.flush();
     	           		out.close();
     	           		cSave = text.getText();
     	           	}
     	           	else{
     	           		PrintWriter out = null;
     	               	JFileChooser fc = new JFileChooser();
     	               	int result = fc.showSaveDialog(frame);
     	               	if (result == JFileChooser.APPROVE_OPTION){
     	               	File sf = fc.getCurrentDirectory();
     	               	saveLocation = sf.getPath();
     	               	k = sf.getName()+".txt";
     	               File nf = new File(sf,k);
     	       			try {out = new PrintWriter(nf);} catch (FileNotFoundException e1){e1.printStackTrace();}
     	               	String a = text.getText();
     	               	String[] b = a.split("");
     	           		for(int j = 0;j<b.length;j++){out.print(b[j]);}
     	           		out.flush();
     	           		out.close();}
     	               	isSaved = true;
     	               	cSave = text.getText();
     	           	}
     	            f.dispose();
     	            }  
     	        });  
     	        
     	       no.addActionListener ( new ActionListener()  
    	        {  
    	            public void actionPerformed( ActionEvent e )  
    	            {  
    	            	dAbout.dispose();
    	            }  
    	        });
     	        dAbout.add(lAbout, BorderLayout.CENTER);
     	        JPanel l = new JPanel(new FlowLayout());
     	        l.add(yes);
     	        l.add(no);
     	        dAbout.add(l,BorderLayout.SOUTH);   
     	        dAbout.setSize(300,100);  
     	        dAbout.setLocationRelativeTo(null);
     	        dAbout.setVisible(true);  
            	
            }  	
        	
        	JFileChooser fc = new JFileChooser();
        	int result = fc.showOpenDialog(frame);
        	if (result == JFileChooser.APPROVE_OPTION){
        		File sFile = fc.getSelectedFile();
        		try {text.setText(getString(sFile));} catch (IOException e1) {e1.printStackTrace();}
        	
        	} } 
    });  
    
	JMenuItem save = new JMenuItem("Save");
    save.addActionListener ( new ActionListener()  
    {  
        public void actionPerformed( ActionEvent e )  
        {  
        	if(isSaved == true){
        		PrintWriter out = null;
        		File n = new File(saveLocation,k);
    			try {out = new PrintWriter(n);} catch (FileNotFoundException e1){e1.printStackTrace();}
            	String a = text.getText();
            	String[] b = a.split("");
        		for(int j = 0;j<b.length;j++){out.print(b[j]);}
        		out.flush();
        		out.close();
        		cSave = text.getText();
        	}
        	else{
        		PrintWriter out = null;
            	JFileChooser fc = new JFileChooser();
            	int result = fc.showSaveDialog(frame);
            	if (result == JFileChooser.APPROVE_OPTION){
        		File sf = fc.getCurrentDirectory();
    			saveLocation = sf.getPath();
    			k = fc.getSelectedFile().getName()+".txt";
    			File nf = new File(sf,k);
    			try {out = new PrintWriter(nf);} catch (FileNotFoundException e1){e1.printStackTrace();}
    			String a = text.getText();
            	String[] b = a.split("");
        		for(int j = 0;j<b.length;j++){out.print(b[j]);}
        		out.flush();
        		out.close();}
            	frame.setTitle(k);
            	isSaved = true;
            	cSave = text.getText();
        	}
        }  
    });  
	
	JMenuItem saveAs = new JMenuItem("SaveAs");
    saveAs.addActionListener ( new ActionListener()  
    {  
        public void actionPerformed( ActionEvent e )  
        {  
        
        	PrintWriter out = null;
        	JFileChooser fc = new JFileChooser();
        	int result = fc.showSaveDialog(frame);
        	if (result == JFileChooser.APPROVE_OPTION){
			File sf = fc.getCurrentDirectory();
			saveLocation = sf.getPath();
			k = sf.getName()+".txt";
			File nf = new File(sf,k);
			try {out = new PrintWriter(nf);} catch (FileNotFoundException e1){e1.printStackTrace();}
			String a = text.getText();
        	String[] b = a.split("");
    		for(int j = 0;j<b.length;j++){out.print(b[j]);}
    		out.flush();
    		out.close();}
        	frame.setTitle(k);
        	isSaved = true;
        	cSave = text.getText();
        }  
    }); 
    
	JMenuItem pageSetup = new JMenuItem("Page Setup",'u');
	pageSetup.setEnabled(false);
    save.addActionListener ( new ActionListener()  
    {  
        public void actionPerformed( ActionEvent e )  
        {  
        	
        }  
    });  
	
    
    JMenuItem print = new JMenuItem("Print");
    print.addActionListener ( new ActionListener()  
    {  
        public void actionPerformed( ActionEvent e )  
        {  
        	try {text.print();} catch (PrinterException e1) {e1.printStackTrace();}
        }  
    });  
	
	JMenuItem exit = new JMenuItem("Exit",'x');
    exit.addActionListener ( new ActionListener()  
    {  
        public void actionPerformed( ActionEvent e )  
        {  
        	checkSave();
        	if(saved == true){frame.dispose();}
        	else{
        		JFrame f= new JFrame();  
            	JButton yes = new JButton("Yes");
            	JButton no = new JButton("No");
            	JLabel lAbout = new JLabel("Would you like to save File?",SwingConstants.CENTER);
     	        JDialog dAbout = new JDialog(f , "Save?", true);  
     	        dAbout.setDefaultCloseOperation(dAbout.DISPOSE_ON_CLOSE);
     	        dAbout.setLayout( new BorderLayout() );  
     	        dAbout.getRootPane().setDefaultButton(no);
     	        yes.addActionListener ( new ActionListener()  
     	        {  
     	            public void actionPerformed( ActionEvent e )  
     	            {  
     	            	if(isSaved == true){
     	           		PrintWriter out = null;
     	           		File n = new File(saveLocation,k);
     	       			try {out = new PrintWriter(n);} catch (FileNotFoundException e1){e1.printStackTrace();}
     	               	String a = text.getText();
     	               	String[] b = a.split("");
     	           		for(int j = 0;j<b.length;j++){out.print(b[j]);}
     	           		out.flush();
     	           		out.close();
     	           		cSave = text.getText();
     	           	}
     	           	else{
     	           		PrintWriter out = null;
     	               	JFileChooser fc = new JFileChooser();
     	               	int result = fc.showSaveDialog(frame);
     	               	if (result == JFileChooser.APPROVE_OPTION){
     	               	File sf = fc.getCurrentDirectory();
     	               	saveLocation = sf.getPath();
     	               	k = sf.getName()+".txt";
     	               File nf = new File(sf,k);
     	       			try {out = new PrintWriter(nf);} catch (FileNotFoundException e1){e1.printStackTrace();}
     	               	String a = text.getText();
     	               	String[] b = a.split("");
     	           		for(int j = 0;j<b.length;j++){out.print(b[j]);}
     	           		out.flush();
     	           		out.close();}
     	               	isSaved = true;
     	               	cSave = text.getText();
     	           	}
     	            frame.dispose();
     	            f.dispose();
     	            }  
     	        });  
     	        
     	       no.addActionListener ( new ActionListener()  
    	        {  
    	            public void actionPerformed( ActionEvent e )  
    	            {  
    	            	dAbout.dispose();
    	            	frame.dispose();
    	            }  
    	        });
     	        dAbout.add(lAbout, BorderLayout.CENTER);
     	        JPanel l = new JPanel(new FlowLayout());
     	        l.add(yes);
     	        l.add(no);
     	        dAbout.add(l,BorderLayout.SOUTH);   
     	        dAbout.setSize(300,100);  
     	        dAbout.setLocationRelativeTo(null);
     	        dAbout.setVisible(true);  
            	
            }  
        		
        		
        	}
    });  
    
     frame.addWindowListener(new WindowAdapter() {
	    public void windowClosing(WindowEvent e) {
	    	checkSave();
        	if(saved == true){frame.dispose();}
        	else{
        		JFrame f= new JFrame();  
            	JButton yes = new JButton("Yes");
            	JButton no = new JButton("No");
            	JLabel lAbout = new JLabel("Would you like to save File?",SwingConstants.CENTER);
     	        JDialog dAbout = new JDialog(f , "Save?", true);  
     	        dAbout.setDefaultCloseOperation(dAbout.DISPOSE_ON_CLOSE);
     	        dAbout.setLayout( new BorderLayout() );  
     	        dAbout.getRootPane().setDefaultButton(no);
     	        yes.addActionListener ( new ActionListener()  
     	        {  
     	            public void actionPerformed( ActionEvent e )  
     	            {  
     	            	if(isSaved == true){
     	           		PrintWriter out = null;
     	           		File n = new File(saveLocation,k);
     	       			try {out = new PrintWriter(n);} catch (FileNotFoundException e1){e1.printStackTrace();}
     	               	String a = text.getText();
     	               	String[] b = a.split("");
     	           		for(int j = 0;j<b.length;j++){out.print(b[j]);}
     	           		out.flush();
     	           		out.close();
     	           		cSave = text.getText();
     	           	}
     	           	else{
     	           		PrintWriter out = null;
     	               	JFileChooser fc = new JFileChooser();
     	               	int result = fc.showSaveDialog(frame);
     	               	if (result == JFileChooser.APPROVE_OPTION){
     	               	File sf = fc.getCurrentDirectory();
     	               	saveLocation = sf.getPath();
     	               	k = sf.getName()+".txt";
     	               File nf = new File(sf,k);
     	       			try {out = new PrintWriter(nf);} catch (FileNotFoundException e1){e1.printStackTrace();}
     	               	String a = text.getText();
     	               	String[] b = a.split("");
     	           		for(int j = 0;j<b.length;j++){out.print(b[j]);}
     	           		out.flush();
     	           		out.close();}
     	               	isSaved = true;
     	               	cSave = text.getText();
     	           	}
     	            frame.dispose();
     	            f.dispose();
     	            }  
     	        });  
     	        
     	       no.addActionListener ( new ActionListener()  
    	        {  
    	            public void actionPerformed( ActionEvent e )  
    	            {  
    	            	dAbout.dispose();
    	            	frame.dispose();
    	            }  
    	        });
     	        dAbout.add(lAbout, BorderLayout.CENTER);
     	        JPanel l = new JPanel(new FlowLayout());
     	        l.add(yes);
     	        l.add(no);
     	        dAbout.add(l,BorderLayout.SOUTH);   
     	        dAbout.setSize(300,100);  
     	        dAbout.setLocationRelativeTo(null);
     	        dAbout.setVisible(true);  
	    	
	    }
         }    
	});	
    
	KeyStroke aNew = KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_DOWN_MASK);
	KeyStroke aOpen = KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_DOWN_MASK);
	KeyStroke aSave = KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK);
	KeyStroke aPrint = KeyStroke.getKeyStroke(KeyEvent.VK_P, KeyEvent.CTRL_DOWN_MASK);
    
	neww.setAccelerator(aNew);
	open.setAccelerator(aOpen);
	save.setAccelerator(aSave);
	print.setAccelerator(aPrint);
	
	file.add(neww);
	file.add(open);
	file.add(save);
	file.add(saveAs);
	file.addSeparator();
	file.add(pageSetup);
	file.add(print);
	file.addSeparator();
	file.add(exit);
	menu.add(file);
}

public void editBar(){
	UndoManager undo = new UndoManager();
	
	JMenuItem cut = new JMenuItem("Cut");
    cut.addActionListener ( new ActionListener()  
    {  
        public void actionPerformed( ActionEvent e )  
        {  
        	text.cut();
        }  
    });  
	
	JMenuItem copy = new JMenuItem("Copy");
    copy.addActionListener ( new ActionListener()  
    {  
        public void actionPerformed( ActionEvent e )  
        {  
        	text.copy();
        }  
    });  
	JMenuItem paste = new JMenuItem("Paste");
    paste.addActionListener ( new ActionListener()  
    {  
        public void actionPerformed( ActionEvent e )  
        {  
        	text.paste();
        }  
    });  
	
	JMenuItem delete = new JMenuItem("Delete");
    delete.addActionListener ( new ActionListener()  
    {  
        public void actionPerformed( ActionEvent e )  
        {  
        	text.setText(text.getText().replace(text.getSelectedText(),""));
        }  
    });  
	
    
    text.getDocument().addUndoableEditListener(new UndoableEditListener(){
        public void undoableEditHappened(UndoableEditEvent e){
            undo.addEdit(e.getEdit());
        }
    });
    
    
    JMenuItem undoo = new JMenuItem("Undo");
    undoo.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent ae){
            try{
                undo.undo();
            }catch(CannotRedoException cre){
                cre.printStackTrace();
            }
        }
    });
    
    JMenuItem replace = new JMenuItem("Replace");
    replace.addActionListener ( new ActionListener()  
    {  
        public void actionPerformed( ActionEvent e )  
        {  
        	JFrame f= new JFrame();  
        	JButton b = new JButton("Replace Next");
        	JLabel find = new JLabel("Find: ");
        	JTextField fText = new JTextField();
        	JLabel replace = new JLabel("Replace: ");
        	JTextField rText = new JTextField();
 	        JDialog replacee = new JDialog(f , "Replace", false);  
 	        replacee.setDefaultCloseOperation(replacee.DISPOSE_ON_CLOSE);
 	        replacee.setLayout( new GridLayout(3,1) );  
 	        replacee.getRootPane().setDefaultButton(b);
 	        b.addActionListener ( new ActionListener()  
 	        {  
 	            public void actionPerformed( ActionEvent e )  
 	            {  
 	            	 String from = fText.getText();
 	            	 int start = text.getText().indexOf(from);
 	            	 if (start >= 0 && from.length() > 0)
 	                 text.replaceRange(rText.getText(), start, start + from.length());
 	            }  
 	        });  
 	        
 	        JButton c = new JButton("Replace All");
 	       c.addActionListener ( new ActionListener()  
	        {  
	            public void actionPerformed( ActionEvent e )  
	            {  
	            	 String temp = fText.getText();
	            	 if(temp.length()>0){
	            		 String t = text.getText();
	            		 t = t.replaceAll(fText.getText(),rText.getText());
	            		 text.setText(t);
	            	 }
	            }  
	        });  
 	        
 	        
			JPanel top = new JPanel(new BorderLayout());
			top.add(find,BorderLayout.WEST);
			top.add(fText,BorderLayout.CENTER);
			replacee.add(top);
			
			JPanel bottem = new JPanel(new BorderLayout());
			bottem.add(replace,BorderLayout.WEST);
			bottem.add(rText,BorderLayout.CENTER);
			replacee.add(bottem);  
			
			JPanel superBottem = new JPanel(new FlowLayout());
			superBottem.add(b);
			superBottem.add(c);
			
			replacee.add(superBottem);
			replacee.setSize(300,100);  
			replacee.setLocationRelativeTo(null);
			replacee.setVisible(true);
			        		
        }  
    });
    
    JMenuItem find = new JMenuItem("Find");
    find.addActionListener ( new ActionListener()  
    {  
        public void actionPerformed( ActionEvent e )  
        {  
        	JFrame f= new JFrame();  
        	JButton b = new JButton("Find");
        	JLabel find = new JLabel("Find: ");
        	JTextField fText = new JTextField();
 	        JDialog replacee = new JDialog(f , "Find", false);  
 	        replacee.setDefaultCloseOperation(replacee.DISPOSE_ON_CLOSE);
 	        replacee.setLayout( new GridLayout(2,1) );  
 	        replacee.getRootPane().setDefaultButton(b);
 	        b.addActionListener ( new ActionListener()  
 	        {  
 	            public void actionPerformed( ActionEvent e )  
 	            {  
 	            	String searchWord = fText.getText();
 	            	Highlighter.HighlightPainter painter = new DefaultHighlighter.DefaultHighlightPainter( Color.cyan );

 	        		int offset = text.getText().indexOf(searchWord);
 	        		int length = searchWord.length();

 	        		while ( offset != -1)
 	        		{
 	        		    try
 	        		    {
 	        		        text.getHighlighter().addHighlight(offset, offset + length, painter);
 	        		        offset = text.getText().indexOf(searchWord, offset+1);
 	        		    }
 	        		    catch(BadLocationException ble) { System.out.println(ble); }
 	        		}
 	            }  
 	        });  
 	        
 	        replacee.addWindowListener(new WindowAdapter() {
	    	    public void windowClosing(WindowEvent e) {
	    	    	Highlighter h = text.getHighlighter();
	    	    	h.removeAllHighlights();
 	            }    
	    	});	
 
        
			JPanel top = new JPanel(new BorderLayout());
			top.add(find,BorderLayout.WEST);
			top.add(fText,BorderLayout.CENTER);
			replacee.add(top);
			
			replacee.add(b);
			replacee.setSize(300,100);  
			replacee.setLocationRelativeTo(null);
			replacee.setVisible(true);       	
        }  
        
    });  
	
    

	JMenuItem findNext = new JMenuItem("Find Next");
    findNext.addActionListener ( new ActionListener()  
    {  
        public void actionPerformed( ActionEvent e )  
        {  
        	JFrame f= new JFrame();  
        	JButton b = new JButton("Find Next");
        	JLabel find = new JLabel("Find: ");
        	JTextField fText = new JTextField();
 	        JDialog replacee = new JDialog(f , "Find Next", false);  
 	        replacee.setDefaultCloseOperation(replacee.DISPOSE_ON_CLOSE);
 	        replacee.setLayout( new GridLayout(2,1) );  
 	        replacee.getRootPane().setDefaultButton(b);
 	        b.addActionListener ( new ActionListener()  
 	        {  
 	            public void actionPerformed( ActionEvent e )  
 	            {   Highlighter h = text.getHighlighter();
 	            	h.removeAllHighlights();
 	            	int offset=0;
 	            	String searchWord = fText.getText();
 	            	Highlighter.HighlightPainter painter = new DefaultHighlighter.DefaultHighlightPainter( Color.cyan );
 	            	if (savedIndex==0){ offset = text.getText().indexOf(searchWord);}
 	            	else{offset = savedIndex;}
 	        		int length = searchWord.length();
 	        		
 	            	 String from = fText.getText();
 	            	 int start = text.getText().indexOf(from);
 	            	 if (start >= 0 && from.length() > 0){
 	            		try {text.getHighlighter().addHighlight(offset, offset + length, painter);} catch (BadLocationException e1) {e1.printStackTrace();}
        		        offset = text.getText().indexOf(searchWord, offset+1);
 	            	 }
 	                 savedIndex = offset;
 	                 
 	            }  
 	        });  
 	       replacee.addWindowListener(new WindowAdapter() {
 	    	    public void windowClosed(WindowEvent e) {
 	    	    	savedIndex =0;
 	    	    }
 	    	});	        
        
			JPanel top = new JPanel(new BorderLayout());
			top.add(find,BorderLayout.WEST);
			top.add(fText,BorderLayout.CENTER);
			replacee.add(top);
			
			replacee.add(b);
			replacee.setSize(300,100);  
			replacee.setLocationRelativeTo(null);
			replacee.setVisible(true);       	
        }  
        
    });  
	
    
	JMenuItem gootoo = new JMenuItem("GoTo");
    gootoo.addActionListener ( new ActionListener()  
    {  
        public void actionPerformed( ActionEvent e )  
        {  
        	JFrame f= new JFrame();  
        	JButton b = new JButton("go to line...");
        	JLabel find = new JLabel("Enter Line: ");
        	JTextField fText = new JTextField();
 	        JDialog replacee = new JDialog(f , "Replace", false);  
 	        replacee.setDefaultCloseOperation(replacee.DISPOSE_ON_CLOSE);
 	        replacee.setLayout( new GridLayout(2,1) );  
 	        replacee.getRootPane().setDefaultButton(b);
 	        b.addActionListener ( new ActionListener()  
 	        {  
 	            public void actionPerformed( ActionEvent e )  
 	            {  
 	            	int i = Integer.parseInt(fText.getText());
 	               text.setCaretPosition(text.getDocument().getDefaultRootElement().getElement(i).getStartOffset());
 	               replacee.dispose();
 	            }  
 	        });  
 	        
 	        
			JPanel top = new JPanel(new BorderLayout());
			top.add(find,BorderLayout.WEST);
			top.add(fText,BorderLayout.CENTER);
			replacee.add(top);
			
			replacee.add(b);
			replacee.setSize(300,100);  
			replacee.setLocationRelativeTo(null);
			replacee.setVisible(true);
        }  
        
    });  
       
    
    JMenuItem selectAll = new JMenuItem("Select All");
    selectAll.addActionListener ( new ActionListener()  
    {  
        public void actionPerformed( ActionEvent e )  
        {  
        	text.selectAll();
        }  
    });
    
    JMenuItem time = new JMenuItem("Time/Date");
    time.addActionListener ( new ActionListener()  
    {  
        public void actionPerformed( ActionEvent e )  
        {  
        	SimpleDateFormat temp = new SimpleDateFormat("h:mm a M/dd/YYY");
        	text.insert(temp.format(new Date()), text.getCaretPosition());
        }  
    });
    
    
    Action cutAction = new AbstractAction("Cut"){
        public void actionPerformed(ActionEvent ae){
            text.cut();
        }
    };
    Action copyAction = new AbstractAction("Copy"){
        public void actionPerformed(ActionEvent ae){
            text.copy();
        }
    };
    Action pasteAction = new AbstractAction("Paste"){
        public void actionPerformed(ActionEvent ae){
            text.paste();
        }
    };
    
    pop.add(cutAction);
    pop.add(copyAction);
    pop.add(pasteAction);
    text.setComponentPopupMenu(pop);
    
    
    
	KeyStroke aCut = KeyStroke.getKeyStroke(KeyEvent.VK_X, KeyEvent.CTRL_DOWN_MASK);
	KeyStroke aCopy = KeyStroke.getKeyStroke(KeyEvent.VK_C, KeyEvent.CTRL_DOWN_MASK);
	KeyStroke aPaste = KeyStroke.getKeyStroke(KeyEvent.VK_V, KeyEvent.CTRL_DOWN_MASK);
	KeyStroke aFind = KeyStroke.getKeyStroke(KeyEvent.VK_F, KeyEvent.CTRL_DOWN_MASK);
	KeyStroke aReplace = KeyStroke.getKeyStroke(KeyEvent.VK_H, KeyEvent.CTRL_DOWN_MASK);
	KeyStroke aGoTo = KeyStroke.getKeyStroke(KeyEvent.VK_G, KeyEvent.CTRL_DOWN_MASK);
	KeyStroke aSelectAll = KeyStroke.getKeyStroke(KeyEvent.VK_A, KeyEvent.CTRL_DOWN_MASK);
	KeyStroke aDelete = KeyStroke.getKeyStroke(KeyEvent.VK_DELETE,0);
	KeyStroke aTimeDate = KeyStroke.getKeyStroke(KeyEvent.VK_F5,0);
    
    cut.setAccelerator(aCut);
    copy.setAccelerator(aCopy);
    paste.setAccelerator(aPaste);
    find.setAccelerator(aFind);
    replace.setAccelerator(aReplace);
    gootoo.setAccelerator(aGoTo);
    selectAll.setAccelerator(aSelectAll);
    delete.setAccelerator(aDelete);
    time.setAccelerator(aTimeDate);
    
	
    edit.add(undoo);
    edit.addSeparator();
	edit.add(cut);
	edit.add(copy);
	edit.add(paste);
	edit.add(delete);
	edit.addSeparator();
	edit.add(find);
	edit.add(findNext);
	edit.add(replace);
	edit.add(gootoo);
	edit.addSeparator();
	edit.add(selectAll);
	edit.add(time);
	menu.add(edit);
	
}

public void formatBar(){
	JCheckBoxMenuItem wordWrap = new JCheckBoxMenuItem("Word Wrap",false);
    wordWrap.addActionListener ( new ActionListener()  
    {  
        public void actionPerformed( ActionEvent e )  
        {  
        	if(t==false){t =true;text.setLineWrap(t);}
        	else if(t==true){t = false;text.setLineWrap(t);}
        }  
    }); 
    
    JMenuItem font = new JMenuItem("Font...",'F');
    font.addActionListener ( new ActionListener()  
    {  
        public void actionPerformed( ActionEvent e )  
        {  
        	JFontChooser jfc = new JFontChooser();
        	text.setForeground(jfc.getColor());
        	text.setFont(jfc.getFont());
        	text.setBackground(jfc.getBackgroundColor());
        }  
    });  
   
   format.add(wordWrap);
   format.add(font);
   
   menu.add(format);
    
}

public void view(){
	
	JMenuItem status = new JMenuItem("Status Bar",'S');
	status.setEnabled(false);
    status.addActionListener ( new ActionListener()  
    {  
        public void actionPerformed( ActionEvent e )  
        {  
        	
        }  
    });  
	view.add(status);
	menu.add(view);
    
}

public void helpBar(){
	
	JMenuItem helpp = new JMenuItem("About JNotepad");
	helpp.setEnabled(false);
    helpp.addActionListener ( new ActionListener()  
    {  
        public void actionPerformed( ActionEvent e )  
        {  
        	
        }  
    }); 

	JMenuItem about = new JMenuItem("About JNotepad");
    about.addActionListener ( new ActionListener()  
    {  
        public void actionPerformed( ActionEvent e )  
        {  
        	JFrame f= new JFrame();  
        	JButton b = new JButton("OK");
        	JLabel lAbout = new JLabel("(c) Matthew Ortiz");
 	        JDialog dAbout = new JDialog(f , "About Notepad", true);  
 	        dAbout.setDefaultCloseOperation(dAbout.DISPOSE_ON_CLOSE);
 	        dAbout.setLayout( new BorderLayout() );  
 	        dAbout.getRootPane().setDefaultButton(b);
 	        b.addActionListener ( new ActionListener()  
 	        {  
 	            public void actionPerformed( ActionEvent e )  
 	            {  
 	            	dAbout.dispose();
 	            }  
 	        });  
 	        
 	        dAbout.add(lAbout, BorderLayout.CENTER);
 	        dAbout.add(b,BorderLayout.SOUTH);   
 	        dAbout.setSize(300,100);  
 	        dAbout.setLocationRelativeTo(null);
 	        dAbout.setVisible(true);  
        	
        }  
    }); 
    help.add(about);
    menu.add(help);
}

public void addToFrame(){
	JScrollPane sp = new JScrollPane(text);
	frame.add(menu,BorderLayout.NORTH);
	frame.add(sp,BorderLayout.CENTER);
	
}

static String getString(File f) throws IOException{
	String everything;
	BufferedReader br = new BufferedReader(new FileReader(f));
	try {
	    StringBuilder sb = new StringBuilder();
	    String line = br.readLine();
	    while (line != null) {
	        sb.append(line);
	        sb.append(System.lineSeparator());
	        line = br.readLine();
	    }
	     everything = sb.toString();
	} finally {
	    br.close();
	}
	return everything;
}

public void checkSave(){
	if (cSave.equals(text.getText())){saved = true;}
	else saved = false;
}

}




