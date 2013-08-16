/**
 * 
 */
package com.vdc.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.ByteArrayBody;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.InputStreamBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

/**
 * HttpClient工具类
 * 
 * @author daniel
 */
public class HttpClientUtil {
	private static final Logger log = Logger.getLogger(HttpClientUtil.class);

	/**
	 * 你的方法上面请加上注释
	 * 
	 * @param args
	 * @author daniel
	 */
	public static void main(String[] args) {

	}

	/**
	 * HTTP GET请求
	 * 
	 * @param url
	 * @param paramMap
	 * @return
	 * @author daniel
	 */
	public static String doHttpGet(String url, Map<String, Object> paramMap) {
		if (paramMap != null && paramMap.size() > 0) {
			if (url.indexOf("?") == -1 && url.indexOf("&") == -1) {
				url += "?";
			} else {
				url += "&";
			}
			StringBuilder param = new StringBuilder();
			for (Map.Entry<String, Object> entry : paramMap.entrySet()) {
				String key = entry.getKey();
				Object value = entry.getValue();
				// NameValuePair kv = new BasicNameValuePair(key,
				// value.toString());
				// paramList.add(kv);
				param.append(key);
				param.append("=");
				param.append(value);
				param.append("&");
			}
			url += param.toString();
		}
		return _doHttpGet(url);
	}

	private static String _doHttpGet(String url) {
		String retStr = null;
		HttpClient httpClient = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(url);

		HttpResponse httpResponse = null;
		try {
			httpResponse = httpClient.execute(httpGet);
			if (HttpStatus.SC_OK == httpResponse.getStatusLine().getStatusCode()) {
				HttpEntity httpEntity = httpResponse.getEntity();
				if (httpEntity != null) {
					retStr = EntityUtils.toString(httpEntity);
					// entity.consumeContent();
				}
			}
		} catch (ClientProtocolException e) {
			log.error("_doHttpGet.ClientProtocolException=", e);
		} catch (IOException e) {
			log.error("_doHttpGet.IOException=", e);
		} finally {
			httpClient.getConnectionManager().shutdown();
		}
		return retStr;
	}

	/**
	 * HTTP POST请求
	 * 
	 * @param url
	 * @param paramMap
	 * @return
	 * @author daniel
	 */
	public static String doHttpPost(String url, Map<String, Object> paramMap) {
		StringEntity stringEntity = null;
		List<NameValuePair> paramList = new ArrayList<NameValuePair>();
		for (Map.Entry<String, Object> entry : paramMap.entrySet()) {
			String key = entry.getKey();
			Object value = entry.getValue();
			NameValuePair kv = new BasicNameValuePair(key, value.toString());
			paramList.add(kv);
		}
		try {
			stringEntity = new UrlEncodedFormEntity(paramList, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			log.error("doHttpPost.UnsupportedEncodingException=", e);
		}
		return _doHttpPost(url, stringEntity);
	}

	/**
	 * HTTP POST请求（适用于带附件的情况）
	 * 
	 * @param url
	 * @param paramMap
	 * @return
	 * @author daniel
	 */
	public static String doHttpPostForMultipart(String url, Map<String, Object> paramMap) {
		MultipartEntity multipartEntity = new MultipartEntity();
		for (Map.Entry<String, Object> entry : paramMap.entrySet()) {
			String key = entry.getKey();
			Object value = entry.getValue();
			if (value instanceof InputStreamBody) {
				multipartEntity.addPart(key, (InputStreamBody) value);
			} else if (value instanceof StringBody) {
				multipartEntity.addPart(key, (StringBody) value);
			} else if (value instanceof ByteArrayBody) {
				multipartEntity.addPart(key, (ByteArrayBody) value);
			} else if (value instanceof FileBody) {
				multipartEntity.addPart(key, (FileBody) value);
			}
		}
		return _doHttpPost(url, multipartEntity);
	}

	private static String _doHttpPost(String url, HttpEntity entity) {
		String retStr = null;
		HttpClient httpClient = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost(url);

		httpPost.setEntity(entity);

		HttpResponse httpResponse = null;
		try {
			httpResponse = httpClient.execute(httpPost);
			if (HttpStatus.SC_OK == httpResponse.getStatusLine().getStatusCode()) {
				HttpEntity httpEntity = httpResponse.getEntity();
				if (httpEntity != null) {
					retStr = EntityUtils.toString(httpEntity);
					// entity.consumeContent();
				}
			}
		} catch (ClientProtocolException e) {
			log.error("_doHttpPost.ClientProtocolException=", e);
		} catch (IOException e) {
			log.error("_doHttpPost.IOException=", e);
		} finally {
			httpClient.getConnectionManager().shutdown();
		}
		return retStr;
	}
}
