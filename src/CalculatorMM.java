import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JFrame;

public class CalculatorMM {
	
	public static void main(String[] args)
	{
		CalculatorSM calculatorSM = new CalculatorSM();
		calculatorSM.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		calculatorSM.setSize(260,360);
		calculatorSM.getContentPane().setBackground( Color.DARK_GRAY);
		
		calculatorSM.setVisible(true);
		calculatorSM.setResizable(false);
	}

}
