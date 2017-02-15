package br.com.indrasistemas.vivoservices.administracaosistema.usuario.manter.tuxgateway.entrada.grvidm;

import java.util.Iterator;

import javax.xml.bind.JAXBException;

import br.com.indrasistemas.vivoservices.administracaosistema.usuario.manter.to.Id;
import br.com.indrasistemas.vivoservices.administracaosistema.usuario.manter.to.RelacionaUsuarioTO;
import br.com.indrasistemas.vivoservices.administracaosistema.usuario.manter.to.RespostaTO;

public class GrvIdmJAXBTOBuilder {

	public static MsgBodyType buildXmlIn(RelacionaUsuarioTO relacionaUsuarioTO) throws JAXBException{

		ObjectFactory factory = new ObjectFactory();
		
		MsgBodyType corpoInB = factory.createMsgBodyType();
		IDMPerfilVOType idmPerfilVO = factory.createIDMPerfilVOType();
		ListasVOType listasVO = factory.createListasVOType();
		ListaType lista = factory.createListaType();
		ItemType itemType = factory.createItemType();
		ItemType disp     = factory.createItemType();
		ItType itTemp = factory.createItemTypeIt();
		
		itTemp.setDs(null);
		itTemp.setId("");
		disp.getIt().add(itTemp);
		
		for (Iterator iterator = relacionaUsuarioTO.getListaId().iterator(); iterator.hasNext();) {
			ItType it = factory.createItemTypeIt();
			Id id = (Id) iterator.next();
			it.setId(id.getId());
			it.setDs(null);
			itemType.getIt().add(it);
		}
		
		String nmSelect = relacionaUsuarioTO.getNmUnidade();
		lista.setDisponivel(disp);
		lista.setSelecionado(itemType);
		lista.setNmSelect(nmSelect);
		listasVO.getLista().add(lista);
		
		idmPerfilVO.setTpOperacao("Relacionar");
		idmPerfilVO.setIdUsuario(relacionaUsuarioTO.getIdUsuario());
		idmPerfilVO.setListasVO(listasVO);
		
		corpoInB.setIDMPerfilVO(idmPerfilVO);
		
		return corpoInB;
	}
	
	public static RespostaTO buildXmlOut(Object body) throws JAXBException {
		RespostaTO to = new RespostaTO();
		MsgBodyType out = (MsgBodyType) body;
		if(out!=null){
			IDMPerfilVOType corpoOut = out.getIDMPerfilVO();
            if(corpoOut!=null && !"".equals(corpoOut)){
            	to.setCodError(corpoOut.getCodError());
            	to.setMsgError(corpoOut.getMsgError());
            }else{
                to.setCodError("99");
                to.setMsgError("Erro no retorno dos dados do Serviço");
            }
		}else{
            to.setCodError("99");
            to.setMsgError("Erro no retorno dos dados do Serviço");
		}
		return to;
	} 
}
