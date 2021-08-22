package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.HashMap;

public class MyGdxGame extends ApplicationAdapter {

    SpriteBatch batch;
    BitmapFont font;

    String testConsonants = filter("កខគឃងចឆជឈញដឋឌឍណ\nតថទធនបផពភមយរលវឝឞសហឡអ");
    String testVowel = filter("ឥឦឧឨឩឪឫឬឭឮឯឰឱឲឳ");
    String testMark = filter("។៘ៗ៕៚៙៖");
    String testNumbers = filter("០១២៣៤៥៦៧៨៩");
    String testFeet = filter("ក្ខ្ក ក្ខ្ខ ក្ខ្គ ក្ខ្ឃ ក្ខ្ង ក្ខ្ច ក្ខ្ឆ ក្ខ្ជ ក្ខ្ឈ ក្ខ្ញ\n" +
            "ក្ខ្ដ ក្ខ្ឋ ក្ខ្ឌ ក្ខ្ឍ ក្ខ្ណ ក្ខ្ត ក្ខ្ថ ក្ខ្ទ ក្ខ្ធ ក្ខ្ន\n" +
            "ក្ខ្ប ក្ខ្ផ ក្ខ្ព ក្ខ្ភ ក្ខ្ម ក្ខ្យ ក្ខ្រ ក្ខ្ល ក្ខ្វ\n" +
            "ក្ខ្ឝ ក្ខ្ឞ ក្ខ្ស ក្ខ្ហ ក្ខ្អ");
    String testFoot = filter("ក្ក ក្ខ ក្គ ក្ឃ ក្ង ក្ច ក្ឆ ក្ជ ក្ឈ ក្ញ ក្ដ ក្ឋ ក្ឌ\n" +
            "ក្ឍ  ក្ណ ក្ត ក្ថ ក្ទ ក្ធ ក្ន ក្ប ក្ផ ក្ព ក្ភ \n" +
            "ក្ម ក្យ ក្រ ក្ល ក្វ ក្ឝ ក្ឞ ក្ស ក្ហ ក្អ");
    String testSpecialFoot = filter("ញ្ក ញ្ញ ក្ញ");
    String testDependentVowel = filter("កា កិ កី កឹ កឺ កុ កូ កួ \n" +
            "កើ កឿ កៀ កេ កែ កៃ កោ កៅ");
    String testFootVowel = filter("ក្កុ ក្កូ ក្កួ");
    String testSpecialDependentVowel = filter("ញុ ញូ ញួ ឡុ ឡូ ឡួ\n" +
            "ញ្ក ញ្ខ ញ្គ ញ្ឃ ញ្ង ញ្ច ញ្ឆ ញ្ជ ញ្ឈ ញ្ញ ញ្ដ \n" +
            "ញ្ឋ ញ្ឌ ញ្ឍ  ញ្ណ \n" +
            "ញ្ត ញ្ថ ញ្ទ ញ្ធ ញ្ន ញ្ប ញ្ផ ញ្ព ញ្ភ ញ្ម ញ្យ \n" +
            "ញ្រ ញ្ល ញ្វ ញ្ឝ ញ្ឞ ញ្ស ញ្ហ ញ្អ");
    String testLongCharacters = filter("ក្ក្ឃ ក្ក្ឈ ក្ក្ឍ ក្ក្ប ក្ក្ប ក្ក្យ ក្ក្រ ក្ក្ឞ ក្ក្ស ក្កឿ ក្កៀ");
    String testDiacritic = filter("កំ កះ កៈ ក៉ ក៊ កុ ក់ ក៌ ក៍ ក៎ ក៏ ក័ ក៑ ក៓");

    // foot
    private final static HashMap<String, Character> khmerFoot;
    static {
        khmerFoot = new HashMap<>();
        khmerFoot.put("្ឍ", '\u1388');
        khmerFoot.put("្ស", '\u1389');
        khmerFoot.put("្ឃ", '\u138a');
        khmerFoot.put("្ឈ", '\u138b');
        khmerFoot.put("្ប", '\u138c');

        khmerFoot.put("្យ", '\u138d');
        khmerFoot.put("្ឞ", '\u138e');
        khmerFoot.put("្រ", '\u138f');
        khmerFoot.put("្ន", '\u1390');
        khmerFoot.put("្វ", '\u1391');

        khmerFoot.put("្ខ", '\u1392');
        khmerFoot.put("្ង", '\u1393');
        khmerFoot.put("្ច", '\u1394');
        khmerFoot.put("្ឋ", '\u1395');
        khmerFoot.put("្ឌ", '\u1396');

        khmerFoot.put("្ទ", '\u1397');
        khmerFoot.put("្ធ", '\u1398');
        khmerFoot.put("្គ", '\u1399');
        khmerFoot.put("្ម", '\u139a');
        khmerFoot.put("្ល", '\u139b');

        khmerFoot.put("្ក", '\u139c');
        khmerFoot.put("្ឆ", '\u139d');
        khmerFoot.put("្ជ", '\u139e');
        khmerFoot.put("្ត", '\u139f');
        khmerFoot.put("្ណ", '\u13a0');

        khmerFoot.put("្ដ", '\u13a1');
        khmerFoot.put("្ថ", '\u13a2');
        khmerFoot.put("្ផ", '\u13a3');
        khmerFoot.put("្ព", '\u13a4');
        khmerFoot.put("្ភ", '\u13a5');

        khmerFoot.put("្ឝ", '\u13a6');
        khmerFoot.put("្ហ", '\u13a7');
        khmerFoot.put("្អ", '\u13a8');
        khmerFoot.put("្ញ", '\u13a9');
    }

    // foot before other foot
    private final static HashMap<String, Character> khmerFeet;
    static {
        khmerFeet = new HashMap<>();
        khmerFeet.put("្ឍ", '\u1288');
        khmerFeet.put("្ស", '\u1289');
        khmerFeet.put("្ឃ", '\u128a');
        khmerFeet.put("្ឈ", '\u128b');
        khmerFeet.put("្ប", '\u128c');

        khmerFeet.put("្យ", '\u128d');
        khmerFeet.put("្ឞ", '\u128e');
        khmerFeet.put("្រ", '\u128f');
        khmerFeet.put("្ន", '\u1290');
        khmerFeet.put("្វ", '\u1291');

        khmerFeet.put("្ខ", '\u1292');
        khmerFeet.put("្ង", '\u1293');
        khmerFeet.put("្ច", '\u1294');
        khmerFeet.put("្ឋ", '\u1295');
        khmerFeet.put("្ឌ", '\u1296');

        khmerFeet.put("្ទ", '\u1297');
        khmerFeet.put("្ធ", '\u1298');
        khmerFeet.put("្គ", '\u1299');
        khmerFeet.put("្ម", '\u129a');
        khmerFeet.put("្ល", '\u129b');

        khmerFeet.put("្ក", '\u129c');
        khmerFeet.put("្ឆ", '\u129d');
        khmerFeet.put("្ជ", '\u129e');
        khmerFeet.put("្ត", '\u129f');
        khmerFeet.put("្ណ", '\u12a0');

        khmerFeet.put("្ដ", '\u12a1');
        khmerFeet.put("្ថ", '\u12a2');
        khmerFeet.put("្ផ", '\u12a3');
        khmerFeet.put("្ព", '\u12a4');
        khmerFeet.put("្ភ", '\u12a5');

        khmerFeet.put("្ឝ", '\u12a6');
        khmerFeet.put("្ហ", '\u12a7');
        khmerFeet.put("្អ", '\u12a8');
        khmerFeet.put("្ញ", '\u12a9');
    }

    // character changes if word has foot
    private final static HashMap<Character, Character> specialCharacter;
    static {
        specialCharacter = new HashMap<>();
        specialCharacter.put('\u17bb', '\u0feb');
        specialCharacter.put('\u17bc', '\u0fec');
        specialCharacter.put('\u17bd', '\u0fed');
        specialCharacter.put('\u17bf', '\u0fee');
        specialCharacter.put('\u17c0', '\u0fef');
    }

    @Override
    public void create() {
        batch = new SpriteBatch();
        font = new BitmapFont(Gdx.files.internal("khmerOsBattamang-64-full.fnt"));
        font.getData().setScale(32f / 64f);
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        font.draw(batch, testDependentVowel, 50, 430);
//        font.draw(batch, testFoot, 50, 430);
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
    }

    private String filter(String khmerText) {
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
                                if (khmerFoot.containsKey(foot)) {
                                    if (chars[i + 1] == '\u179a') {
                                        word.insert(word.length() - 1, khmerFoot.get(foot));
                                    } else {
                                        word.append(khmerFoot.get(foot));
                                    }
                                    i++;
                                }
                            }
                        }

                        // second foot
                        if (i < chars.length - 2 && chars[i + 1] == '\u17d2') {
                            i++;
                            foot = chars[i] + "" + chars[i + 1];
                            if (khmerFeet.containsKey(foot)) {
                                if (chars[i + 1] == '\u179a') {
                                    word.insert(word.length() - 2, khmerFeet.get(foot));
                                } else if (chars[i - 1] == '\u179a') {
                                    word.append(khmerFoot.get(foot));
                                } else {
                                    word.append(khmerFeet.get(foot));
                                }
                                i++;
                            }
                        }
                        if (i < chars.length - 1 && specialCharacter.containsKey(chars[i + 1])) {
                            word.append(specialCharacter.get(chars[i + 1]));
                            i++;
                        }
                    }
                    break;
                case '\u1789':
                case '\u17a1':
                    word.append(chars[i]);
                    if (i < chars.length - 1) {
                        if (specialCharacter.containsKey(chars[i + 1])) {
                            word.append(specialCharacter.get(chars[i + 1]));
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
