//////////////////////////////////////////////////////////////////////
// SRelatorioCampanha.h: interface for the SRelatorioCampanha class.
//////////////////////////////////////////////////////////////////////

// Estrutura que define os dados de entrada para um relatório de campanha.
#ifndef SRelatorioCampanhaHH
#define SRelatorioCampanhaHH
#include "TString.h"

struct SRelatorioCampanha
{
	char pcidCampanha[21+1];
	char pcidSubCampanha[21+1];
	char pcidCanalCampanha[21+1];
	char pcidGrupo[21+1];
	char pcidRegional[21+1];
	char pcdtInicio[21+1];
	char pcdtFim[21+1];
	char pcidMotivo[21+1];
	char pcidPergunta[21+1];
	char pcidOperador[21+1]; 
	char pcsgOperador[255+1];
	char pcidAreaRegistro[21+1];
	char pcdtAniversario[255+1];
	char pcnrLinha[255+1];
	char pcidDistincao[255+1];
	TString pcUser;
	char pcnmOperador[255+1];
};

#endif
