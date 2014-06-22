package ru.nknh.www.tt14rzd;

import java.awt.Cursor;

import javax.swing.JFrame;

import org.apache.batik.swing.gvt.GVTTreeRendererAdapter;
import org.apache.batik.swing.gvt.GVTTreeRendererEvent;
import org.apache.batik.swing.svg.GVTTreeBuilderAdapter;
import org.apache.batik.swing.svg.SVGDocumentLoaderAdapter;

class RealizeGVTHandler {
    private JFrame frame = null;
    private Boolean firstTime = true;

    RealizeGVTHandler(JFrame _frame) {
	frame = _frame;
	frame.setTitle("Loading...");
	frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
    }

    public Boolean checkFirstTime() {
	return firstTime;
    }

    class RealizeGVTLoading extends SVGDocumentLoaderAdapter {
	public void documentLoadnigStarted(GVTTreeRendererEvent e) {
	    if (checkFirstTime() == true) {
		frame.setTitle("Loading..");
	    }
	}

	public void documentLoadnigCompleted(GVTTreeRendererEvent e) {
	    if (checkFirstTime() == true)
		frame.setTitle("Loading.");
	}
    }

    class RealizeGVTBuilder extends GVTTreeBuilderAdapter {
	public void gvtBuilderStarted(GVTTreeRendererEvent e) {
	    if (checkFirstTime() == true) {
		frame.setTitle("Loading...");
	    }
	}

	public void gvtBuilderCompleted(GVTTreeRendererEvent e) {
	    if (checkFirstTime() == true)
		frame.setTitle("Loading..");
	}
    }

    class RealizeGVTRenderer extends GVTTreeRendererAdapter {
	public void gvtRenderingPrepare(GVTTreeRendererEvent e) {
	    if (checkFirstTime() == true) {
		frame.setTitle("Loading.");
	    }
	}

	public void gvtRenderingCompleted(GVTTreeRendererEvent e) {
	    if (checkFirstTime() == true) {
		frame.setTitle("");
		frame.setCursor(null);
	    }
	    firstTime = false;
	}
    }
}
