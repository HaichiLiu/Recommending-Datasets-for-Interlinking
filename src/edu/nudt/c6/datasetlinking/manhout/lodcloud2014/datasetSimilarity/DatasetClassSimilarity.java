package edu.nudt.c6.datasetlinking.mahout.lodcloud2014.datasetSimilarity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import org.apache.mahout.cf.taste.common.Refreshable;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.similarity.AbstractItemSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.similarity.PreferenceInferrer;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;

//lodcloud2014上 实现基于数据集词汇余弦相似度的物品相似度
//这个版本仅实现词汇的余弦相似度来计算物品相似度
//由于在评价函数的运行过程中要多次根据训练集模型参数，生成推荐器，而推荐器又多次需要创建物品相似度对象
//就要求，每次在创建推荐器时，都要对这个相似度对象进行一次初始化操作
public class DatasetClassSimilarity extends AbstractItemSimilarity implements UserSimilarity {
	//AbstractItemSimilarity实现了ItemSimilarity，我的类扩展AbstractItemSimilarity即可
	
	
	double[][] matrixDatasetVocabularyCosSim = null;
	
	
	public DatasetClassSimilarity(DataModel dataModel) throws FileNotFoundException, IOException, ClassNotFoundException {
		super(dataModel);
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("resources//lodcloud2014//matrixDatasetClassCosSim")));
		this.matrixDatasetVocabularyCosSim = (double[][]) ois.readObject();
			
	}
	
	
	

	@Override
	public void refresh(Collection<Refreshable> alreadyRefreshed) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public double itemSimilarity(long itemID1, long itemID2)
			throws TasteException {
		return matrixDatasetVocabularyCosSim[(int) itemID1][(int) itemID2];	
	}

	@Override
	public double[] itemSimilarities(long itemID1, long[] itemID2s)
			throws TasteException {
	    double[] distance = new double[itemID2s.length];
	    for (int i = 0; i < itemID2s.length; ++i) {
	      distance[i] = matrixDatasetVocabularyCosSim[(int) itemID1][(int) itemID2s[i]];
	    }
	    return distance;
	}

	@Override
	public long[] allSimilarItemIDs(long itemID) throws TasteException {
		ArrayList<Integer> lstSimilarityItemID = new ArrayList<Integer>();
		for (int i = 0; i < matrixDatasetVocabularyCosSim.length; i++) {
			if (matrixDatasetVocabularyCosSim[(int) itemID][i] > 0) {
				lstSimilarityItemID.add(new Integer(i));
			}
		}
		long[] arySimilarityItemID = new long[lstSimilarityItemID.size()];
		for (int i = 0; i < lstSimilarityItemID.size(); i++) {
			arySimilarityItemID[i] = lstSimilarityItemID.get(i).longValue();
		}
		return arySimilarityItemID;
	}




	@Override
	public void setPreferenceInferrer(PreferenceInferrer arg0) {
		// TODO Auto-generated method stub
		
	}




	@Override
	public double userSimilarity(long arg0, long arg1) throws TasteException {
		return matrixDatasetVocabularyCosSim[(int) arg0][(int) arg1];	
	}
	
}
