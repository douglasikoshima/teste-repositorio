//////////////////////////////////////////////////////////////////////
// CRelatorioGerenciamento.h: interface for the CRelatorioGerenciamento class.
//////////////////////////////////////////////////////////////////////

#ifndef RelatorioGerenciamentoHH
#define RelatorioGerenciamentoHH

#include "CRelatorioCampanha.h"
#include "SRelatorioGerenciamento.h"
#include <tuxfw.h>

#define strlennull( y ) ( y == NULL ? 0 : strlen( y )  )

class CRelatorioGerenciamento : public CRelatorioCampanha
{
public:
	// Destrutor
	~CRelatorioGerenciamento() {};

	// Monta o relatório
	void montaRelatorio(struct SRelatorioCampanha*,XMLGen*);
	stErro CheckRelatorio(struct SRelatorioCampanha*);

private:
	struct SRelatorioGerenciamento* psRelatorio;

	int GraficoAuxiliarPercentual( struct SRelatorioCampanha* sRelatorioCampanha,
		                            XMLGen* xgOut );
};

#endif