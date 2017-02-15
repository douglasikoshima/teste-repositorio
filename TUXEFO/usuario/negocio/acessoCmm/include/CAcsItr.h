#ifndef CAcsItrH
#define CAcsItrH

#include <stdlib.h>
#include <string.h>
#include <tuxfw.h>

struct STAcessoUsuarioRegistro
{
	char cidAcessoUsuario[21+1];
	char cnmAcessoUsuario[255+1];
};

class CAcsItr
{
public:
	CAcsItr();
	~CAcsItr();
	int Primeiro( void );
	int Proximo( void );
	int Anterior( void );
	int Ultimo( void );
	int Quantidade( void );
	STAcessoUsuarioRegistro* Registro( void );
	STAcessoUsuarioRegistro* Registro(int nPosicao);

private:
	STAcessoUsuarioRegistro* stcrAcessoUsuario;
	int _iQuantidade;
	int _iPosicao;

protected:
	void Add( char* cid, char* cnome );
	void ZeraAcessoUsuario( void );

};

#endif