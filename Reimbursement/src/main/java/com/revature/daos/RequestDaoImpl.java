package com.revature.daos;


import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
//import java.util.Calendar;
import java.sql.Date;
import java.util.List;

//import com.revature.models.Department;
import com.revature.models.Employee;
import com.revature.models.Request;
import com.revature.util.ConnectionUtil;

public class RequestDaoImpl implements RequestDao{
    
	private List<Request> requests = new ArrayList<>();
	private EmployeeDao emDao = new EmployeeDaoImpl();
	private DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	
	
//	public Date stringToDate(String s){
//
//	    Date result = null;
//	    try{
//	        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//	        result  = dateFormat.parse(s);
//	    }
//
//	    catch(ParseException e){
//	        e.printStackTrace();
//
//	    }
//	    return result ;
//	}
	
	public RequestDaoImpl() {
		super();
	}
//		requests.add(new Request(1, stringToDate("2014-11-15"), emDao.getEmployeeById(2), 520.6, "travel", "approved", stringToDate("2014-11-25"), emDao.getEmployeeById(5)));
//		requests.add(new Request(2, stringToDate("2015-01-08"), emDao.getEmployeeById(1), 846.12, "dining", "denied", stringToDate("2015-01-28"), emDao.getEmployeeById(5)));
//		requests.add(new Request(3, stringToDate("2015-02-23"), emDao.getEmployeeById(1), 410.8, "travle", "approved", stringToDate("2015-02-30"), emDao.getEmployeeById(5)));
//		requests.add(new Request(4, stringToDate("2015-02-16"), emDao.getEmployeeById(4), 818.2, "travle", "pending", null, null));
//		requests.add(new Request(5, stringToDate("2014-10-13"), emDao.getEmployeeById(6), 1281, "travle", "approved", stringToDate("2014-10-22"), emDao.getEmployeeById(5)));
//		requests.add(new Request(6, stringToDate("2015-01-23"), emDao.getEmployeeById(9), 658.8, "office", "approved", stringToDate("2015-02-09"), emDao.getEmployeeById(8)));
//		requests.add(new Request(7, stringToDate("2015-02-06"), emDao.getEmployeeById(7), 2240, "office", "pending", null, null));
//		requests.add(new Request(8, stringToDate("2015-02-15"), emDao.getEmployeeById(9), 874, "office", "pending", null,null));
//	}

	@Override
	public List<Request> getAll() {
		List<Request> reqs = new ArrayList<>();
        String sql = "select * from \"Reimbursement\".request";
		
		try(Connection c =ConnectionUtil.getHardCodeConnection();
				Statement s = c.createStatement(); ResultSet rs = s.executeQuery(sql);){
		        
		     
				while(rs.next()) {
					int requestId = rs.getInt("request_id");
					java.sql.Date submitDate = rs.getDate("request_date");
					int employeeId = rs.getInt("submit_from");
					Employee submitFrom = emDao.getEmployeeById(employeeId);
					double requestAmount = rs.getDouble("request_amount");
					String spendCategory = rs.getNString("spend_category");
					String requestStatus = rs.getString("request_status");
					Date resolvedDate = rs.getDate("resolved_date");
					int managerId = rs.getInt("resolved_by");
					Employee manager = emDao.getEmployeeById(managerId);
					Request request = new Request(requestId, submitDate, submitFrom, requestAmount, spendCategory, requestStatus,resolvedDate, manager);
					reqs.add(request);					
				}
			    
	
			    
			} catch (SQLException error) {
		
				error.printStackTrace();
			}
		return requests;
	}
		
		
	

	@Override
	public List<Request> getRequestByStatus(String status) {
		List<Request> reqs = new ArrayList<>();
		for(Request re: requests) {
			if(re.getStatus() == status) {
				reqs.add(re);
			}			
		}
		return reqs;
	}

	// for employee view pending or resolved requests
	@Override
	public List<Request> getRequestByEmplyeeNameAndStatus(String employeeName, String status) {
		
		List<Request> reqs = new ArrayList<>();
		Employee e = new Employee();
		
		e = emDao.getEmployeeByName(employeeName);
		
		String sql = "select * from \"Reimbursement\".request where submit_from = ? and request_status= ?";
		
		try(Connection c =ConnectionUtil.getHardCodeConnection();
				PreparedStatement ps = c.prepareStatement(sql)){
			      
		        ps.setInt(1, e.getId());
		        ps.setString(2,status);
		        
		        ResultSet rs = ps.executeQuery();
			        
				while(rs.next()) {
					int requestId = rs.getInt("request_id");
					java.sql.Date submitDate = rs.getDate("request_date");
					int employeeId = rs.getInt("submit_from");
					Employee submitFrom = emDao.getEmployeeById(employeeId);
					double requestAmount = rs.getDouble("request_amount");
					String spendCategory = rs.getString("spend_category");
					String requestStatus = rs.getString("request_status");
					java.sql.Date resolvedDate = null;
					Employee manager = new Employee();
					manager = null;
					if(status.equals("approved")) {
						resolvedDate = rs.getDate("resolved_date");
						int managerId = rs.getInt("resolved_by");
						manager = emDao.getEmployeeById(managerId);
					}
					Request request = new Request(requestId, submitDate, submitFrom, requestAmount, spendCategory, requestStatus,resolvedDate, manager);
					reqs.add(request);					
				}
			    
	
			    
			} catch (SQLException error) {
		
				error.printStackTrace();
			}
		return reqs;
	}
 // for manager resolved a reimbursement request
	@Override
	public boolean updateRequest(Request updatedRequest) {
		boolean requestFound = false;
		for(Request req: requests) {
			if(req.getId() == updatedRequest.getId()) {
				requestFound = true;
				req.setRequestTime(updatedRequest.getRequestTime());
				req.setSubmitFrom(updatedRequest.getSubmitFrom());
				req.setRequestAmount(updatedRequest.getRequestAmount());
				req.setApprovedBy(updatedRequest.getApprovedBy());;
				req.setSpendCategory(updatedRequest.getSpendCategory());
				req.setStatus(updatedRequest.getStatus());
				req.setResolveDate(updatedRequest.getResolveDate());
			}
		}
		
		return requestFound;
	}
	
	public boolean newRequest(Date requestDate, int submitFrom, Double requestAmount, String requestCategory) {
		
		String sql = "insert into \"Reimbursement\".request (request_date, submit_from, request_amount, spend_category, request_status, resolved_date, resolved_by) values (?, ?, ?, ?, 'pending', null, null)";
//		insert into request(request_date, submit_from, request_amount, spend_category, request_status, resolved_date,resolved_by)
//		Request re = new Request();
		int updatedRow = 0;
		
		try(Connection c = ConnectionUtil.getHardCodeConnection(); PreparedStatement ps = c.prepareStatement(sql)){
			
//			System.out.println("Get Connected with database");
			
			ps.setDate(1, requestDate);
			ps.setInt(2, submitFrom);
			ps.setDouble(3, requestAmount);
			ps.setString(4, requestCategory);
			
			updatedRow = ps.executeUpdate();
			
		}catch (SQLException sqle) {
			sqle.printStackTrace();
		}
//		for(Request req: requests) {
//			if(req.getId()>maxId) {
//				maxId = req.getId();
//			}
//		}
//		
//		int newRequestId = maxId +1;
//		
        System.out.println("updatedRow:"+ updatedRow);
//		requests.add(new Request(newRequestId, stringToDate(requestTime), emDao.getEmployeeById(submitFrom), requestAmount, requestCategory,"pending", null,null));		
		if(updatedRow > 0) {
			return true;
		}else {
			System.out.println("issue with create new request!");
			return false;
		}
	}
   //employee view all requests
	@Override
	public List<Request> getRequestByEmplyeeName(String employeeName) {
		// TODO Auto-generated method stub
		List<Request> requests = new ArrayList<>();
		
		int employeeId = emDao.getEmployeeByName(employeeName).getId();
		
		String sql ="select * from \"Reimbursement\".request where sumbit_from = ?";
		
		try(Connection c = ConnectionUtil.getHardCodeConnection(); PreparedStatement ps = c.prepareStatement(sql)){
			
			ps.setInt(1,employeeId);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				int requestId = rs.getInt("request_id");
				java.sql.Date requestDate = rs.getDate("request_date");
				int submitFromId = rs.getInt("submit_from");
				Employee submitFrom = emDao.getEmployeeById(submitFromId);
				double requestAmount = rs.getDouble("request_amount");
				String spendCategory = rs.getString("spend_category");
				String status = rs.getString("request_status");
				java.sql.Date resolvedDate = rs.getDate("resolved_date");
				int resolvedById = rs.getInt("resolved_by");
				Employee resolvedBy = emDao.getEmployeeById(resolvedById);
				
				Request req = new Request(requestId, requestDate, submitFrom, requestAmount, spendCategory, status, resolvedDate, resolvedBy);
				
				requests.add(req);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return requests;
	}

	
	

	
}
