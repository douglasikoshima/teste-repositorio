/**
 * 
 * @modulo  Commons
 * @usecase Envio de e-mails.
 * @author  Renato Teixeira
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:34:16 $
 **/ 

#ifndef STASSOCIACAMPANHA
	#define STASSOCIACAMPANHA

	struct st_AssociaCampanha
	{
		char* path;
		char* arquivo;
		char* maquina;
		int   idSubCampanhaHistorico;
	};	

	struct st_vlAssociaCampanha
	{
		short path;
		short arquivo;
		short maquina;
		short idSubCampanhaHistorico;
	};

	struct st_DadosCampanha
	{
		int   npu;
		int   idCampanha;
		char  horaInicioLigacoes[6];
		char  horaFimLigacoes[6];
		char  dataHoraInicioCampanha[20];
		char  dataHoraFimCampanha[20];
		int   numeroTentativas;
		int   intervaloEntreTentativas;
		int   idProcessoASerRetornado;
		char  fileName[256];
	};

#endif
