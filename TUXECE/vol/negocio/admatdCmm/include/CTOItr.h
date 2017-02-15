#ifndef CTipoOrganizacaoItrH
#define CTipoOrganizacaoItrH

#include <stdlib.h>
#include <string.h>

struct STTipoOrganizacaoRegistro
{
	char cidTipoOrganizacao[21+1];
	char cdsTipoOrganizacao[255+1];
};

class CTipoOrganizacaoItr
{
public:
	CTipoOrganizacaoItr();
	~CTipoOrganizacaoItr();
	int Primeiro( void );
	int Proximo( void );
	int Anterior( void );
	int Ultimo( void );
	int Quantidade( void );
	STTipoOrganizacaoRegistro* Registro( void );
	STTipoOrganizacaoRegistro* Registro(int nPosicao);

private:
	STTipoOrganizacaoRegistro* stcrTipoOrganizacao;
	int _iQuantidade;
	int _iPosicao;

protected:

	void Add( 
			char* cidTipoOrganizacao,
			char* cdsTipoOrganizacao );

	void ZeraTipoOrganizacao( void );

};

#endif
