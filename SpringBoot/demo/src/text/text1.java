package text;

/**
 * @author : Pj-Xk
 * @date : 2023-01-14 18:21
 **/
public class text1 {
    public boolean canConstruct(String ransomNote,String magazine) {
        if (ransomNote.length() > magazine.length()) {
            return false;
        }
        int[] cnt = new int[26];
        for (char c : magazine.toCharArray()) {
            cnt[c - 'a']++;
        }
        for (char c : ransomNote.toCharArray()) {
            cnt[c - 'a']--;
            if(cnt[c - 'a'] < 0) {
                return false;
            }
        }
        return true;
    }
    public static void main(String[] args){
        text1 t = new text1();
        Boolean b = t.canConstruct("aaab","aaaabbbc");
        System.out.print(b);
    }
}
