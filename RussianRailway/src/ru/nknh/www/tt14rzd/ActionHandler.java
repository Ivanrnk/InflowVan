package ru.nknh.www.tt14rzd;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

class ActionHandler implements ActionListener {

	private List<JButton> actionSource = null;
	private JFileChooser xlsChooser = null;
	private JFrame loadingFrame = null;

	public ActionHandler() {
		actionSource = new ArrayList<JButton>();
		xlsChooser = new JFileChooser();
		xlsChooser.setAcceptAllFileFilterUsed(false);
		xlsChooser.addChoosableFileFilter(new XlsFilter());
	}

	public boolean addActionSourse(JButton src) {
		if (actionSource.indexOf(src) == -1) {
			return actionSource.add(src);
		} else
			return Boolean.FALSE;
	}

	public void setLoadingFrame(JFrame frame) {
		loadingFrame = frame;
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == actionSource.get(0)) {
			xlsChooser.showOpenDialog(loadingFrame);
		}
	}
}
