/*
 * Decompiled with CFR 0_114.
 */
package Zeno410Utils;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class Pair<TypeA, TypeB> {
    public TypeA a;
    public TypeB b;

    public Pair(TypeA theA, TypeB theB) {
        this.a = theA;
        this.b = theB;
    }

    public String toString() {
        return this.a.toString() + " " + this.b.toString();
    }

    public static <A, B> Pair<A, B> of(A a, B b) {
        return new Pair<A, B>(a, b);
    }

    public int hashCode() {
        return this.a.hashCode() + this.b.hashCode();
    }

    public boolean equals(Object compared) {
        if (compared instanceof Pair) {
            Pair comparedPair = (Pair)compared;
            if (this.a.equals(comparedPair.a) && this.b.equals(comparedPair.b)) {
                return true;
            }
        }
        return false;
    }
}

