//////////////////////////////////////////////////////////////////////
// CRelArqResp.h: interface for the CRelArqResp class.
//////////////////////////////////////////////////////////////////////

#ifndef RelArqRespListaHH
#define RelArqRespListaHH

#include "CRelatorioCampanha.h"
#include "SRelArqResp.h"
#include <tuxfw.h>

#define strlennull( y ) ( y == NULL ? 0 : strlen( y )  )

class CRelArqResp : public CRelatorioCampanha
{
public:
	// Destrutor
	~CRelArqResp() {};

	// Monta o relatório
	void montaRelatorio(struct SRelatorioCampanha*,XMLGen*);
	void getRespostas( char *idpergunta, char *atendimento, XMLGen*);
	stErro CheckRelatorio(struct SRelatorioCampanha*);
	void sql_error();

private:
	struct SRelatorioMotivos* psRelatorio;
};

#endif