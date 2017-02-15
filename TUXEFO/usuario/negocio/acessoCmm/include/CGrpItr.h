#ifndef CGrupoItrH
#define CGrupoItrH

#include <stdlib.h>
#include <string.h>
#include<tuxfw.h>


struct STGrupoRegistro
{
	char cidUsuarioGrupo[21+1];
	char cidPessoaUsuario[21+1];
	char cidGrupo[21+1];
	char cnmGrupo[255+1];
	char cinSupervisor[21+1];
	char cidTIPOGRUPO[21+1];
	char cdsTIPOGRUPO[256];
};

class CGrupoItr
{
public:
	CGrupoItr();
	~CGrupoItr();
	int Primeiro( void );
	int Proximo( void );
	int Anterior( void );
	int Ultimo( void );
	int Quantidade( void );
	STGrupoRegistro* Registro( void );
	STGrupoRegistro* Registro(int nPosicao);

private:
	STGrupoRegistro* stcrGrupo;
	int _iQuantidade;
	int _iPosicao;

protected:

	void Add( 
			  char* cidUsuarioGrupo
			 ,char* cidPessoaUsuario
		     ,char* cidGrupo
			 ,char* cinSupervisor );

	void Add( 
			  char* cidUsuarioGrupo
			 ,char* cidPessoaUsuario
		     ,char* cidGrupo
			 ,char* cinSupervisor
			 ,char* cdsTIPOGRUPO );

	void Add( 
			  char* cidGrupo
		     ,char* cnmGrupo );

	void Add( 
			  char* cidGrupo
		     ,char* cnmGrupo
			 ,char* cidTIPOGRUPO );

	void Add( 
			  char* cidGrupo
		     ,char* cnmGrupo
			 ,char* cdsTIPOGRUPO 
             ,char* cidTIPOGRUPO 
			 ,int   iTipo );


	void ZeraGrupo( void );

};

#endif
