import java.util.ArrayList;
public class Trie {
    class TrieCompresssed extends Trie{
        public final TrieNode<String> root;
        public final String END = "$";
        public TrieCompresssed(){
            this.root = new TrieNode<>();
        }
        public void insert(String s){
            TrieNode<String> n = root;
            TrieNode<String> spot = n.children.get(s);
            if(spot == null){
                TrieNode<String> tmp = new TrieNode();
                spot = tmp;
                n.children.add(s,tmp);
                tmp.c = s;
            }
            n.appearance++;
            spot.children.add(String.valueOf(END), new TrieNode<String>());
            // return spot;
        }

        public TrieNode<String> contains(TrieNode<String> n, String s){            
            return n.children.get(s);
        }
    }
    private final TrieNode<Character> root;
    final private Character END = '$';
    public Trie(){
        root = new TrieNode<Character>();
    }

    public void insert(String s){
        TrieNode<Character> n = root;
        for(char c: s.toCharArray()){
            TrieNode<Character> spot = n.children.get(c);
            if(spot == null){
                TrieNode<Character> tmp = new TrieNode();
                spot = tmp;
                n.children.add(c,tmp);
                tmp.c = c;
            }
            n.appearance++;
            n = spot;
        }
        n.appearance++;
        TrieNode<Character> end = new TrieNode();
        end.c = END;
        n.children.add(END, end);
    }

    public boolean search(String s){
        TrieNode<Character> n = root;
        for(char c: s.toCharArray()){
            n = n.children.get(c);
            if(n == null){return false;}
        }
        
        return !(n.children.get(END) == null) ;
    }

    public boolean startsWith(String pre){
        TrieNode<Character> n = root;
        for(char c: pre.toCharArray()){
            n = n.children.get(c);
            if(n == null) return false;
        }
        return true;
    }

    public int prefixCount(String pre){
        TrieNode<Character> n = root;
        for(char c: pre.toCharArray()){
            n = n.children.get(c);
            if(n == null) return -1;
        }
        return n.appearance;
    }
    public String getSequence(String word){
        TrieNode<Character> n = root;
        StringBuilder sb = new StringBuilder();
        StringBuilder copy = new StringBuilder();
        for(char c: word.toCharArray()){
            n = n.children.get(c);
            if(n == null) return null;
            sb.append(c);
            copy.append(c);
            if(copy.toString().equals(word)){
                return sb.toString();
            }
            if(n.children.count > 1 ){
                sb.append("-");
            }
        }
        return sb.toString();
    }
    public boolean delete(String s){
        return deleteHelper(root, s , 0);
    }

    private boolean deleteHelper(TrieNode<Character> n, String s, int index){
        if(index == s.length()){
            if(n.children.get(END) == null) {return false;}
            n.children.remove(END);
            n.appearance--;
            return n.children.count == 0;
        }
        n.appearance--;
        char c = s.charAt(index);
        TrieNode<Character> child =  n.children.get(c);
        if(child == null){return false;}
        boolean shouldDelete = deleteHelper(child, s, index+1);
        if(shouldDelete){
            n.children.remove(c);   
            return ((n.children.count == 0) && (n.children.get(END) == null));
        }
        return false;
    }

    public TrieCompresssed compress(){
        ArrayList<Character> keys = root.children.getKeys();
        TrieCompresssed newTree = new TrieCompresssed();
        for(char c: keys){
            String s = String.valueOf(c);
            TrieNode<Character> child =  root.children.get(c);
            compressHelper(child, newTree.root, s);
            // System.out.println(c);
        }

        return newTree;
    }
    public void compressHelper(TrieNode<Character> n, TrieNode<String> acc, String s){
        TrieNode<String> tNode;
        // This is is suppossed to recursively go through the chars of an entry
        // Once the entry finds an "end" it gets added to the compressed tree
        // A new trieNode is created in case there are more words that follow after (i.e. cat and catapult will catch 
        // -apult under)
        if(n.children.get(END) != null){
            tNode = new TrieNode<>();            
            tNode.c = s;
            acc.children.add(s, tNode);
            n.children.remove(END);
            TrieNode<String> End = new TrieNode<>();
            End.c = "$";
            tNode.children.add(String.valueOf(END), End);
            s = "";
            acc = tNode;
        }
        ArrayList<Character> kids = n.children.getKeys();
        for(char c:kids){
            TrieNode<Character> child = n.children.get(c);
            if (c != '$'){
                String tmp = s + String.valueOf(c);
                compressHelper(child,acc,tmp);
            } 
            
        }
    }
    public static void main(String[] args) {
        Trie t = new Trie();
        t.insert("apple");
        t.insert("apricot");
        t.insert("catapult");
        t.insert("avocado");
        System.out.println(t.prefixCount("a"));
        System.out.println(t.prefixCount("cat"));
    }
}


