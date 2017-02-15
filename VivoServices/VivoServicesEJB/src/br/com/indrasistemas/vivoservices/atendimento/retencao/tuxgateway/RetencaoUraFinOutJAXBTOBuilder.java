package br.com.indrasistemas.vivoservices.atendimento.retencao.tuxgateway;

import javax.xml.bind.JAXBException;
import br.com.indrasistemas.vivoservices.atendimento.retencao.to.RetencaoTO;
import br.com.indrasistemas.vivoservices.atendimento.retencao.tuxgateway.saida.finalret.MsgBodyType;
import br.com.indrasistemas.vivoservices.atendimento.retencao.tuxgateway.saida.finalret.RetornoVOType;

public class RetencaoUraFinOutJAXBTOBuilder {

	static public RetencaoTO buildFINALRETENCAOxmlOut(Object body) throws JAXBException {
	    
	    RetencaoTO     to = new RetencaoTO();
	    String  cdRetorno = "";
	    String msgRetorno = "";
	    
	    if(body!=null){
	        RetornoVOType retornoVO = ((MsgBodyType) body).getRetornoVO();
	        if(retornoVO!=null){
                String nrRetencao = retornoVO.getValor();
                
                to.setNrRetencao((nrRetencao!=null && !"".equals(nrRetencao))?new Long(nrRetencao):new Long(0));
    
                cdRetorno  = retornoVO.getCdRetorno();
                msgRetorno = retornoVO.getMsgRetorno();
	        }
	    }

	    to.setCdRetorno(cdRetorno!=null?cdRetorno:"");
	    to.setMsgRetorno(msgRetorno!=null?msgRetorno:"");

	    return to;
	}
}
