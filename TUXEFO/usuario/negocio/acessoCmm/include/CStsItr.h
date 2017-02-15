#ifndef CStsItrH
#define CStsItrH

#include <stdlib.h>
#include <string.h>
#include<tuxfw.h>

struct STStatusUsuarioRegistro
{
	char cidStatusUsuario[21+1];
	char csgStatusUsuario[255+1];
	char cdsStatusUsuario[255+1];
	char cIndisponibilidade[255+1];
};

class CStsItr
{
public:
	CStsItr();
	~CStsItr();
	int Primeiro( void );
	int Proximo( void );
	int Anterior( void );
	int Ultimo( void );
	int Quantidade( void );
	STStatusUsuarioRegistro* Registro( void );
	STStatusUsuarioRegistro* Registro(int nPosicao);

private:
	STStatusUsuarioRegistro* stcrStatusUsuario;
	int _iQuantidade;
	int _iPosicao;

protected:
	void Add( char* cid, char* csigla, char* cdescricao, char* cindisponibilidade );
	void ZeraStatusUsuario( void );

};

#endif