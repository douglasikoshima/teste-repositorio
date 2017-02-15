//////////////////////////////////////////////////////////////////////
// CRetencaoAnalise.h: interface for the CRelatorioGerenciamento class.
//////////////////////////////////////////////////////////////////////

#ifndef CRetencaoAnaliseHH
#define CRetencaoAnaliseHH


#include "utilretencao.h"

class CRetencaoAnalise
{
public:
	int Inserir(DOMNode*,char*,char*);
	int InserirAnaliseEndereco(DOMNode*,char*,char*);
	int InserirAnaliseCredito(DOMNode*,char*,char*);
	int FinalisarAnalise(char*);
private:

};

#endif