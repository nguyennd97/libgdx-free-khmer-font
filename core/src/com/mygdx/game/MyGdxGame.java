package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.tools.hiero.unicodefont.Glyph;
import com.badlogic.gdx.tools.hiero.unicodefont.GlyphPage;
import com.badlogic.gdx.tools.hiero.unicodefont.UnicodeFont;

import org.lwjgl.opengl.GL11;

import java.awt.Font;
import java.awt.Rectangle;
import java.awt.font.GlyphVector;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class MyGdxGame extends ApplicationAdapter {
    static public final String EXTENDED_CHARS;

    static {
        StringBuilder buffer = new StringBuilder();
        int i = 0;
        for (int c : new int[]{
                0x1781, 0x1783, 0x1783, 0x1784, 0x1785, 0x1786, 0x1787, 0x1788, 0x1789, 0x178A, 0x178B, 0x178C, 0x178D, 0x178E, 0x178F,
                0x1791, 0x1793, 0x1793, 0x1794, 0x1795, 0x1796, 0x1797, 0x1798, 0x1799, 0x179A, 0x179B, 0x179C, 0x179D, 0x179E, 0x179F,
                0x17A1, 0x17A3, 0x17A3, 0x17A4, 0x17A5, 0x17A6, 0x17A7, 0x17A8, 0x17A9, 0x17AA, 0x17AB, 0x17AC, 0x17AD, 0x17AE, 0x17AF,
                0x17B1, 0x17B3, 0x17B3, 0x17B4, 0x17B5, 0x17B6, 0x17B7, 0x17B8, 0x17B9, 0x17BA, 0x17BB, 0x17BC, 0x17BD, 0x17BE, 0x17BF,
                0x17C1, 0x17C3, 0x17C3, 0x17C4, 0x17C5, 0x17C6, 0x17C7, 0x17C8, 0x17C9, 0x17CA, 0x17CB, 0x17CC, 0x17CD, 0x17CE, 0x17CF,
                0x17D1, 0x17D3, 0x17D3, 0x17D4, 0x17D5, 0x17D6, 0x17D7, 0x17D8, 0x17D9, 0x17DA, 0x17DB, 0x17DC, 0x17DD,
                0x17E1, 0x17E3, 0x17E3, 0x17E4, 0x17E5, 0x17E6, 0x17E7, 0x17E8, 0x17E9,
                0x17F1, 0x17F3, 0x17F3, 0x17F4, 0x17F5, 0x17F6, 0x17F7, 0x17F8, 0x17F9
        }) {
            i++;
            if (i > 26) {
                i = 0;
                buffer.append("\r\n");
            }
            buffer.append((char) c);
        }
        EXTENDED_CHARS = buffer.toString();
    }

    String temp = "កខគឃងចឆជឈញដឋឌឍណតថទធនបផពភមយរលវឝឞសហឡអឣឤឥឦឧឨឩឪឫឬឭឮឯឰឱឲឳ" +
            "ាិីឹឺុូួ ើ ឿ ៀ េ ែ ៃ ោ ៅ" +
            "ំ ះ ៈ ៉ ៊់ ៌ ៍ ៎ ៏ ័ ៑ ៓" +
            "។៕៖ៗ ៘ ៙៚៛ៜ ៝ ០១២៣៤៥៦៧៨៩" +
            "៰៱៲៳៴៵៶៷៸៹";
    SpriteBatch batch;
    BitmapFont font;
    public static String KHMER_CONSONANT = "កខគឃងចឆជឈញដឋឌឍណតថទធនបផពភមយរលវឝឞសហឡអ";
    public static String KHMER_CONSONANT_SUBSCRIPT_FORM = "្ក ្ខ ្គ ្ឃ ្ង ្ច ្ឆ ្ជ ្ឈ ្ញ ្ដ ្ឋ ្ឌ ្ឍ  ្ណ ្ត ្ថ ្ទ ្ធ ្ន ្ប ្ផ ្ព ្ភ ្ម ្យ ្រ ្ល ្វ ្ឝ ្ឞ ្ស ្ហ ្អ";
    public static String KHMER_SUPPLEMENTARY_CONSONANT = "ហ្គហ្គ៊ហ្នប៉ហ្មហ្លហ្វហ្វ៊ហ្សហ្ស៊";
    public static String KHMER_DEPENDENT_VOWEL = "ាិីឹឺុូួ ើ ឿ ៀ េ ែ ៃ ោ ៅ";
    public static String KHMER_COMBINATION = "អុំអំអាំអះអិះអុះអេះអោះ";
    public static String KHMER_INDEPENDENT_VOWELS = "ឥឦឧឨឩឪឫឬឭឮឯឰឱឲឳ";
    public static String KHMER_DIACRITIC = "ំ ះ ៈ  ៉  ៊ ុ ់ ៌ ៍ ៎ ៏ ័ ៑";
    public static String KHMER_NUMERALS = "០១២៣៤៥៦៧៨៩";
    public static String KHMER_MARK = "។៘ៗ៕៚៙៖";
    public static String KHMER_ALL = KHMER_CONSONANT + KHMER_CONSONANT_SUBSCRIPT_FORM +
            KHMER_SUPPLEMENTARY_CONSONANT + KHMER_DEPENDENT_VOWEL + KHMER_COMBINATION + KHMER_INDEPENDENT_VOWELS +
            KHMER_DIACRITIC + KHMER_NUMERALS + KHMER_MARK;

    static HashMap<String, Character> khmerFoots;

    static {
        khmerFoots = new HashMap<>();
        khmerFoots.put("្ឍ", '\u1388');
        khmerFoots.put("្ស", '\u1389');
        khmerFoots.put("្ឃ", '\u138a');
        khmerFoots.put("្ឈ", '\u138b');
        khmerFoots.put("្ប", '\u138c');

        khmerFoots.put("្យ", '\u138d');
        khmerFoots.put("្ឞ", '\u138e');
        khmerFoots.put("្រ", '\u138f');
        khmerFoots.put("្ន", '\u1390');
        khmerFoots.put("្វ", '\u1391');

        khmerFoots.put("្ខ", '\u1392');
        khmerFoots.put("្ង", '\u1393');
        khmerFoots.put("្ច", '\u1394');
        khmerFoots.put("្ឋ", '\u1395');
        khmerFoots.put("្ឌ", '\u1396');

        khmerFoots.put("្ទ", '\u1397');
        khmerFoots.put("្ធ", '\u1398');
        khmerFoots.put("្គ", '\u1399');
        khmerFoots.put("្ម", '\u139a');
        khmerFoots.put("្ល", '\u139b');

        khmerFoots.put("្ក", '\u139c');
        khmerFoots.put("្ឆ", '\u139d');
        khmerFoots.put("្ជ", '\u139e');
        khmerFoots.put("្ត", '\u139f');
        khmerFoots.put("្ណ", '\u13a0');

        khmerFoots.put("្ដ", '\u13a1');
        khmerFoots.put("្ថ", '\u13a2');
        khmerFoots.put("្ផ", '\u13a3');
        khmerFoots.put("្ព", '\u13a4');
        khmerFoots.put("្ភ", '\u13a5');

        khmerFoots.put("្ឝ", '\u13a6');
        khmerFoots.put("្ហ", '\u13a7');
        khmerFoots.put("្អ", '\u13a8');
        khmerFoots.put("្ញ", '\u13a9');
    }

    static HashMap<String, Character> khmerDoubleFoots;

    static {
        khmerDoubleFoots = new HashMap<>();
        khmerDoubleFoots.put("្ឍ", '\u1288');
        khmerDoubleFoots.put("្ស", '\u1289');
        khmerDoubleFoots.put("្ឃ", '\u128a');
        khmerDoubleFoots.put("្ឈ", '\u128b');
        khmerDoubleFoots.put("្ប", '\u128c');

        khmerDoubleFoots.put("្យ", '\u128d');
        khmerDoubleFoots.put("្ឞ", '\u128e');
        khmerDoubleFoots.put("្រ", '\u128f');
        khmerDoubleFoots.put("្ន", '\u1290');
        khmerDoubleFoots.put("្វ", '\u1291');

        khmerDoubleFoots.put("្ខ", '\u1292');
        khmerDoubleFoots.put("្ង", '\u1293');
        khmerDoubleFoots.put("្ច", '\u1294');
        khmerDoubleFoots.put("្ឋ", '\u1295');
        khmerDoubleFoots.put("្ឌ", '\u1296');

        khmerDoubleFoots.put("្ទ", '\u1297');
        khmerDoubleFoots.put("្ធ", '\u1298');
        khmerDoubleFoots.put("្គ", '\u1299');
        khmerDoubleFoots.put("្ម", '\u129a');
        khmerDoubleFoots.put("្ល", '\u129b');

        khmerDoubleFoots.put("្ក", '\u129c');
        khmerDoubleFoots.put("្ឆ", '\u129d');
        khmerDoubleFoots.put("្ជ", '\u129e');
        khmerDoubleFoots.put("្ត", '\u129f');
        khmerDoubleFoots.put("្ណ", '\u12a0');

        khmerDoubleFoots.put("្ដ", '\u12a1');
        khmerDoubleFoots.put("្ថ", '\u12a2');
        khmerDoubleFoots.put("្ផ", '\u12a3');
        khmerDoubleFoots.put("្ព", '\u12a4');
        khmerDoubleFoots.put("្ភ", '\u12a5');

        khmerDoubleFoots.put("្ឝ", '\u12a6');
        khmerDoubleFoots.put("្ហ", '\u12a7');
        khmerDoubleFoots.put("្អ", '\u12a8');
        khmerDoubleFoots.put("្ញ", '\u12a9');
    }

    @Override
    public void create() {
        batch = new SpriteBatch();
        font = new BitmapFont(Gdx.files.internal("khmerOsBattamang-64.fnt"));
        font.getData().setScale(32f / 64f);
//		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("KhmerOSbattambang.ttf"));
//		FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
//		parameter.size = 100;
//		parameter.color = Color.WHITE;
//		parameter.characters = KHMER_ALL;
//		font = generator.generateFont(parameter);
//		generator.dispose();
        text = khmerFilter(
                "ចំនួនកាក់់របស់លោកអ្នកសែនធំដើម្បីលេងនៅ\nតំបន់ទីក្រុងនេះ");
    }

    String text;

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        testText();
        batch.end();
    }

    private void testText() {
        font.draw(batch, text,
                50, 430);
    }

    private void testFoot() {
        font.draw(batch, khmerFilter(
                "គ្រ ក្ខ្ក ក្ខ្ខ ក្ខ្គ ក្ខ្ឃ ក្ខ្ង ក្ខ្ច ក្ខ្ឆ ក្ខ្ជ ក្ខ្ឈ ក្ខ្ញ \n" +
                        "ក្ខ្ដ ក្ខ្ឋ ក្ខ្ឌ ក្ខ្ឍ  ក្ខ្ណ ក្ខ្ត ក្ខ្ថ ក្ខ្ទ ក្ខ្ធ ក្ខ្ន \n" +
                        "ក្ខ្ប ក្ខ្ផ ក្ខ្ព ក្ខ្ភ ក្ខ្ម ក្ខ្យ ក្ខ្រ ក្ខ្ល ក្ខ្វ \n" +
                        "ក្ខ្ឝ ក្ខ្ឞ ក្ខ្ស ក្ខ្ហ ក្ខ្អ"),
                50, 430);
    }

    private void testVowel() {
        font.draw(batch, khmerFilter(
                "កា កិ កី កឹ កឺ កុ កូ កួ \n" +
                        "កើ កឿ កៀ កេ កែ កៃ កោ កៅ"),
                50, 430);
    }

    private void testDiacritic() {
        font.draw(batch, khmerFilter(
                "កំ កះ កៈ  ក៉  ក៊ កុ ក់ ក៌ ក៍ ក៎ ក៏ ក័ ក៑ ក៓"),
                50, 430);
    }

    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
    }

    String khmerFilter(String input) {
        char[] chars = input.toCharArray();
        System.out.println(Arrays.toString(chars));
        StringBuilder output = new StringBuilder();
        StringBuilder word = new StringBuilder();
        String foot;
        for (int i = 0; i < chars.length; i++) {
            System.out.println("->" + chars[i]);
            switch (chars[i]) {
                case '\u17d2':
                    if (i < chars.length - 1) {
                        if (word.length() > 0 && word.charAt(word.length() - 1) == '\u1789') {
                            word.setCharAt(word.length() - 1, '\u0fa1');
                        }
                        // first foot
                        foot = chars[i] + "" + chars[i + 1];
                        if (khmerFoots.containsKey(foot)) {
                            if (chars[i + 1] == '\u179a') {
                                word.insert(word.length() - 1, khmerFoots.get(foot));
                            } else {
                                word.append(khmerFoots.get(foot));
                            }
                            i++;
                        }
                        // second foot
                        if (i < chars.length - 2 && chars[i + 1] == '\u17d2') {
                            i++;
                            foot = chars[i] + "" + chars[i + 1];
                            if (khmerDoubleFoots.containsKey(foot)) {
                                if (chars[i + 1] == '\u179a') {
                                    word.insert(word.length() - 2, khmerDoubleFoots.get(foot));
                                } else if (chars[i - 1] == '\u179a') {
                                    word.append(khmerFoots.get(foot));
                                } else {
                                    word.append(khmerDoubleFoots.get(foot));
                                }
                                i++;
                            }
                        }

                        if (i < chars.length - 1) {
                            if (chars[i + 1] == '\u17bb') {
                                word.append('\u0feb');
                                i++;
                            } else if (chars[i + 1] == '\u17bc') {
                                word.append('\u0fec');
                                i++;
                            } else if (chars[i + 1] == '\u17bd') {
                                word.append('\u0fed');
                                i++;
                            }
                        }
                    }
                    break;
                case '\u17be':
                    word.insert(0, '\u0fa0');
                    word.append('\u17b8');
                    break;
                case '\u17bf':
                case '\u17c0':
                    word.insert(0, '\u0fa0');
                    word.append(chars[i]);
                    break;
                case '\u17c1':
                case '\u17c2':
                case '\u17c3':
                    word.insert(0, chars[i]);
                    break;
                case '\u17c5':
                case '\u17c4':
                    word.insert(0, '\u17c1');
                    word.append(chars[i]);
                    break;
                default:
                    System.out.println(word);
                    output.append(word);
                    word = new StringBuilder();
                    word.append(chars[i]);
                    break;
            }
        }
        output.append(word);
        return output.toString();
    }
}
