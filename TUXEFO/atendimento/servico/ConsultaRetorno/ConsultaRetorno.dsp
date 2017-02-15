# Microsoft Developer Studio Project File - Name="ConsultaRetorno" - Package Owner=<4>
# Microsoft Developer Studio Generated Build File, Format Version 6.00
# ** DO NOT EDIT **

# TARGTYPE "Win32 (x86) Console Application" 0x0103

CFG=ConsultaRetorno - Win32 Debug
!MESSAGE This is not a valid makefile. To build this project using NMAKE,
!MESSAGE use the Export Makefile command and run
!MESSAGE 
!MESSAGE NMAKE /f "ConsultaRetorno.mak".
!MESSAGE 
!MESSAGE You can specify a configuration when running NMAKE
!MESSAGE by defining the macro CFG on the command line. For example:
!MESSAGE 
!MESSAGE NMAKE /f "ConsultaRetorno.mak" CFG="ConsultaRetorno - Win32 Debug"
!MESSAGE 
!MESSAGE Possible choices for configuration are:
!MESSAGE 
!MESSAGE "ConsultaRetorno - Win32 Release" (based on "Win32 (x86) Console Application")
!MESSAGE "ConsultaRetorno - Win32 Debug" (based on "Win32 (x86) Console Application")
!MESSAGE 

# Begin Project
# PROP AllowPerConfigDependencies 0
# PROP Scc_ProjName ""
# PROP Scc_LocalPath ""
CPP=cl.exe
RSC=rc.exe

!IF  "$(CFG)" == "ConsultaRetorno - Win32 Release"

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
# ADD CPP /nologo /W3 /GX /O2 /D "WIN32" /D "NDEBUG" /D "_CONSOLE" /D "_MBCS" /YX /FD /c
# ADD BASE RSC /l 0x416 /d "NDEBUG"
# ADD RSC /l 0x416 /d "NDEBUG"
BSC32=bscmake.exe
# ADD BASE BSC32 /nologo
# ADD BSC32 /nologo
LINK32=link.exe
# ADD BASE LINK32 kernel32.lib user32.lib gdi32.lib winspool.lib comdlg32.lib advapi32.lib shell32.lib ole32.lib oleaut32.lib uuid.lib odbc32.lib odbccp32.lib kernel32.lib user32.lib gdi32.lib winspool.lib comdlg32.lib advapi32.lib shell32.lib ole32.lib oleaut32.lib uuid.lib odbc32.lib odbccp32.lib /nologo /subsystem:console /machine:I386
# ADD LINK32 kernel32.lib user32.lib gdi32.lib winspool.lib comdlg32.lib advapi32.lib shell32.lib ole32.lib oleaut32.lib uuid.lib odbc32.lib odbccp32.lib kernel32.lib user32.lib gdi32.lib winspool.lib comdlg32.lib advapi32.lib shell32.lib ole32.lib oleaut32.lib uuid.lib odbc32.lib odbccp32.lib /nologo /subsystem:console /machine:I386

!ELSEIF  "$(CFG)" == "ConsultaRetorno - Win32 Debug"

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
# ADD CPP /nologo /W3 /Gm /GX /ZI /Od /D "WIN32" /D "_DEBUG" /D "_CONSOLE" /D "_MBCS" /YX /FD /GZ /c
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

# Name "ConsultaRetorno - Win32 Release"
# Name "ConsultaRetorno - Win32 Debug"
# Begin Group "Source Files"

# PROP Default_Filter "cpp;c;cxx;rc;def;r;odl;idl;hpj;bat"
# Begin Source File

SOURCE=.\src\CnsCanalEntrada.cpp
# End Source File
# Begin Source File

SOURCE=.\src\CnsCanalEntradaSel.cpp
# End Source File
# Begin Source File

SOURCE=.\src\CnsGrupo.cpp
# End Source File
# Begin Source File

SOURCE=.\src\CnsGrupoSel.cpp
# End Source File
# Begin Source File

SOURCE=.\src\CnsPessoa.cpp
# End Source File
# Begin Source File

SOURCE=.\src\CnsPessoaSel.cpp
# End Source File
# Begin Source File

SOURCE=.\src\CnsProcedencia.cpp
# End Source File
# Begin Source File

SOURCE=.\src\CnsProcedenciaSel.cpp
# End Source File
# Begin Source File

SOURCE=.\src\CnsRelacionamento.cpp
# End Source File
# Begin Source File

SOURCE=.\src\CnsRelacionamentoSel.cpp
# End Source File
# Begin Source File

SOURCE=.\src\CnsSegmentacao.cpp
# End Source File
# Begin Source File

SOURCE=.\src\CnsSegmentacaoSel.cpp
# End Source File
# Begin Source File

SOURCE=.\src\CnsTipoCarteira.cpp
# End Source File
# Begin Source File

SOURCE=.\src\CnsTipoCarteiraSel.cpp
# End Source File
# Begin Source File

SOURCE=.\src\CnsTipoLinha.cpp
# End Source File
# Begin Source File

SOURCE=.\src\CnsTipoLinhaSel.cpp
# End Source File
# Begin Source File

SOURCE=.\src\CnsTipoRetorno.cpp
# End Source File
# Begin Source File

SOURCE=.\src\CnsTipoUfOperadora.cpp
# End Source File
# Begin Source File

SOURCE=.\src\CnsTipoUfOperadoraSel.cpp
# End Source File
# Begin Source File

SOURCE=..\..\..\..\TuxCli\RouterClient.cpp
# End Source File
# Begin Source File

SOURCE=.\src\svcmain.cpp
# End Source File
# End Group
# Begin Group "Header Files"

# PROP Default_Filter "h;hpp;hxx;hm;inl"
# Begin Source File

SOURCE=.\include\RouterClient.h
# End Source File
# Begin Source File

SOURCE=.\include\svcmain.h
# End Source File
# End Group
# Begin Group "Resource Files"

# PROP Default_Filter "ico;cur;bmp;dlg;rc2;rct;bin;rgs;gif;jpg;jpeg;jpe"
# Begin Source File

SOURCE=.\teste.xml
# End Source File
# End Group
# Begin Group "ProC"

# PROP Default_Filter "*.pcpp"
# Begin Source File

SOURCE=.\src\CnsCanalEntrada.pcpp

!IF  "$(CFG)" == "ConsultaRetorno - Win32 Release"

!ELSEIF  "$(CFG)" == "ConsultaRetorno - Win32 Debug"

# Begin Custom Build
InputPath=.\src\CnsCanalEntrada.pcpp

"CnsCanalEntrada.cpp" : $(SOURCE) "$(INTDIR)" "$(OUTDIR)"
	proc  parse=none  cpp_suffix=cpp  code=cpp  $(InputPath)  include=D:\Projeto\FrontOffice\TUXEFO\workflow\servico\ConsultaRetorno\include

# End Custom Build

!ENDIF 

# End Source File
# Begin Source File

SOURCE=.\src\CnsCanalEntradaSel.pcpp

!IF  "$(CFG)" == "ConsultaRetorno - Win32 Release"

!ELSEIF  "$(CFG)" == "ConsultaRetorno - Win32 Debug"

# Begin Custom Build
InputPath=.\src\CnsCanalEntradaSel.pcpp

"CnsCanalEntradaSel.cpp" : $(SOURCE) "$(INTDIR)" "$(OUTDIR)"
	proc  parse=none  cpp_suffix=cpp  code=cpp  $(InputPath)  include=D:\Projeto\FrontOffice\TUXEFO\workflow\servico\ConsultaRetorno\include

# End Custom Build

!ENDIF 

# End Source File
# Begin Source File

SOURCE=.\src\CnsGrupo.pcpp

!IF  "$(CFG)" == "ConsultaRetorno - Win32 Release"

!ELSEIF  "$(CFG)" == "ConsultaRetorno - Win32 Debug"

# Begin Custom Build
InputPath=.\src\CnsGrupo.pcpp

"CnsGrupo.cpp" : $(SOURCE) "$(INTDIR)" "$(OUTDIR)"
	proc  parse=none  cpp_suffix=cpp  code=cpp  $(InputPath)  include=D:\Projeto\FrontOffice\TUXEFO\workflow\servico\ConsultaRetorno\include

# End Custom Build

!ENDIF 

# End Source File
# Begin Source File

SOURCE=.\src\CnsGrupoSel.pcpp

!IF  "$(CFG)" == "ConsultaRetorno - Win32 Release"

!ELSEIF  "$(CFG)" == "ConsultaRetorno - Win32 Debug"

# Begin Custom Build
InputPath=.\src\CnsGrupoSel.pcpp

"CnsGrupoSel.cpp" : $(SOURCE) "$(INTDIR)" "$(OUTDIR)"
	proc  parse=none  cpp_suffix=cpp  code=cpp  $(InputPath)  include=D:\Projeto\FrontOffice\TUXEFO\workflow\servico\ConsultaRetorno\include

# End Custom Build

!ENDIF 

# End Source File
# Begin Source File

SOURCE=.\src\CnsPessoa.pcpp

!IF  "$(CFG)" == "ConsultaRetorno - Win32 Release"

!ELSEIF  "$(CFG)" == "ConsultaRetorno - Win32 Debug"

# Begin Custom Build
InputPath=.\src\CnsPessoa.pcpp

"CnsPessoa.cpp" : $(SOURCE) "$(INTDIR)" "$(OUTDIR)"
	proc  parse=none  cpp_suffix=cpp  code=cpp  $(InputPath)  include=D:\Projeto\FrontOffice\TUXEFO\workflow\servico\ConsultaRetorno\include

# End Custom Build

!ENDIF 

# End Source File
# Begin Source File

SOURCE=.\src\CnsPessoaSel.pcpp

!IF  "$(CFG)" == "ConsultaRetorno - Win32 Release"

!ELSEIF  "$(CFG)" == "ConsultaRetorno - Win32 Debug"

# Begin Custom Build
InputPath=.\src\CnsPessoaSel.pcpp

"CnsPessoaSel.cpp" : $(SOURCE) "$(INTDIR)" "$(OUTDIR)"
	proc  parse=none  cpp_suffix=cpp  code=cpp  $(InputPath)  include=D:\Projeto\FrontOffice\TUXEFO\workflow\servico\ConsultaRetorno\include

# End Custom Build

!ENDIF 

# End Source File
# Begin Source File

SOURCE=.\src\CnsProcedencia.pcpp

!IF  "$(CFG)" == "ConsultaRetorno - Win32 Release"

!ELSEIF  "$(CFG)" == "ConsultaRetorno - Win32 Debug"

# Begin Custom Build
InputPath=.\src\CnsProcedencia.pcpp

"CnsProcedencia.cpp" : $(SOURCE) "$(INTDIR)" "$(OUTDIR)"
	proc  parse=none  cpp_suffix=cpp  code=cpp  $(InputPath)  include=D:\Projeto\FrontOffice\TUXEFO\workflow\servico\ConsultaRetorno\include

# End Custom Build

!ENDIF 

# End Source File
# Begin Source File

SOURCE=.\src\CnsProcedenciaSel.pcpp

!IF  "$(CFG)" == "ConsultaRetorno - Win32 Release"

!ELSEIF  "$(CFG)" == "ConsultaRetorno - Win32 Debug"

# Begin Custom Build
InputPath=.\src\CnsProcedenciaSel.pcpp

"CnsProcedenciaSel.cpp" : $(SOURCE) "$(INTDIR)" "$(OUTDIR)"
	proc  parse=none  cpp_suffix=cpp  code=cpp  $(InputPath)  include=D:\Projeto\FrontOffice\TUXEFO\workflow\servico\ConsultaRetorno\include

# End Custom Build

!ENDIF 

# End Source File
# Begin Source File

SOURCE=.\src\CnsRelacionamento.pcpp

!IF  "$(CFG)" == "ConsultaRetorno - Win32 Release"

!ELSEIF  "$(CFG)" == "ConsultaRetorno - Win32 Debug"

# Begin Custom Build
InputPath=.\src\CnsRelacionamento.pcpp

"CnsRelacionamento.cpp" : $(SOURCE) "$(INTDIR)" "$(OUTDIR)"
	proc  parse=none  cpp_suffix=cpp  code=cpp  $(InputPath)  include=D:\Projeto\FrontOffice\TUXEFO\workflow\servico\ConsultaRetorno\include

# End Custom Build

!ENDIF 

# End Source File
# Begin Source File

SOURCE=.\src\CnsRelacionamentoSel.pcpp

!IF  "$(CFG)" == "ConsultaRetorno - Win32 Release"

!ELSEIF  "$(CFG)" == "ConsultaRetorno - Win32 Debug"

# Begin Custom Build
InputPath=.\src\CnsRelacionamentoSel.pcpp

"CnsRelacionamentoSel.cpp" : $(SOURCE) "$(INTDIR)" "$(OUTDIR)"
	proc  parse=none  cpp_suffix=cpp  code=cpp  $(InputPath)  include=D:\Projeto\FrontOffice\TUXEFO\workflow\servico\ConsultaRetorno\include

# End Custom Build

!ENDIF 

# End Source File
# Begin Source File

SOURCE=.\src\CnsSegmentacao.pcpp

!IF  "$(CFG)" == "ConsultaRetorno - Win32 Release"

!ELSEIF  "$(CFG)" == "ConsultaRetorno - Win32 Debug"

# Begin Custom Build
InputPath=.\src\CnsSegmentacao.pcpp

"CnsSegmentacao.cpp" : $(SOURCE) "$(INTDIR)" "$(OUTDIR)"
	proc  parse=none  cpp_suffix=cpp  code=cpp  $(InputPath)  include=D:\Projeto\FrontOffice\TUXEFO\workflow\servico\ConsultaRetorno\include

# End Custom Build

!ENDIF 

# End Source File
# Begin Source File

SOURCE=.\src\CnsSegmentacaoSel.pcpp

!IF  "$(CFG)" == "ConsultaRetorno - Win32 Release"

!ELSEIF  "$(CFG)" == "ConsultaRetorno - Win32 Debug"

# Begin Custom Build
InputPath=.\src\CnsSegmentacaoSel.pcpp

"CnsSegmentacaoSel.cpp" : $(SOURCE) "$(INTDIR)" "$(OUTDIR)"
	proc  parse=none  cpp_suffix=cpp  code=cpp  $(InputPath)  include=D:\Projeto\FrontOffice\TUXEFO\workflow\servico\ConsultaRetorno\include

# End Custom Build

!ENDIF 

# End Source File
# Begin Source File

SOURCE=.\src\CnsTipoCarteira.pcpp

!IF  "$(CFG)" == "ConsultaRetorno - Win32 Release"

!ELSEIF  "$(CFG)" == "ConsultaRetorno - Win32 Debug"

# Begin Custom Build
InputPath=.\src\CnsTipoCarteira.pcpp

"CnsTipoCarteira.cpp" : $(SOURCE) "$(INTDIR)" "$(OUTDIR)"
	proc  parse=none  cpp_suffix=cpp  code=cpp  $(InputPath)  include=D:\Projeto\FrontOffice\TUXEFO\workflow\servico\ConsultaRetorno\include

# End Custom Build

!ENDIF 

# End Source File
# Begin Source File

SOURCE=.\src\CnsTipoCarteiraSel.pcpp

!IF  "$(CFG)" == "ConsultaRetorno - Win32 Release"

!ELSEIF  "$(CFG)" == "ConsultaRetorno - Win32 Debug"

# Begin Custom Build
InputPath=.\src\CnsTipoCarteiraSel.pcpp

"CnsTipoCarteiraSel.cpp" : $(SOURCE) "$(INTDIR)" "$(OUTDIR)"
	proc  parse=none  cpp_suffix=cpp  code=cpp  $(InputPath)  include=D:\Projeto\FrontOffice\TUXEFO\workflow\servico\ConsultaRetorno\include

# End Custom Build

!ENDIF 

# End Source File
# Begin Source File

SOURCE=.\src\CnsTipoLinha.pcpp

!IF  "$(CFG)" == "ConsultaRetorno - Win32 Release"

!ELSEIF  "$(CFG)" == "ConsultaRetorno - Win32 Debug"

# Begin Custom Build
InputPath=.\src\CnsTipoLinha.pcpp

"CnsTipoLinha.cpp" : $(SOURCE) "$(INTDIR)" "$(OUTDIR)"
	proc  parse=none  cpp_suffix=cpp  code=cpp  $(InputPath)  include=D:\Projeto\FrontOffice\TUXEFO\workflow\servico\ConsultaRetorno\include

# End Custom Build

!ENDIF 

# End Source File
# Begin Source File

SOURCE=.\src\CnsTipoLinhaSel.pcpp

!IF  "$(CFG)" == "ConsultaRetorno - Win32 Release"

!ELSEIF  "$(CFG)" == "ConsultaRetorno - Win32 Debug"

# Begin Custom Build
InputPath=.\src\CnsTipoLinhaSel.pcpp

"CnsTipoLinhaSel.cpp" : $(SOURCE) "$(INTDIR)" "$(OUTDIR)"
	proc  parse=none  cpp_suffix=cpp  code=cpp  $(InputPath)  include=D:\Projeto\FrontOffice\TUXEFO\workflow\servico\ConsultaRetorno\include

# End Custom Build

!ENDIF 

# End Source File
# Begin Source File

SOURCE=.\src\CnsTipoRetorno.pcpp

!IF  "$(CFG)" == "ConsultaRetorno - Win32 Release"

!ELSEIF  "$(CFG)" == "ConsultaRetorno - Win32 Debug"

# Begin Custom Build
InputPath=.\src\CnsTipoRetorno.pcpp

"CnsTipoRetorno.cpp" : $(SOURCE) "$(INTDIR)" "$(OUTDIR)"
	proc  parse=none  cpp_suffix=cpp  code=cpp  $(InputPath)  include=D:\Projeto\FrontOffice\TUXEFO\workflow\servico\ConsultaRetorno\include

# End Custom Build

!ENDIF 

# End Source File
# Begin Source File

SOURCE=.\src\CnsTipoUfOperadora.pcpp

!IF  "$(CFG)" == "ConsultaRetorno - Win32 Release"

!ELSEIF  "$(CFG)" == "ConsultaRetorno - Win32 Debug"

# Begin Custom Build
InputPath=.\src\CnsTipoUfOperadora.pcpp

"CnsTipoUfOperadora.cpp" : $(SOURCE) "$(INTDIR)" "$(OUTDIR)"
	proc  parse=none  cpp_suffix=cpp  code=cpp  $(InputPath)  include=D:\Projeto\FrontOffice\TUXEFO\workflow\servico\ConsultaRetorno\include

# End Custom Build

!ENDIF 

# End Source File
# Begin Source File

SOURCE=.\src\CnsTipoUfOperadoraSel.pcpp

!IF  "$(CFG)" == "ConsultaRetorno - Win32 Release"

!ELSEIF  "$(CFG)" == "ConsultaRetorno - Win32 Debug"

# Begin Custom Build
InputPath=.\src\CnsTipoUfOperadoraSel.pcpp

"CnsTipoUfOperadoraSel.pcpp" : $(SOURCE) "$(INTDIR)" "$(OUTDIR)"
	proc  parse=none  cpp_suffix=cpp  code=cpp  $(InputPath)  include=D:\Projeto\FrontOffice\TUXEFO\workflow\servico\ConsultaRetorno\include

# End Custom Build

!ENDIF 

# End Source File
# End Group
# End Target
# End Project
