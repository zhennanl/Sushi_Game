package sushigame.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.Arrays;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import comp401sushi.Plate;
import comp401sushi.Sushi;
import sushigame.model.Belt;
import sushigame.model.TimedPlate;

public class PlateViewWidget extends JPanel {

	private Belt belt; 
	private JLabel[] beltLabels;
	
	public PlateViewWidget(Belt belt){
		if (belt != null) {
		this.belt = belt;
		this.beltLabels = new JLabel[belt.getSize()];
		setLayout(new GridLayout(belt.getSize(), 1));
		for (int i = 0; i < belt.getSize(); i++) {
			JLabel label = new JLabel("");
			label.setMinimumSize(new Dimension(707, 38));
			label.setPreferredSize(new Dimension(707, 38));
			label.setOpaque(true);
			label.setBackground(Color.GRAY);
			add(label);
			this.beltLabels[i] = label;
		}
		refresh();
		}
	}
	
	public JLabel[] refresh() {
		for (int i=0; i<belt.getSize(); i++) {
			Plate p = belt.getPlateAtPosition(i);
			JLabel plabel = beltLabels[i];
	
			if (p == null) {
				plabel.setText("Empty");
				plabel.setBackground(Color.GRAY);
				plabel.setHorizontalAlignment(SwingConstants.CENTER);
				plabel.setVerticalAlignment(SwingConstants.CENTER);
			} else {	
				plabel.setText("<html>" + "Chef: " + belt.getPlateAtPosition(i).getChef().getName() 
						+ ", Sushi Name: " + getInfo(i) + ", Age: " + belt.getAgeOfPlateAtPosition(i) + "</html>");
				switch (p.getColor()) {
				case RED:
					plabel.setBackground(Color.RED); break;
				case GREEN:
					plabel.setBackground(Color.GREEN); break;
				case BLUE:
					plabel.setBackground(Color.BLUE); break;
				case GOLD:
					plabel.setBackground(Color.YELLOW); break;
				}
				plabel.setHorizontalAlignment(SwingConstants.CENTER);
				plabel.setVerticalAlignment(SwingConstants.CENTER);
			}
		}
		return beltLabels;
	}
	
	public String getInfo(int position) {
		String name, ingredientList;
		String[] ingredients;
		
		Sushi contents = belt.getPlateAtPosition(position).getContents();
		ingredients = new String[contents.getIngredients().length]; 
		
		if (belt.getPlateAtPosition(position) != null) { 
			for (int i = 0; i < contents.getIngredients().length; i++) {
				ingredients[i] = contents.getIngredients()[i].getName() + 
						" (" + ((int) (contents.getIngredients()[i].getAmount() 
								* 100.0))/100.0 + ")";
			}
		}
		
		ingredientList = Arrays.toString(ingredients);
		
		if (contents.getName().contains("nigiri") || contents.getName().contains("sashimi")) {
			name = contents.getName();
		} else { 
			name = contents.getName() + ", Ingredients:  " + ingredientList;
		}
		return name;
	}
}


