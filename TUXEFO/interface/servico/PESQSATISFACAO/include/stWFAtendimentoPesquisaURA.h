/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Renato Teixeira
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:34:12 $
 **/ 

#ifndef STWFAtendimentoPesquisaURA
	#define STWFAtendimentoPesquisaURA

	struct st_AtendimentoPesquisaURA {
		int  idAtendimento;
		int  vlNota;
	};

	struct st_vlAtendimentoPesquisaURA {
		short idAtendimento;
		short vlNota;
	};
#endif
