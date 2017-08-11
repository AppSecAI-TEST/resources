package com.travelsky.szcares.utils;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONUtil {

	private static ObjectMapper MAPPER = new ObjectMapper();
	static {
		MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
				false);
	}

	/**
	 * @MethodName readValueToBean
	 * @Description ��jsonתΪ����
	 * 
	 * @author
	 * @date 2015��2��6�� ����2:21:20
	 * @param json
	 * @param clazz
	 * @return
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public static <X> X readValueToBean(String json, Class<X> clazz) {
		try {
			return MAPPER.readValue(json, clazz);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * @MethodName readValueToBeanArray
	 * @Description ��jsonתΪ��������
	 * 
	 * @author
	 * @date 2015��2��12�� ����4:15:35
	 * @param json
	 * @param clazz
	 * @return
	 */
	public static <X> X[] readValueToBeanArray(String json, Class<X> clazz) {
		try {
			return MAPPER.readValue(json, MAPPER.getTypeFactory()
					.constructArrayType(clazz));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * @MethodName readValueToBeanList
	 * @Description ��jsonתΪ���󼯺�
	 * 
	 * @author
	 * @date 2015��2��12�� ����4:22:04
	 * @param json
	 * @param clazz
	 * @return
	 */
	public static <X> List<X> readValueToBeanList(String json, Class<X> clazz) {
		try {
			return MAPPER.readValue(json, MAPPER.getTypeFactory()
					.constructCollectionType(List.class, clazz));

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * @MethodName readValueToMap
	 * @Description ��jsonתΪMap
	 * 
	 * @author
	 * @date 2015��2��6�� ����2:22:02
	 * @param json
	 * @return
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, Object> readValueToMap(String json) {
		try {
			return MAPPER.readValue(json, Map.class);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * @MethodName writeValueAsString
	 * @Description ������תΪjson�ַ���
	 * 
	 * @author
	 * @date 2015��2��6�� ����2:24:13
	 * @param value
	 * @return
	 * @throws JsonProcessingException
	 */
	public static String writeValueAsString(Object value) {
		try {
			return MAPPER.writeValueAsString(value);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * @MethodName convertJsonString
	 * @Description ����תjson
	 * 
	 * @author
	 * @date 2015��2��6�� ����3:01:08
	 * @param keys
	 * @param values
	 * @return
	 */
	public static String convertArrayToJson(String[] keys, Object... values) {
		if (null == keys || keys.length == 0)
			return null;
		if (keys.length != values.length)
			throw new RuntimeException("jsonת��ʧ��,��ֵ����Ŀ����!");
		StringBuffer sb = new StringBuffer("{");
		for (int i = 0; i < keys.length; i++) {
			sb.append("\"" + keys[i] + "\":");
			if (String.class.isAssignableFrom(values[i].getClass())) {
				sb.append("\"" + values[i] + "\"");
			} else {
				sb.append(values[i]);
			}
		}
		sb.append("}");
		return sb.toString();
	}

	/**
	 * @MethodName getJsonNodeValue
	 * @Description ��ȡ�ڵ�ֵ
	 * 
	 * @author
	 * @date 2015��2��9�� ����5:19:30
	 * @param node
	 * @return
	 */
	public static String getJsonNodeValue(JsonNode node) {
		return node.toString();
	}

	/**
	 * @MethodName getChildJsonNodeValue
	 * @Description ��ȡ�ӽڵ�ڵ�ֵ
	 * 
	 * @author
	 * @date 2015��2��10�� ����9:03:28
	 * @param node
	 * @param name
	 * @return
	 */
	public static String getChildJsonNodeValue(JsonNode node, String name) {
		return getJsonNodeValue(node.get(name));
	}

	/**
	 * @MethodName covertNodeTree
	 * @Description ��jsonתΪjson�ڵ���
	 * 
	 * @author
	 * @date 2015��2��9�� ����5:19:57
	 * @param json
	 * @return
	 */
	public static JsonNode covertNodeTree(String json) {
		try {
			return MAPPER.readTree(json);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	/**
	 * @MethodName getJsonValueByName
	 * @Description ��ȡjson�ַ�����ĳ�ڵ��ֵ
	 * 
	 * @author
	 * @date 2015��2��10�� ����9:06:27
	 * @param json
	 * @param name
	 * @return
	 */
	public static String getJsonValueByName(String json, String name) {
		return getChildJsonNodeValue(covertNodeTree(json), name);
	}

}
