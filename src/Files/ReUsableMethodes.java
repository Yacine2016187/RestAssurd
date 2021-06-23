package Files;

import io.restassured.path.json.JsonPath;

public class ReUsableMethodes {
	
	public static  JsonPath rawToJson(String response)
	{
		JsonPath JsGet=new JsonPath(response);
		return JsGet;
	}

}
