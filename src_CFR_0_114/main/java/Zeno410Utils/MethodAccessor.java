/*
 * Decompiled with CFR 0_114.
 */
package Zeno410Utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class MethodAccessor<ObjectType> {
    private Method method;
    private final String methodName;

    public MethodAccessor(String _fieldName) {
        this.methodName = _fieldName;
    }

    private Method method(ObjectType example) {
        Class classObject = example.getClass();
        if (this.method == null) {
            try {
                this.setMethod(classObject);
            }
            catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
        return this.method;
    }

    private void setMethod(Class classObject) throws IllegalAccessException {
        do {
            Method[] methods = classObject.getDeclaredMethods();
            for (int i = 0; i < methods.length; ++i) {
                if (!methods[i].getName().contains(this.methodName)) continue;
                this.method = methods[i];
                this.method.setAccessible(true);
                return;
            }
        } while ((classObject = classObject.getSuperclass()) != Object.class);
        throw new RuntimeException(this.methodName + " not found in class " + classObject.getName());
    }

    public void run(ObjectType object) {
        try {
            this.method(object).invoke(object, new Object[0]);
        }
        catch (IllegalArgumentException ex) {
            throw new RuntimeException(ex);
        }
        catch (IllegalAccessException ex) {
            throw new RuntimeException(ex);
        }
        catch (InvocationTargetException ex) {
            throw new RuntimeException(ex);
        }
    }
}

