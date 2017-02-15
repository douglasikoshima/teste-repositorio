package com.indracompany.catalogo.to;

import org.json.JSONException;
import org.json.JSONObject;

public interface Jsonable {
	
	public JSONObject toJSONObject() throws JSONException;
	
}