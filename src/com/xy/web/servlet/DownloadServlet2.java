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

		String fileName = "���۰�.csv";
		
		if(request.getHeader("user-agent").toLowerCase().contains("msie")){//ie�����
			fileName = URLEncoder.encode(fileName,"utf-8");
		}else{
			fileName = new String(fileName.getBytes("utf-8"),"iso-8859-1");//��������
		}
		//��������������ļ�
		response.setHeader("content-disposition", "attachment;fileName="+fileName);
		//��������������ļ�������
		response.setContentType(this.getServletContext().getMimeType(fileName));
		//���߷�������utf-8����
		response.setCharacterEncoding("utf-8");
		
		PrintWriter pw = response.getWriter();
		pw.write("���ӻ���20\n");
		pw.write("���䣬20\n");
	}

}
