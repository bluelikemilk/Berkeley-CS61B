public class OffByN implements CharacterComparator {
    private int N;

    public OffByN(int off) {
        N = off;
    }

    @Override
    /** Return true is x and y are off by N*/
    public boolean equalChars(char x, char y) {
        if (Math.abs(x - y) == N) {
            return true;
        }
        return false;
    }

}
