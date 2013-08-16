package com.vdc.util;

//import net.sourceforge.pinyin4j.PinyinHelper;
//import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
//import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
//import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
//import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
//import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * 拼音相关的工具类
 * @author daniel
 */
public class PinyinUtil {
//	public static void main(String[] args){
//        String cnStr = "我是杨磊，英文名叫daniel.我喜欢旅游,I LIKE TRAVEL！" ;
////		String cnStr = "我是杨磊，" ;
//        System.out.println(getPinyin(cnStr,false,true,false));
////        System.out.println(getCnASCII(cnStr));
//	}
//	
//	/**
//	 * 获取拼音
//	 * @param inputStr 输入的字符串
//	 * @return
//	 * @author daniel
//	 */
//	public static String getPinyin(String inputStr) {
//		return getPinyin(inputStr, false, false, false);
//	}
//	/**
//	 * 获取拼音
//	 * @param inputStr 输入的字符串
//	 * @param ignoreNullPinyin 是否忽略非拼音部分
//	 * @return
//	 * @author daniel
//	 */
//	public static String getPinyin(String inputStr,boolean ignoreNullPinyin) {
//		return getPinyin(inputStr, ignoreNullPinyin, false, false);
//	}
//	/**
//	 * 获取拼音
//	 * @param inputStr 输入的字符串
//	 * @param ignoreNullPinyin 是否忽略非拼音部分
//	 * @param upperCaseHeadChar 是否大写拼音的首字母
//	 * @param justExtractHeadChar 是否只提取拼音的首字母
//	 * @return
//	 */
//	public static String getPinyin(String inputStr,boolean ignoreNullPinyin,boolean upperCaseHeadChar,boolean justExtractHeadChar) {
//		StringBuilder retStr = new StringBuilder();
//		// 设置汉字拼音输出的格式
//		HanyuPinyinOutputFormat formater = new HanyuPinyinOutputFormat();
//		formater.setCaseType(HanyuPinyinCaseType.LOWERCASE);
//		formater.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
//		formater.setVCharType(HanyuPinyinVCharType.WITH_V);
//		for (int j = 0; j < inputStr.length(); j++) {
//			char charStr = inputStr.charAt(j);
//			String[] pinyinArray=null;
//			try {
//				pinyinArray = PinyinHelper.toHanyuPinyinStringArray(charStr, formater);
//			} catch (BadHanyuPinyinOutputFormatCombination e) {
//				e.printStackTrace();
//			}
//			if (pinyinArray != null) {
//				if(upperCaseHeadChar){
//					if(justExtractHeadChar){
//						retStr.append(Character.toString(pinyinArray[0].charAt(0)).toUpperCase());
//					}else{
//						char[] charArr=pinyinArray[0].toCharArray();
//						for(int k=0;k<charArr.length;k++){
//							if(k==0){
//								retStr.append(Character.toString(charArr[k]).toUpperCase());
//							}else{
//								retStr.append(charArr[k]);
//							}
//						}
//					}
//				}else{
//					if(justExtractHeadChar){
//						retStr.append(pinyinArray[0].charAt(0));// 提取汉字的首字母
//					}else{
//						retStr.append(pinyinArray[0]);
//					}
//				}
//			} else {
//				if(!ignoreNullPinyin){
//					retStr.append(charStr);
//				}
//			}
//		}
//		return retStr.toString();
//	}
//
//     /**
//      * 将字符串转换成ASCII码
//      *
//      * @param cnStr 字符串
//      * @return
//      */
//     public static String getCnASCII(String cnStr) {
//    	 StringBuffer strBuf = new StringBuffer();
//    	 // 将字符串转换成字节序列
//    	 byte[] bGBK = cnStr.getBytes();
//    	 for (int i = 0; i < bGBK.length; i++) {
//    		 // 将每个字符转换成ASCII码
//    		 strBuf.append(Integer.toHexString(bGBK[i] & 0xff));
//    	 }
//    	 return strBuf.toString();
//     }
}
