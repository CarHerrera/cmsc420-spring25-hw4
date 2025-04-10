
class LinkedList{
    class Node<T>{
        Node<T> next, back;
        T data;
        public Node(T data){
            this.data = data;
            this.next = this.back = null;
        }
        public Node<T> getNext(){return this.next;}
        public Node<T> getBack(){return this.back;}
    }
    Node<Character> head;
    Node<Character> tail;
    public LinkedList(){
        
    }
}
public class HashMap {
    private int size;
    // private St
    
    public HashMap(){
        size = 100;
    }
    public HashMap(int size){
        this.size = size;
    }
    public int HashFunc(Character c){
        return c.hashCode() < 0 ? (c.hashCode() * -1) % size : c.hashCode()%size;
    }
    public void add(Character c, TrieNode T){

    }
}
