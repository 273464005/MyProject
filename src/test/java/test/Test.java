package test;

import org.com.lyz.util.encryptionutil.SHAEncryptionUtil;

public class Test {

	public static void main(String[] args) {
        try {
            String mm = SHAEncryptionUtil.SHAEncryption("0");
            System.out.println(mm);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
