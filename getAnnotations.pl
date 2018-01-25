#!/usr/bin/perl
#use strict;
#Define all Needed Variables
use List::MoreUtils qw(uniq);
my $path = `pwd`;
chomp ($path);
my $workdir= $path;
my %map = ();
my @uniprot=();
my $database="myfile.lst";
my $filename= "$path/annotationAxiom.lst";
open (my $file, "$database");
open (my $fh, '>>',"$filename")or die "Could not open file '$filename' $!";
my $line =<$file>;
$line =<$file>;
while ( $line = <$file>)
{
	chomp ($line);
	if ($line !~"	IEA" and $line !~"	ND" )
	#if ($line !~" IEA " and $line !~"	ND" )
	{
		#print ("GOT HERE !\n");
		if ($line=~/^UniProtKB\s+(\S+)\s+\S+\s+GO:(\S+)/)
		{
			my $unprid=$1;
			my $gofunc=$2;
			print $fh "$unprid hasFunction GO_$gofunc\n";
		}
	}
}
# my @proteins=uniq @uniprot;
# foreach (@proteins)
# {
# 	print $fh "$_\n";
# }
