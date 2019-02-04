
public class Calculate {
	
	private static final char END_OF_TOKEN = '$';
	
	private ArrayList<Character> _oStack;	// 연산자 스택
	private ArrayList<Double> _vStack;		// 값 스택
	
	private char[] _infix;
	private char[] _postfix;
	
	// 생성자
	public Calculate() {
		
	}
	
	
	// Setter : infix 재설정
	public void setInfix(String newInfix) {
		this._infix = newInfix.toCharArray();
	}
	
	
	// Getter
	public String infix() {
		return String.valueOf(this._infix);
	}
	public String postfix() {						// postfix로의 변환이 먼저 되어야 함
		
		// 괄호의 개수 세기
		int gCount = 0;
		for(int i = 0; i < this._infix.length; i++) {
			if(this._infix[i] == '(' || this._infix[i] == ')') {
				gCount++;
			}
		}
		
		// 끝에 $를 제거하고 반환
		return String.valueOf(this._postfix).substring(0, this._postfix.length - gCount - 1);
	}
	
	
	// infix를 Postfix로 변환
	public boolean infixToPostfix() {
		
		int infixIndex = 0;			// string pointer for infix
		int postfixIndex = 0;		// string pointer for postfix
		char currentToken, poppedToken, topToken;
		
		this._oStack = new ArrayList<Character>(this._infix.length);
		this._postfix = new char[this._infix.length + 1];
		
		
		// infix 전체 순회
		while(infixIndex < this._infix.length) {
			
			// infix에서 검사할 토큰 하나 뽑음
			currentToken = this._infix[infixIndex++];
			
			
			// currentToken이 숫자 : 바로 postfix에 추가
			if(this.isDigit(currentToken)) {
				this._postfix[postfixIndex++] = currentToken;
			}
			

			else {	// currentToken이 연산자
				
				// currentToken이 ')'
				if(currentToken == ')') {		
					if(!this._oStack.isEmpty()) {
						poppedToken = (char)this._oStack.pop();
					}
					else {
						return false;
					}
					
					// '(' 까지 계속 pop하면서 postfix에 붙임
					while(poppedToken != '(') {
						this._postfix[postfixIndex++] = poppedToken;
						if(!this._oStack.isEmpty()) {
							poppedToken = (char)this._oStack.pop();
						}
						else {
							return false;
						}
					}
					this.showOStackAll();
				}
				
				
				// currentToken이 '(' 또는 일반 연산자
				else {	
					int inComingP = this.inComingPrecedence(currentToken);
					
					if(!this._oStack.isEmpty()) {
						topToken = (char)this._oStack.peek();
						
						// 스택 안에 우선 순위가 더 높은 연산자가 있을 시, 전부 pop하면서 postfix에 붙임
						while(this.inStackPrecedence(topToken) >= inComingP) {
							poppedToken = (char)this._oStack.pop();
							this._postfix[postfixIndex++] = poppedToken;
							
							if(!this._oStack.isEmpty()) {
								topToken = (char)this._oStack.peek();
							}
							else {
								break;
							}
						}
					}
					this._oStack.push(currentToken);
					this.showOStackAll();
				}
			}
		}
		
		while(!(this._oStack.isEmpty())) {
			this._postfix[postfixIndex++] = this._oStack.pop();
		}
		this._postfix[postfixIndex] = Calculate.END_OF_TOKEN;
		
		return true;
	}
	
	
	
	// 변환된 Postfix 수식 계산
	public double evalPostfix() {
		
		int stringIndex = 0;
		char token;
		this._vStack = new ArrayList<Double>(this._infix.length);		// postfix 식을 double로 변환하여 여기다 push
		
		token = this._postfix[stringIndex++];
		while(token != Calculate.END_OF_TOKEN) {
			
			// token이 숫자일 때
			if(this.isDigit(token)) {
				double doubleToPush = Double.parseDouble(String.valueOf(token));
				this._vStack.push(doubleToPush);
				this.showVStackAll();
			}
			
			// token이 operator일 때
			else {
				double secondOperand = this._vStack.pop();
				double firstOperand = this._vStack.pop();
				
				switch(token) {
				case '+' :
					this._vStack.push(firstOperand + secondOperand);
					break;
					
				case '-' :
					this._vStack.push(firstOperand - secondOperand);
					break;
					
				case '*' :
					this._vStack.push(firstOperand * secondOperand);
					break;
					
				case '/' :
					this._vStack.push(firstOperand / secondOperand);
					break;
					
				case '%' :
					this._vStack.push(firstOperand % secondOperand);
					break;
					
				case '^' :
					this._vStack.push(Math.pow(firstOperand, secondOperand));
					break;
				}
				this.showVStackAll();
			}
			
			token = this._postfix[stringIndex++];
		}
		
		return this._vStack.pop();
	}
	
	
	
	
	
	
	
	// 비공개 함수
	
	// 문자가 숫자 문자인지 확인
	private boolean isDigit(char aToken) {
		return (aToken >= '0' && aToken <= '9');
	}
	
	// 현재 넣을 연산자인 Token의 우선순위 값 반환
	private int inComingPrecedence(char aToken) {
		if(aToken == '+')
			return 12;
		else if(aToken == '-')
			return 12;
		else if(aToken == '(')
			return 20;
		else if(aToken == ')')
			return 19;
		else if(aToken == '*')
			return 13;
		else if(aToken == '/')
			return 13;
		else if(aToken == '%')
			return 13;
		else if(aToken == '^')
			return 17;
		else if(aToken == '$')
			return 0;
		else
			return -1;
	}
	
	// 스택 안의 연산자 Token의 우선순위 값 반환
	private int inStackPrecedence(char aToken) {
		if(aToken == '+')
			return 12;
		else if(aToken == '-')
			return 12;
		else if(aToken == '(')
			return 0;
		else if(aToken == ')')
			return 19;
		else if(aToken == '*')
			return 13;
		else if(aToken == '/')
			return 13;
		else if(aToken == '%')
			return 13;
		else if(aToken == '^')
			return 16;
		else if(aToken == '$')
			return 0;
		else
			return -1;
	}
	
	// Operation 스택의 상태 확인
	private void showOStackAll() {
		System.out.print("OStack : ");
		for(int index = 0; index < this._oStack.size(); index++) {
			System.out.print(this._oStack.elementAt(index) + " ");
		}
		System.out.println();
	}
	
	// Value 스택의 상태 확인
	private void showVStackAll() {
		System.out.print("VStack : ");
		for(int index = 0; index < this._vStack.size(); index++) {
			System.out.print(this._vStack.elementAt(index) + " ");
		}
		System.out.println();
	}
	
	
	
}
