package ru.nknh.www.tt14rzd;

import java.io.File;

import javax.swing.filechooser.FileFilter;

class XlsFilter extends FileFilter {

	@Override
	public boolean accept(File f) {
		// TODO Auto-generated method stub
		if (f.isDirectory())
			return Boolean.TRUE;
		String extension = Utils.getExtension(f);
		if (extension.equals(Utils.xls) || extension.equals(Utils.xlsx)) {
			return Boolean.TRUE;
		} else
			return Boolean.FALSE;
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return "Just excel files";
	}

}
