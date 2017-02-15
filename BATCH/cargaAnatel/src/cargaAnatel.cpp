#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdio.h>
#include <iostream.h>

#include "../include/cargaAnatel.h"
#include "../include/Procedimento.hpp"

Log log;

int executar() {
	Procedimento procedimento;
	try {
		procedimento.processar();
	} catch(...) {
		log.logError("erro no processo anatel");
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
