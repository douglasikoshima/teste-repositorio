package br.com.indrasistemas.vivoservices.portabilidade.cliente.servicegateway.tuxedo.portProcessos;

import javax.xml.bind.JAXBException;

import br.com.indrasistemas.framework.service.dao.exceptions.DAOException;
import br.com.indrasistemas.vivoservices.portabilidade.cliente.dao.ClientePortabilidadeDAO;
import br.com.indrasistemas.vivoservices.portabilidade.cliente.servicegateway.tuxedo.portProcessos.entrada.MsgBodyType;
import br.com.indrasistemas.vivoservices.portabilidade.cliente.servicegateway.tuxedo.portProcessos.entrada.ObjectFactory;
import br.com.indrasistemas.vivoservices.portabilidade.cliente.servicegateway.tuxedo.portProcessos.entrada.MsgBodyType.PortabilidadeProcessosTOType;
import br.com.indrasistemas.vivoservices.portabilidade.cliente.to.PortabilidadeProcessosTO;

public class PortProcessosInJAXBTOBuilder {

	static public MsgBodyType buildPortProcessosXmlIn(PortabilidadeProcessosTO to) throws JAXBException {

		ObjectFactory factory = new ObjectFactory();
		MsgBodyType corpoInB = factory.createMsgBodyType();

		PortabilidadeProcessosTOType corpoIn = factory.createMsgBodyTypePortabilidadeProcessosTOType();

		corpoIn.setCdAreaRegistro(to.getCdAreaRegistro().intValue());
		corpoIn.setNrLinha(to.getNrLinha().longValue());
		corpoIn.setDtJanela(to.getDtJanela());
		corpoIn.setCdOperacao(to.getCdOperacao().intValue());

		if(to.getEstadoPortabilidade().intValue()==21 && (to.getCdOperacao().intValue()==1 || to.getCdOperacao().intValue()==4)){
			ClientePortabilidadeDAO daoPort = new ClientePortabilidadeDAO();
			int acaoPort = to.getEstadoPortabilidade().intValue();
			System.out.println("acaoPort:= "+acaoPort);
			try {
				acaoPort = daoPort.getProcessosLoja(to.getCdAreaRegistro().longValue(), to.getNrLinha().longValue(), to.getEstadoPortabilidade().intValue());
				corpoIn.setEstadoPortabilidade(acaoPort);
				System.out.println("acaoPort:= "+acaoPort);

			} catch (DAOException e) {
				System.err.println("DAOException::"+e.getMessage());
				corpoIn.setEstadoPortabilidade(to.getEstadoPortabilidade().intValue());
			}
		}else{
			corpoIn.setEstadoPortabilidade(to.getEstadoPortabilidade().intValue());
		}
		
		corpoIn.setCdOperadoraSolicitante(to.getCdOperadoraSolicitante().intValue());
		corpoIn.setDsComentarioProcesso(to.getDsComentarioProcesso());
		corpoIn.setNrProcesso(to.getNrProcesso().longValue());
		corpoIn.setNrBilhetePortabilidade(to.getNrBilhetePortabilidade().longValue());
		corpoIn.setCdOperacao(to.getCdOperacao().intValue());
		corpoIn.setCdProcedencia(to.getCdProcedencia().intValue());

		corpoInB.setPortabilidadeProcessosTO(corpoIn);

		return corpoInB;
	}
}