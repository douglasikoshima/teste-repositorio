/**
 * @modulo  Atendimento
 * @usecase 
 * @author  Roberto
 * @version $Revision: 1.1.2.3 $
 * @CVS     $Author: a5116174 $ - $Date: 2011/08/12 17:11:16 $
 **/ 

#ifndef __STWF_ACAO_PC_H__
#define __STWF_ACAO_PC_H__

struct stBaixaHistorico
{
    long idAtendimento;
    int  idBxa;
    int  inEnvEmail;
    int  inEnvSms;
    int  inEnvTel;
    char dsMsg[256];
    char dsCom[256];

    stBaixaHistorico()
    {
        this->idAtendimento = this->idBxa = 
            this->inEnvEmail = this->inEnvSms = this->inEnvTel = 0;

        memset(this->dsMsg,0,sizeof(this->dsMsg));
        memset(this->dsCom,0,sizeof(this->dsCom));
    }
};

struct stMensagemBaixa
{
    int  idBxa;
    int  idMsgBxa;
    int  idBaixaMensagem;

    stMensagemBaixa()
    {
        this->idBxa = this->idMsgBxa = this->idBaixaMensagem = 0;
    }

};

struct stAtendimentoLinhas
{
    char cdConta[256];
    char nrTelefone[256];

    stAtendimentoLinhas()
    {
        memset(cdConta,0,sizeof(cdConta));
        memset(nrTelefone,0,sizeof(nrTelefone));
    }
};

struct DadosOrdemVenda
{
    char strOrdemVenda[256];
    char idStatusSap[39];
    char vlIdReferencia[39];
    char vlLogXMLIn[2501];
};

#endif
