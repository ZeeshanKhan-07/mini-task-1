import java.util.*;

public class ConsensusDemo {
    public static void main(String[] args) {
        // Proof of Work
        int minerPower = new Random().nextInt(100);
        System.out.println("[PoW] MinerA has power: " + minerPower + " → Selected");

        // Proof of Stake
        int stakerStake = new Random().nextInt(100);
        System.out.println("[PoS] StakerB has stake: " + stakerStake + " → Selected");

        // Delegated Proof of Stake
        String[] delegates = {"NodeX", "NodeY", "NodeZ"};
        Map<String, Integer> votes = new HashMap<>();
        for (String d : delegates) votes.put(d, 0);

        String[] voters = {"User1", "User2", "User3"};
        for (String v : voters) {
            String vote = delegates[new Random().nextInt(delegates.length)];
            votes.put(vote, votes.get(vote) + 1);
        }

        String selected = Collections.max(votes.entrySet(), Map.Entry.comparingByValue()).getKey();
        System.out.println("[DPoS] Votes: " + votes);
        System.out.println("[DPoS] " + selected + " got most votes → Selected");
    }
}
