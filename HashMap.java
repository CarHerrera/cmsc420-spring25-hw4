public class HashMap {
    TrieNode[] arr;
    int size;
    int count;
    public HashMap(){
        size = 30;
        arr = new TrieNode[size];
    }
    public int HashFunc(Character c){
        return c.hashCode() < 0 ? (c.hashCode() * -1) % size : c.hashCode()%size;
    }
    public void add(Character c, TrieNode T){
        int index = HashFunc(c);
        TrieNode spot = arr[index];
        if(spot == null){
            arr[index] = T;     
        } 
        count++;
    }

    public TrieNode get(Character c){
        int index = HashFunc(c);
        TrieNode spot = arr[index];
        return spot;
    }
}
