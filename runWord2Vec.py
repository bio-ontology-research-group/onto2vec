import gensim
import gensim.models
import os
class MySentences(object):
    def __init__(self, dirname):
        self.dirname = dirname

    def __iter__(self):
        for line in open('AllAxioms.lst'):
            yield line.split()

sentences =gensim.models.word2vec.LineSentence('AllAxioms.lst') # a memory-friendly iterator
ssmodel =gensim.models.Word2Vec(sentences,min_count=0, size=200, window=10, sg=1, negative=4, iter=5)
#Store vector of each  class
GOvectors={}
word_vectors=ssmodel.wv
file= open ('VecResults.lst', 'w')
with open('AllClasses.lst') as f:
    for line in f:
        GO_class=line.rstrip()
        if GO_class in word_vectors.vocab:
            GOvectors[GO_class]=ssmodel[GO_class];
            file.write (str(GO_class) + ' '+ str(GOvectors[GO_class]) +'\n')
file.close()
