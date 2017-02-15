#include <tuxfw.h>
#include "ManagerBackEndDOMNode.h"
#ifndef RESERVAAPARELHOHPP
#define RESERVAAPARELHOHPP


class ReservaAparelho 
{
public:
	ReservaAparelho();
	virtual ~ReservaAparelho();	
	DOMNode* enviarXML(XMLGen *xml);
	int cancelarReservaAparelho(char*retornoReservaSAP);
	void consultarParametro(char *cdParametro, char *dsParametro);
private:
	ManagerBackEndDOMNode m_pManangerDOMNode;
};


#endif
