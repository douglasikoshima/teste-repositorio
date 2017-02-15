#
# $Id: makefile,v 1.1 2009/07/31 15:33:47 a5110702 Exp $
#
.SILENT:

include ../../defines
include $(HOME)/TUXEFO/modulos.env
MODULODIR=$(TUXDIR_DES)/$(FIDELIZACAO_PTH)
MODULOLIB=$(TUXDIR_DES)/$(FIDELIZACAO_PTH)/lib

DIR=servico
SUBDIR=exc_aparelho
SRCDIR= $(BASEPATH)/$(DIR)/$(SUBDIR)/src
LIBDIR= $(BASEPATH)/$(DIR)/$(SUBDIR)/lib
INCDIR=$(BASEPATH)/$(DIR)/$(SUBDIR)/include
MSGDIR=$(BASEPATH)/$(DIR)/$(SUBDIR)/messages

#
#  Fontes
#  PCPP=lista de fontes a ser precompilado pelo PRO*C
#  CPP=lista de fontes a ser compilado
#  OBJ=lista de obj´s a ser linkado para gerar o servicex
#
PCPP= exc_aparelho.pcpp
CPP= libexc_aparelho.cpp 
OBJ= lib/libexc_aparelho.o lib/exc_aparelho.o 
CUSTSO=orautil cpputil fidutil

all: $(SUBDIR)

#
#  FML
#  messages/d.fml.h:messages/d.fml
#	cd messages; MKFLDHDR messages/d.fml; cd ..
#

#
#  Pre Compile
#  $(SRCDIR)/a.cpp:$(SRCDIR)/a.pcpp $(INCDIR)/a.hpp makefile
#	 $(PROC) $*.pcpp
#     
$(SRCDIR)/libexc_aparelho.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/exc_aparelho.cpp:
	$(PROC) $*.pcpp

INCAUX= `echo $(CUSTSO) | awk '{ flds=split($$0,v," ");    \
                         for(i=1;i<=flds;i++)              \
                          printf("$(DESINCDIRS)/%s ",v[i]);\
                         next}'`
COMPFLAGS= -Iinclude $(PINCLUDE) $(DESINCDIRS)/$(SUBDIR) $(INCAUX) $(CFLAGS)

#
#  Compile
#  lib/a.o:$(SRCDIR)/a.cpp $(INCDIR)/a.hpp makefile
#        $(SACCPP) -Iinclude $(PINCLUDE) -I$(DESINCDIRS) $(CFLAGS) $(SRCDIR)/a.cpp -o $*.o
#
lib/libexc_aparelho.o:$(SRCDIR)/libexc_aparelho.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/libexc_aparelho.cpp -o $*.o  
lib/exc_aparelho.o:$(SRCDIR)/exc_aparelho.cpp  makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/exc_aparelho.cpp -o $*.o 

#
# Link
#
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
	rm -f $(OBJ)	
	cd src; rm -f $(PCPP:.pcpp=.cpp); cd ..
	cd src; rm -f $(PCPP:.pcpp=.lis); cd ..