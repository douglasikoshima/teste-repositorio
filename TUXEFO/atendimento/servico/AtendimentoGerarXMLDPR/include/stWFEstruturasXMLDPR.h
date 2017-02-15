/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Cassio M Garcia
 * @version $Revision: 1.1.2.5.6.2 $
 * @CVS     $Author: a5114878 $ - $Date: 2013/02/06 12:36:43 $
 **/

#ifndef _STWFESTRUTURASXMLDPR_H_
    #define _STWFESTRUTURASXMLDPR_H_

//
//==============================================================================
struct AtendimentoContatoDPR
{
    string idAtendimento;
    string nmContato;
    string opCode;

    void setIdAtendimento(long dado) { char temp[21];temp[sizeof(temp)-1]=0;sprintf(temp,"%ld",dado); if(temp[sizeof(temp)-1]) throw new TuxException("04E0001","AtendimentoContatoDPR:idAtendimento muito grande!"); idAtendimento=temp; }
    void setNmContato(char *dado) { nmContato=dado; }
    void setOpCode(int dado) { char temp[21];temp[sizeof(temp)-1]=0;sprintf(temp,"%d",dado); if(temp[sizeof(temp)-1]) throw new TuxException("04E0001","AtendimentoContatoDPR:opCode muito grande!"); opCode=temp;}

    char *getIdAtendimento() { return (char*)idAtendimento.c_str(); }
    char *getNmContato() { return (char*)nmContato.c_str(); }
    char *getOpCode() { return (char*)opCode.c_str(); }

    bool isEmpty() { return idAtendimento.size() 
                         || nmContato.size() 
                            ? false:true; }
};
//
//==============================================================================
struct AtendimentoObservacaoDPR
{
    string idAtendimento;
    string dsObservacao;
    string opCode;

    void setIdAtendimento(long dado) { char temp[21];temp[sizeof(temp)-1]=0;sprintf(temp,"%ld",dado); if(temp[sizeof(temp)-1]) throw new TuxException("04E0001","AtendimentoObservacaoDPR: idAtendimento muito grande!"); idAtendimento=temp; }
    void setDsObservacao(char *dado) { dsObservacao=dado; }
    void setOpCode(int dado) { char temp[21];temp[sizeof(temp)-1]=0;sprintf(temp,"%d",dado); if(temp[sizeof(temp)-1]) throw new TuxException("04E0001","AtendimentoObservacaoDPR:opCode muito grande!"); opCode=temp;}

    char *getIdAtendimento() { return (char*)idAtendimento.c_str(); }
    char *getDsObservacao() { return (char*)dsObservacao.c_str(); }
    char *getOpCode() { return (char*)opCode.c_str(); }

    bool isEmpty() { return idAtendimento.size() 
                         || dsObservacao.size() 
                            ? false:true; }
};
//
//==============================================================================
struct AtendimentoLinhaDPR
{
    string idAtendimento;
    string idPessoaLinhaHistorico;
    string idEstadoLinha;
    string opCode;

    void setIdAtendimento(long dado) { char temp[21];temp[sizeof(temp)-1]=0;sprintf(temp,"%ld",dado); if(temp[sizeof(temp)-1]) throw new TuxException("04E0001","AtendimentoLinhaDPR:idAtendimento muito grande!"); idAtendimento=temp; }
    void setIdPessoaLinhaHistorico(long dado) { char temp[21];temp[sizeof(temp)-1]=0;sprintf(temp,"%ld",dado); if(temp[sizeof(temp)-1]) throw new TuxException("04E0001","AtendimentoLinhaDPR:idPessoaLinhaHistorico muito grande!");idPessoaLinhaHistorico=temp; }
    void setIdEstadoLinha(int dado) { char temp[21];temp[sizeof(temp)-1]=0;sprintf(temp,"%d",dado); if(temp[sizeof(temp)-1]) throw new TuxException("04E0001","AtendimentoLinhaDPR:idEstadoLinha muito grande!");idEstadoLinha=temp; }
    void setOpCode(int dado) { char temp[21];temp[sizeof(temp)-1]=0;sprintf(temp,"%d",dado); if(temp[sizeof(temp)-1]) throw new TuxException("04E0001","AtendimentoLinhaDPR:opCode muito grande!"); opCode=temp;}

    char *getIdAtendimento() { return (char*)idAtendimento.c_str(); }
    char *getIdPessoaLinhaHistorico() { return (char*)idPessoaLinhaHistorico.c_str(); }
    char *getIdEstadoLinha() { return (char*)idEstadoLinha.c_str(); }
    char *getOpCode() { return (char*)opCode.c_str(); }

    bool isEmpty() { return idAtendimento.size() 
                         || idPessoaLinhaHistorico.size() 
                         || idEstadoLinha.size() 
                            ? false:true; }
};
//
//==============================================================================
struct AtendimentoOrigemDPR
{
    string idAtendimento;
    string idAtendimentoOrigem;
    string opCode;

    void setIdAtendimento(long dado) { char temp[21];temp[sizeof(temp)-1]=0;sprintf(temp,"%ld",dado); if(temp[sizeof(temp)-1]) throw new TuxException("04E0001","AtendimentoOrigemDPR:idAtendimento muito grande!"); idAtendimento=temp; }
    void setIdAtendimentoOrigem(long dado) { char temp[21];temp[sizeof(temp)-1]=0;sprintf(temp,"%d",dado); if(temp[sizeof(temp)-1]) throw new TuxException("04E0001","AtendimentoOrigemDPR:idAtendimentoOrigem muito grande!"); idAtendimentoOrigem=temp; }
    void setOpCode(int dado) { char temp[21];temp[sizeof(temp)-1]=0;sprintf(temp,"%d",dado); if(temp[sizeof(temp)-1]) throw new TuxException("04E0001","AtendimentoOrigemDPR:opCode muito grande!"); opCode=temp;}

    char *getIdAtendimento() { return (char*)idAtendimento.c_str(); }
    char *getIdAtendimentoOrigem() { return (char*)idAtendimentoOrigem.c_str(); }
    char *getOpCode() { return (char*)opCode.c_str(); }

    bool isEmpty() { return idAtendimento.size() 
                         || idAtendimentoOrigem.size() 
                            ? false:true; }
};
//
//==============================================================================
struct AtendimentoHierarquiaDPR
{
    string idAtendimento;
    string idAtendimentoPai;
    string opCode;

    void setIdAtendimento(long dado) { char temp[21];temp[sizeof(temp)-1]=0;sprintf(temp,"%ld",dado); if(temp[sizeof(temp)-1]) throw new TuxException("04E0001","AtendimentoHierarquiaDPR:idAtendimento muito grande!"); idAtendimento=temp; }
    void setIdAtendimentoPai(long dado) { char temp[21];temp[sizeof(temp)-1]=0;sprintf(temp,"%ld",dado); if(temp[sizeof(temp)-1]) throw new TuxException("04E0001","AtendimentoHierarquiaDPR:idAtendimentoPai muito grande!"); idAtendimentoPai=temp; }
    void setOpCode(int dado) { char temp[21];temp[sizeof(temp)-1]=0;sprintf(temp,"%d",dado); if(temp[sizeof(temp)-1]) throw new TuxException("04E0001","AtendimentoHierarquiaDPR:opCode muito grande!"); opCode=temp;}

    char *getIdAtendimento() { return (char*)idAtendimento.c_str(); }
    char *getIdAtendimentoPai() { return (char*)idAtendimentoPai.c_str(); }
    char *getOpCode() { return (char*)opCode.c_str(); }

    bool isEmpty() { return idAtendimento.size() 
                         || idAtendimentoPai.size() 
                            ? false:true; }
};
//
//==============================================================================
struct AtendimentoDPR
{
    string idAtendimento;
    string dtAbertura;
    string dtPrazoFinalInterno;
    string vlPesoAtendimento;
    string idContato;
    string idCanal;
    string idProcedencia;
    string idTipoCarteira;
    string idSegmentacao;
    string qtInsistencia;
    string opCode;

    void setIdAtendimento(long dado) { char temp[21];temp[sizeof(temp)-1]=0;sprintf(temp,"%ld",dado); if(temp[sizeof(temp)-1]) throw new TuxException("04E0001","AtendimentoDPR:idAtendimento muito grande!"); idAtendimento=temp; }
    void setDtAbertura(char *dado) { dtAbertura=dado; }
    void setDtPrazoFinalInterno(char *dado) { dtPrazoFinalInterno=dado; }
    void setVlPesoAtendimento(char *dado) { vlPesoAtendimento=dado; }
    void setVlPesoAtendimento(double dado) 
    { 
        char temp[512];
        temp[sizeof(temp)-1] = 0;
        sprintf(temp,"%f",dado); 
        if (temp[sizeof(temp)-1])
        {
            throw new TuxException("04E0001","AtendimentoDPR:vlPesoAtendimento muito grande!");
        }
        vlPesoAtendimento=temp; 
    }
    void setIdContato(int dado) { char temp[21];temp[sizeof(temp)-1]=0;sprintf(temp,"%d",dado); if (temp[sizeof(temp)-1]) throw new TuxException("04E0001","AtendimentoDPR:idContato muito grande!"); idContato=temp; }
    void setIdCanal(int dado) { char temp[21];temp[sizeof(temp)-1]=0;sprintf(temp,"%d",dado); if(temp[sizeof(temp)-1]) throw new TuxException("04E0001","AtendimentoDPR:idCanal muito grande!"); idCanal=temp; }
    void setIdProcedencia(int dado) { char temp[21];temp[sizeof(temp)-1]=0;sprintf(temp,"%d",dado); if(temp[sizeof(temp)-1]) throw new TuxException("04E0001","AtendimentoDPR:idProcedencia muito grande!"); idProcedencia=temp; }
    void setIdTipoCarteira(int dado) { char temp[21];temp[sizeof(temp)-1]=0;sprintf(temp,"%d",dado); if(temp[sizeof(temp)-1]) throw new TuxException("04E0001","AtendimentoDPR:idTipoCarteira muito grande!"); idTipoCarteira=temp; }
    void setIdSegmentacao(int dado) { char temp[21];temp[sizeof(temp)-1]=0;sprintf(temp,"%d",dado); if(temp[sizeof(temp)-1]) throw new TuxException("04E0001","AtendimentoDPR:idSegmentacao muito grande!");idSegmentacao=temp; }
    void setQtInsistencia(int dado) { char temp[21];temp[sizeof(temp)-1]=0;sprintf(temp,"%d",dado); if(temp[sizeof(temp)-1]) throw new TuxException("04E0001","AtendimentoDPR:qtInsistencia muito grande!");qtInsistencia=temp; }
    void setOpCode(int dado) { char temp[21];temp[sizeof(temp)-1]=0;sprintf(temp,"%d",dado); if(temp[sizeof(temp)-1]) throw new TuxException("04E0001","AtendimentoDPR:opCode muito grande!"); opCode=temp;}

    char *getIdAtendimento() { return (char*)idAtendimento.c_str(); }
    char *getDtAbertura() { return (char*)dtAbertura.c_str(); }
    char *getDtPrazoFinalInterno() { return (char*)dtPrazoFinalInterno.c_str(); }
    char *getVlPesoAtendimento() { return (char*)vlPesoAtendimento.c_str(); }
    char *getIdContato() { return (char*)idContato.c_str(); }
    char *getIdCanal() { return (char*)idCanal.c_str(); }
    char *getIdProcedencia() { return (char*)idProcedencia.c_str(); }
    char *getIdTipoCarteira() { return (char*)idTipoCarteira.c_str(); }
    char *getIdSegmentacao() { return (char*)idSegmentacao.c_str(); }
    char *getQtInsistencia() { return (char*)qtInsistencia.c_str(); }
    char *getOpCode() { return (char*)opCode.c_str(); }

    bool isEmpty() { return idAtendimento.size() 
                         || dtAbertura.size() 
                         || dtPrazoFinalInterno.size() 
                         || vlPesoAtendimento.size() 
                         || idContato.size() 
                         || idCanal.size() 
                         || idProcedencia.size() 
                         || idTipoCarteira.size() 
                         || idSegmentacao.size() 
                         || qtInsistencia.size()
                            ? false:true; }
};
//
//==============================================================================
struct AtendimentoPessoaDPR
{
    string idAtendimento;
    string idAtendimentoPessoa;
    string idPessoaDePara;
    string idTipoRelacionamento;
    string opCode;

    void setIdAtendimento(long dado) { char temp[21];temp[sizeof(temp)-1]=0;sprintf(temp,"%l",dado); if(temp[sizeof(temp)-1]) throw new TuxException("04E0001","AtendimentoPessoaDPR:idAtendimento muito grande!"); idAtendimento=temp; }
    void setIdAtendimentoPessoa(long dado) { char temp[21];temp[sizeof(temp)-1]=0;sprintf(temp,"%ld",dado); if(temp[sizeof(temp)-1]) throw new TuxException("04E0001","AtendimentoPessoaDPR:idAtendimentoPessoa muito grande!"); idAtendimentoPessoa=temp; }
    void setIdPessoaDePara(int dado) { char temp[21];temp[sizeof(temp)-1]=0;sprintf(temp,"%d",dado); if(temp[sizeof(temp)-1]) throw new TuxException("04E0001","AtendimentoPessoaDPR:idPessoaDePara muito grande!");idPessoaDePara=temp; }
    void setIdTipoRelacionamento(int dado) { char temp[21];temp[sizeof(temp)-1]=0;sprintf(temp,"%d",dado); if(temp[sizeof(temp)-1]) throw new TuxException("04E0001","AtendimentoPessoaDPR:idTipoRelacionamento muito grande!");idTipoRelacionamento=temp; }
    void setOpCode(int dado) { char temp[21];temp[sizeof(temp)-1]=0;sprintf(temp,"%d",dado); if(temp[sizeof(temp)-1]) throw new TuxException("04E0001","AtendimentoPessoaDPR:opCode muito grande!"); opCode=temp;}

    char *getIdAtendimento() { return (char*)idAtendimento.c_str(); }
    char *getIdAtendimentoPessoa() { return (char*)idAtendimentoPessoa.c_str(); }
    char *getIdPessoaDePara() { return (char*)idPessoaDePara.c_str(); }
    char *getIdTipoRelacionamento() { return (char*)idTipoRelacionamento.c_str(); }
    char *getOpCode() { return (char*)opCode.c_str(); }

    bool isEmpty() { return idAtendimento.size() 
                         || idAtendimentoPessoa.size() 
                         || idPessoaDePara.size() 
                         || idTipoRelacionamento.size() 
                            ? false:true; }
};
//
//==============================================================================
struct AtendimentoContatoComunicDPR
{
    string idAtendimento;
    string idAtendimentoContatoComunic;
    string idTipoComunicacao;
    string dsComunicacao;
    string nrOrdemutilizacao;
    string opCode;

    void setIdAtendimento(long dado) { char temp[21];temp[sizeof(temp)-1]=0;sprintf(temp,"%ld",dado); if(temp[sizeof(temp)-1]) throw new TuxException("04E0001","AtendimentoContatoComunicDPR:idAtendimento muito grande!"); idAtendimento=temp; }
    void setIdAtendimentoContatoComunic(long dado) { char temp[21];temp[sizeof(temp)-1]=0;sprintf(temp,"%ld",dado); if(temp[sizeof(temp)-1]) throw new TuxException("04E0001","AtendimentoContatoComunicDPR:idAtendimentoContatoComunic muito grande!"); idAtendimentoContatoComunic=temp; }
    void setIdTipoComunicacao(int dado) { char temp[21];temp[sizeof(temp)-1]=0;sprintf(temp,"%d",dado); if(temp[sizeof(temp)-1]) throw new TuxException("04E0001","AtendimentoContatoComunicDPR:idTipoComunicacao muito grande!");idTipoComunicacao=temp; }
    void setDsComunicacao(char *dado) { dsComunicacao=dado; }
    void setNrOrdemutilizacao(int dado) { char temp[21];temp[sizeof(temp)-1]=0;sprintf(temp,"%d",dado); if(temp[sizeof(temp)-1]) throw new TuxException("04E0001","AtendimentoContatoComunicDPR:nrOrdemutilizacao muito grande!");nrOrdemutilizacao=temp; }
    void setOpCode(int dado) { char temp[21];temp[sizeof(temp)-1]=0;sprintf(temp,"%d",dado); if(temp[sizeof(temp)-1]) throw new TuxException("04E0001","AtendimentoContatoComunicDPR:opCode muito grande!"); opCode=temp;}

    char *getIdAtendimento() { return (char*)idAtendimento.c_str(); }
    char *getIdAtendimentoContatoComunic() { return (char*)idAtendimentoContatoComunic.c_str(); }
    char *getIdTipoComunicacao() { return (char*)idTipoComunicacao.c_str(); }
    char *getDsComunicacao() { return (char*)dsComunicacao.c_str(); }
    char *getNrOrdemutilizacao() { return (char*)nrOrdemutilizacao.c_str(); }
    char *getOpCode() { return (char*)opCode.c_str(); }

    bool isEmpty() { return idAtendimento.size() 
                         || idAtendimentoContatoComunic.size() 
                         || idTipoComunicacao.size() 
                         || dsComunicacao.size() 
                         || nrOrdemutilizacao.size() 
                            ? false:true; }
};
//
//==============================================================================
struct AtendimentoFRMDPR
{
    string idAtendimento;
    string idAtendimentoFrm;
    string idCampo;
    string opCode;

    void setIdAtendimento(long dado) { char temp[21];temp[sizeof(temp)-1]=0;sprintf(temp,"%ld",dado); if(temp[sizeof(temp)-1]) throw new TuxException("04E0001","AtendimentoFRMDPR:idAtendimento muito grande!");idAtendimento=temp; }
    void setIdAtendimentoFrm(long dado) { char temp[21];temp[sizeof(temp)-1]=0;sprintf(temp,"%ld",dado); if(temp[sizeof(temp)-1]) throw new TuxException("04E0001","AtendimentoFRMDPR:idAtendimentoFrm muito grande!");idAtendimentoFrm=temp; }
    void setIdCampo(int dado) { char temp[21];temp[sizeof(temp)-1]=0;sprintf(temp,"%d",dado); if(temp[sizeof(temp)-1]) throw new TuxException("04E0001","AtendimentoFRMDPR:idCampo muito grande!");idCampo=temp; }
    void setOpCode(int dado) { char temp[21];temp[sizeof(temp)-1]=0;sprintf(temp,"%d",dado); if(temp[sizeof(temp)-1]) throw new TuxException("04E0001","AtendimentoFRMDPR:opCode muito grande!"); opCode=temp;}

    char *getIdAtendimento() { return (char*)idAtendimento.c_str(); }
    char *getIdAtendimentoFrm() { return (char*)idAtendimentoFrm.c_str(); }
    char *getIdCampo() { return (char*)idCampo.c_str(); }
    char *getOpCode() { return (char*)opCode.c_str(); }

    bool isEmpty() { return idAtendimento.size() 
                         || idAtendimentoFrm.size() 
                         || idCampo.size() 
                            ? false:true; }
};
//
//==============================================================================
struct AtendimentoFRMCampoDPR
{
    // Apesar dos campos idAtendimento e idCampo não fazerem parte da tabela
    // ATENDIMENTOFRMCAMPO eles são exportados junto com este VO para o TIBCO
    // processar os dados dinâmicos.
    //
    string idAtendimento;
    string idAtendimentoFrmCampo;
    string idAtendimentoFrm;
    string idDominio;
    string idCampo; 
    string dsValor;
    string opCode;

    void setIdAtendimentoFrmCampo(long dado) { char temp[21];temp[sizeof(temp)-1]=0;sprintf(temp,"%ld",dado); if(temp[sizeof(temp)-1]) throw new TuxException("04E0001","AtendimentoFRMCampoDPR:idAtendimentoFrmCampo muito grande!"); idAtendimentoFrmCampo=temp; }
    void setIdAtendimentoFrm(long dado) { char temp[21];temp[sizeof(temp)-1]=0;sprintf(temp,"%ld",dado); if(temp[sizeof(temp)-1]) throw new TuxException("04E0001","AtendimentoFRMCampoDPR:idAtendimentoFrm muito grande!"); idAtendimentoFrm=temp; }
    void setIdAtendimento(long dado) { char temp[21];temp[sizeof(temp)-1]=0;sprintf(temp,"%ld",dado); if(temp[sizeof(temp)-1]) throw new TuxException("04E0001","AtendimentoFRMDPR:idAtendimento muito grande!"); idAtendimento=temp; }
    void setIdDominio(int dado) { char temp[21];temp[sizeof(temp)-1]=0;sprintf(temp,"%d",dado); if(temp[sizeof(temp)-1]) throw new TuxException("04E0001","AtendimentoFRMCampoDPR:idDominio muito grande!");idDominio=temp; }
    void setIdCampo(int dado) { char temp[21];temp[sizeof(temp)-1]=0;sprintf(temp,"%d",dado); if(temp[sizeof(temp)-1]) throw new TuxException("04E0001","AtendimentoFRMCampoDPR:idCampo muito grande!");idCampo=temp; }
    void setDsValor(char *dado) { dsValor=dado; }
    void setOpCode(int dado) { char temp[21];temp[sizeof(temp)-1]=0;sprintf(temp,"%d",dado); if(temp[sizeof(temp)-1]) throw new TuxException("04E0001","AtendimentoFRMCampoDPR:opCode muito grande!"); opCode=temp;}

    char *getIdAtendimento() { return (char*)idAtendimento.c_str(); }
    char *getIdAtendimentoFrmCampo() { return (char*)idAtendimentoFrmCampo.c_str(); }
    char *getIdAtendimentoFrm() { return (char*)idAtendimentoFrm.c_str(); }
    char *getIdCampo() { return (char*)idCampo.c_str(); }
    char *getIdDominio() { return (char*)idDominio.c_str(); }
    char *getDsValor() { return (char*)dsValor.c_str(); }
    char *getOpCode() { return (char*)opCode.c_str(); }

    bool isEmpty() { return idAtendimento.size() 
                         || idAtendimentoFrmCampo.size() 
                         || idAtendimentoFrm.size() 
                         || idDominio.size() 
                         || dsValor.size() 
                            ? false:true; }
};
//
//==============================================================================
struct AtendimentoBaixaHistoricoDPR
{
    string idAtendimento;
    string idAtendimentoBaixaHistorico;
    string idBaixa;
    string dtBaixa;
    string opCode;

    void setIdAtendimento(long dado) { char temp[21];temp[sizeof(temp)-1]=0;sprintf(temp,"%ld",dado); if(temp[sizeof(temp)-1]) throw new TuxException("04E0001","AtendimentoBaixaHistoricoDPR: idAtendimento muito grande!");idAtendimento=temp; }
    void setIdAtendimentoBaixaHistorico(long dado) { char temp[21];temp[sizeof(temp)-1]=0;sprintf(temp,"%ld",dado); if(temp[sizeof(temp)-1]) throw new TuxException("04E0001","AtendimentoBaixaHistoricoDPR:idAtendimentoBaixaHistorico muito grande!");idAtendimentoBaixaHistorico=temp; }
    void setIdBaixa(int dado) { char temp[21];temp[sizeof(temp)-1]=0;sprintf(temp,"%d",dado); if(temp[sizeof(temp)-1]) throw new TuxException("04E0001","AtendimentoBaixaHistoricoDPR:idBaixa muito grande!");idBaixa=temp; }
    void setDtBaixa(char *dado) { dtBaixa=dado; }
    void setOpCode(int dado) { char temp[21];temp[sizeof(temp)-1]=0;sprintf(temp,"%d",dado); if(temp[sizeof(temp)-1]) throw new TuxException("04E0001","AtendimentoBaixaHistoricoDPR:opCode muito grande!"); opCode=temp;}

    char *getIdAtendimento() { return (char*)idAtendimento.c_str(); }
    char *getIdAtendimentoBaixaHistorico() { return (char*)idAtendimentoBaixaHistorico.c_str(); }
    char *getIdBaixa() { return (char*)idBaixa.c_str(); }
    char *getDtBaixa() { return (char*)dtBaixa.c_str(); }
    char *getOpCode() { return (char*)opCode.c_str(); }

    bool isEmpty() { return idAtendimento.size() 
                         || idAtendimentoBaixaHistorico.size() 
                         || idBaixa.size() 
                         || dtBaixa.size() 
                            ? false:true; }
};
//
//==============================================================================
struct AtendimentoFechamentoDPR
{
    string idAtendimento;
    string idAndamento;
    string dtFechamento;
    string idPessoaUsuario;
    string opCode;

    void setIdAtendimento(long dado) { char temp[21];temp[sizeof(temp)-1]=0;sprintf(temp,"%ld",dado); if(temp[sizeof(temp)-1]) throw new TuxException("04E0001","AtendimentoFechamentoDPR: idAtendimento muito grande!"); idAtendimento=temp; }
    void setIdAndamento(long dado) { char temp[21];temp[sizeof(temp)-1]=0;sprintf(temp,"%ld",dado); if(temp[sizeof(temp)-1]) throw new TuxException("04E0001","AtendimentoFechamentoDPR:idAndamento muito grande!");idAndamento=temp; }
    void setDtFechamento(char *dado) { dtFechamento=dado; }
    void setIdPessoaUsuario(int dado) { char temp[21];temp[sizeof(temp)-1]=0;sprintf(temp,"%d",dado); if(temp[sizeof(temp)-1]) throw new TuxException("04E0001","AtendimentoFechamentoDPR:idPessoaUsuario muito grande!");idPessoaUsuario=temp; }
    void setOpCode(int dado) { char temp[21];temp[sizeof(temp)-1]=0;sprintf(temp,"%d",dado); if(temp[sizeof(temp)-1]) throw new TuxException("04E0001","AtendimentoFechamentoDPR:opCode muito grande!"); opCode=temp;}
    
    char *getIdAtendimento() { return (char*)idAtendimento.c_str(); }
    char *getIdAndamento() { return (char*)idAndamento.c_str(); }
    char *getDtFechamento() { return (char*)dtFechamento.c_str(); }
    char *getIdPessoaUsuario() { return (char*)idPessoaUsuario.c_str(); }
    char *getOpCode() { return (char*)opCode.c_str(); }

    bool isEmpty() { return idAtendimento.size() 
                         || idAndamento.size() 
                         || dtFechamento.size() 
                         || idPessoaUsuario.size() 
                            ? false:true; }
};
//
//==============================================================================
struct AtendimentoGrupoAtualDPR
{
    string idAtendimento;
    string idGrupo;
    string opCode;

    void setIdAtendimento(long dado) { char temp[21];temp[sizeof(temp)-1]=0;sprintf(temp,"%ld",dado); if(temp[sizeof(temp)-1]) throw new TuxException("04E0001","AtendimentoGrupoAtualDPR: idAtendimento muito grande!"); idAtendimento=temp; }
    void setIdAtendimento(const char *dado) { idAtendimento=dado; }
    void setIdGrupo(int dado) { char temp[21];temp[sizeof(temp)-1]=0;sprintf(temp,"%d",dado); if(temp[sizeof(temp)-1]) throw new TuxException("04E0001","AtendimentoGrupoAtualDPR:idGrupo muito grande!");idGrupo=temp; }
    void setOpCode(int dado) { char temp[21];temp[sizeof(temp)-1]=0;sprintf(temp,"%d",dado); if(temp[sizeof(temp)-1]) throw new TuxException("04E0001","AtendimentoGrupoAtualDPR:opCode muito grande!"); opCode=temp;}

    char *getIdAtendimento() { return (char*)idAtendimento.c_str(); }
    char *getIdGrupo() { return (char*)idGrupo.c_str(); }
    char *getOpCode() { return (char*)opCode.c_str(); }

    bool isEmpty() { return idAtendimento.size() 
                         || idGrupo.size() 
                            ? false:true; }
};
//
//==============================================================================
struct AtendimentoUsuarioAtualDPR
{
    string idAtendimento;
    string idPessoaUsuario;
    string opCode;

    void setIdAtendimento(long dado) { char temp[21];temp[sizeof(temp)-1]=0;sprintf(temp,"%ld",dado); if(temp[sizeof(temp)-1]) throw new TuxException("04E0001","AtendimentoUsuarioAtualDPR: idAtendimento muito grande!");idAtendimento=temp; }
    void setIdPessoaUsuario(int dado) { char temp[21];temp[sizeof(temp)-1]=0;sprintf(temp,"%d",dado); if(temp[sizeof(temp)-1]) throw new TuxException("04E0001","AtendimentoUsuarioAtualDPR:idPessoaUsuario muito grande!");idPessoaUsuario=temp; }
    void setOpCode(int dado) { char temp[21];temp[sizeof(temp)-1]=0;sprintf(temp,"%d",dado); if(temp[sizeof(temp)-1]) throw new TuxException("04E0001","AtendimentoUsuarioAtualDPR:opCode muito grande!"); opCode=temp;}

    char *getIdAtendimento() { return (char*)idAtendimento.c_str(); }
    char *getIdPessoaUsuario() { return (char*)idPessoaUsuario.c_str(); }
    char *getOpCode() { return (char*)opCode.c_str(); }

    bool isEmpty() { return idAtendimento.size() 
                         || idPessoaUsuario.size() 
                            ? false:true; }
};
//
//==============================================================================
struct AtendimentoTesteDPR
{
    string idAtendimento;
    string idAtendimentoTeste;
    string dtTeste;
    string idPessoaUsuario;
    string opCode;

    void setIdAtendimento(long dado) { char temp[21];temp[sizeof(temp)-1]=0;sprintf(temp,"%ld",dado); if(temp[sizeof(temp)-1]) throw new TuxException("04E0001","AtendimentoTesteDPR: idAtendimento muito grande!");idAtendimento=temp; }
    void setIdAtendimentoTeste(long dado) { char temp[21];temp[sizeof(temp)-1]=0;sprintf(temp,"%ld",dado); if(temp[sizeof(temp)-1]) throw new TuxException("04E0001","AtendimentoTesteDPR: idAtendimentoTeste muito grande!");idAtendimentoTeste=temp; }
    void setDtTeste(char *dado) { dtTeste=dado; }
    void setIdPessoaUsuario(int dado) { char temp[21];temp[sizeof(temp)-1]=0;sprintf(temp,"%d",dado); if(temp[sizeof(temp)-1]) throw new TuxException("04E0001","AtendimentoTesteDPR:idPessoaUsuario muito grande!");idPessoaUsuario=temp; }
    void setOpCode(int dado) { char temp[21];temp[sizeof(temp)-1]=0;sprintf(temp,"%d",dado); if(temp[sizeof(temp)-1]) throw new TuxException("04E0001","AtendimentoTesteDPR:opCode muito grande!"); opCode=temp;}
    
    char *getIdAtendimento() { return (char*)idAtendimento.c_str(); }
    char *getIdAtendimentoTeste() { return (char*)idAtendimentoTeste.c_str(); }
    char *getDtTeste() { return (char*)dtTeste.c_str(); }
    char *getIdPessoaUsuario() { return (char*)idPessoaUsuario.c_str(); }
    char *getOpCode() { return (char*)opCode.c_str(); }

    bool isEmpty() { return idAtendimento.size()
                         || idAtendimentoTeste.size()
                         || dtTeste.size()
                         || idPessoaUsuario.size()
                            ? false:true; }
};
//
//==============================================================================
struct AtendimentoMensagemDPR
{
    string idAtendimento;
    string idAtendimentoMensagem;
    string idMensagemAtendimento;
    string dtMensagem;
    string opCode;

    void setIdAtendimento(long dado) { char temp[21];temp[sizeof(temp)-1]=0;sprintf(temp,"%ld",dado); if(temp[sizeof(temp)-1]) throw new TuxException("04E0001","AtendimentoMensagemDPR: idAtendimento muito grande!"); idAtendimento=temp; }
    void setIdAtendimentoMensagem(long dado) { char temp[21];temp[sizeof(temp)-1]=0;sprintf(temp,"%ld",dado); if(temp[sizeof(temp)-1]) throw new TuxException("04E0001","AtendimentoMensagemDPR: idAtendimentoMensagem muito grande!"); idAtendimentoMensagem=temp; }
    void setIdMensagemAtendimento(int dado) { char temp[21];temp[sizeof(temp)-1]=0;sprintf(temp,"%d",dado); if(temp[sizeof(temp)-1]) throw new TuxException("04E0001","AtendimentoMensagemDPR:idMensagemAtendimento muito grande!");idMensagemAtendimento=temp; }
    void setDtMensagem(char *dado) { dtMensagem=dado; }
    void setOpCode(int dado) { char temp[21];temp[sizeof(temp)-1]=0;sprintf(temp,"%d",dado); if(temp[sizeof(temp)-1]) throw new TuxException("04E0001","AtendimentoMensagemDPR:opCode muito grande!"); opCode=temp;}
    
    char *getIdAtendimento() { return (char*)idAtendimento.c_str(); }
    char *getIdAtendimentoMensagem() { return (char*)idAtendimentoMensagem.c_str(); }
    char *getIdMensagemAtendimento() { return (char*)idMensagemAtendimento.c_str(); }
    char *getDtMensagem() { return (char*)dtMensagem.c_str(); }
    char *getOpCode() { return (char*)opCode.c_str(); }

    bool isEmpty() { return idAtendimento.size() 
                         || idAtendimentoMensagem.size() 
                         || idMensagemAtendimento.size() 
                         || dtMensagem.size() 
                            ? false:true; }
};
//
//==============================================================================
struct AndamentoDPR
{
    string idAtendimento;
    string idAndamento;
    string idPessoaUsuario;
    string dtAndamento;
    string idAtividade;
    string idAgrupamentoEstadoTpProc;
    string idGrupo;
    string opCode;

    void setIdAtendimento(long dado) { char temp[21];temp[sizeof(temp)-1]=0;sprintf(temp,"%ld",dado); if(temp[sizeof(temp)-1]) throw new TuxException("04E0001","AndamentoDPR: idAtendimento muito grande!"); idAtendimento=temp; }
    void setIdAndamento(long dado) { char temp[21];temp[sizeof(temp)-1]=0;sprintf(temp,"%d",dado); if(temp[sizeof(temp)-1]) throw new TuxException("04E0001","AndamentoDPR:idAndamento muito grande!");idAndamento=temp; }
    void setIdPessoaUsuario(int dado) { char temp[21];temp[sizeof(temp)-1]=0;sprintf(temp,"%d",dado); if(temp[sizeof(temp)-1]) throw new TuxException("04E0001","AndamentoDPR:idPessoaUsuario muito grande!");idPessoaUsuario=temp; }
    void setDtAndamento(char *dado) { dtAndamento=dado; }
    void setIdAtividade(int dado) { char temp[21];temp[sizeof(temp)-1]=0;sprintf(temp,"%d",dado); if(temp[sizeof(temp)-1]) throw new TuxException("04E0001","AndamentoDPR:idAtividade muito grande!");idAtividade=temp; }
    void setIdAgrupamentoEstadoTpProc(int dado) { char temp[21];temp[sizeof(temp)-1]=0;sprintf(temp,"%d",dado); if(temp[sizeof(temp)-1]) throw new TuxException("04E0001","AndamentoDPR:idAgrupamentoEstadoTpProc muito grande!");idAgrupamentoEstadoTpProc=temp; }
    void setIdGrupo(int dado) { char temp[21];temp[sizeof(temp)-1]=0;sprintf(temp,"%d",dado); if(temp[sizeof(temp)-1]) throw new TuxException("04E0001","AndamentoDPR:idGrupo muito grande!");idGrupo=temp; }
    void setOpCode(int dado) { char temp[21];temp[sizeof(temp)-1]=0;sprintf(temp,"%d",dado); if(temp[sizeof(temp)-1]) throw new TuxException("04E0001","AndamentoDPR:opCode muito grande!"); opCode=temp;}
    
    char *getIdAtendimento() { return (char*)idAtendimento.c_str(); }
    char *getIdAndamento() { return (char*)idAndamento.c_str(); }
    char *getIdPessoaUsuario() { return (char*)idPessoaUsuario.c_str(); }
    char *getDtAndamento() { return (char*)dtAndamento.c_str(); }
    char *getIdAtividade() { return (char*)idAtividade.c_str(); }
    char *getIdAgrupamentoEstadoTpProc() { return (char*)idAgrupamentoEstadoTpProc.c_str(); }
    char *getIdGrupo() { return (char*)idGrupo.c_str(); }
    char *getOpCode() { return (char*)opCode.c_str(); }

    bool isEmpty() { return idAtendimento.size() 
                         || idAndamento.size() 
                         || idPessoaUsuario.size() 
                         || dtAndamento.size() 
                         || idAtividade.size() 
                         || idAgrupamentoEstadoTpProc.size() 
                         || idGrupo.size() 
                            ? false:true; }
};
//
//==============================================================================
struct AndamentoObservacaoDPR
{
    string idAndamento;
    string dsAndamentoObservacao;
    string opCode;

    void setIdAndamento(long dado) { char temp[21];temp[sizeof(temp)-1]=0;sprintf(temp,"%d",dado); if(temp[sizeof(temp)-1]) throw new TuxException("04E0001","AndamentoObservacaoDPR:idAndamento muito grande!");idAndamento=temp; }
    void setDsAndamentoObservacao(char *dado) { dsAndamentoObservacao=dado; }
    void setOpCode(int dado) { char temp[21];temp[sizeof(temp)-1]=0;sprintf(temp,"%d",dado); if(temp[sizeof(temp)-1]) throw new TuxException("04E0001","AndamentoObservacaoDPR:opCode muito grande!"); opCode=temp;}
    
    char *getIdAndamento() { return (char*)idAndamento.c_str(); }
    char *getDsAndamentoObservacao() { return (char*)dsAndamentoObservacao.c_str(); }
    char *getOpCode() { return (char*)opCode.c_str(); }

    bool isEmpty() { return dsAndamentoObservacao.size() ? false:true; }
};
//
//==============================================================================
struct PessoaDPR
{
    string idPessoaDePara;
    string idPessoa;
    string nmPessoa;
    string idTipoPessoa;
    string opCode;

    void setIdPessoaDePara(int dado) { char temp[21];temp[sizeof(temp)-1]=0;sprintf(temp,"%d",dado); if(temp[sizeof(temp)-1]) throw new TuxException("04E0001","PessoaDPR:idPessoaDePara muito grande!");idPessoaDePara=temp; }
    void setIdPessoa(long dado) { char temp[21];temp[sizeof(temp)-1]=0;sprintf(temp,"%ld",dado); if(temp[sizeof(temp)-1]) throw new TuxException("04E0001","PessoaDPR:idPessoa muito grande!");idPessoa=temp; }
    void setNmPessoa(char *dado) { nmPessoa=dado; }
    void setIdTipoPessoa(int dado) { char temp[21];temp[sizeof(temp)-1]=0;sprintf(temp,"%d",dado); if(temp[sizeof(temp)-1]) throw new TuxException("04E0001","PessoaDPR:idTipoPessoa muito grande!");idTipoPessoa=temp; }
    void setOpCode(int dado) { char temp[21];temp[sizeof(temp)-1]=0;sprintf(temp,"%d",dado); if(temp[sizeof(temp)-1]) throw new TuxException("04E0001","PessoaDPR:opCode muito grande!"); opCode=temp;}
    
    char *getIdPessoaDePara() { return (char*)idPessoaDePara.c_str(); }
    char *getIdPessoa() { return (char*)idPessoa.c_str(); }
    char *getNmPessoa() { return (char*)nmPessoa.c_str(); }
    char *getIdTipoPessoa() { return (char*)idTipoPessoa.c_str(); }
    char *getOpCode() { return (char*)opCode.c_str(); }

    bool isEmpty() { return idPessoaDePara.size() 
                         || idPessoa.size() 
                         || nmPessoa.size() 
                         || idTipoPessoa.size() 
                            ? false:true; }
};
//
//==============================================================================
struct PessoaDeParaDPR
{
    string idPessoaDePara;
    string idPessoa;
    string idPessoaOrigem;
    string opCode;

    void setIdPessoaDePara(int dado) { char temp[21];temp[sizeof(temp)-1]=0;sprintf(temp,"%d",dado); if(temp[sizeof(temp)-1]) throw new TuxException("04E0001","PessoaDeParaDPR:idPessoaDePara muito grande!");idPessoaDePara=temp; }
    void setIdPessoa(long dado) { char temp[21];temp[sizeof(temp)-1]=0;sprintf(temp,"%ld",dado); if(temp[sizeof(temp)-1]) throw new TuxException("04E0001","PessoaDeParaDPR:idPessoa muito grande!");idPessoa=temp; }
    void setIdPessoaOrigem(long dado) { char temp[21];temp[sizeof(temp)-1]=0;sprintf(temp,"%ld",dado); if(temp[sizeof(temp)-1]) throw new TuxException("04E0001","PessoaDeParaDPR:idPessoaOrigem muito grande!");idPessoaOrigem=temp; }
    void setOpCode(int dado) { char temp[21];temp[sizeof(temp)-1]=0;sprintf(temp,"%d",dado); if(temp[sizeof(temp)-1]) throw new TuxException("04E0001","PessoaDeParaDPR:opCode muito grande!"); opCode=temp;}
    
    char *getIdPessoaDePara() { return (char*)idPessoaDePara.c_str(); }
    char *getIdPessoa() { return (char*)idPessoa.c_str(); }
    char *getIdPessoaOrigem() { return (char*)idPessoaOrigem.c_str(); }
    char *getOpCode() { return (char*)opCode.c_str(); }

    bool isEmpty() { return idPessoaDePara.size() 
                         || idPessoa.size() 
                         || idPessoaOrigem.size() 
                            ? false:true; }
};
//
//==============================================================================
struct PessoaLinhaHistoricoDPR
{
    string idPessoaLinhaHistorico;
    string cdAreaRegistro;
    string nrLinha;
    string idTipoRelacionamento;
    string idPessoaDePara;
    string idLinhaTelefonica;
    string dtRelacionamento;
    string opCode;

    void setIdPessoaLinhaHistorico(long dado) { char temp[21];temp[sizeof(temp)-1]=0;sprintf(temp,"%ld",dado); if(temp[sizeof(temp)-1]) throw new TuxException("04E0001","PessoaLinhaHistoricoDPR:idPessoaLinhaHistorico muito grande!");idPessoaLinhaHistorico=temp; }
    void setCdAreaRegistro(int dado) { char temp[21];temp[sizeof(temp)-1]=0;sprintf(temp,"%d",dado); if(temp[sizeof(temp)-1]) throw new TuxException("04E0001","PessoaLinhaHistoricoDPR:cdAreaRegistro muito grande!");cdAreaRegistro=temp; }
    void setNrLinha(int dado) { char temp[21];temp[sizeof(temp)-1]=0;sprintf(temp,"%d",dado); if(temp[sizeof(temp)-1]) throw new TuxException("04E0001","PessoaLinhaHistoricoDPR:nrLinha muito grande!");nrLinha=temp; }
    void setIdTipoRelacionamento(int dado) { char temp[21];temp[sizeof(temp)-1]=0;sprintf(temp,"%d",dado); if(temp[sizeof(temp)-1]) throw new TuxException("04E0001","PessoaLinhaHistoricoDPR:idTipoRelacionamento muito grande!");idTipoRelacionamento=temp; }
    void setIdPessoaDePara(int dado) { char temp[21];temp[sizeof(temp)-1]=0;sprintf(temp,"%d",dado); if(temp[sizeof(temp)-1]) throw new TuxException("04E0001","PessoaLinhaHistoricoDPR:idPessoaDePara muito grande!");idPessoaDePara=temp; }
    void setIdLinhaTelefonica(int dado) { char temp[21];temp[sizeof(temp)-1]=0;sprintf(temp,"%d",dado); if(temp[sizeof(temp)-1]) throw new TuxException("04E0001","PessoaLinhaHistoricoDPR:idLinhaTelefonica muito grande!");idLinhaTelefonica=temp; }
    void setDtRelacionamento(char *dado) { dtRelacionamento=dado; }
    void setOpCode(int dado) { char temp[21];temp[sizeof(temp)-1]=0;sprintf(temp,"%d",dado); if(temp[sizeof(temp)-1]) throw new TuxException("04E0001","PessoaLinhaHistoricoDPR:opCode muito grande!"); opCode=temp;}
    
    char *getIdPessoaLinhaHistorico() { return (char*)idPessoaLinhaHistorico.c_str(); }
    char *getCdAreaRegistro() { return (char*)cdAreaRegistro.c_str(); }
    char *getNrLinha() { return (char*)nrLinha.c_str(); }
    char *getIdTipoRelacionamento() { return (char*)idTipoRelacionamento.c_str(); }
    char *getIdPessoaDePara() { return (char*)idPessoaDePara.c_str(); }
    char *getIdLinhaTelefonica() { return (char*)idLinhaTelefonica.c_str(); }
    char *getDtRelacionamento() { return (char*)dtRelacionamento.c_str(); }
    char *getOpCode() { return (char*)opCode.c_str(); }

    bool isEmpty() { return idPessoaLinhaHistorico.size() 
                         || cdAreaRegistro.size() 
                         || nrLinha.size() 
                         || idTipoRelacionamento.size() 
                         || idPessoaDePara.size() 
                         || idLinhaTelefonica.size() 
                         || dtRelacionamento.size() 
                            ? false:true; }
};

#endif
