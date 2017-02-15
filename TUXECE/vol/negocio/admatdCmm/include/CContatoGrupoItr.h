#ifndef CContatoGrupoItrH
#define CContatoGrupoItrH

#include <stdlib.h>
#include <string.h>


struct STContatoGrupoRegistro
{
	char cidContatoGrupo[21+1];
	char cidContato[21+1];
	char cidGrupo[21+1];
};

class CContatoGrupoItr
{
public:
	CContatoGrupoItr();
	~CContatoGrupoItr();
	int Primeiro( void );
	int Proximo( void );
	int Anterior( void );
	int Ultimo( void );
	int Quantidade( void );
	STContatoGrupoRegistro* Registro( void );
	STContatoGrupoRegistro* Registro(int nPosicao);

private:
	STContatoGrupoRegistro* stcrContatoGrupo;
	int _iQuantidade;
	int _iPosicao;

protected:

	void Add( 
			char* cidContatoGrupo,
			char* cidContato,
			char* cidGrupo );

	void ZeraContatoGrupo( void );

};

#endif
