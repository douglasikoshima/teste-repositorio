package com.indracompany.catalogo.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.indracompany.catalogo.datalayer.Plano;
import com.indracompany.catalogo.datalayer.PlanoUfRestricao;
import com.indracompany.catalogo.datalayer.Plataforma;
import com.indracompany.catalogo.to.CategorizacaoAnaliseCreditoTO;
import com.indracompany.catalogo.to.PlanoServicoUfRestricaoTO;
import com.indracompany.catalogo.to.PlanoTO;
import com.indracompany.catalogo.to.SegmentoTO;
import com.indracompany.catalogo.to.UfTO;

/**
 * @author Luiz Pereira
 * 
 */
public class PlanoTOBuilder {

	private static Logger logger = Logger.getLogger(PlanoTOBuilder.class);

	/**
	 * @param plano
	 * @return
	 */
	public CategorizacaoAnaliseCreditoTO createCategorizacaoAnaliseCreditoTO(
			Plano plano) {

		CategorizacaoAnaliseCreditoTO categorizacaoAnaliseCreditoTO = null;

		if (plano != null) {
			categorizacaoAnaliseCreditoTO = new CategorizacaoAnaliseCreditoTO();
			categorizacaoAnaliseCreditoTO.setId(plano.getIdPlano());
			categorizacaoAnaliseCreditoTO.setNome(plano.getNmComercial());

			Plataforma plataforma = null;

			if (plano.getSistemaPlano() != null
					&& plano.getSistemaPlano().getSistema().getNmSistema()
							.equals("NGIN")) {
				plataforma = new Plataforma();
				plataforma.setIdPlataforma(1);
				plataforma.setNmPlataforma("PRÉ-PAGO");
			} else {
				if (plano.getPlanoPlataformaList() != null
						&& plano.getPlanoPlataformaList().size() > 0) {
					plataforma = plano.getPlanoPlataformaList().get(0)
							.getPlataforma();
				}
			}

			if (plataforma != null) {
				categorizacaoAnaliseCreditoTO.setNmPlataforma(plataforma
						.getNmPlataforma());
				categorizacaoAnaliseCreditoTO.setIdPlataforma(plataforma
						.getIdPlataforma());
			}

			if (plano.getPlanoCategoriaScore() != null) {
				categorizacaoAnaliseCreditoTO.setNmCategoria(plano
						.getPlanoCategoriaScore().getCategoriaScore()
						.getNmCategoriaScore());
				categorizacaoAnaliseCreditoTO.setIdCategoria(plano
						.getPlanoCategoriaScore().getCategoriaScore()
						.getIdCategoriaScore());
			}
		}

		return categorizacaoAnaliseCreditoTO;
	}

	/**
	 * @param planoList
	 * @return
	 */
	public List<CategorizacaoAnaliseCreditoTO> createCategorizacaoAnaliseCreditoTOList(
			List<Plano> planoList) {

		List<CategorizacaoAnaliseCreditoTO> categorizacaoAnaliseCreditoTOList = new ArrayList<CategorizacaoAnaliseCreditoTO>();

		if (planoList != null) {
			for (Plano plano : planoList) {
				categorizacaoAnaliseCreditoTOList
						.add(createCategorizacaoAnaliseCreditoTO(plano));
			}
		}
		return categorizacaoAnaliseCreditoTOList;
	}

	public PlanoTO createTO(Plano ent) {

		PlanoTO to = new PlanoTO();

		if (ent != null) {
			to.setIdPlano(ent.getIdPlano());
			to.setNmComercial(ent.getNmComercial());
			if (ent.getSistemaPlano() != null) {
				to.setCdPlano(ent.getSistemaPlano().getCdCodigo());
			}
			if (ent.getPlataformaList() != null
					&& !ent.getPlataformaList().isEmpty()) {
				to.setPlataformaTO(new PlataformaTOBuilder()
						.createPlataformaTO(ent.getPlataformaList().get(0)));
			}
			if (ent.getSegmentoList() != null
					&& !ent.getSegmentoList().isEmpty()) {
				to.setSegmentoTO(new SegmentoTOBuilder().createSegmentoTO(ent
						.getSegmentoList().get(0)));
			}
		}
		return to;
	}
	
	public PlanoTO createPlanoSegmentoTO(Plano ent) {

		PlanoTO to = new PlanoTO();

		if (ent != null) {
			to.setIdPlano(ent.getIdPlano());
			to.setNmComercial(ent.getNmComercial());
			if (ent.getSistemaPlano() != null) {
				to.setCdPlano(ent.getSistemaPlano().getCdCodigo());
			}
			if (ent.getPlataformaList() != null
					&& !ent.getPlataformaList().isEmpty()) {
				to.setPlataformaTO(new PlataformaTOBuilder()
						.createPlataformaTO(ent.getPlataformaList().get(0)));
			}
			if (ent.getPlanoSegmento() != null && ent.getPlanoSegmento().getSegmento() != null) {
				SegmentoTO segmentoTO = new SegmentoTOBuilder().createSegmentoTO(ent.getPlanoSegmento().getSegmento());
				segmentoTO.setInInfancia(ent.getPlanoSegmento().getInInfancia());
				to.setSegmentoTO(segmentoTO);
			}
		}
		return to;
	}

	public List<PlanoTO> creatoPlanoTOList(List<Plano> entList) {
		List<PlanoTO> toList = new ArrayList<PlanoTO>();

		for (Plano ent : entList) {
			PlanoTO to = createTO(ent);
			toList.add(to);
		}
		return toList;
	}

	public List<PlanoTO> createPlanoSegmentoTOList(List<Plano> entList) {
		List<PlanoTO> toList = new ArrayList<PlanoTO>();

		for (Plano ent : entList) {
			PlanoTO to = createPlanoSegmentoTO(ent);
			toList.add(to);
		}
		return toList;
	}
	
	public List<PlanoServicoUfRestricaoTO> createPlanoServicoUfRestricaoTOList(
			List<Plano> planoList) {

		logger.debug(">> createPlanoServicoUfRestricaoTOList()");

		List<PlanoServicoUfRestricaoTO> toList = new ArrayList<PlanoServicoUfRestricaoTO>();
		PlanoServicoUfRestricaoTO restricaoTO;
		UfTO ufTO;

		for (Plano p : planoList) {
			restricaoTO = new PlanoServicoUfRestricaoTO();
			restricaoTO.setId(p.getIdPlano().longValue());
			restricaoTO.setNome(p.getNmComercial());
			restricaoTO.setCodigo(p.getSistemaPlano().getCdCodigo());

			for (PlanoUfRestricao pUf : p.getPlanoUfRestricaoList()) {
				ufTO = new UfTO();
				ufTO.setIdUf(pUf.getUf().getIdUf().longValue());
				ufTO.setNmUf(pUf.getUf().getNmUf());
				restricaoTO.getUfTOList().add(ufTO);
			}
			toList.add(restricaoTO);
		}

		logger.debug("<< createPlanoServicoUfRestricaoTOList()");

		return toList;
	}
	
	

}
