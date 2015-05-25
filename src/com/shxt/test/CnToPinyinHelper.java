package com.shxt.test;

import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 
 * @author 刘文铭
 * @日期 2012 2012-12-15 下午2:15:01
 * @描述 获取汉字的全拼和首拼。
 */
public class CnToPinyinHelper {
	/** 存放拼音和其编码的Map */
	private static Map<String, Integer> pinyinMap = null;
	/** 存放生僻字和其拼音的Map */
	private static Map<Character, String> uncommonWordsMap = null;
	/** GBK 字符集名称 */
	private static final String CHARSET_GBK = "GBK";

	static {
		if (pinyinMap == null) {
			pinyinMap = Collections
			        .synchronizedMap(new LinkedHashMap<String, Integer>(396));
			uncommonWordsMap = Collections
			        .synchronizedMap(new LinkedHashMap<Character, String>(200));
		}
		initialize();
		initUncommonWords();
	}

	/** 构造方法声明为 private，不允许在外部进行实例化 */
	private CnToPinyinHelper() {
	}

	/**
	 * 初始化
	 */
	private static void initialize() {
		pinyinMap.put("’a", -20319);
		pinyinMap.put("’ai", -20317);
		pinyinMap.put("’an", -20304);
		pinyinMap.put("’ang", -20295);
		pinyinMap.put("’ao", -20292);
		pinyinMap.put("ba", -20283);
		pinyinMap.put("bai", -20265);
		pinyinMap.put("ban", -20257);
		pinyinMap.put("bang", -20242);
		pinyinMap.put("bao", -20230);
		pinyinMap.put("bei", -20051);
		pinyinMap.put("ben", -20036);
		pinyinMap.put("beng", -20032);
		pinyinMap.put("bi", -20026);
		pinyinMap.put("bian", -20002);
		pinyinMap.put("biao", -19990);
		pinyinMap.put("bie", -19986);
		pinyinMap.put("bin", -19982);
		pinyinMap.put("bing", -19976);
		pinyinMap.put("bo", -19805);
		pinyinMap.put("bu", -19784);
		pinyinMap.put("ca", -19775);
		pinyinMap.put("cai", -19774);
		pinyinMap.put("can", -19763);
		pinyinMap.put("cang", -19756);
		pinyinMap.put("cao", -19751);
		pinyinMap.put("ce", -19746);
		pinyinMap.put("ceng", -19741);
		pinyinMap.put("cha", -19739);
		pinyinMap.put("chai", -19728);
		pinyinMap.put("chan", -19725);
		pinyinMap.put("chang", -19715);
		pinyinMap.put("chao", -19540);
		pinyinMap.put("che", -19531);
		pinyinMap.put("chen", -19525);
		pinyinMap.put("cheng", -19515);
		pinyinMap.put("chi", -19500);
		pinyinMap.put("chong", -19484);
		pinyinMap.put("chou", -19479);
		pinyinMap.put("chu", -19467);
		pinyinMap.put("chuai", -19289);
		pinyinMap.put("chuan", -19288);
		pinyinMap.put("chuang", -19281);
		pinyinMap.put("chui", -19275);
		pinyinMap.put("chun", -19270);
		pinyinMap.put("chuo", -19263);
		pinyinMap.put("ci", -19261);
		pinyinMap.put("cong", -19249);
		pinyinMap.put("cou", -19243);
		pinyinMap.put("cu", -19242);
		pinyinMap.put("cuan", -19238);
		pinyinMap.put("cui", -19235);
		pinyinMap.put("cun", -19227);
		pinyinMap.put("cuo", -19224);
		pinyinMap.put("da", -19218);
		pinyinMap.put("dai", -19212);
		pinyinMap.put("dan", -19038);
		pinyinMap.put("dang", -19023);
		pinyinMap.put("dao", -19018);
		pinyinMap.put("de", -19006);
		pinyinMap.put("deng", -19003);
		pinyinMap.put("di", -18996);
		pinyinMap.put("dian", -18977);
		pinyinMap.put("diao", -18961);
		pinyinMap.put("die", -18952);
		pinyinMap.put("ding", -18783);
		pinyinMap.put("diu", -18774);
		pinyinMap.put("dong", -18773);
		pinyinMap.put("dou", -18763);
		pinyinMap.put("du", -18756);
		pinyinMap.put("duan", -18741);
		pinyinMap.put("dui", -18735);
		pinyinMap.put("dun", -18731);
		pinyinMap.put("duo", -18722);
		pinyinMap.put("’e", -18710);
		pinyinMap.put("’en", -18697);
		pinyinMap.put("’er", -18696);
		pinyinMap.put("fa", -18526);
		pinyinMap.put("fan", -18518);
		pinyinMap.put("fang", -18501);
		pinyinMap.put("fei", -18490);
		pinyinMap.put("fen", -18478);
		pinyinMap.put("feng", -18463);
		pinyinMap.put("fo", -18448);
		pinyinMap.put("fou", -18447);
		pinyinMap.put("fu", -18446);
		pinyinMap.put("ga", -18239);
		pinyinMap.put("gai", -18237);
		pinyinMap.put("gan", -18231);
		pinyinMap.put("gang", -18220);
		pinyinMap.put("gao", -18211);
		pinyinMap.put("ge", -18201);
		pinyinMap.put("gei", -18184);
		pinyinMap.put("gen", -18183);
		pinyinMap.put("geng", -18181);
		pinyinMap.put("gong", -18012);
		pinyinMap.put("gou", -17997);
		pinyinMap.put("gu", -17988);
		pinyinMap.put("gua", -17970);
		pinyinMap.put("guai", -17964);
		pinyinMap.put("guan", -17961);
		pinyinMap.put("guang", -17950);
		pinyinMap.put("gui", -17947);
		pinyinMap.put("gun", -17931);
		pinyinMap.put("guo", -17928);
		pinyinMap.put("ha", -17922);
		pinyinMap.put("hai", -17759);
		pinyinMap.put("han", -17752);
		pinyinMap.put("hang", -17733);
		pinyinMap.put("hao", -17730);
		pinyinMap.put("he", -17721);
		pinyinMap.put("hei", -17703);
		pinyinMap.put("hen", -17701);
		pinyinMap.put("heng", -17697);
		pinyinMap.put("hong", -17692);
		pinyinMap.put("hou", -17683);
		pinyinMap.put("hu", -17676);
		pinyinMap.put("hua", -17496);
		pinyinMap.put("huai", -17487);
		pinyinMap.put("huan", -17482);
		pinyinMap.put("huang", -17468);
		pinyinMap.put("hui", -17454);
		pinyinMap.put("hun", -17433);
		pinyinMap.put("huo", -17427);
		pinyinMap.put("ji", -17417);
		pinyinMap.put("jia", -17202);
		pinyinMap.put("jian", -17185);
		pinyinMap.put("jiang", -16983);
		pinyinMap.put("jiao", -16970);
		pinyinMap.put("jie", -16942);
		pinyinMap.put("jin", -16915);
		pinyinMap.put("jing", -16733);
		pinyinMap.put("jiong", -16708);
		pinyinMap.put("jiu", -16706);
		pinyinMap.put("ju", -16689);
		pinyinMap.put("juan", -16664);
		pinyinMap.put("jue", -16657);
		pinyinMap.put("jun", -16647);
		pinyinMap.put("ka", -16474);
		pinyinMap.put("kai", -16470);
		pinyinMap.put("kan", -16465);
		pinyinMap.put("kang", -16459);
		pinyinMap.put("kao", -16452);
		pinyinMap.put("ke", -16448);
		pinyinMap.put("ken", -16433);
		pinyinMap.put("keng", -16429);
		pinyinMap.put("kong", -16427);
		pinyinMap.put("kou", -16423);
		pinyinMap.put("ku", -16419);
		pinyinMap.put("kua", -16412);
		pinyinMap.put("kuai", -16407);
		pinyinMap.put("kuan", -16403);
		pinyinMap.put("kuang", -16401);
		pinyinMap.put("kui", -16393);
		pinyinMap.put("kun", -16220);
		pinyinMap.put("kuo", -16216);
		pinyinMap.put("la", -16212);
		pinyinMap.put("lai", -16205);
		pinyinMap.put("lan", -16202);
		pinyinMap.put("lang", -16187);
		pinyinMap.put("lao", -16180);
		pinyinMap.put("le", -16171);
		pinyinMap.put("lei", -16169);
		pinyinMap.put("leng", -16158);
		pinyinMap.put("li", -16155);
		pinyinMap.put("lia", -15959);
		pinyinMap.put("lian", -15958);
		pinyinMap.put("liang", -15944);
		pinyinMap.put("liao", -15933);
		pinyinMap.put("lie", -15920);
		pinyinMap.put("lin", -15915);
		pinyinMap.put("ling", -15903);
		pinyinMap.put("liu", -15889);
		pinyinMap.put("long", -15878);
		pinyinMap.put("lou", -15707);
		pinyinMap.put("lu", -15701);
		pinyinMap.put("lv", -15681);
		pinyinMap.put("luan", -15667);
		pinyinMap.put("lue", -15661);
		pinyinMap.put("lun", -15659);
		pinyinMap.put("luo", -15652);
		pinyinMap.put("ma", -15640);
		pinyinMap.put("mai", -15631);
		pinyinMap.put("man", -15625);
		pinyinMap.put("mang", -15454);
		pinyinMap.put("mao", -15448);
		pinyinMap.put("me", -15436);
		pinyinMap.put("mei", -15435);
		pinyinMap.put("men", -15419);
		pinyinMap.put("meng", -15416);
		pinyinMap.put("mi", -15408);
		pinyinMap.put("mian", -15394);
		pinyinMap.put("miao", -15385);
		pinyinMap.put("mie", -15377);
		pinyinMap.put("min", -15375);
		pinyinMap.put("ming", -15369);
		pinyinMap.put("miu", -15363);
		pinyinMap.put("mo", -15362);
		pinyinMap.put("mou", -15183);
		pinyinMap.put("mu", -15180);
		pinyinMap.put("na", -15165);
		pinyinMap.put("nai", -15158);
		pinyinMap.put("nan", -15153);
		pinyinMap.put("nang", -15150);
		pinyinMap.put("nao", -15149);
		pinyinMap.put("ne", -15144);
		pinyinMap.put("nei", -15143);
		pinyinMap.put("nen", -15141);
		pinyinMap.put("neng", -15140);
		pinyinMap.put("ni", -15139);
		pinyinMap.put("nian", -15128);
		pinyinMap.put("niang", -15121);
		pinyinMap.put("niao", -15119);
		pinyinMap.put("nie", -15117);
		pinyinMap.put("nin", -15110);
		pinyinMap.put("ning", -15109);
		pinyinMap.put("niu", -14941);
		pinyinMap.put("nong", -14937);
		pinyinMap.put("nu", -14933);
		pinyinMap.put("nv", -14930);
		pinyinMap.put("nuan", -14929);
		pinyinMap.put("nue", -14928);
		pinyinMap.put("nuo", -14926);
		pinyinMap.put("’o", -14922);
		pinyinMap.put("’ou", -14921);
		pinyinMap.put("pa", -14914);
		pinyinMap.put("pai", -14908);
		pinyinMap.put("pan", -14902);
		pinyinMap.put("pang", -14894);
		pinyinMap.put("pao", -14889);
		pinyinMap.put("pei", -14882);
		pinyinMap.put("pen", -14873);
		pinyinMap.put("peng", -14871);
		pinyinMap.put("pi", -14857);
		pinyinMap.put("pian", -14678);
		pinyinMap.put("piao", -14674);
		pinyinMap.put("pie", -14670);
		pinyinMap.put("pin", -14668);
		pinyinMap.put("ping", -14663);
		pinyinMap.put("po", -14654);
		pinyinMap.put("pu", -14645);
		pinyinMap.put("qi", -14630);
		pinyinMap.put("qia", -14594);
		pinyinMap.put("qian", -14429);
		pinyinMap.put("qiang", -14407);
		pinyinMap.put("qiao", -14399);
		pinyinMap.put("qie", -14384);
		pinyinMap.put("qin", -14379);
		pinyinMap.put("qing", -14368);
		pinyinMap.put("qiong", -14355);
		pinyinMap.put("qiu", -14353);
		pinyinMap.put("qu", -14345);
		pinyinMap.put("quan", -14170);
		pinyinMap.put("que", -14159);
		pinyinMap.put("qun", -14151);
		pinyinMap.put("ran", -14149);
		pinyinMap.put("rang", -14145);
		pinyinMap.put("rao", -14140);
		pinyinMap.put("re", -14137);
		pinyinMap.put("ren", -14135);
		pinyinMap.put("reng", -14125);
		pinyinMap.put("ri", -14123);
		pinyinMap.put("rong", -14122);
		pinyinMap.put("rou", -14112);
		pinyinMap.put("ru", -14109);
		pinyinMap.put("ruan", -14099);
		pinyinMap.put("rui", -14097);
		pinyinMap.put("run", -14094);
		pinyinMap.put("ruo", -14092);
		pinyinMap.put("sa", -14090);
		pinyinMap.put("sai", -14087);
		pinyinMap.put("san", -14083);
		pinyinMap.put("sang", -13917);
		pinyinMap.put("sao", -13914);
		pinyinMap.put("se", -13910);
		pinyinMap.put("sen", -13907);
		pinyinMap.put("seng", -13906);
		pinyinMap.put("sha", -13905);
		pinyinMap.put("shai", -13896);
		pinyinMap.put("shan", -13894);
		pinyinMap.put("shang", -13878);
		pinyinMap.put("shao", -13870);
		pinyinMap.put("she", -13859);
		pinyinMap.put("shen", -13847);
		pinyinMap.put("sheng", -13831);
		pinyinMap.put("shi", -13658);
		pinyinMap.put("shou", -13611);
		pinyinMap.put("shu", -13601);
		pinyinMap.put("shua", -13406);
		pinyinMap.put("shuai", -13404);
		pinyinMap.put("shuan", -13400);
		pinyinMap.put("shuang", -13398);
		pinyinMap.put("shui", -13395);
		pinyinMap.put("shun", -13391);
		pinyinMap.put("shuo", -13387);
		pinyinMap.put("si", -13383);
		pinyinMap.put("song", -13367);
		pinyinMap.put("sou", -13359);
		pinyinMap.put("su", -13356);
		pinyinMap.put("suan", -13343);
		pinyinMap.put("sui", -13340);
		pinyinMap.put("sun", -13329);
		pinyinMap.put("suo", -13326);
		pinyinMap.put("ta", -13318);
		pinyinMap.put("tai", -13147);
		pinyinMap.put("tan", -13138);
		pinyinMap.put("tang", -13120);
		pinyinMap.put("tao", -13107);
		pinyinMap.put("te", -13096);
		pinyinMap.put("teng", -13095);
		pinyinMap.put("ti", -13091);
		pinyinMap.put("tian", -13076);
		pinyinMap.put("tiao", -13068);
		pinyinMap.put("tie", -13063);
		pinyinMap.put("ting", -13060);
		pinyinMap.put("tong", -12888);
		pinyinMap.put("tou", -12875);
		pinyinMap.put("tu", -12871);
		pinyinMap.put("tuan", -12860);
		pinyinMap.put("tui", -12858);
		pinyinMap.put("tun", -12852);
		pinyinMap.put("tuo", -12849);
		pinyinMap.put("wa", -12838);
		pinyinMap.put("wai", -12831);
		pinyinMap.put("wan", -12829);
		pinyinMap.put("wang", -12812);
		pinyinMap.put("wei", -12802);
		pinyinMap.put("wen", -12607);
		pinyinMap.put("weng", -12597);
		pinyinMap.put("wo", -12594);
		pinyinMap.put("wu", -12585);
		pinyinMap.put("xi", -12556);
		pinyinMap.put("xia", -12359);
		pinyinMap.put("xian", -12346);
		pinyinMap.put("xiang", -12320);
		pinyinMap.put("xiao", -12300);
		pinyinMap.put("xie", -12120);
		pinyinMap.put("xin", -12099);
		pinyinMap.put("xing", -12089);
		pinyinMap.put("xiong", -12074);
		pinyinMap.put("xiu", -12067);
		pinyinMap.put("xu", -12058);
		pinyinMap.put("xuan", -12039);
		pinyinMap.put("xue", -11867);
		pinyinMap.put("xun", -11861);
		pinyinMap.put("ya", -11847);
		pinyinMap.put("yan", -11831);
		pinyinMap.put("yang", -11798);
		pinyinMap.put("yao", -11781);
		pinyinMap.put("ye", -11604);
		pinyinMap.put("yi", -11589);
		pinyinMap.put("yin", -11536);
		pinyinMap.put("ying", -11358);
		pinyinMap.put("yo", -11340);
		pinyinMap.put("yong", -11339);
		pinyinMap.put("you", -11324);
		pinyinMap.put("yu", -11303);
		pinyinMap.put("yuan", -11097);
		pinyinMap.put("yue", -11077);
		pinyinMap.put("yun", -11067);
		pinyinMap.put("za", -11055);
		pinyinMap.put("zai", -11052);
		pinyinMap.put("zan", -11045);
		pinyinMap.put("zang", -11041);
		pinyinMap.put("zao", -11038);
		pinyinMap.put("ze", -11024);
		pinyinMap.put("zei", -11020);
		pinyinMap.put("zen", -11019);
		pinyinMap.put("zeng", -11018);
		pinyinMap.put("zha", -11014);
		pinyinMap.put("zhai", -10838);
		pinyinMap.put("zhan", -10832);
		pinyinMap.put("zhang", -10815);
		pinyinMap.put("zhao", -10800);
		pinyinMap.put("zhe", -10790);
		pinyinMap.put("zhen", -10780);
		pinyinMap.put("zheng", -10764);
		pinyinMap.put("zhi", -10587);
		pinyinMap.put("zhong", -10544);
		pinyinMap.put("zhou", -10533);
		pinyinMap.put("zhu", -10519);
		pinyinMap.put("zhua", -10331);
		pinyinMap.put("zhuai", -10329);
		pinyinMap.put("zhuan", -10328);
		pinyinMap.put("zhuang", -10322);
		pinyinMap.put("zhui", -10315);
		pinyinMap.put("zhun", -10309);
		pinyinMap.put("zhuo", -10307);
		pinyinMap.put("zi", -10296);
		pinyinMap.put("zong", -10281);
		pinyinMap.put("zou", -10274);
		pinyinMap.put("zu", -10270);
		pinyinMap.put("zuan", -10262);
		pinyinMap.put("zui", -10260);
		pinyinMap.put("zun", -10256);
		pinyinMap.put("zuo", -10254);
	}

	/**
	 * 添加生僻字
	 * 
	 * @param cnWord
	 *            生僻字
	 * @param pinyin
	 *            生僻字的拼音, 如果拼音以 a, o ,e 开头， 请将开头分别改为 ’a, ’o, ’e， 如：安(’an)
	 */
	public static void putUncommonWord(char cnWord, String pinyin) {
		uncommonWordsMap.put(cnWord, pinyin);
	}

	/**
	 * 初始化生僻字
	 */
	private static void initUncommonWords() {
		putUncommonWord('奡', "’ao");
		putUncommonWord('灞', "ba");
		putUncommonWord('犇', "ben");
		putUncommonWord('猋', "biao");
		putUncommonWord('骉', "biao");
		putUncommonWord('杈', "cha");
		putUncommonWord('棽', "chen");
		putUncommonWord('琤', "cheng");
		putUncommonWord('魑', "chi");
		putUncommonWord('蟲', "chong");
		putUncommonWord('翀', "chong");
		putUncommonWord('麤', "cu");
		putUncommonWord('毳', "cui");
		putUncommonWord('昉', "fang");
		putUncommonWord('沣', "feng");
		putUncommonWord('玽', "gou");
		putUncommonWord('焓', "han");
		putUncommonWord('琀', "han");
		putUncommonWord('晗', "han");
		putUncommonWord('浛', "han");
		putUncommonWord('翮', "he");
		putUncommonWord('翯', "he");
		putUncommonWord('嬛', "huan");
		putUncommonWord('翙', "hui");
		putUncommonWord('劼', "jie");
		putUncommonWord('璟', "jing");
		putUncommonWord('誩', "jing");
		putUncommonWord('競', "jing");
		putUncommonWord('焜', "kun");
		putUncommonWord('琨', "kun");
		putUncommonWord('鹍', "kun");
		putUncommonWord('骊', "li");
		putUncommonWord('鎏', "liu");
		putUncommonWord('嫚', "man");
		putUncommonWord('槑', "mei");
		putUncommonWord('淼', "miao");
		putUncommonWord('婻', "nan");
		putUncommonWord('暔', "nan");
		putUncommonWord('甯', "ning");
		putUncommonWord('寗', "ning");
		putUncommonWord('掱', "pa");
		putUncommonWord('玭', "pi");
		putUncommonWord('汧', "qian");
		putUncommonWord('骎', "qin");
		putUncommonWord('甠', "qing");
		putUncommonWord('暒', "qing");
		putUncommonWord('凊', "qing");
		putUncommonWord('郬', "qing");
		putUncommonWord('靘', "qing");
		putUncommonWord('悫', "que");
		putUncommonWord('慤', "que");
		putUncommonWord('瑢', "rong");
		putUncommonWord('珅', "shen");
		putUncommonWord('屾', "shen");
		putUncommonWord('燊', "shen");
		putUncommonWord('焺', "sheng");
		putUncommonWord('珄', "sheng");
		putUncommonWord('晟', "sheng");
		putUncommonWord('昇', "sheng");
		putUncommonWord('眚', "sheng");
		putUncommonWord('湦', "sheng");
		putUncommonWord('陹', "sheng");
		putUncommonWord('竔', "sheng");
		putUncommonWord('琞', "sheng");
		putUncommonWord('湜', "shi");
		putUncommonWord('甦', "su");
		putUncommonWord('弢', "tao");
		putUncommonWord('瑱', "tian");
		putUncommonWord('仝', "tong");
		putUncommonWord('烓', "wei");
		putUncommonWord('炜', "wei");
		putUncommonWord('玮', "wei");
		putUncommonWord('沕', "wu");
		putUncommonWord('邬', "wu");
		putUncommonWord('晞', "xi");
		putUncommonWord('顕', "xian");
		putUncommonWord('婋', "xiao");
		putUncommonWord('虓', "xiao");
		putUncommonWord('筱', "xiao");
		putUncommonWord('勰', "xie");
		putUncommonWord('忻', "xin");
		putUncommonWord('庥', "xiu");
		putUncommonWord('媭', "xu");
		putUncommonWord('珝', "xu");
		putUncommonWord('昫', "xu");
		putUncommonWord('烜', "xuan");
		putUncommonWord('煊', "xuan");
		putUncommonWord('翾', "xuan");
		putUncommonWord('昍', "xuan");
		putUncommonWord('暄', "xuan");
		putUncommonWord('娅', "ya");
		putUncommonWord('琰', "yan");
		putUncommonWord('妍', "yan");
		putUncommonWord('焱', "yan");
		putUncommonWord('玚', "yang");
		putUncommonWord('旸', "yang");
		putUncommonWord('飏', "yang");
		putUncommonWord('垚', "yao");
		putUncommonWord('峣', "yao");
		putUncommonWord('怡', "yi");
		putUncommonWord('燚', "yi");
		putUncommonWord('晹', "yi");
		putUncommonWord('祎', "yi");
		putUncommonWord('瑛', "ying");
		putUncommonWord('煐', "ying");
		putUncommonWord('媖', "ying");
		putUncommonWord('暎', "ying");
		putUncommonWord('滢', "ying");
		putUncommonWord('锳', "ying");
		putUncommonWord('莜', "you");
		putUncommonWord('昱', "yu");
		putUncommonWord('沄', "yun");
		putUncommonWord('晢', "zhe");
		putUncommonWord('喆', "zhe");
		putUncommonWord('臸', "zhi");
	}

	/**
	 * 获得单个汉字的Ascii.
	 * 
	 * @param cn
	 *            汉字字符
	 * @return 汉字对应的 ascii, 错误时返回 0
	 */
	public static int getCnAscii(char cn) {
		String cnStr = (String.valueOf(cn));
		byte[] bytes = null;
		try {
			bytes = cnStr.getBytes(CHARSET_GBK);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
		// 错误
		if (bytes == null || bytes.length == 0 || bytes.length > 2) {
			return 0;
		}
		// 英文字符
		if (bytes.length == 1) {
			return bytes[0];
		}
		// 中文字符
		if (bytes.length == 2) {
			int hightByte = 256 + bytes[0];
			int lowByte = 256 + bytes[1];
			return (256 * hightByte + lowByte) - 256 * 256;
		}
		return 0;
	}

	/**
	 * 根据ASCII码到SpellMap中查找对应的拼音
	 * 
	 * @param ascii
	 *            ASCII
	 * @return ascii对应的拼音, 如果ascii对应的字符为单字符，则返回对应的单字符, 如果不是单字符且在 pinyinMap
	 *         中没找到对应的拼音，则返回空字符串(""),
	 */
	public static String getPinyinByAscii(int ascii) {
		if (ascii > 0 && ascii < 160) { // 单字符 return String.valueOf((char)
			                            // ascii);
		}

		if (ascii < -20319 || ascii > -10247) { // 不知道的字符
			return "";
		}

		Map.Entry<String, Integer> entry;
		String pinyin = null; // key
		Integer asciiRang; // value
		String pinyinPrevious = null; // 用来保存上次轮循环的key
		int asciiRangPrevious = -20319; // 用来保存上一次循环的value，此处 -20319 对应
		                                // pinyinMap.put("’a", -20319);
		for (Iterator<Map.Entry<String, Integer>> it = pinyinMap.entrySet()
		        .iterator(); it.hasNext();) {
			entry = it.next();
			pinyin = entry.getKey(); // 拼音
			asciiRang = entry.getValue(); // 拼音的ASCII
			if (asciiRang != null) {
				if (ascii >= asciiRangPrevious && ascii < asciiRang) { // 区间找到,
					                                                   // 返回对应的拼音

					return (pinyinPrevious == null) ? pinyin : pinyinPrevious;
				}

				pinyinPrevious = pinyin;
				asciiRangPrevious = asciiRang;
			}
		}
		return "";
	}

	/**
	 * 获取字符串的全拼,是汉字则转化为对应的拼音,其它字符不进行转换
	 * 
	 * @param cnStr
	 *            要获取全拼的字符串
	 * @return String cnStr的全拼, 如果 cnStr 为null, 返回""
	 * @see #getPinyin(String, boolean)
	 */
	public static String getQuanPin(String cnStr) {
		return getPinyin(cnStr, false);
	}

	/**
	 * 获取字符串的首拼,是汉字则转化为对应的拼音首字母,其它字符不进行转换
	 * 
	 * @param cnStr
	 *            要获取首拼的字符串
	 * @return String cnStr的首拼, 如果 cnStr 为null, 返回""
	 * @see #getPinyin(String, boolean)
	 */
	public static String getShouPin(String cnStr) {
		return getPinyin(cnStr, true);
	}

	/**
	 * 获取字符串的全拼或首拼,是汉字则转化为对应的拼音或拼音首字母,其它字符不进行转换
	 * 
	 * @param cnStr
	 *            要获取全拼或首拼的字符串
	 * @param onlyFirstSpell
	 *            是否只获取首拼，为true时，只获取首拼，为false时，获取全拼
	 * @return String cnStr的全拼或首拼, 如果 cnStr 为null 时, 返回""
	 */
	public static String getPinyin(String cnStr, boolean onlyFirstSpell) {
		if (cnStr == null) {
			return "";
		}

		char[] chars = cnStr.trim().toCharArray();
		StringBuffer sb = new StringBuffer();
		for (int i = 0, len = chars.length; i < len; i++) {
			int ascii = getCnAscii(chars[i]);
			if (ascii == 0) { // 如果获取汉字的ASCII出错，则不进行转换
				sb.append(chars[i]);
			} else {
				String pinyin = getPinyinByAscii(ascii); // 根据ASCII取拼音
				if (pinyin == null || pinyin.length() == 0) { // 如果根据ASCII取拼音没取到，则到生僻字Map中取
					pinyin = uncommonWordsMap.get(chars[i]);
				}

				if (pinyin == null || pinyin.length() == 0) { // 如果没有取到对应的拼音，则不做转换，追加原字符
					pinyin = uncommonWordsMap.get(chars[i]);
				} else {
					if (onlyFirstSpell) {
						sb.append(pinyin.startsWith("’") ? pinyin.substring(1,
						        2) : pinyin.substring(0, 1));
					} else {
						sb.append(pinyin);
					}
				}
			}
		} // end of for
		return sb.toString();
	}
	public static void main(String[] args) {
		String str = "四海兴唐";
		System.out.println(getPinyin(str,false));
		
   /*   String[] s = {"获取汉字全拼和首拼测试","This is a test","a,b; c[d]","标，点。","圆角数字１２３，特殊符号·￥%——……", "繁体字：西安會議"};
//		String[] s = {"西安", "棽 燊 顕 峣 山 ", "一恩"};
        for (int i = 0; i < s.length; i++) {
                long l1 = System.currentTimeMillis();
                System.out.println(s[i]+" 的全拼:" + getPinyin(s[i], false));
                System.out.println(s[i]+" 的首拼:" + getPinyin(s[i], true));
                System.out.println("获取全拼和首拼共用了"+(System.currentTimeMillis()-l1)+"毫秒\n");
        }*/
    }
}
