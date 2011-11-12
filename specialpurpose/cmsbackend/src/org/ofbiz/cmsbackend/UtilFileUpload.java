package org.ofbiz.cmsbackend;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import javolution.util.FastMap;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.ofbiz.base.util.Debug;
import org.ofbiz.base.util.UtilProperties;
/**
 * 
 * @author hezengyao
 * @since 2010-05
 */
public class UtilFileUpload {

	public static final String module = UtilFileUpload.class.getName();
	/**
	 * TODO 
	 * cmsupload.properties
	 * disable.debug.config=true
	 */
	public static final Boolean useDebug = UtilProperties.getPropertyAsBoolean("cmsupload.properties", "isable.debug.config",false);

	

	public static Map<String, Object> uploadFile(String imageFolder,HttpServletRequest request) throws Exception {

		Map<String, Object> context = FastMap.newInstance();
		
		FileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);

		try {

			List<FileItem> items = upload.parseRequest(request);

			Iterator<FileItem> it = items.iterator();

			while (it.hasNext()) {
				FileItem fileItem = it.next();
				if (fileItem.isFormField()) {
					String fieldName = fileItem.getFieldName();
					String fieldValue = fileItem.getString("UTF-8");

					if (!context.containsKey(fieldName)) {
						context.put(fieldName, fieldValue);
					} else {
						String old = (String) context.get(fieldName);
						context.put(fieldName, old + ";" + fieldValue);
					}

				} else {

					if (fileItem.getSize() > 0) {
						
						String fileFullName = fileItem.getName();
						String fileName = fileFullName.substring(fileFullName.lastIndexOf("\\") + 1, fileFullName.lastIndexOf("."));
						String extendName = fileFullName.substring(fileFullName.lastIndexOf("."));
						String nowDay=new SimpleDateFormat("yyyyMMdd").format(new Date());
						String uuid =UtilUUID.uuidTomini();
						String filePath =  nowDay;
	                    File saveFilePath = new File(imageFolder+filePath);
	                    if (!saveFilePath.exists()) { saveFilePath.mkdirs();} 
	                    filePath = filePath+"/" + uuid+ extendName.toLowerCase();
						File saveFile = new File(imageFolder+filePath);
						if (!saveFile.exists()) {
							saveFile.createNewFile();
						}

						fileItem.write(saveFile);
						context.put("fileName", fileName);
						context.put("filePath", filePath);
					}

				}
			}

		} catch (Exception e) {
		    Debug.log(e.getMessage(),module);
		    if(useDebug){
		        throw e;
		    }
		    
		}

		return context;
	}

	
	

}
