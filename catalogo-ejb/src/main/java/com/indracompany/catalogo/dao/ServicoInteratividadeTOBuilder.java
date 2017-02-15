package com.indracompany.catalogo.dao;

import java.util.ArrayList;
import java.util.List;
import com.indracompany.catalogo.datalayer.ServicoInteratividade;
import com.indracompany.catalogo.to.ServicoIntCanalTO;
import com.indracompany.catalogo.to.ServicoIntClienteTO;
import com.indracompany.catalogo.to.ServicoIntPlataformaTO;
import com.indracompany.catalogo.to.ServicoIntTecnologiaTO;
import com.indracompany.catalogo.to.ServicoInteratividadeTO;

/**
 * @author Luiz Pereira
 *
 * Classe responsável em transformar TO em Entity e Entiy em TO.
 */
public class ServicoInteratividadeTOBuilder {
	
	/**
	 * @param servicoInteratividadeTO
	 * @return
	 * 
	 *  Método responsável em transformar um TO em um Entity.
	 */
	public ServicoInteratividade createServicoInteratividade(ServicoInteratividadeTO servicoInteratividadeTO) {
		
		ServicoInteratividade servicoInteratividade = new ServicoInteratividade();
		
		if (servicoInteratividadeTO != null) {
			servicoInteratividade = new ServicoInteratividade();
			servicoInteratividade.setAtivo(servicoInteratividadeTO.getAtivo());
			//servicoInteratividade.setCanalAtendimento(new CanalAtendimentoTOBuilder().createCanalAtendimento(servicoInteratividadeTO.getCanalAtendimentoTO()));
/*			if (servicoInteratividadeTO.getServicoIntCanalTOList().size() > 0 ) {										
				List<ServicoIntCanal> servIntCanalList = new ArrayList<ServicoIntCanal>();

				for (int i = 0; i < servicoInteratividadeTO.getServicoIntCanalTOList().size(); i++) {
					
					if (servicoInteratividadeTO.getServicoIntCanalTOList().get(0).getCanalAtendimento().getIdCanalAtendimento() != 0 ) {						
						CanalAtendimento can = servicoInteratividadeTO.getServicoIntCanalTOList().get(i).getCanalAtendimento();
						
						ServicoIntCanal servIntCanal = new ServicoIntCanal();
						servIntCanal.setCanalAtendimento(can);
						servIntCanal.setServicoInteratividade(new ServicoInteratividade(servicoInteratividadeTO.getIdServicoInteratividade()));
						servIntCanal.setNmUsuarioCriacao(servicoInteratividadeTO.getNmUsuarioCriacao());
						servIntCanal.setDtCriacao(servicoInteratividadeTO.getDtCriacao());
												
						servIntCanalList.add(servIntCanal);												
					}									
				}
				servicoInteratividade.setServicoIntCanal(servIntCanalList);
				//new CanalAtendimentoTOBuilder().createCanalAtendimentoTOList(canalAtendimentoList);								
			}*/
			
			servicoInteratividade.setFuncionalidade(new FuncionalidadeTOBuilder().createFuncionalidade(servicoInteratividadeTO.getFuncionalidadeTO()));
			servicoInteratividade.setDtCriacao(servicoInteratividadeTO.getDtCriacao());
			servicoInteratividade.setDtUltimaAlteracao(servicoInteratividadeTO.getDtUltimaAlteracao());
			servicoInteratividade.setNmServico(servicoInteratividadeTO.getNmServico());
			servicoInteratividade.setNmUsuarioAlteracao(servicoInteratividadeTO.getNmUsuarioAlteracao());
			servicoInteratividade.setNmUsuarioCriacao(servicoInteratividadeTO.getNmUsuarioCriacao());
			//servicoInteratividade.setPlataforma(new PlataformaTOBuilder().createPlataforma(servicoInteratividadeTO.getPlataformaTO()));
			
/*			if (servicoInteratividadeTO.getServicoIntPlataformaTOList().size() > 0 ) {				
				List<ServicoIntPlataforma> servIntPlataformList = new ArrayList<ServicoIntPlataforma>();
				
				for (int i = 0; i < servicoInteratividadeTO.getServicoIntPlataformaTOList().size(); i++) {
					
					if (servicoInteratividadeTO.getServicoIntPlataformaTOList().get(0).getPlataforma().getIdPlataforma() != 0) {
						Plataforma platfom = servicoInteratividadeTO.getServicoIntPlataformaTOList().get(i).getPlataforma();
						
						ServicoIntPlataforma servIntPlataforma = new ServicoIntPlataforma();
						servIntPlataforma.setPlataforma(platfom);
						servIntPlataforma.setServicoInteratividade(new ServicoInteratividade(servicoInteratividadeTO.getIdServicoInteratividade()));
						servIntPlataforma.setNmUsuarioCriacao(servicoInteratividadeTO.getNmUsuarioCriacao());
						servIntPlataforma.setDtCriacao(servicoInteratividadeTO.getDtCriacao());
						
						
						servIntPlataformList.add(servIntPlataforma);						
						
					}
				}
				servicoInteratividade.setServicoIntPlataforma(servIntPlataformList);
				//new PlataformaTOBuilder().createPlataformaTOList(plataformaList);
			}*/
			servicoInteratividade.setUrl(servicoInteratividadeTO.getUrl());
			servicoInteratividade.setIdServicoInteratividade(servicoInteratividadeTO.getIdServicoInteratividade());
			servicoInteratividade.setTipoLinha(new TipoLinhaTOBuilder().createTipoLinha(servicoInteratividadeTO.getTipoLinhaTO()));
			//servicoInteratividade.setTipoCliente(new TipoClienteTOBuilder().createTipoCliente(servicoInteratividadeTO.getTipoClienteTO()));
			
/*			if (servicoInteratividadeTO.getServicoIntClienteTOList().size() > 0 ) {				
				List<ServicoIntCliente> servIntTipoClienteList = new ArrayList<ServicoIntCliente>();
				
				for (int i = 0; i < servicoInteratividadeTO.getServicoIntClienteTOList().size(); i ++ ) {
					
					if (servicoInteratividadeTO.getServicoIntClienteTOList().get(0).getTipoCliente().getIdTipoCliente() != 0 ) {
						TipoCliente tpCliente = servicoInteratividadeTO.getServicoIntClienteTOList().get(i).getTipoCliente();
						
						ServicoIntCliente servIntCliente = new ServicoIntCliente();
						servIntCliente.setTipoCliente(tpCliente);
						servIntCliente.setServicoInteratividade(new ServicoInteratividade(servicoInteratividadeTO.getIdServicoInteratividade()));
						servIntCliente.setDtCriacao(servicoInteratividadeTO.getDtCriacao());
						servIntCliente.setNmUsuarioCriacao(servicoInteratividadeTO.getNmUsuarioCriacao());
						
						servIntTipoClienteList.add(servIntCliente);												
					}					
				}
				servicoInteratividade.setServicoIntCliente(servIntTipoClienteList);				
				//new TipoClienteTOBuilder().createTipoClienteTOList(tipoClienteList);
			}*/
			
			//servicoInteratividade.setTecnologia(new TecnologiaTOBuilder().createTecnologia(servicoInteratividadeTO.getTecnologiaTO()));
/*			if (servicoInteratividadeTO.getServicoIntTecnologiaTOList().size() > 0 ) {
				List<ServicoIntTecnologia> servIntTecnologiaList = new ArrayList<ServicoIntTecnologia>();
				
				for (int i = 0; i < servicoInteratividadeTO.getServicoIntTecnologiaTOList().size(); i++) {
					
					if (servicoInteratividadeTO.getServicoIntTecnologiaTOList().get(0).getTecnologia().getIdTecnologia() != 0) {
						Tecnologia tecnol = servicoInteratividadeTO.getServicoIntTecnologiaTOList().get(0).getTecnologia();	
						
						ServicoIntTecnologia servIntTecno = new ServicoIntTecnologia();
						servIntTecno.setTecnologia(tecnol);
						servIntTecno.setServicoInteratividade(new ServicoInteratividade(servicoInteratividadeTO.getIdServicoInteratividade()));
						servIntTecno.setDtCriacao(servicoInteratividadeTO.getDtCriacao());
						servIntTecno.setNmUsuarioCriacao(servicoInteratividadeTO.getNmUsuarioCriacao());
						
						servIntTecnologiaList.add(servIntTecno);												
					}
				}		
				servicoInteratividade.setServicoIntTecnologia(servIntTecnologiaList);
				//new TecnologiaTOBuilder().createTecnologiaTOList(tecnologiaList);
			}	*/

		}
		return servicoInteratividade;
	}
	
	/**
	 * @param servicoInteratividade
	 * @return
	 * 
	 * Método responsável em transformar um Entity em um TO.
	 */
	public ServicoInteratividadeTO createServicoInteratividadeTO(ServicoInteratividade servicoInteratividade) {
		
		ServicoInteratividadeTO servicoInteratividadeTO = null;
		
		if (servicoInteratividade != null) {
			servicoInteratividadeTO = new ServicoInteratividadeTO();
			servicoInteratividadeTO.setAtivo(servicoInteratividade.getAtivo());
			//servicoInteratividadeTO.setCanalAtendimentoTO(new CanalAtendimentoTOBuilder().createCanalAtendimentoTO(servicoInteratividade.getCanalAtendimento()));
			// Canal
			List<ServicoIntCanalTO> servicoIntCanalTOList = new ArrayList<ServicoIntCanalTO>();
			
			if (servicoInteratividade.getServicoIntCanal() != null && !servicoInteratividade.getServicoIntCanal().isEmpty()) {				
			
				if (servicoInteratividade.getServicoIntCanal().size() > 0) {
					for (int i = 0; i < servicoInteratividade.getServicoIntCanal().size(); i++) {
						servicoIntCanalTOList.add(new ServicoIntCanalTOBuilder().createServicoIntCanalTO(servicoInteratividade.getServicoIntCanal().get(i)));						
					}					
					servicoInteratividadeTO.setServicoIntCanalTOList(servicoIntCanalTOList);									
				}				
			}
			servicoInteratividadeTO.setFuncionalidadeTO(new FuncionalidadeTOBuilder().createFuncionalidadeTO(servicoInteratividade.getFuncionalidade()));
			
			servicoInteratividadeTO.setDtCriacao(servicoInteratividade.getDtCriacao());
			servicoInteratividadeTO.setDtUltimaAlteracao(servicoInteratividade.getDtUltimaAlteracao());
			servicoInteratividadeTO.setIdServicoInteratividade(servicoInteratividade.getIdServicoInteratividade());
			servicoInteratividadeTO.setNmServico(servicoInteratividade.getNmServico());
			servicoInteratividadeTO.setNmUsuarioAlteracao(servicoInteratividade.getNmUsuarioAlteracao());
			servicoInteratividadeTO.setNmUsuarioCriacao(servicoInteratividade.getNmUsuarioCriacao());
			//servicoInteratividadeTO.setPlataformaTO(new PlataformaTOBuilder().createPlataformaTO(servicoInteratividade.getPlataforma()));
			// Plataforma
			List<ServicoIntPlataformaTO> servicoIntPlataformaTOList = new ArrayList<ServicoIntPlataformaTO>();
			
			if (servicoInteratividade.getServicoIntPlataforma() != null && !servicoInteratividade.getServicoIntPlataforma().isEmpty()) {				
			
				if (servicoInteratividade.getServicoIntPlataforma().size() > 0) {
					for (int j = 0; j < servicoInteratividade.getServicoIntPlataforma().size(); j++) {
						servicoIntPlataformaTOList.add(new ServicoIntPlataformaTOBuilder().createServicoIntPlataformaTO(servicoInteratividade.getServicoIntPlataforma().get(j)));
					}
					servicoInteratividadeTO.setServicoIntPlataformaTOList(servicoIntPlataformaTOList);
				}				
			}
						
			servicoInteratividadeTO.setUrl(servicoInteratividade.getUrl());
			servicoInteratividadeTO.setTipoLinhaTO(new TipoLinhaTOBuilder().createTipoLinhaTO(servicoInteratividade.getTipoLinha()));
			//servicoInteratividadeTO.setTipoClienteTO(new TipoClienteTOBuilder().createTipoClienteTO(servicoInteratividade.getTipoCliente()));
			
			// Tipo Cliente
			List<ServicoIntClienteTO> servicoIntClienteTOList = new ArrayList<ServicoIntClienteTO>();
			
			if (servicoInteratividade.getServicoIntCliente() != null && !servicoInteratividade.getServicoIntCliente().isEmpty()) {				
			
				if (servicoInteratividade.getServicoIntCliente().size() > 0) {
					for (int k = 0; k < servicoInteratividade.getServicoIntCliente().size(); k++) {
						servicoIntClienteTOList.add(new ServicoIntClienteTOBuilder().createServicoIntClienteTO(servicoInteratividade.getServicoIntCliente().get(k)));
					}
					servicoInteratividadeTO.setServicoIntClienteTOList(servicoIntClienteTOList);
				}				
			}
			
			//servicoInteratividadeTO.setTecnologiaTO(new TecnologiaTOBuilder().createTecnologiaTO(servicoInteratividade.getTecnologia()));
			
			// Tecnologia
			List<ServicoIntTecnologiaTO> servicoIntTecnologiaTOList = new ArrayList<ServicoIntTecnologiaTO>();
			
			if (servicoInteratividade.getServicoIntTecnologia() != null && !servicoInteratividade.getServicoIntTecnologia().isEmpty()) {				
			
				if (servicoInteratividade.getServicoIntTecnologia().size() > 0) {
					for (int h = 0; h < servicoInteratividade.getServicoIntTecnologia().size(); h++) {
						servicoIntTecnologiaTOList.add(new ServicoIntTecnologiaTOBuilder().createServicoIntTecnologiaTO(servicoInteratividade.getServicoIntTecnologia().get(h)));
						
					}
					servicoInteratividadeTO.setServicoIntTecnologiaTOList(servicoIntTecnologiaTOList);
				}
				
			}
			
			SrvInteratividadeParamTOBuilder srvInteratividadeParametroTOBuilder = new SrvInteratividadeParamTOBuilder();
			servicoInteratividadeTO.setSrvInteratividadeParametroTOList(srvInteratividadeParametroTOBuilder.createSrvInteratividadeParametroTOList(servicoInteratividade.getSrvInteratividadeParametroList()));

		}
		
		return servicoInteratividadeTO;
	}
	
	/**
	 * @param servicoInteratividadeList
	 * @return
	 * 
	 * Método responsável em transformar uma lista de Entity em uma lista de TO.
	 */
	public List<ServicoInteratividadeTO> createServicoInteratividadeTOList(List<ServicoInteratividade> servicoInteratividadeList) {
		
		List<ServicoInteratividadeTO> servicoInteratividadeTOList = new ArrayList<ServicoInteratividadeTO>();
		
		if (servicoInteratividadeList != null && servicoInteratividadeList.size() > 0) {
			for (ServicoInteratividade servicoInteratividade : servicoInteratividadeList) {
				servicoInteratividadeTOList.add(createServicoInteratividadeTO(servicoInteratividade));
			}
		}
		
		return servicoInteratividadeTOList;
	}
}
