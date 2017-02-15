///////////////////////////////////////////////////////////////////////////////
/**
 * @modulo  Sincronismo
 * @usecase LinhaBase
 * @author  Renato Striitzel Russo
 * @author  Carlos Eduardo Barbosa Braga
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:17 $
 **/
///////////////////////////////////////////////////////////////////////////////

#include <string.h>
#include <stdlib.h>
#include "../include/LinhaBase.h"
#include "../include/TString.h"

CLinhaBase::CLinhaBase( void )
{
    memset(&tLinhaBase, 0x00, sizeof(TLinhaBase));
}

CLinhaBase::CLinhaBase(char* pszNrLinha)
{
    memset(&tLinhaBase, 0x00, sizeof(TLinhaBase));
    strcpy(tLinhaBase.szNrLinha, pszNrLinha);
    strcpy(tLinhaBase.szIdAreaRegistro, "0");
}

void CLinhaBase::setIdLinhaBase( char *pszIdLinhaBase )
{
    strcpy( tLinhaBase.szIdLinhaBase, pszIdLinhaBase );
}

void CLinhaBase::setNrLinha( char *pszNrLinha )
{
    strcpy( tLinhaBase.szNrLinha, pszNrLinha );
}

TString CLinhaBase::getNrLinha()
{
	TString ret = tLinhaBase.szNrLinha; 
    return ret.Trim(); 
}


void CLinhaBase::setIdAreaRegistro( char *pszIdAreaRegistro )
{
    strcpy( tLinhaBase.szIdAreaRegistro, pszIdAreaRegistro );
}

char *CLinhaBase::getIdLinhaBase(void)
{
    static char szAux[LEN_IDLINHABASE + LEN_EOS];

    strcpy(szAux, tLinhaBase.szIdLinhaBase);
    return szAux;
}

bool CLinhaBase::buscaLinhaBase(void)
{
    return clLinhaBasepc.proCBuscaLinhaBase(&tLinhaBase);
}

void CLinhaBase::atualizaLinhaBase(void)
{
    clLinhaBasepc.proCAtualizaLinhaBase(&tLinhaBase);
}

void CLinhaBase::insereLinhaBase(void)
{
	memset( tLinhaBase.szIdLinhaBase, 0, sizeof( tLinhaBase.szIdLinhaBase )); 
    clLinhaBasepc.proCInsereLinhaBase(&tLinhaBase);
}


void CLinhaBase::BuscaAreaServico(TString DDD)
{
	if(clLinhaBasepc.proCBuscaAreaServico(&tLinhaBase, DDD.c_str())==false) 
	     setIdAreaRegistro( "1" );
}


void CLinhaBase::setData( TPessoaJuridicaXML xm) 
{
	TString s, telefone, ddd ; 

	s = xm.nrLinha; 

	if (!s.IsEmpty())
	{
   	  telefone = s.SubString( 3, s.Length() - 2 ); 
	  BuscaAreaServico( s.SubString(1, 2) );
      setNrLinha( telefone.c_str() ); 
	}
	else
	setIdAreaRegistro( "1" );

	setIdLinhaBase( xm.idLinha ); 
}

void CLinhaBase::setData( TPessoaFisicaXML xm) 
{
	TString s, telefone, ddd ; 

	s = xm.nrLinha; 

	if (!s.IsEmpty())
	{
   	  telefone = s.SubString( 3, s.Length() - 2 ); 
	  BuscaAreaServico( s.SubString(1, 2) );
      setNrLinha( telefone.c_str() ); 
	}
	else
	setIdAreaRegistro( "1" );

	setIdLinhaBase( xm.idLinha ); 
}



void CLinhaBase::setData( TEnderecoXML xm) 
{
	TString s, telefone, ddd ; 

	s = xm.nrLinha; 

	if (!s.IsEmpty())
	{
   	  telefone = s.SubString( 3, s.Length() - 2 ); 
	  BuscaAreaServico( s.SubString(1, 2) );
      setNrLinha( telefone.c_str() ); 
	}
	else
	setIdAreaRegistro( "1" );

	setIdLinhaBase( xm.idLinha );  
}
