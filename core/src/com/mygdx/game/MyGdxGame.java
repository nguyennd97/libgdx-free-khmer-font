package com.mygdx.game;

import static com.mygdx.game.KhmerWrapper.wrap;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MyGdxGame extends ApplicationAdapter {

    SpriteBatch batch;
    BitmapFont font;

    String testConsonants = wrap("កខគឃងចឆជឈញដឋឌឍណ\nតថទធនបផពភមយរលវឝឞសហឡអ");
    String testVowel = wrap("ឥឦឧឨឩឪឫឬឭឮឯឰឱឲឳ");
    String testMark = wrap("។៘ៗ៕៚៙៖");
    String testNumbers = wrap("០១២៣៤៥៦៧៨៩");
    String testFeet = wrap("ក្ខ្ក ក្ខ្ខ ក្ខ្គ ក្ខ្ឃ ក្ខ្ង ក្ខ្ច ក្ខ្ឆ ក្ខ្ជ ក្ខ្ឈ ក្ខ្ញ\n" +
            "ក្ខ្ដ ក្ខ្ឋ ក្ខ្ឌ ក្ខ្ឍ ក្ខ្ណ ក្ខ្ត ក្ខ្ថ ក្ខ្ទ ក្ខ្ធ ក្ខ្ន\n" +
            "ក្ខ្ប ក្ខ្ផ ក្ខ្ព ក្ខ្ភ ក្ខ្ម ក្ខ្យ ក្ខ្រ ក្ខ្ល ក្ខ្វ\n" +
            "ក្ខ្ឝ ក្ខ្ឞ ក្ខ្ស ក្ខ្ហ ក្ខ្អ");
    String testFoot = wrap("ក្ក ក្ខ ក្គ ក្ឃ ក្ង ក្ច ក្ឆ ក្ជ ក្ឈ ក្ញ ក្ដ ក្ឋ ក្ឌ\n" +
            "ក្ឍ  ក្ណ ក្ត ក្ថ ក្ទ ក្ធ ក្ន ក្ប ក្ផ ក្ព ក្ភ \n" +
            "ក្ម ក្យ ក្រ ក្ល ក្វ ក្ឝ ក្ឞ ក្ស ក្ហ ក្អ");
    String testSpecialFoot = wrap("ញ្ក ញ្ញ ក្ញ");
    String testDependentVowel = wrap("កា កិ កី កឹ កឺ កុ កូ កួ \n" +
            "កើ កឿ កៀ កេ កែ កៃ កោ កៅ");
    String testFootVowel = wrap("ក្កុ ក្កូ ក្កួ");
    String testSpecialDependentVowel = wrap(wrap("ញុ ញូ ញួ ឡុ ឡូ ឡួ\n" +
            "ញ្ក ញ្ខ ញ្គ ញ្ឃ ញ្ង ញ្ច ញ្ឆ ញ្ជ ញ្ឈ ញ្ញ ញ្ដ \n" +
            "ញ្ឋ ញ្ឌ ញ្ឍ  ញ្ណ \n" +
            "ញ្ត ញ្ថ ញ្ទ ញ្ធ ញ្ន ញ្ប ញ្ផ ញ្ព ញ្ភ ញ្ម ញ្យ \n" +
            "ញ្រ ញ្ល ញ្វ ញ្ឝ ញ្ឞ ញ្ស ញ្ហ ញ្អ"));
    String testLongCharacters = wrap("ក្ក្ឃ ក្ក្ឈ ក្ក្ឍ ក្ក្ប ក្ក្ប ក្ក្យ ក្ក្រ ក្ក្ឞ ក្ក្ស ក្កឿ ក្កៀ");
    String testDiacritic = wrap("កំ កះ កៈ ក៉ ក៊ កុ ក់ ក៌ ក៍ ក៎ ក៏ ក័ ក៑ ក៓");


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
        font.draw(batch, testSpecialDependentVowel, 50, 430);
//        font.draw(batch, testFoot, 50, 430);
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
    }
}
