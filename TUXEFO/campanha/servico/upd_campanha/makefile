#
# $Id: makefile,v 1.1 2009/07/31 15:34:02 a5110702 Exp $
#
.SILENT:

include ../../defines
include $(HOME)/TUXEFO/modulos.env

DIR=servico
SUBDIR=upd_campanha
SRCDIR= $(BASEPATH)/$(DIR)/$(SUBDIR)/src
LIBDIR= $(BASEPATH)/$(DIR)/$(SUBDIR)/lib
INCDIR=$(BASEPATH)/$(DIR)/$(SUBDIR)/include
MSGDIR=$(BASEPATH)/$(DIR)/$(SUBDIR)/messages
MODULODIR=$(TUXDIR_DES)/$(CAMPANHA_PTH)
MODULOLIB=$(TUXDIR_DES)/$(CAMPANHA_PTH)/lib

#
#  Fontes
#  PCPP=lista de fontes a ser precompilado pelo PRO*C
#  CPP=lista de fontes a ser compilado
#  OBJ=lista de obj´s a ser linkado para gerar o servicex
#
PCPP= upd_campanha.pcpp
CPP= libupd_campanha.cpp
OBJ= lib/libupd_campanha.o lib/upd_campanha.o
CUSTSO=orautil cpputil cmputil

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
$(SRCDIR)/libupd_campanha.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/upd_campanha.cpp:
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
# if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR);
lib/libupd_campanha.o:$(SRCDIR)/libupd_campanha.cpp makefile
	if ! [ -d $(MODULODIR) ]; then mkdir $(MODULODIR); fi
	if ! [ -d $(MODULOLIB) ]; then mkdir $(MODULOLIB); fi
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/libupd_campanha.cpp -o $*.o  
lib/upd_campanha.o:$(SRCDIR)/upd_campanha.cpp  makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/upd_campanha.cpp -o $*.o 
#
# Link
#
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
