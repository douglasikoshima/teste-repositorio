/* $Id: RegistraContato.h,v 1.1 2009/07/31 15:33:41 a5110702 Exp $ */

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