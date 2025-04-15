import java.util.ArrayList;
class ObjPair<K,V>{
    K key;
    V value;
    public ObjPair(K key, V val){
        this.key = key;
        this.value = val;
    }
}
public class HashMap<K,V>{
    
    Object[] values;
    ArrayList<K> keys;
    int size;
    int count;
    double load_fac;
    public HashMap(){
        size = 37;
        values = new Object[size];
        keys = new ArrayList<>();
        this.count = 0;
        this.load_fac = (double) count/size;
    }
    public HashMap(int size){
        this.size = size;
        values = new Object[size];
        keys = new ArrayList<>();
        this.count = 0;
        this.load_fac = count/size;
    }
    public int HashFunc(K c, int rep){
        int val = c.hashCode() < 0 ? (c.hashCode() * -1): c.hashCode();
        return (int)(val + Math.pow(rep,2))%size;
    }
    public void add(K c, V T){
        ObjPair<K,V> item = new ObjPair(c,T);
        int index = HashFunc(c,0);
        ObjPair<K,V>  spot = (ObjPair<K,V> ) values[index];
        if(spot == null){
            values[index] = item;     
            keys.add(c);
        } else{
            int i = 1;
            while(spot != null){
                index = HashFunc(c,i);
                spot = (ObjPair<K,V> ) values[index];
                i++;
            }
            values[index] = item;
            keys.add(c);
        }
        count++;
        load_fac = (double) count/size;
        if(load_fac >= .6){
            reHash();
        }
    }
    public void reHash(){
        Object[] shell = new Object[size * 3];
        this.size = size*3;
        for(int i = 0; i<(size/3); i++){
            if(values[i] == null){continue;}
            else {
                ObjPair<K,V>  t = (ObjPair<K,V> ) values[i];
                int j = 0;
                int newIndex = HashFunc(t.key, j);
                while(shell[newIndex] != null){
                    j++;
                    newIndex = HashFunc(t.key,j);
                }
                shell[newIndex] = t;
            }
        }
        this.values = shell;
        load_fac = (double) count/size;
    }

    public V get(K c){
        int index = HashFunc(c,0);
        ObjPair<K,V>  spot = (ObjPair<K,V> ) values[index];
        if(spot == null){return null;}
        if(spot.key.equals(c)){
            return spot.value;
        } else {
            int i = 1;
            while(spot != null){
                index = HashFunc(c,i);
                spot = (ObjPair<K,V> ) values[index];
                if(spot != null){
                    if(spot.key == c){return spot.value;}
                }
            }
            return null;
        }
        
    }
    public ObjPair<K,V>  getRaw(K c){
        int index = HashFunc(c,0);
        ObjPair<K,V>  spot = (ObjPair<K,V> ) values[index];
        if(spot == null){return null;}
        if(spot.key.equals(c)){
            return spot;
        } else {
            int i = 1;
            while(spot != null){
                index = HashFunc(c,i);
                spot = (ObjPair<K,V> ) values[index];
                if(spot != null){
                    if(spot.key == c){return spot;}
                }
            }
            return null;
        }
        
    }
    public ArrayList<K> getKeys(){
        return keys;
    }
    public void remove(K c){
        int j = 0;
        int index = HashFunc(c,j);
        ObjPair spot = (ObjPair) values[index];
        if(spot == null){return;}
        if(spot.key.equals(c)){
            values[index] = null;
        } else {
            while(spot != null){
                j++;
                index = HashFunc(c,j);
                spot = (ObjPair) values[index];
            }
            values[index] = null;
        }
        keys.remove(c);
        count--;
        load_fac = (double) count/size;
    }


    public static void main(String[] args) {
        HashMap<String, String> test = new HashMap<>(5);
        test.add("cat", "A small domesticated carnivorous mammal");
        test.add("category","A class or division of things");
        test.add("categorize","A device for hurling objects");
        test.add("catapult", "A device for hurling objects");
        String d = test.get("Cringe");
        test.remove("Carlos");
        System.out.println(d);
    }
}

