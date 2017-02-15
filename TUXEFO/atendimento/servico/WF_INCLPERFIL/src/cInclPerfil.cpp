#include <iostream>
#include <time.h>
#include <vector>
#include <algorithm>
#include <functional>

#include "../include/cInclPerfil.h"
#include "../include/cInclPerfilImpl.h"

//
// Prototipos
//
extern bool proCAlteraVariaveis(const unsigned long idPerfilPrm,VEC_TPLINHAXML     * idTpLinhaXML,VEC_SEGMENTOXML    * idSegmentacaoXML,VEC_CARTEIRAXML    * idCarteiraXML,VEC_PROCEDENCIAXML * idProcedenciaXML,VEC_NATUREZAXML    * idNaturezaXML,VEC_GRUPOXML       * idGrupoXML,VEC_TPCLIENTEXML   * idTipoClienteXML,VEC_CANALXML       * idCanalXML,VEC_REGIONALXML    * idUFOperadoraXML);	
extern bool proCHabilitaDesabilitaPerfil( unsigned long idPerfil, const unsigned long idUser, unsigned int iStatus );	
extern bool proCInsereNomePerfil( const char * sNomePerfil );	
extern bool proCInsereNomePerfil( const unsigned long idUser, const char * sNomePerfil, unsigned long * idPerfilPrm );	
extern bool proCInsereVariaveis(  const unsigned long idPerfilPrm,VEC_TPLINHAXML     * idTpLinhaXML,VEC_SEGMENTOXML    * idSegmentacaoXML,VEC_CARTEIRAXML    * idCarteiraXML,VEC_PROCEDENCIAXML * idProcedenciaXML,VEC_NATUREZAXML    * idNaturezaXML,VEC_GRUPOXML	   * idGrupoXML,VEC_TPCLIENTEXML   * idTipoClienteXML,VEC_CANALXML       * idCanalXML,VEC_REGIONALXML    * idUFOperadoraXML);
extern bool proCValidaNomePerfil( const unsigned long idPerfil, const char * sNomePerfil );	
extern bool proCVerificaPerfilHabilitado( unsigned long idPerfil );	
extern bool proCVerificaPerfilRelacionado( unsigned long idPerfil );	
extern bool proCVerificaVariaveis( unsigned long idPerfil );	
extern int proCAlteraNomePerfil( unsigned long idUser, const char * sNomePerfil, const unsigned long idPerfilXML );	
extern void proCCarregaCanal( const unsigned long idPerfilPrm, VEC_VARIAVEL * pDados );	
extern void proCCarregaCarteira( const unsigned long idPerfilPrm, VEC_VARIAVEL * pDados );	
extern void proCCarregaGrupo( const unsigned long idPerfilPrm, VEC_VARIAVEL * pDados );	
extern void proCCarregaNatureza( const unsigned long idPerfilPrm, VEC_VARIAVEL * pDados );	
extern void proCCarregaPerfil(VEC_TPLINHAXML * idTpLinhaXML,VEC_SEGMENTOXML * idSegmentacaoXML,VEC_CARTEIRAXML * idCarteiraXML,VEC_PROCEDENCIAXML * idProcedenciaXML,VEC_NATUREZAXML * idNaturezaXML,VEC_GRUPOXML * idGrupoXML,VEC_TPCLIENTEXML * idTipoClienteXML,VEC_CANALXML * idCanalXML,VEC_REGIONALXML * idUFOperadoraXML,VEC_PERFIL * pDados);	
extern void proCCarregaProcedencia( const unsigned long idPerfilPrm, VEC_VARIAVEL * pDados );	
extern void proCCarregaRegional( const unsigned long idPerfilPrm, VEC_VARIAVEL * pDados );
extern void proCCarregaSegmentacao( const unsigned long idPerfilPrm, VEC_VARIAVEL * pDados );
extern void proCCarregaTipoCliente( const unsigned long idPerfilPrm, VEC_VARIAVEL * pDados );
extern void proCCarregaTipoLinha( const unsigned long idPerfilPrm, VEC_VARIAVEL * pDados );

//
//------------------------------------------------------------------------
cInclPerfil::cInclPerfil( char *pIdUser,DOMNode * dnode, XMLGen * xml_g )
{
	entrada = dnode;
	saida   = xml_g;
    
    idPerfilXML = 0;
    sNomePerfil[0] = 0x0;
    idUser = strtoul( pIdUser,0,10 );
    inOperacao = 0;

	carregaDados();
    CarregaPerfil();
}




short  cInclPerfil::Processa( void )
{
    short retorno;

    switch ( inOperacao )
    {
        case 1:  
            retorno = IncluirPerfil();
            break;
        
        case 2:
            retorno = IncluirVariaveis();
            break;

        case 3:
            retorno = AlterarPerfil();
            break;
        
        case 4:
            retorno = AlterarVariaveis();
            break;

        case 5:
            retorno = HabilitaDesabilita();
            break;
    }

    return retorno;

}




int cInclPerfil::IncluirPerfil( void )
{

    if ( ValidaNomePerfil() == 0 )
    {
        InsereNomePerfil();
        return 0;
    }
    
    // Jah existe este nome de perfil
    return 1;

}




int cInclPerfil::AlterarPerfil( void )
{
    
    if ( ValidaNomePerfil() == 0 )
    {
        AlteraNomePerfil();
        return 0;
    }
    
    // Jah existe este nome de perfil
    return 1;
    
}




int cInclPerfil::IncluirVariaveis( void )
{

    if ( ValidaPerfil() == 0 )
    {
        if( TotalVariaveisXMLIN() <= 0 )
        {
        	if( proCVerificaPerfilRelacionado( idPerfilXML ) == true )
        	{
        		return 3;//Sem variaveis com relacao, não pode apagar
        	}
        	else
            {
        		proCHabilitaDesabilitaPerfil( idPerfilXML, idUser, 0 );//sem variaveis, sem relacao, desabilita o perfil
            }
       	}
        proCInsereVariaveis
        (
             idPerfilXML
            ,&idTpLinhaXML
            ,&idSegmentacaoXML
            ,&idCarteiraXML
            ,&idProcedenciaXML
            ,&idNaturezaXML
            ,&idGrupoXML
            ,&idTipoClienteXML
            ,&idCanalXML
            ,&idUFOperadoraXML
        );
        
        saida->createTag( "PerfilVariaveisVO" );
        saida->addProp( "xmlns","admsistemas.fo.vivo.com.br/vo" );
        saida->addProp( "xmlns:ns2","cliente.fo.vivo.com.br/vo" );
        saida->addProp( "xmlns:ns3","workflow.fo.vivo.com.br/vo" );
        saida->closeTag();

        return 0;
    }
    else
    {
            return 2;  //  Jah existe um conjunto 
                       //  de variaveis para
                       //  outro perfil
    }

}




int cInclPerfil::AlterarVariaveis( void )
{

    if ( ValidaPerfil() == 3 )
    {
        if( TotalVariaveisXMLIN() <= 0 )
        {
        	if( proCVerificaPerfilRelacionado( idPerfilXML ) == true )
        	{
        		return 3;//Sem variaveis com relacao, não pode apagar
        	}
        	else
        		proCHabilitaDesabilitaPerfil( idPerfilXML, idUser, 0 );//sem variaveis, sem relacao, desabilita o perfil
       	}
        proCAlteraVariaveis
            (
            idPerfilXML
            ,&idTpLinhaXML
            ,&idSegmentacaoXML
            ,&idCarteiraXML
            ,&idProcedenciaXML
            ,&idNaturezaXML
            ,&idGrupoXML
            ,&idTipoClienteXML
            ,&idCanalXML
            ,&idUFOperadoraXML
            );

        saida->createTag( "PerfilVariaveisVO" );
        saida->addProp( "xmlns","admsistemas.fo.vivo.com.br/vo" );
        saida->addProp( "xmlns:ns2","cliente.fo.vivo.com.br/vo" );
        saida->addProp( "xmlns:ns3","workflow.fo.vivo.com.br/vo" );
        saida->closeTag();
        
        return 0;
    }
    else
    {
            return 2;  //  Jah existe um conjunto 
                             //  de variaveis para
                             //  outro perfil
    }

}


int cInclPerfil::HabilitaDesabilita( void )
{
	//Habilitar um perfil
    saida->createTag( "WFAcaoRetornoVO" );
    saida->addProp( "xmlns","workflow.fo.vivo.com.br/vo" );
    saida->addItem( "acaoExecucao","S" );
	if( status == 1 )
	{
    	if( proCVerificaVariaveis( idPerfilXML ) == false )
    	{
        	saida->addItem( "mensagem","Perfil sem variáveis não podem serem habilitados" );
    		return 0;//Sem variaveis não pode habilitar
    	}
       	else
       		proCHabilitaDesabilitaPerfil( idPerfilXML, idUser, 1 );
    }//if( status == 1 )
    else
    {
    	//Não há restrição para desabilitar
		proCHabilitaDesabilitaPerfil( idPerfilXML, idUser, 0 );
    }//else if( status == 1 )
        
   	saida->addItem( "mensagem","Operação realizada com sucesso" );
   	saida->addItem( "urlDestino","" );
    saida->closeTag();
    return 0;
}

void cInclPerfil::InsereNomePerfil( void )
{
    bool retorno = false;
    unsigned long idPerfilPrm = 0;
    
    retorno =  proCInsereNomePerfil( idUser, sNomePerfil, &idPerfilPrm );
    if ( retorno == true )
    {
        saida->createTag( "PerfilVariaveisVO" );
        saida->addProp( "xmlns","admsistemas.fo.vivo.com.br/vo" );
        saida->addProp( "xmlns:ns2","cliente.fo.vivo.com.br/vo" );
        saida->addProp( "xmlns:ns3","workflow.fo.vivo.com.br/vo" );
        saida->createTag( "Perfil" );
            saida->addItem( "idPerfil",idPerfilPrm );
            saida->addItem( "nmPerfil",sNomePerfil );
            saida->addItem( "associado",0 );
            saida->addItem( "habilitado",1 );
        saida->closeTag();
    }

}

int cInclPerfil::AlteraNomePerfil( void )
{
    int retorno = -1;

    retorno = proCAlteraNomePerfil( idUser, sNomePerfil, idPerfilXML );
    if ( retorno == 0 )
    {
        
        bool TemAssociados = proCVerificaPerfilRelacionado(idPerfilXML);
        bool PerfilHabilitado = proCVerificaPerfilHabilitado(idPerfilXML);
                       
        saida->createTag( "PerfilVariaveisVO" );
        saida->addProp( "xmlns","admsistemas.fo.vivo.com.br/vo" );
        saida->addProp( "xmlns:ns2","cliente.fo.vivo.com.br/vo" );
        saida->addProp( "xmlns:ns3","workflow.fo.vivo.com.br/vo" );
        saida->createTag( "Perfil" );
        saida->addItem( "idPerfil",idPerfilXML );
        saida->addItem( "nmPerfil",sNomePerfil );
        
        if (PerfilHabilitado)
        {
                if (TemAssociados)
                      saida->addItem( "associado",1 );
                else
        		      saida->addItem( "associado",0 );
                
                saida->addItem( "habilitado",1 );
        }
        else
        {
                saida->addItem( "associado",0 );
        		saida->addItem( "habilitado",0 );   
        }

        saida->closeTag();
        return retorno;
    }
    else
        return retorno;

}

void cInclPerfil::CarregaPerfil( void )
{

    proCCarregaPerfil
        ( 
        &idTpLinhaXML,
        &idSegmentacaoXML,
        &idCarteiraXML,
        &idProcedenciaXML,
        &idNaturezaXML,
		&idGrupoXML,
        &idTipoClienteXML,
        &idCanalXML,
        &idUFOperadoraXML,
        &pDados
        );

}

int cInclPerfil::ValidaNomePerfil( void )
{

    // Valida Nome de Perfil
    if ( sNomePerfil[0] != 0x0 )
    {
        if ( proCValidaNomePerfil( idPerfilXML,sNomePerfil ) )
        {
            return 1;   // Existe mesmo nome cadastrado no perfil
        }
        else
        {
            return 0;
        }
    }
    else
    {
        return 0;  //  VERIFICAR SE O NOME DO PERFIL VIRA VAZIO...
    }

}

short cInclPerfil::ValidaPerfil( void )
{
    int i = 0;
    unsigned long idPerfil;
    int TotalPerfil;
    int temDiferenca = 0;
    int diferencas = 0;
    short existe = 0;
    bool retorno;
    bool  insereTpLinha     = false;
    bool  insereSegmentacao = false;
    bool  insereTpCarteira  = false;
    bool  insereProcedencia = false;
    bool  insereNatureza    = false;
    bool  insereTpCliente   = false;
    bool  insereGrupo       = false;
    bool  insereCanal       = false;
    bool  insereRegional    = false;

    int iguais = 0;
    TotalPerfil = pDados.size();
    for ( i=0;i < TotalPerfil;i++ )
    {
        idPerfil = pDados.at(i);

        if ( temDiferenca > 0 )
        {
            temDiferenca = 0;
            diferencas++;
        }
        else
        {
            if ( diferencas > 0 )
            {
                diferencas--;
            }
        }

        // Valida Tipo Linha
        pDadosVar.clear();
        proCCarregaTipoLinha( idPerfil,&pDadosVar );
        
        sort( pDadosVar.begin(),pDadosVar.end() );

        retorno = Diferencas( &idTpLinhaXML,&pDadosVar );
        if ( retorno == true )
        {
            temDiferenca++;
            insereTpLinha = true;
        }
        else
        {
            insereTpLinha = false;
        }


        // Valida Segmentacao
        pDadosVar.clear();
        proCCarregaSegmentacao( idPerfil,&pDadosVar );
        retorno = Diferencas( &idSegmentacaoXML,&pDadosVar );
        if ( retorno == true )
        {
            temDiferenca++;
            insereSegmentacao = true;
        }
        else
        {
            insereSegmentacao = false;
        }

    
        // Valida Carteira
        pDadosVar.clear();
        proCCarregaCarteira( idPerfil,&pDadosVar );
        retorno = Diferencas( &idCarteiraXML,&pDadosVar );
        if ( retorno == true )
        {
            temDiferenca++;
            insereTpCarteira = true;
        }
        else
        {
            insereTpCarteira = false;
        }
    
        // Valida Procedencia
        pDadosVar.clear();
        proCCarregaProcedencia( idPerfil,&pDadosVar );
        retorno = Diferencas( &idProcedenciaXML,&pDadosVar );
        if ( retorno == true )
        {
            temDiferenca++;
            insereProcedencia = true;
        }
        else
        {
            insereProcedencia = false;
        }
    
        // Valida Natureza
        pDadosVar.clear();
        proCCarregaNatureza( idPerfil,&pDadosVar );
        retorno = Diferencas( &idNaturezaXML,&pDadosVar );
        if ( retorno == true )
        {
            temDiferenca++;
            insereNatureza = true;
        }
        else
        {
            insereNatureza = false;
        }

    
        // Valida Grupo
        pDadosVar.clear();
        proCCarregaGrupo( idPerfil,&pDadosVar );
        retorno = Diferencas( &idGrupoXML,&pDadosVar );
        if ( retorno == true )
        {
            temDiferenca++;
           insereGrupo = true;
        }
        else
        {
            insereGrupo = false;
        }
		

        // Valida Tipo Cliente
        pDadosVar.clear();
        proCCarregaTipoCliente( idPerfil,&pDadosVar );
        retorno = Diferencas( &idTipoClienteXML,&pDadosVar );
        if ( retorno == true )
        {
            temDiferenca++;
            insereTpCliente = true;
        }
        else
        {
            insereTpCliente = false;
        }
        
        // Valida Canal
        pDadosVar.clear();
        proCCarregaCanal( idPerfil,&pDadosVar );
        retorno = Diferencas( &idCanalXML,&pDadosVar );
        if ( retorno == true )
        {
            temDiferenca++;
            insereCanal = true;
        }
        else
        {
            insereCanal = false;
        }
    
        // Valida Regional
        pDadosVar.clear();
        proCCarregaRegional( idPerfil,&pDadosVar );
        retorno = Diferencas( &idUFOperadoraXML,&pDadosVar );
        if ( retorno == true )
        {
            temDiferenca++;
            insereRegional = true;
        }
        else
        {
            insereRegional = false;
        }

        if (    
                insereTpLinha     == false &&
                insereSegmentacao == false &&
                insereTpCarteira  == false &&
                insereProcedencia == false &&
                insereNatureza    == false &&
                insereGrupo       == false &&
                insereTpCliente   == false &&
                insereCanal       == false &&
                insereRegional    == false
           )
        {
            if ( idPerfilXML != idPerfil )
            {
                return 2;
            }
            else
            {
                return 3;
            }
            iguais++;
        }
    
    
    }

    if ( iguais == TotalPerfil )
    {
        if ( iguais > 0 )
        {
            return 2;
        }
        else
        {
            if ( idPerfilXML == 0 && inOperacao == 1 )
            {
                return 0;
            }
            else
            {
                if ( inOperacao == 2 )
                {
                    return 0;
                }
                else
                {
                    return 3;
                }
            }
        }
    }
    else
    {
        if ( idPerfilXML == 0 && inOperacao == 1 )
        {
            return 0;
        }
        else
        {
            if ( inOperacao == 2 )
            {
                return 0;
            }
            else
            {
                return 3;
            }
        }
    }

}

bool cInclPerfil::Diferencas( VETOR_IDS * idXML,VETOR_IDS * idBDados )
{
    int idx;
    int ct;
    bool igual;

    int tamxml = idXML->size();
    int tamvar = idBDados->size();

    if ( idXML->size() != idBDados->size() )
    {
        return true;
    }

    for ( idx=0; idx < idXML->size(); idx++ )
    {
        for ( ct=0; ct < idBDados->size(); ct++ )
        {
            if ( idBDados->at(idx) == idXML->at(ct) )
            {
                igual = true;
                break;
            }
        }
        if ( igual == true)
        {
            igual = false;
            continue;
        }
        else
        {
           return true;
        }
    }
    return false;

}

void cInclPerfil::carregaDados( void )
{
    DOMNode       * pDOM;
    TuxHelperImpl   txhlpIMPL;
    int             i;
    int             iRec = 0;
	char          * p;
    
    p = tx.walkTree( entrada,"idPerfil",0 );
    if ( p != NULL )
    {
        idPerfilXML = strtoul( p,0,10 );
        XMLString::release(&p);
    }

    p = tx.walkTree( entrada,"status",0 );
    if ( p != NULL )
    {
        status = strtoul( p,0,10 );
        XMLString::release(&p);
    }

    p = tx.walkTree( entrada,"nmPerfil",0 );
    if ( p != NULL )
    {
        strcpy( sNomePerfil,p );
        XMLString::release(&p);
    }

    p = tx.walkTree( entrada,"inOperacao",0 );
    if ( p != NULL )
    {
        inOperacao = atoi( p );
        XMLString::release(&p);
    }

    /*  Tipo Linha */
    iRec = 0;
    pDOM = txhlpIMPL.walkDOMImpl( entrada,"TipoLinhaVO",&iRec,0 );
    for ( i=0;;i++ )  
    {
        p = tx.walkTree( pDOM,"id",i );
        if( p == NULL )
            break;

        idTpLinhaXML.push_back( strtoul( p,0,10 ) );        
        XMLString::release(&p);
    }

    /*  Segmentacao */
    iRec = 0;
    pDOM = txhlpIMPL.walkDOMImpl( entrada,"SegmentacaoVO",&iRec,0 );
    for ( i=0;;i++ )  
    {
        p = tx.walkTree( pDOM,"idSegmentacao",i );
        if( p == NULL )
            break;

        idSegmentacaoXML.push_back( strtoul( p,0,10 ) );
        XMLString::release(&p);
    }

    /*  Tipo Carteira */
    iRec = 0;
    pDOM = txhlpIMPL.walkDOMImpl( entrada,"CarterizacaoVO",&iRec,0 );
    for ( i=0;;i++ )  
    {
        p = tx.walkTree( pDOM,"idTipoCarteira",i );
        if( p == NULL )
            break;
        
        idCarteiraXML.push_back( strtoul( p,0,10 ) );
        XMLString::release(&p);
    }

    /*  Procedencia */
    iRec = 0;
    pDOM = txhlpIMPL.walkDOMImpl( entrada,"ProcedenciaVO",&iRec,0 );
    for ( i=0;;i++ )  
    {
        p = tx.walkTree( pDOM,"idProcedencia",i );
        if( p == NULL )
            break;
        
        idProcedenciaXML.push_back( strtoul( p,0,10 ) );
        XMLString::release(&p);
    }

    /*  Natureza */
    iRec = 0;
    pDOM = txhlpIMPL.walkDOMImpl( entrada,"AdmNaturezaVO",&iRec,0 );
    for ( i=0;;i++ )  
    {
        p = tx.walkTree( pDOM,"idNatureza",i );
        if( p == NULL )
            break;
        
        idNaturezaXML.push_back( strtoul( p,0,10 ) );
        XMLString::release(&p);
    }

    /*  Grupo */
    iRec = 0;
    pDOM = txhlpIMPL.walkDOMImpl( entrada,"AdmGrupoAberturaVO",&iRec,0 );
    for ( i=0;;i++ )  
    {
        p = tx.walkTree( pDOM,"idGrupo",i );
        if( p == NULL )
            break;
        
        idGrupoXML.push_back( strtoul( p,0,10 ) );
        XMLString::release(&p);
    }

    /*  Tipo Cliente */
    iRec = 0;
    pDOM = txhlpIMPL.walkDOMImpl( entrada,"TipoClienteVO",&iRec,0 );
    for ( i=0;;i++ )  
    {
        p = tx.walkTree( pDOM,"codigo",i );
        if( p == NULL )
            break;

        idTipoClienteXML.push_back( strtoul( p,0,10 ) );
        XMLString::release(&p);
    }


    /*  Canal */
    iRec = 0;
    pDOM = txhlpIMPL.walkDOMImpl( entrada,"CanalVO",&iRec,0 );
    for ( i=0;;i++ )  
    {
        p = tx.walkTree( pDOM,"idCanal",i );
        if( p == NULL )
            break;
        
        idCanalXML.push_back( strtoul( p,0,10 ) );
        XMLString::release(&p);
    }

    /*  Regional */
    iRec = 0;
    pDOM = txhlpIMPL.walkDOMImpl( entrada,"AdmUFOperadoraVO",&iRec,0 );
    for ( i=0;;i++ )  
    {
        p = tx.walkTree( pDOM,"idUFOperadora",i );
        if( p == NULL )
            break;
        
        idUFOperadoraXML.push_back( strtoul( p,0,10 ) );
        XMLString::release(&p);
    }

}

int cInclPerfil::TotalVariaveisXMLIN( void )
{
	return (idTpLinhaXML.size()+idSegmentacaoXML.size()+idCarteiraXML.size()+idProcedenciaXML.size()+idNaturezaXML.size()+idGrupoXML.size()+idTipoClienteXML.size()+idCanalXML.size()+idUFOperadoraXML.size());

}