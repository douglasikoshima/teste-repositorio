/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Miguel Benaventes
 * @version $Revision: 1.1.6.3 $
 * @CVS     $Author: a5114878 $ - $Date: 2012/08/27 19:27:30 $
 **/

#include <stdio.h>
#include <stdlib.h>
#include "../include/selectData.h"
#include "../include/CGrupos.h"

#ifdef WIN32
//====================================================================
// Em ambiente windows não é possível compilar este serviço sem que 
// se deixe apenas um DECLARE_TUXEDO_SERVICE() habilitado, pois cada
// um deles possui uma declaração main() embutida. Assim, selecione
// antes qual dos serviços deseja debuggar e o habilite pelo '#define'
// respectivo. Junho,2006 - Cassio
//====================================================================

    //#define DEF_READGRPSTRATN
    //#define DEF_WRITEGRPSTRAT
    //#define DEF_CONSGRPABERT
    //#define DEF_CNSGRPDSPASS
    //#define DEF_ATUFLXFASEGRP
    #define DEF_WRITEGRPSPROC
    //#define DEF_READGRPSPROCVO
#else
    #define DEF_READGRPSTRATN
    #define DEF_WRITEGRPSTRAT
    #define DEF_CONSGRPABERT
    #define DEF_CNSGRPDSPASS
    #define DEF_ATUFLXFASEGRP
    #define DEF_WRITEGRPSPROC
    #define DEF_READGRPSPROCVO
#endif

#ifdef DEF_READGRPSTRATN
DECLARE_TUXEDO_SERVICE(READGRPSTRATN);
#endif

#ifdef DEF_WRITEGRPSTRAT
    DECLARE_TUXEDO_SERVICE(WRITEGRPSTRAT);
#endif

#ifdef DEF_CONSGRPABERT
    DECLARE_TUXEDO_SERVICE(CONSGRPABERT);
#endif

#ifdef DEF_CNSGRPDSPASS
    DECLARE_TUXEDO_SERVICE(CNSGRPDSPASS);
#endif

#ifdef DEF_ATUFLXFASEGRP
    DECLARE_TUXEDO_SERVICE(ATUFLXFASEGRP);
#endif

#ifdef DEF_WRITEGRPSPROC
    DECLARE_TUXEDO_SERVICE(WRITEGRPSPROC);
#endif

#ifdef DEF_READGRPSPROCVO
    DECLARE_TUXEDO_SERVICE(readGrpsProcVO);
#endif

class TuxHelperImpl:public TuxHelper
{
    public:
        DOMNode * walkDOMImpl( DOMNode*a , char*b , int*c , int d )
        {
                return walkDOM( a , b , c , d );
        }
};

char pSignature[]="Administracao Workflow;libCGPS;so;0.1;#20040510:112500#";

#ifdef DEF_READGRPSPROCVO
void implreadGrpsProcVO::Execute( DOMNode * XMLIn, XMLGen * XMLOut )
{
    ULOG_START("implreadGrpsProcVO::Execute()");
    GruposRequest grp( XMLOut );
    char * preqnode;
    char * ptype;
    int  iFiltroGrupos;
    char * p;

    preqnode = walkAttr(XMLIn,"readPerfProcVO","reqnode",0);
    ptype = walkAttr(XMLIn,"readPerfProcVO","type",0);
    if( !ptype || !preqnode )
        throw new TuxBasicSvcException("04E0001");

    if( !grp.SetQueryType(*ptype) )
        throw new TuxBasicSvcException("04E0002");

    p = walkTree(XMLIn,"filtroGrupos",0);
    if ( p == NULL )
        iFiltroGrupos = 0;
    else
    {
        iFiltroGrupos = atoi(p);
        XMLString::release(&p);
    }

    XMLOut->createTag("GruposProcessosVO");
    XMLOut->addProp("xmlns","admsistemas.fo.vivo.com.br/vo");
    XMLOut->addProp("xmlns:xsi","http://www.w3.org/2001/XMLSchema-instance");
    grp.QueryData( preqnode,iFiltroGrupos );
    XMLOut->closeTag();

    setStatusCode("04I0000","readGrpsProcVO: Sucesso na execução");
    ULOG_END("implreadGrpsProcVO::Execute()");                     
}
#endif

#ifdef DEF_WRITEGRPSPROC
void implWRITEGRPSPROC::Execute( DOMNode * XMLIn, XMLGen * XMLOut )
{
    ULOG_START("implWRITEGRPSPROC::Execute()");
    int erro = 9;
    char *msg = "Operação realizada com sucesso.";

    GruposWriter grp(getHdrAttr("user"));
    erro = grp.BatchWriter(XMLIn);

    if ( erro == 0 )
    {
        throw new TuxBasicSvcException("04E0001");
    }

    if ( erro == -1 )
    {
        msg = "O contato precisa de configuração dos grupos de tratamento.";
    }
    else if ( erro == -2 )
    {
        msg = "A lista Retorno tem que possuir pelo menos um Grupo de Retorno.";
    }
    else if ( erro == -3 )
    {
        msg = "Grupo de tratamento associado a ENCAMINHAMENTO ou ENCAMINHAMENTO/FECHAMENTO IMEDIATO.";
    }
        
    XMLOut->createTag( "WFAcaoRetornoVO" );
    XMLOut->addProp( "xmlns","workflow.fo.vivo.com.br/vo" );
    
        XMLOut->addItem( "acaoExecucao","S" );
        XMLOut->addItem( "mensagem",msg );
    
    XMLOut->closeTag();

    setStatusCode("04I0000","Success Execution");
    ULOG_END("implWRITEGRPSPROC::Execute()");                     
}
#endif

#ifdef DEF_READGRPSTRATN
void implREADGRPSTRATN::Execute(DOMNode*XMLIn,XMLGen*XMLOut)
{
    ULOG_START("implREADGRPSTRATN::Execute()");
    NiveisRequest nrq(XMLOut);
    char*preqnode;

    preqnode=walkAttr(XMLIn,"readGrpsTratNvl","reqnode",0);
    if(!preqnode)
        throw new TuxBasicSvcException("04E0001");

    XMLOut->createTag("GruposTratamentoNiveisVO");
    XMLOut->addProp("xmlns","admsistemas.fo.vivo.com.br/vo");
    XMLOut->addProp("xmlns:xsi","http://www.w3.org/2001/XMLSchema-instance");
    nrq.QueryData(preqnode);
    XMLOut->closeTag();

    setStatusCode("04I0000","READGRPSTRATN: Sucesso na execução");
    
    ULOG_END("implREADGRPSTRATN::Execute()");                 
}
#endif

#ifdef DEF_WRITEGRPSTRAT
void implWRITEGRPSTRAT::Execute( DOMNode*XMLIn,XMLGen*XMLOut )
{
    ULOG_START("implWRITEGRPSTRAT::Execute()"); 
    NiveisWriter nwr(getHdrAttr("user"));

    if ( !nwr.BatchWriter(XMLIn) )
        throw new TuxBasicSvcException("04E0001");

   setStatusCode("04I0000","WRITEGRPSTRAT:: Sucesso na execução");
   ULOG_END("implWRITEGRPSTRAT::Execute()");                 
}
#endif

#ifdef DEF_CONSGRPABERT
void implCONSGRPABERT::Execute( DOMNode*XMLIn,XMLGen*XMLOut )
{
    ULOG_START("implCONSGRPABERT::Execute()");
    bool  flgNotXML = false;
    long idContato = 0x0;
    long idGrupo = 0x0;
    long idTpFechamento = 0x0;
    char *p;

    GruposRequest grp( XMLOut );

    p = walkTree( XMLIn,"contato",0 );
    if( p == NULL )
    {
        p = walkTree( XMLIn,"grupo",0 );
        if( p == NULL )
        {
            p = walkTree( XMLIn,"fechamento",0 );
            if( p == NULL )
                flgNotXML = true;
        }
    }
    if ( p != NULL )
        XMLString::release(&p);

    if ( flgNotXML == true )
    {
        p = walkTree( XMLIn,"idContato",0 );
        if( p == NULL )
            idContato = 0L;
        else
        {
            idContato = atol(p);
            XMLString::release(&p);
        }
        grp.ConsultaGruposAbertura(idContato);
    }
    else
    {
        p = walkTree( XMLIn,"contato",0 );
        if( p == NULL )
            idContato = 0L;
        else
        {
            idContato = atol(p);
            XMLString::release(&p);
        }

        p = walkTree( XMLIn,"grupo",0 );
        if( p == NULL )
            idGrupo = 0L;
        else
        {
            idGrupo = atol(p);
            XMLString::release(&p);
        }

        p = walkTree( XMLIn,"fechamento",0 );
        if( p == NULL )
            idTpFechamento = 0L;
        else
        {
            idTpFechamento = atol(p);
            XMLString::release(&p);
        }

        XMLOut->createTag("AssosiacaoGrupoVariaveisVO");
        XMLOut->addProp("xmlns","admsistemas.fo.vivo.com.br/vo");

        XMLOut->addProp("xmlns:ns2","cliente.fo.vivo.com.br/vo");
        XMLOut->addProp("xmlns:xsi","http://www.w3.org/2001/XMLSchema-instance");

        XMLOut->createTag("AssosiacaoGrupoVariaveisDisponiveisVO");
            grp.ConsultaTipoCarteira( idContato, idGrupo, idTpFechamento );
            grp.ConsultaTipoLinha( idContato, idGrupo, idTpFechamento );
            grp.ConsultaTipoPessoa( idContato, idGrupo, idTpFechamento );
            grp.ConsultaTipoRelacion( idContato, idGrupo, idTpFechamento );
            grp.ConsultaSegmentacao( idContato, idGrupo, idTpFechamento );
        XMLOut->closeTag();
        XMLOut->createTag("AssosiacaoGrupoVariaveisAssosiadosVO");
            grp.ConsultaTipoCarteiraAssoc( idContato,idGrupo,idTpFechamento );
            grp.ConsultaTipoLinhaAssoc( idContato,idGrupo,idTpFechamento );
            grp.ConsultaTipoPessoaAssoc( idContato,idGrupo,idTpFechamento );
            grp.ConsultaTipoRelacionAssoc( idContato,idGrupo,idTpFechamento );
            grp.ConsultaSegmentacaoAssoc( idContato,idGrupo,idTpFechamento );
        XMLOut->closeTag();

    }

    setStatusCode("04I0000","Consulta de Acoes para Grupos de Abertura Efetuada com Sucesso");
    ULOG_END("implCONSGRPABERT::Execute()");                 
}
#endif

#ifdef DEF_CNSGRPDSPASS
void implCNSGRPDSPASS::Execute( DOMNode*XMLIn,XMLGen*XMLOut )
{
    ULOG_START("implCNSGRPDSPASS::Execute()");
    long idFechamento;
    long idContato;
    char *p;

    // Carrega idContato
    p = walkTree( XMLIn,"contato",0 );
    if( p == NULL )
    {
        setStatusCode("04E0005","XML -> ERRO INTERNO");
        throw new TuxBasicSvcException( "04E0005","??? ERRO: XML -> NAO ENCONTROU idContato" );
    }
    else
    {
        idContato = atol(p);
        XMLString::release(&p);
        if ( idContato == 0L )
        {
            setStatusCode("04E0005","XML -> ERRO INTERNO");
            throw new TuxBasicSvcException( "04E0005","??? ERRO: XML -> NAO ENCONTROU idContato" );
        }
    }

    // Carrega idAtividade
    p = walkTree( XMLIn,"acao",0 );
    if( p == NULL )
    {
        setStatusCode("04E0010","XML -> ERRO INTERNO");
        throw new TuxBasicSvcException( "04E0010","??? ERRO: XML -> NAO ENCONTROU fechamento" );
    }
    else
    {
        idFechamento = atol(p);
        XMLString::release(&p);
        if ( idFechamento == 0L )
        {
            setStatusCode("04E0010","XML -> ERRO INTERNO");
            throw new TuxBasicSvcException( "04E0010","??? ERRO: XML -> NAO ENCONTROU idAtividade" );
        }
    }

    GruposRequest grp( XMLOut );

    XMLOut->createTag("GruposProcessosVO");
    XMLOut->addProp("xmlns","admsistemas.fo.vivo.com.br/vo");
    XMLOut->addProp("xmlns:xsi","http://www.w3.org/2001/XMLSchema-instance");
        grp.ConsultaGruposTpFechamento( idContato,idFechamento );
        grp.ConsultaGruposTpFechamentoAssoc( idContato,idFechamento );
    XMLOut->closeTag();

    setStatusCode("04I0000","Consulta de Grupos Disponiveis e Associados Efetuada com Sucesso");
    ULOG_END("implCNSGRPDSPASS::Execute()");                 

}
#endif


#ifdef DEF_ATUFLXFASEGRP
/*
 * Atualiza Fluxo Fase Grupo
 */
void implATUFLXFASEGRP::Execute( DOMNode *XMLIn,XMLGen *XMLOut )
{
    ULOG_START("implATUFLXFASEGRP::Execute()"); 
    DOMNode        *pDOMAux;
    TuxHelperImpl   tuxhelperIMPL;
    int   persiste = -1;
    int   i = 0;
    int   iRec = 0;
    long  idAtividade;
    long  idContato;
    long  idGrupo;
    long  idFaseGrpFech;
    long  idTipoCarteiraArray[500];
    long  idSegmentacaoArray[500];
    long  idTipoLinhaArray[500];
    long  idTipoRelacionamentoArray[500];
    long  idTipoPessoaArray[500];

    char *p;

    // Carrega idContato
    p = walkTree( XMLIn,"idContato",0 );
    if( p == NULL )
    {
        setStatusCode("04E0005","XML -> ERRO INTERNO");
        throw new TuxBasicSvcException( "04E0005","??? ERRO: XML -> NAO ENCONTROU idContato" );
    }
    else
    {
        idContato = atol(p);
        XMLString::release(&p);
        if ( idContato == 0 )
        {
            setStatusCode("04E0005","XML -> ERRO INTERNO");
            throw new TuxBasicSvcException( "04E0005","??? ERRO: XML -> NAO ENCONTROU idContato" );
        }
    }

    // Carrega idAtividade
    p = walkTree( XMLIn,"idAtividade",0 );
    if( p == NULL )
    {
        setStatusCode("04E0010","XML -> ERRO INTERNO");
        throw new TuxBasicSvcException( "04E0010","??? ERRO: XML -> NAO ENCONTROU idAtividade" );
    }
    else
    {
        idAtividade = atol(p);
        XMLString::release(&p);
        if ( idAtividade == 0 )
        {
            setStatusCode("04E0010","XML -> ERRO INTERNO");
            throw new TuxBasicSvcException( "04E0010","??? ERRO: XML -> NAO ENCONTROU idAtividade" );
        }
    }

    // Carrega idGrupo
    p = walkTree( XMLIn,"idGrupo",0 );
    if( p == NULL )
    {
        setStatusCode("04E0010","XML -> ERRO INTERNO");
        throw new TuxBasicSvcException( "04E0010","??? ERRO: XML -> NAO ENCONTROU idAtividade" );
    }
    else
    {
        idGrupo = atol(p);
        XMLString::release(&p);
        if ( idGrupo == 0 )
        {
            setStatusCode("04E0011","XML -> ERRO INTERNO");
            throw new TuxBasicSvcException( "04E0011","??? ERRO: XML -> NAO ENCONTROU idGrupo" );
        }
    }

    // Carrega Persiste
    p = walkTree( XMLIn,"persiste",0 );
    if( p == NULL )
    {
        setStatusCode("04E0015","XML -> ERRO INTERNO");
        throw new TuxBasicSvcException( "04E0015","??? ERRO: XML -> NAO ENCONTROU persiste" );
    }
    else
    {
        persiste = atoi(p);
        XMLString::release(&p);
    }

    GruposWriter grp( getHdrAttr("user") );

    if ( persiste == -1 )
    {
        for (i=0;;i++ )  
        {
            p = walkTree( XMLIn,"idGrupo",i );
            if( p == NULL )
                break;
			idGrupo = atol(p);
			ULOG( "Grupo: %lu", idGrupo );
			ULOG( "idAtividade: %lu", idAtividade );
			ULOG( "idContato: %lu", idContato );
			grp.LimpaFluxoFaseGrupo( idContato,idGrupo,idAtividade );
		}
        setStatusCode( "04I0000","Atualização de Fluxo Fase Grupo Efetuada com Sucesso" );

        XMLOut->createTag("WFAcaoRetornoVO");
        XMLOut->addProp("xmlns","workflow.fo.vivo.com.br/vo");
        
        XMLOut->addItem("acaoExecucao","0");
        XMLOut->addItem("mensagem","Informações Atualizadas com Sucesso");

        return;
    }

    // TipoLinha
    iRec = 0;
    pDOMAux = tuxhelperIMPL.walkDOMImpl( XMLIn,"TipoLinhaVO",&iRec,0 );
    if ( pDOMAux != NULL )
    {    
        for ( i=0;;i++ )  
        {
            p = walkTree( pDOMAux,"id",i );
            if( p == NULL )
                break;
            idTipoLinhaArray[i] = atol(p);
            XMLString::release(&p);
        }
        idTipoLinhaArray[i] = END_ARRAY;
    }

    // Segmentacao
    iRec = 0;
    pDOMAux = tuxhelperIMPL.walkDOMImpl( XMLIn,"SegmentacaoVO",&iRec,0 );
    if ( pDOMAux != NULL )
    {
        for ( i=0;;i++ )  
        {
            p = walkTree( pDOMAux,"idSegmentacao",i );
            if( p == NULL )
                break;
            idSegmentacaoArray[i] = atol(p);
            XMLString::release(&p);
        }
        idSegmentacaoArray[i] = END_ARRAY;
    }

    // TipoCarteira
    iRec = 0;
    pDOMAux = tuxhelperIMPL.walkDOMImpl( XMLIn,"CarterizacaoVO",&iRec,0 );
    if ( pDOMAux != NULL )
    {
        for ( i=0;;i++ )  
        {
            p = walkTree( pDOMAux,"idTipoCarteira",i );
            if( p == NULL )
                break;
            idTipoCarteiraArray[i] = atol(p);
            XMLString::release(&p);
        }
        idTipoCarteiraArray[i] = END_ARRAY;
    }

    // TipoPessoa
    iRec = 0;
    pDOMAux = tuxhelperIMPL.walkDOMImpl( XMLIn,"TipoClienteVO",&iRec,0 );
    if ( pDOMAux != NULL )
    {
        for ( i=0;;i++ )  
        {
            p = walkTree( pDOMAux,"codigo",i );
            if( p == NULL )
                break;
            idTipoPessoaArray[i] = atol(p);
            XMLString::release(&p);
        }
        idTipoPessoaArray[i] = END_ARRAY;
    }

    // TipoRelacionamento
    iRec = 0;
    pDOMAux = tuxhelperIMPL.walkDOMImpl( XMLIn,"AdmNaturezaVO",&iRec,0 );
    if ( pDOMAux != NULL )
    {
        for ( i=0;;i++ )  
        {
            p = walkTree( pDOMAux,"idNatureza",i );
            if( p == NULL )
                break;
            idTipoRelacionamentoArray[i] = atol(p);
            XMLString::release(&p);
        }
        idTipoRelacionamentoArray[i] = END_ARRAY;
    }

    GruposRequest obj(XMLOut);

    if ( persiste == 0 )
    {
        int retorno = obj.TotalRegistros(idContato,idGrupo,
                                         idAtividade,idTipoLinhaArray,idSegmentacaoArray,
                                         idTipoCarteiraArray,idTipoPessoaArray,
                                         idTipoRelacionamentoArray );
        if ( retorno > 0 )
        {
            XMLOut->createTag("WFAcaoRetornoVO");
            XMLOut->addProp("xmlns","workflow.fo.vivo.com.br/vo");
            
            XMLOut->addItem("acaoExecucao","1");
            XMLOut->addItem("mensagem","Existem Variáveis Cadastradas.");
            XMLOut->closeTag();
            
            setStatusCode( "04I0005","Existem Variáveis Cadastradas." );
            
            return;
        }
    }  
    else  // PERSISTE == 1
    {
        obj.RegistrosInconsistentes(idContato,idGrupo,
                                    idAtividade,
                                    idTipoLinhaArray,idSegmentacaoArray,
                                    idTipoCarteiraArray,idTipoPessoaArray,
                                    idTipoRelacionamentoArray);
    }

	ULOG( "Executando RegistroAtual" );

    idFaseGrpFech = obj.RegistroAtual(idContato,idGrupo,idAtividade);
    
    if ( idFaseGrpFech == NULL )
    {
        idFaseGrpFech = 0L;
        grp.InsereFluxoFaseGrupo( idContato, idGrupo, idAtividade, &idFaseGrpFech );
    }
    else
    {
        grp.OrganizaFluxoFaseGrupo( idFaseGrpFech );
    }

    //
    //  Inserindo TipoLinha
    grp.FechamentoTipoLinha( idFaseGrpFech,idTipoLinhaArray );

    //
    //  Inserindo Segmentacao
    grp.FechamentoSegmentacao( idFaseGrpFech,idSegmentacaoArray );

    //
    //  Inserindo TipoCarteira
    grp.FechamentoTipoCarteira( idFaseGrpFech,idTipoCarteiraArray );

    //
    //  Inserindo TipoPessoa
    grp.FechamentoTipoPessoa( idFaseGrpFech,idTipoPessoaArray );

    //
    //  Inserindo TipoRelacionamento
    grp.FechamentoTipoRelac( idFaseGrpFech,idTipoRelacionamentoArray );

    XMLOut->createTag("WFAcaoRetornoVO");
    XMLOut->addProp("xmlns","workflow.fo.vivo.com.br/vo");
    
    XMLOut->addItem("acaoExecucao","0");
    XMLOut->addItem("mensagem","Informações Atualizadas com Sucesso");
    
    XMLOut->closeTag();
    
    setStatusCode( "04I0000","Atualização de Fluxo Fase Grupo Efetuada com Sucesso");
                 
    ULOG_END("implATUFLXFASEGRP::Execute()");                 
}
#endif
