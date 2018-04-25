package groovy.owl;

@Grapes ([

	  @Grab(group='org.semanticweb.elk', module='elk-owlapi', version='0.4.2'),
          @Grab(group='net.sourceforge.owlapi', module='owlapi-api', version='4.1.0'),
          @Grab(group='net.sourceforge.owlapi', module='owlapi-apibinding', version='4.1.0'),
          @Grab(group='net.sourceforge.owlapi', module='owlapi-impl', version='4.1.0'),
          @Grab(group='net.sourceforge.owlapi', module='owlapi-parsers', version='4.1.0'),
          @Grab(group='net.sourceforge.owlapi', module='org.semanticweb.hermit', version='1.3.8.413'),
	  @Grab(group ='net.sourceforge.owlapi',module='owlapi-osgidistribution',version='4.2.6'),

	  @GrabConfig(systemClassLoader=true)

		])

import org.coode.owlapi.manchesterowlsyntax.ManchesterOWLSyntaxOntologyFormat;
import org.coode.owlapi.turtle.TurtleOntologyFormat;
import org.junit.Ignore;
import org.junit.Test;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.io.OWLOntologyDocumentTarget;
import org.semanticweb.owlapi.io.OWLXMLOntologyFormat;
import org.semanticweb.owlapi.io.RDFXMLOntologyFormat;
import org.semanticweb.owlapi.io.StreamDocumentTarget;
import org.semanticweb.owlapi.io.StringDocumentTarget;
import org.semanticweb.owlapi.io.SystemOutDocumentTarget;
import org.semanticweb.owlapi.model.AddAxiom;
import org.semanticweb.owlapi.model.AddOntologyAnnotation;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLAnnotation;
import org.semanticweb.owlapi.model.OWLAnnotationProperty;
import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.*;
import org.semanticweb.owlapi.model.OWLClassAssertionAxiom;
import org.semanticweb.owlapi.model.OWLClassExpression;
import org.semanticweb.owlapi.model.OWLDataExactCardinality;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLDataProperty;
import org.semanticweb.owlapi.model.OWLDataPropertyAssertionAxiom;
import org.semanticweb.owlapi.model.OWLDataPropertyRangeAxiom;
import org.semanticweb.owlapi.model.OWLDataRange;
import org.semanticweb.owlapi.model.OWLDataSomeValuesFrom;
import org.semanticweb.owlapi.model.OWLDataUnionOf;
import org.semanticweb.owlapi.model.OWLDatatype;
import org.semanticweb.owlapi.model.OWLDatatypeDefinitionAxiom;
import org.semanticweb.owlapi.model.OWLDatatypeRestriction;
import org.semanticweb.owlapi.model.OWLDeclarationAxiom;
import org.semanticweb.owlapi.model.OWLDifferentIndividualsAxiom;
import org.semanticweb.owlapi.model.OWLDisjointClassesAxiom;
import org.semanticweb.owlapi.model.OWLEntity;
import org.semanticweb.owlapi.model.OWLEquivalentClassesAxiom;
import org.semanticweb.owlapi.model.OWLFacetRestriction;
import org.semanticweb.owlapi.model.OWLFunctionalDataPropertyAxiom;
import org.semanticweb.owlapi.model.OWLIndividual;
import org.semanticweb.owlapi.model.OWLLiteral;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLObjectAllValuesFrom;
import org.semanticweb.owlapi.model.OWLObjectExactCardinality;
import org.semanticweb.owlapi.model.OWLObjectHasValue;
import org.semanticweb.owlapi.model.OWLObjectIntersectionOf;
import org.semanticweb.owlapi.model.OWLObjectOneOf;
import org.semanticweb.owlapi.model.OWLObjectProperty;
import org.semanticweb.owlapi.model.OWLObjectPropertyAssertionAxiom;
import org.semanticweb.owlapi.model.OWLObjectPropertyExpression;
import org.semanticweb.owlapi.model.OWLObjectSomeValuesFrom;
import org.semanticweb.owlapi.io.OWLObjectRenderer;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyFormat;
import org.semanticweb.owlapi.model.OWLOntologyID;
import org.semanticweb.owlapi.model.OWLOntologyIRIMapper;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;
import org.semanticweb.owlapi.model.OWLSubClassOfAxiom;
import org.semanticweb.owlapi.model.OWLSubObjectPropertyOfAxiom;
import org.semanticweb.owlapi.model.PrefixManager;
import org.semanticweb.owlapi.model.SWRLAtom;
import org.semanticweb.owlapi.model.SWRLObjectPropertyAtom;
import org.semanticweb.owlapi.model.SWRLRule;
import org.semanticweb.owlapi.model.SWRLVariable;
import org.semanticweb.owlapi.model.SetOntologyID;
import org.semanticweb.owlapi.reasoner.BufferingMode;
import org.semanticweb.owlapi.reasoner.ConsoleProgressMonitor;
import org.semanticweb.owlapi.reasoner.InferenceType;
import org.semanticweb.owlapi.reasoner.Node;
import org.semanticweb.owlapi.reasoner.NodeSet;
import org.semanticweb.owlapi.reasoner.OWLReasoner;
import org.semanticweb.owlapi.reasoner.OWLReasonerConfiguration;
import org.semanticweb.owlapi.reasoner.OWLReasonerFactory;
import org.semanticweb.owlapi.reasoner.SimpleConfiguration;
import org.semanticweb.owlapi.reasoner.structural.StructuralReasoner;
import org.semanticweb.owlapi.reasoner.structural.StructuralReasonerFactory;
import org.semanticweb.owlapi.util.AutoIRIMapper;
import org.semanticweb.owlapi.util.DefaultPrefixManager;
import org.semanticweb.owlapi.util.InferredAxiomGenerator;
import org.semanticweb.owlapi.util.InferredOntologyGenerator;
import org.semanticweb.owlapi.util.InferredSubClassAxiomGenerator;
import org.semanticweb.owlapi.util.InferredDisjointClassesAxiomGenerator;
import org.semanticweb.owlapi.util.InferredEquivalentClassAxiomGenerator;
import org.semanticweb.owlapi.util.InferredClassAxiomGenerator;
import org.semanticweb.owlapi.util.InferredPropertyAssertionGenerator;
import org.semanticweb.owlapi.util.OWLClassExpressionVisitorAdapter;
import org.semanticweb.owlapi.util.OWLEntityRemover;
import org.semanticweb.owlapi.util.OWLOntologyMerger;
import org.semanticweb.owlapi.util.OWLOntologyWalker;
import org.semanticweb.owlapi.util.OWLOntologyWalkerVisitor;
import org.semanticweb.owlapi.util.SimpleIRIMapper;
import org.semanticweb.owlapi.vocab.OWL2Datatype;
import org.semanticweb.owlapi.vocab.OWLFacet;
import org.semanticweb.owlapi.vocab.OWLRDFVocabulary;
import org.semanticweb.HermiT.Reasoner;

import uk.ac.manchester.cs.owlapi.modularity.ModuleType;
import uk.ac.manchester.cs.owlapi.modularity.SyntacticLocalityModuleExtractor;
import org.semanticweb.owlapi.manchestersyntax.renderer.*;
import java.io.*;
import java.io.PrintWriter;

println ("Hello! This is the first stage of Onto2Vec!");
// String geneonto_IRI = "http://purl.obolibrary.org/obo/go.owl";
OWLOntologyManager manager= OWLManager.createOWLOntologyManager ();
IRI iri= IRI.create ("http://purl.obolibrary.org/obo/go.owl");
OWLOntology GOOntology =manager.loadOntologyFromOntologyDocument (iri);
System.out.println ("Loaded ontology:" + GOOntology);
System.out.printf("%n ");
System.out.printf("*********Reasoner starts here **********%n");
OWLReasonerFactory reasonerFactory=new Reasoner.ReasonerFactory();
OWLReasoner reasoner= reasonerFactory.createReasoner(GOOntology);
OWLDataFactory factory=manager.getOWLDataFactory()
reasoner.precomputeInferences();
System.out.println ("Got here !");
boolean consistent = reasoner.isConsistent() ;
println( "consistent: "+ consistent);
println("Get number of axioms after inference %n ");
int numaxiom= GOOntology.getAxiomCount();
println ("Number of axioms is : "+ numaxiom);
OWLObjectRenderer renderer =new ManchesterOWLSyntaxOWLObjectRendererImpl ();
//Infer subclass/equivalent/disjoint axioms using reasoner
InferredSubClassAxiomGenerator generator = new InferredSubClassAxiomGenerator();
//InferredDisjointClassesAxiomGenerator generatordis = new  InferredDisjointClassesAxiomGenerator();
//InferredEquivalentClassAxiomGenerator generatequi = new  InferredEquivalentClassAxiomGenerator();
Set<OWLAxiom> axioms = generator.createAxioms(factory, reasoner);
//Set<OWLAxiom> dis_axioms=generatordis.createAxioms(factory, reasoner);
//Set<OWLAxiom> equ_axioms=generatequi.createAxioms(factory,reasoner);
manager.addAxioms(GOOntology,axioms);
//manager.addAxioms(GOOntology,dis_axioms);
//manager.addAxioms(GOOntology,equ_axioms);
int numaxiom1= GOOntology.getAxiomCount();
println ("Number of axioms is : "+ numaxiom1);

//Iterate through all classes of ontology
Set<OWLClass> classes=GOOntology.getClassesInSignature();
FileWriter fw= new FileWriter ("axioms.lst",true); BufferedWriter bw =new BufferedWriter (fw); PrintWriter out =new PrintWriter (bw);
FileWriter fw1= new FileWriter ("classes.lst",true); BufferedWriter bw1 =new BufferedWriter (fw1); PrintWriter out1 =new PrintWriter (bw1);
for (OWLClass class1 : classes)
{
   Set<OWLClassAxiom> ontoaxioms=GOOntology.getAxioms (class1);
   for (OWLClassAxiom claxiom: ontoaxioms)
    {
    classess=renderer.render(class1);
    classaxiom=renderer.render (claxiom);
    out1.println (classess);
    out.println (classaxiom);
    }
}

//BufferedReader br = new BufferedReader(new FileReader("ProtClasses.lst")) ;
  //  String line;
    //while ((line = br.readLine()) != null) {
	//String newclass=line.trim ();// process the line.
	//OWLClass c=factory.getOWLClass(IRI.create(newclass));
	//OWLAxiom declarec=factory.getOWLdeclaratiomAxiom(c);
	//manager.addAxiom(GOOntology,declarec);
    //}
