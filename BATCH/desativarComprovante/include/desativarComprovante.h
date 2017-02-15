#ifndef __DESATIVARCOMPROVANTEH__
#define __DESATIVARCOMPROVANTEH__


#define SIZE_PARAMETRO 11

struct stParametros{
    char usr_db[SIZE_PARAMETRO];
	char pwd_db[SIZE_PARAMETRO];
	char inst_db[21];
	char usr_tux[SIZE_PARAMETRO];
	char pwd_tux[SIZE_PARAMETRO];
	char pwd_tux_gen[SIZE_PARAMETRO];
	char clt_tux[SIZE_PARAMETRO];
};

int lerParametros(stParametros *param,char*arquivo);


/* controle de processo */
int iSignalProcessa = 1;
void ArmaSinal(int iSignal);
void ProcessaSignal(int iSig);

#endif