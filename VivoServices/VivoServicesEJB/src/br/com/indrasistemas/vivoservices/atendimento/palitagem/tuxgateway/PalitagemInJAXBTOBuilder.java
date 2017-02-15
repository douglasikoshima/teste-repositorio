package br.com.indrasistemas.vivoservices.atendimento.palitagem.tuxgateway;

import javax.xml.bind.JAXBException;

import br.com.indrasistemas.vivoservices.atendimento.palitagem.to.FormularioCampoTO;
import br.com.indrasistemas.vivoservices.atendimento.palitagem.to.FormularioCampoValorTO;
import br.com.indrasistemas.vivoservices.atendimento.palitagem.to.PalitagemTO;
import br.com.indrasistemas.vivoservices.atendimento.palitagem.tuxgateway.entrada.AtendimentoVOType;
import br.com.indrasistemas.vivoservices.atendimento.palitagem.tuxgateway.entrada.FormularioCampoVOType;
import br.com.indrasistemas.vivoservices.atendimento.palitagem.tuxgateway.entrada.FormularioCampoValorVOType;
import br.com.indrasistemas.vivoservices.atendimento.palitagem.tuxgateway.entrada.FormularioVOType;
import br.com.indrasistemas.vivoservices.atendimento.palitagem.tuxgateway.entrada.MsgBodyType;
import br.com.indrasistemas.vivoservices.atendimento.palitagem.tuxgateway.entrada.ObjectFactory;

public class PalitagemInJAXBTOBuilder {

    //ObjectFactory factory = new ObjectFactory();

    static public MsgBodyType buildREGPALITAGEFOxmlIn(PalitagemTO to) throws JAXBException {

        ObjectFactory factory = new ObjectFactory();
        MsgBodyType corpoInB = factory.createMsgBodyType();
        AtendimentoVOType corpoIn = factory.createAtendimentoVOType();

        Integer inConsultor = new Integer(0);

        if (to.isInConsultor()!=null && to.isInConsultor().equals(inConsultor)) {
            inConsultor = new Integer(1);
        }

        corpoIn.setCdServico(to.getCdServico()!=null?to.getCdServico():"");
        corpoIn.setDsComentario(to.getDsComentario()!=null?to.getDsComentario():"");
        corpoIn.setIdCanal(to.getIdCanal()!=null?to.getIdCanal():"");
        corpoIn.setIdGrupoAbertura(to.getIdGrupoAbertura()!=null?to.getIdGrupoAbertura():"");
        corpoIn.setIdSistema(to.getIdSistema()!=null?to.getIdSistema():"");
        corpoIn.setIdUsuario(to.getIdUsuario()!=null?to.getIdUsuario():"");
        corpoIn.setNrProtocolo(to.getNrProtocolo()!=null?to.getNrProtocolo():"");
        corpoIn.setIdContato(to.getIdContato()!=null?to.getIdContato().toString():"");
        corpoIn.setInConsultor(inConsultor.intValue());
        corpoIn.setTpOperacao(to.getTpOperacao()!=null?to.getTpOperacao().intValue():1);
        corpoIn.setNrDocumento(to.getNrDocumento()!=null?to.getNrDocumento():"");

        if (to.getFormularioTO() != null && to.getFormularioTO().getFormularioCampoTO() != null && to.getFormularioTO().getFormularioCampoTO().length > 0) {
            FormularioVOType fVOType = factory.createFormularioVOType();

            for (int i = 0; i < to.getFormularioTO().getFormularioCampoTO().length; i++) {
                FormularioCampoVOType fCVOType = factory.createFormularioCampoVOType();
                FormularioCampoTO fCTO = to.getFormularioTO().getFormularioCampoTO()[i];
                fCVOType.setIdCampo(fCTO.getIdCampo());
                fCVOType.setIdContatoFolhaCampo(fCTO.getIdContatoFolhaCampo());
                if (fCTO.getFormularioCampoValorTO() != null && fCTO.getFormularioCampoValorTO().length > 0) {
                    for (int j = 0; j < fCTO.getFormularioCampoValorTO().length; j++) {
                        FormularioCampoValorVOType fCVVOType = factory.createFormularioCampoValorVOType();
                        FormularioCampoValorTO fCVTO = fCTO.getFormularioCampoValorTO()[j];
                        fCVVOType.setIdFormularioCampoValor(fCVTO.getIdFormularioCampoValor());
                        fCVVOType.setValor(fCVTO.getValor());
                        fCVOType.getFormularioCampoValorVO().add(fCVVOType);
                    }
                    fVOType.getFormularioCampoVO().add(fCVOType);
                }
            }
            corpoIn.setFormularioVO(fVOType);
        }
        corpoInB.setAtendimentoVO(corpoIn);
        return corpoInB;
    }
}