#ifndef CTipDocItrH
#define CTipDocItrH

#include <stdlib.h>
#include <string.h>
#include<tuxfw.h>

struct STTipoDocumentoRegistro
{
	char cidTipoDocumento[21+1];
	char csgTipoDocumento[255+1];
	char cdsTipoDocumento[255+1];
	char cidTipoPessoa[21+1];
};

class CTipDocItr
{
public:
	CTipDocItr();
	~CTipDocItr();
	int Primeiro( void );
	int Proximo( void );
	int Anterior( void );
	int Ultimo( void );
	int Quantidade( void );
	STTipoDocumentoRegistro* Registro( void );
	STTipoDocumentoRegistro* Registro(int nPosicao);

private:
	STTipoDocumentoRegistro* stcrTipoDocumento;
	int _iQuantidade;
	int _iPosicao;

protected:
	void Add( char* cid, char* csigla, char* cdescricao, char* cidtipopessoa );
	void ZeraTipoDocumento( void );

};

#endif