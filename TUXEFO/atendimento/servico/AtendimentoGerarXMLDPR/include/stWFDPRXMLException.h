/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Cassio Garcia
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:30 $
 **/

#ifndef __STWFEXCEPTIONXMLDPR_H__
#define __STWFEXCEPTIONXMLDPR_H__

#ifdef WIN32
#pragma warning(disable:4786)
#endif

#include <string>
using namespace std;

// Códigos de erro usados em setStatusCode() (max=9999)
#define ERR_DPR_GERANDOXML                  1
#define ERR_DPR_PONTEIRONULO                2
#define ERR_DPR_TMAX_XML_OVERFLOW           3

struct DPRXMLException
{
    DPRXMLException(const char *cod,const char *msg,const char *arq=__FILE__,int lin=__LINE__)
        { 
            setOutCode(cod);setOutMesg(msg);setArquivo(arq);setLinha(lin);
            ULOG("Erro '%s-%s' ocorrido na linha %d do arquivo %s",getCodErr(),getMsgErr(),lin,arq); 
        }

    DPRXMLException(int cod,string msg,const char *arq=__FILE__,int lin=__LINE__)
        {
            char codErro[9];sprintf(codErro,"04E%04d",cod<0 ?cod*(-1):cod);
            setOutCode(codErro);setOutMesg(msg.c_str());setArquivo(arq);setLinha(lin);
            ULOG("Erro '%s-%s' ocorrido na linha %d do arquivo %s",getCodErr(),getMsgErr(),lin,arq);
        }

    DPRXMLException(int cod,const char *arq,int lin)
        { 
            setarErro(cod);setArquivo(arq);setLinha(lin);
            ULOG("Erro '%s-%s' ocorrido na linha %d do arquivo %s",getCodErr(),getMsgErr(),lin,arq); 
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
            { ERR_DPR_GERANDOXML,"Falha na geração do XML" },
            { ERR_DPR_PONTEIRONULO,"Ponteiro nulo" },
            { ERR_DPR_TMAX_XML_OVERFLOW, "Tamanho máximo do XML (4000) estourou!" },
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

#endif // __DPRXMLEXCEPTION_H__
