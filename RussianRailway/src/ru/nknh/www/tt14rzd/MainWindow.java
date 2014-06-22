package ru.nknh.www.tt14rzd;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.SplashScreen;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Paths;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.TransferHandler;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;

import net.miginfocom.swing.MigLayout;

import org.apache.batik.swing.JSVGCanvas;
import org.pushingpixels.substance.api.skin.SubstanceNebulaLookAndFeel;

/**
 * 
 * @author RyazanovII
 * @version <h1>1.0</h1>
 *          <p>
 *          Create a main window of application
 *          </p>
 */

public class MainWindow extends JFrame {
	/**
	 * Launch the application.
	 */
	private static SplashScreen splash = SplashScreen.getSplashScreen();
	private RealizeGVTHandler gvtListener = null;
	private ActionHandler actionHandler = null;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				UIDefaults def = null;
				try {
					JFrame.setDefaultLookAndFeelDecorated(true);
					UIManager.setLookAndFeel(new SubstanceNebulaLookAndFeel());
					def = UIManager.getDefaults();
				} catch (Exception e) {
					System.err.println("Mistake with LaF");
					Thread.currentThread().interrupt();
				}
				try {
					MainWindow frame = new MainWindow();
					System.out.println(frame.getInsets());
					frame.addComponentListener(new ClsComponentListener(frame.getContentPane()));
					Thread.sleep(3000);
					splash.close();
					frame.setLocationRelativeTo(null);
					frame.pack();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainWindow() {
		gvtListener = new RealizeGVTHandler(this);
		splash = SplashScreen.getSplashScreen();
		actionHandler = new ActionHandler();
		actionHandler.setLoadingFrame(this);
		// set main icon
		try {
			ImageIcon toolicon = new ImageIcon(Paths.get("images", "Logo.png").toUri().toURL());
			setIconImage(toolicon.getImage());
		} catch (MalformedURLException e) {
			System.err.println(e.getMessage());
			Thread.currentThread().interrupt();
		}

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setMinimumSize(new Dimension(816, 754));
		setPreferredSize(new Dimension(816, 754));
		setMaximumSize(new Dimension(Short.MAX_VALUE, Short.MAX_VALUE));

		setContentPane();

		addComponentToPaneContext();

	}

	/**
	 * 
	 * @return maximal size, which can have inside Panel
	 */

	private Dimension getResolution() {
		Dimension windowSize = Toolkit.getDefaultToolkit().getScreenSize();
		System.out.println(windowSize);
		int w = windowSize.width - 216;
		int h = windowSize.height - 234;
		int nod = Math.min(w / 4, h / 3);
		return new Dimension(4 * nod + 8, 3 * nod + 8);

	}

	/**
	 * Create content pane
	 */

	private void setContentPane() {
		JPanel mainPanel = new JPanel();
		BoxLayout mainLayout = new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS);
		mainPanel.setLayout(mainLayout);

		mainPanel.setMinimumSize(new Dimension(808, 721));
		mainPanel.setPreferredSize(new Dimension(808, 721));
		mainPanel.setMaximumSize(new Dimension(Short.MAX_VALUE, Short.MAX_VALUE));

		setContentPane(mainPanel);
	}

	/**
	 * fill content pane
	 */

	private void addComponentToPaneContext() {
		JPanel commandPanel = null, cardPanel = null;

		commandPanel = new JPanel();
		commandPanel.setMinimumSize(new Dimension(808, 108));
		commandPanel.setPreferredSize(new Dimension(808, 108));
		commandPanel.setBorder(new CompoundBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), new EtchedBorder(
		        EtchedBorder.RAISED, null, null)));
		commandPanel.setBackground(new Color(0xCD919E));
		commandPanel.setLayout(new BoxLayout(commandPanel, BoxLayout.LINE_AXIS));
		addComponentToCommandPanel(commandPanel);

		cardPanel = new JPanel(new CardLayout());
		cardPanel.setMinimumSize(new Dimension(808, 608));
		cardPanel.setPreferredSize(new Dimension(808, 608));
		cardPanel.setBorder(new CompoundBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(0xCD919E), null),
		        new EtchedBorder(EtchedBorder.RAISED, new Color(0xCD919E), null)));
		makeCards(cardPanel);

		Dimension maxSize = getResolution();
		System.out.println(maxSize);
		commandPanel.setMaximumSize(new Dimension(maxSize.width, 158));
		cardPanel.setMaximumSize((maxSize));

		Container mainPanel = this.getContentPane();

		mainPanel.add("ComPanel", commandPanel);
		mainPanel.add(Box.createRigidArea(new Dimension(1, 5)));
		mainPanel.add("CardPanel", cardPanel);

	}

	/**
	 * fill context of CommandPanel
	 * 
	 * @param commandPanel
	 *            - Top container
	 */
	private void addComponentToCommandPanel(JPanel commandPanel) {
		JButton button1 = null, button2 = null;

		// sample create test button
		button1 = new JButton("1");
		button1.setMinimumSize(new Dimension(100, 100));
		button1.setPreferredSize(new Dimension(100, 100));
		button1.setMaximumSize(new Dimension(150, 150));

		button2 = new JButton("2");
		button2.setMinimumSize(new Dimension(100, 100));
		button2.setPreferredSize(new Dimension(100, 100));
		button2.setMaximumSize(new Dimension(150, 150));
		button2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Container mainPanel = getContentPane();
				JPanel cardPanel = (JPanel) mainPanel.getComponent(2);
				CardLayout cl = (CardLayout) cardPanel.getLayout();
				cl.show(cardPanel, "LoadXls");
			}
		});

		commandPanel.add(button1);
		commandPanel.add(new Box.Filler(new Dimension(5, 100), new Dimension(5, 100), new Dimension(10, 150)));
		commandPanel.add(button2);
	}

	/**
	 * Make main work panel
	 * 
	 * @param cardPanel
	 *            - JPanel which have CardLayout
	 */

	private void makeCards(JPanel cardPanel) {
		cardPanel.add(createJSVGCanvas(Boolean.TRUE), "HomePage");

		// create editor pane with instruction about load excel-file
		JEditorPane xlsInstruct = new JEditorPane();
		xlsInstruct.setBackground(Color.WHITE);
		xlsInstruct.setEditable(false);
		xlsInstruct.setContentType("text/html;charset=utf-8");
		try {
			xlsInstruct.setPage(Paths.get("script", "loadingXls.html").toUri().toURL());

		} catch (IOException e) {
			xlsInstruct.setContentType("text/plan");
			xlsInstruct.setText("Инструкция не найдена.");
		}

		JScrollPane scrollForXlsInstruct = new JScrollPane();
		// set excel instruct in scroll panel
		scrollForXlsInstruct.setViewportView(xlsInstruct);

		// panel with kit for download excel-file
		JPanel xlsLoad = new JPanel(new MigLayout("", "[grow 50][][][grow 50]", "[grow 50][][][grow 50]"));
		// add component for load kit
		JButton but = new JButton();
		but.setMinimumSize(new Dimension(40, 40));
		but.setMaximumSize(new Dimension(40, 40));
		XlsHunter xlsHunter = new XlsHunter();
		xlsHunter.setTransferHandler(new XlsHunterTransferHandler(TransferHandler.MOVE));

		// set action handler
		if (actionHandler.addActionSourse(but) == Boolean.TRUE) {
			but.addActionListener(actionHandler);
		}
		xlsLoad.add(but, "cell 2 1,alignx center,aligny center");
		xlsLoad.add(xlsHunter, "cell 1 2,alignx center,aligny center");
		// split pane as two pane of card layout
		JSplitPane splitXlsPage = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		splitXlsPage.setLeftComponent(xlsLoad);
		splitXlsPage.setRightComponent(scrollForXlsInstruct);
		splitXlsPage.setOneTouchExpandable(Boolean.TRUE);
		splitXlsPage.setContinuousLayout(Boolean.TRUE);
		// if main pane increase, left pane will get all extra space
		splitXlsPage.setResizeWeight(1.0);

		// for compound component with in splitXlsPage
		Dimension minimumSize = new Dimension(0, 0);
		scrollForXlsInstruct.setMinimumSize(minimumSize);
		scrollForXlsInstruct.setPreferredSize(new Dimension(300, 600));
		xlsLoad.setMinimumSize(minimumSize);
		xlsLoad.setPreferredSize(new Dimension(489, 600));

		// System.out.println(splitXlsPage.getDividerSize()); => 11 px - default
		// size of divider
		cardPanel.add(splitXlsPage, "LoadXls");
	}

	private JSVGCanvas createJSVGCanvas(Boolean is_startpage) {
		JSVGCanvas SvgImg = new JSVGCanvas();
		if (is_startpage == Boolean.TRUE) {
			SvgImg.addSVGDocumentLoaderListener(gvtListener.new RealizeGVTLoading());
			SvgImg.addGVTTreeBuilderListener(gvtListener.new RealizeGVTBuilder());
			SvgImg.addGVTTreeRendererListener(gvtListener.new RealizeGVTRenderer());
			SvgImg.setURI(Paths.get("images", "mainBaground.svg").toUri().toString());
		}
		return SvgImg;
	}

}
