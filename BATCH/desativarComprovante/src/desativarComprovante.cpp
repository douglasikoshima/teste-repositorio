#include "../include/desativarComprovante.h"
#include "../include/ComprovanteServico.h"
#include "../../commons/Log/include/Log.h"
#include "../../commons/Propriedade/include/MFile.h"
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <tuxfw.h>

int main(int argc, char *argv[])
{
	stParametros param;	
	Log log;
	ComprovanteServico servico;
	char strLog[400];
	memset(&strLog,0,sizeof(strLog));
	servico.setSignalProcessa(&iSignalProcessa);
	

	log.logInformation("Iniciando processo de desativação de comprovante de serviço");

	log.logInformation("lendo parametros do arquivo de configuração");
	// ler arquivo de configuração com parametros
	if(!lerParametros(&param,"desativarComprovante.cfg"))
	{
		log.logInformation("erro ao ler parametros");
		return 0;
	}

  
	// conectar no banco
	char sid[256];
	memset(&sid,0,sizeof(sid));
	sprintf(sid,"%s/%s@%s",param.usr_db,param.pwd_db,param.inst_db);
	if(servico.connect(sid)){
		log.logError("Erro na conexao com o oracle");
		return 0;
	}

	// iniciar tuxedo
	servico.tuxedoInit(param.usr_tux,param.clt_tux,param.pwd_tux,param.pwd_tux_gen);

	ArmaSinal(SIGTERM);

	// Executa método principal
	servico.executar();

	// desconectar do tuxedo
	log.logInformation("Desconectar tuxedo");
	tpterm(); 
	
	// desconectar do banco
	servico.disconnect();
	
	log.logInformation("Finalizando Deammon <<desativarComprovante>>");
	return 0;
}


int lerParametros(stParametros *param,char*arquivo)
{
	Log log;
	MFile file;
	char aux[513];
	file.setPath(arquivo);

	if(!file.abrir())
	{
		log.logInformation("Erro ao abrir arquivo");
		return 0;
	}
	char linha[500];
	memset(&linha,0,sizeof(linha));
	memset(param,0,sizeof(stParametros));
	while(file.getLine(linha) == 1)
	{
		sprintf(aux,"parametro: %s",linha);
		log.logInformation(aux);
		
		int len = strlen(linha);
		char atributo[100];
		char valor[100];
		int indice1 = 0;
		int indice2 = 0;
		bool token = false;
		memset(&atributo,0,sizeof(atributo));
		memset(&valor,0,sizeof(valor));
		for(int i=0;i<len;i++)
		{
			if(linha[i] == '=')
			{
				token = true;
			}
			else
			{
				if(linha[i] == '\n' || linha[i] == '\t')				
					break;				
				if(token)
					valor[indice2++] = linha[i];
				else
					atributo[indice1++] = linha[i];
			}
		}
		//valor[strlen(valor)-1] = 0;


		if(!strcmp(atributo,"usr_db"))
		{			
			strncpy(param->usr_db,valor,10);
		}
		else if(!strcmp(atributo,"pwd_db"))
		{
			strncpy(param->pwd_db,valor,10);
		}
		else if(!strcmp(atributo,"inst_db"))
		{
			strncpy(param->inst_db,valor,20);
		}
		else if(!strcmp(atributo,"usr_tux"))
		{
			strncpy(param->usr_tux,valor,10);
		}
		else if(!strcmp(atributo,"pwd_tux"))
		{
			strncpy(param->pwd_tux,valor,10);
		}
		else if(!strcmp(atributo,"pwd_tux_gen"))
		{
			strncpy(param->pwd_tux_gen,valor,10);
		}
		else if(!strcmp(atributo,"clt_tux"))
		{
			strncpy(param->clt_tux,valor,10);
		}
		memset(&linha,0,sizeof(linha));
	}
	file.fechar();
	return 1;
}

void ArmaSinal(int iSignal) {
	Log oLog;
	char szAux[4096 + 1];
    sprintf(szAux, "Armando tratamento para Signal(%d)\n", iSignal); oLog.logInformation(szAux);

    if(signal((iSignal), ProcessaSignal) == SIG_ERR) {
        fprintf(stderr, "ERRO ARMANDO SINAL!!!\n");
        exit(-1);
    }
}

void ProcessaSignal(int iSig)
{
	Log oLog;
	char szAux[4096 + 1];
    oLog.logInformation(">>>ProcessaSignal\n");
    sprintf(szAux, "iSig(%d)\n", iSig); oLog.logInformation(szAux);

    /* rearma o mesmo sinal lancado */
    ArmaSinal(iSig);

    if(iSig == SIGTERM) {
        oLog.logInformation("Finalizando processamento via sinal....\n");
        iSignalProcessa=0;
    }

    oLog.logInformation(">>>ProcessaSignal\n");
}