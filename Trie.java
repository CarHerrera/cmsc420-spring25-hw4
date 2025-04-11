public class Trie {
    private final TrieNode root;
    final private Character END = '$';
    public Trie(){
        root = new TrieNode();
    }

    public void insert(String s){
        TrieNode n = root;
        for(char c: s.toCharArray()){
            TrieNode spot = n.children.get(c);
            if(spot == null){
                TrieNode tmp = new TrieNode();
                spot = tmp;
                n.children.add(c,tmp);
                tmp.c = c;
            }
            n.appearance++;
            n = spot;
        }
        n.appearance++;
        TrieNode end = new TrieNode();
        end.c = END;
        n.children.add(END, end);
    }

    public boolean search(String s){
        TrieNode n = root;
        for(char c: s.toCharArray()){
            n = n.children.get(c);
            if(n == null){return false;}
        }
        
        return !(n.children.get(END) == null) ;
    }

    public boolean startsWith(String pre){
        TrieNode n = root;
        for(char c: pre.toCharArray()){
            n = n.children.get(c);
            if(n == null) return false;
        }
        return true;
    }

    public int startsWithCount(String pre){
        TrieNode n = root;
        for(char c: pre.toCharArray()){
            n = n.children.get(c);
            if(n == null) return -1;
        }
        return n.appearance;
    }
    public boolean delete(String s){
        return deleteHelper(root, s , 0);
    }

    private boolean deleteHelper(TrieNode n, String s, int index){
        if(index == s.length()){
            if(n.children.get(END) == null) {return false;}
            return n.children.count == 0;
        }
        return false;
    }
    public static void main(String[] args) {
        Trie t = new Trie();
        t.insert("Carlos");
        t.insert("Car");
        t.insert("Cart");

        System.out.println(t.search("Carlos"));
        System.out.println(t.search("Carl"));
        System.out.println(t.search("Duhhh"));
        System.out.println(t.search("D"));
        System.out.println(t.search("C"));
    }
}
