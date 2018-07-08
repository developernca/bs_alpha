package com.developernca.utility;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.developernca.game.BSGame;

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
     * @param lblText   text to display
     * @param fontType  true type font(ttf) file name
     * @param fontColor font color
     * @param fontSize  font size in pixel
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
        fontParameter.borderColor = Color.WHITE;
        fontParameter.borderWidth = BSGame.as.pt(0.5f);
        fontParameter.size = fontSize;
        fontParameter.characters = BSGame.fontParameterCharacters;
        return generator.generateFont(fontParameter);
    }



    /**
     * Check whether a given number is between a desired start and end numbers.
     *
     * @param num   number to check
     * @param start start value (inclusive)
     * @param end   end value (inclusive)
     * @return boolean
     */
    public static boolean BSMathBetween(int num, int start, int end) {
        return (num >= start && num <= end);
    }

}