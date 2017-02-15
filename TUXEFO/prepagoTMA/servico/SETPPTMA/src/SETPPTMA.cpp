///////////////////////////////////////////////////////////////////////////////
/**
 *
 * 33374 – Modelo Atendimento Futuro PF e PJ Fixa e Móvel - Marcação Cliente Segmentada
 *    Criação do metodo CPessoaSegmentacaopc::proCClassificaInfancia
 *    Agosto/2015   [A0001]
 *
 **/
///////////////////////////////////////////////////////////////////////////////#include <tuxfw.h>

#include <PPGlobalTMA.h>
#include <PPExceptionTMA.h>
#include <ToolsTMA.h>
#include <PessoaTMA.h>
#include <PessoaDeParaTMA.h>
#include <PessoaDocumentoTMA.h>
#include <PessoaContaTMA.h>
#include <PessoaFisicaTMA.h>
#include <PessoaJuridicaTMA.h>
#include <PessoaComunicacaoTMA.h>
#include <PessoaSegmentacaoTMA.h>
#include <PessoaSegmentacaoHistoricoTMA.h>
#include <PessoaValorPossivelTMA.h>
#include <PessoaEnderecoTMA.h>
#include <PessoaLinhaTMA.h>
#include <PessoaLinhaHistoricoTMA.h>
#include <DocumentoTMA.h>
#include <SistemaOrigemTMA.h>
#include <LinhaContaTMA.h>
#include <ContaEnderecoTMA.h>
#include <TipoEnderecoCobrancaTMA.h>
#include <TipoDocumentoTMA.h>
#include <ParametroTMA.h>
// #include <RegistraContatoTMA.h>
#include <UFTMA.h>
#include <FilaSetClientInfoTMA.h>
#include <PessoaLinhaPreTMA.h>
#include <PermissaoLinhaPUPTMA.h>
#include <FilaPreEnvioSetCliInfopcTMA.h>

DECLARE_TUXEDO_SERVICE(SETPPTMA);

void implSETPPTMA::Execute(DOMNode *dnode, XMLGen *xml_g)
{
    /*--- [A0001] ------------------------------*/
    char vo_idLinhaTelefonica[256];
    char vo_idPessoa[256];
    char vo_idTipoPessoa[256];
    /*------------------------------------------*/

    char * bf;
    /* ponteiro dos objetos */
    CPessoa *pclPessoa=NULL;
    CPessoaDePara *pclPessoaDePara=NULL;
    CPessoaFisica *pclPessoaFisica=NULL;
    CPessoaJuridica *pclPessoaJuridica=NULL;
    CPessoaDocumento *pclPessoaDocumento=NULL;
    CPessoaComunicacao *pclPessoaComunicacao=NULL;
    CPessoaValorPossivel *pclPessoaValorPossivel=NULL;
    CPessoaSegmentacao *pclPessoaSegmentacao=NULL;
    CPessoaSegmentacaoHistorico *pclPessoaSegmentacaoHistorico=NULL;
    CPessoaEndereco *pclPessoaEndereco=NULL;
    CPessoaLinha *pclPessoaLinha=NULL;
    CPessoaLinhaHistorico *pclPessoaLinhaHistorico=NULL;
    CDocumento *pclDocumento=NULL;
    CSistemaOrigem *pclSistemaOrigem=NULL;
    CLinhaConta *pclLinhaConta=NULL;
    CPessoaConta *pclPessoaConta=NULL;
    CContaEndereco *pclContaEndereco=NULL;
    CTipoEnderecoCobranca *pclTipoEnderecoCobranca=NULL;
    CTipoDocumento *pclTipoDocumento=NULL;
    CParametro *pclParametro=NULL;
    // CRegistraContato *pclRegistraContato=NULL;
    CUF *pclUF=NULL;
    CFilaSetClientInfo *pclFilaSetClientInfo=NULL;
    CPessoaLinhaPre *pclPessoaLinhaPre=NULL;
    CPermissaoLinhaPUP *pclPermissaoLinhaPUP=NULL;
    CFilaPreEnvioSetCliInfopc cFilaPreEnvioSetCliInfopc;

    // XMLGen *pclXmlGenRegContato=NULL;
    XMLGen *pclXmlGenSetInterception=NULL;
    XMLGen *pclXmlGenSetClient=NULL;

    TuxMessage *inputMessageHabLinha=NULL;
    TuxRemoteService *remoteServiceHabLinha=NULL;

	int i_iduf=0;


    /* Usadas no acesso ao NGIN */
    char *pStatusCode=NULL;
    char *pStatusText=NULL;
    int iRetSistemaLegado=0;
    int iTamSaidaSetClient=0;
    // char *pMessageBody=NULL;
    
    char nrCEP[16];
    char nmLograd[256];
    char nmBairro[256];
    char nmCidade[256];

    try
    {
        ULOG_START("SETPPTMA");

        /*--- [A0001] ---------------------------------------------------*/
        memset( vo_idLinhaTelefonica, 0x0, sizeof(vo_idLinhaTelefonica) );
        memset( vo_idPessoa         , 0x0, sizeof(vo_idPessoa) );
        memset( vo_idTipoPessoa     , 0x0, sizeof(vo_idTipoPessoa) );
        /*---------------------------------------------------------------*/

        /* controle de Cliente/Usuario */
        bool bClienteUsuario;
        int iIndexDOM=0;
        char szIdPessoaDePara[QTD_ARRAY_PESSOA_DE_PARA][LEN_IDPESSOADEPARA + LEN_EOS];
        int iIdPessoaDeParaIndex=0;

        /* controle para navegacao no xml */
        int iIdTipoPessoaCtrl=0;
        int iQtdLoop=0;
        int iCountClientUser=0;
        int iIndexAux=0;

        /* Usadas na macro GETTREE */
        char szMessage[LEN_RETURN_MESSAGE + LEN_EOS];
        char *pTree;

        /* Uso geral */
        char szAux[LEN_AUX + LEN_EOS];
        char szAuxNGIN[LEN_AUX + LEN_EOS];
        // char szAuxRegistraContato[LEN_XML_REGCONTATO + LEN_EOS];
        char *pTmp=NULL;
        char szArruamento[LEN_AUX + LEN_EOS];
        int iCountLogradouro=0;
        char szInEndPrincipal[LEN_INENDPRINCIPAL + LEN_EOS];
        bool bEndPrincipal=false;
        char szIdPessoaEnderecoEndPrincipal[LEN_IDPESSOAENDERECO + LEN_EOS];
        char szIdPessoaCtrl[LEN_IDPESSOA + LEN_EOS];
        bool bEnviaSetInterception=true;

        // Verificar
        char szUsrNaoInformado[LEN_INUSUARIONAOINFORMADO + LEN_EOS];


        /* Ponteiros dos nos */
        DOMNode *DNPessoaFisica=NULL;
        DOMNode *DNPessoaJuridica=NULL;
        DOMNode *DNDocumento=NULL;
        DOMNode *DNTelefonePJ=NULL;
        DOMNode *DNTelefonePF=NULL;
        DOMNode *DNDadosEnderecoPJ=NULL;
        DOMNode *DNDadosEnderecoPF=NULL;
        DOMNode *DNPUP=NULL;

        /* De para de tipo de logradouros para o NGIN */
        char *pszTipoLogNGIN[] = {
            "ACS", "AL", "AV", "BC", "ESTR", "GLR", "JD", "LAD", "LGO", "PAS","PCA",
            "PRA", "PQ", "PTE", "R", "ROD", "TRAV", "TRV", "V", "VD", "VIA", "VL"
        };
        char *pszTipoLogFO[] = {
            "ACESSO", "ALAMEDA", "AVENIDA", "BECO", "ESTRADA", "GALERIA", "JARDIM",
            "LADEIRA", "LARGO", "PASSAGEM", "PRACA", "PRAIA", "PARQUE", "PONTE",
            "RUA", "RODOVIA", "TRAVESSA", "TREVO", "VALE", "VIADUTO", "VIA", "VIELA", NULL
        };


        /* estruturas de apoio a manipulacao dos dados dos objetos referentes */
        TDataProc tDataProc;                                memset(&tDataProc, 0x00, sizeof(TDataProc));
        TPessoa tPessoaXML;                                 memset(&tPessoaXML, 0x00, sizeof(TPessoa));
        TDocumento tDocumentoXML;                           memset(&tDocumentoXML, 0x00, sizeof(TDocumento));
        TDocumento tDocumentoTmp;                           memset(&tDocumentoTmp, 0x00, sizeof(TDocumento));
        TPessoaValorPossivel tPessoaValorPossivelXML;       memset(&tPessoaValorPossivelXML, 0x00, sizeof(TPessoaValorPossivel));
        TPessoaComunicacao tPessoaComunicacaoXML;           memset(&tPessoaComunicacaoXML, 0x00, sizeof(TPessoaComunicacao));
        TPessoaJuridica tPessoaJuridicaXML;                 memset(&tPessoaJuridicaXML, 0x00, sizeof(TPessoaJuridica));
        TPessoaFisica tPessoaFisicaXML;                     memset(&tPessoaFisicaXML, 0x00, sizeof(TPessoaFisica));
        TPessoaEndereco tPessoaEnderecoXML;                 memset(&tPessoaEnderecoXML, 0x00, sizeof(TPessoaEndereco));
        TDesmembraNome tDesmembraNome;


        /* Instancia objetos */
        pclPessoa = new CPessoa;
        pclPessoaDePara = new CPessoaDePara;
        pclPessoaFisica = new CPessoaFisica;
        pclPessoaJuridica = new CPessoaJuridica;
        pclPessoaDocumento = new CPessoaDocumento;
        pclPessoaValorPossivel = new CPessoaValorPossivel;
        pclPessoaComunicacao = new CPessoaComunicacao;
        pclPessoaSegmentacao = new CPessoaSegmentacao;
        pclPessoaSegmentacaoHistorico = new CPessoaSegmentacaoHistorico;
        pclPessoaEndereco = new CPessoaEndereco;
        pclPessoaLinhaHistorico = new CPessoaLinhaHistorico;
        pclPessoaLinha = new CPessoaLinha;
        pclDocumento = new CDocumento;
        pclSistemaOrigem = new CSistemaOrigem;
        pclLinhaConta = new CLinhaConta;
        pclPessoaConta = new CPessoaConta;
        pclContaEndereco = new CContaEndereco;
        pclTipoEnderecoCobranca = new CTipoEnderecoCobranca;
        pclTipoDocumento = new CTipoDocumento;
        pclParametro = new CParametro;
        // pclRegistraContato = new CRegistraContato;
        pclUF = new CUF;
        pclFilaSetClientInfo = new CFilaSetClientInfo;
        pclPessoaLinhaPre = new CPessoaLinhaPre;
        pclPermissaoLinhaPUP = new CPermissaoLinhaPUP;

        // pclXmlGenRegContato = new XMLGen;
        pclXmlGenSetInterception = new XMLGen;
        pclXmlGenSetClient = new XMLGen;

        remoteServiceHabLinha = new TuxRemoteService();
        inputMessageHabLinha = new TuxMessage();

        memset(szIdPessoaEnderecoEndPrincipal, 0x00, sizeof(szIdPessoaEnderecoEndPrincipal));
        memset(szIdPessoaCtrl, 0x00, sizeof(szIdPessoaCtrl));

        /* obtem o idUsuarioAlteracao do header */
        pTmp=getUser(); ULOG("getUser()[%s]", pTmp?pTmp:"...NULL...");
        if(pTmp != NULL) {
            if(strlen(pTmp) > 0)
                strcpy(tDataProc.szUser, pTmp);
            else
                throw PPExceptionTMA(ERRO_EXECUCAO, "Tag user vazia");
        }
        else
            throw PPExceptionTMA(ERRO_EXECUCAO, "Tag user inexistente");

        ULOG("tDataProc.szUser[%s]", tDataProc.szUser);


        pTmp=getSubSystem(); ULOG("getSubSystem()[%s]", pTmp?pTmp:"...NULL...");
        if(pTmp == NULL)
            strcpy(tDataProc.szSubSystem, SG_FRONTOFFICCE);
        else {
            strcpy(tDataProc.szSubSystem, pTmp);
            XMLString::release(&pTmp); pTmp=NULL;
        }
        ULOG("tDataProc.szSubSystem[%s]", tDataProc.szSubSystem);
        pclSistemaOrigem->setSgSistemaOrigem(tDataProc.szSubSystem);
        if(pclSistemaOrigem->buscaSistemaOrigem() == false) {
            sprintf(tDataProc.szMessageException, "SistemaOrigem nao encontrado para SgSistemaOrigem[%s]", tDataProc.szSubSystem);
            throw PPExceptionTMA(ERRO_EXECUCAO, tDataProc.szMessageException);
        }


        pclTipoEnderecoCobranca->setSgTipoEnderecoCobranca(TIPO_ENDERECO_COBRANCA);
        if(pclTipoEnderecoCobranca->buscaTipoEnderecoCobranca() == false ) {
            sprintf(tDataProc.szMessageException, "TipoEnderecoCobranca nao encontrado para SgTipoEnderecoCobranca[%s]", TIPO_ENDERECO_COBRANCA);
            throw PPExceptionTMA(ERRO_EXECUCAO, tDataProc.szMessageException);
        }

        ULOG("RSR DNODE>>>");
        GETTREE(tDataProc.szIdTipoPessoa, dnode, "inTipoPessoa", 0, OBRIGATORIO, "inTipoPessoa"); ULOG("tDataProc.szIdTipoPessoa[%s]", tDataProc.szIdTipoPessoa);
        GETTREE(tDataProc.szIdLinhaTelefonica, dnode, "idLinhaTelefonica", 0, OBRIGATORIO, "idLinhaTelefonica"); ULOG("tDataProc.szIdLinhaTelefonica[%s]", tDataProc.szIdLinhaTelefonica);
        
        /*   [A0001]   */
        GETTREE( vo_idLinhaTelefonica, dnode, "idLinhaTelefonica", 0, OBRIGATORIO, "idLinhaTelefonica");
        GETTREE( vo_idTipoPessoa, dnode, "inTipoPessoa", 0, OBRIGATORIO, "inTipoPessoa");
        /*---------------------------------------------------------------------------------------------------------*/
        
        GETTREE(tDataProc.szNrLinha, dnode, "nrLinha", 0, OBRIGATORIO, "nrLinha"); ULOG("tDataProc.szNrLinha[%s]", tDataProc.szNrLinha);
        GETTREE(tDataProc.szDsTipoOperacao, dnode, "tpOperacao", 0, OBRIGATORIO, "tpOperacao"); ULOG("tDataProc.szDsTipoOperacao[%s]", tDataProc.szDsTipoOperacao);


        // pclXmlGenRegContato->createTag("msg");
        // pclXmlGenRegContato->addItem("ProxyOperacao", "getFidelizacao");
        // pclXmlGenRegContato->addItem("idChamadaTelefonica", "1");
        // pclXmlGenRegContato->addItem("idGrupoAbertura", "118");
        // pclXmlGenRegContato->addItem("inResponsavelAbertura", "2");
        // pclXmlGenRegContato->addItem("tpOperacao", "1");
        // pclXmlGenRegContato->createTag("ProcedenciaVO");
        // pclXmlGenRegContato->addItem("idProcedencia", "1");
        // pclXmlGenRegContato->closeTag(); //ProcedenciaVO
        // pclXmlGenRegContato->createTag("CanalVO");
        // pclXmlGenRegContato->addItem("idCanal", "1");
        // pclXmlGenRegContato->closeTag(); //CanalVO
        // pclXmlGenRegContato->addItem("nrTelefone", tDataProc.szNrLinha);

        // pclRegistraContato->setIdUsuarioAlteracao(tDataProc.szUser);
        // pclRegistraContato->setNrTelefone(tDataProc.szNrLinha);

        pclXmlGenSetInterception->addItem("ProxyLinha", tDataProc.szNrLinha);


        pclXmlGenSetClient->createTag("msg");
        pclXmlGenSetClient->addItem("ProxyOperacao", "setCliente");
        pclXmlGenSetClient->addItem("usuario", "FO" );
        pclXmlGenSetClient->addItem("ProxyLinha", tDataProc.szNrLinha);

        pclParametro->clearStruct();
        pclParametro->setCdParametro("ENVIA_SETINTERCEPTION");
        if(pclParametro->buscaParametro() == false) {
            ULOG("Pesquisa em APOIO.PARAMETRO não encontrou nenhum registro buscando por CdParametro [%s]", pclParametro->getCdParametro());
        }
        else {
            ULOG("Pesquisa em APOIO.PARAMETRO encontrou registro buscando por CdParametro[%s] DsValorParametro[%s]", pclParametro->getCdParametro(), pclParametro->getDsValorParametro());
            if(!strcmp(pclParametro->getDsValorParametro(), "0"))
                bEnviaSetInterception=false;
        }
        ULOG("bEnviaSetInterception(%d)", bEnviaSetInterception);

        pclParametro->clearStruct();
        if(!strcmp(tDataProc.szDsTipoOperacao, "T"))
            pclParametro->setCdParametro("PREPAGOTROCATITULARIDADE");
        else if(!strcmp(tDataProc.szDsTipoOperacao, "A"))
            pclParametro->setCdParametro("PREPAGOALTERACAOCADASTRO");
        else if(!strcmp(tDataProc.szDsTipoOperacao, "C"))
            pclParametro->setCdParametro("PREPAGONOVOCADASTRO");
        else
            throw PPExceptionTMA(ERRO_EXECUCAO, "Nao foi possivel obter Parametro para pesquisa em APOIO.PARAMETRO");

        if(pclParametro->buscaParametro() == false) {
            sprintf(tDataProc.szMessageException, "Pesquisa em APOIO.PARAMETRO não encontrou nenhum registro para CdParametro [%s]", pclParametro->getCdParametro());
            throw PPExceptionTMA(ERRO_EXECUCAO, tDataProc.szMessageException);
        }

        /* a logica eh assim: Cliente eh usuario da linha? Se sim, 1 noh, inUsuario = 1, se nao, 2 nohs, inUsuario = 0 */
        GETTREE(szAux, dnode, "inUsuario", 0, OBRIGATORIO, "inUsuario"); ULOG("inUsuario[%s]", szAux);
        if(!strcmp(szAux, "0"))
            bClienteUsuario=false;
        else if(!strcmp(szAux, "1"))
            bClienteUsuario=true;
        else
            throw PPExceptionTMA(ERRO_EXECUCAO, "inUsuario Invalido");


        /* Seta a quantidade de loops necessarios para obtencao dos dados do xml */
        if(bClienteUsuario == true)
            iQtdLoop=1;
        else
            iQtdLoop=2;


        /* Seta a localizacao dos dados no xml */
        if(!strcmp(tDataProc.szIdTipoPessoa, "1")) // 1 - Pessoa Fisica
            iIdTipoPessoaCtrl=1;
        else if(!strcmp(tDataProc.szIdTipoPessoa, "2")) // 2 - Pessoa Juridica
            iIdTipoPessoaCtrl=2;
        else
            throw PPExceptionTMA(ERRO_EXECUCAO, "iIdTipoPessoaCtrl invalido");

        for(iCountClientUser=0; iCountClientUser < 2; iCountClientUser++) {

            ULOG("iIdTipoPessoaCtrl(%d)iCountClientUser(%d)iIndexDOM(%d)", iIdTipoPessoaCtrl, iCountClientUser, iIndexDOM);

            /* Verifica o tipo de pessoa  1 - Fisica  2 - Juridica */
            if(iIdTipoPessoaCtrl == 1) { // Pessoa Fisica

                if((DNPessoaFisica = walkDOM(dnode, "PF", iIndexDOM))) {
                    ULOG("RSR PESSOA FISICA>>>");
                    GETTREE(tPessoaXML.szIdPessoa, DNPessoaFisica, "idPessoa", 0, NOBRIGATORIO, "idPessoa");
                    GETTREE(tPessoaXML.szNmPessoa, DNPessoaFisica, "nmPessoa", 0, OBRIGATORIO, "nmPessoa");
                    memset(&tDesmembraNome, 0x00, sizeof(TDesmembraNome));
                    strcpy(tDesmembraNome.szNomeCompleto, tPessoaXML.szNmPessoa);
                    DesmembraNome(&tDesmembraNome);
                    strcpy(tPessoaXML.szNmNome, tDesmembraNome.szNomePrimeiro);
                    strcpy(tPessoaXML.szNmNomeMeio, tDesmembraNome.szNomeMeio);
                    strcpy(tPessoaXML.szNmSobrenome, tDesmembraNome.szNomeFim);

                    // OS 1093 Pré-Pago - Troca de Titularidade deve aproveitar cliente antigo
                    if ( 0 == iCountClientUser && *(tDataProc.szDsTipoOperacao)=='T' && 0 == tPessoaXML.szIdPessoa[0] )
                    {
                        for(iIndexAux=0; (DNDocumento = walkDOM(DNPessoaFisica, "Documento", iIndexAux)) != NULL; iIndexAux++) {
                            memset(&tDocumentoXML, 0x00, sizeof(TDocumento));
                            GETTREE(tDocumentoXML.szIdTipoDocumento, DNDocumento, "idTipoDocumento", 0, OBRIGATORIO, "idTipoDocumento");
                            if ( pclDocumento->documentoIsRequired(tDocumentoXML.szIdTipoDocumento,"CPF") ) {

                                GETTREE(tDocumentoXML.szNrDocumento, DNDocumento, "nrDocumento", 0, OBRIGATORIO, "nrDocumento");
                                TrataNrDocumento(tDocumentoXML.szNrDocumento);

                                /*
								pclPessoaDocumento->buscaPessoaPessoaDocumento(tDocumentoXML.szNrDocumento
                                                                              ,tDocumentoXML.szIdTipoDocumento
                                                                              ,tPessoaXML.szNmNome
                                                                              ,tPessoaXML.szNmNomeMeio
                                                                              ,tPessoaXML.szNmSobrenome
                                                                              ,tPessoaXML.szIdPessoa);
								*/
								
                            } // if ( pclDocumento->documentoIsRequired(tDocumentoXML.szIdTipoDocumento,"CPF")
                        } // for(iIndexAux=0;...
                    } // if ( 0 == iCountClientUser && ...

                    GETTREE(tPessoaXML.szDsCargoContato, DNPessoaFisica, "dsProfissao", 0, NOBRIGATORIO, "dsProfissao");
                    pclPessoa->setStruct(&tPessoaXML);
                    pclPessoa->setIdTipoPessoa(tDataProc.szIdTipoPessoa);
                    pclPessoa->setIdTipoCarteira("13");
                    pclPessoa->setInFalecimentoInformado("0");
                    pclPessoa->setIdUf("1");
                    pclPessoa->setIdProbInadimplencia("5");
                    pclPessoa->setIdChurnProbabilidade("5");
                    pclPessoa->setIdUsuarioAlteracao(tDataProc.szUser);
                    pclPessoa->setIdSistemaOrigem(pclSistemaOrigem->getIdSistemaOrigem());

                    if(strlen(pclPessoa->getIdPessoa()) == 0) {
                        pclPessoa->inserePessoa();
                        ULOG("RSR INSERIU EM CUSTOMER.PESSOA COM IDPESSOA[%s]", pclPessoa->getIdPessoa());

                        pclPessoaDePara->setIdPessoa(pclPessoa->getIdPessoa());
                        pclPessoaDePara->setIdPessoaOrigem(pclPessoa->getIdPessoa());
                        pclPessoaDePara->setIdUsuarioAlteracao(tDataProc.szUser);
                        pclPessoaDePara->inserePessoaDePara();
                        ULOG("RSR INSERIU EM CUSTOMER.PESSOADEPARA COM IDPESSOADEPARA[%s]", pclPessoaDePara->getIdPessoaDePara());
                    }
                    else
                    {
                        /* soh verifica se o idpessoa estah na base */
                        tPessoaXML = pclPessoa->tPessoa;
                        if(pclPessoa->buscaPessoa(&tPessoaXML) == false) {
                            sprintf(tDataProc.szMessageException, "Nao ha registro em customer.pessoa para idpessoa[%s]", pclPessoa->getIdPessoa());
                            throw PPExceptionTMA(ERRO_EXECUCAO, tDataProc.szMessageException);
                        }
                        pclPessoa->atualizaPessoa();
                        ULOG("RSR ATUALIZOU EM CUSTOMER.PESSOA COM IDPESSOA[%s]", pclPessoa->getIdPessoa());

                        pclPessoaDePara->setIdPessoa(pclPessoa->getIdPessoa());
                        if(pclPessoaDePara->buscaPessoaDePara() == false) {
                            sprintf(tDataProc.szMessageException, "Nao ha registro em customer.pessoadepara para idpessoa[%s]", pclPessoa->getIdPessoa());
                            throw PPExceptionTMA(ERRO_EXECUCAO, tDataProc.szMessageException);
                        }
                        ULOG("RSR BUSCOU EM CUSTOMER.PESSOADEPARA COM IDPESSOA[%s]", pclPessoaDePara->getIdPessoa());
                    }

                    ULOG("iCountClientUser(%d)", iCountClientUser);
                    if(iCountClientUser == 0) {
                        pclXmlGenSetClient->addItem("tipoCliente", "P");
                        pclXmlGenSetClient->addItem("nome", pclPessoa->getNmPessoa());
                        pclXmlGenSetClient->addItem("confidencial", "N");
                    }

                    GETTREE(tPessoaFisicaXML.szIdSexo, DNPessoaFisica, "idSexo", 0, OBRIGATORIO, "idSexo");
                    GETTREE(tPessoaFisicaXML.szDtNascimento, DNPessoaFisica, "dtNascimento", 0, OBRIGATORIO, "dtNascimento");
                    ConvDataFromJava(tPessoaFisicaXML.szDtNascimento);
                    GETTREE(tPessoaFisicaXML.szIdEstadoCivil, DNPessoaFisica, "idEstadoCivil", 0, NOBRIGATORIO, "idEstadoCivil");
                    if(strlen(tPessoaFisicaXML.szIdEstadoCivil) == 0)
                        strcpy(tPessoaFisicaXML.szIdEstadoCivil, "0");
                    pclPessoaFisica->setStruct(&tPessoaFisicaXML);
                    pclPessoaFisica->setIdPais("1");
                    pclPessoaFisica->setIdTratamento("0");
                    pclPessoaFisica->setIdPessoa(pclPessoa->getIdPessoa());
                    pclPessoaFisica->setIdUsuarioAlteracao(tDataProc.szUser);

                    TPessoaFisica tPessoaFisicaTmp = pclPessoaFisica->tPessoaFisica;
                    if(pclPessoaFisica->buscaPessoaFisica(&tPessoaFisicaTmp) == true) {
                        pclPessoaFisica->atualizaPessoaFisica();
                        ULOG("RSR ATUALIZOU EM CUSTOMER.PESSOAFISICA COM IDPESSOA[%s]", pclPessoaFisica->getIdPessoa());
                    }
                    else {
                        pclPessoaFisica->inserePessoaFisica();
                        ULOG("RSR INSERIU EM CUSTOMER.PESSOAFISICA COM IDPESSOA[%s]", pclPessoaFisica->getIdPessoa());
                    }

                    ULOG("iCountClientUser(%d)", iCountClientUser);
                    if(iCountClientUser == 0) {
                        GETTREE(szAux, DNPessoaFisica, "dtNascimento", 0, OBRIGATORIO, "dtNascimento");
                        pclXmlGenSetClient->addItem("dataNascimento", szAux);

                        if(!strcmp(pclPessoaFisica->getIdEstadoCivil(), ID_TIPO_EC_SOLTEIRO))
                            pclXmlGenSetClient->addItem("estadoCivil", SG_TIPO_EC_SOLTEIRO);
                        else if(!strcmp(pclPessoaFisica->getIdEstadoCivil(), ID_TIPO_EC_CASADO))
                            pclXmlGenSetClient->addItem("estadoCivil", SG_TIPO_EC_CASADO);
                        else if(!strcmp(pclPessoaFisica->getIdEstadoCivil(), ID_TIPO_EC_VIUVO))
                            pclXmlGenSetClient->addItem("estadoCivil", SG_TIPO_EC_VIUVO);
                        else if(!strcmp(pclPessoaFisica->getIdEstadoCivil(), ID_TIPO_EC_DIVORCIADO))
                            pclXmlGenSetClient->addItem("estadoCivil", SG_TIPO_EC_DIVORCIADO);
                        else
                            pclXmlGenSetClient->addItem("estadoCivil", "");

                        if(!strcmp(pclPessoaFisica->getIdSexo(), ID_SEXO_MASCULINO))
                            pclXmlGenSetClient->addItem("codSexo", SG_SEXO_MASCULINO);
                        else if(!strcmp(pclPessoaFisica->getIdSexo(), ID_SEXO_FEMININO))
                            pclXmlGenSetClient->addItem("codSexo", SG_SEXO_FEMININO);
                        else
                            pclXmlGenSetClient->addItem("codSexo", "");
                    }


                    GETTREE(tPessoaValorPossivelXML.szIdValorPossivel, DNPessoaFisica, "idEscolaridade", 0, NOBRIGATORIO, "idEscolaridade");
                    if(strlen(tPessoaValorPossivelXML.szIdValorPossivel) > 0) {
                        pclPessoaValorPossivel->setIdPessoa(pclPessoa->getIdPessoa());
                        pclPessoaValorPossivel->apagaEscolaridade();

                        pclPessoaValorPossivel->setStruct(&tPessoaValorPossivelXML);
                        pclPessoaValorPossivel->setIdPessoa(pclPessoa->getIdPessoa());
                        pclPessoaValorPossivel->setIdUsuarioAlteracao(tDataProc.szUser);
                        pclPessoaValorPossivel->inserePessoaValorPossivel();
                        ULOG("RSR INSERIU EM CUSTOMER.PESSOAVALORPOSSIVEL COM IDPESSOAVALORPOSSIVEL[%s]", pclPessoaValorPossivel->getIdPessoaValorPossivel());
                        pclPessoaValorPossivel->limpaPessoaValorPossivel();
                    }

                    GETTREE(tPessoaValorPossivelXML.szIdValorPossivel, DNPessoaFisica, "idNatOcupacao", 0, NOBRIGATORIO, "idNatOcupacao");
                    if(strlen(tPessoaValorPossivelXML.szIdValorPossivel) > 0) {
                        pclPessoaValorPossivel->setIdPessoa(pclPessoa->getIdPessoa());
                        pclPessoaValorPossivel->apagaOcupacao();

                        pclPessoaValorPossivel->setStruct(&tPessoaValorPossivelXML);
                        pclPessoaValorPossivel->setIdPessoa(pclPessoa->getIdPessoa());
                        pclPessoaValorPossivel->setIdUsuarioAlteracao(tDataProc.szUser);
                        pclPessoaValorPossivel->inserePessoaValorPossivel();
                        ULOG("RSR INSERIU EM CUSTOMER.PESSOAVALORPOSSIVEL COM IDPESSOAVALORPOSSIVEL[%s]", pclPessoaValorPossivel->getIdPessoaValorPossivel());
                        pclPessoaValorPossivel->limpaPessoaValorPossivel();
                    }
                    else {
                        pclPessoaValorPossivel->setIdPessoa(pclPessoa->getIdPessoa());
                        pclPessoaValorPossivel->apagaOcupacao();
                    }


                    // Referente ao Email Comercial
                    GETTREE(szAux, DNPessoaFisica, "nmEmailComercial", 0, NOBRIGATORIO, "nmEmailComercial");
                    if(strlen(szAux) > 0)
                    {
                        memset(&tPessoaComunicacaoXML, 0x00, sizeof(TPessoaComunicacao));
                        strcpy(tPessoaComunicacaoXML.szIdPessoa, pclPessoa->getIdPessoa());
                        strcpy(tPessoaComunicacaoXML.szIdTipoComunicacao, ID_TIPO_COMUNICACAO_EMAIL_COME);
                        if(pclPessoaComunicacao->buscaPessoaComunicacaoIdPessoaSgClass(&tPessoaComunicacaoXML) == true) {
                            ULOG("RSR BUSCOU EM CUSTOMER.PESSOACOMUNICACAO COM IDPESSOACOMUNICACAO[%s] EM COM", pclPessoaComunicacao->getIdPessoaComunicacao());
                            pclPessoaComunicacao->setStruct(&tPessoaComunicacaoXML);
                            pclPessoaComunicacao->setIdPessoa(pclPessoa->getIdPessoa());
                            pclPessoaComunicacao->setIdUsuarioAlteracao(tDataProc.szUser);
                            pclPessoaComunicacao->setIdSistemaOrigem(pclSistemaOrigem->getIdSistemaOrigem());
                            pclPessoaComunicacao->setDsContato(szAux);

                            pclPessoaComunicacao->atualizaPessoaComunicacao();
                            ULOG("RSR ATUALIZOU EM CUSTOMER.PESSOACOMUNICACAO COM IDPESSOACOMUNICACAO[%s]", pclPessoaComunicacao->getIdPessoaComunicacao());
                        }
                        else
                        {
                            strcpy(tPessoaComunicacaoXML.szIdTipoComunicacao, ID_TIPO_COMUNICACAO_EMAIL_COME);
                            GETTREE(tPessoaComunicacaoXML.szDsContato, DNPessoaFisica, "nmEmailComercial", 0, NOBRIGATORIO, "nmEmailComercial");

                            pclPessoaComunicacao->setStruct(&tPessoaComunicacaoXML);
                            pclPessoaComunicacao->setIdPessoa(pclPessoa->getIdPessoa());
                            pclPessoaComunicacao->setIdUsuarioAlteracao(tDataProc.szUser);
                            pclPessoaComunicacao->setIdSistemaOrigem(pclSistemaOrigem->getIdSistemaOrigem());
                            pclPessoaComunicacao->setDsContato(szAux);

                            pclPessoaComunicacao->inserePessoaComunicacao();
                            ULOG("RSR INSERIU EM CUSTOMER.PESSOACOMUNICACAO COM IDPESSOACOMUNICACAO[%s]", pclPessoaComunicacao->getIdPessoaComunicacao());
                        }

                        if(iCountClientUser == 0) {
                            pclXmlGenSetClient->addItem("eMail", pclPessoaComunicacao->getDsContato());
                            pclXmlGenSetClient->addItem("numDepend", "");
                        }
                    }

                    // Referente ao Email Particular
                    GETTREE(szAux, DNPessoaFisica, "nmEmailParticular", 0, NOBRIGATORIO, "nmEmailParticular");
                    if(strlen(szAux) > 0)
                    {
                        memset(&tPessoaComunicacaoXML, 0x00, sizeof(TPessoaComunicacao));
                        strcpy(tPessoaComunicacaoXML.szIdPessoa, pclPessoa->getIdPessoa());
                        strcpy(tPessoaComunicacaoXML.szIdTipoComunicacao, ID_TIPO_COMUNICACAO_EMAIL_PART);
                        if(pclPessoaComunicacao->buscaPessoaComunicacaoIdPessoaSgClass(&tPessoaComunicacaoXML) == true) {
                            ULOG("RSR BUSCOU EM CUSTOMER.PESSOACOMUNICACAO COM IDPESSOACOMUNICACAO[%s] EM PART", pclPessoaComunicacao->getIdPessoaComunicacao());
                            pclPessoaComunicacao->setStruct(&tPessoaComunicacaoXML);
                            pclPessoaComunicacao->setIdPessoa(pclPessoa->getIdPessoa());
                            pclPessoaComunicacao->setIdUsuarioAlteracao(tDataProc.szUser);
                            pclPessoaComunicacao->setIdSistemaOrigem(pclSistemaOrigem->getIdSistemaOrigem());
                            pclPessoaComunicacao->setDsContato(szAux);

                            pclPessoaComunicacao->atualizaPessoaComunicacao();
                            ULOG("RSR ATUALIZOU EM CUSTOMER.PESSOACOMUNICACAO COM IDPESSOACOMUNICACAO[%s]", pclPessoaComunicacao->getIdPessoaComunicacao());
                        }
                        else
                        {
                            strcpy(tPessoaComunicacaoXML.szIdTipoComunicacao, ID_TIPO_COMUNICACAO_EMAIL_PART);
                            GETTREE(tPessoaComunicacaoXML.szDsContato, DNPessoaFisica, "nmEmailParticular", 0, NOBRIGATORIO, "nmEmailParticular");

                            pclPessoaComunicacao->setStruct(&tPessoaComunicacaoXML);
                            pclPessoaComunicacao->setIdPessoa(pclPessoa->getIdPessoa());
                            pclPessoaComunicacao->setIdUsuarioAlteracao(tDataProc.szUser);
                            pclPessoaComunicacao->setIdSistemaOrigem(pclSistemaOrigem->getIdSistemaOrigem());
                            pclPessoaComunicacao->setDsContato(szAux);

                            pclPessoaComunicacao->inserePessoaComunicacao();
                            ULOG("RSR INSERIU EM CUSTOMER.PESSOACOMUNICACAO COM IDPESSOACOMUNICACAO[%s]", pclPessoaComunicacao->getIdPessoaComunicacao());
                        }
                        //if(iCountClientUser == 0) {
                        //    pclXmlGenSetClient->addItem("eMail", pclPessoaComunicacao->getDsContato());
                        //    pclXmlGenSetClient->addItem("numDepend", "");
                        //}
                    }


                    // Referente ao documento CPR
                    GETTREE(szAux, DNPessoaFisica, "nrCPR", 0, NOBRIGATORIO, "nrCPR");
                    if(strlen(szAux) > 0)
                    {
                        ULOG("szIdPessoaCtrl[%s]", szIdPessoaCtrl);
                        ULOG("pclPessoa->getIdPessoa()[%s]", pclPessoa->getIdPessoa());
                        if((pclPessoaDocumento->apagouPessoaDocumento() == false) || (strcmp(pclPessoa->getIdPessoa(), szIdPessoaCtrl)))
                        {
                            strcpy(szIdPessoaCtrl, pclPessoa->getIdPessoa());

                            pclPessoaDocumento->setIdPessoa(pclPessoa->getIdPessoa());
                            pclPessoaDocumento->setIdUsuarioAlteracao(tDataProc.szUser);
                            pclPessoaDocumento->setIdSistemaOrigem(pclSistemaOrigem->getIdSistemaOrigem());
                            pclPessoaDocumento->apagaPessoaDocumento();
                            ULOG("RSR APAGOU CUSTOMER.PESSOADOCUMENTO COM IDPESSOA[%s]", pclPessoaDocumento->getIdPessoa());
                        }

                        TrataNrDocumento(szAux);
                        memset(&tDocumentoXML, 0x00, sizeof(TDocumento));
                        strcpy(tDocumentoXML.szNrDocumento, szAux);
                        strcpy(tDocumentoXML.szIdUf, "0");
                        strcpy(tDocumentoXML.szIdTipoDocumento, "30"); // CPR
                        pclTipoDocumento->setIdTipoDocumento(tDocumentoXML.szIdTipoDocumento);
                        if(pclTipoDocumento->buscaTipoDocumento() == false) {
                            sprintf(tDataProc.szMessageException, "Nao ha registro em apoio.tipodocumento para idtipodocumento[%s]", tDocumentoXML.szIdTipoDocumento);
                            throw PPExceptionTMA(ERRO_EXECUCAO, tDataProc.szMessageException);
                        }
                        ULOG("RSR BUSCOU EM APOIO.TIPODOCUMENTO COM IDTIPODOCUMENTO[%s]", pclTipoDocumento->getIdTipoDocumento());

                        pclDocumento->setStruct(&tDocumentoXML);
                        pclDocumento->setIdPais("1");
                        pclDocumento->setIdUsuarioAlteracao(tDataProc.szUser);

                        tDocumentoTmp = pclDocumento->tDocumento;
                        // Busca Documento por NrDocumento, IdTipoDocumento, IdUf e SgOrgaoexpedidor
                        if(pclDocumento->buscaDocumentoChaveComposta(&tDocumentoTmp) == false) {
                            pclDocumento->insereDocumento();
                            ULOG("RSR INSERIU EM CUSTOMER.DOCUMENTO COM IDDOCUMENTO[%s]", pclDocumento->getIdDocumento());
                            pclPessoaDocumento->setIdDocumento(pclDocumento->getIdDocumento());
                        }
                        else
                            pclPessoaDocumento->setIdDocumento(tDocumentoTmp.szIdDocumento);

                        // pclPessoaDocumento->setIdDocumentoSistemaOrigem(pclTipoDocumento->getSgClassificacao());
                        pclPessoaDocumento->inserePessoaDocumento();
                        ULOG("RSR INSERIU EM CUSTOMER.PESSOADOCUMENTO COM IDPESSOADOCUMENTO[%s]IDDOCUMENTO[%s]", pclPessoaDocumento->getIdPessoaDocumento(), pclPessoaDocumento->getIdDocumento());
                    }


                    for(iIndexAux=0; (DNTelefonePF = walkDOM(DNPessoaFisica, "Telefone", iIndexAux)) != NULL; iIndexAux++) {
                        ULOG("RSR TELEFONE PESSOA FISICA>>>"); ULOG("iIndexAux(%d)", iIndexAux);
                        memset(&tPessoaComunicacaoXML, 0x00, sizeof(TPessoaComunicacao));
                        GETTREE(tPessoaComunicacaoXML.szIdPessoaComunicacao, DNTelefonePF, "idContato", 0, NOBRIGATORIO, "idContato");
                        GETTREE(tPessoaComunicacaoXML.szIdTipoComunicacao, DNTelefonePF, "idTipoTelefone", 0, OBRIGATORIO, "idTipoTelefone");
                        GETTREE(tPessoaComunicacaoXML.szDsContato, DNTelefonePF, "nrTelefone", 0, OBRIGATORIO, "nrTelefone");
                        GETTREE(tPessoaComunicacaoXML.szNmContato, DNTelefonePF, "nmContato", 0, OBRIGATORIO, "nmContato");

                        pclPessoaComunicacao->setStruct(&tPessoaComunicacaoXML);
                        pclPessoaComunicacao->setIdPessoa(pclPessoa->getIdPessoa());
                        pclPessoaComunicacao->setIdUsuarioAlteracao(tDataProc.szUser);
                        pclPessoaComunicacao->setIdSistemaOrigem(pclSistemaOrigem->getIdSistemaOrigem());

                        if(strlen(pclPessoaComunicacao->getIdPessoaComunicacao()) == 0) {
                            pclPessoaComunicacao->inserePessoaComunicacao();
                            ULOG("RSR INSERIU EM CUSTOMER.PESSOACOMUNICACAO COM IDPESSOACOMUNICACAO[%s]", pclPessoaComunicacao->getIdPessoaComunicacao());
                        }
                        else {
                            pclPessoaComunicacao->atualizaPessoaComunicacao();
                            ULOG("RSR ATUALIZOU EM CUSTOMER.PESSOACOMUNICACAO COM IDPESSOACOMUNICACAO[%s]", pclPessoaComunicacao->getIdPessoaComunicacao());
                        }

                        ULOG("iCountClientUser(%d)", iCountClientUser);
                        if(iCountClientUser == 0) {
                            if((!strcmp(pclPessoaComunicacao->getIdTipoComunicacao(), ID_TIPO_COMUNICACAO_TEL_RES)) ||
                               (!strcmp(pclPessoaComunicacao->getIdTipoComunicacao(), ID_TIPO_COMUNICACAO_TEL_COM)))
                                pclXmlGenSetClient->addItem("telefone", pclPessoaComunicacao->getNmContato());
                            else if(!strcmp(pclPessoaComunicacao->getIdTipoComunicacao(), ID_TIPO_COMUNICACAO_FAX))
                                pclXmlGenSetClient->addItem("fax", pclPessoaComunicacao->getNmContato());
                            else if(!strcmp(pclPessoaComunicacao->getIdTipoComunicacao(), ID_TIPO_COMUNICACAO_EMAIL_PART)) {
                                pclXmlGenSetClient->addItem("eMail", pclPessoaComunicacao->getNmContato());
                                pclXmlGenSetClient->addItem("numDepend", "");
                            }
                            else if(!strcmp(pclPessoaComunicacao->getIdTipoComunicacao(), ID_TIPO_COMUNICACAO_PASSAPORTE))
                                pclXmlGenSetClient->addItem("passaporte", pclPessoaComunicacao->getNmContato());

                            //pclXmlGenSetClient->addItem("numDepend", "");
                        }
                        ULOG("RSR TELEFONE PESSOA FISICA<<<"); ULOG("iIndexAux(%d)", iIndexAux);
                    }

                    for(iIndexAux=0; (DNDocumento = walkDOM(DNPessoaFisica, "Documento", iIndexAux)) != NULL; iIndexAux++) {
                        ULOG("RSR DOCUMENTO PESSOA FISICA>>>"); ULOG("iIndexAux(%d)", iIndexAux);

                        ULOG("szIdPessoaCtrl[%s]", szIdPessoaCtrl);
                        ULOG("pclPessoa->getIdPessoa()[%s]", pclPessoa->getIdPessoa());
                        if((pclPessoaDocumento->apagouPessoaDocumento() == false) || (strcmp(pclPessoa->getIdPessoa(), szIdPessoaCtrl)))
                        {
                            strcpy(szIdPessoaCtrl, pclPessoa->getIdPessoa());

                            pclPessoaDocumento->setIdPessoa(pclPessoa->getIdPessoa());
                            pclPessoaDocumento->setIdUsuarioAlteracao(tDataProc.szUser);
                            pclPessoaDocumento->setIdSistemaOrigem(pclSistemaOrigem->getIdSistemaOrigem());
                            pclPessoaDocumento->apagaPessoaDocumento();
                            ULOG("RSR APAGOU CUSTOMER.PESSOADOCUMENTO COM IDPESSOA[%s]", pclPessoaDocumento->getIdPessoa());
                        }

                        // Referente ao documento do XML
                        memset(&tDocumentoXML, 0x00, sizeof(TDocumento));
                        GETTREE(tDocumentoXML.szIdDocumento, DNDocumento, "idDocumento", 0, NOBRIGATORIO, "idDocumento");
                        GETTREE(tDocumentoXML.szIdTipoDocumento, DNDocumento, "idTipoDocumento", 0, OBRIGATORIO, "idTipoDocumento");

                        pclTipoDocumento->setIdTipoDocumento(tDocumentoXML.szIdTipoDocumento);
                        if(pclTipoDocumento->buscaTipoDocumento() == false) {
                            sprintf(tDataProc.szMessageException, "Nao ha registro em apoio.tipodocumento para idtipodocumento[%s]", tDocumentoXML.szIdTipoDocumento);
                            throw PPExceptionTMA(ERRO_EXECUCAO, tDataProc.szMessageException);
                        }
                        ULOG("RSR BUSCOU EM APOIO.TIPODOCUMENTO COM IDTIPODOCUMENTO[%s]", pclTipoDocumento->getIdTipoDocumento());

                        GETTREE(tDocumentoXML.szNrDocumento, DNDocumento, "nrDocumento", 0, OBRIGATORIO, "nrDocumento");
                        TrataNrDocumento(tDocumentoXML.szNrDocumento);
                        GETTREE(tDocumentoXML.szDtEmissao, DNDocumento, "dtExpedicao", 0, NOBRIGATORIO, "dtExpedicao");
                        ConvDataFromJava(tDocumentoXML.szDtEmissao);
                        GETTREE(tDocumentoXML.szSgOrgaoExpedidor, DNDocumento, "dsOrgaoEmissor", 0, NOBRIGATORIO, "dsOrgaoEmissor");
                        GETTREE(tDocumentoXML.szIdUf, DNDocumento, "idUFDocumento", 0, NOBRIGATORIO, "idUFDocumento");
                        if(strlen(tDocumentoXML.szIdUf) == 0)
                            strcpy(tDocumentoXML.szIdUf, "0");
                        pclDocumento->setStruct(&tDocumentoXML);
                        pclDocumento->setIdPais("1");
                        pclDocumento->setIdUsuarioAlteracao(tDataProc.szUser);

                        tDocumentoTmp = pclDocumento->tDocumento;
                        // Busca Documento por NrDocumento, IdTipoDocumento, IdUf e SgOrgaoexpedidor
                        if(pclDocumento->buscaDocumentoChaveComposta(&tDocumentoTmp) == false) {
                            pclDocumento->insereDocumento();
                            ULOG("RSR INSERIU EM CUSTOMER.DOCUMENTO COM IDDOCUMENTO[%s]", pclDocumento->getIdDocumento());
                            pclPessoaDocumento->setIdDocumento(pclDocumento->getIdDocumento());
                        }
                        else {
                            pclPessoaDocumento->setIdDocumento(tDocumentoTmp.szIdDocumento);
                        }

                        // pclPessoaDocumento->setIdDocumentoSistemaOrigem(pclTipoDocumento->getSgClassificacao());
                        if ( pclPessoaDocumento->existePessoaDocumento(pclPessoaDocumento->getIdDocumento(),tPessoaXML.szIdPessoa) ) {
                            pclPessoaDocumento->apagaPessoaDocumentoEspecifico(pclPessoaDocumento->getIdDocumento(),tPessoaXML.szIdPessoa);
                            ULOG("RSR REMOVEU DE CUSTOMER.PESSOADOCUMENTO COM IDDOCUMENTO=[%s] e IDPESSOA=[%s]",pclPessoaDocumento->getIdDocumento(),tPessoaXML.szIdPessoa);
                        }

                        pclPessoaDocumento->inserePessoaDocumento();
                        ULOG("RSR INSERIU EM CUSTOMER.PESSOADOCUMENTO COM IDPESSOADOCUMENTO[%s]", pclPessoaDocumento->getIdPessoaDocumento());

                        ULOG("iCountClientUser(%d)", iCountClientUser);
                        if(iCountClientUser == 0) {
                            if((!strcmp(pclTipoDocumento->getSgTipoDocumento(), SG_CPF)) ||
                               (!strcmp(pclTipoDocumento->getSgTipoDocumento(), SG_FCPF)) ||
                               (!strcmp(pclTipoDocumento->getSgTipoDocumento(), SG_KLCPF)))
                            {
                                strcpy(szAux, pclDocumento->getNrDocumento());
                                TrataNrDocumento(szAux);
                                szAux[LEN_CPF_LEGADO]=0x00;
                                if(strlen(szAux) == 0)
                                    strcpy(szAux, "0");
                                pclXmlGenSetClient->addItem("CPF", szAux);
                                pclXmlGenSetClient->addItem("tipoCPF", "D");
                            }
                            else if(!strcmp(pclTipoDocumento->getSgTipoDocumento(), SG_RG))
                            {
                                strcpy(szAux, pclDocumento->getNrDocumento());
                                TrataNrDocumento(szAux);
                                szAux[LEN_RG_LEGADO]=0x00;
                                if(strlen(szAux) == 0)
                                    strcpy(szAux, "0");
                                pclXmlGenSetClient->addItem("RG", szAux);
                                pclXmlGenSetClient->addItem("tipoRG", "D");
                                GETTREE(szAux, DNDocumento, "dtExpedicao", 0, NOBRIGATORIO, "dtExpedicao");
                                pclXmlGenSetClient->addItem("dataExpiracao", szAux);
                                pclXmlGenSetClient->addItem("orgaoExpeditor", pclDocumento->getSgOrgaoExpeditor());

                                pclUF->setIdUF(pclDocumento->getIdUF());
                                if(pclUF->buscaUF() == true) {
                                    strcpy(szAux, pclUF->getSgUF());
                                    ULOG("1.estadoExpedicao szAux[%s]", szAux);
                                    szAux[LEN_ESTADO_EXPEDICAO_LEGADO]=0x00;
                                    ULOG("2.estadoExpedicao szAux[%s]", szAux);

                                    if(strcmp(szAux, "NC"))
                                        pclXmlGenSetClient->addItem("estadoExpedicao", szAux);
                                }
                            }
                            else if(!strcmp(pclTipoDocumento->getSgTipoDocumento(), SG_PASS))
                            {
                                strcpy(szAux, pclDocumento->getNrDocumento());
                                TrataNrDocumento(szAux);
                                szAux[LEN_PASSAPORTE_LEGADO]=0x00;
                                if(strlen(szAux) == 0 )
                                    strcpy(szAux, "0");
                                pclXmlGenSetClient->addItem("passaporte", szAux);
                                pclXmlGenSetClient->addItem("tipoPassaporte", "D");
                            }
                        } //if(iCountClientUser == 0)
                        ULOG("RSR DOCUMENTO PESSOA FISICA<<<"); ULOG("iIndexAux(%d)", iIndexAux);
                    }
                    if(iIndexAux == 0)
                        throw PPExceptionTMA(ERRO_EXECUCAO, "Noh Documento inexistente!");

                    for(iIndexAux=0; (DNDadosEnderecoPF = walkDOM(DNPessoaFisica, "DadosEndereco", iIndexAux)) != NULL; iIndexAux++) {

                        ULOG("RSR DADOSENDERECO PESSOA FISICA>>>"); ULOG("iIndexAux(%d)", iIndexAux);

                        //if ( 0 == iIndexAux && (*(tDataProc.szDsTipoOperacao)=='C'||*(tDataProc.szDsTipoOperacao)=='T') ) {
                        //    pclPessoaEndereco->excluirEndereco(pclPessoa->getIdPessoa());
                        //}

                        GETTREE(tPessoaEnderecoXML.szIdPessoaEndereco, DNDadosEnderecoPF, "idEndereco", 0, NOBRIGATORIO, "");
                        GETTREE(szInEndPrincipal, DNDadosEnderecoPF, "inEndPrincipal", 0, OBRIGATORIO, "inEndPrincipal");
                        GETTREE(tPessoaEnderecoXML.szIdTipoEndereco, DNDadosEnderecoPF, "idTipoEndereco", 0, OBRIGATORIO, "idTipoEndereco");
                        GETTREE(tPessoaEnderecoXML.szNmTipoLogradouro, DNDadosEnderecoPF, "nmTipoLogradouro", 0, OBRIGATORIO, "nmTipoLogradouro");
                        GETTREE(tPessoaEnderecoXML.szNmTituloLogradouro, DNDadosEnderecoPF, "nmTitLogradouro", 0, NOBRIGATORIO, "");
                        GETTREE(tPessoaEnderecoXML.szNrCep, DNDadosEnderecoPF, "nrCEP", 0, OBRIGATORIO, "nrCEP");
                        strcpy( nrCEP, tPessoaEnderecoXML.szNrCep );

                        GETTREE(tPessoaEnderecoXML.szNmLogradouro, DNDadosEnderecoPF, "nmLogradouro", 0, OBRIGATORIO, "nmLogradouro");
                        strcpy( nmLograd,tPessoaEnderecoXML.szNmLogradouro );

                        GETTREE(tPessoaEnderecoXML.szcdLogradouro, DNDadosEnderecoPF, "codLogradouro", 0, NOBRIGATORIO, "codLogradouro");
                        GETTREE(tPessoaEnderecoXML.szInCnl, DNDadosEnderecoPF, "inCnl", 0, NOBRIGATORIO, "inCnl");
                        GETTREE(tPessoaEnderecoXML.szcdIbge, DNDadosEnderecoPF, "inCodigoIBGE", 0, NOBRIGATORIO, "inCodigoIBGE");

                        GETTREE(tPessoaEnderecoXML.szNrEndereco, DNDadosEnderecoPF, "nrLogradouro", 0, OBRIGATORIO, "nrLogradouro");
                        GETTREE(tPessoaEnderecoXML.szDsEnderecoComplemento, DNDadosEnderecoPF, "nmComplemento", 0, NOBRIGATORIO, "");

                        GETTREE(tPessoaEnderecoXML.szNmBairro, DNDadosEnderecoPF, "nmBairro", 0, OBRIGATORIO, "nmBairro");
                        strcpy( nmBairro,tPessoaEnderecoXML.szNmBairro );

                        GETTREE(tPessoaEnderecoXML.szNmMunicipio, DNDadosEnderecoPF, "nmMunicipio", 0, OBRIGATORIO, "nmMunicipio");
                        strcpy( nmCidade, tPessoaEnderecoXML.szNmMunicipio );

                        GETTREE(tPessoaEnderecoXML.szIdUf, DNDadosEnderecoPF, "idUF", 0, OBRIGATORIO, "idUF");

                        i_iduf=atoi(tPessoaEnderecoXML.szIdUf);

                        pclPessoaEndereco->setStruct(&tPessoaEnderecoXML);
                        pclPessoaEndereco->setIdPessoa(pclPessoa->getIdPessoa());
                        pclPessoaEndereco->setIdUsuarioAlteracao(tDataProc.szUser);
                        pclPessoaEndereco->setIdSistemaOrigem(pclSistemaOrigem->getIdSistemaOrigem());
                        pclPessoaEndereco->setIdPais("1");
                        pclPessoaEndereco->BuscaSiglaUF( i_iduf );
                        strcpy( tPessoaEnderecoXML.szIdUf, pclPessoaEndereco->getSgUF() );
                        char idPessoaEnderecoAux[39];

                        //if(strlen(pclPessoaEndereco->getIdPessoaEndereco()) == 0) {
                        if( pclPessoaEndereco->existePessoaEndereco(pclPessoa->getIdPessoa(),idPessoaEnderecoAux) == false ) {
                            pclPessoaEndereco->inserePessoaEndereco();
                            ULOG("RSR INSERIU EM CUSTOMER.PESSOAENDERECO COM IDPESSOAENDERECO[%s]", pclPessoaEndereco->getIdPessoaEndereco());
                        }
                        else {
                            pclPessoaEndereco->setIdPessoaEndereco(idPessoaEnderecoAux);
                            pclPessoaEndereco->atualizaPessoaEndereco();
                            ULOG("RSR ATUALIZOU EM CUSTOMER.PESSOAENDERECO COM IDPESSOAENDERECO[%s]", pclPessoaEndereco->getIdPessoaEndereco());
                        }

                        ULOG("iCountClientUser(%d)", iCountClientUser);
                        /* soh envia para o NGIN os dados do endereco principal */
                        if((!strcmp(szInEndPrincipal, "1")) && (iCountClientUser == 0)) {

                            if(bEndPrincipal == true)
                                throw PPExceptionTMA(ERRO_EXECUCAO, "Endereco principal jah existe!");

                            bEndPrincipal=true;
                            strcpy(szIdPessoaEnderecoEndPrincipal, pclPessoaEndereco->getIdPessoaEndereco());
                            ULOG("szIdPessoaEnderecoEndPrincipal Pessoa Fisica[%s]", szIdPessoaEnderecoEndPrincipal);

                            strcpy(szAux, pclPessoaEndereco->getNmTipoLogradouro());
                            ULOG("*** 1.Logradouro szAux[%s]", szAux);
                            alltrim(szAux);
                            ULOG("*** 2.Logradouro szAux[%s]", szAux);

                            memset(szAuxNGIN, 0x00, sizeof(szAuxNGIN));
                            for(iCountLogradouro=0; pszTipoLogFO[iCountLogradouro] != NULL; iCountLogradouro++) {
                                if(!strcasecmp(pszTipoLogFO[iCountLogradouro], szAux)) {
                                    strcpy(szAuxNGIN, pszTipoLogNGIN[iCountLogradouro]);
                                    break;
                                }
                            }
                            ULOG("*** 3.Logradouro szAuxNGIN[%s]", szAuxNGIN);
                            pclXmlGenSetClient->addItem("logradouro", szAuxNGIN);

                            memset(szArruamento, 0x00, sizeof(szArruamento));
                            if(strlen(szAuxNGIN) > 0)
                                strcpy(szArruamento, szAuxNGIN);

                            ULOG("1.szArruamento[%s]", szArruamento);

                            if(strlen(pclPessoaEndereco->getNmTituloLogradouro()) > 0) {
                                strcat(szArruamento, " ");
                                strcat(szArruamento, pclPessoaEndereco->getNmTituloLogradouro());
                            }
                            ULOG("2.szArruamento[%s]", szArruamento);

                            if(strlen(pclPessoaEndereco->getNmLogradouro()) > 0) {
                                strcat(szArruamento, " ");
                                strcat(szArruamento, pclPessoaEndereco->getNmLogradouro());
                            }
                            ULOG("3.szArruamento[%s]", szArruamento);
                            szArruamento[LEN_ARRUAMENTO_LEGADO]=0x00;
                            ULOG("4.szArruamento[%s]", szArruamento);
                            pclXmlGenSetClient->addItem("endereco", szArruamento);

                            // SPED
                            ULOG( "nrCEP    [%s]",nrCEP );
                            ULOG( "nmLograd [%s]",nmLograd );
                            ULOG( "nmBairro [%s]",nmBairro );
                            ULOG( "nmCidade [%s]",nmCidade );
                            pclPessoaEndereco->ProcuraSPED(nrCEP, nmLograd, nmBairro, nmCidade );
                            if (strlen(pclPessoaEndereco->getCdLogradouro()) > 0)
                            {
                                pclXmlGenSetClient->addItem("cdLogradouro", pclPessoaEndereco->getCdLogradouro());
                            }
                            
                            if (strlen(pclPessoaEndereco->getInCNL()) > 0)
                            {
                                pclXmlGenSetClient->addItem("inCnl", pclPessoaEndereco->getInCNL());
                            }

                            if (strlen(pclPessoaEndereco->getCdIbge()) > 0)
                            {
                                pclXmlGenSetClient->addItem("cdIbge", pclPessoaEndereco->getCdIbge());
                            }

                            pclXmlGenSetClient->addItem("complemento", pclPessoaEndereco->getDsEnderecoComplemento());
                            pclXmlGenSetClient->addItem("bairro", pclPessoaEndereco->getNmBairro());

                            strcpy(szAux, pclPessoaEndereco->getNrCep());
                            TrataNrDocumento(szAux);
                            szAux[LEN_CEP_LEGADO]=0x00;
                            pclXmlGenSetClient->addItem("CEP", szAux);

                            pclXmlGenSetClient->addItem("cidade", pclPessoaEndereco->getNmMunicipio());
                            pclXmlGenSetClient->addItem("estado", pclPessoaEndereco->getSgUF());
                            pclXmlGenSetClient->addItem("pais", "BRA");
                            pclXmlGenSetClient->addItem("numero", pclPessoaEndereco->getNrEndereco());

                            char cdAreaRegistro[3];
                            memcpy(cdAreaRegistro, tDataProc.szNrLinha, 2);
                            cdAreaRegistro[2]=0x00;

                            cFilaPreEnvioSetCliInfopc.setIdPessoaEndereco(pclPessoaEndereco->getIdPessoaEndereco());
                            cFilaPreEnvioSetCliInfopc.setNrLinha(tDataProc.szNrLinha + 2);
                            cFilaPreEnvioSetCliInfopc.setIdUsuarioAlteracao(tDataProc.szUser);

                            cFilaPreEnvioSetCliInfopc.inserir(cdAreaRegistro);

                        } //if(!strcmp((szInEndPrincipal, "1")) && (iCountClientUser == 0))
                        ULOG("RSR DADOSENDERECO PESSOA FISICA<<<"); ULOG("iIndexAux(%d)", iIndexAux);
                    }
                    ULOG("RSR PESSOA FISICA<<<");
                }
                else
                    throw PPExceptionTMA(ERRO_EXECUCAO, "Noh PF inexistente!");

            } //if(iIdTipoPessoaCtrl == 1) // Pessoa Fisica
            else if(iIdTipoPessoaCtrl == 2) // Pessoa Juridica
            {
                if((DNPessoaJuridica = walkDOM(dnode, "PJ", iIndexDOM))) {
                    ULOG("RSR PESSOA JURIDICA>>>");
                    GETTREE(tPessoaXML.szIdPessoa, DNPessoaJuridica, "idPessoa", 0, NOBRIGATORIO, "idPessoa");
                    GETTREE(tPessoaXML.szNmPessoa, DNPessoaJuridica, "nmRazaoSocial", 0, OBRIGATORIO, "nmRazaoSocial");
                    memset(&tDesmembraNome, 0x00, sizeof(TDesmembraNome));
                    strcpy(tDesmembraNome.szNomeCompleto, tPessoaXML.szNmPessoa); ULOG("tDesmembraNome.szNomeCompleto[%s]", tDesmembraNome.szNomeCompleto);
                    DesmembraNome(&tDesmembraNome); ULOG("tDesmembraNome.szNomeCompleto[%s]", tDesmembraNome.szNomeCompleto); ULOG("tDesmembraNome.szNomePrimeiro[%s]", tDesmembraNome.szNomePrimeiro);ULOG("tDesmembraNome.szNomeMeio[%s]", tDesmembraNome.szNomeMeio);ULOG("tDesmembraNome.szNomeFim[%s]", tDesmembraNome.szNomeFim);
                    strcpy(tPessoaXML.szNmNome, tDesmembraNome.szNomePrimeiro);
                    strcpy(tPessoaXML.szNmNomeMeio, tDesmembraNome.szNomeMeio);
                    strcpy(tPessoaXML.szNmSobrenome, tDesmembraNome.szNomeFim);

                    // OS 1093 Pré-Pago - Troca de Titularidade deve aproveitar cliente antigo
                    if ( 0 == iCountClientUser && *(tDataProc.szDsTipoOperacao)=='T' && 0 == tPessoaXML.szIdPessoa[0])
                    {

                        for(iIndexAux=0; (DNDocumento = walkDOM(DNPessoaFisica, "Documento", iIndexAux)) != NULL; iIndexAux++) {
                            memset(&tDocumentoXML, 0x00, sizeof(TDocumento));
                            GETTREE(tDocumentoXML.szIdTipoDocumento, DNDocumento, "idTipoDocumento", 0, OBRIGATORIO, "idTipoDocumento");
                            if ( pclDocumento->documentoIsRequired(tDocumentoXML.szIdTipoDocumento,"CNPJ") ) {

                                GETTREE(tDocumentoXML.szNrDocumento, DNDocumento, "nrDocumento", 0, OBRIGATORIO, "nrDocumento");
                                TrataNrDocumento(tDocumentoXML.szNrDocumento);

                                /*
								pclPessoaDocumento->buscaPessoaPessoaDocumento(tDocumentoXML.szNrDocumento
                                                                              ,tDocumentoXML.szIdTipoDocumento
                                                                              ,tPessoaXML.szNmNome
                                                                              ,tPessoaXML.szNmNomeMeio
                                                                              ,tPessoaXML.szNmSobrenome
                                                                              ,tPessoaXML.szIdPessoa);
								*/
								
                            } // if ( pclDocumento->documentoIsRequired(tDocumentoXML.szIdTipoDocumento,"CPF")
                        } // for(iIndexAux=0;...
                    } // if ( 0 == iCountClientUser && ...

                    GETTREE(tPessoaXML.szIdTipoCarteira, DNPessoaJuridica, "idClassEmpresa", 0, OBRIGATORIO, "idClassEmpresa");

                    pclPessoa->setStruct(&tPessoaXML);
                    pclPessoa->setIdTipoPessoa(tDataProc.szIdTipoPessoa);
                    pclPessoa->setInFalecimentoInformado("0");
                    pclPessoa->setIdUf("1");
                    pclPessoa->setIdProbInadimplencia("5");
                    pclPessoa->setIdChurnProbabilidade("5");
                    pclPessoa->setIdUsuarioAlteracao(tDataProc.szUser);
                    pclPessoa->setIdSistemaOrigem(pclSistemaOrigem->getIdSistemaOrigem());

                    if(strlen(pclPessoa->getIdPessoa()) == 0) {
                        pclPessoa->inserePessoa();
                        ULOG("RSR INSERIU EM CUSTOMER.PESSOA COM IDPESSOA[%s]", pclPessoa->getIdPessoa());
                        pclPessoaDePara->setIdPessoa(pclPessoa->getIdPessoa());
                        pclPessoaDePara->setIdPessoaOrigem(pclPessoa->getIdPessoa());
                        pclPessoaDePara->setIdUsuarioAlteracao(tDataProc.szUser);
                        pclPessoaDePara->inserePessoaDePara();
                        ULOG("RSR INSERIU EM CUSTOMER.PESSOADEPARA COM IDPESSOA[%s]", pclPessoaDePara->getIdPessoaDePara());
                    }
                    else {
                        /* soh verifica se o idpessoa estah na base */
                        tPessoaXML = pclPessoa->tPessoa;
                        if(pclPessoa->buscaPessoa(&tPessoaXML) == false) {
                            sprintf(tDataProc.szMessageException, "Nao ha registro em customer.pessoa para idpessoa[%s]", pclPessoa->getIdPessoa());
                            throw PPExceptionTMA(ERRO_EXECUCAO, tDataProc.szMessageException);
                        }
                        pclPessoa->atualizaPessoa();
                        ULOG("RSR ATUALIZOU EM CUSTOMER.PESSOA COM IDPESSOA[%s]", pclPessoa->getIdPessoa());

                        pclPessoaDePara->setIdPessoa(pclPessoa->getIdPessoa());
                        if(pclPessoaDePara->buscaPessoaDePara() == false) {
                            sprintf(tDataProc.szMessageException, "Nao ha registro em customer.pessoadepara para idpessoa[%s]", pclPessoa->getIdPessoa());
                            throw PPExceptionTMA(ERRO_EXECUCAO, tDataProc.szMessageException);
                        }
                        ULOG("RSR BUSCOU EM CUSTOMER.PESSOADEPARA COM IDPESSOA[%s]", pclPessoaDePara->getIdPessoa());
                    }

                    ULOG("iCountClientUser(%d)", iCountClientUser);
                    if(iCountClientUser == 0) {
                        pclXmlGenSetClient->addItem("tipoCliente", "E");
                        pclXmlGenSetClient->addItem("nome", pclPessoa->getNmPessoa());
                        pclXmlGenSetClient->addItem("nomeAbreviado", "");

                        // pclXmlGenRegContato->createTag("ArvoreAtendimentoVO");
                        // pclXmlGenRegContato->addItem("idContato", pclParametro->getDsValorParametro());
                        // pclXmlGenRegContato->createTag("CarterizacaoVO");
                        // pclXmlGenRegContato->addItem("idTipoCarteira", (strlen(pclPessoa->getIdTipoCarteira()) == 0)?"13":pclPessoa->getIdTipoCarteira());
                        // pclXmlGenRegContato->closeTag(); //CarterizacaoVO
                        // pclXmlGenRegContato->createTag("SegmentacaoVO");
                        // pclXmlGenRegContato->addItem("idSegmentacao", "11");
                        // pclXmlGenRegContato->closeTag(); //SegmentacaoVO
                        // pclXmlGenRegContato->closeTag(); //ArvoreAtendimentoVO
                    }

                    GETTREE(tPessoaJuridicaXML.szNmFantasia, DNPessoaJuridica, "nmFantasia", 0, OBRIGATORIO, "nmFantasia");
                    GETTREE(tPessoaJuridicaXML.szDtFundacao, DNPessoaJuridica, "dtFundacao", 0, OBRIGATORIO, "dtFundacao");
                    ConvDataFromJava(tPessoaJuridicaXML.szDtFundacao);
                    GETTREE(tPessoaJuridicaXML.szNmPessoaFilial, DNPessoaJuridica, "nmRazaoSocial", 0, OBRIGATORIO, "nmRazaoSocial");
                    GETTREE(tPessoaJuridicaXML.szIdCFOP, DNPessoaJuridica, "idClassTributaria", 0, OBRIGATORIO, "idClassTributaria");
                    pclPessoaJuridica->setStruct(&tPessoaJuridicaXML);
                    pclPessoaJuridica->setIdUsuarioAlteracao(tDataProc.szUser);
                    pclPessoaJuridica->setIdPessoa(pclPessoa->getIdPessoa());
                    TPessoaJuridica tPessoaJuridicaTmp = pclPessoaJuridica->tPessoaJuridica;
                    if(pclPessoaJuridica->buscaPessoaJuridica(&tPessoaJuridicaTmp) == true) {
                        pclPessoaJuridica->atualizaPessoaJuridica();
                        ULOG("RSR BUSCOU EM CUSTOMER.PESSOAJURIDICA COM IDPESSOA[%s]", pclPessoaJuridica->getIdPessoa());
                    }
                    else {
                        pclPessoaJuridica->inserePessoaJuridica();
                        ULOG("RSR INSERIU EM CUSTOMER.PESSOAJURIDICA COM IDPESSOA[%s]", pclPessoaJuridica->getIdPessoa());
                    }

                    pclPessoaSegmentacao->setIdPessoaDePara(pclPessoaDePara->getIdPessoaDePara());
                    if(pclPessoaSegmentacao->buscaPessoaSegmentacao() == false) {
                        pclPessoaSegmentacaoHistorico->setIdSegmentacao("11");
                        pclPessoaSegmentacaoHistorico->setIdPessoaDePara(pclPessoaDePara->getIdPessoaDePara());
                        pclPessoaSegmentacaoHistorico->setIdUsuarioAlteracao(tDataProc.szUser);
                        pclPessoaSegmentacaoHistorico->inserePessoaSegmentacaoHistorico();
                        ULOG("RSR INSERIU EM CUSTOMER.PESSOASEGMENTACAOHISTORICO COM IDPESSOADEPARA[%s]", pclPessoaSegmentacaoHistorico->getIdPessoaDePara());

                        pclPessoaSegmentacao->setIdPessoaDePara(pclPessoaDePara->getIdPessoaDePara());
                        pclPessoaSegmentacao->setIdPessoaSegmentacao(pclPessoaSegmentacaoHistorico->getIdPessoaSegmentacao());
                        pclPessoaSegmentacao->setIdUsuarioAlteracao(tDataProc.szUser);
                        pclPessoaSegmentacao->inserePessoaSegmentacao();
                        ULOG("RSR INSERIU EM CUSTOMER.PESSOASEGMENTACAO COM IDPESSOADEPARA[%s]", pclPessoaSegmentacao->getIdPessoaDePara());
                    }

                    for(iIndexAux=0; (DNTelefonePJ = walkDOM(DNPessoaJuridica, "Telefone", iIndexAux)) != NULL; iIndexAux++) {
                        ULOG("RSR TELEFONE PESSOA JURIDICA>>>"); ULOG("iIndexAux(%d)", iIndexAux);
                        memset(&tPessoaComunicacaoXML, 0x00, sizeof(TPessoaComunicacao));
                        GETTREE(tPessoaComunicacaoXML.szIdPessoaComunicacao, DNTelefonePJ, "idContato", 0, NOBRIGATORIO, "idContato");
                        GETTREE(tPessoaComunicacaoXML.szIdTipoComunicacao, DNTelefonePJ, "idTipoTelefone", 0, OBRIGATORIO, "idTipoTelefone");
                        GETTREE(tPessoaComunicacaoXML.szDsContato, DNTelefonePJ, "nrTelefone", 0, OBRIGATORIO, "nrTelefone");
                        GETTREE(tPessoaComunicacaoXML.szNmContato, DNTelefonePJ, "nmContato", 0, OBRIGATORIO, "nmContato");

                        pclPessoaComunicacao->setStruct(&tPessoaComunicacaoXML);
                        pclPessoaComunicacao->setIdPessoa(pclPessoa->getIdPessoa());
                        pclPessoaComunicacao->setIdUsuarioAlteracao(tDataProc.szUser);
                        pclPessoaComunicacao->setIdSistemaOrigem(pclSistemaOrigem->getIdSistemaOrigem());

                        if(strlen(pclPessoaComunicacao->getIdPessoaComunicacao()) == 0) {
                            pclPessoaComunicacao->inserePessoaComunicacao();
                            ULOG("RSR INSERIU EM CUSTOMER.PESSOACOMUNICACAO COM IDPESSOACOMUNICACAO[%s]", pclPessoaComunicacao->getIdPessoaComunicacao());
                        }
                        else {
                            pclPessoaComunicacao->atualizaPessoaComunicacao();
                            ULOG("RSR ATUALIZOU EM CUSTOMER.PESSOACOMUNICACAO COM IDPESSOACOMUNICACAO[%s]", pclPessoaComunicacao->getIdPessoaComunicacao());
                        }

                        // ULOG("iCountClientUser(%d)", iCountClientUser);
                        // if(iCountClientUser == 0) // Cliente
                            // pclXmlGenRegContato->addItem("nmContato", pclPessoaComunicacao->getNmContato());

                        ULOG("RSR TELEFONE PESSOA JURIDICA<<<"); ULOG("iIndexAux(%d)", iIndexAux);
                    }
                    if(iIndexAux == 0)
                        throw PPExceptionTMA(ERRO_EXECUCAO, "Noh Telefone inexistente!");


                    ULOG("iCountClientUser(%d)", iCountClientUser);
                    if(iCountClientUser == 0) {
                        ULOG("RSR \"DOCUMENTO\" PESSOA JURIDICA>>>");
                        ULOG("szIdPessoaCtrl[%s]", szIdPessoaCtrl);
                        ULOG("pclPessoa->getIdPessoa()[%s]", pclPessoa->getIdPessoa());
                        if((pclPessoaDocumento->apagouPessoaDocumento() == false) || (strcmp(pclPessoa->getIdPessoa(), szIdPessoaCtrl)))
                        {
                            strcpy(szIdPessoaCtrl, pclPessoa->getIdPessoa());

                            pclPessoaDocumento->setIdPessoa(pclPessoa->getIdPessoa());
                            pclPessoaDocumento->setIdUsuarioAlteracao(tDataProc.szUser);
                            pclPessoaDocumento->setIdSistemaOrigem(pclSistemaOrigem->getIdSistemaOrigem());
                            pclPessoaDocumento->apagaPessoaDocumento();
                            ULOG("RSR APAGOU CUSTOMER.PESSOADOCUMENTO COM IDPESSOA[%s]", pclPessoaDocumento->getIdPessoa());
                        }

                        // Referente ao documento CNPJ
                        memset(&tDocumentoXML, 0x00, sizeof(TDocumento));
                        GETTREE(szAux, DNPessoaJuridica, "nrCNPJ", 0, OBRIGATORIO, "nrCNPJ"); TrataNrDocumento(szAux);
                        strcpy(tDocumentoXML.szNrDocumento, szAux);
                        pclXmlGenSetClient->addItem("CNPJ", szAux);
                        strcpy(tDocumentoXML.szIdUf, "0");
                        strcpy(tDocumentoXML.szIdTipoDocumento, "2"); // CNPJ
                        pclTipoDocumento->setIdTipoDocumento(tDocumentoXML.szIdTipoDocumento);
                        if(pclTipoDocumento->buscaTipoDocumento() == false) {
                            sprintf(tDataProc.szMessageException, "Nao ha registro em apoio.tipodocumento para idtipodocumento[%s]", tDocumentoXML.szIdTipoDocumento);
                            throw PPExceptionTMA(ERRO_EXECUCAO, tDataProc.szMessageException);
                        }
                        ULOG("RSR BUSCOU EM APOIO.TIPODOCUMENTO COM IDTIPODOCUMENTO[%s]", pclTipoDocumento->getIdTipoDocumento());

                        pclDocumento->setStruct(&tDocumentoXML);
                        pclDocumento->setIdPais("1");
                        pclDocumento->setIdUsuarioAlteracao(tDataProc.szUser);

                        tDocumentoTmp = pclDocumento->tDocumento;
                        // Busca Documento por NrDocumento, IdTipoDocumento, IdUf e SgOrgaoexpedidor
                        if(pclDocumento->buscaDocumentoChaveComposta(&tDocumentoTmp) == false) {
                            pclDocumento->insereDocumento();
                            ULOG("RSR INSERIU EM CUSTOMER.DOCUMENTO COM IDDOCUMENTO[%s]", pclDocumento->getIdDocumento());
                            pclPessoaDocumento->setIdDocumento(pclDocumento->getIdDocumento());
                        }
                        else
                            pclPessoaDocumento->setIdDocumento(tDocumentoTmp.szIdDocumento);

                        // pclPessoaDocumento->setIdDocumentoSistemaOrigem(pclTipoDocumento->getSgClassificacao());
                        pclPessoaDocumento->inserePessoaDocumento();
                        ULOG("RSR INSERIU EM CUSTOMER.PESSOADOCUMENTO COM IDPESSOADOCUMENTO[%s]IDDOCUMENTO[%s]", pclPessoaDocumento->getIdPessoaDocumento(), pclPessoaDocumento->getIdDocumento());


                        // Referente ao documento CNAE
                        memset(&tDocumentoXML, 0x00, sizeof(TDocumento));
                        GETTREE(szAux, DNPessoaJuridica, "nrCNAE", 0, OBRIGATORIO, "nrCNAE"); TrataNrDocumento(szAux);
                        strcpy(tDocumentoXML.szNrDocumento, szAux);
                        pclXmlGenSetClient->addItem("CNAE", szAux);
                        strcpy(tDocumentoXML.szIdUf, "0");
                        strcpy(tDocumentoXML.szIdTipoDocumento, "29"); // CNAE
                        pclTipoDocumento->setIdTipoDocumento(tDocumentoXML.szIdTipoDocumento);
                        if(pclTipoDocumento->buscaTipoDocumento() == false) {
                            sprintf(tDataProc.szMessageException, "Nao ha registro em apoio.tipodocumento para idtipodocumento[%s]", tDocumentoXML.szIdTipoDocumento);
                            throw PPExceptionTMA(ERRO_EXECUCAO, tDataProc.szMessageException);
                        }
                        ULOG("RSR BUSCOU EM APOIO.TIPODOCUMENTO COM IDTIPODOCUMENTO[%s]", pclTipoDocumento->getIdTipoDocumento());

                        pclDocumento->setStruct(&tDocumentoXML);
                        pclDocumento->setIdPais("1");
                        pclDocumento->setIdUsuarioAlteracao(tDataProc.szUser);

                        tDocumentoTmp = pclDocumento->tDocumento;
                        // Busca Documento por NrDocumento, IdTipoDocumento, IdUf e SgOrgaoexpedidor
                        if(pclDocumento->buscaDocumentoChaveComposta(&tDocumentoTmp) == false) {
                            pclDocumento->insereDocumento();
                            ULOG("RSR INSERIU EM CUSTOMER.DOCUMENTO COM IDDOCUMENTO[%s]", pclDocumento->getIdDocumento());
                            pclPessoaDocumento->setIdDocumento(pclDocumento->getIdDocumento());
                        }
                        else
                            pclPessoaDocumento->setIdDocumento(tDocumentoTmp.szIdDocumento);

                        // pclPessoaDocumento->setIdDocumentoSistemaOrigem(pclTipoDocumento->getSgClassificacao());
                        pclPessoaDocumento->inserePessoaDocumento();
                        ULOG("RSR INSERIU EM CUSTOMER.PESSOADOCUMENTO COM IDPESSOADOCUMENTO[%s]IDDOCUMENTO[%s]", pclPessoaDocumento->getIdPessoaDocumento(), pclPessoaDocumento->getIdDocumento());



                        // Referente ao documento CCM
                        memset(&tDocumentoXML, 0x00, sizeof(TDocumento));
                        GETTREE(szAux, DNPessoaJuridica, "nrCCM", 0, OBRIGATORIO, "nrCCM"); TrataNrDocumento(szAux);
                        strcpy(tDocumentoXML.szNrDocumento, szAux);
                        strcpy(tDocumentoXML.szIdUf, "0");
                        strcpy(tDocumentoXML.szIdTipoDocumento, "39"); // CCM
                        pclTipoDocumento->setIdTipoDocumento(tDocumentoXML.szIdTipoDocumento);
                        if(pclTipoDocumento->buscaTipoDocumento() == false) {
                            sprintf(tDataProc.szMessageException, "Nao ha registro em apoio.tipodocumento para idtipodocumento[%s]", tDocumentoXML.szIdTipoDocumento);
                            throw PPExceptionTMA(ERRO_EXECUCAO, tDataProc.szMessageException);
                        }
                        ULOG("RSR BUSCOU EM APOIO.TIPODOCUMENTO COM IDTIPODOCUMENTO[%s]", pclTipoDocumento->getIdTipoDocumento());

                        pclDocumento->setStruct(&tDocumentoXML);
                        pclDocumento->setIdPais("1");
                        pclDocumento->setIdUsuarioAlteracao(tDataProc.szUser);

                        tDocumentoTmp = pclDocumento->tDocumento;
                        // Busca Documento por NrDocumento, IdTipoDocumento, IdUf e SgOrgaoexpedidor
                        if(pclDocumento->buscaDocumentoChaveComposta(&tDocumentoTmp) == false) {
                            pclDocumento->insereDocumento();
                            ULOG("RSR INSERIU EM CUSTOMER.DOCUMENTO COM IDDOCUMENTO[%s]", pclDocumento->getIdDocumento());
                            pclPessoaDocumento->setIdDocumento(pclDocumento->getIdDocumento());
                        }
                        else
                            pclPessoaDocumento->setIdDocumento(tDocumentoTmp.szIdDocumento);

                        // pclPessoaDocumento->setIdDocumentoSistemaOrigem(pclTipoDocumento->getSgClassificacao());
                        pclPessoaDocumento->inserePessoaDocumento();
                        ULOG("RSR INSERIU EM CUSTOMER.PESSOADOCUMENTO COM IDPESSOADOCUMENTO[%s]IDDOCUMENTO[%s]", pclPessoaDocumento->getIdPessoaDocumento(), pclPessoaDocumento->getIdDocumento());



                        // Referente ao documento IE
                        memset(&tDocumentoXML, 0x00, sizeof(TDocumento));
                        GETTREE(szAux, DNPessoaJuridica, "nrInscricao", 0, NOBRIGATORIO, "nrInscricao"); 
                        if ( szAux[0] == 0x0 )
                        {
                            strcpy( szAux, "ISENTO" );
                        }
                        else
                        {
                            TrataNrDocumento(szAux);
                        }
                        
                        pclXmlGenSetClient->addItem("IE", szAux);
                        strcpy(tDocumentoXML.szNrDocumento, szAux);
                        strcpy(tDocumentoXML.szIdUf, "0");
                        GETTREE(tDocumentoXML.szIdTipoDocumento, DNPessoaJuridica, "idTipoInscricao", 0, OBRIGATORIO, "idTipoInscricao");
                        pclTipoDocumento->setIdTipoDocumento(tDocumentoXML.szIdTipoDocumento);
                        if(pclTipoDocumento->buscaTipoDocumento() == false) {
                            sprintf(tDataProc.szMessageException, "Nao ha registro em apoio.tipodocumento para idtipodocumento[%s]", tDocumentoXML.szIdTipoDocumento);
                            throw PPExceptionTMA(ERRO_EXECUCAO, tDataProc.szMessageException);
                        }
                        ULOG("RSR BUSCOU EM APOIO.TIPODOCUMENTO COM IDTIPODOCUMENTO[%s]", pclTipoDocumento->getIdTipoDocumento());

                        pclDocumento->setStruct(&tDocumentoXML);
                        pclDocumento->setIdPais("1");
                        pclDocumento->setIdUsuarioAlteracao(tDataProc.szUser);

                        tDocumentoTmp = pclDocumento->tDocumento;
                        // Busca Documento por NrDocumento, IdTipoDocumento, IdUf e SgOrgaoexpedidor
                        if(pclDocumento->buscaDocumentoChaveComposta(&tDocumentoTmp) == false) {
                            pclDocumento->insereDocumento();
                            ULOG("RSR INSERIU EM CUSTOMER.DOCUMENTO COM IDDOCUMENTO[%s]", pclDocumento->getIdDocumento());
                            pclPessoaDocumento->setIdDocumento(pclDocumento->getIdDocumento());
                        }
                        else
                            pclPessoaDocumento->setIdDocumento(tDocumentoTmp.szIdDocumento);

                        // pclPessoaDocumento->setIdDocumentoSistemaOrigem(pclTipoDocumento->getSgClassificacao());
                        pclPessoaDocumento->inserePessoaDocumento();
                        ULOG("RSR INSERIU EM CUSTOMER.PESSOADOCUMENTO COM IDPESSOADOCUMENTO[%s]IDDOCUMENTO[%s]", pclPessoaDocumento->getIdPessoaDocumento(), pclPessoaDocumento->getIdDocumento());


                        pclXmlGenSetClient->addItem("telefone", pclPessoaComunicacao->getDsContato());
                        pclXmlGenSetClient->addItem("fax", "");
                        pclXmlGenSetClient->addItem("eMail", "");
                        pclXmlGenSetClient->addItem("numDepend", "");
                        pclXmlGenSetClient->addItem("passaporte", "");
                        pclXmlGenSetClient->addItem("tipoPassaporte", "");
                        pclXmlGenSetClient->addItem("cartaConducao", "");
                        pclXmlGenSetClient->addItem("tipoCartaCond", "");
                        pclXmlGenSetClient->addItem("aoCuidadoDe", "");
                        pclXmlGenSetClient->addItem("obs", "");
                        pclXmlGenSetClient->addItem("conservatoriaRegistro", "");
                        pclXmlGenSetClient->addItem("nomeContato", pclPessoaComunicacao->getNmContato());
                    }


                    for(iIndexAux=0; (DNDadosEnderecoPJ = walkDOM(DNPessoaJuridica, "DadosEndereco", iIndexAux)) != NULL; iIndexAux++) {
                        ULOG("RSR DADOSENDERECO PESSOA JURIDICA>>>"); ULOG("iIndexAux(%d)", iIndexAux);
                        GETTREE(tPessoaEnderecoXML.szIdPessoaEndereco, DNDadosEnderecoPJ, "idEndereco", 0, NOBRIGATORIO, "");
                        GETTREE(szInEndPrincipal, DNDadosEnderecoPJ, "inEndPrincipal", 0, OBRIGATORIO, "inEndPrincipal");
                        GETTREE(tPessoaEnderecoXML.szIdTipoEndereco, DNDadosEnderecoPJ, "idTipoEndereco", 0, OBRIGATORIO, "idTipoEndereco");
                        GETTREE(tPessoaEnderecoXML.szNmTipoLogradouro, DNDadosEnderecoPJ, "nmTipoLogradouro", 0, OBRIGATORIO, "nmTipoLogradouro");
                        GETTREE(tPessoaEnderecoXML.szNmTituloLogradouro, DNDadosEnderecoPJ, "nmTitLogradouro", 0, NOBRIGATORIO, "");

                        GETTREE(tPessoaEnderecoXML.szNrCep, DNDadosEnderecoPJ, "nrCEP", 0, OBRIGATORIO, "nrCEP");
                        strcpy( nrCEP, tPessoaEnderecoXML.szNrCep );

                        GETTREE(tPessoaEnderecoXML.szNmLogradouro, DNDadosEnderecoPJ, "nmLogradouro", 0, OBRIGATORIO, "nmLogradouro");
                        strcpy( nmLograd,tPessoaEnderecoXML.szNmLogradouro );

                        GETTREE(tPessoaEnderecoXML.szcdLogradouro, DNDadosEnderecoPJ, "codLogradouro", 0, NOBRIGATORIO, "codLogradouro");
                        GETTREE(tPessoaEnderecoXML.szInCnl, DNDadosEnderecoPJ, "inCnl", 0, NOBRIGATORIO, "inCnl");
                        GETTREE(tPessoaEnderecoXML.szcdIbge, DNDadosEnderecoPJ, "inCodigoIBGE", 0, NOBRIGATORIO, "inCodigoIBGE");

                        GETTREE(tPessoaEnderecoXML.szNrEndereco, DNDadosEnderecoPJ, "nrLogradouro", 0, OBRIGATORIO, "nrLogradouro");
                        GETTREE(tPessoaEnderecoXML.szDsEnderecoComplemento, DNDadosEnderecoPJ, "nmComplemento", 0, NOBRIGATORIO, "");
                        GETTREE(tPessoaEnderecoXML.szNmBairro, DNDadosEnderecoPJ, "nmBairro", 0, OBRIGATORIO, "nmBairro");
                        strcpy( nmBairro,tPessoaEnderecoXML.szNmBairro );

                        GETTREE(tPessoaEnderecoXML.szNmMunicipio, DNDadosEnderecoPJ, "nmMunicipio", 0, OBRIGATORIO, "nmMunicipio");
                        strcpy( nmCidade,tPessoaEnderecoXML.szNmMunicipio );
                        
                        GETTREE(tPessoaEnderecoXML.szIdUf, DNDadosEnderecoPJ, "idUF", 0, OBRIGATORIO, "idUF");

						i_iduf=atoi(tPessoaEnderecoXML.szIdUf);

                        pclPessoaEndereco->setStruct(&tPessoaEnderecoXML);
                        pclPessoaEndereco->setIdPessoa(pclPessoa->getIdPessoa());
                        pclPessoaEndereco->setIdUsuarioAlteracao(tDataProc.szUser);
                        pclPessoaEndereco->setIdSistemaOrigem(pclSistemaOrigem->getIdSistemaOrigem());
                        pclPessoaEndereco->setIdPais("1");
                        pclPessoaEndereco->BuscaSiglaUF( i_iduf );
                        strcpy( tPessoaEnderecoXML.szIdUf, pclPessoaEndereco->getSgUF() );
                        char idPessoaEnderecoAux[39];
                       
                        //if(strlen(pclPessoaEndereco->getIdPessoaEndereco()) == 0) {
                        if( pclPessoaEndereco->existePessoaEndereco(pclPessoa->getIdPessoa(),idPessoaEnderecoAux) == false ) {
                            pclPessoaEndereco->inserePessoaEndereco();
                            ULOG("RSR INSERIU EM CUSTOMER.PESSOAENDERECO COM IDPESSOAENDERECO[%s]", pclPessoaEndereco->getIdPessoaEndereco());
                        }
                        else {
                            pclPessoaEndereco->setIdPessoaEndereco(idPessoaEnderecoAux);
                            pclPessoaEndereco->atualizaPessoaEndereco();
                            ULOG("RSR ATUALIZOU EM CUSTOMER.PESSOAENDERECO COM IDPESSOAENDERECO[%s]", pclPessoaEndereco->getIdPessoaEndereco());
                        }

                        ULOG("iCountClientUser(%d)", iCountClientUser);
                        if((!strcmp(szInEndPrincipal, "1")) && (iCountClientUser == 0)) {
                            if(bEndPrincipal == true)
                                throw PPExceptionTMA(ERRO_EXECUCAO, "Endereco principal jah existe!");

                            bEndPrincipal=true;
                            strcpy(szIdPessoaEnderecoEndPrincipal, pclPessoaEndereco->getIdPessoaEndereco());
                            ULOG("szIdPessoaEnderecoEndPrincipal Pessoa Juridica[%s]", szIdPessoaEnderecoEndPrincipal);

                            strcpy(szAux, pclPessoaEndereco->getNmTipoLogradouro());
                            ULOG("*** 1.Logradouro szAux[%s]", szAux);
                            alltrim(szAux);
                            ULOG("*** 2.Logradouro szAux[%s]", szAux);

                            memset(szAuxNGIN, 0x00, sizeof(szAuxNGIN));
                            for(iCountLogradouro=0; pszTipoLogFO[iCountLogradouro] != NULL; iCountLogradouro++) {
                                if(!strcasecmp(pszTipoLogFO[iCountLogradouro], szAux)) {
                                    strcpy(szAuxNGIN, pszTipoLogNGIN[iCountLogradouro]);
                                    break;
                                }
                            }
                            ULOG("*** 3.Logradouro szAuxNGIN[%s]", szAuxNGIN);
                            pclXmlGenSetClient->addItem("logradouro", szAuxNGIN);

                            memset(szArruamento, 0x00, sizeof(szArruamento));
                            if(strlen(szAuxNGIN) > 0)
                                strcpy(szArruamento, szAuxNGIN);

                            ULOG("1.szArruamento[%s]", szArruamento);

                            if(strlen(pclPessoaEndereco->getNmTituloLogradouro()) > 0) {
                                strcat(szArruamento, " ");
                                strcat(szArruamento, pclPessoaEndereco->getNmTituloLogradouro());
                            }
                            ULOG("2.szArruamento[%s]", szArruamento);

                            if(strlen(pclPessoaEndereco->getNmLogradouro()) > 0) {
                                strcat(szArruamento, " ");
                                strcat(szArruamento, pclPessoaEndereco->getNmLogradouro());
                            }
                            ULOG("3.szArruamento[%s]", szArruamento);
                            szArruamento[LEN_ARRUAMENTO_LEGADO]=0x00;
                            ULOG("4.szArruamento[%s]", szArruamento);
                            pclXmlGenSetClient->addItem("endereco", szArruamento);

                            // SPED
                            ULOG( "nrCEP    [%s]",nrCEP );
                            ULOG( "nmLograd [%s]",nmLograd );
                            ULOG( "nmBairro [%s]",nmBairro );
                            ULOG( "nmCidade [%s]",nmCidade );
                            pclPessoaEndereco->ProcuraSPED(nrCEP, nmLograd, nmBairro, nmCidade );
                            bf = pclPessoaEndereco->getCdLogradouro();
                            strcpy( tPessoaEnderecoXML.szcdLogradouro, bf );
                            
                            ULOG( "tPessoaEnderecoXML.szcdLogradouro [%s]",tPessoaEnderecoXML.szcdLogradouro );
                            
                            bf = pclPessoaEndereco->getInCNL();
                            strcpy( tPessoaEnderecoXML.szInCnl, bf );
                            ULOG( "tPessoaEnderecoXML.szInCnl [%s]",tPessoaEnderecoXML.szInCnl );
                            
                            bf = pclPessoaEndereco->getCdIbge();
                            strcpy( tPessoaEnderecoXML.szcdIbge, bf );
                            ULOG( "tPessoaEnderecoXML.szcdIbge [%s]",tPessoaEnderecoXML.szcdIbge );

                            if (strlen(pclPessoaEndereco->getCdLogradouro()) > 0)
                            {
                                pclXmlGenSetClient->addItem("cdLogradouro", pclPessoaEndereco->getCdLogradouro());
                            }
                            
                            if (strlen(pclPessoaEndereco->getInCNL()) > 0)
                            {
                                pclXmlGenSetClient->addItem("inCnl", pclPessoaEndereco->getInCNL());
                            }

                            if (strlen(pclPessoaEndereco->getCdIbge()) > 0)
                            {
                                pclXmlGenSetClient->addItem("cdIbge", pclPessoaEndereco->getCdIbge());
                            }

                            pclXmlGenSetClient->addItem("complemento", pclPessoaEndereco->getDsEnderecoComplemento());
                            pclXmlGenSetClient->addItem("bairro", pclPessoaEndereco->getNmBairro());

                            strcpy(szAux, pclPessoaEndereco->getNrCep());
                            TrataNrDocumento(szAux);
                            szAux[LEN_CEP_LEGADO]=0x00;
                            pclXmlGenSetClient->addItem("CEP", szAux);

                            pclXmlGenSetClient->addItem("cidade", pclPessoaEndereco->getNmMunicipio());
                            pclXmlGenSetClient->addItem("estado", pclPessoaEndereco->getSgUF());
                            pclXmlGenSetClient->addItem("pais", "BRA");
                            pclXmlGenSetClient->addItem("numero", pclPessoaEndereco->getNrEndereco());
                        } //if(!strcmp(szInEndPrincipal, "1"))
                        ULOG("RSR DADOSENDERECO PESSOA JURIDICA<<<"); ULOG("iIndexAux(%d)", iIndexAux);
                    }
                    ULOG("RSR PESSOA JURIDICA<<<");
                }
                else
                    throw PPExceptionTMA(ERRO_EXECUCAO, "Noh PJ inexistente!");
            } //else if(iIdTipoPessoaCtrl == 2) // Pessoa Juridica

            if(iIdPessoaDeParaIndex < QTD_ARRAY_PESSOA_DE_PARA) {
                strcpy(szIdPessoaDePara[iIdPessoaDeParaIndex], pclPessoaDePara->getIdPessoaDePara());
                ULOG("szIdPessoaDePara[%d][%s]", iIdPessoaDeParaIndex, szIdPessoaDePara[iIdPessoaDeParaIndex]);
                iIdPessoaDeParaIndex++;
            }
            else
                throw PPExceptionTMA(ERRO_EXECUCAO, "DEBUG iIdPessoaDeParaIndex");


            /* Controle para navegacao no xml */
            if(iQtdLoop == 2) {
                if(!strcmp(tDataProc.szIdTipoPessoa, "1")) // Pessoa Fisica
                    iIndexDOM++; // Pega o proximo noh de PF
                else if(!strcmp(tDataProc.szIdTipoPessoa, "2")) // Pessoa Juridica
                {
                    if(bClienteUsuario == true)
                        iIndexDOM++; // Pega o proximo noh de PJ
                    else
                        iIdTipoPessoaCtrl=1; // Busca o usuario no noh de PF
                }
            }
            else if(iQtdLoop == 1) {
                strcpy(szIdPessoaDePara[1], szIdPessoaDePara[0]);
                break;
            }
            else
                throw PPExceptionTMA(ERRO_EXECUCAO, "DEBUG iQtdLoop");

        } //for(iCountClientUser=0; iCountClientUser < 2; iCountClientUser++)
        ULOG("szIdPessoaDePara[0][%s]", szIdPessoaDePara[0]);
        ULOG("szIdPessoaDePara[1][%s]", szIdPessoaDePara[1]);

        for(iCountClientUser=0; iCountClientUser < 2; iCountClientUser++) {
            if(iCountClientUser == 0) { // Cliente
                // pclXmlGenRegContato->createTag("PessoaVO");
                // pclXmlGenRegContato->addItem("idPessoa", szIdPessoaDePara[0]);
                // pclXmlGenRegContato->addItem("AtendimentoTipoComunicacaoVO", "1");
                // pclXmlGenRegContato->closeTag(); //PessoaVO

                pclPessoaLinha->setIdTipoRelacionamento(ID_TIPO_RELACIONAMENTO_C);
            }
            else if(iCountClientUser == 1) { // Usuario
                // pclXmlGenRegContato->createTag("UsuarioLinhaVO");
                // pclXmlGenRegContato->addItem("idPessoa", szIdPessoaDePara[1]);
                // pclXmlGenRegContato->closeTag(); //UsuarioLinhaVO

                pclPessoaLinha->setIdTipoRelacionamento(ID_TIPO_RELACIONAMENTO_U);
            }

            pclPessoaLinha->setIdLinhaTelefonica(tDataProc.szIdLinhaTelefonica);
            pclPessoaLinha->setIdPessoaDePara(szIdPessoaDePara[iCountClientUser]);
            pclPessoaLinha->setIdUsuarioAlteracao(tDataProc.szUser);

            TPessoaLinha tPessoaLinhaTmp = pclPessoaLinha->tPessoaLinha;
            if(pclPessoaLinha->buscaPessoaLinha(&tPessoaLinhaTmp) == false) {
                pclPessoaLinha->inserePessoaLinha();
                ULOG("RSR INSERIU EM CUSTOMER.PESSOALINHA COM IDPESSOALINHA[%s]", pclPessoaLinha->getIdPessoaLinha());
            }
            else if(strcmp(pclPessoaLinha->getIdPessoaDePara(), tPessoaLinhaTmp.szIdPessoaDePara)) {
                pclPessoaLinha->setIdPessoaLinha(tPessoaLinhaTmp.szIdPessoaLinha);
                pclPessoaLinha->setIdTipoRelacionamento(tPessoaLinhaTmp.szIdTipoRelacionamento);
                pclPessoaLinha->atualizaPessoaLinha();
                ULOG("RSR ATUALIZOU EM CUSTOMER.PESSOALINHA COM IDPESSOALINHA[%s]", pclPessoaLinha->getIdPessoaLinha());
            }
            ULOG("pclPessoaLinha->getIdPessoaDePara()[%s] tPessoaLinhaTmp.szIdPessoaDePara[%s]", pclPessoaLinha->getIdPessoaDePara(), tPessoaLinhaTmp.szIdPessoaDePara);


            if(iCountClientUser == 0) { // Cliente
                // pclRegistraContato->setIdPessoaDePara(pclPessoaLinha->getIdPessoaDePara());
                // pclRegistraContato->setIdLinhaTelefonica(pclPessoaLinha->getIdLinhaTelefonica());

                if(pclPessoaLinha->inseriuPessoaLinha() == true)
                    pclPessoaLinhaPre->setIdPessoaLinha(pclPessoaLinha->getIdPessoaLinha());
                else
                    pclPessoaLinhaPre->setIdPessoaLinha(tPessoaLinhaTmp.szIdPessoaLinha);


                GETTREE(szUsrNaoInformado, dnode, "usrNaoInformado", 0, NOBRIGATORIO, "usrNaoInformado");
                if(szUsrNaoInformado[0] != 0x00) {
                    ULOG("Inicio PESSOALINHAPRE");
                    if(!strcmp(tDataProc.szDsTipoOperacao, "T")) {
                        pclPessoaLinhaPre->setInMudancaTitularidade("1");
                        pclPessoaLinhaPre->insereMudancaTitularidade(tDataProc.szNrLinha);
                    }
                    else
                        pclPessoaLinhaPre->setInMudancaTitularidade("0");

                    pclPessoaLinhaPre->setInSincronismo("0");
                    pclPessoaLinhaPre->setInUsuarioNaoInformado(szUsrNaoInformado);

                    TPessoaLinhaPre tPessoaLinhaPreAux = pclPessoaLinhaPre->tPessoaLinhaPre;
                    if(pclPessoaLinhaPre->buscaPessoaLinhaPre() == true) {
                        pclPessoaLinhaPre->setInUsuarioNaoInformado(szUsrNaoInformado);
                        pclPessoaLinhaPre->atualizaPessoaLinhaPre();
                        ULOG("RSR ATUALIZOU EM CUSTOMER.PESSOALINHAPRE COM IDPESSOALINHA[%s]", pclPessoaLinhaPre->getIdPessoaLinha());
                    }
                    else {
                        pclPessoaLinhaPre->setStruct(&tPessoaLinhaPreAux);
                        pclPessoaLinhaPre->inserePessoaLinhaPre();
                        ULOG("RSR INSERIU EM CUSTOMER.PESSOALINHAPRE COM IDPESSOALINHA[%s]", pclPessoaLinhaPre->getIdPessoaLinha());
                    }
                    ULOG("Final PESSOALINHAPRE");
                }
            }

            if(pclPessoaLinha->inseriuPessoaLinha() == true) {
                memcpy(szAux, tDataProc.szNrLinha, 2); szAux[2]=0x00; ULOG("CdAreaRegistro[%s]", szAux);
                pclPessoaLinhaHistorico->setCdAreaRegistro(szAux);
                pclPessoaLinhaHistorico->setNrLinha(tDataProc.szNrLinha + 2);
                pclPessoaLinhaHistorico->setIdUsuarioAlteracao(tDataProc.szUser);
                pclPessoaLinhaHistorico->setIdPessoaDePara(szIdPessoaDePara[iCountClientUser]);
                pclPessoaLinhaHistorico->setIdLinhaTelefonica(pclPessoaLinha->getIdLinhaTelefonica());
                pclPessoaLinhaHistorico->setIdTipoRelacionamento(pclPessoaLinha->getIdTipoRelacionamento());

                pclPessoaLinhaHistorico->inserePessoaLinhaHistorico();
                ULOG("RSR INSERIU EM CUSTOMER.PESSOALINHAHISTORICO COM IDPESSOALINHAHISTORICO[%s]", pclPessoaLinhaHistorico->getIdPessoaLinhaHistorico());

                // pclRegistraContato->setIdPessoaLinhaHistorico(pclPessoaLinhaHistorico->getIdPessoaLinhaHistorico());
            }
        }// for(iCountClientUser=0; iCountClientUser < 2; iCountClientUser++)

         /* Referente ao BlackList PUP */
        if(!strcmp(tDataProc.szDsTipoOperacao, "T") || !strcmp(tDataProc.szDsTipoOperacao, "C")) // PREPAGOTROCATITULARIDADE ou PREPAGONOVOCADASTRO
        {
            if((DNPUP = walkDOM(dnode, "PUP", 0)))
            {
                ULOG("RSR PUP>>>");

                pclParametro->clearStruct();
                pclParametro->setCdParametro("PUP_PRAZO_EXPIRACAO");
                if(pclParametro->buscaParametro() == false) {
                    sprintf(tDataProc.szMessageException, "Pesquisa em APOIO.PARAMETRO não encontrou nenhum registro para CdParametro [%s]", pclParametro->getCdParametro());
                    throw PPExceptionTMA(ERRO_EXECUCAO, tDataProc.szMessageException);
                }


                pclPermissaoLinhaPUP->setIdLinhaTelefonica(tDataProc.szIdLinhaTelefonica);
                pclPermissaoLinhaPUP->setAlteraCadastroLinhaPUP( tDataProc.szIdLinhaTelefonica,tDataProc.szUser );  /* Faz Update em Linha.LinhaTelefonica */
                pclPermissaoLinhaPUP->AlteraCadastroLinhaPUP();
                pclPermissaoLinhaPUP->apagaPermissaoLinhaPUP();
                ULOG("RSR APAGOU EM LINHA.PERMISSAOLINHAPUP COM IDLINHATELEFONICA[%s]", pclPermissaoLinhaPUP->getIdLinhaTelefonica());


             /*   GETTREE(szAux, DNPUP, "inProtocolo", 0, NOBRIGATORIO, "inProtocolo");  // SMSPROT
                pclPermissaoLinhaPUP->clearStruct();
                pclPermissaoLinhaPUP->setInAtivo(szAux);
                pclPermissaoLinhaPUP->setSgPermissaoPUP("SMSPROT");
                pclPermissaoLinhaPUP->setIdLinhaTelefonica(tDataProc.szIdLinhaTelefonica);
                pclPermissaoLinhaPUP->setIdUsuarioAlteracao(tDataProc.szUser);
                pclPermissaoLinhaPUP->setDtExpiracao(pclParametro->getDsValorParametro());
                pclPermissaoLinhaPUP->inserePermissaoLinhaPUP();
                ULOG("RSR INSERIU EM LINHA.PERMISSAOLINHAPUP COM IDLINHATELEFONICA[%s] SgPermissaoPUP[%s]", pclPermissaoLinhaPUP->getIdLinhaTelefonica(), pclPermissaoLinhaPUP->getSgPermissaoPUP());*/


                GETTREE(szAux, DNPUP, "inPromocoes", 0, OBRIGATORIO, "inPromocoes");  // SMSPROM
                pclPermissaoLinhaPUP->clearStruct();
                pclPermissaoLinhaPUP->setInAtivo(szAux);
                pclPermissaoLinhaPUP->setSgPermissaoPUP("SMSPROM");
                pclPermissaoLinhaPUP->setIdLinhaTelefonica(tDataProc.szIdLinhaTelefonica);
                pclPermissaoLinhaPUP->setIdUsuarioAlteracao(tDataProc.szUser);
                pclPermissaoLinhaPUP->setDtExpiracao(pclParametro->getDsValorParametro());
                pclPermissaoLinhaPUP->inserePermissaoLinhaPUP();
                ULOG("RSR INSERIU EM LINHA.PERMISSAOLINHAPUP COM IDLINHATELEFONICA[%s] SgPermissaoPUP[%s]", pclPermissaoLinhaPUP->getIdLinhaTelefonica(), pclPermissaoLinhaPUP->getSgPermissaoPUP());


                GETTREE(szAux, DNPUP, "inProdutos", 0, OBRIGATORIO, "inProdutos");    // SMSPROD
                pclPermissaoLinhaPUP->clearStruct();
                pclPermissaoLinhaPUP->setInAtivo(szAux);
                pclPermissaoLinhaPUP->setSgPermissaoPUP("SMSPROD");
                pclPermissaoLinhaPUP->setIdLinhaTelefonica(tDataProc.szIdLinhaTelefonica);
                pclPermissaoLinhaPUP->setIdUsuarioAlteracao(tDataProc.szUser);
                pclPermissaoLinhaPUP->setDtExpiracao(pclParametro->getDsValorParametro());
                pclPermissaoLinhaPUP->inserePermissaoLinhaPUP();
                ULOG("RSR INSERIU EM LINHA.PERMISSAOLINHAPUP COM IDLINHATELEFONICA[%s] SgPermissaoPUP[%s]", pclPermissaoLinhaPUP->getIdLinhaTelefonica(), pclPermissaoLinhaPUP->getSgPermissaoPUP());

                GETTREE(szAux, DNPUP, "inParceiros", 0, OBRIGATORIO, "inParceiros");  // SMSPARC
                pclPermissaoLinhaPUP->clearStruct();
                pclPermissaoLinhaPUP->setInAtivo(szAux);
                pclPermissaoLinhaPUP->setSgPermissaoPUP("SMSPARC");
                pclPermissaoLinhaPUP->setIdLinhaTelefonica(tDataProc.szIdLinhaTelefonica);
                pclPermissaoLinhaPUP->setIdUsuarioAlteracao(tDataProc.szUser);
                pclPermissaoLinhaPUP->setDtExpiracao(pclParametro->getDsValorParametro());
                pclPermissaoLinhaPUP->inserePermissaoLinhaPUP();
                ULOG("RSR INSERIU EM LINHA.PERMISSAOLINHAPUP COM IDLINHATELEFONICA[%s] SgPermissaoPUP[%s]", pclPermissaoLinhaPUP->getIdLinhaTelefonica(), pclPermissaoLinhaPUP->getSgPermissaoPUP());

                ULOG("RSR PUP<<<");
            }
        }

        pclLinhaConta->setIdLinhaTelefonica(tDataProc.szIdLinhaTelefonica);
        pclLinhaConta->setIdTipoRelacionamento(ID_TIPO_RELACIONAMENTO_C);
        if(pclLinhaConta->buscaLinhaConta() == false) {
            sprintf(tDataProc.szMessageException, "Conta ficticia nao encontrada para IdLinhaTelefonica[%s]", pclLinhaConta->getIdLinhaTelefonica());
            throw PPExceptionTMA(ERRO_EXECUCAO, tDataProc.szMessageException);
        }
        ULOG("RSR BUSCOU EM CUSTOMER.LINHACONTA COM IDLINHATELEFONICA[%s] IDTIPORELACIONAMENTO[%s]", pclLinhaConta->getIdLinhaTelefonica(), pclLinhaConta->getIdTipoRelacionamento());

        // pclXmlGenRegContato->createTag("Contas");
        // pclXmlGenRegContato->createTag("ContaVO");
        // pclXmlGenRegContato->addItem("idConta", pclLinhaConta->getIdConta());
        // pclXmlGenRegContato->createTag("LinhaVO");
        // pclXmlGenRegContato->addItem("idPessoaLinhaHistorico", pclLinhaConta->getIdLinhaTelefonica());
        // pclXmlGenRegContato->closeTag(); //LinhaVO
        // pclXmlGenRegContato->closeTag(); //ContaVO
        // pclXmlGenRegContato->closeTag(); //Contas
        // pclXmlGenRegContato->closeTag(); // msg

        pclPessoaConta->setIdConta(pclLinhaConta->getIdConta());
        pclPessoaConta->setIdTipoRelacionamento(ID_TIPO_RELACIONAMENTO_C);
        pclPessoaConta->setIdPessoaDePara(szIdPessoaDePara[0]);
        pclPessoaConta->setIdUsuarioAlteracao(tDataProc.szUser);

        TPessoaConta tPessoaContaTmp = pclPessoaConta->tPessoaConta;
        if(pclPessoaConta->buscaPessoaConta(&tPessoaContaTmp) == true) {
            pclPessoaConta->setIdPessoaConta(tPessoaContaTmp.szIdPessoaConta);
            pclPessoaConta->atualizaPessoaConta();
            ULOG("RSR ATUALIZOU EM CUSTOMER.PESSOACONTA COM IDPESSOACONTA[%s]", pclPessoaConta->getIdPessoaConta());
        }
        else {
            pclPessoaConta->inserePessoaConta();
            ULOG("RSR INSERIU EM CUSTOMER.PESSOACONTA COM IDPESSOACONTA[%s]", pclPessoaConta->getIdPessoaConta());
        }

        pclContaEndereco->setIdTipoEnderecoCobranca(pclTipoEnderecoCobranca->getIdTipoEnderecoCobranca());
        pclContaEndereco->setIdConta(pclLinhaConta->getIdConta());
        ULOG("szIdPessoaEnderecoEndPrincipal[%s]", szIdPessoaEnderecoEndPrincipal);
        pclContaEndereco->setIdPessoaEndereco(szIdPessoaEnderecoEndPrincipal);
        pclContaEndereco->setIdUsuarioAlteracao(tDataProc.szUser);

        TContaEndereco tContaEnderecoTmp = pclContaEndereco->tContaEndereco;
        if(pclContaEndereco->buscaContaEndereco(&tContaEnderecoTmp) == true) {
            pclContaEndereco->setIdTipoEnderecoCobranca(pclTipoEnderecoCobranca->getIdTipoEnderecoCobranca());
            pclContaEndereco->setIdContaEndereco(tContaEnderecoTmp.szIdContaEndereco);

            /* sem noh de endereco */
            if(strlen(pclContaEndereco->getIdPessoaEndereco()) == 0)
                pclContaEndereco->setIdPessoaEndereco(tContaEnderecoTmp.szIdPessoaEndereco);

            pclContaEndereco->atualizaContaEndereco();
            ULOG("RSR ATUALIZOU EM CUSTOMER.CONTAENDERECO COM IDCONTAENDERECO[%s]", pclContaEndereco->getIdContaEndereco());
        }
        else {
            pclContaEndereco->insereContaEndereco();
            ULOG("RSR INSERIU EM CUSTOMER.CONTAENDERECO COM IDCONTAENDERECO[%s]", pclContaEndereco->getIdContaEndereco());
        }
        ULOG("RSR DNODE<<<");

        // TuxMessage inputMessage;
        // inputMessage.setUser(tDataProc.szUser);
        // inputMessage.setMessageBody(pclXmlGenRegContato);
        // inputMessage.setService("DREGCONTATO");

        // pMessageBody = inputMessage.getMessageBody();
        // sprintf(szAuxRegistraContato,
        //     "<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>"
        //         "<msg>"
        //             "<msgHdr>"
        //                 "<user>%s</user>"
        //                 "<service>DREGCONTATO</service>"
        //             "</msgHdr>"
        //             "<msgBody>"
        //                 "%s"
        //             "</msgBody>"
        //         "</msg>", tDataProc.szUser, pMessageBody);

        // ULOG("RegistraContato(%d)[%s]", strlen(szAuxRegistraContato), szAuxRegistraContato);
        // pclRegistraContato->setXml(szAuxRegistraContato);

        // ULOG("Inicio do processo Registra Contato");
        /* Nao registra contato para o tibco */
        /* pclRegistraContato->insereRegistraContato(); */
        // ULOG("Final do processo Registra Contato");
        // ULOG("RSR INSERIU EM TIBCO_OW.P_REGISTRACONTATO COM IDREGISTRACONTATO[%s]", pclRegistraContato->getIdRegistraContato());

        if(bEnviaSetInterception == true)
        {
            if(!strcmp(tDataProc.szDsTipoOperacao, "C"))
            {
                try
                {
                    ULOG("Inicio <setInterception>");
                    pclXmlGenSetInterception->addItem("ProxyOperacao", "setInterceptacao");
                    pclXmlGenSetInterception->addItem("usuario", "FO");
                    pclXmlGenSetInterception->addItem("operacao", "0");
                    pclXmlGenSetInterception->addItem("xmlns", "cliente.fo.vivo.com.br/vo");

                    inputMessageHabLinha->setUser(tDataProc.szUser);
                    inputMessageHabLinha->setService("DTUXINTERBE");
                    inputMessageHabLinha->setMessageBody(pclXmlGenSetInterception);

                    remoteServiceHabLinha->setServiceName("DTUXINTERBE");
                    remoteServiceHabLinha->setInputMessage(inputMessageHabLinha);

                    ULOG("Inicio do acesso remoto ao sistema legado <remoteCall>");
                    iRetSistemaLegado = remoteServiceHabLinha->remoteCall(); ULOG("iRetSistemaLegado(%d)", iRetSistemaLegado);
                    if(iRetSistemaLegado != TUXFWRET_OK)
                        throw PPExceptionTMA(ERRO_LEGADO_SET_INTERCEPTACAO, "");

                    pStatusCode = remoteServiceHabLinha->getOutputMessage()->getStatusCode(); ULOG("pStatusCode[%s](%p)", pStatusCode?pStatusCode:"...NULL...", pStatusCode);
                    if(pStatusCode == NULL)
                        throw PPExceptionTMA(ERRO_LEGADO_SET_INTERCEPTACAO, "");

                    if(strlen(pStatusCode) == 0)
                        throw PPExceptionTMA(ERRO_LEGADO_SET_INTERCEPTACAO, "");

                    if(pStatusCode[2] != 'I') {
                        pStatusText = remoteServiceHabLinha->getOutputMessage()->getStatusText();
                        ULOG("pStatusText[%s](%p)", pStatusText?pStatusText:"...NULL...", pStatusText);
                        throw PPExceptionTMA(ERRO_LEGADO_SET_INTERCEPTACAO, "");
                    }
                }
                catch(...)
                {
                    throw PPExceptionTMA(ERRO_LEGADO_SET_INTERCEPTACAO, "Erro de comunicação com sistema de Habilitacao de Linha.");
                }

                ULOG("Final do acesso remoto ao sistema legado <remoteCall>");
                ULOG("pStatusCode[%s](%p)", pStatusCode?pStatusCode:"...NULL...", pStatusCode);
                ULOG("pStatusText[%s](%p)", pStatusText?pStatusText:"...NULL...", pStatusText);

                if(pStatusCode) {
                    free(pStatusCode); pStatusCode=NULL; ULOG("free - pStatusCode");
                }
                if(pStatusText) {
                    free(pStatusText); pStatusText=NULL; ULOG("free - pStatusText");
                }

                ULOG("Final <setInterception>");
                ULOG("RSR SETINTERCEPTION OK");

                /*--- [A0001] ---------------------------------------------------*/
                strcpy( vo_idPessoa,pclPessoa->getIdPessoa() );
                pclPessoaSegmentacao->ClassificaInfancia( vo_idLinhaTelefonica, vo_idPessoa , vo_idTipoPessoa ) ;
                /*---------------------------------------------------------------*/
                
            } //if(!strcmp(tDataProc.szDsTipoOperacao, "C"))
        } //if(bEnviaSetInterception == true)

        ULOG("Inicio do setCliente em tabela <setCliente>");
        pclFilaSetClientInfo->setIdLinhaTelefonica(tDataProc.szIdLinhaTelefonica);

        pTmp = pclXmlGenSetClient->retrieveXML(&iTamSaidaSetClient);
        ULOG("SetClient pTmp[%s]", pTmp?pTmp:"...NULL...");
        if(pTmp == NULL)
            throw PPExceptionTMA(ERRO_EXECUCAO, "XML A SER GRAVADO NULL");

        ULOG("iTamSaidaSetClient(%d) strlen(pTmp)(%d)", iTamSaidaSetClient, strlen(pTmp));

        if(strlen(pTmp) >= LEN_XML)
            throw PPExceptionTMA(ERRO_EXECUCAO, "!!!REFINAR TRATAMENTO DE XML A SER GRAVADO!!!");

        pclFilaSetClientInfo->setXml(pTmp);

        if(pclFilaSetClientInfo->existeFilaSetClientInfo() == true) {
            pclFilaSetClientInfo->atualizaXmlFilaSetClientInfo();
            ULOG("RSR ATUALIZOU EM INFRA.FILASETCLIENTINFO COM IDLINHATELEFONICA[%s]", pclFilaSetClientInfo->getIdLinhaTelefonica());
        }
        else {
            pclFilaSetClientInfo->insereFilaSetClientInfo();
            ULOG("RSR INSERIU EM INFRA.FILASETCLIENTINFO COM IDFILASETCLIENTINFO[%s]", pclFilaSetClientInfo->getIdFilaSetClientInfo());
        }
        ULOG("Final da Setagem do cliente em tabela <setCliente>");

		xml_g->createTag("SETPPTMAVO");
        xml_g->addProp("xmlns", "atendimento.fo.vivo.com.br/vo");
			   xml_g->addItem("idPessoaDepara", pclPessoaDePara->getIdPessoaDePara());
			   xml_g->addItem("idLinhaTelefonica", tDataProc.szIdLinhaTelefonica);
		xml_g->closeTag();

    }
    catch(PPExceptionTMA ExcepTMA)
    {
        bool statusCodeSetado = false;
        switch(ExcepTMA.getTipo())
        {
            case ERRO_EXECUCAO:
                setStatusCode(E_APLICACAO_PREPAGO, ExcepTMA.getMsg());
                statusCodeSetado = true;
                break;
            case ERRO_PARAMETRO_NULL:
                setStatusCode(E_PARAMETRO_PREPAGO, ExcepTMA.getMsg());
                statusCodeSetado = true;
                break;
            case ERRO_SEQUENCE:
                setStatusCode(W_SEQUENCE_PREPAGO, ExcepTMA.getMsg());
                statusCodeSetado = true;
                break;
            case ERRO_REGISTRO_NAO_ENCONTRADO:
                setStatusCode(W_DELETE_PREPAGO, ExcepTMA.getMsg());
                statusCodeSetado = true;
                break;
            case ERRO_LEGADO_SET_INTERCEPTACAO:
                setStatusCode(E_APLICACAO_PREPAGO, ExcepTMA.getMsg());
                statusCodeSetado = true;
                break;
            default:
                setStatusCode(E_APLICACAO_PREPAGO, ExcepTMA.getMsg());
                statusCodeSetado = true;
                break;
        }

        ULOG("Desalocando objetos de negocio Exception PP");
        delete pclPessoa;
        delete pclPessoaDePara;
        delete pclPessoaFisica;
        delete pclPessoaJuridica;
        delete pclPessoaDocumento;
        delete pclPessoaValorPossivel;
        delete pclPessoaComunicacao;
        delete pclPessoaSegmentacao;
        delete pclPessoaSegmentacaoHistorico;
        delete pclPessoaEndereco;
        delete pclPessoaLinhaHistorico;
        delete pclPessoaLinha;
        delete pclDocumento;
        delete pclSistemaOrigem;
        delete pclLinhaConta;
        delete pclPessoaConta;
        delete pclContaEndereco;
        delete pclTipoEnderecoCobranca;
        delete pclTipoDocumento;
        delete pclParametro;
        // delete pclRegistraContato;
        delete pclUF;
        delete pclFilaSetClientInfo;
        delete pclPessoaLinhaPre;
        delete pclPermissaoLinhaPUP;

        // delete pclXmlGenRegContato;
        delete pclXmlGenSetInterception;
        delete pclXmlGenSetClient;

        delete remoteServiceHabLinha;
        delete inputMessageHabLinha;

        if(pStatusText) {
            ULOG("free pStatusText Exception PPExceptionTMA");
            free(pStatusText); pStatusText=NULL;
        }

        if(pStatusCode) {
            ULOG("free pStatusCode Exception PPExceptionTMA");
            free(pStatusCode); pStatusCode=NULL;
        }

        // if(pMessageBody) {
        //     ULOG("free pMessageBody Exception PPExceptionTMA");
        //     free(pMessageBody); pMessageBody=NULL;
        // }

        ULOGE(">>>Final do processamento de SETPPTMA");

		ULOG_END("SETPPTMA");

        if ( !statusCodeSetado )
        {
            char *msgErr = ExcepTMA.getMsg();

            if ( msgErr && *msgErr )
                setStatusCode("13E0000", msgErr);
            else
                setStatusCode("13E0000", "Erro");
        }

        return;
    }
    catch(...)
    {
        ULOG("Exception");

        ULOG("Desalocando objetos de negocio Exception ...");
        delete pclPessoa;
        delete pclPessoaDePara;
        delete pclPessoaFisica;
        delete pclPessoaJuridica;
        delete pclPessoaDocumento;
        delete pclPessoaValorPossivel;
        delete pclPessoaComunicacao;
        delete pclPessoaSegmentacao;
        delete pclPessoaSegmentacaoHistorico;
        delete pclPessoaEndereco;
        delete pclPessoaLinhaHistorico;
        delete pclPessoaLinha;
        delete pclDocumento;
        delete pclSistemaOrigem;
        delete pclLinhaConta;
        delete pclPessoaConta;
        delete pclContaEndereco;
        delete pclTipoEnderecoCobranca;
        delete pclTipoDocumento;
        delete pclParametro;
        // delete pclRegistraContato;
        delete pclUF;
        delete pclFilaSetClientInfo;
        delete pclPessoaLinhaPre;
        delete pclPermissaoLinhaPUP;

        // delete pclXmlGenRegContato;
        delete pclXmlGenSetInterception;
        delete pclXmlGenSetClient;

        delete remoteServiceHabLinha;
        delete inputMessageHabLinha;

        if(pStatusText) {
            ULOG("free pStatusText Exception ...");
            free(pStatusText); pStatusText=NULL;
        }

        if(pStatusCode) {
            ULOG("free pStatusCode Exception ...");
            free(pStatusCode); pStatusCode=NULL;
        }

        // if(pMessageBody) {
        //     ULOG("free pMessageBody Exception ...");
        //     free(pMessageBody); pMessageBody=NULL;
        // }

        ULOGE(">>>Final do processamento de SETPPTMA");
        ULOG_END("SETPPTMA");

        setStatusCode("13E0000", "Erro desconhecido");

        throw;
    }

    ULOG("Desalocando objetos de negocio");
    delete pclPessoa;
    delete pclPessoaDePara;
    delete pclPessoaFisica;
    delete pclPessoaJuridica;
    delete pclPessoaDocumento;
    delete pclPessoaValorPossivel;
    delete pclPessoaComunicacao;
    delete pclPessoaSegmentacao;
    delete pclPessoaSegmentacaoHistorico;
    delete pclPessoaEndereco;
    delete pclPessoaLinhaHistorico;
    delete pclPessoaLinha;
    delete pclDocumento;
    delete pclSistemaOrigem;
    delete pclLinhaConta;
    delete pclPessoaConta;
    delete pclContaEndereco;
    delete pclTipoEnderecoCobranca;
    delete pclTipoDocumento;
    delete pclParametro;
    // delete pclRegistraContato;
    delete pclUF;
    delete pclFilaSetClientInfo;
    delete pclPessoaLinhaPre;
    delete pclPermissaoLinhaPUP;

    // delete pclXmlGenRegContato;
    delete pclXmlGenSetInterception;
    delete pclXmlGenSetClient;

    delete remoteServiceHabLinha;
    delete inputMessageHabLinha;

    if(pStatusText) {
        ULOG("free pStatusText ok");
        free(pStatusText); pStatusText=NULL;
    }

    if(pStatusCode) {
        ULOG("free pStatusCode ok");
        free(pStatusCode); pStatusCode=NULL;
    }

    // if(pMessageBody) {
    //     ULOG("free pMessageBody ok");
    //     free(pMessageBody); pMessageBody=NULL;
    // }

	 



    ULOG_END("SETPPTMA");
    setStatusCode("13I0000", "Sucesso");
}
