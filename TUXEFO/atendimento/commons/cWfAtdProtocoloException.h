/**
 * 
 * @modulo  Atendimento
 * @usecase Protocolo
 * @author  Cassio Garcia
 * @version $Revision: 1.1.2.1 $
 * @CVS     $Author: a5116174 $ - $Date: 2011/08/12 17:11:20 $
 **/

#ifndef __ATDPROTOCOLOEXCEPTION_H__
#define __ATDPROTOCOLOEXCEPTION_H__

// C�digos de erro usados em setStatusCode() (max=9999)
//      [---tamanho m�ximo do label---]
#define ERR_TPABERPROT_CONTA_CDCONTA    "04I0001"
#define ERR_TPABERPROT_CONTA_NRTELEF    "04I0002"

#define ERR_TPABERPROT_LINHA_NRTELEF    "04I0200"

#define ERR_TPABERPROT_LINCLI_LINHA     "04I0300"
#define ERR_TPABERPROT_LINCLI_NRTELEF   "04I0301"

#define ERR_TPABERPROT_PESSOA_IDPDP     "04I0400"
#define ERR_TPABERPROT_PESSOA_NRTELEF   "04I0401"
#define ERR_TPABERPROT_PESSOA_NRTELSMS  "04I0402"

// Erros de neg�cio fatais 
#define ERR_NEGOCIO_IDLINHATEL_NOTFOUND "04E1000"
#define ERR_NEGOCIO_SISTORIGEM_NAOBATE  "04E1001"
#define ERR_NEGOCIO_PROTOCOLO_NOTFOUND  "04E1002"
#define ERR_NEGOCIO_TMPVIDAPR_NOTFOUND  "04E1003"

// Erro apenas do tipo INFORMATION
#define ERR_NEGINFO_IDLINHATEL_NOTFOUND "04I2000"
#define ERR_NEGINFO_SISTORIGEM_NAOBATE  "04I2001"
#define ERR_NEGINFO_PROTOCOLO_NOTFOUND  "04I2002"
#define ERR_NEGINFO_PROTOCOLO_NAOABERTO "04I2003"
#define ERR_NEGINFO_OPERACAO_INVALIDA   "04I2004"
#define ERR_NEGINFO_NRTELEFONE_NOTFOUND "04I2005"
#define ERR_NEGINFO_CDAREAREG_INVALIDO  "04I2006"
#define ERR_NEGINFO_NRTELEFONE_INVALIDO "04I2007"
#define ERR_NEGINFO_PROTATEND_NOTFOUND  "04I2008"
#define ERR_NEGINFO_TAGSOBRIG_NOTFOUND  "04I2009"
#define ERR_NEGINFO_CDCONTA_NOTFOUND    "04I2010"

#define ERR_XML_ENTRADA                 "04I9000"
#define ERR_XML_IDTIPOABERTURAPROTOCOLO "04I9001"
#define ERR_XML_IDSISTEMAORIGEM         "04I9002"
#define ERR_XML_IDATENDIMENTOPROTOCOLO  "04I9003"
#define ERR_XML_CDAREAREGISTRO          "04I9004"
#define ERR_XML_NRLINHA                 "04I9005"

struct CErroAtendimento
{
    char *outMesg;

    char *outMsg() { return outMesg; }

    CErroAtendimento(const char *codErro)
    {
        struct 
        {
            const char *codErro;
            char *msgErro;
        } MapaErros[] = 
        {
            { ERR_XML_ENTRADA,"XML de entrada n�o fornecido" },

            { ERR_TPABERPROT_CONTA_CDCONTA   ,"Tipo de abertura por conta: N�mero da conta obrigat�rio e n�o informado." },
            { ERR_TPABERPROT_CONTA_NRTELEF   ,"Tipo de abertura por conta: N�mero do telefone obrigat�rio e n�o informado." },
            { ERR_TPABERPROT_LINHA_NRTELEF   ,"Tipo de abertura por linha: N�mero do telefone obrigat�rio e n�o informado." },
            { ERR_TPABERPROT_LINCLI_LINHA    ,"Tipo de abertura por linha de cliente: ID da linha obrigat�rio e n�o informado." },
            { ERR_TPABERPROT_LINCLI_NRTELEF  ,"Tipo de abertura por linha de cliente: N�mero do telefone obrigat�rio e n�o informado." },
            { ERR_TPABERPROT_PESSOA_IDPDP    ,"Tipo de abertura por pessoa: ID da pessoa (deXpara) obrigat�rio e n�o informado." },
            { ERR_TPABERPROT_PESSOA_NRTELEF  ,"Tipo de abertura por pessoa: N�mero do telefone obrigat�rio e n�o informado." },
            { ERR_TPABERPROT_PESSOA_NRTELSMS ,"Tipo de abertura por pessoa: N�mero do telefone de SMS obrigat�rio e n�o informado." },

            { ERR_NEGOCIO_IDLINHATEL_NOTFOUND,"Linha telefonica informada n�o encontrada no FrontOffice." },
            { ERR_NEGOCIO_SISTORIGEM_NAOBATE ,"O sistema origem informado n�o bate com o do protocolo." },
            { ERR_NEGOCIO_PROTOCOLO_NOTFOUND ,"O protocolo informado n�o existe." },
            { ERR_NEGOCIO_TMPVIDAPR_NOTFOUND ,"Par�metro do tempo de vida de um protocolo n�o encontrado" },

            { ERR_NEGINFO_IDLINHATEL_NOTFOUND,"Linha telefonica informada n�o encontrada no FrontOffice." },
            { ERR_NEGINFO_SISTORIGEM_NAOBATE ,"O sistema origem informado n�o bate com o do protocolo." },
            { ERR_NEGINFO_PROTOCOLO_NOTFOUND ,"O protocolo informado n�o existe." },
            { ERR_NEGINFO_PROTOCOLO_NAOABERTO,"O protocolo nao esta ''Em Aberto''" },
            { ERR_NEGINFO_OPERACAO_INVALIDA  ,"tag <operacao> n�o fornecida ou fornecida sem valor v�lido." },
            { ERR_NEGINFO_NRTELEFONE_NOTFOUND,"Telefone informado n�o encontrado no FrontOffice." },
            { ERR_NEGINFO_CDAREAREG_INVALIDO,"C�digo de �rea de registro n�o informado para o telefone."},
            { ERR_NEGINFO_NRTELEFONE_INVALIDO,"C�digo de �rea informado sem n�mero de telefone correspondente."},
            { ERR_NEGINFO_PROTATEND_NOTFOUND,"N�o encontrado um protocolo 'Em Atendimento' para o telefone informado."},
            { ERR_NEGINFO_TAGSOBRIG_NOTFOUND,"Para pesquisa de protocolo 'Em Atendimento todas as tags s�o obrigat�rias."},
            { ERR_NEGINFO_CDCONTA_NOTFOUND,"Valor da tag <cdConta> informado n�o existe na base do FrontOffice."},

            { ERR_XML_IDTIPOABERTURAPROTOCOLO,"tag <idTipoAberturaProtocolo> n�o fornecida ou fornecida sem valor associado." },
            { ERR_XML_IDSISTEMAORIGEM        ,"tag <idSistemaOrigem> n�o fornecida ou fornecida sem valor associado." },
            { ERR_XML_IDATENDIMENTOPROTOCOLO ,"tag <idAtendimentoProtocolo> n�o fornecida ou fornecida sem valor associado." },
            { ERR_XML_CDAREAREGISTRO ,"tag <cdAreaRegistro> n�o fornecida ou fornecida sem valor associado." },
            { ERR_XML_NRLINHA ,"tag <nrTelefone> n�o fornecida ou fornecida sem valor associado." },

            { 0,0 }
        };
    
        outMesg = "Erro desconhecido ocorrido";

        int it=0;

        while ( MapaErros[it].codErro )
        {
            if ( strcmp(codErro,MapaErros[it].codErro)==0 )
            {
                outMesg = MapaErros[it].msgErro;
                break;
            }
            it++;
        }
    }
};

#endif // __ATDPROTOCOLOEXCEPTION_H__
