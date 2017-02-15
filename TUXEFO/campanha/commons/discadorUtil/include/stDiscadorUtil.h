/**
 * 
 * @modulo  Commons
 * @usecase Envio de e-mails.
 * @author  Renato Teixeira
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:32 $
 **/ 

#ifndef stDiscadorUtil
	#define stDiscadorUtil

	struct st_DiscadorUtil 
	{
		char  entity[256];
		char  sequence[256];
		int   npu;
		int   idLigacao;
		int   idCampanha;
		char  horaInicio[256];
		char  horaFim[256];
		char  dataHoraInicio[256];
		char  dataHoraFim[256];
		char  dataConsultaInicio[256];
		char  dataConsultaFim[256];
		char  dataHoraLigacaoInicio[256];
		char  dataHoraLigacaoFim[256];
		int   numeroTentativas;
		int   intervaloTentativa;
		int   idProcessoRetorno;
		char  fileName[256];
		int   idUsuario;
		char  loginUsuarioCTI[256];
		char  loginPabx[256];
		char  chamada[256];
	};	

	struct st_vlDiscadorUtil 
	{
		short entity;
		short sequence;
		short npu;
		short idLigacao;
		short idCampanha;
		short listaContatos;
		short listaCampanhas;
		short horaInicio;
		short horaFim;
		short dataHoraInicio;
		short dataHoraFim;
		short dataConsultaInicio;
	    short dataConsultaFim;
		short dataHoraLigacaoInicio;
		short dataHoraLigacaoFim;
		short numeroTentativas;
		short intervaloTentativa;
		short idProcessoRetorno;
		short fileName;
		short idUsuario;
		short loginUsuarioCTI;
		short loginPabx;
		short chamada;
	};
#endif
