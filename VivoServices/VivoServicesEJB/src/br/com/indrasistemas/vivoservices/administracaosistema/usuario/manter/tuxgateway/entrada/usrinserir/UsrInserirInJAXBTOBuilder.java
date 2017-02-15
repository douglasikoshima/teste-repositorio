package br.com.indrasistemas.vivoservices.administracaosistema.usuario.manter.tuxgateway.entrada.usrinserir;

import javax.xml.bind.JAXBException;

import br.com.indrasistemas.vivoservices.administracaosistema.usuario.manter.to.ParametrosTO;

public class UsrInserirInJAXBTOBuilder {

    static public MsgBodyType buildUsrInserirXmlIn(ParametrosTO to) throws JAXBException {

        ObjectFactory factory = new ObjectFactory();
        
        MsgBodyType corpoInB = factory.createMsgBodyType();
        UsuarioVOType corpoIn = factory.createUsuarioVOType();
        UFOperadoraPessoaVOType ufOpera = factory.createUFOperadoraPessoaVOType();
        DocumentoSimpVOType docVO = factory.createDocumentoSimpVOType();
        
        UFVOType ufVO = factory.createUFVOType();
        UFVOType ufVO1 = factory.createUFVOType();
        TipoDocumentoVOType tipoDocVO = factory.createTipoDocumentoVOType();
        PaisVOType paisVO = factory.createPaisVOType();

        paisVO.setIdPais(to.getIdPais());
        paisVO.setDsNacionalidade(to.getDsNacionalidade());
        paisVO.setNmPais("");
        paisVO.setSgPais("");
        
        tipoDocVO.setIdTipoDocumento(to.getIdTipoDocumento());
        tipoDocVO.setDsTipoDocumento(to.getDsTipoDocumento());
        tipoDocVO.setSgTipoDocumento(to.getSgTipoDocumento());
        
        ufVO.setIdUF(to.getIdUF());
        ufVO.setNmUF("");
        ufVO.setSgUF("");

        ufVO1.setIdUF(to.getIdUF());
        ufVO1.setNmUF("");
        ufVO1.setSgUF("");
        
        ufOpera.setIdUFOperadora(to.getIdUFOperadora());
        ufOpera.setDsUFOperadora("");
        ufOpera.setUFVO(ufVO);

        docVO.setFromWS("1");
        docVO.setIdDocumento(to.getIdDocumento());
        docVO.setDsDocumento(to.getDsDocumento());
        docVO.setTipoDocumentoVO(tipoDocVO);
        docVO.setUFVO(ufVO1);
        docVO.setPaisVO(paisVO);

        corpoIn.setIdDDD(to.getIdDDD());
        corpoIn.setDsTelefone(to.getDsTelefone());
        corpoIn.setNome(to.getNome());
        corpoIn.setSobrenome(to.getSobrenome());
        corpoIn.setEmail(to.getEmail());
        corpoIn.setLogin(to.getLogin());
        corpoIn.setLoginCti(to.getLoginCti());
        corpoIn.setLoginChefe(to.getLoginChefe());
        corpoIn.setIdStatusAtual(to.getIdStatusAtual());
        corpoIn.setIdPerfilConsultorAtdAtual(to.getIdPerfilConsultorAtdAtual());
        corpoIn.setIdFornecedorConsultorAtdAtual(to.getIdFornecedorConsultorAtdAtual());
        corpoIn.setIdSiteConsultorAtdAtual(to.getIdSiteConsultorAtdAtual());
        corpoIn.setDsLoginRoteamento(to.getDsLoginRoteamento());
        corpoIn.setIdMotivo(to.getIdMotivo());
        corpoIn.setDtInicio(to.getDtInicio());
        corpoIn.setDtRetorno(to.getDtRetorno());
        corpoIn.setIdCargoAtual(to.getIdCargoAtual());
        corpoIn.setIdUF(to.getIdUF());
        corpoIn.setDtInclusao(to.getDtInclusao());
        corpoIn.setDtExclusao(to.getDtExclusao());
        corpoIn.setIdUsuario(to.getIdUsuario());
        corpoIn.setInConsultor(to.getInConsultor());
        
        corpoIn.getUFOperadoraPessoaVO().add(ufOpera);
        corpoIn.getDocumentoSimpVO().add(docVO);

        corpoIn.setDsDDD("");
        corpoIn.setDescricaoCargoAtual("");
        corpoIn.setDescricaoStatusAtual(to.getDescricaoStatusAtual());
        corpoIn.setDsFornecedorConsultorAtdAtual("");
        corpoIn.setDsMotivo(to.getDsMotivo());
        corpoIn.setDsPerfilConsultorAtdAtual("");
        corpoIn.setDsSiteConsultorAtdAtual("");
        corpoIn.setNmUF("");
        corpoIn.setNomeChefe(to.getNomeChefe());
        
        corpoInB.setUsuarioVO(corpoIn);
        
        return corpoInB;
    }

}