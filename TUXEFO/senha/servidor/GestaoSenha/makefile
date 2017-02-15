#Compile Tuxedo services
#This makefile generate a executable binary files
.SILENT:

#Variable Section
BIN=svrGestaoSenha
CUSTOBJ=
DIRLIB=$(TUXDIR_DES)/Senha/lib
CUSTSO=AlteraSenha AtualizaCadastro ConsultaHistorico DesbloqueiaSenha GeraSenha RegistraContato ReiniciaSenha ValidaSenha ConsultaRamal ConsultaRamalURA
TUXSERVICES=AlteraSenha,ConsHistorico,ReiniciaSenha,GeraSenha,ValidaSenha,ConsRamal,ConsRamalURA

#Fix Section
export CFLAGS=-L$(ORALIBDIR) -L/opt/esac/dvfo01rt/staging/Senha/lib -L/opt/tuxedo/current/lib -L/usr/lib -L/usr/local/lib -L../so -L $(DIRLIB) -L $(TUXDIR_DES)/lib
export CC='xlC -qrtti'
ORAINCDIR=-I$(ORACLE_HOME)/precomp/public
ORALIBLINK=-l-lclntsh -l-lld -l-lm -l-lc_r -l-lpthreads
ORAXACMD=-r Oracle_XA
XERCES_CLIB=-l-lxerces-c
FWLIB=-f "-ltuxfw"
CC=$(SACCPP) $(PINCLUDE) $(PLIBPATH)
BUILDSERV=buildserver -o
CSOLNK=`echo $(CUSTSO)|awk '{flds=split($$0,v," ");for(i=1;i<=flds;i++)printf("-l-l%s ",v[i]);next;}'`

all: $(BIN)
$(BIN):
	echo Building Server...
	if ! [ -d bin ]; then mkdir bin; fi
	$(BUILDSERV) bin/$(BIN) $(ORALIBLINK) -brtl -f " $(CUSTOBJ)" $(CSOLNK) $(FWLIB) $(XERCES_CLIB) $(ORAXACMD) -s $(TUXSERVICES)

install:
	echo Instalando $(BIN)...
	if ! [ -d $(TUXDIR_DES)/Senha/bin ]; then mkdir -p $(TUXDIR_DES)/Senha/bin; fi
	cp bin/$(BIN) $(TUXDIR_DES)/Senha/bin

clean:
	echo Limpando $(BIN)...
	rm -f bin/$(BIN)