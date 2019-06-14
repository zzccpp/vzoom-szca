package com.vzoom.sz.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vzoom.sz.task.UpdateSignTask;
import org.apache.log4j.Logger;

public class JsonUtils {
	private static Logger logger = Logger.getLogger(UpdateSignTask.class);
	/**
	 * 格式化输出JSON格式
	 * @param jsonStr
	 * @return
	 */
	public static String prettyPrinter(String jsonStr){
		try {
			ObjectMapper mapper = new ObjectMapper();
			Object obj = mapper.readValue(jsonStr, Object.class);
			return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.debug("格式输出JSON异常:"+jsonStr);
		}
		return "";
	}
}
