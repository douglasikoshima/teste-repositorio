#include <tuxfw.h>
#include <PPGlobalPORT.h>
#include <PPExceptionPORT.h>
#include <ToolsPORT.h>
#include <PessoaPORT.h>
#include <PessoaDeParaPORT.h>
#include <PessoaDocumentoPORT.h>
#include <PessoaContaPORT.h>
#include <PessoaFisicaPORT.h>
#include <PessoaJuridicaPORT.h>
#include <PessoaComunicacaoPORT.h>
// #include <PessoaSegmentacaoPORT.h>
// #include <PessoaSegmentacaoHistoricoPORT.h>
#include <PessoaEnderecoPORT.h>
#include <DocumentoPORT.h>
#include <SistemaOrigemPORT.h>
#include <TipoEnderecoCobrancaPORT.h>
#include <TipoDocumentoPORT.h>
#include <UFPORT.h>
#include <TipoPessoaPORT.h>
#include <TipoLinhaPORT.h>
#include <MiscPORT.h>
#include <SexoPORT.h>
#include <EstadoCivilPORT.h>
#include <TipoComunicacaoPORT.h>
#include <TipoEnderecoPORT.h>
#include <TipoCarteiraPORT.h>
#include <CFOPPORT.h>
#include <PessoaPortabilidadePORT.h>
#include <PessoaPorHistPORT.h>
#include <RegistraContatoPORT.h>
#include <ContatoFuncionalidadePORT.h>
#include <AcaoPortabilidadePORT.h>

DECLARE_TUXEDO_SERVICE(SETCLIENTPORT);

void implSETCLIENTPORT::Execute(DOMNode *dnode, XMLGen *xml_g)
{
    /* ponteiro dos objetos */
    CPessoa                         *pclPessoa=NULL;
    CPessoaDePara                   *pclPessoaDePara=NULL;
    CPessoaFisica                   *pclPessoaFisica=NULL;
    CPessoaJuridica                 *pclPessoaJuridica=NULL;
    CPessoaDocumento                *pclPessoaDocumento=NULL;
    CPessoaComunicacao              *pclPessoaComunicacao=NULL;
    // CPessoaSegmentacao              *pclPessoaSegmentacao=NULL;
    // CPessoaSegmentacaoHistorico     *pclPessoaSegmentacaoHistorico=NULL;
    CPessoaEndereco                 *pclPessoaEndereco=NULL;
    CDocumento                      *pclDocumento=NULL;
    CSistemaOrigem                  *pclSistemaOrigem=NULL;
    CTipoEnderecoCobranca           *pclTipoEnderecoCobranca=NULL;
    CTipoDocumento                  *pclTipoDocumento=NULL;
    CUF                             *pclUF=NULL;
    CTipoPessoa                     *pclTipoPessoa=NULL;
    CTipoLinha                      *pclTipoLinha=NULL;
    CMisc                           *pclMisc=NULL;
    CSexo                           *pclSexo=NULL;
    CEstadoCivil                    *pclEstadoCivil=NULL;
    CTipoComunicacao                *pclTipoComunicacao=NULL;
    CTipoEndereco                   *pclTipoEndereco=NULL;
    CTipoCarteira                   *pclTipoCarteira=NULL;
    CCFOP                           *pclCFOP=NULL;
    CPessoaPortabilidade            *pclPessoaPortabilidade=NULL;
    CPessoaPortabilidadeHist        *pclPessoaPortabilidadeHist=NULL;
    CRegistraContato                *pclRegistraContato=NULL;
    CContatoFuncionalidade          *pclContatoFuncionalidade=NULL;
    CAcaoPortabilidade              *pclAcaoPortabilidade=NULL;
    XMLGen                          *pclXmlGenRegContato=NULL;

    try
    {
        ULOG_START("SETCLIENTPORT");

        /* controle para navegacao no xml */
        int iIdTipoPessoaCtrl=0;

        /* Usadas na macro GETTREE */
        char szMessage[LEN_RETURN_MESSAGE + LEN_EOS];
        char *pTree;

        /* Uso geral */
        char szAux[LEN_AUX + LEN_EOS];
        char *pTmp=NULL;
        bool bFlagNovoCadastro, bAchou;
        char szAuxRegistraContato[LEN_XML_REGCONTATO + LEN_EOS];
        char *pMessageBody=NULL;

        /* Ponteiros dos nos */
        DOMNode *DNPessoaFisica=NULL;
        DOMNode *DNPessoaJuridica=NULL;
        DOMNode *DNDocumento=NULL;
        DOMNode *DNTelefonePJ=NULL;
        DOMNode *DNTelefonePF=NULL;
        DOMNode *DNDadosEnderecoPJ=NULL;
        DOMNode *DNDadosEnderecoPF=NULL;


        /* estruturas de apoio para manipulacao dos dados dos objetos referentes */
        TDataProc tDataProc;                                memset(&tDataProc, 0x00, sizeof(TDataProc));
        TPessoa tPessoaXML;                                 memset(&tPessoaXML, 0x00, sizeof(TPessoa));
        TDocumento tDocumentoTmp;                           memset(&tDocumentoTmp, 0x00, sizeof(TDocumento));
        TPessoaJuridica tPessoaJuridicaXML;                 memset(&tPessoaJuridicaXML, 0x00, sizeof(TPessoaJuridica));
        TPessoaEndereco tPessoaEnderecoXML;                 memset(&tPessoaEnderecoXML, 0x00, sizeof(TPessoaEndereco));
        TDesmembraNome tDesmembraNome;


        /* Instancia objetos */
        pclPessoa                           = new CPessoa;
        pclPessoaDePara                     = new CPessoaDePara;
        pclPessoaFisica                     = new CPessoaFisica;
        pclPessoaJuridica                   = new CPessoaJuridica;
        pclPessoaDocumento                  = new CPessoaDocumento;
        pclPessoaComunicacao                = new CPessoaComunicacao;
        // pclPessoaSegmentacao                = new CPessoaSegmentacao;
        // pclPessoaSegmentacaoHistorico       = new CPessoaSegmentacaoHistorico;
        pclPessoaEndereco                   = new CPessoaEndereco;
        pclDocumento                        = new CDocumento;
        pclSistemaOrigem                    = new CSistemaOrigem;
        pclTipoEnderecoCobranca             = new CTipoEnderecoCobranca;
        pclTipoDocumento                    = new CTipoDocumento;
        pclUF                               = new CUF;
        pclTipoPessoa                       = new CTipoPessoa;
        pclTipoLinha                        = new CTipoLinha;
        pclMisc                             = new CMisc;
        pclSexo                             = new CSexo;
        pclEstadoCivil                      = new CEstadoCivil;
        pclTipoComunicacao                  = new CTipoComunicacao;
        pclTipoEndereco                     = new CTipoEndereco;
        pclTipoCarteira                     = new CTipoCarteira;
        pclCFOP                             = new CCFOP;
        pclPessoaPortabilidade              = new CPessoaPortabilidade;
        pclPessoaPortabilidadeHist          = new CPessoaPortabilidadeHist;
        pclRegistraContato                  = new CRegistraContato;
        pclContatoFuncionalidade            = new CContatoFuncionalidade;
        pclAcaoPortabilidade                = new CAcaoPortabilidade;
        pclXmlGenRegContato                 = new XMLGen;


        /* obtem o idUsuarioAlteracao do header */
        pTmp=getUser(); ULOG("getUser()[%s]", pTmp?pTmp:"...NULL...");
        if(pTmp != NULL) {
            if(strlen(pTmp) > 0)
                strcpy(tDataProc.szUser, pTmp);
            else
                throw PPExceptionPORT(ERRO_EXECUCAO, "Tag user vazia");
        }
        else
            throw PPExceptionPORT(ERRO_EXECUCAO, "Tag user inexistente");

        ULOG("tDataProc.szUser[%s]", tDataProc.szUser);

        ULOG("RSR DNODE>>>");
        GETTREE(tDataProc.szTpOperacao,         dnode, "tpOperacao",            0, OBRIGATORIO, "tpOperacao");          ULOG("tDataProc.szTpOperacao[%s]", tDataProc.szTpOperacao);
        GETTREE(tDataProc.szSgTipoPessoa,       dnode, "sgTipoPessoa",          0, OBRIGATORIO, "sgTipoPessoa");        ULOG("tDataProc.szSgTipoPessoa[%s]", tDataProc.szSgTipoPessoa);
        GETTREE(tDataProc.szNrLinha,            dnode, "nrLinha",               0, OBRIGATORIO, "nrLinha");             ULOG("tDataProc.szNrLinha[%s]", tDataProc.szNrLinha);
        GETTREE(tDataProc.szSgTipoLinha,        dnode, "sgTipoLinha",           0, NOBRIGATORIO, "sgTipoLinha");        ULOG("tDataProc.szSgTipoLinha[%s]", tDataProc.szSgTipoLinha);
        GETTREE(tDataProc.szIdPessoa,           dnode, "idPessoa",              0, NOBRIGATORIO, "idPessoa");           ULOG("tDataProc.szIdPessoa[%s]", tDataProc.szIdPessoa);
        
        if(strlen(tDataProc.szSgTipoLinha) == 0) {
            strcpy(tDataProc.szSgTipoLinha, "NC");
        }
        else if(!strcmp(tDataProc.szSgTipoLinha, "PRE")) {
            strcpy(tDataProc.szSgTipoLinha, "PRÉ");
        }

        // Cadastro Novo
        if(!strcmp(tDataProc.szTpOperacao, "0")) {
            bFlagNovoCadastro = true;
        } // Alteracao de Cadastro
        else if(!strcmp(tDataProc.szTpOperacao, "1")) {
            bFlagNovoCadastro = false;
        }
        else {
            sprintf(tDataProc.szMessageException, "tpOperacao invalido[%s]", tDataProc.szTpOperacao);
            throw PPExceptionPORT(ERRO_EXECUCAO, tDataProc.szMessageException);
        }

        /* Busca ids referentes as siglas de Tipo de Pessoa */
        pclTipoPessoa->setSgTipoPessoa(tDataProc.szSgTipoPessoa);
        if(pclTipoPessoa->buscaTipoPessoa() == false) {
            sprintf(tDataProc.szMessageException, "Nao ha registro em apoio.tipopessoa para SgTipoPessoa[%s]", pclTipoPessoa->getSgTipoPessoa());
            throw PPExceptionPORT(ERRO_EXECUCAO, tDataProc.szMessageException);
        }
        ULOG("RSR BUSCOU EM APOIO.TIPOPESSOA COM SGTIPOPESSOA[%s]", pclTipoPessoa->getSgTipoPessoa());


        /* Busca ids referentes as siglas de Tipo de Linha */
        pclTipoLinha->setSgTipoLinha(tDataProc.szSgTipoLinha);
        if(pclTipoLinha->buscaTipoLinha() == false) {
            sprintf(tDataProc.szMessageException, "Nao ha registro em apoio.tipolinha para SgTipoLinha[%s]", pclTipoLinha->getSgTipoLinha());
            throw PPExceptionPORT(ERRO_EXECUCAO, tDataProc.szMessageException);
        }
        ULOG("RSR BUSCOU EM APOIO.TIPOLINHA COM SGTIPOLINHA[%s]", pclTipoLinha->getSgTipoLinha());


        /* Busca ids referentes as siglas de Tipo de Cobranca */
        pclTipoEnderecoCobranca->setSgTipoEnderecoCobranca(TIPO_ENDERECO_COBRANCA);
        if(pclTipoEnderecoCobranca->buscaTipoEnderecoCobranca() == false ) {
            sprintf(tDataProc.szMessageException, "TipoEnderecoCobranca nao encontrado para SgTipoEnderecoCobranca[%s]", pclTipoEnderecoCobranca->getSgTipoEnderecoCobranca());
            throw PPExceptionPORT(ERRO_EXECUCAO, tDataProc.szMessageException);
        }
        ULOG("RSR BUSCOU EM APOIO.TIPOENDERECOCOBRANCA COM SGTIPOENDERECOCOBRANCA[%s]", pclTipoEnderecoCobranca->getSgTipoEnderecoCobranca());


        /* Busca ids referentes as siglas de Acao Portabilidade */
        pclAcaoPortabilidade->setDsAcaoPortabilidade("CADASTRAMENTO DE PORTIN");
        if(pclAcaoPortabilidade->buscaAcaoPortabilidade() == false ) {
            sprintf(tDataProc.szMessageException, "IdAcaoPortabilidade nao encontrado para DsAcaoPortabilidade[%s]", pclAcaoPortabilidade->getDsAcaoPortabilidade());
            throw PPExceptionPORT(ERRO_EXECUCAO, tDataProc.szMessageException);
        }
        ULOG("RSR BUSCOU EM APOIO.ACAOPORTABILIDADE COM DSACAOPORTABILIDADE[%s]", pclAcaoPortabilidade->getDsAcaoPortabilidade());


        if(bFlagNovoCadastro == true)
            pclContatoFuncionalidade->setCdFuncionalidade("PORTIN_CADASTRO");
        else
            pclContatoFuncionalidade->setCdFuncionalidade("PORTIN_ALTERACAO");

        /* Busca idContato referente a funcionalidade */
        if(pclContatoFuncionalidade->buscaContatoFuncionalidade() == false ) {
            sprintf(tDataProc.szMessageException, "IdContato nao encontrado para CdFuncionalidade[%s]", pclContatoFuncionalidade->getCdFuncionalidade());
            throw PPExceptionPORT(ERRO_EXECUCAO, tDataProc.szMessageException);
        }
        ULOG("RSR BUSCOU EM CONTATOADM.CONTATOFUNCIONALIDADE COM CDFUNCIONALIDADE[%s]", pclContatoFuncionalidade->getCdFuncionalidade());



        pTmp=getSubSystem(); ULOG("getSubSystem()[%s]", pTmp?pTmp:"...NULL...");
        if(pTmp == NULL)
            strcpy(tDataProc.szSubSystem, SG_FRONTOFFICCE);
        else {
            strcpy(tDataProc.szSubSystem, pTmp); XMLString::release(&pTmp); pTmp=NULL;
        }
        ULOG("tDataProc.szSubSystem[%s]", tDataProc.szSubSystem);
        pclSistemaOrigem->setSgSistemaOrigem(tDataProc.szSubSystem);
        if(pclSistemaOrigem->buscaSistemaOrigem() == false) {
            sprintf(tDataProc.szMessageException, "SistemaOrigem nao encontrado para SgSistemaOrigem[%s]", pclSistemaOrigem->getSgSistemaOrigem());
            throw PPExceptionPORT(ERRO_EXECUCAO, tDataProc.szMessageException);
        }
        ULOG("RSR BUSCOU EM APOIO.SISTEMAORIGEM COM SGSISTEMAORIGEM[%s]", pclSistemaOrigem->getSgSistemaOrigem());



        ULOG("iIdTipoPessoaCtrl(%d)pclTipoPessoa->getIdTipoPessoa()[%s]", iIdTipoPessoaCtrl, pclTipoPessoa->getIdTipoPessoa());
        // Controle para navegacao no xml -> Pessoa Fisica - 1, Pessoa Juridica - 2
        if(!strcmp(pclTipoPessoa->getIdTipoPessoa(), "1")) {
            iIdTipoPessoaCtrl=1; // direciona para PF
        }
        else if(!strcmp(pclTipoPessoa->getIdTipoPessoa(), "2")) {
                iIdTipoPessoaCtrl=2; // direciona para PJ
        }
        else {
            sprintf(tDataProc.szMessageException, "IdTipoPessoa invalido[%s] SgTipoPessoa[%s]", pclTipoPessoa->getIdTipoPessoa(), pclTipoPessoa->getSgTipoPessoa());
            throw PPExceptionPORT(ERRO_EXECUCAO, tDataProc.szMessageException);
        }

        /* verifica se a linha jah foi cadastrada */
        pclMisc->setIdTipoLinha(pclTipoLinha->getIdTipoLinha());
        pclMisc->setIdTipoPessoa(pclTipoPessoa->getIdTipoPessoa());
        pclMisc->setNrLinha(tDataProc.szNrLinha+2);
        memcpy(szAux, tDataProc.szNrLinha, 2); szAux[2]=0x00;
        pclMisc->setCdAreaRegistro(szAux);
        /* Busca em misc por IdTipoLinha + IdTipoPessoa + CdAreaRegistro + NrLinha */
        bAchou = pclMisc->buscaCliente();
        if((bFlagNovoCadastro == true && bAchou == true) || (bFlagNovoCadastro == false && bAchou == false)) {
            ULOG("RSR BUSCOU EM MISC COM IDTIPOLINHA[%s] IDTIPOPESSOA[%s] CDAREAREGISTRO[%s] NRLINHA[%s]", pclMisc->getIdTipoLinha(), pclMisc->getIdTipoPessoa(),pclMisc->getCdAreaRegistro(), pclMisc->getNrLinha());
            sprintf(tDataProc.szMessageException, "VIVONET-ERRO-1");
            ULOG( "*** ESTAS INFORMACOES JA EXISTEM NA BASE, ABANDONANDO PROCESSAMENTO." );
            throw PPExceptionPORT(ERRO_EXECUCAO, tDataProc.szMessageException);
        }

        /* Tratamento para gravacao em customer.pessoaportabilidade */
        if(strlen(tDataProc.szIdPessoa) > 0 && tDataProc.szIdPessoa[0] != '0') {

            pclPessoaPortabilidade->setInSincronizado(bFlagNovoCadastro ?"0":"2");
            pclPessoaPortabilidade->setIdTipoPessoa(pclTipoPessoa->getIdTipoPessoa());
            pclPessoaPortabilidade->setIdTipoLinha(pclTipoLinha->getIdTipoLinha());
            pclPessoaPortabilidade->setIdAcaoPortabilidade(pclAcaoPortabilidade->getIdAcaoPortabilidade());
            pclPessoaPortabilidade->setNrLinha(tDataProc.szNrLinha+2);
            memcpy(szAux, tDataProc.szNrLinha, 2); szAux[2]=0x00;
            pclPessoaPortabilidade->setCdAreaRegistro(szAux);
            pclPessoaPortabilidade->setIdUsuarioAlteracao(tDataProc.szUser);

            /* Busca IdPessoaDePara */
            pclPessoaDePara->setIdPessoa(tDataProc.szIdPessoa);
            if(pclPessoaDePara->buscaPessoaDePara() == false) {
                sprintf(tDataProc.szMessageException, "Nao ha registro em customer.pessoadepara para idpessoa[%s]", pclPessoaDePara->getIdPessoa());
                throw PPExceptionPORT(ERRO_EXECUCAO, tDataProc.szMessageException);
            }
            ULOG("RSR BUSCOU EM CUSTOMER.PESSOADEPARA COM IDPESSOA[%s]", pclPessoaDePara->getIdPessoa());

            pclPessoaPortabilidade->setIdPessoaDePara(pclPessoaDePara->getIdPessoaDePara());
            pclPessoaPortabilidade->inserePessoaPortabilidade();
            ULOG("RSR INSERIU EM CUSTOMER.PESSOAPORTABILIDADE COM IDPESSOADEPARA[%s]", pclPessoaPortabilidade->getIdPessoaDePara());
        }
        else {

            pclXmlGenRegContato->createTag("msg");
            pclXmlGenRegContato->addItem("ProxyOperacao", "getFidelizacao");
            pclXmlGenRegContato->addItem("idChamadaTelefonica", "1");
            pclXmlGenRegContato->addItem("idGrupoAbertura", "118");
            pclXmlGenRegContato->addItem("inResponsavelAbertura", "2");
            pclXmlGenRegContato->addItem("tpOperacao", "1");
            pclXmlGenRegContato->createTag("ProcedenciaVO");
            pclXmlGenRegContato->addItem("idProcedencia", "1");
            pclXmlGenRegContato->closeTag(); //ProcedenciaVO
            pclXmlGenRegContato->createTag("CanalVO");
            pclXmlGenRegContato->addItem("idCanal", "1");
            pclXmlGenRegContato->closeTag(); //CanalVO
            pclXmlGenRegContato->addItem("nrTelefone", "");

            pclRegistraContato->setIdUsuarioAlteracao(tDataProc.szUser);
            pclRegistraContato->setNrTelefone("");

            /* Verifica o tipo de pessoa  1 - Fisica  2 - Juridica */
            if(iIdTipoPessoaCtrl == 1) { // Pessoa Fisica
                if((DNPessoaFisica = walkDOM(dnode, "PF", 0))) {
                    ULOG("RSR PESSOA FISICA>>>");
                    GETTREE(tPessoaXML.szNmPessoa, DNPessoaFisica, "nmPessoa", 0, OBRIGATORIO, "nmPessoa");
                    memset(&tDesmembraNome, 0x00, sizeof(TDesmembraNome));
                    strcpy(tDesmembraNome.szNomeCompleto, tPessoaXML.szNmPessoa);
                    DesmembraNome(&tDesmembraNome);
                    strcpy(tPessoaXML.szNmNome, tDesmembraNome.szNomePrimeiro);
                    strcpy(tPessoaXML.szNmNomeMeio, tDesmembraNome.szNomeMeio);
                    strcpy(tPessoaXML.szNmSobreNome, tDesmembraNome.szNomeFim);
                    pclPessoa->setStruct(&tPessoaXML);
                    pclPessoa->setIdTipoPessoa(pclTipoPessoa->getIdTipoPessoa());
                    pclPessoa->setIdTipoCarteira("13");
                    pclPessoa->setInFalecimentoInformado("0");
                    pclPessoa->setIdUf("1");
                    pclPessoa->setIdProbInadimplencia("5");
                    pclPessoa->setIdChurnProbabilidade("5");
                    pclPessoa->setIdUsuarioAlteracao(tDataProc.szUser);
                    pclPessoa->setIdSistemaOrigem(pclSistemaOrigem->getIdSistemaOrigem());

                    pclPessoaPortabilidade->setInSincronizado(bFlagNovoCadastro ?"0":"2");
                    pclPessoaPortabilidade->setIdTipoPessoa(pclTipoPessoa->getIdTipoPessoa());
                    pclPessoaPortabilidade->setIdTipoLinha(pclTipoLinha->getIdTipoLinha());
                    pclPessoaPortabilidade->setIdAcaoPortabilidade(pclAcaoPortabilidade->getIdAcaoPortabilidade());
                    pclPessoaPortabilidade->setNrLinha(tDataProc.szNrLinha+2);
                    memcpy(szAux, tDataProc.szNrLinha, 2); szAux[2]=0x00;
                    pclPessoaPortabilidade->setCdAreaRegistro(szAux);
                    pclPessoaPortabilidade->setIdUsuarioAlteracao(tDataProc.szUser);

                    if(bFlagNovoCadastro == true) {
                        pclPessoa->inserePessoa();
                        ULOG("RSR INSERIU EM CUSTOMER.PESSOA COM IDPESSOA[%s]", pclPessoa->getIdPessoa());
                        pclPessoaDePara->setIdPessoa(pclPessoa->getIdPessoa());
                        pclPessoaDePara->setIdPessoaOrigem(pclPessoa->getIdPessoa());
                        pclPessoaDePara->setIdUsuarioAlteracao(tDataProc.szUser);
                        pclPessoaDePara->inserePessoaDePara();
                        ULOG("RSR INSERIU EM CUSTOMER.PESSOADEPARA COM IDPESSOADEPARA[%s]", pclPessoaDePara->getIdPessoaDePara());

                        pclPessoaPortabilidade->setIdPessoaDePara(pclPessoaDePara->getIdPessoaDePara());
                        pclPessoaPortabilidade->inserePessoaPortabilidade();
                        ULOG("RSR INSERIU EM CUSTOMER.PESSOAPORTABILIDADE COM IDPESSOADEPARA[%s]", pclPessoaPortabilidade->getIdPessoaDePara());
                    }
                    else {
                        pclMisc->setIdTipoLinha(pclTipoLinha->getIdTipoLinha());
                        pclMisc->setIdTipoPessoa(pclTipoPessoa->getIdTipoPessoa());
                        pclMisc->setNrLinha(tDataProc.szNrLinha+2);
                        memcpy(szAux, tDataProc.szNrLinha, 2); szAux[2]=0x00;
                        pclMisc->setCdAreaRegistro(szAux);
                        /* Busca em misc por IdTipoLinha + IdTipoPessoa + CdAreaRegistro + NrLinha */
                        if(pclMisc->buscaCliente() == false) {
                            sprintf(tDataProc.szMessageException, "Nao ha registro para IdTipoLinha[%s] IdTipoPessoa[%s] CdAreaRegistro[%s] NrLinha[%s]", pclMisc->getIdTipoLinha(), pclMisc->getIdTipoPessoa(), pclMisc->getCdAreaRegistro(), pclMisc->getNrLinha());
                            throw PPExceptionPORT(ERRO_EXECUCAO, tDataProc.szMessageException);
                        }
                        ULOG("RSR BUSCOU EM MISC COM IDTIPOLINHA[%s] IDTIPOPESSOA[%s] CDAREAREGISTRO[%s] NRLINHA[%s]", pclMisc->getIdTipoLinha(), pclMisc->getIdTipoPessoa(),pclMisc->getCdAreaRegistro(), pclMisc->getNrLinha());

                        TPessoaPortabilidade tPessoaPortabilidadeTmp = pclPessoaPortabilidade->tPessoaPortabilidade;
                        /* Busca em customer.pessoaportabilidade por NrLinha + CdAreaRegistro + IdTipoLinha + IdTipoPessoa */
                        if(pclPessoaPortabilidade->buscaPessoaPortabilidade(&tPessoaPortabilidadeTmp) == false) {
                            sprintf(tDataProc.szMessageException, "Nao ha registro em customer.pessoaportabilidade para IdTipoLinha[%s] IdTipoPessoa[%s] CdAreaRegistro[%s] NrLinha[%s]", pclPessoaPortabilidade->getIdTipoLinha(), pclPessoaPortabilidade->getIdTipoPessoa(), pclPessoaPortabilidade->getCdAreaRegistro(), pclPessoaPortabilidade->getNrLinha());
                            throw PPExceptionPORT(ERRO_EXECUCAO, tDataProc.szMessageException);
                        }
                        ULOG("RSR BUSCOU EM CUSTOMER.PESSOAPORTABILIDADE COM CDAREAREGISTRO[%s] NRLINHA[%s] IDTIPOLINHA[%s] IDTIPOPESSOA[%s]", pclPessoaPortabilidade->getCdAreaRegistro(), pclPessoaPortabilidade->getNrLinha(), pclPessoaPortabilidade->getIdTipoLinha(), pclPessoaPortabilidade->getIdTipoPessoa());
    
                        pclPessoaPortabilidade->setIdPessoaDePara(tPessoaPortabilidadeTmp.szIdPessoaDePara);
                        pclPessoaPortabilidade->setIdTipoLinha(pclTipoLinha->getIdTipoLinha());

                        /* Atualiza IdUsuarioAlteracao + DtUltimaAlteracao */
                        pclPessoaPortabilidade->atualizaPessoaPortabilidade();
                        ULOG("RSR ATUALIZOU EM CUSTOMER.PESSOAPORTABILIDADE COM IDPESSOADEPARA[%s]", pclPessoaPortabilidade->getIdPessoaDePara());
    
                        pclPessoa->setIdPessoa(pclMisc->getIdPessoa());
                        TPessoa tPessoaTmp = pclPessoa->tPessoa;
                        /* Busca em customer.pessoa por IdPessoa */
                        if(pclPessoa->buscaPessoa(&tPessoaTmp) == false) {
                            sprintf(tDataProc.szMessageException, "Nao ha registro em customer.pessoa para idpessoa[%s]", pclPessoa->getIdPessoa());
                            throw PPExceptionPORT(ERRO_EXECUCAO, tDataProc.szMessageException);
                        }
                        ULOG("RSR BUSCOU EM CUSTOMER.PESSOA COM IDPESSOA[%s]", pclPessoa->getIdPessoa());
    
    
                        pclPessoa->setIdPessoa(tPessoaTmp.szIdPessoa);
                        pclPessoa->atualizaPessoa();
                        ULOG("RSR ATUALIZOU EM CUSTOMER.PESSOA COM IDPESSOA[%s]", pclPessoa->getIdPessoa());
    
                        pclPessoaDePara->setIdPessoa(pclPessoa->getIdPessoa());
                        if(pclPessoaDePara->buscaPessoaDePara() == false) {
                            sprintf(tDataProc.szMessageException, "Nao ha registro em customer.pessoadepara para idpessoa[%s]", pclPessoaDePara->getIdPessoa());
                            throw PPExceptionPORT(ERRO_EXECUCAO, tDataProc.szMessageException);
                        }
                        ULOG("RSR BUSCOU EM CUSTOMER.PESSOADEPARA COM IDPESSOA[%s]", pclPessoaDePara->getIdPessoa());
                    }
    
                    //GETTREE(szAux, DNPessoaFisica, "sgSexo", 0, OBRIGATORIO, "sgSexo");
                    pclSexo->setSgSexo("NC");
                    if(pclSexo->buscaSexo() == false) {
                        sprintf(tDataProc.szMessageException, "Nao ha registro em apoio.sexo para sgSexo[%s]", pclSexo->getSgSexo());
                        throw PPExceptionPORT(ERRO_EXECUCAO, tDataProc.szMessageException);
                    }
                    ULOG("RSR BUSCOU EM APOIO.SEXO COM SGSEXO[%s]", pclSexo->getSgSexo());
                    pclPessoaFisica->setIdSexo(pclSexo->getIdSexo());
    
                    GETTREE(szAux, DNPessoaFisica, "dtNascimento", 0, NOBRIGATORIO, "dtNascimento");
                    //ConvDataFromJava(szAux);
					
					memset(&szAux,0,sizeof(szAux)); //zerar a variavel

                    pclPessoaFisica->setDtNascimento(szAux);
    
                    GETTREE(szAux, DNPessoaFisica, "sgEstadoCivil", 0, NOBRIGATORIO, "sgEstadoCivil");
                    if(strlen(szAux) > 0) {
                        pclEstadoCivil->setSgEstadoCivil(szAux);
                        if(pclEstadoCivil->buscaEstadoCivil() == false) {
                            sprintf(tDataProc.szMessageException, "Nao ha registro em apoio.estadocivil para sgEstadoCivil[%s]", pclEstadoCivil->getSgEstadoCivil());
                            throw PPExceptionPORT(ERRO_EXECUCAO, tDataProc.szMessageException);
                        }
                        ULOG("RSR BUSCOU EM APOIO.ESTADOCIVIL COM SGESTADOCIVIL[%s]", pclEstadoCivil->getSgEstadoCivil());
                        pclPessoaFisica->setIdEstadoCivil(pclEstadoCivil->getIdEstadoCivil());
                    }
                    else
                        pclPessoaFisica->setIdEstadoCivil("0");
    
                    pclPessoaFisica->setIdPais("1");
                    pclPessoaFisica->setIdTratamento("0");
                    pclPessoaFisica->setIdPessoa(pclPessoa->getIdPessoa());
                    pclPessoaFisica->setIdUsuarioAlteracao(tDataProc.szUser);
    
    
                    if(bFlagNovoCadastro == true) {
                        pclPessoaFisica->inserePessoaFisica();
                        ULOG("RSR INSERIU EM CUSTOMER.PESSOAFISICA COM IDPESSOA[%s]", pclPessoaFisica->getIdPessoa());
                    }
                    else {
                        TPessoaFisica tPessoaFisicaTmp = pclPessoaFisica->tPessoaFisica;
                        /* Busca por IdPessoa */
                        if(pclPessoaFisica->buscaPessoaFisica(&tPessoaFisicaTmp) == false) {
                            pclPessoaFisica->inserePessoaFisica();
                            ULOG("RSR INSERIU EM CUSTOMER.PESSOAFISICA COM IDPESSOA[%s]", pclPessoaFisica->getIdPessoa());
                        }
                        else {
                            ULOG("RSR BUSCOU EM CUSTOMER.PESSOAFISICA COM IDPESSOA[%s]", pclPessoaFisica->getIdPessoa());
                            pclPessoaFisica->atualizaPessoaFisica();
                            ULOG("RSR ATUALIZOU EM CUSTOMER.PESSOAFISICA COM IDPESSOA[%s]", pclPessoaFisica->getIdPessoa());
                        }
                    }
    
    
                    // Referente ao documento CPR
                    GETTREE(szAux, DNPessoaFisica, "nrCPR", 0, NOBRIGATORIO, "nrCPR");
                    if(strlen(szAux) > 0) {
                        ULOG("pclPessoa->getIdPessoa()[%s]", pclPessoa->getIdPessoa());
                        if((pclPessoaDocumento->apagouPessoaDocumento() == false)) {
    
                            pclPessoaDocumento->setIdPessoa(pclPessoa->getIdPessoa());
                            pclPessoaDocumento->setIdUsuarioAlteracao(tDataProc.szUser);
                            pclPessoaDocumento->setIdSistemaOrigem(pclSistemaOrigem->getIdSistemaOrigem());
                            pclPessoaDocumento->apagaPessoaDocumento();
                            ULOG("RSR APAGOU CUSTOMER.PESSOADOCUMENTO COM IDPESSOA[%s]", pclPessoaDocumento->getIdPessoa());
                        }
    
                        TrataNrDocumento(szAux);
                        pclDocumento->clearStruct();
                        pclDocumento->setNrDocumento(szAux);
                        pclDocumento->setIdUF("0");
    
                        pclTipoDocumento->clearStruct();
                        pclTipoDocumento->setSgClassificacao("CPR");
                        if(pclTipoDocumento->buscaTipoDocumento() == false) {
                            sprintf(tDataProc.szMessageException, "Nao ha registro em apoio.tipodocumento para sgClassificacao[%s]", pclTipoDocumento->getSgClassificacao());
                            throw PPExceptionPORT(ERRO_EXECUCAO, tDataProc.szMessageException);
                        }
                        ULOG("RSR BUSCOU EM APOIO.TIPODOCUMENTO COM SGCLASSIFICACAO[%s]", pclTipoDocumento->getSgClassificacao());
    
                        pclDocumento->setIdTipoDocumento(pclTipoDocumento->getIdTipoDocumento());
                        pclDocumento->setIdPais("1");
                        pclDocumento->setIdUsuarioAlteracao(tDataProc.szUser);
    
                        if(bFlagNovoCadastro == true) {
                            tDocumentoTmp = pclDocumento->tDocumento;
                            if (pclDocumento->buscaDocumentoChaveComposta(&tDocumentoTmp) == false ) {
                                pclDocumento->insereDocumento();
                                ULOG("RSR(1A) INSERIU EM CUSTOMER.DOCUMENTO COM IDDOCUMENTO[%s]", pclDocumento->getIdDocumento());
                                pclPessoaDocumento->setIdDocumento(pclDocumento->getIdDocumento());
                            }
                            else {
                                ULOG("CMG(1A) EXISTE EM CUSTOMER.DOCUMENTO COM NRDOCUMENTO[%s] IDTIPODOCUMENTO[%s] IDUF[%s] SGORGAOEXPEDIDOR[%s]", pclDocumento->getNrDocumento(), pclDocumento->getIdTipoDocumento(), pclDocumento->getIdUF(), pclDocumento->getSgOrgaoExpedidor());
                                pclPessoaDocumento->setIdDocumento(tDocumentoTmp.szIdDocumento);
                            }
                        }
                        else {
                            tDocumentoTmp = pclDocumento->tDocumento;
                            /* Busca Documento por NrDocumento, IdTipoDocumento, IdUf e SgOrgaoexpedidor */
                            if(pclDocumento->buscaDocumentoChaveComposta(&tDocumentoTmp) == false) {
                                pclDocumento->insereDocumento();
                                ULOG("RSR(1B) INSERIU EM CUSTOMER.DOCUMENTO COM IDDOCUMENTO[%s]", pclDocumento->getIdDocumento());
                                pclPessoaDocumento->setIdDocumento(pclDocumento->getIdDocumento());
                            }
                            else  {
                                ULOG("RSR(1B) BUSCOU EM CUSTOMER.DOCUMENTO COM NRDOCUMENTO[%s] IDTIPODOCUMENTO[%s] IDUF[%s] SGORGAOEXPEDIDOR[%s]", pclDocumento->getNrDocumento(), pclDocumento->getIdTipoDocumento(), pclDocumento->getIdUF(), pclDocumento->getSgOrgaoExpedidor());
                                pclPessoaDocumento->setIdDocumento(tDocumentoTmp.szIdDocumento);
                            }
                        }
    
                        if ( pclPessoaDocumento->buscaPessoaDocumento() == false ) {
                            pclPessoaDocumento->inserePessoaDocumento();
                            ULOG("RSR(1) INSERIU EM CUSTOMER.PESSOADOCUMENTO COM IDPESSOADOCUMENTO[%s]IDDOCUMENTO[%s]", pclPessoaDocumento->getIdPessoaDocumento(), pclPessoaDocumento->getIdDocumento());
                        }
                        else {
                            pclPessoaDocumento->atualizaPessoaDocumento();
                            ULOG("CMG(1) ATUALIZOU EM CUSTOMER.PESSOADOCUMENTO COM IDPESSOADOCUMENTO[%s]IDDOCUMENTO[%s]", pclPessoaDocumento->getIdPessoaDocumento(), pclPessoaDocumento->getIdDocumento());
                        }
                    }
    
    
                    if((DNTelefonePF = walkDOM(DNPessoaFisica, "Telefone", 0))) {
                        ULOG("RSR TELEFONE PESSOA FISICA>>>");
    
                        GETTREE(szAux, DNTelefonePF, "dsTipoTelefone", 0, NOBRIGATORIO, "dsTipoTelefone");
                        if(strlen(szAux) > 0)
                            pclTipoComunicacao->setSgTipoComunicacao(szAux);
                        else
                            pclTipoComunicacao->setSgTipoComunicacao("NC"); // Nao Classificado
                        
                        if(pclTipoComunicacao->buscaIdTipoComunicacao() == false) {
                            sprintf(tDataProc.szMessageException, "Nao ha registro em apoio.tipocomunicacao para sgTipoComunicacao[%s]", pclTipoComunicacao->getSgTipoComunicacao());
                            throw PPExceptionPORT(ERRO_EXECUCAO, tDataProc.szMessageException);
                        }
                        ULOG("RSR BUSCOU EM APOIO.TIPOCOMUNICACAO COM SGTIPOCOMUNICACAO[%s]", pclTipoComunicacao->getSgTipoComunicacao());
    
                        GETTREE(szAux, DNTelefonePF, "nrTelefone", 0, OBRIGATORIO, "nrTelefone");
                        pclPessoaComunicacao->setDsContato(szAux);
                        GETTREE(szAux, DNTelefonePF, "nmContato", 0, OBRIGATORIO, "nmContato");
                        pclPessoaComunicacao->setNmContato(szAux);
                        pclPessoaComunicacao->setIdTipoComunicacao(pclTipoComunicacao->getIdTipoComunicacao());
                        pclPessoaComunicacao->setIdPessoa(pclPessoa->getIdPessoa());
                        pclPessoaComunicacao->setIdUsuarioAlteracao(tDataProc.szUser);
                        pclPessoaComunicacao->setIdSistemaOrigem(pclSistemaOrigem->getIdSistemaOrigem());
    
    
                        if(bFlagNovoCadastro == true) {
                            pclPessoaComunicacao->inserePessoaComunicacao();
                            ULOG("RSR INSERIU EM CUSTOMER.PESSOACOMUNICACAO COM IDPESSOACOMUNICACAO[%s]", pclPessoaComunicacao->getIdPessoaComunicacao());
                        }
                        else {
                            /* Busca por IdSistemaOrigem + IdTipoComunicacao + IdPessoa */
                            TPessoaComunicacao tPessoaComunicacaoTmp = pclPessoaComunicacao->tPessoaComunicacao;
                            if(pclPessoaComunicacao->buscaPessoaComunicacao(&tPessoaComunicacaoTmp) == false) {
                                pclPessoaComunicacao->inserePessoaComunicacao();
                                ULOG("RSR INSERIU EM CUSTOMER.PESSOACOMUNICACAO COM IDPESSOACOMUNICACAO[%s]", pclPessoaComunicacao->getIdPessoaComunicacao());
                            }
                            else {
                                ULOG("RSR BUSCOU EM CUSTOMER.PESSOACOMUNICACAO COM IDSISTEMAORIGEM[%s] IDTIPOCOMUNICACAO[%s] IDPESSOA[%s]", pclPessoaComunicacao->getIdSistemaOrigem(), pclPessoaComunicacao->getIdTipoComunicacao(), pclPessoaComunicacao->getIdPessoa());
                                pclPessoaComunicacao->setIdPessoaComunicacao(tPessoaComunicacaoTmp.szIdPessoaComunicacao);
                                pclPessoaComunicacao->atualizaPessoaComunicacao();
                                ULOG("RSR ATUALIZOU EM CUSTOMER.PESSOACOMUNICACAO COM IDPESSOACOMUNICACAO[%s]", pclPessoaComunicacao->getIdPessoaComunicacao());
                            }
                        }
                        ULOG("RSR TELEFONE PESSOA FISICA<<<");
                    }
                    else
                        throw PPExceptionPORT(ERRO_EXECUCAO, "Noh Telefone PF inexistente!");
    
    
                    if((DNDocumento = walkDOM(DNPessoaFisica, "Documento", 0))) {
                        ULOG("RSR DOCUMENTO PESSOA FISICA>>>");
                        ULOG("pclPessoa->getIdPessoa()[%s]", pclPessoa->getIdPessoa());
                        if((pclPessoaDocumento->apagouPessoaDocumento() == false)) {
    
                            pclPessoaDocumento->setIdPessoa(pclPessoa->getIdPessoa());
                            pclPessoaDocumento->setIdUsuarioAlteracao(tDataProc.szUser);
                            pclPessoaDocumento->setIdSistemaOrigem(pclSistemaOrigem->getIdSistemaOrigem());
                            pclPessoaDocumento->apagaPessoaDocumento();
                            ULOG("RSR APAGOU CUSTOMER.PESSOADOCUMENTO COM IDPESSOA[%s]", pclPessoaDocumento->getIdPessoa());
                        }
    
                        // Referente ao documento do XML
                        GETTREE(szAux, DNDocumento, "dsTipoDocumento", 0, OBRIGATORIO, "dsTipoDocumento");
    
                        pclTipoDocumento->clearStruct();
                        pclTipoDocumento->setSgClassificacao(szAux);
                        if(pclTipoDocumento->buscaTipoDocumento() == false) {
                            sprintf(tDataProc.szMessageException, "Nao ha registro em apoio.tipodocumento para sgClassificacao[%s]", pclTipoDocumento->getSgClassificacao());
                            throw PPExceptionPORT(ERRO_EXECUCAO, tDataProc.szMessageException);
                        }
                        ULOG("RSR BUSCOU EM APOIO.TIPODOCUMENTO COM SGCLASSIFICACAO[%s]", pclTipoDocumento->getSgClassificacao());
    
                        /* valida os tipos de documento possiveis para PF */
                        if(strcmp(pclTipoDocumento->getSgClassificacao(), "RG") &&
                           strcmp(pclTipoDocumento->getSgClassificacao(), "CPF") &&
                           strcmp(pclTipoDocumento->getSgClassificacao(), "PAS")) {
                            sprintf(tDataProc.szMessageException, "Tipo de documento invalido para PF[%s]", pclTipoDocumento->getSgClassificacao());
                            throw PPExceptionPORT(ERRO_EXECUCAO, tDataProc.szMessageException);
                        }
    
                        pclDocumento->clearStruct();
                        pclDocumento->setIdTipoDocumento(pclTipoDocumento->getIdTipoDocumento());
                        GETTREE(szAux, DNDocumento, "nrDocumento", 0, OBRIGATORIO, "nrDocumento");
                        TrataNrDocumento(szAux);
                        pclDocumento->setNrDocumento(szAux);
    
                        if(!strcmp(pclTipoDocumento->getSgClassificacao(), "RG")) {
    
                            GETTREE(szAux, DNDocumento, "dtExpedicao", 0, OBRIGATORIO, "dtExpedicao");
                            ConvDataFromJava(szAux);
                            pclDocumento->setDtEmissao(szAux);
                            GETTREE(szAux, DNDocumento, "dsOrgaoEmissor", 0, OBRIGATORIO, "dsOrgaoEmissor");
                            pclDocumento->setSgOrgaoExpedidor(szAux);
                            GETTREE(szAux, DNDocumento, "sgUFDocumento", 0, OBRIGATORIO, "sgUFDocumento");
                            if(strlen(szAux) > 0) {
                                pclUF->clearStruct();
                                pclUF->setSgUF(szAux);
                                if(pclUF->buscaUF() == false) {
                                    sprintf(tDataProc.szMessageException, "Nao ha registro em apoio.uf para sgUF[%s]", pclUF->getSgUF());
                                    throw PPExceptionPORT(ERRO_EXECUCAO, tDataProc.szMessageException);
                                }
                                ULOG("RSR BUSCOU EM APOIO.UF COM SGUF[%s]", pclUF->getSgUF());
                                pclDocumento->setIdUF(pclUF->getIdUF());
                            }
                            else
                                pclDocumento->setIdUF("0");
    
                        }
                        else {
                            pclDocumento->setIdUF("0");
                        }
    
                        pclDocumento->setIdPais("1");
                        pclDocumento->setIdUsuarioAlteracao(tDataProc.szUser);
    
                        if(bFlagNovoCadastro == true) {
                            tDocumentoTmp = pclDocumento->tDocumento;
                            if (pclDocumento->buscaDocumentoChaveComposta(&tDocumentoTmp) == false ) {
                                pclDocumento->insereDocumento();
                                ULOG("RSR(2A) INSERIU EM CUSTOMER.DOCUMENTO COM IDDOCUMENTO[%s]", pclDocumento->getIdDocumento());
                                pclPessoaDocumento->setIdDocumento(pclDocumento->getIdDocumento());
                            }
                            else {
                                ULOG("CMG(2A) EXISTE EM CUSTOMER.DOCUMENTO COM NRDOCUMENTO[%s] IDTIPODOCUMENTO[%s] IDUF[%s] SGORGAOEXPEDIDOR[%s]", pclDocumento->getNrDocumento(), pclDocumento->getIdTipoDocumento(), pclDocumento->getIdUF(), pclDocumento->getSgOrgaoExpedidor());
                                pclPessoaDocumento->setIdDocumento(tDocumentoTmp.szIdDocumento);
                            }
                        }
                        else {
                            tDocumentoTmp = pclDocumento->tDocumento;
                            /* Busca Documento por NrDocumento, IdTipoDocumento, IdUf e SgOrgaoexpedidor */
                            if(pclDocumento->buscaDocumentoChaveComposta(&tDocumentoTmp) == false) {
                                pclDocumento->insereDocumento();
                                ULOG("RSR(2B) INSERIU EM CUSTOMER.DOCUMENTO COM IDDOCUMENTO[%s]", pclDocumento->getIdDocumento());
                                pclPessoaDocumento->setIdDocumento(pclDocumento->getIdDocumento());
                            }
                            else {
                                ULOG("RSR(2B) BUSCOU EM CUSTOMER.DOCUMENTO COM NRDOCUMENTO[%s] IDTIPODOCUMENTO[%s] IDUF[%s] SGORGAOEXPEDIDOR[%s]", pclDocumento->getNrDocumento(), pclDocumento->getIdTipoDocumento(), pclDocumento->getIdUF(), pclDocumento->getSgOrgaoExpedidor());
                                pclPessoaDocumento->setIdDocumento(tDocumentoTmp.szIdDocumento);
                            }
                        }
    
                        if ( pclPessoaDocumento->buscaPessoaDocumento() == false ) {
                            pclPessoaDocumento->inserePessoaDocumento();
                            ULOG("RSR(2) INSERIU EM CUSTOMER.PESSOADOCUMENTO COM IDPESSOADOCUMENTO[%s]IDDOCUMENTO[%s]", pclPessoaDocumento->getIdPessoaDocumento(), pclPessoaDocumento->getIdDocumento());
                        }
                        else {
                            pclPessoaDocumento->atualizaPessoaDocumento();
                            ULOG("CMG(2) ATUALIZOU EM CUSTOMER.PESSOADOCUMENTO COM IDPESSOADOCUMENTO[%s]IDDOCUMENTO[%s]", pclPessoaDocumento->getIdPessoaDocumento(), pclPessoaDocumento->getIdDocumento());
                        }
    
    
                        /* Referente a PessoaPortabilidadeHist */
                        pclPessoaPortabilidadeHist->setIdTipoDocumento(pclTipoDocumento->getIdTipoDocumento());
                        pclPessoaPortabilidadeHist->setNrDocumento(pclDocumento->getNrDocumento());
    
    
                        ULOG("RSR DOCUMENTO PESSOA FISICA<<<");
                    }
                    else
                        throw PPExceptionPORT(ERRO_EXECUCAO, "Noh Documento PF inexistente!");
    
    
                    if((DNDadosEnderecoPF = walkDOM(DNPessoaFisica, "DadosEndereco", 0))) {
                        ULOG("RSR DADOSENDERECO PESSOA FISICA>>>");
    
                        GETTREE(szAux, DNDadosEnderecoPF, "sgTipoEndereco", 0, OBRIGATORIO, "sgTipoEndereco");
                        pclTipoEndereco->setSgTipoEndereco(szAux);
                        if(pclTipoEndereco->buscaTipoEndereco() == false) {
                            sprintf(tDataProc.szMessageException, "Nao ha registro em apoio.tipoendereco para sgTipoEndereco[%s]", pclTipoEndereco->getSgTipoEndereco());
                            throw PPExceptionPORT(ERRO_EXECUCAO, tDataProc.szMessageException);
                        }
                        ULOG("RSR BUSCOU EM APOIO.TIPOENDERECO COM SGTIPOENDERECO[%s]", pclTipoEndereco->getSgTipoEndereco());
    
                        strcpy(tPessoaEnderecoXML.szIdTipoEndereco, pclTipoEndereco->getIdTipoEndereco());
                        GETTREE(tPessoaEnderecoXML.szNmTipoLogradouro, DNDadosEnderecoPF, "nmTipoLogradouro", 0, OBRIGATORIO, "nmTipoLogradouro");
                        GETTREE(tPessoaEnderecoXML.szNmTituloLogradouro, DNDadosEnderecoPF, "nmTitLogradouro", 0, NOBRIGATORIO, "");
                        GETTREE(tPessoaEnderecoXML.szNrCep, DNDadosEnderecoPF, "nrCEP", 0, OBRIGATORIO, "nrCEP");
                        GETTREE(tPessoaEnderecoXML.szNmLogradouro, DNDadosEnderecoPF, "nmLogradouro", 0, OBRIGATORIO, "nmLogradouro");
                        GETTREE(tPessoaEnderecoXML.szNrEndereco, DNDadosEnderecoPF, "nrLogradouro", 0, OBRIGATORIO, "nrLogradouro");
                        GETTREE(tPessoaEnderecoXML.szDsEnderecoComplemento, DNDadosEnderecoPF, "nmComplemento", 0, NOBRIGATORIO, "");
                        GETTREE(tPessoaEnderecoXML.szNmBairro, DNDadosEnderecoPF, "nmBairro", 0, OBRIGATORIO, "nmBairro");
                        GETTREE(tPessoaEnderecoXML.szNmMunicipio, DNDadosEnderecoPF, "nmMunicipio", 0, OBRIGATORIO, "nmMunicipio");
    
                        GETTREE(szAux, DNDadosEnderecoPF, "sgUF", 0, OBRIGATORIO, "sgUF");
                        pclUF->clearStruct();
                        pclUF->setSgUF(szAux);
                        if(pclUF->buscaUF() == false) {
                            sprintf(tDataProc.szMessageException, "Nao ha registro em apoio.uf para sgUF[%s]", pclUF->getSgUF());
                            throw PPExceptionPORT(ERRO_EXECUCAO, tDataProc.szMessageException);
                        }
                        ULOG("RSR BUSCOU EM APOIO.UF COM SGUF[%s]", pclUF->getSgUF());
                        strcpy(tPessoaEnderecoXML.szIdUF, pclUF->getIdUF());
    
                        pclPessoaEndereco->setStruct(&tPessoaEnderecoXML);
                        pclPessoaEndereco->setIdPessoa(pclPessoa->getIdPessoa());
                        pclPessoaEndereco->setIdUsuarioAlteracao(tDataProc.szUser);
                        pclPessoaEndereco->setIdSistemaOrigem(pclSistemaOrigem->getIdSistemaOrigem());
                        pclPessoaEndereco->setIdPais("1");
    
    
                        if(bFlagNovoCadastro == true) {
                            pclPessoaEndereco->inserePessoaEndereco();
                            ULOG("RSR INSERIU EM CUSTOMER.PESSOAENDERECO COM IDPESSOAENDERECO[%s]", pclPessoaEndereco->getIdPessoaEndereco());
                        }
                        else {
                            /* Busca por IdPessoa + IdSistemaOrigem */
                            TPessoaEndereco tPessoaEnderecoTmp = pclPessoaEndereco->tPessoaEndereco;
                            if(pclPessoaEndereco->buscaPessoaEndereco(&tPessoaEnderecoTmp) == false) {
                                pclPessoaEndereco->inserePessoaEndereco();
                                ULOG("RSR INSERIU EM CUSTOMER.PESSOAENDERECO COM IDPESSOAENDERECO[%s]", pclPessoaEndereco->getIdPessoaEndereco());
                            }
                            else {
                                ULOG("RSR BUSCOU EM CUSTOMER.PESSOAENDERECO COM IDPESSOA[%s] IDSISTEMAORIGEM[%s]", pclPessoaEndereco->getIdPessoa(), pclPessoaEndereco->getIdSistemaOrigem());
                                pclPessoaEndereco->setIdPessoaEndereco(tPessoaEnderecoTmp.szIdPessoaEndereco);
                                pclPessoaEndereco->atualizaPessoaEndereco();
                                ULOG("RSR ATUALIZOU EM CUSTOMER.PESSOAENDERECO COM IDPESSOAENDERECO[%s]", pclPessoaEndereco->getIdPessoaEndereco());
                            }
                        }
                        ULOG("RSR DADOSENDERECO PESSOA FISICA<<<");
                    } //if((DNDadosEnderecoPF = walkDOM(DNPessoaFisica, "DadosEndereco", 0)))
                    else {
                        ULOG("RSR DADOSENDERECO PESSOA FISICA NAO ENVIADO");
                    }
    
                    ULOG("RSR PESSOA FISICA<<<");
                }
                else
                    throw PPExceptionPORT(ERRO_EXECUCAO, "Noh PF inexistente!");
    
    
            } //if(iIdTipoPessoaCtrl == 1) // Pessoa Fisica
            else if(iIdTipoPessoaCtrl == 2) // Pessoa Juridica
            {
                if((DNPessoaJuridica = walkDOM(dnode, "PJ", 0))) {
                    ULOG("RSR PESSOA JURIDICA>>>");
                    GETTREE(tPessoaXML.szNmPessoa, DNPessoaJuridica, "nmRazaoSocial", 0, OBRIGATORIO, "nmRazaoSocial");
                    memset(&tDesmembraNome, 0x00, sizeof(TDesmembraNome));
                    strcpy(tDesmembraNome.szNomeCompleto, tPessoaXML.szNmPessoa); ULOG("tDesmembraNome.szNomeCompleto[%s]", tDesmembraNome.szNomeCompleto);
                    DesmembraNome(&tDesmembraNome); ULOG("tDesmembraNome.szNomeCompleto[%s]", tDesmembraNome.szNomeCompleto); ULOG("tDesmembraNome.szNomePrimeiro[%s]", tDesmembraNome.szNomePrimeiro);ULOG("tDesmembraNome.szNomeMeio[%s]", tDesmembraNome.szNomeMeio);ULOG("tDesmembraNome.szNomeFim[%s]", tDesmembraNome.szNomeFim);
                    strcpy(tPessoaXML.szNmNome, tDesmembraNome.szNomePrimeiro);
                    strcpy(tPessoaXML.szNmNomeMeio, tDesmembraNome.szNomeMeio);
                    strcpy(tPessoaXML.szNmSobreNome, tDesmembraNome.szNomeFim);
                    pclPessoa->setStruct(&tPessoaXML);
    
                    GETTREE(szAux, DNPessoaJuridica, "sgClassEmpresa", 0, NOBRIGATORIO, "sgClassEmpresa");
                    if(strlen(szAux) > 0) {
                        pclTipoCarteira->setSgTipoCarteira(szAux);
                        if(pclTipoCarteira->buscaTipoCarteira() == false) {
                            sprintf(tDataProc.szMessageException, "Nao ha registro em apoio.tipocarteira para sgTipoCarteira[%s]", pclTipoCarteira->getSgTipoCarteira());
                            throw PPExceptionPORT(ERRO_EXECUCAO, tDataProc.szMessageException);
                        }
                        ULOG("RSR BUSCOU EM APOIO.TIPOCARTEIRA COM SGTIPOCARTEIRA[%s]", pclTipoCarteira->getSgTipoCarteira());
                        pclPessoa->setIdTipoCarteira(pclTipoCarteira->getIdTipoCarteira());
                    }
                    else
                        pclPessoa->setIdTipoCarteira("0"); // Tipo Carteira = Nao Classificada
    
                    pclPessoa->setIdTipoPessoa(pclTipoPessoa->getIdTipoPessoa());
                    pclPessoa->setInFalecimentoInformado("0");
                    pclPessoa->setIdUf("1");
                    pclPessoa->setIdProbInadimplencia("5");
                    pclPessoa->setIdChurnProbabilidade("5");
                    pclPessoa->setIdUsuarioAlteracao(tDataProc.szUser);
                    pclPessoa->setIdSistemaOrigem(pclSistemaOrigem->getIdSistemaOrigem());

                    pclPessoaPortabilidade->setInSincronizado(bFlagNovoCadastro ?"0":"2");
                    pclPessoaPortabilidade->setIdTipoPessoa(pclTipoPessoa->getIdTipoPessoa());
                    pclPessoaPortabilidade->setIdTipoLinha(pclTipoLinha->getIdTipoLinha());
                    pclPessoaPortabilidade->setIdAcaoPortabilidade(pclAcaoPortabilidade->getIdAcaoPortabilidade());
                    pclPessoaPortabilidade->setNrLinha(tDataProc.szNrLinha+2);
                    memcpy(szAux, tDataProc.szNrLinha, 2); szAux[2]=0x00;
                    pclPessoaPortabilidade->setCdAreaRegistro(szAux);
                    pclPessoaPortabilidade->setIdUsuarioAlteracao(tDataProc.szUser);
    
                    if(bFlagNovoCadastro == true) {
                        pclPessoa->inserePessoa();
                        ULOG("RSR INSERIU EM CUSTOMER.PESSOA COM IDPESSOA[%s]", pclPessoa->getIdPessoa());
                        pclPessoaDePara->setIdPessoa(pclPessoa->getIdPessoa());
                        pclPessoaDePara->setIdPessoaOrigem(pclPessoa->getIdPessoa());
                        pclPessoaDePara->setIdUsuarioAlteracao(tDataProc.szUser);
                        pclPessoaDePara->inserePessoaDePara();
                        ULOG("RSR INSERIU EM CUSTOMER.PESSOADEPARA COM IDPESSOADEPARA[%s]", pclPessoaDePara->getIdPessoaDePara());
    
                        pclPessoaPortabilidade->setIdPessoaDePara(pclPessoaDePara->getIdPessoaDePara());
                        pclPessoaPortabilidade->inserePessoaPortabilidade();
                        ULOG("RSR INSERIU EM CUSTOMER.PESSOAPORTABILIDADE COM IDPESSOADEPARA[%s]", pclPessoaPortabilidade->getIdPessoaDePara());
                    }
                    else {
                        pclMisc->setIdTipoLinha(pclTipoLinha->getIdTipoLinha());
                        pclMisc->setIdTipoPessoa(pclTipoPessoa->getIdTipoPessoa());
                        pclMisc->setNrLinha(tDataProc.szNrLinha+2);
                        memcpy(szAux, tDataProc.szNrLinha, 2); szAux[2]=0x00;
                        pclMisc->setCdAreaRegistro(szAux);
                        /* Busca em misc por IdTipoLinha + IdTipoPessoa + CdAreaRegistro + NrLinha */
                        if(pclMisc->buscaCliente() == false) {
                            sprintf(tDataProc.szMessageException, "Nao ha registro para IdTipoLinha[%s] IdTipoPessoa[%s] CdAreaRegistro[%s] NrLinha[%s]", pclMisc->getIdTipoLinha(), pclMisc->getIdTipoPessoa(), pclMisc->getCdAreaRegistro(), pclMisc->getNrLinha());
                            throw PPExceptionPORT(ERRO_EXECUCAO, tDataProc.szMessageException);
                        }
                        ULOG("RSR BUSCOU EM MISC COM IDTIPOLINHA[%s] IDTIPOPESSOA[%s] CDAREAREGISTRO[%s] NRLINHA[%s]", pclMisc->getIdTipoLinha(), pclMisc->getIdTipoPessoa(),pclMisc->getCdAreaRegistro(), pclMisc->getNrLinha());

                        TPessoaPortabilidade tPessoaPortabilidadeTmp = pclPessoaPortabilidade->tPessoaPortabilidade;
                        /* Busca em customer.pessoaportabilidade por NrLinha + CdAreaRegistro + IdTipoLinha + IdTipoPessoa */
                        if(pclPessoaPortabilidade->buscaPessoaPortabilidade(&tPessoaPortabilidadeTmp) == false) {
                            sprintf(tDataProc.szMessageException, "Nao ha registro em customer.pessoaportabilidade para IdTipoLinha[%s] IdTipoPessoa[%s] CdAreaRegistro[%s] NrLinha[%s]", pclPessoaPortabilidade->getIdTipoLinha(), pclPessoaPortabilidade->getIdTipoPessoa(), pclPessoaPortabilidade->getCdAreaRegistro(), pclPessoaPortabilidade->getNrLinha());
                            throw PPExceptionPORT(ERRO_EXECUCAO, tDataProc.szMessageException);
                        }
                        ULOG("RSR BUSCOU EM CUSTOMER.PESSOAPORTABILIDADE COM CDAREAREGISTRO[%s] NRLINHA[%s] IDTIPOLINHA[%s] IDTIPOPESSOA[%s]", pclPessoaPortabilidade->getCdAreaRegistro(), pclPessoaPortabilidade->getNrLinha(), pclPessoaPortabilidade->getIdTipoLinha(), pclPessoaPortabilidade->getIdTipoPessoa());
    
                        pclPessoaPortabilidade->setIdPessoaDePara(tPessoaPortabilidadeTmp.szIdPessoaDePara);
                        pclPessoaPortabilidade->setIdTipoLinha(pclTipoLinha->getIdTipoLinha());
                        /* Atualiza IdUsuarioAlteracao + DtUltimaAlteracao */
                        pclPessoaPortabilidade->atualizaPessoaPortabilidade();
                        ULOG("RSR ATUALIZOU EM CUSTOMER.PESSOAPORTABILIDADE COM IDPESSOADEPARA[%s]", pclPessoaPortabilidade->getIdPessoaDePara());
    
                        pclPessoa->setIdPessoa(pclMisc->getIdPessoa());
                        TPessoa tPessoaTmp = pclPessoa->tPessoa;
                        /* Busca em customer.pessoa por IdPessoa */
                        if(pclPessoa->buscaPessoa(&tPessoaTmp) == false) {
                            sprintf(tDataProc.szMessageException, "Nao ha registro em customer.pessoa para idpessoa[%s]", pclPessoa->getIdPessoa());
                            throw PPExceptionPORT(ERRO_EXECUCAO, tDataProc.szMessageException);
                        }
                        ULOG("RSR BUSCOU EM CUSTOMER.PESSOA COM IDPESSOA[%s]", pclPessoa->getIdPessoa());

                        pclPessoa->setIdPessoa(tPessoaTmp.szIdPessoa);
                        pclPessoa->atualizaPessoa();
                        ULOG("RSR ATUALIZOU EM CUSTOMER.PESSOA COM IDPESSOA[%s]", pclPessoa->getIdPessoa());

                        pclPessoaDePara->setIdPessoa(pclPessoa->getIdPessoa());
                        if(pclPessoaDePara->buscaPessoaDePara() == false) {
                            sprintf(tDataProc.szMessageException, "Nao ha registro em customer.pessoadepara para idpessoa[%s]", pclPessoaDePara->getIdPessoa());
                            throw PPExceptionPORT(ERRO_EXECUCAO, tDataProc.szMessageException);
                        }
                        ULOG("RSR BUSCOU EM CUSTOMER.PESSOADEPARA COM IDPESSOA[%s]", pclPessoaDePara->getIdPessoa());

                        // Insere o registro que irá gerar a solicitação de alteração de dados junto ao NGIN
                        pclPessoaPortabilidade->inserePessoaPortabilidade();
                        ULOG("RSR INSERIU EM CUSTOMER.PESSOAPORTABILIDADE COM IDPESSOADEPARA[%s]", pclPessoaPortabilidade->getIdPessoaDePara());
                    }

                    pclXmlGenRegContato->createTag("ArvoreAtendimentoVO");
                    pclXmlGenRegContato->addItem("idContato", pclContatoFuncionalidade->getIdContato());
                    pclXmlGenRegContato->createTag("CarterizacaoVO");
                    pclXmlGenRegContato->addItem("idTipoCarteira", (strlen(pclPessoa->getIdTipoCarteira()) == 0)?"13":pclPessoa->getIdTipoCarteira());
                    pclXmlGenRegContato->closeTag(); //CarterizacaoVO
                    pclXmlGenRegContato->createTag("SegmentacaoVO");
                    pclXmlGenRegContato->addItem("idSegmentacao", "11");
                    pclXmlGenRegContato->closeTag(); //SegmentacaoVO
                    pclXmlGenRegContato->closeTag(); //ArvoreAtendimentoVO

                    GETTREE(tPessoaJuridicaXML.szNmPessoaFilial, DNPessoaJuridica, "nmRazaoSocial", 0, OBRIGATORIO, "nmRazaoSocial");
                    GETTREE(tPessoaJuridicaXML.szNmFantasia, DNPessoaJuridica, "nmFantasia", 0, OBRIGATORIO, "nmFantasia");
                    GETTREE(tPessoaJuridicaXML.szDtFundacao, DNPessoaJuridica, "dtFundacao", 0, NOBRIGATORIO, "dtFundacao");
                    if(strlen(tPessoaJuridicaXML.szDtFundacao) > 0) {
                        ConvDataFromJava(tPessoaJuridicaXML.szDtFundacao);
                    }
                    pclPessoaJuridica->setStruct(&tPessoaJuridicaXML);

                    GETTREE(szAux, DNPessoaJuridica, "sgClassTributaria", 0, NOBRIGATORIO, "sgClassTributaria");
                    if(strlen(szAux) > 0) {
                        pclCFOP->setSgCFOP(szAux);
                        if(pclCFOP->buscaCFOP() == false) {
                            sprintf(tDataProc.szMessageException, "Nao ha registro em apoio.cfop para sgcfop[%s]", pclCFOP->getSgCFOP());
                            throw PPExceptionPORT(ERRO_EXECUCAO, tDataProc.szMessageException);
                        }
                        ULOG("RSR BUSCOU EM APOIO.CFOP COM SGCFOP[%s]", pclCFOP->getSgCFOP());
                        pclPessoaJuridica->setIdCFOP(pclCFOP->getIdCFOP());
                    }
                    else
                        pclPessoaJuridica->setIdCFOP("0"); // Classificacao Tributaria = Nao Classificada
    
    
                    pclPessoaJuridica->setIdUsuarioAlteracao(tDataProc.szUser);
                    pclPessoaJuridica->setIdPessoa(pclPessoa->getIdPessoa());
    
    
                    if(bFlagNovoCadastro == true) {
                        pclPessoaJuridica->inserePessoaJuridica();
                        ULOG("RSR INSERIU EM CUSTOMER.PESSOAJURIDICA COM IDPESSOA[%s]", pclPessoaJuridica->getIdPessoa());
                    }
                    else {
                        TPessoaJuridica tPessoaJuridicaTmp = pclPessoaJuridica->tPessoaJuridica;
                        /* Busca por idPessoa */
                        if(pclPessoaJuridica->buscaPessoaJuridica(&tPessoaJuridicaTmp) == true) {
                            pclPessoaJuridica->atualizaPessoaJuridica();
                            ULOG("RSR BUSCOU EM CUSTOMER.PESSOAJURIDICA COM IDPESSOA[%s]", pclPessoaJuridica->getIdPessoa());
                        }
                        else {
                            pclPessoaJuridica->inserePessoaJuridica();
                            ULOG("RSR INSERIU EM CUSTOMER.PESSOAJURIDICA COM IDPESSOA[%s]", pclPessoaJuridica->getIdPessoa());
                        }
                    }

                    // pclPessoaSegmentacao->setIdPessoaDePara(pclPessoaDePara->getIdPessoaDePara());
                    // /* Busca por IdPessoaDePara  (alteracao ou inclusao tem o mesmo tratamento nesse ponto) */
                    // if(pclPessoaSegmentacao->buscaPessoaSegmentacao() == false) {
                    //     pclPessoaSegmentacaoHistorico->setIdSegmentacao("11");
                    //     pclPessoaSegmentacaoHistorico->setIdPessoaDePara(pclPessoaDePara->getIdPessoaDePara());
                    //     pclPessoaSegmentacaoHistorico->setIdUsuarioAlteracao(tDataProc.szUser);
                    //     pclPessoaSegmentacaoHistorico->inserePessoaSegmentacaoHistorico();
                    //     ULOG("RSR INSERIU EM CUSTOMER.PESSOASEGMENTACAOHISTORICO COM IDPESSOADEPARA[%s]", pclPessoaSegmentacaoHistorico->getIdPessoaDePara());
                    //
                    //     pclPessoaSegmentacao->setIdPessoaDePara(pclPessoaDePara->getIdPessoaDePara());
                    //     pclPessoaSegmentacao->setIdPessoaSegmentacao(pclPessoaSegmentacaoHistorico->getIdPessoaSegmentacao());
                    //     pclPessoaSegmentacao->setIdUsuarioAlteracao(tDataProc.szUser);
                    //     pclPessoaSegmentacao->inserePessoaSegmentacao();
                    //     ULOG("RSR INSERIU EM CUSTOMER.PESSOASEGMENTACAO COM IDPESSOADEPARA[%s]", pclPessoaSegmentacao->getIdPessoaDePara());
                    // }
                    // else {
                    //     ULOG("RSR BUSCOU EM CUSTOMER.PESSOASEGMENTACAO COM IDPESSOADEPARA[%s]", pclPessoaSegmentacao->getIdPessoaDePara());
                    // }
    
    
                    if((DNTelefonePJ = walkDOM(DNPessoaJuridica, "Telefone", 0))) {
                        ULOG("RSR TELEFONE PESSOA JURIDICA>>>");
    
                        GETTREE(szAux, DNTelefonePJ, "dsTipoTelefone", 0, NOBRIGATORIO, "dsTipoTelefone");
                        if(strlen(szAux) > 0)
                            pclTipoComunicacao->setSgTipoComunicacao(szAux);
                        else
                            pclTipoComunicacao->setSgTipoComunicacao("NC"); // Nao Classificado
                        
                        if(pclTipoComunicacao->buscaIdTipoComunicacao() == false) {
                            sprintf(tDataProc.szMessageException, "Nao ha registro em apoio.tipocomunicacao para sgTipoComunicacao[%s]", pclTipoComunicacao->getSgTipoComunicacao());
                            throw PPExceptionPORT(ERRO_EXECUCAO, tDataProc.szMessageException);
                        }
                        ULOG("RSR BUSCOU EM APOIO.TIPOCOMUNICACAO COM SGTIPOCOMUNICACAO[%s]", pclTipoComunicacao->getSgTipoComunicacao());
    
                        GETTREE(szAux, DNTelefonePJ, "nrTelefone", 0, OBRIGATORIO, "nrTelefone");
                        pclPessoaComunicacao->setDsContato(szAux);
                        GETTREE(szAux, DNTelefonePJ, "nmContato", 0, OBRIGATORIO, "nmContato");
                        pclPessoaComunicacao->setNmContato(szAux);
                        pclPessoaComunicacao->setIdTipoComunicacao(pclTipoComunicacao->getIdTipoComunicacao());
                        pclPessoaComunicacao->setIdPessoa(pclPessoa->getIdPessoa());
                        pclPessoaComunicacao->setIdUsuarioAlteracao(tDataProc.szUser);
                        pclPessoaComunicacao->setIdSistemaOrigem(pclSistemaOrigem->getIdSistemaOrigem());

                        if(bFlagNovoCadastro == true) {
                            pclPessoaComunicacao->inserePessoaComunicacao();
                            ULOG("RSR INSERIU EM CUSTOMER.PESSOACOMUNICACAO COM IDPESSOACOMUNICACAO[%s]", pclPessoaComunicacao->getIdPessoaComunicacao());
                        }
                        else {
                            /* Busca por IdSistemaOrigem + IdTipoComunicacao + IdPessoa */
                            TPessoaComunicacao tPessoaComunicacaoTmp = pclPessoaComunicacao->tPessoaComunicacao;
                            if(pclPessoaComunicacao->buscaPessoaComunicacao(&tPessoaComunicacaoTmp) == false) {
                                pclPessoaComunicacao->inserePessoaComunicacao();
                                ULOG("RSR INSERIU EM CUSTOMER.PESSOACOMUNICACAO COM IDPESSOACOMUNICACAO[%s]", pclPessoaComunicacao->getIdPessoaComunicacao());
                            }
                            else {
                                ULOG("RSR BUSCOU EM CUSTOMER.PESSOACOMUNICACAO COM IDSISTEMAORIGEM[%s] IDTIPOCOMUNICACAO[%s] IDPESSOA[%s]", pclPessoaComunicacao->getIdSistemaOrigem(), pclPessoaComunicacao->getIdTipoComunicacao(), pclPessoaComunicacao->getIdPessoa());
                                pclPessoaComunicacao->setIdPessoaComunicacao(tPessoaComunicacaoTmp.szIdPessoaComunicacao);
                                pclPessoaComunicacao->atualizaPessoaComunicacao();
                                ULOG("RSR ATUALIZOU EM CUSTOMER.PESSOACOMUNICACAO COM IDPESSOACOMUNICACAO[%s]", pclPessoaComunicacao->getIdPessoaComunicacao());
                            }
                        }

                        pclXmlGenRegContato->addItem("nmContato", pclPessoaComunicacao->getNmContato());
    
                        ULOG("RSR TELEFONE PESSOA JURIDICA<<<");
                    }
                    else
                        throw PPExceptionPORT(ERRO_EXECUCAO, "Noh Telefone PJ inexistente!");

                    ULOG("RSR \"DOCUMENTO\" PESSOA JURIDICA>>>");
                    ULOG("pclPessoa->getIdPessoa()[%s]", pclPessoa->getIdPessoa());
                    if((pclPessoaDocumento->apagouPessoaDocumento() == false)) {
    
                        pclPessoaDocumento->setIdPessoa(pclPessoa->getIdPessoa());
                        pclPessoaDocumento->setIdUsuarioAlteracao(tDataProc.szUser);
                        pclPessoaDocumento->setIdSistemaOrigem(pclSistemaOrigem->getIdSistemaOrigem());
                        pclPessoaDocumento->apagaPessoaDocumento();
                        ULOG("RSR APAGOU CUSTOMER.PESSOADOCUMENTO COM IDPESSOA[%s]", pclPessoaDocumento->getIdPessoa());
                    }

                    // Referente ao documento CNPJ
                    GETTREE(szAux, DNPessoaJuridica, "nrCNPJ", 0, OBRIGATORIO, "nrCNPJ");
                    TrataNrDocumento(szAux);
                    pclDocumento->clearStruct();
                    pclDocumento->setNrDocumento(szAux);
                    pclDocumento->setIdUF("0");

                    pclTipoDocumento->clearStruct();
                    pclTipoDocumento->setSgClassificacao("CNPJ");
                    if(pclTipoDocumento->buscaTipoDocumento() == false) {
                        sprintf(tDataProc.szMessageException, "Nao ha registro em apoio.tipodocumento para sgClassificacao[%s]", pclTipoDocumento->getSgClassificacao());
                        throw PPExceptionPORT(ERRO_EXECUCAO, tDataProc.szMessageException);
                    }
                    ULOG("RSR BUSCOU EM APOIO.TIPODOCUMENTO COM SGCLASSIFICACAO[%s]", pclTipoDocumento->getSgClassificacao());

                    pclDocumento->setIdTipoDocumento(pclTipoDocumento->getIdTipoDocumento());
                    pclDocumento->setIdPais("1");
                    pclDocumento->setIdUsuarioAlteracao(tDataProc.szUser);

                    if(bFlagNovoCadastro == true) {
                        tDocumentoTmp = pclDocumento->tDocumento;
                        if (pclDocumento->buscaDocumentoChaveComposta(&tDocumentoTmp) == false ) {
                            pclDocumento->insereDocumento();
                            ULOG("RSR(3A) INSERIU EM CUSTOMER.DOCUMENTO COM IDDOCUMENTO[%s]", pclDocumento->getIdDocumento());
                            pclPessoaDocumento->setIdDocumento(pclDocumento->getIdDocumento());
                        }
                        else {
                            ULOG("CMG(3A) EXISTE EM CUSTOMER.DOCUMENTO COM NRDOCUMENTO[%s] IDTIPODOCUMENTO[%s] IDUF[%s] SGORGAOEXPEDIDOR[%s]", pclDocumento->getNrDocumento(), pclDocumento->getIdTipoDocumento(), pclDocumento->getIdUF(), pclDocumento->getSgOrgaoExpedidor());
                            pclPessoaDocumento->setIdDocumento(tDocumentoTmp.szIdDocumento);
                        }
                    }
                    else {
                        tDocumentoTmp = pclDocumento->tDocumento;
                        /* Busca Documento por NrDocumento, IdTipoDocumento, IdUf e SgOrgaoexpedidor */
                        if(pclDocumento->buscaDocumentoChaveComposta(&tDocumentoTmp) == false) {
                            pclDocumento->insereDocumento();
                            ULOG("RSR(3B) INSERIU EM CUSTOMER.DOCUMENTO COM IDDOCUMENTO[%s]", pclDocumento->getIdDocumento());
                            pclPessoaDocumento->setIdDocumento(pclDocumento->getIdDocumento());
                        }
                        else  {
                            ULOG("RSR(3B) BUSCOU EM CUSTOMER.DOCUMENTO COM NRDOCUMENTO[%s] IDTIPODOCUMENTO[%s] IDUF[%s] SGORGAOEXPEDIDOR[%s]", pclDocumento->getNrDocumento(), pclDocumento->getIdTipoDocumento(), pclDocumento->getIdUF(), pclDocumento->getSgOrgaoExpedidor());
                            pclPessoaDocumento->setIdDocumento(tDocumentoTmp.szIdDocumento);
                        }
                    }

                    if ( pclPessoaDocumento->buscaPessoaDocumento() == false ) {
                        pclPessoaDocumento->inserePessoaDocumento();
                        ULOG("RSR(3) INSERIU EM CUSTOMER.PESSOADOCUMENTO COM IDPESSOADOCUMENTO[%s]IDDOCUMENTO[%s]", pclPessoaDocumento->getIdPessoaDocumento(), pclPessoaDocumento->getIdDocumento());
                    }
                    else {
                        pclPessoaDocumento->atualizaPessoaDocumento();
                        ULOG("CMG(3) ATUALIZOU EM CUSTOMER.PESSOADOCUMENTO COM IDPESSOADOCUMENTO[%s]IDDOCUMENTO[%s]", pclPessoaDocumento->getIdPessoaDocumento(), pclPessoaDocumento->getIdDocumento());
                    }

                    /* Referente a PessoaPortabilidadeHist */
                    pclPessoaPortabilidadeHist->setIdTipoDocumento(pclTipoDocumento->getIdTipoDocumento());
                    pclPessoaPortabilidadeHist->setNrDocumento(pclDocumento->getNrDocumento());

                    // Referente ao documento CNAE
                    GETTREE(szAux, DNPessoaJuridica, "nrCNAE", 0, NOBRIGATORIO, "nrCNAE");
                    if(strlen(szAux) > 0) {
                        TrataNrDocumento(szAux);
                        pclDocumento->clearStruct();
                        pclDocumento->setNrDocumento(szAux);
                        pclDocumento->setIdUF("0");
        
                        pclTipoDocumento->clearStruct();
                        pclTipoDocumento->setSgClassificacao("CNAE");
                        if(pclTipoDocumento->buscaTipoDocumento() == false) {
                            sprintf(tDataProc.szMessageException, "Nao ha registro em apoio.tipodocumento para sgClassificacao[%s]", pclTipoDocumento->getSgClassificacao());
                            throw PPExceptionPORT(ERRO_EXECUCAO, tDataProc.szMessageException);
                        }
                        ULOG("RSR BUSCOU EM APOIO.TIPODOCUMENTO COM SGCLASSIFICACAO[%s]", pclTipoDocumento->getSgClassificacao());
        
                        pclDocumento->setIdTipoDocumento(pclTipoDocumento->getIdTipoDocumento());
                        pclDocumento->setIdPais("1");
                        pclDocumento->setIdUsuarioAlteracao(tDataProc.szUser);
        
                        if(bFlagNovoCadastro == true) {
                            tDocumentoTmp = pclDocumento->tDocumento;
                            if (pclDocumento->buscaDocumentoChaveComposta(&tDocumentoTmp) == false ) {
                                pclDocumento->insereDocumento();
                                ULOG("RSR(4A) INSERIU EM CUSTOMER.DOCUMENTO COM IDDOCUMENTO[%s]", pclDocumento->getIdDocumento());
                                pclPessoaDocumento->setIdDocumento(pclDocumento->getIdDocumento());
                            }
                            else {
                                ULOG("CMG(4A) EXISTE EM CUSTOMER.DOCUMENTO COM NRDOCUMENTO[%s] IDTIPODOCUMENTO[%s] IDUF[%s] SGORGAOEXPEDIDOR[%s]", pclDocumento->getNrDocumento(), pclDocumento->getIdTipoDocumento(), pclDocumento->getIdUF(), pclDocumento->getSgOrgaoExpedidor());
                                pclPessoaDocumento->setIdDocumento(tDocumentoTmp.szIdDocumento);
                            }
                        }
                        else {
                            tDocumentoTmp = pclDocumento->tDocumento;
                            /* Busca Documento por NrDocumento, IdTipoDocumento, IdUf e SgOrgaoexpedidor */
                            if(pclDocumento->buscaDocumentoChaveComposta(&tDocumentoTmp) == false) {
                                pclDocumento->insereDocumento();
                                ULOG("RSR(4B) INSERIU EM CUSTOMER.DOCUMENTO COM IDDOCUMENTO[%s]", pclDocumento->getIdDocumento());
                                pclPessoaDocumento->setIdDocumento(pclDocumento->getIdDocumento());
                            }
                            else  {
                                ULOG("RSR(4B) BUSCOU EM CUSTOMER.DOCUMENTO COM NRDOCUMENTO[%s] IDTIPODOCUMENTO[%s] IDUF[%s] SGORGAOEXPEDIDOR[%s]", pclDocumento->getNrDocumento(), pclDocumento->getIdTipoDocumento(), pclDocumento->getIdUF(), pclDocumento->getSgOrgaoExpedidor());
                                pclPessoaDocumento->setIdDocumento(tDocumentoTmp.szIdDocumento);
                            }
                        }
        
                        if ( pclPessoaDocumento->buscaPessoaDocumento() == false ) {
                            pclPessoaDocumento->inserePessoaDocumento();
                            ULOG("RSR(4) INSERIU EM CUSTOMER.PESSOADOCUMENTO COM IDPESSOADOCUMENTO[%s]IDDOCUMENTO[%s]", pclPessoaDocumento->getIdPessoaDocumento(), pclPessoaDocumento->getIdDocumento());
                        }
                        else {
                            pclPessoaDocumento->atualizaPessoaDocumento();
                            ULOG("CMG(4) ATUALIZOU EM CUSTOMER.PESSOADOCUMENTO COM IDPESSOADOCUMENTO[%s]IDDOCUMENTO[%s]", pclPessoaDocumento->getIdPessoaDocumento(), pclPessoaDocumento->getIdDocumento());
                        }
                    } //if(strlen(szAux) > 0) {
    
    
    
                    // Referente ao documento CCM
                    GETTREE(szAux, DNPessoaJuridica, "nrCCM", 0, NOBRIGATORIO, "nrCCM");
                    if(strlen(szAux) > 0) {
                        TrataNrDocumento(szAux);
                        pclDocumento->clearStruct();
                        pclDocumento->setNrDocumento(szAux);
                        pclDocumento->setIdUF("0");
        
                        pclTipoDocumento->clearStruct();
                        pclTipoDocumento->setSgClassificacao("CCM");
                        if(pclTipoDocumento->buscaTipoDocumento() == false) {
                            sprintf(tDataProc.szMessageException, "Nao ha registro em apoio.tipodocumento para sgClassificacao[%s]", pclTipoDocumento->getSgClassificacao());
                            throw PPExceptionPORT(ERRO_EXECUCAO, tDataProc.szMessageException);
                        }
                        ULOG("RSR BUSCOU EM APOIO.TIPODOCUMENTO COM SGCLASSIFICACAO[%s]", pclTipoDocumento->getSgClassificacao());
        
                        pclDocumento->setIdTipoDocumento(pclTipoDocumento->getIdTipoDocumento());
                        pclDocumento->setIdPais("1");
                        pclDocumento->setIdUsuarioAlteracao(tDataProc.szUser);
        
                        if(bFlagNovoCadastro == true) {
                            tDocumentoTmp = pclDocumento->tDocumento;
                            if (pclDocumento->buscaDocumentoChaveComposta(&tDocumentoTmp) == false ) {
                                pclDocumento->insereDocumento();
                                ULOG("RSR(5A) INSERIU EM CUSTOMER.DOCUMENTO COM IDDOCUMENTO[%s]", pclDocumento->getIdDocumento());
                                pclPessoaDocumento->setIdDocumento(pclDocumento->getIdDocumento());
                            }
                            else {
                                ULOG("CMG(5A) EXISTE EM CUSTOMER.DOCUMENTO COM NRDOCUMENTO[%s] IDTIPODOCUMENTO[%s] IDUF[%s] SGORGAOEXPEDIDOR[%s]", pclDocumento->getNrDocumento(), pclDocumento->getIdTipoDocumento(), pclDocumento->getIdUF(), pclDocumento->getSgOrgaoExpedidor());
                                pclPessoaDocumento->setIdDocumento(tDocumentoTmp.szIdDocumento);
                            }
                        }
                        else {
                            tDocumentoTmp = pclDocumento->tDocumento;
                            /* Busca Documento por NrDocumento, IdTipoDocumento, IdUf e SgOrgaoexpedidor */
                            if(pclDocumento->buscaDocumentoChaveComposta(&tDocumentoTmp) == false) {
                                pclDocumento->insereDocumento();
                                ULOG("RSR(5B) INSERIU EM CUSTOMER.DOCUMENTO COM IDDOCUMENTO[%s]", pclDocumento->getIdDocumento());
                                pclPessoaDocumento->setIdDocumento(pclDocumento->getIdDocumento());
                            }
                            else  {
                                ULOG("RSR(5B) BUSCOU EM CUSTOMER.DOCUMENTO COM NRDOCUMENTO[%s] IDTIPODOCUMENTO[%s] IDUF[%s] SGORGAOEXPEDIDOR[%s]", pclDocumento->getNrDocumento(), pclDocumento->getIdTipoDocumento(), pclDocumento->getIdUF(), pclDocumento->getSgOrgaoExpedidor());
                                pclPessoaDocumento->setIdDocumento(tDocumentoTmp.szIdDocumento);
                            }
                        }

                        if ( pclPessoaDocumento->buscaPessoaDocumento() == false ) {
                            pclPessoaDocumento->inserePessoaDocumento();
                            ULOG("RSR(5) INSERIU EM CUSTOMER.PESSOADOCUMENTO COM IDPESSOADOCUMENTO[%s]IDDOCUMENTO[%s]", pclPessoaDocumento->getIdPessoaDocumento(), pclPessoaDocumento->getIdDocumento());
                        }
                        else {
                            pclPessoaDocumento->atualizaPessoaDocumento();
                            ULOG("CMG(5) ATUALIZOU EM CUSTOMER.PESSOADOCUMENTO COM IDPESSOADOCUMENTO[%s]IDDOCUMENTO[%s]", pclPessoaDocumento->getIdPessoaDocumento(), pclPessoaDocumento->getIdDocumento());
                        }
                    } //if(strlen(szAux) > 0) {

                    // Referente ao documento IE
                  /*  GETTREE(szAux, DNPessoaJuridica, "nrInscricao", 0, NOBRIGATORIO, "nrInscricao");
                    TrataNrDocumento(szAux);
                    pclDocumento->clearStruct();
                    pclDocumento->setNrDocumento(szAux?szAux:0);
                    pclDocumento->setIdUF("0");

                    GETTREE(szAux, DNPessoaJuridica, "sgTipoInscricao", 0, NOBRIGATORIO, "sgTipoInscricao");
                    pclTipoDocumento->clearStruct();

                    if ( szAux[0] )
                    {
                        pclTipoDocumento->setSgClassificacao(szAux);

                        if(pclTipoDocumento->buscaTipoDocumentoInscricao() == false) {
                            sprintf(tDataProc.szMessageException, "Nao ha registro em apoio.tipodocumento para sgClassificacao[%s]", pclTipoDocumento->getSgClassificacao());
                            throw PPExceptionPORT(ERRO_EXECUCAO, tDataProc.szMessageException);
                        }
                        ULOG("RSR BUSCOU EM APOIO.TIPODOCUMENTO COM SGCLASSIFICACAO[%s]", pclTipoDocumento->getSgClassificacao());

                        pclDocumento->setIdTipoDocumento(pclTipoDocumento->getIdTipoDocumento());
                    }
    
                    pclDocumento->setIdPais("1");
                    pclDocumento->setIdUsuarioAlteracao(tDataProc.szUser);
    
                    if(bFlagNovoCadastro == true) {
                        tDocumentoTmp = pclDocumento->tDocumento;
                        if (pclDocumento->buscaDocumentoChaveComposta(&tDocumentoTmp) == false ) {
                            pclDocumento->insereDocumento();
                            ULOG("RSR(6A) INSERIU EM CUSTOMER.DOCUMENTO COM IDDOCUMENTO[%s]", pclDocumento->getIdDocumento());
                            pclPessoaDocumento->setIdDocumento(pclDocumento->getIdDocumento());
                        }
                        else {
                            ULOG("CMG(6A) EXISTE EM CUSTOMER.DOCUMENTO COM NRDOCUMENTO[%s] IDTIPODOCUMENTO[%s] IDUF[%s] SGORGAOEXPEDIDOR[%s]", pclDocumento->getNrDocumento(), pclDocumento->getIdTipoDocumento(), pclDocumento->getIdUF(), pclDocumento->getSgOrgaoExpedidor());
                            pclPessoaDocumento->setIdDocumento(tDocumentoTmp.szIdDocumento);
                        }
                    }
                    else {
                        tDocumentoTmp = pclDocumento->tDocumento;
                        //Busca Documento por NrDocumento, IdTipoDocumento, IdUf e SgOrgaoexpedidor 
                    
                    
                        if(pclDocumento->buscaDocumentoChaveComposta(&tDocumentoTmp) == false) {
                            pclDocumento->insereDocumento();
                            ULOG("RSR(6B) INSERIU EM CUSTOMER.DOCUMENTO COM IDDOCUMENTO[%s]", pclDocumento->getIdDocumento());
                            pclPessoaDocumento->setIdDocumento(pclDocumento->getIdDocumento());
                        }
                        else  {
                            ULOG("RSR(6B) BUSCOU EM CUSTOMER.DOCUMENTO COM NRDOCUMENTO[%s] IDTIPODOCUMENTO[%s] IDUF[%s] SGORGAOEXPEDIDOR[%s]", pclDocumento->getNrDocumento(), pclDocumento->getIdTipoDocumento(), pclDocumento->getIdUF(), pclDocumento->getSgOrgaoExpedidor());
                            pclPessoaDocumento->setIdDocumento(tDocumentoTmp.szIdDocumento);
                        }
                    }
    
                    if ( pclPessoaDocumento->buscaPessoaDocumento() == false ) {
                        pclPessoaDocumento->inserePessoaDocumento();
                        ULOG("RSR(6) INSERIU EM CUSTOMER.PESSOADOCUMENTO COM IDPESSOADOCUMENTO[%s]IDDOCUMENTO[%s]", pclPessoaDocumento->getIdPessoaDocumento(), pclPessoaDocumento->getIdDocumento());
                    }
                    else {
                        pclPessoaDocumento->atualizaPessoaDocumento();
                        ULOG("CMG(6) ATUALIZOU EM CUSTOMER.PESSOADOCUMENTO COM IDPESSOADOCUMENTO[%s]IDDOCUMENTO[%s]", pclPessoaDocumento->getIdPessoaDocumento(), pclPessoaDocumento->getIdDocumento());
                    }
                    */ /*   Referente ao documento IE   */

                    if((DNDadosEnderecoPJ = walkDOM(DNPessoaJuridica, "DadosEndereco", 0))) {
                        ULOG("RSR DADOSENDERECO PESSOA JURIDICA>>>");
    
                        GETTREE(szAux, DNDadosEnderecoPJ, "sgTipoEndereco", 0, OBRIGATORIO, "sgTipoEndereco");
                        pclTipoEndereco->setSgTipoEndereco(szAux);
                        if(pclTipoEndereco->buscaTipoEndereco() == false) {
                            sprintf(tDataProc.szMessageException, "Nao ha registro em apoio.tipoendereco para sgTipoEndereco[%s]", pclTipoEndereco->getSgTipoEndereco());
                            throw PPExceptionPORT(ERRO_EXECUCAO, tDataProc.szMessageException);
                        }
                        ULOG("RSR BUSCOU EM APOIO.TIPOENDERECO COM SGTIPOENDERECO[%s]", pclTipoEndereco->getSgTipoEndereco());
    
                        strcpy(tPessoaEnderecoXML.szIdTipoEndereco, pclTipoEndereco->getIdTipoEndereco());
                        GETTREE(tPessoaEnderecoXML.szNmTipoLogradouro, DNDadosEnderecoPJ, "nmTipoLogradouro", 0, OBRIGATORIO, "nmTipoLogradouro");
                        GETTREE(tPessoaEnderecoXML.szNmTituloLogradouro, DNDadosEnderecoPJ, "nmTitLogradouro", 0, NOBRIGATORIO, "");
                        GETTREE(tPessoaEnderecoXML.szNrCep, DNDadosEnderecoPJ, "nrCEP", 0, OBRIGATORIO, "nrCEP");
                        GETTREE(tPessoaEnderecoXML.szNmLogradouro, DNDadosEnderecoPJ, "nmLogradouro", 0, OBRIGATORIO, "nmLogradouro");
                        GETTREE(tPessoaEnderecoXML.szNrEndereco, DNDadosEnderecoPJ, "nrLogradouro", 0, OBRIGATORIO, "nrLogradouro");
                        GETTREE(tPessoaEnderecoXML.szDsEnderecoComplemento, DNDadosEnderecoPJ, "nmComplemento", 0, NOBRIGATORIO, "");
                        GETTREE(tPessoaEnderecoXML.szNmBairro, DNDadosEnderecoPJ, "nmBairro", 0, OBRIGATORIO, "nmBairro");
                        GETTREE(tPessoaEnderecoXML.szNmMunicipio, DNDadosEnderecoPJ, "nmMunicipio", 0, OBRIGATORIO, "nmMunicipio");
    
                        GETTREE(szAux, DNDadosEnderecoPJ, "sgUF", 0, OBRIGATORIO, "sgUF");
                        pclUF->clearStruct();
                        pclUF->setSgUF(szAux);
                        if(pclUF->buscaUF() == false) {
                            sprintf(tDataProc.szMessageException, "Nao ha registro em apoio.uf para sgUF[%s]", pclUF->getSgUF());
                            throw PPExceptionPORT(ERRO_EXECUCAO, tDataProc.szMessageException);
                        }
                        ULOG("RSR BUSCOU EM APOIO.UF COM SGUF[%s]", pclUF->getSgUF());
                        strcpy(tPessoaEnderecoXML.szIdUF, pclUF->getIdUF());
    
                        pclPessoaEndereco->setStruct(&tPessoaEnderecoXML);
                        pclPessoaEndereco->setIdPessoa(pclPessoa->getIdPessoa());
                        pclPessoaEndereco->setIdUsuarioAlteracao(tDataProc.szUser);
                        pclPessoaEndereco->setIdSistemaOrigem(pclSistemaOrigem->getIdSistemaOrigem());
                        pclPessoaEndereco->setIdPais("1");
    
    
                        if(bFlagNovoCadastro == true) {
                            pclPessoaEndereco->inserePessoaEndereco();
                            ULOG("RSR INSERIU EM CUSTOMER.PESSOAENDERECO COM IDPESSOAENDERECO[%s]", pclPessoaEndereco->getIdPessoaEndereco());
                        }
                        else {
                            /* Busca por IdPessoa + IdSistemaOrigem */
                            TPessoaEndereco tPessoaEnderecoTmp = pclPessoaEndereco->tPessoaEndereco;
                            if(pclPessoaEndereco->buscaPessoaEndereco(&tPessoaEnderecoTmp) == false) {
                                pclPessoaEndereco->inserePessoaEndereco();
                                ULOG("RSR INSERIU EM CUSTOMER.PESSOAENDERECO COM IDPESSOAENDERECO[%s]", pclPessoaEndereco->getIdPessoaEndereco());
                            }
                            else {
                                ULOG("RSR BUSCOU EM CUSTOMER.PESSOAENDERECO COM IDPESSOA[%s] IDSISTEMAORIGEM[%s]", pclPessoaEndereco->getIdPessoa(), pclPessoaEndereco->getIdSistemaOrigem());
                                pclPessoaEndereco->setIdPessoaEndereco(tPessoaEnderecoTmp.szIdPessoaEndereco);
                                pclPessoaEndereco->atualizaPessoaEndereco();
                                ULOG("RSR ATUALIZOU EM CUSTOMER.PESSOAENDERECO COM IDPESSOAENDERECO[%s]", pclPessoaEndereco->getIdPessoaEndereco());
                            }
                        }
    
                        ULOG("RSR DADOSENDERECO PESSOA JURIDICA<<<");
                    } //if((DNDadosEnderecoPJ = walkDOM(DNPessoaJuridica, "DadosEndereco", 0)))
                    else {
                        ULOG("RSR DADOSENDERECO PESSOA JURIDICA NAO ENVIADO");
                    }
    
                    ULOG("RSR PESSOA JURIDICA<<<");
    
                } //if((DNPessoaJuridica = walkDOM(dnode, "PJ", 0)))
                else
                    throw PPExceptionPORT(ERRO_EXECUCAO, "Noh PJ inexistente!");
            } //else if(iIdTipoPessoaCtrl == 2) // Pessoa Juridica
            else
                throw PPExceptionPORT(ERRO_EXECUCAO, "DEBUG iIdTipoPessoaCtrl");
    
    
            if(bFlagNovoCadastro == true) {
                pclPessoaPortabilidadeHist->setIdTipoLinha(pclTipoLinha->getIdTipoLinha());
                pclPessoaPortabilidadeHist->setNrLinha(tDataProc.szNrLinha+2);
                memcpy(szAux, tDataProc.szNrLinha, 2); szAux[2]=0x00;
                pclPessoaPortabilidadeHist->setCdAreaRegistro(szAux);
                pclPessoaPortabilidadeHist->setIdTipoPessoa(pclTipoPessoa->getIdTipoPessoa());
                pclPessoaPortabilidadeHist->setNmPessoa(pclPessoa->getNmPessoa());
    
                /* pclPessoaPortabilidadeHist->setIdTipoDocumento(pclTipoDocumento->getIdTipoDocumento()); */
                /* pclPessoaPortabilidadeHist->setNrDocumento(pclDocumento->getNrDocumento()); */
    
                pclPessoaPortabilidadeHist->setIdTipoEndereco(pclTipoEndereco->getIdTipoEndereco());
                pclPessoaPortabilidadeHist->setNmTipoLogradouro(pclPessoaEndereco->getNmTipoLogradouro());
                pclPessoaPortabilidadeHist->setNmLogradouro(pclPessoaEndereco->getNmLogradouro());
                pclPessoaPortabilidadeHist->setNrEndereco(pclPessoaEndereco->getNrEndereco());
                pclPessoaPortabilidadeHist->setNmMunicipio(pclPessoaEndereco->getNmMunicipio());
                pclPessoaPortabilidadeHist->setNmBairro(pclPessoaEndereco->getNmBairro());
                pclPessoaPortabilidadeHist->setNrCep(pclPessoaEndereco->getNrCep());
                pclPessoaPortabilidadeHist->setDsAcaoPortabilidade(pclAcaoPortabilidade->getDsAcaoPortabilidade());
                pclPessoaPortabilidadeHist->setIdUsuarioAlteracao(tDataProc.szUser);
                pclPessoaPortabilidadeHist->setSgTipoPortabilidade("PORTIN");
                pclPessoaPortabilidadeHist->inserePessoaPortabilidadeHist();
                ULOG("RSR INSERIU EM CUSTOMER.PESSOAPORTABILIDADEHIST COM IDPESSOAPORTABILIDADEHIST[%s]", pclPessoaPortabilidadeHist->getIdPessoaPortabilidadeHist());
            }
    
            pclXmlGenRegContato->createTag("PessoaVO");
            pclXmlGenRegContato->addItem("idPessoa", pclPessoaDePara->getIdPessoaDePara());
            pclXmlGenRegContato->addItem("AtendimentoTipoComunicacaoVO", "1");
            pclXmlGenRegContato->closeTag(); //PessoaVO
    
    
            ULOG("RSR DNODE<<<");
    
            pclRegistraContato->setIdPessoaDePara(pclPessoaDePara->getIdPessoaDePara());
    
            TuxMessage inputMessage;
            inputMessage.setUser(tDataProc.szUser);
            inputMessage.setMessageBody(pclXmlGenRegContato);
            inputMessage.setService("DREGCONTATO");
    
            pMessageBody = inputMessage.getMessageBody();
            sprintf(szAuxRegistraContato,
                "<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>"
                    "<msg>"
                        "<msgHdr>"
                            "<user>%s</user>"
                            "<service>DREGCONTATO</service>"
                        "</msgHdr>"
                        "<msgBody>"
                            "%s"
                        "</msgBody>"
                    "</msg>", tDataProc.szUser, pMessageBody);
    
            ULOG("RegistraContato(%d)[%s]", strlen(szAuxRegistraContato), szAuxRegistraContato);
            pclRegistraContato->setXml(szAuxRegistraContato);
    
            ULOG("Inicio do processo Registra Contato");
            pclRegistraContato->insereRegistraContato();
            ULOG("Final do processo Registra Contato");
            ULOG("RSR INSERIU EM TIBCO_OW.P_REGISTRACONTATO COM IDREGISTRACONTATO[%s]", pclRegistraContato->getIdRegistraContato());
    
        }
    }
    catch(PPExceptionPORT ExcepPORT)
    {
        switch(ExcepPORT.getTipo())
        {
            case ERRO_EXECUCAO:
                setStatusCode(E_APLICACAO_PREPAGO, ExcepPORT.getMsg());
                break;
            case ERRO_PARAMETRO_NULL:
                setStatusCode(E_PARAMETRO_PREPAGO, ExcepPORT.getMsg());
                break;
            case ERRO_SEQUENCE:
                setStatusCode(W_SEQUENCE_PREPAGO, ExcepPORT.getMsg());
                break;
            case ERRO_REGISTRO_NAO_ENCONTRADO:
                setStatusCode(W_DELETE_PREPAGO, ExcepPORT.getMsg());
                break;
            case ERRO_LEGADO_SET_INTERCEPTACAO:
                setStatusCode(E_APLICACAO_PREPAGO, ExcepPORT.getMsg());
                break;
            default:
                setStatusCode(E_APLICACAO_PREPAGO, ExcepPORT.getMsg());
                break;
        }

        ULOG("Desalocando objetos de negocio Exception PORTABILIDADE");



        delete pclPessoa;
        delete pclPessoaDePara;
        delete pclPessoaFisica;
        delete pclPessoaJuridica;
        delete pclPessoaDocumento;
        delete pclPessoaComunicacao;
        // delete pclPessoaSegmentacao;
        // delete pclPessoaSegmentacaoHistorico;
        delete pclPessoaEndereco;
        delete pclDocumento;
        delete pclSistemaOrigem;
        delete pclTipoEnderecoCobranca;
        delete pclTipoDocumento;
        delete pclUF;
        delete pclTipoPessoa;
        delete pclTipoLinha;
        delete pclMisc;
        delete pclSexo;
        delete pclEstadoCivil;
        delete pclTipoComunicacao;
        delete pclTipoEndereco;
        delete pclTipoCarteira;
        delete pclCFOP;
        delete pclPessoaPortabilidade;
        delete pclPessoaPortabilidadeHist;
        delete pclContatoFuncionalidade;
        delete pclRegistraContato;
        delete pclAcaoPortabilidade;
        delete pclXmlGenRegContato;

        ULOGE(">>>Final do processamento de SETCLIENTPORT");
        ULOG_END("SETCLIENTPORT");
        return;
    }
    catch(...) {
        ULOG("Exception");

        ULOG("Desalocando objetos de negocio Exception ...");
        delete pclPessoa;
        delete pclPessoaDePara;
        delete pclPessoaFisica;
        delete pclPessoaJuridica;
        delete pclPessoaDocumento;
        delete pclPessoaComunicacao;
        // delete pclPessoaSegmentacao;
        // delete pclPessoaSegmentacaoHistorico;
        delete pclPessoaEndereco;
        delete pclDocumento;
        delete pclSistemaOrigem;
        delete pclTipoEnderecoCobranca;
        delete pclTipoDocumento;
        delete pclUF;
        delete pclTipoPessoa;
        delete pclTipoLinha;
        delete pclMisc;
        delete pclSexo;
        delete pclEstadoCivil;
        delete pclTipoComunicacao;
        delete pclTipoEndereco;
        delete pclTipoCarteira;
        delete pclCFOP;
        delete pclPessoaPortabilidade;
        delete pclPessoaPortabilidadeHist;
        delete pclContatoFuncionalidade;
        delete pclRegistraContato;
        delete pclAcaoPortabilidade;
        delete pclXmlGenRegContato;

        ULOGE(">>>Final do processamento de SETCLIENTPORT");
        ULOG_END("SETCLIENTPORT");
        throw;
    }

    ULOG("Desalocando objetos de negocio");

    delete pclPessoa;
    delete pclPessoaDePara;
    delete pclPessoaFisica;
    delete pclPessoaJuridica;
    delete pclPessoaDocumento;
    delete pclPessoaComunicacao;
    // delete pclPessoaSegmentacao;
    // delete pclPessoaSegmentacaoHistorico;
    delete pclPessoaEndereco;
    delete pclDocumento;
    delete pclSistemaOrigem;
    delete pclTipoEnderecoCobranca;
    delete pclTipoDocumento;
    delete pclUF;
    delete pclTipoPessoa;
    delete pclTipoLinha;
    delete pclMisc;
    delete pclSexo;
    delete pclEstadoCivil;
    delete pclTipoComunicacao;
    delete pclTipoEndereco;
    delete pclTipoCarteira;
    delete pclCFOP;
    delete pclPessoaPortabilidade;
    delete pclPessoaPortabilidadeHist;
    delete pclRegistraContato;
    delete pclContatoFuncionalidade;
    delete pclAcaoPortabilidade;
    delete pclXmlGenRegContato;

    xml_g->createTag("PortabilidadeOutTO");
        xml_g->addItem("cdError", "0");
        xml_g->addItem("msgError", "sucesso...");
    xml_g->closeTag();

    ULOG_END("SETCLIENTPORT");
    setStatusCode("13I0000", "Sucesso");
}
