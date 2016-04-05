package view.mvp;

import java.awt.BorderLayout;
import javax.swing.*;

/**
 * This passive view just reflects some changes in the model (but those changes are 
 * triggered by its Presenter). It does not respond to user
 * events (so it does not implement ActionListener).
 * @author rbejar
 * This class depends on StyledTextPresenter, but that is not really necessary.
 * The other passive view (OrderedByPopulationPassiveView) depends on its presenter
 * because it relays it some user action events, and I have chosen to keep the
 * design of both views as similar as possible.
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