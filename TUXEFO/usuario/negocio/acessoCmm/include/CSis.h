/*****************************************************************************
 *
 * Modulo:    CSis
 * Arquivo:   CSis.h
 * Proposito: servicos Pro*C++ para persistencia de banco de dados
 * Historico:
 * Data        Autor                 Descricao
 * ----------  --------------------  --------------------------------
 * 18/05/2004  C_RECOliveira         Criacao
 * 18/05/2004  C_EDMartins           Criacao
 *
 ****************************************************************************/
#ifndef CSisH

/*****************************************************************************
 * Definicao Global
 ****************************************************************************/
#define CSisH

/*****************************************************************************
 * Header
 ****************************************************************************/
#include<tuxfw.h>
#define strlennull( y ) ( y == NULL ? 0 : strlen( y )  )

/*****************************************************************************
 * Classe
 ****************************************************************************/
class CSis
{
	private:
		char pzcidSistema[21+1];
	public:
		CSis(){};
		~CSis(){};
		char* RTrim(char *pszString);
		int SisInserir(DOMNode*dnode, XMLGen*xml_g, char* cLogUser);
		int SisEditar(DOMNode*dnode, XMLGen*xml_g, char* cLogUser);
		int SisRemover(DOMNode*dnode, XMLGen*xml_g, char* cLogUser);
		int SisSrvRelaciona(DOMNode*dnode, XMLGen*xml_g, char* cLogUser);
		int SisListaId(DOMNode*dnode, XMLGen*xml_g);
		int SisListaId(char* cidSistema, XMLGen*xml_g);
		int SisLista(DOMNode*dnode, XMLGen*xml_g);
		int SisListaPar(DOMNode*dnode, XMLGen*xml_g);
		int SisSrvRelacao(DOMNode*dnode, XMLGen*xml_g);

		int SisListaSigla( char* csgSistema );
		void  setIdSistema( char* cidSistema );
		char* getIdSistema( void );

};

#endif
