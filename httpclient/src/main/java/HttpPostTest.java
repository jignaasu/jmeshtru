import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by abhijith.nagarajan on 1/8/17.
 */
public class HttpPostTest {

	private static final String URL = "https://api.budgetair" +
			".nl/flightbooking/cid-317f1f15-09e8-40a1-a1a4-80b85ab2b47f/passengersv2/6f2aaaa4-8cec-4fd9-ab45-aee684c42f81";

	private static final String inputJson = "{\"Items\":[{\"Reference\":0,\"Person\":{\"GenderType\":\"Male\"," +
			"\"FirstName\":\"Prakash\",\"LastName\":\"Azhagappan\",\"Dob\":\"2000-04-13T00:00:00\",\"MinValidDob\":\"1916-01-13T00:00:00\",\"MaxValidDob\":\"2005-01-30T00:00:00\"},\"PassengerType\":\"Adult\",\"SelectedBaggageService\":null,\"DefaultBaggage\":{\"Included\":true,\"Quantity\":1,\"Weight\":0,\"Unit\":\"K\",\"Amount\":0},\"BaggageServiceOptions\":null,\"IsMealOptionAvailable\":true,\"IsSeatOptionAvailable\":true,\"IsFrequentFlyerAvailable\":true,\"FrequentFlyer\":{\"AirlineCode\":null,\"FrequentFlyerNumber\":null},\"SelectedMealOption\":{\"Code\":\"\"},\"SelectedSeatOption\":{\"Code\":\"\"},\"IsPassportMandatory\":false,\"IdentityDocument\":null,\"RedressNumber\":null}],\"SelectedPriorityBoardingService\":null,\"SubscribePassengersToFrequentFlyer\":null,\"EnableFrequentFlyerSubscription\":false,\"ZeroBaggageEnabled\":false,\"ZeroBaggageText\":null}";

	public static void main(String[] args) throws Exception {
		final HttpPost httpPost = new HttpPost(URL);
		httpPost.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
		httpPost.setEntity(new StringEntity(inputJson));
		final CloseableHttpResponse execute = HttpClients.createDefault().execute(httpPost);
		new BufferedReader(new InputStreamReader(execute.getEntity().getContent())).lines().forEach(System.out::println);
	}

//	private static void testPost() throws Exception {
//		HttpPost postRequest = new HttpPost("http://jsonplaceholder.typicode.com/posts");
//		postRequest.setHeader(HttpHeaders.ACCEPT, "application/xml");
//		postRequest.setHeader(HttpHeaders.CONTENT_LENGTH, "512");
//		final String data = new JSONObject().put("title", "foo").put("body", "bar").put("userId", 1).toString();
//		postRequest.setEntity(new StringEntity(data));
//		final CloseableHttpResponse execute = HttpClients.createDefault().execute(postRequest);
//		final InputStream content = execute.getEntity().getContent();
//		new BufferedReader(new InputStreamReader(content)).lines().forEach(System.out::println);
//	}
}
