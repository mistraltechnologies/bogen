public class Widget<P, Q> {
    private P p;
    private Q q;

    public Widget(P p) {
        this.p = p;
    }

    public P getP() {
        return p;
    }

    public Q getQ() {
        return q;
    }

    public void setQ(Q q) {
        this.q = q;
    }
}