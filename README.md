# onto2vec
## Introduction
Onto2Vec is a program that can be used to produce feature vectors for biological entities based on their annotations to biomedical ontologies. Onto2Vec uses the integrity of the axioms describing the structure of a medical ontology as well as the entity-concept annotation axioms. 
This is a detailed documentation on how to use Onto2Vec to produce ontology-based feature vectors. The Onto2Vec implementation shared here uses GO ontology as a case study and can be directly used to produce feature vectors for GO functions and proteins. However, Onto2Vec can also be used to produce feature vectors for entities using any other biomedical ontology. We provide here instructions on how to use Onto2Vec with the ontology of your choice.
## Prerequisites
Onto2Vec implementation uses groovy, python and perl. No other programs are required to run it.
## OWL Processing 
The first step of Onto2Vec is to process the ontology using OWL API and infer new axioms using a reasoner. 
- Create a new directory and name it *Onto2Vec*.
- In this directory paste the file *OntoProcess.groovy*.
- If you want to use the GO ontology:
  - Open the terminal and run 
    ```
    groovy OntoProcess.groovy
    ```
  The script should create two files in your directory: *classes.lst*  which contains the set of classes in the ontology, and  *axioms.lst*  which contains all axioms from the ontology including the inferred ones.
 - If you want to use a different ontology:
    - Open the file *OntoProcess.groovy* in editor.
    - Edit the following line:
       ```
       IRI iri= IRI.create ("http://purl.obolibrary.org/obo/go.owl");
      ```
    by replacing the url with the link to the owl version of the ontology you would like to use.
   - Please note that the file uses the reasoner to automatically infer axioms of type *SubClass*, *Equivalent* and *Disjoint*. You may need to change that depending on the axioms available in your ontology and what type of axioms you would like to infer.
  
  
## Adding Annotation axioms
The second step of Onto2Vec is to add annotation axioms to our corpus of axioms. This can be easily done using some simple text mining code without using the OWL API sice there is no needed owl processing. 
- If you are using GO ontology you can add the annotation axioms by following these steps:
  - Download the GOA annotation file from ftp://ftp.ebi.ac.uk/pub/databases/GO/goa/UNIPROT/ and save it to the *Onto2Vec* directory. For copyright reasons, we do not include the annotation file in this package. 
  - Save the file *getAnnotations.pl* in the *Onto2Vec* directory.
  - Open the file *getAnnotations.pl* in editor.
  - Edit the following line:
    ```
    my $database="Enter Path Here";
    ```
      by replacing *Enter Path Here* with the path to the annotation file you downloaded.
  - Open the terminal and run
    ```
    perl getAnnotations.pl 
    ```
    This script should create a file called *annotationAxiom.lst* with all experimental annotation axioms using *hasFunction* as the relation between proteins and their functions . 
   - To make sure the annotation axioms are complete, we need to propagate the annotations up in the GO DAG.To do that save the file *AddAncestors.pl* to the *Onto2Vec* directory.
   - Open the terminal and run 
      ```
       perl AddAncestors.pl
      ```
    The file *annotationAxiom.lst* should now be complete and includes all the needed annotation axioms. 
    - At this point, the GO ontology axioms and the annotation axioms needs to be merged into one corpus. To do that, open the terminal in the *Onto2Vec* directory and run:
      ```
      cat axioms.lst annotationAxiom.lst > AllAxioms.lst
      ```
 - If you are using a different biomedical ontology:
   - If you do not need to add annotation axioms (Onto2Vec can work without annotation axioms)
      - Open the terminal in the *Onto2Vec* directory and run
      ```
      cat axioms.lst  > AllAxioms.lst
      ```
    - If you would like to include the annotation axioms, do the following:
      - Upload and save the annotation file corresponding to the ontology you are using in the *Onto2Vec* directory.
      - Open the file *getAnnotations.pl* in editor.
      - Edit the following line:
       ```
      my $database="Enter Path Here";
      ```
       by replacing *Enter Path Here* with the path to the annotation file you downloaded.
       - Edit the line :
       ```
       if ($line !~"\s+IEA\s+ " and $line !~"\s+ND\s+" )
       ```
       by replacing *IEA* and *ND* with the evidence codes you want to filter (if there are any). Or you can comment the if statement and get all the annotaions in the file.
       - Open the terminal and run
        ```
        perl getAnnotations.pl 
        ```
      The obtained file *annotationAxiom.lst* should have the annotation axioms you added.
        - To merge the ontology axioms with the annotation axioms in one corpus, open the terminal and run
         ```
          cat axioms.lst annotationAxiom.lst > AllAxioms.lst
         ```
  
## Representation Learning 
Since the axioms corpus is now ready, the only step left is to run Word2Vec to obtain the representation vectors in two steps:
- The first step is to obtain the entities we would like to get the feature vectors for. To get the vectors for all classes, open the terminal and run
  ```
  perl getClasses.pl
  ```
  This script will create a file ,*AllClasses.lst*, with the list of all classes in it. You can easily edit the perl file *getClasses.pl* to only keep the classes ou are interested in.
- The second and last step is to run Word2Vec on the corpus. To do that, open the terminal and run
  ```
  python runWord2vec.py
  ```
  This script will create a file with the vector representations of all classes in *AllClasses.lst*. This file, *VecResults.lst*, is the final and main output of Onto2Vec. 
## Data and Results
As an example we are including examples of input and output files of Onto2Vec using GO.
- **totalaxioms.lst** contains ontology structure axioms and annotation axioms from GO.
- **ProtClasses.lst** contains the list of proteins we were interested in getting vector representations for.  
- **VecResults.lst** contains vector representations for the proteins. 
## Final notes
For any comments or help needed with how to run Onto2Vec, please send an email to: fzohrasmaili@gmail.com
