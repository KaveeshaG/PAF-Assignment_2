package com.hos.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hos.model.Hospital;
import com.hos.controller.*;;
/**
 * 
 * @author KaveeshaG
 * all the services regarding to Hospitals are implemented here
 */
@WebServlet("/HospitalAPI")
public class HospitalAPI extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Hospital hos = new Hospital();
	HospitalController hosc = new HospitalController(); 
//	PaymentSchemeBean psBean = new PaymentSchemeBean();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		hos.setHid(Integer.parseInt(req.getParameter("doc_id")));	
		hos.setHospitalname(req.getParameter("hospitalname"));
		hos.setHospitaladdress(req.getParameter("hospitaladdress"));
		hos.setContactnumber(req.getParameter("contactnumber"));
		
//		String output = hos.insertHospital(hos);
		String output = hosc.insertHospital(hos);
		
		resp.getWriter().write(output); 
		
	}

}
