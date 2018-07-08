package com.pokerface.model;

import java.util.Date;
import java.util.Vector;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.StringUtils;

@Entity
@Table(name = "ROOM_INNINGS")
public class RoomInning {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
	
    @Column(name = "ROOM_ID")
    private Long roomId;

    @Column(name = "INNING")
    private Long inning;

    @Column(name = "USER_ID")
    private Long userId;

    @Column(name = "USER_NAME")
    private String userName;
    
    @Column(name = "SEAT_ID")
    private Long seatId;
    
    @Column(name = "USER_BET")
    private Long bet;

    @Column(name = "USER_GRAB")
    private Long grab;
    
    @Column(name = "CARDS")
    private String cards;
    
    @Column(name = "SHOW_HAND_CARDS")
    private String showHandCards;
    
    @Column(name = "MIDDLE_CARD_TYPE")
    private String middleCardType;
    
    @Column(name = "CARD_TYPE")
    private String cardType;
    
    @Column(name = "MIDDLE_SCORE")
    private Long middleScore;
    
    @Column(name = "MIDDLE_TOTAL")
    private Long middleTotal;
    
    @Column(name = "END_SCORE")
    private Long endScore;
    
    @Column(name = "END_TOTAL")
    private Long endTotal;
    
    @Column(name = "IS_DEALER")
    private boolean dealer;
    
    @Column(name = "STATUS")
    private Long status;
    
    @Column(name = "CREATE_TIME")
    private Date createTime;
    
    @Column(name = "END_TIME")
    private Date endTime;
    
	public RoomInning() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getRoomId() {
		return roomId;
	}

	public void setRoomId(Long roomId) {
		this.roomId = roomId;
	}
	
	public Long getInning() {
		return inning;
	}

	public void setInning(Long inning) {
		this.inning = inning;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Long getSeatId() {
		return seatId;
	}

	public void setSeatId(Long seatId) {
		this.seatId = seatId;
	}

	public Long getBet() {
		return bet;
	}

	public void setBet(Long bet) {
		this.bet = bet;
	}

	public Long getGrab() {
		return grab;
	}

	public void setGrab(Long grab) {
		this.grab = grab;
	}

	public String getCards() {
		return cards;
	}

	public void setCards(String cards) {
		this.cards = cards;
	}

	public String getShowHandCards() {
		return showHandCards;
	}

	public void setShowHandCards(String showHandCards) {
		this.showHandCards = showHandCards;
	}

	public String getMiddleCardType() {
		return middleCardType;
	}

	public void setMiddleCardType(String middleCardType) {
		this.middleCardType = middleCardType;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public Long getMiddleScore() {
		return middleScore;
	}

	public void setMiddleScore(Long middleScore) {
		this.middleScore = middleScore;
	}

	public Long getMiddleTotal() {
		return middleTotal;
	}

	public void setMiddleTotal(Long middleTotal) {
		this.middleTotal = middleTotal;
	}

	public Long getEndScore() {
		return endScore;
	}

	public void setEndScore(Long endScore) {
		this.endScore = endScore;
	}

	public Long getEndTotal() {
		return endTotal;
	}

	public void setEndTotal(Long endTotal) {
		this.endTotal = endTotal;
	}

	public boolean isDealer() {
		return dealer;
	}

	public void setDealer(boolean dealer) {
		this.dealer = dealer;
	}

	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
	public String getThreeCardDesc(){
		if(StringUtils.isEmpty(cards)){
			return "";
		}
		String[] cardStrs = cards.split(",");
		String threeCardDesc = "";
		Vector<Integer> cardInts = new Vector<Integer>();
		for(int i=0;i<3;i++){
			cardInts.add(Integer.parseInt(cardStrs[i]));
		}
		
		for(Integer card : cardInts){
			threeCardDesc += getCardFace(card)+",";
		}
		return threeCardDesc.substring(0, threeCardDesc.length()-1);
	}
	
	public String getFiveCardDesc(){
		String fiveCards = cards;
		if(!StringUtils.isEmpty(showHandCards)){
			fiveCards = showHandCards;
		}
		String[] cardStrs = fiveCards.split(",");
		String fiveCardDesc = "";
		Vector<Integer> cardInts = new Vector<Integer>();
		for(String cardStr : cardStrs){
			cardInts.add(Integer.parseInt(cardStr));
		}
		
		for(Integer card : cardInts){
			fiveCardDesc += getCardFace(card)+",";
		}
		return fiveCardDesc.substring(0, fiveCardDesc.length()-1);
	}
	
	private String getCardFace(int card){
		int round = (card & 0xF0) >> 4;
		int value = card & 0x0F;
		String colour = "";
		switch (round) {
		case 0:
			colour = "方块";
			break;
		case 1:
			colour = "梅花";
			break;
		case 2:
			colour = "红桃";
			break;
		case 3:
			colour = "黑桃";
			break;
		default:
			break;
		}
		String point = "";
		switch (value) {
		case 1:
			point = "A";
			break;
		case 11:
			point = "J";
			break;
		case 12:
			point = "Q";
			break;
		case 13:
			point = "K";
			break;
		default:
			point = ""+value;
			break;
		}
		return colour + point;
	}
	
	
	public String getThreeCardTypeDesc(){
		if(StringUtils.isEmpty(middleCardType)){
			return "";
		}
		int threeCardType = Integer.parseInt(middleCardType);
		String threeCardTypeDesc = "";
		switch (threeCardType) {
		case GameConstants.ThreeDukes:
			threeCardTypeDesc = "三爵士";
			break;
		case GameConstants.ThreeSame:
			threeCardTypeDesc = "三条";
			break;
		case GameConstants.ThreeAce:
			threeCardTypeDesc = "三Ace";
			break;
		default:
			threeCardTypeDesc = threeCardType + "点";
			break;
		}
		return threeCardTypeDesc;
	}
	
	public String getFiveCardTypeDesc(){
		if(StringUtils.isEmpty(cardType)){
			return "";
		}
		int fiveCardType = Integer.parseInt(cardType);
		String fiveCardTypeDesc = "";
		switch (fiveCardType) {
		case GameConstants.NiuFiveDukes:
			fiveCardTypeDesc = "牛五爵";
			break;
		case GameConstants.NiuBullOfSpades:
			fiveCardTypeDesc = "牛冬菇";
			break;
		case GameConstants.NiuPairOfAces:
			fiveCardTypeDesc = "牛双A";
			break;
		case GameConstants.NiuNiu:
			fiveCardTypeDesc = "牛牛";
			break;
		case GameConstants.NoNiu:
			fiveCardTypeDesc = "没牛";
			break;
		default:
			if(GameConstants.NiuOne<= fiveCardType && fiveCardType<=GameConstants.NiuNine){
				fiveCardTypeDesc = "牛"+ (fiveCardType - GameConstants.NoNiu);
			} else if(GameConstants.NiuPairOfKing_2<=fiveCardType && fiveCardType<=GameConstants.NiuPairOfKing_13){
				fiveCardTypeDesc = "牛双"+ (fiveCardType - GameConstants.NiuPairOfKing_2 + 2);
			}
			break;
		}
		return fiveCardTypeDesc;
	}
}
