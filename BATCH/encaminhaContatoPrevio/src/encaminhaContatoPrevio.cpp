/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Charles Santos
 * @version $Revision: 1.1 $
 * @CVS     $Author: cmgarcia $ - $Date: 2005/09/11 01:07:39 $
 **/

#include <stdio.h>
#include <time.h>

#include "../include/encaminhaContatoPrevioPC.h"

int main(int argc, char* argv[])
{
    int retorno = 0;

	Log log;
    string retMesg = "Processo executado com sucesso\n";

	try
	{
	    log.logInformation("Encaminhamento para grupos de resposta ao cliente\n");

		cContatoPrevioPC cprevpc;

        if ( cprevpc.ObterConfiguracaoCompleta() )
        {
            cprevpc.ProcessarAtualizacao();
        }
        else
        {
            throw "erro na carga de parâmetros de configuração\n";
        }
	}
	catch (char* msg)
    {
        retMesg = msg;
        retorno = -1;
	}
    catch(...)
    {
        retMesg = "Excecao desconhecida\n";
        retorno = -1;
    }

	if (!retorno) log.logInformation((char*)retMesg.c_str());
    else if (retorno) log.logError((char*)retMesg.c_str());

	return retorno;
}
