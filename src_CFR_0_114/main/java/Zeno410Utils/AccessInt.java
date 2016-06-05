/*
 * Decompiled with CFR 0_114.
 */
package Zeno410Utils;

import java.lang.reflect.Field;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class AccessInt<ObjectType> {
    private Field field;
    private final String fieldName;

    public AccessInt(String _fieldName) {
        this.fieldName = _fieldName;
    }

    private Field field(ObjectType example) {
        Class classObject = example.getClass();
        if (this.field == null) {
            try {
                this.setField(classObject);
            }
            catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
        return this.field;
    }

    private void setField(Class classObject) throws IllegalAccessException {
        do {
            Field[] fields = classObject.getDeclaredFields();
            for (int i = 0; i < fields.length; ++i) {
                if (!fields[i].getName().contains(this.fieldName)) continue;
                this.field = fields[i];
                this.field.setAccessible(true);
                return;
            }
        } while ((classObject = classObject.getSuperclass()) != Object.class);
        throw new RuntimeException(this.fieldName + " not found in class " + classObject.getName());
    }

    public int get(ObjectType object) {
        try {
            return this.field(object).getInt(object);
        }
        catch (IllegalArgumentException ex) {
            throw new RuntimeException(ex);
        }
        catch (IllegalAccessException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void setField(ObjectType object, int fieldValue) {
        try {
            this.field(object).setInt(object, fieldValue);
        }
        catch (IllegalArgumentException ex) {
            throw new RuntimeException(ex);
        }
        catch (IllegalAccessException ex) {
            throw new RuntimeException(ex);
        }
    }
}

