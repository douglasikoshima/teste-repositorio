/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Renato Teixeira
 * @version $Revision: 1.1.2.1 $
 * @CVS     $Author: a5116174 $ - $Date: 2011/08/12 17:11:37 $
 **/ 

#ifndef STWFAtendimentoPesquisaURA
	#define STWFAtendimentoPesquisaURA

	struct st_AtendimentoPesquisaURA {
		long idAtendimento;
		int  vlNota;
	};

	struct st_vlAtendimentoPesquisaURA {
		short idAtendimento;
		short vlNota;
	};
#endif
