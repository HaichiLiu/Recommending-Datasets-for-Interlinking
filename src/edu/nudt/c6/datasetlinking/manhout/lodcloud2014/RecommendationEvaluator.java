package edu.nudt.c6.datasetlinking.mahout.lodcloud2014;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.common.Weighting;
import org.apache.mahout.cf.taste.eval.DataModelBuilder;
import org.apache.mahout.cf.taste.eval.IRStatistics;
import org.apache.mahout.cf.taste.eval.RecommenderBuilder;
import org.apache.mahout.cf.taste.eval.RecommenderEvaluator;
import org.apache.mahout.cf.taste.eval.RecommenderIRStatsEvaluator;
import org.apache.mahout.cf.taste.impl.common.LongPrimitiveIterator;
import org.apache.mahout.cf.taste.impl.eval.AverageAbsoluteDifferenceRecommenderEvaluator;
import org.apache.mahout.cf.taste.impl.eval.GenericRecommenderIRStatsEvaluator;
import org.apache.mahout.cf.taste.impl.eval.RMSRecommenderEvaluator;
import org.apache.mahout.cf.taste.impl.recommender.svd.Factorizer;
import org.apache.mahout.cf.taste.impl.similarity.CityBlockSimilarity;
import org.apache.mahout.cf.taste.impl.similarity.EuclideanDistanceSimilarity;
import org.apache.mahout.cf.taste.impl.similarity.LogLikelihoodSimilarity;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.impl.similarity.SpearmanCorrelationSimilarity;
import org.apache.mahout.cf.taste.impl.similarity.TanimotoCoefficientSimilarity;
import org.apache.mahout.cf.taste.impl.similarity.UncenteredCosineSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.similarity.ItemSimilarity;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;

import edu.nudt.c6.datasetlinking.lodcloud2014.CONSTANT;
import edu.nudt.c6.datasetlinking.lodcloud2014.svdfeature.SVDFeatureRecommender;
import edu.nudt.c6.datasetlinking.lodcloud2014.svdfeature.SVDFeatureRecommender.MODEL;
import edu.nudt.c6.datasetlinking.mahout.MyRecommenderBuilder;
import edu.nudt.c6.datasetlinking.mahout.RecommendFactory;
import edu.nudt.c6.datasetlinking.mahout.MyRecommenderBuilder.CRFACTORIZER;
import edu.nudt.c6.datasetlinking.mahout.MyRecommenderBuilder.SVDFACTORIZER;
import edu.nudt.c6.datasetlinking.mahout.RecommendFactory.RECOMMENDER;
import edu.nudt.c6.datasetlinking.mahout.lodcloud2014.MyRecommenderIRStatsEvaluator;
import edu.nudt.c6.datasetlinking.mahout.lodcloud2014.datasetSimilarity.DatasetClassSimilarity;
import edu.nudt.c6.datasetlinking.mahout.lodcloud2014.datasetSimilarity.DatasetPropertySubjectsSimilarity;
import edu.nudt.c6.datasetlinking.mahout.lodcloud2014.datasetSimilarity.DatasetPropertyTriplesSimilarity;
import edu.nudt.c6.datasetlinking.mahout.lodcloud2014.datasetSimilarity.DatasetVocabularySimilarity;

public class RecommendationEvaluator {
	
	public static int evalTimes = 10;
	
	public static double usingPercent = 1.0;

	public static void main(String[] args) throws TasteException, IOException, ClassNotFoundException {
		

	
//		evaluateLinkDocumentBasedTopXXX("1sameAs");
//		
//		evaluateLinkDocumentBasedTopXXX("2sameAs+self");
		


//		evaluateMySVDTopXXX("1sameAs",false, SVDFACTORIZER.MyRatingSGD, 10, 100, 0.01);
		
	
		
//		evaluateCollaborativeRankingTopXXX("sameAs", CRFACTORIZER.BasicLFM, 20, 40);
//		evaluateCollaborativeRankingTopXXX("sameAs", CRFACTORIZER.BasicLFM, 30, 5, 0.01);
		
//		evaluateCollaborativeRankingTopXXX("sameAs", CRFACTORIZER.LFMTrans, 20, 40, 0.01);
					

//		evaluateRandomTopXXX(true, "datamodel_exist_self");
		
//		evaluateItemBasedTopXXX(true, RecommendFactory.SIMILARITY.DATASET_VOCABULARY_COSINE, "datamodel_exist_self");	
//		evaluateItemBasedTopXXX(true, RecommendFactory.SIMILARITY.DATASET_CLASS_COSINE, "datamodel_exist_self");
//		evaluateItemBasedTopXXX(true, RecommendFactory.SIMILARITY.DATASET_PPROPERTY_COSINE_SUBJECTS, "datamodel_exist_self");
//		evaluateItemBasedTopXXX(true, RecommendFactory.SIMILARITY.DATASET_PPROPERTY_COSINE_TRIPLES, "datamodel_exist_self");
//
//		evaluateItemBasedTopXXX(true, RecommendFactory.SIMILARITY.COSINE, "datamodel_exist_self");
//		evaluateItemBasedTopXXX(true, RecommendFactory.SIMILARITY.PEARSON, "datamodel_exist_self");
		
//		evaluateUserBasedTopXXXThresholdXXX("datamodel_exist_self", true, RecommendFactory.SIMILARITY.COSINE);
//		evaluateUserBasedTopXXXThresholdXXX("datamodel_exist_self", true, RecommendFactory.SIMILARITY.PEARSON);
//		evaluateUserBasedTopXXXNearestXXX("datamodel_exist_self", true,RecommendFactory.SIMILARITY.COSINE);
//		evaluateUserBasedTopXXXNearestXXX("datamodel_exist_self", true,RecommendFactory.SIMILARITY.PEARSON);
		
//		evalTimes = 2;
//		for (int f = 10; f <= 50; f += 10) {
//			for (int i = 10; i <= 100; i += 10) {
//				evaluateSVDTopXXX("datamodel_exist_self", true, SVDFACTORIZER.RatingSGD, f, i, 0.01);
////				evaluateSVDTopXXX("datamodel_exist_self", true, SVDFACTORIZER.SVDPlusPlus, f, i, 0.01);
//			}
//		}

//		evalTimes = 10;
//		double lamda = 0.01;
//		for (int f = 10; f <= 50; f += 10) {
//			for (int i = 10; i <= 100; i += 10) {
////				evaluateSVDPrefDiffPercentXXX("datamodel_exist_self", SVDFACTORIZER.ALSWR, f, i, lamda);
//				evaluateSVDPrefDiffPercentXXX("datamodel_exist_self", SVDFACTORIZER.SVDPlusPlus, f, i, lamda);
//			}
//		}
		
//		evaluateSVDPrefDiffPercentXXX("datamodel_exist_self", SVDFACTORIZER.RatingSGD, 20, 50, 0.01);
		
		
//		evaluateUserBasedTopXXXThresholdXXX("datamodel_exist_self", true, RecommendFactory.SIMILARITY.DATASET_VOCABULARY_COSINE);
//		evaluateUserBasedTopXXXNearestXXX("datamodel_exist_self", true,RecommendFactory.SIMILARITY.DATASET_VOCABULARY_COSINE);	
//		evaluateUserBasedPrefDiffPercentXXXNearestXXX("datamodel_exist_self", RecommendFactory.SIMILARITY.DATASET_VOCABULARY_COSINE);
//		evaluateUserBasedPrefDiffPercentXXXThresholdXXX("datamodel_exist_self", RecommendFactory.SIMILARITY.DATASET_VOCABULARY_COSINE);
		
		evaluateUserBasedTopXXXThresholdXXX("datamodel_exist_self", true, RecommendFactory.SIMILARITY.DATASET_CLASS_COSINE);
		evaluateUserBasedTopXXXNearestXXX("datamodel_exist_self", true,RecommendFactory.SIMILARITY.DATASET_CLASS_COSINE);	
		evaluateUserBasedPrefDiffPercentXXXNearestXXX("datamodel_exist_self", RecommendFactory.SIMILARITY.DATASET_CLASS_COSINE);
		evaluateUserBasedPrefDiffPercentXXXThresholdXXX("datamodel_exist_self", RecommendFactory.SIMILARITY.DATASET_CLASS_COSINE);
		
		evaluateUserBasedTopXXXThresholdXXX("datamodel_exist_self", true, RecommendFactory.SIMILARITY.DATASET_PPROPERTY_COSINE_SUBJECTS);
		evaluateUserBasedTopXXXNearestXXX("datamodel_exist_self", true,RecommendFactory.SIMILARITY.DATASET_PPROPERTY_COSINE_SUBJECTS);	
		evaluateUserBasedPrefDiffPercentXXXNearestXXX("datamodel_exist_self", RecommendFactory.SIMILARITY.DATASET_PPROPERTY_COSINE_SUBJECTS);
		evaluateUserBasedPrefDiffPercentXXXThresholdXXX("datamodel_exist_self", RecommendFactory.SIMILARITY.DATASET_PPROPERTY_COSINE_SUBJECTS);
		
		evaluateUserBasedTopXXXThresholdXXX("datamodel_exist_self", true, RecommendFactory.SIMILARITY.DATASET_PPROPERTY_COSINE_TRIPLES);
		evaluateUserBasedTopXXXNearestXXX("datamodel_exist_self", true,RecommendFactory.SIMILARITY.DATASET_PPROPERTY_COSINE_TRIPLES);	
		evaluateUserBasedPrefDiffPercentXXXNearestXXX("datamodel_exist_self", RecommendFactory.SIMILARITY.DATASET_PPROPERTY_COSINE_TRIPLES);
		evaluateUserBasedPrefDiffPercentXXXThresholdXXX("datamodel_exist_self", RecommendFactory.SIMILARITY.DATASET_PPROPERTY_COSINE_TRIPLES);
		
	}
	
	public static void evaluateBaselineTopXXX(boolean pref, String datamodelFile, RECOMMENDER recommenderType) throws TasteException, IOException, ClassNotFoundException {
		
		String file = CONSTANT.DATAMODEL_LOCATION + datamodelFile + ".txt";
		DataModel dataModel = null;
		if (pref) {
			dataModel = RecommendFactory.buildDataModel(file);
		} else {
			dataModel = RecommendFactory.buildDataModelNoPref(file);
		}
			
		MyRecommenderBuilder recommenderBuilder = new MyRecommenderBuilder();
		recommenderBuilder.setRecommender(recommenderType);
		
	  	RecommenderIRStatsEvaluator irStatsEvaluator = new GenericRecommenderIRStatsEvaluator();  	
	  
	  	BufferedWriter bw = new BufferedWriter(new FileWriter("test//lodcloud2014 mahout//baseline " + pref + " " + recommenderType + " topXXX " + datamodelFile + ".txt"));
	  	bw.write("topN	Precision	Recall	F1Measure	FallOut	Reach	NormalizedDiscountedCumulativeGain" + "\n");
	
	  	for (int topn = 1; topn <= 10; topn++) {
		    	IRStatistics stats = null;
		    	if (pref) {
		    		stats = irStatsEvaluator.evaluate(
		    		recommenderBuilder, null, dataModel, null, topn, 1, 1.0);
		    		
		    	} else {
		    		DataModelBuilder dataModelBuilder = RecommendFactory.buildDataModelNoPrefBuilder();
		    		stats = irStatsEvaluator.evaluate(
		    		recommenderBuilder, dataModelBuilder, dataModel, null, topn, 1, 1.0);						
		    	}
	
		    	bw.write("top" + topn + "	" + stats.getPrecision() + "	" + stats.getRecall() + "	" + stats.getF1Measure());
		    	bw.write("	" + stats.getFallOut() + "	" + stats.getReach() +"	" + stats.getNormalizedDiscountedCumulativeGain() + "\n");	    
	    					        		
	    	}
	    
	    bw.flush();
	    bw.close();
	
	}
	
	public static void evaluateRandomTopXXX(boolean pref, String datamodelFile) throws TasteException, IOException, ClassNotFoundException {
		
		String file = CONSTANT.DATAMODEL_LOCATION + datamodelFile + ".txt";
		DataModel dataModel = null;
		if (pref) {
			dataModel = RecommendFactory.buildDataModel(file);
		} else {
			dataModel = RecommendFactory.buildDataModelNoPref(file);
		}
			
		MyRecommenderBuilder recommenderBuilder = new MyRecommenderBuilder();
		
		recommenderBuilder.setRecommender(RECOMMENDER.Random);
		
	  	RecommenderIRStatsEvaluator irStatsEvaluator = new GenericRecommenderIRStatsEvaluator();  	
	  
	  	BufferedWriter bw = new BufferedWriter(new FileWriter("test//lodcloud2014 mahout//baseline " + pref + " " + RECOMMENDER.Random + " topXXX " + datamodelFile + ".txt"));
	  	bw.write("topN	Precision	Recall	F1Measure	FallOut	Reach	NormalizedDiscountedCumulativeGain" + "\n");
	
	  	for (int topn = 1; topn <= 10; topn++) {
		  		double precision = 0;
	    		double recall = 0;
	    		double f1 = 0;
	    		double fallOut = 0;
	    		double reach = 0;
	    		double NDCG = 0;
	    		for (int i = 0; i < evalTimes; i++) {
	    			
	    			IRStatistics stats = null;
	    			if (pref) {
	    					stats = irStatsEvaluator.evaluate(
	    				recommenderBuilder, null, dataModel, null, topn, 1, 1.0);
	    		    		
	    			} else {
	    				DataModelBuilder dataModelBuilder = RecommendFactory.buildDataModelNoPrefBuilder();
	    				stats = irStatsEvaluator.evaluate(
	    				recommenderBuilder, dataModelBuilder, dataModel, null, topn, 1, 1.0);
	    			}
	    			
	    			bw.write("run" + i + "	"+ stats.getPrecision() + "	" + stats.getRecall() + "	" + stats.getF1Measure());
	    			bw.write("	" + stats.getFallOut() + "	" + stats.getReach() +"	" + stats.getNormalizedDiscountedCumulativeGain() + "\n");
	    			bw.flush();
	    			
	    			precision += stats.getPrecision();
	    			recall += stats.getRecall();
	    			f1 += stats.getF1Measure();
	    			fallOut += stats.getFallOut();
	    			reach += stats.getReach();
	    			NDCG += stats.getNormalizedDiscountedCumulativeGain();
	    
	    		}
	    		bw.write("top" + topn + "	"+ precision/evalTimes + "	" + recall/evalTimes + "	" + f1/evalTimes);
			bw.write("	" + fallOut/evalTimes + "	" + reach/evalTimes +"	" + NDCG/evalTimes + "\n");	    
			bw.flush();    		
	    	}
	    
	    bw.flush();
	    bw.close();
	
	}
	
	
	public static void evaluateItemBasedTopXXX(boolean pref, RecommendFactory.SIMILARITY similarityType, String datamodelFile) throws TasteException, IOException, ClassNotFoundException {
		
		String file = CONSTANT.DATAMODEL_LOCATION + datamodelFile + ".txt";
		DataModel dataModel = null;
		if (pref) {
			dataModel = RecommendFactory.buildDataModel(file);
		} else {
			dataModel = RecommendFactory.buildDataModelNoPref(file);
		}
			
		MyRecommenderBuilder recommenderBuilder = new MyRecommenderBuilder();
		
		recommenderBuilder.setRecommender(RECOMMENDER.ITEM);
		recommenderBuilder.setPref(pref);
		
		recommenderBuilder.setSimilarity(similarityType);
		
		
	  	RecommenderIRStatsEvaluator irStatsEvaluator = new GenericRecommenderIRStatsEvaluator();
	  	//

	  	BufferedWriter bw = new BufferedWriter(new FileWriter("test//lodcloud2014 mahout//item-based " + pref + " " + similarityType + " topXXX " + datamodelFile + ".txt"));
	  	bw.write("topN	Precision	Recall	F1Measure	FallOut	Reach	NormalizedDiscountedCumulativeGain" + "\n");
	
	   for (int topn = 1; topn <= 10; topn++) {
	    		IRStatistics stats = null;
		    	if (pref) {
		    		stats = irStatsEvaluator.evaluate(
		    		recommenderBuilder, null, dataModel, null, topn, 1, 1.0);
		    		
		    	} else {
		    		DataModelBuilder dataModelBuilder = RecommendFactory.buildDataModelNoPrefBuilder();
		    		stats = irStatsEvaluator.evaluate(
		    		recommenderBuilder, dataModelBuilder, dataModel, null, topn, 1, 1.0);						
		    	}
	
		    	bw.write("top" + topn + "	" + stats.getPrecision() + "	" + stats.getRecall() + "	" + stats.getF1Measure());
		    	bw.write("	" + stats.getFallOut() + "	" + stats.getReach() +"	" + stats.getNormalizedDiscountedCumulativeGain() + "\n");	    
	    					        		
	    	}
	    
	    bw.flush();
	    bw.close();
	
	}
	
	public static void evaluateUserBasedTopXXXNearestXXX(String datamodelFile, boolean pref, RecommendFactory.SIMILARITY similarityType) throws TasteException, IOException {
		
		String file = CONSTANT.DATAMODEL_LOCATION + datamodelFile + ".txt";
		DataModel dataModel = null;
		if (pref) {
			dataModel = RecommendFactory.buildDataModel(file);
		} else {
			dataModel = RecommendFactory.buildDataModelNoPref(file);
		}
		
		RecommenderIRStatsEvaluator irStatsEvaluator = new GenericRecommenderIRStatsEvaluator();
		  
		MyRecommenderBuilder recommenderBuilder = new MyRecommenderBuilder();
		recommenderBuilder.setRecommender(RECOMMENDER.USER);
		recommenderBuilder.setPref(pref);
		recommenderBuilder.setSimilarity(similarityType);
		recommenderBuilder.setNeighborhood(RecommendFactory.NEIGHBORHOOD.NEAREST);
		
		BufferedWriter bw = new BufferedWriter(new FileWriter("test//lodcloud2014 mahout//user-based " + pref + " "+ similarityType + " topXXX neighborXXX " + datamodelFile + ".txt"));
	  	 	
	  	bw.write("topN	neighbor	Precision	Recall	F1Measure	FallOut	Reach	NormalizedDiscountedCumulativeGain" + "\n");
	   	
	  	
	  	for (int topn = 1; topn <= 10; topn++) {
			for (int neighbor = 1; neighbor <= 10;  neighbor += 1) {
				
				recommenderBuilder.setNearestNum(neighbor);	
				
					IRStatistics stats = null;
		    		if (pref) {
		    			stats = irStatsEvaluator.evaluate(
					recommenderBuilder, null, dataModel, null, topn, 1, 1.0);
				    		
		    		} else {
		    			DataModelBuilder dataModelBuilder = RecommendFactory.buildDataModelNoPrefBuilder();
					stats = irStatsEvaluator.evaluate(
					recommenderBuilder, dataModelBuilder, dataModel, null, topn, 1, 1.0);
						
		    		}
			    		
					
				bw.write("top" + topn + "	neighbor" + neighbor + "	"+ stats.getPrecision() + "	" + stats.getRecall() + "	" + stats.getF1Measure());
				bw.write("	" + stats.getFallOut() + "	" + stats.getReach() +"	" + stats.getNormalizedDiscountedCumulativeGain() + "\n");
			}
		}
		
		

	    bw.flush();
	    bw.close();
	}
	
	
	public static void evaluateUserBasedTopXXXThresholdXXX(String datamodelFile, boolean pref, RecommendFactory.SIMILARITY similarityType) throws TasteException, IOException {
		
		String file = CONSTANT.DATAMODEL_LOCATION + datamodelFile + ".txt";
		DataModel dataModel = null;
		if (pref) {
			dataModel = RecommendFactory.buildDataModel(file);
		} else {
			dataModel = RecommendFactory.buildDataModelNoPref(file);
		}
		
		RecommenderIRStatsEvaluator irStatsEvaluator = new GenericRecommenderIRStatsEvaluator();
		  
		MyRecommenderBuilder recommenderBuilder = new MyRecommenderBuilder();
		recommenderBuilder.setRecommender(RECOMMENDER.USER);
		recommenderBuilder.setPref(pref);
		recommenderBuilder.setSimilarity(similarityType);
		recommenderBuilder.setNeighborhood(RecommendFactory.NEIGHBORHOOD.THRESHOLD);
		
		
		BufferedWriter bw = new BufferedWriter(new FileWriter("test//lodcloud2014 mahout//user-based " + pref + " "+ similarityType + " topXXX thresholdXXX " + datamodelFile + ".txt"));
	  	 	
	  	bw.write("topN	threshold	Precision	Recall	F1Measure	FallOut	Reach	NormalizedDiscountedCumulativeGain" + "\n");
	   	
	  	
	  	for (int topn = 1; topn <= 10; topn++) {
			
			for (double threshold = 50; threshold <= 90;  threshold += 10) {
				
				recommenderBuilder.setNeighborThreshold(threshold/100);	
				
					IRStatistics stats = null;
		    		if (pref) {
		    			stats = irStatsEvaluator.evaluate(
					recommenderBuilder, null, dataModel, null, topn, 1, 1.0);
				    		
		    		} else {
		    			DataModelBuilder dataModelBuilder = RecommendFactory.buildDataModelNoPrefBuilder();
					stats = irStatsEvaluator.evaluate(
					recommenderBuilder, dataModelBuilder, dataModel, null, topn, 1, 1.0);
						
		    		}
			    		
						
				bw.write("top" + topn + "	threshold" + threshold + "	"+ stats.getPrecision() + "	" + stats.getRecall() + "	" + stats.getF1Measure());
				bw.write("	" + stats.getFallOut() + "	" + stats.getReach() +"	" + stats.getNormalizedDiscountedCumulativeGain() + "\n");
			}
		}
		
	    bw.flush();
	    bw.close();
	}

	public static void evaluateSVDTopXXX(String datamodelFile, boolean pref, SVDFACTORIZER factorizerType, int factorNum, int iterationNum, double lambda) throws TasteException, IOException {
	
	
		String file = CONSTANT.DATAMODEL_LOCATION + datamodelFile + ".txt";
		DataModel dataModel = RecommendFactory.buildDataModel(file);
		
		
		RecommenderIRStatsEvaluator irStatsEvaluator = new GenericRecommenderIRStatsEvaluator();
		
		MyRecommenderBuilder recommenderBuilder = new MyRecommenderBuilder();
		recommenderBuilder.setRecommender(RECOMMENDER.SVD);
		recommenderBuilder.setSVDFactorizerType(factorizerType);
		recommenderBuilder.setFactorNum(factorNum);
		recommenderBuilder.setIterationNum(iterationNum);
		recommenderBuilder.setLambda(lambda);
		
		BufferedWriter bw = new BufferedWriter(new FileWriter("test//lodcloud2014 mahout//svd topn " + datamodelFile + " " + factorizerType + " " + factorNum + " " + iterationNum + ".txt"));
	
		bw.write("topN	Precision	Recall	F1Measure	FallOut	Reach	NormalizedDiscountedCumulativeGain" + "\n");
	   		
		for (int topn = 1; topn <= 10; topn++) {
			 		
				double precision = 0;
	    		double recall = 0;
	    		double f1 = 0;
	    		double fallOut = 0;
	    		double reach = 0;
	    		double NDCG = 0;
	    		
	    		for (int i = 0; i < evalTimes; i++) {
	    			
	    			IRStatistics stats = null;
	    			if (pref) {
	    		    		stats = irStatsEvaluator.evaluate(
	    				recommenderBuilder, null, dataModel, null, topn, 1, 1.0);
	    		    		
	    			} else {
	    				DataModelBuilder dataModelBuilder = RecommendFactory.buildDataModelNoPrefBuilder();
	    				stats = irStatsEvaluator.evaluate(
	    				recommenderBuilder, dataModelBuilder, dataModel, null, topn, 1, 1.0);
	    			}
	    			
	    			bw.write("run" + i + "	"+ stats.getPrecision() + "	" + stats.getRecall() + "	" + stats.getF1Measure());
	    			bw.write("	" + stats.getFallOut() + "	" + stats.getReach() +"	" + stats.getNormalizedDiscountedCumulativeGain() + "\n");
	    			bw.flush();
	    			
	    			precision += stats.getPrecision();
	    			recall += stats.getRecall();
	    			f1 += stats.getF1Measure();
	    			fallOut += stats.getFallOut();
	    			reach += stats.getReach();
	    			NDCG += stats.getNormalizedDiscountedCumulativeGain();
	    
	    		}
			
	    		bw.write("top" + topn + "	"+ precision/evalTimes + "	" + recall/evalTimes + "	" + f1/evalTimes);
			bw.write("	" + fallOut/evalTimes + "	" + reach/evalTimes +"	" + NDCG/evalTimes + "\n");	    
			bw.flush();
		}
		
		bw.flush();
	    bw.close();
	
	}


	public static void evaluateBaselinePrefDiffPercentXXX(String datamodelFile, RECOMMENDER recommenderType) throws TasteException, IOException {
	
		String file = CONSTANT.DATAMODEL_LOCATION + datamodelFile + ".txt";
		DataModel dataModel = RecommendFactory.buildDataModel(file);
				
		MyRecommenderBuilder recommenderBuilder = new MyRecommenderBuilder();
		recommenderBuilder.setRecommender(recommenderType);
		
		RecommenderEvaluator maeEvaluator = new AverageAbsoluteDifferenceRecommenderEvaluator();
		RecommenderEvaluator rmseEvaluator = new RMSRecommenderEvaluator();
	  	  			  		
	  	BufferedWriter bw = new BufferedWriter(new FileWriter("test//lodcloud2014 mahout//baseline diff " + datamodelFile + " " + recommenderType + ".txt"));
	  	bw.write("Percent\tMAE\tRMSE\n"); 
	  		
		for (int percent = 50; percent <= 90; percent += 10) {
						  
	    		double mae = 0;
	    		double rmse = 0;
	    		for (int i = 0; i < evalTimes; i++) {	    					
	    			mae += maeEvaluator.evaluate(recommenderBuilder, null, dataModel, (double) percent/100, usingPercent);
			  	rmse += rmseEvaluator.evaluate(recommenderBuilder, null, dataModel, (double) percent/100, usingPercent); 		 
	    		}
		  	bw.write(percent + "%\t" + mae/evalTimes + "\t" + rmse/evalTimes + "\n"); 
		  	bw.flush();	  	
			}
	  	bw.close();
	}

	public static void evaluateItemBasedPrefDiffPercentXXX(RecommendFactory.SIMILARITY similarityType, String datamodelFile) throws TasteException, IOException, ClassNotFoundException {
		
		String file = CONSTANT.DATAMODEL_LOCATION + datamodelFile + ".txt";
		DataModel dataModel = RecommendFactory.buildDataModel(file);
		
		MyRecommenderBuilder recommenderBuilder = new MyRecommenderBuilder();
		recommenderBuilder.setRecommender(RECOMMENDER.ITEM);
		recommenderBuilder.setSimilarity(similarityType);
			
		RecommenderEvaluator maeEvaluator = new AverageAbsoluteDifferenceRecommenderEvaluator();
		RecommenderEvaluator rmseEvaluator = new RMSRecommenderEvaluator();
	  		  
	  	BufferedWriter bw = new BufferedWriter(new FileWriter("test//lodcloud2014 mahout//item-based diff" + " " + similarityType + " precentXXX " + datamodelFile + ".txt"));
	  	bw.write("Percent\tMAE\tRMSE\n");
	
	    for (int percent = 50; percent <= 90; percent += 10) {
	  		
				double mae = 0;
	    		double rmse = 0;
	    		for (int i = 0; i < evalTimes; i++) {	    					
	    			mae += maeEvaluator.evaluate(recommenderBuilder, null, dataModel, (double) percent/100, usingPercent);
			  	rmse += rmseEvaluator.evaluate(recommenderBuilder, null, dataModel, (double) percent/100, usingPercent); 		 
	    		}
	      	bw.write(percent + "%\t" + mae/evalTimes + "\t" + rmse/evalTimes + "\n"); 
		  	bw.flush();	 
		 
	    	}
	    
	    bw.close();
	
	}


	public static void evaluateUserBasedPrefDiffPercentXXXNearestXXX(String datamodelFile, RecommendFactory.SIMILARITY similarityType) throws TasteException, IOException {
		
		String file = CONSTANT.DATAMODEL_LOCATION + datamodelFile + ".txt";
		DataModel dataModel = RecommendFactory.buildDataModel(file);
		
		  
		MyRecommenderBuilder recommenderBuilder = new MyRecommenderBuilder();
		recommenderBuilder.setRecommender(RECOMMENDER.USER);
		recommenderBuilder.setSimilarity(similarityType);
		recommenderBuilder.setNeighborhood(RecommendFactory.NEIGHBORHOOD.NEAREST);
		
		
		RecommenderEvaluator maeEvaluator = new AverageAbsoluteDifferenceRecommenderEvaluator();
		RecommenderEvaluator rmseEvaluator = new RMSRecommenderEvaluator();
	  	
		BufferedWriter bw = new BufferedWriter(new FileWriter("test//lodcloud2014 mahout//user-based diff "+ similarityType + " percentXXX neighborXXX " + datamodelFile + ".txt"));
	  	bw.write("Percent\tNeighbor\tMAE\tRMSE\n"); 	
	  		
	  	for (int percent = 50; percent <= 90; percent += 10) {
			for (int neighbor = 1; neighbor <= 10;  neighbor += 1) {
				
				recommenderBuilder.setNearestNum(neighbor);	
				
			  		double mae = 0;
		    		double rmse = 0;
		    		for (int i = 0; i < evalTimes; i++) {	    					
		    			mae += maeEvaluator.evaluate(recommenderBuilder, null, dataModel, (double) percent/100, usingPercent);
				  	rmse += rmseEvaluator.evaluate(recommenderBuilder, null, dataModel, (double) percent/100, usingPercent); 		 
		    		}
					bw.write(percent + "%\t" + neighbor + "\t"+ mae/evalTimes + "\t" + rmse/evalTimes + "\n"); 
		  		bw.flush();
			}
			
		}
		    
	    bw.close();
	}


	public static void evaluateUserBasedPrefDiffPercentXXXThresholdXXX(String datamodelFile, RecommendFactory.SIMILARITY similarityType) throws TasteException, IOException {
		
		String file = CONSTANT.DATAMODEL_LOCATION + datamodelFile + ".txt";
		DataModel dataModel = RecommendFactory.buildDataModel(file);
		
		  
		MyRecommenderBuilder recommenderBuilder = new MyRecommenderBuilder();
		recommenderBuilder.setRecommender(RECOMMENDER.USER);
		recommenderBuilder.setSimilarity(similarityType);
		recommenderBuilder.setNeighborhood(RecommendFactory.NEIGHBORHOOD.THRESHOLD);
		
		
		RecommenderEvaluator maeEvaluator = new AverageAbsoluteDifferenceRecommenderEvaluator();
		RecommenderEvaluator rmseEvaluator = new RMSRecommenderEvaluator();
	  	
		BufferedWriter bw = new BufferedWriter(new FileWriter("test//lodcloud2014 mahout//user-based diff "+ similarityType + " percentXXX thresholdXXX " + datamodelFile + ".txt"));
	  	bw.write("Percent\tThreshold\tMAE\tRMSE\n"); 	
	  			
	  	for (int percent = 50; percent <= 90; percent += 10) {
	
			for (double threshold = 50; threshold <= 90;  threshold += 10) {
					
				recommenderBuilder.setNeighborThreshold(threshold/100);	
					
				double mae = 0;
		    		double rmse = 0;
		    		for (int i = 0; i < evalTimes; i++) {	    					
		    			mae += maeEvaluator.evaluate(recommenderBuilder, null, dataModel, (double) percent/100, usingPercent);
				  	rmse += rmseEvaluator.evaluate(recommenderBuilder, null, dataModel, (double) percent/100, usingPercent); 		 
		    		}				
		    		
				bw.write(percent + "%\t" + threshold/100 + "\t"+ mae/evalTimes + "\t" + rmse/evalTimes + "\n"); 
		  		bw.flush();
			}
			
		}
		    
	    bw.close();
	}
	
	
	public static void evaluateSVDPrefDiffPercentXXX(String datamodelFile, SVDFACTORIZER factorizerType, int factorNum, int iterationNum, double lambda) throws TasteException, IOException {
	
		String file = CONSTANT.DATAMODEL_LOCATION + datamodelFile + ".txt";
		DataModel dataModel = RecommendFactory.buildDataModel(file);
				
		MyRecommenderBuilder recommenderBuilder = new MyRecommenderBuilder();
		recommenderBuilder.setRecommender(RECOMMENDER.SVD);
		recommenderBuilder.setSVDFactorizerType(factorizerType);
		recommenderBuilder.setFactorNum(factorNum);
		recommenderBuilder.setIterationNum(iterationNum);
		recommenderBuilder.setLambda(lambda);
	
		RecommenderEvaluator maeEvaluator = new AverageAbsoluteDifferenceRecommenderEvaluator();
		RecommenderEvaluator rmseEvaluator = new RMSRecommenderEvaluator();
	  	  			  		
	  	BufferedWriter bw = new BufferedWriter(new FileWriter("test//lodcloud2014 mahout//svd diff " + datamodelFile + " " + factorizerType + " " + factorNum + " " + iterationNum + " " + lambda + ".txt"));
	  	bw.write("Percent\tMAE\tRMSE\n"); 
	  		
		for (int percent = 50; percent <= 90; percent += 10) {
						  
	    		double mae = 0;
	    		double rmse = 0;
	    		for (int i = 0; i < evalTimes; i++) {
	    			double onceMae = 0; double onceRmse = 0;
	    			
	    			onceMae = maeEvaluator.evaluate(recommenderBuilder, null, dataModel, (double) percent/100, usingPercent);
	    			onceRmse = rmseEvaluator.evaluate(recommenderBuilder, null, dataModel, (double) percent/100, usingPercent); 		 
			  	bw.write("run" + evalTimes + "\t" + onceMae + "\t" + onceRmse + "\n"); 
			  	bw.flush();	 
			  	mae += onceMae; rmse += onceRmse;
	    		}
	    	bw.write(percent + "%\t" + mae/evalTimes + "\t" + rmse/evalTimes + "\n"); 
		  	bw.flush();	  	
		}
	  	bw.close();
	}


	public static void evaluateLinkDocumentBasedTopXXX(String datamodelFile) throws FileNotFoundException, IOException, ClassNotFoundException, TasteException {
		

		String file = CONSTANT.DATAMODEL_LOCATION + datamodelFile + ".txt";
		DataModel dataModel = RecommendFactory.buildDataModel(file);
	
					
		RecommenderIRStatsEvaluator irStatsEvaluator = new MyRecommenderIRStatsEvaluator();
		
		BufferedWriter bw = new BufferedWriter(new FileWriter("test result//lodcloud2014 mahout//link-document-similarity-based " + datamodelFile + " VOCABULARY" + " topXXX limited.txt"));
	  	bw.write("topN	Precision	Recall	F1Measure	FallOut	Reach	NormalizedDiscountedCumulativeGain" + "\n");
	
	    for (int i = 1; i <= 10; i++) {
	    		
	    		IRStatistics stats = null;
	        	stats = irStatsEvaluator.evaluate(
			null, null, dataModel, null, i, 1, 1.0);
		    			
			bw.write("top" + i + "	" + stats.getPrecision() + "	" + stats.getRecall() + "	" + stats.getF1Measure());
			bw.write("	" + stats.getFallOut() + "	" + stats.getReach() +"	" + stats.getNormalizedDiscountedCumulativeGain() + "\n");
		}

	    
	    bw.write("\n");

	    
	    bw.flush();
	    bw.close();
	}
	
	
	public static void evaluateMySVDTopXXX(String datamodelFile, boolean pref, SVDFACTORIZER factorizerType, int factorNum, int iterationNum, double lambda) throws TasteException, IOException {
	

		String file = CONSTANT.DATAMODEL_LOCATION + datamodelFile + ".txt";
		DataModel dataModel = RecommendFactory.buildDataModel(file);
		
		
		RecommenderIRStatsEvaluator irStatsEvaluator = new MyRecommenderIRStatsEvaluator();
		
		MyRecommenderBuilder recommenderBuilder = new MyRecommenderBuilder();
		recommenderBuilder.setRecommender(RECOMMENDER.SVD);
		recommenderBuilder.setSVDFactorizerType(factorizerType);
		recommenderBuilder.setFactorNum(factorNum);
		recommenderBuilder.setIterationNum(iterationNum);
		recommenderBuilder.setLambda(lambda);
		
		
		
	  	BufferedWriter bw = new BufferedWriter(new FileWriter("test result//lodcloud2014 mahout//svd " + pref + " " + factorizerType + " " + factorNum + " " + iterationNum + " topXXX " + datamodelFile + ".txt"));
	  
		bw.write("topN	Precision	Recall	F1Measure	FallOut	Reach	NormalizedDiscountedCumulativeGain" + "\n");
	   		
		for (int topn = 1; topn <= 10; topn++) {
			
			int times = 3;
	    		
				double precision = 0;
	    		double recall = 0;
	    		double f1 = 0;
	    		double fallOut = 0;
	    		double reach = 0;
	    		double NDCG = 0;
	    		
	    		for (int i = 0; i < times; i++) {
	    			
	    			IRStatistics stats = null;
	    			if (pref) {
	    		    		stats = irStatsEvaluator.evaluate(
	    				recommenderBuilder, null, dataModel, null, topn, 1, 1.0);
	    		    		
	    			} else {
	    				DataModelBuilder dataModelBuilder = RecommendFactory.buildDataModelNoPrefBuilder();
	    				stats = irStatsEvaluator.evaluate(
	    				recommenderBuilder, dataModelBuilder, dataModel, null, topn, 1, 1.0);
	    			}
	    			
	    			bw.write("times" + i + "	"+ stats.getPrecision() + "	" + stats.getRecall() + "	" + stats.getF1Measure());
	    			bw.write("	" + stats.getFallOut() + "	" + stats.getReach() +"	" + stats.getNormalizedDiscountedCumulativeGain() + "\n");
	    			
	    			precision += stats.getPrecision();
	    			recall += stats.getRecall();
	    			f1 += stats.getF1Measure();
	    			fallOut += stats.getFallOut();
	    			reach += stats.getReach();
	    			NDCG += stats.getNormalizedDiscountedCumulativeGain();
	    
	    		}
    		
	    		bw.write("top" + topn + "	"+ precision/times + "	" + recall/times + "	" + f1/times);
			bw.write("	" + fallOut/times + "	" + reach/times +"	" + NDCG/times + "\n");	    
		}
		
		bw.flush();
	    bw.close();

	}
	
	public static void evaluateCollaborativeRankingTopXXX(String datamodelFile, CRFACTORIZER CRfactorizerType, int factorNum, int iterationNum, double learningRate) throws TasteException, IOException {
	

		String file = CONSTANT.DATAMODEL_LOCATION + datamodelFile + ".txt";
		DataModel dataModel = RecommendFactory.buildDataModel(file);
		
		
		RecommenderIRStatsEvaluator irStatsEvaluator = new MyRecommenderIRStatsEvaluator();
		
		MyRecommenderBuilder recommenderBuilder = new MyRecommenderBuilder();
		recommenderBuilder.setRecommender(RECOMMENDER.CollaborativeRanking);
		recommenderBuilder.setCRFactorizerType(CRfactorizerType);
		recommenderBuilder.setFactorNum(factorNum);
		recommenderBuilder.setIterationNum(iterationNum);
		recommenderBuilder.setLearningRate(learningRate);
			
	  	BufferedWriter bw = new BufferedWriter(new FileWriter("test result//lodcloud2014 mahout//cr " + CRfactorizerType + " " + factorNum + " " + iterationNum + " " + learningRate + " topXXX " + datamodelFile + ".txt"));
	  
		bw.write("topN	Precision	Recall	F1Measure	FallOut	Reach	NormalizedDiscountedCumulativeGain" + "\n");
	   		
		for (int topn = 1; topn <= 5; topn+=2) {
			
			int times = 1;
			double precision = 0;
	    		double recall = 0;
	    		double f1 = 0;
	    		double fallOut = 0;
	    		double reach = 0;
	    		double NDCG = 0;
	    		
	    		for (int i = 0; i < times; i++) {
	    			
	    			IRStatistics stats = null;
	    		
	    			DataModelBuilder dataModelBuilder = RecommendFactory.buildDataModelNoPrefBuilder();
	    			stats = irStatsEvaluator.evaluate(
	    			recommenderBuilder, dataModelBuilder, dataModel, null, topn, 1, 1.0);
	    	
					bw.write("times" + i + "	"+ stats.getPrecision() + "	" + stats.getRecall() + "	" + stats.getF1Measure());
	    			bw.write("	" + stats.getFallOut() + "	" + stats.getReach() +"	" + stats.getNormalizedDiscountedCumulativeGain() + "\n");
	    			
	    			precision += stats.getPrecision();
	    			recall += stats.getRecall();
	    			f1 += stats.getF1Measure();
	    			fallOut += stats.getFallOut();
	    			reach += stats.getReach();
	    			NDCG += stats.getNormalizedDiscountedCumulativeGain();
	    
	    		}
    		
	    		bw.write("top" + topn + "	"+ precision/times + "	" + recall/times + "	" + f1/times);
			bw.write("	" + fallOut/times + "	" + reach/times +"	" + NDCG/times + "\n");	  
			bw.flush();
		}
		
	    bw.close();

	}
	
	public static void evaluateSVDFeatureRecommenderTopXXX(String datamodelFile, MODEL model, int factorNum, int iterationNum, double learningRate) throws TasteException, IOException, ClassNotFoundException, InterruptedException {
		

		String file = CONSTANT.DATAMODEL_LOCATION + datamodelFile + ".txt";
		DataModel dataModel = RecommendFactory.buildDataModel(file);
		
		
		MyRecommenderIRStatsEvaluator irStatsEvaluator = new MyRecommenderIRStatsEvaluator();
				
	  	BufferedWriter bw = new BufferedWriter(new FileWriter("test result//lodcloud2014 mahout//cr " + model + " " + factorNum + " " + iterationNum + " " + learningRate + " topXXX " + datamodelFile + ".txt"));
	  
		bw.write("topN	Precision	Recall	F1Measure	FallOut	Reach	NormalizedDiscountedCumulativeGain" + "\n");
	   		
		for (int topn = 1; topn <= 5; topn+=2) {
			
		int times = 1;
				double precision = 0;
	    		double recall = 0;
	    		double f1 = 0;
	    		double fallOut = 0;
	    		double reach = 0;
	    		double NDCG = 0;
	    		
	    		for (int i = 0; i < times; i++) {
	    			
	    			IRStatistics stats = null;
	    		
	    			stats = irStatsEvaluator.evaluateSVDFeatureRecommender(dataModel, i, 1.0, model);
	    			bw.write("times" + i + "	"+ stats.getPrecision() + "	" + stats.getRecall() + "	" + stats.getF1Measure());
	    			bw.write("	" + stats.getFallOut() + "	" + stats.getReach() +"	" + stats.getNormalizedDiscountedCumulativeGain() + "\n");
	    			
	    			precision += stats.getPrecision();
	    			recall += stats.getRecall();
	    			f1 += stats.getF1Measure();
	    			fallOut += stats.getFallOut();
	    			reach += stats.getReach();
	    			NDCG += stats.getNormalizedDiscountedCumulativeGain();
	    
	    		}
    		
	    	
	    		bw.write("top" + topn + "	"+ precision/times + "	" + recall/times + "	" + f1/times);
			bw.write("	" + fallOut/times + "	" + reach/times +"	" + NDCG/times + "\n");	  
			bw.flush();
		}
		
	    bw.close();

	}
	
	public static void checkDataModel(boolean pref, String datamodelFile) throws TasteException, IOException, ClassNotFoundException {
		
		String file = CONSTANT.DATAMODEL_LOCATION + datamodelFile + ".txt";
		DataModel dataModel = null;
		if (pref) {
			dataModel = RecommendFactory.buildDataModel(file);
		} else {
			dataModel = RecommendFactory.buildDataModelNoPref(file);
		}
		
		System.out.println(dataModel.getNumUsers() + " users");
		System.out.println(dataModel.getNumItems() + " items");
		
		LongPrimitiveIterator lpit = dataModel.getItemIDs();
		while (lpit.hasNext()) {
			Long itemid = lpit.next();
			System.out.println(itemid + "	" + dataModel.getNumUsersWithPreferenceFor(itemid));				
		}
		
	}
	
	public static void checkSimilarityRange(RecommendFactory.SIMILARITY similarityType, String datamodelFile) throws TasteException, IOException, ClassNotFoundException {
		String file = CONSTANT.DATAMODEL_LOCATION + datamodelFile + ".txt";
		DataModel dataModel = RecommendFactory.buildDataModel(file);
	
		ItemSimilarity similarity = null;
		switch (similarityType) {
        case PEARSON:
        		similarity = new PearsonCorrelationSimilarity(dataModel);
        		break;
        case PEARSON_WEIGHTED:
        		similarity = new PearsonCorrelationSimilarity(dataModel, Weighting.WEIGHTED);
        		break;
        case COSINE:
        		similarity = new UncenteredCosineSimilarity(dataModel);
        		break;
		case CITYBLOCK:
        		similarity = new CityBlockSimilarity(dataModel);	
        		break;
        case EUCLIDEAN:
        		similarity = new EuclideanDistanceSimilarity(dataModel);
        		break;
        case EUCLIDEAN_WEIGHTED:
        		similarity = new EuclideanDistanceSimilarity(dataModel, Weighting.WEIGHTED);
        		break;
        case DATASET_VOCABULARY_COSINE:
			try {
				similarity = new DatasetVocabularySimilarity(dataModel);
			} catch (ClassNotFoundException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        		break;
        case DATASET_CLASS_COSINE:
			try {
				similarity = new DatasetClassSimilarity(dataModel);
			} catch (ClassNotFoundException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        		break;
        case DATASET_PPROPERTY_COSINE_SUBJECTS:
			try {
				similarity = new DatasetPropertySubjectsSimilarity(dataModel);
			} catch (ClassNotFoundException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        		break;
        case DATASET_PPROPERTY_COSINE_TRIPLES:
			try {
				similarity = new DatasetPropertyTriplesSimilarity(dataModel);
			} catch (ClassNotFoundException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        		break;
        default:
        		similarity = new EuclideanDistanceSimilarity(dataModel);
        }
		
		
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File(CONSTANT.FEATURE_LOCATION + "lstDatasetID")));
		ArrayList<Integer> lstDatasetID = (ArrayList<Integer>) ois.readObject();
				
		BufferedWriter bw = new BufferedWriter(new FileWriter(CONSTANT.DATAMODEL_LOCATION + "simValues " + similarityType + ".txt"));
		
		for (int i = 0; i < lstDatasetID.size(); i++) {
			for (int j = i + 1; j < lstDatasetID.size(); j++) {
				bw.write(lstDatasetID.get(i) + "\t" + lstDatasetID.get(j) + "\t" + similarity.itemSimilarity(lstDatasetID.get(i), lstDatasetID.get(j)) + "\n");
			}
			bw.flush();
		}
	
		bw.close();
	}
}
