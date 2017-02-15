/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Cassio Garcia
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:44 $
 **/

#ifndef __RCFOEXCEPTION_H__
#define __RCFOEXCEPTION_H__

#ifdef WIN32
#pragma warning(disable:4786)
#endif

#include <string>
using namespace std;

// C�digos de erro usados em setStatusCode() (max=9999)
#define ERR_XML_ENTRADA                 1
#define ERR_CODUSU_ALFA                 2
#define ERR_XML_IDCONTATO               3
#define ERR_XML_SEGMENTACAO             4
#define ERR_XML_PROCEDENCIA             5
#define ERR_XML_TPCARTEIRA              6
#define ERR_XML_CANAL                   7
#define ERR_XML_TPOPERACAO              8
#define ERR_XML_GRPABERTURA             9
#define ERR_XML_INRESPABERT             10
#define ERR_XML_DDD                     11
#define ERR_XML_NRLINHA                 12
#define ERR_XML_IDPESSOADEPARA          13
#define ERR_XML_IDPESSOA                14
#define ERR_XML_CDCONTATO               15
#define ERR_PRC_OBT_IDCONTA             16
#define ERR_PRC_OBT_IDPLINHAHISTORICO   17
#define ERR_PRC_OBT_DADOSLINHA          18
#define ERR_DDD_TOOLONG                 19
#define ERR_NRLINHA_TOOLONG             20
#define ERR_DDD_TOOSHORT                21
#define ERR_NRLINHA_TOOSHORT            22
#define ERR_IDPROCEDENCIA_NAOEXISTE     23
#define ERR_IDCANAL_NAOEXISTE           24
#define ERR_IDCONTATO_NAOEXISTE         25
#define ERR_IDCONTATO_INDISPONI         26
#define ERR_TPOPERACAO_INVALIDO         27
#define ERR_IDCONTATO_NAOEHFOLHA        28
#define ERR_IDPESSOADEPARA_NAOEXISTE    29
#define ERR_IDSEGMENTACAO_NSEG_NEXIS    30
#define ERR_TPRELACIONAME_NCLI_NEXIS    31
#define ERR_TPRELACIONAME_INVALIDO      32
#define ERR_TPRELACIONAME_CUINVALIDO    33
#define ERR_PRC_OBT_DADOSLINHA_CLI      34
#define ERR_DSVALORPARAMETRO_NAOEXIS    35

struct RCFOException
{
    RCFOException(const char *cod,const char *msg,const char *arq=__FILE__,int lin=__LINE__)
        { 
            setOutCode(cod);setOutMesg(msg);setArquivo(arq);setLinha(lin);
            ULOG("Erro '%s-%s' ocorrido pr�ximo a linha %d do arquivo %s",getCodErr(),getMsgErr(),lin,arq); 
        }

    RCFOException(int cod,string msg,const char *arq=__FILE__,int lin=__LINE__)
        {
            char codErro[9];sprintf(codErro,"04E%04d",cod<0 ?cod*(-1):cod);
            setOutCode(codErro);setOutMesg(msg.c_str());setArquivo(arq);setLinha(lin);
            ULOG("Erro '%s-%s' ocorrido pr�ximo a linha %d do arquivo %s",getCodErr(),getMsgErr(),lin,arq);
        }

    RCFOException(int cod,const char *arq=__FILE__,int lin=__LINE__)
        { 
            setarErro(cod);setArquivo(arq);setLinha(lin);
            ULOG("Erro '%s-%s' ocorrido pr�ximo a linha %d do arquivo %s",getCodErr(),getMsgErr(),lin,arq); 
        }

    char *getCodErr() { return (char*)this->outCode.c_str(); }
    char *getMsgErr() { return (char*)this->outMesg.c_str(); }
    char *getArquivo() { return (char*)this->arquivo.c_str(); }
    int getLinha() { return linha; }

    void setOutCode(const char *cod) { outCode = cod?cod:"04E0000"; }
    void setOutMesg(const char *msg) { outMesg = msg?msg:"Ponteiro invalido"; }
    void setArquivo(const char *arq) { arquivo = arq?arq:__FILE__; }
    void setLinha(int lin) { linha = lin; }

    void setarErro(int codErro)
    {
        bool setou = false;
        struct 
        {
            int codErro;
            char *msgErro;
        } MapaErros[] = 
        {
            { ERR_XML_ENTRADA,"XML de entrada n�o fornecido" },
            { ERR_CODUSU_ALFA,"C�digo do usu�rio deve ser completamente num�rico" },
            { ERR_XML_IDCONTATO,"tag <idContato> n�o fornecida" },
            { ERR_XML_SEGMENTACAO,"tag <idSegmentacao> n�o fornecida" },
            { ERR_XML_PROCEDENCIA,"tag <idProcedencia> n�o fornecida" },
            { ERR_XML_TPCARTEIRA,"tag <idTipoCarteira> n�o fornecida" },
            { ERR_XML_CANAL,"tag <idCanal> n�o fornecida" },
            { ERR_XML_TPOPERACAO,"tag <tpOperacao> n�o fornecida" },
            { ERR_XML_GRPABERTURA,"tag <idGrupoAbertura> n�o fornecida" },
            { ERR_XML_INRESPABERT,"tag <inResponsavelAbertura> n�o fornecida" },
            { ERR_XML_DDD,"tag <ddd> n�o fornecida" },
            { ERR_XML_NRLINHA,"tag <nrLinha> n�o fornecida" },
            { ERR_XML_IDPESSOADEPARA,"tag <idPessoaPara> n�o fornecida" },
            { ERR_XML_IDPESSOA,"tag <idPessoa> n�o fornecida" },
            { ERR_XML_CDCONTATO,"tag <idContato> ou <dsContato> n�o fornecidas" },
            { ERR_PRC_OBT_IDCONTA,"id de conta n�o encontrado" },
            { ERR_PRC_OBT_IDPLINHAHISTORICO,"id de pessoalinhahistorico n�o encontrado" },
            { ERR_PRC_OBT_DADOSLINHA,"dados da linha n�o encontrados" },
            { ERR_DDD_TOOLONG,"valor DDD acima de 2 d�gitos" },
            { ERR_NRLINHA_TOOLONG,"n�mero da linha muito longo" },
            { ERR_DDD_TOOSHORT,"valor ddd menor que 2 d�gitos" },
            { ERR_NRLINHA_TOOSHORT,"n�mero da linha muito curto" },
            { ERR_IDPROCEDENCIA_NAOEXISTE,"ID da proced�ncia n�o existe na base" },
            { ERR_IDCANAL_NAOEXISTE,"ID do canal n�o existe na base" },
            { ERR_IDCONTATO_NAOEXISTE,"ID do contato n�o existe na base" },
            { ERR_IDCONTATO_INDISPONI,"ID do contato fornecido existe mas esta indispon�vel" },
            { ERR_TPOPERACAO_INVALIDO,"Tipo de opera��o invalido (deve ser apenas 1 ou 2)" },
            { ERR_IDCONTATO_NAOEHFOLHA,"O contato selecionado n�o � folha da �rvore" },
            { ERR_IDPESSOADEPARA_NAOEXISTE,"idPessoaDePara n�o encontrado para o idPessoa fornecido"},
            { ERR_IDSEGMENTACAO_NSEG_NEXIS,"N�o foi possivel determinar a segmenta��o. idSegmentacao 'N�O SEGMENTADO' n�o existe."},
            { ERR_TPRELACIONAME_NCLI_NEXIS,"N�o foi possivel determinar o tipo de relacionamento. idTipoRelacionamento 'N�O CLIENTE' n�o existe."},
            { ERR_TPRELACIONAME_INVALIDO,"Tipo de relacionamento deve ser do tipo CLIENTE, USUARIO ou N�O-CLIENTE."},
            { ERR_TPRELACIONAME_CUINVALIDO,"tipoRelacionamento diferente de CLIENTE/USUARIO"},
            { ERR_PRC_OBT_DADOSLINHA_CLI,"dados da linha do cliente n�o encontrados" },
            { ERR_DSVALORPARAMETRO_NAOEXIS,"DSVALORPARAMETRO n�o encontrado" },
            { -1,0 }
        };

        int it=0;
        while ( MapaErros[it].codErro != -1 && !setou )
        {
            if ( codErro == MapaErros[it].codErro )
            {
                char codErro[9];
                sprintf(codErro,"04E%04d",MapaErros[it].codErro);
                setOutCode(codErro);
                setOutMesg(MapaErros[it].msgErro);
                setou = true;
            }
            it++;
        }

        if ( !setou )
        {
            setOutCode("04E9999");
            setOutMesg("Erro desconhecido ocorrido");
        }
    }

    string outCode;
    string outMesg;
    string arquivo;
    int linha;
};

#endif // __RCFOEXCEPTION_H__
