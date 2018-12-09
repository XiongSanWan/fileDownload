package com.xy.web.servlet;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DownloadServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//得到要下载文件的路径,得到该文件的输入流
		String path = this.getServletContext().getRealPath("/WEB-INF/classes/熊耀的车.jpg");
		FileInputStream fis = new FileInputStream(path);
		//得到输出流
		ServletOutputStream sos = response.getOutputStream();
		//得到要下载的文件名
		String fileName = path.substring(path.lastIndexOf("\\")+1);
		
		if(request.getHeader("user-agent").toLowerCase().contains("msie")){//ie浏览器
			fileName = URLEncoder.encode(fileName,"utf-8");
		}else{
			fileName = new String(fileName.getBytes("utf-8"),"iso-8859-1");//火狐浏览器
		}
		//告诉浏览器要下载文件
		response.setHeader("content-disposition", "attachment;fileName="+fileName);
		//告诉浏览器下载的文件类型
		//response.setHeader("content-type", "image/jpeg");
		
		//不管下载什么类型的文件都可以
		response.setContentType(this.getServletContext().getMimeType(fileName));
		
		int len = 1;
		byte[] b = new byte[1024];
		while((len=fis.read(b)) != -1){
			sos.write(b,0,len);
		}
		fis.close();
		sos.close();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request,response);
	}

}
