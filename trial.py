import gensim
import gensim.models
import os
class MySentences(object):
    def __init__(self, dirname):
        self.dirname = dirname
 
    def __iter__(self):       
	for line in open('/home/smailif/Documents/PROT2VEC/totalaxioms.lst'):
                yield line.split()
 
sentences =gensim.models.word2vec.LineSentence('/home/smailif/Documents/PROT2VEC/totalaxioms.lst') # a memory-friendly iterator
ssmodel =gensim.models.Word2Vec(sentences,min_count=0, size=200)
#Store vector of each GO class
GOvectors={}
word_vectors=ssmodel.wv
file= open ('newvecres.lst', 'w')
with open('ProtClasses.lst') as f:
	for line in f:
		GO_class=line.rstrip()
		if GO_class in word_vectors.vocab:		
			GOvectors[GO_class]=ssmodel[GO_class];
			file.write (str(GO_class) + ' '+ str(GOvectors[GO_class]) +'\n')
file.close()

