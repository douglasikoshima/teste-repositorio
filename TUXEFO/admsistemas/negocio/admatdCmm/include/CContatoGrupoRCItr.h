#ifndef CContatoGrupoRCItrH
#define CContatoGrupoRCItrH

#include <stdlib.h>
#include <string.h>

struct STContatoGrupoRCRegistro
{
	char cidContatoGrupoRC[21+1];
	char cidContato[21+1];
	char cidGrupo[21+1];
    char cdsGrupo [255+1];
};



class CContatoGrupoRCItr
{
public:
	CContatoGrupoRCItr();
	~CContatoGrupoRCItr();
	int Quantidade( void );
	STContatoGrupoRCRegistro* Registro( void );
	STContatoGrupoRCRegistro* Registro(int nPosicao);

private:
	STContatoGrupoRCRegistro* stcrContatoGrupoRC;
	int _iQuantidade;
	int _iPosicao;

protected:

	void Add( 
		 char* cidContatoGrupoRC
		,char* cidContato
		,char* cidGrupo
	);

	void Add( 
		 char* cidContatoGrupoRC
		,char* cidContato
		,char* cidGrupo
		,char* cdsGrupo
	);
   void ZeraContatoGrupoRC( void );
};
#endif