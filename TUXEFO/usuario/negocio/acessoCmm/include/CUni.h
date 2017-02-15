/*****************************************************************************
 *
 * Modulo:    CUni
 * Arquivo:   CUni.h
 * Proposito: servicos Pro*C++ para persistencia de banco de dados
 * Historico:
 * Data        Autor                 Descricao
 * ----------  --------------------  --------------------------------
 * 18/05/2004  C_RECOliveira         Criacao
 * 18/05/2004  C_EDMartins           Criacao
 *
 ****************************************************************************/
#ifndef CUniH

/*****************************************************************************
 * Definicao Global
 ****************************************************************************/
#define CUniH

/*****************************************************************************
 * Header
 ****************************************************************************/
#include<tuxfw.h>
//#include<XMLImpl.h>
#define strlennull( y ) ( y == NULL ? 0 : strlen( y )  )

/*****************************************************************************
 * Classe
 ****************************************************************************/
class CUni
{
	public:
		CUni(){};
		~CUni(){};
		char* RTrim(char *pszString);
		long UniListaId(DOMNode*dnode, XMLGen*xml_g);
		long UniListaIdUni(DOMNode*dnode, XMLGen*xml_g, char* cidUni);
		int UniRemover(DOMNode*dnode, XMLGen*xml_g, char* cLogUser);
		int UniEditar(DOMNode*dnode, XMLGen*xml_g, char* cLogUser);
		int UniLista(DOMNode*dnode, XMLGen*xml_g);
		int UniInserir(DOMNode*dnode, XMLGen*xml_g, char* cLogUser);
		int UniListaPar(DOMNode*dnode, XMLGen*xml_g);

};

#endif
