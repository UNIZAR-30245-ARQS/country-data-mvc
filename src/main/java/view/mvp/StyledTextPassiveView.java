package view.mvp;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.util.Observable;

import javax.swing.*;

import model.CountryDAO;
import model.CountryDTO;

/**
 * This passive view just reflects some changes. It does not respond to user
 * events (so it does not implement ActionListener).
 * @author rbejar
 *
 */
public class StyledTextPassiveView extends JPanel {	
	private JEditorPane editorPane;
	private StyledTextPresenter presenter; 
	

	public StyledTextPassiveView() {
		presenter = new StyledTextPresenter(this);

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
	
	public StyledTextPresenter getPresenter() { 
		return presenter;
	}
	
	public void setData(String countryName, int countryPopulation) {
		editorPane.setText("<h2><strong>"+countryName+", "+
				countryPopulation+"</strong></h2>");			
	}
}