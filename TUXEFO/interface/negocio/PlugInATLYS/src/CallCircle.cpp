#include "CallCircle.h"
#include "PlugInATLYS.h"

#include <tuxfw.h>

#include <cstring>

using namespace std;



CallCircle::CallCircle()
{
	strcpy (m_cAccNbr,"");
	strcpy (m_cEffDt,"");
	strcpy (m_cExprDt,"");	
}

CallCircle::~CallCircle(){};


// Setters
void CallCircle::setAccNbr(char* value)
{
	if(value == NULL)
		return;

	strcpy (this->m_cAccNbr,value);
}

void CallCircle::setEffDt(char* value)
{
	if(value == NULL)
		return;

	strcpy (this->m_cEffDt,value);
}

void CallCircle::setExprDt(char* value)
{
	if(value == NULL)
		return;

	strcpy (this->m_cExprDt,value);
}

// Getters	
char* CallCircle::getAccNbr()
{
	return this->m_cAccNbr; 
}

char* CallCircle::getEffDt()
{
	return this->m_cEffDt;
}

char* CallCircle::getExprDt()
{
	return this->m_cExprDt;
}


// Sobrecarga do operador de atribuição.
CallCircle& CallCircle::operator=(const CallCircle& oCallCircle)
{
	// Faz a Atruibução.
	strcpy( this->m_cAccNbr, oCallCircle.m_cAccNbr );
	strcpy( this->m_cEffDt,  oCallCircle.m_cEffDt );
	strcpy( this->m_cExprDt, oCallCircle.m_cExprDt );

	return *this;
}

bool CallCircle::operator==( const CallCircle & oCallCircle )
{ 	
    return ((strcmp(this->m_cAccNbr,oCallCircle.m_cAccNbr) == 0));

}

	//Implementação dos Parsing

// Metodo de Parsing para o XML de Retorno de GetSubscriptionInfo
void  CallCircle::getCallCircleInPrimarySvc(TuxHelper* tuxhp, DOMNode* outputDOMNodeGetSubscriptionInfo,char* pcSvcName, char* pcSeqNbr, char* pcListTypeInd, char* pcMethodInd)
{  
	/*
	Condição a ser verificada: 
	--------------------------		
	*/
    
    if(pcSvcName == NULL)
		throw new TuxBasicSvcException(ECD_ATL_TNE_PRIMARYSVC_SVCNM_NOT_NULL, EMSG_ATL_TNE_PRIMARYSVC_SVCNM_NOT_NULL);

	bool bAchou = false;
	DOMNode* auxDOMSvcAsgmInfo;
	DOMNode* auxDOMPrimarySvc;
	DOMNode* auxDOMCallCircle;

	char *pcAux;
	
	int i=0;

	while(!bAchou && (auxDOMSvcAsgmInfo = tuxhp->walkDOM(outputDOMNodeGetSubscriptionInfo,"svcAsgmInfo", i)) != NULL )
	{
		if((auxDOMPrimarySvc = tuxhp->walkDOM(auxDOMSvcAsgmInfo,"primarySvc", 0)) != NULL )
		{
			// lendo Atributo "svcNm" da Tag <primarySvc>
			pcAux  = tuxhp->getAttrValue(auxDOMPrimarySvc,"svcNm");	
			if(strcmp(pcAux, pcSvcName) == 0)
			{	
				// Obter <callCircle>
				if((auxDOMCallCircle = tuxhp->walkDOM(auxDOMPrimarySvc,"callCircle", 0)) != NULL )
				{
					// lendo Atributo "seqNbr" da Tag <callCircle>
					pcAux  = tuxhp->getAttrValue(auxDOMCallCircle,"seqNbr");	
					strcpy(pcSeqNbr,pcAux);

					// lendo Atributo "listTypeInd" da Tag <callCircle>
					pcAux  = tuxhp->getAttrValue(auxDOMCallCircle,"listTypeInd");	
					strcpy(pcListTypeInd,pcAux);

					// lendo Atributo "methodInd" da Tag <callCircle>
					pcAux  = tuxhp->getAttrValue(auxDOMCallCircle,"methodInd");	
					strcpy(pcMethodInd,pcAux);

					bAchou = true;
				}
				else
				{
					// Chamar setFavorito(), para Ativar o callCircle (depois)

					throw new TuxBasicSvcException(ECD_ATL_TNE_CALLCIRCLE, EMSG_ATL_TNE_CALLCIRCLE);
				}	
			}


		}
		else
			throw new TuxBasicSvcException(ECD_ATL_TNE_PRIMARYSVC, EMSG_ATL_TNE_PRIMARYSVC);

		i++;
	}

	if(!bAchou)
	{
		tuxfw_getlogger()->debug("Nao Encontrado o PrimarySvc:svcNm=%s",pcSvcName); 
		
		throw new TuxBasicSvcException(ECD_ATL_TNE_PRIMARYSVC_SVCNM, EMSG_ATL_TNE_PRIMARYSVC_SVCNM);
	}
}


// Metodo de Parsing para o XML de Retorno de GetCallingCircle
void CallCircle::getListCallCircle(TuxHelper* tuxhp, DOMNode* outputDOMNodeGetCallingCircle, list<CallCircle>& lstCallCircle)
{
	CallCircle oCallCircle;

	DOMNode* auxDOMCallCircle;
	char *pcAux;

	int i=0;	
	
	while((auxDOMCallCircle = tuxhp->walkDOM(outputDOMNodeGetCallingCircle,"callCircleInfo", i)) != NULL )
	{
		// lendo Atributo "accessNbr" da Tag <callCircleInfo>
		pcAux  = tuxhp->getAttrValue(auxDOMCallCircle,"accessNbr");	
		oCallCircle.setAccNbr(pcAux);
		
		// lendo Atributo "effDt" da Tag <callCircleInfo>
		pcAux  = tuxhp->getAttrValue(auxDOMCallCircle,"effDt");	
		oCallCircle.setEffDt(pcAux);

		// lendo Atributo "exprDt" da Tag <callCircleInfo>
		pcAux  = tuxhp->getAttrValue(auxDOMCallCircle,"exprDt");	
		oCallCircle.setExprDt(pcAux);

 		lstCallCircle.push_back( oCallCircle );

		i++;
	}

	/*
	if(lstCallCircle.size() == 0)
	{
		tuxfw_getlogger()->debug("LISTA VAZIA: Nenhum <callCircleInfo> Encontrado"); 
	
		throw new TuxBasicSvcException("24E0000", "Nenhum <callCircleInfo> Encontrado");
	}
	*/

}







