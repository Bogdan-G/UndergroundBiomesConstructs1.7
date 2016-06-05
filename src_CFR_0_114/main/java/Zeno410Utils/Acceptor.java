/*
 * Decompiled with CFR 0_114.
 */
package Zeno410Utils;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public abstract class Acceptor<Type> {
    public abstract void accept(Type var1);

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    public static class OneShotRedirector<RedirectedType>
    extends Acceptor<RedirectedType> {
        private Acceptor<RedirectedType> target;

        @Override
        public void accept(RedirectedType redirected) {
            this.target.accept(redirected);
            this.target = null;
        }

        public void redirectTo(Acceptor<RedirectedType> newTarget) {
            this.target = newTarget;
        }
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    public static class Ignorer<IgnoredType>
    extends Acceptor<IgnoredType> {
        @Override
        public void accept(IgnoredType ignored) {
        }
    }

}

