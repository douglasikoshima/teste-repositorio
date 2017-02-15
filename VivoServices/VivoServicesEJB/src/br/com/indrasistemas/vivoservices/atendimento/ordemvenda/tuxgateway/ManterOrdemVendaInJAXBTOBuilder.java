package br.com.indrasistemas.vivoservices.atendimento.ordemvenda.tuxgateway;

import javax.xml.bind.JAXBException;

import br.com.indrasistemas.vivoservices.atendimento.ordemvenda.to.PalitagemTO;
import br.com.indrasistemas.vivoservices.atendimento.ordemvenda.tuxgateway.entrada.AtendimentoVOType;
import br.com.indrasistemas.vivoservices.atendimento.ordemvenda.tuxgateway.entrada.MsgBodyType;
import br.com.indrasistemas.vivoservices.atendimento.ordemvenda.tuxgateway.entrada.ObjectFactory;

public class ManterOrdemVendaInJAXBTOBuilder {

    static public MsgBodyType buildREGPALITAGEFOxmlIn(PalitagemTO to) throws JAXBException {

        ObjectFactory factory = new ObjectFactory();
        MsgBodyType corpoInB = factory.createMsgBodyType();
        AtendimentoVOType corpoIn = factory.createAtendimentoVOType();
        
        corpoIn.setCdServico(to.getCdServico()!=null?to.getCdServico():"");
        corpoIn.setDsComentario(to.getDsComentario()!=null?to.getDsComentario():"");
        corpoIn.setIdCanal(to.getIdCanal()!=null?to.getIdCanal():"");
        corpoIn.setIdGrupoAbertura(to.getIdGrupoAbertura()!=null?to.getIdGrupoAbertura():"");
        corpoIn.setIdSistema(to.getIdSistema()!=null?to.getIdSistema():"");
        corpoIn.setIdUsuario(to.getIdUsuario()!=null?to.getIdUsuario():"");
        corpoIn.setNrProtocolo(to.getNrProtocolo()!=null?to.getNrProtocolo():"");
        corpoIn.setIdContato(to.getIdContato()!=null?to.getIdContato().toString():"");
        corpoIn.setInConsultor(0);
        corpoIn.setNrTelefone(to.getNrTelefone()!=null?to.getNrTelefone().toString():"");
        //corpoIn.setTpOperacao(to.getTpOperacao()!=null?to.getTpOperacao().intValue():1);
        corpoIn.setTpOperacao(2);
        corpoIn.setNrOrdemVenda(to.getNrOrdemVenda()!=null?to.getNrOrdemVenda():"");
        corpoIn.setCdStatusRejeicao(to.getCdStatusRejeicao());
        //corpoIn.setNrDocumento(to.getNrDocumento()!=null?to.getNrDocumento():"");

        corpoInB.setAtendimentoVO(corpoIn);
        return corpoInB;
    }
}
