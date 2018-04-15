package com.developernca.utility;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

/**
 * Utility class for whole game.
 * Created on 4/15/2018.
 *
 * @author Nyein Chan Aung
 * @since 1.0
 */

public class BSUtils {

    /**
     * Create a scene2d ui label.
     *
     * @param lblText    text to display
     * @param fontType   true type font(ttf) file name
     * @param fontColor  font color
     * @param fontSize   font size in pixel
     * @return Label a label object
     */
    public static Label makeLabel(String lblText, String fontType, Color fontColor, int fontSize) {
        Label.LabelStyle style = new Label.LabelStyle();
        style.font = getFreeTypeFont(fontType, fontColor, fontSize);
        return new Label(lblText, style);
    }

    /**
     * Make Bitmap font with gdx {@link FreeTypeFontGenerator} extension.
     *
     * @param fontType  true type font (ttf) file name
     * @param fontColor font color
     * @param fontSize  font size in pixel
     * @return BitmapFont
     */
    private static BitmapFont getFreeTypeFont(String fontType, Color fontColor, int fontSize) {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal(fontType));
        FreeTypeFontGenerator.FreeTypeFontParameter fontParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        fontParameter.color = fontColor;
        fontParameter.borderWidth = 0.0f;
        fontParameter.size = fontSize;
        fontParameter.characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        return generator.generateFont(fontParameter);
    }
}