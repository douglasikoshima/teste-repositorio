/* $Id: RegistraContato.h,v 1.1.4.1 2007/04/19 21:42:56 esiqueir Exp $ */

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