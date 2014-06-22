package ru.nknh.www.tt14rzd;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.swing.TransferHandler;

class XlsHunterTransferHandler extends TransferHandler {
	private int action;
	private List<File> fileList = null;

	public XlsHunterTransferHandler(int action) {
		this.action = action;
	}

	/*
	 * We only import excel file
	 */
	public boolean canImport(TransferHandler.TransferSupport info) {
		if (!info.isDataFlavorSupported(DataFlavor.javaFileListFlavor) || !info.isDrop())
			return Boolean.FALSE;
		try {
			fileList = (List<File>) info.getTransferable().getTransferData(DataFlavor.javaFileListFlavor);
			if (fileList.isEmpty())
				return Boolean.FALSE;
			else {
				File f = fileList.get(0);
				if (f.isDirectory())
					return Boolean.FALSE;
				boolean moveSupport = (action & info.getSourceDropActions()) == action;

				if (moveSupport == Boolean.FALSE)
					return Boolean.FALSE;

				String extension = Utils.getExtension(f);

				if (extension.equals(Utils.xls) || extension.equals(Utils.xlsx)) {
					info.setDropAction(action);
					return Boolean.TRUE;
				} else
					return Boolean.FALSE;
			}
		} catch (IOException | UnsupportedFlavorException e) {
			return Boolean.FALSE;
		}
	}

	public boolean importData(TransferHandler.TransferSupport info) {
		if (!canImport(info))
			return Boolean.FALSE;
		File f = fileList.get(0);
		return Boolean.TRUE;
	}
}
