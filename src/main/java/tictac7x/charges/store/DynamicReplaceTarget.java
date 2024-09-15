package tictac7x.charges.store;

import java.util.concurrent.Callable;

public class DynamicReplaceTarget {
    public final String target;
    public final Callable<String> replace;

    public DynamicReplaceTarget(final String target, final Callable<String> replace) {
        this.target = target;
        this.replace = replace;
    }
}
