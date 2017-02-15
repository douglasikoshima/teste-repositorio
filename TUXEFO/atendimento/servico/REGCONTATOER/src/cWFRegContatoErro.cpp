/**
 * @author  Eder Jani Martins
 * @version $Revision: 1.1.2.1 $
 * @CVS     $Author: a5116174 $ - $Date: 2011/08/12 17:11:35 $
 **/

#ifdef WIN32
#pragma warning(disable:4786)
#endif

#include <string>
using namespace std;

#include "../include/cWFRegContatoErro.h"
#include "../../../commons/definesAtendimento.h"

extern unsigned long proCIncluirWFRegContatoErro(st_RegContatoLogErro* dados, st_vlRegContatoLogErro* status);


cWFRegContatoErro::cWFRegContatoErro(DOMNode*dnode, char* oIdPessoaUsuario)
{
    memset( pcMensagemErro, 0, sizeof(pcMensagemErro) ); 
    memset( pcIdPessoaUsuario, 0, sizeof(pcIdPessoaUsuario) ); 

    entrada = dnode;

    SAFE_STRNCPY( pcIdPessoaUsuario, oIdPessoaUsuario );
    strcpy( pcMensagemErro, "Mensagem de erro não informada.Deve vir na tag <statusText> através do método setStatusText()." );

}

cWFRegContatoErro::cWFRegContatoErro(DOMNode*dnode, char* oIdPessoaUsuario, char* oMensagemErro)
{
    memset( pcMensagemErro, 0, sizeof(pcMensagemErro) ); 
    memset( pcIdPessoaUsuario, 0, sizeof(pcIdPessoaUsuario) ); 

    entrada = dnode;

    SAFE_STRNCPY( pcMensagemErro, oMensagemErro );
    SAFE_STRNCPY( pcIdPessoaUsuario, oIdPessoaUsuario );

}

void cWFRegContatoErro::carregaDados()
{
    // Inicializa as estruturas de dados para armazenar os valores.
    memset(&m_stDados,0,sizeof(st_RegContatoLogErro));
    memset(&m_vlDados,-1,sizeof(st_vlRegContatoLogErro));

    if( entrada )
    {
        char* p;

        if (p = tx.walkTree( entrada, "idContato", 0 ), p ) 
        {
            m_stDados.idContato = atoi( p );
            m_vlDados.idContato = 1;
            XMLString::release(&p);
        }

        if (p = tx.walkTree( entrada, "idCanal", 0 ), p ) 
        {
            m_stDados.idCanal = atoi( p );
            m_vlDados.idCanal = 1;
            XMLString::release(&p);
        }

        if (p = tx.walkTree( entrada, "idGrupoAbertura", 0 ), p ) 
        {
            m_stDados.idGrupoAbertura = atoi( p );
            m_vlDados.idGrupoAbertura = 1;
            XMLString::release(&p);
        }

        if (p = tx.walkTree( entrada, "idProcedencia", 0 ), p ) 
        {
            m_stDados.idProcedencia = atoi( p );
            m_vlDados.idProcedencia = 1;
            XMLString::release(&p);
        }

        //Usuário que vem no HEADER do XML de entrada do sevicos TUXEDO
        //Ou seja, id do usuario que está operado o VIVONet
        if( pcIdPessoaUsuario )
        {
            strcpy( m_stDados.idPessoaUsuarioAbertura, pcIdPessoaUsuario );
            m_vlDados.idPessoaUsuarioAbertura = 1;
        }

        if (p = tx.walkTree( entrada, "idSegmentacao", 0 ), p ) 
        {
            m_stDados.idSegmentacao = atoi( p );
            m_vlDados.idSegmentacao = 1;
            XMLString::release(&p);
        }

        if (p = tx.walkTree( entrada, "idTipoCarteira", 0 ), p ) 
        {
            m_stDados.idTipoCarteira = atoi( p );
            m_vlDados.idTipoCarteira = 1;
            XMLString::release(&p);
        }

        p = tx.getNodeAsString(entrada);
        SAFE_STRNCPY(m_stDados.xmlEntrada, p );
        if ( p ) { free(p); }
        m_vlDados.xmlEntrada = 1;

        //A mensagem de erro é opcional, pois é apenas um indicativo que pode ou não 
        //ajudar na solução do problema
        if (p = tx.walkTree( entrada, "mensagemRegContatoErro", 0 ), p ) 
        {
            strcpy(m_stDados.mensagemErro, p );
            m_vlDados.mensagemErro = 1;
            XMLString::release(&p);
        }
        else if( pcMensagemErro )//se não vier no XML, tenta recuperar de variavel interna
        {
            strcpy(m_stDados.mensagemErro, pcMensagemErro );
            m_vlDados.mensagemErro = 1;
        }
    }//if( entrada)
}

unsigned long cWFRegContatoErro::incluir()
{
    carregaDados();

    // por algum motivo mensagem de sucesso esta trafegando como erro e
    // se isto ocorrer irá ignorar.
    if ( 0 == stricmp(m_stDados.mensagemErro,"Atendimento registrado.") )
    {
        return 0;
    }

    return proCIncluirWFRegContatoErro(&m_stDados, &m_vlDados);
    
}

void cWFRegContatoErro::executar()
{
    ULOG_START( "cWFRegContatoErro::executar()" );

    TuxMessage o_inputMessage;

    o_inputMessage.setUser(pcIdPessoaUsuario);
    o_inputMessage.setStatusText(pcMensagemErro);
    char *p = tx.getNodeAsString(entrada);
    o_inputMessage.setMessageBody(p);
    o_inputMessage.setService("REGCONTATOER");

    //
    // Nos foi recomendado usar getMessageBody() pois o Framework não libera a memória usada 
    // pelo getMessageXML(). -- Agosto/2007 -- Cassio
    //
    //char*sMsgIn = o_inputMessage.getMessageXML();
    //
    string strMsgIn = 
        "<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>"
            "<msg>"
                "<msgHdr>"
                    "<user>"+(string)pcIdPessoaUsuario+"</user>"
                    "<statusText>"+(string)pcMensagemErro+"</statusText>"
                    "<service>REGCONTATOER</service>"
                "</msgHdr>"
                "<msgBody>"
                    + o_inputMessage.getMessageBody() +
                    //+ "<mensagemRegContatoErro>" + pcMensagemErro + "</mensagemRegContatoErro>" +
                "</msgBody>"
            "</msg>";

    char *sMsgIn = (char*)strMsgIn.c_str();

    remoteACall("REGCONTATOER", sMsgIn, 0);

    if ( p ) { free(p); }

    ULOG_END( "cWFRegContatoErro::executar()" );
}

char* cWFRegContatoErro::remoteACall(char* nomeServico, char* sMsgIn, int lFlags)
{
    ULOG_START( "cWFRegContatoErro::remoteACall()" );

    // Declaracao de variaveis
    char*   sMsgOut = 0L;

#ifndef WIN32

    int ret = TUXFWRET_ERROR;

    char*   bufferE = 0L;
    char*   bufferS = 0L;

    // Preenche os tamanhos de buffers para a chamada ao tpacall
    long    snd_len = strlen(sMsgIn);
    long    rcv_len = TUXFW_MAX_MSG_LEN;

    // Aloca um buffer de envio com o tamanho do XML de entrada
    if ((bufferE = (char *)tpalloc("STRING", NULL, snd_len+1)) == NULL)
    {
        throw new TuxException(ERR_TUX_TPALLOC_RET_CD, ERR_TUX_TPALLOC_RET_MSG);
    }
    //
    // Aloca um buffer de recepcao  com tamanho maximo possivel para retorno
    if ((bufferS = (char *)tpalloc("STRING", NULL, TUXFW_MAX_MSG_LEN)) == NULL)
    {
        tpfree(bufferE);
        throw new TuxException(ERR_TUX_TPALLOC_RET_CD, ERR_TUX_TPALLOC_RET_MSG);
    }

    // clona o buffer de entrada no buffer tuxedo de envio
    strcpy(bufferE, sMsgIn);
    ULOG("remoteACall: dump call parameters:\n\t# serviceName=[%s]\n\tinputBuffer=[%s]\n\t# inputLen=[%d]\n\t# flags=[%d]", nomeServico, bufferE, snd_len, lFlags );
    if ( tpacall(nomeServico, (char*) bufferE, snd_len, TPNOTRAN|TPNOREPLY) == -1)
    {
        long errNo = tperrno;
        long urCode = tpurcode;
        ULOG("tpacall com erro, Tperrno = %d, TpUrCode = %d ", errNo, urCode);

        if( errNo == TPESVCFAIL )
        {
            // Copia o buffer tuxedo de retorno
            bufferS[rcv_len]='\0';
            //sMsgOut = derivStr( bufferS );
            ULOGW("tpacall processada com ERRO, outputMessage=[%s]", sMsgOut);
        }
        else 
        {
            tpfree(bufferE); 
            bufferE = 0L;

            tpfree(bufferS);
            bufferS = 0L;

            throw new TuxException(ERR_TUX_REMOTE_CALL_CD,ERR_TUX_REMOTE_CALL_MSG,nomeServico,errNo,urCode);
        }
    }
    else
    {
        // Copia o buffer tuxedo de retorno
        bufferS[rcv_len]='\0';
        ULOG("tpacall processada com sucesso, outputMessage=[%s], tamanho=[%d]",
                sMsgOut, strlen(sMsgOut));
    }

    if(bufferE) tpfree(bufferE); bufferE = 0L;
    if(bufferS) tpfree(bufferS); bufferS = 0L;

#endif // WIN32

    ULOG_END( "cWFRegContatoErro::remoteACall()" );

    return sMsgOut;
}
