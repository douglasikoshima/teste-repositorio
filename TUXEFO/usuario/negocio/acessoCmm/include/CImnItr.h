#ifndef CImnItrH
#define CImnItrH

#include <stdlib.h>
#include <string.h>
#include<tuxfw.h>

struct STItemMenuRegistro
{
	char cidItemMenu[21+1];
	char cnmItemMenu[255+1];
	char cdsHint[255+1];
	char csqApresentacao[255+1];
	char cinVisibilidade[255+1];
	char cidSubSistema[255+1];
	int  iLevel;
	char cidItemMenuPai[21+1];
	char cinFolha[21+1];
	int  isqApresentacao;
};

class CImnItr
{
public:
	CImnItr();
	~CImnItr();
	int Primeiro( void );
	int Proximo( void );
	int Anterior( void );
	int Ultimo( void );
	int Quantidade( void );
	STItemMenuRegistro* Registro( void );
	STItemMenuRegistro* Registro(int nPosicao);

private:
	STItemMenuRegistro* stcrItemMenu;
	int _iQuantidade;
	int _iPosicao;

protected:
	void Add( char* cid, 
	          char* cnome, 
	          char* chint, 
	          char* capresentacao, 
	          char* cvisibilidade, 
	          char* cidsubsistema, 
	          int   ilevel,
	          char* cpai,
			  char* cinFolha );
	void Add( char* cidItemMenu
		     ,int   isqApresentacao );
	void ZeraItemMenu( void );

};

#endif