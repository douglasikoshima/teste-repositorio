/*****************************************************************************
 *
 * Modulo:    CPgn
 * Arquivo:   CPgn.h
 * Proposito: servicos Pro*C++ para persistencia de banco de dados
 * Historico:
 * Data        Autor                 Descricao
 * ----------  --------------------  --------------------------------
 * 18/05/2004  C_RECOliveira         Criacao
 * 18/05/2004  C_EDMartins           Criacao
 *
 ****************************************************************************/
#ifndef CPgnH

/*****************************************************************************
 * Definicao Global
 ****************************************************************************/
#define CPgnH

/*****************************************************************************
 * Header
 ****************************************************************************/
#include<tuxfw.h>
#define strlennull( y ) ( y == NULL ? 0 : strlen( y )  )

#include "CPgnItr.h"

/*****************************************************************************
 * Classe
 ****************************************************************************/
class CPgn : public CPgnItr
{
	public:
		CPgn(){};
		~CPgn(){};
		char* RTrim(char *pszString);
		int PgnInserir(DOMNode*dnode, XMLGen*xml_g, char* cLogUser);
		int PgnEditar(DOMNode*dnode, XMLGen*xml_g, char* cLogUser);
		int PgnLista(DOMNode*dnode, XMLGen*xml_g);
		int PgnUniRelacao(DOMNode*dnode, XMLGen*xml_g);
		int PgnListaId(DOMNode*dnode, XMLGen*xml_g);
		int PgnListaPar(DOMNode*dnode, XMLGen*xml_g);
		int PgnUniRelaciona(DOMNode*dnode, XMLGen*xml_g, char* cLogUser);
		int PgnRemover(DOMNode*dnode, XMLGen*xml_g, char* cLogUser);
/*****************************************************************************
 * Metodos do Eder (Serviços complexos)
 ****************************************************************************/
		int ListId( char* cid );
		int ListIdSub( char* cid );
		int ListIdItemMenu( char* cidItemMenu );
		int ListAll( void );
		int ListPar( char* cnmPagina
			        ,char* cnmUrl
					,char* cinDisponib
					,char* cidSubSistema
                    ,char* cidSistema );
		void GetXml( char* cNomeTag, XMLGen*xml );
};

#endif
