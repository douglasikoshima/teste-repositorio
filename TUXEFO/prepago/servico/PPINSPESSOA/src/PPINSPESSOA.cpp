///////////////////////////////////////////////////////////////////////////////
/**
 * @modulo  prepago
 * @usecase PPINSPESSOA
 * @author  Renato Striitzel Russo
 * @author  Carlos Eduardo Barbosa Braga
 * @author  Eder Martins
 * @version $Revision: 1.1.2.5.16.4.8.2.20.1 $
 * @CVS     $Author: a5114878 $ - $Date: 2015/09/24 17:55:46 $
 **/
///////////////////////////////////////////////////////////////////////////////

#include "tuxfw.h"
#include "Global.h"
#include "Tools.h"
#include "PrePagoException.h"

#include "Documento.h"
#include "Pessoa.h"
#include "PessoaComunicacao.h"
#include "PessoaDocumento.h"
#include "PessoaEndereco.h"
#include "PessoaFisica.h"
#include "PessoaJuridica.h"
#include "PessoaLinha.h"
#include "PessoaConta.h"
#include "PessoaLinhaPre.h"
#include "ContaEndereco.h"
#include "LinhaConta.h"
#include "SistemaOrigem.h"
#include "PessoaValorPossivel.h"
#include "PessoaSegmentacaoHistorico.h"
#include "PessoaSegmentacao.h"
#include "TipoEnderecoCobranca.h"
#include "TipoRelacionamento.h"
#include "PessoaLinhaHistorico.h"
#include "TipoConta.h"
#include "TipoDocumento.h"
#include "FilaSetClientInfo.h"
#include "Conta.h"
#include "UF.h"
#include "Parametro.h"
#include "RegistraContato.h"
#include "PermissaoLinhaPUP.h"


DECLARE_TUXEDO_SERVICE(PPINSPESSOA);

void implPPINSPESSOA::Execute(DOMNode *dnode, XMLGen *xml_g)
{
    ULOG_START("PPINSPESSOA");

    /*--- [A0001] ------------------------------*/
    char vo_idLinhaTelefonica[256];
    char vo_idPessoa[256];
    char vo_idTipoPessoa[256];
    /*------------------------------------------*/

    CPessoa                     *pclPessoa;
    CPessoaSegmentacao          *pclPessoaSegmentacao;
    CConta                      *pclConta;
    CTipoConta                  *pclTipoConta;
    CTipoEnderecoCobranca       *pclTipoEnderecoCobranca;
    CTipoRelacionamento         *pclTipoRelacionamento;
    CSistemaOrigem              *pclSistemaOrigemNgin;
    CPessoaConta                *pclPessoaConta;
    CDocumento                  *pclDocumento;
    CContaEndereco              *pclContaEndereco;
    CTipoDocumento              *pclTipoDocumento;
    CUF                         *pclUF;
    CPessoaLinhaPre             *pclPessoaLinhaPre;
    CFilaSetClientInfo          *pclFilaSetClientInfo;
    CRegistraContato            *pclRegistraContato;
    CParametro                  *pclParametro;
    CPermissaoLinhaPUP          *pclPermissaoLinhaPUP;

    XMLGen                      *pclXmlGen;
    XMLGen                      *pclXmlGenHabLinha;
    XMLGen                      *pclXmlGenRegContato;

    TuxMessage                  *inputMessageHabLinha;
    TuxRemoteService            *remoteServiceHabLinha;

    int iRetSistemaLegado=0;
    char *pStatusCode = NULL;
    char *pStatusText = NULL;

    char szAuxLegado[LEN_AUX + LEN_EOS];
    char szUser[LEN_HEADER + LEN_EOS], *pUser;
    char szArruamento[LEN_AUX + LEN_EOS];
    char szTipoAcao[LEN_AUX + LEN_EOS];
    char szIdGrupoAbertura[LEN_AUX + LEN_EOS];
    char szIdLinhaTelefonicaPUP[LEN_IDLINHATELEFONICA + LEN_EOS];

    bool bIdGrupo = true;
    bool bMudancaTitularidade = false; // Solicitado pela OS 347
    ///////////////////////////////////////////////////////////////////////////////
    //Parte da SM041
    //Quando existe a TAG dsTipoAcao com o valor 'SINCRONIZAR' então significa que faremos tudo como se fosse uma
    //alteração de cadastro, mas sem gravar nada no banco, apenas as validacoes e os envios para o legado.
    //Tudo isto sera controlado pela variavel bFlagSincronizar
    ///////////////////////////////////////
    bool bFlagSincronizar = false;
    int  iCont;

    int iTamSaidaSetClient;
    char *pRet=NULL;

    //De para de tipo de logradouros para o NGIN
    char* pszTipoLogNGIN[] = {
        "ACS", "AL", "AV", "BC", "ESTR", "GLR", "JD", "LAD", "LGO", "PAS","PCA",
        "PRA", "PQ", "PTE", "R", "ROD", "TRAV", "TRV", "V", "VD", "VIA", "VL"
    };
    char* pszTipoLogFO[] = {
        "ACESSO", "ALAMEDA", "AVENIDA", "BECO", "ESTRADA", "GALERIA", "JARDIM",
        "LADEIRA", "LARGO", "PASSAGEM", "PRACA", "PRAIA", "PARQUE", "PONTE",
        "RUA", "RODOVIA", "TRAVESSA", "TREVO", "VALE", "VIADUTO", "VIA", "VIELA"
    };
    //Quantidade de elemento nas arrays acima, se forem modificadas a const a baixo
    //devera ser modificada
    const int iQuantidadeLogradouros = 22;

    try
    {
        char idCanalWrk[40];
        char scdLograd[256];
        char sinCnl[256];
        char scdIBGE[256];
        memset( idCanalWrk, 0x0, sizeof(idCanalWrk) );
        memset( scdLograd, 0x0, sizeof(scdLograd) );
        memset( sinCnl   , 0x0, sizeof(sinCnl) );
        memset( scdIBGE  , 0x0, sizeof(scdIBGE) );

        /*--- [A0001] ---------------------------------------------------*/
        memset( vo_idLinhaTelefonica, 0x0, sizeof(vo_idLinhaTelefonica) );
        memset( vo_idPessoa         , 0x0, sizeof(vo_idPessoa) );
        memset( vo_idTipoPessoa     , 0x0, sizeof(vo_idTipoPessoa) );
        /*---------------------------------------------------------------*/

        DOMNode *DNInicial=NULL;
        DOMNode *DNPrePagoPessoaVO=NULL;
        DOMNode *DNPF=NULL;
        DOMNode *DNDoc=NULL;
        DOMNode *DNContato=NULL;
        DOMNode *DNListaContato=NULL;
        DOMNode *DNLinha=NULL;
        DOMNode *DNPJ=NULL;
        DOMNode *DNEnderecoPrePagoVO=NULL;
        DOMNode *DNEndereco=NULL;
        DOMNode *DNUF=NULL;
        DOMNode *DNPais=NULL;
        DOMNode *DNPUP=NULL;

        TDocumento                   tDocumentoXML;
        TPessoa                      tPessoaXML;
        TPessoaDocumentoB01          tPessoaDocumentoB01XML;
        TPessoaEndereco              tPessoaEnderecoXML;
        TPessoaJuridica              tPessoaJuridicaXML;
        TPessoaFisica                tPessoaFisicaXML;
        TPessoaComunicacao           tPessoaComunicacaoXML;
        TPessoaLinha                 tPessoaLinhaXML;
        TPessoaConta                 tPessoaContaXML;
        TLinhaConta                  tLinhaContaXML;
        TContaEndereco               tContaEnderecoXML;
        TPessoaValorPossivel         tPessoaValorPossivelXML;

        TDesmembraNome               tDesmembraNome;

        memset(&tDocumentoXML,                  0x00, sizeof(TDocumento));
        memset(&tPessoaXML,                     0x00, sizeof(TPessoa));
        memset(&tPessoaDocumentoB01XML,         0x00, sizeof(TPessoaDocumentoB01));
        memset(&tPessoaEnderecoXML,             0x00, sizeof(TPessoaEndereco));
        memset(&tPessoaFisicaXML,               0x00, sizeof(TPessoaFisica));
        memset(&tPessoaJuridicaXML,             0x00, sizeof(TPessoaJuridica));
        memset(&tPessoaComunicacaoXML,          0x00, sizeof(TPessoaComunicacao));
        memset(&tPessoaLinhaXML,                0x00, sizeof(TPessoaLinha));
        memset(&tPessoaContaXML,                0x00, sizeof(TPessoaConta));
        memset(&tLinhaContaXML,                 0x00, sizeof(TLinhaConta));
        memset(&tContaEnderecoXML,              0x00, sizeof(TContaEndereco));
        memset(&tPessoaValorPossivelXML,        0x00, sizeof(TPessoaValorPossivel));

        CPessoaDocumento            clPessoaDocumento;
        CPessoaEndereco             clPessoaEndereco;
        CPessoaJuridica             clPessoaJuridica;
        CPessoaFisica               clPessoaFisica;
        CPessoaComunicacao          clPessoaComunicacao;
        CPessoaLinha                clPessoaLinha;
        CLinhaConta                 clLinhaConta;
        CSistemaOrigem              clSistemaOrigem;
        CPessoaValorPossivel        clPessoaValorPossivel;
        CPessoaSegmentacaoHistorico clPessoaSegmentacaoHistorico;

        pclPessoaConta = new CPessoaConta;
        pclTipoConta = new CTipoConta;
        pclTipoEnderecoCobranca = new CTipoEnderecoCobranca;
        pclTipoRelacionamento = new CTipoRelacionamento;
        pclSistemaOrigemNgin = new CSistemaOrigem;
        pclConta = new CConta;
        pclPessoaSegmentacao = new CPessoaSegmentacao;
        pclPessoa = new CPessoa;
        pclDocumento = new CDocumento;
        pclContaEndereco = new CContaEndereco;
        pclTipoDocumento = new CTipoDocumento;
        pclPessoaLinhaPre = new CPessoaLinhaPre;
        pclFilaSetClientInfo = new CFilaSetClientInfo;
        pclRegistraContato = new CRegistraContato;
        pclUF = new CUF;
        pclParametro = new CParametro;
        pclPermissaoLinhaPUP = new CPermissaoLinhaPUP;

        pclXmlGen = new XMLGen;
        pclXmlGenHabLinha = new XMLGen;
        pclXmlGenRegContato = new XMLGen;


        remoteServiceHabLinha = new TuxRemoteService();
        inputMessageHabLinha = new TuxMessage();

        ///////////////////////////////////////////////////////////////////////
        // IdGrupo = numero da Conta Grupo.
        ///////////////////////////////////////////////////////////////////////
        char szIdGrupo[LEN_IDGRUPO + LEN_EOS];

        char szSubSystem[LEN_HEADER + LEN_EOS];

        char szIdPessoaXML[LEN_IDPESSOA + LEN_EOS];
        char szIdTipoPessoaXML[LEN_IDTIPOPESSOA + LEN_EOS];
        char szIdPessoaComunicacaoXML[LEN_IDPESSOACOMUNICACAO + LEN_EOS];
        char szIdPessoaEnderecoXML[LEN_IDPESSOAENDERECO + LEN_EOS];

        int iIdPessoaDeParaIndex=0;
        int iCtrlTR;
        int iIndexPDP;

        int iIdTipoPessoaLegado=0;

        char szMessageException[LEN_RETURN_MESSAGE + LEN_EOS];
        char szIdPessoaDePara[QTD_ARRAY_PESSOA_DE_PARA][LEN_IDPESSOADEPARA + LEN_EOS];
        char szIdPessoaEndereco[LEN_IDPESSOAENDERECO + LEN_EOS];
        char szinClienteUsuario[LEN_AUX + LEN_EOS];
        char szCdAreaRegistro[LEN_NRLINHA + LEN_EOS];
        char szAux[LEN_AUX + LEN_EOS];
        char *pXML[] = {"Cliente", "Usuario", NULL};
        char szIdCanal[LEN_AUX + LEN_EOS];
        char szNrLinha[LEN_NRLINHA + LEN_EOS];

        bool bFlagInsereLinhaHistorico = false;
        bool bFlagPlanoControle = false;
        bool bInterceptacao = true;
        bool flgSPED = false;


        // Obtem informacoes do header XML
        pUser = getUser();
        if(pUser != NULL)
        {
            if(strlen(pUser) > 0)
                strcpy(szUser, pUser);
            else
                throw PrePagoException(ERRO_EXECUCAO, "Tag user vazia");
        }
        else
            throw PrePagoException(ERRO_EXECUCAO, "Tag user inexistente");

        ULOG("szUser[%s]", szUser);

        GET_HEADER(szSubSystem, getSubSystem(), NOBRIGATORIO); ULOG("szSubSystem[%s]", szSubSystem);
        if(strlen(szSubSystem) == 0)
            clSistemaOrigem.setSgSistemaOrigem(ID_SG_FRONTOFFICCE);
        else
            clSistemaOrigem.setSgSistemaOrigem(szSubSystem);


        if(clSistemaOrigem.buscaSistemaOrigem() == false){
            sprintf(szMessageException, "SistemaOrigem nao encontrado para SgSistemaOrigem[%s]", szSubSystem);
            throw PrePagoException(ERRO_EXECUCAO, szMessageException);
        }

        // Para tratamento de Conta Grupo( Plano Opcoes )
        pclSistemaOrigemNgin->setSgSistemaOrigem(SG_NGIN);

        if(pclSistemaOrigemNgin->buscaSistemaOrigem() == false) {
            sprintf(szMessageException, "SistemaOrigem nao encontrado para SgSistemaOrigem[%s]", SG_NGIN);
            throw PrePagoException(ERRO_EXECUCAO, szMessageException);
        }

        GETTREE(szAux, dnode, "interceptacao", 0, NOBRIGATORIO, "");
        if(!strcmp(szAux, "0"))
            bInterceptacao=false;


        GETTREE(szAux, dnode, "cdControle", 0, NOBRIGATORIO, "");
        if(!strcmp(szAux, "1"))
            bFlagPlanoControle=true;


        // Reunião Ata 19/12/05 definiu que o grupo de abertura
        // seria o PRÉ-PAGO ou URA (ao invés do ADMINISTRADOR) para atender
        // incidência 2285 - Dez/2005 - Cassio
        GETTREE(szIdGrupoAbertura, dnode, "idGrupoAbertura", 0, NOBRIGATORIO, "");
        if ( szIdGrupoAbertura[0] == 0 )
        {
            strcpy(szIdGrupoAbertura,"118"); // Grupo = PRÉ-PAGO na falta de informação em Tag
        }

        GETTREE(szTipoAcao, dnode, "dsTipoAcao", 0, NOBRIGATORIO, "");
        ///////////////////////////////////////////////////////////////////////////////
        //Parte da SM041
        //Quando existe a TAG dsTipoAcao com o valor 'SINCRONIZAR' então significa que faremos tudo como se fosse uma
        //alteração de cadastro, mas sem gravar nada no banco, apenas as validacoes e os envios para o legado.
        //Tudo isto sera controlado pela variavel bFlagSincronizar
        ///////////////////////////////////////
        if( !strcmp(szTipoAcao, "SINCRONIZAR") )
            bFlagSincronizar = true;

        GETTREE(szIdGrupo, dnode, "idGrupo", 0, NOBRIGATORIO, "");
        if(strlen(szIdGrupo) == 0)
            bIdGrupo=false;

        pclTipoEnderecoCobranca->setSgTipoEnderecoCobranca( TIPO_ENDERECO_COBRANCA );
        if(pclTipoEnderecoCobranca->buscaTipoEnderecoCobranca() == false ) {
            sprintf(szMessageException, "TipoEnderecoCobranca nao encontrado para SgTipoEnderecoCobranca[%s]", TIPO_ENDERECO_COBRANCA);
            throw PrePagoException(ERRO_EXECUCAO, szMessageException);
        }

        GETTREE(szinClienteUsuario, dnode, "inClienteUsuario", 0, OBRIGATORIO, "");
        ULOG("szinClienteUsuario[%s]", szinClienteUsuario);

        GETTREE( idCanalWrk, dnode, "idCanal", 0, OBRIGATORIO, "idCanalWrk" );
        ULOG( "idCanalWrk[%s]", idCanalWrk );
        
        if(bIdGrupo == false) {
            GETTREE(szIdCanal, dnode, "idCanal", 0, OBRIGATORIO, "IdCanal");
            ULOG("szIdCanal[%s]", szIdCanal);

            pclXmlGenRegContato->createTag("msg");
            pclXmlGenRegContato->addItem("ProxyOperacao", "getFidelizacao");
            pclXmlGenRegContato->addItem("idChamadaTelefonica", "1");
            // Reunião Ata 19/12/05 definiu que o grupo de abertura
            // seria o PRÉ-PAGO ou URA (ao invés do ADMINISTRADOR) para atender
            // incidência 2285 - Dez/2005 - Cassio
            pclXmlGenRegContato->addItem("idGrupoAbertura", szIdGrupoAbertura);
            pclXmlGenRegContato->addItem("inResponsavelAbertura", "2");
            pclXmlGenRegContato->addItem("tpOperacao", "1");

            pclXmlGenRegContato->createTag("ProcedenciaVO");
            pclXmlGenRegContato->addItem("idProcedencia", "1");
            pclXmlGenRegContato->closeTag();

            pclXmlGenRegContato->createTag("CanalVO");
            pclXmlGenRegContato->addItem("idCanal", szIdCanal);
            pclXmlGenRegContato->closeTag();
        }

        // Seta informacoes para acesso ao sistema legado
        pclXmlGen->createTag("msg");
        pclXmlGen->addItem("ProxyOperacao", "setCliente");
        pclXmlGen->addItem("usuario", "FO" );

        if ( !memcmp(idCanalWrk,"666",3) )
        {
            pclXmlGen->addItem( "idCanal", "666" );
        }

        if(bIdGrupo == false) {
            // Obtendo dados para o sistema legado
            if((DNLinha = walkDOM(dnode, "Linha", 0))) {
                GETTREE_LEGADO(szAuxLegado, DNLinha, "nrLinha", 0, OBRIGATORIO, LEN_NUMBER_LEGADO, "Numero da linha");
            }
            alltrim(szAuxLegado);

            ULOG("szAuxLegado[%s]", szAuxLegado);
            pclXmlGenRegContato->addItem("nrTelefone", szAuxLegado);
            pclXmlGen->addItem("ProxyLinha", szAuxLegado);
            pclXmlGenHabLinha->addItem("ProxyLinha", szAuxLegado);

            pclRegistraContato->setIdUsuarioAlteracao(szUser);
            pclRegistraContato->setNrTelefone(szAuxLegado);

       }
        else
        {
            pclXmlGen->addItem("ProxyLinha", "");
            pclXmlGen->addItem("idGrupo", szIdGrupo);
            pclXmlGenHabLinha->addItem("ProxyLinha", "");
            pclXmlGenHabLinha->addItem("idGrupo", szIdGrupo);
        }


        if((DNPrePagoPessoaVO = walkDOM(dnode, "PrePagoPessoaVO", 0))) {
            GETTREE(szAuxLegado, DNPrePagoPessoaVO, "inTipoPessoa", 0, OBRIGATORIO, "");

            if(!strcmp(szAuxLegado, PP_PESSOA_FISICA)) {
                pclXmlGen->addItem("tipoCliente", "P");
                iIdTipoPessoaLegado = PESSOA_FISICA;
            } else if(!strcmp(szAuxLegado, PP_PESSOA_JURIDICA)) {
                pclXmlGen->addItem("tipoCliente", "E");
                iIdTipoPessoaLegado = PESSOA_JURIDICA;
            } else {
                sprintf(szMessageException, "inTipoPessoa/IdTipoPessoa invalido [%s]", szAuxLegado);
                throw PrePagoException(ERRO_EXECUCAO, szMessageException);
            }
        }


        // Referente ao sistema legado
        if(iIdTipoPessoaLegado == PESSOA_FISICA) {
            if((DNPF = walkDOM(DNPrePagoPessoaVO, "PF", 0))) {
                GETTREE_LEGADO(szAuxLegado, DNPF, "nmPessoa", 0, OBRIGATORIO, LEN_NOME_PESSOA_LEGADO, "Nome da Pessoa");
                pclXmlGen->addItem("nome", szAuxLegado);
            }

            // pclXmlGen->addItem("nomeAbreviado", "");
            pclXmlGen->addItem("confidencial", "N");
        } else if(iIdTipoPessoaLegado == PESSOA_JURIDICA) {
            // pclXmlGen->addItem("nomeSufixo", "");
            // pclXmlGen->addItem("primeiroNome", "");
            // pclXmlGen->addItem("sobreNome", "");
        }

        for(int iContXML=0;
            pXML[iContXML] != NULL;
            iContXML++)
        {
            ULOG("iContXML(%d)", iContXML);

            if((DNInicial = walkDOM(dnode, pXML[iContXML], 0)))
            {
                for(int iIndexPrePagoVO=0;
                    (DNPrePagoPessoaVO = walkDOM(DNInicial, "PrePagoPessoaVO", iIndexPrePagoVO));
                    iIndexPrePagoVO++)
                {
                    ULOG("iIndexPrePagoVO(%d)", iIndexPrePagoVO);

                    if(iContXML == 0)
                    {
                        if(bIdGrupo == false) {

                            if(!strcmp(szTipoAcao, "TITULARIDADE")) {
                                strcpy(szAux, "PREPAGOTROCATITULARIDADE");
                                bMudancaTitularidade = true;  // Solicitado pela OS 347
                            }
                            else if(!strcmp(szTipoAcao, "ALTERAR"))
                                strcpy(szAux, "PREPAGOALTERACAOCADASTRO");
                            else if(!strcmp(szTipoAcao, "CADASTRAR"))
                                strcpy(szAux, "PREPAGONOVOCADASTRO");
                            else if(!strcmp(szTipoAcao, "SINCRONIZAR"))
                                strcpy(szAux, "PREPAGOSINCRONISMOCADASTRO");
                            else
                                throw PrePagoException(ERRO_EXECUCAO, "Nao foi possivel obter Parametro para pesquisa em APOIO.PARAMETRO");

                            pclParametro->setCdParametro(szAux);
                            if(pclParametro->buscaParametro() == false) {
                                throw PrePagoException(ERRO_EXECUCAO, "Pesquisa em APOIO.PARAMETRO não encontrou nenhum registro");
                            }

                            // Referente ao processo Registra Contato
                            pclXmlGenRegContato->createTag("ArvoreAtendimentoVO");
                                pclXmlGenRegContato->addItem("idContato", pclParametro->getDsValorParametro());
                                pclXmlGenRegContato->createTag("CarterizacaoVO");
                                    //Se for PESSOA_FISICA então assume como não carteirizado
                                    //Como, mesmo PESSOA_JURIDICA, a TAG é opcional então eu já assumo 13
                                    //O valor só será modificado se for PESSOA_JURIDICA e existir a TAG <idClassificacaoEmpresaSelecionado>
                                    strcpy(szAux, "13");
                                    //Se for PESSOA_JURIDICA então busca o tipo de carteira
                                    if(iIdTipoPessoaLegado == PESSOA_JURIDICA) {
                                        if((DNPJ = walkDOM(DNPrePagoPessoaVO, "PJ", 0)) != NULL) {
                                            GETTREE(szAux, DNPJ, "idClassificacaoEmpresaSelecionado", 0, NOBRIGATORIO, "");
                                            if(strlen(szAux) == 0)
                                                strcpy(szAux, "13");
                                        }
                                    }
                                    pclXmlGenRegContato->addItem("idTipoCarteira", szAux);

                                pclXmlGenRegContato->closeTag();
                                pclXmlGenRegContato->createTag("SegmentacaoVO");
                                    pclXmlGenRegContato->addItem("idSegmentacao", "11");
                                pclXmlGenRegContato->closeTag();
                            pclXmlGenRegContato->closeTag();
                        }
                    }

                    // Obtem o IdPessoa
                    GETTREE(tPessoaXML.szIdPessoa, DNPrePagoPessoaVO, "idPessoa", 0, NOBRIGATORIO, "");
                    GETTREE(szAux, DNPrePagoPessoaVO, "inTipoPessoa", 0, OBRIGATORIO, "");
                    if(!strcmp(szAux, PP_PESSOA_FISICA))
                        sprintf(tPessoaXML.szIdTipoPessoa, "%d", PESSOA_FISICA);
                    else if(!strcmp(szAux, PP_PESSOA_JURIDICA))
                        sprintf(tPessoaXML.szIdTipoPessoa, "%d", PESSOA_JURIDICA);
                    else {
                        sprintf(szMessageException, "inTipoPessoa/IdTipoPessoa invalido [%s]", szAux);
                        throw PrePagoException(ERRO_EXECUCAO, szMessageException);
                    }

                    ULOG("tPessoaXML.szIdTipoPessoa[%s]", tPessoaXML.szIdTipoPessoa);

                    // Verifica o tipo da pessoa
                    if(atoi(tPessoaXML.szIdTipoPessoa) == PESSOA_FISICA) {
                        if((DNPF = walkDOM(DNPrePagoPessoaVO, "PF", 0))) {
                            GETTREE(tPessoaXML.szNmPessoa, DNPF, "nmPessoa", 0, OBRIGATORIO, "Nome da Pessoa");

                            // Realiza Desmembramento do Nome da Pessoa Fisica.
                            memset(&tDesmembraNome, 0x00, sizeof(TDesmembraNome));
                            strcpy(tDesmembraNome.szNomeCompleto, tPessoaXML.szNmPessoa);
                            DesmembraNome(&tDesmembraNome);

                            strcpy(tPessoaXML.szNmNome, tDesmembraNome.szNomePrimeiro);
                            strcpy(tPessoaXML.szNmNomeMeio, tDesmembraNome.szNomeMeio);
                            strcpy(tPessoaXML.szNmSobrenome, tDesmembraNome.szNomeFim);

                            GETTREE(tPessoaFisicaXML.szIdPessoa, DNPrePagoPessoaVO, "idPessoa", 0, NOBRIGATORIO, "");
                            GETTREE(tPessoaFisicaXML.szIdSexo, DNPF, "idSexoSelecionado", 0, OBRIGATORIO, "Sexo");
                            GETTREE(tPessoaFisicaXML.szIdEstadoCivil, DNPF, "idEstadoCivilSelecionado", 0, NOBRIGATORIO, "");
                            if(strlen(tPessoaFisicaXML.szIdEstadoCivil) == 0)
                                strcpy(tPessoaFisicaXML.szIdEstadoCivil, "0");

                            GETTREE(tPessoaFisicaXML.szDtNascimento, DNPF, "dtNascimento", 0, NOBRIGATORIO, "");
                            ConvDataFromJava(tPessoaFisicaXML.szDtNascimento);
                            GETTREE(tPessoaXML.szDsCargoContato, DNPF, "dsProfissao", 0, NOBRIGATORIO, "");

                            // Referente ao sistema legado
                            if(iContXML == 0)
                            {
                                GETTREE_LEGADO(szAuxLegado, DNPF, "dtNascimento", 0, NOBRIGATORIO, LEN_DATE_LEGADO, "");
                                pclXmlGen->addItem("dataNascimento", szAuxLegado);

                                GETTREE(szAux, DNPF, "idEstadoCivilSelecionado", 0, NOBRIGATORIO, "");
                                if(atoi(szAux) == ID_TIPO_EC_SOLTEIRO)
                                    strcpy(szAuxLegado, "S");
                                else if(atoi(szAux) == ID_TIPO_EC_CASADO)
                                    strcpy(szAuxLegado, "C");
                                else if(atoi(szAux) == ID_TIPO_EC_VIUVO)
                                    strcpy(szAuxLegado, "V");
                                else if(atoi(szAux) == ID_TIPO_EC_DIVORCIADO)
                                    strcpy(szAuxLegado, "D");
                                else
                                    memset(szAuxLegado, 0x00, sizeof(szAuxLegado));

                                pclXmlGen->addItem("estadoCivil", szAuxLegado);

                                GETTREE(szAuxLegado, DNPF, "idSexoSelecionado", 0, OBRIGATORIO, "Sexo");
                                if(atoi(szAuxLegado) == ID_SEXO_MASCULINO)
                                    pclXmlGen->addItem("codSexo", "M");
                                else if(atoi(szAuxLegado) == ID_SEXO_FEMININO)
                                    pclXmlGen->addItem("codSexo", "F");
                                else
                                    pclXmlGen->addItem("codSexo", "");
                            }
                        }
                        else
                        {
                            throw PrePagoException(ERRO_PARAMETRO_NULL, "Tag de PessoaFisica inexistente");
                        }
                    }
                    else if(atoi(tPessoaXML.szIdTipoPessoa) == PESSOA_JURIDICA)
                    {
                        GETTREE(tPessoaJuridicaXML.szIdPessoa, DNPrePagoPessoaVO, "idPessoa", 0, NOBRIGATORIO, "");

                        if((DNPJ = walkDOM(DNPrePagoPessoaVO, "PJ", 0)))
                        {
                            GETTREE(tPessoaJuridicaXML.szDtFundacao, DNPJ, "dtFundacao", 0, OBRIGATORIO, "Data de Fundacao");
                            ConvDataFromJava(tPessoaJuridicaXML.szDtFundacao);
                            GETTREE(tPessoaJuridicaXML.szNmFantasia, DNPJ, "nmFantasia", 0, OBRIGATORIO, "Nome Fantasia");

                            GETTREE_LEGADO(szAuxLegado, DNPJ, "dsRazaoSocial", 0, OBRIGATORIO, LEN_NOME_PESSOA_LEGADO, "Razao Social");
                            pclXmlGen->addItem("nome", szAuxLegado);

                            GETTREE(szAuxLegado, DNPJ, "nmFantasia", 0, OBRIGATORIO, "Nome Fantasia");
                            pclXmlGen->addItem("nomeAbreviado", "");

                            GETTREE(tPessoaJuridicaXML.szNmPessoaFilial, DNPJ, "dsRazaoSocial", 0, OBRIGATORIO, "Razao Social");
                            GETTREE(tPessoaJuridicaXML.szIdCFOP, DNPJ, "idTributariaSelecionado", 0, OBRIGATORIO, "Classificacao Tributaria");

                            GETTREE(tPessoaXML.szNmPessoa, DNPJ, "dsRazaoSocial", 0, OBRIGATORIO, "Razao Social");
                            memset(&tDesmembraNome, 0x00, sizeof(TDesmembraNome));
                            strcpy(tDesmembraNome.szNomeCompleto, tPessoaXML.szNmPessoa);
                            ULOG("tDesmembraNome.szNomeCompleto[%s]", tDesmembraNome.szNomeCompleto);

                            DesmembraNome(&tDesmembraNome);
                            ULOG("tDesmembraNome.szNomeCompleto[%s]", tDesmembraNome.szNomeCompleto);
                            ULOG("tDesmembraNome.szNomePrimeiro[%s]", tDesmembraNome.szNomePrimeiro);
                            ULOG("tDesmembraNome.szNomeMeio[%s]", tDesmembraNome.szNomeMeio);
                            ULOG("tDesmembraNome.szNomeFim[%s]", tDesmembraNome.szNomeFim);

                            strcpy(tPessoaXML.szNmNome, tDesmembraNome.szNomePrimeiro);
                            strcpy(tPessoaXML.szNmNomeMeio, tDesmembraNome.szNomeMeio);
                            strcpy(tPessoaXML.szNmSobrenome, tDesmembraNome.szNomeFim);

                            GETTREE(tPessoaXML.szIdTipoCarteira, DNPJ, "idClassificacaoEmpresaSelecionado", 0, NOBRIGATORIO, "");

                            if(iContXML == 0) {
                                GETTREE_LEGADO(szAuxLegado, DNPJ, "nrInscricao", 0, OBRIGATORIO, LEN_IE_LEGADO, "Inscricao");
                                pclXmlGen->addItem("IE", szAuxLegado);
                            }
                        }
                        else
                        {
                            throw PrePagoException(ERRO_PARAMETRO_NULL, "Tag de PessoaJuridica inexistente");
                        }
                    }
                    else
                    {
                        sprintf(szMessageException, "Tipo de pessoa invalida [%s]", tPessoaXML.szIdTipoPessoa);
                        throw PrePagoException(ERRO_EXECUCAO, szMessageException);
                    }


                    strcpy(szIdPessoaXML, tPessoaXML.szIdPessoa);
                    strcpy(szIdTipoPessoaXML, tPessoaXML.szIdTipoPessoa);


                    if(strlen(tPessoaXML.szIdTipoCarteira) == 0)
                        strcpy(tPessoaXML.szIdTipoCarteira, "13"); // A pedido do Vitor para atender ao Miguel

                    pclPessoa->setStruct(&tPessoaXML);

                    // CAMPOS NOT NULL
                    pclPessoa->setInFalecimentoInformado("0");
                    pclPessoa->setIdUf("1");
                    pclPessoa->setIdProbInadimplencia("5"); // NC - Não Classificado
                    pclPessoa->setIdChurnProbabilidade("5"); // NC - Não Classificado
                    pclPessoa->setIdSistemaOrigem(ID_FRONTOFFICE);
                    pclPessoa->setIdUsuarioAlteracao(szUser);
                    pclPessoa->setIdUsuarioAlteracaoDePara(szUser);
                    pclPessoa->setIdSistemaOrigem(clSistemaOrigem.getIdSistemaOrigem());

                    ULOG("PESSOA");
                    if(strlen(szIdPessoaXML) == 0)
                    {
                        if( !bFlagSincronizar ) pclPessoa->inserePessoa();
                    }
                    else
                    {
                        if( !bFlagSincronizar ) pclPessoa->atualizaPessoa();

                        // Busca IdPessoaDePara
                        pclPessoa->resetIdPessoaDePara();
                        pclPessoa->setDeParaIdPessoa(szIdPessoaXML);

                        if(pclPessoa->buscaPessoaDePara() == false)
                        {
                            sprintf(szMessageException, "Nao ha registro em customer.pessoadepara para idpessoa[%s]", szIdPessoaXML);
                            throw PrePagoException(ERRO_EXECUCAO, szMessageException);
                        }
                    }

                    if(iIdPessoaDeParaIndex < QTD_ARRAY_PESSOA_DE_PARA) {
                        strcpy(szIdPessoaDePara[iIdPessoaDeParaIndex++], pclPessoa->getIdPessoaDePara());
                    }
                    else
                        throw PrePagoException(ERRO_EXECUCAO, "DEBUG iIdPessoaDeParaIndex");


                    ULOG("szIdPessoaDePara[%d][%s]", iIdPessoaDeParaIndex-1, szIdPessoaDePara[iIdPessoaDeParaIndex-1]);

                    if(atoi(szIdTipoPessoaXML) == PESSOA_FISICA) {
                        clPessoaFisica.setStruct(&tPessoaFisicaXML);
                        clPessoaFisica.setIdPais("0");
                        clPessoaFisica.setIdTratamento("0");
                        clPessoaFisica.setIdPessoa(pclPessoa->getIdPessoa());
                        clPessoaFisica.setIdUsuarioAlteracao(szUser);

                        TPessoaFisica tPessoaFisicaTmp = clPessoaFisica.tPessoaFisica;

                        ULOG("PESSOAFISICA");


                        if(clPessoaFisica.buscaPessoaFisica(&tPessoaFisicaTmp))
                        {
                            if( !bFlagSincronizar ) clPessoaFisica.atualizaPessoaFisica();
                        }
                        else
                        {
                            if( !bFlagSincronizar ) clPessoaFisica.inserePessoaFisica();
                        }

                    }
                    else if(atoi(szIdTipoPessoaXML) == PESSOA_JURIDICA)
                    {
                        clPessoaJuridica.setStruct(&tPessoaJuridicaXML);
                        clPessoaJuridica.setIdPessoa(pclPessoa->getIdPessoa());
                        clPessoaJuridica.setIdUsuarioAlteracao(szUser);

                        TPessoaJuridica tPessoaJuridicaTmp = clPessoaJuridica.tPessoaJuridica;

                        ULOG("PESSOAJURIDICA");

                        if(clPessoaJuridica.buscaPessoaJuridica(&tPessoaJuridicaTmp))
                        {
                            if( !bFlagSincronizar ) clPessoaJuridica.atualizaPessoaJuridica();
                        }
                        else
                        {
                            if( !bFlagSincronizar ) clPessoaJuridica.inserePessoaJuridica();
                        }

                        // Referente a PessoaSegmentacaoHistorico e PessoaSegmentacao
                        pclPessoaSegmentacao->setIdPessoaDePara(pclPessoa->getIdPessoaDePara());

                        ULOG("PESSOASEGMENTACAO");
                        if(pclPessoaSegmentacao->buscaPessoaSegmentacao() == false)
                        {
                            clPessoaSegmentacaoHistorico.setIdSegmentacao("11");
                            clPessoaSegmentacaoHistorico.setIdPessoaDePara(pclPessoa->getIdPessoaDePara());
                            clPessoaSegmentacaoHistorico.setIdUsuarioAlteracao(szUser);
                            if( !bFlagSincronizar ) clPessoaSegmentacaoHistorico.inserePessoaSegmentacaoHistorico();

                            pclPessoaSegmentacao->setIdPessoaDePara(pclPessoa->getIdPessoaDePara());
                            pclPessoaSegmentacao->setIdPessoaSegmentacao(clPessoaSegmentacaoHistorico.getIdPessoaSegmentacao());
                            pclPessoaSegmentacao->setIdUsuarioAlteracao(szUser);
                            if( !bFlagSincronizar ) pclPessoaSegmentacao->inserePessoaSegmentacao();
                        }
                    }

                    bool blTemDocumentoObrig = false;

                    // Tratamento Documento
                    for(int iIndexDoc=0;
                        (DNDoc = walkDOM(DNPrePagoPessoaVO, "Doc", iIndexDoc));
                        iIndexDoc++)
                    {
                        ULOG("iIndexDoc(%d)", iIndexDoc);

                        if(iIndexDoc == 0) {
                            // Referenta a PessoaDocumento
                            clPessoaDocumento.setIdPessoa(pclPessoa->getIdPessoa());
                            clPessoaDocumento.setIdUsuarioAlteracao(szUser);
                            clPessoaDocumento.setIdSistemaOrigem(clSistemaOrigem.getIdSistemaOrigem());

                            if( !bFlagSincronizar ) clPessoaDocumento.apagaPessoaDocumento();
                        }

                        // Referente a Documento
                        GETTREE(tDocumentoXML.szIdDocumento, DNDoc, "idDoc", 0, NOBRIGATORIO, "");
                        GETTREE(tDocumentoXML.szSgOrgaoExpedidor, DNDoc, "dsOrgaoEmissorDoc", 0, NOBRIGATORIO, "");
                        GETTREE(tDocumentoXML.szNrDocumento, DNDoc, "nrDoc", 0, OBRIGATORIO, "Numero do Documento");
                        TrataNrDocumento(tDocumentoXML.szNrDocumento);

                        //Caso soh venha documento somente com caracteres especiais eh cadastrado o documento "0"
                        if( strlen( tDocumentoXML.szNrDocumento ) <= 0 )
                            strcpy( tDocumentoXML.szNrDocumento, "0" );

                        GETTREE(szAux, DNDoc, "idTipoDoc", 0, OBRIGATORIO, "Tipo de Documento");
                        pclTipoDocumento->setSgClassificacao(szAux);
                        if(pclTipoDocumento->buscaIdTipoDocumento() == false) {
                            sprintf(szMessageException, "Nao ha registro em apoio.tipodocumento para SgClassificacao[%s]", szAux);
                            throw PrePagoException(ERRO_EXECUCAO, szMessageException);
                        }

                        strcpy(tDocumentoXML.szIdTipoDocumento, pclTipoDocumento->getIdTipoDocumento());


                        // Busca a SgTipoDocumento correspondente ao IdTipoDocumento
                        if(pclTipoDocumento->buscaTipoDocumento() == false) {
                            sprintf(szMessageException, "Nao ha registro em apoio.tipodocumento para idtipodocumento[%s]", szAux);
                            throw PrePagoException(ERRO_EXECUCAO, szMessageException);
                        }

                        if(iContXML == 0) {
                            if(iIdTipoPessoaLegado == PESSOA_FISICA) {
                                if( ( strcmp(pclTipoDocumento->getSgTipoDocumento(), SG_CPF   ) == 0 ) ||
                                    ( strcmp(pclTipoDocumento->getSgTipoDocumento(), SG_FCPF  ) == 0 ) ||
                                    ( strcmp(pclTipoDocumento->getSgTipoDocumento(), SG_KLCPF ) == 0 )  )
                                {
                                    GETTREE(szAuxLegado, DNDoc, "nrDoc", 0, OBRIGATORIO, "Numero do Documento");
                                    TrataNrDocumento(szAuxLegado);
                                    szAuxLegado[LEN_CPF_LEGADO]=0x00;
                                    //Caso soh venha documento somente com caracteres especiais eh cadastrado o documento "0"
                                    if( strlen( szAuxLegado ) <= 0 )
                                        strcpy( szAuxLegado, "0" );
                                    pclXmlGen->addItem("CPF", szAuxLegado);
                                    pclXmlGen->addItem("tipoCPF", "D");
                                    blTemDocumentoObrig = true;
                                } else if(!strcmp(pclTipoDocumento->getSgTipoDocumento(), SG_RG)) {
                                    GETTREE(szAuxLegado, DNDoc, "nrDoc", 0, OBRIGATORIO, "Numero do Documento");
                                    TrataNrDocumento(szAuxLegado);
                                    szAuxLegado[LEN_RG_LEGADO]=0x00;
                                    //Caso soh venha documento somente com caracteres especiais eh cadastrado o documento "0"
                                    if( strlen( szAuxLegado ) <= 0 )
                                        strcpy( szAuxLegado, "0" );
                                    pclXmlGen->addItem("RG", szAuxLegado);
                                    pclXmlGen->addItem("tipoRG", "D");

                                    GETTREE_LEGADO(szAuxLegado, DNDoc, "dtExpedicaoDoc", 0, NOBRIGATORIO, LEN_DATE_LEGADO, "");
                                    pclXmlGen->addItem("dataExpiracao", szAuxLegado);

                                    GETTREE_LEGADO(szAuxLegado, DNDoc, "dsOrgaoEmissorDoc", 0, NOBRIGATORIO, LEN_ORGAO_EXPEDITOR_LEGADO, "");
                                    pclXmlGen->addItem("orgaoExpeditor", szAuxLegado);

                                    //Recuperando o ID de UF do XML IN
                                    GETTREE(szAuxLegado, DNDoc, "idUFDoc", 0, NOBRIGATORIO, "");
                                    //Buscando sigla na base
                                    pclUF->setIdUF( szAuxLegado );
                                    if( pclUF->buscaUF() )
                                    {
                                        //Apontando a sigla para o xml OUT para o TuxProxyBE
                                        char szEstadoExpedicao[LEN_ESTADO_EXPEDICAO_LEGADO + LEN_EOS];
                                        memset(szEstadoExpedicao, 0x00, sizeof(szEstadoExpedicao));

                                        // Verificar Max
                                        strncpy(szEstadoExpedicao, pclUF->getSgUF(), LEN_ESTADO_EXPEDICAO_LEGADO);

                                        if( strcmp( szEstadoExpedicao, "NC") != 0 )
                                            pclXmlGen->addItem("estadoExpedicao", szEstadoExpedicao );
                                    }

                                    blTemDocumentoObrig = true;
                                } else if(!strcmp(pclTipoDocumento->getSgTipoDocumento(), SG_PASS)) {
                                    GETTREE(szAuxLegado, DNDoc, "nrDoc", 0, OBRIGATORIO, "Numero do Documento");
                                    TrataNrDocumento(szAuxLegado);
                                    szAuxLegado[LEN_PASSAPORTE_LEGADO]=0x00;
                                    //Caso soh venha documento somente com caracteres especiais eh cadastrado o documento "0"
                                    if( strlen( szAuxLegado ) <= 0 )
                                        strcpy( szAuxLegado, "0" );
                                    pclXmlGen->addItem("passaporte", szAuxLegado);
                                    pclXmlGen->addItem("tipoPassaporte", "D");
                                    blTemDocumentoObrig = true;
                                }
                            }
                            else if(iIdTipoPessoaLegado == PESSOA_JURIDICA)
                            {
                                if( ( strcmp(pclTipoDocumento->getSgTipoDocumento(), SG_CNPJ)  == 0 ) ||
                                    ( strcmp(pclTipoDocumento->getSgTipoDocumento(), SG_OCNPJ) == 0 ) ||
                                    ( strcmp(pclTipoDocumento->getSgTipoDocumento(), SG_UCNPJ) == 0 )  )
                                {
                                    GETTREE(szAuxLegado, DNDoc, "nrDoc", 0, OBRIGATORIO, "Numero do Documento");
                                    TrataNrDocumento(szAuxLegado);
                                    szAuxLegado[LEN_CNPJ_LEGADO]=0x00;
                                    //Caso soh venha documento somente com caracteres especiais eh cadastrado o documento "0"
                                    if( strlen( szAuxLegado ) <= 0 )
                                        strcpy( szAuxLegado, "0" );
                                    pclXmlGen->addItem("CNPJ", szAuxLegado);
                                    blTemDocumentoObrig = true;
                                } else if(!strcmp(pclTipoDocumento->getSgTipoDocumento(), SG_CNAE)) {
                                    GETTREE(szAuxLegado, DNDoc, "nrDoc", 0, OBRIGATORIO, "Numero do Documento");
                                    TrataNrDocumento(szAuxLegado);
                                    szAuxLegado[LEN_CNAE_LEGADO]=0x00;
                                    //Caso soh venha documento somente com caracteres especiais eh cadastrado o documento "0"
                                    if( strlen( szAuxLegado ) <= 0 )
                                        strcpy( szAuxLegado, "0" );
                                    pclXmlGen->addItem("CNAE", szAuxLegado);

                                }
                            }
                        }


                        GETTREE(tDocumentoXML.szDtEmissao, DNDoc, "dtExpedicaoDoc", 0, NOBRIGATORIO, "");
                        ConvDataFromJava(tDocumentoXML.szDtEmissao);
                        GETTREE(tDocumentoXML.szSgOrgaoExpedidor, DNDoc, "dsOrgaoEmissorDoc", 0, NOBRIGATORIO, "");
                        GETTREE(tDocumentoXML.szIdUf, DNDoc, "idUFDoc", 0, NOBRIGATORIO, "");
                        if(strlen(tDocumentoXML.szIdUf) == 0)
                            strcpy(tDocumentoXML.szIdUf, "0");

                        pclDocumento->setStruct(&tDocumentoXML);
                        pclDocumento->setIdPais("1");
                        pclDocumento->setIdUsuarioAlteracao(szUser);

                        TDocumento tDocumentoTmp = pclDocumento->tDocumento;

                        // Busca Documento por NrDocumento, IdTipoDocumento, IdUf e SgOrgaoexpedidor
                        if(pclDocumento->buscaDocumentoChaveComposta(&tDocumentoTmp) == false)
                        {
                            if( !bFlagSincronizar ) pclDocumento->insereDocumento();
                            clPessoaDocumento.setIdDocumento(pclDocumento->getIdDocumento());
                        }
                        else
                        {
                            if( !bFlagSincronizar ) clPessoaDocumento.setIdDocumento(tDocumentoTmp.szIdDocumento);
                        }

                        clPessoaDocumento.setIdDocumentoSistemaOrigem(pclTipoDocumento->getSgClassificacao());

                        if( !bFlagSincronizar ) clPessoaDocumento.inserePessoaDocumento();
                    } // Loop de Documento

                    if(iContXML == 0) {
                        if(blTemDocumentoObrig == false)
                            throw new TuxBasicSvcException("24E9001", "Nenhum documento obrigatorio encontrado");
                    }

                    if((DNContato = walkDOM(DNPrePagoPessoaVO, "Contato", 0)))
                    {
                        for(int iIndexListaContato=0;
                            (DNListaContato = walkDOM(DNContato, "ListCont", iIndexListaContato));
                            iIndexListaContato++)
                        {
                            ULOG("iIndexListaContato (%d)", iIndexListaContato);
							char credenciada[256];
							memset(credenciada,0,sizeof(credenciada));

                            GETTREE(tPessoaComunicacaoXML.szIdPessoaComunicacao, DNListaContato, "idContato", 0, NOBRIGATORIO, "");
                            GETTREE(tPessoaComunicacaoXML.szIdTipoComunicacao, DNListaContato, "idTipoContatoSelecionado", 0, OBRIGATORIO, "Tipo de Telefone");
                            GETTREE(tPessoaComunicacaoXML.szDsContato, DNListaContato, "dsContato", 0, OBRIGATORIO, "Numero do Telefone");
                            GETTREE(tPessoaComunicacaoXML.szNmContato, DNListaContato, "nmContato", 0, NOBRIGATORIO, "");
							GETTREE(credenciada, DNListaContato, "credenciada", 0, NOBRIGATORIO, "");
							
							if (!strcmp(credenciada,"true")) {
								pclXmlGen->addItem("InTelContato", tPessoaComunicacaoXML.szDsContato);
							}

                            if(bIdGrupo == false && iContXML == 0) {
                                GETTREE(szAux, DNListaContato, "nmContato", 0, NOBRIGATORIO, "");
                                pclXmlGenRegContato->addItem("nmContato", szAux);
                            }

                            strcpy(tPessoaComunicacaoXML.szIdPessoa, pclPessoa->getIdPessoa());
                            strcpy(szIdPessoaComunicacaoXML, tPessoaComunicacaoXML.szIdPessoaComunicacao);

                            clPessoaComunicacao.setStruct(&tPessoaComunicacaoXML);

                            clPessoaComunicacao.setIdSistemaOrigem(ID_FRONTOFFICE);
                            clPessoaComunicacao.setIdUsuarioAlteracao(szUser);
                            clPessoaComunicacao.setIdSistemaOrigem(clSistemaOrigem.getIdSistemaOrigem());

                            ULOG("PESSOACOMUNICACAO");

                            if(strlen(szIdPessoaComunicacaoXML) == 0)
                            {
                                if( !bFlagSincronizar ) clPessoaComunicacao.inserePessoaComunicacao();
                            }
                            else
                            {
                                if( !bFlagSincronizar ) clPessoaComunicacao.atualizaPessoaComunicacao();
                            }

                            if(iContXML == 0)
                            {
                                // Referente ao sistema legado
                                if(iIdTipoPessoaLegado == PESSOA_FISICA)
                                {
                                    GETTREE(szAux, DNListaContato, "idTipoContatoSelecionado", 0, OBRIGATORIO, "Tipo de Telefone");
                                    if((atoi(szAux) == ID_TIPO_COMUNICACAO_TEL_RES) || (atoi(szAux) == ID_TIPO_COMUNICACAO_TEL_COM)) {
                                        GETTREE_LEGADO(szAuxLegado, DNListaContato, "dsContato", 0, OBRIGATORIO, LEN_TELEFONE_LEGADO, "Numero do Telefone");
                                        pclXmlGen->addItem("telefone", szAuxLegado);
                                    }
                                    else if(atoi(szAux) == ID_TIPO_COMUNICACAO_FAX) {
                                        GETTREE_LEGADO(szAuxLegado, DNListaContato, "dsContato", 0, OBRIGATORIO, LEN_FAX_LEGADO, "Numero do Fax");
                                        pclXmlGen->addItem("fax", szAuxLegado);
                                    }
                                    else if(atoi(szAux) == ID_TIPO_COMUNICACAO_EMAIL_PARTICULAR) {
                                        GETTREE_LEGADO(szAuxLegado, DNListaContato, "dsContato", 0, OBRIGATORIO, LEN_EMAIL_LEGADO, "Email");
                                        pclXmlGen->addItem("eMail", szAuxLegado);
                                        pclXmlGen->addItem("numDepend", "");
                                    }
                                    else if(atoi(szAux) == ID_TIPO_COMUNICACAO_PASSAPORTE) {
                                        GETTREE_LEGADO(szAuxLegado, DNListaContato, "dsContato", 0, OBRIGATORIO, LEN_PASSAPORTE_LEGADO, "Numero do Contato");
                                        pclXmlGen->addItem("passaporte", szAuxLegado);
                                    }
                                }
                                else if(iIdTipoPessoaLegado == PESSOA_JURIDICA)
                                {
                                    GETTREE_LEGADO(szAuxLegado, DNListaContato, "dsContato", 0, OBRIGATORIO, LEN_TELEFONE_LEGADO, "Numero do Telefone");
                                    pclXmlGen->addItem("telefone", szAuxLegado);

                                    pclXmlGen->addItem("fax", "");
                                    pclXmlGen->addItem("eMail", "");
                                    pclXmlGen->addItem("numDepend", "");
                                    pclXmlGen->addItem("passaporte", "");
                                    pclXmlGen->addItem("tipoPassaporte", "");
                                    pclXmlGen->addItem("cartaConducao", "");
                                    pclXmlGen->addItem("tipoCartaCond", "");
                                    pclXmlGen->addItem("aoCuidadoDe", "");
                                    pclXmlGen->addItem("obs", "");
                                    pclXmlGen->addItem("conservatoriaRegistro", "");

                                    GETTREE_LEGADO(szAuxLegado, DNListaContato, "nmContato", 0, NOBRIGATORIO, LEN_NOME_CONTATO_LEGADO, "");
                                    pclXmlGen->addItem("nomeContato", szAuxLegado);
                                }
                            }
                        }
                    }


                    for(int iIndexEndereco=0;
                        (DNEndereco = walkDOM(DNPrePagoPessoaVO, "Endereco", iIndexEndereco));
                        iIndexEndereco++)
                    {
                        ULOG("iIndexEndereco(%d)", iIndexEndereco);

                        if((DNEnderecoPrePagoVO = walkDOM(DNEndereco, "EnderecoPrePagoVO", 0)))
                        {
                            flgSPED = false;
                            tPessoaEnderecoXML.szCdLogradouro[0] = 0x0;
                            tPessoaEnderecoXML.szInCnl[0] = 0x0;
                            tPessoaEnderecoXML.szCdIBGE[0] = 0x0;
                            
                            GETTREE(tPessoaEnderecoXML.szIdPessoaEndereco, DNEnderecoPrePagoVO, "idEndereco", 0, NOBRIGATORIO, "");
                            GETTREE(tPessoaEnderecoXML.szIdTipoEndereco, DNEnderecoPrePagoVO, "idTipoEnderecoSelecionado", 0, OBRIGATORIO, "Tipo do Endereco");
                            GETTREE(tPessoaEnderecoXML.szNmTituloLogradouro, DNEnderecoPrePagoVO, "dsTituloLogradouro", 0, NOBRIGATORIO, "");
                            GETTREE(tPessoaEnderecoXML.szNmTipoLogradouro, DNEnderecoPrePagoVO, "dsTipoLogradouro", 0, NOBRIGATORIO, "");
                            GETTREE(tPessoaEnderecoXML.szNmLogradouro, DNEnderecoPrePagoVO, "dsLogradouro", 0, OBRIGATORIO, "Logradouro");
                            GETTREE(tPessoaEnderecoXML.szCdLogradouro, DNEnderecoPrePagoVO, "codLogradouro", 0, NOBRIGATORIO, "codLogradouro");
                            if ( strlen(tPessoaEnderecoXML.szCdLogradouro) > 0 )
                            {
                                flgSPED = true;
                            }
                            GETTREE(tPessoaEnderecoXML.szInCnl, DNEnderecoPrePagoVO, "inCnl", 0, NOBRIGATORIO, "inCnl");
                            if ( strlen(tPessoaEnderecoXML.szInCnl) > 0 )
                            {
                                flgSPED = true;
                            }
                            GETTREE(tPessoaEnderecoXML.szCdIBGE, DNEnderecoPrePagoVO, "inCodigoIBGE", 0, NOBRIGATORIO, "inCodigoIBGE");
                            if ( strlen(tPessoaEnderecoXML.szCdIBGE) > 0 )
                            {
                                flgSPED = true;
                            }
                            GETTREE(tPessoaEnderecoXML.szNrEndereco, DNEnderecoPrePagoVO, "nrEndereco", 0, OBRIGATORIO, "Numero do Endereco");
                            GETTREE(tPessoaEnderecoXML.szDsEnderecoComplemento, DNEnderecoPrePagoVO, "dsComplementoEndereco", 0, NOBRIGATORIO, "");
                            GETTREE(tPessoaEnderecoXML.szNmBairro, DNEnderecoPrePagoVO, "dsBairro", 0, OBRIGATORIO, "Bairro");
                            GETTREE(tPessoaEnderecoXML.szNmMunicipio, DNEnderecoPrePagoVO, "dsMunicipio", 0, OBRIGATORIO, "Municipio");
                            GETTREE(tPessoaEnderecoXML.szNrCep, DNEnderecoPrePagoVO, "nrCEP", 0, OBRIGATORIO, "CEP");
                            strcpy(tPessoaEnderecoXML.szIdPessoa, pclPessoa->getIdPessoa());

                            if ( flgSPED == false )
                            {
                                clPessoaEndereco.BuscaSped(
                                    tPessoaEnderecoXML.szNrCep, 
                                    tPessoaEnderecoXML.szNmLogradouro,
                                    tPessoaEnderecoXML.szNmBairro,
                                    tPessoaEnderecoXML.szNmMunicipio,
                                    tPessoaEnderecoXML.szCdLogradouro ,
                                    tPessoaEnderecoXML.szInCnl ,
                                    tPessoaEnderecoXML.szCdIBGE
                               );
                            }
                            strcpy( scdLograd, (char*)tPessoaEnderecoXML.szCdLogradouro );
                            strcpy( sinCnl, (char*)tPessoaEnderecoXML.szInCnl );
                            strcpy( scdIBGE, (char*)tPessoaEnderecoXML.szCdIBGE );
                            ULOG( "scdLograd [%s], sinCnl [%s], scdIBGE [%s]", scdLograd, sinCnl, scdIBGE );

                            if((DNUF = walkDOM(DNEnderecoPrePagoVO, "UF", 0))) {
                                GETTREE(tPessoaEnderecoXML.szIdUf, DNUF, "idUFEndereco", 0, OBRIGATORIO, "UF");
                            }
                            else
                                throw PrePagoException(ERRO_EXECUCAO, "No UF Inexistente");

                            if((DNPais = walkDOM(DNEnderecoPrePagoVO, "Pais", 0))) {
                                GETTREE(tPessoaEnderecoXML.szIdPais, DNPais, "idPaisEndereco", 0, OBRIGATORIO, "Pais");
                            }
                            else
                                throw PrePagoException(ERRO_EXECUCAO, "No Pais Inexistente");

                            strcpy(szIdPessoaEnderecoXML, tPessoaEnderecoXML.szIdPessoaEndereco);
                            clPessoaEndereco.setStruct(&tPessoaEnderecoXML);

                            clPessoaEndereco.setIdSistemaOrigem(ID_FRONTOFFICE);
                            clPessoaEndereco.setIdUsuarioAlteracao(szUser);
                            clPessoaEndereco.setIdSistemaOrigem(clSistemaOrigem.getIdSistemaOrigem());


                            ULOG( "PESSOAENDERECO" );

                            if(strlen(szIdPessoaEnderecoXML) == 0)
                            {
                                if( !bFlagSincronizar ) clPessoaEndereco.inserePessoaEndereco();
                            }
                            else
                            {
                                if( !bFlagSincronizar ) clPessoaEndereco.atualizaPessoaEndereco();
                            }

                            // Obtem a tag para controle de enderecos
                            GETTREE(szAuxLegado, dnode, "inEnderecoSujo", 0, NOBRIGATORIO, "");
                            ULOG("inEnderecoSujo[%s]", szAuxLegado);

                            clPessoaEndereco.setInEnderecoSujo(szAuxLegado);
                            if(strlen(clPessoaEndereco.getInEnderecoSujo()) > 0) {
                                clPessoaEndereco.enderecoSujo();
                            }


                            // Obtem o IdPessoaEndereco do Cliente
                            if(iIndexEndereco == 0 && iContXML == 0)
                                strcpy(szIdPessoaEndereco, clPessoaEndereco.getIdPessoaEndereco());

                            if(iContXML == 0)
                            {
                                // Referente ao sistema legado
                                if(iIdTipoPessoaLegado == PESSOA_FISICA || iIdTipoPessoaLegado == PESSOA_JURIDICA)
                                {
                                    GETTREE(szAuxLegado, DNEnderecoPrePagoVO, "dsTipoLogradouro", 0, NOBRIGATORIO, "");
                                    alltrim( szAuxLegado );
                                    for( iCont=0; iCont<iQuantidadeLogradouros; iCont++ )
                                    {
                                        if( strcmp( pszTipoLogFO[iCont], szAuxLegado ) == 0 )
                                        {
                                            strcpy( szAuxLegado, pszTipoLogNGIN[iCont] );
                                            break;
                                        }
                                    }

                                    //Caso nao ache nenhum item no array acima, fica definido com RUA (R)
                                    if( iCont >= iQuantidadeLogradouros )
                                        strcpy( szAuxLegado, "" );

                                    ULOG("2.szAuxLegado[%s]", szAuxLegado);

                                    memset(szArruamento, 0x00, sizeof(szArruamento));
                                    ULOG("Valor de logradouro transformado:[%s]", szAuxLegado );
                                    pclXmlGen->addItem("logradouro", szAuxLegado);
                                    if(strlen(szAuxLegado) > 0)
                                    {
                                        strcpy(szArruamento, szAuxLegado);
                                    }
                                    ULOG("1.szArruamento[%s]", szArruamento);

                                    // SPED
                                    pclXmlGen->addItem("cdLogradouro", scdLograd );
                                    pclXmlGen->addItem("inCnl", sinCnl );
                                    pclXmlGen->addItem("cdIbge", scdIBGE );

                                    GETTREE(szAux, DNEnderecoPrePagoVO, "dsTituloLogradouro", 0, NOBRIGATORIO, "");
                                    ULOG("dsTituloLogradouro[%s]", szAux);
                                    if(strlen(szAux) > 0)
                                    {
                                        strcat(szArruamento, " ");
                                        strcat(szArruamento, szAux);
                                    }
                                    ULOG("2.szArruamento[%s]", szArruamento);

                                    GETTREE(szAuxLegado, DNEnderecoPrePagoVO, "dsLogradouro", 0, NOBRIGATORIO, "");
                                    ULOG("dsLogradouro[%s]", szAuxLegado);
                                    if(strlen(szAuxLegado) > 0)
                                    {
                                        strcat(szArruamento, " ");
                                        strcat(szArruamento, szAuxLegado);
                                    }

                                    ULOG("3.szArruamento[%s]", szArruamento);
                                    szArruamento[LEN_ARRUAMENTO_LEGADO]=0x00;
                                    ULOG("4.szArruamento[%s]", szArruamento);
                                    pclXmlGen->addItem("endereco", szArruamento);

                                    GETTREE_LEGADO(szAuxLegado, DNEnderecoPrePagoVO, "dsComplementoEndereco", 0, NOBRIGATORIO, LEN_COMPLEMENTO_LEGADO, "");
                                    pclXmlGen->addItem("complemento", szAuxLegado);
                                    GETTREE_LEGADO(szAuxLegado, DNEnderecoPrePagoVO, "dsBairro", 0, OBRIGATORIO, LEN_BAIRRO_LEGADO, "Bairro");
                                    pclXmlGen->addItem("bairro", szAuxLegado);
                                    GETTREE(szAuxLegado, DNEnderecoPrePagoVO, "nrCEP", 0, OBRIGATORIO, "CEP");
                                    TrataNrDocumento(szAuxLegado);
                                    szAuxLegado[LEN_CEP_LEGADO]=0x00;
                                    pclXmlGen->addItem("CEP", szAuxLegado);
                                    GETTREE_LEGADO(szAuxLegado, DNEnderecoPrePagoVO, "dsMunicipio", 0, OBRIGATORIO, LEN_CIDADE_LEGADO, "Municipio");
                                    pclXmlGen->addItem("cidade", szAuxLegado);
                                    GETTREE(szAuxLegado, DNUF, "idUFEndereco", 0, OBRIGATORIO, "UF");

                                    ULOG("BUSCA UF");
                                    ULOG("idUF ->szAuxLegado[%s]", szAuxLegado);
                                    pclUF->setIdUF(szAuxLegado);

                                    if(pclUF->buscaUF() == false)
                                        throw PrePagoException(ERRO_EXECUCAO, "Sigla UF nao encontrada!");

                                    // Verificar Max
                                    char szEstado[LEN_ESTADO_LEGADO + LEN_EOS];
                                    memset(szEstado, 0x00, sizeof(szEstado));

                                    strncpy(szEstado, pclUF->getSgUF(), LEN_ESTADO_LEGADO);

                                    pclXmlGen->addItem("estado", szEstado);
                                    pclXmlGen->addItem("pais", "BRA");
                                    GETTREE_LEGADO(szAuxLegado, DNEnderecoPrePagoVO, "nrEndereco", 0, OBRIGATORIO, LEN_NUMERO_LEGADO, "Numero do Endereco");
                                    pclXmlGen->addItem("numero", szAuxLegado);
                                }
                            }
                        }
                        else
                            throw PrePagoException(ERRO_EXECUCAO, "Tag EnderecoPrePagoVO inexistente");
                    }

                    // Referente a PessoaValorPossivel
                    if((DNPF = walkDOM(DNPrePagoPessoaVO, "PF", 0)))
                    {
                        ULOG("PESSOAVALORPOSSIVEL");

                        GETTREE(tPessoaValorPossivelXML.szIdValorPossivel, DNPF, "idEscolaridadeSelecionado", 0, NOBRIGATORIO, "");
                        if(strlen(tPessoaValorPossivelXML.szIdValorPossivel) > 0)
                        {
                            clPessoaValorPossivel.setIdPessoa(pclPessoa->getIdPessoa());
                            clPessoaValorPossivel.setIdUsuarioAlteracao(szUser);
                            if( !bFlagSincronizar ) clPessoaValorPossivel.apagaEscolaridade();

                            clPessoaValorPossivel.setStruct(&tPessoaValorPossivelXML);

                            clPessoaValorPossivel.setIdPessoa(pclPessoa->getIdPessoa());
                            clPessoaValorPossivel.setIdUsuarioAlteracao(szUser);
                            if( !bFlagSincronizar ) clPessoaValorPossivel.inserePessoaValorPossivel();
                            clPessoaValorPossivel.limpaPessoaValorPossivel();
                        }


                        GETTREE(tPessoaValorPossivelXML.szIdValorPossivel, DNPF, "idOcupacaoSelecionado", 0, NOBRIGATORIO, "");
                        if(strlen(tPessoaValorPossivelXML.szIdValorPossivel) > 0)
                        {
                            clPessoaValorPossivel.setIdPessoa(pclPessoa->getIdPessoa());
                            clPessoaValorPossivel.setIdUsuarioAlteracao(szUser);
                            if( !bFlagSincronizar ) clPessoaValorPossivel.apagaOcupacao();

                            clPessoaValorPossivel.setStruct(&tPessoaValorPossivelXML);

                            clPessoaValorPossivel.setIdPessoa(pclPessoa->getIdPessoa());
                            clPessoaValorPossivel.setIdUsuarioAlteracao(szUser);
                            if( !bFlagSincronizar ) clPessoaValorPossivel.inserePessoaValorPossivel();
                            clPessoaValorPossivel.limpaPessoaValorPossivel();
                        }
                        else
                        {
                            clPessoaValorPossivel.setIdPessoa(pclPessoa->getIdPessoa());
                            if( !bFlagSincronizar ) clPessoaValorPossivel.apagaOcupacao();
                        }
                    }
                } // PrePagoPessoaVO
            } // Cliente ou Usuario


            // Se a flag inClienteUsuario conter valor 1 o cliente eh o usuario
            if(szinClienteUsuario[0] == '1') {
                strcpy(szIdPessoaDePara[1], szIdPessoaDePara[0]);
                iIdPessoaDeParaIndex++;
                break;
            }
        } // for(int iContXML=0;...)

        ///////////////////////////////////////////////////////////////////////
        // Verifica se recebeu algum numero de Conta Grupo
        ///////////////////////////////////////////////////////////////////////
        if(bIdGrupo == false)
        {
            if((DNLinha = walkDOM(dnode, "Linha", 0))) {
                GETTREE(tPessoaLinhaXML.szIdLinhaTelefonica, DNLinha, "idLinha", 0, OBRIGATORIO, "");
                GETTREE(szNrLinha, DNLinha, "nrLinha", 0, OBRIGATORIO, "Numero da linha");
            }

            ULOG("iIdPessoaDeParaIndex(%d)", iIdPessoaDeParaIndex);

            TPessoaLinha tPessoaLinhaAux = tPessoaLinhaXML;

            // Referente a PessoaLinha
            for(iIndexPDP=0,
                iCtrlTR=0;
                iIndexPDP < iIdPessoaDeParaIndex;
                iCtrlTR++,
                iIndexPDP++)
            {
                ULOG("iCtrlTR(%d)", iCtrlTR);
                ULOG("iIndexPDP(%d)", iIndexPDP);
                ULOG("iIdPessoaDeParaIndex(%d)", iIdPessoaDeParaIndex);

                tPessoaLinhaXML = tPessoaLinhaAux;

                clPessoaLinha.setStruct(&tPessoaLinhaXML);

                if(iCtrlTR == 0)
                {
                    clPessoaLinha.setIdTipoRelacionamento(ID_TIPO_RELACIONAMENTO_C);

                    if(bIdGrupo == false) {
                        pclXmlGenRegContato->createTag("PessoaVO");
                            pclXmlGenRegContato->addItem("idPessoa", szIdPessoaDePara[0]);
                            pclXmlGenRegContato->addItem("AtendimentoTipoComunicacaoVO", "1");
                        pclXmlGenRegContato->closeTag();
                    }
                }
                else if(iCtrlTR == 1)
                {
                    clPessoaLinha.setIdTipoRelacionamento(ID_TIPO_RELACIONAMENTO_U);

                    if(bIdGrupo == false) {
                        pclXmlGenRegContato->createTag("UsuarioLinhaVO");
                            pclXmlGenRegContato->addItem("idPessoa", szIdPessoaDePara[1]);
                        pclXmlGenRegContato->closeTag();
                    }
                }
                else
                    throw PrePagoException(ERRO_EXECUCAO, "DEBUG iCtrlTR");


                ULOG("szIdPessoaDePara[%s](%d)", szIdPessoaDePara[iIndexPDP], iIndexPDP);
                clPessoaLinha.setIdPessoaDePara(szIdPessoaDePara[iIndexPDP]);
                clPessoaLinha.setIdUsuarioAlteracao(szUser);

                TPessoaLinha tPessoaLinhaTmp = clPessoaLinha.tPessoaLinha;

                ULOG("PESSOALINHA");
                ULOG("Antes da pesquisa:tPessoaLinhaTmp.szIdPessoaDePara[%s]", tPessoaLinhaTmp.szIdPessoaDePara);
                /* Busca PessoaLinha por IdPessoaDePara, IdLinhaTelefonica e IdTipoRelacionamento */
                if(clPessoaLinha.buscaPessoaLinha(&tPessoaLinhaTmp) == false)
                {
                    ULOG("clPessoaLinha.buscaPessoaLinha-> false");
                    if( !bFlagSincronizar ) clPessoaLinha.inserePessoaLinha();
                    bFlagInsereLinhaHistorico=true;
                }
                else if(strcmp(clPessoaLinha.getIdPessoaDePara(), tPessoaLinhaTmp.szIdPessoaDePara))
                {
                    ULOG("clPessoaLinha.buscaPessoaLinha-> true");

                    /* Update em PessoaLinha (IdTipoRelacionamento) */
                    clPessoaLinha.setIdPessoaLinha(tPessoaLinhaTmp.szIdPessoaLinha);
                    clPessoaLinha.setIdTipoRelacionamento(clPessoaLinha.getIdTipoRelacionamento());
                    if( !bFlagSincronizar ) clPessoaLinha.atualizaPessoaLinha();

                    bFlagInsereLinhaHistorico=true;
                }
                ULOG("Depois da pesquisa:clPessoaLinha.getIdPessoaDePara()[%s]", clPessoaLinha.getIdPessoaDePara());
                ULOG("Depois da pesquisa:tPessoaLinhaTmp.szIdPessoaDePara[%s]", tPessoaLinhaTmp.szIdPessoaDePara);

                if(iCtrlTR == 0)
                {
                    pclRegistraContato->setIdPessoaDePara(clPessoaLinha.getIdPessoaDePara());
                    pclRegistraContato->setIdLinhaTelefonica(clPessoaLinha.getIdLinhaTelefonica());
                }

                ULOG("iIndexPDP(%d)", iIndexPDP);
                ULOG("clPessoaLinha.getIdPessoaLinha()[%s]", clPessoaLinha.getIdPessoaLinha());

                if(iIndexPDP == 0) {

                    if(bFlagInsereLinhaHistorico == true)
                        pclPessoaLinhaPre->setIdPessoaLinha(clPessoaLinha.getIdPessoaLinha());
                    else
                        pclPessoaLinhaPre->setIdPessoaLinha(tPessoaLinhaTmp.szIdPessoaLinha);


                    char szUsrNaoInformado[LEN_INUSUARIONAOINFORMADO + LEN_EOS];
                    GETTREE(szUsrNaoInformado, dnode, "usrNaoInformado", 0, NOBRIGATORIO, "usrNaoInformado");

                    if(szUsrNaoInformado[0] != 0x00) {
                        ULOG("Inicio PESSOALINHAPRE");

                        // Solicitado pela OS 347
                        if(bMudancaTitularidade) {
                            pclPessoaLinhaPre->setInMudancaTitularidade("1");
                            memset(szAux, 0x00, sizeof(szAux));
                            memcpy(szAux, szNrLinha, 2);
                            pclPessoaLinhaPre->insereMudancaTitularidade(szAux, szNrLinha+2);
                        }
                        else
                            pclPessoaLinhaPre->setInMudancaTitularidade("0");

                        pclPessoaLinhaPre->setInSincronismo("0");
                        pclPessoaLinhaPre->setInUsuarioNaoInformado(szUsrNaoInformado);

                        TPessoaLinhaPre tPessoaLinhaPreAux = pclPessoaLinhaPre->tPessoaLinhaPre;

                        if(pclPessoaLinhaPre->buscaPessoaLinhaPre() == true) {
                            pclPessoaLinhaPre->setInUsuarioNaoInformado(szUsrNaoInformado);
                            if( !bFlagSincronizar ) pclPessoaLinhaPre->atualizaPessoaLinhaPre();
                        }
                        else
                        {
                            pclPessoaLinhaPre->setStruct(&tPessoaLinhaPreAux);
                            if( !bFlagSincronizar ) pclPessoaLinhaPre->inserePessoaLinhaPre();
                        }

                        ULOG("Final PESSOALINHAPRE");
                    }
                }


                if(bFlagInsereLinhaHistorico == true)
                {
                    ULOG("szTipoAcao[%s]", szTipoAcao);
                    ULOG("szIdPessoaDePara[0][%s]- szIdPessoaDePara[1][%s]", szIdPessoaDePara[0], szIdPessoaDePara[1]);
                    ULOG("Insert em PessoaLinhaHistorico");
                    CPessoaLinhaHistorico *pclPessoaLinhaHistorico;
                    pclPessoaLinhaHistorico = new CPessoaLinhaHistorico;

                    //char szCdAreaRegistro[LEN_NRLINHA + LEN_EOS];
                    memset(szCdAreaRegistro, 0x00, sizeof(szCdAreaRegistro));
                    memcpy(szCdAreaRegistro, szNrLinha, 2);
                    pclPessoaLinhaHistorico->setCdAreaRegistro(szCdAreaRegistro);

                    pclPessoaLinhaHistorico->setNrLinha(szNrLinha+2);

                    pclPessoaLinhaHistorico->setIdUsuarioAlteracao(szUser);
                    pclPessoaLinhaHistorico->setIdPessoaDePara(szIdPessoaDePara[iIndexPDP]);
                    pclPessoaLinhaHistorico->setIdLinhaTelefonica(clPessoaLinha.getIdLinhaTelefonica());
                    pclPessoaLinhaHistorico->setIdTipoRelacionamento(clPessoaLinha.getIdTipoRelacionamento());

                    if( !bFlagSincronizar )
                    {
                        pclPessoaLinhaHistorico->inserePessoaLinhaHistorico();
                        pclRegistraContato->setIdPessoaLinhaHistorico(pclPessoaLinhaHistorico->getIdPessoaLinhaHistorico());
                    }

                    delete pclPessoaLinhaHistorico;
                    bFlagInsereLinhaHistorico=false;
                }
            }//for(...)


            // Referente a LinhaConta
            if((DNLinha = walkDOM(dnode, "Linha", 0))) {
                GETTREE(tLinhaContaXML.szIdLinhaTelefonica, DNLinha, "idLinha", 0, OBRIGATORIO, "");
            }
            clLinhaConta.setStruct(&tLinhaContaXML);
            clLinhaConta.setIdTipoRelacionamento(ID_TIPO_RELACIONAMENTO_C);

            ULOG("LINHACONTA");
            if(clLinhaConta.buscaLinhaConta() == false) {
                sprintf(szMessageException, "Conta ficticia nao encontrada para IdLinhaTelefonica[%s]", tLinhaContaXML.szIdLinhaTelefonica);
                throw PrePagoException(ERRO_EXECUCAO, szMessageException);
            }


            if(bIdGrupo == false) {
                // Referente ao processo Registra Contato
                pclXmlGenRegContato->createTag("Contas");
                    pclXmlGenRegContato->createTag("ContaVO");
                        pclXmlGenRegContato->addItem("idConta", clLinhaConta.getIdConta());
                        pclXmlGenRegContato->createTag("LinhaVO");
                            pclXmlGenRegContato->addItem("idPessoaLinhaHistorico", clLinhaConta.getIdLinhaTelefonica());
                        pclXmlGenRegContato->closeTag();
                    pclXmlGenRegContato->closeTag();
                pclXmlGenRegContato->closeTag();
                pclXmlGenRegContato->closeTag();
            }


            // Referente a PessoaConta
            pclPessoaConta->setIdConta(clLinhaConta.getIdConta());
            pclPessoaConta->setIdTipoRelacionamento(ID_TIPO_RELACIONAMENTO_C);
            pclPessoaConta->setIdPessoaDePara(szIdPessoaDePara[0]);
            pclPessoaConta->setIdUsuarioAlteracao(szUser);

            TPessoaConta tPessoaContaTmp = pclPessoaConta->tPessoaConta;

            ULOG("PESSOACONTA");

            if(pclPessoaConta->buscaPessoaConta(&tPessoaContaTmp) == true) {
                // atualiza o idpessoadepara
                pclPessoaConta->setIdPessoaConta(tPessoaContaTmp.szIdPessoaConta);
                if( !bFlagSincronizar ) pclPessoaConta->atualizaPessoaConta();
            }
            else
            {
                if( !bFlagSincronizar ) pclPessoaConta->inserePessoaConta();
            }


            // Referente a ContaEndereco
            pclContaEndereco->setIdTipoEnderecoCobranca( pclTipoEnderecoCobranca->getIdTipoEnderecoCobranca() );
            pclContaEndereco->setIdConta(clLinhaConta.getIdConta());
            pclContaEndereco->setIdPessoaEndereco(szIdPessoaEndereco);
            pclContaEndereco->setIdUsuarioAlteracao(szUser);

            TContaEndereco tContaEnderecoTmp = pclContaEndereco->tContaEndereco;

            ULOG("CONTAENDERECO");

            if(pclContaEndereco->buscaContaEndereco(&tContaEnderecoTmp) == true) {
                pclContaEndereco->setIdTipoEnderecoCobranca( pclTipoEnderecoCobranca->getIdTipoEnderecoCobranca() );
                pclContaEndereco->setIdContaEndereco(tContaEnderecoTmp.szIdContaEndereco);
                if( !bFlagSincronizar ) pclContaEndereco->atualizaContaEndereco();
            }
            else
            {
                if( !bFlagSincronizar ) pclContaEndereco->insereContaEndereco();
            }
        }
        ///////////////////////////////////////////////////////////////////////
        // Tratamento de cadastro de Conta Grupo.
        ///////////////////////////////////////////////////////////////////////
        else
        {
            if((DNLinha = walkDOM(dnode, "Linha", 0))) {
                GETTREE(tPessoaLinhaXML.szIdLinhaTelefonica, DNLinha, "idLinha", 0, NOBRIGATORIO, "");
                GETTREE(szNrLinha, DNLinha, "nrLinha", 0, NOBRIGATORIO, "Numero da linha");
            }

            pclConta->setIdSistemaOrigem( pclSistemaOrigemNgin->getIdSistemaOrigem() );
            pclConta->setIdContaSistemaOrigem( szIdGrupo );
            pclConta->setCdConta( szIdGrupo );
            pclConta->setIdUsuarioAlteracao( szUser );

            pclTipoConta->setSgTipoConta( TIPO_CONTA_GRUPO );

            if( pclTipoConta->buscaTipoConta() == false ) {
                sprintf(szMessageException, "TipoContaGrupo nao encontrado para SgContaGrupo[%s]", TIPO_CONTA_GRUPO);
                throw PrePagoException(ERRO_EXECUCAO, szMessageException);
            }

            pclConta->setIdTipoConta(pclTipoConta->getIdTipoConta());

            // Busca a Conta pelo szIdGrupo.
            if(pclConta->buscaContaGrupo() == false) {
                if( !bFlagSincronizar ) pclConta->insereConta();
            }

            pclTipoRelacionamento->setSgTipoRelacionamento(SG_TIPO_RELACIONAMENTO_C);

            if( pclTipoRelacionamento->buscaTipoRelacionamento() == false ) {
                sprintf(szMessageException, "TipoContaGrupo nao encontrado para SgContaGrupo[%s]", SG_TIPO_RELACIONAMENTO_CG);
                throw PrePagoException(ERRO_EXECUCAO, szMessageException);
            }

            //Preenche a estrutura com os dados necessario para pesquisa de Pessoa Conta
            pclPessoaConta->setIdTipoRelacionamento(pclTipoRelacionamento->getIdTipoRelacionamento() );
            pclPessoaConta->setIdPessoaDePara(pclPessoa->getIdPessoaDePara() );
            pclPessoaConta->setIdConta(pclConta->getIdConta());
            pclPessoaConta->setIdUsuarioAlteracao( szUser );

            //Busca PessoaConta por IdPessoaDePara e IdTipoRelacionamento = ContaGrupo
            if( pclPessoaConta->buscaPessoaConta() == false ) {
                // Se não encontrar insere
                if( !bFlagSincronizar ) pclPessoaConta->inserePessoaConta();
            }
            else
            {
                //Preenche a classe com o novo idConta
                pclPessoaConta->setIdPessoaDePara( pclPessoa->getIdPessoaDePara() );
                // Se achar atualiza com o IdConta encontrado ou inserido
                if( !bFlagSincronizar ) pclPessoaConta->atualizaPessoaConta();
            }

            //Antes de buscar preenche a estrutura auxiliar
            pclContaEndereco->setIdConta( pclConta->getIdConta() );
            pclContaEndereco->setIdTipoEnderecoCobranca( pclTipoEnderecoCobranca->getIdTipoEnderecoCobranca() );
            pclContaEndereco->setIdPessoaEndereco( clPessoaEndereco.getIdPessoaEndereco() );
            pclContaEndereco->setIdUsuarioAlteracao( szUser );
            // Busca ContaEndereco por IdConta e IdTipoEnderecoCobranca = Cobranca
            if( pclContaEndereco->buscaContaEndereco() == false ) {
                // Se não encontrar insere
                if( !bFlagSincronizar ) pclContaEndereco->insereContaEndereco();
            }
            else
            {
                pclContaEndereco->setIdPessoaEndereco( clPessoaEndereco.getIdPessoaEndereco() );
                // Se encontrar atualiza com o Endereco atual
                if( !bFlagSincronizar ) pclContaEndereco->atualizaContaEndereco();
            }
        }

        memcpy(szCdAreaRegistro, szNrLinha, 2); szCdAreaRegistro[2] = 0;
        pclFilaSetClientInfo->setCdAreaRegistro(szCdAreaRegistro);
        pclFilaSetClientInfo->setNrLinha(szNrLinha+2);

        if(bIdGrupo == false)
        {
             /* Referente ao BlackList PUP */
            if(!strcmp(szTipoAcao, "CADASTRAR") || !strcmp(szTipoAcao, "TITULARIDADE"))
            {

                GETTREE(szIdLinhaTelefonicaPUP, DNLinha, "idLinha", 0, OBRIGATORIO, "");

                if((DNPUP = walkDOM(dnode, "PUP", 0)))
                {
                    ULOG("RSR PUP>>>");

                    pclParametro->clearStruct();
                    pclParametro->setCdParametro("PUP_PRAZO_EXPIRACAO");
                    if(pclParametro->buscaParametro() == false) {
                        sprintf(szMessageException, "Pesquisa em APOIO.PARAMETRO não encontrou nenhum registro para CdParametro [%s]", pclParametro->getCdParametro());
                        throw PrePagoException(ERRO_EXECUCAO, szMessageException);
                    }


                    pclPermissaoLinhaPUP->setIdLinhaTelefonica(szIdLinhaTelefonicaPUP);
                    pclPermissaoLinhaPUP->setAlteraCadastroLinhaPUP( szIdLinhaTelefonicaPUP,szUser );  /* Faz Update em Linha.LinhaTelefonica */
                    pclPermissaoLinhaPUP->AlteraCadastroLinhaPUP();
                    pclPermissaoLinhaPUP->apagaPermissaoLinhaPUP();
                    ULOG("RSR APAGOU EM LINHA.PERMISSAOLINHAPUP COM IDLINHATELEFONICA[%s]", pclPermissaoLinhaPUP->getIdLinhaTelefonica());


                    /*--> Removido por Solicitação do Tiago, durante os testes de Homologacao
                    GETTREE(szAux, DNPUP, "inProtocolo", 0, OBRIGATORIO, "inProtocolo");  // SMSPROT
                    pclPermissaoLinhaPUP->clearStruct();
                    pclPermissaoLinhaPUP->setInAtivo(szAux);
                    pclPermissaoLinhaPUP->setSgPermissaoPUP("SMSPROT");
                    pclPermissaoLinhaPUP->setIdLinhaTelefonica(szIdLinhaTelefonicaPUP);
                    pclPermissaoLinhaPUP->setIdUsuarioAlteracao(szUser);
                    pclPermissaoLinhaPUP->setDtExpiracao(pclParametro->getDsValorParametro());
                    pclPermissaoLinhaPUP->inserePermissaoLinhaPUP();
                    ULOG("RSR INSERIU EM LINHA.PERMISSAOLINHAPUP COM IDLINHATELEFONICA[%s] SgPermissaoPUP[%s]", pclPermissaoLinhaPUP->getIdLinhaTelefonica(), pclPermissaoLinhaPUP->getSgPermissaoPUP());
                    */


                    szAux[0] = 0x0;
                    GETTREE(szAux, DNPUP, "inPromocoes", 0, NOBRIGATORIO, "inPromocoes");  // SMSPROM
                    if ( szAux[0] != 0x0 )
                    {
                        pclPermissaoLinhaPUP->clearStruct();
                        pclPermissaoLinhaPUP->setInAtivo(szAux);
                        pclPermissaoLinhaPUP->setSgPermissaoPUP("SMSPROM");
                        pclPermissaoLinhaPUP->setIdLinhaTelefonica(szIdLinhaTelefonicaPUP);
                        pclPermissaoLinhaPUP->setIdUsuarioAlteracao(szUser);
                        pclPermissaoLinhaPUP->setDtExpiracao(pclParametro->getDsValorParametro());
                    pclPermissaoLinhaPUP->inserePermissaoLinhaPUP();
                    ULOG("RSR INSERIU EM LINHA.PERMISSAOLINHAPUP COM IDLINHATELEFONICA[%s] SgPermissaoPUP[%s]", pclPermissaoLinhaPUP->getIdLinhaTelefonica(), pclPermissaoLinhaPUP->getSgPermissaoPUP());
                    }


                    szAux[0] = 0x0;
                    GETTREE(szAux, DNPUP, "inProdutos", 0, NOBRIGATORIO, "inProdutos");    // SMSPROD
                    if ( szAux[0] != 0x0 )
                    {
                        pclPermissaoLinhaPUP->clearStruct();
                        pclPermissaoLinhaPUP->setInAtivo(szAux);
                        pclPermissaoLinhaPUP->setSgPermissaoPUP("SMSPROD");
                        pclPermissaoLinhaPUP->setIdLinhaTelefonica(szIdLinhaTelefonicaPUP);
                        pclPermissaoLinhaPUP->setIdUsuarioAlteracao(szUser);
                        pclPermissaoLinhaPUP->setDtExpiracao(pclParametro->getDsValorParametro());
                        pclPermissaoLinhaPUP->inserePermissaoLinhaPUP();
                        ULOG("RSR INSERIU EM LINHA.PERMISSAOLINHAPUP COM IDLINHATELEFONICA[%s] SgPermissaoPUP[%s]", pclPermissaoLinhaPUP->getIdLinhaTelefonica(), pclPermissaoLinhaPUP->getSgPermissaoPUP());
                    }

                    szAux[0] = 0x0;
                    GETTREE(szAux, DNPUP, "inParceiros", 0, NOBRIGATORIO, "inParceiros");  // SMSPARC
                    if ( szAux[0] != 0x0 )
                    {
                        pclPermissaoLinhaPUP->clearStruct();
                        pclPermissaoLinhaPUP->setInAtivo(szAux);
                        pclPermissaoLinhaPUP->setSgPermissaoPUP("SMSPARC");
                        pclPermissaoLinhaPUP->setIdLinhaTelefonica(szIdLinhaTelefonicaPUP);
                        pclPermissaoLinhaPUP->setIdUsuarioAlteracao(szUser);
                        pclPermissaoLinhaPUP->setDtExpiracao(pclParametro->getDsValorParametro());
                        pclPermissaoLinhaPUP->inserePermissaoLinhaPUP();
                        ULOG("RSR INSERIU EM LINHA.PERMISSAOLINHAPUP COM IDLINHATELEFONICA[%s] SgPermissaoPUP[%s]", pclPermissaoLinhaPUP->getIdLinhaTelefonica(), pclPermissaoLinhaPUP->getSgPermissaoPUP());
                    }

                    ULOG("RSR PUP<<<");
                }
            }
        }


        //////////////////////////////////////////////////////////////////////////////
        // Referente ao processo Registra Contato (registra o contato efetivamente) //
        //////////////////////////////////////////////////////////////////////////////
        if(bIdGrupo == false)
        {
            TuxMessage o_inputMessage;
            o_inputMessage.setUser(pUser);
            o_inputMessage.setMessageBody(pclXmlGenRegContato);
            o_inputMessage.setService("DREGCONTATO");

            char*sMsgIn = o_inputMessage.getMessageXML();
            pclRegistraContato->setXml(sMsgIn);

            // ULOG("Inicio do processo Registra Contato");
            // pclRegistraContato->insereRegistraContato();
            // ULOG("Final do processo Registra Contato");
        }//if(bIdGrupo == false)


        if(bFlagPlanoControle == false)
        {
            bool bErroComunicacao = false;

            if( strcmp(szTipoAcao, "CADASTRAR")==0 && bInterceptacao == true)
            {
                /************************************/
                /* INICIO DA HABILITACAO DO CLIENTE */
                /************************************/
                ULOG("* Inicio do processamento para sistema legado...");
                try
                {
                    ULOG("** Inicio de habilita linha... <setInterceptacao>");
                    pclXmlGenHabLinha->addItem("ProxyOperacao", "setInterceptacao");
                    pclXmlGenHabLinha->addItem("usuario", "FO");
                    pclXmlGenHabLinha->addItem("operacao", "0");
                    pclXmlGenHabLinha->addItem("xmlns", "cliente.fo.vivo.com.br/vo");


                    inputMessageHabLinha->setUser(szUser);
                    inputMessageHabLinha->setService("DTUXINTERBE");
                    inputMessageHabLinha->setMessageBody(pclXmlGenHabLinha);

                    // Repassa configuracoes ao manipulador do serviço remoto e invoca o servico.
                    remoteServiceHabLinha->setServiceName("DTUXINTERBE");
                    remoteServiceHabLinha->setInputMessage(inputMessageHabLinha);

                    ULOG("*** Inicio do acesso remoto ao sistema legado... <remoteCall>");
                    iRetSistemaLegado = remoteServiceHabLinha->remoteCall(); ULOG("iRetSistemaLegado(%d)", iRetSistemaLegado);
                    if(iRetSistemaLegado != TUXFWRET_OK) {
                        throw PrePagoException(ERRO_LEGADO_SET_INTERCEPTACAO, "Erro de comunicação com sistema de Habilitacao de Linha");
                    }

                    pStatusCode = remoteServiceHabLinha->getOutputMessage()->getStatusCode();
                    ULOG("pStatusCode[%s](%p)", pStatusCode, pStatusCode);

                    if(pStatusCode != NULL) {
                        if(strlen(pStatusCode) > 0) {
                            if(pStatusCode[2] != 'I') {
                                pclFilaSetClientInfo->setCdErro(pStatusCode);
                                free(pStatusCode); pStatusCode = NULL;
                                pStatusText = remoteServiceHabLinha->getOutputMessage()->getStatusText();
                                if ( pStatusText )
                                {
                                    ULOG("pStatusText[%s](%p)", pStatusText, pStatusText);
                                    char statusText[LEN_MESSAGE_EXCEPTION+LEN_EOS];
                                    strncpy(statusText,*pStatusText?pStatusText:"Erro de comunicação com sistema de Habilitacao de Linha",sizeof(statusText)-1);
                                    statusText[sizeof(statusText)-1]=0;
                                    free(pStatusText); pStatusText = NULL;
                                    throw PrePagoException(ERRO_LEGADO_SET_INTERCEPTACAO,statusText);
                                }
                                else
                                {
                                    ULOGW("pStatusText[%s](%p)", "(null)", "(null)");
                                    throw PrePagoException(ERRO_LEGADO_SET_INTERCEPTACAO,"Erro de comunicação com sistema de Habilitacao de Linha");
                                }
                            }
                        }
                    }
                }
                catch(PrePagoException ppe)
                {
                    bErroComunicacao = true;
                    pclFilaSetClientInfo->setDsErro(ppe.getMsg());
                    //throw PrePagoException(ERRO_LEGADO_SET_INTERCEPTACAO, "Erro de comunicação com sistema de Habilitacao de Linha");
                }
                catch(...)
                {
                    bErroComunicacao = true;
                    pclFilaSetClientInfo->setDsErro("Erro de comunicação desconhecido com o sistema de Habilitacao de Linha");
                    //throw PrePagoException(ERRO_LEGADO_SET_INTERCEPTACAO, "Erro de comunicação desconhecido com o sistema de Habilitacao de Linha");
                }

                ULOG("*** Final do acesso remoto ao sistema legado... <remoteCall>");

                if ( false == bErroComunicacao )
                {
                    pStatusCode = remoteServiceHabLinha->getOutputMessage()->getStatusCode(); ULOG("pStatusCode[%s](%p)", pStatusCode, pStatusCode);
                    pStatusText = remoteServiceHabLinha->getOutputMessage()->getStatusText(); ULOG("pStatusText[%s](%p)", pStatusText, pStatusText);
                    if(pStatusCode) {
                        ULOG("free - pStatusCode");
                        free(pStatusCode); pStatusCode = NULL;
                    }
                    if(pStatusText) {
                        ULOG("free - pStatusText");
                        free(pStatusText); pStatusText = NULL;
                    }
                }

                ULOG("** Final de habilita linha... <setInterceptacao>");
            }//if( strcmp(szTipoAcao, "CADASTRAR")==0 && bInterceptacao == true)


            /************************************/
            /* INICIO DA SETAGEM DO CLIENTE     */
            /************************************/
            ULOG("** Inicio do setCliente em tabela... <setCliente>");

            /* Obtem o idLinhaTelefonica */
            if((DNLinha = walkDOM(dnode, "Linha", 0))) {
                GETTREE(szAux, DNLinha, "idLinha", 0, OBRIGATORIO, "");
            }
            pclFilaSetClientInfo->setIdLinhaTelefonica(szAux);

            /*--- [A0001] ---------------------------------------------------*/
            strcpy( vo_idLinhaTelefonica, szAux );

            pRet = pclXmlGen->retrieveXML(&iTamSaidaSetClient);
            ULOG("pRet(%p) iTamSaidaSetClient(%d) strlen(p)(%d)", pRet, iTamSaidaSetClient, strlen(pRet));
            if(strlen(pRet) >= LEN_XML) {
                throw PrePagoException(ERRO_EXECUCAO, "!!!REFINAR TRATAMENTO DE XML A SER GRAVADO!!!");
            }
            pclFilaSetClientInfo->setXml(pRet);

            // os valores do flag INTERCEPTADO (INFRA.FILASETCLIENTINFO) significam:
            //
            // 0: Era para interceptar junto ao NGIN e depois executar o enviaLegado mas deu erro de comunicação.
            // 2: Era para interceptar junto ao NGIN e depois NÂO executar o enviaLegado mas deu erro de comunicação.
            // 1: Sucesso na interceptação e agora deve executar o enviaLegado, ou não era para interceptar mas
            //    era para executar o enviaLegado.
            //
            pclFilaSetClientInfo->setInterceptado("-1"); // nao vai gravar
            if(strcmp(szTipoAcao,"CADASTRAR")==0 && true == bInterceptacao) {
                if( false == bFlagSincronizar ) {
                    pclFilaSetClientInfo->setInterceptado(bErroComunicacao?"0":"1");
                }
                else
                {
                    pclFilaSetClientInfo->setInterceptado(bErroComunicacao?"2":"-1");
                }
            }
            else if( false == bFlagSincronizar ) {
                pclFilaSetClientInfo->setInterceptado("1");
            }

            if ( strcmp(pclFilaSetClientInfo->getInterceptado(),"-1") ) {
                if ( !memcmp(idCanalWrk,"666",3) )
                {
                    if(pclFilaSetClientInfo->existeFilaSetClientInfo_2() == true) {
                       pclFilaSetClientInfo->atualizaXmlFilaSetClientInfo_2();
                    }
                    else
                    {
                       pclFilaSetClientInfo->insereFilaSetClientInfo_2();
                    }
                }
                else
                {
                    if(pclFilaSetClientInfo->existeFilaSetClientInfo() == true) {
                       pclFilaSetClientInfo->atualizaXmlFilaSetClientInfo();
                    }
                    else {
                       pclFilaSetClientInfo->insereFilaSetClientInfo();
                    }
                }
            }

            ULOG("** Final da Setagem do cliente em tabela... <setCliente>");
            
            /*--- [A0001] ---------------------------------------------------*/
            if ( strcmp(szTipoAcao, "CADASTRAR") == 0 )
            {
                strcpy( vo_idPessoa,pclPessoa->getIdPessoa() );
                sprintf( vo_idTipoPessoa, "%d", iIdTipoPessoaLegado );
                pclPessoaSegmentacao->ClassificaInfancia( vo_idLinhaTelefonica, vo_idPessoa , vo_idTipoPessoa ) ;
            }
            /*--------------------------------------------------------------*/
            
        }//if(bFlagPlanoControle == false)
        else
        {
            ULOG("* Plano controle - nao envia dados para ngin...");
        }

        delete pclParametro;
        delete pclRegistraContato;
        delete pclXmlGenRegContato;
        delete pclPessoa;
        delete pclPessoaLinhaPre;
        delete pclTipoConta;
        delete pclTipoEnderecoCobranca;
        delete pclTipoRelacionamento;
        delete pclSistemaOrigemNgin;
        delete pclConta;
        delete pclPessoaSegmentacao;
        delete pclPessoaConta;
        delete pclDocumento;
        delete pclContaEndereco;
        delete pclTipoDocumento;
        delete pclUF;
        delete pclXmlGen;
        delete pclFilaSetClientInfo;
        delete remoteServiceHabLinha;
        delete inputMessageHabLinha;
        delete pclXmlGenHabLinha;
        delete pclPermissaoLinhaPUP;

        ULOG_END("PPINSPESSOA");
        setStatusCode(SUCESSO_EXECUCAO, "Success Execution");
        return;

    }
    catch(PrePagoException sE)
    {
        delete pclParametro;
        delete pclRegistraContato;
        delete pclXmlGenRegContato;
        delete pclPessoa;
        delete pclPessoaLinhaPre;
        delete pclTipoConta;
        delete pclTipoEnderecoCobranca;
        delete pclTipoRelacionamento;
        delete pclSistemaOrigemNgin;
        delete pclConta;
        delete pclPessoaSegmentacao;
        delete pclPessoaConta;
        delete pclDocumento;
        delete pclContaEndereco;
        delete pclTipoDocumento;
        delete pclUF;
        delete pclXmlGen;
        delete pclFilaSetClientInfo;
        delete remoteServiceHabLinha;
        delete inputMessageHabLinha;
        delete pclXmlGenHabLinha;
        delete pclPermissaoLinhaPUP;

        switch(sE.getTipo())
        {
            case ERRO_EXECUCAO:
                setStatusCode(E_APLICACAO_PREPAGO, sE.getMsg());
                break;
            case ERRO_PARAMETRO_NULL:
                setStatusCode(E_PARAMETRO_PREPAGO, sE.getMsg());
                break;
            case ERRO_SEQUENCE:
                setStatusCode(W_SEQUENCE_PREPAGO, sE.getMsg());
                break;
            case ERRO_REGISTRO_NAO_ENCONTRADO:
                setStatusCode(W_DELETE_PREPAGO, sE.getMsg());
                break;
            case ERRO_LEGADO_SET_INTERCEPTACAO:
                setStatusCode(E_APLICACAO_PREPAGO, sE.getMsg());
                break;
            default:
                setStatusCode(E_APLICACAO_PREPAGO, sE.getMsg());
                break;
        }

        ULOGE(">>>Final do processamento de PPINSPESSOA..");
        ULOG_END("PPINSPESSOA");
        return;
    }
    catch(...)
    {
        delete pclParametro;
        delete pclRegistraContato;
        delete pclXmlGenRegContato;
        delete pclPessoa;
        delete pclPessoaLinhaPre;
        delete pclTipoConta;
        delete pclTipoEnderecoCobranca;
        delete pclTipoRelacionamento;
        delete pclSistemaOrigemNgin;
        delete pclConta;
        delete pclPessoaSegmentacao;
        delete pclPessoaConta;
        delete pclDocumento;
        delete pclContaEndereco;
        delete pclTipoDocumento;
        delete pclUF;
        delete pclXmlGen;
        delete pclFilaSetClientInfo;
        delete remoteServiceHabLinha;
        delete inputMessageHabLinha;
        delete pclXmlGenHabLinha;
        delete pclPermissaoLinhaPUP;

        ULOGE(">>>Final do processamento de PPINSPESSOA...");
        ULOG_END("PPINSPESSOA");
        setStatusCode(E_APLICACAO_PREPAGO, "Procedimento encerrado com ERRO.");
        throw;
    }
}
