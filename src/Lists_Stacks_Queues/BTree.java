package Lists_Stacks_Queues;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

public class BTree<K, V> {
	
	private static final int DEFAULT_T = 2;
	
	//B�����ڵ�
	private BTreeNode<K, V> root;
	//����B���Ķ��壺B��ÿ���ڵ�Ǹ��ڵ�Ĺؼ���n����(t-1)<= n <= (2t-1)
	private int t = DEFAULT_T;
	//�Ǹ��ڵ�����С�ļ�ֵ��
	private int minKeySize = t-1;
	//�Ǹ��ڵ������ļ�ֵ��
	private int maxKeySize = 2*t-1;
	//���ıȽ϶���
	private Comparator<K> kComparator;
	
	/**
	 * ����һ��������ֵ������Ȼ����ʽ
	 */
	public BTree(){
		root = new BTreeNode<K,V>();
		root.setLeaf(true);
	}
	public BTree(int t){
		this();
		this.t = t;
		minKeySize = t-1;
		maxKeySize = 2*t-1;
	}
	
	/**
	 * �Ը����ļ�ֵ�ȽϺ���������һ��B��
	 * @param kComparator  ��ֵ�ȽϺ�������
	 */
	public BTree (Comparator<K> kComparator){
		root = new BTreeNode<>();
		root.setLeaf(true);
		this.kComparator = kComparator;
	}
	
	public BTree(Comparator<K> kComparator,int t){
		this(kComparator);
		this.t = t;
		minKeySize = t - 1;
		maxKeySize = 2*t - 1;
	}
	
	
	/**
	 * B���ڵ��еļ�ֵ��
	 * B���ڵ��д洢���Ǽ�ֵ��
	 * ͨ��������ֵ
	 */
	private static class Entry<K,V>{
		private K key;
		private V value;
		
		public Entry(K k, V v){
			this.key = k;
			this.value = v;
		}
		
		public V getValue(){
			return value;
		}
		
		public K getKey(){
			return key;
		}
		
		public void setValue(V value){
			this.value = value;
		}
		
		public String toString(){
			return key + "��" + value;
		}
		
	}
	
	
	/**
	 * ��B���ڵ�������������ֵ�ķ��ؽ��
	 * 
	 * �ý������������ɣ���һ���ֱ�ʾ�����Ƿ�ɹ�
	 * ����ɹ����ڶ����ֱ�ʾ������ֵ��B���ڵ��е�λ��
	 * ���ʧ�ܣ��ڶ����ֱ�ʾ�����Ҿ�����Ӧ�ò����λ��
	 */
	private static class SearchResult<V>{
		private boolean exist;
		private int index;
		private V value;
		
		public SearchResult(boolean exist, int index){
			this.exist = exist;
			this.index = index;
		}
		
		public SearchResult(boolean exist, int index, V value){
			this(exist, index);
			this.value = value;
		}
		
		public boolean isExist(){
			return exist;
		}
		
		public int getIndex(){
			return index;
		}
		
		public V getValue() {
			return value;
		}
	}
	
	
	/**
	 * B���еĽڵ�
	 * 
	 * ��Ҫ���ǲ�������µĴ�ȡ
	 */
	private static class BTreeNode<K,V>{
		//�ڵ��������ǽ�����
		private List<Entry<K, V>> entrys;
		//�ڽڵ���ӽڵ�
		private List<BTreeNode<K, V>> children;
		//�Ƿ�ΪҶ�ӽڵ�
		private boolean leaf;
		//���ıȽϺ�������
		private Comparator<K> kComparator;
		
		private BTreeNode(){
			entrys = new ArrayList<Entry<K, V>>();
			children = new ArrayList<BTreeNode<K, V>>();
			leaf = false;
		}
		
		public BTreeNode(Comparator<K> kComparator){
			this();
			this.kComparator = kComparator;
		}
		
		public boolean isLeaf(){
			return leaf;
		}
		
		public void setLeaf(boolean leaf){
			this.leaf = leaf;
		}
		
		
		/**
		 * ������ĸ�����
		 * ����Ƿ�Ҷ�ӽڵ㣬�ɶ���
		 * �ýڵ���ӽڵ����Ϊceil(m/2-1)��m
		 */
		//���عؼ��ָ���
		public int size(){
			return entrys.size();
		}
		
		int compare(K key1, K key2){
			return kComparator == null?((Comparable<K>) key1).compareTo(key2):kComparator.compare(key2, key1);
		}
		
		/**
		 * �ڽڵ��в��Ҹ�����ֵ
		 * 
		 * ����ڵ��д��ڸ�����ֵ���򷵻�һ��SearchResult
		 * ��ʶ�˴β��ҳɹ��������ļ��ڽڵ��е������͸����ļ�������ֵ
		 * 
		 * ���������
		 * ��ʶ�˴β���ʧ�ܣ������ļ�Ӧ�ò����λ�ã��ü��Ĺ���ֵΪnull
		 * 
		 * �������ʧ�ܣ����ؽ���е�������Ϊ[0, {@link #size()}]��
		 * ������ҳɹ������ؽ���е�������Ϊ[0, {@link #size()} - 1]
		 * 
		 * ����һ�����ֲ����㷨�����Ա�֤ʱ�临�Ӷ�ΪO(log(t))��
		 */
		public SearchResult<V> searchKey(K key){
			int low = 0;
			int high = entrys.size()-1;
			int mid = 0;
			
			while(low <= high){
				mid = (low+high)/2;    // ����ôд�ɣ�BTreeʵ���У�l+h���������
				Entry<K, V> entry = entrys.get(mid);
				if(compare(entry.getKey(), key) == 0 ) //�ҵ�
					break;
				else if (compare(entry.getKey(), key) > 0) {
					high = mid - 1;
				}else {             //compare(entry.getKey(), key) < 0
					low = mid+1;
				}
			}
			boolean result = false;
			int index = 0;
			V value = null;
			if(low<=high)          //˵�����ҳɹ�����Ϊ���û�ҵ�����Ҫlow>high��������ѭ��
			{
				result = true;
				index = mid;       //index ��ʾԪ������λ��
				value = entrys.get(index).getValue();
			}else{
				result = false;
				index = low;       //index��ʾԪ��Ӧ�ò����λ��
			}
			return new SearchResult<>(result, index,value);
		}
		
		
		/**
		 * ����������׷�ӽڵ��ĩβ
		 * ��Ҫȷ�����ø÷����󣬽ڵ��е���ǰ��չؼ���һ�ǽ�����
		 * 
		 * @param bTreeNode ��������
		 */
		public void addEntry(Entry<K, V> entry){
			entrys.add(entry);
		}
		
		/**
		 * ɾ������������entry
		 * 
		 * @param index  ����������
		 * @return       ��������������
		 */
		public Entry<K, V> removeEntry(int index){
			return entrys.remove(index);
		}
		
		/**
		 * �õ��ڵ��и�����������
		 * @param index  ����������
		 * @return       �ڵ��и�����������
		 */
		public Entry<K, V> entryAt(int index){
		    return entrys.get(index);
		}
		
		/**
		 * ����ڵ��д��ڸ����ļ���������������ֵ
		 * �������
		 * 
		 * @param entry  ��������
		 * @return       ����ڵ�֮ǰ�����ڸ����ļ�������null�����򷵻ظ�����֮ǰ������ֵ
		 */
		public V putEntry(Entry<K, V> entry){
			
			SearchResult<V> result = searchKey(entry.getKey());
			if(result.exist){  //�Ѵ���,�����
				V oldValue = entrys.get(result.getIndex()).getValue();
				entrys.get(result.getIndex()).setValue(entry.getValue());
				return oldValue;
			}else {            //û�У������
				insertEntry(entry, result.getIndex());
				return null;
			}
		}
		
		/**
		 * �ڸýڵ��в���������
		 * �÷�����֤����֮�����ֵ�����Էǽ����š�
		 * 
		 * �����÷�����ʱ�临�Ӷ�ΪO(t)��
		 * 
		 * @param entry  �����ļ�ֵ
		 * @return true������ɹ���
		 * @return false ����ʧ��
		 */
		public boolean insertEntry(Entry<K, V> entry){
			
			SearchResult<V> result = searchKey(entry.getKey());
			if(result.isExist())
				return false;
			else{
				insertEntry(entry,result.getIndex());
				return true;
			}
			
		}
		
		/**
		 * �ڸýڵ��и���������λ�ò���������
		 * ����Ҫ�Լ���֤���������ȷ��λ�á�
		 * 
		 * @param entry  �����ļ�ֵ
		 * @param index  ����������
		 */
		public void insertEntry(Entry<K, V> entry, int index){
			
			/*
			 * ͨ���½�һ��ArrayList��ʵ�ֲ�����ĺܶ��ģ���������
			 * Ҫ��������C�е�reallocate�ͺ��ˡ�
			 */
			List<Entry<K, V>> newEntrys = new ArrayList<>();
			int i = 0;
			
			// index = 0����index = keys.size()��û������
			for(; i < index; ++ i)
				newEntrys.add(entrys.get(i));
			newEntrys.add(entry);
			for(; i < entrys.size(); ++ i)
				newEntrys.add(entrys.get(i));
			entrys.clear();
			entrys = newEntrys;
		}
		
		/**
		 * ���ؽڵ��и����������ӽڵ㡣
		 * ��Ҫ�Լ���֤�����������ǺϷ��ġ�
		 * 
		 * @param index  ����������
		 * @return       ����������Ӧ���ӽڵ�
		 */
		public BTreeNode<K, V> childAt(int index){
			if(isLeaf())
				throw new UnsupportedOperationException();
			return children.get(index);
		}
		
		/**
		 * ���������ӽڵ�׷�ӵ��ýڵ��ĩβ��
		 * 
		 * @param child �������ӽڵ�
		 */
		public void addChild(BTreeNode<K, V> child)
		{
			children.add(child);
		}
		
		/**
		 * ɾ���ýڵ��и�������λ�õ��ӽڵ�
		 * ��Ҫ�Լ���֤�����������ǺϷ��ġ�
		 */
		public void removeChild(int index){
			children.remove(index);
		}
		
		/**
		 * ���������ӽڵ���뵽�ýڵ��и�������
		 * ��λ�á�
		 * 
		 * @param child - �������ӽڵ�
		 * @param index - �ӽڵ�������λ��
		 */
		public void insertChild(BTreeNode<K, V> child, int index){
			List<BTreeNode<K, V>> newChildren = new ArrayList<BTreeNode<K,V>>();
			int i = 0 ;
			for(;i<index;++i){
				newChildren.add(children.get(i));
			}
			newChildren.add(child);
			for(; i<children.size(); ++i)
				newChildren.add(children.get(i));
			children = newChildren;
		}

	}
	
	int compare(K key1, K key2){
		return kComparator == null?((Comparable<K>)key1).compareTo(key2) : kComparator.compare(key1,key2);
	}
	
	/**
	 * ���������ļ�
	 * 
	 * @param key  �����ļ�
	 * @return  ������ֵ
	 */
	public V search(K key) {
		return search(root,key);
	}
	
	/**
	 * ���Ը����ڵ�Ϊ���������У��ݹ�����
	 * 
	 * @param node �����ĸ��ڵ�
	 * @param key  �����ļ�ֵ
	 * @return
	 */
	private V search(BTreeNode<K, V> node, K key){
		
		SearchResult<V> result = node.searchKey(key);
		if(result.isExist())
			return result.getValue();
		else{
			if(node.isLeaf())
				return null;
			else {
				//��ʱ�����Ҳ�����result.getIndex����Ӧ�����λ�ã����������ڵ�λ��
				search(node.childAt(result.getIndex()),key);
			}
		}
		return null;
	}
	
	
	/**
	 * ����һ�����ӽڵ�
	 * ע�⣺
	 * ��Ҫ��֤�������ӽڵ������ڵ�
	 * @param parentNode    ���ڵ�
	 * @param childrenNode  ���ӽڵ�
	 * @param index         ���ӽڵ��ڸ��ڵ��е�����
	 */
	private void splitNode(BTreeNode<K, V> parentNode, BTreeNode<K, V> childNode, int index){
		
		//ע��
		assert childNode.size() == maxKeySize;
		
		BTreeNode<K, V> siblingNode = new BTreeNode<K,V>(kComparator);
		siblingNode.setLeaf(childNode.isLeaf());
		//�����ӽڵ�������Ϊ[t,2t-2]��(t-1)��������µĽڵ���
		for(int i = 0; i < minKeySize; ++i)
			siblingNode.addEntry(childNode.entryAt(t+i));
		//��ȡ���ӽڵ��е��м��������Ϊt-1
		Entry<K, V> entry = childNode.entryAt(t-1);
		//ɾ�����ӽڵ�������Ϊ[t-1,2t-2]��t����
		for(int i = maxKeySize-1;i>=t-1;--i)
			childNode.removeEntry(i);
		if(!childNode.isLeaf()) //������ӽڵ㲻��Ҷ�ڵ㣬���账������
		{
			//�����ӽڵ�������Ϊ[t,2t-1]��t���ӽڵ�����µĽڵ�                              ע�⣺�Ƿ���Ҷ�ӽڵ㣬���������Ŀ��ͬ��
			for(int i = 0 ; i<minKeySize+1;++i)
				siblingNode.addChild(childNode.childAt(t+i));
			//ɾ�����ӽڵ�������Ϊ[t, 2t - 1]��t���ӽڵ�
			for(int i = maxKeySize; i >= t; -- i)
				childNode.removeChild(i);
		}
		// ��entry���븸�ڵ�
		parentNode.insertEntry(entry, index);
		// ���½ڵ���븸�ڵ�
		parentNode.insertChild(siblingNode, index + 1);
	}
	
	/**
	 * ��һ�������ڵ��в����������
	 * @param node   ������ ��
	 * @param entry  ��������
	 * @return true      ���B���в����ڸ����������false
	 */
	private boolean insertNotFull(BTreeNode<K, V> node, Entry<K, V> entry){
		assert node.size()<maxKeySize;
		
		if(node.isLeaf())     //�����Ҷ�ӽڵ㣬ֱ�Ӳ���
			return node.insertEntry(entry);
		else{
			/*
			 * �ҵ�entry�ڸ����ڵ�Ӧ�ò����λ�ã�
			 * ��ôentryӦ�ò����λ�ö�Ӧ��������
			 */
			SearchResult<V> result = node.searchKey(entry.getKey());
			//������ڣ�ֱ�ӷ���ʧ��
			if(result.isExist())
				return false;
			BTreeNode<K, V> childNode = node.childAt(result.getIndex());
			if(childNode.size() == 2*t-1)  //����ӽڵ������ڵ�
			{
				//�ȷ���
				splitNode(node, childNode, result.getIndex());
				/*
				 * �������entry�ļ����ڷ���֮�������ɵļ�������Ҫ�����������ұ�
				 * �������
				 */
				if(compare(entry.getKey(), node.entryAt(result.getIndex()).getKey())>0)
					childNode = node.childAt(result.getIndex()+1);
			}
			return insertNotFull(childNode, entry);
		}
	}
	
	/**
	 * ��B���ֲ�������ļ�ֵ��
	 * @param key    �� 
	 * @param value  ֵ
	 * @return 
	 */
	public boolean insert(K key, V value){
		if(root.size() == maxKeySize)  //������ڵ����ˣ���B������
		{
			BTreeNode<K, V> newRoot = new BTreeNode<K,V>(kComparator);
			newRoot.setLeaf(false);
			newRoot.addChild(root);
			splitNode(newRoot, root, 0);
			root = newRoot;
		}
		return insertNotFull(root, new Entry<K, V>(key, value));
	}
	
	/**
	 * ������ڸ����ļ�������¹�����ֵ
	 * ��������������
	 * 
	 * @param node   �����ڵ�
	 * @param entry  ��������
	 * @return ���B���в����ڸ����������true������false
	 */
	private V putNotFull(BTreeNode<K, V> node, Entry<K, V> entry){
		
		assert node.size() < maxKeySize;
		
		if(node.isLeaf())    //�����Ҷ�ӽڵ㣬ֱ�Ӳ���
			return node.putEntry(entry);
		else{
			/*
			 * �ҵ�entry�ڸ����ڵ�Ӧ�ò����λ�ã���ôentryӦ�ò�����
			 * ��λ�ö�Ӧ��������
			 */
			SearchResult<V> result = node.searchKey(entry.getKey());
			// ������ڣ������
			if(result.isExist())
				return node.putEntry(entry);
			BTreeNode<K, V> childNode = node.childAt(result.getIndex());
			if(childNode.size() == 2*t-1)  //����ӽڵ������ڵ�
			{
				//���ȷ���
				splitNode(node, childNode, result.getIndex());
				/* �������entry�ļ����ڷ���֮����������ļ�������Ҫ�����������ұߣ�
				 * ������ߡ�
				 */
				if(compare(entry.getKey(), node.entryAt(result.getIndex()).getKey()) > 0)
					childNode = node.childAt(result.getIndex() + 1);
			}
			return putNotFull(childNode, entry);
		}
	}
	
	/**
	 * ���B���д��ڸ����ļ��������ֵ��
	 * ������롣
	 * 
	 * @param key - ��
	 * @param value - ֵ
	 * @return ���B���д��ڸ����ļ����򷵻�֮ǰ��ֵ������null
	 */
	public V put(K key, V value)
	{
		if(root.size() == maxKeySize) // ������ڵ����ˣ���B������
		{
			BTreeNode<K, V> newRoot = new BTreeNode<K, V>(kComparator);
			newRoot.setLeaf(false);
			newRoot.addChild(root);
			splitNode(newRoot, root, 0);
			root = newRoot;
		}
		return putNotFull(root, new Entry<K, V>(key, value));
	}
	
	/**
	 * ��B��ɾ��һ�����������������
	 * 
	 * @param key   �����ļ�
	 * @return      ���B���д��ڸ�������������򷵻�ɾ���������null
	 */
	public Entry<K, V> delete(K key){
		return delete(root, key);
	}
	
	
	public void output(){
		Queue<BTreeNode<K, V>> queue = new LinkedList<BTreeNode<K,V>>();
		queue.offer(root);
		while(!queue.isEmpty()){
			BTreeNode<K, V> node = queue.poll();
			for(int i = 0; i < node.size(); ++ i)
				System.out.print(node.entryAt(i) + " ");
			System.out.println();
			if(!node.isLeaf())
			{
				for(int i = 0; i <= node.size(); ++ i)
					queue.offer(node.childAt(i));
			}
		}
	}
	
	/**
	 * ���Ը���<code>node</code>Ϊ����������ɾ����������������
	 * <p/>
	 * ɾ����ʵ��˼����ο����㷨���ۡ��ڶ���ĵ�18�¡�
	 * 
	 * @param node - �����Ľڵ�
	 * @param key - �����ļ�
	 * @return ���B���д��ڸ�������������򷵻�ɾ���������null
	 */
	private Entry<K, V> delete(BTreeNode<K, V> node, K key)
	{
		// �ù�����Ҫ��֤���ԷǸ��ڵ�ִ��ɾ������ʱ����ؼ��ָ�������Ϊt��
		assert node.size() >= t || node == root;
		
		SearchResult<V> result = node.searchKey(key);
		/*
		 * ��Ϊ���ǲ��ҳɹ��������0 <= result.getIndex() <= (node.size() - 1)��
		 * ���(result.getIndex() + 1)���������
		 */
		if(result.isExist())
		{
			// 1.����ؼ����ڽڵ�node�У�������Ҷ�ڵ㣬��ֱ��ɾ����
			if(node.isLeaf())
				return node.removeEntry(result.getIndex());
			else
			{
				// 2.a ����ڵ�node��ǰ��key���ӽڵ��������t����
				BTreeNode<K, V> leftChildNode = node.childAt(result.getIndex());
				if(leftChildNode.size() >= t)
				{
					// ʹ��leftChildNode�е����һ�������node����Ҫɾ������
					node.removeEntry(result.getIndex());
					node.insertEntry(leftChildNode.entryAt(leftChildNode.size() - 1), result.getIndex());
					// �ݹ�ɾ�����ӽڵ��е����һ����
					return delete(leftChildNode, leftChildNode.entryAt(leftChildNode.size() - 1).getKey());
				}
				else
				{
					// 2.b ����ڵ�node�к���key���ӽڵ��������t���ؼ���
					BTreeNode<K, V> rightChildNode = node.childAt(result.getIndex() + 1);
					if(rightChildNode.size() >= t)
					{
						// ʹ��rightChildNode�еĵ�һ�������node����Ҫɾ������
						node.removeEntry(result.getIndex());
						node.insertEntry(rightChildNode.entryAt(0), result.getIndex());
						// �ݹ�ɾ�����ӽڵ��еĵ�һ����
						return delete(rightChildNode, rightChildNode.entryAt(0).getKey());
					}
					else // 2.c ǰ��key�ͺ���key���ӽڵ㶼ֻ����t-1����
					{
						Entry<K, V> deletedEntry = node.removeEntry(result.getIndex());
						node.removeChild(result.getIndex() + 1);
						// ��node����key���������rightChildNode�е���ϲ���leftChildNode
						leftChildNode.addEntry(deletedEntry);
						for(int i = 0; i < rightChildNode.size(); ++ i)
							leftChildNode.addEntry(rightChildNode.entryAt(i));
						// ��rightChildNode�е��ӽڵ�ϲ���leftChildNode������еĻ�
						if(!rightChildNode.isLeaf())
						{
							for(int i = 0; i <= rightChildNode.size(); ++ i)
								leftChildNode.addChild(rightChildNode.childAt(i));
						}
						return delete(leftChildNode, key);
					}
				}
			}
		}
		else
		{
			/*
			 * ��Ϊ���ǲ���ʧ�ܵ������0 <= result.getIndex() <= node.size()��
			 * ���(result.getIndex() + 1)�������
			 */
			if(node.isLeaf()) // ����ؼ��ֲ��ڽڵ�node�У�������Ҷ�ڵ㣬��ʲô����������Ϊ�ùؼ��ֲ��ڸ�B����
			{
				//logger.info("The key: " + key + " isn't in this BTree.");
				return null;
			}
			BTreeNode<K, V> childNode = node.childAt(result.getIndex());
			if(childNode.size() >= t) // // ����ӽڵ��в�����t�����ݹ�ɾ��
				return delete(childNode, key);
			else // 3
			{
				// �Ȳ����ұߵ��ֵܽڵ�
				BTreeNode<K, V> siblingNode = null;
				int siblingIndex = -1;
				if(result.getIndex() < node.size()) // �������ֵܽڵ�
				{
					if(node.childAt(result.getIndex() + 1).size() >= t)
					{
						siblingNode = node.childAt(result.getIndex() + 1);
						siblingIndex = result.getIndex() + 1;
					}
				}
				// ����ұߵ��ֵܽڵ㲻������������������ߵ��ֵܽڵ�
				if(siblingNode == null)
				{
					if(result.getIndex() > 0) // �������ֵܽڵ�
					{
						if(node.childAt(result.getIndex() - 1).size() >= t)
						{
							siblingNode = node.childAt(result.getIndex() - 1);
							siblingIndex = result.getIndex() - 1;
						}
					}
				}
				// 3.a ��һ�������ֵܽڵ����ٰ���t����
				if(siblingNode != null)
				{
					if(siblingIndex < result.getIndex()) // ���ֵܽڵ���������
					{
						childNode.insertEntry(node.entryAt(siblingIndex), 0);
						node.removeEntry(siblingIndex);
						node.insertEntry(siblingNode.entryAt(siblingNode.size() - 1), siblingIndex);
						siblingNode.removeEntry(siblingNode.size() - 1);
						// �����ֵܽڵ�����һ�������Ƶ�childNode
						if(!siblingNode.isLeaf())
						{
							childNode.insertChild(siblingNode.childAt(siblingNode.size()), 0);
							siblingNode.removeChild(siblingNode.size());
						}
					}
					else // ���ֵܽڵ���������
					{
						childNode.insertEntry(node.entryAt(result.getIndex()), childNode.size() - 1);
						node.removeEntry(result.getIndex());
						node.insertEntry(siblingNode.entryAt(0), result.getIndex());
						siblingNode.removeEntry(0);
						// �����ֵܽڵ�ĵ�һ�������Ƶ�childNode
						// childNode.insertChild(siblingNode.childAt(0), childNode.size() + 1);
						if(!siblingNode.isLeaf())
						{
							childNode.addChild(siblingNode.childAt(0));
							siblingNode.removeChild(0);
						}
					}
					return delete(childNode, key);
				}
				else // 3.b ������������ҽڵ㶼����t-1����
				{
					if(result.getIndex() < node.size()) // �������ֵܣ�ֱ���ں���׷��
					{
						BTreeNode<K, V> rightSiblingNode = node.childAt(result.getIndex() + 1);
						childNode.addEntry(node.entryAt(result.getIndex()));
						node.removeEntry(result.getIndex());
						node.removeChild(result.getIndex() + 1);
						for(int i = 0; i < rightSiblingNode.size(); ++ i)
							childNode.addEntry(rightSiblingNode.entryAt(i));
						if(!rightSiblingNode.isLeaf())
						{
							for(int i = 0; i <= rightSiblingNode.size(); ++ i)
								childNode.addChild(rightSiblingNode.childAt(i));
						}
					}
					else // ������ڵ㣬��ǰ�����
					{
						BTreeNode<K, V> leftSiblingNode = node.childAt(result.getIndex() - 1);
						childNode.insertEntry(node.entryAt(result.getIndex() - 1), 0);
						node.removeEntry(result.getIndex() - 1);
						node.removeChild(result.getIndex() - 1);
						for(int i = leftSiblingNode.size() - 1; i >= 0; -- i)
							childNode.insertEntry(leftSiblingNode.entryAt(i), 0);
						if(!leftSiblingNode.isLeaf())
						{
							for(int i = leftSiblingNode.size(); i >= 0; -- i)
								childNode.insertChild(leftSiblingNode.childAt(i), 0);
						}
					}
					// ���node��root����node�������κ�����
					if(node == root && node.size() == 0)
						root = childNode;
					return delete(childNode, key);
				}
			}
		}
	}
		
		
		public static void main(String[] args)
		{
			Random random = new Random();
			BTree<Integer, Integer> btree = new BTree<Integer, Integer>(3);
			List<Integer> save = new ArrayList<Integer>();
			for(int i = 0; i < 10; ++ i)
			{
				int r = random.nextInt(100);
				save.add(r);
				System.out.println(r);
				btree.insert(r, r);
			}
			
			System.out.println("----------------------");
			btree.output();
			System.out.println("----------------------");
			btree.delete(save.get(0));
			btree.output();
		}
	
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	


