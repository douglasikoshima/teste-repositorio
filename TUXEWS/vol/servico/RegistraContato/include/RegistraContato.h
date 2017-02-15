/* $Id: RegistraContato.h,v 1.1 2007/07/06 22:00:13 akurak Exp $ */

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