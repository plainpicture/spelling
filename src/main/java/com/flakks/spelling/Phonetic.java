
package com.flakks.spelling;

import org.apache.commons.codec.language.ColognePhonetic;
import org.apache.commons.codec.language.DoubleMetaphone;

public class Phonetic {
	public static Sonnex sonnex = new Sonnex();
	public static ColognePhonetic colognePhonetic = new ColognePhonetic();
	public static DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
	
	public static String encode(String input, String locale) {
		if(locale.equals("de"))
			return colognePhonetic.encode(input);
		else if(locale.equals("fr"))
			return sonnex.encode(input);
		else if(locale.equals("en"))
			return doubleMetaphone.encode(input);
		else
			return null;
	}
}