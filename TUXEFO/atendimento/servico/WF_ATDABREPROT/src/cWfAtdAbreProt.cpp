/**
 * 
 * @modulo  Atendimento
 * @usecase Protocolo
 * @author  Cassio Garcia
 * @version $Revision: 1.1.2.7.40.2 $
 * @CVS     $Author: a5114878 $ - $Date: 2013/12/05 16:36:38 $
 **/

#include "../../WF_ATDGETDADOPRO/include/cWfAtdGetDadoProPC.h"

#include "../include/cWfAtdAbreProtPC.h"
#include "../include/cWfAtdAbreProt.h"
#include "../../../commons/cWfAtdProtocoloException.h"

void cWfAtdAbreProt::Executar()
{
    try
    {
        CarregarDados();

        ValidarDados();

        // Se não mandou numero de telefone de envio de SMS do cliente assume que
        // será o mesmo telefone cadastrado no FO.
        if ( strcmp(dados.idTipoAberturaProtocolo,TIPO_ABER_PROT_LINHA_CLIENTE)==0 )
        {
            if ( -1 == status.cdAreaRegistroSMS || -1 == status.nrTelefoneSMS )
            {
                SAFE_STRNCPY(dados.cdAreaRegistroSMS,dados.cdAreaRegistro);
                status.cdAreaRegistroSMS = 1;

                SAFE_STRNCPY(dados.nrTelefoneSMS,dados.nrTelefone);
                status.nrTelefoneSMS = 1;
            }
        }

        strcpy(dados.idUsuarioAlteracao,getIdPessoaUsuarioWeb());
        status.idUsuarioAlteracao = 1;

        if ( status.idAtendimentoProtocolo > 0 )
        {
            AbrirProtocoloCondicional(dados.idAtendimentoProtocolo);
        }
        else
        {
            AbrirProtocolo();
        }
    }
    catch (...)
    {
        throw;
    }
}

bool cWfAtdAbreProt::AbrirProtocoloCondicional(const char *idAtendimentoProtocolo)
{
    ULOG_START("bool cWfAtdAbreProt::AbrirProtocoloCondicional(const char *idAtendimentoProtocolo)");

    /*
        1.  Recebeu um numero de protocolo, um sistema origem e outros dados de protocolo
        2.  Se o protocolo não existe na base
            a.  Abrir novo protocolo com dados recebidos 
        3.  Estado = "Encerrado" ?
            a.  Dentro do workflow ?
                i.  Usar o mesmo numero do protocolo encerrado
                ii. Ir para a saida
            b.  Fora do workflow ?
                i.  Abrir protocolo com os mesmos dados do protocolo informado
                ii. Ir para a saída retornando o novo numero de protocolo.
        4.  Sistema origem informado <> do sistema origem do protocolo?
            a.  Sistema origem informado = URA/SCREEN POP/FO?
                i.  Ir para a saída aproveitando o # do protocolo
            b.  Abrir novo protocolo com os dados recebidos.
        5.  Retornar o numero do protocolo recebido.
        6.  Sair
    */

    try
    {
        bool abrirNovoProtocolo = true;
        
        if ( idAtendimentoProtocolo && *idAtendimentoProtocolo )
        {
            cWfAtdGetDadoProPC cwfatdgetdadopropc;

            ULOG("idAtendimentoProtocolo=%s",idAtendimentoProtocolo);
            ULOG("forceUsoProtocoloNaoAberto=%d",dados.forceUsoProtocoloNaoAberto);

            abrirNovoProtocolo = 
                !cwfatdgetdadopropc.proCObterDadosProtocoloSMS(idAtendimentoProtocolo);

            ULOG("abrirNovoProtocolo=%s",
                    abrirNovoProtocolo?
                        "não encontrou dados do protocolo. vai abrir novo..."
                       :"protocolo encontrado. vai analisar...");

            if ( abrirNovoProtocolo == false )
            { // se encontrou dados do protocolo aplica outras regras..
                ULOG("statusProtocolo=%s",cwfatdgetdadopropc.getDsStatusProtocolo());
                if ( cwfatdgetdadopropc.statusEmAtendimentoSimNao() == false )
                { // se não esta em atendimento ...
				     ULOG("cwfatdgetdadopropc.statusEmAtendimentoSimNao() == false");
                    if ( dados.forceUsoProtocoloNaoAberto == 1 )
                    { // se é pra forçar uso de protocolo encerrado...
                        ULOG("vai aproveitar o protocolo fora de atendimento");
                    }
                    else
                    {
                        ULOG("vai abrir novo protocolo usando dados do protocolo encontrado");

                        abrirNovoProtocolo = true;

                        setCdAreaRegistro(cwfatdgetdadopropc.getCdAreaRegistro());
                        setNrTelefone(cwfatdgetdadopropc.getNrTelefone());
                        setCdAreaRegistroSMS(cwfatdgetdadopropc.getCdAreaRegistroSMS());
                        setNrTelefoneSMS(cwfatdgetdadopropc.getNrTelefoneSMS());
                        setIdSistemaOrigem(cwfatdgetdadopropc.getIdSistemaOrigem());
                        setIdPessoaDePara(cwfatdgetdadopropc.getIdPessoaDePara());
                        setCdConta(cwfatdgetdadopropc.getCdConta());
                        setDtAbertura(cwfatdgetdadopropc.getDtAbertura());
                        setDtEncerramento(cwfatdgetdadopropc.getDtEncerramento());
                        setIdLinhaTelefonica(cwfatdgetdadopropc.getIdLinhaTelefonica());
                        setIdTipoAberturaProtocolo(cwfatdgetdadopropc.getIdTipoAberturaProtocolo());
                    }
                }
                else
                {
					ULOG("ELSE -> cwfatdgetdadopropc.statusEmAtendimentoSimNao() == false");
					ULOG("nmSistemaOrigem=%s",cwfatdgetdadopropc.getNmSistemaOrigem());
                    ULOG("idSistemaOrigem=%s",cwfatdgetdadopropc.getIdSistemaOrigem());
                    ULOG("idSistemaOrigemEntrada=%s",dados.idSistemaOrigem);
					
                    if(strcmp(cwfatdgetdadopropc.getIdSistemaOrigem(),dados.idSistemaOrigem))
                    { // se o sistema origem informado não é o mesmo que o do protocolo...
						
						ULOG("if(strcmp(cwfatdgetdadopropc.getIdSistemaOrigem(),dados.idSistemaOrigem))");
                        if ( strcmp(cwfatdgetdadopropc.getNmSistemaOrigem(),"FrontOffice")
                           && strcmp(cwfatdgetdadopropc.getNmSistemaOrigem(),"Atendimento")
                           && strcmp(cwfatdgetdadopropc.getNmSistemaOrigem(),"URA")
                           && strcmp(cwfatdgetdadopropc.getNmSistemaOrigem(),"*8486") // URA
                           && strcmp(cwfatdgetdadopropc.getNmSistemaOrigem(),"*9000") // URA
                           && strcmp(cwfatdgetdadopropc.getNmSistemaOrigem(),"1058") // URA
                           && strcmp(cwfatdgetdadopropc.getNmSistemaOrigem(),"SCREEN POP"))
                        { // .. e não bate com a lista acima, vai abrir outro.
						   ULOG("ifcomparacao sistemaorigem lista");
                            abrirNovoProtocolo = true;
                        }
                    }
                }
            }
        }
        else
        {
            ULOG("protocolo não informado, vai abrir novo...");
        }

				ULOG("Extreme noise terro1");
        if ( abrirNovoProtocolo )
        {
            AbrirProtocolo();

            ULOG_END("bool cWfAtdAbreProt::AbrirProtocoloCondicional(const char *idAtendimentoProtocolo)");

            return true; // informa que abriu protocolo novo
        }
        else
        {
            ULOG("Protocolo informado pode ser reutilizado.");
			ULOG("Extreme noise terror");
			return false;
        }
    }
    catch (...)
    {
        throw;
    }
		ULOG("Extreme noise terro2");
    ULOG_END("bool cWfAtdAbreProt::AbrirProtocoloCondicional(const char *idAtendimentoProtocolo)");

    return false; // não abriu protocolo novo


}



bool cWfAtdAbreProt::AbrirProtocoloCondicional( const char *idAtendimentoProtocolo, char * idLinhaTelefonicaPrm )
{
    ULOG_START("bool cWfAtdAbreProt::AbrirProtocoloCondicional( const char *idAtendimentoProtocolo, char * idLinhaTelefonicaPrm )");

    /*
        1.  Recebeu um numero de protocolo, um sistema origem e outros dados de protocolo
        2.  Se o protocolo não existe na base
            a.  Abrir novo protocolo com dados recebidos 
        3.  Estado = "Encerrado" ?
            a.  Dentro do workflow ?
                i.  Usar o mesmo numero do protocolo encerrado
                ii. Ir para a saida
            b.  Fora do workflow ?
                i.  Abrir protocolo com os mesmos dados do protocolo informado
                ii. Ir para a saída retornando o novo numero de protocolo.
        4.  Sistema origem informado <> do sistema origem do protocolo?
            a.  Sistema origem informado = URA/SCREEN POP/FO?
                i.  Ir para a saída aproveitando o # do protocolo
            b.  Abrir novo protocolo com os dados recebidos.
        5.  Retornar o numero do protocolo recebido.
        6.  Sair
    */

    try
    {
        bool abrirNovoProtocolo = true;
        
        if ( idAtendimentoProtocolo && *idAtendimentoProtocolo )
        {
            cWfAtdGetDadoProPC cwfatdgetdadopropc;

            ULOG("idAtendimentoProtocolo=%s",idAtendimentoProtocolo);
            ULOG("forceUsoProtocoloNaoAberto=%d",dados.forceUsoProtocoloNaoAberto);

            abrirNovoProtocolo = 
                !cwfatdgetdadopropc.proCObterDadosProtocoloSMS(idAtendimentoProtocolo);

            ULOG("abrirNovoProtocolo=%s",
                    abrirNovoProtocolo?
                        "não encontrou dados do protocolo. vai abrir novo..."
                       :"protocolo encontrado. vai analisar...");

            if ( abrirNovoProtocolo == false )
            { // se encontrou dados do protocolo aplica outras regras..
                ULOG("statusProtocolo=%s",cwfatdgetdadopropc.getDsStatusProtocolo());
                if ( cwfatdgetdadopropc.statusEmAtendimentoSimNao() == false )
                { // se não esta em atendimento ...
				     ULOG("cwfatdgetdadopropc.statusEmAtendimentoSimNao() == false");
                    if ( dados.forceUsoProtocoloNaoAberto == 1 )
                    { // se é pra forçar uso de protocolo encerrado...
                        ULOG("vai aproveitar o protocolo fora de atendimento");
                    }
                    else
                    {
                        ULOG("vai abrir novo protocolo usando dados do protocolo encontrado");

                        abrirNovoProtocolo = true;

                        setCdAreaRegistro(cwfatdgetdadopropc.getCdAreaRegistro());
                        setNrTelefone(cwfatdgetdadopropc.getNrTelefone());
                        setCdAreaRegistroSMS(cwfatdgetdadopropc.getCdAreaRegistroSMS());
                        setNrTelefoneSMS(cwfatdgetdadopropc.getNrTelefoneSMS());
                        setIdSistemaOrigem(cwfatdgetdadopropc.getIdSistemaOrigem());
                        setIdPessoaDePara(cwfatdgetdadopropc.getIdPessoaDePara());
                        setCdConta(cwfatdgetdadopropc.getCdConta());
                        setDtAbertura(cwfatdgetdadopropc.getDtAbertura());
                        setDtEncerramento(cwfatdgetdadopropc.getDtEncerramento());
                        setIdLinhaTelefonica(cwfatdgetdadopropc.getIdLinhaTelefonica());
                        setIdTipoAberturaProtocolo(cwfatdgetdadopropc.getIdTipoAberturaProtocolo());
                    }
                }
                else
                {
					ULOG("ELSE -> cwfatdgetdadopropc.statusEmAtendimentoSimNao() == false");
					ULOG("nmSistemaOrigem=%s",cwfatdgetdadopropc.getNmSistemaOrigem());
                    ULOG("idSistemaOrigem=%s",cwfatdgetdadopropc.getIdSistemaOrigem());
                    ULOG("idSistemaOrigemEntrada=%s",dados.idSistemaOrigem);
					
                    if(strcmp(cwfatdgetdadopropc.getIdSistemaOrigem(),dados.idSistemaOrigem))
                    { // se o sistema origem informado não é o mesmo que o do protocolo...
						
						ULOG("if(strcmp(cwfatdgetdadopropc.getIdSistemaOrigem(),dados.idSistemaOrigem))");
                        if ( strcmp(cwfatdgetdadopropc.getNmSistemaOrigem(),"FrontOffice")
                           && strcmp(cwfatdgetdadopropc.getNmSistemaOrigem(),"Atendimento")
                           && strcmp(cwfatdgetdadopropc.getNmSistemaOrigem(),"URA")
                           && strcmp(cwfatdgetdadopropc.getNmSistemaOrigem(),"*8486") // URA
                           && strcmp(cwfatdgetdadopropc.getNmSistemaOrigem(),"*9000") // URA
                           && strcmp(cwfatdgetdadopropc.getNmSistemaOrigem(),"1058") // URA
                           && strcmp(cwfatdgetdadopropc.getNmSistemaOrigem(),"SCREEN POP"))
                        { // .. e não bate com a lista acima, vai abrir outro.
						   ULOG("ifcomparacao sistemaorigem lista");
                            abrirNovoProtocolo = true;
                        }
                    }
                }
            }
        }
        else
        {
            ULOG("protocolo não informado, vai abrir novo...");
        }

        if ( abrirNovoProtocolo )
        {
            AbrirProtocolo( idLinhaTelefonicaPrm );

            ULOG_END("bool cWfAtdAbreProt::AbrirProtocoloCondicional( const char *idAtendimentoProtocolo, char * idLinhaTelefonicaPrm )");

            return true; // informa que abriu protocolo novo
        }
        else
        {
            ULOG("Protocolo informado pode ser reutilizado.");
			return false;
        }
    }
    catch (...)
    {
        throw;
    }
    ULOG_END("bool cWfAtdAbreProt::AbrirProtocoloCondicional( const char *idAtendimentoProtocolo, char * idLinhaTelefonicaPrm )");

    return false; // não abriu protocolo novo


}



void cWfAtdAbreProt::AbrirProtocolo()
{
    ULOG_START("void cWfAtdAbreProt::AbrirProtocolo()");

    try
    {
        cWfAtdAbreProtPC cwfAtdAbreProtpc;
        cwfAtdAbreProtpc.CriarNovoProtocolo(&dados,&status);
    }
    catch (...)
    {
        throw;
    }

    ULOG_END("void cWfAtdAbreProt::AbrirProtocolo()");
}




void cWfAtdAbreProt::AbrirProtocolo( char * idLinhaTelefonica )
{
    ULOG_START("void cWfAtdAbreProt::AbrirProtocolo( char * idLinhaTelefonica )");

    try
    {
        cWfAtdAbreProtPC cwfAtdAbreProtpc;
        cwfAtdAbreProtpc.CriarNovoProtocolo(&dados,&status,idLinhaTelefonica );
    }
    catch (...)
    {
        throw;
    }

    ULOG_END("void cWfAtdAbreProt::AbrirProtocolo( char * idLinhaTelefonica )");
}



void cWfAtdAbreProt::ValidarDados()
{
    if ( status.cdAreaRegistro > 0 && status.nrTelefone == -1 )
    { // se informou código de área sem telefone gera erro
        throw new TuxException(ERR_NEGINFO_NRTELEFONE_INVALIDO,CErroAtendimento(ERR_NEGINFO_NRTELEFONE_INVALIDO).outMsg());
    }

    if ( status.cdAreaRegistro == -1 && status.nrTelefone > 0 )
    { // se informou telefone sem código de área gera erro
        throw new TuxException(ERR_NEGINFO_CDAREAREG_INVALIDO,CErroAtendimento(ERR_NEGINFO_CDAREAREG_INVALIDO).outMsg());
    }

    if ( dados.idTipoAberturaProtocolo[0]==0 )
    {
        throw new TuxException(ERR_XML_IDTIPOABERTURAPROTOCOLO,CErroAtendimento(ERR_XML_IDTIPOABERTURAPROTOCOLO).outMsg());
    }

    if ( dados.idSistemaOrigem[0]==0 )
    {
        throw new TuxException(ERR_XML_IDSISTEMAORIGEM,CErroAtendimento(ERR_XML_IDSISTEMAORIGEM).outMsg());
    }

    // Valida dados de entrada para tipo de abertura de protocolo por CONTA
    if ( strcmp(dados.idTipoAberturaProtocolo,TIPO_ABER_PROT_CONTA)==0 )
    {
        if ( -1 == status.cdConta )
        {
            throw new TuxException(ERR_TPABERPROT_CONTA_CDCONTA,CErroAtendimento(ERR_TPABERPROT_CONTA_CDCONTA).outMsg());
        }
        if ( -1 == status.cdAreaRegistroSMS || -1 == status.nrTelefoneSMS )
        {
            throw new TuxException(ERR_TPABERPROT_PESSOA_NRTELSMS,CErroAtendimento(ERR_TPABERPROT_PESSOA_NRTELSMS).outMsg());
        }
        return;
    }

    // Valida dados de entrada para tipo de abertura de protocolo por LINHA
    if ( strcmp(dados.idTipoAberturaProtocolo,TIPO_ABER_PROT_LINHA)==0 )
    {
        if ( -1 == status.cdAreaRegistro || -1 == status.nrTelefone )
        {
            throw new TuxException(ERR_TPABERPROT_LINHA_NRTELEF,CErroAtendimento(ERR_TPABERPROT_LINHA_NRTELEF).outMsg());
        }
        return;
    }

    // Valida dados de entrada para tipo de abertura de protocolo por LINHA DE CLIENTE
    if ( strcmp(dados.idTipoAberturaProtocolo,TIPO_ABER_PROT_LINHA_CLIENTE)==0 )
    {
        if ( -1 == status.cdAreaRegistro || -1 == status.nrTelefone )
        {
            throw new TuxException(ERR_TPABERPROT_LINCLI_NRTELEF,CErroAtendimento(ERR_TPABERPROT_LINCLI_NRTELEF).outMsg());
        }
        return;
    }

    // Valida dados de entrada para tipo de abertura de protocolo por PESSOA
    if ( strcmp(dados.idTipoAberturaProtocolo,TIPO_ABER_PROT_PESSOA)==0 )
    {
        if ( -1 == status.idPessoaDePara )
        {
            throw new TuxException(ERR_TPABERPROT_PESSOA_IDPDP,CErroAtendimento(ERR_TPABERPROT_PESSOA_IDPDP).outMsg());
        }
        //if ( -1 == status.cdAreaRegistroSMS || -1 == status.nrTelefoneSMS )
        //{
        //    throw new TuxException(ERR_TPABERPROT_PESSOA_NRTELSMS,CErroAtendimento(ERR_TPABERPROT_PESSOA_NRTELSMS).outMsg());
        //}
        return;
    }
}

void cWfAtdAbreProt::CarregarDados()
{
    if ( 0 == entrada )
    {
        throw new TuxException(ERR_XML_ENTRADA,CErroAtendimento(ERR_XML_ENTRADA).outMsg());
    }

    char *p;

    if ( p=walkTree(entrada,"idTipoAberturaProtocolo",0),p )
    {
        setIdTipoAberturaProtocolo(p);
        ULOG("idTipoAberturaProtocolo=%s",dados.idTipoAberturaProtocolo);
        XMLString::release(&p);
    }

    if ( p=walkTree(entrada,"idSistemaOrigem",0),p )
    {
        setIdSistemaOrigem(p);
        ULOG("idSistemaOrigem=%s",dados.idSistemaOrigem);
        XMLString::release(&p);
    }

    if ( p=walkTree(entrada,"nrProtocolo",0),p )
    {
        setIdAtendimentoProtocolo(p);
        ULOG("idAtendimentoProtocolo=%s",dados.idAtendimentoProtocolo);
        XMLString::release(&p);
    }

    if ( p=walkTree(entrada,"cdAreaRegistro",0),p )
    {
        setCdAreaRegistro(p);
        ULOG("cdAreaRegistro=%s",dados.cdAreaRegistro);
        XMLString::release(&p);
    }

    if ( p=walkTree(entrada,"nrTelefone",0),p )
    {
        setNrTelefone(p);
        ULOG("nrTelefone=%s",dados.nrTelefone);
        XMLString::release(&p);
    }

    if ( p=walkTree(entrada,"cdAreaRegistroSMS",0),p )
    {
        setCdAreaRegistroSMS(p);
        ULOG("cdAreaRegistroSMS=%s",dados.cdAreaRegistroSMS);
        XMLString::release(&p);
    }

    if ( p=walkTree(entrada,"nrTelefoneSMS",0),p )
    {
        setNrTelefoneSMS(p);
        ULOG("nrTelefoneSMS=%s",dados.nrTelefoneSMS);
        XMLString::release(&p);
    }

    if ( p=walkTree(entrada,"idPessoaDePara",0),p )
    {
        setIdPessoaDePara(p);
        ULOG("idPessoaDePara=%s",dados.idPessoaDePara);
        XMLString::release(&p);
    }

    if ( p=walkTree(entrada,"cdConta",0),p )
    {
        setCdConta(p);
        ULOG("cdConta=%s",dados.cdConta);
        XMLString::release(&p);
    }

    if (p=walkTree( entrada, "forceUsoProtocoloNaoAberto", 0 ),p)
    {
        dados.forceUsoProtocoloNaoAberto = 1;
        XMLString::release(&p);
        ULOG("forceUsoProtocoloNaoAberto=%d",dados.forceUsoProtocoloNaoAberto);
    }

     setQtProcessoAberto("0");
     status.qtProcessoAberto = 1;


     SAFE_STRNCPY(dados.qtProcessoPendente,"0");
     status.qtProcessoPendente = 1;
}
