/*****************************************************************************
 *
 * Modulo:    CSrv
 * Arquivo:   CSrv.h
 * Proposito: servicos Pro*C++ para persistencia de banco de dados
 * Historico:
 * Data        Autor                 Descricao
 * ----------  --------------------  --------------------------------
 * 18/05/2004  C_RECOliveira         Criacao
 * 18/05/2004  C_EDMartins           Criacao
 *
 ****************************************************************************/
#ifndef CSrvH

/*****************************************************************************
 * Definicao Global
 ****************************************************************************/
#define CSrvH

/*****************************************************************************
 * Header
 ****************************************************************************/
#include<tuxfw.h>
#define strlennull( y ) ( y == NULL ? 0 : strlen( y )  )

/*****************************************************************************
 * Classe
 ****************************************************************************/
class CSrv
{
	private:
		char cidHost[21+1];
		char cdsHost[255+1];
	public:
		CSrv(){};
		~CSrv(){};
		char* RTrim(char *pszString);
		int SrvInserir(DOMNode*dnode, XMLGen*xml_g, char* cLogUser);
		int SrvListaPar(DOMNode*dnode, XMLGen*xml_g);
		int SrvRemover(DOMNode*dnode, XMLGen*xml_g, char* cLogUser);
		int SrvLista(char* cdsServidor, XMLGen*xml_g);
		int SrvEditar(DOMNode*dnode, XMLGen*xml_g, char* cLogUser);
		int SrvListaId(DOMNode*dnode, XMLGen*xml_g);
		int SrvListaId(char* cidHost, XMLGen*xml_g);
		char* getIdHost() { return cidHost; };
		char* getDsHost() { return cdsHost; };
		void setIdHost( char* pzcidHost );
		void setDsHost( char* pzcdsHost );
};

#endif
