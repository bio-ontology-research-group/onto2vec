% ********************** Plotting Yeast results *************************
figure
subplot (2,2,1)
FResnik= resnik(:,1);
TResnik=resnik(:,2);
% plot(RMFGO,PMFGO,'LineWidth',2.5); hold on

Fonto2vec=onto2vec(:,1);
Tonto2vec=onto2vec(:,2);
% plot (RMFPPI, PMFPPI, 'LineWidth', 2.5); hold on

Fnoreasn=onto2vec_noReasn(:,1);
Tnoreasn=onto2vec_noReasn(:,2);
% plot (RMFMOT, PMFMOT, 'LineWidth', 2.5); hold on

FAddvec=Onto_AddVec(:,1);
TAddvec=Onto_AddVec(:,2);
% plot (RMFNAI, PMFNAI,  'LineWidth',2.5); hold on

FBMA=Onto_BMA(:,1);
TBMA=Onto_BMA(:,2);
% plot (RMFLOM, PMFLOM,  'LineWidth', 2.5); hold on

FBG=BinaryGo (:,1);% ********************** Plotting Yeast results *************************
figure
subplot (2,2,1)
FResnik= resnik(:,1);
TResnik=resnik(:,2);
% plot(RMFGO,PMFGO,'LineWidth',2.5); hold on

Fonto2vec=onto2vec(:,1);
Tonto2vec=onto2vec(:,2);
% plot (RMFPPI, PMFPPI, 'LineWidth', 2.5); hold on

Fnoreasn=onto2vec_noReasn(:,1);
Tnoreasn=onto2vec_noReasn(:,2);
% plot (RMFMOT, PMFMOT, 'LineWidth', 2.5); hold on

FAddvec=Onto_AddVec(:,1);
TAddvec=Onto_AddVec(:,2);
% plot (RMFNAI, PMFNAI,  'LineWidth',2.5); hold on

FBMA=Onto_BMA(:,1);
TBMA=Onto_BMA(:,2);
% plot (RMFLOM, PMFLOM,  'LineWidth', 2.5); hold on

FBG=BinaryGo (:,1);
TBG=BinaryGo(:,2);
% plot (RMFHHS, PMFHHS,'LineWidth',2.5); hold on
% 
% RMFING=MFING (:,1);
% PMFING=MFING (:,2);
% % plot (RMFING, PMFING , 'LineWidth', 2.5);hold on;
% 
% RMFBLAS= MFBLAS (:,1);
% PMFBLAS= MFBLAS (:,2);
plot (Fonto2vec, Tonto2vec, FResnik,TResnik,Fnoreasn,Tnoreasn,  FAddvec, TAddvec,FBMA, TBMA, FBG, TBG, 'LineWidth', 1.5);
grid on;
xlabel('False Positive Rate')
ylabel('True Positive Rate')
xlim([0 1])
ylim([0 1])
title ('Yeast')
% ********************** Plotting Human results *************************

subplot (2,2,2)
hFResnik= hresnik(:,1);
hTResnik=hresnik(:,2);
% plot(RMFGO,PMFGO,'LineWidth',2.5); hold on

hFonto2vec=honto2vec(:,1);
hTonto2vec=honto2vec(:,2);
% plot (RMFPPI, PMFPPI, 'LineWidth', 2.5); hold on

hFnoreasn=honto2vec_noReasn(:,1);
hTnoreasn=honto2vec_noReasn(:,2);
% plot (RMFMOT, PMFMOT, 'LineWidth', 2.5); hold on

hFAddvec=hAdd_Vec(:,1);
hTAddvec=hAdd_Vec(:,2);
% plot (RMFNAI, PMFNAI,  'LineWidth',2.5); hold on

hFBMA=hOnto_BMA(:,1);
hTBMA=hOnto_BMA(:,2);
% plot (RMFLOM, PMFLOM,  'LineWidth', 2.5); hold on

hFBG=hBinaryGO (:,1);
hTBG=hBinaryGO(:,2);

plot ( hFonto2vec, hTonto2vec,hFResnik,hTResnik,hFnoreasn,hTnoreasn,  hFAddvec, hTAddvec,hFBMA, hTBMA, hFBG, hTBG, 'LineWidth', 1.5);

grid on;
xlabel('False Postive Rate')
ylabel('True Positive Rate')
xlim([0 1])
ylim([0 1])
title ('Human')
% % ********************** Plotting BP results *************************

hsub= subplot (2,2,3);
plot (1,1,1,1,1,1,1,1,1,1,1,1,1,1,'LineWidth',1.5 );
axis off;
hlegend=legend ('Onto2Vec','Resnik',  'Onto2Vec\_NoReasoner', 'Onto\_AddVec','Onto\_BMA', 'BinaryGO');
set (hlegend, 'position', get(hsub, 'position'));
set (gcf, 'color','w');
set(gca,'Color','w')
whitebg('white')
%  set(gcf, 'Position',' [pos(1) pos(2) width*200, height*200]'); %<- Set size
 set (gcf, 'color','w');
% set(gca, 'FontSize', fsz, 'LineWidth', alw); %<- Set properties
 print('/home/smailif/Documents/PROT2VEC/unsupervised3','-dpng','-r300');
