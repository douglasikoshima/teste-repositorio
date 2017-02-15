//////////////////////////////////////////////////////////////////////
// CRelatorioGerenciamento.h: interface for the CRelatorioGerenciamento class.
//////////////////////////////////////////////////////////////////////

#ifndef SRelatorioGerenciamentoHH
#define SRelatorioGerenciamentoHH

struct SRelatorioGerenciamento {
	char stidTipoMotivoCampanha[21+1];
	char stsgTipoMotivoCampanha[256+1];
	char stdsTipoMotivoCampanha[256+1];
	char stnrTotal[21+1];
};

#endif
