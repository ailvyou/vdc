/**
 * 
 */
package com.vdc.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.entity.mime.content.InputStreamBody;
import org.apache.http.entity.mime.content.StringBody;

import com.vdc.componet.ServletUploadFileItem;
import com.vdc.util.HttpClientUtil;

/**
 * 代理controller基类
 * 
 * @author daniel
 */
public class ProxyBaseController extends BaseController {
	/**
	 * 上传文件代理（适用于KindEditor）
	 * 
	 * @param request
	 * @param response
	 * @throws FileUploadException
	 * @throws ClientProtocolException
	 * @throws IOException
	 * @author daniel
	 */
	public void uploadProxyForKindEditor(String kindeditorUploadServerUrl, HttpServletRequest request, HttpServletResponse response) throws FileUploadException, IOException {
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setSizeThreshold(4096);
		ServletUploadFileItem upload = new ServletUploadFileItem(factory, request);
		upload.setHeaderEncoding("UTF-8");
		Map<String, String> filedMap = upload.getFormFieldItem();
		FileItem imgFileItem = upload.getFileItem().get(0);
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("imgFile", new InputStreamBody(imgFileItem.getInputStream(), imgFileItem.getContentType(), imgFileItem.getName()));
		for (Map.Entry<String, String> entry : filedMap.entrySet()) {
			if (entry.getValue() != null) {
				paramMap.put(entry.getKey(), new StringBody(entry.getValue()));
			}
		}
		String retStr = HttpClientUtil.doHttpPostForMultipart(kindeditorUploadServerUrl, paramMap);
		super.outputText(retStr, response);
	}

	/**
	 * HTTP GET请求代理
	 * 
	 * @param url
	 * @param request
	 * @return
	 * @throws FileUploadException
	 * @throws IOException
	 * @author daniel
	 */
	public String proxyForHttpGet(String url, HttpServletRequest request) throws FileUploadException, IOException {
		Map<String, Object> paramMap = super.buildParamMapFromRequest(request);
		return HttpClientUtil.doHttpGet(url, paramMap);
	}

	/**
	 * HTTP POST请求代理
	 * 
	 * @param url
	 * @param request
	 * @return
	 * @throws FileUploadException
	 * @throws IOException
	 * @author daniel
	 */
	public String proxyForHttpPost(String url, HttpServletRequest request) throws FileUploadException, IOException {
		Map<String, Object> paramMap = super.buildParamMapFromRequest(request);
		return HttpClientUtil.doHttpPost(url, paramMap);
	}

}
