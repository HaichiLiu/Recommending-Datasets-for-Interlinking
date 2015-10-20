/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package edu.nudt.c6.datasetlinking.mahout;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.common.Weighting;
import org.apache.mahout.cf.taste.eval.RecommenderBuilder;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.neighborhood.ThresholdUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericBooleanPrefItemBasedRecommender;
import org.apache.mahout.cf.taste.impl.recommender.GenericBooleanPrefUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.recommender.GenericItemBasedRecommender;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.recommender.ItemAverageRecommender;
import org.apache.mahout.cf.taste.impl.recommender.ItemUserAverageRecommender;
import org.apache.mahout.cf.taste.impl.recommender.RandomRecommender;
import org.apache.mahout.cf.taste.impl.recommender.svd.ALSWRFactorizer;
import org.apache.mahout.cf.taste.impl.recommender.svd.AbstractFactorizer;
import org.apache.mahout.cf.taste.impl.recommender.svd.ParallelSGDFactorizer;
import org.apache.mahout.cf.taste.impl.recommender.svd.RatingSGDFactorizer;
import org.apache.mahout.cf.taste.impl.recommender.svd.SVDPlusPlusFactorizer;
import org.apache.mahout.cf.taste.impl.recommender.svd.SVDRecommender;
import org.apache.mahout.cf.taste.impl.similarity.CityBlockSimilarity;
import org.apache.mahout.cf.taste.impl.similarity.EuclideanDistanceSimilarity;
import org.apache.mahout.cf.taste.impl.similarity.LogLikelihoodSimilarity;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.impl.similarity.SpearmanCorrelationSimilarity;
import org.apache.mahout.cf.taste.impl.similarity.TanimotoCoefficientSimilarity;
import org.apache.mahout.cf.taste.impl.similarity.UncenteredCosineSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.ItemSimilarity;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;

import edu.nudt.c6.datasetlinking.lodcloud2014.collaborativeranking.AbstractCRFactorizer;
import edu.nudt.c6.datasetlinking.lodcloud2014.collaborativeranking.BasicLFMFactorizer;
import edu.nudt.c6.datasetlinking.lodcloud2014.collaborativeranking.CollaborativeRankingRecommender;
import edu.nudt.c6.datasetlinking.lodcloud2014.collaborativeranking.LFMTransFactorizer;
import edu.nudt.c6.datasetlinking.mahout.RecommendFactory.NEIGHBORHOOD;
import edu.nudt.c6.datasetlinking.mahout.RecommendFactory.RECOMMENDER;
import edu.nudt.c6.datasetlinking.mahout.RecommendFactory.SIMILARITY;
import edu.nudt.c6.datasetlinking.mahout.lodcloud2014.MyRatingSGDFactorizer;
import edu.nudt.c6.datasetlinking.mahout.lodcloud2014.datasetSimilarity.DatasetClassSimilarity;
import edu.nudt.c6.datasetlinking.mahout.lodcloud2014.datasetSimilarity.DatasetPropertySubjectsSimilarity;
import edu.nudt.c6.datasetlinking.mahout.lodcloud2014.datasetSimilarity.DatasetPropertyTriplesSimilarity;
import edu.nudt.c6.datasetlinking.mahout.lodcloud2014.datasetSimilarity.DatasetVocabularySimilarity;
import edu.nudt.c6.datasetlinking.recommendation.CustomizedItemSimilarity.DatasetLinkingItemSimilarity;
import edu.nudt.c6.datasetlinking.recommendation.CustomizedItemSimilarity.DatasetLinkingWeightSumItemSimilarity;
import edu.nudt.c6.datasetlinking.recommendation.CustomizedItemSimilarity.DatasetMaxVocabularyItemSimilarity;
import edu.nudt.c6.datasetlinking.recommendation.CustomizedItemSimilarity.MaterializedCityBlockItemSimilarity;


public final class MyRecommenderBuilder implements RecommenderBuilder {
	
	boolean pref = true;
    RECOMMENDER recommenderType = null;
    SIMILARITY similarityType = null;
    
    NEIGHBORHOOD neighborhoodType = null;
    int nearestNum = 0;
    double neighborThreshold = 0;
    
	private int factorNum;
	private int iterationNum;
	private double lambda;
    
	public enum SVDFACTORIZER {
		RatingSGD, ALSWR, SVDPlusPlus, ParallelSGD, MyRatingSGD 
    }
	
	SVDFACTORIZER SVDfactorizerType;
	
	public enum CRFACTORIZER {//CollaborativeRanking
		BasicLFM, LFMTrans 
    }
	
	CRFACTORIZER CRFactorizerType;
	private double learningRate;
	  
	@Override
	public Recommender buildRecommender(DataModel dataModel) throws TasteException {
		if (recommenderType == RECOMMENDER.ITEM) {
			ItemSimilarity itemSimilarity = null;
			switch (similarityType) {
	        case PEARSON:
	        		itemSimilarity = new PearsonCorrelationSimilarity(dataModel);
	        		break;
	        case PEARSON_WEIGHTED:
	        		itemSimilarity = new PearsonCorrelationSimilarity(dataModel, Weighting.WEIGHTED);
	        		break;
	        case COSINE:
	        		itemSimilarity = new UncenteredCosineSimilarity(dataModel);
	        		break;
	        case TANIMOTO:
	        		itemSimilarity = new TanimotoCoefficientSimilarity(dataModel);
	        		break;
	        case LOGLIKELIHOOD:
	        		itemSimilarity = new LogLikelihoodSimilarity(dataModel);
	        		break;
	        case CITYBLOCK:
	        		itemSimilarity = new CityBlockSimilarity(dataModel);	
	        		break;
	        case EUCLIDEAN:
	        		itemSimilarity = new EuclideanDistanceSimilarity(dataModel);
	        		break;
	        case EUCLIDEAN_WEIGHTED:
	        		itemSimilarity = new EuclideanDistanceSimilarity(dataModel, Weighting.WEIGHTED);
	        		break;
	        case DATASET_VOCABULARY_COSINE:
				try {
					itemSimilarity = new DatasetVocabularySimilarity(dataModel);
				} catch (ClassNotFoundException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        		break;
	        case DATASET_CLASS_COSINE:
				try {
					itemSimilarity = new DatasetClassSimilarity(dataModel);
				} catch (ClassNotFoundException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        		break;
	        case DATASET_PPROPERTY_COSINE_SUBJECTS:
				try {
					itemSimilarity = new DatasetPropertySubjectsSimilarity(dataModel);
				} catch (ClassNotFoundException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        		break;
	        case DATASET_PPROPERTY_COSINE_TRIPLES:
				try {
					itemSimilarity = new DatasetPropertyTriplesSimilarity(dataModel);
				} catch (ClassNotFoundException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        		break;
	        default:
	        		itemSimilarity = new EuclideanDistanceSimilarity(dataModel);
	        }
			
			if (pref) {
				return new GenericItemBasedRecommender(dataModel, itemSimilarity);
			} else {
				return new GenericBooleanPrefItemBasedRecommender(dataModel, itemSimilarity);
			}
			
		} else if (recommenderType == RECOMMENDER.USER) {
			UserSimilarity userSimilarity = null;
			switch (similarityType) {
	        case PEARSON:
	        		userSimilarity = new PearsonCorrelationSimilarity(dataModel);
	        		break;
	        case PEARSON_WEIGHTED:
	        		userSimilarity = new PearsonCorrelationSimilarity(dataModel, Weighting.WEIGHTED);
	        		break;
	        case COSINE:
	        		userSimilarity = new UncenteredCosineSimilarity(dataModel);
	        		break;
	        case SPEARMAN:
		        	userSimilarity = new SpearmanCorrelationSimilarity(dataModel);
	        		break;
	        case TANIMOTO:
	        		userSimilarity = new TanimotoCoefficientSimilarity(dataModel);
	        		break;
	        case LOGLIKELIHOOD:
	        		userSimilarity = new LogLikelihoodSimilarity(dataModel);
	        		break;
	        case CITYBLOCK:
	        		userSimilarity = new CityBlockSimilarity(dataModel);	
	        		break;
	        case EUCLIDEAN:
	        		userSimilarity = new EuclideanDistanceSimilarity(dataModel);
	        		break;
	        case EUCLIDEAN_WEIGHTED:
	        		userSimilarity = new EuclideanDistanceSimilarity(dataModel, Weighting.WEIGHTED);
	        		break;
	        case DATASET_VOCABULARY_COSINE:
				try {
					userSimilarity = new DatasetVocabularySimilarity(dataModel);
				} catch (ClassNotFoundException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        		break;
	        case DATASET_CLASS_COSINE:
				try {
					userSimilarity = new DatasetClassSimilarity(dataModel);
				} catch (ClassNotFoundException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        		break;
	        case DATASET_PPROPERTY_COSINE_SUBJECTS:
				try {
					userSimilarity = new DatasetPropertySubjectsSimilarity(dataModel);
				} catch (ClassNotFoundException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        		break;
	        case DATASET_PPROPERTY_COSINE_TRIPLES:
				try {
					userSimilarity = new DatasetPropertyTriplesSimilarity(dataModel);
				} catch (ClassNotFoundException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        		break;
	        default:
	        		userSimilarity = new EuclideanDistanceSimilarity(dataModel);
	        }
			
			UserNeighborhood userNeighborhood = null;
			switch (neighborhoodType) {
			case NEAREST:
				userNeighborhood = new NearestNUserNeighborhood(this.nearestNum, userSimilarity, dataModel);
				break;
			case THRESHOLD:
	        default:
	        		userNeighborhood = new ThresholdUserNeighborhood(this.neighborThreshold, userSimilarity, dataModel);
			}
			
			if (pref) {
				return new GenericUserBasedRecommender(dataModel, userNeighborhood, userSimilarity);
			} else {
				return new GenericBooleanPrefUserBasedRecommender(dataModel, userNeighborhood, userSimilarity);
			}
		} else if (recommenderType == RECOMMENDER.SVD) {
			AbstractFactorizer factorizer = null;
			
			switch (SVDfactorizerType) {
	        case RatingSGD:
	        		factorizer = new RatingSGDFactorizer(dataModel, factorNum, iterationNum);
	        		break;
	        case ALSWR:
	        		factorizer = new ALSWRFactorizer(dataModel, factorNum, lambda, iterationNum);
	        		break;
	        case SVDPlusPlus:
	        		factorizer = new SVDPlusPlusFactorizer(dataModel, factorNum, iterationNum);
	        		break;
	        case ParallelSGD:
	        		factorizer = new ParallelSGDFactorizer(dataModel, factorNum, lambda, iterationNum);
	        		break;
	        case MyRatingSGD:
		        	factorizer = new MyRatingSGDFactorizer(dataModel, factorNum, iterationNum);
	        		break;
			}
			
			return new SVDRecommender(dataModel, factorizer);
			
		} else if (recommenderType == RECOMMENDER.LINKDOCUMENT) {
			
		}else if (recommenderType == RECOMMENDER.CollaborativeRanking) {
			AbstractCRFactorizer factorizer = null;
			
			switch (CRFactorizerType) {
	        case BasicLFM:
				try {
					factorizer = new BasicLFMFactorizer(dataModel, factorNum, iterationNum);
				} catch (ClassNotFoundException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        		break;
	        case LFMTrans:
				try {
					factorizer = new LFMTransFactorizer(dataModel, factorNum, iterationNum, learningRate);
				} catch (ClassNotFoundException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			}
			return new CollaborativeRankingRecommender(dataModel, factorizer);
		}  else if (recommenderType == RECOMMENDER.Random) {
			return new RandomRecommender(dataModel);
		}  else if (recommenderType == RECOMMENDER.ItemAverage) {
			return new ItemAverageRecommender(dataModel);
		}  else if (recommenderType == RECOMMENDER.ItemUserAverage) {
			return new ItemUserAverageRecommender(dataModel);
		}  
		
		return null;	
	    
	}
  
    public void setRecommender (RECOMMENDER recommenderType) {
	    this.recommenderType = recommenderType; 
    }
    
    public void setPref (boolean pref) {
	    this.pref = pref; 
    }
    
    public void setSimilarity (SIMILARITY similarityType) {
    		this.similarityType = similarityType;
    }
    
    public void setNeighborhood (NEIGHBORHOOD neighborhoodType) {
    		this.neighborhoodType = neighborhoodType;
    }
    
    public void setNearestNum (int nearestNum) {
    		this.nearestNum = nearestNum;
    }
    
    public void setNeighborThreshold (double neighborThreshold) {
    		this.neighborThreshold = neighborThreshold;
    }
    
    public void setSVDFactorizerType (SVDFACTORIZER factorizerType) {
    		this.SVDfactorizerType = factorizerType;
    }
    public void setCRFactorizerType (CRFACTORIZER CRfactorizerType) {
		this.CRFactorizerType = CRfactorizerType;
}
    public void setFactorNum (int factorNum) {
    		this.factorNum = factorNum;
    }
    
    public void setIterationNum (int iterationNum) {
    		this.iterationNum = iterationNum;
    }
  
    public void setLambda (double lambda) {
    		this.lambda = lambda;
    }
    
    public void setLearningRate(double learningRate) {
    		this.learningRate = learningRate;
    }
}
