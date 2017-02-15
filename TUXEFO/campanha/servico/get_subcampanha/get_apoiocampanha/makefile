#
# $Id: makefile,v 1.1 2009/07/31 15:33:22 a5110702 Exp $
#
.SILENT:

include ../../defines
include $(HOME)/TUXEFO/modulos.env

DIR=servico
SUBDIR=get_apoiocampanha
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
PCPP= get_campanha.pcpp get_motivocampanha.pcpp get_submotivocampanha.pcpp get_canal.pcpp get_statuscampanha.pcpp get_tipocampanha.pcpp get_grupo.pcpp
CPP= libapoio_campanha.cpp
OBJ= lib/libapoio_campanha.o lib/get_campanha.o lib/get_motivocampanha.o lib/get_submotivocampanha.o lib/get_canal.o lib/get_statuscampanha.o lib/get_tipocampanha.o lib/get_grupo.o
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
$(SRCDIR)/libapoio_campanha.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/get_campanha.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/get_motivocampanha.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/get_submotivocampanha.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/get_canal.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/get_statuscampanha.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/get_tipocampanha.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/get_grupo.cpp:
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
lib/libapoio_campanha.o:$(SRCDIR)/libapoio_campanha.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/libapoio_campanha.cpp -o $*.o  
lib/get_campanha.o:$(SRCDIR)/get_campanha.cpp  makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/get_campanha.cpp -o $*.o 
lib/get_motivocampanha.o:$(SRCDIR)/get_motivocampanha.cpp  makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/get_motivocampanha.cpp -o $*.o 
lib/get_submotivocampanha.o:$(SRCDIR)/get_submotivocampanha.cpp  makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/get_submotivocampanha.cpp -o $*.o 
lib/get_canal.o:$(SRCDIR)/get_canal.cpp  makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/get_canal.cpp -o $*.o 
lib/get_statuscampanha.o:$(SRCDIR)/get_statuscampanha.cpp  makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/get_statuscampanha.cpp -o $*.o 
lib/get_tipocampanha.o:$(SRCDIR)/get_tipocampanha.cpp  makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/get_tipocampanha.cpp -o $*.o 
lib/get_grupo.o:$(SRCDIR)/get_grupo.cpp  makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/get_grupo.cpp -o $*.o 
#
# Link
#
$(SUBDIR): $(OBJ)
	$(SACCPP) $(PLIBPATH) $(DESLIBDIRS) \
		$(LINK) -G -o $(LIBDIR)/lib$(SUBDIR).so $(OBJ) $(FWLIB) $(XERCES_CLIB)


install:
	cp $(LIBDIR)/lib$(SUBDIR).so $(TUXDIR_DES)/lib
	@-mkdir $(TUXDIR_DES)/include/$(SUBDIR)  2> /dev/null
	@-cp -f $(INCDIR)/*.h* $(TUXDIR_DES)/include/$(SUBDIR) 2> /dev/null

insbin:
	cp -f $(LIBDIR)/lib$(SUBDIR).so $(SVRPATH)/lib

clean:
	rm -f $(LIBDIR)/lib$(SUBDIR).so*
	rm -f $(LIBDIR)/lib$(SUBDIR).o*
	rm -f $(OBJ)	
	cd src; rm -f $(PCPP:.pcpp=.cpp); cd ..
	cd src; rm -f $(PCPP:.pcpp=.lis); cd ..
