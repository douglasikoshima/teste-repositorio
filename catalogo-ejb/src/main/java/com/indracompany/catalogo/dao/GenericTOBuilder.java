package com.indracompany.catalogo.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.indracompany.catalogo.dao.interfaces.TOBuilder;

public abstract class GenericTOBuilder<E extends Serializable, TO extends Serializable> implements TOBuilder<E, TO> {

	public abstract E createEntity(TO to);

	public abstract TO createTO(E entity);

	public List<TO> createTOList(List<E> entityList) {
		List<TO> toList = null;
		if (entityList != null) {
			toList = new ArrayList<TO>(entityList.size());
			for (E entity : entityList) {
				TO to = createTO(entity);
				toList.add(to);
			}
		}
		return toList;
	}
	
	public List<E> createEntityList(List<TO> toList) {
		List<E> entityList = null;
		if (toList != null) {
			entityList = new ArrayList<E>(toList.size());
			for (TO to : toList) {
				E e = createEntity(to);
				entityList.add(e);
			}
		}
		return entityList;
	}

}
