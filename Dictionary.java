/**
 * Dictionary class that stores words and associates them with their definitions
 */
public class Dictionary {
    private HashMap<String,String> defPair;
    private Trie tree;

    /**
     * Constructor to initialize the Dictionary
     */
    public Dictionary() {
        tree = new Trie();
        defPair = new HashMap<>(1000);
    }

    /**
     * A method to add a new word to the dictionary
     * If the word is already in the dictionary, this method will change its
     * definition
     *
     * @param word       The word we want to add to our dictionary
     * @param definition The definition we want to associate with the word
     */
    public void add(String word, String definition) {
        // This means that the word was not found in the dictionary
        ObjPair<String,String> item = defPair.getRaw(word);
        if(item== null){  
            defPair.add(word, definition);
            tree.insert(word);
        } else {
            // Meaning that the word has been found already
            item.value = definition;
        }
    }

    /**
     * A method to remove a word from the dictionary
     *
     * @param word The word we want to remove from our dictionary
     */
    public void remove(String word) {
        // TODO
        String item = defPair.get(word);
        if(item == null){return;}
        defPair.remove(word);
        tree.delete(word);
    }

    /**
     * A method to get the definition associated with a word from the dictionary
     * Returns null if the word is not in the dictionary
     *
     * @param word The word we want to get the definition for
     * @return The definition of the word, or null if not found
     */
    public String getDefinition(String word) {
        return defPair.get(word);
    }

    /**
     * A method to get a string representation of the sequence of nodes which would
     * store the word
     * in a compressed trie consisting of all words in the dictionary
     * Returns null if the word is not in the dictionary
     *
     * @param word The word we want the sequence for
     * @return The sequence representation, or null if word not found
     */
    public String getSequence(String word) {
        return null;
    }

    /**
     * Gives the number of words in the dictionary with the given prefix
     *
     * @param prefix The prefix we want to count words for
     * @return The number of words that start with the prefix
     */
    public int countPrefix(String prefix) {
        return tree.prefixCount(prefix);
    }

    /**
     * Compresses the trie by combining nodes with single children
     * This operation should not change the behavior of any other methods
     */
    public void compress() {
        Trie t = tree.compress();
        this.tree = t;
    }
}