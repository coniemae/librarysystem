package com.conie.library.webcontroller.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.conie.library.util.ImageUtil;

/**
 * Servlet implementation class adsasd
 */
@WebServlet("/images/dynamic/*")
public class DynamicImageServlet extends HttpServlet {
	private static final long serialVersionUID = -7710395708952475247L;
	Logger logger = Logger.getLogger("DynamicImageServlet");
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException

	{
		try {
			// Get image file.
			String file = request.getParameter("file");
			BufferedInputStream in = new BufferedInputStream(new FileInputStream(ImageUtil.getTempfolder() + file));
			// Get image contents.
			byte[] bytes = new byte[in.available()];
			in.read(bytes);
			in.close();
			// Write image contents to response.
			response.getOutputStream().write(bytes);
			new File(ImageUtil.getTempfolder() + file).delete();
		} catch (IOException e) {
			//IGNORE (exception only occures when the temporary file is deleted)
		}

	}

}
