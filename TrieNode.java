public class TrieNode<T>{
    HashMap<T,TrieNode> children;
    T c;
    boolean isEnd;
    int appearance;
    public TrieNode(){
        children = new HashMap<T,TrieNode>();
        isEnd = false;
        appearance = 0;
    }
}
