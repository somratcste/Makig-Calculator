import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JDialog;
import javax.swing.JTextField;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import javax.swing.JOptionPane;
import javax.swing.text.PlainView;
import javax.swing.Icon;
import javax.swing.ImageIcon;

public class CalculatorSM extends JFrame implements ActionListener {
	
	private JButton[] buttons;
	public JTextField textField;
	private final String[] buttonOrder = {"7","8","9","4","5","6","1","2","3",".","0"," "};
	private final String[] operator = {"C","+","-","*","/" ,"="};
	private final String[] clear = {"       C       "};
	private static final Font font = new Font("monospaced", Font.BOLD,20);
	private static final Font fontMenu = new Font("Times New Roman" , Font.BOLD,15);
	private JMenu menuFile,menuHelp;
	private JMenuItem menuItemExit,menuItemAbout;
	private JMenuBar menuBar;
	private ImageIcon icons;
	public boolean number = true;
    private String equalOp = "=";
    private CalculatorOp operation = new CalculatorOp();
	
	
	
	
	 public CalculatorSM() {
		// TODO Auto-generated constructor stub
		super("Calculator By Somrat");
		super.setFont(font);
	    icons = new ImageIcon("somrat.gif"); 
		
		
	
		menuFile =new JMenu("File");
		menuFile.setForeground(Color.WHITE);
		menuFile.setFont(fontMenu);
		menuHelp = new JMenu("Help");
		menuHelp.setFont(fontMenu);
		menuHelp.setForeground(Color.WHITE);
		menuItemExit = new JMenuItem("Exit");
		menuItemExit.setForeground(Color.white);
		menuItemExit.setBackground(Color.black);
		menuItemExit.setFont(fontMenu);
		//menuFile.setMnemonic(KeyEvent.VK_F);
		//menuHelp.setMnemonic(KeyEvent.VK_H);
		menuFile.add(menuItemExit);
		//menuItemExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,ActionEvent.CTRL_MASK));
		menuItemAbout = new JMenuItem("About");
		menuItemAbout.setForeground(Color.white);
		menuItemAbout.setBackground(Color.black);
		menuItemAbout.setFont(fontMenu);
		menuHelp.add(menuItemAbout);
		
		
		menuBar = new JMenuBar();
		menuBar.add(menuFile);
		menuBar.add(menuHelp);
		menuBar.setBackground(Color.DARK_GRAY);
		setJMenuBar(menuBar);
		
		
		
		
		ActionListener numberListener = new NumberListener();
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(4,buttonOrder.length,5,5));
		for(int count= 0 ;count< buttonOrder.length;count++)
		{
			String key = buttonOrder[count];
			JButton b = new JButton(key);
			if(key.equals(" "))
			{
				b.setEnabled(false);
			}
			else
			{
				
			}
			
			
			
			b.setFont(font);
			b.setBackground(Color.BLACK);
			b.setForeground(Color.WHITE);
			b.addActionListener(numberListener);
			buttonPanel.setBackground(Color.DARK_GRAY);
			buttonPanel.add(b);
			
		}
		
		
		ActionListener operatorListener = new OperatorListener();
		JPanel operatorPanel = new JPanel();
		operatorPanel.setLayout(new GridLayout(6,operator.length,5,5));
		for(int count =0;count <operator.length; count++)
		{
			JButton o = new JButton(operator[count]);
			o.setFont(font);
			o.setBackground(Color.BLACK);
			o.setForeground(Color.WHITE);
			//o.setBorder(BorderFactory.createLineBorder(Color.red));
		    o.addActionListener(operatorListener);
			operatorPanel.setBackground(Color.DARK_GRAY);
			operatorPanel.add(o);
			
		}
		
		
		
		JPanel clearPanel = new JPanel();
		clearPanel.setLayout(new FlowLayout());
		JButton clearButton = new JButton(clear[0]);
		clearButton.setFont(font);
		clearButton.setBackground(Color.BLACK);
		clearButton.setForeground(Color.WHITE);
		clearPanel.setBackground(Color.DARK_GRAY);
		clearPanel.add(clearButton);
		
		
		JPanel textPanel = new JPanel();
		textPanel.setLayout(new FlowLayout());
		textField = new JTextField("0",16);
		textField.setEditable(true);
		textField.setHorizontalAlignment(textField.RIGHT);
		textField.setFont(font);
		textField.setEditable(false);
		textPanel.add(textField);
		
		JPanel content = new JPanel();
		content.setLayout(new BorderLayout(5,5));
		content.add(textPanel,BorderLayout.NORTH);
		content.add(buttonPanel,BorderLayout.WEST);
		content.add(operatorPanel,BorderLayout.EAST);
		//content.add(clearPanel,BorderLayout.SOUTH);
		content.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		this.setContentPane(content);
		
	
		
	    menuItemExit.addActionListener(this);
	    addWindowListener(new WindowAdapter() {
	    	
	    	public void windowClosed(WindowEvent e)
	    	{
	    		System.exit(0);
	    		
	    	}
		});
	    
	    menuItemAbout.addActionListener(this);
		
		
	}
	 
	 public void action()
	 {
		 number = true;
		 textField.setText("0");
		 equalOp = "=";
		 operation.setTotal("0");
			 
	 }
	 

	 
	 public class NumberListener implements ActionListener {

			public void actionPerformed(ActionEvent event) {
				// TODO Auto-generated method stub
				String digit = event.getActionCommand();
				if(number)
				{
				   textField.setText(digit);
				   number = false;
				}
				else
				{
					textField.setText(textField.getText() + digit);
				}
				   

			}

		}
	 
	 
	 
	 public class OperatorListener implements ActionListener {

			public void actionPerformed(ActionEvent event) {
				// TODO Auto-generated method stub
				
				if(number)
				{
					action();
					textField.setText("0");
				}
				else
				{
					number = true;
					String displayText = textField.getText();
					if(equalOp.equals("="))
						operation.setTotal(displayText);
					else if(equalOp.equals("+"))
						operation.add(displayText);
					else if(equalOp.equals("-"))
						operation.subtract(displayText);
					else if(equalOp.equals("*"))
						operation.multiply(displayText);
					else if(equalOp.equals("/"))
						operation.divide(displayText);
					textField.setText("" +operation.getTotalString());
					equalOp = event.getActionCommand();
				}
				

			}

		}
	 
	 
	 
	 

	public void actionPerformed(ActionEvent event) {
		// TODO Auto-generated method stub
		
		if(event.getSource() == menuItemExit)
			System.exit(0);
		else if(event.getSource()==menuItemAbout)
		{
			JOptionPane.showMessageDialog(null, "\nThis Calculator is one of my JAVA GUI project. \n" +
					"Version 1.0 (Buid 7600) \n" + 
					"copyright 2014 G.M.Nazmul Hossain.\n All right reserved.\n" +
					" \n\n\n" + 
					"This product is license under the NSTU-CSTE to \n"+
					"https://www.somrat.info", 
					"About Calculator", JOptionPane.PLAIN_MESSAGE, icons);
			
		}
		
		
	
	}
	
	
	

public class CalculatorOp {
	private double total;
	
	public CalculatorOp()
	{
		total = 0;
	}
	
	public String getTotalString()
	{
		return "" + total;
	}
	
	public void setTotal(String n)
	{
		total = convertToNumber(n);
	}
	
	public void add(String n )
	{
		total+= convertToNumber(n);
	}
	
	public void subtract(String n)
	{
		total -= convertToNumber(n);
	}
	
	public void multiply(String n)
	{
		total *= convertToNumber(n);
	}
	
	public void divide(String n)
	{
		total /= convertToNumber(n);
	}

	private double convertToNumber(String n) {
		// TODO Auto-generated method stub
		
		return Double.parseDouble(n);
	}
	

}

	

}
