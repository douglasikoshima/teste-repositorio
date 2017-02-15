//////////////////////////////////////////////////////////////////////
// CRelatorioMotivos.h: interface for the CRelatorioMotivos class.
//////////////////////////////////////////////////////////////////////

#ifndef SRelatorioMotivosHH
#define SRelatorioMotivosHH

struct RelatorioMotivosLista
{
	char szIdTipoMotivoCampanha[255+1];
	char szSgTipoMotivoCampanha[255+1];
	char szDsTipoMotivoCampanha[255+1];
	char szSubMotivoCampanha[300+1];
	char szMotivoCampanha[300+1];
	char szIdTipoSubMotivoCampanha[255+1];
	char szSgTipoSubMotivoCampanha[255+1];
	char szDsTipoSubMotivoCampanha[255+1];
	char szValor[255+1];
};

struct SRelatorioMotivos {
	char pcPorcentagem[21+1];
};



#endif
