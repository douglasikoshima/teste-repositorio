.SILENT:

include ../../defines
include $(HOME)/TUXEFO/modulos.env
MODULODIR=$(TUXDIR_DES)/$(FIDELIZACAO_PTH)
MODULOLIB=$(TUXDIR_DES)/$(FIDELIZACAO_PTH)/lib

DIR=servico
SUBDIR=cad_pp_seg
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
PCPP= cad_pp_seg.pcpp
CPP= libcad_pp_seg.cpp
OBJ= lib/libcad_pp_seg.o lib/cad_pp_seg.o
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
$(SRCDIR)/libcad_pp_seg.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/cad_pp_seg.cpp:
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
lib/libcad_pp_seg.o:$(SRCDIR)/libcad_pp_seg.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/libcad_pp_seg.cpp -o $*.o  
lib/cad_pp_seg.o:$(SRCDIR)/cad_pp_seg.cpp  makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/cad_pp_seg.cpp -o $*.o 

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
