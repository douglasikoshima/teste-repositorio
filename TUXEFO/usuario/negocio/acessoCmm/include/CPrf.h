/*****************************************************************************
 *
 * Modulo:    CPrf
 * Arquivo:   CPrf.h
 * Proposito: servicos Pro*C++ para persistencia de banco de dados
 * Historico:
 * Data        Autor                 Descricao
 * ----------  --------------------  --------------------------------
 * 18/05/2004  C_RECOliveira         Criacao
 * 18/05/2004  C_EDMartins           Criacao
 *
 ****************************************************************************/
#ifndef CPrfH

/*****************************************************************************
 * Definicao Global
 ****************************************************************************/
#define CPrfH

/*****************************************************************************
 * Header
 ****************************************************************************/
#include<tuxfw.h>
#define strlennull( y ) ( y == NULL ? 0 : strlen( y )  )

/*****************************************************************************
 * Classe
 ****************************************************************************/
class CPrf
{
	public:
		CPrf(){};
		~CPrf(){};
		char* RTrim(char *pszString);
		int PrfGptRelaciona(DOMNode*dnode, XMLGen*xml_g, char* cLogUser);
		int PrfInserir(DOMNode*dnode, XMLGen*xml_g, char* cLogUser);
		int PrfGptRelacao(DOMNode*dnode, XMLGen*xml_g);
		int PrfListaPar(DOMNode*dnode, XMLGen*xml_g);
		int PrfEditar(DOMNode*dnode, XMLGen*xml_g, char* cLogUser);
		int PrfLista(DOMNode*dnode, XMLGen*xml_g);
		int PrfListaId(DOMNode*dnode, XMLGen*xml_g);
		int PrfUniRelacao(DOMNode*dnode, XMLGen*xml_g);
		int PrfUniRelaciona(DOMNode*dnode, XMLGen*xml_g, char* cLogUser);
		int PrfRemover(DOMNode*dnode, XMLGen*xml_g, char* cLogUser);

};

#endif
