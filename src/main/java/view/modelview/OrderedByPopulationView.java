package view.modelview;

import java.awt.*;
import java.util.*;

import javax.swing.*;
import javax.swing.event.*;

import model.CountryDAO;
import model.CountryDTO;

public class OrderedByPopulationView extends JPanel implements Observer, ListSelectionListener {
    private JList list;
    private DefaultListModel listModel;
    
	public OrderedByPopulationView(CountryDAO countryDAO) {
        countryDAO.addObserver(this);
		
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
        list.addListSelectionListener(this);
        
        JScrollPane listScrollPane = new JScrollPane(list);        
        
        this.add(listScrollPane, BorderLayout.CENTER);
    }
	
	@Override
	public void update(Observable o, Object arg) {		
		// A change in the model, get new data and refresh view
		CountryDAO countryDAO = (CountryDAO) o;
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
	public void valueChanged(ListSelectionEvent e) {
		// AÑADIR UN BOTÓN DE BORRAR EL ELEMENTO SELECCIONADO Y ASÍ
		// TENGO ALGO MÁS DE JUEGO
		
	}
}
