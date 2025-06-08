import java.security.MessageDigest;
import java.util.Date;

class Block {
    public int index;
    public long timestamp;
    public String data;
    public String previousHash;
    public String hash;
    public int nonce;

    public Block(int index, String data, String previousHash) {
        this.index = index;
        this.timestamp = new Date().getTime();
        this.data = data;
        this.previousHash = previousHash;
        this.nonce = 0;
        this.hash = calculateHash();
    }

    public String calculateHash() {
        String input = index + Long.toString(timestamp) + data + previousHash + nonce;
        return applySHA256(input);
    }

    public static String applySHA256(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(input.getBytes("UTF-8"));
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) hexString.append(String.format("%02x", b));
            return hexString.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String toString() {
        return "Block " + index + ":\nHash: " + hash + "\nPrev: " + previousHash + "\nData: " + data + "\n";
    }
}

public class BlockchainSimulation {
    public static void main(String[] args) {
        Block genesis = new Block(0, "Genesis Block", "0");
        Block block1 = new Block(1, "Alice pays Bob 10 BTC", genesis.hash);
        Block block2 = new Block(2, "Bob pays Charlie 5 BTC", block1.hash);

        System.out.println(genesis);
        System.out.println(block1);
        System.out.println(block2);

        System.out.println("⚠️ Tampering with Block 1 data...");
        block1.data = "Alice pays Eve 100 BTC";
        block1.hash = block1.calculateHash();
        System.out.println(block1);

        System.out.println("🚨 Block 2 still points to old hash: " + block2.previousHash);
    }
}
