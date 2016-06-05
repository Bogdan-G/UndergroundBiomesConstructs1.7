/*
 * Decompiled with CFR 0_114.
 */
package Zeno410Utils;

import java.lang.reflect.Field;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class Accessor<ObjectType, FieldType> {
    private Field field;
    private final Class FieldTypeVar;

    public Accessor(Class _FieldType) {
        this.FieldTypeVar = _FieldType;
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
                if (!this.FieldTypeVar.equals(fields[i].getType()) && !this.FieldTypeVar.isAssignableFrom(fields[i].getClass())) continue;
                this.field = fields[i];
                this.field.setAccessible(true);
                return;
            }
        } while ((classObject = classObject.getSuperclass()) != Object.class);
        throw new RuntimeException(this.FieldTypeVar.getName() + " not found in class " + classObject.getName());
    }

    public FieldType get(ObjectType object) {
        try {
            return (FieldType)this.field(object).get(object);
        }
        catch (IllegalArgumentException ex) {
            throw new RuntimeException(ex);
        }
        catch (IllegalAccessException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void setField(ObjectType object, FieldType fieldValue) {
        try {
            this.field(object).set(object, fieldValue);
        }
        catch (IllegalArgumentException ex) {
            throw new RuntimeException(ex);
        }
        catch (IllegalAccessException ex) {
            throw new RuntimeException(ex);
        }
    }
}

