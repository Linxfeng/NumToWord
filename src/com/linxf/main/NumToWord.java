package com.linxf.main;

/**
 * 将数字转换为大写汉字输出
 * 
 * @author lintao
 *
 */
public class NumToWord {
	
	/**
	 * 将String强转为long
	 * 
	 * @param s
	 * @return
	 */
	public long num(String s) {
		long n = Long.parseLong(s);
		return n;
	}

	/**
	 * 判断n有多少位
	 * 
	 * @param n
	 * @return
	 */
	public int hwei(long n) {
		int wei = 1;
		for (int i = 2; n >= 10; i++) {
			wei = i;
			n /= 10;
		}
		return wei;
	}

	/**
	 * 将n中的数字逐位保存在一个数组中
	 * @param n
	 * @return
	 */
	public int[] cutNum(long n) {
		int wei = hwei(n);// 获取n的位数
		int[] num = new int[wei];
		for (int i = 0; i < wei; i++) {
			num[i] = (int) (n % 10);
			n /= 10;
		}
		return num;
	}
	
	/**
	 * 将int类型数组对应转换为大写汉字数组输出
	 * @param num
	 * @return
	 */
	public String[] word(int[] num, long n){
		int wei = hwei(n);// 获取n的位数
		
		String word_bz[] = {"零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖"};
		String word[] = new String[wei];

		for (int i = 0; i < wei; i++) {
			switch (num[i]) {
			case 0:
				word[i] = word_bz[0];
				break;
			case 1:
				word[i] = word_bz[1];
				break;
			case 2:
				word[i] = word_bz[2];
				break;
			case 3:
				word[i] = word_bz[3];
				break;
			case 4:
				word[i] = word_bz[4];
				break;
			case 5:
				word[i] = word_bz[5];
				break;
			case 6:
				word[i] = word_bz[6];
				break;
			case 7:
				word[i] = word_bz[7];
				break;
			case 8:
				word[i] = word_bz[8];
				break;
			case 9:
				word[i] = word_bz[9];
				break;
			}
		}
		return word;
	}
	
	/**
	 * 将汉字数组加上量级单位数组输出
     *
	 * @param word
	 * @param n
	 * @return
	 */
	public String[] wordTato(String[] word, long n){
		
		int wei = hwei(n);// 获取n的位数
		
		String[] wordTato = new String[25];//接收处理之后的数组
		
		for (int i = 0; i < wei; i++) {
			switch (i) {
			case 0:
				wordTato[0] = word[0];
				break;
			case 1:
				wordTato[1] = "拾";
				wordTato[2] = word[i];
				break;
			case 2:
				wordTato[3] = "佰";
				wordTato[4] = word[i];
				break;
			case 3:
				wordTato[5] = "仟";
				wordTato[6] = word[i];
				break;
			case 4:
				wordTato[7] = "萬";
				wordTato[8] = word[i];
				break;
			case 5:
				wordTato[9] = "拾";
				wordTato[10] = word[i];
				break;
			case 6:
				wordTato[11] = "佰";
				wordTato[12] = word[i];
				break;
			case 7:
				wordTato[13] = "仟";
				wordTato[14] = word[i];
				break;
			case 8:
				wordTato[15] = "亿";
				wordTato[16] = word[i];
				break;
			case 9:
				wordTato[17] = "拾";
				wordTato[18] = word[i];
				break;
			case 10:
				wordTato[19] = "佰";
				wordTato[20] = word[i];
				break;
			case 11:
				wordTato[21] = "仟";
				wordTato[22] = word[i];
				break;
			default:
				wordTato[i] = null;
				break;
			}
		}
		return wordTato;
	}
	
	/**
	 * 让数组wordTato中的内容连续传递给resultw[]
	 * 
	 * @param wordTato
	 * @return
	 */
	public String[] resultw(String[] wordTato){
		String[] resultw = new String[24];
		int n123 = 0;
		for (int i =wordTato.length-1 ; i >= 0; i--){
			if(wordTato[i] != null){
				resultw[n123] = wordTato[i];
				n123++;
			}
		}
		return resultw;
	}
	
	/**
	 * 获得最终汉字字符串输出
     *
	 * @param s
	 * @return
	 */
	public String wordFinal(String s){
		long n = this.num(s);
		int[] cutNum = cutNum(n);//将n中的数字逐位保存在一个数组中
		String[] word = word(cutNum, n);//将int类型数组对应转换为大写汉字数组输出
		String[] wordTato = wordTato(word, n);//将汉字数组加上量级单位数组输出
		
		//去掉零后面的单位
		for (int i =wordTato.length-1 ; i >= 0; i--){
			if("零".equals(wordTato[i])){
				if("萬".equals(wordTato[i-1]) ||  "亿".equals(wordTato[i-1])){
					//wordTato[i] = null;
				} else if(i != 0){
					wordTato[i-1] = null;
				}
			}
		}
		
		//让连在一起的零只显示一个
		String[] resultw = this.resultw(wordTato);
		
		for (int i = 0 ; i < resultw.length ; i++){
			if("零".equals(resultw[i])){
				if("零".equals(resultw[i+1])){
					resultw[i] = null;
				}
			}
		}
		
		//去掉萬、亿前面的零
		for (int i = resultw.length-1 ; i >= 0 ; i--){
			if("萬".equals(resultw[i]) || "亿".equals(resultw[i])){
				if("零".equals(resultw[i-1])){
					resultw[i-1] = null;
				}
			}
		}
	
		//如果萬前面是亿，就去掉萬
		String[] result2 = this.resultw(resultw);
		for (int i = 0 ; i < result2.length ; i++){
			if("萬".equals(result2[i])){
				if("亿".equals(result2[i+1])){
					result2[i] = null;
				}
			}
		}
		
		//获得最终汉字字符串输出
		String str = "";
		for (int i = result2.length-1 ; i >= 0  ; i--){
			if(result2[i] != null){	//去掉数组中的null
				str += result2[i];
			}
		}

        System.out.println("\n\n输入的数字："+s);
        System.out.println("\n转换输出："+str);
		return str;
	}
	

}
