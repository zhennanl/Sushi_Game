package sushigame.view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import comp401sushi.AvocadoPortion;
import comp401sushi.CrabPortion;
import comp401sushi.EelPortion;
import comp401sushi.IngredientPortion;
import comp401sushi.Nigiri;
import comp401sushi.Plate;
import comp401sushi.RedPlate;
import comp401sushi.RicePortion;
import comp401sushi.Roll;
import comp401sushi.Sashimi;
import comp401sushi.SeaweedPortion;
import comp401sushi.ShrimpPortion;
import comp401sushi.Sushi;
import comp401sushi.TunaPortion;

public class PlayerChefView extends JPanel implements ActionListener {

	private List<ChefViewListener> listeners;
	private Sushi kmp_roll;
	private Sushi crab_sashimi;
	private Sushi eel_nigiri;
	private int belt_size;
	private JComboBox colorCombo;
	private JComboBox sashimiCombo;
	private JComboBox nigiriCombo;
	public String[] colorTypes = {"Red", "Green", "Blue", "Gold"};
	private String[] seafoodTypes = { "Crab", "Eel", "Yellowtail", "Shrimp", "Tuna" };
	public double[] rollIngredients = { 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D };
	private JSlider goldSlider;
	private JSlider avocadoSlider;
	private JSlider crabSlider;
	private JSlider eelSlider;
	private JSlider riceSlider;
	private JSlider yellowtailSlider;
	private JSlider seaweedSlider;
	private JSlider shrimpSlider;
	private JSlider tunaSlider;
	private JSlider positionSlider;
	public int platePosition;
	public double goldPlatePrice;
	public double avocadoAmount;
	public double crabAmount;
	public double eelAmount;
	public double riceAmount;
	public double yellowtailAmount;
	public double seaweedAmount;
	public double shrimpAmount;
	public double tunaAmount;
	private JTextField rollName;
	private JButton makeRollButton, makeSashimiButton, makeNigiriButton;
	private ArrayList<IngredientPortion> rollIngredientList;
	private Sushi sushi;

	public PlayerChefView(int belt_size) {
		this.belt_size = belt_size;
		listeners = new ArrayList<ChefViewListener>();

		setLayout(new BoxLayout(this, 1));

		JLabel colorLabel = new JLabel("Plate Color: ");
		add(colorLabel);

		colorCombo = new JComboBox(colorTypes);
		add(colorCombo);

		JLabel goldPlateLabel = new JLabel("Gold Plate Price: ");
		add(goldPlateLabel);
		goldPlatePrice = 5.0D;
		goldSlider = new JSlider(0, 500, 1000, 500);
		goldSlider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent event) {
				goldPlateLabel.setText("Gold Plate Price: $" + ((JSlider)event.getSource()).getValue() / 100.0D);
				goldPlatePrice = (((JSlider)event.getSource()).getValue() / 100.0D);
				setGoldPrice(goldPlatePrice);
			}
		});
		add(goldSlider);

		JPanel rollPanel = new JPanel();
		rollPanel.setLayout(new GridLayout(8, 2));

		JLabel avocadoText = new JLabel("Avocado: ");
		avocadoSlider = new JSlider(0, 0, 15, 0);
		JLabel avocadoLabel = new JLabel("0.0 oz");

		avocadoSlider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent event) {
				avocadoLabel.setText(((JSlider)event.getSource()).getValue() / 10.0D + " oz");
				avocadoAmount = Double.valueOf(((JSlider)event.getSource()).getValue() / 10.0D);
				setAvocadoAmount(avocadoAmount);
			}
		});
		rollPanel.add(avocadoText);
		rollPanel.add(avocadoSlider);
		rollPanel.add(avocadoLabel);

		JLabel crabText = new JLabel("Crab: ");
		crabSlider = new JSlider(0, 0, 15, 0);
		JLabel crabLabel = new JLabel("0.0 oz");

		crabSlider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent event) {
				crabLabel.setText(((JSlider)event.getSource()).getValue() / 10.0D + " oz");
				crabAmount = Double.valueOf(((JSlider)event.getSource()).getValue() / 10.0D);
				setCrabAmount(crabAmount);
			}
		});

		rollPanel.add(crabText);
		rollPanel.add(crabSlider);
		rollPanel.add(crabLabel);

		JLabel eelText = new JLabel("Eel: ");
		eelSlider = new JSlider(0, 0, 15, 0);
		JLabel eelLabel = new JLabel("0.0 oz");

		eelSlider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent event) {
				eelLabel.setText(((JSlider)event.getSource()).getValue() / 10.0D + " oz");
				eelAmount = Double.valueOf(((JSlider)event.getSource()).getValue() / 10.0D);
				setEelAmount(eelAmount);
			}
		});

		rollPanel.add(eelText);
		rollPanel.add(eelSlider);
		rollPanel.add(eelLabel);

		JLabel riceText = new JLabel("Rice: ");
		riceSlider = new JSlider(0, 0, 15, 0);
		JLabel riceLabel = new JLabel("0.0 oz");

		riceSlider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent event) {
				riceLabel.setText(((JSlider)event.getSource()).getValue() / 10.0D + " oz");
				riceAmount = Double.valueOf(((JSlider)event.getSource()).getValue() / 10.0D);
				setRiceAmount(riceAmount);
			}
		});

		rollPanel.add(riceText);
		rollPanel.add(riceSlider);
		rollPanel.add(riceLabel);

		JLabel yellowtailText = new JLabel("Yellowtail: ");
		yellowtailSlider = new JSlider(0, 0, 15, 0);
		JLabel yellowtailLabel = new JLabel("0.0 oz");
		yellowtailSlider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent event) {
				yellowtailLabel.setText(((JSlider)event.getSource()).getValue() / 10.0D + " oz");
				yellowtailAmount = Double.valueOf(((JSlider)event.getSource()).getValue() / 10.0D);
				setYellowtailAmount(yellowtailAmount);
			}
		});
		rollPanel.add(yellowtailText);
		rollPanel.add(yellowtailSlider);
		rollPanel.add(yellowtailLabel);

		JLabel seaweedText = new JLabel("Seaweed: ");
		seaweedSlider = new JSlider(0, 0, 15, 0);
		JLabel seaweedLabel = new JLabel("0.0 oz");
		seaweedSlider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent event) {
				seaweedLabel.setText(((JSlider)event.getSource()).getValue() / 10.0D + " oz");
				seaweedAmount = Double.valueOf(((JSlider)event.getSource()).getValue() / 10.0D);
				setSeaweedAmount(seaweedAmount);
			}
		});
		rollPanel.add(seaweedText);
		rollPanel.add(seaweedSlider);
		rollPanel.add(seaweedLabel);

		JLabel shrimpText = new JLabel("Shrimp: ");
		shrimpSlider = new JSlider(0, 0, 15, 0);
		JLabel shrimpLabel = new JLabel("0.0 oz");
		shrimpSlider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent event) {
				shrimpLabel.setText(((JSlider)event.getSource()).getValue() / 10.0D + " oz");
				shrimpAmount = Double.valueOf(((JSlider)event.getSource()).getValue() / 10.0D);
				setShrimpAmount(shrimpAmount);
			}
		});
		rollPanel.add(shrimpText);
		rollPanel.add(shrimpSlider);
		rollPanel.add(shrimpLabel);

		JLabel tunaText = new JLabel("Tuna: ");
		tunaSlider = new JSlider(0, 0, 15, 0);
		JLabel tunaLabel = new JLabel("0.0 oz");
		tunaSlider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent event) {
				tunaLabel.setText(((JSlider)event.getSource()).getValue() / 10.0D + " oz");
				tunaAmount = Double.valueOf(((JSlider)event.getSource()).getValue() / 10.0D);
				setTunaAmount(tunaAmount);
			}
		});
		rollPanel.add(tunaText);
		rollPanel.add(tunaSlider);
		rollPanel.add(tunaLabel);
		add(rollPanel);

		JLabel rollLabel = new JLabel("Enter Roll Name: ");
		add(rollLabel);
		rollName = new JTextField();
		add(rollName);

		makeRollButton = new JButton("Make Roll");
		makeRollButton.addActionListener(this);
		makeRollButton.setActionCommand("roll");
		add(makeRollButton);


		JLabel sashimiLabel = new JLabel("Sashimi Type: ");
		add(sashimiLabel);
		sashimiCombo = new JComboBox(seafoodTypes);
		add(sashimiCombo);

		makeSashimiButton = new JButton("Make Sashimi");
		makeSashimiButton.addActionListener(this);
		makeSashimiButton.setActionCommand("sashimi");
		add(makeSashimiButton);

		JLabel nigiriLabel = new JLabel("Nigiri Type: ");
		add(nigiriLabel);
		nigiriCombo = new JComboBox(seafoodTypes);
		add(nigiriCombo);

		makeNigiriButton = new JButton("Make Nigiri");
		makeNigiriButton.addActionListener(this);
		makeNigiriButton.setActionCommand("nigiri");
		add(makeNigiriButton);

		JLabel positionLabel = new JLabel("Place Near Position: 0");
		positionSlider = new JSlider(0, 0, belt_size-1, 0);
		positionSlider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent event) {
				positionLabel.setText("Place Near Position: " + ((JSlider)event.getSource()).getValue());
				platePosition = ((JSlider)event.getSource()).getValue();
			}
		});
		add(positionLabel);
		add(positionSlider);
	}

	public void setGoldPrice(double goldPlatePrice) {
		goldPlatePrice = goldPlatePrice;
	}

	public void setAvocadoAmount(double avocadoAmount) { 
		rollIngredients[0] = avocadoAmount; 
	}

	public void setCrabAmount(double crabAmount) { 
		rollIngredients[1] = crabAmount; 
	}

	public void setEelAmount(double eelAmount) { 
		rollIngredients[2] = eelAmount; 
	}

	public void setRiceAmount(double riceAmount) { 
		rollIngredients[3] = riceAmount; 
	}

	public void setYellowtailAmount(double yellowtailAmount) { 
		rollIngredients[4] = crabAmount; 
	}

	public void setSeaweedAmount(double seaweedAmount) { 
		rollIngredients[5] = seaweedAmount; 
	}

	public void setShrimpAmount(double shrimpAmount) { 
		rollIngredients[6] = shrimpAmount; 
	}

	public void setTunaAmount(double tunaAmount) { 
		rollIngredients[7] = tunaAmount; 
	}

	public void registerChefListener(ChefViewListener cl) {
		listeners.add(cl);
	}

	private void makeRedPlateRequest(Sushi plate_sushi, int plate_position) {
		for (ChefViewListener l : listeners) {
			l.handleRedPlateRequest(plate_sushi, plate_position);
		}
	}

	private void makeGreenPlateRequest(Sushi plate_sushi, int plate_position) {
		for (ChefViewListener l : listeners) {
			l.handleGreenPlateRequest(plate_sushi, plate_position);
		}
	}

	private void makeBluePlateRequest(Sushi plate_sushi, int plate_position) {
		for (ChefViewListener l : listeners) {
			l.handleBluePlateRequest(plate_sushi, plate_position);
		}
	}

	private void makeGoldPlateRequest(Sushi plate_sushi, int plate_position, double price) {
		for (ChefViewListener l : listeners) {
			l.handleGoldPlateRequest(plate_sushi, plate_position, price);
		}
	}

	private void makePlateRequest() {
		// {"Red", "Green", "Blue", "Gold"}; 
		switch (colorCombo.getSelectedIndex()) {
		case 0: 
			makeRedPlateRequest(sushi, positionSlider.getValue());
			break;
		case 1:
			makeGreenPlateRequest(sushi, positionSlider.getValue());
			break;
		case 2:
			makeBluePlateRequest(sushi, positionSlider.getValue());
			break;
		case 3:
			makeGoldPlateRequest(sushi, positionSlider.getValue(), goldSlider.getValue() / 100.0);
			break;
		}
	}
	private Sushi makeRoll(ActionEvent event) {
		String name = rollName.getText();
		rollIngredientList = new ArrayList();
		if (avocadoAmount > 0.0D) {
			rollIngredientList.add(new AvocadoPortion(avocadoAmount));
		}
		if (crabAmount > 0.0D) {
			rollIngredientList.add(new CrabPortion(crabAmount));
		} 
		if (eelAmount > 0.0D) {
			rollIngredientList.add(new EelPortion(eelAmount));
		}
		if (riceAmount > 0.0D){
			rollIngredientList.add(new RicePortion(riceAmount));
		}
		if (yellowtailAmount > 0.0D) {
			rollIngredientList.add(new EelPortion(yellowtailAmount));
		}			
		if (seaweedAmount > 0.0D) {
			rollIngredientList.add(new SeaweedPortion(seaweedAmount));
		}
		if (shrimpAmount > 0.0D) {
			rollIngredientList.add(new ShrimpPortion(shrimpAmount));
		}
		if (tunaAmount > 0.0D) {
			rollIngredientList.add(new TunaPortion(tunaAmount));
		}
		IngredientPortion[] temp = (IngredientPortion[]) rollIngredientList.toArray(new IngredientPortion[rollIngredientList.size()]);
		sushi = new Roll(name, temp);
		return sushi;
	}


	private Sushi makeSashimi(ActionEvent event) {
		switch(sashimiCombo.getSelectedIndex()) {
		case 0: 
			sushi = new Sashimi(Sashimi.SashimiType.CRAB);
			break;
		case 1:
			sushi = new Sashimi(Sashimi.SashimiType.EEL);
			break;
		case 2:
			sushi = new Sashimi(Sashimi.SashimiType.YELLOWTAIL);
			break;
		case 3:
			sushi = new Sashimi(Sashimi.SashimiType.SHRIMP);
			break;
		case 4: 
			sushi = new Sashimi(Sashimi.SashimiType.TUNA);
			break;
		}
		return sushi;
	}

	private Sushi makeNigiri(ActionEvent event) {
		switch(nigiriCombo.getSelectedIndex()) {
		case 0: 
			sushi = new Nigiri(Nigiri.NigiriType.CRAB);
			break;
		case 1:
			sushi = new Nigiri(Nigiri.NigiriType.EEL);
			break;
		case 2:
			sushi = new Nigiri(Nigiri.NigiriType.YELLOWTAIL);
			break;
		case 3:
			sushi = new Nigiri(Nigiri.NigiriType.SHRIMP);
			break;
		case 4: 
			sushi = new Sashimi(Sashimi.SashimiType.TUNA);
			break;
		}
		return sushi;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "roll":
			makeRoll(e);
			makePlateRequest();
			break;

		case "sashimi":
			makeSashimi(e);
			makePlateRequest();
			break;

		case "nigiri":
			makeNigiri(e);
			makePlateRequest();
			break;
		}
	}
}
