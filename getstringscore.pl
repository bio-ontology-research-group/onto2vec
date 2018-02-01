#!/usr/bin/perl 
#use strict; 
#Define all Needed Variables

my $path=`pwd`;
print ("$path\n");
chomp ($path);
my $stringfile ="$path/STRING/Human/9606.protein.links.v10.5.txt";
my $mapping = "$path/STRING/mapping.tsv";
my $outfile = "$path/STRING/Human/Unipr_score.lst";
my $outfile1 = "$path/STRING/Human/tst.txt";
open (FILE, "$stringfile");
open (OUT,  ">>$outfile");
open (FH1, '>>', "$outfile1");
open (FH, "$mapping");
print (FH1 "Hello\n");
while (my $line =<FILE>)
{	
	
	#print ($line);
	chomp ($line);
	if ($line=~ /\S+\.(\S+)\s+\S+\.(\S+)\s+(\S+)/)
	{
		#print ("$1 $2 $3 \n");
		my $prot1= $1;
		my $prot2= $2;
		my $strscore=$3;
		my $uni1 =();
		my $uni2 =();		
		open (FH, "$mapping");		
		while (my $map =<FH>)
		{
			chomp ($map);
			if ($map =~ /\S+\s+(\S+)\|\S+\s+(\S+)/)
			{
				my $unipr =$1;
				my $stracc=$2;
				if ($stracc=~ /$prot1/)
				{
					#print ("First found\n");					
					$uni1 =$unipr;
					
				}
				if ($stracc=~ /$prot2/)
				{
					#print ("Second found\n");					
					$uni2 =$unipr;
				}					
			}
				
		}
		print  "$uni1 $uni2 $strscore \n" or die ("Can't print \n");
		close (FH);
	}  
}
