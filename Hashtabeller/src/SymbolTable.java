public class SymbolTable {
	private static final int INIT_CAPACITY = 7;

	/* Number of key-value pairs in the symbol table */
	private int N;
	/* Size of linear probing table */
	private int M;
	/* The keys */
	private String[] keys;
	/* The values */
	private Character[] vals;

	/**
	 * Create an empty hash table - use 7 as default size
	 */
	public SymbolTable() {
		this(INIT_CAPACITY);
	}

	/**
	 * Create linear probing hash table of given capacity
	 */
	public SymbolTable(int capacity) {
		N = 0;
		M = capacity;
		keys = new String[M];
		vals = new Character[M];
	}

	/**
	 * Return the number of key-value pairs in the symbol table
	 */
	public int size() {
		return N;
	}

	/**
	 * Is the symbol table empty?
	 */
	public boolean isEmpty() {
		return size() == 0;
	}

	/**
	 * Does a key-value pair with the given key exist in the symbol table?
	 */
	public boolean contains(String key) {
		return get(key) != null;
	}

	/**
	 * Hash function for keys - return keys value between 0 and M-1
	 */
	public int hash(String key) {
		int i;
		int v = 0;

		for (i = 0; i < key.length(); i++) {
			v += key.charAt(i);
		}
		return v % M;
	}

	/**
	 * Insert the key-value pair into the symbol table
	 */
	public void put(String key, Character val) {

		int hash = hash(key);
		if (val == null || val == '\n') { //if the entered value is empty, run delete on the key
			delete(key);
			return;
		}

		for (int i = hash; i < M; i++){

			if (keys[i] != null){
				if (keys[i].equals(key)){ //if key is already in table, replace val and exit
					vals[i] = val;
					break;
				}
			}

			if (keys[i] == null) { // if keys[i] = null, put element here and break
				keys[i] = key;
				vals[i] = val;
				N++;
				break;
			}

			if (i + 1 == M){ //loop table at the end
				i = -1;
			} else if (i + 1 == hash) { //break after one full loop
				break;
			}
		}

	}

	/**
	 * Return the value associated with the given key, null if no such value
	 */
	public Character get(String key) {

		int hash = hash(key);

		for (int i = hash; i < M; i++) {
			if (keys[hash] == null)
				break;
			if (keys[i] != null && keys[i].equals(key))
				return this.vals[i];
			if (i + 1 == M) // if at the end of the table, loop to beginning
				i = -1;
			if (i + 1 == hash) // break after one full loop
				break;
		}

		return null;
	} 

	/**
	 * Delete the key (and associated value) from the symbol table
	 */
	// Fundera på vad som borde ske när man
	// har en tabell med följande element och tar bort het:
	// index: 0    1    2    3    4    5    6
	// key:   the  info fusk teh  null null het
	// hash:  (6)  (1)  (0)  (6)  -    -    (6)
	public void delete(String key) {

		int hash = hash(key);

		for (int i = hash; i < M; i++){

			if (keys[i] != (null) && keys[i].equals(key)){
				keys[i] = null;
				vals[i] = null;
				N--;

				for (int j = i + 1; j <= M; j++){

					if (j == M)
						j = 0;

					if (keys[j] == null)
						break;

					int hashJ = hash(keys[j]);

					if (hashJ != j) {
						if (keys[hashJ] == null) {
							put(keys[j], vals[j]);
							keys[j] = null;
							vals[j] = null;
							N--;
						} else if (keys[hashJ] != null) {
							if (j != 0 && keys[j-1] == null) { // Check if index != 0 and index-1 == null
								put(keys[j], vals[j]); // Put index on first available position
								keys[j] = null;
								vals[j] = null;
								N--;
							}
						}
					}

					if (j + 1 == M){
						j = -1;
					} else if (j + 1 == i){
						break;
					}
				}
				break;
			}

			if (i + 1 == M){
				i = -1;
			} else if (i + 1 == hash){
				break;
			}
		}

	}


	public void dump() {
		String str = "";

		for (int i = 0; i < M; i++) {
			str = str + i + ". " + vals[i];
			if (keys[i] != null) {
				str = str + " " + keys[i] + " (";
				str = str + hash(keys[i]) + ")";
			} else {
				str = str + " -";
			}
			System.out.println(str);
			str = "";
		}
	}
}
