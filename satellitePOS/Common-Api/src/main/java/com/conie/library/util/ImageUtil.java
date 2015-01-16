package com.conie.library.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class ImageUtil {

	private static final int BUFFER_SIZE = 1024;
	public static String getTempfolder() {
		String tmpImageDir = initTempImageFolder();
		return tmpImageDir;
	}

	private static String initTempImageFolder() {
		String tmpImageDir = System.getProperty("jboss.server.temp.dir") + "/satelliteTmpImages/";
		File f = new File(tmpImageDir);
		if(!f.exists()){
			f.mkdir();
		}
		return tmpImageDir;
	}
	
	public static void writeTempImageFile(InputStream is, String fileName) throws FileNotFoundException, IOException {
		File result = new File(ImageUtil.getTempfolder() +fileName);
		FileOutputStream fileOutputStream = new FileOutputStream(result);
		byte[] buffer = new byte[BUFFER_SIZE];
		int bulk;
		InputStream inputStream = is;
		while (true) {
			bulk = inputStream.read(buffer);
			if (bulk < 0) {
				break;
			}
			fileOutputStream.write(buffer, 0, bulk);
			fileOutputStream.flush();
		}
		fileOutputStream.close();
		inputStream.close();
	}
}