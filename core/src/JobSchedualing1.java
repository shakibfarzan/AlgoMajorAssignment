public class JobSchedualing1 extends BackTracking<Integer> {



    public JobSchedualing1(Integer[] x) {
        super(x);
    }

    @Override
    protected Integer[] nodeValues(int k) {
        return new Integer[0];
    }

    @Override
    protected double bound(int k) {
        return 0;
    }

    @Override
    protected boolean isSolution(int k) {
        return false;
    }

    @Override
    protected void solutionFound(int k) {

    }
}
