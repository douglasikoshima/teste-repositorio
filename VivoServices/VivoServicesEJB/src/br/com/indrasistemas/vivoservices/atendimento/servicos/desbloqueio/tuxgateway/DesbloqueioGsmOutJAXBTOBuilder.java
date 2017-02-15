package br.com.indrasistemas.vivoservices.atendimento.servicos.desbloqueio.tuxgateway;

import javax.xml.bind.JAXBException;
import br.com.indrasistemas.vivoservices.atendimento.servicos.desbloqueio.to.DesbloqueioGsmTO;
import br.com.indrasistemas.vivoservices.atendimento.servicos.desbloqueio.tuxgateway.saida.MsgBodyType;
import br.com.indrasistemas.vivoservices.atendimento.servicos.desbloqueio.tuxgateway.saida.MsgBodyType.DesbloqueioGsmVOType;

public class DesbloqueioGsmOutJAXBTOBuilder {

	static public DesbloqueioGsmTO buildDesbloqueioGsmTO(Object body) throws JAXBException {

	    DesbloqueioGsmTO to = new DesbloqueioGsmTO();
		MsgBodyType out = (MsgBodyType) body;
		if(out!=null){
            DesbloqueioGsmVOType corpoOut = out.getDesbloqueioGsmVO();
            if(corpoOut!=null && !"".equals(corpoOut)){
                to.setNrSimLock(corpoOut.getNrSimLock());
                to.setStatusConsulta(corpoOut.getStatusConsulta());
                to.setErrorCode(corpoOut.getErrorCode());
                to.setErrorDescription(corpoOut.getErrorDescription());
            }else{
                to.setNrSimLock("");
                to.setStatusConsulta("ERRO");
                to.setErrorCode("3");
                to.setErrorDescription("Erro no retorno dos dados do Serviço");
            }
		}else{
            to.setNrSimLock("");
            to.setStatusConsulta("ERRO");
            to.setErrorCode("3");
            to.setErrorDescription("Erro no retorno dos dados do Serviço");
		}
		return to;
	}
}
