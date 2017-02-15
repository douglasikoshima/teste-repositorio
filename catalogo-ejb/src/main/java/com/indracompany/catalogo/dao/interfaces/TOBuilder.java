package com.indracompany.catalogo.dao.interfaces;

import java.util.List;

public interface TOBuilder<E, TO> {
	
	public E createEntity(TO to);
	
	public TO createTO(E entity);
	
	public List<TO> createTOList(List<E> entityList);
	
	public List<E> createEntityList(List<TO> toList);
	
}