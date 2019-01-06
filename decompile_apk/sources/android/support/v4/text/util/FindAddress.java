package android.support.v4.text.util;

import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.annotation.VisibleForTesting;
import java.util.Locale;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestrictTo({Scope.LIBRARY_GROUP})
class FindAddress {
    private static final String HOUSE_COMPONENT = "(?:one|\\d+([a-z](?=[^a-z]|$)|st|nd|rd|th)?)";
    private static final String HOUSE_END = "(?=[,\"'\t                　\n\u000b\f\r  ]|$)";
    private static final String HOUSE_POST_DELIM = ",\"'\t                　\n\u000b\f\r  ";
    private static final String HOUSE_PRE_DELIM = ":,\"'\t                　\n\u000b\f\r  ";
    private static final int MAX_ADDRESS_LINES = 5;
    private static final int MAX_ADDRESS_WORDS = 14;
    private static final int MAX_LOCATION_NAME_DISTANCE = 5;
    private static final int MIN_ADDRESS_WORDS = 4;
    private static final String NL = "\n\u000b\f\r  ";
    private static final String SP = "\t                　";
    private static final String WORD_DELIM = ",*•\t                　\n\u000b\f\r  ";
    private static final String WORD_END = "(?=[,*•\t                　\n\u000b\f\r  ]|$)";
    private static final String WS = "\t                　\n\u000b\f\r  ";
    private static final int kMaxAddressNameWordLength = 25;
    private static final Pattern sHouseNumberRe = Pattern.compile("(?:one|\\d+([a-z](?=[^a-z]|$)|st|nd|rd|th)?)(?:-(?:one|\\d+([a-z](?=[^a-z]|$)|st|nd|rd|th)?))*(?=[,\"'\t                　\n\u000b\f\r  ]|$)", 2);
    private static final Pattern sLocationNameRe = Pattern.compile("(?:alley|annex|arcade|ave[.]?|avenue|alameda|bayou|beach|bend|bluffs?|bottom|boulevard|branch|bridge|brooks?|burgs?|bypass|broadway|camino|camp|canyon|cape|causeway|centers?|circles?|cliffs?|club|common|corners?|course|courts?|coves?|creek|crescent|crest|crossing|crossroad|curve|circulo|dale|dam|divide|drives?|estates?|expressway|extensions?|falls?|ferry|fields?|flats?|fords?|forest|forges?|forks?|fort|freeway|gardens?|gateway|glens?|greens?|groves?|harbors?|haven|heights|highway|hills?|hollow|inlet|islands?|isle|junctions?|keys?|knolls?|lakes?|land|landing|lane|lights?|loaf|locks?|lodge|loop|mall|manors?|meadows?|mews|mills?|mission|motorway|mount|mountains?|neck|orchard|oval|overpass|parks?|parkways?|pass|passage|path|pike|pines?|plains?|plaza|points?|ports?|prairie|privada|radial|ramp|ranch|rapids?|rd[.]?|rest|ridges?|river|roads?|route|row|rue|run|shoals?|shores?|skyway|springs?|spurs?|squares?|station|stravenue|stream|st[.]?|streets?|summit|speedway|terrace|throughway|trace|track|trafficway|trail|tunnel|turnpike|underpass|unions?|valleys?|viaduct|views?|villages?|ville|vista|walks?|wall|ways?|wells?|xing|xrd)(?=[,*•\t                　\n\u000b\f\r  ]|$)", 2);
    private static final Pattern sStateRe = Pattern.compile("(?:(ak|alaska)|(al|alabama)|(ar|arkansas)|(as|american[\t                　]+samoa)|(az|arizona)|(ca|california)|(co|colorado)|(ct|connecticut)|(dc|district[\t                　]+of[\t                　]+columbia)|(de|delaware)|(fl|florida)|(fm|federated[\t                　]+states[\t                　]+of[\t                　]+micronesia)|(ga|georgia)|(gu|guam)|(hi|hawaii)|(ia|iowa)|(id|idaho)|(il|illinois)|(in|indiana)|(ks|kansas)|(ky|kentucky)|(la|louisiana)|(ma|massachusetts)|(md|maryland)|(me|maine)|(mh|marshall[\t                　]+islands)|(mi|michigan)|(mn|minnesota)|(mo|missouri)|(mp|northern[\t                　]+mariana[\t                　]+islands)|(ms|mississippi)|(mt|montana)|(nc|north[\t                　]+carolina)|(nd|north[\t                　]+dakota)|(ne|nebraska)|(nh|new[\t                　]+hampshire)|(nj|new[\t                　]+jersey)|(nm|new[\t                　]+mexico)|(nv|nevada)|(ny|new[\t                　]+york)|(oh|ohio)|(ok|oklahoma)|(or|oregon)|(pa|pennsylvania)|(pr|puerto[\t                　]+rico)|(pw|palau)|(ri|rhode[\t                　]+island)|(sc|south[\t                　]+carolina)|(sd|south[\t                　]+dakota)|(tn|tennessee)|(tx|texas)|(ut|utah)|(va|virginia)|(vi|virgin[\t                　]+islands)|(vt|vermont)|(wa|washington)|(wi|wisconsin)|(wv|west[\t                　]+virginia)|(wy|wyoming))(?=[,*•\t                　\n\u000b\f\r  ]|$)", 2);
    private static final ZipRange[] sStateZipCodeRanges = new ZipRange[]{new ZipRange(99, 99, -1, -1), new ZipRange(35, 36, -1, -1), new ZipRange(71, 72, -1, -1), new ZipRange(96, 96, -1, -1), new ZipRange(85, 86, -1, -1), new ZipRange(90, 96, -1, -1), new ZipRange(80, 81, -1, -1), new ZipRange(6, 6, -1, -1), new ZipRange(20, 20, -1, -1), new ZipRange(19, 19, -1, -1), new ZipRange(32, 34, -1, -1), new ZipRange(96, 96, -1, -1), new ZipRange(30, 31, -1, -1), new ZipRange(96, 96, -1, -1), new ZipRange(96, 96, -1, -1), new ZipRange(50, 52, -1, -1), new ZipRange(83, 83, -1, -1), new ZipRange(60, 62, -1, -1), new ZipRange(46, 47, -1, -1), new ZipRange(66, 67, 73, -1), new ZipRange(40, 42, -1, -1), new ZipRange(70, 71, -1, -1), new ZipRange(1, 2, -1, -1), new ZipRange(20, 21, -1, -1), new ZipRange(3, 4, -1, -1), new ZipRange(96, 96, -1, -1), new ZipRange(48, 49, -1, -1), new ZipRange(55, 56, -1, -1), new ZipRange(63, 65, -1, -1), new ZipRange(96, 96, -1, -1), new ZipRange(38, 39, -1, -1), new ZipRange(55, 56, -1, -1), new ZipRange(27, 28, -1, -1), new ZipRange(58, 58, -1, -1), new ZipRange(68, 69, -1, -1), new ZipRange(3, 4, -1, -1), new ZipRange(7, 8, -1, -1), new ZipRange(87, 88, 86, -1), new ZipRange(88, 89, 96, -1), new ZipRange(10, 14, 0, 6), new ZipRange(43, 45, -1, -1), new ZipRange(73, 74, -1, -1), new ZipRange(97, 97, -1, -1), new ZipRange(15, 19, -1, -1), new ZipRange(6, 6, 0, 9), new ZipRange(96, 96, -1, -1), new ZipRange(2, 2, -1, -1), new ZipRange(29, 29, -1, -1), new ZipRange(57, 57, -1, -1), new ZipRange(37, 38, -1, -1), new ZipRange(75, 79, 87, 88), new ZipRange(84, 84, -1, -1), new ZipRange(22, 24, 20, -1), new ZipRange(6, 9, -1, -1), new ZipRange(5, 5, -1, -1), new ZipRange(98, 99, -1, -1), new ZipRange(53, 54, -1, -1), new ZipRange(24, 26, -1, -1), new ZipRange(82, 83, -1, -1)};
    private static final Pattern sSuffixedNumberRe = Pattern.compile("(\\d+)(st|nd|rd|th)", 2);
    private static final Pattern sWordRe = Pattern.compile("[^,*•\t                　\n\u000b\f\r  ]+(?=[,*•\t                　\n\u000b\f\r  ]|$)", 2);
    private static final Pattern sZipCodeRe = Pattern.compile("(?:\\d{5}(?:-\\d{4})?)(?=[,*•\t                　\n\u000b\f\r  ]|$)", 2);

    private static class ZipRange {
        int mException1;
        int mException2;
        int mHigh;
        int mLow;

        ZipRange(int i, int i2, int i3, int i4) {
            this.mLow = i;
            this.mHigh = i2;
            this.mException1 = i3;
            this.mException2 = i3;
        }

        boolean matches(String str) {
            str = Integer.parseInt(str.substring(0, 2));
            if ((this.mLow <= str && str <= this.mHigh) || str == this.mException1 || str == this.mException2) {
                return true;
            }
            return false;
        }
    }

    private static boolean checkHouseNumber(String str) {
        int i;
        int i2 = 0;
        for (i = 0; i < str.length(); i++) {
            if (Character.isDigit(str.charAt(i))) {
                i2++;
            }
        }
        if (i2 > 5) {
            return false;
        }
        str = sSuffixedNumberRe.matcher(str);
        if (!str.find()) {
            return true;
        }
        i = Integer.parseInt(str.group(1));
        if (i == 0) {
            return false;
        }
        str = str.group(2).toLowerCase(Locale.getDefault());
        switch (i % 10) {
            case 1:
                return str.equals(i % 100 == 11 ? "th" : "st");
            case 2:
                return str.equals(i % 100 == 12 ? "th" : "nd");
            case 3:
                return str.equals(i % 100 == 13 ? "th" : "rd");
            default:
                return str.equals("th");
        }
    }

    @VisibleForTesting
    public static MatchResult matchHouseNumber(String str, int i) {
        if (i > 0 && HOUSE_PRE_DELIM.indexOf(str.charAt(i - 1)) == -1) {
            return null;
        }
        str = sHouseNumberRe.matcher(str).region(i, str.length());
        if (str.lookingAt() != 0) {
            str = str.toMatchResult();
            if (checkHouseNumber(str.group(0)) != 0) {
                return str;
            }
        }
        return null;
    }

    @VisibleForTesting
    public static MatchResult matchState(String str, int i) {
        MatchResult matchResult = null;
        if (i > 0 && WORD_DELIM.indexOf(str.charAt(i - 1)) == -1) {
            return null;
        }
        str = sStateRe.matcher(str).region(i, str.length());
        if (str.lookingAt() != 0) {
            matchResult = str.toMatchResult();
        }
        return matchResult;
    }

    private static boolean isValidZipCode(String str, MatchResult matchResult) {
        boolean z = false;
        if (matchResult == null) {
            return false;
        }
        int groupCount = matchResult.groupCount();
        while (groupCount > 0) {
            int i = groupCount - 1;
            if (matchResult.group(groupCount) != null) {
                groupCount = i;
                break;
            }
            groupCount = i;
        }
        if (!(sZipCodeRe.matcher(str).matches() == null || sStateZipCodeRanges[r1].matches(str) == null)) {
            z = true;
        }
        return z;
    }

    @VisibleForTesting
    public static boolean isValidZipCode(String str, String str2) {
        return isValidZipCode(str, matchState(str2, 0));
    }

    @VisibleForTesting
    public static boolean isValidZipCode(String str) {
        return sZipCodeRe.matcher(str).matches();
    }

    @VisibleForTesting
    public static boolean isValidLocationName(String str) {
        return sLocationNameRe.matcher(str).matches();
    }

    private static int attemptMatch(String str, MatchResult matchResult) {
        matchResult = matchResult.end();
        Matcher matcher = sWordRe.matcher(str);
        String str2 = "";
        int i = 1;
        int i2 = 1;
        Object obj = 1;
        MatchResult matchResult2 = -1;
        Object obj2 = null;
        int i3 = -1;
        while (matchResult < str.length()) {
            if (!matcher.find(matchResult)) {
                return -str.length();
            }
            if (matcher.end() - matcher.start() > 25) {
                return -matcher.end();
            }
            while (matchResult < matcher.start()) {
                int i4 = matchResult + 1;
                if (NL.indexOf(str.charAt(matchResult)) != -1) {
                    i++;
                }
                matchResult = i4;
            }
            if (i > 5) {
                break;
            }
            i2++;
            if (i2 > 14) {
                break;
            }
            if (matchHouseNumber(str, matchResult) == null) {
                if (!isValidLocationName(matcher.group(0))) {
                    if (i2 == 5 && obj2 == null) {
                        matchResult = matcher.end();
                        break;
                    }
                    if (obj2 != null && i2 > 4) {
                        matchResult = matchState(str, matchResult);
                        if (matchResult != null) {
                            if (str2.equals("et") && matchResult.group(0).equals("al")) {
                                matchResult = matchResult.end();
                                break;
                            }
                            Matcher matcher2 = sWordRe.matcher(str);
                            if (!matcher2.find(matchResult.end())) {
                                i3 = matchResult.end();
                            } else if (isValidZipCode(matcher2.group(0), matchResult) != null) {
                                return matcher2.end();
                            }
                        }
                    }
                    obj = null;
                } else {
                    obj = null;
                    obj2 = 1;
                }
            } else if (obj != null && i > 1) {
                return -matchResult;
            } else {
                if (matchResult2 == -1) {
                    matchResult2 = matchResult;
                }
            }
            str2 = matcher.group(0);
            matchResult = matcher.end();
        }
        if (i3 > 0) {
            return i3;
        }
        if (matchResult2 > null) {
            matchResult = matchResult2;
        }
        return -matchResult;
    }

    static String findAddress(String str) {
        Object matcher = sHouseNumberRe.matcher(str);
        int i = 0;
        while (matcher.find(i)) {
            if (checkHouseNumber(matcher.group(0))) {
                i = matcher.start();
                int attemptMatch = attemptMatch(str, matcher);
                if (attemptMatch > 0) {
                    return str.substring(i, attemptMatch);
                }
                i = -attemptMatch;
            } else {
                i = matcher.end();
            }
        }
        return null;
    }

    private FindAddress() {
    }
}
