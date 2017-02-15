/*****************************************************************************
 *
 * Modulo:    CImn
 * Arquivo:   CImn.h
 * Proposito: servicos Pro*C++ para persistencia de banco de dados
 * Historico:
 * Data        Autor                 Descricao
 * ----------  --------------------  --------------------------------
 * 18/05/2004  C_RECOliveira         Criacao
 * 18/05/2004  C_EDMartins           Criacao
 *
 ****************************************************************************/
#ifndef CImnH

/*****************************************************************************
 * Definicao Global
 ****************************************************************************/
#define CImnH

/*****************************************************************************
 * Header
 ****************************************************************************/
#include<tuxfw.h>
#define strlennull( y ) ( y == NULL ? 0 : strlen( y )  )

#include "CImnItr.h"


/*****************************************************************************
 * Classe
 ****************************************************************************/
class CImn : public CImnItr
{
	public:
		CImn();
		~CImn();
		char* RTrim(char *pszString);
		int ImnEditar(DOMNode*dnode, XMLGen*xml_g, char* cLogUser);
		int ImnInserir(DOMNode*dnode, XMLGen*xml_g, char* cLogUser);
		int ImnMover(char* cidItemMenu, int inMoveUp, char* cLogUser);
		int ImnRemover(DOMNode*dnode, XMLGen*xml_g, char* cLogUser);
		int ImnLista(DOMNode*dnode, XMLGen*xml_g);
		int ImnListaId(DOMNode*dnode, XMLGen*xml_g);
		int ImnListaPar(DOMNode*dnode, XMLGen*xml_g);
		int ImnPgnRelacao(DOMNode*dnode, XMLGen*xml_g);
		int ImnPgnRelaciona(DOMNode*dnode, XMLGen*xml_g, char* cLogUser);
/*****************************************************************************
 * Metodos do Eder (Serviços complexos)
 ****************************************************************************/
		int  ListId( char* cidsubsistema );
		void AcertaPath( char* cidItemMenu, char* cidUser );
		int  ListAll( void );
		int  ListUser( char* cidsubsistema, char* cidusuario );
		int  ListUserMenu( char* cidsubsistema, char* cidusuario );
		int  ListUserMenuRapida( char* cidsubsistema, char* cidusuario );
		void GetXml( char* cNomeTag, XMLGen*xml );
		int  FindMenu( char* cIdItemMenu );
		int  MontaMenu( CImn& oItemMenu );
		void Path( char* cidItemMenu, char* cidItemMenuRaiz );
		char* ItemMenuRaiz( char* cidSubSistema );

		void  setRaiz( char* cidItemMenuRaiz );
		char* getRaiz( void );

		void  setPathNivel( char* cPath, char* cNivel );
		char* getPath( void );
		char* getNivel( void );
		
	private:
		int iListAll;
		char _cPath[2000+1];
		char _cNivel[21+1];
		char _cidItemMenuRaiz[21+1];
};

#endif
