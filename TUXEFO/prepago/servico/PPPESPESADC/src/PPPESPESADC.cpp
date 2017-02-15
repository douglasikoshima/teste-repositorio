///////////////////////////////////////////////////////////////////////////////
/**
 * @modulo  prepago
 * @usecase PPPESPESADC
 * @remark  ETD-542 Melhoria de TMA. Pesquisa dados de pessoa pelo número do documento.
 * @author  Cassio M Garcia
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:35:05 $
 **/
///////////////////////////////////////////////////////////////////////////////

#include "../include/PsqPFDoc.h"
#include "../include/PsqPFDocDdsCttpc.h"
#include "../include/PsqPJDocDdsCttpc.h"
#include <PrePagoException.h>

#include <tuxfw.h>
#include <ctype.h>

#include <CPais.h>
#include <CTipoComunicacao.h>
#include <CTipoDocumento.h>
#include <CUF.h>
#include <CValorPossivel.h>
#include <Documento.h>
#include <LinhaConta.h>
#include <Pessoa.h>
#include <PessoaComunicacao.h>
#include <PessoaEndereco.h>
#include <PessoaFisica.h>
#include <PessoaJuridica.h>

extern bool proCBuscarPFContato(const char *ptIdPessoa,DadosPFContato *ptDadosPFContato,StatusPFContato *ptStatusPFContato,XMLGen *xml_g);
extern bool proCBuscarPFPorDoc(const char *ptIdPessoa,DadosPFDocumento *ptDadosPFDocumento,StatusPFDocumento *ptStatusPFDocumento,XMLGen *xml_g);
extern bool proCBuscarPFEMailCom(const char *ptIdPessoa,DadosPFEMailComercial *ptDadosPFEMailComercial,StatusPFEMailComercial *ptStatusPFEMailComercial,XMLGen *xml_g);
extern bool proCBuscarPFEMailPart(const char *ptIdPessoa,DadosPFEMailParticular *ptDadosPFEMailParticular,StatusPFEMailParticular *ptStatusPFEMailParticular,XMLGen *xml_g);
extern bool proCBuscarPFPorDocLinPos(const char *ptszNrDocumento,const char *ptszDsDocumento,DadosPessoaFisicaPorDoc *ptDadosPessoaFisicaPorDoc,XMLGen *xml_g);
extern bool proCBuscarPFPorDocLinPre(const char *ptszNrDocumento,const char *ptszDsDocumento,DadosPessoaFisicaPorDoc *ptDadosPessoaFisicaPorDoc,XMLGen *xml_g);
extern bool proCBuscarPJPorDocLinPre(const char *ptszNrDocumento,const char *ptszDsDocumento,DadosPessoaJuridicaPorDoc *ptDadosPessoaJuridicaPorDoc,XMLGen *xml_g);
extern bool proCBuscarPJPorDocLinPos(const char *ptszNrDocumento,const char *ptszDsDocumento,DadosPessoaJuridicaPorDoc *ptDadosPessoaJuridicaPorDoc,XMLGen *xml_g);

DECLARE_TUXEDO_SERVICE(PPPESPESADC);

void implPPPESPESADC::Execute(DOMNode *dnode, XMLGen *xml_g)
{
    ULOG_START("PPPESPESADC");

    CPesquisaPFPorDoc cpesquisapfpordoc(dnode,xml_g);

    try
    {
        cpesquisapfpordoc.Executar();

        setStatusCode("12I0000", "Sucesso");
    }
    catch(PrePagoException &pe)
    {
        char codErr[32];
        sprintf(codErr,"%02dE9999",pe.getTipo());

        setStatusCode(codErr, pe.getMsg());
    }
    catch (TuxBasicOraException *ex)
    {
        char codErro[25];
        sprintf(codErro,"04E%04d",ex->eCode<0 ?ex->eCode*(-1):ex->eCode);

        setStatusCode(codErro,ex->pMsg);

        delete ex;
    }
    catch(...)
    {
        ULOGE("erro desconhecido");
        setStatusCode("04E9999", "Erro desconhecido");
    }

    ULOG("FINAL DO PROCESSAMENTO");
    ULOG_END("PPPESPESADC");
}

//==============================================================================
void CPesquisaPFPorDoc::Executar()
{
    char szInTipoPessoa[LEN_AUX + LEN_EOS];
    char szNrDocumento[LEN_AUX + LEN_EOS];
    char szIdTipoDocumento[LEN_AUX + LEN_EOS];

    GETTREE(szNrDocumento,dnode,"nrDoc",0,OBRIGATORIO,"Numero do Documento");
    GETTREE(szInTipoPessoa,dnode,"inTipoPessoa",0,OBRIGATORIO,"Tipo de pessoa 'PF' ou 'PJ'");
    GETTREE(szIdTipoDocumento,dnode,"idTipoDocumento",0,OBRIGATORIO,"Tipo do documento");

    removerPontuacaoNrDocumento(szNrDocumento);

    if ( !stricmp(szInTipoPessoa,PP_PESSOA_FISICA) )
    {
        BuscarDadosPessoaFisica(szNrDocumento,szIdTipoDocumento);
    }
    else if ( !stricmp(szInTipoPessoa,PP_PESSOA_JURIDICA) )
    {
        BuscarDadosPessoaJuridica(szNrDocumento,szIdTipoDocumento);
    }
    else
    {
        sprintf(szMessageException, "inTipoPessoa/IdTipoPessoa invalido [%s]", szInTipoPessoa);
        throw PrePagoException(ERRO_EXECUCAO, szMessageException);
    }
}

//==============================================================================
void CPesquisaPFPorDoc::BuscarDadosPessoaFisica(const char *pszNrDocumento,const char *pszIdTipoDocumento)
{
    int x;

    DadosPessoaFisicaPorDoc dadospessoafisicapordoc;

    //DadosPFContato dadospfcontato;
    //StatusPFContato statuspfcontato;

    // DadosPFDocumento dadospfdocumento;
    // StatusPFDocumento statuspfdocumento;

    // DadosPFEMailComercial dadospfemailcomercial;
    // StatusPFEMailComercial statuspfemailcomercial;

    // DadosPFEMailParticular dadospfemailparticular;
    // StatusPFEMailParticular statuspfemailparticular;

    // memset(&dadospfcontato,0,sizeof(dadospfcontato));
    // memset(&statuspfcontato,0,sizeof(statuspfcontato));
    // memset(&dadospfdocumento,0,sizeof(dadospfdocumento));
    // memset(&statuspfdocumento,0,sizeof(statuspfdocumento));
    // memset(&dadospfemailcomercial,0,sizeof(dadospfemailcomercial));
    // memset(&statuspfemailcomercial,0,sizeof(statuspfemailcomercial));
    // memset(&dadospfemailparticular,0,sizeof(dadospfemailparticular));
    // memset(&statuspfemailparticular,0,sizeof(statuspfemailparticular));

    xml_g->createTag("ManterPrePagoVO");
    xml_g->addProp("xmlns", "cliente.fo.vivo.com.br/vo" );
    xml_g->createTag("Cliente");
    xml_g->createTag("PrePagoPessoaVO");

    //==========================================================================
    // Obtém o IDPESSOA+IDLINHATELEFONICA a partir do numero do documento
    //==========================================================================
    if ( false == proCBuscarPFPorDocLinPos(pszNrDocumento,pszIdTipoDocumento,&dadospessoafisicapordoc,xml_g) )
    {
        if ( false == proCBuscarPFPorDocLinPre(pszNrDocumento,pszIdTipoDocumento,&dadospessoafisicapordoc,xml_g) )
        {
            sprintf(szMessageException, "Dados não encontrados para o documento '%s-%s'",pszIdTipoDocumento,pszNrDocumento);
            throw PrePagoException(ERRO_EXECUCAO, szMessageException);
        }
    }

    //==========================================================================
    // Dados da Pessoa Física
    //==========================================================================
    CPessoa oPessoa;
    CPessoaFisica oPessoaFisica;
    CValorPossivel oValorPossivel;

    oPessoa.setIdPessoa(dadospessoafisicapordoc.oszIDPESSOA);
    if( oPessoa.buscaPessoaPorIdPessoa() )
    {
        oPessoaFisica.setIdPessoa(dadospessoafisicapordoc.oszIDPESSOA);
        if( oPessoaFisica.buscaPessoaFisica() )
        {
            xml_g->createTag("PF");
            xml_g->addItem("nmPessoa", oPessoa.tPessoa.szNmPessoa);
            xml_g->addItem("idSexoSelecionado", oPessoaFisica.tPessoaFisica.szIdSexo);
            xml_g->addItem("idEstadoCivilSelecionado", oPessoaFisica.tPessoaFisica.szIdEstadoCivil);
            xml_g->addItem("dtNascimento", oPessoaFisica.tPessoaFisica.szDtNascimento);
            xml_g->addItem("dsProfissao", oPessoa.tPessoa. szDsCargoContato);

            //Busca a escolaridade da pessoa
            if( oValorPossivel.ListEscolaridadePorIdPessoa(dadospessoafisicapordoc.oszIDPESSOA) > 0 )
            {
                //Mesmo que a pesquisa retorne mais de um, monta o xml apenas o primeiro
                xml_g->addItem("idEscolaridadeSelecionado", oValorPossivel.Registro(0)->cidValorPossivel);
                xml_g->addItem("dsEscolaridadeSelecionado", oValorPossivel.Registro(0)->cdsValorPossivel);
            }
            //Busca a ocupacao da pessoa
            if( oValorPossivel.ListOcupacaoPorIdPessoa(dadospessoafisicapordoc.oszIDPESSOA) > 0 )
            {
                //Mesmo que a pesquisa retorne mais de um, monta o xml apenas o primeiro
                xml_g->addItem("idOcupacaoSelecionado", oValorPossivel.Registro()->cidValorPossivel);
            }
            xml_g->closeTag();//PF
        }
    }

    //==========================================================================
    // Endereço
    //==========================================================================
    CPessoaEndereco oPessoaEndereco;
    //CPessoaEndereco oPessoaEnderecoConta;
    CLinhaConta oLinhaConta;
    CUF oUF;
    CPais oPais;
    // char cEnderecoConta[255+1];
    // char cEndereco[255+1];
    // int nAchouEnderecoIgual = 0;

    //oPessoaEnderecoConta.Zera();
    //oPessoaEnderecoConta.setIdPessoa(dadospessoafisicapordoc.oszIDPESSOA);
    oPessoaEndereco.setIdPessoa(dadospessoafisicapordoc.oszIDPESSOA);
    oLinhaConta.setIdLinhaTelefonica(dadospessoafisicapordoc.oszIDLINHATELEFONICA);

    if( oLinhaConta.buscaLinhaContaCliente() )
    {
        //Se retornou a conta do tipo relacionamento cliente, parte para a busca de endereco
        oPessoaEndereco.buscaEnderecoContaPorIdPessoa(oLinhaConta.getIdConta());
    }
    else
    {
        oPessoaEndereco.Zera();
    }

    xml_g->createTag("Endereco");

    xml_g->createTag("EnderecoPrePagoVO");

    xml_g->addItem("idEndereco", oPessoaEndereco.getIdPessoaEndereco(0));
    xml_g->addItem("inAtualEndereco", "1");
    xml_g->addItem("idTipoEnderecoSelecionado", oPessoaEndereco.getIdTipoEndereco(0));
    xml_g->addItem("idTituloLogradouro", "");
    xml_g->addItem("dsTituloLogradouro", oPessoaEndereco.getNmTituloLogradouro(0));
    xml_g->addItem("idTipoLogradouro", "");
    xml_g->addItem("dsTipoLogradouro", oPessoaEndereco.getNmTipoLogradouro(0));
    xml_g->addItem("dsLogradouro", oPessoaEndereco.getNmLogradouro(0));
    xml_g->addItem("nrEndereco", oPessoaEndereco.getNrEndereco(0));
    xml_g->addItem("dsComplementoEndereco", oPessoaEndereco.getDsEnderecoComplemento(0));
    xml_g->addItem("dsBairro", oPessoaEndereco.getNmBairro(0));
    xml_g->addItem("dsMunicipio", oPessoaEndereco.getNmMunicipio(0));
    xml_g->addItem("nrCEP", oPessoaEndereco.getNrCep(0));

    xml_g->createTag("UF");
    oUF.ListId(oPessoaEndereco.getIdUF(0));
    xml_g->addItem("idUFEndereco", oUF.Registro() ? oUF.Registro()->cidUF:"");
    xml_g->addItem("sgUFEndereco", oUF.Registro() ? oUF.Registro()->csgUF: "");
    xml_g->addItem("dsUFEndereco", oUF.Registro() ? oUF.Registro()->cnmUF: "");
    xml_g->closeTag();//UF

    xml_g->createTag("Pais");
    oPais.ListId(oPessoaEndereco.getIdPais(0));
    xml_g->addItem("idPaisEndereco", oPais.Registro() ? oPais.Registro()->cidPais : "");
    xml_g->addItem("sgPaisEndereco", oPais.Registro() ? oPais.Registro()->csgPais : "");
    xml_g->addItem("dsPaisEndereco", oPais.Registro() ? oPais.Registro()->cdsPais : "");
    xml_g->closeTag();//Pais

    xml_g->closeTag();//Endereco

    xml_g->closeTag();//EnderecoPrePagoVO

    // if( oLinhaConta.buscaLinhaContaCliente() )
    // {
    //     //Se retornou a conta do tipo relacionamento cliente, parte para a busca de endereco
    //     oPessoaEnderecoConta.buscaPessoaEnderecoContaPorIdPessoa( oLinhaConta.getIdConta());
    // }
    // else
    // {
    //     oPessoaEnderecoConta.Zera();
    // }

    // oPessoaEndereco.buscaPessoaEnderecoPorIdPessoa();

    // if( oPessoaEndereco.getQuantidade() > 0 )
    // {
    //     for( x = 0; x < oPessoaEndereco.getQuantidade(); x++ )
    //     {
    //         xml_g->createTag("Endereco");
    //         xml_g->createTag("EnderecoPrePagoVO");

    //         xml_g->addItem("idEndereco", oPessoaEndereco.getIdPessoaEndereco(x));
    //         if( oPessoaEnderecoConta.getQuantidade() > 0 )
    //         {
    //             strcpy( cEnderecoConta, oPessoaEnderecoConta.getIdPessoaEndereco(0));
    //             strcpy( cEndereco     , oPessoaEndereco.getIdPessoaEndereco(x));
    //             
    //             if( strcmp(cEndereco,cEnderecoConta ) == 0 )
    //             {
    //                 xml_g->addItem("inAtualEndereco", "1");
    //                 nAchouEnderecoIgual++;
    //             }
    //             else
    //             {
    //                 xml_g->addItem("inAtualEndereco", "");
    //             }
    //         }
    //         else
    //         {
    //             if( strcmp( oPessoaEndereco.getInEnderecoPreferencial(x), "1" ) == 0 )
    //             {
    //                 xml_g->addItem("inAtualEndereco", "1");
    //             }
    //             else
    //             {
    //                 xml_g->addItem("inAtualEndereco", "");
    //             }
    //         }
    //         xml_g->addItem("idTipoEnderecoSelecionado", oPessoaEndereco.getIdTipoEndereco(x));
    //         xml_g->addItem("idTituloLogradouro", "");
    //         xml_g->addItem("dsTituloLogradouro", oPessoaEndereco.getNmTituloLogradouro(x));
    //         xml_g->addItem("idTipoLogradouro", "");
    //         xml_g->addItem("dsTipoLogradouro", oPessoaEndereco.getNmTipoLogradouro(x));
    //         xml_g->addItem("dsLogradouro", oPessoaEndereco.getNmLogradouro(x));
    //         xml_g->addItem("nrEndereco", oPessoaEndereco.getNrEndereco(x));
    //         xml_g->addItem("dsComplementoEndereco", oPessoaEndereco.getDsEnderecoComplemento(x));
    //         xml_g->addItem("dsBairro", oPessoaEndereco.getNmBairro(x));
    //         xml_g->addItem("dsMunicipio", oPessoaEndereco.getNmMunicipio(x));
    //         xml_g->addItem("nrCEP", oPessoaEndereco.getNrCep(x));
    // 
    //         xml_g->createTag("UF");
    //         oUF.ListId(oPessoaEndereco.getIdUF(x));
    //         xml_g->addItem("idUFEndereco", oUF.Registro()->cidUF);
    //         xml_g->addItem("sgUFEndereco", oUF.Registro()->csgUF);
    //         xml_g->addItem("dsUFEndereco", oUF.Registro()->cnmUF);
    //         xml_g->closeTag();//UF
    // 
    //         xml_g->createTag("Pais");
    //         oPais.ListId( oPessoaEndereco.getIdPais(x));
    //         xml_g->addItem("idPaisEndereco", oPais.Registro()->cidPais);
    //         xml_g->addItem("sgPaisEndereco", oPais.Registro()->csgPais);
    //         xml_g->addItem("dsPaisEndereco", oPais.Registro()->cdsPais);
    //         xml_g->closeTag();//Pais
    // 
    //         xml_g->closeTag();//Endereco
    // 
    //         xml_g->closeTag();//EnderecoPrePagoVO
    // 
    //         if( ( nAchouEnderecoIgual == 0 ) && ( (x+1) == oPessoaEndereco.getQuantidade() ) )
    //         {
    //             if( oPessoaEnderecoConta.getQuantidade() > 0 ) 
    //             {
    //                 xml_g->createTag("Endereco");
    // 
    //                 xml_g->createTag("EnderecoPrePagoVO");
    // 
    //                 xml_g->addItem("idEndereco", oPessoaEnderecoConta.getIdPessoaEndereco(x));
    //                 xml_g->addItem("inAtualEndereco", "1");
    //                 xml_g->addItem("idTipoEnderecoSelecionado", oPessoaEnderecoConta.getIdTipoEndereco(x));
    //                 xml_g->addItem("idTituloLogradouro", "");
    //                 xml_g->addItem("dsTituloLogradouro", oPessoaEnderecoConta.getNmTituloLogradouro(x));
    //                 xml_g->addItem("idTipoLogradouro", "");
    //                 xml_g->addItem("dsTipoLogradouro", oPessoaEnderecoConta.getNmTipoLogradouro(x));
    //                 xml_g->addItem("dsLogradouro", oPessoaEnderecoConta.getNmLogradouro(x));
    //                 xml_g->addItem("nrEndereco", oPessoaEnderecoConta.getNrEndereco(x));
    //                 xml_g->addItem("dsComplementoEndereco", oPessoaEnderecoConta.getDsEnderecoComplemento(x));
    //                 xml_g->addItem("dsBairro", oPessoaEnderecoConta.getNmBairro(x));
    //                 xml_g->addItem("dsMunicipio", oPessoaEnderecoConta.getNmMunicipio(x));
    //                 xml_g->addItem("nrCEP", oPessoaEnderecoConta.getNrCep(x));
    // 
    //                 xml_g->createTag("UF");
    //                 oUF.ListId( oPessoaEnderecoConta.getIdUF(x));
    //                 xml_g->addItem("idUFEndereco", oUF.Registro()->cidUF);
    //                 xml_g->addItem("sgUFEndereco", oUF.Registro()->csgUF);
    //                 xml_g->addItem("dsUFEndereco", oUF.Registro()->cnmUF);
    //                 xml_g->closeTag();//UF
    // 
    //                 xml_g->createTag("Pais");
    //                 oPais.ListId( oPessoaEnderecoConta.getIdPais(x));
    //                 xml_g->addItem("idPaisEndereco", oPais.Registro()->cidPais);
    //                 xml_g->addItem("sgPaisEndereco", oPais.Registro()->csgPais);
    //                 xml_g->addItem("dsPaisEndereco", oPais.Registro()->cdsPais);
    //                 xml_g->closeTag();//Pais
    // 
    //                 xml_g->closeTag();//Endereco
    // 
    //                 xml_g->closeTag();//EnderecoPrePagoVO
    //             }//if( oPessoaEnderecoConta.getQuantidade() > 0 ) 
    //         }//if( ( nAchouEnderecoIgual == 0 ) && ( x == oPessoaEndereco.getQuantidade() ) )
    // 
    //     }//for( int x = 0; x < oPessoaEndereco.Quantidade(); x++ )
    // }

    //==========================================================================
    // Contato
    //==========================================================================
    //proCBuscarPFContato(dadospessoafisicapordoc.oszIDPESSOA,&dadospfcontato,&statuspfcontato,xml_g);
    CPessoaComunicacao oPessoaComunicacao;
    CTipoComunicacao oTipoComunicacao;

    xml_g->createTag("Contato");
    oPessoaComunicacao.setIdPessoa(dadospessoafisicapordoc.oszIDPESSOA);

    if( oPessoaComunicacao.buscaTodosPessoaComunicacaoPorIdPessoa() )
    {
        for( x = 0; x < oPessoaComunicacao.getQuantidade(); x++ )
        {
            xml_g->createTag("ListCont");
                xml_g->addItem("idContato", oPessoaComunicacao.getIdPessoaComunicacao(x));
                xml_g->addItem("idTipoContatoSelecionado", oPessoaComunicacao.getIdTipoComunicacao(x));
                xml_g->addItem("dsContato", oPessoaComunicacao.getDsContato(x));
                xml_g->addItem("nmContato", oPessoaComunicacao.getNmContato(x));
                oTipoComunicacao.ListId( oPessoaComunicacao.getIdTipoComunicacao(x));
                xml_g->addItem("sgContato", oTipoComunicacao.Registro()->csgTipoComunicacao);
            xml_g->closeTag();//ListaContato
        }
    }
    xml_g->closeTag();//Contato

    //==========================================================================
    // Documento
    //==========================================================================
    //proCBuscarPFPorDoc(dadospessoafisicapordoc.oszIDPESSOA,&dadospfdocumento,&statuspfdocumento,xml_g);
    CDocumento oDocumento;
    CTipoDocumento oTipoDocumento;

    oDocumento.setIdPessoa(dadospessoafisicapordoc.oszIDPESSOA);

    if( oDocumento.buscaDocumentoPorIdPessoa() )
    {
        for( x = 0; x < oDocumento.getQuantidade(); x++ )
        {
            oTipoDocumento.ListId( oDocumento.getRegistro(x)->szIdTipoDocumento);

            xml_g->createTag("Doc");
                xml_g->addItem("idDoc", oDocumento.getRegistro(x)->szIdDocumento);
                xml_g->addItem("sgDoc", oTipoDocumento.Registro()->csgTipoDocumento);
                xml_g->addItem("nrDoc", oDocumento.getRegistro(x)->szNrDocumento);
                xml_g->addItem("idTipoDoc", oTipoDocumento.Registro()->csgClassificacao);
                xml_g->addItem("dsTipoDoc", oTipoDocumento.Registro()->cdsTipoDocumento);
                xml_g->addItem("dtExpedicaoDoc", oDocumento.getRegistro(x)->szDtEmissao);
                xml_g->addItem("dsOrgaoEmissorDoc", oDocumento.getRegistro(x)->szSgOrgaoExpedidor);
                xml_g->addItem("idUFDoc", oDocumento.getRegistro(x)->szIdUf);
            xml_g->closeTag();//Doc
        }
    }

    //proCBuscarPFEMailCom(dadospessoafisicapordoc.oszIDPESSOA,&dadospfemailcomercial,&statuspfemailcomercial,xml_g);

    //proCBuscarPFEMailPart(dadospessoafisicapordoc.oszIDPESSOA,&dadospfemailparticular,&statuspfemailparticular,xml_g);

    //==========================================================================

    xml_g->addItem("idPessoa",dadospessoafisicapordoc.oszIDPESSOA);
    xml_g->addItem("inTipoPessoa", "PF");

    //xml_g->addItem("inAtual", tPrePagoPessoaVO.inAtual);
    //xml_g->addItem("inClientePosPago", tPrePagoPessoaVO.inClientePosPago);
    
    xml_g->closeTag();//ManterPrePagoVO

    xml_g->closeTag();//PrePagoPessoaVO

    xml_g->closeTag();//Cliente
}

//==============================================================================
void CPesquisaPFPorDoc::BuscarDadosPessoaJuridica(const char *pszNrDocumento,const char *pszIdTipoDocumento)
{
    int x;

    xml_g->createTag("ManterPrePagoVO");
	xml_g->addProp("xmlns", "cliente.fo.vivo.com.br/vo" );
    xml_g->createTag("Cliente");
    xml_g->createTag("PrePagoPessoaVO");

    //==========================================================================
    // Obtém o IDPESSOA+IDLINHATELEFONICA a partir do numero do documento
    //==========================================================================
    DadosPessoaJuridicaPorDoc dadospessoajuridicapordoc;
    if ( false == proCBuscarPJPorDocLinPre(pszNrDocumento,pszIdTipoDocumento,&dadospessoajuridicapordoc,xml_g) )
    {
        if ( false == proCBuscarPJPorDocLinPos(pszNrDocumento,pszIdTipoDocumento,&dadospessoajuridicapordoc,xml_g) )
        {
            sprintf(szMessageException, "Dados não encontrados para o documento '%s-%s'",pszIdTipoDocumento,pszNrDocumento);
            throw PrePagoException(ERRO_EXECUCAO, szMessageException);
        }
    }

    //==========================================================================
    // Dados da Pessoa Física
    //==========================================================================
    CPessoa oPessoa;
    CPessoaJuridica oPessoaJuridica;
    CValorPossivel oValorPossivel;

    oPessoa.setIdPessoa(dadospessoajuridicapordoc.oszIDPESSOA);
    if( oPessoa.buscaPessoaPorIdPessoa() )
    {
        oPessoaJuridica.setIdPessoa(dadospessoajuridicapordoc.oszIDPESSOA);
        if( oPessoaJuridica.buscaPessoaJuridica() )
        {
            xml_g->createTag("PJ");
            xml_g->addItem("idGrupo", "");
            xml_g->addItem("dsRazaoSocial", oPessoa.tPessoa.szNmPessoa);
            xml_g->addItem("nmFantasia", oPessoaJuridica.tPessoaJuridica.szNmFantasia);
            xml_g->addItem("idTributariaSelecionado", oPessoaJuridica.tPessoaJuridica.szIdCFOP);
            xml_g->addItem("idClassificacaoEmpresaSelecionado", oPessoa.tPessoa.szIdTipoCarteira);
            xml_g->addItem("dtFundacao", oPessoaJuridica.tPessoaJuridica.szDtFundacao);
            xml_g->closeTag();//PJ
        }
    }

    //==========================================================================
    // Endereço
    //==========================================================================
    CPessoaEndereco oPessoaEndereco;
    //CPessoaEndereco oPessoaEnderecoConta;
    CLinhaConta oLinhaConta;
    CUF oUF;
    CPais oPais;
    // char cEnderecoConta[255+1];
    // char cEndereco[255+1];
    // int nAchouEnderecoIgual = 0;

    //oPessoaEnderecoConta.Zera();
    //oPessoaEnderecoConta.setIdPessoa(dadospessoajuridicapordoc.oszIDPESSOA);
    oPessoaEndereco.setIdPessoa(dadospessoajuridicapordoc.oszIDPESSOA);
    oLinhaConta.setIdLinhaTelefonica(dadospessoajuridicapordoc.oszIDLINHATELEFONICA);

    if( oLinhaConta.buscaLinhaContaCliente() )
    {
        //Se retornou a conta do tipo relacionamento cliente, parte para a busca de endereco
        oPessoaEndereco.buscaEnderecoContaPorIdPessoa(oLinhaConta.getIdConta());
    }
    else
    {
        oPessoaEndereco.Zera();
    }

    xml_g->createTag("Endereco");

    xml_g->createTag("EnderecoPrePagoVO");

    xml_g->addItem("idEndereco", oPessoaEndereco.getIdPessoaEndereco(0));
    xml_g->addItem("inAtualEndereco", "1");
    xml_g->addItem("idTipoEnderecoSelecionado", oPessoaEndereco.getIdTipoEndereco(0));
    xml_g->addItem("idTituloLogradouro", "");
    xml_g->addItem("dsTituloLogradouro", oPessoaEndereco.getNmTituloLogradouro(0));
    xml_g->addItem("idTipoLogradouro", "");
    xml_g->addItem("dsTipoLogradouro", oPessoaEndereco.getNmTipoLogradouro(0));
    xml_g->addItem("dsLogradouro", oPessoaEndereco.getNmLogradouro(0));
    xml_g->addItem("nrEndereco", oPessoaEndereco.getNrEndereco(0));
    xml_g->addItem("dsComplementoEndereco", oPessoaEndereco.getDsEnderecoComplemento(0));
    xml_g->addItem("dsBairro", oPessoaEndereco.getNmBairro(0));
    xml_g->addItem("dsMunicipio", oPessoaEndereco.getNmMunicipio(0));
    xml_g->addItem("nrCEP", oPessoaEndereco.getNrCep(0));

    xml_g->createTag("UF");
    oUF.ListId( oPessoaEndereco.getIdUF(0));
    xml_g->addItem("idUFEndereco", oUF.Registro()->cidUF);
    xml_g->addItem("sgUFEndereco", oUF.Registro()->csgUF);
    xml_g->addItem("dsUFEndereco", oUF.Registro()->cnmUF);
    xml_g->closeTag();//UF

    xml_g->createTag("Pais");
    oPais.ListId( oPessoaEndereco.getIdPais(0));
    xml_g->addItem("idPaisEndereco", oPais.Registro()->cidPais);
    xml_g->addItem("sgPaisEndereco", oPais.Registro()->csgPais);
    xml_g->addItem("dsPaisEndereco", oPais.Registro()->cdsPais);
    xml_g->closeTag();//Pais

    xml_g->closeTag();//Endereco

    xml_g->closeTag();//EnderecoPrePagoVO

    // if( oLinhaConta.buscaLinhaContaCliente() )
    // {
    //     //Se retornou a conta do tipo relacionamento cliente, parte para a busca de endereco
    //     oPessoaEnderecoConta.buscaPessoaEnderecoContaPorIdPessoa( oLinhaConta.getIdConta());
    // }
    // else
    // {
    //     oPessoaEnderecoConta.Zera();
    // }
    // 
    // oPessoaEndereco.buscaPessoaEnderecoPorIdPessoa();
    // 
    // if( oPessoaEndereco.getQuantidade() > 0 )
    // {
    //     for( x = 0; x < oPessoaEndereco.getQuantidade(); x++ )
    //     {
    //         xml_g->createTag("Endereco");
    //         xml_g->createTag("EnderecoPrePagoVO");
    // 
    //         xml_g->addItem("idEndereco", oPessoaEndereco.getIdPessoaEndereco(x));
    //         if( oPessoaEnderecoConta.getQuantidade() > 0 )
    //         {
    //             strcpy( cEnderecoConta, oPessoaEnderecoConta.getIdPessoaEndereco(0));
    //             strcpy( cEndereco     , oPessoaEndereco.getIdPessoaEndereco(x));
    //             
    //             if( strcmp(cEndereco,cEnderecoConta ) == 0 )
    //             {
    //                 xml_g->addItem("inAtualEndereco", "1");
    //                 nAchouEnderecoIgual++;
    //             }
    //             else
    //             {
    //                 xml_g->addItem("inAtualEndereco", "");
    //             }
    //         }
    //         else
    //         {
    //             if( strcmp( oPessoaEndereco.getInEnderecoPreferencial(x), "1" ) == 0 )
    //             {
    //                 xml_g->addItem("inAtualEndereco", "1");
    //             }
    //             else
    //             {
    //                 xml_g->addItem("inAtualEndereco", "");
    //             }
    //         }
    //         xml_g->addItem("idTipoEnderecoSelecionado", oPessoaEndereco.getIdTipoEndereco(x));
    //         xml_g->addItem("idTituloLogradouro", "");
    //         xml_g->addItem("dsTituloLogradouro", oPessoaEndereco.getNmTituloLogradouro(x));
    //         xml_g->addItem("idTipoLogradouro", "");
    //         xml_g->addItem("dsTipoLogradouro", oPessoaEndereco.getNmTipoLogradouro(x));
    //         xml_g->addItem("dsLogradouro", oPessoaEndereco.getNmLogradouro(x));
    //         xml_g->addItem("nrEndereco", oPessoaEndereco.getNrEndereco(x));
    //         xml_g->addItem("dsComplementoEndereco", oPessoaEndereco.getDsEnderecoComplemento(x));
    //         xml_g->addItem("dsBairro", oPessoaEndereco.getNmBairro(x));
    //         xml_g->addItem("dsMunicipio", oPessoaEndereco.getNmMunicipio(x));
    //         xml_g->addItem("nrCEP", oPessoaEndereco.getNrCep(x));
    // 
    //         xml_g->createTag("UF");
    //         oUF.ListId( oPessoaEndereco.getIdUF(x));
    //         xml_g->addItem("idUFEndereco", oUF.Registro()->cidUF);
    //         xml_g->addItem("sgUFEndereco", oUF.Registro()->csgUF);
    //         xml_g->addItem("dsUFEndereco", oUF.Registro()->cnmUF);
    //         xml_g->closeTag();//UF
    // 
    //         xml_g->createTag("Pais");
    //         oPais.ListId( oPessoaEndereco.getIdPais(x));
    //         xml_g->addItem("idPaisEndereco", oPais.Registro()->cidPais);
    //         xml_g->addItem("sgPaisEndereco", oPais.Registro()->csgPais);
    //         xml_g->addItem("dsPaisEndereco", oPais.Registro()->cdsPais);
    //         xml_g->closeTag();//Pais
    // 
    //         xml_g->closeTag();//Endereco
    // 
    //         xml_g->closeTag();//EnderecoPrePagoVO
    // 
    //         if( ( nAchouEnderecoIgual == 0 ) && ( (x+1) == oPessoaEndereco.getQuantidade() ) )
    //         {
    //             if( oPessoaEnderecoConta.getQuantidade() > 0 ) 
    //             {
    //                 xml_g->createTag("Endereco");
    // 
    //                 xml_g->createTag("EnderecoPrePagoVO");
    // 
    //                 xml_g->addItem("idEndereco", oPessoaEnderecoConta.getIdPessoaEndereco(x));
    //                 xml_g->addItem("inAtualEndereco", "1");
    //                 xml_g->addItem("idTipoEnderecoSelecionado", oPessoaEnderecoConta.getIdTipoEndereco(x));
    //                 xml_g->addItem("idTituloLogradouro", "");
    //                 xml_g->addItem("dsTituloLogradouro", oPessoaEnderecoConta.getNmTituloLogradouro(x));
    //                 xml_g->addItem("idTipoLogradouro", "");
    //                 xml_g->addItem("dsTipoLogradouro", oPessoaEnderecoConta.getNmTipoLogradouro(x));
    //                 xml_g->addItem("dsLogradouro", oPessoaEnderecoConta.getNmLogradouro(x));
    //                 xml_g->addItem("nrEndereco", oPessoaEnderecoConta.getNrEndereco(x));
    //                 xml_g->addItem("dsComplementoEndereco", oPessoaEnderecoConta.getDsEnderecoComplemento(x));
    //                 xml_g->addItem("dsBairro", oPessoaEnderecoConta.getNmBairro(x));
    //                 xml_g->addItem("dsMunicipio", oPessoaEnderecoConta.getNmMunicipio(x));
    //                 xml_g->addItem("nrCEP", oPessoaEnderecoConta.getNrCep(x));
    // 
    //                 xml_g->createTag("UF");
    //                 oUF.ListId( oPessoaEnderecoConta.getIdUF(x));
    //                 xml_g->addItem("idUFEndereco", oUF.Registro()->cidUF);
    //                 xml_g->addItem("sgUFEndereco", oUF.Registro()->csgUF);
    //                 xml_g->addItem("dsUFEndereco", oUF.Registro()->cnmUF);
    //                 xml_g->closeTag();//UF
    // 
    //                 xml_g->createTag("Pais");
    //                 oPais.ListId( oPessoaEnderecoConta.getIdPais(x));
    //                 xml_g->addItem("idPaisEndereco", oPais.Registro()->cidPais);
    //                 xml_g->addItem("sgPaisEndereco", oPais.Registro()->csgPais);
    //                 xml_g->addItem("dsPaisEndereco", oPais.Registro()->cdsPais);
    //                 xml_g->closeTag();//Pais
    // 
    //                 xml_g->closeTag();//Endereco
    // 
    //                 xml_g->closeTag();//EnderecoPrePagoVO
    //             }//if( oPessoaEnderecoConta.getQuantidade() > 0 ) 
    //         }//if( ( nAchouEnderecoIgual == 0 ) && ( x == oPessoaEndereco.getQuantidade() ) )
    // 
    //     }//for( int x = 0; x < oPessoaEndereco.Quantidade(); x++ )
    // }

    //==========================================================================
    // Contato
    //==========================================================================
    //proCBuscarPFContato(dadospessoajuridicapordoc.oszIDPESSOA,&dadospfcontato,&statuspfcontato,xml_g);
    CPessoaComunicacao oPessoaComunicacao;
    CTipoComunicacao oTipoComunicacao;

    xml_g->createTag("Contato");
    oPessoaComunicacao.setIdPessoa(dadospessoajuridicapordoc.oszIDPESSOA);

    if( oPessoaComunicacao.buscaTodosPessoaComunicacaoPorIdPessoa() )
    {
        for( x = 0; x < oPessoaComunicacao.getQuantidade(); x++ )
        {
            xml_g->createTag("ListCont");
                xml_g->addItem("idContato", oPessoaComunicacao.getIdPessoaComunicacao(x));
                xml_g->addItem("idTipoContatoSelecionado", oPessoaComunicacao.getIdTipoComunicacao(x));
                xml_g->addItem("dsContato", oPessoaComunicacao.getDsContato(x));
                xml_g->addItem("nmContato", oPessoaComunicacao.getNmContato(x));
                oTipoComunicacao.ListId( oPessoaComunicacao.getIdTipoComunicacao(x));
                xml_g->addItem("sgContato", oTipoComunicacao.Registro()->csgTipoComunicacao);
            xml_g->closeTag();//ListaContato
        }
    }
    xml_g->closeTag();//Contato

    //==========================================================================
    // Documento
    //==========================================================================
    //proCBuscarPFPorDoc(dadospessoajuridicapordoc.oszIDPESSOA,&dadospfdocumento,&statuspfdocumento,xml_g);
    CDocumento oDocumento;
    CTipoDocumento oTipoDocumento;

    oDocumento.setIdPessoa(dadospessoajuridicapordoc.oszIDPESSOA);

    if( oDocumento.buscaDocumentoPorIdPessoa() )
    {
        for( x = 0; x < oDocumento.getQuantidade(); x++ )
        {
            oTipoDocumento.ListId( oDocumento.getRegistro(x)->szIdTipoDocumento);

            xml_g->createTag("Doc");
                xml_g->addItem("idDoc", oDocumento.getRegistro(x)->szIdDocumento);
                xml_g->addItem("sgDoc", oTipoDocumento.Registro()->csgTipoDocumento);
                xml_g->addItem("nrDoc", oDocumento.getRegistro(x)->szNrDocumento);
                xml_g->addItem("idTipoDoc", oTipoDocumento.Registro()->csgClassificacao);
                xml_g->addItem("dsTipoDoc", oTipoDocumento.Registro()->cdsTipoDocumento);
                xml_g->addItem("dtExpedicaoDoc", oDocumento.getRegistro(x)->szDtEmissao);
                xml_g->addItem("dsOrgaoEmissorDoc", oDocumento.getRegistro(x)->szSgOrgaoExpedidor);
                xml_g->addItem("idUFDoc", oDocumento.getRegistro(x)->szIdUf);
            xml_g->closeTag();//Doc
        }
    }

    //proCBuscarPFEMailCom(dadospessoajuridicapordoc.oszIDPESSOA,&dadospfemailcomercial,&statuspfemailcomercial,xml_g);

    //proCBuscarPFEMailPart(dadospessoajuridicapordoc.oszIDPESSOA,&dadospfemailparticular,&statuspfemailparticular,xml_g);

    //==========================================================================

    xml_g->addItem("idPessoa",dadospessoajuridicapordoc.oszIDPESSOA);
    xml_g->addItem("inTipoPessoa", "PJ");

    //xml_g->addItem("inAtual", tPrePagoPessoaVO.inAtual);
    //xml_g->addItem("inClientePosPago", tPrePagoPessoaVO.inClientePosPago);
    
    xml_g->closeTag();//ManterPrePagoVO

    xml_g->closeTag();//PrePagoPessoaVO

    xml_g->closeTag();//Cliente
}

//==============================================================================
void CPesquisaPFPorDoc::removerPontuacaoNrDocumento(char *pszNrDocumento)
{  // remove todos os caracteres diferentes de 0..9, A..Z e a..z
    char *p = pszNrDocumento;
    char szNrDocumento[LEN_AUX + LEN_EOS];
    int x=0;

    while (p && *p)
    {
        if (isalnum(*p)) { szNrDocumento[x++] = *p; }
        p++;
    }
    szNrDocumento[x] = 0;

    strcpy(pszNrDocumento,szNrDocumento);
}
