#ifndef CTipoGrupoItrH
#define CTipoGrupoItrH
#include<tuxfw.h>

#include <stdlib.h>
#include <string.h>

struct STTipoGrupoRegistro
{
	char cidTipoGrupo[21+1];
	char csgTipoGrupo[255+1];
	char cdsTipoGrupo[255+1];
};

class CTipoGrupoItr
{
public:
	CTipoGrupoItr();
	~CTipoGrupoItr();
	int Quantidade( void );
	STTipoGrupoRegistro* Registro( void );
	STTipoGrupoRegistro* Registro(int nPosicao);

private:
	STTipoGrupoRegistro* stcrTipoGrupo;
	int _iQuantidade;
	int _iPosicao;

protected:

	void Add( 
		 char* cidTipoGrupo
		,char* csgTipoGrupo
		,char* cdsTipoGrupo
	);
   void ZeraTipoGrupo( void );
};
#endif