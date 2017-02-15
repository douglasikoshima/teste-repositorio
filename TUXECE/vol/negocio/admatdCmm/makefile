include ../../defines



DIR=negocio

SUBDIR=admatdCmm

SRCDIR=$(BASEPATH)/$(DIR)/$(SUBDIR)/src

LIBDIR=$(BASEPATH)/$(DIR)/$(SUBDIR)/lib

INCDIR=$(BASEPATH)/$(DIR)/$(SUBDIR)/include/$(SUBDIR)

INCPROC=$(BASEPATH)/$(DIR)/$(SUBDIR)/include

MSGDIR=$(BASEPATH)/$(DIR)/$(SUBDIR)/messages



#PCPP=lista de fontes a ser precompilado pelo PRO*C

PCPP=CFiltroArvContatoPC.pcpp CCttCmb.pcpp CCttPes.pcpp CAnt.pcpp CTap.pcpp CCttFtr.pcpp CAvs.pcpp CBxa.pcpp CBxaAnt.pcpp CBxaHie.pcpp \
	CBxaMsg.pcpp CBxaNom.pcpp CCmp.pcpp CCmpCls.pcpp CCmpDom.pcpp CCmpFse.pcpp CCtt.pcpp CCttAca.pcpp CCttFch.pcpp CCttHie.pcpp \
	CCttInf.pcpp CCttLin.pcpp CCttNom.pcpp CCttRtn.pcpp CCttUfo.pcpp CDom.pcpp CDomTbl.pcpp CFlh.pcpp CFlhAnt.pcpp CFlhAvs.pcpp \
	CFlhBxa.pcpp CFlhCmp.pcpp CFlhDpr.pcpp CFlhMsg.pcpp CFlhPrz.pcpp CFrd.pcpp CFrdMnc.pcpp CFrdNom.pcpp CFrdTip.pcpp CFrdUF.pcpp \
	CHrrVra.pcpp CLin.pcpp CMnc.pcpp CPcd.pcpp CRes.pcpp CPer.pcpp CSat.pcpp CSatPer.pcpp CSgm.pcpp CUF.pcpp CUfo.pcpp CAtv.pcpp \
	CCrg.pcpp CPss.pcpp CTO.pcpp CUnd.pcpp CCA.pcpp CNC.pcpp CHUP.pcpp COU.pcpp COrg.pcpp COH.pcpp CNvl.pcpp CNH.pcpp \
	CContatoPesquisaSatisfacao.pcpp CContatoSegmentacao.pcpp CContatoTipoCarteira.pcpp CContatoTipoRelacionamento.pcpp \
	CContatoTipoRetorno.pcpp CContatoTipoRetornoRelacoes.pcpp CNivelSequencia.pcpp CPesquisaSatisfacaoTPPessoa.pcpp \
	CTipoRetornoSequencia.pcpp CContatoGrupo.pcpp CSequencia.pcpp CTipPes.pcpp CBxaFtr.pcpp CBxaUfo.pcpp CFluxoFaseGrupo.pcpp \
	CContatoGrupoRC.pcpp CAdmCamposDependPC.pcpp CCtpa.pcpp CTabConsRelac.pcpp CSistemaOrigemPC.pcpp CProcedenciaPC.pcpp CCfgPalitagem.pcpp \
	CPerfilIDM.pcpp CSms.pcpp
#CPP=lista de fontes a ser compilado

SRC=$(CPP) $(PCPP:.pcpp=.cpp)



#OBJ=lista de obj´s a ser linkado para gerar o servicex

OBJ=lib/CFiltroArvContato.o lib/CFiltroArvContatoPC.o lib/CAntItr.o lib/CCttPesItr.o lib/CCttPes.o lib/CCttCmbItr.o lib/CCttCmb.o \
	lib/CTapItr.o lib/CCttFtrItr.o lib/CAvsItr.o lib/CBxaAntItr.o lib/CBxaHieItr.o lib/CBxaItr.o lib/CBxaMsgItr.o lib/CBxaNomItr.o \
	lib/CCmpClsItr.o lib/CCmpDomItr.o lib/CCmpFseItr.o lib/CCmpItr.o lib/CCttAcaItr.o lib/CCttFchItr.o lib/CCttHieItr.o \
	lib/CCttInfItr.o lib/CCttItr.o lib/CCttLinItr.o lib/CCttNomItr.o lib/CCttRtnItr.o lib/CCttUfoItr.o lib/CDomItr.o \
	lib/CDomTblItr.o lib/CFlhAntItr.o lib/CFlhAvsItr.o lib/CFlhBxaItr.o lib/CFlhCmpItr.o lib/CFlhDprItr.o lib/CFlhItr.o \
	lib/CFlhMsgItr.o lib/CFlhPrzItr.o lib/CFrdItr.o lib/CFrdMncItr.o lib/CFrdNomItr.o lib/CFrdTipItr.o lib/CFrdUFItr.o \
	lib/CHrrVraItr.o lib/CLinItr.o lib/CMncItr.o lib/CPcdItr.o lib/CResItr.o lib/CPerItr.o lib/CSatItr.o lib/CSatPerItr.o \
	lib/CSgmItr.o lib/CUFItr.o lib/CUfoItr.o lib/CTap.o lib/CCttFtr.o lib/CAnt.o lib/CAvs.o lib/CBxa.o lib/CBxaAnt.o \
	lib/CBxaHie.o lib/CBxaMsg.o lib/CBxaNom.o lib/CCmp.o lib/CCmpCls.o lib/CCmpDom.o lib/CCmpFse.o lib/CCtt.o lib/CCttAca.o \
	lib/CCttFch.o lib/CCttHie.o lib/CCttInf.o lib/CCttLin.o lib/CCttNom.o lib/CCttRtn.o lib/CCttUfo.o lib/CDom.o lib/CDomTbl.o \
	lib/CFlh.o lib/CFlhAnt.o lib/CFlhAvs.o lib/CFlhBxa.o lib/CFlhCmp.o lib/CFlhDpr.o lib/CFlhMsg.o lib/CFlhPrz.o lib/CFrd.o \
	lib/CFrdMnc.o lib/CFrdNom.o lib/CFrdTip.o lib/CFrdUF.o lib/CHrrVra.o lib/CLin.o lib/CMnc.o lib/CPcd.o lib/CRes.o lib/CPer.o \
	lib/CSat.o lib/CSatPer.o lib/CSgm.o lib/CUF.o lib/CUfo.o lib/CAtvItr.o lib/CAtv.o lib/CCrgItr.o lib/CCrg.o lib/CPssItr.o \
	lib/CPss.o lib/CTOItr.o lib/CTO.o lib/CUndItr.o lib/CUnd.o lib/CCAItr.o lib/CCA.o lib/CNCItr.o lib/CNC.o lib/CHUPItr.o \
	lib/CHUP.o lib/COUItr.o lib/COU.o lib/COrgItr.o lib/COrg.o lib/COHItr.o lib/COH.o lib/CNvlItr.o lib/CNvl.o lib/CNHItr.o \
	lib/CNH.o lib/CContatoPesquisaSatisfacaoItr.o lib/CContatoSegmentacaoItr.o lib/CContatoTipoCarteiraItr.o \
	lib/CContatoTipoRelacionamentoItr.o lib/CContatoTipoRetornoRelacoesItr.o lib/CPesquisaSatisfacaoTPPessoaItr.o \
	lib/CNivelSequenciaItr.o lib/CContatoTipoRetornoItr.o lib/CTipoRetornoSequenciaItr.o lib/CContatoGrupoItr.o \
	lib/CSequenciaItr.o lib/CTipPesItr.o lib/CContatoPesquisaSatisfacao.o lib/CContatoSegmentacao.o lib/CContatoTipoCarteira.o \
	lib/CContatoTipoRelacionamento.o lib/CContatoTipoRetorno.o lib/CContatoTipoRetornoRelacoes.o lib/CNivelSequencia.o \
	lib/CPesquisaSatisfacaoTPPessoa.o lib/CTipoRetornoSequencia.o lib/CContatoGrupo.o lib/CSequencia.o lib/CTipPes.o \
	lib/CBxaFtrItr.o lib/CBxaFtr.o lib/CBxaUfoItr.o lib/CBxaUfo.o lib/CSafePointer.o lib/CFluxoFaseGrupoItr.o \
	lib/CFluxoFaseGrupo.o lib/CContatoGrupoRCItr.o lib/CContatoGrupoRC.o lib/CAdmCamposDependPC.o lib/CAdmCamposDepend.o \
	lib/CCtpa.o lib/CCtpaItr.o lib/CTabConsRelac.o lib/CTabConsRelacItr.o lib/CSistemaOrigemPC.o lib/CProcedenciaPC.o lib/CProcedenciaItr.o lib/CSistemaOrigemItr.o \
	lib/CCfgPalitagemItr.o lib/CCfgPalitagem.o lib/CPerfilIDM.o lib/CSms.o lib/CSmsItr.o

all: $(LIBDIR)/lib$(SUBDIR).so

#
#  Pre Compile
#  $(SRCDIR)/cpp_tpl.cpp:
#	 $(PROC) $*.pcpp
#
$(SRCDIR)/CPerfilIDM.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CSms.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CCfgPalitagem.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CAdmCamposDependPC.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CFiltroArvContatoPC.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CFluxoFaseGrupoItr.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CFluxoFaseGrupo.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CSafePointer.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CBxaUfoItr.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CBxaUfo.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CBxaFtrItr.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CBxaFtr.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CTipPesItr.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CCttPesItr.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CCttPes.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CCttCmbItr.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CAntItr.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CTapItr.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CCttFtrItr.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CAvsItr.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CBxaAntItr.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CBxaHieItr.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CBxaItr.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CBxaMsgItr.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CBxaNomItr.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CCmpClsItr.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CCmpDomItr.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CCmpFseItr.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CCmpItr.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CCttAcaItr.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CCttFchItr.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CCttHieItr.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CCttInfItr.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CCttItr.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CCttLinItr.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CCttNomItr.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CCttRtnItr.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CCttUfoItr.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CDomItr.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CDomTblItr.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CFlhAntItr.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CFlhAvsItr.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CFlhBxaItr.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CFlhCmpItr.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CFlhDprItr.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CFlhItr.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CFlhMsgItr.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CFlhPrzItr.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CFrdItr.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CFrdMncItr.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CFrdNomItr.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CFrdTipItr.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CFrdUFItr.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CHrrVraItr.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CLinItr.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CMncItr.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CPcdItr.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CPerItr.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CResItr.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CSatItr.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CSatPerItr.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CSgmItr.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CUFItr.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CUfoItr.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CTipPes.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CCttCmb.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CTap.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CCttFtr.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CAnt.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CAvs.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CBxa.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CBxaAnt.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CBxaHie.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CBxaMsg.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CBxaNom.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CCmp.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CCmpCls.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CCmpDom.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CCmpFse.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CCtt.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CCttAca.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CCttFch.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CCttHie.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CCttInf.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CCttLin.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CCttNom.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CCttRtn.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CCttUfo.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CDom.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CDomTbl.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CFlh.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CFlhAnt.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CFlhAvs.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CFlhBxa.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CFlhCmp.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CFlhDpr.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CFlhMsg.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CFlhPrz.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CFrd.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CFrdMnc.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CFrdNom.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CFrdTip.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CFrdUF.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CHrrVra.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CLin.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CMnc.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CPcd.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CRes.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CPer.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CSat.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CSatPer.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CSgm.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CUF.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CUfo.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CAtvItr.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CAtv.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CCrgItr.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CCrg.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CPssItr.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CPss.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CTOItr.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CTO.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CUndItr.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CUnd.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CCAItr.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CCA.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CNCItr.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CNC.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CHUPItr.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CHUP.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/COUItr.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/COU.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/COrgItr.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/COrg.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/COHItr.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/COH.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CNvlItr.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CNvl.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CNHItr.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CNH.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CContatoPesquisaSatisfacaoItr.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CContatoSegmentacaoItr.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CContatoTipoCarteiraItr.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CContatoTipoRelacionamentoItr.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CContatoTipoRetornoItr.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CContatoTipoRetornoRelacoesItr.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CNivelSequenciaItr.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CPesquisaSatisfacaoTPPessoaItr.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CTipoRetornoSequenciaItr.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CContatoGrupoItr.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CContatoGrupoRCItr.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CContatoGrupo.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CContatoGrupoRC.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CContatoPesquisaSatisfacao.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CContatoSegmentacao.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CContatoTipoCarteira.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CContatoTipoRelacionamento.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CContatoTipoRetorno.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CContatoTipoRetornoRelacoes.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CNivelSequencia.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CPesquisaSatisfacaoTPPessoa.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CTipoRetornoSequencia.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CSequenciaItr.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CSequencia.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CCtpa.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CTabConsRelac.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CSistemaOrigemPC.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CProcedenciaPC.cpp:
	$(PROC) $*.pcpp

INCAUX= `echo $(CUSTSO) | awk '{ flds=split($$0,v," ");    \
                         for(i=1;i<=flds;i++)              \
                          printf("$(DESINCDIRS)/%s ",v[i]);\
                         next}'`
COMPFLAGS= -Iinclude $(PINCLUDE) $(DESINCDIRS)/$(SUBDIR) $(INCAUX) $(CFLAGS)

#
#  FML
#  messages/msg_tpl.fml.h:messages/msg_tpl.fml
#	cd messages; MKFLDHDR messages/msg_tpl.fml; cd ..
#


#  Compile
#  lib/obj_tpl.o:$(SRCDIR)/cpp.cpp makefile
#        $(SACCPP) -Iinclude $(PINCLUDE) -I$(DESINCDIRS) $(CFLAGS) $(SRCDIR)/a.cpp -o $*.o
#
lib/CPerfilIDM.o:$(SRCDIR)/CPerfilIDM.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CPerfilIDM.cpp -o $*.o
lib/CSms.o:$(SRCDIR)/CSms.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CSms.cpp -o $*.o
lib/CSmsItr.o:$(SRCDIR)/CSmsItr.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CSmsItr.cpp -o $*.o
lib/CCfgPalitagemItr.o:$(SRCDIR)/CCfgPalitagemItr.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CCfgPalitagemItr.cpp -o $*.o
lib/CCfgPalitagem.o:$(SRCDIR)/CCfgPalitagem.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CCfgPalitagem.cpp -o $*.o
lib/CProcedenciaItr.o:$(SRCDIR)/CProcedenciaItr.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CProcedenciaItr.cpp -o $*.o
lib/CProcedenciaPC.o:$(SRCDIR)/CProcedenciaPC.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CProcedenciaPC.cpp -o $*.o
lib/CSistemaOrigemItr.o:$(SRCDIR)/CSistemaOrigemItr.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CSistemaOrigemItr.cpp -o $*.o
lib/CSistemaOrigemPC.o:$(SRCDIR)/CSistemaOrigemPC.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CSistemaOrigemPC.cpp -o $*.o
lib/CAdmCamposDependPC.o:$(SRCDIR)/CAdmCamposDependPC.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CAdmCamposDependPC.cpp -o $*.o
lib/CAdmCamposDepend.o:$(SRCDIR)/CAdmCamposDepend.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CAdmCamposDepend.cpp -o $*.o
lib/CFiltroArvContatoPC.o:$(SRCDIR)/CFiltroArvContatoPC.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CFiltroArvContatoPC.cpp -o $*.o
lib/CFiltroArvContato.o:$(SRCDIR)/CFiltroArvContato.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CFiltroArvContato.cpp -o $*.o
lib/CAntItr.o:$(SRCDIR)/CAntItr.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CAntItr.cpp -o $*.o
lib/CSafePointer.o:$(SRCDIR)/CSafePointer.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CSafePointer.cpp -o $*.o
lib/CBxaUfoItr.o:$(SRCDIR)/CBxaUfoItr.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CBxaUfoItr.cpp -o $*.o
lib/CBxaUfo.o:$(SRCDIR)/CBxaUfo.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CBxaUfo.cpp -o $*.o
lib/CBxaFtrItr.o:$(SRCDIR)/CBxaFtrItr.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CBxaFtrItr.cpp -o $*.o
lib/CBxaFtr.o:$(SRCDIR)/CBxaFtr.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CBxaFtr.cpp -o $*.o
lib/CTipPesItr.o:$(SRCDIR)/CTipPesItr.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CTipPesItr.cpp -o $*.o
lib/CCttPesItr.o:$(SRCDIR)/CCttPesItr.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CCttPesItr.cpp -o $*.o
lib/CCttPes.o:$(SRCDIR)/CCttPes.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CCttPes.cpp -o $*.o
lib/CCttCmbItr.o:$(SRCDIR)/CCttCmbItr.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CCttCmbItr.cpp -o $*.o
lib/CCttCmb.o:$(SRCDIR)/CCttCmb.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CCttCmb.cpp -o $*.o
lib/CTapItr.o:$(SRCDIR)/CTapItr.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CTapItr.cpp -o $*.o
lib/CCttFtrItr.o:$(SRCDIR)/CCttFtrItr.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CCttFtrItr.cpp -o $*.o
lib/CAvsItr.o:$(SRCDIR)/CAvsItr.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CAvsItr.cpp -o $*.o
lib/CBxaAntItr.o:$(SRCDIR)/CBxaAntItr.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CBxaAntItr.cpp -o $*.o
lib/CBxaHieItr.o:$(SRCDIR)/CBxaHieItr.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CBxaHieItr.cpp -o $*.o
lib/CBxaItr.o:$(SRCDIR)/CBxaItr.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CBxaItr.cpp -o $*.o
lib/CBxaMsgItr.o:$(SRCDIR)/CBxaMsgItr.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CBxaMsgItr.cpp -o $*.o
lib/CBxaNomItr.o:$(SRCDIR)/CBxaNomItr.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CBxaNomItr.cpp -o $*.o
lib/CCmpClsItr.o:$(SRCDIR)/CCmpClsItr.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CCmpClsItr.cpp -o $*.o
lib/CCmpDomItr.o:$(SRCDIR)/CCmpDomItr.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CCmpDomItr.cpp -o $*.o
lib/CCmpFseItr.o:$(SRCDIR)/CCmpFseItr.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CCmpFseItr.cpp -o $*.o
lib/CCmpItr.o:$(SRCDIR)/CCmpItr.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CCmpItr.cpp -o $*.o
lib/CCttAcaItr.o:$(SRCDIR)/CCttAcaItr.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CCttAcaItr.cpp -o $*.o
lib/CCttFchItr.o:$(SRCDIR)/CCttFchItr.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CCttFchItr.cpp -o $*.o
lib/CCttHieItr.o:$(SRCDIR)/CCttHieItr.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CCttHieItr.cpp -o $*.o
lib/CCttInfItr.o:$(SRCDIR)/CCttInfItr.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CCttInfItr.cpp -o $*.o
lib/CCttItr.o:$(SRCDIR)/CCttItr.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CCttItr.cpp -o $*.o
lib/CCttLinItr.o:$(SRCDIR)/CCttLinItr.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CCttLinItr.cpp -o $*.o
lib/CCttNomItr.o:$(SRCDIR)/CCttNomItr.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CCttNomItr.cpp -o $*.o
lib/CCttRtnItr.o:$(SRCDIR)/CCttRtnItr.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CCttRtnItr.cpp -o $*.o
lib/CCttUfoItr.o:$(SRCDIR)/CCttUfoItr.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CCttUfoItr.cpp -o $*.o
lib/CDomItr.o:$(SRCDIR)/CDomItr.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CDomItr.cpp -o $*.o
lib/CDomTblItr.o:$(SRCDIR)/CDomTblItr.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CDomTblItr.cpp -o $*.o
lib/CFlhAntItr.o:$(SRCDIR)/CFlhAntItr.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CFlhAntItr.cpp -o $*.o
lib/CFlhAvsItr.o:$(SRCDIR)/CFlhAvsItr.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CFlhAvsItr.cpp -o $*.o
lib/CFlhBxaItr.o:$(SRCDIR)/CFlhBxaItr.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CFlhBxaItr.cpp -o $*.o
lib/CFlhCmpItr.o:$(SRCDIR)/CFlhCmpItr.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CFlhCmpItr.cpp -o $*.o
lib/CFlhDprItr.o:$(SRCDIR)/CFlhDprItr.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CFlhDprItr.cpp -o $*.o
lib/CFlhItr.o:$(SRCDIR)/CFlhItr.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CFlhItr.cpp -o $*.o
lib/CFlhMsgItr.o:$(SRCDIR)/CFlhMsgItr.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CFlhMsgItr.cpp -o $*.o
lib/CFlhPrzItr.o:$(SRCDIR)/CFlhPrzItr.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CFlhPrzItr.cpp -o $*.o
lib/CFrdItr.o:$(SRCDIR)/CFrdItr.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CFrdItr.cpp -o $*.o
lib/CFrdMncItr.o:$(SRCDIR)/CFrdMncItr.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CFrdMncItr.cpp -o $*.o
lib/CFrdNomItr.o:$(SRCDIR)/CFrdNomItr.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CFrdNomItr.cpp -o $*.o
lib/CFrdTipItr.o:$(SRCDIR)/CFrdTipItr.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CFrdTipItr.cpp -o $*.o
lib/CFrdUFItr.o:$(SRCDIR)/CFrdUFItr.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CFrdUFItr.cpp -o $*.o
lib/CHrrVraItr.o:$(SRCDIR)/CHrrVraItr.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CHrrVraItr.cpp -o $*.o
lib/CLinItr.o:$(SRCDIR)/CLinItr.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CLinItr.cpp -o $*.o
lib/CMncItr.o:$(SRCDIR)/CMncItr.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CMncItr.cpp -o $*.o
lib/CPcdItr.o:$(SRCDIR)/CPcdItr.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CPcdItr.cpp -o $*.o
lib/CResItr.o:$(SRCDIR)/CResItr.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CResItr.cpp -o $*.o
lib/CPerItr.o:$(SRCDIR)/CPerItr.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CPerItr.cpp -o $*.o
lib/CSatItr.o:$(SRCDIR)/CSatItr.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CSatItr.cpp -o $*.o
lib/CSatPerItr.o:$(SRCDIR)/CSatPerItr.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CSatPerItr.cpp -o $*.o
lib/CSgmItr.o:$(SRCDIR)/CSgmItr.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CSgmItr.cpp -o $*.o
lib/CUFItr.o:$(SRCDIR)/CUFItr.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CUFItr.cpp -o $*.o
lib/CUfoItr.o:$(SRCDIR)/CUfoItr.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CUfoItr.cpp -o $*.o
lib/CTipPes.o:$(SRCDIR)/CTipPes.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CTipPes.cpp -o $*.o
lib/CCttFtr.o:$(SRCDIR)/CCttFtr.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CCttFtr.cpp -o $*.o
lib/CTap.o:$(SRCDIR)/CTap.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CTap.cpp -o $*.o
lib/CAnt.o:$(SRCDIR)/CAnt.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CAnt.cpp -o $*.o
lib/CAvs.o:$(SRCDIR)/CAvs.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CAvs.cpp -o $*.o
lib/CBxa.o:$(SRCDIR)/CBxa.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CBxa.cpp -o $*.o
lib/CBxaAnt.o:$(SRCDIR)/CBxaAnt.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CBxaAnt.cpp -o $*.o
lib/CBxaHie.o:$(SRCDIR)/CBxaHie.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CBxaHie.cpp -o $*.o
lib/CBxaMsg.o:$(SRCDIR)/CBxaMsg.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CBxaMsg.cpp -o $*.o
lib/CBxaNom.o:$(SRCDIR)/CBxaNom.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CBxaNom.cpp -o $*.o
lib/CCmp.o:$(SRCDIR)/CCmp.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CCmp.cpp -o $*.o
lib/CCmpCls.o:$(SRCDIR)/CCmpCls.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CCmpCls.cpp -o $*.o
lib/CCmpDom.o:$(SRCDIR)/CCmpDom.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CCmpDom.cpp -o $*.o
lib/CCmpFse.o:$(SRCDIR)/CCmpFse.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CCmpFse.cpp -o $*.o
lib/CCtt.o:$(SRCDIR)/CCtt.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CCtt.cpp -o $*.o
lib/CCttAca.o:$(SRCDIR)/CCttAca.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CCttAca.cpp -o $*.o
lib/CCttFch.o:$(SRCDIR)/CCttFch.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CCttFch.cpp -o $*.o
lib/CCttHie.o:$(SRCDIR)/CCttHie.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CCttHie.cpp -o $*.o
lib/CCttInf.o:$(SRCDIR)/CCttInf.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CCttInf.cpp -o $*.o
lib/CCttLin.o:$(SRCDIR)/CCttLin.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CCttLin.cpp -o $*.o
lib/CCttNom.o:$(SRCDIR)/CCttNom.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CCttNom.cpp -o $*.o
lib/CCttRtn.o:$(SRCDIR)/CCttRtn.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CCttRtn.cpp -o $*.o
lib/CCttUfo.o:$(SRCDIR)/CCttUfo.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CCttUfo.cpp -o $*.o
lib/CDom.o:$(SRCDIR)/CDom.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CDom.cpp -o $*.o
lib/CDomTbl.o:$(SRCDIR)/CDomTbl.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CDomTbl.cpp -o $*.o
lib/CFlh.o:$(SRCDIR)/CFlh.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CFlh.cpp -o $*.o
lib/CFlhAnt.o:$(SRCDIR)/CFlhAnt.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CFlhAnt.cpp -o $*.o
lib/CFlhAvs.o:$(SRCDIR)/CFlhAvs.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CFlhAvs.cpp -o $*.o
lib/CFlhBxa.o:$(SRCDIR)/CFlhBxa.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CFlhBxa.cpp -o $*.o
lib/CFlhCmp.o:$(SRCDIR)/CFlhCmp.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CFlhCmp.cpp -o $*.o
lib/CFlhDpr.o:$(SRCDIR)/CFlhDpr.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CFlhDpr.cpp -o $*.o
lib/CFlhMsg.o:$(SRCDIR)/CFlhMsg.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CFlhMsg.cpp -o $*.o
lib/CFlhPrz.o:$(SRCDIR)/CFlhPrz.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CFlhPrz.cpp -o $*.o
lib/CFrd.o:$(SRCDIR)/CFrd.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CFrd.cpp -o $*.o
lib/CFrdMnc.o:$(SRCDIR)/CFrdMnc.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CFrdMnc.cpp -o $*.o
lib/CFrdNom.o:$(SRCDIR)/CFrdNom.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CFrdNom.cpp -o $*.o
lib/CFrdTip.o:$(SRCDIR)/CFrdTip.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CFrdTip.cpp -o $*.o
lib/CFrdUF.o:$(SRCDIR)/CFrdUF.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CFrdUF.cpp -o $*.o
lib/CHrrVra.o:$(SRCDIR)/CHrrVra.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CHrrVra.cpp -o $*.o
lib/CLin.o:$(SRCDIR)/CLin.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CLin.cpp -o $*.o
lib/CMnc.o:$(SRCDIR)/CMnc.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CMnc.cpp -o $*.o
lib/CPcd.o:$(SRCDIR)/CPcd.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CPcd.cpp -o $*.o
lib/CRes.o:$(SRCDIR)/CRes.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CRes.cpp -o $*.o
lib/CPer.o:$(SRCDIR)/CPer.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CPer.cpp -o $*.o
lib/CSat.o:$(SRCDIR)/CSat.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CSat.cpp -o $*.o
lib/CSatPer.o:$(SRCDIR)/CSatPer.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CSatPer.cpp -o $*.o
lib/CSgm.o:$(SRCDIR)/CSgm.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CSgm.cpp -o $*.o
lib/CUF.o:$(SRCDIR)/CUF.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CUF.cpp -o $*.o
lib/CUfo.o:$(SRCDIR)/CUfo.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CUfo.cpp -o $*.o
lib/CAtvItr.o:$(SRCDIR)/CAtvItr.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CAtvItr.cpp -o $*.o
lib/CAtv.o:$(SRCDIR)/CAtv.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CAtv.cpp -o $*.o
lib/CCrgItr.o:$(SRCDIR)/CCrgItr.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CCrgItr.cpp -o $*.o
lib/CCrg.o:$(SRCDIR)/CCrg.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CCrg.cpp -o $*.o
lib/CPssItr.o:$(SRCDIR)/CPssItr.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CPssItr.cpp -o $*.o
lib/CPss.o:$(SRCDIR)/CPss.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CPss.cpp -o $*.o
lib/CTOItr.o:$(SRCDIR)/CTOItr.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CTOItr.cpp -o $*.o
lib/CTO.o:$(SRCDIR)/CTO.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CTO.cpp -o $*.o
lib/CUndItr.o:$(SRCDIR)/CUndItr.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CUndItr.cpp -o $*.o
lib/CUnd.o:$(SRCDIR)/CUnd.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CUnd.cpp -o $*.o
lib/CCAItr.o:$(SRCDIR)/CCAItr.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CCAItr.cpp -o $*.o
lib/CCA.o:$(SRCDIR)/CCA.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CCA.cpp -o $*.o
lib/CNCItr.o:$(SRCDIR)/CNCItr.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CNCItr.cpp -o $*.o
lib/CNC.o:$(SRCDIR)/CNC.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CNC.cpp -o $*.o
lib/CHUPItr.o:$(SRCDIR)/CHUPItr.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CHUPItr.cpp -o $*.o
lib/CHUP.o:$(SRCDIR)/CHUP.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CHUP.cpp -o $*.o
lib/COUItr.o:$(SRCDIR)/COUItr.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/COUItr.cpp -o $*.o
lib/COU.o:$(SRCDIR)/COU.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/COU.cpp -o $*.o
lib/COrgItr.o:$(SRCDIR)/COrgItr.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/COrgItr.cpp -o $*.o
lib/COrg.o:$(SRCDIR)/COrg.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/COrg.cpp -o $*.o
lib/COHItr.o:$(SRCDIR)/COHItr.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/COHItr.cpp -o $*.o
lib/COH.o:$(SRCDIR)/COH.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/COH.cpp -o $*.o
lib/CNvlItr.o:$(SRCDIR)/CNvlItr.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CNvlItr.cpp -o $*.o
lib/CNvl.o:$(SRCDIR)/CNvl.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CNvl.cpp -o $*.o
lib/CNHItr.o:$(SRCDIR)/CNHItr.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CNHItr.cpp -o $*.o
lib/CNH.o:$(SRCDIR)/CNH.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CNH.cpp -o $*.o
lib/CNivelSequenciaItr.o:$(SRCDIR)/CNivelSequenciaItr.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CNivelSequenciaItr.cpp -o $*.o
lib/CContatoTipoRetornoRelacoesItr.o:$(SRCDIR)/CContatoTipoRetornoRelacoesItr.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CContatoTipoRetornoRelacoesItr.cpp -o $*.o
lib/CContatoTipoRetornoItr.o:$(SRCDIR)/CContatoTipoRetornoItr.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CContatoTipoRetornoItr.cpp -o $*.o
lib/CPesquisaSatisfacaoTPPessoaItr.o:$(SRCDIR)/CPesquisaSatisfacaoTPPessoaItr.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CPesquisaSatisfacaoTPPessoaItr.cpp -o $*.o
lib/CContatoTipoRelacionamentoItr.o:$(SRCDIR)/CContatoTipoRelacionamentoItr.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CContatoTipoRelacionamentoItr.cpp -o $*.o
lib/CContatoTipoCarteiraItr.o:$(SRCDIR)/CContatoTipoCarteiraItr.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CContatoTipoCarteiraItr.cpp -o $*.o
lib/CContatoSegmentacaoItr.o:$(SRCDIR)/CContatoSegmentacaoItr.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CContatoSegmentacaoItr.cpp -o $*.o
lib/CContatoPesquisaSatisfacaoItr.o:$(SRCDIR)/CContatoPesquisaSatisfacaoItr.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CContatoPesquisaSatisfacaoItr.cpp -o $*.o
lib/CTipoRetornoSequenciaItr.o:$(SRCDIR)/CTipoRetornoSequenciaItr.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CTipoRetornoSequenciaItr.cpp -o $*.o
lib/CContatoGrupoItr.o:$(SRCDIR)/CContatoGrupoItr.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CContatoGrupoItr.cpp -o $*.o
lib/CContatoGrupoRCItr.o:$(SRCDIR)/CContatoGrupoRCItr.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CContatoGrupoRCItr.cpp -o $*.o
lib/CContatoPesquisaSatisfacao.o:$(SRCDIR)/CContatoPesquisaSatisfacao.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CContatoPesquisaSatisfacao.cpp -o $*.o
lib/CContatoSegmentacao.o:$(SRCDIR)/CContatoSegmentacao.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CContatoSegmentacao.cpp -o $*.o
lib/CContatoTipoCarteira.o:$(SRCDIR)/CContatoTipoCarteira.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CContatoTipoCarteira.cpp -o $*.o
lib/CContatoTipoRelacionamento.o:$(SRCDIR)/CContatoTipoRelacionamento.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CContatoTipoRelacionamento.cpp -o $*.o
lib/CContatoTipoRetorno.o:$(SRCDIR)/CContatoTipoRetorno.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CContatoTipoRetorno.cpp -o $*.o
lib/CContatoTipoRetornoRelacoes.o:$(SRCDIR)/CContatoTipoRetornoRelacoes.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CContatoTipoRetornoRelacoes.cpp -o $*.o
lib/CNivelSequencia.o:$(SRCDIR)/CNivelSequencia.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CNivelSequencia.cpp -o $*.o
lib/CPesquisaSatisfacaoTPPessoa.o:$(SRCDIR)/CPesquisaSatisfacaoTPPessoa.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CPesquisaSatisfacaoTPPessoa.cpp -o $*.o
lib/CTipoRetornoSequencia.o:$(SRCDIR)/CTipoRetornoSequencia.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CTipoRetornoSequencia.cpp -o $*.o
lib/CContatoGrupo.o:$(SRCDIR)/CContatoGrupo.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CContatoGrupo.cpp -o $*.o
lib/CContatoGrupoRC.o:$(SRCDIR)/CContatoGrupoRC.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CContatoGrupoRC.cpp -o $*.o
lib/CSequenciaItr.o:$(SRCDIR)/CSequenciaItr.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CSequenciaItr.cpp -o $*.o
lib/CSequencia.o:$(SRCDIR)/CSequencia.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CSequencia.cpp -o $*.o
lib/CFluxoFaseGrupo.o:$(SRCDIR)/CFluxoFaseGrupo.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CFluxoFaseGrupo.cpp -o $*.o
lib/CFluxoFaseGrupoItr.o:$(SRCDIR)/CFluxoFaseGrupoItr.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CFluxoFaseGrupoItr.cpp -o $*.o
lib/CCtpaItr.o:$(SRCDIR)/CCtpaItr.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CCtpaItr.cpp -o $*.o
lib/CCtpa.o:$(SRCDIR)/CCtpa.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CCtpa.cpp -o $*.o
lib/CTabConsRelac.o:$(SRCDIR)/CTabConsRelac.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CTabConsRelac.cpp -o $*.o
lib/CTabConsRelacItr.o:$(SRCDIR)/CTabConsRelacItr.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CTabConsRelacItr.cpp -o $*.o

#
# Link

$(LIBDIR)/lib$(SUBDIR).so: $(OBJ)
	$(SACCPP) $(PLIBPATH) $(DESLIBDIR) $(LINK) $(LIBDIR)/lib$(SUBDIR).so $(OBJ) -lxml2

install:
	if [ -f $(TUXDIR_DES)/lib/lib$(SUBDIR).so ]; then rm -f $(TUXDIR_DES)/lib/lib$(SUBDIR).so; fi

	cp -p $(LIBDIR)/lib$(SUBDIR).so $(TUXDIR_DES)/lib

	if ! [ -d $(TUXDIR_DES)/include/$(SUBDIR) ]; then mkdir $(TUXDIR_DES)/include/$(SUBDIR); fi

	if [ -f  $(INCDIR)/*.h* ]; then cp -f $(INCDIR)/*.h* $(TUXDIR_DES)/include/$(SUBDIR); fi

	if [ -f $(MSGDIR)/*.h* ]; then cp -f $(MSGDIR)/*.h* $(TUXDIR_DES)/messages; fi

	if [ -f $(MSGDIR)/*.fml ]; then cp -f $(MSGDIR)/*.fml $(TUXDIR_DES)/messages; fi

	if [ -f $(MSGDIR)/*.xsd ]; then cp -f $(MSGDIR)/*.xsd $(TUXDIR_DES)/messages; fi

clean:

	rm -f $(LIBDIR)/lib$(SUBDIR).so*

	rm -f $(OBJ)	

	cd src; rm -f $(PCPP:.pcpp=.cpp); rm -f $(PCPP:.pcpp=.lis); cd ..


install_h:
	if ! [ -d $(TUXDIR_DES)/include/$(SUBDIR) ]; then mkdir $(TUXDIR_DES)/include/$(SUBDIR); fi

	if [ -f  $(INCDIR)/*.h* ]; then cp -f $(INCDIR)/*.h* $(TUXDIR_DES)/include/$(SUBDIR); fi

