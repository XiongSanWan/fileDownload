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
		
		//�õ�Ҫ�����ļ���·��,�õ����ļ���������
		String path = this.getServletContext().getRealPath("/WEB-INF/classes/��ҫ�ĳ�.jpg");
		FileInputStream fis = new FileInputStream(path);
		//�õ������
		ServletOutputStream sos = response.getOutputStream();
		//�õ�Ҫ���ص��ļ���
		String fileName = path.substring(path.lastIndexOf("\\")+1);
		
		if(request.getHeader("user-agent").toLowerCase().contains("msie")){//ie�����
			fileName = URLEncoder.encode(fileName,"utf-8");
		}else{
			fileName = new String(fileName.getBytes("utf-8"),"iso-8859-1");//��������
		}
		//���������Ҫ�����ļ�
		response.setHeader("content-disposition", "attachment;fileName="+fileName);
		//������������ص��ļ�����
		//response.setHeader("content-type", "image/jpeg");
		
		//��������ʲô���͵��ļ�������
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
