package org.kitfox.string;

import java.text.Normalizer;
import java.util.regex.Pattern;

public class UnaccentUtils {
  private UnaccentUtils() {}

  public static String unAccent(String s) {
      String temp = Normalizer.normalize(s, Normalizer.Form.NFD);
      Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
      return pattern.matcher(temp).replaceAll("");
  }

  public static void main(String args[]) throws Exception{
      String s = "È,É,Ê,Ë,Û,Ù,Ï,Î,À,Â,Ô,ç,è,é,ê,ë,û,ù,ï,î,à,â,ô";
      System.out.println(UnaccentUtils.unAccent(s));
      // output : e a i _ @
  }
}