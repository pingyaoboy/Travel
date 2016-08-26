package com.lhk.travel.travel.Utils;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * Created by pyboy on 15/10/31.
 */
public class GetPinyin {
    /**
     * 返回一个字的拼音
     * @param hanzi
     * @return
     */
    public static String toPinYin(char hanzi){
        HanyuPinyinOutputFormat hanyuPinyin = new HanyuPinyinOutputFormat();
        hanyuPinyin.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        hanyuPinyin.setToneType(HanyuPinyinToneType.WITH_TONE_MARK);
        hanyuPinyin.setVCharType(HanyuPinyinVCharType.WITH_U_UNICODE);
        String[] pinyinArray=null;
        try {
            //是否在汉字范围内
            if(hanzi>=0x4e00 && hanzi<=0x9fa5){
                pinyinArray = PinyinHelper.toHanyuPinyinStringArray(hanzi, hanyuPinyin);
            } else {
                return String.valueOf(hanzi);
            }
        } catch (BadHanyuPinyinOutputFormatCombination e) {
            e.printStackTrace();
        }
        //将获取到的拼音返回
        return pinyinArray[0];
    }

    public static String toPinYin(String hanzi){
        char[] hanzis = hanzi.toCharArray();
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < hanzis.length; i++) {
            buffer.append(toPinYin(hanzis[i]));
            buffer.append(" ");
        }
        return buffer.toString().trim();
    }
}
