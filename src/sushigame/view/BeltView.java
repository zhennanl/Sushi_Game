package sushigame.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import comp401sushi.IngredientPortion;
import comp401sushi.Plate;
import comp401sushi.Sushi;
import sushigame.model.Belt;
import sushigame.model.BeltEvent;
import sushigame.model.BeltObserver;
import sushigame.model.TimedPlate;

public class BeltView extends JPanel implements BeltObserver {

	private Belt belt;
	private JLabel[] beltLabels;
	private PlateViewWidget pWidget;

	public BeltView(Belt b) {
		this.belt = b;
		this.beltLabels = new JLabel[belt.getSize()];
		this.pWidget = new PlateViewWidget(b);
		belt.registerBeltObserver(this);
		add(pWidget);
		refresh();
	}

	@Override
	public void handleBeltEvent(BeltEvent e) {	
		refresh();
	}

	private void refresh() {
		beltLabels = pWidget.refresh();
	}
}
