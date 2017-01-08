package me.gelli.atlauncher;


import android.graphics.drawable.Drawable;

public class AppModel {
    private Drawable icon;
    private String label;
    private String packageName;

    public Drawable getIcon() {
        return icon;
    }

    void setIcon(Drawable icon) {
        this.icon = icon;
    }

    public String getLabel() {
        return label;
    }

    void setLabel(String label) {
        this.label = label;
    }

    String getPackageName() {
        return packageName;
    }

    void setPackageName(String packageName) {
        this.packageName = packageName;
    }
}
