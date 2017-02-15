#ifndef CLASSDATA
#define CLASSDATA

#include "../../../commons/routerLib/include/RouterClient.h"

#define TO_VARCHAR(VAR,VAL)	{strcpy((char*)VAR.arr,VAL);VAR.len=strlen(VAL);}	
#define INITIALIZE_SQL		struct sqlca sqlca;sqlca.sqlcode=0

int SaveAtendimentoTeste(int,int,char*);
int SaveTesteResposta(char,int,int,int);

#endif