import java.util.*;

class Solution {
    public int solution(int coin, int[] cards) {
        int target = cards.length + 1;
        Set<Integer> myCards = new HashSet<>();
        Set<Integer> tempCards = new HashSet<>();
        for(int i = 0; i < cards.length / 3; i++) {
            myCards.add(cards[i]);
        }
        int idx = cards.length / 3;
        int round = 1;
        while(idx + 1 < cards.length) {
            tempCards.add(cards[idx]);
            tempCards.add(cards[idx + 1]);
            
            boolean flag = false;
            for(Integer num : myCards) {
                if(myCards.contains(target - num)) {
                    myCards.remove(num);
                    myCards.remove(target - num);
                    flag = true;
                    break;
                }
            }
            if(!flag) {
                if(coin >= 1) {
                    for(Integer num : tempCards) {
                        if(myCards.contains(target - num)) {
                            myCards.remove(target - num);
                            tempCards.remove(num);
                            coin--;
                            flag = true;
                            break;
                        }
                    }
                }
            }
            
            if(!flag) {
                if(coin >= 2) {
                    for(Integer num : tempCards) {
                        if(tempCards.contains(target - num)) {
                            tempCards.remove(target - num);
                            tempCards.remove(num);
                            coin -= 2;
                            flag = true;
                            break;
                        }
                    }
                }
            }
            
            if(!flag) {
                break;
            }
            
            idx += 2;
            round++;
        }
        return round;
    }
}
