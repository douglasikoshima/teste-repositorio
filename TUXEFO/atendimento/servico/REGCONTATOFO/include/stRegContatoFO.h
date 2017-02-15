/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Cassio Garcia
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:44 $
 **/

#ifndef __STREGCONTATOFO_H_
#define __STREGCONTATOFO_H_

#ifdef WIN32
#pragma warning(disable:4786)
#endif

#include <string>
using namespace std;

struct st_RegContatoFO
{
    char *idContato;
    char *cdContato;
    char *idSegmentacao;
    char *idProcedencia;
    char *idTipoCarteira;
    char *idCanal;
    char *tpOperacao;
    char *idGrupoAbertura;
    char *idTipoRelacionamento;
    char *idTerminal;
    char *idPessoaDePara;
    char *ddd;
    char *nrLinha;
    char *observacao;
    char *tipoPortabilidade;
    char *sgOperadoraSolicitante;
    char *dtJanelaPortout;
    char *dsOperacao;
    char *nrProtocoloPortabilidade;

    st_RegContatoFO()
    {
        idContato = cdContato = idSegmentacao = idProcedencia = 
        idTipoCarteira = idCanal = tpOperacao = idGrupoAbertura = 
        idTipoRelacionamento = idTerminal = ddd = nrLinha = 
        idPessoaDePara = observacao = sgOperadoraSolicitante =
        dtJanelaPortout = dsOperacao = nrProtocoloPortabilidade = 
        tipoPortabilidade = 0;

    }

    ~st_RegContatoFO()
    {
        if (idContato) {delete idContato;idContato=0;}
        if (cdContato) {XMLString::release(&cdContato);cdContato=0;}
        if (idSegmentacao) {XMLString::release(&idSegmentacao);idSegmentacao=0;}
        if (idProcedencia) {XMLString::release(&idProcedencia);idProcedencia=0;}
        if (idTipoCarteira) {XMLString::release(&idTipoCarteira);idTipoCarteira=0;}
        if (idCanal) {XMLString::release(&idCanal);idCanal=0;}
        if (tpOperacao) {XMLString::release(&tpOperacao);tpOperacao=0;}
        if (idGrupoAbertura) {XMLString::release(&idGrupoAbertura);idGrupoAbertura=0;}
        if (idTipoRelacionamento) {XMLString::release(&idTipoRelacionamento);idTipoRelacionamento=0;}
        if (idTerminal) {XMLString::release(&idTerminal);idTerminal=0;}
        if (ddd) {XMLString::release(&ddd);ddd=0;}
        if (nrLinha) {XMLString::release(&nrLinha);nrLinha=0;}
        if (idPessoaDePara) {XMLString::release(&idPessoaDePara);idPessoaDePara=0;}
        if (observacao) {XMLString::release(&observacao);observacao=0;}
        if (sgOperadoraSolicitante) {XMLString::release(&sgOperadoraSolicitante);sgOperadoraSolicitante=0;}
        if (dtJanelaPortout) {XMLString::release(&dtJanelaPortout);dtJanelaPortout=0;}
        if (dsOperacao) {XMLString::release(&dsOperacao);dsOperacao=0;}
        if (nrProtocoloPortabilidade) {XMLString::release(&nrProtocoloPortabilidade);nrProtocoloPortabilidade=0;}
        if (tipoPortabilidade) {XMLString::release(&tipoPortabilidade);tipoPortabilidade=0;}
    }
};

#endif //#ifndef __STREGCONTATOFO_H_
