package com.indracompany.catalogo.dao;

import java.util.ArrayList;
import java.util.List;

import com.indracompany.catalogo.datalayer.Plataforma;
import com.indracompany.catalogo.to.PlataformaTO;

/**
 * @author Luiz Pereira
 *
 * Classe responsável em transformar TO em Entity e Entiy em TO.
 */
public class PlataformaTOBuilder {
	
	/**
	 * @param plataformaTO
	 * @return
	 * 
	 *  Método responsável em transformar um TO em um Entity.
	 */
	public Plataforma createPlataforma(PlataformaTO plataformaTO) {
		
		Plataforma plataforma = null;
		
		if (plataformaTO != null) {
			plataforma = new Plataforma();
			plataforma.setIdPlataforma(plataformaTO.getIdPlataforma());
			plataforma.setDtCriacao(plataformaTO.getDtCriacao());
			plataforma.setDtUltimaAlteracao(plataformaTO.getDtUltimaAlteracao());
			plataforma.setNmPlataforma(plataformaTO.getNmPlataforma());
			plataforma.setNmUsuarioAlteracao(plataformaTO.getNmUsuarioAlteracao());
			plataforma.setNmUsuarioCriacao(plataformaTO.getNmUsuarioCriacao());
		}
		
		return plataforma;
	}
	
	/**
	 * @param plataforma
	 * @return
	 * 
	 * Método responsável em transformar um Entity em um TO.
	 */
	public PlataformaTO createPlataformaTO(Plataforma plataforma) {
		
		PlataformaTO plataformaTO = null;
		
		if (plataforma != null) {
			plataformaTO = new PlataformaTO();
			plataformaTO.setIdPlataforma(plataforma.getIdPlataforma());
			plataformaTO.setDtCriacao(plataforma.getDtCriacao());
			plataformaTO.setDtUltimaAlteracao(plataforma.getDtUltimaAlteracao());
			plataformaTO.setNmPlataforma(plataforma.getNmPlataforma());
			plataformaTO.setNmUsuarioAlteracao(plataforma.getNmUsuarioAlteracao());
			plataformaTO.setNmUsuarioCriacao(plataforma.getNmUsuarioCriacao());
		}
		
		return plataformaTO;
	}
	
	/**
	 * @param plataformaList
	 * @return
	 * 
	 * Método responsável em transformar uma lista de Entity em uma lista de TO.
	 */
	public List<PlataformaTO> createPlataformaTOList(List<Plataforma> plataformaList) {
		
		List<PlataformaTO> list = new ArrayList<PlataformaTO>();
		
		if (plataformaList != null && plataformaList.size() > 0) {
			for (Plataforma plataforma : plataformaList) {
				list.add(createPlataformaTO(plataforma));
			}
		}
		
		return list;
	}

    public List<Plataforma> createPlataformaList(List<PlataformaTO> plataformaTOList) {
        List<Plataforma> plataformaList = new ArrayList<Plataforma>();
        for(PlataformaTO to : plataformaTOList) {
            plataformaList.add(this.createPlataforma(to));
        }
        return plataformaList;
    }
}
