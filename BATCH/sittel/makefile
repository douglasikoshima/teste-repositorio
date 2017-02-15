
APPNAME=sittel

BATCH_DIR=$(HOME)/BATCH


COMPILE_COMMONS_COLE=$(HOME)/BATCH/commons/Collection/src
COMPILE_COMMONS_PROP=$(HOME)/BATCH/commons/Propriedade/src
COMPILE_COMMONS_LOG=$(HOME)/BATCH/commons/Log/src

COMPILE_DIR=$(BATCH_DIR)/$(APPNAME)
COMPILE_SRC=$(COMPILE_DIR)/src
COMPILE_BIN=$(COMPILE_DIR)/bin
COMPILE_CFG=$(COMPILE_DIR)/cfg
COMPILE_INC=$(COMPILE_DIR)/include
COMPILE_LIB=$(COMPILE_DIR)/lib
COMPILE_XSD=$(COMPILE_DIR)/xsd

DEPLOY_DIR=$(HOME)/deploy
PROCESSOS_DIR=$(DEPLOY_DIR)/processos

EXEC_DIR=$(PROCESSOS_DIR)/$(APPNAME)
EXEC_BIN=$(EXEC_DIR)/bin
EXEC_CFG=$(EXEC_DIR)/bin
EXEC_LOG=$(EXEC_DIR)/log
EXEC_TMP=$(EXEC_DIR)/tmp
EXEC_DAT=$(EXEC_DIR)/data
EXEC_XSD=$(EXEC_DIR)/xsd

CC=xlc -w -g
CXX=xlC -w -g
CFLAGS=-q64
CXXFLAGS=-q64 -qrtti=all -brtl 
LDFLAGS=-L$(BATCH_DIR)/commons/gc/lib -L$(COMPILE_LIB) -L$(ORACLE_HOME)/lib -L$(ORACLE_HOME)/rdbms/lib -L$(BATCH_DIR)/commons/zlib/lib  -L$(BATCH_DIR)/commons/libzip/lib 
CPPFLAGS=-DAIX -D_AIX -I$(BATCH_DIR)/commons/gc/include -I$(COMPILE_INC) -I$(ORACLE_HOME)/rdbms/public -I$(ORACLE_HOME)/xdk/include -I$(BATCH_DIR)/commons/zlib/include -I$(BATCH_DIR)/commons/libzip/include -I$(BATCH_DIR)/commons/libzip/lib/libzip/include
LIBS=-lgc -lgccpp -lxml11 -lcore11 -lnls11 -lunls11 -locci -lz -lzip -lc -lm -lbsd -ldl -lpthread 
KERNEL_BITS=64
OBJECT_MODE=64

all: build

build: $(APPNAME) sittelChld sittelMonitor sittel_ID sittelSaida RequestRequisicaoJudicial.class

clean:
	rm -f $(COMPILE_SRC)/sittel.o $(COMPILE_BIN)/sittel
	rm -r -f $(COMPILE_BIN)

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
	if [ ! -d $(EXEC_TMP) ] ; then mkdir $(EXEC_TMP); fi
	if [ ! -d $(EXEC_DAT) ] ; then mkdir $(EXEC_DAT); fi
	if [ ! -d $(EXEC_XSD) ] ; then mkdir $(EXEC_XSD); fi

	# copia sittel.sh
	cp -pf $(COMPILE_DIR)/sittel.sh $(EXEC_DIR)

	# copia executavel
	cp -pf $(COMPILE_BIN)/$(APPNAME) $(EXEC_BIN)
	cp -pf $(COMPILE_BIN)/sittelChld $(EXEC_BIN)
	cp -pf $(COMPILE_BIN)/sittelMonitor $(EXEC_BIN)
	cp -pf $(COMPILE_BIN)/sittel_ID $(EXEC_BIN)
	cp -pf $(COMPILE_BIN)/sittelSaida $(EXEC_BIN)
	cp -pf $(COMPILE_BIN)/RequestRequisicaoJudicial.class $(EXEC_BIN)

	# copia arquivos de configuracao comuns
	cp -pf $(BATCH_DIR)/oracle.`hostname`.`whoami`.cfg $(PROCESSOS_DIR)/oracle.cfg

	# copia arquivo de configuracao da aplicacao
	cp -pf $(COMPILE_CFG)/$(APPNAME).`hostname`.`whoami`.cfg $(EXEC_CFG)/$(APPNAME).cfg

	# copia arquivos xsd
	cp -pf $(COMPILE_XSD)/*.* $(EXEC_XSD)/

	# seta permissoes
	chmod ug=rwx,o=rx $(EXEC_DIR)/sittel.sh
	chmod ug=rw,o=r $(EXEC_CFG)/$(APPNAME).cfg
	chmod ugo=rwx $(EXEC_LOG) $(EXEC_DAT) $(EXEC_TMP)

uninstall:
	rm -r -f $(EXEC_DIR)

sittel: CSittel.o
	if ! [ -d $(COMPILE_BIN) ]; then mkdir $(COMPILE_BIN); fi 
	$(CXX) $(CXXFLAGS) $(CPPFLAGS) $(LDFLAGS) $(LIBS) \
		$(COMPILE_SRC)/CParametro.o \
		$(COMPILE_SRC)/CFilaProcessum.o \
		$(COMPILE_SRC)/CAlvoProcessum.o \
		$(COMPILE_SRC)/CPessoaDocumento.o \
		$(COMPILE_SRC)/CContaEndereco.o \
		$(COMPILE_SRC)/CPessoaEndereco.o \
		$(COMPILE_SRC)/CPessoaLinha.o \
		$(COMPILE_SRC)/CPessoaLinhaHistorico.o \
		$(COMPILE_SRC)/CLinhaConta.o \
		$(COMPILE_SRC)/CLinhaEndereco.o \
		$(COMPILE_SRC)/CEnderecoLinha.o \
		$(COMPILE_SRC)/CAssinante.o \
		$(COMPILE_SRC)/CAssinanteTerminal.o \
		$(COMPILE_SRC)/CInstalacao.o \
		$(COMPILE_SRC)/CRequisicaoJudicial.o \
		$(COMPILE_SRC)/CXmlImpl.o \
		$(COMPILE_SRC)/CUtil.o \
		$(COMPILE_SRC)/CZip.o \
		$(COMPILE_SRC)/CNet.o \
		$(COMPILE_SRC)/CLock.o \
		$(COMPILE_SRC)/CException.o \
		$(COMPILE_SRC)/CSoapException.o \
		$(COMPILE_SRC)/CLog.o \
		$(COMPILE_SRC)/CConfig.o \
		$(COMPILE_SRC)/CSittel.o \
		$(COMPILE_SRC)/main.cpp -o $(COMPILE_BIN)/$(APPNAME)

CSittel.o: \
CConfig.o \
CLog.o \
CException.o \
CSoapException.o \
CLock.o \
CNet.o \
CZip.o \
CUtil.o \
CXmlImpl.o \
CParametro.o \
CFilaProcessum.o \
CAlvoProcessum.o \
CPessoaDocumento.o \
CContaEndereco.o \
CPessoaEndereco.o \
CPessoaLinha.o \
CPessoaLinhaHistorico.o \
CLinhaConta.o \
CLinhaEndereco.o \
CEnderecoLinha.o \
CAssinante.o \
CAssinanteTerminal.o \
CInstalacao.o \
CRequisicaoJudicial.o \
	$(COMPILE_SRC)/CSittel.cpp
	$(CXX) $(CXXFLAGS) $(CPPFLAGS) $(COMPILE_SRC)/CSittel.cpp -c -o $(COMPILE_SRC)/CSittel.o

CConfig.o: $(COMPILE_SRC)/CConfig.cpp
	$(CXX) $(CXXFLAGS) $(CPPFLAGS) $(COMPILE_SRC)/CConfig.cpp -c -o $(COMPILE_SRC)/CConfig.o

CLog.o: $(COMPILE_SRC)/CLog.cpp
	$(CXX) $(CXXFLAGS) $(CPPFLAGS) $(COMPILE_SRC)/CLog.cpp -c -o $(COMPILE_SRC)/CLog.o

CException.o: $(COMPILE_SRC)/CException.cpp
	$(CXX) $(CXXFLAGS) $(CPPFLAGS) $(COMPILE_SRC)/CException.cpp -c -o $(COMPILE_SRC)/CException.o

CSoapException.o: $(COMPILE_SRC)/CSoapException.cpp
	$(CXX) $(CXXFLAGS) $(CPPFLAGS) $(COMPILE_SRC)/CSoapException.cpp -c -o $(COMPILE_SRC)/CSoapException.o

CLock.o: $(COMPILE_SRC)/CLock.cpp
	$(CXX) $(CXXFLAGS) $(CPPFLAGS) $(COMPILE_SRC)/CLock.cpp -c -o $(COMPILE_SRC)/CLock.o

CNet.o: $(COMPILE_SRC)/CNet.cpp
	$(CXX) $(CXXFLAGS) $(CPPFLAGS) $(COMPILE_SRC)/CNet.cpp -c -o $(COMPILE_SRC)/CNet.o
	
CZip.o: $(COMPILE_SRC)/CZip.cpp
	$(CXX) $(CXXFLAGS) $(CPPFLAGS) $(COMPILE_SRC)/CZip.cpp -c -o $(COMPILE_SRC)/CZip.o

CUtil.o: $(COMPILE_SRC)/CUtil.cpp
	$(CXX) $(CXXFLAGS) $(CPPFLAGS) $(COMPILE_SRC)/CUtil.cpp -c -o $(COMPILE_SRC)/CUtil.o

CXmlImpl.o: $(COMPILE_SRC)/CXmlImpl.cpp
	$(CXX) $(CXXFLAGS) $(CPPFLAGS) $(COMPILE_SRC)/CXmlImpl.cpp -c -o $(COMPILE_SRC)/CXmlImpl.o

CParametro.o: $(COMPILE_SRC)/CParametro.cpp
	$(CXX) $(CXXFLAGS) $(CPPFLAGS) $(COMPILE_SRC)/CParametro.cpp -c -o $(COMPILE_SRC)/CParametro.o

CFilaProcessum.o: $(COMPILE_SRC)/CFilaProcessum.cpp
	$(CXX) $(CXXFLAGS) $(CPPFLAGS) $(COMPILE_SRC)/CFilaProcessum.cpp -c -o $(COMPILE_SRC)/CFilaProcessum.o

CAlvoProcessum.o: $(COMPILE_SRC)/CAlvoProcessum.cpp
	$(CXX) $(CXXFLAGS) $(CPPFLAGS) $(COMPILE_SRC)/CAlvoProcessum.cpp -c -o $(COMPILE_SRC)/CAlvoProcessum.o

CPessoaDocumento.o: $(COMPILE_SRC)/CPessoaDocumento.cpp
	$(CXX) $(CXXFLAGS) $(CPPFLAGS) $(COMPILE_SRC)/CPessoaDocumento.cpp -c -o $(COMPILE_SRC)/CPessoaDocumento.o	

CContaEndereco.o: $(COMPILE_SRC)/CContaEndereco.cpp
	$(CXX) $(CXXFLAGS) $(CPPFLAGS) $(COMPILE_SRC)/CContaEndereco.cpp -c -o $(COMPILE_SRC)/CContaEndereco.o	

CPessoaEndereco.o: $(COMPILE_SRC)/CPessoaEndereco.cpp
	$(CXX) $(CXXFLAGS) $(CPPFLAGS) $(COMPILE_SRC)/CPessoaEndereco.cpp -c -o $(COMPILE_SRC)/CPessoaEndereco.o	

CPessoaLinha.o: $(COMPILE_SRC)/CPessoaLinha.cpp
	$(CXX) $(CXXFLAGS) $(CPPFLAGS) $(COMPILE_SRC)/CPessoaLinha.cpp -c -o $(COMPILE_SRC)/CPessoaLinha.o	

CPessoaLinhaHistorico.o: $(COMPILE_SRC)/CPessoaLinhaHistorico.cpp
	$(CXX) $(CXXFLAGS) $(CPPFLAGS) $(COMPILE_SRC)/CPessoaLinhaHistorico.cpp -c -o $(COMPILE_SRC)/CPessoaLinhaHistorico.o	

CLinhaConta.o: $(COMPILE_SRC)/CLinhaConta.cpp
	$(CXX) $(CXXFLAGS) $(CPPFLAGS) $(COMPILE_SRC)/CLinhaConta.cpp -c -o $(COMPILE_SRC)/CLinhaConta.o

CLinhaEndereco.o: $(COMPILE_SRC)/CLinhaEndereco.cpp
	$(CXX) $(CXXFLAGS) $(CPPFLAGS) $(COMPILE_SRC)/CLinhaEndereco.cpp -c -o $(COMPILE_SRC)/CLinhaEndereco.o

CEnderecoLinha.o: $(COMPILE_SRC)/CEnderecoLinha.cpp
	$(CXX) $(CXXFLAGS) $(CPPFLAGS) $(COMPILE_SRC)/CEnderecoLinha.cpp -c -o $(COMPILE_SRC)/CEnderecoLinha.o

CAssinante.o: $(COMPILE_SRC)/CAssinante.cpp
	$(CXX) $(CXXFLAGS) $(CPPFLAGS) $(COMPILE_SRC)/CAssinante.cpp -c -o $(COMPILE_SRC)/CAssinante.o

CAssinanteTerminal.o: $(COMPILE_SRC)/CAssinanteTerminal.cpp
	$(CXX) $(CXXFLAGS) $(CPPFLAGS) $(COMPILE_SRC)/CAssinanteTerminal.cpp -c -o $(COMPILE_SRC)/CAssinanteTerminal.o

CInstalacao.o: $(COMPILE_SRC)/CInstalacao.cpp
	$(CXX) $(CXXFLAGS) $(CPPFLAGS) $(COMPILE_SRC)/CInstalacao.cpp -c -o $(COMPILE_SRC)/CInstalacao.o

CRequisicaoJudicial.o: $(COMPILE_SRC)/CRequisicaoJudicial.cpp
	$(CXX) $(CXXFLAGS) $(CPPFLAGS) $(COMPILE_SRC)/CRequisicaoJudicial.cpp -c -o $(COMPILE_SRC)/CRequisicaoJudicial.o

sittelChld: sittelChld.cpp sittelChld.o Log.o MFile.o
	if ! [ -d $(COMPILE_BIN) ]; then mkdir $(COMPILE_BIN); fi 
	export CC="xlC -g -L$(ORALIBDIR) -lclntsh -lld -lm -lc_r -lpthreads -lc -lbind -lisode";	\
	buildclient -v -w -f " -g " -f $(COMPILE_SRC)/sittelChld.o -f $(COMPILE_SRC)/MFile.o -f $(COMPILE_SRC)/Log.o -o $(COMPILE_BIN)/sittelChld

sittelMonitor: sittelMonitor.cpp sittelMonitor.o Log.o MFile.o
	if ! [ -d $(COMPILE_BIN) ]; then mkdir $(COMPILE_BIN); fi 
	export CC="xlC -g -L$(ORALIBDIR) -lclntsh -lld -lm -lc_r -lpthreads -lc -lbind -lisode";	\
	buildclient -v -w -f " -g " -f $(COMPILE_SRC)/sittelMonitor.o -f $(COMPILE_SRC)/MFile.o -f $(COMPILE_SRC)/Log.o -o $(COMPILE_BIN)/sittelMonitor

sittel_ID: sittel_ID.cpp sittel_ID.o Log.o MFile.o
	if ! [ -d $(COMPILE_BIN) ]; then mkdir $(COMPILE_BIN); fi 
	export CC="xlC -g -L$(ORALIBDIR) -lclntsh -lld -lm -lc_r -lpthreads -lc -lbind -lisode";	\
	buildclient -v -w -f " -g " -f $(COMPILE_SRC)/sittel_ID.o -f $(COMPILE_SRC)/MFile.o -f $(COMPILE_SRC)/Log.o -o $(COMPILE_BIN)/sittel_ID

sittelSaida: sittelSaida.cpp sittelSaida.o Log.o MFile.o
	if ! [ -d $(COMPILE_BIN) ]; then mkdir $(COMPILE_BIN); fi 
	export CC="xlC -g -L$(ORALIBDIR) -lclntsh -lld -lm -lc_r -lpthreads -lc -lbind -lisode";	\
	buildclient -v -w -f " -g " -f $(COMPILE_SRC)/sittelSaida.o -f $(COMPILE_SRC)/MFile.o -f $(COMPILE_SRC)/Log.o -o $(COMPILE_BIN)/sittelSaida

Log.o: $(COMPILE_COMMONS_LOG)/Log.cpp makefile
	xlC -g -c $(PINCLUDE) $(COMPILE_COMMONS_LOG)/Log.cpp -o $(COMPILE_SRC)/Log.o

MFile.o: $(COMPILE_COMMONS_PROP)/MFile.cpp makefile
	xlC -g -c $(PINCLUDE) $(COMPILE_COMMONS_PROP)/MFile.cpp -o $(COMPILE_SRC)/MFile.o

sittelChld.o: $(COMPILE_SRC)/sittelChld.cpp makefile
	xlC -g -c -I$(COMPILE_INC) $(PINCLUDE) $(COMPILE_SRC)/sittelChld.cpp -o $(COMPILE_SRC)/sittelChld.o

sittelChld.cpp: $(COMPILE_SRC)/sittelChld.pcpp makefile
	proc include=$(TUXDIR)/include  parse=none cpp_suffix=cpp code=cpp $(COMPILE_SRC)/sittelChld.pcpp

sittelMonitor.o: $(COMPILE_SRC)/sittelMonitor.cpp makefile
	xlC -g -c -I$(COMPILE_INC) $(PINCLUDE) $(COMPILE_SRC)/sittelMonitor.cpp -o $(COMPILE_SRC)/sittelMonitor.o

sittelMonitor.cpp: $(COMPILE_SRC)/sittelMonitor.pcpp makefile
	proc include=$(TUXDIR)/include  parse=none cpp_suffix=cpp code=cpp $(COMPILE_SRC)/sittelMonitor.pcpp

sittel_ID.o: $(COMPILE_SRC)/sittel_ID.cpp makefile
	xlC -g -c -I$(COMPILE_INC) $(PINCLUDE) $(COMPILE_SRC)/sittel_ID.cpp -o $(COMPILE_SRC)/sittel_ID.o

sittel_ID.cpp: $(COMPILE_SRC)/sittel_ID.pcpp makefile
	proc include=$(TUXDIR)/include  parse=none cpp_suffix=cpp code=cpp $(COMPILE_SRC)/sittel_ID.pcpp

sittelSaida.o: $(COMPILE_SRC)/sittelSaida.cpp makefile
	xlC -g -c -I$(COMPILE_INC) $(PINCLUDE) $(COMPILE_SRC)/sittelSaida.cpp -o $(COMPILE_SRC)/sittelSaida.o

sittelSaida.cpp: $(COMPILE_SRC)/sittelSaida.pcpp makefile
	proc include=$(TUXDIR)/include  parse=none cpp_suffix=cpp code=cpp $(COMPILE_SRC)/sittelSaida.pcpp

RequestRequisicaoJudicial.class: $(COMPILE_SRC)/RequestRequisicaoJudicial.java makefile
	javac -g $(COMPILE_SRC)/RequestRequisicaoJudicial.java -d $(COMPILE_BIN)
