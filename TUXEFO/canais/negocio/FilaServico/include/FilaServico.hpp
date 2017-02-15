#ifndef _FILASERVICO_
#define _FILASERVICO_
#include<tuxfw.h>

class FilaServico{

public:
	FilaServico();
	~FilaServico();
	char*getParametro(char*nome,char*param);
	int setStatusParametro(char*parametro,char*valor);
	void getRegistroAgrupado(XMLGen*gen);
	void getRegistros(XMLGen*gen,char*cderro,char*regIni,char*regFim);
	void apagaIdErroFila(char*idComprovante);
	void apagaGrupoErroFila(char*erro);
	void atualizaIdErroFila(char*idComprovante);
	void atualizaGrupoErroFila(char*cderro);
	
private:


};

#endif