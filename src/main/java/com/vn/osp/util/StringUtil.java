package com.vn.osp.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by TienManh on 7/25/2017.
 */
public class StringUtil {

    public static String removeSpecialCharactersNotSpace(String value) {
        String ALPHANUMERIC = "[^\\p{L}\\p{N} ]+";
//        String ALPHANUMERIC = "[^\\p{L}\\p{N} ~`!@#$%^&*()_=+{}<>\"/;=&:\\-\\[\\]]+";
        try {
            return value.replaceAll(ALPHANUMERIC, "");
        } catch (Exception var2) {
            return null;
        }
    }
    public static String removeSpecialCharactersNotHTML(String value) {
        /* ky' hieu dau' "-" thi` se la` "\\-", con` dau' "\" thi` se la "\\\\", tuong tu ] la \\], [ la \\[ */
        String ALPHANUMERIC = "[^\\p{L}\\p{N} ~`!@#$%^&*()_=+{}<>\"/;=&:\\-\\[\\]\t\\\\°C≥≤≡≠×÷±→?.,|«©®£¨»½¼¾ǂʼʾʿˀˁ˅ΣΞΘΔθπФ‰†‡⅓⅔⅕⅖⅗⅘⅙⅚⅛⅜⅝⅞⅟―Γʽ←↑→↓↔↕←↑→↓↔↕↖↗↘↙↚↛↜↝↞↟↠↡↢↣↤↥↦↧↨↩↪↫↬↭↮↯↰↱↲↳↴↵↶↷↸↹↺↻↼↽↾↿⇀⇁⇂⇃⇄⇅⇆⇇⇈⇉⇊⇋⇌⇍⇎⇏⇐⇑⇒⇓⇔⇕⇖⇗⇘⇙⇚⇛⇜⇝⇞⇟⇠⇡⇢⇣⇤⇥⇦⇧⇨⇩⇪⇫⇬⇭⇮⇯⇰⇱⇲⇳⇴⇵⇶⇷⇸⇹⇺⇻⇼⇽⇾⇿∀∁∂∃∄∅∆∇∈∉∊∋∌∍∎∏∐∑−∓∔∕∖∗∘∙√∛∜∝∞∟∠∡∢∣∤∥∦∧∨∩∪∫∬∭∮∯∰∱∲∳∴∵∶∷∸∹∺∻∼∽∾∿≀≁≂≃≄≅≆≇≈≉≊≋≌≍≎≏≐≑≒≓≔≕≖≗≘≙≚≛≜≝≞≟≠≡≢≣≤≥≦≧≨≩≪≫≬≭≮≯≰≱≲≳≴≵≶≷≸≹≺≻≼≽≾≿⊀⊁⊂⊃⊄⊅⊆⊇⊈⊉⊊⊋⊌⊍⊎⊏⊐⊑⊒⊓⊔⊕⊖⊗⊘⊙⊚⊛⊜⊝⊞⊟⊠⊡⊢⊣⊤⊥⊦⊧⊨⊩⊪⊫⊬⊭⊮⊯⊰⊱⊲⊳⊴⊵⊶⊷⊸⊹⊺⊻⊼⊽⊾⊿⋀⋁⋂⋃⋄⋅⋆⋇⋈⋉⋊⋋⋌⋍⋎⋏⋐⋑⋒⋓⋔⋕⋖⋗⋘⋙⋚⋛⋜⋝⋞⋟⋠⋡⋢⋣⋤⋥⋦⋧⋨⋩⋪⋫⋬⋭⋮⋯⋰⋱⋲⋳⋴⋵⋶⋷⋸⋹⋺⋻⋼⋽⋾⋿]+";
        try {
            return value.replaceAll(ALPHANUMERIC, "");
        } catch (Exception var2) {
            return null;
        }
    }
    public static boolean checkSpecialCharacter(String value) {
        String ALPHANUMERIC = "^[\\p{L}\\p{N} ]+";//gom` ko phai la cac so, chu~ ko dau' va co dau', dau cach
        try {
            Pattern p = Pattern.compile(ALPHANUMERIC, Pattern.CANON_EQ | Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);
            Matcher m = p.matcher(value);
            return m.matches();
        } catch (Exception var2) {
            return false;
        }
    }
//    public static void main(String[] args){
//        String str="<div class=\"sdfs\" style=\"font:'Time New Roman';font-size:23px\">~!@#$%^&*()_+=-[] bạn nghĩ \t sao \\t  °C ≥ ≤ ≡ ≠ × ÷ ± → ? ...</div>";
//        String b=removeSpecialCharactersNotHTML(str);
//        System.out.println(str);
//        System.out.println(b);
//        String g="←	↑	→	↓	↔	↕	↖	↗	↘	↙	↚	↛	↜	↝	↞	↟";
//        g+="↠	↡	↢	↣	↤	↥	↦	↧	↨	↩	↪	↫	↬	↭	↮	↯";
//        g+="↰	↱	↲	↳	↴	↵	↶	↷	↸	↹	↺	↻	↼	↽	↾	↿";
//        g+="⇀	⇁	⇂	⇃	⇄	⇅	⇆	⇇	⇈	⇉	⇊	⇋	⇌	⇍	⇎	⇏";
//        g+="⇐	⇑	⇒	⇓	⇔	⇕	⇖	⇗	⇘	⇙	⇚	⇛	⇜	⇝	⇞	⇟";
//       	g+="⇠	⇡	⇢	⇣	⇤	⇥	⇦	⇧	⇨	⇩	⇪	⇫	⇬	⇭	⇮	⇯";
//        g+="⇰	⇱	⇲	⇳	⇴	⇵	⇶	⇷	⇸	⇹	⇺	⇻	⇼	⇽	⇾	⇿";
//        g+="∀	∁	∂	∃	∄	∅	∆	∇	∈	∉	∊	∋	∌	∍	∎	∏";
//        g+="∐	∑	−	∓	∔	∕	∖	∗	∘	∙	√	∛	∜	∝	∞	∟";
//        g+="∠	∡	∢	∣	∤	∥	∦	∧	∨	∩	∪	∫	∬	∭	∮	∯";
//        g+="∰	∱	∲	∳	∴	∵	∶	∷	∸	∹	∺	∻	∼	∽	∾	∿";
//        g+="≀	≁	≂	≃	≄	≅	≆	≇	≈	≉	≊	≋	≌	≍	≎	≏";
//        g+="≐	≑	≒	≓	≔	≕	≖	≗	≘	≙	≚	≛	≜	≝	≞	≟";
//        g+="≠	≡	≢	≣	≤	≥	≦	≧	≨	≩	≪	≫	≬	≭	≮	≯";
//        g+="≰	≱	≲	≳	≴	≵	≶	≷	≸	≹	≺	≻	≼	≽	≾	≿";
//        g+="⊀	⊁	⊂	⊃	⊄	⊅	⊆	⊇	⊈	⊉	⊊	⊋	⊌	⊍	⊎	⊏";
//        g+="⊐	⊑	⊒	⊓	⊔	⊕	⊖	⊗	⊘	⊙	⊚	⊛	⊜	⊝	⊞	⊟";
//        g+="⊠	⊡	⊢	⊣	⊤	⊥	⊦	⊧	⊨	⊩	⊪	⊫	⊬	⊭	⊮	⊯";
//        g+="⊰	⊱	⊲	⊳	⊴	⊵	⊶	⊷	⊸	⊹	⊺	⊻	⊼	⊽	⊾	⊿";
//        g+="⋀	⋁	⋂	⋃	⋄	⋅	⋆	⋇	⋈	⋉	⋊	⋋	⋌	⋍	⋎	⋏";
//        g+="⋐	⋑	⋒	⋓	⋔	⋕	⋖	⋗	⋘	⋙	⋚	⋛	⋜	⋝	⋞	⋟";
//        g+="⋠	⋡	⋢	⋣	⋤	⋥	⋦	⋧	⋨	⋩	⋪	⋫	⋬	⋭	⋮	⋯";
//        g+="⋰	⋱	⋲	⋳	⋴	⋵	⋶	⋷	⋸	⋹	⋺	⋻	⋼	⋽	⋾	⋿";
////        g+="[^\\p{L}\\p{N} ~`!@#$%^&*()_=+{}<>\"/;=&:\\-\\[\\]\t\\\\°C≥≤≡≠×÷±→?.,|«©®£¨»½¼¾ǂʼʾʿˀˁ˅ΣΞΘΔθπФ‰†‡⅓⅔⅕⅖⅗⅘⅙⅚⅛⅜⅝⅞⅟―Γʽ←↑→↓↔↕]+";
//        g=g.replaceAll(" ","");
//        g=g.replaceAll("\t","");
//        System.out.println(g);
//        g=removeSpecialCharactersNotHTML(g);
//        System.out.println(g);
//
//    }
}
