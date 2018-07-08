package com.pokerface.model;



public class GameConstants {
	
	public static final int BullGame = 1;		// 斗牛
	
	public static final int ThreeCardGame = 2;	// 三五张
	
	public enum SUITS {
		DIAMONDS, 	// 方块
		CLUBS, 		// 梅花
		HEARTS, 	// 红桃
		SPADES,		// 黑桃
	};

	public static final int NoNiu = 0x0100;
	
	public static final int NiuOne = 0x0101;
	
	public static final int NiuTwo = 0x0102;
	
	public static final int NiuThree = 0x0103;
	
	public static final int NiuFour = 0x0104;
	
	public static final int NiuFive = 0x0105;
	
	public static final int NiuSix = 0x0106;
	
	public static final int NiuSeven = 0x0107;
	
	public static final int NiuEight = 0x0108;
	
	public static final int NiuNine = 0x0109;
	
	public static final int NiuNiu =  0x0201;				// 牛牛，两倍

	public static final int NiuPairOfKing_2 = 0x0302;		// 牛+对子2
	
	public static final int NiuPairOfKing_3 = 0x0303;		// 牛+对子3
	
	public static final int NiuPairOfKing_4 = 0x0304;		// 牛+对子4
	
	public static final int NiuPairOfKing_5 = 0x0305;		// 牛+对子5
	
	public static final int NiuPairOfKing_6 = 0x0306;		// 牛+对子6
	
	public static final int NiuPairOfKing_7 = 0x0307;		// 牛+对子7
	
	public static final int NiuPairOfKing_8 = 0x0308;		// 牛+对子8
	
	public static final int NiuPairOfKing_9 = 0x0309;		// 牛+对子9
	
	public static final int NiuPairOfKing_10 = 0x030A;		// 牛+对子10
	
	public static final int NiuPairOfKing_11 = 0x030B;		// 牛+对子J
	
	public static final int NiuPairOfKing_12 = 0x030C;		// 牛+对子Q
	
	public static final int NiuPairOfKing_13 = 0x030D;		// 牛+对子K
	
	public static final int NiuPairOfAces = 0x0401;		// 牛双A，三倍
	
	public static final int NiuBullOfSpades = 0x0501;	// 牛冬菇=牛+人头像（J、Q、K）+黑桃A，四倍
	
	public static final int NiuFiveDukes = 0x0801;		// 牛五爵=五张人头像(J、Q、K)，五倍
	
	// 三张结算牌型
	public static final int ThreeDukes = 11;	// 三爵士=三张人头像(J、Q、K)，三倍
	
	public static final int ThreeSame = 12;		// 三条=三张同点数的牌，四倍
	
	public static final int ThreeAce = 13;		// 三ACE=三张A，五倍

}
