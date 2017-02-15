/**
 * 
 * @modulo  Commons
 * @usecase Prototipo para gera��o de liga��es no discador.
 * @author  Renato Teixeira
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:51 $
 **/ 


#include "../include/ReagendaContatoCampanha.h"

ReagendaContatoCampanha::ReagendaContatoCampanha() : cDiscadorUtil() {
	cDiscadorUtil::setChamada("ReaContatoCamp");
	cDiscadorUtil::setEntity("002");
}

ReagendaContatoCampanha::ReagendaContatoCampanha(DOMNode*dnode, XMLGen*xml_g) : cDiscadorUtil(dnode, xml_g) {
	cDiscadorUtil::setChamada("ReaContatoCamp");
	cDiscadorUtil::setEntity("002");
}	

int   ReagendaContatoCampanha::getNPU()
{
	return cDiscadorUtil::getNPU();
}

int   ReagendaContatoCampanha::getIdLigacao()
{
	return cDiscadorUtil::getIdLigacao();
}

int   ReagendaContatoCampanha::getIdCampanha()
{
	return cDiscadorUtil::getIdCampanha();
}

char* ReagendaContatoCampanha::getDataHoraLigacao()
{
	return cDiscadorUtil::getDataHoraLigacaoInicio();
}

void ReagendaContatoCampanha::setNPU(int npu)
{
	cDiscadorUtil::setNPU(npu);
}

void ReagendaContatoCampanha::setIdLigacao(int idLigacao)
{
	cDiscadorUtil::setIdLigacao(idLigacao);
}

void ReagendaContatoCampanha::setIdCampanha(int idCampanha)
{
	cDiscadorUtil::setIdCampanha(idCampanha);
}

void ReagendaContatoCampanha::setDataHoraLigacao(char* dataHoraLigacao)
{
	cDiscadorUtil::setDataHoraLigacaoInicio(dataHoraLigacao);
}

int ReagendaContatoCampanha::getRegistrosContatoLista()
{
	return cDiscadorUtil::getRegistrosContatoLista();
}

int ReagendaContatoCampanha::addContatoLista(char* campanha)
{
	return cDiscadorUtil::addContatoLista(campanha);
}

char* ReagendaContatoCampanha::getContatoLista(int idRegistro)
{
	return cDiscadorUtil::getContatoLista(idRegistro);
}

void ReagendaContatoCampanha::limpaContatoLista()
{
	cDiscadorUtil::limpaContatoLista();
}



void ReagendaContatoCampanha::enviar() {

	if (cDiscadorUtil::m_vlDados.npu == -1)
		throw new TuxException( "99E00001", "� necess�rio informar o c�digo do NPU para reagendar contatos a campanha.");		
	
	if (cDiscadorUtil::m_vlDados.idLigacao == -1)
		throw new TuxException( "99E00002", "� necess�rio informar o idLigacao para reagendar contatos a campanha.");		

	if (cDiscadorUtil::m_vlDados.idCampanha == -1)
		throw new TuxException( "99E00003", "� necess�rio informar o idCampanha para reagendar contatos a campanha.");		

	if (cDiscadorUtil::m_vlDados.dataHoraLigacaoInicio == -1)
		throw new TuxException( "99E00004", "� necess�rio informar a dataHoraLigacao para reagendar contatos a campanha.");		

	if (cDiscadorUtil::getRegistrosContatoLista() <= 0)
		throw new TuxException( "99E00004", "� necess�rio informar a os contatos para reagendar a campanha.");		

	cDiscadorUtil::enviar();
	
}
