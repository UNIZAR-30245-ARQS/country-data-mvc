package view.modelview;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;
import javax.swing.event.*;

import model.CountryDAO;
import model.CountryDTO;

public class OrderedByPopulationView extends JPanel implements Observer, ActionListener {

	private static final String buttonText = "Delete Selected Country";

	private JList list;
	private DefaultListModel listModel;
	private CountryDAO countryDAO;


	public OrderedByPopulationView(CountryDAO countryDAO) {
		this.countryDAO = countryDAO;
		this.countryDAO.addObserver(this);

		setLayout(new BorderLayout());

		listModel = new DefaultListModel();
		try {
			for (CountryDTO country: countryDAO.findAllOrderedByPopulation()) {
				listModel.addElement(country.getName()+", "+country.getPopulation());
			}
		} catch (Exception e) {
			e.printStackTrace();
			// Exception reading data from the model. We can't go on
			System.exit(1);
		}

		//Create the list and put it in a scroll pane.
		list = new JList(listModel);        
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);        
		list.setVisibleRowCount(5);

		JScrollPane listScrollPane = new JScrollPane(list);

		JButton deleteCountryButton = new JButton();
		deleteCountryButton.setText(buttonText);
		deleteCountryButton.setMargin(new Insets(0,0,0,0));
		deleteCountryButton.setActionCommand(buttonText);
		deleteCountryButton.addActionListener(this);

		JPanel containerPanel = new JPanel(new BorderLayout());
		containerPanel.add(listScrollPane, BorderLayout.CENTER);
		containerPanel.add(deleteCountryButton, BorderLayout.SOUTH);

		this.add(containerPanel, BorderLayout.CENTER);
	}

	@Override
	public void update(Observable o, Object arg) {		
		listModel.clear();
		try {
			for (CountryDTO country: countryDAO.findAllOrderedByPopulation()) {
				listModel.addElement(country.getName()+", "+country.getPopulation());
			}
		} catch (Exception e) {
			e.printStackTrace();
			// Exception reading data from the model. We can't go on
			System.exit(1);
		}		
	}

	@Override
	public void actionPerformed(ActionEvent e) {		
		// Button click
		if (e.getActionCommand().equals(buttonText)) {
			String selected = (String)list.getSelectedValue();
			if (selected != null) {
				String countryName = selected.substring(0, selected.indexOf(','));
				try {
					countryDAO.delete(countryName);
				} catch (Exception ex) {
					ex.printStackTrace();
					// Deleting a country which does not exist should not happen. Exit
					System.exit(1);
				}
			}
		}
	}
}
