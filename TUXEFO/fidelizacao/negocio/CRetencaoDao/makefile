#
# $Id: makefile,v 1.1 2009/07/31 15:34:56 a5110702 Exp $
#
.SILENT:

include ../../defines
include $(HOME)/TUXEFO/modulos.env
MODULODIR=$(TUXDIR_DES)/$(FIDELIZACAO_PTH)
MODULOLIB=$(TUXDIR_DES)/$(FIDELIZACAO_PTH)/lib

DIR=negocio
SUBDIR=CRetencaoDao
SRCDIR= $(BASEPATH)/$(DIR)/$(SUBDIR)/src
LIBDIR= $(BASEPATH)/$(DIR)/$(SUBDIR)/lib
INCDIR=$(BASEPATH)/$(DIR)/$(SUBDIR)/include
MSGDIR=$(BASEPATH)/$(DIR)/$(SUBDIR)/messages

#PCPP=lista de fontes a ser precompilado pelo PRO*C
PCPP= CRetencao.pcpp			CRetencaoLinha.pcpp		\
	CCaractOfAceita.pcpp		CRetencaoAparelho.pcpp		\
	CRetencaoPlano.pcpp		CRetencaoBonus.pcpp		\
	CRetencaoMigracao.pcpp		CRetencaoPontos.pcpp		\
	CRetencaoAnalise.pcpp		CRetencaoSuspensao.pcpp		\
	COfertaRealizada.pcpp		CRetencaoConsolidada.pcpp	\
	CApoioRet.pcpp


#CPP=lista de fontes a ser compilado
CPP=	CRetencao.cpp			\
	CRetencaoLinha.cpp		\
	CCaractOfAceita.cpp		\
	CRetencaoAparelho.cpp		\
	CRetencaoPlano.cpp		\
	CRetencaoBonus.cpp		\
	CRetencaoMigracao.cpp		\
	CRetencaoPontos.cpp		\
	CRetencaoAnalise.cpp		\
	CRetencaoSuspensao.cpp		\
	COfertaRealizada.cpp		\
	CRetencaoConsolidada.cpp	\
	CApoioRet.cpp			\


 

SRC=$(CPP) $(PCPP:.pcpp=.cpp)
#OBJ=lista de obj´s a ser linkado para gerar o servicex
OBJ=  lib/CRetencao.o			lib/CRetencaoLinha.o		\
	lib/CRetencaoConsolidada.o	lib/CCaractOfAceita.o		\
	lib/CRetencaoAparelho.o		lib/CRetencaoPlano.o		\
	lib/CRetencaoBonus.o		lib/CRetencaoMigracao.o		\
	lib/CRetencaoPontos.o		lib/CRetencaoAnalise.o		\
	lib/CRetencaoSuspensao.o	lib/COfertaRealizada.o		\
	lib/CApoioRet.o


CUSTSO=cpputil orautil fidutil
 
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

$(SRCDIR)/CRetencao.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CRetencaoLinha.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CRetencaoConsolidada.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CCaractOfAceita.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CRetencaoAparelho.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CRetencaoPlano.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CRetencaoBonus.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CRetencaoMigracao.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CRetencaoPontos.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CRetencaoAnalise.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CRetencaoSuspensao.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/COfertaRealizada.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CApoioRet.cpp:
	$(PROC) $*.pcpp


INCAUX= `echo $(CUSTSO) | awk '{ flds=split($$0,v," ");    \
			 for(i=1;i<=flds;i++)		  \
			  printf("$(DESINCDIRS)/%s ",v[i]);\
			 next}'`
COMPFLAGS= -Iinclude $(PINCLUDE) $(DESINCDIRS)/$(SUBDIR) $(INCAUX) $(CFLAGS)


#
# Compile
# ex: lib/a.o:$(SRCDIR)/a.cpp $(INCDIR)/a.hpp makefile
#	  $(SACCPP) -Iinclude $(PINCLUDE) -I$(DESINCDIRS) -I$(TUXDIR_DES)/include $(CFLAGS) $(SRCDIR)/a.cpp -o $*.o
#

lib/CRetencao.o:$(SRCDIR)/CRetencao.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CRetencao.cpp -o $*.o
lib/CRetencaoLinha.o:$(SRCDIR)/CRetencaoLinha.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CRetencaoLinha.cpp -o $*.o
lib/CRetencaoConsolidada.o:$(SRCDIR)/CRetencaoConsolidada.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CRetencaoConsolidada.cpp -o $*.o
lib/CCaractOfAceita.o:$(SRCDIR)/CCaractOfAceita.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CCaractOfAceita.cpp -o $*.o
lib/CRetencaoAparelho.o:$(SRCDIR)/CRetencaoAparelho.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CRetencaoAparelho.cpp -o $*.o
lib/CRetencaoPlano.o:$(SRCDIR)/CRetencaoPlano.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CRetencaoPlano.cpp -o $*.o
lib/CRetencaoBonus.o:$(SRCDIR)/CRetencaoBonus.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CRetencaoBonus.cpp -o $*.o
lib/CRetencaoMigracao.o:$(SRCDIR)/CRetencaoMigracao.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CRetencaoMigracao.cpp -o $*.o
lib/CRetencaoPontos.o:$(SRCDIR)/CRetencaoPontos.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CRetencaoPontos.cpp -o $*.o
lib/CRetencaoAnalise.o:$(SRCDIR)/CRetencaoAnalise.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CRetencaoAnalise.cpp -o $*.o
lib/CRetencaoSuspensao.o:$(SRCDIR)/CRetencaoSuspensao.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CRetencaoSuspensao.cpp -o $*.o
lib/COfertaRealizada.o:$(SRCDIR)/COfertaRealizada.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/COfertaRealizada.cpp -o $*.o
lib/CApoioRet.o:$(SRCDIR)/CApoioRet.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CApoioRet.cpp -o $*.o


# Link
$(SUBDIR): $(OBJ)
	$(SACCPP) $(PLIBPATH) $(DESLIBDIRS) \
		$(LINK) -G -o $(LIBDIR)/lib$(SUBDIR).so $(OBJ) $(FWLIB) $(XERCES_CLIB)
	strip $(LIBDIR)/lib$(SUBDIR).so
	if ! [ -d $(MODULODIR) ]; then mkdir $(MODULODIR); fi
	if ! [ -d $(MODULOLIB) ]; then mkdir $(MODULOLIB); fi
	cp $(LIBDIR)/lib$(SUBDIR).so $(TUXDIR_DES)/$(FIDELIZACAO_PTH)/lib

install:
	if ! [ -d $(MODULODIR) ]; then mkdir $(MODULODIR); fi
	if ! [ -d $(MODULOLIB) ]; then mkdir $(MODULOLIB); fi
	cp $(LIBDIR)/lib$(SUBDIR).so $(TUXDIR_DES)/$(FIDELIZACAO_PTH)/lib

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
