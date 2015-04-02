package view.mvp;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.*;

/**
* This passive view reflects changes in the model (but those changes are 
* triggered by its Presenter) and responds to user events (but it is
* its Presenter the responsible for acting accordingly to each user event)
* @author rbejar
*
*/
public class OrderedByPopulationPassiveView extends JPanel implements ActionListener {
	// I can make this public safely, it is only a constant 
	public static final String buttonText = "Delete Selected Country";

	private JList list;
	private DefaultListModel listModel;
	private JButton deleteCountryButton;
	private OrderedByPopulationPresenter presenter; 


	public OrderedByPopulationPassiveView() {
		presenter = 
				new OrderedByPopulationPresenter(this);
		
		setLayout(new BorderLayout());			

		listModel = new DefaultListModel();

		//Create the list and put it in a scroll pane.
		list = new JList(listModel);        
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);        
		list.setVisibleRowCount(5);

		JScrollPane listScrollPane = new JScrollPane(list);

		deleteCountryButton = new JButton();
		deleteCountryButton.setText(buttonText);
		deleteCountryButton.setMargin(new Insets(0,0,0,0));
		deleteCountryButton.setActionCommand(buttonText);
		deleteCountryButton.addActionListener(this);
		

		JPanel containerPanel = new JPanel(new BorderLayout());
		containerPanel.add(listScrollPane, BorderLayout.CENTER);
		containerPanel.add(deleteCountryButton, BorderLayout.SOUTH);

		this.add(containerPanel, BorderLayout.CENTER);
	}
	
	public OrderedByPopulationPresenter getPresenter() { 
		return presenter;
	}

	public void setData(List<String> dataList) {
		listModel.clear();
		for (String item: dataList) {
			listModel.addElement(item);	
		}		
	}
		
	/**
	 * A passive view responds to the user events. But the logic of
	 * the response is in the Presenter.
	 */
	@Override	
	public void actionPerformed(ActionEvent e) {
		String selected = (String)list.getSelectedValue();
		presenter.deleteSelectedCountry(selected);
	}
}
