package util;

import model.ShortenerURLInfo;
import org.apache.commons.codec.binary.Base64;

import java.security.SecureRandom;
import java.util.Map;


public class ShortenerURLHelper {
	/**
	 * Characters for random selection
	 */
	private static final String RANDOM_STR = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	
	static SecureRandom secRadom = new SecureRandom();

	/**
	 * Generates random alphanumeric String.
	 *
	 * @param len length of a generated string
	 * @return secRadom alphanumeric string for given length
	 */
	public static String generateRandomString(Integer len) {
		StringBuilder sb = new StringBuilder(len);
		for (int i = 0; i < len; i++)
			sb.append(RANDOM_STR.charAt(secRadom.nextInt(RANDOM_STR.length())));

		return sb.toString();
	}

	/**
	 * Generates json object. Example {"http://www.myweb.com/abc/d":"2","http://www.myweb.com/trt/page":"15"}
	 *
	 * @param urls map of urls, similar to cache
	 * @return json object as String with key:value representation of parameters
	 */
	public static String generateStatisticResponse(Map<String, ShortenerURLInfo> urls) {
		StringBuilder sb = new StringBuilder("{");
		for (String key : urls.keySet())
			sb.append("\"").append(urls.get(key).getUrl()).append("\":\"").append(urls.get(key).getCounter())
					.append("\",");

		return sb.length() == 1 ? "" : sb.deleteCharAt(sb.length() - 1).append("}").toString();
	}

	/**
	 * Generates json object. Example {"shortUrl":"http://localhost:8080/xYswlE"}
	 *
	 * @return json object as String with parameter shortUrl
	 */
	public static String generateURLResponse(String shortUrl) {
		return "{\"shortUrl\":\"" + shortUrl + "\"}";
	}

	/**
	 * Decodes Authorization header in a request.
	 *
	 * @return decoded header, should be in format "account:password"
	 */
	public static String decodeHeader(String header) {
		byte[] decodeHeader = Base64.decodeBase64(header.substring(6).getBytes());
		return decodeHeader.toString();
	}
}
