/*****************************************************************************
 *
 * Modulo:    CSub
 * Arquivo:   CSub.h
 * Proposito: servicos Pro*C++ para persistencia de banco de dados
 * Historico:
 * Data        Autor                 Descricao
 * ----------  --------------------  --------------------------------
 * 18/05/2004  C_RECOliveira         Criacao
 * 18/05/2004  C_EDMartins           Criacao
 *
 ****************************************************************************/
#ifndef CSubH

/*****************************************************************************
 * Definicao Global
 ****************************************************************************/
#define CSubH

/*****************************************************************************
 * Header
 ****************************************************************************/
#include <tuxfw.h>
#include "CSubItr.h"
#define strlennull( y ) ( y == NULL ? 0 : strlen( y )  )

/*****************************************************************************
 * Classe
 ****************************************************************************/
class CSub : public CSubItr
{
	public:
		CSub(){};
		~CSub(){};
		char* RTrim(char *pszString);
		int SubRemover(DOMNode*dnode, XMLGen*xml_g, char* cLogUser);
		int SubEditar(DOMNode*dnode, XMLGen*xml_g, char* cLogUser);
		int SubListaId(DOMNode*dnode, XMLGen*xml_g);
		int SubInserir(DOMNode*dnode, XMLGen*xml_g, char* cLogUser);
		int SubLista(DOMNode*dnode, XMLGen*xml_g);
		int SubListaPar(DOMNode*dnode, XMLGen*xml_g);
		int SubListaIdSis(DOMNode*dnode,XMLGen*xml_g);
		int SubListaTodos(DOMNode*dnode,XMLGen*xml_g);
		int SubListaTodos( char* cidSistema );

};

#endif
