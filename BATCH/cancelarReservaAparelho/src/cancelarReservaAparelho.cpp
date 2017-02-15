#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdio.h>
#include <iostream.h>

#include "../include/cancelarReservaAparelho.h"
#include "../include/ReservaAparelho.hpp"

Log log;

int executar() {
	ReservaAparelho reservaAparelho;
	try {
		reservaAparelho.processar();
	} catch(...) {
		log.logError("erro no processo reserva aparelho");
	}
	return 0;
}

int main(void)
{	

	log.setNivel(2);
	log.logDebug("inicio");
	executar();		
	log.logDebug("fim");
	return 0;
}
