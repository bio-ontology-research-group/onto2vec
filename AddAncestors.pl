#!/usr/bin/perl
#use strict;
#Define all Needed Variables
use List::MoreUtils qw(uniq);
my $path = `pwd`;
chomp ($path);
my $annotfile="$path/annotationAxiom.lst";
my $ancestfile= "$path/axioms.lst";
my $addoutfile= "$path/annotationAxiomFinal.lst";
my @temparray=();
open (FILE, '>>', "$addoutfile");
open (FH,"$annotfile");
while (my $line =<FH>)
{
	chomp ($line);
	if ($line =~ /(\S+)\s+hasFunction\s+(\S+)/)
	{
		chomp ($line);
		push @temparray, $line;
		my $prot=$1;
		my $childGO=$2;
		open (FILE1, "$ancestfile" );
		while (my $line2 =<FILE1>)
		{
			chomp ($line2);
			if ($line2 =~ /(\S+)\s+SubClassOf\s+(\S+)/)
			{
				#print ("Found one \n");
				my $checkgo=$1;
				my $goAnces=$2;
				if ($checkgo eq $childGO)
				{
				my$newline= "$prot hasFunction $goAnces\n";
				push @temparray, my$newline;
				print ("Found two \n");
				}

			}
		}
	#print ("Done with this line\n");
	}

}
my @Finalarray= uniq @temparray;
foreach (@Finalarray)
{
 	my $final_annotation=$_;
	print FILE "$final_annotation\n";
}
