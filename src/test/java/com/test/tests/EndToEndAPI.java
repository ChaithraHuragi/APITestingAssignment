package com.test.tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.test.helpers.ReusableMethods;
import com.test.helpers.UserServiceHelpers;
import com.test.utils.utilsTest;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class EndToEndAPI extends UserServiceHelpers {

	String token;
	Response res;
	protected Integer id;
	utilsTest utils;

	@BeforeClass
	public void init() {
		System.out.println("insider init");
		loadfile();
		System.out.println("------");
		RestAssured.baseURI = getBaseUri();

	}

	@Test(priority = 1)
	public void tc01() {
		System.out.println("inside getdata of employee");
		res = getEmployee();
		res.then().statusCode(200);
		res.prettyPrint();
		UserDataResponsePojo data = res.as(UserDataResponsePojo.class);
		System.out.println("Status:" + data.getStatus());
		System.out.println("length of the data:" + data.getData().size());

	}

	@Test(priority = 2)
	public void tc02() {
		System.out.println("inside createData of employee");
		res = addEmployee();
		res.then().statusCode(200);
		res.prettyPrint();
		CreateDataResponse dataRes = res.as(CreateDataResponse.class);
		System.out.println("Status:" + dataRes.getStatus());
		id = ReusableMethods.extractId(dataRes);
		System.out.println("id:" + id);
		ReusableMethods.verifyData(dataRes);
	}

	@Test(priority = 3)
	public void tc03() {
		res = deleteEmployee(id);
		res.then().statusCode(200);
		res.prettyPrint();
		ReusableMethods.verifyDeleteData(res, id);

	}

	@Test(priority = 4)
	public void tc04() {
		int findid = 0;
		res = deleteEmployee(findid);
		// res.body().
		res.then().statusCode(400);
		res.prettyPrint();
		ReusableMethods.verifyDeleteDataIdZero(res, id);

	}

	@Test(priority = 5)
	public void tc05() {
		int findid = 2;
		res = getEmployeeRecord(findid);
		res.then().statusCode(200);
		res.prettyPrint();
		ReusableMethods.validateExtractRecord(res);

	}

}
