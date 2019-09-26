package com.revature.daos;

import java.sql.Date;
import java.util.List;

import com.revature.models.Request;

public interface RequestDao {
	public List<Request> getAll();
	public List<Request> getRequestByStatus(String status);
	public List<Request> getRequestByEmplyeeName(String employeeName);
	public boolean updateRequest(Request updatedRequest);
	public boolean newRequest(Date requestDate, int submitFrom, Double requestAmount, String requestCategory);
//	public Date stringToDate(String s);
	public List<Request> getRequestByEmplyeeNameAndStatus(String employeeName, String status);

}
