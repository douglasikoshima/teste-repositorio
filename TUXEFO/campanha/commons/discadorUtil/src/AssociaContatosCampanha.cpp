/**
 * 
 * @modulo  Commons
 * @usecase Classe utilitária para geração de discagens no discador via EAI.
 * @author  Renato Teixeira
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:51 $
 **/ 


#include "../include/AssociaContatosCampanha.h"

AssociaContatosCampanha::AssociaContatosCampanha() : cDiscadorUtil() {
	cDiscadorUtil::setChamada("AssContatosCam");
}

AssociaContatosCampanha::AssociaContatosCampanha(DOMNode*dnode, XMLGen*xml_g) : cDiscadorUtil(dnode, xml_g) {

	cDiscadorUtil::setChamada("AssContatosCam");
}

int   AssociaContatosCampanha::getNPU()
{
	return cDiscadorUtil::getNPU();
}

int   AssociaContatosCampanha::getIdCampanha()
{
	return cDiscadorUtil::getIdCampanha();
}

char* AssociaContatosCampanha::getHoraLigacoesInicio()
{
	return cDiscadorUtil::getHoraInicio();
}

char* AssociaContatosCampanha::getHoraLigacoesFim()
{
	return cDiscadorUtil::getHoraFim();
}

char* AssociaContatosCampanha::getDataHoraCampanhaInicio()
{
	return cDiscadorUtil::getDataHoraInicio();
}

char* AssociaContatosCampanha::getDataHoraCampanhaFim()    
{
	return cDiscadorUtil::getDataHoraFim();
}
     
char* AssociaContatosCampanha::getDataHoraLigacaoInicio()
{
	return cDiscadorUtil::getDataHoraLigacaoInicio();
}

char* AssociaContatosCampanha::getDataHoraLigacaoFim()
{
	return cDiscadorUtil::getDataHoraLigacaoFim();
}

int   AssociaContatosCampanha::getNumeroTentativas()
{
	return cDiscadorUtil::getNumeroTentativas();
}

int   AssociaContatosCampanha::getIntervaloTentativa()
{
	return cDiscadorUtil::getIntervaloTentativa();
}

int   AssociaContatosCampanha::getIdProcessoRetorno()
{
	return cDiscadorUtil::getIdProcessoRetorno();
}

char* AssociaContatosCampanha::getFileName()
{
	return cDiscadorUtil::getFileName();
}

void AssociaContatosCampanha::setNPU(int npu)
{
	cDiscadorUtil::setNPU(npu);
}

void AssociaContatosCampanha::setIdCampanha(int idCampanha)
{
	cDiscadorUtil::setIdCampanha(idCampanha);
}

void AssociaContatosCampanha::setHoraLigacoesInicio(char* horaInicio)
{
	cDiscadorUtil::setHoraInicio(horaInicio);
}

void AssociaContatosCampanha::setHoraLigacoesFim(char* horaFim)
{
	cDiscadorUtil::setHoraFim(horaFim);
}

void AssociaContatosCampanha::setDataHoraCampanhaInicio(char* dataHoraInicio)
{
	cDiscadorUtil::setDataHoraInicio(dataHoraInicio);
}

void AssociaContatosCampanha::setDataHoraCampanhaFim(char* dataHoraFim)
{
	cDiscadorUtil::setDataHoraFim(dataHoraFim);
}

void AssociaContatosCampanha::setDataHoraLigacaoInicio(char* dataHoraLigacaoInicio)
{
	cDiscadorUtil::setDataHoraLigacaoInicio(dataHoraLigacaoInicio);
}

void AssociaContatosCampanha::setDataHoraLigacaoFim(char* dataHoraLigacaoFim)
{
	cDiscadorUtil::setDataHoraLigacaoFim(dataHoraLigacaoFim);
}

void AssociaContatosCampanha::setNumeroTentativas(int numeroTentativas)
{
	cDiscadorUtil::setNumeroTentativas(numeroTentativas);
}

void AssociaContatosCampanha::setIntervaloTentativa(int intervaloTentativa)
{
	cDiscadorUtil::setIntervaloTentativa(intervaloTentativa);
}

void AssociaContatosCampanha::setIdProcessoRetorno(int idProcessoRetorno)
{
	cDiscadorUtil::setIdProcessoRetorno(idProcessoRetorno);
}

void AssociaContatosCampanha::setFileName(char* fileName)
{
	cDiscadorUtil::setFileName(fileName);
}

void AssociaContatosCampanha::enviar() 
{

	if (cDiscadorUtil::m_vlDados.npu == -1)
		throw new TuxException( "99E00001", "É necessário informar o código do NPU para associar contatos a uma campanha.");		

	if (cDiscadorUtil::m_vlDados.idCampanha == -1)
		throw new TuxException( "99E00001", "É necessário informar o código do idCampanha para associar contatos a uma campanha.");		

	if (cDiscadorUtil::m_vlDados.horaInicio == -1)
		throw new TuxException( "99E00001", "É necessário informar o código do horaInicio para associar contatos a uma campanha.");		

	if (cDiscadorUtil::m_vlDados.horaFim == -1)
		throw new TuxException( "99E00001", "É necessário informar o código do horaFim para associar contatos a uma campanha.");		

	if (cDiscadorUtil::m_vlDados.dataHoraInicio == -1)
		throw new TuxException( "99E00001", "É necessário informar o código do dataHoraInicio para associar contatos a uma campanha.");		

	if (cDiscadorUtil::m_vlDados.dataHoraFim == -1)
		throw new TuxException( "99E00001", "É necessário informar o código do dataHoraFim para associar contatos a uma campanha.");		

	if (cDiscadorUtil::m_vlDados.numeroTentativas == -1)
		throw new TuxException( "99E00001", "É necessário informar o código do numeroTentativas para associar contatos a uma campanha.");		

	if (cDiscadorUtil::m_vlDados.intervaloTentativa == -1)
		throw new TuxException( "99E00001", "É necessário informar o código do intervaloTentativa para associar contatos a uma campanha.");		

	if (cDiscadorUtil::m_vlDados.fileName == -1)
		throw new TuxException( "99E00001", "É necessário informar o código do fileName para associar contatos a uma campanha.");		

	cDiscadorUtil::enviar();
}
