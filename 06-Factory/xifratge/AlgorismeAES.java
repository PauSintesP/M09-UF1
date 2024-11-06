package iticbcn.xifratge;

import iticbcn.xifratge.AlgorismeFactory; // replace with the actual package name

public class AlgorismeAES extends AlgorismeFactory {
    @Override
    public Xifrador creaXifrador() {
        return new XifradorAES();
    }
}
