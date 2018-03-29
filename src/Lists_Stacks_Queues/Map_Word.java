package Lists_Stacks_Queues;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.jws.Oneway;

public class Map_Word {
	
	/**
	 * �������������Ƿ�ֻ��һ����ĸ�Ĳ��
	 */
	private static boolean oneCharOff(String word1, String word2){
		if(word1.length() != word2.length())
			return false;
		
		for(int i=0; i<word1.length(); i++)
			if(word1.charAt(i) != word2.charAt(i))
				return false;
		return true;
	}

	
	
	/**
	 * ��������һЩ������Ϊ�ؼ��ֺ�ֻ��һ����ĸ�ϵĲ�ͬ��һ�е�����Ϊ�ؼ��ֵ�ֵ
	 * �����Щ����minWords�����߸����ͨ��һ����ĸ�滻�õ��ĵ��ʵĵ���
	 */
	public static void printHighChaneables(Map<String, List<String>> adjWords, int minWords){
		
		for(Map.Entry<String, List<String>> entry : adjWords.entrySet()){
			List<String> words = entry.getValue();
			
			if(words.size()>=minWords){
				System.out.print(entry.getKey()+" ( ");
				System.out.println(words.size() + " ): ");
				for(String w : words)
					System.out.println(" " + w);
				System.out.println();
			}
		}
	}
	
	/**
	 * ¼��һϵ�е��ʣ��������ʰ�����Ҫ��¼��Map����
	 * ����һ��Map���������ö�����һЩ������Ϊ�ؼ��֣�����ֻ��һ����ĸ����ͬ��һ�е�����Ϊ�ؼ��ֵ�ֵ
	 */
	//�㷨һ��
	//���㷨¼��89000�����ʵ��ֵ�����75��
	public static Map<String, List<String>> computeAdjacentWords(List<String> theWords){
		
		Map<String,List<String>> adjWords = new TreeMap<>();
		
		//������ת����һ��shuz
		String [] words = new String[theWords.size()];
		theWords.toArray(words);
		
		for(int i = 0; i<words.length; i++)
			for(int j = i+1; j<words.length; j++)
				if(oneCharOff(words[i], words[j])){
					update(adjWords, words[i], words[j]);
					update(adjWords, words[j], words[i]);
				}
		return adjWords;
	}
	
	private static <KeyType> void update(Map<KeyType, List<String>> m,
			KeyType key, String value){
		List<String> lst = m.get(key);
		if(lst == null){
			lst = new ArrayList<>();
			m.put(key, lst);
		}
		
		lst.add(value);
	}
	
	
	//�㷨����
	//���ƣ��Ƚ����ʰ����ȷ��飬�ٷ���Ƚ�
	//���㷨¼��89000�����ʵ��ֵ�����16��
	public static Map<String, List<String>> computeAdjacentWords_B(List<String> theWords){
		
		Map<String,List<String>> adjWords = new TreeMap<>();
		Map<Integer,List<String>> wordsByLength = new TreeMap<>();
		
		//�����ʰ����ȷ���
		for(String w : theWords){
			update(wordsByLength, w.length(), w);
		}
		
		for(List<String> groupsWords : wordsByLength.values()){

			String [] words = new String[groupsWords.size()];
			groupsWords.toArray(words);
			
			for(int i=0; i<words.length; i++)
				for(int j=i+1; j<words.length; j++)
					if(oneCharOff(words[i], words[j])){
						update(adjWords, words[i], words[j]);
						update(adjWords, words[j], words[i]);
					}
		}
		return adjWords;
	}
	
	
	//�㷨����
	public static Map<String, List<String>> computeAdjacentWords_C(List<String> theWords){
		
		Map<String,List<String>> adjWords = new TreeMap<>();
		Map<Integer,List<String>> wordsByLength = new TreeMap<>();
		
		//�����ʰ����ȷ���
		for(String w : theWords){
			update(wordsByLength, w.length(), w);
		}
		
		for(Map.Entry<Integer, List<String>> entry : wordsByLength.entrySet()){
			
			List<String> groupsWords = entry.getValue();
			int groupNum = entry.getKey();
			
			for(int i=0; i<groupNum; i++){
				
				Map<String, List<String>> repToWord = new TreeMap<>();
				
				for(String str : groupsWords){
					String rep = str.substring(0,i)+str.substring(i+1);   //��ͬһ���ȵ���ĸ�У��ֱ��ȡһ����ĸ����ʣ������ؼ���
					update(repToWord, rep, str);
				}
				
				for(List<String> wordClique : repToWord.values()){
					if(wordClique.size()>=2)
						for(String s1 : wordClique)
							for(String s2: wordClique)
								if(s1 != s2)
									update(adjWords, s1, s2);
				}
			}
		}
		return adjWords;
	}
}
