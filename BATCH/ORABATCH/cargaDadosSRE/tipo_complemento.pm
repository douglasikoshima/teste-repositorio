#!/apps/perl-5.005_03/bin/perl
#ident @(#) P2K: src/cacs/tools/loadDNE/tipo_complemento.pm 70.2 09/12/14 14:47:50
# (c) 2008,2009, Convergys Information Management Group Inc. All Rights Reserved. Private and Confidential. May not be disclosed without permission.

################################################################################
#   tipo_complemento.pm  -  10/03/2008 by fabio schiozer
#
#   Brazil's address tipo_logradouro class
################################################################################

package tipo_complemento;

use     strict;

################################################################################
#   constants declaration
################################################################################

use constant SUCCESS => 0;
use constant ERROR   => 1;

################################################################################
#   construct a new tipo_complemento object
################################################################################

sub new {
    my  $self = {};

    $self->{ COD_TIPO_COMPLEMENTO } = undef;
    $self->{ DSC_ABREV_TIPO_COMPLEMENTO } = undef;
    $self->{ DSC_TIPO_COMPLEMENTO } = undef;
    $self->{ DAT_ATUALIZACAO } = undef;
    $self->{ USUARIO } = undef;
    $self->{ VER_NBR } = undef;
    $self->{ DAT_CARGA } = undef;

    bless( $self );

    return $self;
}

################################################################################
#   set/get COD_TIPO_COMPLEMENTO attribute
################################################################################

sub codigo {
    my  $self = shift;

    if( @_ ) {
        $self->{ COD_TIPO_COMPLEMENTO } = shift;
    }

    $self->{ COD_TIPO_COMPLEMENTO };
}

################################################################################
#   set/get DSC_ABREV_TIPO_COMPLEMENTO attribute
################################################################################

sub abreviatura {
    my  $self = shift;

    if( @_ ) {
        $self->{ DSC_ABREV_TIPO_COMPLEMENTO } = substr( shift, 0, 7 );
    }

    $self->{ DSC_ABREV_TIPO_COMPLEMENTO };
}

################################################################################
#   set/get DSC_TIPO_COMPLEMENTO attribute
################################################################################

sub descricao {
    my  $self = shift;

    if( @_ ) {
        $self->{ DSC_TIPO_COMPLEMENTO } = substr( shift, 0, 15 );
    }

    $self->{ DSC_TIPO_COMPLEMENTO };
}

################################################################################
#   set/get DAT_ATUALIZACAO attribute
################################################################################

sub dataAtualizacao {
    my  $self = shift;

    if( @_ ) {
        $self->{ DAT_ATUALIZACAO } = shift;
    }

    $self->{ DAT_ATUALIZACAO };
}

################################################################################
#   set/get DAT_CARGA attribute
################################################################################

sub dataCarga {
    my  $self = shift;

    if( @_ ) {
        $self->{ DAT_CARGA } = shift;
    }

    $self->{ DAT_CARGA };
}

################################################################################
#   set/get USUARIO attribute
################################################################################

sub usuarioAtualizacao {
    my  $self = shift;

    if( @_ ) {
        $self->{ USUARIO } = shift;
    }

    $self->{ USUARIO };
}

################################################################################
#   set/get VER_NBR attribute
################################################################################

sub verNbr {
    my  $self = shift;

    if( @_ ) {
        $self->{ VER_NBR } = shift;
    }

    $self->{ VER_NBR };
}

################################################################################
#   transform an object in a printable string
################################################################################

sub to_string {
    my  $self = shift;

    sprintf qq/codigo: %d\nabreviacao: '%s'\ndescricao: '%s'\ndata: %s\nusuario: %d/
            , ( defined $self->{ COD_TIPO_COMPLEMENTO } )?$self->{ COD_TIPO_COMPLEMENTO }:0
            , ( defined $self->{ DSC_ABREV_TIPO_COMPLEMENTO } )?$self->{ DSC_ABREV_TIPO_COMPLEMENTO }:''
            , ( defined $self->{ DSC_TIPO_COMPLEMENTO } )?$self->{ DSC_TIPO_COMPLEMENTO }:''
            , ( defined $self->{ DAT_ATUALIZACAO } )?$self->{ DAT_ATUALIZACAO }:0
            , ( defined $self->{ USUARIO } )?$self->{ USUARIO }:0
            , ( defined $self->{ VER_NBR } )?$self->{ VER_NBR }:0
            , ( defined $self->{ DAT_CARGA } )?$self->{ DAT_CARGA }:0;
            
}

################################################################################
#   get all objects from database that has non relatinpship with cdd
################################################################################

sub queryNonRelated {
    
    my  $connection = shift;

    my  $SQLQuery = <<SQL_QUERY;
select
    COD_TIPO_COMPLEMENTO
    , DSC_ABREV_TIPO_COMPLEMENTO
    , DSC_TIPO_COMPLEMENTO
    , DAT_ATUALIZACAO
    , USUARIO
    , VER_NBR
    , DAT_CARGA
from
    TIPO_COMPLEMENTO tc
where
    not exists (select 1 from CENTRO_DISTRIBUICAO cdd 
                 where cdd.COD_TIPO_COMPL01_CDD = tc.COD_TIPO_COMPLEMENTO
                    or cdd.COD_TIPO_COMPL02_CDD = tc.COD_TIPO_COMPLEMENTO
                    or cdd.COD_TIPO_COMPL03_CDD = tc.COD_TIPO_COMPLEMENTO)
SQL_QUERY
    my  $recordSet;
    my  @resultRow;
    my  @container = ();
    
    $recordSet = $connection->prepare( $SQLQuery );
    $recordSet->execute()
            or die "error running the query\n$SQLQuery\non database server\n";

    while( @resultRow = $recordSet->fetchrow_array() ) {
        my  $new = {};

        $new->{ COD_TIPO_COMPLEMENTO } = $resultRow[ 0 ];
        $new->{ DSC_ABREV_TIPO_COMPLEMENTO } = $resultRow[ 1 ];
        $new->{ DSC_TIPO_COMPLEMENTO } = $resultRow[ 2 ];
        $new->{ DAT_ATUALIZACAO } = $resultRow[ 3 ];
        $new->{ USUARIO } = $resultRow[ 4 ];
        $new->{ VER_NBR } = $resultRow[ 5 ];
        $new->{ DAT_CARGA } = $resultRow[ 6 ];

        bless( $new );

        push(@container, $new);
    }

    $recordSet->finish();

    @container;
}

################################################################################
#   delete an object from database
################################################################################

sub delete {
    my $self       = shift;
    my $connection = shift;

#   nullable fields
    my $COD_TIPO_COMPLEMENTO;
    my $returnCode = SUCCESS;

    if( defined $self->{ COD_TIPO_COMPLEMENTO } ) {
        $COD_TIPO_COMPLEMENTO = $self->{ COD_TIPO_COMPLEMENTO };

        my $SQLQuery = <<SQL_QUERY;
delete from TIPO_COMPLEMENTO
where COD_TIPO_COMPLEMENTO = ?
SQL_QUERY

        my $recordSet;

        $recordSet = $connection->prepare( $SQLQuery );
        $recordSet->execute( $self->{ COD_TIPO_COMPLEMENTO } )
                or return ERROR;

        if( $recordSet->rows == 0 ) {
            $returnCode = ERROR;
        }

    } else {
        $returnCode = ERROR;
    }

    $returnCode;
}

################################################################################
#   delete all non related objects
################################################################################

sub deleteNonRelated {
    
    my  $connection = shift;
    my $returnCode = SUCCESS;
    
    my  $SQLQuery = <<SQL_QUERY;
delete from
    TIPO_COMPLEMENTO tc
where
    not exists (select 1 from CENTRO_DISTRIBUICAO cdd 
                 where cdd.COD_TIPO_COMPL01_CDD = tc.COD_TIPO_COMPLEMENTO
                    or cdd.COD_TIPO_COMPL02_CDD = tc.COD_TIPO_COMPLEMENTO
                    or cdd.COD_TIPO_COMPL03_CDD = tc.COD_TIPO_COMPLEMENTO)
SQL_QUERY

    my $recordSet;

    $recordSet = $connection->prepare( $SQLQuery );
    $recordSet->execute()
            or return ERROR;

    if( $recordSet->rows == 0 ) {
        $returnCode = ERROR;
    }

    $returnCode;
}

#   return 1 to module requiring or using it

1;



