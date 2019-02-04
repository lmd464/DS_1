// 동전에 대한 정보

public class Coin {
	
	// 인스턴스 변수
	
	// 금액
	private int _value;
	
	
	// 생성자
	public Coin() {	}
	public Coin(int givenValue) {
		this._value = givenValue;
	}
	
	
	// 코인 금액 반환 : getter
	public int value() {
		return this._value;
	}
	
	
	// 코인 금액 설정 : setter
	public void setValue(int newValue) {
		this._value = newValue;
	}
	
	
	// 코인 금액 비교
	@Override
	public boolean equals(Object coin_p) {
		
		// 인자로 받은 값이 Coin형이 아닐 경우
		if(coin_p.getClass() != Coin.class) {
			return false;
		}
		
		// Coin형을 받았을 경우 금액 비교
		else {
			return (this.value() == ((Coin)coin_p).value());
		}
	}
	
}
