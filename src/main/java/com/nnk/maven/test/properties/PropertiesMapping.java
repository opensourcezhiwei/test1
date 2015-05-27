package com.nnk.maven.test.properties;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PropertiesMapping {

	private static Logger log = LoggerFactory.getLogger(PropertiesMapping.class);

	/**
	 * 
	 * @param filePath
	 *            properties 文件位置
	 * @return 包含属性的properties文件
	 */
	public static Map<String, Object> getProperties(String filePath) {
		Map<String, Object> map = new HashMap<String, Object>();
		Properties p = new Properties();
		try {
			// InputStream in =
			// PropertiesMapping.class.getClassLoader().getResourceAsStream(filePath);
			// p.load(in);
			// properties2Map(map, p);
			ResourceBundle resourceBundle = ResourceBundle.getBundle(filePath);
			Set<String> keySet = resourceBundle.keySet();
			for (String key : keySet) {
				map.put(key, resourceBundle.getObject(key));
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.debug("文件解析失败 : " + filePath);
		}
		return map;
	}

	private static void properties2Map(Map<String, Object> map, Properties p) {
		Set<Entry<Object, Object>> entrySet = p.entrySet();
		for (Entry<Object, Object> entry : entrySet) {
			map.put(entry.getKey() + "", entry.getValue());
		}
	}

	public static void main(String[] args) throws Exception {
		Map<String, Object> properties = PropertiesMapping.getProperties("hrbb2c");
		System.out.println(properties);
		Set<Entry<String, Object>> entrySet = properties.entrySet();
		for (Entry<String, Object> entry : entrySet) {
			log.debug("key = {}, value={}", entry.getKey(), entry.getValue());
		}
//		BufferedInputStream is = new BufferedInputStream(new FileInputStream("/src/main/resources/hrbb2c.properties"));
//		System.out.println(is);
	}

}
