package android.support.v4.media;

import android.media.Rating;
import android.os.Build.VERSION;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.util.Log;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public final class RatingCompat implements Parcelable {
    public static final Creator<RatingCompat> CREATOR = new C01151();
    public static final int RATING_3_STARS = 3;
    public static final int RATING_4_STARS = 4;
    public static final int RATING_5_STARS = 5;
    public static final int RATING_HEART = 1;
    public static final int RATING_NONE = 0;
    private static final float RATING_NOT_RATED = -1.0f;
    public static final int RATING_PERCENTAGE = 6;
    public static final int RATING_THUMB_UP_DOWN = 2;
    private static final String TAG = "Rating";
    private Object mRatingObj;
    private final int mRatingStyle;
    private final float mRatingValue;

    /* renamed from: android.support.v4.media.RatingCompat$1 */
    static class C01151 implements Creator<RatingCompat> {
        C01151() {
        }

        public RatingCompat createFromParcel(Parcel parcel) {
            return new RatingCompat(parcel.readInt(), parcel.readFloat());
        }

        public RatingCompat[] newArray(int i) {
            return new RatingCompat[i];
        }
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    @Retention(RetentionPolicy.SOURCE)
    public @interface StarStyle {
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Style {
    }

    RatingCompat(int i, float f) {
        this.mRatingStyle = i;
        this.mRatingValue = f;
    }

    public String toString() {
        String str;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Rating:style=");
        stringBuilder.append(this.mRatingStyle);
        stringBuilder.append(" rating=");
        if (this.mRatingValue < 0.0f) {
            str = "unrated";
        } else {
            str = String.valueOf(this.mRatingValue);
        }
        stringBuilder.append(str);
        return stringBuilder.toString();
    }

    public int describeContents() {
        return this.mRatingStyle;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.mRatingStyle);
        parcel.writeFloat(this.mRatingValue);
    }

    public static RatingCompat newUnratedRating(int i) {
        switch (i) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
                return new RatingCompat(i, RATING_NOT_RATED);
            default:
                return 0;
        }
    }

    public static RatingCompat newHeartRating(boolean z) {
        return new RatingCompat(1, z ? true : false);
    }

    public static RatingCompat newThumbRating(boolean z) {
        return new RatingCompat(2, z ? true : false);
    }

    public static RatingCompat newStarRating(int i, float f) {
        float f2;
        switch (i) {
            case 3:
                f2 = 3.0f;
                break;
            case 4:
                f2 = 4.0f;
                break;
            case 5:
                f2 = 5.0f;
                break;
            default:
                f = TAG;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Invalid rating style (");
                stringBuilder.append(i);
                stringBuilder.append(") for a star rating");
                Log.e(f, stringBuilder.toString());
                return null;
        }
        if (f >= 0.0f) {
            if (f <= f2) {
                return new RatingCompat(i, f);
            }
        }
        Log.e(TAG, "Trying to set out of range star-based rating");
        return null;
    }

    public static RatingCompat newPercentageRating(float f) {
        if (f >= 0.0f) {
            if (f <= 100.0f) {
                return new RatingCompat(6, f);
            }
        }
        Log.e(TAG, "Invalid percentage-based rating value");
        return 0.0f;
    }

    public boolean isRated() {
        return this.mRatingValue >= 0.0f;
    }

    public int getRatingStyle() {
        return this.mRatingStyle;
    }

    public boolean hasHeart() {
        boolean z = false;
        if (this.mRatingStyle != 1) {
            return false;
        }
        if (this.mRatingValue == 1.0f) {
            z = true;
        }
        return z;
    }

    public boolean isThumbUp() {
        boolean z = false;
        if (this.mRatingStyle != 2) {
            return false;
        }
        if (this.mRatingValue == 1.0f) {
            z = true;
        }
        return z;
    }

    public float getStarRating() {
        switch (this.mRatingStyle) {
            case 3:
            case 4:
            case 5:
                if (isRated()) {
                    return this.mRatingValue;
                }
                break;
            default:
                break;
        }
        return RATING_NOT_RATED;
    }

    public float getPercentRating() {
        if (this.mRatingStyle == 6) {
            if (isRated()) {
                return this.mRatingValue;
            }
        }
        return RATING_NOT_RATED;
    }

    public static RatingCompat fromRating(Object obj) {
        if (obj == null || VERSION.SDK_INT < 19) {
            return null;
        }
        RatingCompat newHeartRating;
        Rating rating = (Rating) obj;
        int ratingStyle = rating.getRatingStyle();
        if (rating.isRated()) {
            switch (ratingStyle) {
                case 1:
                    newHeartRating = newHeartRating(rating.hasHeart());
                    break;
                case 2:
                    newHeartRating = newThumbRating(rating.isThumbUp());
                    break;
                case 3:
                case 4:
                case 5:
                    newHeartRating = newStarRating(ratingStyle, rating.getStarRating());
                    break;
                case 6:
                    newHeartRating = newPercentageRating(rating.getPercentRating());
                    break;
                default:
                    return null;
            }
        }
        newHeartRating = newUnratedRating(ratingStyle);
        newHeartRating.mRatingObj = obj;
        return newHeartRating;
    }

    public Object getRating() {
        if (this.mRatingObj == null && VERSION.SDK_INT >= 19) {
            if (isRated()) {
                switch (this.mRatingStyle) {
                    case 1:
                        this.mRatingObj = Rating.newHeartRating(hasHeart());
                        break;
                    case 2:
                        this.mRatingObj = Rating.newThumbRating(isThumbUp());
                        break;
                    case 3:
                    case 4:
                    case 5:
                        this.mRatingObj = Rating.newStarRating(this.mRatingStyle, getStarRating());
                        break;
                    case 6:
                        this.mRatingObj = Rating.newPercentageRating(getPercentRating());
                        break;
                    default:
                        return null;
                }
            }
            this.mRatingObj = Rating.newUnratedRating(this.mRatingStyle);
        }
        return this.mRatingObj;
    }
}
