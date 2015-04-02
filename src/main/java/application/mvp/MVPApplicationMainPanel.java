package application.mvp;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import model.CountryDAO;
import model.CountryDTO;

public class MVPApplicationMainPanel extends JPanel implements ActionListener {
	private static final String buttonText = "Insert/Update Country";

	private CountryDAO countryDAO;
	private JTextField countryNameField; 
	private JTextField countryPopulationField;
	private JLabel countryNameFieldLabel; 
	private JLabel countryPopulationFieldLabel;


	public MVPApplicationMainPanel(CountryDAO countryDAO, 
			JPanel orderedView,
			JPanel styledView) {
		
		super(new BorderLayout());

		this.countryDAO = countryDAO;

		countryNameField = new JTextField(10);

		countryNameFieldLabel = new JLabel("Country: ");
		countryNameFieldLabel.setLabelFor(countryNameField);

		countryPopulationField = new JTextField(10);

		countryPopulationFieldLabel = new JLabel("Population: ");
		countryPopulationFieldLabel.setLabelFor(countryPopulationField);

		JButton addCountryButton = new JButton();
		addCountryButton.setText(buttonText);
		addCountryButton.setMargin(new Insets(0,0,0,0));
		addCountryButton.setActionCommand(buttonText);
		addCountryButton.addActionListener(this);


		// 2 columns, as many rows as necessary
		JPanel textInputPanel = new JPanel(new GridLayout(0, 2));
		textInputPanel.add(countryNameFieldLabel);
		textInputPanel.add(countryNameField);
		textInputPanel.add(countryPopulationFieldLabel);
		textInputPanel.add(countryPopulationField);
		textInputPanel.add(addCountryButton);


		textInputPanel.setBorder(
				BorderFactory.createCompoundBorder(
						BorderFactory.createTitledBorder("Input"),
						BorderFactory.createEmptyBorder(3,3,3,3)));

		this.add(textInputPanel, BorderLayout.NORTH);		
			
		this.add(orderedView, BorderLayout.CENTER);
		this.add(styledView, BorderLayout.SOUTH);		
	}

	@Override
	public void actionPerformed(ActionEvent e) {		
		// Button click
		if (e.getActionCommand().equals(buttonText)) {
			countryNameFieldLabel.setForeground(Color.black);
			countryPopulationFieldLabel.setForeground(Color.black);

			String candidateCountryName = countryNameField.getText();
			int candidatePopulation = -1;
			try {
				candidatePopulation = Integer.parseInt(countryPopulationField.getText());					
			} catch (NumberFormatException ex) {
				// give it a wrong value, so the validators catch it and warn the user
				candidatePopulation = -1;
			}

			// User input validation is very simple			
			if (countryDAO.isValidCountryName(candidateCountryName)) {
				if (countryDAO.isValidCountryPopulation(candidatePopulation)) {
					try {
						if (countryDAO.find(candidateCountryName) != null) {											
							countryDAO.update(new CountryDTO(candidateCountryName, candidatePopulation));
						} else {						
							countryDAO.insert(new CountryDTO(candidateCountryName, candidatePopulation));
						}
						return; 
					} catch (Exception ex) {
						ex.printStackTrace();
						// An exception here means a problem accessing the data.
						// We can't go on
						System.exit(1);				
					}
				}
			} 

			if (!countryDAO.isValidCountryName(candidateCountryName)) {				
				countryNameFieldLabel.setForeground(Color.red);
			}

			if (!countryDAO.isValidCountryPopulation(candidatePopulation)) {				
				countryPopulationFieldLabel.setForeground(Color.red);
			}
		}
	}
}
