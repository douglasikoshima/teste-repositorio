#
# $Id: makefile,v 1.1.2.1 2007/07/23 20:23:11 akurak Exp $
#
.SILENT:

#include ../../defines

BASEPATH=$(HOME)/TUXEWS/vol
#CFLAGS=-cLINK=-G -o
CFLAGS=-cLINK=-G
TUXINCDIR=$(TUXDIR)/include
PROC=proc include=$(TUXINCDIR) code=cpp cpp_suffix=cpp parse=none
DESINCDIRS=-I$(TUXDIR_DES)/include
DESLIBDIRS=-L$(TUXDIR_DES)/lib
MKFLDHDR=mkfldhdr32

#minhas inclusoes
XERCES_CLIB=-L$(XERCESPATH)/xerces-c

FWLIB=-ltuxfw

DIR=servico
SUBDIR=SMSENVIA
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
PCPP=
CPP=SMSENVIA.cpp
OBJ=lib/SMSENVIA.o
CUSTSO=SMS

all: $(SUBDIR)

INCAUX= `echo $(CUSTSO) | awk '{ flds=split($$0,v," "); \
	for(i=1;i<=flds;i++) \
		printf("$(DESINCDIRS)/%s ",v[i]); \
	next}'`
COMPFLAGS= -Iinclude $(PINCLUDE) $(DESINCDIRS)/$(SUBDIR) $(INCAUX) $(CFLAGS)

#
#  FML
#  messages/msg_tpl.fml.h:messages/msg_tpl.fml
#	cd messages; MKFLDHDR messages/msg_tpl.fml; cd ..
#

#
#  Pre Compile
#  $(SRCDIR)/cpp_tpl.cpp:
#	 $(PROC) $*.pcpp
#
     
#$(SRCDIR)/BXALISTID.cpp:
#	$(PROC) $*.pcpp

#
#  Compile
#  lib/obj_tpl.o:$(SRCDIR)/cpp.cpp makefile
#        $(SACCPP) -Iinclude $(PINCLUDE) -I$(DESINCDIRS) $(CFLAGS) $(SRCDIR)/a.cpp -o $*.o
#
lib/SMSENVIA.o:$(SRCDIR)/SMSENVIA.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi
	$(SACCPP) -I$(BASEPATH)/negocio/SMS/include $(COMPFLAGS) $(SRCDIR)/SMSENVIA.cpp -o $*.o  
	echo $(SRCDIR)/SMSENVIA.o gerado com sucesso


#
# Link
#
$(SUBDIR): $(OBJ)
	$(SACCPP) $(PLIBPATH) $(DESLIBDIRS) \
		$(LINK) $(DESLIBDIRS) -G -o $(LIBDIR)/lib$(SUBDIR).so $(OBJ) $(FWLIB) $(XERCES_CLIB)

install:
	cp $(LIBDIR)/lib$(SUBDIR).so $(TUXDIR_DES)/lib
	@-mkdir $(TUXDIR_DES)/include/$(SUBDIR)  2> /dev/null
	@-cp -f $(INCDIR)/*.h* $(TUXDIR_DES)/include/$(SUBDIR) 2> /dev/null

clean:
	rm -f $(LIBDIR)/lib$(SUBDIR).so*
	rm -f $(OBJ)	
	cd src; rm -f $(PCPP:.pcpp=.cpp); cd ..
	cd src; rm -f $(PCPP:.pcpp=.lis); cd ..

