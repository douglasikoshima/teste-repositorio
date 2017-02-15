/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Cassio Garcia
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:34:04 $
 **/

#ifndef __CORECONTEXCEPTION_H__
#define __CORECONTEXCEPTION_H__

#ifdef WIN32
#pragma warning(disable:4786)
#endif

#include <string>
using namespace std;

// Códigos de erro usados em setStatusCode() (max=9999)
//      [---tamanho máximo do label---]
#define ERR_XML_ENTRADA                 1
#define ERR_XML_CODOPERACAO             2
#define ERR_XML_IDACAOPORTABILIDADE     3

#define ERR_VLR_CODOPERACAO             100
#define ERR_VLR_IDACAOPORTABILIDADE     101
#define ERR_VLR_CDAREAREGISTRO          102
#define ERR_VLR_NRLINHA                 103
#define ERR_VLR_OPERADORAINVALIDA       104
#define ERR_VLR_NRPROCESSO              105
#define ERR_VLR_NRBILHETEPORTABILIDADE  106
#define ERR_VLR_CFUNC_PORTOUT           107
#define ERR_VLR_CFUNC_PORTIN            108
#define ERR_VLR_CFUNC_FRAUDE            109
#define ERR_VLR_CFUNC_WINBACK           110
#define ERR_VLR_CFUNC_GENERIC           111
#define ERR_VLR_PROCEDENCIAINVALIDO     112
#define ERR_VLR_DSPROCEDENCIANEXISTE    113

#define ERR_FLX_SERVICONAOIDENTIFICADO  200

#define ERR_PRM_GRUPOABERTURA           300
#define ERR_PRM_TPOPERACAO              301
#define ERR_PRM_IDPROCEDENCIA           302
#define ERR_PRM_IDCANAL                 303
#define ERR_PRM_IDCONTATO               304

#define ERR_RMT_REMOTECALL              400

#define ERR_CODUSU_ALFA                 502
#define ERR_XML_IDCONTATO               503
#define ERR_XML_SEGMENTACAO             504
#define ERR_XML_PROCEDENCIA             505
#define ERR_XML_TPCARTEIRA              506
#define ERR_XML_CANAL                   507
#define ERR_XML_TPOPERACAO              508
#define ERR_XML_GRPABERTURA             509
#define ERR_XML_INRESPABERT             510
#define ERR_XML_DDD                     511
#define ERR_XML_NRLINHA                 512
#define ERR_XML_IDPESSOADEPARA          513
#define ERR_XML_IDPESSOA                514
#define ERR_XML_CDCONTATO               515
#define ERR_PRC_OBT_IDCONTA             516
#define ERR_PRC_OBT_IDPLINHAHISTORICO   517
#define ERR_PRC_OBT_DADOSLINHA          518
#define ERR_DDD_TOOLONG                 519
#define ERR_NRLINHA_TOOLONG             520
#define ERR_DDD_TOOSHORT                521
#define ERR_NRLINHA_TOOSHORT            522
#define ERR_IDPROCEDENCIA_NAOEXISTE     523
#define ERR_IDCANAL_NAOEXISTE           524
#define ERR_IDCONTATO_NAOEXISTE         525
#define ERR_IDCONTATO_INDISPONI         526
#define ERR_TPOPERACAO_INVALIDO         527
#define ERR_IDCONTATO_NAOEHFOLHA        528
#define ERR_IDPESSOADEPARA_NAOEXISTE    529
#define ERR_IDSEGMENTACAO_NSEG_NEXIS    530
#define ERR_TPRELACIONAME_NCLI_NEXIS    531
#define ERR_TPRELACIONAME_INVALIDO      532
#define ERR_TPRELACIONAME_CUINVALIDO    533
#define ERR_PRC_OBT_DADOSLINHA_CLI      534
#define ERR_DSVALORPARAMETRO_NAOEXIS    535
#define ERR_NFO_LINHATELEFONICA         536
#define ERR_NFO_DADOSPESSOA             537
#define ERR_NFO_MOTIVO                  538
#define ERR_NFO_ATIVIDADE               539
#define ERR_NFO_SGATIVIDADE             540
#define ERR_NFO_NRPROTOCOLPORTABILIDADE 541
#define ERR_NFO_ESTADOFUTURO            542
#define ERR_NFO_PROTPORTDUPLICADO       543
#define ERR_NFO_OPERSOLICITANTE         544
#define ERR_IDTPCARTEIRA_NAO_EXISTE     554
#define ERR_NFO_DSACAOPORTABILIDADE     555
#define ERR_NFO_GRUPOABERTURANF         556
#define ERR_NFO_PARAMETRONOTFOUND       557

#define ERR_DTA_INVALIDA                600
#define ERR_HRA_INVALIDA                601
#define ERR_DTA_PZOJPO_ANTERIORHOJE     602
#define ERR_DTA_PZOJPO_PRAZOINFMIN      603
#define ERR_XML_DTJANELAPORTOUT         604

struct CoreContatoException
{
    CoreContatoException(const char *cod,const char *msg,const char *arq=__FILE__,int lin=__LINE__)
    { 
        setOutCode(cod);setOutMesg(msg);setArquivo(arq);setLinha(lin);
        ULOG("Erro '%s-%s' ocorrido próximo a linha %d do arquivo %s",getCodErr(),getMsgErr(),lin,arq); 
    }

    CoreContatoException(int cod,string msg,const char *arq=__FILE__,int lin=__LINE__)
    {
        char codErro[9];sprintf(codErro,"04I%04d",cod<0 ?cod*(-1):cod);
        setOutCode(codErro);setOutMesg(msg.c_str());setArquivo(arq);setLinha(lin);
        ULOG("Erro '%s-%s' ocorrido próximo a linha %d do arquivo %s",getCodErr(),getMsgErr(),lin,arq);
    }

    CoreContatoException(int cod,const char *arq=__FILE__,int lin=__LINE__)
    { 
        setarErro(cod);setArquivo(arq);setLinha(lin);
        ULOG("Erro '%s-%s' ocorrido próximo a linha %d do arquivo %s",getCodErr(),getMsgErr(),lin,arq); 
    }

    char *getCodErr() { return (char*)this->outCode.c_str(); }
    char *getMsgErr() { return (char*)this->outMesg.c_str(); }
    char *getArquivo() { return (char*)this->arquivo.c_str(); }
    int getLinha() { return linha; }

    void setOutCode(const char *cod) { outCode = cod?cod:"04I0000"; }
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
            { ERR_XML_ENTRADA,"XML de entrada não fornecido" },
            { ERR_XML_CODOPERACAO,"Tag <cdOperacao> obrigatória e não fornecida" },
            { ERR_XML_IDACAOPORTABILIDADE,"Tag <estadoPortabilidade> obrigatória e não fornecida" },
            { ERR_VLR_CODOPERACAO,"Valor da tag <cdOperacao> inválido" },
            { ERR_VLR_IDACAOPORTABILIDADE,"<estadoPortabilidade> informado não existe na base do FrontOffice" },
            { ERR_VLR_CDAREAREGISTRO,"Código de área é obrigatório para a operação solicitada" },
            { ERR_VLR_NRLINHA,"Número da linha é obrigatório para a operação solicitada" },
            { ERR_VLR_NRPROCESSO,"Número do processo é obrigatório para a operação solicitada" },
            { ERR_VLR_NRBILHETEPORTABILIDADE,"Número do protocolo de portabilidade é obrigatório para a operação solicitada" },
            { ERR_VLR_CFUNC_PORTOUT,"Parametrização de portout não cadastrada para o estado fornecido"},
            { ERR_VLR_CFUNC_PORTIN,"Parametrização de portin não cadastrada para o estado fornecido"},
            { ERR_VLR_CFUNC_FRAUDE,"Parametrização de fraude não cadastrada para o estado fornecido"},
            { ERR_VLR_CFUNC_WINBACK,"Parametrização de winback não cadastrada para o estado fornecido"},
            { ERR_VLR_PROCEDENCIAINVALIDO,"Código de procedência fornecido é inválido"},
            { ERR_FLX_SERVICONAOIDENTIFICADO,"Ação solicitada não possui serviço correspondente no FrontOffice" },
            { ERR_PRM_GRUPOABERTURA,"Grupo de abertura não parametrizado na base do FrontOffice para o ação solicitada" },
            { ERR_PRM_TPOPERACAO,"Grupo de operação não parametrizado na base do FrontOffice para o ação solicitada" },
            { ERR_PRM_IDPROCEDENCIA,"id da procedência não parametrizado na base do FrontOffice para o ação solicitada" },
            { ERR_VLR_OPERADORAINVALIDA,"id da operadora solicitante informado é invalido" },
            { ERR_CODUSU_ALFA,"Código do usuário deve ser completamente numérico" },
            { ERR_XML_IDCONTATO,"tag <idContato> não fornecida" },
            { ERR_XML_SEGMENTACAO,"tag <idSegmentacao> não fornecida" },
            { ERR_XML_PROCEDENCIA,"tag <idProcedencia> não fornecida" },
            { ERR_XML_TPCARTEIRA,"tag <idTipoCarteira> não fornecida" },
            { ERR_XML_CANAL,"tag <idCanal> não fornecida" },
            { ERR_XML_TPOPERACAO,"tag <tpOperacao> não fornecida" },
            { ERR_XML_GRPABERTURA,"tag <idGrupoAbertura> não fornecida" },
            { ERR_XML_INRESPABERT,"tag <inResponsavelAbertura> não fornecida" },
            { ERR_XML_DDD,"tag <ddd> não fornecida" },
            { ERR_XML_NRLINHA,"tag <nrLinha> não fornecida" },
            { ERR_XML_IDPESSOADEPARA,"tag <idPessoaPara> não fornecida" },
            { ERR_XML_IDPESSOA,"tag <idPessoa> não fornecida" },
            { ERR_XML_CDCONTATO,"tag <idContato> ou <dsContato> não fornecidas" },
            { ERR_PRC_OBT_IDCONTA,"id de conta não encontrado" },
            { ERR_PRC_OBT_IDPLINHAHISTORICO,"id de pessoalinhahistorico não encontrado" },
            { ERR_PRC_OBT_DADOSLINHA,"dados da linha não encontrados" },
            { ERR_DDD_TOOLONG,"valor DDD acima de 2 dígitos" },
            { ERR_NRLINHA_TOOLONG,"número da linha muito longo" },
            { ERR_DDD_TOOSHORT,"valor ddd menor que 2 dígitos" },
            { ERR_NRLINHA_TOOSHORT,"número da linha muito curto" },
            { ERR_IDPROCEDENCIA_NAOEXISTE,"ID da procedência não existe na base" },
            { ERR_IDCANAL_NAOEXISTE,"ID do canal não existe na base" },
            { ERR_IDCONTATO_NAOEXISTE,"ID do contato não existe na base" },
            { ERR_IDCONTATO_INDISPONI,"ID do contato fornecido existe mas esta indisponível" },
            { ERR_TPOPERACAO_INVALIDO,"Tipo de operação invalido (deve ser apenas 1 ou 2)" },
            { ERR_IDCONTATO_NAOEHFOLHA,"O contato selecionado não é folha da árvore" },
            { ERR_IDPESSOADEPARA_NAOEXISTE,"Nome da pessoa não encontrado para a linha fornecida"},
            { ERR_IDSEGMENTACAO_NSEG_NEXIS,"Não foi possivel determinar a segmentação. Segmentação 'NÃO SEGMENTADO' não existe."},
            { ERR_IDTPCARTEIRA_NAO_EXISTE,"Não foi possivel determinar o tipo da carteira.Tipo de carteira 'NÃO CLASSIFICADO' não existe."},
            { ERR_TPRELACIONAME_NCLI_NEXIS,"Não foi possivel determinar o tipo de relacionamento. idTipoRelacionamento 'NÃO CLIENTE' não existe."},
            { ERR_TPRELACIONAME_INVALIDO,"Tipo de relacionamento deve ser do tipo CLIENTE, USUARIO ou NÃO-CLIENTE."},
            { ERR_TPRELACIONAME_CUINVALIDO,"tipoRelacionamento diferente de CLIENTE/USUARIO"},
            { ERR_PRC_OBT_DADOSLINHA_CLI,"dados da linha do cliente não encontrados" },
            { ERR_DSVALORPARAMETRO_NAOEXIS,"DSVALORPARAMETRO não encontrado" },
            { ERR_NFO_LINHATELEFONICA,"Linha telefonica não cadastrada na base do FrontOffice"},
            { ERR_NFO_DADOSPESSOA,"VIVONET-ERRO-2"}, // msg original era "Dados da pessoa não encontrados para a o ddd+linha fornecidos"
            { ERR_NFO_MOTIVO,"Motivo não encontrado para o tipo de portabilidade solicitado"},
            { ERR_NFO_ATIVIDADE,"Atividade não encontrada para o estado de portabilidade solicitado"},
            { ERR_NFO_SGATIVIDADE,"Atividade não parametrizada para o estado de portout solicitado"},
            { ERR_NFO_NRPROTOCOLPORTABILIDADE,"Não existe bilhete de portabilidade em aberto com este número na base do FrontOffice"},
            { ERR_NFO_ESTADOFUTURO,"Ação solicitada não é compatível com o estado atual do processo."},
            { ERR_NFO_PROTPORTDUPLICADO,"Não pode abrir mais de um processo de PORT-OUT para um mesmo bilhete de portabilidade"},
            { ERR_NFO_OPERSOLICITANTE,"valor de cdOperadorasolicitante não cadastrado na base do FrontOffice."},
            { ERR_NFO_DSACAOPORTABILIDADE,"ID do estadoPortabilidade fornecido não esta cadastrado na base do FrontOffice."},
            { ERR_NFO_GRUPOABERTURANF,"Grupo de abertura não encontrado."},
            { ERR_NFO_PARAMETRONOTFOUND,"Parametro de execução não encontrado."},
            { ERR_DTA_INVALIDA, "Data inválida"},
            { ERR_DTA_PZOJPO_ANTERIORHOJE, "Janela de portout não pode ser anterior a data de hoje"},
            { ERR_DTA_PZOJPO_PRAZOINFMIN, "Janela de portout anterior ao prazo mínimo"},
            { ERR_HRA_INVALIDA, "Hora inválida"},
            { ERR_XML_DTJANELAPORTOUT, "Data de janela de port-out obrigatória não fornecida"},
            { -1,0 }
        };

        int it=0;
        while ( MapaErros[it].codErro != -1 && !setou )
        {
            if ( codErro == MapaErros[it].codErro )
            {
                char codErro[9];
                sprintf(codErro,"04I%04d",MapaErros[it].codErro);
                setOutCode(codErro);
                setOutMesg(MapaErros[it].msgErro);
                setou = true;
            }
            it++;
        }

        if ( !setou )
        {
            setOutCode("04I9999");
            setOutMesg("Erro desconhecido ocorrido");
        }
    }

    string outCode;
    string outMesg;
    string arquivo;
    int linha;
};

#endif // __CORECONTEXCEPTION_H__
