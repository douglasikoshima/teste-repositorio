SUBDIRS=CadLojaTerm FILACOMPSVC CadastraInSid MSGCADSITEF SetLstRestrita svcPontoContato \
		svcFiltroCampanha svcListaCampanha svcDetalheCampanha svcGestor svcProtocolos \
		svcCampanhaCrud svcConsultaGestao svcSenhaGRV svcSenha svcEmpresa svcLinha svcGestor svcGestorGRV \
		svcSolicitacoes svcContas svcMenu svcQuadroAviso svcQuadroAvisGRV svcBanner

all:
	for dir in $(SUBDIRS); do \
		cd $$dir; \
		make all; \
		cd ..; \
	done

clean:
	for dir in $(SUBDIRS); do \
		cd $$dir; \
		make clean; \
		cd ..; \
	done

install:
	for dir in $(SUBDIRS); do \
		cd $$dir; \
		make install; \
		cd ..; \
	done 

build:
	for dir in $(SUBDIRS); do \
		cd $$dir; \
		make; \
		make install; \
		cd ..; \
	done
