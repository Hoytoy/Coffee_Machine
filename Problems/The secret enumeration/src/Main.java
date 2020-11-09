public class Main {

    public static void main(String[] args) {
        /*Secret[] secrets = Secret.values();
        String[] secretsString = new String[secrets.length];
        int i = 0;
        for (Secret sec: secrets) {
            secretsString[i] = sec.name();
            i++;
        }
        int count = 0;
        for (String s: secretsString) {
            if (s.startsWith("STAR")) {
                count++;
            }
        }*/
        int count = 0;
        for (Secret s: Secret.values()) {
            count += s.name().startsWith("STAR") ? 1 : 0;
        }
        System.out.println(count);
    }
}

/* At least two constants start with STAR
enum Secret {
    STAR, CRASH, START, // ...
}
*/