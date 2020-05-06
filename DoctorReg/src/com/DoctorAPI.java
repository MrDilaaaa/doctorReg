package com;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.Doctor;


@WebServlet("/DoctorAPI")
public class DoctorAPI extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
	Doctor docObj =new Doctor();

    public DoctorAPI() {
        super();
 
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String output = docObj.insertDoc(request.getParameter("firstName"),
				request.getParameter("lastName"),
				request.getParameter("email"),
				request.getParameter("password"),
				request.getParameter("specialty"),
				request.getParameter("nic"),
				request.getParameter("phoneNo"));
				
			response.getWriter().write(output);
	}
	
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		Map paras = getParasMap(request);
		String output = docObj.updateDoc(paras.get("firstName").toString(),
				paras.get("lastName").toString(),
				paras.get("email").toString(),
				paras.get("password").toString(),
				paras.get("hiddoctorIDSave").toString(),
				paras.get("specialty").toString(),
				paras.get("nic").toString(),
				paras.get("phoneNo").toString());

		response.getWriter().write(output); 
	}
	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	

		Map paras = getParasMap(request);
		String output = docObj.deleteDoc(paras.get("doctorID").toString());
		response.getWriter().write(output); 
	}
	
	private static Map getParasMap(HttpServletRequest request)
	{
		Map<String, String> map = new HashMap<String, String>();
		try
		{
			Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
			String queryString = scanner.hasNext() ?
					scanner.useDelimiter("\\A").next() : "";
					scanner.close();
					String[] params = queryString.split("&");
					for (String param : params)
					{ 

						String[] p = param.split("=");
						map.put(p[0], p[1]);
					}
		}
		catch (Exception e)
		{
		}
		return map;
	}


}
