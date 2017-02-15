///////////////////////////////////////////////////////////////////////////////
/**
 * @modulo  Sincronismo
 * @usecase LinhaBase
 * @author  Renato Striitzel Russo
 * @author  Carlos Eduardo Barbosa Braga
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:26 $
 **/
///////////////////////////////////////////////////////////////////////////////

#ifndef LINHABASE
#define LINHABASE

#include "LinhaBasepc.h"
#include "Global.h"
#include "TString.h"

class CLinhaBase
{
public:
	//------------------------------------------------------------------------------------
    TLinhaBase      tLinhaBase;
    CLinhaBasepc    clLinhaBasepc;
	//------------------------------------------------------------------------------------
    CLinhaBase( void );
    CLinhaBase( char *pszIdLinhaBase);
	//------------------------------------------------------------------------------------
    void setIdLinhaBase	  ( char *pszIdLinhaBase );
    void setNrLinha		  ( char *pszNrLinha );
    void setIdAreaRegistro( char *pszIdAreaRegistro );
    char *getIdLinhaBase  ( void );
	TString getNrLinha();
	//------------------------------------------------------------------------------------
    bool buscaLinhaBase	  ( void );
    void atualizaLinhaBase( void );
    void insereLinhaBase  ( void );
	void BuscaAreaServico ( TString );
	//------------------------------------------------------------------------------------
	void setData( TPessoaJuridicaXML );
	void setData( TPessoaFisicaXML );
	void setData( TEnderecoXML );
	//------------------------------------------------------------------------------------
};

#endif
