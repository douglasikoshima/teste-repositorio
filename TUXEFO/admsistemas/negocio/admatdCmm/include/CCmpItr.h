#ifndef CCampoItrH
#define CCampoItrH

#include <stdlib.h>
#include <string.h>


struct STCampoRegistro
{
	char cidCampo[21+1];
	char cnmCampo[2000+1];
	char cidTipoDadoCampo[21+1];
	char csgTipoDadoCampo[255+1];
	char cnmTipoDadoCampo[255+1];
	char cinDominio[255+1];
	char cidMascaraApresentacaoCampo[21+1];
	char cnmMascaraApresentacaoCampo[255+1];
	char csgMascaraApresentacaoCampo[255+1];
	char cidLayoutApresentacaoCampo[21+1];
	char cnmLayoutApresentacaoCampo[255+1];
	char csgLayoutApresentacaoCampo[255+1];
	char cnrTamanho[21+1];
	char cinDisponibilidade[21+1];
	char cidClassificadorCampo[21+1];
	char cnmClassificadorCampo[255+1];
	char cinFiltro[21+1];
	char cinPesquisa[21+1];
	char cinObrigatorio[21+1];
	char ctemDominio[21+1];
};

class CCampoItr
{
public:
	CCampoItr();
	~CCampoItr();
	int Primeiro( void );
	int Proximo( void );
	int Anterior( void );
	int Ultimo( void );
	int Quantidade( void );
	STCampoRegistro* Registro( void );
	STCampoRegistro* Registro(int nPosicao);

private:
	STCampoRegistro* stcrCampo;
	int _iQuantidade;
	int _iPosicao;

protected:

	void Add( 
		char* cidCampo,
		char* cnmCampo,
		char* cidTipoDadoCampo,
		char* cidMascaraApresentacaoCampo,
		char* cidLayoutApresentacaoCampo,
		char* cnrTamanho,
		char* cinDisponibilidade,
		char* cidClassificadorCampo,
		char* cnmClassificadorCampo,
		char* cinFiltro 
	);

	void Add( 
		char* cidCampo,
		char* cnmCampo,
		char* cidTipoDadoCampo,
		char* cidMascaraApresentacaoCampo,
		char* cidLayoutApresentacaoCampo,
		char* cnrTamanho,
		char* cinDisponibilidade,
		char* cidClassificadorCampo,
		char* cnmClassificadorCampo,
		char* cinFiltro,
		char* cinPesquisa,
		char* cinObrigatorio
	);

	void Add( 
		char* cidCampo,
		char* cnmCampo,
		char* cidTipoDadoCampo,
		char* csgTipoDadoCampo,
		char* cnmTipoDadoCampo,
		char* cinDominio,
		char* cidMascaraApresentacaoCampo,
		char* cnmMascaraApresentacaoCampo,
		char* csgMascaraApresentacaoCampo,
		char* cidLayoutApresentacaoCampo,
		char* cnmLayoutApresentacaoCampo,
		char* csgLayoutApresentacaoCampo,
		char* cnrTamanho,
		char* cinDisponibilidade,
		char* cidClassificadorCampo,
		char* cnmClassificadorCampo,
		char* cinFiltro,
		char* cinPesquisa,
		char* cinObrigatorio,
		char* ctemDominio
	);

	void ZeraCampo( void );

};

#endif
