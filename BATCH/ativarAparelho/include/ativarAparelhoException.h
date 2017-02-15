
#ifndef __ativarAparelhoException_H__
#define __ativarAparelhoException_H__

#ifdef WIN32
#pragma warning(disable:4786)
#endif

#include <string>
using namespace std;

// Códigos de erro usados em setStatusCode() (max=9999)
//      [---tamanho máximo do label---]
#define ERR_BATCH_PARAMETROSINCOMPLETOS 1
#define ERR_BATCH_CONEXAO               2
#define ERR_BATCH_PARAMETROSDINAMICOS   3
#define ERR_BATCH_CONVER_TXT_DOM        4
#define ERR_BATCH_CONVER_TXT_NIL        5

#define ERR_BATCH_ERRO_ORACLE           100

#define ERR_CALLAPI_BUFFER_TX_NAOALOCA  200
#define ERR_CALLAPI_BUFFER_TX_INVALIDO  201
#define ERR_CALLAPI_BUFFER_RX_INVALIDO  202

#define ERR_CALLAPI_TPEINVAL            300
#define ERR_CALLAPI_TPENOENT            301
#define ERR_CALLAPI_TPEITYPE            302
#define ERR_CALLAPI_TPEOTYPE            303
#define ERR_CALLAPI_TPETRAN             304
#define ERR_CALLAPI_TPETIME             305
#define ERR_CALLAPI_TPESVCFAIL          306
#define ERR_CALLAPI_TPESVCERR           307
#define ERR_CALLAPI_TPEBLOCK            308
#define ERR_CALLAPI_TPGOTSIG            309
#define ERR_CALLAPI_TPEPROTO            310
#define ERR_CALLAPI_TPESYSTEM           311
#define ERR_CALLAPI_TPEOS               312
#define ERR_CALLAPI_DEFAULT             313

struct CAtivarAparelhoException
{
    CAtivarAparelhoException(const char *cod,const char *msg,const char *arq=__FILE__,int lin=__LINE__)
    { 
        setOutCode(cod);setOutMesg(msg);setArquivo(arq);setLinha(lin);
    }

    CAtivarAparelhoException(int cod,string msg,const char *arq=__FILE__,int lin=__LINE__)
    {
        char codErro[9];sprintf(codErro,"04I%04d",cod<0 ?cod*(-1):cod);
        setOutCode(codErro);setOutMesg(msg.c_str());setArquivo(arq);setLinha(lin);
    }

    CAtivarAparelhoException(int cod,const char *arq=__FILE__,int lin=__LINE__)
    { 
        setarErro(cod);setArquivo(arq);setLinha(lin);
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
            { ERR_BATCH_PARAMETROSINCOMPLETOS,"Parâmetros de entrada incompletos ou faltando" },
            { ERR_BATCH_CONEXAO,"*** MENSAGEM DINÂMICA ***" },
            { ERR_BATCH_PARAMETROSDINAMICOS,"Parâmetros dinâmicos não encontrados" },
            { ERR_BATCH_CONVER_TXT_DOM,"Erro na conversão de buffer recebido do Atlys para DOM" },
            { ERR_CALLAPI_BUFFER_TX_NAOALOCA,"Erro alocando buffer de envio" },
            { ERR_CALLAPI_BUFFER_TX_INVALIDO,"Área de buffer de entrada não existe" },
            { ERR_CALLAPI_BUFFER_RX_INVALIDO,"Área de buffer de retorno não existe" },
            { ERR_CALLAPI_TPEINVAL,"[TPEINVAL] Invalid arguments were given" },
            { ERR_CALLAPI_TPENOENT,"[TPENOENT] Cannot send to svc because it does not exist" },
            { ERR_CALLAPI_TPEITYPE,"[TPEITYPE] The type and sub-type of idata is not one of the allowed types and sub-types that svc accepts" },
            { ERR_CALLAPI_TPEOTYPE,"[TPEOTYPE] Either the type and sub-type of the reply are not known to the caller" },
            { ERR_CALLAPI_TPETRAN,"[TPETRAN] svc belongs to a server that does not support transactions and TPNOTRAN was not set" },
            { ERR_CALLAPI_TPETIME,"[TPETIME] A timeout occurred" },
            { ERR_CALLAPI_TPESVCFAIL,"[TPESVCFAIL] The service routine sending the caller's reply called tpreturn() with TPFAIL" },
            { ERR_CALLAPI_TPESVCERR,"[TPESVCERR] Tuxedo error" },
            { ERR_CALLAPI_TPEBLOCK,"[TPEBLOCK] A blocking condition was found on the send call and TPNOBLOCK was specified" },
            { ERR_CALLAPI_TPGOTSIG,"[TPGOTSIG] A signal was received and TPSIGRSTRT was not specified" },
            { ERR_CALLAPI_TPEPROTO,"[TPEPROTO] tpcall() was called improperly" },
            { ERR_CALLAPI_TPESYSTEM,"[TPESYSTEM] A BEA Tuxedo system error has occurred" },
            { ERR_CALLAPI_TPEOS,"[TPEOS] An operating system error has occurred" },
            { ERR_CALLAPI_DEFAULT,"[UNRECOGNIZED] An error has occurred" },
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

#endif // __ativarAparelhoException_H__
