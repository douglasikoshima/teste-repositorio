/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Cassio Garcia
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:44 $
 **/

#ifndef __CREGCONTATOFOPC_H__
#define __CREGCONTATOFOPC_H__

#ifdef WIN32
#pragma warning(disable:4786)
#endif

#undef SQLCA
#define SQLCA_NONE
#include <sqlca.h>
#include <sqlda.h>

#include <string>
using namespace std;

#include <tuxfw.h>

#include "../../../commons/definesAtendimento.h"

class cRegContatoFOPC
{
    public:
        TuxHelper tx;
        struct sqlca sqlca;

    public:
        cRegContatoFOPC() {}
        ~cRegContatoFOPC() {}

        bool ObterDadosLinPessoaCliente(const char *ddd,const char *nrLinha);
        bool ObterDadosLinPessoaUsuario(const char *ddd,const char *nrLinha);
        void ObterIdContato(const char *_cdContato, char **_idContato);
        void ObterDadosPessoa(const char *_idPessoa);
        void ObterIDConta(const char *_idLinhaTelefonica,const char *_idTipoRelacionamento);
        void ObterIDPessoaLinhaHistorico(const char *_idPessoaDePara
                                        ,const char *_idLinhaTelefonica
                                        ,const char *_idTipoRelacionamento);
        void VerificarContato(const char *_idContato);
        void VerificarCanal(const char *_idCanal);
        void VerificarProcedencia(const char *_idProcedencia);

        char *getIDPessoaCliente() { return (char*)idPessoaCliente.c_str(); }
        char *getIDPessoaDeParaCliente() { return (char*)idPessoaDeParaCliente.c_str(); }
        char *getIDTipoCarteiraCliente() { return (char*)idTipoCarteiraCliente.c_str(); }
        char *getIDSegmentacaoCliente() { return (char*)idSegmentacaoCliente.c_str(); }
        char *getIDLinhaTelefonicaCliente() { return (char*)idLinhaTelefonicaCliente.c_str(); }
        char *getIDTipoRelacionamentoCliente() { return (char*)idTipoRelacionamentoCliente.c_str(); }

        char *getIDPessoaUsuario() { return (char*)idPessoaUsuario.c_str(); }
        char *getIDPessoaDeParaUsuario() { return (char*)idPessoaDeParaUsuario.c_str(); }
        char *getIDTipoCarteiraUsuario() { return (char*)idTipoCarteiraUsuario.c_str(); }
        char *getIDSegmentacaoUsuario() { return (char*)idSegmentacaoUsuario.c_str(); }
        char *getIDLinhaTelefonicaUsuario() { return (char*)idLinhaTelefonicaUsuario.c_str(); }
        char *getIDTipoRelacionamentoUsuario() { return (char*)idTipoRelacionamentoUsuario.c_str(); }

        char *getIDConta() { return (char*)idConta.c_str(); }
        char *getIDPessoaLinhaHistorico() { return (char*)idPessoaLinhaHistorico.c_str(); }

        char *getDDD() { return (char*)ddd.c_str(); }
        char *getnrTelefone() { return (char*)nrTelefone.c_str(); }
        char *getIDSegmentacao() { return (char*)idSegmentacao.c_str(); }
        char *getIDTipoCarteira() { return (char*)idTipoCarteira.c_str(); }
        char *getIDPessoaDePara() { return (char*)idPessoaDePara.c_str(); }
        char *getIDPessoa() { return (char*)idPessoa.c_str(); }
        char *getnmPessoa() { return (char*)nmPessoa.c_str(); }
        char *getIDLinhaTelefonica() { return (char*)idLinhaTelefonica.c_str(); }
        char *getIDTipoRelacionamento() { return (char*)idTipoRelacionamento.c_str(); }

        void setDDD(const char *valor) { ddd = valor; }
        void setnrTelefone(const char *valor) { nrTelefone = valor; }

        void setIDPessoa(const char *valor) { idPessoa = valor; }
        void setIDTipoRelacionamento(const char *valor) 
        {
            char temp[9];
            if ( stricmp(valor,SG_TPRELA_U_USUARIO) == 0 )
            {
                sprintf(temp,"%d",TIPO_RELA_U_USUARIO);
                idTipoRelacionamento = temp;
            }
            else if ( stricmp(valor,SG_TPRELA_C_CLIENTE) == 0 )
            {
                sprintf(temp,"%d",TIPO_RELA_C_CLIENTE);
                idTipoRelacionamento = temp;
            }
            else if ( stricmp(valor,SG_TPRELA_R_CONS_RELACIONAMENTO) == 0 )
            {
                sprintf(temp,"%d",TIPO_RELA_R_CONS_RELACIONAMENTO);
                idTipoRelacionamento = temp;
            }
            else if ( stricmp(valor,SG_TPRELA_V_EXECUTIVO_DE_VENDAS) == 0 )
            {
                sprintf(temp,"%d",TIPO_RELA_V_EXECUTIVO_DE_VENDAS);
                idTipoRelacionamento = temp;
            }
            else if ( stricmp(valor,SG_TPRELA_GC_GESTOR_DA_CONTA) == 0 )
            {
                sprintf(temp,"%d",TIPO_RELA_GC_GESTOR_DA_CONTA);
                idTipoRelacionamento = temp;
            }
            else if ( stricmp(valor,SG_TPRELA_P_PROSPECT) == 0 )
            {
                sprintf(temp,"%d",TIPO_RELA_P_PROSPECT);
                idTipoRelacionamento = temp;
            }
            else if ( stricmp(valor,SG_TPRELA_NC_NAO_CLIENTE) == 0 )
            {
                sprintf(temp,"%d",TIPO_RELA_NC_NAO_CLIENTE);
                idTipoRelacionamento = temp;
            }
            else if ( stricmp(valor,SG_TPRELA_GRP_CONTA_GRUPO) == 0 )
            {
                sprintf(temp,"%d",TIPO_RELA_GRP_CONTA_GRUPO);
                idTipoRelacionamento = temp;
            }
            else
            {
                idTipoRelacionamento = temp;
            }
        }

        bool idPessoaIsEmpty() { return idPessoa.size()==0?true:false; }
        bool idTipoRelacionamentoIsEmpty() { return idTipoRelacionamento.size()==0?true:false; }

    private:
        void sql_error_WFcRegContatoFOPC();

    private:
        string idPessoaCliente;
        string idPessoaDeParaCliente;
        string idTipoCarteiraCliente;
        string idSegmentacaoCliente;
        string idLinhaTelefonicaCliente;
        string idTipoRelacionamentoCliente;

        string idPessoaUsuario;
        string idPessoaDeParaUsuario;
        string idTipoCarteiraUsuario;
        string idSegmentacaoUsuario;
        string idLinhaTelefonicaUsuario;
        string idTipoRelacionamentoUsuario;

        string idConta;
        string idPessoaLinhaHistorico;
        string idPessoa;

        string nmPessoa;
        string idSegmentacao;
        string idPessoaDePara;
        string idTipoCarteira;
        string idLinhaTelefonica;
        string idTipoRelacionamento;
        string ddd;
        string nrTelefone;
};

#endif