
public class HashTable<K, V> {

	private int TABLE_SIZE = 128;
	HashEntry<K, V>[] table;
	String keyCtrl = "abcdefghijklmnopqrstuvwxyz";
	private int count = 0;
	int collision =0;

	public HashTable() {
		table = new HashEntry[TABLE_SIZE];
		for (int i = 0; i < TABLE_SIZE; i++)
			table[i] = null;
	}

	public int getTABLE_SIZE() {
		return TABLE_SIZE;
	}

	public void setTABLE_SIZE(int tABLE_SIZE) {
		TABLE_SIZE = tABLE_SIZE;
	}

	public HashEntry<K, V>[] getTable() {
		return table;
	}

	public String getKeyCtrl() {
		return keyCtrl;
	}

	public int getCount() {
		return count;
	}

	public void show() {
		for (int i = 0; i < table.length; i++) {
			if (table[i] != null) {
				System.out.println(i + " " + table[i].getKey());
				table[i].getValue().display();
			}
		}
	}

	public int SSF(K key) {
		int ret = 0;

		String str = key.toString().toLowerCase();
		for (int i = 0; i < str.length(); i++) {

			char c = str.charAt(i);
			for (int j = 0; j < keyCtrl.length(); j++) {

				if (c == keyCtrl.charAt(j)) {
					ret += j + 1;
					break;
				}
			}
		}
		return ret;
	}

	public int PAF(K key) {
		int ret = 0;
		String str = key.toString().toLowerCase();
		int expo = str.length();
		for (int i = 0; i < str.length(); i++) {
			expo--;
			char c = str.charAt(i);
			for (int j = 0; j < keyCtrl.length(); j++) {

				if (c == keyCtrl.charAt(j)) {
					ret += (j + 1) * Math.pow(33, expo);
					break;
				}
			}
		}
		return ret;
	}

	public int hashFunction(int ret) {
		return ret % TABLE_SIZE;
	}

	public int DH(K key, int i) {
		int arr = 0;
		int q = 7;
		int dk = q - (SSF(key) % q);
		int hk = hashFunction(SSF(key));
		
		arr= (hk + i * dk) % TABLE_SIZE;
		if (table[arr] == null  || table[arr].getKey().equals(key) ) {
			return arr;
		}
		else {
			while (table[arr] != null && !table[arr].getKey().equals(key)) {
				collision++;
				i++;
				arr= (hk + i * dk) % TABLE_SIZE;
				
			}
			return arr;
		}
	}
	public int LP(K key, int hash) {
		int i = 1;
		while (table[hash] != null && !table[hash].getKey().equals(key)) {
			hash = hashFunction(SSF(key) + i);
			collision++;
			i++;
			// System.out.println(hash);

		}
		return hash;
	}

//	public int DH(K key) {
//		
//	}
	public void put(K key, V value) {

		int hash = hashFunction(SSF(key));

		if (table[hash] != null && !table[hash].getKey().equals(key)) {
			collision++;
			hash = DH(key, 1);
			// System.out.println(hash);

		}
		if (table[hash] == null && count < (TABLE_SIZE * 8 / 10)) {
			table[hash] = new HashEntry(key, value);
			count++;
//			System.out.println(count);
		} else if (table[hash] == null && count >= (TABLE_SIZE * 8 / 10)) {
//			System.out.println("hello-------------");
//			show();
			resize();
//			System.out.println("bye-----bye-----------");
		} else {
			table[hash].add(value);
//			System.out.println("true");
		}
	}

	public int primeNumber(int number) {
		int upNumber;
		for (upNumber = number * 2; upNumber < number * 3; upNumber++) {
			boolean flag = true;
			for (int d = 2; d < upNumber; d++) {
				if (upNumber % d == 0) {
					flag = false;
					break;
				}
			}
			if (flag) {
				break;
			}
		}
		return upNumber;

	}

	public void resize() {
		HashTable temp = new HashTable();
		TABLE_SIZE = primeNumber(TABLE_SIZE);
		int counter = 0;
		temp.table = new HashEntry[TABLE_SIZE];

		for (int i = 0; i < table.length; i++) {
			if (table[i] != null) {
				int hash = hashFunction(SSF(table[i].getKey()));
				int j = 1;

				while (temp.table[hash] != null) {
					hash = hashFunction(SSF(table[i].getKey()) + j);
					collision++;
					j++;
				}

				if (temp.table[hash] == null) {

					temp.table[hash] = table[i];
					counter++;

				}

			}
		}
		this.table = new HashEntry[TABLE_SIZE];
		this.count = counter;
		this.table = temp.table;

	}

	public int getCollision() {
		return collision;
	}

	public void setCollision(int collision) {
		this.collision = collision;
	}

	public void search(K key) {

		int hash = hashFunction(SSF(key));
		int i = 1;
		if (table[hash].getKey() == null ) {
			System.out.println("This key is not exist !!!");
		} else {
			if (table[hash].getKey() != null ) {

				while (table[hash] != null && !table[hash].getKey().equals(key)) {
					hash = hashFunction(SSF(key) + i);
					i++;

				}

				
					System.out.println(hash + " " + table[hash].getKey());
					table[hash].getValue().display();
				}

		 else {
				System.out.println("This key is not exist");
			}

		}
	}

	public void remove(K key) {

		int hash = hashFunction(SSF(key));
		int i = 1;
		SingleLinkedList temp1 = new SingleLinkedList();
		HashEntry temp = new HashEntry("XREMOVEDWORDX", temp1.getHead());
		if (table[hash] == null || table[hash].getKey() == "XREMOVEDWORDX") {
			System.out.println("This key is not exist !!!");
		} else {
			while (!table[hash].getKey().equals(key)) {
				hash = hashFunction(SSF(key) + i);
				i++;

			}
			table[hash] = temp;
		}
		System.out.println("*** Perfectly Removed" );
//		System.out.print("***" ); table[hash].getValue().display();
	}
}