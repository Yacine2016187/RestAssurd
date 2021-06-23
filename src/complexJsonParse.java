import Files.PayLoad;
import io.restassured.path.json.JsonPath;

public class complexJsonParse {

	public static void main(String[] args) {
	
	JsonPath js=new JsonPath(PayLoad.coursPrice());
	
	//Print No of courses returned by API
	int count=js.getInt("courses.size()");
	System.out.println(count);
	
	//Print Purchase Amount
	
	int totalamount=js.getInt("dashboard.purchaseAmount");
	System.out.println(totalamount);
	
	String Title=js.getString("courses[0].title");
	System.out.println(Title);
}
}