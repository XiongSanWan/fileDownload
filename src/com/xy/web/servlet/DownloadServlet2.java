package com.xy.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DownloadServlet2 extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request,response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String fileName = "销售榜单.csv";
		
		if(request.getHeader("user-agent").toLowerCase().contains("msie")){//ie浏览器
			fileName = URLEncoder.encode(fileName,"utf-8");
		}else{
			fileName = new String(fileName.getBytes("utf-8"),"iso-8859-1");//火狐浏览器
		}
		//告诉浏览器下载文件
		response.setHeader("content-disposition", "attachment;fileName="+fileName);
		//告诉浏览器下载文件的类型
		response.setContentType(this.getServletContext().getMimeType(fileName));
		//告诉服务器用utf-8编码
		response.setCharacterEncoding("utf-8");
		
		PrintWriter pw = response.getWriter();
		pw.write("电视机，20\n");
		pw.write("冰箱，20\n");
	}

}
