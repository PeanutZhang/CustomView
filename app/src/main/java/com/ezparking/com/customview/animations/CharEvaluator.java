package com.ezparking.com.customview.animations;

import android.animation.TypeEvaluator;

/**
 * Created by zyh
 */

public class CharEvaluator implements TypeEvaluator<Character> {


    @Override
    public Character evaluate(float fraction, Character startValue, Character endValue) {
                // (A - Z) ( 65 - 90)
               //  char temp = 'A' int num = (int) temp;  字符转数字
              //  int num = 65 ;   char temp = (char) num; 数字转字符
        int startInt  = (int)startValue;
        int endInt = (int)endValue;
        int curInt = (int)(startInt + fraction *(endInt - startInt));
        char result = (char)curInt;
        return result;
    }
}
