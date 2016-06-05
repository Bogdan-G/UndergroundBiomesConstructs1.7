/*
 * Decompiled with CFR 0_114.
 * 
 * Could not load the following classes:
 *  net.minecraftforge.common.config.Configuration
 *  net.minecraftforge.common.config.Property
 */
package Zeno410Utils;

import Zeno410Utils.Acceptor;
import Zeno410Utils.ConfigManager;
import Zeno410Utils.Mutable;
import Zeno410Utils.Streamable;
import Zeno410Utils.Trackers;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Logger;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

public abstract class Settings
implements Streamable {
    private ArrayList<Setting> settings = new ArrayList();
    private HashMap<String, Category> categories = new HashMap();

    public void readFrom(Configuration source) {
        for (Setting setting : this.settings) {
            setting.readFrom(source);
        }
    }

    public void copyTo(Configuration target) {
        for (Setting setting : this.settings) {
            setting.copyTo(target);
        }
    }

    public void readFrom(DataInput input) throws IOException {
        for (Setting setting : this.settings) {
            setting.readFrom(input);
        }
    }

    public void writeTo(DataOutput output) throws IOException {
        for (Setting setting : this.settings) {
            setting.writeTo(output);
        }
    }

    public Category category(String name) {
        Category result = this.categories.get(name);
        if (result != null) {
            return result;
        }
        result = new Category(name);
        this.categories.put(name, result);
        return result;
    }

    public Category general() {
        return this.category("general");
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    public class Category {
        public final String name;
        private String description;

        public Category(String name) {
            this.name = name;
        }

        public String description() {
            return this.description;
        }

        public void setDescription(String newDescription) {
            this.description = newDescription;
        }

        public Mutable<Boolean> booleanSetting(String key, String comment, boolean defaultValue) {
            return new BooleanSetting(this, key, comment, defaultValue);
        }

        public Mutable<Boolean> booleanSetting(String key, boolean defaultValue, String comment) {
            return new BooleanSetting(this, key, comment, defaultValue);
        }

        public Mutable<Integer> intSetting(String key, Integer defaultValue, String comment) {
            return new IntSetting(this, key, comment, defaultValue);
        }

        public Mutable<Integer> intSetting(String key, Integer defaultValue) {
            return new IntSetting(this, key, "", defaultValue);
        }

        public Mutable<String> stringSetting(String key, String defaultValue, String comment) {
            return new StringSetting(this, key, comment, defaultValue);
        }

        public Mutable<Double> doubleSetting(String key, Double defaultValue, String comment) {
            return new DoubleSetting(this, key, comment, defaultValue);
        }
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    protected abstract class Setting<Type>
    implements Mutable<Type>,
    Streamable {
        protected boolean set;
        protected Type value;
        protected Type defaultValue;
        final Category category;
        final String key;
        final String comment;
        private final Trackers<Type> trackers;

        Setting(Category category, String key, String comment, Type defaultValue) {
            this.set = false;
            this.trackers = new Trackers();
            this.category = category;
            this.key = key;
            this.comment = comment;
            this.defaultValue = defaultValue;
            Settings.this.settings.add(this);
        }

        @Override
        public void set(Type newValue) {
            this.value = newValue;
            this.set = true;
        }

        @Override
        public Type value() {
            if (this.set) {
                return this.value;
            }
            return this.defaultValue;
        }

        public void update(Type updated) {
            this.trackers.update(updated);
        }

        @Override
        public void informOnChange(Acceptor<Type> toInform) {
            this.trackers.informOnChange(toInform);
        }

        @Override
        public void stopInforming(Acceptor<Type> dontInform) {
            this.trackers.stopInforming(dontInform);
        }

        public boolean hasBeenSet() {
            return this.set;
        }

        public boolean exists(Configuration tested) {
            return tested.hasKey(this.category.name, this.key);
        }

        public void setValue(Property inFile) {
            Type oldValue = this.value();
            this.set = inFile.wasRead();
            this.value = this.set ? this.dataFrom(inFile) : this.defaultValue;
            if (oldValue == null && this.value() != null) {
                ConfigManager.logger.info("updating null to " + this.value().toString());
                this.update(this.value());
            } else if (!oldValue.equals(this.value())) {
                ConfigManager.logger.info("updating " + this.key + " to " + this.value().toString());
                this.update(this.value());
            }
        }

        public void readFrom(Configuration source) {
            this.setValue(this.propertyFrom(source));
        }

        public abstract Property propertyFrom(Configuration var1);

        public abstract Type dataFrom(Property var1);

        public abstract void copyTo(Configuration var1);
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    protected class DoubleSetting
    extends Setting<Double> {
        public DoubleSetting(Category category, String key, String comment, Double defaultValue) {
            super(category, key, comment, defaultValue);
        }

        @Override
        public void copyTo(Configuration target) {
            this.propertyFrom(target).set(((Double)this.value()).doubleValue());
        }

        @Override
        public Property propertyFrom(Configuration source) {
            if (this.comment.equals("")) {
                return source.get(this.category.name, this.key, ((Double)this.defaultValue).doubleValue());
            }
            return source.get(this.category.name, this.key, ((Double)this.defaultValue).doubleValue(), this.comment);
        }

        @Override
        public Double dataFrom(Property source) {
            return source.getDouble(((Double)this.defaultValue).doubleValue());
        }

        @Override
        public void readFrom(DataInput input) throws IOException {
            Double oldValue = (Double)this.value();
            this.set = input.readBoolean();
            this.value = input.readDouble();
            this.defaultValue = input.readDouble();
            if (oldValue == null && this.value() != null || !oldValue.equals(this.value())) {
                this.update(this.value());
            }
        }

        @Override
        public void writeTo(DataOutput output) throws IOException {
            output.writeBoolean(this.set);
            output.writeDouble((Double)this.value);
            output.writeDouble((Double)this.defaultValue);
        }
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    protected class StringSetting
    extends Setting<String> {
        public StringSetting(Category category, String key, String comment, String defaultValue) {
            super(category, key, comment, defaultValue);
        }

        @Override
        public void copyTo(Configuration target) {
            this.propertyFrom(target).set((String)this.value());
        }

        @Override
        public Property propertyFrom(Configuration source) {
            if (this.comment.equals("")) {
                return source.get(this.category.name, this.key, (String)this.defaultValue);
            }
            return source.get(this.category.name, this.key, (String)this.defaultValue, this.comment);
        }

        @Override
        public String dataFrom(Property source) {
            return source.getString();
        }

        @Override
        public void readFrom(DataInput input) throws IOException {
            String oldValue = (String)this.value();
            this.set = input.readBoolean();
            this.value = input.readUTF();
            this.defaultValue = input.readUTF();
            if (oldValue == null && this.value() != null || !oldValue.equals(this.value())) {
                this.update(this.value());
            }
        }

        @Override
        public void writeTo(DataOutput output) throws IOException {
            output.writeBoolean(this.set);
            output.writeUTF((String)this.value);
            output.writeUTF((String)this.defaultValue);
        }
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    protected class IntSetting
    extends Setting<Integer> {
        public IntSetting(Category category, String key, String comment, Integer defaultValue) {
            super(category, key, comment, defaultValue);
        }

        @Override
        public void copyTo(Configuration target) {
            this.propertyFrom(target).set(((Integer)this.value()).intValue());
        }

        @Override
        public Property propertyFrom(Configuration source) {
            if (this.comment.equals("")) {
                return source.get(this.category.name, this.key, ((Integer)this.defaultValue).intValue());
            }
            return source.get(this.category.name, this.key, ((Integer)this.defaultValue).intValue(), this.comment);
        }

        @Override
        public Integer dataFrom(Property source) {
            return source.getInt(((Integer)this.defaultValue).intValue());
        }

        @Override
        public void readFrom(DataInput input) throws IOException {
            Integer oldValue = (Integer)this.value();
            this.set = input.readBoolean();
            this.value = input.readInt();
            this.defaultValue = input.readInt();
            if (oldValue == null && this.value() != null || !oldValue.equals(this.value())) {
                this.update(this.value());
            }
        }

        @Override
        public void writeTo(DataOutput output) throws IOException {
            output.writeBoolean(this.set);
            output.writeInt((Integer)this.value);
            output.writeInt((Integer)this.defaultValue);
        }
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    protected class BooleanSetting
    extends Setting<Boolean> {
        public BooleanSetting(Category category, String key, String comment, boolean defaultValue) {
            super(category, key, comment, defaultValue);
        }

        @Override
        public void copyTo(Configuration target) {
            this.propertyFrom(target).set(((Boolean)this.value()).booleanValue());
        }

        @Override
        public Property propertyFrom(Configuration source) {
            if (this.comment.equals("")) {
                return source.get(this.category.name, this.key, ((Boolean)this.defaultValue).booleanValue());
            }
            return source.get(this.category.name, this.key, ((Boolean)this.defaultValue).booleanValue(), this.comment);
        }

        @Override
        public Boolean dataFrom(Property source) {
            return source.getBoolean(((Boolean)this.defaultValue).booleanValue());
        }

        @Override
        public void readFrom(DataInput input) throws IOException {
            Boolean oldValue = (Boolean)this.value();
            this.set = input.readBoolean();
            this.value = input.readBoolean();
            this.defaultValue = input.readBoolean();
            if (oldValue == null && this.value() != null || !oldValue.equals(this.value())) {
                this.update(this.value());
            }
        }

        @Override
        public void writeTo(DataOutput output) throws IOException {
            output.writeBoolean(this.set);
            output.writeBoolean((Boolean)this.value);
            output.writeBoolean((Boolean)this.defaultValue);
        }
    }

}

