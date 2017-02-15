#ifndef CSubCampanhaGrupoUsuarioItrH
#define CSubCampanhaGrupoUsuarioItrH

#include <stdlib.h>
#include <string.h>

struct STSubCampanhaGrupoUsuarioRegistro
{
	char cidSubCampanhaGrupoUsuario[21+1];
	char cidSubCampanhaFixa[21+1];
	char cidGrupo[21+1];
	char cinAtivo[21+1];
};

class CSubCampanhaGrupoUsuarioItr
{
public:
	CSubCampanhaGrupoUsuarioItr();
	~CSubCampanhaGrupoUsuarioItr();
	int Primeiro( void );
	int Proximo( void );
	int Anterior( void );
	int Ultimo( void );
	int Quantidade( void );
	STSubCampanhaGrupoUsuarioRegistro* Registro( void );
	STSubCampanhaGrupoUsuarioRegistro* Registro(int nPosicao);

private:
	STSubCampanhaGrupoUsuarioRegistro* stcrSubCampanhaGrupoUsuario;
	int _iQuantidade;
	int _iPosicao;

protected:

	void Add( char* cidSubCampanhaGrupoUsuario,
	          char* cidSubCampanhaFixa,
	          char* cidGrupo,
	          char* cinAtivo );

	void ZeraSubCampanhaGrupoUsuario( void );

};

#endif