#ifndef CListaCampanhaHistoricoItrH
#define CListaCampanhaHistoricoItrH

#include <stdlib.h>
#include <string.h>

struct STListaCampanhaHistoricoRegistro
{
	char cidCampanha[21+1];
	char cdsCampanha[256+1];
	char csgCampanha[256+1];
	char cidSubCampanhaHistorico[12+1];
	char cdsScriptSubCampanha[12+1];
	char cidCanal[21+1];
	char ccdCanal[21+1];
	char cnmCanal[21+1];
	char cidMotivoCampanha[256+1];
	char cidAtendimentoCampanha[256+1];
	char cdtAtendimento[256+1];
	char cqtTempoAtendimento[256+1];
	char cidUFOperadora[256+1];
	char cidPessoaDeParaOperadora[256+1];
	char cidTipoStatusCampanha[256+1];
	char cidTipoMotivoCampanha[256+1];
	char cidTipoSubMotivoCampanha[256+1];
	char cidSubCampanhaFixa[256+1];
	char cdsTipoSubMotivoCampanha[256+1];
	char csgTipoSubMotivoCampanha[256+1];
	char cdsTipoStatusCampanha[256+1];
	char csgTipoStatusCampanha[256+1];
	char cdsTipoMotivoCampanha[256+1];
	char csgTipoMotivoCampanha[256+1];
	char cidListaConteudo[256+1];
	char cidPessoaDePara[256+1];
	char cidCanalCampanha[256+1];
	char cidCanalUfOperadora[256+1];
	char csgUfOperadora[256+1];
	char cnmSubCampanha[255+1];
};

class CListaCampanhaHistoricoItr
{
public:
	CListaCampanhaHistoricoItr();
	~CListaCampanhaHistoricoItr();
	int Primeiro( void );
	int Proximo( void );
	int Anterior( void );
	int Ultimo( void );
	int Quantidade( void );
	STListaCampanhaHistoricoRegistro* Registro( void );
	STListaCampanhaHistoricoRegistro* Registro(int nPosicao);

private:
	STListaCampanhaHistoricoRegistro* stcrListaCampanhaHistorico;
	int _iQuantidade;
	int _iPosicao;

protected:

	void Add(   char* cidCampanha, 
		        char* cdsCampanha, 
			    char* csgCampanha, 
			    char* cidSubCampanhaHistorico, 
			    char* cdsScriptSubCampanha,
			    char* cidCanal,
			    char* ccdCanal,
			    char* cnmCanal,
				char* cidMotivoCampanha,
				char* cidAtendimentoCampanha,
				char* cdtAtendimento,
				char* cqtTempoAtendimento,
				char* cidUFOperadora,
				char* cidPessoaDeParaOperadora,
				char* cidTipoStatusCampanha,
				char* cidTipoMotivoCampanha,
				char* cidTipoSubMotivoCampanha,
				char* cidSubCampanhaFixa,
				char* cdsTipoSubMotivoCampanha,
				char* csgTipoSubMotivoCampanha,
				char* cdsTipoStatusCampanha,
				char* csgTipoStatusCampanha,
				char* cdsTipoMotivoCampanha,
				char* csgTipoMotivoCampanha,
				char* cidListaConteudo,
				char* cidPessoaDePara,
				char* cidCanalCampanha,
				char* cidCanalUfOperadora,
				char* csgUfOperadora,
				char* cnmSubCampanha);

	void ZeraListaCampanhaHistorico( void );

};

#endif
