public class TrieNode {
    HashMap children;
    Character c;
    boolean isEnd;
    int appearance;
    public TrieNode(){
        children = new HashMap();
        isEnd = false;
        appearance = 0;
    }
}
