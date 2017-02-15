#ifndef _CAtividadeMotivo_H
#define _CAtividadeMotivo_H

#include <tuxfw.h>
#include "stcWFAtividadeMotivo.h"
#include "../../../commons/Collection/include/Collection.h"

class cWFAtividadeMotivo
{
	st_AtividadeMotivo m_stDados;
	st_vlAtividadeMotivo m_vlDados;

	DOMNode* entrada;
	XMLGen* saida;

	TuxHelper tx;

public:
	cWFAtividadeMotivo();
	cWFAtividadeMotivo(DOMNode*dnode, XMLGen*xml_g);
	cWFAtividadeMotivo(st_AtividadeMotivo* dados, st_vlAtividadeMotivo* status, XMLGen* xml_g);

	bool incluir();
	bool alterar();
	bool excluir();
	bool ObterAtividadeMotivo(int _idAtividade,XMLGen* saida);
	bool ObterAtividadeMotivo(int _idAtividade,int _idFase,XMLGen* saida);	
};

#endif
