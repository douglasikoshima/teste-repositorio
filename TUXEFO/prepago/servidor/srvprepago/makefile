# 
# $Id: makefile,v 1.1 2009/07/31 15:35:18 a5110702 Exp $
#
.SILENT:

include ../../defines

EXTDEP=ppbasico PPPESPESSOA PPVALLIN PPLISPESSOA PPVALCNAE PPPESGRUPO GRAVALEGADO PPPESPESADC
TUXSERVICES=PPPESPESSOA,PPVALLIN,PPLISPESSOA,PPVALCNAE,PPPESGRUPO,GRAVALEGADO,PPPESPESADC

DIR=servidor
SERVER=srvprepago
SRCDIR=$(BASEPATH)/$(DIR)/$(SERVER)/src
LIBDIR=$(BASEPATH)/$(DIR)/$(SERVER)/lib
CFGDIR=$(BASEPATH)/$(DIR)/$(SERVER)/config
INCDIR=$(BASEPATH)/$(DIR)/$(SERVER)/include/$(SERVER)
MSGDIR=$(BASEPATH)/$(DIR)/$(SUBDIR)/messages

ORALIBLINK=-lclntsh -lld -lm -lm -lc_r -lpthreads
ORAXACMD=-r Oracle_XA

CSOLNK=`echo $(EXTDEP)|awk '{flds=split($$0,v," ");for(i=1;i<=flds;i++)printf("-l-l%s ",v[i]);next}'`

PCPP=
CPP=
SRC=$(CPP) $(PCPP:.pcpp=.cpp)
OBJ=

all: $(SERVER)

#FML
messages/d.fml.h:messages/d.fml
	cd messages; MKFLDHDR messages/d.fml; cd ..

# Pre Compile
$(SRCDIR)/a.cpp:$(SRCDIR)/a.pcpp $(INCDIR)/a.hpp makefile
	$(PROC) $*.pcpp
$(SRCDIR)/b.cpp:$(SRCDIR)/b.pcpp $(INCDIR)/b.hpp $(INCDIR)/a.hpp makefile
	$(PROC) $*.pcpp

# Compile
lib/a.o:$(SRCDIR)/a.cpp $(INCDIR)/a.hpp makefile
	$(SACCPP) -Iinclude $(PINCLUDE) $(DESINCDIRS) $(CFLAGS) $(SRCDIR)/a.cpp -o $*.o
lib/b.o:$(SRCDIR)/b.cpp $(INCDIR)/b.hpp $(INCDIR)/a.hpp makefile
	$(SACCPP) -Iinclude $(PINCLUDE) $(DESINCDIRS) $(CFLAGS) $(SRCDIR)/a.cpp -o $*.o

# Link
$(SERVER): 
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi
	$(TUXDIR)/bin/buildserver -o $(LIBDIR)/$(SERVER)   \
           -f " $(OBJ) " -f " $(ORALIBLINK) -brtl"         \
           $(CSOLNK)                                       \
           -f " $(DESLIBDIRS) $(PLIBPATH) -L$(ORALIBDIR) "   \
           -f " $(FWLIB) $(XERCES_CLIB) " $(ORAXACMD)      \
           -s $(TUXSERVICES)
	@echo "$(EXTDEP)" > $(LIBDIR)/$(SERVER).txt 


install:
	if ! [ -d $(TUXDIR_PP) ]; then mkdir $(TUXDIR_PP); fi
	if ! [ -d $(TUXDIR_PPBIN) ]; then mkdir $(TUXDIR_PPBIN); fi
	if ! [ -d $(TUXDIR_PPCFG) ]; then mkdir $(TUXDIR_PPCFG); fi
	if ! [ -d $(TUXDIR_PPCFG)/env ]; then mkdir $(TUXDIR_PPCFG)/env; fi
	if ! [ -d $(TUXDIR_PPCFG)/env/app ]; then mkdir $(TUXDIR_PPCFG)/env/app; fi
	if ! [ -d $(TUXDIR_PPCFG)/xml ]; then mkdir $(TUXDIR_PPCFG)/xml; fi
	if ! [ -d $(TUXDIR_PPCFG)/xml/app ]; then mkdir $(TUXDIR_PPCFG)/xml/app; fi
	cp $(LIBDIR)/$(SERVER) $(TUXDIR_PPBIN)
	if [ -f $(CFGDIR)/$(SERVER).env ]; then cp -f $(CFGDIR)/$(SERVER).env $(TUXDIR_PPCFG)/env/app 2>/dev/null; fi
	if [ -f $(CFGDIR)/$(SERVER).xml ]; then cp -f $(CFGDIR)/$(SERVER).xml $(TUXDIR_PPCFG)/xml/app 2>/dev/null; fi

clean:
	rm -f $(LIBDIR)/$(SERVER)
	@-rm -f $(OBJ) 2>/dev/null
