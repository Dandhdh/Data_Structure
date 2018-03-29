package Lists_Stacks_Queues;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.jws.Oneway;

public class Map_Word {
	
	/**
	 * 测试两个单词是否只是一个字母的差别
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
	 * 给出包含一些单词作为关键字和只在一个字母上的不同的一列单词作为关键字的值
	 * 输出那些具有minWords个或者更多个通过一个字母替换得到的单词的单词
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
	 * 录入一系列单词，并将单词按如下要求录入Map对象
	 * 计算一个Map对象函数，该对象以一些单词作为关键字，而以只在一个字母处不同的一列单词作为关键字的值
	 */
	//算法一：
	//该算法录入89000个单词的字典运行75秒
	public static Map<String, List<String>> computeAdjacentWords(List<String> theWords){
		
		Map<String,List<String>> adjWords = new TreeMap<>();
		
		//将集合转储到一个shuz
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
	
	
	//算法二：
	//改善：先将单词按长度分组，再分组比较
	//该算法录入89000个单词的字典运行16秒
	public static Map<String, List<String>> computeAdjacentWords_B(List<String> theWords){
		
		Map<String,List<String>> adjWords = new TreeMap<>();
		Map<Integer,List<String>> wordsByLength = new TreeMap<>();
		
		//将单词按长度分组
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
	
	
	//算法三：
	public static Map<String, List<String>> computeAdjacentWords_C(List<String> theWords){
		
		Map<String,List<String>> adjWords = new TreeMap<>();
		Map<Integer,List<String>> wordsByLength = new TreeMap<>();
		
		//将单词按长度分组
		for(String w : theWords){
			update(wordsByLength, w.length(), w);
		}
		
		for(Map.Entry<Integer, List<String>> entry : wordsByLength.entrySet()){
			
			List<String> groupsWords = entry.getValue();
			int groupNum = entry.getKey();
			
			for(int i=0; i<groupNum; i++){
				
				Map<String, List<String>> repToWord = new TreeMap<>();
				
				for(String str : groupsWords){
					String rep = str.substring(0,i)+str.substring(i+1);   //在同一长度的字母中，分别截取一个字母，将剩余的做关键字
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
