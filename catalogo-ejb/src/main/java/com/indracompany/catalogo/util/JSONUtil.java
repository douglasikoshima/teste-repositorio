package com.indracompany.catalogo.util;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.indracompany.catalogo.to.Jsonable;

public class JSONUtil {
	
	public static JSONArray toJSONArray(List<? extends Jsonable> jsonableList) throws JSONException {
		JSONArray jsonArray = null;
		if (jsonableList != null && !jsonableList.isEmpty()) {
			jsonArray = new JSONArray();
			for (Jsonable jsonable : jsonableList) {
				JSONObject jsonObject = jsonable.toJSONObject();
				jsonArray.put(jsonObject);
			}
		}
		return jsonArray;
	}
	
}