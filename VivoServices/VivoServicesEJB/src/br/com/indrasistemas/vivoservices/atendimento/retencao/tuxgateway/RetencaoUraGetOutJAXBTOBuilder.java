package br.com.indrasistemas.vivoservices.atendimento.retencao.tuxgateway;

import java.util.List;
import javax.xml.bind.JAXBException;
import br.com.indrasistemas.vivoservices.atendimento.retencao.to.RetencaoParamTO;
import br.com.indrasistemas.vivoservices.atendimento.retencao.to.RetencaoTO;
import br.com.indrasistemas.vivoservices.atendimento.retencao.tuxgateway.saida.getbonus.ListaBonusVOType;
import br.com.indrasistemas.vivoservices.atendimento.retencao.tuxgateway.saida.getbonus.MsgBodyType;
import br.com.indrasistemas.vivoservices.atendimento.retencao.tuxgateway.saida.getbonus.ListaBonusVOType.OfertasType;

public class RetencaoUraGetOutJAXBTOBuilder {

	static public RetencaoTO buildGETBONUSxmlOut(Object body) throws JAXBException {
	    
	    RetencaoTO to = new RetencaoTO();
	    //String[]   aList = {"","05 Dias","15 Dias","30 Dias","45 Dias","60 Dias","Sem Validade"};
	    
	    if(body!=null){
	        ListaBonusVOType listaBonusVOType = ((MsgBodyType) body).getListaBonusVO();

	        if(listaBonusVOType!=null){
                List ofertas = listaBonusVOType.getOfertas();
                
                if(ofertas!=null && ofertas.size()>0){
                    RetencaoParamTO[] out = new RetencaoParamTO[ofertas.size()];
                    
                    for(int i=0;i<ofertas.size();i++){
                        OfertasType item = (OfertasType) ofertas.get(i);
                        if(item!=null){
                            out[i] = new RetencaoParamTO();
                            out[i].setCdBonus(item.getCodigo());
                            //out[i].setDtValidade((item.getValidade()!=null && !"".equals(item.getValidade()))?aList[Integer.parseInt(item.getValidade())]:"");
                            out[i].setDtValidade(item.getValidade());
                            out[i].setNmGrupo(item.getGrupo());
                            out[i].setVlTarifa(item.getTarifa());
                        }
                    }
                    to.setOut(out);
                }
                
                to.setCdRetorno(listaBonusVOType.getCdRetorno());
                to.setMsgRetorno(listaBonusVOType.getMsgRetorno());
	        }
	    }
		return to;
	}
}
