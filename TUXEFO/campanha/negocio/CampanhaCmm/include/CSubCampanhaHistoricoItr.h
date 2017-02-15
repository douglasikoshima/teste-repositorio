#ifndef CSubCampanhaHistoricoItrH
#define CSubCampanhaHistoricoItrH

#include <stdlib.h>
#include <string.h>

struct STSubCampanhaHistoricoRegistro
{
	char cidSubCampanhaHistorico[21+1];
	char cidSubCampanhaFixa[21+1];
	char cdsScriptSubCampanha[255+1];
	char cinClienteTelefonica[21+1];
	char cqtMaximaAgenda[21+1];
	char cdtInicio[12+1];
	char cdtTermino[12+1];
	char csqVersao[21+1];
	char cinReincidente[21+1];
	char cidTipoCampanha[21+1];
	char sqApresentacao[21+1];
	char cdsNmSubCampanha[255+1];
	char cinDisponibilidade[21+1];
};

class CSubCampanhaHistoricoItr
{
public:
	CSubCampanhaHistoricoItr();
	~CSubCampanhaHistoricoItr();
	int Primeiro( void );
	int Proximo( void );
	int Anterior( void );
	int Ultimo( void );
	int Quantidade( void );
	STSubCampanhaHistoricoRegistro* Registro( void );
	STSubCampanhaHistoricoRegistro* Registro(int nPosicao);

private:
	STSubCampanhaHistoricoRegistro* stcrSubCampanhaHistorico;
	int _iQuantidade;
	int _iPosicao;

protected:

	void Add( char* cidSubCampanhaHistorico, 
		      char* cidSubCampanhaFixa, 
			  char* cdsScriptSubCampanha, 
			  char* cinClienteTelefonica, 
			  char* cqtMaximaAgenda,
			  char* cdtInicio,
			  char* cdtTermino,
			  char* csqVersao,
			  char* cinReincidente,
			  char* cidTipoCampanha,
			  char* sqApresentacao,
			  char* cdsNmSubCampanha);

	void Add( char* cidSubCampanhaHistorico, 
		      char* cidSubCampanhaFixa, 
			  char* cdsScriptSubCampanha, 
			  char* cinClienteTelefonica, 
			  char* cqtMaximaAgenda,
			  char* cdtInicio,
			  char* cdtTermino,
			  char* csqVersao,
			  char* cinReincidente,
			  char* cidTipoCampanha,
			  char* sqApresentacao,
			  char* cdsNmSubCampanha,
			  char* cinDisponibilidade);
			  
	void ZeraSubCampanhaHistorico( void );

};

#endif
