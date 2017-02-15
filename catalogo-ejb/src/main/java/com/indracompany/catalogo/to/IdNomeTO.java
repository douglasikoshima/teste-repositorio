package com.indracompany.catalogo.to;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * 
 * @author gustavo
 *
 */
public class IdNomeTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id = null;
	private String nome = null;
	private int level;
	private IdNomeTO parent = null;
	private List<IdNomeTO> children = new ArrayList<IdNomeTO>();
	
	public IdNomeTO() {
	}
	public IdNomeTO(Integer id, String nome, int level) {
		this.id = id;
		this.nome = nome;
		this.level = level;
	}
	public IdNomeTO(Integer id, String nome, int level, IdNomeTO parent) {
		this(id, nome, level);
		this.parent = parent;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public IdNomeTO getParent() {
		return parent;
	}
	public void setParent(IdNomeTO parent) {
		this.parent = parent;
	}
	public List<IdNomeTO> getChildren() {
		return children;
	}
	public void setChildren(List<IdNomeTO> children) {
		this.children = children;
	}
	
	public JSONArray childrenToJSONArray() throws JSONException {
		JSONArray jsonArray = new JSONArray();
		for (IdNomeTO child : getChildren()) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("id", child.getId());
			jsonObject.put("nome", child.getNome());
			jsonObject.put("children", child.childrenToJSONArray());
			jsonArray.put(jsonObject);
		}
		return jsonArray;
	}
	
	public String childrenToJSONString() throws JSONException {
		return childrenToJSONArray().toString();
	}
	
}