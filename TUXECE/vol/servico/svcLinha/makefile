include ../../defines



DIR=servico

SUBDIR=svcLinha

SRCDIR=$(BASEPATH)/$(DIR)/$(SUBDIR)/src

LIBDIR=$(BASEPATH)/$(DIR)/$(SUBDIR)/lib

INCDIR=$(BASEPATH)/$(DIR)/$(SUBDIR)/include/$(SUBDIR)

MSGDIR=$(BASEPATH)/$(DIR)/$(SUBDIR)/messages





#CPP=lista de fontes a ser compilado

SRC=$(CPP)



#OBJ=lista de obj´s a ser linkado para gerar o servicex

OBJ=lib/svcLinha.o



all: $(LIBDIR)/lib$(SUBDIR).so



# Compile

lib/svcLinha.o:$(SRCDIR)/svcLinha.cpp makefile

	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi
	$(SACCPP) -Iinclude -Imessages $(PINCLUDE) $(DESINCDIRS) $(CFLAGS) $(SRCDIR)/svcLinha.cpp -o $*.o



# Link

$(LIBDIR)/lib$(SUBDIR).so: $(OBJ)

	$(SACCPP) $(PLIBPATH) $(DESLIBDIR) $(LINK) $(LIBDIR)/lib$(SUBDIR).so $(OBJ) -lxml2



install:
	if [ -f $(TUXDIR_DES)/lib/lib$(SUBDIR).so ]; then rm -f $(TUXDIR_DES)/lib/lib$(SUBDIR).so; fi

	cp -p $(LIBDIR)/lib$(SUBDIR).so $(TUXDIR_DES)/lib



clean:

	rm -f $(LIBDIR)/lib$(SUBDIR).so*

	rm -f $(OBJ)

