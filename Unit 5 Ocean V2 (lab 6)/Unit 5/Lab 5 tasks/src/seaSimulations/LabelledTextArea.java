package seaSimulations;

import java.awt.*;
import javax.swing.*;
//import javax.swing.event.*;
import javax.swing.border.*;
//import java.text.*;


/**
 * Creates a JLabel and JTextArea coupled together
 *
 * @author Maria Chli
 * @version 18/10/2006
 */


public class LabelledTextArea extends JComponent {
	
	//private static final long serialVersionUID = -1978073598544126546L;
	private JLabel label;
	private JTextArea textArea;

	
	/**
	 * Creates a <code>LabelledTextArea</code>.
	 *
	 * @param text a {@link java.lang.String} that names the label and text area
	 * @param value a double that gives the initial value
	 */
	public LabelledTextArea(String text, String value) 
	{
		this.label = new JLabel(text);
		this.textArea = new JTextArea(value);
		
		
		// Set slider properties
		//slider.setPaintTicks(true);
		//slider.addChangeListener(new SliderListener());
		
		
		this.setLayout(new BorderLayout());
		this.add(label, BorderLayout.WEST);
		this.add(textArea, BorderLayout.EAST);
		int margin = 10;
		//this.setBorder(new CompoundBorder(
		//		new EmptyBorder(margin, margin, margin, margin),
		//		new EtchedBorder()));
		this.textArea.setBorder(new CompoundBorder(new EmptyBorder(0, 0, 0, 0),new EtchedBorder()));
		
	}
	
	/**
	 * Sets the major tick spacing for the LabelledSlider
	 *
	 * @param spacing an int that gives the new spacing value
	 */
	public void setMajorTickSpacing(int spacing) {
		//slider.setMajorTickSpacing(spacing);
		repaint();
	}
	
	/**
	 * Returns text area value
	 *
	 * @return the double value.
	 */
	public double getValue() {
		double d = Double.parseDouble(textArea.getText().trim()); 
		return d;
	}
	
	/**
	 * Inner class to update the slider location and the text label
	 
	private class SliderListener implements ChangeListener {
		
		public void stateChanged(ChangeEvent e) {
			if (!slider.getValueIsAdjusting()) {
				int number = slider.getValue();
				label.setText(labelString + nf.format(((double)number/scale)));
			}
		}
	}*/
}
