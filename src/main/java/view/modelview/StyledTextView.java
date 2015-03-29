package view.modelview;

import java.awt.*;
import java.util.*;

import javax.swing.*;

import model.CountryDAO;
import model.CountryDTO;

public class StyledTextView extends JPanel implements Observer {
	private JEditorPane editorPane;

	public StyledTextView(CountryDAO countryDAO) {
		countryDAO.addObserver(this);

		setLayout(new BorderLayout());

		editorPane = new JEditorPane();
		editorPane.setEditable(false);
		editorPane.setContentType("text/html");        
		editorPane.setText("None updated");

		JScrollPane editorScrollPane = new JScrollPane(editorPane);
		
		editorScrollPane.setBorder(
				BorderFactory.createCompoundBorder(
						BorderFactory.createTitledBorder("Latest Change"),
						BorderFactory.createEmptyBorder(3,3,3,3)));

		this.add(editorScrollPane, BorderLayout.CENTER);
	}

	@Override
	public void update(Observable o, Object arg) {
		// A change in the model, get new data and refresh view
		CountryDTO countryDTO = (CountryDTO) arg;	

		editorPane.setText("<h2><strong>"+countryDTO.getName()+", "+
				countryDTO.getPopulation()+"</strong></h2>");	
	}
}
