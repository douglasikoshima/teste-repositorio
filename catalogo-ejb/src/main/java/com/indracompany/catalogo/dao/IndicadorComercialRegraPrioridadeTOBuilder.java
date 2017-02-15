package com.indracompany.catalogo.dao;

import com.indracompany.catalogo.datalayer.GerencRegra;
import com.indracompany.catalogo.datalayer.IndicadorComercial;
import com.indracompany.catalogo.datalayer.RegraPrioridadeAlta;
import com.indracompany.catalogo.datalayer.TipoAlta;
import com.indracompany.catalogo.to.IndicadorComercialRegraPrioridadeTO;
import com.indracompany.catalogo.to.RegraPrioridadeAltaTO;

public class IndicadorComercialRegraPrioridadeTOBuilder {
	
	public IndicadorComercialRegraPrioridadeTO createIndicadorComercialRegraPrioridadeTO(GerencRegra ent){
		IndicadorComercialRegraPrioridadeTO to = new IndicadorComercialRegraPrioridadeTO();
		if(ent != null){
			IndicadorComercial indicadorComercial = ent.getIndicadorComercial();
			if(indicadorComercial != null){
				to.setIdIndicadorComercial(indicadorComercial.getIdIndicadorComercial());
				to.setInTipoFiltro(indicadorComercial.getInTipoFiltro());
				to.setNmIndicadorComercial(indicadorComercial.getNmIndicadorComercial());
			}
			RegraPrioridadeAlta regraPrioridadeAlta = ent.getRegraPrioridadeAlta();
			if(regraPrioridadeAlta != null){
				RegraPrioridadeAltaTO regraPrioridadeAltaTO = new RegraPrioridadeAltaTO();
				regraPrioridadeAltaTO.setIdRegraPrioridadeAlta(regraPrioridadeAlta.getIdRegraPrioridadeAlta());
				regraPrioridadeAltaTO.setCdPrioridade(regraPrioridadeAlta.getCdPrioridade());
				regraPrioridadeAltaTO.setDsRegraAlta(regraPrioridadeAlta.getDsRegraAlta());
				TipoAlta tipoAlta = regraPrioridadeAlta.getTipoAlta();
				if(tipoAlta != null){
					regraPrioridadeAltaTO.setDsTipoAlta(tipoAlta.getDsTipoAlta());
					regraPrioridadeAltaTO.setIdTipoAlta(tipoAlta.getIdTipoAlta());
				}
				to.setRegraPrioridadeAltaTO(regraPrioridadeAltaTO);
			}
		}
		return to;
	}
}
