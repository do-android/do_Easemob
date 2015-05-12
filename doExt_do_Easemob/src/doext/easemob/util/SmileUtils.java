/**
 * Copyright (C) 2013-2014 EaseMob Technologies. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *     http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package doext.easemob.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.content.Context;
import android.text.Spannable;
import android.text.Spannable.Factory;
import android.text.style.ImageSpan;
import core.helper.DoResourcesHelper;
import doext.app.do_HuanXinIM_App;


public class SmileUtils {
	public static final String ee_1 = "[):]";
	public static final String ee_2 = "[:D]";
	public static final String ee_3 = "[;)]";
	public static final String ee_4 = "[:-o]";
	public static final String ee_5 = "[:p]";
	public static final String ee_6 = "[(H)]";
	public static final String ee_7 = "[:@]";
	public static final String ee_8 = "[:s]";
	public static final String ee_9 = "[:$]";
	public static final String ee_10 = "[:(]";
	public static final String ee_11 = "[:'(]";
	public static final String ee_12 = "[:|]";
	public static final String ee_13 = "[(a)]";
	public static final String ee_14 = "[8o|]";
	public static final String ee_15 = "[8-|]";
	public static final String ee_16 = "[+o(]";
	public static final String ee_17 = "[<o)]";
	public static final String ee_18 = "[|-)]";
	public static final String ee_19 = "[*-)]";
	public static final String ee_20 = "[:-#]";
	public static final String ee_21 = "[:-*]";
	public static final String ee_22 = "[^o)]";
	public static final String ee_23 = "[8-)]";
	public static final String ee_24 = "[(|)]";
	public static final String ee_25 = "[(u)]";
	public static final String ee_26 = "[(S)]";
	public static final String ee_27 = "[(*)]";
	public static final String ee_28 = "[(#)]";
	public static final String ee_29 = "[(R)]";
	public static final String ee_30 = "[({)]";
	public static final String ee_31 = "[(})]";
	public static final String ee_32 = "[(k)]";
	public static final String ee_33 = "[(F)]";
	public static final String ee_34 = "[(W)]";
	public static final String ee_35 = "[(D)]";
	
	private static final Factory spannableFactory = Spannable.Factory
	        .getInstance();
	
	private static final Map<Pattern, Integer> emoticons = new HashMap<Pattern, Integer>();
	private static String moduleTypeID = do_HuanXinIM_App.getInstance().getModuleTypeID();
	
	static {
		
	    addPattern(emoticons, ee_1, DoResourcesHelper.getRIdByDrawable("ee_1", moduleTypeID));
	    addPattern(emoticons, ee_2, DoResourcesHelper.getRIdByDrawable("ee_2", moduleTypeID));
	    addPattern(emoticons, ee_3, DoResourcesHelper.getRIdByDrawable("ee_3", moduleTypeID));
	    addPattern(emoticons, ee_4, DoResourcesHelper.getRIdByDrawable("ee_4", moduleTypeID));
	    addPattern(emoticons, ee_5, DoResourcesHelper.getRIdByDrawable("ee_5", moduleTypeID));
	    addPattern(emoticons, ee_6, DoResourcesHelper.getRIdByDrawable("ee_6", moduleTypeID));
	    addPattern(emoticons, ee_7, DoResourcesHelper.getRIdByDrawable("ee_7", moduleTypeID));
	    addPattern(emoticons, ee_8, DoResourcesHelper.getRIdByDrawable("ee_8", moduleTypeID));
	    addPattern(emoticons, ee_9, DoResourcesHelper.getRIdByDrawable("ee_9", moduleTypeID));
	    addPattern(emoticons, ee_10, DoResourcesHelper.getRIdByDrawable("ee_10", moduleTypeID));
	    addPattern(emoticons, ee_11, DoResourcesHelper.getRIdByDrawable("ee_11", moduleTypeID));
	    addPattern(emoticons, ee_12, DoResourcesHelper.getRIdByDrawable("ee_12", moduleTypeID));
	    addPattern(emoticons, ee_13, DoResourcesHelper.getRIdByDrawable("ee_13", moduleTypeID));
	    addPattern(emoticons, ee_14, DoResourcesHelper.getRIdByDrawable("ee_14", moduleTypeID));
	    addPattern(emoticons, ee_15, DoResourcesHelper.getRIdByDrawable("ee_15", moduleTypeID));
	    addPattern(emoticons, ee_16, DoResourcesHelper.getRIdByDrawable("ee_16", moduleTypeID));
	    addPattern(emoticons, ee_17, DoResourcesHelper.getRIdByDrawable("ee_17", moduleTypeID));
	    addPattern(emoticons, ee_18, DoResourcesHelper.getRIdByDrawable("ee_18", moduleTypeID));
	    addPattern(emoticons, ee_19, DoResourcesHelper.getRIdByDrawable("ee_19", moduleTypeID));
	    addPattern(emoticons, ee_20, DoResourcesHelper.getRIdByDrawable("ee_20", moduleTypeID));
	    addPattern(emoticons, ee_21, DoResourcesHelper.getRIdByDrawable("ee_21", moduleTypeID));
	    addPattern(emoticons, ee_22, DoResourcesHelper.getRIdByDrawable("ee_22", moduleTypeID));
	    addPattern(emoticons, ee_23, DoResourcesHelper.getRIdByDrawable("ee_23", moduleTypeID));
	    addPattern(emoticons, ee_24, DoResourcesHelper.getRIdByDrawable("ee_24", moduleTypeID));
	    addPattern(emoticons, ee_25, DoResourcesHelper.getRIdByDrawable("ee_25", moduleTypeID));
	    addPattern(emoticons, ee_26, DoResourcesHelper.getRIdByDrawable("ee_26", moduleTypeID));
	    addPattern(emoticons, ee_27, DoResourcesHelper.getRIdByDrawable("ee_27", moduleTypeID));
	    addPattern(emoticons, ee_28, DoResourcesHelper.getRIdByDrawable("ee_28", moduleTypeID));
	    addPattern(emoticons, ee_29, DoResourcesHelper.getRIdByDrawable("ee_29", moduleTypeID));
	    addPattern(emoticons, ee_30, DoResourcesHelper.getRIdByDrawable("ee_30", moduleTypeID));
	    addPattern(emoticons, ee_31, DoResourcesHelper.getRIdByDrawable("ee_31", moduleTypeID));
	    addPattern(emoticons, ee_32, DoResourcesHelper.getRIdByDrawable("ee_32", moduleTypeID));
	    addPattern(emoticons, ee_33, DoResourcesHelper.getRIdByDrawable("ee_33", moduleTypeID));
	    addPattern(emoticons, ee_34, DoResourcesHelper.getRIdByDrawable("ee_34", moduleTypeID));
	    addPattern(emoticons, ee_35, DoResourcesHelper.getRIdByDrawable("ee_35", moduleTypeID));
	}

	private static void addPattern(Map<Pattern, Integer> map, String smile,
	        int resource) {
	    map.put(Pattern.compile(Pattern.quote(smile)), resource);
	}

	/**
	 * replace existing spannable with smiles
	 * @param context
	 * @param spannable
	 * @return
	 */
	public static boolean addSmiles(Context context, Spannable spannable) {
	    boolean hasChanges = false;
	    for (Entry<Pattern, Integer> entry : emoticons.entrySet()) {
	        Matcher matcher = entry.getKey().matcher(spannable);
	        while (matcher.find()) {
	            boolean set = true;
	            for (ImageSpan span : spannable.getSpans(matcher.start(),
	                    matcher.end(), ImageSpan.class))
	                if (spannable.getSpanStart(span) >= matcher.start()
	                        && spannable.getSpanEnd(span) <= matcher.end())
	                    spannable.removeSpan(span);
	                else {
	                    set = false;
	                    break;
	                }
	            if (set) {
	                hasChanges = true;
	                spannable.setSpan(new ImageSpan(context, entry.getValue()),
	                        matcher.start(), matcher.end(),
	                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
	            }
	        }
	    }
	    return hasChanges;
	}

	public static Spannable getSmiledText(Context context, CharSequence text) {
	    Spannable spannable = spannableFactory.newSpannable(text);
	    addSmiles(context, spannable);
	    return spannable;
	}
	
	public static boolean containsKey(String key){
		boolean b = false;
		for (Entry<Pattern, Integer> entry : emoticons.entrySet()) {
	        Matcher matcher = entry.getKey().matcher(key);
	        if (matcher.find()) {
	        	b = true;
	        	break;
	        }
		}
		
		return b;
	}
	
	
	
}
