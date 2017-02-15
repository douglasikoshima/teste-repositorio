SUBDIRS=GetCampanha LstCampanha GetEmail ValSenhaWS LstArvoreCli LstDadosIni LstLembretFS \
	LstServicos LstSessao LstVigServ ValLinha LstExtratoPO LstHistRelac LstEndereco LstUltAcesso \
	LstProcessos LstTipoEnd ModEmail ModEndereco ModFraSecret EnvLembSenha LstBancos LstAcessoRap \
	IncAcessoRap ExcAcessoRap LstTipoRecar LstMenu LstPessoa ValPriAcesso LstQtdFavor ExcFraSecret \
	DadAcesso ValFraSecret VOLParam IncRgContato EnvMsg LstProfile IncRegSessao LstEmail LstDoc LstLinha \
	DadMenu GetBanners GetProcLinha ValidaSenhaWS GetCadPessoaPP GETIDTRANS SETIDTRANS LstAlertaVIP \
	ValPriAcessVIP ModAlertaVIP EnvAlertaVIP GetLinhaVoz EnvEmailSenha svcLinha svcEmpresa CTTCOPIA AdminstracaoGrupos \
	CttListarCont CttFrmRelacao CTTGRPLISTAR CttRetRelacao CttPerListar CttBxaRelacao BxaListarCont ConsultaRetorno \
	WF_LSTGRUPOASSOC WF_LSTPERFILASSOC WF_LSTPERFILGRU PPESSOA

all:
	for dir in $(SUBDIRS); do \
		cd $$dir; \
		make all; \
		cd ..; \
	done

clean:
	for dir in $(SUBDIRS); do \
		cd $$dir; \
		make clean; \
		cd ..; \
	done

install:
	for dir in $(SUBDIRS); do \
		cd $$dir; \
		make install; \
		cd ..; \
	done 

build:
	for dir in $(SUBDIRS); do \
		cd $$dir; \
		make; \
		make install; \
		cd ..; \
	done
