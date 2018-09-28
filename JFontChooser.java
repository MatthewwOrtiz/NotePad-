
	/**
	 * Name: Ortiz, Matthew
	 * Project:# 2
	 * Due: March 7, 2018
	 * Course: cs24501-w18
	 *
	 * Description:
	 * A graphical representation of a font sampler
	 */ 


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;


public class JFontChooser{
	private JList fonts = new JList();
	private float pp = 0;
	private String what;
	private Font finalFont;
	private Color finalColor;
	private Color finalBackgroundColor;
	

		public static void main(String args[]){
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
			new JFontChooser();
		}});}
		
		JFontChooser(){
			JFrame frame = new JFrame("Font");
			JDialog d = new JDialog(frame , "Font", true);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			d.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			d.setLayout(new GridLayout(3,1));
			d.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			
			JLabel butText = new JLabel("AaBbYyZz",SwingConstants.CENTER);
			butText.setForeground(finalColor);
			butText.setFont(finalFont);
			
			JButton ok = new JButton("OK");
			ok.addActionListener ( new ActionListener()  
	        {  
	            public void actionPerformed( ActionEvent e )  
	            {  
	            	d.dispose();
	            }  
	        });  
		
			JButton cancel = new JButton("Canel");
			cancel.addActionListener ( new ActionListener()  
	        {  
	            public void actionPerformed( ActionEvent e )  
	            {  
	            	d.dispose();
	            }  
	        });  
			JButton color = new JButton("Set Text Color");
			color.addActionListener ( new ActionListener()  
	        {  
	            public void actionPerformed( ActionEvent e )  
	            {  
	            	 Color c = JColorChooser.showDialog(null, "Choose a Color", butText.getForeground());
	                 if (c != null)
	                   butText.setForeground(c);
	                 finalColor=c;

	            }  
	        });  
			
			JButton bColor = new JButton("Set Background Color");
			bColor.addActionListener ( new ActionListener()  
	        {  
	            public void actionPerformed( ActionEvent e )  
	            {  
	            	 Color c = JColorChooser.showDialog(null, "Choose a Color", butText.getForeground());
	                 if (c != null)
	                   butText.setBackground(c);
	                 finalBackgroundColor=c;

	            }  
	        });  
		
			
			
//FONT STYLE
//FONT STYLE
//FONT STYLE
//FONT STYLE
			JPanel fontStyle = new JPanel(new BorderLayout());
			JLabel fst = new JLabel("Font style:");
			JTextField fontStyleWrite = new JTextField();
			JScrollPane fsPane = new JScrollPane();
			
			String styy[] = {"Regular","Italic","Bold","Bold Italic"};
			JList sty = new JList(styy);
			
			sty.setSelectedIndex(0);
			fontStyleWrite.setText(sty.getSelectedValue().toString());
			
			sty.addListSelectionListener((ListSelectionEvent le) ->{
				String what = "";
				Font f = butText.getFont();
				Object values[] = sty.getSelectedValues();
				
				String search = fontStyleWrite.getText();
				
				
				for (int i = 0; i < values.length; i++) {
	                what += values[i];}
				switch (what){
				case "Regular":
					butText.setFont(f.deriveFont((f.getStyle() &~ Font.ITALIC)));
					butText.setFont(f.deriveFont((f.getStyle() &~ Font.BOLD)));
					break;
				case "Italic":
					butText.setFont(f.deriveFont((f.getStyle() &~ Font.ITALIC)));
					butText.setFont(f.deriveFont((f.getStyle() &~ Font.BOLD)));
					butText.setFont(f.deriveFont((f.getStyle() | Font.ITALIC)));
					break;
				case "Bold":
					butText.setFont(f.deriveFont((f.getStyle() &~ Font.ITALIC)));
					butText.setFont(f.deriveFont((f.getStyle() &~ Font.BOLD)));
					butText.setFont(f.deriveFont((f.getStyle() | Font.BOLD)));
					break;
				case "Bold Italic":
					butText.setFont(f.deriveFont((f.getStyle() | Font.ITALIC)));
					butText.setFont(f.deriveFont((f.getStyle() | Font.BOLD)));
					break;
				}
	            {fontStyleWrite.setText(sty.getSelectedValue().toString());
	            	finalFont=butText.getFont();}
				});
			
			sty.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			sty.setLayoutOrientation(JList.VERTICAL);
			sty.setVisibleRowCount(-1);
			JScrollPane styleScroll = new JScrollPane(sty);
			styleScroll.setPreferredSize(new Dimension(150,150));

			JPanel m = new JPanel(new BorderLayout());
			
			m.add(fst,BorderLayout.NORTH);
			m.add(fontStyleWrite,BorderLayout.SOUTH);
			fontStyle.add(m,BorderLayout.NORTH);
			fontStyle.add(styleScroll,BorderLayout.CENTER);
			
			
//FONT SIZE
//FONT SIZE
//FONT SIZE
//FONT SIZE
//FONT SIZE
			JPanel size = new JPanel(new BorderLayout());
			JLabel ssize = new JLabel("Size:");
			JTextField sizeWrite = new JTextField("11");
			JScrollPane sPane = new JScrollPane();
			String num[] = {"11","12","14","16","18","20","22","24","26","28","36","48","72"};
			JList numz = new JList(num);
			
			numz.setSelectedIndex(0);
			sizeWrite.setText(numz.getSelectedValue().toString());
			pp=Float.parseFloat((numz.getSelectedValue().toString()+".0"));
			numz.addListSelectionListener((ListSelectionEvent le) ->{
				String what = "";
				
				Object values[] = sty.getSelectedValues();
				for (int i = 0; i < values.length; i++) {what += values[i];}
	            
	    
	           {sizeWrite.setText(numz.getSelectedValue().toString());}
	           pp=Float.parseFloat((numz.getSelectedValue().toString()+".0"));
				butText.setFont(Font.decode(what).deriveFont(pp));
				finalFont=butText.getFont();
				});
			
			numz.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			numz.setLayoutOrientation(JList.VERTICAL);
			numz.setVisibleRowCount(-1);
			JScrollPane sizeScroll = new JScrollPane(numz);
			sizeScroll.setPreferredSize(new Dimension(50,150));
			
			JPanel o = new JPanel(new BorderLayout());
			
			o.add(ssize,BorderLayout.NORTH);
			o.add(sizeWrite,BorderLayout.SOUTH);
			size.add(o,BorderLayout.NORTH);
			size.add(sizeScroll,BorderLayout.CENTER);
			

//FONT FONT
//FONT FONT
//FONT FONT
//FONT FONT
//FONT FONT
			JLabel font = new JLabel("Font:");
			JPanel fontss = new JPanel(new BorderLayout());
			JTextField fontsWrite = new JTextField();
			String gonts [] = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames(); 
			JList fonts = new JList(gonts);
			
			
			fonts.setSelectedIndex(0);
			fontsWrite.setText(fonts.getSelectedValue().toString());
			
			fonts.addListSelectionListener((ListSelectionEvent le) ->{
				String what = "";
				
				Object values[] = fonts.getSelectedValues();
				for (int i = 0; i < values.length; i++) {
	                what += values[i];}
	            
	           
	           {fontsWrite.setText(fonts.getSelectedValue().toString());}
				butText.setFont(Font.decode(what).deriveFont(pp));
				finalFont = butText.getFont();
				});			
			
			fonts.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			fonts.setLayoutOrientation(JList.VERTICAL);
			fonts.setVisibleRowCount(-1);
			JScrollPane fontScroll = new JScrollPane(fonts);
			fontScroll.setPreferredSize(new Dimension(220,150));

			JPanel n = new JPanel(new BorderLayout());
			
			n.add(font,BorderLayout.NORTH);
			n.add(fontsWrite,BorderLayout.SOUTH);
			fontss.add(n,BorderLayout.NORTH);
			fontss.add(fontScroll,BorderLayout.CENTER);
			
//SAMPLE TEXT
//SAMPLE TEXT
//SAMPLE TEXT
//SAMPLE TEXT
			JLabel sss = new JLabel("Sample");
			JPanel ePanel = new JPanel();
			JPanel oPanel = new JPanel(new GridLayout(1,2));
			JPanel sPanel = new JPanel(new BorderLayout());
			
			
			butText.setForeground(getColor());
			butText.setFont(getFont());
			
			
			sPanel.add(sss,BorderLayout.NORTH);
			sPanel.add(butText,BorderLayout.CENTER);
			
			oPanel.add(ePanel);
			oPanel.add(sPanel);
			
			Border thickBorder = new LineBorder(Color.GRAY, 1);
			sPanel.setBorder(thickBorder);
			sPanel.setForeground(finalBackgroundColor);

			
			butText.setFont(Font.decode(what).deriveFont(pp));
			JPanel top = new JPanel(new FlowLayout());
			JPanel middle = new JPanel(new GridLayout(1,2));
			JPanel bottem = new JPanel(new GridLayout(1,1));
			
			
			top.setBorder(new EmptyBorder(10,10,10,10));
			middle.setBorder(new EmptyBorder(10,10,10,10));
			bottem.setBorder(new EmptyBorder(50,10,50,10));
			
			
	
//ADD TO FRAME
//ADD TO FRAME
//ADD TO FRAME
//ADD TO FRAME
//ADD TO FRAME
//ADD TO FRAME
//ADD TO FRAME
			
			top.add(fontss);
			top.add(fontStyle);
			top.add(size);
			middle.add(oPanel);
			d.add(top);
			d.add(middle);
			JPanel butt = new JPanel(new BorderLayout());
			JPanel superButt = new JPanel(new FlowLayout());
			superButt.add(ok);
			superButt.add(color);
			superButt.add(bColor);
			superButt.add(cancel);
			butt.add(superButt,BorderLayout.SOUTH);
			d.add(butt);
			
			d.setSize(500,650);
			d.setVisible(true);
			d.setLocationRelativeTo(null);
			
			}
public Font getFont(){return finalFont;}
public Color getColor(){return finalColor;}
public Color getBackgroundColor(){return finalBackgroundColor;}
public void setDefault(Font font){finalFont = font; fonts.setSelectedValue((Object)finalFont.getName(),true);}
public void setDefault(Color color){finalColor = color;}
}
