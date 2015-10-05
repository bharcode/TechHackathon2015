package com.aexp.hackathon.dbms;

import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.*;
import java.util.Random;

/**
 * Created by vdevabat on 22/07/15.
 */
public class AugMethods {
	//
	//    public static void main(String[] args) {
	//
	//
	//        AugMethods test = new AugMethods();
	//        try {
	//            test.getTableData();
	//        } catch (Exception e) {
	//            System.err.println("Got an exception!");
	//            System.err.println(e.getMessage());
	//        }
	//    }

	public static long updateTable(String jarName, String className, String methodName, String inputParams, String outputParams) throws SQLException {

		String createtable = "create table if not exists apiDetails (id bigint not null auto_increment, jarname varchar(128), className varchar(128), "
				+ "methodName varchar(128), inputParams text, outputParams varchar(128), downloadLink varchar(256), primary key(id));";

		//Connection
		String myUrl = "jdbc:mysql://localhost/hackathon";
		Connection conn = DriverManager.getConnection(myUrl, "root", "");
		String query = " insert into apiDetails (jarName, className, methodName, inputParams, outputParams)"
				+ " values (?, ?, ?, ?, ?);";
		
		PreparedStatement ps  = conn.prepareStatement(createtable);
		ps.execute();
		ps.close();

		// Modifiing query
		PreparedStatement preparedStmt = conn.prepareStatement(query,PreparedStatement.RETURN_GENERATED_KEYS);
		//        preparedStmt.setInt(1, rand.nextInt(500000));
		preparedStmt.setString(1, jarName);
		preparedStmt.setString(2, className);
		preparedStmt.setString(3, methodName);
		preparedStmt.setString(4, inputParams);
		preparedStmt.setString(5, outputParams);

		preparedStmt.executeUpdate();
		long apiId = -1;
		ResultSet rs = preparedStmt.getGeneratedKeys();
		if (rs != null && rs.next()) {
			apiId = rs.getLong(1);
		}
		
		preparedStmt.close();
		rs.close();

		conn.close();
		return apiId;
	}

	public static long updateDownloadLink(long apiId , String downloadLink) throws SQLException {

		String createtable = "create table if not exists apiDetails (id bigint not null auto_increment, jarname varchar(128), className varchar(128), "
				+ "methodName varchar(128), inputParams text, outputParams varchar(128), downloadLink varchar(256), primary key(id));";

		//Connection
		String myUrl = "jdbc:mysql://localhost/hackathon";
		Connection conn = DriverManager.getConnection(myUrl, "root", "");

		// Base querry

		Random rand = new Random();
		String tableName = "test"; // TBD
		String query = " update apiDetails set downloadLink =\"" + downloadLink +"\" where id=" + apiId + ";";


		// Modifiing query
		PreparedStatement preparedStmt = conn.prepareStatement(query);
		//        preparedStmt.setInt(1, rand.nextInt(500000));

		preparedStmt.executeUpdate();
		preparedStmt.close();
		conn.close();
		return apiId;
	}


	public static JSONArray getTableData() throws Exception {
		//Connection
		String myUrl = "jdbc:mysql://localhost/hackathon";
		Connection conn = DriverManager.getConnection(myUrl, "root", "");

		// Base querry
		String tableName = "apiDetails"; // TBD
		String query = " select * from " + tableName + " ;";
		Statement st = conn.createStatement();

		// execute the statement;
		ResultSet rs = st.executeQuery(query);

		JSONArray jArray = convertResultSetIntoJSON(rs);
		rs.close();
		conn.close();

		return jArray;
	}


	public static JSONArray convertResultSetIntoJSON(ResultSet resultSet) throws Exception {
		JSONArray jsonArray = new JSONArray();
		while (resultSet.next()) {
			int total_rows = resultSet.getMetaData().getColumnCount();
			JSONObject obj = new JSONObject();
			for (int i = 0; i < total_rows; i++) {
				String columnName = resultSet.getMetaData().getColumnLabel(i + 1).toLowerCase();
				Object columnValue = resultSet.getObject(i + 1);
				// if value in DB is null, then we set it to default value
				if (columnValue == null){
					columnValue = "null";
				}
				/*
                Next if block is a hack. In case when in db we have values like price and price1 there's a bug in jdbc -
                both this names are getting stored as price in ResulSet. Therefore when we store second column value,
                we overwrite original value of price. To avoid that, i simply add 1 to be consistent with DB.
				 */
				if (obj.has(columnName)){
					columnName += "1";
				}
				obj.put(columnName, columnValue);
			}
			String methodName = obj.optString("methodname");
			String id = obj.optString("id");
			String apiName = methodName+"-"+id;
			obj.put("apiname", apiName);
			obj.remove("id");
			jsonArray.put(obj);
		}
		return jsonArray;
	}



	public static JSONObject getTableData(String key) throws Exception {

		//Connection
		String myUrl = "jdbc:mysql://localhost/hackathon";
		Connection conn = DriverManager.getConnection(myUrl, "root", "");

		// Base querry
		String tableName = "apiDetails"; // TBD
		String query = " select * from " + tableName + " where id=" + key + " ;";
		Statement st = conn.createStatement();

		// execute the statement;
		ResultSet rs = st.executeQuery(query);
		JSONArray jArray = convertResultSetIntoJSON(rs);
		rs.close();
		conn.close();

		if(jArray.length() > 0 )
			return jArray.getJSONObject(0);
		else
			return null;

	}

	public static JSONObject getTableData(String key, String method) throws Exception {

		//Connection
		String myUrl = "jdbc:mysql://localhost/hackathon";
		Connection conn = DriverManager.getConnection(myUrl, "root", "");

		// Base querry
		String tableName = "apiDetails"; // TBD
		String query = " select * from " + tableName + " where id=" + key + " and methodName=\"" + method +"\";";
		Statement st = conn.createStatement();

		// execute the statement;
		ResultSet rs = st.executeQuery(query);
		JSONArray jArray = convertResultSetIntoJSON(rs);
		rs.close();
		conn.close();

		if(jArray.length() > 0 )
			return jArray.getJSONObject(0);
		else
			return null;

	}

}

