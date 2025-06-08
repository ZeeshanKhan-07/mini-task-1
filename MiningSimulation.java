import java.security.MessageDigest;
import java.util.Date;

class MinedBlock {
    public int index;
    public long timestamp;
    public String data;
    public int nonce;
    public String hash;

    public MinedBlock(int index, String data) {
        this.index = index;
        this.timestamp = new Date().getTime();
        this.data = data;
        this.nonce = 0;
    }

    public void mineBlock(int difficulty) {
        String prefix = "0".repeat(difficulty);
        long start = System.currentTimeMillis();

        while (true) {
            String input = index + Long.toString(timestamp) + data + nonce;
            hash = applySHA256(input);
            if (hash.startsWith(prefix)) break;
            nonce++;
        }

        long end = System.currentTimeMillis();
        System.out.println("✅ Block mined: " + hash);
        System.out.println("Nonce: " + nonce);
        System.out.println("Time: " + (end - start) + "ms");
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
}

public class MiningSimulation {
    public static void main(String[] args) {
        MinedBlock block = new MinedBlock(1, "Transaction data");
        block.mineBlock(4);
    }
}
