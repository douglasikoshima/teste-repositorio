.SILENT:

APPNAME=cargaAnatel

BATCH_DIR=$(HOME)/BATCH

COMPILE_DIR=$(BATCH_DIR)/$(APPNAME)
COMPILE_SRC=$(COMPILE_DIR)/src
COMPILE_BIN=$(COMPILE_DIR)/bin
COMPILE_CFG=$(COMPILE_DIR)/cfg
COMPILE_INC=$(COMPILE_DIR)/include
XERCES_INC=$(XERCESCPATH)/include

COMPILE_COMMONS_COLE=$(HOME)/BATCH/commons/Collection/src
COMPILE_COMMONS_PROP=$(HOME)/BATCH/commons/Propriedade/src
COMPILE_COMMONS_LOG=$(HOME)/BATCH/commons/Log/src
COMPILE_COMMONS_FILE=$(HOME)/BATCH/commons/CFile/src

DEPLOY_DIR=$(HOME)/deploy
PROCESSOS_DIR=$(DEPLOY_DIR)/processos
DATA_DIR=$(DEPLOY_DIR)/

EXEC_DIR=$(PROCESSOS_DIR)/$(APPNAME)
EXEC_BIN=$(EXEC_DIR)/bin
EXEC_CFG=$(EXEC_DIR)/bin
EXEC_LOG=$(EXEC_DIR)/log
EXEC_DATA=$(EXEC_DIR)/data


CFLAGS= -L$(ORALIBDIR) -lclntsh -lld -lm -lm -lc_r -lpthreads
export CFLAGS

all:  build

build:$(APPNAME)

clean:
	echo limpando binarios
	rm -r -f $(COMPILE_SRC)/*.o $(COMPILE_SRC)/*.lis $(COMPILE_SRC)/AtendimentoAnatel.cpp $(COMPILE_SRC)/CCtt.cpp $(COMPILE_BIN)/cargaAnatel
	rm -r -f $(COMPILE_BIN)
	echo clean finalizado.


install:
	umask 077
	# cria estrutura inicial
	if [ ! -d $(DEPLOY_DIR) ] ; then mkdir $(DEPLOY_DIR); fi
	if [ ! -d $(PROCESSOS_DIR) ] ; then mkdir $(PROCESSOS_DIR); fi
	# cria diretorios de aplicacao
	if [ ! -d $(EXEC_DIR) ] ; then mkdir $(EXEC_DIR); fi
	if [ ! -d $(EXEC_BIN) ] ; then mkdir $(EXEC_BIN); fi
	if [ ! -d $(EXEC_CFG) ] ; then mkdir $(EXEC_CFG); fi
	if [ ! -d $(EXEC_LOG) ] ; then mkdir $(EXEC_LOG); fi
	if [ ! -d $(EXEC_DATA) ] ; then mkdir $(EXEC_DATA); fi
	# copia cargaAnatel.sh
	cp -pf $(COMPILE_DIR)/cargaAnatel.sh $(EXEC_DIR)
	# copia executavel
	cp -pf $(COMPILE_BIN)/$(APPNAME) $(EXEC_BIN)
	# copia arquivos de configuracao comuns
	cp -pf $(BATCH_DIR)/oracle.`hostname`.`whoami`.cfg $(PROCESSOS_DIR)/oracle.cfg
	# copia arquivo de configuracao da aplicacao
	cp -pf $(COMPILE_CFG)/$(APPNAME).`hostname`.`whoami`.xml $(EXEC_CFG)/$(APPNAME).xml
	

uninstall:
	rm -r -f $(EXEC_DIR)

cargaAnatel: cargaAnatel.o AtendimentoAnatel.cpp AtendimentoAnatel.o Arquivo.o XMLManager.o XMLReader.o tinystr.o tinyxml.o tinyxmlerror.o tinyxmlparser.o Procedimento.o Log.o MFile.o Atendimento.o CCttItr.o CCtt.cpp CCtt.o tuxfwbasicoraexception.o CFile.o Historico.o
	if ! [ -d $(COMPILE_BIN) ]; then mkdir $(COMPILE_BIN); fi 
	echo copiando arquivo de $(COMPILE_CFG)/$(APPNAME).`hostname`.`whoami`.xml
	echo para $(COMPILE_BIN)/$(APPNAME).xml
	cp -pf $(COMPILE_CFG)/$(APPNAME).`hostname`.`whoami`.xml $(COMPILE_BIN)/$(APPNAME).xml
	export CC=xlC;	\
	buildclient -w -f " -g " -f $(COMPILE_SRC)/cargaAnatel.o -f $(COMPILE_SRC)/AtendimentoAnatel.o -f $(COMPILE_SRC)/Arquivo.o -f $(COMPILE_SRC)/XMLManager.o -f $(COMPILE_SRC)/XMLReader.o -f $(COMPILE_SRC)/Procedimento.o -f $(COMPILE_SRC)/tinystr.o -f $(COMPILE_SRC)/tinyxml.o -f $(COMPILE_SRC)/tinyxmlerror.o -f $(COMPILE_SRC)/tinyxmlparser.o -f $(COMPILE_SRC)/MFile.o -f $(COMPILE_SRC)/Log.o -f $(COMPILE_SRC)/Atendimento.o -f $(COMPILE_SRC)/CCttItr.o -f $(COMPILE_SRC)/CCtt.o -f $(COMPILE_SRC)/tuxfwbasicoraexception.o -f $(COMPILE_SRC)/CFile.o -f $(COMPILE_SRC)/Historico.o -f "$(XERCESCPATH)/lib/libxerces-c25.0.a $(PLIBPATH) -lxml2 -ltibems64 -lssl -lcrypto" -o $(COMPILE_BIN)/$(APPNAME)
	
CFile.o: $(COMPILE_COMMONS_FILE)/CFile.cpp makefile
	xlC -c -I$(PINCLUDE) $(COMPILE_COMMONS_FILE)/CFile.cpp -o $(COMPILE_SRC)/CFile.o	
	
Historico.o: $(COMPILE_SRC)/Historico.cpp makefile
	xlC -c -I$(COMPILE_INC) $(COMPILE_SRC)/Historico.cpp -o $(COMPILE_SRC)/Historico.o	
	
tuxfwbasicoraexception.o: $(COMPILE_SRC)/tuxfwbasicoraexception.cpp makefile
	xlC -c -I$(COMPILE_INC) $(COMPILE_SRC)/tuxfwbasicoraexception.cpp -o $(COMPILE_SRC)/tuxfwbasicoraexception.o	

Log.o: $(COMPILE_COMMONS_LOG)/Log.cpp makefile
	xlC -c $(PINCLUDE) $(COMPILE_COMMONS_LOG)/Log.cpp -o $(COMPILE_SRC)/Log.o

MFile.o: $(COMPILE_COMMONS_PROP)/MFile.cpp makefile
	xlC -c $(PINCLUDE) $(COMPILE_COMMONS_PROP)/MFile.cpp -o $(COMPILE_SRC)/MFile.o
	
CCttItr.o: $(COMPILE_SRC)/CCttItr.cpp makefile
	xlC -c -I$(COMPILE_INC) $(COMPILE_SRC)/CCttItr.cpp -o $(COMPILE_SRC)/CCttItr.o
	
Atendimento.o: $(COMPILE_SRC)/Atendimento.cpp makefile
	xlC -c -I$(COMPILE_INC) $(PINCLUDE) $(COMPILE_SRC)/Atendimento.cpp -o $(COMPILE_SRC)/Atendimento.o		

AtendimentoAnatel.o: $(COMPILE_SRC)/AtendimentoAnatel.cpp makefile
	xlC -c -I$(COMPILE_INC) $(PINCLUDE) $(COMPILE_SRC)/AtendimentoAnatel.cpp -o $(COMPILE_SRC)/AtendimentoAnatel.o

AtendimentoAnatel.cpp: $(COMPILE_SRC)/AtendimentoAnatel.pcpp makefile
	proc include=$(TUXDIR)/include  parse=none cpp_suffix=cpp code=cpp $(COMPILE_SRC)/AtendimentoAnatel.pcpp

CCtt.o: $(COMPILE_SRC)/CCtt.cpp makefile
	xlC -c -I$(COMPILE_INC) $(COMPILE_SRC)/CCtt.cpp -o $(COMPILE_SRC)/CCtt.o
	
CCtt.cpp: $(COMPILE_SRC)/CCtt.pcpp makefile
	proc include=$(TUXDIR)/include  parse=none cpp_suffix=cpp code=cpp $(COMPILE_SRC)/CCtt.pcpp	

cargaAnatel.o: $(COMPILE_SRC)/cargaAnatel.cpp makefile
	xlC -c -I$(COMPILE_INC) $(PINCLUDE) $(XERCES_INC) $(COMPILE_SRC)/cargaAnatel.cpp -o $(COMPILE_SRC)/cargaAnatel.o	
	
Arquivo.o: $(COMPILE_SRC)/Arquivo.cpp makefile
	xlC -c -I$(COMPILE_INC) $(PINCLUDE) $(COMPILE_SRC)/Arquivo.cpp -o $(COMPILE_SRC)/Arquivo.o		
	
XMLManager.o: $(COMPILE_SRC)/XMLManager.cpp makefile
	xlC -c -I$(COMPILE_INC) $(PINCLUDE) $(COMPILE_SRC)/XMLManager.cpp -o $(COMPILE_SRC)/XMLManager.o		

XMLReader.o: $(COMPILE_SRC)/XMLReader.cpp makefile
	xlC -c -I$(COMPILE_INC) $(PINCLUDE) $(COMPILE_SRC)/XMLReader.cpp -o $(COMPILE_SRC)/XMLReader.o						

Procedimento.o: $(COMPILE_SRC)/Procedimento.cpp makefile
	xlC -c -I$(COMPILE_INC) $(PINCLUDE) $(COMPILE_SRC)/Procedimento.cpp -o $(COMPILE_SRC)/Procedimento.o		
	
tinystr.o: $(COMPILE_SRC)/tinystr.cpp makefile
	xlC -c -I$(COMPILE_INC) $(PINCLUDE) $(COMPILE_SRC)/tinystr.cpp -o $(COMPILE_SRC)/tinystr.o	
	
tinyxml.o: $(COMPILE_SRC)/tinyxml.cpp makefile
	xlC -c -I$(COMPILE_INC) $(PINCLUDE) $(COMPILE_SRC)/tinyxml.cpp -o $(COMPILE_SRC)/tinyxml.o	
	
tinyxmlerror.o: $(COMPILE_SRC)/tinyxmlerror.cpp makefile
	xlC -c -I$(COMPILE_INC) $(PINCLUDE) $(COMPILE_SRC)/tinyxmlerror.cpp -o $(COMPILE_SRC)/tinyxmlerror.o		
	
tinyxmlparser.o: $(COMPILE_SRC)/tinyxmlparser.cpp makefile
	xlC -c -I$(COMPILE_INC) $(PINCLUDE) $(COMPILE_SRC)/tinyxmlparser.cpp -o $(COMPILE_SRC)/tinyxmlparser.o		
		