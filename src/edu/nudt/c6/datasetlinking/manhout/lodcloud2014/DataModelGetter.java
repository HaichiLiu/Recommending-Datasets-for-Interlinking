/**
 * 
 */
package edu.nudt.c6.datasetlinking.mahout.lodcloud2014;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.HashMap;

import edu.nudt.c6.datasetlinking.lodcloud2014.CONSTANT;

/**
 *
 */
public class DataModelGetter {

	/**
	 * 
	 */
	public DataModelGetter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws ClassNotFoundException, IOException {
			
//		genDataModel();
		
		genDataModel2();
		
	}
	
	public static void genDataModel () throws ClassNotFoundException, IOException {
		
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File(CONSTANT.FEATURE_LOCATION + "sameAs Info/mapDatasetSameAsLinkedDatasetCount")));
		HashMap<String, HashMap<String, Integer>> mapDatasetSameAsLinkedDatasetCount = (HashMap<String,HashMap<String, Integer>>) ois.readObject();
		ois = new ObjectInputStream(new FileInputStream(new File(CONSTANT.FEATURE_LOCATION + "lstDatasetID")));
		ArrayList<Integer> lstDatasetID = (ArrayList<Integer>) ois.readObject();
		ois = new ObjectInputStream(new FileInputStream(new File(CONSTANT.FEATURE_LOCATION + "mapDatasetSubjects")));
		HashMap<Integer, Long> mapDatasetSubjects = (HashMap<Integer, Long>) ois.readObject();
		
		BufferedWriter bw = new BufferedWriter(new FileWriter(CONSTANT.DATAMODEL_LOCATION + "datamodel_all_self.txt"));
		
		int count = 0;
		int countSelf = 0;
		
		for (int i = 0; i < lstDatasetID.size(); i++) {
			for (int j = 0; j < lstDatasetID.size(); j++) {
				if (i == j) {
					int pref = getPreferenceValue(mapDatasetSubjects.get(lstDatasetID.get(i)).intValue());
					bw.write(lstDatasetID.get(i) + "\t" + lstDatasetID.get(i) + "\t" + pref + "\n");
					countSelf++;
				} else {

					int linkNum = 0;
					
					if (mapDatasetSameAsLinkedDatasetCount.get(lstDatasetID.get(i)) != null
							&& mapDatasetSameAsLinkedDatasetCount.get(lstDatasetID.get(i)).get(lstDatasetID.get(j)) != null) {
						linkNum = mapDatasetSameAsLinkedDatasetCount.get(lstDatasetID.get(i)).get(lstDatasetID.get(j));	
						
						if(linkNum > 0) {
							count++;
						}
					}
					int pref = getPreferenceValue(linkNum);
					bw.write(lstDatasetID.get(i) + "\t" + lstDatasetID.get(j) + "\t" + pref + "\n");
				}
			}
			bw.flush();
		}
		bw.close();
		System.out.println(count + " " + countSelf);
				
	}
	
	public static int getPreferenceValue (int linkNum) {
		
		int pref = 0;
		if (linkNum > 1000000) {
			pref = 7;
		} else if (linkNum >= 100000) {
			pref = 6;
		} else if (linkNum >= 10000) {
			pref = 5;
		} else if (linkNum >= 1000) {
			pref = 4;
		} else if (linkNum >= 100) {
			pref = 3;
		} else if (linkNum >= 10) {
			pref = 2;
		} else if (linkNum >= 1) {
			pref = 1;
		} else {
			pref = 0;
		}
		return pref;
	}
	
	public static void genDataModel2() throws ClassNotFoundException, IOException {
		
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File(CONSTANT.FEATURE_LOCATION + "sameAs Info/mapDatasetSameAsLinkedDatasetCount")));
		HashMap<String, HashMap<String, Integer>> mapDatasetSameAsLinkedDatasetCount = (HashMap<String,HashMap<String, Integer>>) ois.readObject();
		ois = new ObjectInputStream(new FileInputStream(new File(CONSTANT.FEATURE_LOCATION + "lstDatasetID")));
		ArrayList<Integer> lstDatasetID = (ArrayList<Integer>) ois.readObject();
		ois = new ObjectInputStream(new FileInputStream(new File(CONSTANT.FEATURE_LOCATION + "mapDatasetSubjects")));
		HashMap<Integer, Long> mapDatasetSubjects = (HashMap<Integer, Long>) ois.readObject();
		
		BufferedWriter bw = new BufferedWriter(new FileWriter(CONSTANT.DATAMODEL_LOCATION + "datamodel_exist_self.txt"));
		
			
		for (int i = 0; i < lstDatasetID.size(); i++) {
			for (int j = 0; j < lstDatasetID.size(); j++) {
				if (i == j) {
					int pref = getPreferenceValue(mapDatasetSubjects.get(lstDatasetID.get(i)).intValue());
					bw.write(lstDatasetID.get(i) + "\t" + lstDatasetID.get(i) + "\t" + pref + "\n");
				} else {

						
					if (mapDatasetSameAsLinkedDatasetCount.get(lstDatasetID.get(i)) != null
							&& mapDatasetSameAsLinkedDatasetCount.get(lstDatasetID.get(i)).get(lstDatasetID.get(j)) != null) {
						int linkNum = mapDatasetSameAsLinkedDatasetCount.get(lstDatasetID.get(i)).get(lstDatasetID.get(j));	
						
						bw.write(lstDatasetID.get(i) + "\t" + lstDatasetID.get(j) + "\t" + getPreferenceValue(linkNum) + "\n");
		
					}
				}
			}
			bw.flush();
		}
		bw.close();
				
	}

}
