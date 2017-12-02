import static java.time.format.DateTimeFormatter.*;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.nio.charset.Charset;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Base64;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by abhijith.nagarajan on 1/8/17.
 */
public class HttpPostTest {

	private static final String inputJson = "input_json";

	public static void main(String[] args) throws Exception {
		final HttpPost httpPost = new HttpPost("url");
		httpPost.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
		String date = RFC_1123_DATE_TIME.format(ZonedDateTime.now(ZoneId.of("GMT")));
		httpPost.setHeader(HttpHeaders.DATE, date);
		httpPost.setHeader(HttpHeaders.AUTHORIZATION, getAuthorizationHeader(date));
		httpPost.setEntity(new StringEntity(inputJson));
		try (CloseableHttpResponse execute = HttpClients.createDefault().execute(httpPost)) {
			new BufferedReader(new InputStreamReader(execute.getEntity().getContent())).lines().forEach(System.out::println);
		}
	}

	private static String getAuthorizationHeader(String date) throws Exception {
		String privateKey = "<secrete_key>";
		String publicKey = "<api_key>";
		Mac hmacSHA256 = Mac.getInstance("HmacSHA256");
		SecretKeySpec secretKeySpec = new SecretKeySpec(privateKey.getBytes(Charset.forName("UTF-8")), "HmacSHA256");
		hmacSHA256.init(secretKeySpec);
		StringBuilder data = new StringBuilder();
		data.append("POST").append("\n");
		data.append("application/json").append("\n");
		data.append(date).append("\n");
		data.append("<relative_url>").append("\n");
		byte[] finalData = hmacSHA256.doFinal(data.toString().getBytes("UTF-8"));
		String authorizationData = Base64.getEncoder().encodeToString(finalData);
		return "<prefix>" + publicKey + ":" + authorizationData;
	}
}
