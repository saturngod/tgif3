package android.support.v4.app;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.v4.graphics.drawable.IconCompat;

public class Person {
    private static final String ICON_KEY = "icon";
    private static final String IS_BOT_KEY = "isBot";
    private static final String IS_IMPORTANT_KEY = "isImportant";
    private static final String KEY_KEY = "key";
    private static final String NAME_KEY = "name";
    private static final String URI_KEY = "uri";
    @Nullable
    IconCompat mIcon;
    boolean mIsBot;
    boolean mIsImportant;
    @Nullable
    String mKey;
    @Nullable
    CharSequence mName;
    @Nullable
    String mUri;

    public static class Builder {
        @Nullable
        IconCompat mIcon;
        boolean mIsBot;
        boolean mIsImportant;
        @Nullable
        String mKey;
        @Nullable
        CharSequence mName;
        @Nullable
        String mUri;

        Builder(Person person) {
            this.mName = person.mName;
            this.mIcon = person.mIcon;
            this.mUri = person.mUri;
            this.mKey = person.mKey;
            this.mIsBot = person.mIsBot;
            this.mIsImportant = person.mIsImportant;
        }

        @NonNull
        public Builder setName(@Nullable CharSequence charSequence) {
            this.mName = charSequence;
            return this;
        }

        @NonNull
        public Builder setIcon(@Nullable IconCompat iconCompat) {
            this.mIcon = iconCompat;
            return this;
        }

        @NonNull
        public Builder setUri(@Nullable String str) {
            this.mUri = str;
            return this;
        }

        @NonNull
        public Builder setKey(@Nullable String str) {
            this.mKey = str;
            return this;
        }

        @NonNull
        public Builder setBot(boolean z) {
            this.mIsBot = z;
            return this;
        }

        @NonNull
        public Builder setImportant(boolean z) {
            this.mIsImportant = z;
            return this;
        }

        @NonNull
        public Person build() {
            return new Person(this);
        }
    }

    @NonNull
    public static Person fromBundle(@NonNull Bundle bundle) {
        Bundle bundle2 = bundle.getBundle(ICON_KEY);
        return new Builder().setName(bundle.getCharSequence(NAME_KEY)).setIcon(bundle2 != null ? IconCompat.createFromBundle(bundle2) : null).setUri(bundle.getString(URI_KEY)).setKey(bundle.getString(KEY_KEY)).setBot(bundle.getBoolean(IS_BOT_KEY)).setImportant(bundle.getBoolean(IS_IMPORTANT_KEY)).build();
    }

    @RequiresApi(28)
    @RestrictTo({Scope.LIBRARY_GROUP})
    @NonNull
    public static Person fromAndroidPerson(@NonNull android.app.Person person) {
        return new Builder().setName(person.getName()).setIcon(person.getIcon() != null ? IconCompat.createFromIcon(person.getIcon()) : null).setUri(person.getUri()).setKey(person.getKey()).setBot(person.isBot()).setImportant(person.isImportant()).build();
    }

    Person(Builder builder) {
        this.mName = builder.mName;
        this.mIcon = builder.mIcon;
        this.mUri = builder.mUri;
        this.mKey = builder.mKey;
        this.mIsBot = builder.mIsBot;
        this.mIsImportant = builder.mIsImportant;
    }

    @NonNull
    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        bundle.putCharSequence(NAME_KEY, this.mName);
        bundle.putBundle(ICON_KEY, this.mIcon != null ? this.mIcon.toBundle() : null);
        bundle.putString(URI_KEY, this.mUri);
        bundle.putString(KEY_KEY, this.mKey);
        bundle.putBoolean(IS_BOT_KEY, this.mIsBot);
        bundle.putBoolean(IS_IMPORTANT_KEY, this.mIsImportant);
        return bundle;
    }

    @NonNull
    public Builder toBuilder() {
        return new Builder(this);
    }

    @RequiresApi(28)
    @RestrictTo({Scope.LIBRARY_GROUP})
    @NonNull
    public android.app.Person toAndroidPerson() {
        return new android.app.Person.Builder().setName(getName()).setIcon(getIcon() != null ? getIcon().toIcon() : null).setUri(getUri()).setKey(getKey()).setBot(isBot()).setImportant(isImportant()).build();
    }

    @Nullable
    public CharSequence getName() {
        return this.mName;
    }

    @Nullable
    public IconCompat getIcon() {
        return this.mIcon;
    }

    @Nullable
    public String getUri() {
        return this.mUri;
    }

    @Nullable
    public String getKey() {
        return this.mKey;
    }

    public boolean isBot() {
        return this.mIsBot;
    }

    public boolean isImportant() {
        return this.mIsImportant;
    }
}
