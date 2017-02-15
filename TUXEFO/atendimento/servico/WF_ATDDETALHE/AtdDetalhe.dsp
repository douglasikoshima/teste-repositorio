# Microsoft Developer Studio Project File - Name="AtdDetalhe" - Package Owner=<4>
# Microsoft Developer Studio Generated Build File, Format Version 6.00
# ** DO NOT EDIT **

# TARGTYPE "Win32 (x86) Console Application" 0x0103

CFG=AtdDetalhe - Win32 Debug
!MESSAGE This is not a valid makefile. To build this project using NMAKE,
!MESSAGE use the Export Makefile command and run
!MESSAGE 
!MESSAGE NMAKE /f "AtdDetalhe.mak".
!MESSAGE 
!MESSAGE You can specify a configuration when running NMAKE
!MESSAGE by defining the macro CFG on the command line. For example:
!MESSAGE 
!MESSAGE NMAKE /f "AtdDetalhe.mak" CFG="AtdDetalhe - Win32 Debug"
!MESSAGE 
!MESSAGE Possible choices for configuration are:
!MESSAGE 
!MESSAGE "AtdDetalhe - Win32 Release" (based on "Win32 (x86) Console Application")
!MESSAGE "AtdDetalhe - Win32 Debug" (based on "Win32 (x86) Console Application")
!MESSAGE 

# Begin Project
# PROP AllowPerConfigDependencies 0
# PROP Scc_ProjName ""
# PROP Scc_LocalPath ""
CPP=cl.exe
RSC=rc.exe

!IF  "$(CFG)" == "AtdDetalhe - Win32 Release"

# PROP BASE Use_MFC 0
# PROP BASE Use_Debug_Libraries 0
# PROP BASE Output_Dir "Release"
# PROP BASE Intermediate_Dir "Release"
# PROP BASE Target_Dir ""
# PROP Use_MFC 0
# PROP Use_Debug_Libraries 0
# PROP Output_Dir "Release"
# PROP Intermediate_Dir "Release"
# PROP Target_Dir ""
# ADD BASE CPP /nologo /W3 /GX /O2 /D "WIN32" /D "NDEBUG" /D "_CONSOLE" /D "_MBCS" /YX /FD /c
# ADD CPP /nologo /W4 /GX /D "WIN32" /D "NDEBUG" /D "_CONSOLE" /D "_MBCS" /YX /FD /c
# ADD BASE RSC /l 0x416 /d "NDEBUG"
# ADD RSC /l 0x416 /d "NDEBUG"
BSC32=bscmake.exe
# ADD BASE BSC32 /nologo
# ADD BSC32 /nologo
LINK32=link.exe
# ADD BASE LINK32 kernel32.lib user32.lib gdi32.lib winspool.lib comdlg32.lib advapi32.lib shell32.lib ole32.lib oleaut32.lib uuid.lib odbc32.lib odbccp32.lib kernel32.lib user32.lib gdi32.lib winspool.lib comdlg32.lib advapi32.lib shell32.lib ole32.lib oleaut32.lib uuid.lib odbc32.lib odbccp32.lib /nologo /subsystem:console /machine:I386
# ADD LINK32 kernel32.lib user32.lib gdi32.lib winspool.lib comdlg32.lib advapi32.lib shell32.lib ole32.lib oleaut32.lib uuid.lib odbc32.lib odbccp32.lib kernel32.lib user32.lib gdi32.lib winspool.lib comdlg32.lib advapi32.lib shell32.lib ole32.lib oleaut32.lib uuid.lib odbc32.lib odbccp32.lib /nologo /subsystem:console /machine:I386

!ELSEIF  "$(CFG)" == "AtdDetalhe - Win32 Debug"

# PROP BASE Use_MFC 0
# PROP BASE Use_Debug_Libraries 1
# PROP BASE Output_Dir "Debug"
# PROP BASE Intermediate_Dir "Debug"
# PROP BASE Target_Dir ""
# PROP Use_MFC 0
# PROP Use_Debug_Libraries 1
# PROP Output_Dir "Debug"
# PROP Intermediate_Dir "Debug"
# PROP Target_Dir ""
# ADD BASE CPP /nologo /W3 /Gm /GX /ZI /Od /D "WIN32" /D "_DEBUG" /D "_CONSOLE" /D "_MBCS" /YX /FD /GZ /c
# ADD CPP /nologo /Zp1 /W3 /Gm /GX /ZI /Od /D "WIN32" /D "_DEBUG" /D "_CONSOLE" /D "_MBCS" /FR /FD /GZ /c
# SUBTRACT CPP /YX
# ADD BASE RSC /l 0x416 /d "_DEBUG"
# ADD RSC /l 0x416 /d "_DEBUG"
BSC32=bscmake.exe
# ADD BASE BSC32 /nologo
# ADD BSC32 /nologo
LINK32=link.exe
# ADD BASE LINK32 kernel32.lib user32.lib gdi32.lib winspool.lib comdlg32.lib advapi32.lib shell32.lib ole32.lib oleaut32.lib uuid.lib odbc32.lib odbccp32.lib kernel32.lib user32.lib gdi32.lib winspool.lib comdlg32.lib advapi32.lib shell32.lib ole32.lib oleaut32.lib uuid.lib odbc32.lib odbccp32.lib /nologo /subsystem:console /debug /machine:I386 /pdbtype:sept
# ADD LINK32 kernel32.lib user32.lib gdi32.lib winspool.lib comdlg32.lib advapi32.lib shell32.lib ole32.lib oleaut32.lib uuid.lib odbc32.lib odbccp32.lib kernel32.lib user32.lib gdi32.lib winspool.lib comdlg32.lib advapi32.lib shell32.lib ole32.lib oleaut32.lib uuid.lib odbc32.lib odbccp32.lib /nologo /subsystem:console /debug /machine:I386 /pdbtype:sept

!ENDIF 

# Begin Target

# Name "AtdDetalhe - Win32 Release"
# Name "AtdDetalhe - Win32 Debug"
# Begin Group "Source Files"

# PROP Default_Filter "cpp;c;cxx;rc;def;r;odl;idl;hpj;bat"
# Begin Source File

SOURCE=..\..\..\commons\Collection\src\Collection.cpp
# End Source File
# Begin Source File

SOURCE=..\Andamento\src\cWFAndamento.cpp
# End Source File
# Begin Source File

SOURCE=..\Andamento\src\cWFAndamentoPC.cpp
# End Source File
# Begin Source File

SOURCE=.\src\cWFAtdDetalhe.cpp
# End Source File
# Begin Source File

SOURCE=.\src\cWFAtdDetalhePC.cpp
# End Source File
# Begin Source File

SOURCE=..\Atendimento\src\cWFAtendimento.cpp
# End Source File
# Begin Source File

SOURCE=..\AtendimentoBaixa\src\cWFAtendimentoBaixa.cpp
# End Source File
# Begin Source File

SOURCE=..\AtendimentoBaixaAtual\src\cWFAtendimentoBaixaAtual.cpp
# End Source File
# Begin Source File

SOURCE=..\AtendimentoBaixaAtual\src\cWFAtendimentoBaixaAtualPC.cpp
# End Source File
# Begin Source File

SOURCE=..\AtendimentoBaixa\src\cWFAtendimentoBaixaPC.cpp
# End Source File
# Begin Source File

SOURCE=..\AtendimentoConta\src\cWFAtendimentoConta.cpp
# End Source File
# Begin Source File

SOURCE=..\AtendimentoConta\src\cWFAtendimentoContaPC.cpp
# End Source File
# Begin Source File

SOURCE=..\AtendimentoContato\src\cWFAtendimentoContato.cpp
# End Source File
# Begin Source File

SOURCE=..\AtendimentoContatoComunic\src\cWFAtendimentoContatoComunic.cpp
# End Source File
# Begin Source File

SOURCE=..\AtendimentoContatoComunic\src\cWFAtendimentoContatoComunicPC.cpp
# End Source File
# Begin Source File

SOURCE=..\AtendimentoContato\src\cWFAtendimentoContatoPC.cpp
# End Source File
# Begin Source File

SOURCE=..\AtendimentoFechamento\src\cWFAtendimentoFechamento.cpp
# End Source File
# Begin Source File

SOURCE=..\AtendimentoFechamento\src\cWFAtendimentoFechamentoPC.cpp
# End Source File
# Begin Source File

SOURCE=..\AtendimentoFrmCampoValor\src\cWFAtendimentoFrmCampoValor.cpp
# End Source File
# Begin Source File

SOURCE=..\AtendimentoFrmCampoValor\src\cWFAtendimentoFrmCampoValorPC.cpp
# End Source File
# Begin Source File

SOURCE=..\AtendimentoGrupoAtual\src\cWFAtendimentoGrupoAtual.cpp
# End Source File
# Begin Source File

SOURCE=..\AtendimentoGrupoAtual\src\cWFAtendimentoGrupoAtualPC.cpp
# End Source File
# Begin Source File

SOURCE=..\AtendimentoLinha\src\cWFAtendimentoLinha.cpp
# End Source File
# Begin Source File

SOURCE=..\AtendimentoLinha\src\cWFAtendimentoLinhaPC.cpp
# End Source File
# Begin Source File

SOURCE=..\AtendimentoNivel\src\cWFAtendimentoNivel.cpp
# End Source File
# Begin Source File

SOURCE=..\AtendimentoNivel\src\cWFAtendimentoNivelPC.cpp
# End Source File
# Begin Source File

SOURCE=..\AtendimentoOrigem\src\cWFAtendimentoOrigem.cpp
# End Source File
# Begin Source File

SOURCE=..\AtendimentoOrigem\src\cWFAtendimentoOrigemPC.cpp
# End Source File
# Begin Source File

SOURCE=..\Atendimento\src\cWFAtendimentoPC.cpp
# End Source File
# Begin Source File

SOURCE=..\AtendimentoPessoa\src\cWFAtendimentoPessoa.cpp
# End Source File
# Begin Source File

SOURCE=..\AtendimentoPessoa\src\cWFAtendimentoPessoaPC.cpp
# End Source File
# Begin Source File

SOURCE=..\..\..\workflow\servico\CondicaoAparicao\src\cWFCondicaoAparicao.cpp
# End Source File
# Begin Source File

SOURCE=..\..\..\workflow\servico\CondicaoAparicao\src\cWFCondicaoAparicaoPC.cpp
# End Source File
# Begin Source File

SOURCE=..\..\..\workflow\servico\Estado\src\cWFEstado.cpp
# End Source File
# Begin Source File

SOURCE=..\..\..\workflow\servico\Estado\src\cWFEstadoPC.cpp
# End Source File
# Begin Source File

SOURCE=..\..\..\workflow\servico\Fase\src\cWFFase.cpp
# End Source File
# Begin Source File

SOURCE=..\..\..\workflow\servico\Fase\src\cWFFasePC.cpp
# End Source File
# Begin Source File

SOURCE=..\..\..\workflow\servico\FluxoFase\src\cWFFluxoFase.cpp
# End Source File
# Begin Source File

SOURCE=..\..\..\workflow\servico\FluxoFase\src\cWFFluxoFasePC.cpp
# End Source File
# Begin Source File

SOURCE=..\..\..\..\TuxCli\RouterClient.cpp
# End Source File
# Begin Source File

SOURCE=.\src\svcWFAtdDetalhe.cpp
# End Source File
# End Group
# Begin Group "Header Files"

# PROP Default_Filter "h;hpp;hxx;hm;inl"
# Begin Source File

SOURCE=.\include\cWFAtdDetalhe.h
# End Source File
# Begin Source File

SOURCE=..\..\..\commons\msgPadrao.h
# End Source File
# Begin Source File

SOURCE=..\..\..\..\TuxCli\RouterClient.h
# End Source File
# Begin Source File

SOURCE=.\include\stWFAtdDetalhe.h
# End Source File
# End Group
# Begin Group "Resource Files"

# PROP Default_Filter "ico;cur;bmp;dlg;rc2;rct;bin;rgs;gif;jpg;jpeg;jpe"
# Begin Source File

SOURCE=.\makefile
# End Source File
# Begin Source File

SOURCE=.\xml\XMLIn.xml
# End Source File
# End Group
# Begin Group "ProC"

# PROP Default_Filter "*.pcpp"
# Begin Source File

SOURCE=.\src\cWFAtdDetalhePC.pcpp

!IF  "$(CFG)" == "AtdDetalhe - Win32 Release"

!ELSEIF  "$(CFG)" == "AtdDetalhe - Win32 Debug"

# Begin Custom Build
InputPath=.\src\cWFAtdDetalhePC.pcpp

"cWFAtdDetalhePC.cpp" : $(SOURCE) "$(INTDIR)" "$(OUTDIR)"
	proc        parse=none        cpp_suffix=cpp        code=cpp        $(InputPath)        include=D:\Projeto\FrontOffice\TUXEFO\atendimento\servico\ATDDETALHE\include

# End Custom Build

!ENDIF 

# End Source File
# End Group
# End Target
# End Project
