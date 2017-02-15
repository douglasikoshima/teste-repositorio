//////////////////////////////////////////////////////////////////////
// CRelArqResult.h: interface for the CRelArqResult class.
//////////////////////////////////////////////////////////////////////

#ifndef RelArqResultListaHH
#define RelArqResultListaHH

#include "CRelatorioCampanha.h"
#include "SRelArqResult.h"
#include <tuxfw.h>

#define strlennull( y ) ( y == NULL ? 0 : strlen( y )  )

class CRelArqResult : public CRelatorioCampanha
{
public:
	// Destrutor
	~CRelArqResult() {};

	// Monta o relatório
	void montaRelatorio(struct SRelatorioCampanha*,XMLGen*);
	stErro CheckRelatorio(struct SRelatorioCampanha*);
	void sql_error();

private:
	struct SRelatorioMotivos* psRelatorio;
};

#endif