#
# $Id: makefile,v 1.1 2009/07/31 15:34:15 a5110702 Exp $
#
.SILENT:

include ../../defines
include $(HOME)/TUXEFO/modulos.env
MODULODIR=$(TUXDIR_DES)/$(CAMPANHA_PTH)
MODULOLIB=$(TUXDIR_DES)/$(CAMPANHA_PTH)/lib

DIR=commons
SUBDIR=orautil
SRCDIR=$(BASEPATH)/$(DIR)/$(SUBDIR)/src
LIBDIR=$(BASEPATH)/$(DIR)/$(SUBDIR)/lib
INCDIR=$(BASEPATH)/$(DIR)/$(SUBDIR)/include
MSGDIR=$(BASEPATH)/$(DIR)/$(SUBDIR)/messages

#PCPP=lista de fontes a ser precompilado pelo PRO*C
PCPP= orautil.pcpp
#CPP=lista de fontes a ser compilado
CPP=
SRC=$(CPP) $(PCPP:.pcpp=.cpp)
#OBJ=lista de obj´s a ser linkado para gerar o servicex
OBJ= lib/orautil.o 
CUSTSO= cpputil

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
$(SRCDIR)/orautil.cpp:
	$(PROC) $*.pcpp

INCAUX= `echo $(CUSTSO) | awk '{ flds=split($$0,v," ");    \
                         for(i=1;i<=flds;i++)              \
                          printf("$(DESINCDIRS)/%s ",v[i]);\
                         next}'`
COMPFLAGS= -Iinclude $(PINCLUDE) $(DESINCDIRS)/$(SUBDIR) $(INCAUX) $(CFLAGS)


#
# Compile
# ex: lib/a.o:$(SRCDIR)/a.cpp $(INCDIR)/a.hpp makefile
#        $(SACCPP) -Iinclude $(PINCLUDE) -I$(DESINCDIRS) $(CFLAGS) $(SRCDIR)/a.cpp -o $*.o
#
lib/orautil.o:$(SRCDIR)/orautil.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/orautil.cpp -o $*.o  

# Link
$(SUBDIR): $(OBJ)
	$(SACCPP) $(PLIBPATH) $(DESLIBDIRS) \
		$(LINK) -G -o $(LIBDIR)/lib$(SUBDIR).so $(OBJ) $(FWLIB) $(XERCES_CLIB)
	if ! [ -d $(MODULODIR) ]; then mkdir $(MODULODIR); fi
	if ! [ -d $(MODULOLIB) ]; then mkdir $(MODULOLIB); fi
	rm -f $(TUXDIR_DES)/$(CAMPANHA_PTH)/lib/lib$(SO).so
	strip $(LIBDIR)/lib$(SUBDIR).so
	cp $(LIBDIR)/lib$(SUBDIR).so $(TUXDIR_DES)/$(CAMPANHA_PTH)/lib


install:
	if ! [ -d $(MODULODIR) ]; then mkdir $(MODULODIR); fi
	if ! [ -d $(MODULOLIB) ]; then mkdir $(MODULOLIB); fi
	rm -f $(TUXDIR_DES)/$(CAMPANHA_PTH)/lib/lib$(SO).so
	cp $(LIBDIR)/lib$(SUBDIR).so $(TUXDIR_DES)/$(CAMPANHA_PTH)/lib

insbin:
#	cp -f $(LIBDIR)/lib$(SUBDIR).so $(SVRPATH)/lib

clean:
	rm -f $(LIBDIR)/$(SUBDIR).so*
	rm -f $(OBJ)	
	cd src; rm -f $(PCPP:.pcpp=.cpp); cd ..
	cd src; rm -f $(PCPP:.pcpp=.lis); cd ..

config:
	@-mkdir lib
