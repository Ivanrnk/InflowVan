package ru.nknh.www.tt14rzd;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class ClsComponentListener extends ComponentAdapter {
	private Container mainPanel;
	private Dimension lastSize = null;

	ClsComponentListener(Container cP) {
		mainPanel = cP;
	}

	@Override
	public void componentResized(ComponentEvent e) {
		Container framePanel = (Container) e.getComponent();
		Dimension framePanelSize = framePanel.getSize();
		if (lastSize == null)
			lastSize = new Dimension(framePanelSize);
		System.out.println(framePanelSize);
		Dimension maxSize = Toolkit.getDefaultToolkit().getScreenSize();
		if (maxSize.width != framePanelSize.width) {
			Component[] component = mainPanel.getComponents();
			int w = component[2].getSize().width - 8;
			int h = component[2].getSize().height - 8;
			int nod;
			if (framePanelSize.height >= lastSize.height && framePanelSize.width >= lastSize.width)
				nod = Math.max((w + 3) / 4, (h + 2) / 3);
			else
				nod = Math.min((w + 3) / 4, (h + 2) / 3);
			w = 4 * nod - w;
			h = 3 * nod - h;
			lastSize = new Dimension(framePanelSize.width + w, framePanelSize.height + h
					+ Math.min(component[0].getMaximumSize().height - component[0].getSize().height, h));
			framePanel.setSize(lastSize);
		}
	}
}
