# Microsoft Developer Studio Project File - Name="TRCKCMPPEDIDO" - Package Owner=<4>
# Microsoft Developer Studio Generated Build File, Format Version 6.00
# ** DO NOT EDIT **

# TARGTYPE "Win32 (x86) Console Application" 0x0103

CFG=TRCKCMPPEDIDO - Win32 Debug
!MESSAGE This is not a valid makefile. To build this project using NMAKE,
!MESSAGE use the Export Makefile command and run
!MESSAGE 
!MESSAGE NMAKE /f "TRCKCMPPEDIDO.mak".
!MESSAGE 
!MESSAGE You can specify a configuration when running NMAKE
!MESSAGE by defining the macro CFG on the command line. For example:
!MESSAGE 
!MESSAGE NMAKE /f "TRCKCMPPEDIDO.mak" CFG="TRCKCMPPEDIDO - Win32 Debug"
!MESSAGE 
!MESSAGE Possible choices for configuration are:
!MESSAGE 
!MESSAGE "TRCKCMPPEDIDO - Win32 Release" (based on "Win32 (x86) Console Application")
!MESSAGE "TRCKCMPPEDIDO - Win32 Debug" (based on "Win32 (x86) Console Application")
!MESSAGE 

# Begin Project
# PROP AllowPerConfigDependencies 0
# PROP Scc_ProjName ""
# PROP Scc_LocalPath ""
CPP=cl.exe
RSC=rc.exe

!IF  "$(CFG)" == "TRCKCMPPEDIDO - Win32 Release"

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

!ELSEIF  "$(CFG)" == "TRCKCMPPEDIDO - Win32 Debug"

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
# ADD CPP /nologo /W3 /Gm /GX /ZI /Od /D "WIN32" /D "_DEBUG" /D "_CONSOLE" /D "_MBCS" /FR /YX /FD /GZ /c
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

# Name "TRCKCMPPEDIDO - Win32 Release"
# Name "TRCKCMPPEDIDO - Win32 Debug"
# Begin Group "Source Files"

# PROP Default_Filter "cpp;c;cxx;rc;def;r;odl;idl;hpj;bat"
# Begin Source File

SOURCE=..\..\negocio\clienteCmm\src\EtapaPedido.cpp
# End Source File
# Begin Source File

SOURCE=..\..\negocio\clienteCmm\src\EtapaPedidopc.cpp
# End Source File
# Begin Source File

SOURCE=..\..\negocio\clienteCmm\src\ItemOrdemVenda.cpp
# End Source File
# Begin Source File

SOURCE=..\..\negocio\clienteCmm\src\ItemOrdemVendapc.cpp
# End Source File
# Begin Source File

SOURCE=..\..\negocio\clienteCmm\src\ItemPedido.cpp
# End Source File
# Begin Source File

SOURCE=..\..\negocio\clienteCmm\src\ItemPedidopc.cpp
# End Source File
# Begin Source File

SOURCE=.\src\main.cpp
# End Source File
# Begin Source File

SOURCE=..\..\negocio\clienteCmm\src\OrdemNotaFiscalpc.cpp
# End Source File
# Begin Source File

SOURCE=..\..\negocio\clienteCmm\src\OrdemVenda.cpp
# End Source File
# Begin Source File

SOURCE=..\..\negocio\clienteCmm\src\OrdemVendapc.cpp
# End Source File
# Begin Source File

SOURCE=..\..\negocio\clienteCmm\src\Pedido.cpp
# End Source File
# Begin Source File

SOURCE=..\..\negocio\clienteCmm\src\Pedidopc.cpp
# End Source File
# Begin Source File

SOURCE=..\..\negocio\clienteCmm\src\ProdutoEntregapc.cpp
# End Source File
# End Group
# Begin Group "Header Files"

# PROP Default_Filter "h;hpp;hxx;hm;inl"
# Begin Source File

SOURCE=..\..\negocio\clienteCmm\include\EtapaPedido.h
# End Source File
# Begin Source File

SOURCE=..\..\negocio\clienteCmm\include\EtapaPedidopc.h
# End Source File
# Begin Source File

SOURCE=..\..\negocio\clienteCmm\include\Global.h
# End Source File
# Begin Source File

SOURCE=..\..\negocio\clienteCmm\include\GlobalPedido.h
# End Source File
# Begin Source File

SOURCE=..\..\negocio\clienteCmm\include\ItemOrdemVenda.h
# End Source File
# Begin Source File

SOURCE=..\..\negocio\clienteCmm\include\ItemOrdemVendapc.h
# End Source File
# Begin Source File

SOURCE=..\..\negocio\clienteCmm\include\ItemPedido.h
# End Source File
# Begin Source File

SOURCE=..\..\negocio\clienteCmm\include\ItemPedidopc.h
# End Source File
# Begin Source File

SOURCE=..\..\negocio\clienteCmm\include\OrdemNotaFiscal.h
# End Source File
# Begin Source File

SOURCE=..\..\negocio\clienteCmm\include\OrdemNotaFiscalpc.h
# End Source File
# Begin Source File

SOURCE=..\..\negocio\clienteCmm\include\OrdemVenda.h
# End Source File
# Begin Source File

SOURCE=..\..\negocio\clienteCmm\include\OrdemVendapc.h
# End Source File
# Begin Source File

SOURCE=..\..\negocio\clienteCmm\include\Pedido.h
# End Source File
# Begin Source File

SOURCE=..\..\negocio\clienteCmm\include\PedidoCarga.h
# End Source File
# Begin Source File

SOURCE=..\..\negocio\clienteCmm\include\PedidoNegocio.h
# End Source File
# Begin Source File

SOURCE=..\..\negocio\clienteCmm\include\Pedidopc.h
# End Source File
# Begin Source File

SOURCE=..\..\negocio\clienteCmm\include\ProdutoEntrega.h
# End Source File
# Begin Source File

SOURCE=..\..\negocio\clienteCmm\include\ProdutoEntregapc.h
# End Source File
# Begin Source File

SOURCE=..\..\..\..\TuxCli\tuxfw.h
# End Source File
# End Group
# Begin Group "Resource Files"

# PROP Default_Filter "ico;cur;bmp;dlg;rc2;rct;bin;rgs;gif;jpg;jpeg;jpe"
# Begin Source File

SOURCE=..\..\negocio\clienteCmm\makefile
# End Source File
# Begin Source File

SOURCE=.\makefile
# End Source File
# Begin Source File

SOURCE=..\..\servidor\TelaInicial\makefile
# End Source File
# Begin Source File

SOURCE=.\teste.xml
# End Source File
# End Group
# Begin Group "ProC"

# PROP Default_Filter "*.pcpp"
# Begin Source File

SOURCE=..\..\negocio\clienteCmm\src\EtapaPedidopc.pcpp

!IF  "$(CFG)" == "TRCKCMPPEDIDO - Win32 Release"

!ELSEIF  "$(CFG)" == "TRCKCMPPEDIDO - Win32 Debug"

# Begin Custom Build
InputDir=\Projeto\FrontOffice\EVOLUTIVA-V1\TUXEFO\cliente\negocio\clienteCmm\src
InputPath=..\..\negocio\clienteCmm\src\EtapaPedidopc.pcpp
InputName=EtapaPedidopc

"$(InputDir)/$(InputName).cpp" : $(SOURCE) "$(INTDIR)" "$(OUTDIR)"
	proc  parse=none  cpp_suffix=cpp  code=cpp $(InputPath)  include=.\include include=D:\Projeto\FrontOffice\EVOLUTIVA-V1\TUXEFO\cliente\negocio\clienteCmm\include

# End Custom Build

!ENDIF 

# End Source File
# Begin Source File

SOURCE=..\..\negocio\clienteCmm\src\ItemOrdemVendapc.pcpp

!IF  "$(CFG)" == "TRCKCMPPEDIDO - Win32 Release"

!ELSEIF  "$(CFG)" == "TRCKCMPPEDIDO - Win32 Debug"

# Begin Custom Build
InputDir=\Projeto\FrontOffice\EVOLUTIVA-V1\TUXEFO\cliente\negocio\clienteCmm\src
InputPath=..\..\negocio\clienteCmm\src\ItemOrdemVendapc.pcpp
InputName=ItemOrdemVendapc

"$(InputDir)/$(InputName).cpp" : $(SOURCE) "$(INTDIR)" "$(OUTDIR)"
	proc  parse=none  cpp_suffix=cpp  code=cpp $(InputPath)  include=.\include include=D:\Projeto\FrontOffice\EVOLUTIVA-V1\TUXEFO\cliente\negocio\clienteCmm\include

# End Custom Build

!ENDIF 

# End Source File
# Begin Source File

SOURCE=..\..\negocio\clienteCmm\src\ItemPedidopc.pcpp

!IF  "$(CFG)" == "TRCKCMPPEDIDO - Win32 Release"

!ELSEIF  "$(CFG)" == "TRCKCMPPEDIDO - Win32 Debug"

# Begin Custom Build
InputDir=\Projeto\FrontOffice\EVOLUTIVA-V1\TUXEFO\cliente\negocio\clienteCmm\src
InputPath=..\..\negocio\clienteCmm\src\ItemPedidopc.pcpp
InputName=ItemPedidopc

"$(InputDir)/$(InputName).cpp" : $(SOURCE) "$(INTDIR)" "$(OUTDIR)"
	proc  parse=none  cpp_suffix=cpp  code=cpp $(InputPath)  include=.\include include=D:\Projeto\FrontOffice\EVOLUTIVA-V1\TUXEFO\cliente\negocio\clienteCmm\include

# End Custom Build

!ENDIF 

# End Source File
# Begin Source File

SOURCE=..\..\negocio\clienteCmm\src\OrdemNotaFiscalpc.pcpp

!IF  "$(CFG)" == "TRCKCMPPEDIDO - Win32 Release"

!ELSEIF  "$(CFG)" == "TRCKCMPPEDIDO - Win32 Debug"

# Begin Custom Build
InputDir=\Projeto\FrontOffice\EVOLUTIVA-V1\TUXEFO\cliente\negocio\clienteCmm\src
InputPath=..\..\negocio\clienteCmm\src\OrdemNotaFiscalpc.pcpp
InputName=OrdemNotaFiscalpc

"$(InputDir)/$(InputName).cpp" : $(SOURCE) "$(INTDIR)" "$(OUTDIR)"
	proc  parse=none  cpp_suffix=cpp  code=cpp $(InputPath)  include=.\include include=D:\Projeto\FrontOffice\EVOLUTIVA-V1\TUXEFO\cliente\negocio\clienteCmm\include

# End Custom Build

!ENDIF 

# End Source File
# Begin Source File

SOURCE=..\..\negocio\clienteCmm\src\OrdemVendapc.pcpp

!IF  "$(CFG)" == "TRCKCMPPEDIDO - Win32 Release"

!ELSEIF  "$(CFG)" == "TRCKCMPPEDIDO - Win32 Debug"

# Begin Custom Build
InputDir=\Projeto\FrontOffice\EVOLUTIVA-V1\TUXEFO\cliente\negocio\clienteCmm\src
InputPath=..\..\negocio\clienteCmm\src\OrdemVendapc.pcpp
InputName=OrdemVendapc

"$(InputDir)/$(InputName).cpp" : $(SOURCE) "$(INTDIR)" "$(OUTDIR)"
	proc  parse=none  cpp_suffix=cpp  code=cpp $(InputPath)  include=.\include include=D:\Projeto\FrontOffice\EVOLUTIVA-V1\TUXEFO\cliente\negocio\clienteCmm\include

# End Custom Build

!ENDIF 

# End Source File
# Begin Source File

SOURCE=..\..\negocio\clienteCmm\src\Pedidopc.pcpp

!IF  "$(CFG)" == "TRCKCMPPEDIDO - Win32 Release"

!ELSEIF  "$(CFG)" == "TRCKCMPPEDIDO - Win32 Debug"

# Begin Custom Build
InputDir=\Projeto\FrontOffice\EVOLUTIVA-V1\TUXEFO\cliente\negocio\clienteCmm\src
InputPath=..\..\negocio\clienteCmm\src\Pedidopc.pcpp
InputName=Pedidopc

"$(InputDir)/$(InputName).cpp" : $(SOURCE) "$(INTDIR)" "$(OUTDIR)"
	proc  parse=none  cpp_suffix=cpp  code=cpp $(InputPath)  include=.\include include=D:\Projeto\FrontOffice\EVOLUTIVA-V1\TUXEFO\cliente\negocio\clienteCmm\include

# End Custom Build

!ENDIF 

# End Source File
# Begin Source File

SOURCE=..\..\negocio\clienteCmm\src\ProdutoEntregapc.pcpp

!IF  "$(CFG)" == "TRCKCMPPEDIDO - Win32 Release"

!ELSEIF  "$(CFG)" == "TRCKCMPPEDIDO - Win32 Debug"

# Begin Custom Build
InputDir=\Projeto\FrontOffice\EVOLUTIVA-V1\TUXEFO\cliente\negocio\clienteCmm\src
InputPath=..\..\negocio\clienteCmm\src\ProdutoEntregapc.pcpp
InputName=ProdutoEntregapc

"$(InputDir)/$(InputName).cpp" : $(SOURCE) "$(INTDIR)" "$(OUTDIR)"
	proc  parse=none  cpp_suffix=cpp  code=cpp $(InputPath)  include=.\include include=D:\Projeto\FrontOffice\EVOLUTIVA-V1\TUXEFO\cliente\negocio\clienteCmm\include

# End Custom Build

!ENDIF 

# End Source File
# End Group
# End Target
# End Project
