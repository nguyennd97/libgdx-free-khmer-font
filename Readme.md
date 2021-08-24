# Free Libgdx Font for Khmer Script

Using Khmer Wrapper to solve [libgdx Khmer font issue](https://github.com/libgdx/libgdx/issues/6626).

- Copy font **khmerOsBattamang-64-full.fnt, khmerOsBattamang-64-full.png and KhmerWrapper** to your project
- Use **KhmerWrapper.wrap** to wrap String before put it in **Widget**.

## Example

Here is an example code:

        BitmapFont font = new BitmapFont(Gdx.files.internal("khmerOsBattamang-64-full.fnt"));
        font.draw(batch, KhmerWrapper.wrap("ឥឦឧឨឩឪឫឬឭឮឯឰឱឲឳ"), 50, 430);

