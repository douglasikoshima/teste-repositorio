/**
 * 
 * @modulo  Atendimento
 * @usecase Protocolo
 * @author  Cassio Garcia
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:34:00 $
 **/

#include "../include/cWfAtdAlterProtPC.h"
#include "../include/cWfAtdAlterProt.h"
#include "../../../commons/cWfAtdProtocoloException.h"

void cWfAtdAlterProt::Executar()
{
    try
    {
        CarregarDados();

        if ( 0 == dados.idAtendimentoProtocolo[0] )
        {
            throw new TuxException(ERR_XML_IDATENDIMENTOPROTOCOLO,CErroAtendimento(ERR_XML_IDATENDIMENTOPROTOCOLO).outMsg());
        }

        strcpy(dados.idUsuarioAlteracao,getIdPessoaUsuarioWeb());
        status.idUsuarioAlteracao = 1;

        cWfAtdAlterProtPC cwfatdalterprotpc;

        // Se não mandou numero de telefone de envio de SMS do cliente assume que
        // será o mesmo telefone cadastrado no FO.
        if ( strcmp(dados.operacao,OPERACAO_ALTERAR_DADOS)==0 )
        {
            cwfatdalterprotpc.AlterarDadosProtocolo(&dados,&status);
        }
        else if ( strcmp(dados.operacao,OPERACAO_INCR_TOTABRPEN)==0 )
        {
            cwfatdalterprotpc.IncrementarQuantidades(dados.idAtendimentoProtocolo,dados.incAberto,dados.incPendente);
        }
        else
        {
            throw new TuxException(ERR_NEGINFO_OPERACAO_INVALIDA,CErroAtendimento(ERR_NEGINFO_OPERACAO_INVALIDA).outMsg());
        }
    }
    catch (...)
    {
        throw;
    }
}

void cWfAtdAlterProt::AlterarDadosProtocolo()
{
    cWfAtdAlterProtPC cwfatdalterprotpc;
    cwfatdalterprotpc.AlterarDadosProtocolo(&dados,&status);
}

void cWfAtdAlterProt::IncrementarQuantidades(const char *idAtendimentoProtocolo,const char *incAberto,const char *incPendente)
{
    cWfAtdAlterProtPC cwfatdalterprotpc;
    cwfatdalterprotpc.IncrementarQuantidades(idAtendimentoProtocolo,incAberto,incPendente,idPessoaUsuarioWeb);
}

void cWfAtdAlterProt::IncrementarQuantidades(const char *idAtendimentoProtocolo,const char *incAberto,const char *incPendente,const char *idUsuarioAlteracao)
{
    cWfAtdAlterProtPC cwfatdalterprotpc;
    cwfatdalterprotpc.IncrementarQuantidades(idAtendimentoProtocolo,incAberto,incPendente,idUsuarioAlteracao);
}

void cWfAtdAlterProt::DecrementarPendentes(const char *idAtendimentoProtocolo,const char *incPendente,const char *idPessoaUsuario)
{
    cWfAtdAlterProtPC cwfatdalterprotpc;
    cwfatdalterprotpc.DecrementarPendentes(idAtendimentoProtocolo,incPendente,idPessoaUsuario);
}

void cWfAtdAlterProt::DecrementarPendentes(const char *idAtendimentoProtocolo,const char *incPendente,const char *idPessoaUsuario,const char *idUsuarioAlteracao)
{
    cWfAtdAlterProtPC cwfatdalterprotpc;
    cwfatdalterprotpc.DecrementarPendentes(idAtendimentoProtocolo,incPendente,idUsuarioAlteracao);
}

void cWfAtdAlterProt::CarregarDados()
{
    if ( 0 == entrada )
    {
        throw new TuxException(ERR_XML_ENTRADA,CErroAtendimento(ERR_XML_ENTRADA).outMsg());
    }

    char *p;

    if ( p=walkTree(entrada,"operacao",0),p )
    {
        setOperacao(p);
        XMLString::release(&p);
    }

    if ( p=walkTree(entrada,"nrProtocolo",0),p )
    {
        setIdAtendimentoProtocolo(p);
        XMLString::release(&p);
    }

    if ( p=walkTree(entrada,"cdAreaRegistro",0),p )
    {
        setCdAreaRegistro(p);
        XMLString::release(&p);
    }

    if ( p=walkTree(entrada,"nrTelefone",0),p )
    {
        setNrTelefone(p);
        XMLString::release(&p);
    }

    if ( p=walkTree(entrada,"idSistemaOrigem",0),p )
    {
        setIdSistemaOrigem(p);
        XMLString::release(&p);
    }

    if ( p=walkTree(entrada,"idPessoaDePara",0),p )
    {
        setIdPessoaDePara(p);
        XMLString::release(&p);
    }

    if ( p=walkTree(entrada,"cdConta",0),p )
    {
        setCdConta(p);
        XMLString::release(&p);
    }

    if ( p=walkTree(entrada,"dtAbertura",0),p )
    {
        setDtAbertura(p);
        XMLString::release(&p);
    }

    if ( p=walkTree(entrada,"dtEncerramento",0),p )
    {
        setDtEncerramento(p);
        XMLString::release(&p);
    }

    if ( p=walkTree(entrada,"qtProcessoAberto",0),p )
    {
        setQtProcessoAberto(p);
        XMLString::release(&p);
    }

    if ( p=walkTree(entrada,"qtProcessoPendente",0),p )
    {
        setQtProcessoPendente(p);
        XMLString::release(&p);
    }

    if ( p=walkTree(entrada,"idLinhaTelefonica",0),p )
    {
        setIdLinhaTelefonica(p);
        XMLString::release(&p);
    }

    if ( p=walkTree(entrada,"cdAreaRegistroSMS",0),p )
    {
        setCdAreaRegistroSMS(p);
        XMLString::release(&p);
    }

    if ( p=walkTree(entrada,"nrTelefoneSMS",0),p )
    {
        setNrTelefoneSMS(p);
        XMLString::release(&p);
    }

    if ( p=walkTree(entrada,"dtEnvioSMS",0),p )
    {
        setDtEnvioSMS(p);
        XMLString::release(&p);
    }

    if ( p=walkTree(entrada,"dsErroSMS",0),p )
    {
        setDsErroSMS(p);
        XMLString::release(&p);
    }

    if ( p=walkTree(entrada,"idTipoAberturaProtocolo",0),p )
    {
        setIdTipoAberturaProtocolo(p);
        XMLString::release(&p);
    }

    if ( p=walkTree(entrada,"idUsuarioAlteracao",0),p )
    {
        setIdUsuarioAlteracao(p);
        XMLString::release(&p);
    }

    if ( p=walkTree(entrada,"dtUltimaAlteracao",0),p )
    {
        setDtUltimaAlteracao(p);
        XMLString::release(&p);
    }

    if ( p=walkTree(entrada,"incrementarAbertos",0),p )
    {
        setIncAberto(p);
        XMLString::release(&p);
    }

    if ( p=walkTree(entrada,"incrementarPendentes",0),p )
    {
        setIncPendente(p);
        XMLString::release(&p);
    }
}
