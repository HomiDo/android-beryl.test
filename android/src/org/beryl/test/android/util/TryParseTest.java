package org.beryl.test.android.util;

import org.beryl.util.TryParse;
import org.json.JSONException;
import org.json.JSONObject;

import junit.framework.Assert;
import junit.framework.TestCase;

public class TryParseTest extends TestCase {

	private static final String JSON_SINGLE_PATTERN = "{\"testObject\": %s}";
	
	public void test_toInt_nullReturnsDefaultValue() {
		int expectedValue = 5;
		int actualValue = TryParse.toInt(null, expectedValue);
		assertSame(expectedValue, actualValue);
	}
	
	public void test_toInt_validValueReturnsSelf() {
		int expectedValue = 5;
		int actualValue = TryParse.toInt("5", 0);
		assertSame(expectedValue, actualValue);
	}
	
	public void test_toInt_badValueReturnsDefaultValue() {
		int expectedValue = 5;
		int actualValue = TryParse.toInt("garbage", expectedValue);
		assertSame(expectedValue, actualValue);
	}
	
	public void test_toString_fromJson_nullObjectAndNullNameReturnsDefaultValue() {
		try {
			String expectedValue = "expected";
			String actualValue = TryParse.toString(null, null, expectedValue);
			Assert.assertEquals(expectedValue, actualValue);
			fail("Expecting NullPointerException.");
		} catch(NullPointerException e) {
		} catch(Exception e) { 
			fail("Expecting NullPointerException.");
		}
	}
	
	public void test_toString_fromJson_nullObjectAndBlankNameReturnsDefaultValue() {
		try {
			String expectedValue = "expected";
			String actualValue = TryParse.toString(null, "", expectedValue);
			Assert.assertEquals(expectedValue, actualValue);
			fail("Expecting NullPointerException.");
		} catch(NullPointerException e) {
		} catch(Exception e) { 
			fail("Expecting NullPointerException.");
		}
	}
	
	public void test_toString_fromJson_blankObjectAndNullNameReturnsDefaultValue() {
		String expectedValue = "expected";
		String actualValue = TryParse.toString(new JSONObject(), null, expectedValue);
		Assert.assertEquals(expectedValue, actualValue);
	}
	
	public void test_toString_fromJson_blankObjectAndBlankNameReturnsDefaultValue() {
		String expectedValue = "expected";
		String actualValue = TryParse.toString(new JSONObject(), "", expectedValue);
		Assert.assertEquals(expectedValue, actualValue);
	}

	public void test_toString_fromJson_existingValueReturnsSelf() throws JSONException {
		String expectedValue = "expected";
		String actualValue = TryParse.toString(new JSONObject(String.format(JSON_SINGLE_PATTERN, "\"expected\"")),
											"testObject", "unexpected");
		Assert.assertEquals(expectedValue, actualValue);
	}
	
	public void test_toString_fromJson_nonExistingValueReturnsDefaultValue() throws JSONException {
		String expectedValue = "expected";
		String actualValue = TryParse.toString(new JSONObject(String.format(JSON_SINGLE_PATTERN, "\"unexpected\"")),
											"somethingThatDoesntExist", expectedValue);
		Assert.assertEquals(expectedValue, actualValue);
	}
	
	public void test_toInt_fromJson_nullObjectAndNullNameReturnsDefaultValue() {
		try {
			int expectedValue = 5;
			int actualValue = TryParse.toInt(null, null, expectedValue);
			Assert.assertEquals(expectedValue, actualValue);
			fail("Expecting NullPointerException.");
		} catch(NullPointerException e) {
		} catch(Exception e) { 
			fail("Expecting NullPointerException.");
		}
	}
	
	public void test_toInt_fromJson_nullObjectAndBlankNameReturnsDefaultValue() {
		try {
			int expectedValue = 5;
			int actualValue = TryParse.toInt(null, "", expectedValue);
			Assert.assertEquals(expectedValue, actualValue);
			fail("Expecting NullPointerException.");
		} catch(NullPointerException e) {
		} catch(Exception e) { 
			fail("Expecting NullPointerException.");
		}
	}
	
	public void test_toInt_fromJson_blankObjectAndNullNameReturnsDefaultValue() {
		int expectedValue = 5;
		int actualValue = TryParse.toInt(new JSONObject(), null, expectedValue);
		Assert.assertEquals(expectedValue, actualValue);
	}
	
	public void test_toInt_fromJson_blankObjectAndBlankNameReturnsDefaultValue() {
		int expectedValue = 5;
		int actualValue = TryParse.toInt(new JSONObject(), "", expectedValue);
		Assert.assertEquals(expectedValue, actualValue);
	}

	public void test_toInt_fromJson_existingValueReturnsSelf() throws JSONException {
		int expectedValue = 5;
		int actualValue = TryParse.toInt(new JSONObject(String.format(JSON_SINGLE_PATTERN, String.valueOf(expectedValue))),
											"testObject", 0);
		Assert.assertEquals(expectedValue, actualValue);
	}
	
	public void test_toInt_fromJson_nonExistingValueReturnsDefaultValue() throws JSONException {
		int expectedValue = 5;
		int actualValue = TryParse.toInt(new JSONObject(String.format(JSON_SINGLE_PATTERN, "20")),
											"somethingThatDoesntExist", expectedValue);
		Assert.assertEquals(expectedValue, actualValue);
	}
	
	public void test_toInt_fromJson_invalidValueReturnsDefaultValue() throws JSONException {
		int expectedValue = 5;
		int actualValue = TryParse.toInt(new JSONObject(String.format(JSON_SINGLE_PATTERN, "\"garbage\"")),
											"testObject", expectedValue);
		Assert.assertEquals(expectedValue, actualValue);
	}
	
	public void test_toBoolean_fromJson_nullObjectAndNullNameReturnsDefaultValue() {
		try {
			boolean expectedValue = true;
			boolean actualValue = TryParse.toBoolean(null, null, expectedValue);
			Assert.assertEquals(expectedValue, actualValue);
			fail("Expecting NullPointerException.");
		} catch(NullPointerException e) {
		} catch(Exception e) { 
			fail("Expecting NullPointerException.");
		}
	}
	
	public void test_toBoolean_fromJson_nullObjectAndBlankNameReturnsDefaultValue() {
		try {
			boolean expectedValue = false;
			boolean actualValue = TryParse.toBoolean(null, "", expectedValue);
			Assert.assertEquals(expectedValue, actualValue);
			fail("Expecting NullPointerException.");
		} catch(NullPointerException e) {
		} catch(Exception e) { 
			fail("Expecting NullPointerException.");
		}
	}
	
	public void test_toBoolean_fromJson_blankObjectAndNullNameReturnsDefaultValue() {
		boolean expectedValue = true;
		boolean actualValue = TryParse.toBoolean(new JSONObject(), null, expectedValue);
		Assert.assertEquals(expectedValue, actualValue);
	}
	
	public void test_toBoolean_fromJson_blankObjectAndBlankNameReturnsDefaultValue() {
		boolean expectedValue = false;
		boolean actualValue = TryParse.toBoolean(new JSONObject(), "", expectedValue);
		Assert.assertEquals(expectedValue, actualValue);
	}

	public void test_toBoolean_fromJson_existingValueReturnsSelf() throws JSONException {
		boolean expectedValue = true;
		boolean actualValue = TryParse.toBoolean(new JSONObject(String.format(JSON_SINGLE_PATTERN, String.valueOf(expectedValue))),
											"testObject", false);
		Assert.assertEquals(expectedValue, actualValue);
	}
	
	public void test_toBoolean_fromJson_nonExistingValueReturnsDefaultValue() throws JSONException {
		boolean expectedValue = false;
		boolean actualValue = TryParse.toBoolean(new JSONObject(String.format(JSON_SINGLE_PATTERN, "true")),
											"somethingThatDoesntExist", expectedValue);
		Assert.assertEquals(expectedValue, actualValue);
	}
	
	public void test_toBoolean_fromJson_invalidValueReturnsDefaultValue() throws JSONException {
		boolean expectedValue = true;
		boolean actualValue = TryParse.toBoolean(new JSONObject(String.format(JSON_SINGLE_PATTERN, "\"garbage\"")),
											"testObject", expectedValue);
		Assert.assertEquals(expectedValue, actualValue);
	}
}
