package utilities;

import java.util.Map;

import org.junit.Assert;

public class ApiValidate {

	public static void validateCreateAPost(Map<String, String> actualResult, Map<String, String> expectedResult) {
	Assert.assertTrue(actualResult.equals(expectedResult));
	
	}
}
