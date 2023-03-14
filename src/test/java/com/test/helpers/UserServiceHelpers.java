package com.test.helpers;

import com.test.constants.EndPoints;
import com.test.models.AddDataPojo;
import com.test.utils.utilsTest;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserServiceHelpers {
	static utilsTest utils;
	static Response res;
	static String token;
	static Integer resourcePath;

	public static void loadfile() {
		utils = new utilsTest();
		utils.loadFile("config.properties");
	}

	public static String getBaseUri() {
		String baseuri = utils.getConfigProperty("baseuri");
		return baseuri;
	}

	public static String getname() {
		String name = utils.getConfigProperty("name");
		return name;
	}

	public static String getage() {
		String age = utils.getConfigProperty("age");
		return age;
	}

	public static String getSalary() {
		String salary = utils.getConfigProperty("salary");
		return salary;
	}

	public static Response getEmployee() {
		res = RestAssured.given().contentType(ContentType.JSON).when().get(EndPoints.EMPLOYEES);
		return res;

	}

	public static Response addEmployee() {
		AddDataPojo ob = new AddDataPojo();
		ob.setName(getname());
		ob.setSalary(getSalary());
		ob.setAge(getage());
		res = RestAssured.given().contentType(ContentType.JSON).body(ob).when().post(EndPoints.CREATE);
		return res;
	}

	public static Response deleteEmployee(Integer id) {

		res = RestAssured.given().pathParam("resourcePath", id).when().contentType(ContentType.JSON)
				.delete(EndPoints.DELETE);
		return res;

	}

	public static Response getEmployeeRecord(Integer id) {
		res = RestAssured.given().pathParam("resourcePath", id).when().contentType(ContentType.JSON)
				.get(EndPoints.EXTRACTDATA);
		return res;

	}

}
