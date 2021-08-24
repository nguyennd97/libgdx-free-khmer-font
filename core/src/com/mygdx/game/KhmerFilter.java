package com.mygdx.game;

import java.util.HashMap;

public class KhmerFilter {

    // foot
    private final static HashMap<String, Character> blwSC1;
    static {
        blwSC1 = new HashMap<>();
        blwSC1.put("្ឍ", '\u1388');
        blwSC1.put("្ស", '\u1389');
        blwSC1.put("្ឃ", '\u138a');
        blwSC1.put("្ឈ", '\u138b');
        blwSC1.put("្ប", '\u138c');

        blwSC1.put("្យ", '\u138d');
        blwSC1.put("្ឞ", '\u138e');
        blwSC1.put("្រ", '\u138f');
        blwSC1.put("្ន", '\u1390');
        blwSC1.put("្វ", '\u1391');

        blwSC1.put("្ខ", '\u1392');
        blwSC1.put("្ង", '\u1393');
        blwSC1.put("្ច", '\u1394');
        blwSC1.put("្ឋ", '\u1395');
        blwSC1.put("្ឌ", '\u1396');

        blwSC1.put("្ទ", '\u1397');
        blwSC1.put("្ធ", '\u1398');
        blwSC1.put("្គ", '\u1399');
        blwSC1.put("្ម", '\u139a');
        blwSC1.put("្ល", '\u139b');

        blwSC1.put("្ក", '\u139c');
        blwSC1.put("្ឆ", '\u139d');
        blwSC1.put("្ជ", '\u139e');
        blwSC1.put("្ត", '\u139f');
        blwSC1.put("្ណ", '\u13a0');

        blwSC1.put("្ដ", '\u13a1');
        blwSC1.put("្ថ", '\u13a2');
        blwSC1.put("្ផ", '\u13a3');
        blwSC1.put("្ព", '\u13a4');
        blwSC1.put("្ភ", '\u13a5');

        blwSC1.put("្ឝ", '\u13a6');
        blwSC1.put("្ហ", '\u13a7');
        blwSC1.put("្អ", '\u13a8');
        blwSC1.put("្ញ", '\u13a9');
    }

    // foot before other foot
    private final static HashMap<String, Character> blwSC2;
    static {
        blwSC2 = new HashMap<>();
        blwSC2.put("្ឍ", '\u1288');
        blwSC2.put("្ស", '\u1289');
        blwSC2.put("្ឃ", '\u128a');
        blwSC2.put("្ឈ", '\u128b');
        blwSC2.put("្ប", '\u128c');

        blwSC2.put("្យ", '\u128d');
        blwSC2.put("្ឞ", '\u128e');
        blwSC2.put("្រ", '\u128f');
        blwSC2.put("្ន", '\u1290');
        blwSC2.put("្វ", '\u1291');

        blwSC2.put("្ខ", '\u1292');
        blwSC2.put("្ង", '\u1293');
        blwSC2.put("្ច", '\u1294');
        blwSC2.put("្ឋ", '\u1295');
        blwSC2.put("្ឌ", '\u1296');

        blwSC2.put("្ទ", '\u1297');
        blwSC2.put("្ធ", '\u1298');
        blwSC2.put("្គ", '\u1299');
        blwSC2.put("្ម", '\u129a');
        blwSC2.put("្ល", '\u129b');

        blwSC2.put("្ក", '\u129c');
        blwSC2.put("្ឆ", '\u129d');
        blwSC2.put("្ជ", '\u129e');
        blwSC2.put("្ត", '\u129f');
        blwSC2.put("្ណ", '\u12a0');

        blwSC2.put("្ដ", '\u12a1');
        blwSC2.put("្ថ", '\u12a2');
        blwSC2.put("្ផ", '\u12a3');
        blwSC2.put("្ព", '\u12a4');
        blwSC2.put("្ភ", '\u12a5');

        blwSC2.put("្ឝ", '\u12a6');
        blwSC2.put("្ហ", '\u12a7');
        blwSC2.put("្អ", '\u12a8');
        blwSC2.put("្ញ", '\u12a9');
    }

    // character changes if word has foot
    private final static HashMap<Character, Character> blwV;
    static {
        blwV = new HashMap<>();
        blwV.put('\u17bb', '\u0feb');
        blwV.put('\u17bc', '\u0fec');
        blwV.put('\u17bd', '\u0fed');
        blwV.put('\u17bf', '\u0fee');
        blwV.put('\u17c0', '\u0fef');
    }

    public static String filter(String khmerText) {
        char[] chars = khmerText.toCharArray();
        StringBuilder encodedText = new StringBuilder();
        StringBuilder word = new StringBuilder();
        String foot;
        for (int i = 0; i < chars.length; i++) {
            switch (chars[i]) {
                // check foot
                case '\u17d2':
                    if (i < chars.length - 1) {
                        // special word ញ with foot will be removed its original foot
                        if (word.length() > 0) {
                            if (word.charAt(word.length() - 1) == '\u1789') {
                                word.setCharAt(word.length() - 1, '\u0ff2');
                            }
                            // change foot to ញ្ញ and ក្ញ
                            if ((word.charAt(word.length() - 1) == '\u1789' || word.charAt(word.length() - 1) == '\u1780') &&
                                    chars[i + 1] == '\u1789') {
                                word.append('\u0ff0');
                                i++;
                            } else {
                                // first foot
                                foot = chars[i] + "" + chars[i + 1];
                                if (blwSC1.containsKey(foot)) {
                                    if (chars[i + 1] == '\u179a') {
                                        word.insert(word.length() - 1, blwSC1.get(foot));
                                    } else {
                                        word.append(blwSC1.get(foot));
                                    }
                                    i++;
                                }
                            }
                        }

                        // second foot
                        if (i < chars.length - 2 && chars[i + 1] == '\u17d2') {
                            i++;
                            foot = chars[i] + "" + chars[i + 1];
                            if (blwSC2.containsKey(foot)) {
                                if (chars[i + 1] == '\u179a') {
                                    word.insert(word.length() - 2, blwSC2.get(foot));
                                } else if (chars[i - 1] == '\u179a') {
                                    word.append(blwSC1.get(foot));
                                } else {
                                    word.append(blwSC2.get(foot));
                                }
                                i++;
                            }
                        }
                        if (i < chars.length - 1 && blwV.containsKey(chars[i + 1])) {
                            word.append(blwV.get(chars[i + 1]));
                            i++;
                        }
                    }
                    break;
                case '\u1789':
                case '\u17a1':
                    word.append(chars[i]);
                    if (i < chars.length - 1) {
                        if (blwV.containsKey(chars[i + 1])) {
                            word.append(blwV.get(chars[i + 1]));
                            i++;
                        }
                    }
                    break;
                // insert េ to the left and ី to the right
                case '\u17be':  // ើ
                    word.insert(0, '\u17c1');
                    word.append('\u17b8');
                    break;
                // insert េ to the left word
                case '\u17bf': // ឿ
                case '\u17c0': // ៀ
                case '\u17c5': // ៅ
                case '\u17c4': // ោ
                    word.insert(0, '\u17c1');
                    word.append(chars[i]);
                    break;
                // append to the left word
                case '\u17c1': // េ
                case '\u17c2': // ែ
                case '\u17c3': // ៃ
                    word.insert(0, chars[i]);
                    break;
                default:
                    encodedText.append(word);
                    word = new StringBuilder();
                    word.append(chars[i]);
                    break;
            }
        }
        encodedText.append(word);
        return encodedText.toString();
    }
}
