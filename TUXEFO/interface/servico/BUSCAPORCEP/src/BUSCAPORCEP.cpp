#define BUSCAPORCEP__

#include <tuxfw.h>
#include <ctype.h>
#include "./include/endereco.h"

int IsNumeric(char* d);

DECLARE_TUXEDO_SERVICE(BUSCAPORCEP);

void implBUSCAPORCEP::Execute(DOMNode* pDnode, XMLGen* xml_g)
{
	try
    {
        ULOG_START("implBUSCAPORCEP::Execute");

        char *pPointer=NULL;
        char szNrCEP[255 + 1];
        char szLimiteEndereco[10 + 1];
        int iRet;
        int iAux;
        int iCount=0;
		int lengthCEP = 0;
        endereco clEndereco;

        /* referente a nrCEP */
    	pPointer = walkTree(pDnode,"nrCEP",0); ULOG("nrCEP[%s]", pPointer?pPointer:"...NULL..");
        strcpy(szNrCEP, pPointer?pPointer:"");
        if(pPointer) XMLString::release(&pPointer);		
        if(szNrCEP[0] == 0x00) { throw new TuxBasicSvcException("00W0002", "nrCEP invalido"); }

		lengthCEP = strlen(szNrCEP);
		if(lengthCEP != 8) { throw new TuxBasicSvcException("00W0002", "nrCEP invalido"); }
    
        /* referente a limiteEndereco */
    	pPointer = walkTree(pDnode,"limiteEndereco",0); ULOG("limiteEndereco[%s]", pPointer?pPointer:"...NULL..");
        strcpy(szLimiteEndereco, pPointer?pPointer:"");
        if(pPointer) XMLString::release(&pPointer);
        if(!IsNumeric(szLimiteEndereco)){ throw new TuxBasicSvcException("00W0002", "limiteEndereco nao numerico"); }
        if(szLimiteEndereco[0] == 0x00) { throw new TuxBasicSvcException("00W0002", "limiteEndereco invalido"); }
        iAux = atoi(szLimiteEndereco); ULOG("Quantidade de registro solicitados (%d)", iAux);
        if(iAux > 5) { throw new TuxBasicSvcException("00W0005", "limite excedido"); }
		if(iAux == 0) { throw new TuxBasicSvcException("00W0002", "limiteEndereco inválido"); }


    	xml_g->createTag("EnderecoClienteVO");
    	xml_g->addProp("xmlns","servicos.ura/vo");

    	ULOG("1.xml_g(%p)", xml_g);
		clEndereco.getEndereco(szNrCEP, szLimiteEndereco, xml_g, &iCount);

        ULOG("Total de registros(%d)", iCount);
        if(iCount == 0)
            throw new TuxBasicSvcException("00W0001","nenhum registro obtido");

    	xml_g->closeTag();

	}
	catch(TuxBasicOraException eboe)
	{
		ULOG("ERRO: %s COD: %d",eboe.pMsg, eboe.eCode);
		throw new TuxBasicSvcException("00W0003","erro de oracle");
	}

    ULOG_END("implBUSCAPORCEP::Execute");
	setStatusCode("00I0000","Sucesso");
}

/***********************************************************************/
int IsNumeric(char* d)
{
    for(; *d; d++ )
    {
       if ( !isdigit(*d) )
       {
            return 0;
       }
    }

    return 1;
}
