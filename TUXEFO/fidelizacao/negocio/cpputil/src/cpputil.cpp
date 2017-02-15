//
// $Id: cpputil.cpp,v 1.1 2009/07/31 15:33:39 a5110702 Exp $
//

#include "cpputil.hpp"

//
// int  get_tag(char *parm,DOMNode *dnode,char *tag,int indice,char *fmt,int codErro)
// 
//   parm    : Buffer onde serah copiado informacao. Deve ser alocado
//             externamente.
//   dnode   : DOM de pesquisa
//   tag     : tag de pesquisa
//   indice  : Ocorrencia do tag ( 0 - primeira, 1 - segunda ...)
//   fmt     : formato do erro ( campanha ou fidelizacao )
//   codErro : n > 0 -- Erro se nao encontrar tag
//             -1    -- Retorna -1, caso nao encontre tag
//
int get_tag_base(char *parm,DOMNode *dnode,char *tag,int indice,char *fmt,int codErro)
{
    char *lcParm;
    int rc = 0;

    try
    {
        TuxHelper txh;

        lcParm = txh.walkTree(dnode,tag,indice);

        if(lcParm != NULL) 
        {
            strcpy(parm,lcParm);
            ulog("%s='%s'",tag,parm);
            rc = 0;
        }

        if((!lcParm) && (codErro != -1))
        {
            char codErroStr[10];
            char buf[100];

            sprintf(codErroStr, fmt, codErro);
            sprintf(buf,"Cannot obtain value from tag [%s] from XML string",tag);
            ulog("%s",buf);
            XMLString::release(&lcParm);
            throw new TuxBasicSvcException(codErroStr,buf);
        }

        if( (!lcParm) && (codErro == -1) )
        {
            rc = -1;
        }
    }
    catch(...)
    {
        throw;
    }

    XMLString::release(&lcParm);

    return(rc);
}


void cl_flag(int *flg)
{
    *flg = 0;
}

void st_flag(int *flg)
{
    *flg = 1;
}


