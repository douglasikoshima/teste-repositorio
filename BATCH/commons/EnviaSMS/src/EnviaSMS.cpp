/**
 * Implementa as característas de um atributo.
 */

#include "../include/EnviaSMS.h"

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "atmi.h"

EnviaSMS::EnviaSMS()
{
	prop.setOrigem("enviaSMS.cfg");

	genericPassword		= prop.getParametro("genericPassword");
	userName			= prop.getParametro("userName");
	cltName				= prop.getParametro("cltName");
	aplicationPassword	= prop.getParametro("aplicationPassword");
}

EnviaSMS::~EnviaSMS()
{

	tpterm();

}

/************************************************************************
Esse construtor recebe como parametro o nível de log a ser usado durante
o processamento.
************************************************************************/
EnviaSMS::EnviaSMS( int _nivelLog )
{
	log.setNivel( _nivelLog );

	prop.setNivelLog( _nivelLog );
	prop.setOrigem("enviaSMS.cfg");

	genericPassword		= prop.getParametro("genericPassword");
	userName			= prop.getParametro("userName");
	cltName				= prop.getParametro("cltName");
	aplicationPassword	= prop.getParametro("aplicationPassword");
}

void EnviaSMS::setMensagem(char* _mensagem)
{

	mensagem = _mensagem;

}

void EnviaSMS::setDestinatario(char* _destinatario)
{

	destinatario = _destinatario;
}

bool EnviaSMS::enviar()
{

    log.logInformation("Processando envio, conectando no tuxedo.....");

	conectaTuxedo();

	SmallString mensagem;

	montaXML( &mensagem );

    char *sendbuf, *rcvbuf;
    long rcvlen;

    sprintf(szAux, "Tamanho do XML = %d\n", mensagem.Size());
    log.logInformation(szAux);

    log.logInformation("Alocando buffer para a mensagem...");

    if((sendbuf = (char *) tpalloc("STRING", NULL, mensagem.Size()+1)) == NULL) {
        //fprintf(stderr,"Error allocating send buffer\n");
        log.logError("Error allocating send buffer\n");
        tpterm();
        return 1;
    }

    log.logInformation("Alocando buffer para o retorno...");

    if((rcvbuf = (char *) tpalloc("STRING", NULL, mensagem.Size()+1)) == NULL) {
        //fprintf(stderr,"Error allocating receive buffer\n");
        log.logError("Error allocating receive buffer\n");
        tpfree(sendbuf);
        tpterm();
        return 1;
    }

    strcpy(sendbuf, mensagem.c_str());

    log.logInformation("Chamando tpcall...");

    if(tpcall("SMSSend", (char *)sendbuf, mensagem.Size(), (char **)&rcvbuf, &rcvlen, (long)0) == -1) {

        //fprintf(stderr, "Tperrno = %d\n", tperrno);
        sprintf(szAux, "Tperrno = %d\n", tperrno);
        log.logError(szAux);

        tpfree(sendbuf);
        tpfree(rcvbuf);
        tpterm();
		
        return false;
    }

    log.logInformation("Enviado SMS !");

    tpfree(sendbuf);
    tpfree(rcvbuf);

	log.logInformation("fechando conexao.....");
	tpterm();
    return true;


}

void EnviaSMS::montaXML(SmallString* _mensagem)
{
	
	*_mensagem +=	"<?xml version='1.0' encoding='ISO-8859-1'?>"
						"<msg>"
							"<msgHdr>"
								"<user>1</user>"
								"<service>SMSSend</service>"
							"</msgHdr>"
							"<msgBody>"
								"<message>";

	*_mensagem +=					mensagem;

    *_mensagem +=				"</message>"
								"<recipient>";

	*_mensagem +=					destinatario;
	
	*_mensagem +=				"</recipient>"
							"</msgBody>"
						"</msg>";
							

}

void EnviaSMS::conectaTuxedo()
{

    log.logInformation("Conectando ao TUXEDO...");

	/* Verifica se todos os dados foram recuperados do arquivo de configuração. */
	if (!genericPassword || 
		!userName || 
		!cltName || 
		!aplicationPassword
	   ) {
		log.logError("Dados de conexão com tuxedo incompletos. Abortando processo.!");

		throw;
	}


    TPINIT *tpInitInfo;
    int i;

	if((tpInitInfo = (TPINIT *)tpalloc("TPINIT", (char *)NULL, TPINITNEED(strlen(genericPassword) - 1) )) == (TPINIT *)NULL)
	{
		log.logError("Erro ao conectar ao tuxedo");
        throw;
	}

    log.logInformation("Atribuindo variaveis...");

    sprintf(szAux, "userName = [%s]", userName);
    log.logInformation(szAux);

    sprintf(szAux, "cltName = [%s]", cltName);
    log.logInformation(szAux);

    sprintf(szAux, "aplicationPassword = [%s]", aplicationPassword);
    log.logInformation(szAux);

    sprintf(szAux, "genericPassword = [%s]", genericPassword);
    log.logInformation(szAux);

    log.logInformation("copiando...");

	strcpy(tpInitInfo->usrname,      userName);
	strcpy(tpInitInfo->cltname,      cltName);
	strcpy(tpInitInfo->passwd,       aplicationPassword);
	strcpy((char *)&tpInitInfo->data,genericPassword);

	i = tpinit(tpInitInfo);
		
	sprintf(szAux, "TPINIT: %i, TPERRNO: %i\n", i, tperrno);
	log.logInformation(szAux);

	if (i < 0)
	{
		log.logError("Nao foi possivel conectar ao TUXEDO...");
		throw;
	}

    log.logInformation("PRONTO - Conectado ao TUXEDO...");

}
