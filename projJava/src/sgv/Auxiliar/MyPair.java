package sgv.Auxiliar;

public class MyPair<F,S> {
    private F first;
    private S second;

    public MyPair(F first, S second){
        this.first = first;
        this.second = second;
    }

    public static <F, S> MyPair<F, S> of(F first, S second) {
        return new MyPair<>(first,second);
    }

    public F getFirst(){
        return first;
    }

    public S getSecond(){
        return second;
    }

    public void setFirst(F first){
        this.first = first;
    }
    public void setSecond(S second){
        this.second = second;
    }

}
