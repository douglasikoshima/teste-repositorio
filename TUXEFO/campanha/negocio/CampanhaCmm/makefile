#
# $Id: makefile,v 1.1 2009/07/31 15:33:28 a5110702 Exp $
#
.SILENT:

include ../../defines
include $(HOME)/TUXEFO/modulos.env
MODULODIR=$(TUXDIR_DES)/$(CAMPANHA_PTH)
MODULOLIB=$(TUXDIR_DES)/$(CAMPANHA_PTH)/lib

DIR=negocio
SUBDIR=CampanhaCmm
SRCDIR= $(BASEPATH)/$(DIR)/$(SUBDIR)/src
LIBDIR= $(BASEPATH)/$(DIR)/$(SUBDIR)/lib
INCDIR=$(BASEPATH)/$(DIR)/$(SUBDIR)/include
MSGDIR=$(BASEPATH)/$(DIR)/$(SUBDIR)/messages

#PCPP=lista de fontes a ser precompilado pelo PRO*C
PCPP= CCampanha.pcpp                  CSubCampanhaFixa.pcpp                \
      CTipoStatusCampanha.pcpp        CMotivoCampanha.pcpp                 \
      CSubCampanhaHistorico.pcpp      CTipoSubMotivoCampanha.pcpp          \
      CPergunta.pcpp                  CTipoApresentacaoPergunta.pcpp       \
      CCampanhaQuestionario.pcpp      CResposta.pcpp                       \
      CTipoMotivoCampanha.pcpp        CRspPxm.pcpp                         \
      CRelatorioOperadorOra.pcpp      CRelatorioRespostasOra.pcpp          \
      CRelatorioMotivosOra.pcpp       CRelatorioGerenciamentoOra.pcpp      \
      CRelatorioEfetividadeOra.pcpp   CRelatorioAgendaAniversarioOra.pcpp  \
      CCanalCampanha.pcpp             CListaCanalCampanha.pcpp             \
      CSubCampanhaGrupoUsuario.pcpp   CRelArqRespOra.pcpp                  \
      CRelArqResultOra.pcpp           CGetHeaderOra.pcpp

#     CRelatorioOperadorOra.cpp        CRelatorioRespostasOra.cpp           \
#     CRelatorioMotivosOra.cpp         CRelatorioGerenciamentoOra.cpp      CRelatorioEfetividadeOra.cpp \

#CPP=lista de fontes a ser compilado
CPP= CCampanhaItr.cpp                 CSubCampanhaFixaItr.cpp              \
     CTipoStatusCampanhaItr.cpp       CMotivoCampanhaItr.cpp               \
     CSubCampanhaHistoricoItr.cpp     CTipoSubMotivoCampanhaItr.cpp        \
     CPerguntaItr.cpp                 CTipoApresentacaoPerguntaItr.cpp     \
     CCampanhaQuestionarioItr.cpp     CRespostaItr.cpp                     \
     CTipoMotivoCampanhaItr.cpp       CRspPxmItr.cpp                       \
     CRelatorioMotivos.cpp            CRelatorioEfetividade.cpp            \
     CRelatorioOperador.cpp           CRelatorioRespostas.cpp              \
     CRelatorioGerenciamento.cpp      CRelatorioCampanha.cpp               \
     CRelatorioFactory.cpp            CRelatorioAgendaAniversario.cpp      \
     CCanalCampanha.cpp               CCanalCampanhaItr.cpp                \
     CListaCanalCampanha.cpp          CListaCanalCampanhaItr.cpp           \
     CSubCampanhaGrupoUsuario.cpp     CSubCampanhaGrupoUsuarioItr.cpp      \
     CRelArqResp.cpp                  CRelArqResult.cpp                    \
     CGetHeader.cpp                                                        \
     main.cpp

SRC=$(CPP) $(PCPP:.pcpp=.cpp)
#OBJ=lista de obj´s a ser linkado para gerar o servicex
OBJ=  lib/CCampanha.o                 lib/CSubCampanhaFixa.o               \
      lib/CTipoStatusCampanha.o       lib/CMotivoCampanha.o                \
      lib/CSubCampanhaHistorico.o     lib/CTipoSubMotivoCampanha.o         \
      lib/CPergunta.o                 lib/CTipoApresentacaoPergunta.o      \
      lib/CCampanhaQuestionario.o     lib/CResposta.o                      \
      lib/CTipoMotivoCampanha.o       lib/CRspPxm.o                        \
      lib/CCampanhaItr.o              lib/CSubCampanhaFixaItr.o            \
      lib/CTipoStatusCampanhaItr.o    lib/CMotivoCampanhaItr.o             \
      lib/CSubCampanhaHistoricoItr.o  lib/CTipoSubMotivoCampanhaItr.o      \
      lib/CPerguntaItr.o              lib/CTipoApresentacaoPerguntaItr.o   \
      lib/CCampanhaQuestionarioItr.o  lib/CRespostaItr.o                   \
      lib/CTipoMotivoCampanhaItr.o    lib/CRspPxmItr.o                     \
      lib/CRelatorioMotivos.o         lib/CRelatorioEfetividade.o          \
      lib/CRelatorioOperador.o        lib/CRelatorioRespostas.o            \
      lib/CRelatorioGerenciamento.o   lib/CRelatorioEfetividadeOra.o       \
      lib/CRelatorioOperadorOra.o     lib/CRelatorioRespostasOra.o         \
      lib/CRelatorioMotivosOra.o      lib/CRelatorioGerenciamentoOra.o     \
      lib/CRelArqRespOra.o            lib/CRelArqResp.o                    \
      lib/CRelArqResultOra.o          lib/CRelArqResult.o                  \
      lib/CRelatorioCampanha.o        lib/CRelatorioFactory.o              \
      lib/CRelatorioAgendaAniversario.o                                    \
      lib/CRelatorioAgendaAniversarioOra.o                                 \
      lib/CCanalCampanhaItr.o                                              \
      lib/CCanalCampanha.o                                                 \
      lib/CListaCanalCampanhaItr.o                                         \
      lib/CListaCanalCampanha.o                                            \
      lib/CSubCampanhaGrupoUsuario.o                                       \
      lib/CSubCampanhaGrupoUsuarioItr.o                                    \
      lib/CGetHeaderOra.o                                                  \
      lib/CGetHeader.o                                                     \
      lib/main.o

all: config $(SUBDIR)

#FML
messages/d.fml.h:messages/d.fml
	cd messages; MKFLDHDR messages/d.fml; cd ..

#
# Pre Compile
#ex: $(SRCDIR)/a.cpp:$(SRCDIR)/a.pcpp $(INCDIR)/a.hpp makefile
#	 $(PROC) $*.pcpp
#
#$(SRCDIR)/libsel_excecao.cpp:$(SRCDIR)/sel_excecao.pcpp   makefile

$(SRCDIR)/CSubCampanhaGrupoUsuario.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CSubCampanhaGrupoUsuarioItr.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CListaCanalCampanhaItr.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CListaCanalCampanha.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CCanalCampanha.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CCanalCampanhaItr.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CCampanha.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CRespostaItr.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CTipoMotivoCampanha.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CCampanhaItr.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CSubCampanhaFixa.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CTipoMotivoCampanhaItr.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CMotivoCampanha.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CSubCampanhaFixaItr.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CTipoStatusCampanha.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CMotivoCampanhaItr.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CSubCampanhaHistorico.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CTipoStatusCampanhaItr.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CPergunta.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CSubCampanhaHistoricoItr.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CTipoSubMotivoCampanha.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CPerguntaItr.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CCampanhaQuestionario.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CCampanhaQuestionarioItr.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CTipoApresentacaoPergunta.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CTipoSubMotivoCampanhaItr.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CResposta.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CTipoApresentacaoPerguntaItr.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CRspPxm.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CRspPxmItr.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CRelatorioOperadorOra.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CRelatorioRespostasOra.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CRelatorioMotivosOra.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CGetHeaderOra.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CRelArqRespOra.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CGetHeader.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CRelArqResultOra.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CRelArqResp.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CRelArqResult.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CRelatorioGerenciamentoOra.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CRelatorioEfetividadeOra.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/main.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CRelatorioMotivos.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CRelatorioEfetividade.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CRelatorioOperador.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CRelatorioRespostas.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CRelatorioGerenciamento.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CRelatorioCampanha.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CRelatorioFactory.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CRelatorioAgendaAniversarioOra.cpp:
	$(PROC) $*.pcpp


INCAUX= `echo $(CUSTSO) | awk '{ flds=split($$0,v," ");    \
                         for(i=1;i<=flds;i++)              \
                          printf("$(DESINCDIRS)/%s ",v[i]);\
                         next}'`
COMPFLAGS= -Iinclude $(PINCLUDE) $(DESINCDIRS)/$(SUBDIR) $(INCAUX) $(CFLAGS)


#
# Compile
# ex: lib/a.o:$(SRCDIR)/a.cpp $(INCDIR)/a.hpp makefile
#        $(SACCPP) -Iinclude $(PINCLUDE) -I$(DESINCDIRS) -I$(TUXDIR_DES)/include $(CFLAGS) $(SRCDIR)/a.cpp -o $*.o
#

lib/CSubCampanhaGrupoUsuario.o:$(SRCDIR)/CSubCampanhaGrupoUsuario.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CSubCampanhaGrupoUsuario.cpp -o $*.o
lib/CSubCampanhaGrupoUsuarioItr.o:$(SRCDIR)/CSubCampanhaGrupoUsuarioItr.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CSubCampanhaGrupoUsuarioItr.cpp -o $*.o
lib/CCampanha.o:$(SRCDIR)/CCampanha.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CCampanha.cpp -o $*.o
lib/CListaCanalCampanha.o:$(SRCDIR)/CListaCanalCampanha.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CListaCanalCampanha.cpp -o $*.o
lib/CListaCanalCampanhaItr.o:$(SRCDIR)/CListaCanalCampanhaItr.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CListaCanalCampanhaItr.cpp -o $*.o
lib/CCanalCampanha.o:$(SRCDIR)/CCanalCampanha.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CCanalCampanha.cpp -o $*.o
lib/CCanalCampanhaItr.o:$(SRCDIR)/CCanalCampanhaItr.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CCanalCampanhaItr.cpp -o $*.o
lib/CRespostaItr.o:$(SRCDIR)/CRespostaItr.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CRespostaItr.cpp -o $*.o
lib/CTipoMotivoCampanha.o:$(SRCDIR)/CTipoMotivoCampanha.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CTipoMotivoCampanha.cpp -o $*.o
lib/CCampanhaItr.o:$(SRCDIR)/CCampanhaItr.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CCampanhaItr.cpp -o $*.o
lib/CSubCampanhaFixa.o:$(SRCDIR)/CSubCampanhaFixa.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CSubCampanhaFixa.cpp -o $*.o
lib/CTipoMotivoCampanhaItr.o:$(SRCDIR)/CTipoMotivoCampanhaItr.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CTipoMotivoCampanhaItr.cpp -o $*.o
lib/CMotivoCampanha.o:$(SRCDIR)/CMotivoCampanha.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CMotivoCampanha.cpp -o $*.o
lib/CSubCampanhaFixaItr.o:$(SRCDIR)/CSubCampanhaFixaItr.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CSubCampanhaFixaItr.cpp -o $*.o
lib/CTipoStatusCampanha.o:$(SRCDIR)/CTipoStatusCampanha.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CTipoStatusCampanha.cpp -o $*.o
lib/CMotivoCampanhaItr.o:$(SRCDIR)/CMotivoCampanhaItr.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CMotivoCampanhaItr.cpp -o $*.o
lib/CSubCampanhaHistorico.o:$(SRCDIR)/CSubCampanhaHistorico.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CSubCampanhaHistorico.cpp -o $*.o
lib/CTipoStatusCampanhaItr.o:$(SRCDIR)/CTipoStatusCampanhaItr.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CTipoStatusCampanhaItr.cpp -o $*.o
lib/CPergunta.o:$(SRCDIR)/CPergunta.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CPergunta.cpp -o $*.o
lib/CSubCampanhaHistoricoItr.o:$(SRCDIR)/CSubCampanhaHistoricoItr.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CSubCampanhaHistoricoItr.cpp -o $*.o
lib/CTipoSubMotivoCampanha.o:$(SRCDIR)/CTipoSubMotivoCampanha.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CTipoSubMotivoCampanha.cpp -o $*.o
lib/CCampanhaQuestionario.o:$(SRCDIR)/CCampanhaQuestionario.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CCampanhaQuestionario.cpp -o $*.o
lib/CPerguntaItr.o:$(SRCDIR)/CPerguntaItr.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CPerguntaItr.cpp -o $*.o
lib/CCampanhaQuestionarioItr.o:$(SRCDIR)/CCampanhaQuestionarioItr.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CCampanhaQuestionarioItr.cpp -o $*.o
lib/CTipoApresentacaoPergunta.o:$(SRCDIR)/CTipoApresentacaoPergunta.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CTipoApresentacaoPergunta.cpp -o $*.o
lib/CTipoSubMotivoCampanhaItr.o:$(SRCDIR)/CTipoSubMotivoCampanhaItr.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CTipoSubMotivoCampanhaItr.cpp -o $*.o
lib/CResposta.o:$(SRCDIR)/CResposta.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CResposta.cpp -o $*.o
lib/CTipoApresentacaoPerguntaItr.o:$(SRCDIR)/CTipoApresentacaoPerguntaItr.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CTipoApresentacaoPerguntaItr.cpp -o $*.o
lib/CRspPxm.o:$(SRCDIR)/CRspPxm.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CRspPxm.cpp -o $*.o
lib/CRspPxmItr.o:$(SRCDIR)/CRspPxmItr.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CRspPxmItr.cpp -o $*.o
lib/CRelatorioOperadorOra.o:$(SRCDIR)/CRelatorioOperadorOra.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CRelatorioOperadorOra.cpp -o $*.o
lib/CRelatorioRespostasOra.o:$(SRCDIR)/CRelatorioRespostasOra.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CRelatorioRespostasOra.cpp -o $*.o
lib/CRelArqResult.o:$(SRCDIR)/CRelArqResult.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CRelArqResult.cpp -o $*.o
lib/CRelArqResp.o:$(SRCDIR)/CRelArqResp.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CRelArqResp.cpp -o $*.o
lib/CRelArqResultOra.o:$(SRCDIR)/CRelArqResultOra.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CRelArqResultOra.cpp -o $*.o
lib/CRelArqRespOra.o:$(SRCDIR)/CRelArqRespOra.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CRelArqRespOra.cpp -o $*.o
lib/CRelatorioMotivosOra.o:$(SRCDIR)/CRelatorioMotivosOra.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CRelatorioMotivosOra.cpp -o $*.o
lib/CRelatorioGerenciamentoOra.o:$(SRCDIR)/CRelatorioGerenciamentoOra.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CRelatorioGerenciamentoOra.cpp -o $*.o
lib/CRelatorioEfetividadeOra.o:$(SRCDIR)/CRelatorioEfetividadeOra.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CRelatorioEfetividadeOra.cpp -o $*.o
lib/main.o:$(SRCDIR)/main.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/main.cpp -o $*.o
lib/CRelatorioMotivos.o:$(SRCDIR)/CRelatorioMotivos.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CRelatorioMotivos.cpp -o $*.o
lib/CRelatorioEfetividade.o:$(SRCDIR)/CRelatorioEfetividade.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CRelatorioEfetividade.cpp -o $*.o
lib/CRelatorioOperador.o:$(SRCDIR)/CRelatorioOperador.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CRelatorioOperador.cpp -o $*.o
lib/CRelatorioRespostas.o:$(SRCDIR)/CRelatorioRespostas.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CRelatorioRespostas.cpp -o $*.o
lib/CRelatorioGerenciamento.o:$(SRCDIR)/CRelatorioGerenciamento.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CRelatorioGerenciamento.cpp -o $*.o
lib/CRelatorioCampanha.o:$(SRCDIR)/CRelatorioCampanha.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CRelatorioCampanha.cpp -o $*.o
lib/CRelatorioAgendaAniversario.o:$(SRCDIR)/CRelatorioAgendaAniversario.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CRelatorioAgendaAniversario.cpp -o $*.o
lib/CRelatorioAgendaAniversarioOra.o:$(SRCDIR)/CRelatorioAgendaAniversarioOra.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CRelatorioAgendaAniversarioOra.cpp -o $*.o
lib/CRelatorioFactory.o:$(SRCDIR)/CRelatorioFactory.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CRelatorioFactory.cpp -o $*.o
lib/CGetHeaderOra.o:$(SRCDIR)/CGetHeaderOra.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CGetHeaderOra.cpp -o $*.o
lib/CGetHeader.o:$(SRCDIR)/CGetHeader.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CGetHeader.cpp -o $*.o

# Link
$(SUBDIR): $(OBJ)
	$(SACCPP) $(PLIBPATH) $(DESLIBDIRS) \
		$(LINK) -G -o $(LIBDIR)/lib$(SUBDIR).so $(OBJ) $(FWLIB) $(XERCES_CLIB)
	if ! [ -d $(MODULODIR) ]; then mkdir $(MODULODIR); fi
	if ! [ -d $(MODULOLIB) ]; then mkdir $(MODULOLIB); fi
	cp $(LIBDIR)/lib$(SUBDIR).so $(TUXDIR_DES)/$(CAMPANHA_PTH)/lib

install:
	if ! [ -d $(MODULODIR) ]; then mkdir $(MODULODIR); fi
	if ! [ -d $(MODULOLIB) ]; then mkdir $(MODULOLIB); fi
	cp $(LIBDIR)/lib$(SUBDIR).so $(TUXDIR_DES)/$(CAMPANHA_PTH)/lib

insbin:
#	cp -f $(LIBDIR)/lib$(SUBDIR).so $(SVRPATH)/lib

clean:
	rm -f $(LIBDIR)/lib$(SUBDIR).so*
	rm -f $(LIBDIR)/lib$(SUBDIR).o*
	rm -f $(OBJ)
	cd src; rm -f $(PCPP:.pcpp=.cpp); cd ..
	cd src; rm -f $(PCPP:.pcpp=.lis); cd ..

config:
	@-mkdir lib
