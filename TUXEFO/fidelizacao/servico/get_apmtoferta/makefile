#
# $Id: makefile,v 1.1 2009/07/31 15:34:55 a5110702 Exp $
#
.SILENT:

include ../../defines
include $(HOME)/TUXEFO/modulos.env
MODULODIR=$(TUXDIR_DES)/$(FIDELIZACAO_PTH)
MODULOLIB=$(TUXDIR_DES)/$(FIDELIZACAO_PTH)/lib

DIR=servico
SUBDIR=get_apmtoferta
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
PCPP= get_destint.pcpp sel_seg.pcpp sel_tpcli.pcpp sel_ufop.pcpp
CPP= libget_apmtoferta.cpp 
OBJ= lib/libget_apmtoferta.o  \
     lib/get_destint.o       \
     lib/sel_seg.o           \
     lib/sel_tpcli.o        
CUSTSO=orautil cpputil fidutil get_regional

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
$(SRCDIR)/get_destint.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/sel_seg.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/sel_tpcli.cpp:
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
lib/libget_apmtoferta.o:$(SRCDIR)/libget_apmtoferta.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/libget_apmtoferta.cpp -o $*.o  
lib/get_destint.o:$(SRCDIR)/get_destint.cpp  makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/get_destint.cpp -o $*.o 
lib/sel_seg.o:$(SRCDIR)/sel_seg.cpp  makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/sel_seg.cpp -o $*.o 
lib/sel_tpcli.o:$(SRCDIR)/sel_tpcli.cpp  makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/sel_tpcli.cpp -o $*.o 
lib/sel_ufop.o:$(SRCDIR)/sel_ufop.cpp  makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/sel_ufop.cpp -o $*.o 

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