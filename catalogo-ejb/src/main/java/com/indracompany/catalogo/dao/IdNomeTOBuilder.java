package com.indracompany.catalogo.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.indracompany.catalogo.to.IdNomeTO;

/**
 * 
 * @author gustavo
 *
 */
public class IdNomeTOBuilder {
	
	public List<IdNomeTO> createIdNomeTOList(List<Object[]> objectsArrayList) {
		List<IdNomeTO> idNomeTOList = null;
		if (objectsArrayList != null && !objectsArrayList.isEmpty()) {
			if (objectsArrayList.get(0).length == 4) {
				idNomeTOList = createIdNomeTO2LevelsList(objectsArrayList);
			} else if (objectsArrayList.get(0).length == 6) {
				idNomeTOList = createIdNomeTO3LevelsList(objectsArrayList);
			}
		}
		return idNomeTOList;
	}
	
	public List<IdNomeTO> createIdNomeTO2LevelsList(List<Object[]> objectsArrayList) {
		List<IdNomeTO> idNomeTOList = new ArrayList<IdNomeTO>();
		IdNomeTO idNomeTO = null;
		Integer currentId = new Integer(0);
		for (Object[] objectsArray : objectsArrayList) {
			Integer id = getId(objectsArray[0]);
			if (!currentId.equals(id)) {
				idNomeTO = new IdNomeTO(id, getNome(objectsArray[1]), 1);
				idNomeTOList.add(idNomeTO);
				currentId = id;
			}
			if (objectsArray[2] != null) {
				Integer childId = getId(objectsArray[2]);
				IdNomeTO childIdNomeTO = new IdNomeTO(childId, getNome(objectsArray[3]), 2, idNomeTO);
				idNomeTO.getChildren().add(childIdNomeTO);
			}
		}
		return idNomeTOList;
	}
	
	public List<IdNomeTO> createIdNomeTO3LevelsList(List<Object[]> objectsArrayList) {
		List<IdNomeTO> idNomeTOList = new ArrayList<IdNomeTO>();
		IdNomeTO idNomeTOLevel1 = null, idNomeTOLevel2 = null;
		Integer currentIdLevel1 = new Integer(0), currentIdLevel2 = new Integer(0);
		for (Object[] objectsArray : objectsArrayList) {
			Integer idLevel1 = getId(objectsArray[0]);
			if (!currentIdLevel1.equals(idLevel1)) {
				idNomeTOLevel1 = new IdNomeTO(idLevel1, getNome(objectsArray[1]), 1);
				idNomeTOList.add(idNomeTOLevel1);
				currentIdLevel1 = idLevel1;
				idNomeTOLevel2 = null;
				currentIdLevel2 = new Integer(0);
			}
			if (objectsArray[2] != null) {
				Integer idLevel2 = getId(objectsArray[2]);
				if (!currentIdLevel2.equals(idLevel2)) {
					idNomeTOLevel2 = new IdNomeTO(idLevel2, getNome(objectsArray[3]), 2, idNomeTOLevel1);
					idNomeTOLevel1.getChildren().add(idNomeTOLevel2);
					currentIdLevel2 = idLevel2;
				}
				if (objectsArray[4] != null) {
					Integer idLevel3 = getId(objectsArray[4]);
					IdNomeTO idNomeTOLevel3 = new IdNomeTO(idLevel3, getNome(objectsArray[5]), 3, idNomeTOLevel2);
					idNomeTOLevel2.getChildren().add(idNomeTOLevel3);
				}
			}
		}
		return idNomeTOList;
	}
	
	private Integer getId(Object obj) {
		Integer id = null;
		if (obj != null) {
			id = (obj instanceof BigDecimal) ? ((BigDecimal) obj).intValue() : (Integer) obj;
		}
		return id;
	}
	
	private String getNome(Object obj) {
		String nome = null;
		if (obj != null) {
			nome = (obj instanceof BigDecimal) ? ((BigDecimal) obj).toString() : (String) obj;
		}
		return nome;
	}
	
}