/*
 * Decompiled with CFR 0_114.
 */
package exterminatorJeff.undergroundBiomes.api;

import exterminatorJeff.undergroundBiomes.api.UBIDs;

public class Names {
    private final String internal;

    public Names(String internalName) {
        this.internal = internalName;
    }

    public final String internal() {
        return this.internal;
    }

    public final String external() {
        return UBIDs.publicName(this.internal);
    }

    public final String iconName() {
        return UBIDs.iconName(this.internal);
    }

    public final RuntimeException duplicateRegistry() {
        return new RuntimeException("duplication registry for Block " + this.external());
    }

    public String toString() {
        return this.external();
    }
}

