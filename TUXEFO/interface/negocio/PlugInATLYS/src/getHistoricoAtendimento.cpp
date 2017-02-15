#include "PlugInATLYS.h"

#include <tuxfw.h>
#include "../../PlugInBE/include/Util.h"

#include <cstdlib>
#include <cstring>

using namespace std;


void PlugInATLYS::getHistoricoAtendimento()
{
/*	JAVA -> FO

	<msg>
		<msgHdr>
			<user>2</user>
			<service>TUXPROXYBE</service>
		</msgHdr>
		<msgBody>
			<ProxyOperacao>getHistoricoAtendimento</ProxyOperacao>
			<ProxyLinha></ProxyLinha>
			<usuario>FO</usuario>
			<idcontasistemaorigem>123</idcontasistemaorigem>
			<xmlns>test.vivo.com.br/vo</xmlns>
			<dataIni></dataIni>
			<dataFim></dataFim>
		</msgBody>
	</msg>
*/

	char* pc_acctNbr = NULL;
	char* pc_sbscrpId = NULL;

	// Pega idLinhaSystemaOrigem
	pc_sbscrpId = getIdLinhaSistemaOrigem();


	// Trata a tag 'idcontasistemaorigem'
	pc_acctNbr = tuxhp->walkTree(dnode, TAG_XML_IN_ID_CONTA_SIS_ORIGEM, 0);

	if (pc_acctNbr == NULL)
		throw new TuxBasicSvcException(NRO_ID_CONTA_SIS_ORG_NE, MSG_ID_CONTA_SIS_ORG_NE);

	if (pc_acctNbr == '\0')
		throw new TuxBasicSvcException(NRO_ID_CONTA_SIS_ORG_VV, MSG_ID_CONTA_SIS_ORG_VV);

	if (! Util::isNum(pc_acctNbr))
		throw new TuxBasicSvcException(NRO_ID_CONTA_SIS_ORG_VI, MSG_ID_CONTA_SIS_ORG_VI);

	tuxfw_getlogger()->debug("IdContaSistemaOrigem: %s", pc_acctNbr);

/*	FO -> GW

	<inputSearchAccountNotes acctNbr="xxx"/>
*/

	XMLGen XMLtoSearchAccountNotes;
	DOMNode* XMLfromSearchAccountNotes = NULL;

	XMLtoSearchAccountNotes.createTag(XML_ATL_INPUT_ACCT_NOTES);
	XMLtoSearchAccountNotes.addProp(XML_ATL_ACCT_NBR, pc_acctNbr);

	if (pc_sbscrpId != NULL)
		XMLtoSearchAccountNotes.addProp(XML_ATL_ACCT_SBSCRID, pc_sbscrpId);

	XMLtoSearchAccountNotes.closeTag();


	// Chama a API para trazer as informações de histórico de atendimento.
	XMLfromSearchAccountNotes = callRemoteAPI(this->getServiceName(), &XMLtoSearchAccountNotes, XML_ATL_INPUT_ACCT_NOTES);

	if ((getLastStatusCode() != NULL) && (strlen(getLastStatusCode()) > 3) && (getLastStatusCode()[2] == 'E')) 
		throw new TuxBasicSvcException(getLastStatusCode(), getLastStatusText());

	if (XMLfromSearchAccountNotes == NULL)
		throw new TuxBasicSvcException(NRO_ERR_ATLYS, MSG_ERR_ATLYS);

/*	GW -> FO

	<outputSearchAccountNotes areMore="false">
		<acctNote acctNbr="xxx" sbscrpId="xxx" noteDate="xxx" noteTime="xxx" noteText="xxx" seqNbr="xxx" noteType="xxx" userId="xxx" hiPriorityFlag="xxx"/>
	</outputSearchAccountNotes>
*/

	DOMNode* po_outputSearchAccountNotes = NULL;

	po_outputSearchAccountNotes = tuxhp->walkDOM(XMLfromSearchAccountNotes, XML_ATL_OUTPUT_SEARCH_ACCOUNT_NOTES, 0);

	if (po_outputSearchAccountNotes == NULL)
		throw new TuxBasicSvcException(NRO_ERR_ATLYS, MSG_ERR_ATLYS);

/*	FO -> JAVA

	<HistoricoAtendimentoVO>
		<HistoricoAtendimentoItem>
			<descricao></descricao>
			<data></data>
			<servico></servico>
			<inPrioridade></inPrioridade>
		</HistoricoAtendimentoItem>
	</HistoricoAtendimentoVO>
*/

	DOMNode* po_acctNote = NULL;


	xml_g->createTag(TAG_XML_OUT_HISTORICO_ATENDIMENTO_VO);

		// Pega o valor da tag 'xmlns' de entrada do xml
		char* pc_xmlns = NULL;

		pc_xmlns = tuxhp->walkTree(dnode, TAG_XML_IN_XMLNS, 0);

		if(pc_xmlns != NULL)
			xml_g->addProp(PROP_XML_OUT_XMLNS, pc_xmlns);

		for (unsigned int i = 0; (po_acctNote = tuxhp->walkDOM(po_outputSearchAccountNotes, XML_ATL_ACCT_NOTE, i)) != NULL; i++)
		{
			// Recupera os dados que precisam ser formatados em variáveis locais.
			char* pcDataNotaAtl = tuxhp->walkAttr(po_acctNote, XML_ATL_ACCT_NOTE, XML_ATL_NOTE_DATE, i);
			char* pcHoraNotaAtl = tuxhp->walkAttr(po_acctNote, XML_ATL_ACCT_NOTE, XML_ATL_NOTE_TIME, i);
			char* pcIndPriorAtl = tuxhp->walkAttr(po_acctNote, XML_ATL_ACCT_NOTE, XML_ATL_NOTE_PRI, i);

			// Formata os dados recuperados acima.
			char sDtNota[20];

			memset(sDtNota, '\0', sizeof(sDtNota));

			if ((pcDataNotaAtl != NULL) && (strlen(pcDataNotaAtl) == 10))
			{
				strncpy(sDtNota, (pcDataNotaAtl + 8), 2);
				strcat(sDtNota, "/");
				strncat(sDtNota, (pcDataNotaAtl + 5), 2);
				strcat(sDtNota, "/");
				strncat(sDtNota, pcDataNotaAtl, 4);
			}

			if ((pcHoraNotaAtl != NULL) && (strlen(pcHoraNotaAtl) == 8))
			{
				strcat(sDtNota, " ");
				strncat(sDtNota, pcHoraNotaAtl, 2);
				strcat(sDtNota, ":");
				strncat(sDtNota, (pcHoraNotaAtl + 2), 2);
				strcat(sDtNota, ":");
				strncat(sDtNota, (pcHoraNotaAtl + 4), 2);
			}

			char sInPrioridade[2];
			memset(sInPrioridade, '\0', sizeof(sInPrioridade));

			if (pcIndPriorAtl != NULL)
			{
				if (strcmp(pcIndPriorAtl, XML_VAL_TRUE) == 0)
					sInPrioridade[0] = 'S';
				else
					sInPrioridade[0] = 'N';
			}

			// Monta XML de saída
			xml_g->createTag(TAG_XML_OUT_HISTORICO_ATENDIMENTO_ITEM);

				xml_g->addItem(TAG_XML_OUT_DATA, sDtNota);
				xml_g->addItem(TAG_XML_OUT_DESCRICAO, tuxhp->walkAttr(po_acctNote, XML_ATL_ACCT_NOTE, XML_ATL_NOTE_DESC, i));
				xml_g->addItem(TAG_XML_OUT_IN_PRIORIDADE, sInPrioridade);
			
			xml_g->closeTag();
		}

	xml_g->closeTag();
}
