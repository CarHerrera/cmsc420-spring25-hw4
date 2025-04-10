public class TrieNode {
    HashMap children;
    String content;
    String definition;
    boolean isEnd;
    public TrieNode(){
        children = new HashMap();
        isEnd = false;
    }
}
