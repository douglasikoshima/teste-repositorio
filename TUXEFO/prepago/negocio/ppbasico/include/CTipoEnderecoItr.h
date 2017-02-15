#ifndef CTipoEnderecoItrH
#define CTipoEnderecoItrH

#include <stdlib.h>
#include <string.h>


struct STTipoEnderecoRegistro
{
	char cidTipoEndereco[21+1];
	char csgTipoEndereco[255+1];
	char cdsTipoEndereco[255+1];
};

class CTipoEnderecoItr
{
public:
	CTipoEnderecoItr();
	~CTipoEnderecoItr();
	int Primeiro( void );
	int Ultimo( void );
	int Quantidade( void );
	STTipoEnderecoRegistro* Registro( void );
	STTipoEnderecoRegistro* Registro(int nPosicao);

private:
	STTipoEnderecoRegistro* stcrTipoEndereco;
	int _iQuantidade;
	int _iPosicao;

protected:

	void Add( 
			char* cidTipoEndereco,
			char* csgTipoEndereco,
			char* cdsTipoEndereco );

	void ZeraTipoEndereco( void );

};

#endif
