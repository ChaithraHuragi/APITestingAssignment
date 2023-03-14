package com.test.helpers;

import org.testng.Assert;

import com.test.tests.CreateDataResponse;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ReusableMethods {
	//UserServiceHelpers helpers;
	public static Integer extractId(CreateDataResponse dataRes) {
	Integer id = dataRes.getData().getId();
	return id;
	}

	public static void verifyData(CreateDataResponse dataRes) {
		String name= UserServiceHelpers.getname();
		String salary =UserServiceHelpers.getSalary();
		String age = UserServiceHelpers.getage();
		Assert.assertEquals(dataRes.getData().getName(),name);
		Assert.assertEquals(dataRes.getData().getAge(),age);
		Assert.assertEquals(dataRes.getData().getSalary(),salary);
	}
	
	public static void validateExtractRecord( Response res) {
		System.out.println("message displayed:" + res.body().jsonPath().getString("message"));
		String name = res.body().jsonPath().getString("data.employee_name");
		String salary =  res.body().jsonPath().getString("data.employee_salary");
		String age = res.body().jsonPath().getString("data.employee_age");
		res.then().assertThat().contentType(ContentType.JSON);
		Assert.assertEquals(name,"Garrett Winters");
		Assert.assertEquals(salary,"170750");
		Assert.assertEquals(age,"63");
	}
	
	
	public static void verifyDeleteData(Response res,Integer id) {
		String status= res.body().jsonPath().getString("status");
		Assert.assertEquals(status,"success");
		String message = res.body().jsonPath().getString("message");
		System.out.println(message);
		Assert.assertEquals(message, "Successfully! Record has been deleted");
		Integer delid = res.body().jsonPath().getInt("data");
		Assert.assertEquals(delid, id);
	}
	
	public static void verifyDeleteDataIdZero(Response res,Integer id) {
		String status= res.body().jsonPath().getString("status");
		Assert.assertEquals(status,"error");
		String message = res.body().jsonPath().getString("message");
		System.out.println(message);
	}
}
