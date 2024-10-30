public class Converter {
    public static int Precedence(char operator) {
        if(operator == '(' || operator == ')') return 10;
        if(operator == '!' || operator == '~') return 9;
        if(operator == '^') return 8;
        if(operator == '*' || operator == '/' || operator == '%') return 7;
        if(operator == '+' || operator == '-') return 6;
        if(operator == '<' || operator == '>') return 5;
        if(operator == '&') return 4;
        if(operator == '|') return 3;
        if(operator == '=') return 1;
        else return 0;
    }

    public static int isValid(char operator) {
        if(operator == '!' || operator == '~' || operator == '^' || operator == '*' || operator == '/' || operator == '%' || operator == '+' || operator == '-'
                || operator == '<' || operator == '>' || operator == '&' || operator == '|' || operator == '=') {
            return 1;
        } else return 0;
    }

    public static String ONPtoINF(String phrase) {
        if(phrase == null) return "error";

        StringBuilder builder = new StringBuilder();
        int operandCount = 0;
        int operatorCount = 0;

        for (int i = 0; i < phrase.length(); i++) {
            boolean validChar = (phrase.charAt(i) >= 'a' && phrase.charAt(i) <= 'z');
            if (isValid(phrase.charAt(i)) == 1 || validChar) { 
                if (isValid(phrase.charAt(i)) == 1 && phrase.charAt(i) != '!' && phrase.charAt(i) != '~' && phrase.charAt(i) != '^') {
                    operatorCount++;
                }
                if (validChar) {
                    operandCount++;
                }
                builder.append(phrase.charAt(i));
            }
        }

        if (operatorCount >= operandCount) return "error"; 

        String result = builder.toString(); 
        StringStack stackOfStrings = new StringStack();
        IntStack stackOfInts = new IntStack();
        CharStack stackOfChars = new CharStack();

        for (int i = 0; i < result.length(); i++) {
            char currentChar = result.charAt(i);

            if (currentChar >= 'a' && currentChar <= 'z') { 
                stackOfStrings.push(String.valueOf(currentChar));
                stackOfInts.push(11); 
                stackOfChars.push('r'); 
            }
            else if (currentChar == '~' || currentChar == '!') {
                if (stackOfStrings.isEmpty()) return "error";

                String topString = stackOfStrings.pop();
                int topPrecedence = stackOfInts.pop();
                stackOfChars.pop();

                if (Precedence(currentChar) <= topPrecedence) {
                    stackOfStrings.push(currentChar + " " + topString);
                } else {
                    stackOfStrings.push(currentChar + " ( " + topString + " )");
                }
                stackOfInts.push(9); 
                stackOfChars.push('r'); 
            } else {
                if (stackOfStrings.size() < 2) return "error"; 

                String rightOperand = stackOfStrings.pop();
                int rightPrecedence = stackOfInts.pop();
                stackOfChars.pop();

                String leftOperand = stackOfStrings.pop();
                int leftPrecedence = stackOfInts.pop();
                stackOfChars.pop();

                int currentPrecedence = Precedence(result.charAt(i));
                char currentAssociativity = (result.charAt(i) == '~' || result.charAt(i) == '!' ||
                        result.charAt(i) == '=' || result.charAt(i) == '^') ? 'r' : 'l';

                if (currentPrecedence > rightPrecedence ||
                        (currentPrecedence == rightPrecedence && currentAssociativity == 'l')) {
                    rightOperand = "( " + rightOperand + " )";
                }

                if (currentPrecedence > leftPrecedence) {
                    leftOperand = "( " + leftOperand + " )";
                }

                String clean = leftOperand + " " + result.charAt(i) + " " + rightOperand;
                stackOfStrings.push(clean);
                stackOfInts.push(currentPrecedence);
                stackOfChars.push(currentAssociativity);

            }
        }
        if (stackOfStrings.size() != 1) return "error";
        phrase = stackOfStrings.top();
        return phrase;
    }

    public static String INFtoONP(String phrase) {
        if (phrase == null) return "error";

        StringBuilder builder = new StringBuilder();

        int state = 0; 
        int operatorCounter = 0; 
        int operandCounter = 0; 
        int parenthesesCounter = 0; 

        for (int i = 0; i < phrase.length(); i++) {
            char currentChar = phrase.charAt(i);

            if (!(isValid(currentChar) == 1 || (currentChar >= 'a' && currentChar <= 'z') || currentChar == '(' || currentChar == ')')) {
                continue;
            }
            builder.append(currentChar);

            if (currentChar == '~' || currentChar == '!') {
                if (state == 1) return "error";
                else state = 2;
            }
            if (isValid(currentChar) == 1 && currentChar != '~' && currentChar != '!') {
                if (state == 0 || state == 2) return "error";
                else state = 0;

                if (currentChar != '^') {
                    operatorCounter++;
                }
            }
            if (currentChar >= 'a' && currentChar <= 'z') {
                if (state == 1) return "error";
                else state = 1; 
                operandCounter++; 
            }
            if (currentChar == '(') {
                parenthesesCounter++;
                if (state == 1) return "error"; 
                else state = 0;
            }
            else if (currentChar == ')') {
                if (state == 0 || state == 2) return "error"; 
                state = 1;
                parenthesesCounter--; 
            }
        }

        if ((state != 1) || (parenthesesCounter != 0) || (operatorCounter >= operandCounter)) return "error";

        StringBuilder answer = new StringBuilder();
        CharStack mainStack = new CharStack();

        for (int i = 0; i < phrase.length(); i++) {
            if (phrase.charAt(i) >= 'a' && phrase.charAt(i) <= 'z') {
                String aux = Character.toString(phrase.charAt(i));
                answer.append(aux);
                answer.append(" ");
            }

            else if (phrase.charAt(i) == '(') {
                mainStack.push(phrase.charAt(i));
            }

            else if (isValid(phrase.charAt(i)) == 1 && phrase.charAt(i) != '!' && phrase.charAt(i) != '~' && phrase.charAt(i) != '^' && phrase.charAt(i) != '=') {
                while (!mainStack.isEmpty() && Precedence(phrase.charAt(i)) <= Precedence(mainStack.top()) && Precedence(mainStack.top()) != 10) {
                    String aux = String.valueOf(mainStack.pop());
                    answer.append(aux);
                    answer.append(' ');
                }
                mainStack.push(phrase.charAt(i));
            }

            else if (phrase.charAt(i) == '!' || phrase.charAt(i) == '~' || phrase.charAt(i) == '^' || phrase.charAt(i) == '=') {
                while (!mainStack.isEmpty() && Precedence(phrase.charAt(i)) < Precedence(mainStack.top()) && Precedence(mainStack.top()) != 10) {
                    String aux = String.valueOf(mainStack.pop());
                    answer.append(aux);
                    answer.append(' ');
                }
                mainStack.push(phrase.charAt(i));
            }

            else if (phrase.charAt(i) == ')') {
                while (mainStack.top() != '(' && !mainStack.isEmpty()) {
                    String aux = String.valueOf(mainStack.pop());
                    answer.append(aux);
                    answer.append(' ');
                }
                mainStack.pop();
            }
        }

        while (!mainStack.isEmpty()) {
            String aux = String.valueOf(mainStack.pop());
            answer.append(aux);
            answer.append(' ');
        }

        StringBuilder helper = new StringBuilder();
        for (int i = 0; i < answer.length() - 1 ; i++) {
            helper.append(answer.charAt(i));
        }
        phrase = helper.toString();
        return phrase;
    }
}
