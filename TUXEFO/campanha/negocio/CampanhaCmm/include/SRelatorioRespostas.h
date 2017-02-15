//////////////////////////////////////////////////////////////////////
// CRelatorioRespostas.h: interface for the CRelatorioRespostas class.
//////////////////////////////////////////////////////////////////////

#ifndef SRelatorioRespostasHH
#define SRelatorioRespostasHH

struct RelatorioRespostasLista
{
	char szPergunta				[2000+1];
	char szResposta				[2000+1];
	char szNumeroAtendimento	[255+1];
	char szPercentual			[255+1];
};

struct SRelatorioRespostas {
	char pcPorcentagem[21+1];

};

#endif
