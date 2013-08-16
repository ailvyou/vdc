package com.vdc.componet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * 扩展上传组件
 * 
 * @author kevin
 * 
 */
@SuppressWarnings("unchecked")
public class ServletUploadFileItem extends ServletFileUpload {
	private List<FileItem> fileItems;

	public ServletUploadFileItem() {

	}

	public ServletUploadFileItem(FileItemFactory fileItemFactory, HttpServletRequest request) throws FileUploadException {
		super(fileItemFactory);
		this.parse(request);
	}

	private void parse(HttpServletRequest request) throws FileUploadException {
		fileItems = this.parseRequest(request);
	}

	/**
	 * 获取文件上传表单对象
	 * 
	 * @return
	 */
	public Map<String, String> getFormFieldItem() {
		Map<String, String> fileMap = new HashMap<String, String>();
		for (FileItem item : fileItems) {
			if (item.isFormField()) {
				fileMap.put(item.getFieldName(), item.getString());
			}
		}
		return fileMap;
	}

	/**
	 * 获取文件上传文件对象
	 * 
	 * @return
	 */
	public List<FileItem> getFileItem() {
		List<FileItem> fileList = new ArrayList<FileItem>();
		for (FileItem item : fileItems) {
			if (!item.isFormField()) {
				fileList.add(item);
			}
		}
		return fileList;
	}
}
