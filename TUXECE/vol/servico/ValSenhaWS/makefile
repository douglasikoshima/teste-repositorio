include ../../defines

DIR=servico
SUBDIR=ValSenhaWS
SRCDIR=$(BASEPATH)/$(DIR)/$(SUBDIR)/src
LIBDIR=$(BASEPATH)/$(DIR)/$(SUBDIR)/lib
INCDIR=$(BASEPATH)/$(DIR)/$(SUBDIR)/include/$(SUBDIR)
MSGDIR=$(BASEPATH)/$(DIR)/$(SUBDIR)/messages


#CPP=lista de fontes a ser compilado
SRC=$(CPP)

#OBJ=lista de obj´s a ser linkado para gerar o servicex
OBJ=lib/ValSenhaWS.o

all: $(LIBDIR)/lib$(SUBDIR)Tuxece.so


# Compile
lib/ValSenhaWS.o:$(SRCDIR)/ValSenhaWS.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi
	$(SACCPP) -Iinclude -Imessages $(PINCLUDE) $(DESINCDIRS) $(CFLAGS) $(SRCDIR)/ValSenhaWS.cpp -o $*.o

# Link
$(LIBDIR)/lib$(SUBDIR)Tuxece.so: $(OBJ)
	$(SACCPP) $(PLIBPATH) $(DESLIBDIR) $(LINK) $(LIBDIR)/lib$(SUBDIR)Tuxece.so $(OBJ) -lxml2

install:
	if [ -f $(TUXDIR_DES)/lib/lib$(SUBDIR)Tuxece.so ]; then rm -f $(TUXDIR_DES)/lib/lib$(SUBDIR)Tuxece.so; fi
	cp -p $(LIBDIR)/lib$(SUBDIR)Tuxece.so $(TUXDIR_DES)/lib

clean:
	rm -f $(LIBDIR)/lib$(SUBDIR)Tuxece.so*
	rm -f $(OBJ)
