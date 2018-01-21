# onto2vec
## Introduction
Onto2Vec is a program that can be used to produce feature vectors for biological entities based on their annotations to biomedical ontologies. Onto2Vec uses the integrity of the axioms describing the structure of a medical ontology as well as the entity-concept annotation axioms. 
This is a detailed documentation on how to use Onto2Vec to produce ontology-based feature vectors. The Onto2Vec implementation shared here uses GO ontology as a case study and can be directly used to produce feature vectors for GO functions and proteins. However, Onto2Vec can also be used to produce feature vectors for entities using any other biomedical ontology. We provide here instructions on how to use Onto2Vec with the ontology of your choice.
## Prerequisites
Onto2Vec implementation uses groovy, python and perl. No other programs are required to run it.
## OWL Processing 
The first step of Onto2Vec is to process the ontolofy using OWL API and infer new axioms using a reasoner. 
- Create a new directory and name it *Onto2Vec*.
- In this directory paste the file *OntoProcess.groovy*
- If you want to use the GO ontology:
  - open terminal and run 
  ```
  groovy OntoProcess.groovy
  ```
  The script should create two file in your directory: *classes.lst* which contains the set of classes in the ontology and  *axioms.lst* which contains all axioms from the ontology as well as the inferred ones
 - If you want to use a different ontology:
  - open the file *OntoProcess.groovy* in editor
  - in line 
  ```
  IRI iri= IRI.create ("http://purl.obolibrary.org/obo/go.owl");
  ```
  replace the url with the link to owl version of the ontology you would like to use.
  
## Adding Annotation axioms 
## Representation Learning 
## Data and Results
