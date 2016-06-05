/*
 * Decompiled with CFR 0_114.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Function
 *  com.google.common.base.Predicate
 */
package Zeno410Utils;

import Zeno410Utils.Streamer;
import com.google.common.base.Function;
import com.google.common.base.Predicate;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Collections;
import java.util.Iterator;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public abstract class Maybe<T>
implements Iterable<T> {
    public abstract boolean isKnown();

    public abstract T otherwise(T var1);

    public abstract Maybe<T> otherwise(Maybe<T> var1);

    public abstract <U> Maybe<U> to(Function<? super T, ? extends U> var1);

    public abstract Maybe<Boolean> query(Predicate<? super T> var1);

    public static <T> Maybe<T> unknown() {
        return new Maybe<T>(){

            @Override
            public boolean isKnown() {
                return false;
            }

            @Override
            public Iterator<T> iterator() {
                return Collections.emptyList().iterator();
            }

            @Override
            public T otherwise(T defaultValue) {
                return defaultValue;
            }

            @Override
            public Maybe<T> otherwise(Maybe<T> maybeDefaultValue) {
                return maybeDefaultValue;
            }

            @Override
            public <U> Maybe<U> to(Function<? super T, ? extends U> mapping) {
                return .unknown();
            }

            @Override
            public Maybe<Boolean> query(Predicate<? super T> mapping) {
                return .unknown();
            }

            public String toString() {
                return "unknown";
            }

            public boolean equals(Object obj) {
                return false;
            }

            public int hashCode() {
                return 0;
            }
        };
    }

    public static <T> Maybe<T> definitely(T theValue) {
        return new DefiniteValue<T>(theValue);
    }

    public static <Type> Streamer<Maybe<Type>> streamer(Streamer<Type> parent) {
        return new MaybeStreamer<Type>(parent);
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    public static class MaybeStreamer<Type>
    extends Streamer<Maybe<Type>> {
        private final Streamer<Type> streamer;

        public MaybeStreamer(Streamer<Type> streamer) {
            this.streamer = streamer;
        }

        @Override
        public Maybe<Type> readFrom(DataInput target) throws IOException {
            boolean exists = target.readBoolean();
            if (exists) {
                return Maybe.definitely(this.streamer.readFrom(target));
            }
            return Maybe.unknown();
        }

        @Override
        public void writeTo(Maybe<Type> written, DataOutput target) throws IOException {
            Iterator<Type> toWrite = written.iterator();
            if (toWrite.hasNext()) {
                target.writeBoolean(true);
                this.streamer.writeTo(written.iterator().next(), target);
            } else {
                target.writeBoolean(false);
            }
        }
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    private static class DefiniteValue<T>
    extends Maybe<T> {
        private final T theValue;

        public DefiniteValue(T theValue) {
            this.theValue = theValue;
        }

        @Override
        public boolean isKnown() {
            return true;
        }

        @Override
        public Iterator<T> iterator() {
            return Collections.singleton(this.theValue).iterator();
        }

        @Override
        public T otherwise(T defaultValue) {
            return this.theValue;
        }

        @Override
        public Maybe<T> otherwise(Maybe<T> maybeDefaultValue) {
            return this;
        }

        @Override
        public <U> Maybe<U> to(Function<? super T, ? extends U> mapping) {
            return DefiniteValue.definitely(mapping.apply(this.theValue));
        }

        @Override
        public Maybe<Boolean> query(Predicate<? super T> mapping) {
            return DefiniteValue.definitely(mapping.apply(this.theValue));
        }

        public String toString() {
            return "definitely " + this.theValue.toString();
        }

        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || this.getClass() != o.getClass()) {
                return false;
            }
            DefiniteValue that = (DefiniteValue)o;
            return this.theValue.equals(that.theValue);
        }

        public int hashCode() {
            return this.theValue.hashCode();
        }
    }

}

