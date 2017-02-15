#include <stdio.h>
#include <tuxfw/tuxfw.h>
#include <Defines/Defines.h>
#include <Linha/Linha.hpp>
#include <Parametro/Parametro.hpp>
#include <TuxHelperClever/TuxHelperClever.h>

//Habilitar para depurar no windows
//#define USE_DEBUG

DECLARE_TUXEDO_SERVICE(INCRGCONTATO);

void implINCRGCONTATO::Execute(DOMNode* dnode, XMLGen* xml_g) 
{
	CTuxHelperClever helper;

#ifdef USE_DEBUG

	int valTag = 0;

//  Código utilizado em Defines.h para a macro REC_CONTATO
//	Pode ser testado por aqui com mais precisao...

//#define REG_CONTATO(iResult, valTag) 

	int iResult = 0; 
	try 
	{ 		
		CLinha     oLinhaReg;
		CParametro oParametro;
        char*  pPointer = NULL;
		int    iCdAreaRegistro = -1; 
 		int    iNrLinha = -1; 
		int    iIdTipoRelacionamento = -1; 
		int    iIdContato = -1; 
		int    iIdCanal = -1; 
		int	   iIdTerminal = -1;
		int    iInRegistrarContato = 1;
		//memset(&protocolo,0,sizeof(protocolo));
		if(valTag)
		{
            pPointer = helper.walkTree(dnode,"inRegistrarContato", 0);
            ASSERT_INT(iInRegistrarContato, pPointer, "inRegistrarContato");
        //    if(pPointer) XMLString::release(&pPointer);
		}		

		if(iInRegistrarContato)
		{
            pPointer = helper.walkTree(dnode,"cdAreaRegistro", 0);
            ASSERT_INT(iCdAreaRegistro, pPointer, "cdAreaRegistro");
          //  if(pPointer) XMLString::release(&pPointer);

            pPointer = helper.walkTree(dnode,"nrLinha", 0);
            ASSERT_INT(iNrLinha, pPointer, "nrLinha");
          //  if(pPointer) XMLString::release(&pPointer);

            pPointer = helper.walkTree(dnode,"idTipoRelacionamento", 0);
            ASSERT_INT(iIdTipoRelacionamento, pPointer, "idTipoRelacionamento");
           // if(pPointer) XMLString::release(&pPointer);

            pPointer = helper.walkTree(dnode,"cdContato", 0);
            ASSERT_STR(pPointer, "cdContato");
            oParametro.setChave(pPointer);
           // if(pPointer) XMLString::release(&pPointer);
			oParametro.consultar();
			iIdContato = atoi(oParametro.getConsulta());

            pPointer = helper.walkTree(dnode,"idCanal", 0);
            ASSERT_INT(iIdCanal, pPointer, "idCanal");
           // if(pPointer) XMLString::release(&pPointer);

            pPointer = helper.walkTree(dnode,"idTerminal", 0);
            if(pPointer != NULL)
                ASSERT_INT(iIdTerminal, pPointer, "idTerminal");
           // if(pPointer) XMLString::release(&pPointer);

			oLinhaReg.setCdAreaRegistro(iCdAreaRegistro); 
			oLinhaReg.setNrLinha(iNrLinha); 
			oLinhaReg.getPessoa()->setIdTipoRelacionamento(iIdTipoRelacionamento); 
			oLinhaReg.getPessoa()->getRel()->setIdCanal(iIdCanal); 
			oLinhaReg.getPessoa()->getRel()->setIdContato(iIdContato); 
			oLinhaReg.registrarContato(iIdTerminal, this->getUser()); 
			oLinhaReg.getTipoProtocolo(helper.walkTree(dnode,"cdContato", 0)); 	
		}
		iResult = 0; 
	} 
    catch(...)
    {
        iResult = 1;
    }

#else

	// registrando contato
    // REG_CONTATO(iResult, REG_NAO_VALIDA_TAG);
	REG_CONTATO_PROTOCOLO(iResult,REG_NAO_VALIDA_TAG,protocolo);

#endif
	xml_g->createTag("ProtocoloVO");
	xml_g->addProp("xmlns","cliente.fo.vivo.com.br/vo");
	if(iResult)
	{
		setStatusCode("11W0001", "NAO FOI POSSIVEL REALIZAR O REGISTRO DE CONTATO");
	}
	else
	{
		xml_g->addItem("nrProtocolo",protocolo.nrProtocolo);	
		setStatusCode("11I0000", "REGISTRO REALIZADO COM SUCESSO");
	}
	xml_g->closeTag();
}