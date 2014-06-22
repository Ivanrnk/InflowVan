package ru.nknh.www.tt14rzd;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Paths;

import javax.imageio.ImageIO;
import javax.swing.JComponent;

class XlsHunter extends JComponent {
	BufferedImage img = null;

	public XlsHunter() {
		try {
			img = ImageIO.read(Paths.get("images", "LogoForXlsHunter.png").toFile());
		} catch (IOException e) {
		}
	}

	public void paint(Graphics g) {
		g.drawImage(img, 0, 0, null);
	}

	public Dimension getPreferredSize() {
		if (img == null)
			return new Dimension(100, 100);
		else
			return new Dimension(img.getWidth(), img.getHeight());
	}
}
