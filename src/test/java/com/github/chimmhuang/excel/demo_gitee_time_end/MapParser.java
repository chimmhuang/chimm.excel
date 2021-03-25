package com.github.chimmhuang.excel.demo_gitee_time_end;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.Data;

public class MapParser extends HashMap<String, Object> {
	@Data
	class ParseKey {
		String key;
		Boolean isList;
		Integer index;
		ParseKey sub;
	}

	public ParseKey parseKey(String key) {
		ParseKey parse = new MapParser.ParseKey();
		int point = key.indexOf('.');
		String preKey;
		if (point == -1) {
			preKey = key;
		} else {
			preKey = key.substring(0, point);
			String newKey = key.substring(point + 1, key.length());
			parse.setSub(parseKey(newKey));
		}
		parse.setIsList(false);
		if (preKey.endsWith("]")) {
			parse.setIsList(true);
			int prePoint = preKey.lastIndexOf("[");
			if (prePoint + 1 != preKey.length() - 1) {
				String index = preKey.substring(prePoint + 1, preKey.length() - 1);
				parse.setIndex(Integer.parseInt(index));
			}
			preKey = preKey.substring(0, prePoint);
		}
		parse.setKey(preKey);
		return parse;
	}

	public Object getObject(String key) {
		if (key.startsWith("${") && key.endsWith("}")) {
			key = key.substring(2, key.length() - 1);
			ParseKey parse = this.parseKey(key);
			Map<String, Object> subMap = this;
			while (parse != null) {
				Object obj = subMap.get(parse.getKey());
				if (obj == null) {
					return null;
				}
				if (parse.getIsList() && parse.getIndex() != null && obj instanceof List) {
					List list = (List) obj;
					if (list.size() > parse.getIndex()) {
						Object listVal = list.get(parse.getIndex());
						obj = listVal;
					}
				}
				if (parse.getSub() == null) {
					return obj;
				}
				
				if (!(obj instanceof Map)) {
					return null;
				}
				subMap = (Map<String, Object>) obj;
				parse = parse.getSub();
			}
		}
		return null;
	}

}
