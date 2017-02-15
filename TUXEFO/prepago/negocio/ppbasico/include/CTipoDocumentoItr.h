#ifndef CTipoDocumentoItrH
#define CTipoDocumentoItrH

#include <stdlib.h>
#include <string.h>

struct STTipoDocumentoRegistro
{
	char cidTipoDocumento[21+1];
	char csgTipoDocumento[255+1];
	char cdsTipoDocumento[255+1];
	char cidTipoPessoa[21+1];
	char cnrPrioridade[21+1];
	char csgClassificacao[255+1];
};

class CTipoDocumentoItr
{
public:
	CTipoDocumentoItr();
	~CTipoDocumentoItr();
	int Primeiro( void );
	int Ultimo( void );
	int Quantidade( void );
	STTipoDocumentoRegistro* Registro( void );
	STTipoDocumentoRegistro* Registro(int nPosicao);

private:
	STTipoDocumentoRegistro* stcrTipoDocumento;
	int _iQuantidade;
	int _iPosicao;

protected:
	void Add( 
				char* cidTipoDocumento,
				char* csgTipoDocumento,
				char* cdsTipoDocumento,
				char* cidTipoPessoa,
				char* cnrPrioridade
			);
	void Add( 
				char* cidTipoDocumento,
				char* csgTipoDocumento,
				char* cdsTipoDocumento,
				char* cidTipoPessoa,
				char* cnrPrioridade,
				char* csgClassificacao
			);
	void ZeraTipoDocumento( void );

};

#endif