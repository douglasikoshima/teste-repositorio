/* $Id: RegistraContato.h,v 1.1 2007/07/04 13:40:06 akurak Exp $ */

#ifndef REGISTRACONTATO
	#define REGISTRACONTATO

#include <tuxfw.h>
#include"../include/stRegistraContato.h"

class RegistraContato
{
	stRegistraContato m_stDados;

public:
	RegistraContato();
	bool registarAlteracao(stRegistraContato* dados);

};

#endif