package com.znsx.test.license;

import java.io.*;
import java.security.*;
import java.security.spec.*;
import javax.swing.JOptionPane;

import org.apache.commons.net.util.Base64;

/**
 * @author jeffrey walton
 **/
public class Main {

  static String ALGORITHM = "DSA";
  
  public static void main(String[] args) {
    try {

//      CreateDSAKeys();

//      SignDSAMessage();
      
      VerifyDSAMessage();

    } catch (Exception e) {
      System.err.println("Main: Exception " + e.toString());
    }
  }
  
  private static String getPath() {
	 return Main.class.getResource("").getPath();
  }

  private static void VerifyDSAMessage() {

    try {
      
      // Load the public
      PublicKey publicKey = LoadPublicKey(getPath() + "/binPublic.ky");

      // Load the message from file
      FileInputStream mis = new FileInputStream(getPath() + "/message.txt");
      byte[] message = new byte[mis.available()];
      mis.read(message); mis.close();

      // Display the resurrected string
      JOptionPane.showMessageDialog(null,
          new String(message, 0, message.length, "UTF-8"));       

      // Load the signature of the message from file
      FileInputStream sis = new FileInputStream(getPath() + "/signature.txt");
      byte[] signature = new byte[sis.available()];
      sis.read(signature); sis.close();
      Base64 base64 = new Base64();
      byte[] sig = base64.decode(signature);

      // Initialize Signature Object
      Signature verifier = Signature.getInstance(ALGORITHM);
      verifier.initVerify(publicKey);

      // Load the message into the Verifier Object
      verifier.update(message);

      // Verify the Signature on the Message
      boolean result = verifier.verify(sig);
      
      StringBuilder sb = new StringBuilder();
      if( result )
      {
        sb.append("Message Verified:\n");
        sb.append(new String(message, 0, message.length, "UTF-8"));
      }
      else
      {
        sb.append("Message Not Verified");
      }
      
      JOptionPane.showMessageDialog(null, sb.toString());

    } catch (Exception e) {
      System.err.println("VerifyDSAMessage: " + e.toString());
    }
  }
  
  private static void SignDSAMessage() {

    try {
      // Retrieve the Private Key
      PrivateKey privateKey = LoadPrivateKey("F:\\binPrivate.ky");

      // Create the signer object
      Signature signer = Signature.getInstance(ALGORITHM);
      signer.initSign(privateKey, new SecureRandom());

      // Prepare the Message
      String s = "user_amount = 100\r\ncamera_amount = 1000\r\ndevice_amount = 5000\r\nexpire_time = 2020-01-18\r\nmother_board_list = CN1374031H06D, CN1374031H03D, CN1374044H00D\r\ncpuid_list = BFEBFBFF000206D7, BFEBFBFF011206D8\r\nmac_list = 90:B1:1C:37:A0:94, 90:B1:1C:37:A0:87";

      // Save the binary of the String which we will sign
      byte[] message = s.getBytes("UTF-8");

      // Insert the message into the signer object
      signer.update(message);
      byte[] signature = signer.sign();

      // mos: message filestream
      // sos: signature filestream
      FileOutputStream mos = new FileOutputStream("dsa.java.msg");
      mos.write(message);

      FileOutputStream sos = new FileOutputStream("dsa.java.sig");
      sos.write(signature);

    } catch (Exception e) {
      System.err.println("SignDSAMessage: " + e.toString());
    }
  }

  private static PublicKey LoadPublicKey(String filename) {

    PublicKey key = null;

    try {

      FileInputStream fis = new FileInputStream(filename);
      byte[] b = new byte[fis.available()];
      fis.read(b);
      fis.close();

      X509EncodedKeySpec spec = new X509EncodedKeySpec(b);

      KeyFactory factory = KeyFactory.getInstance(ALGORITHM);
      key = factory.generatePublic(spec);

    } catch (Exception e) {
      System.err.println("LoadPublicKey: " + e.toString());
    }

    return key;
  }
  
  private static PrivateKey LoadPrivateKey( String filename) {

    PrivateKey key = null;

    try {

      FileInputStream fis = new FileInputStream(filename);
      byte[] b = new byte[fis.available()];
      fis.read(b);
      fis.close();

      PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(b);

      KeyFactory factory = KeyFactory.getInstance("DSA");
      key = factory.generatePrivate(spec);

    } catch (Exception e) {
      System.err.println("LoadPrivateKey: " + e.toString());
    }

    return key;
  }

  private static void CreateDSAKeys() throws NoSuchAlgorithmException {

    // http://java.sun.com/j2se/1.4.2/docs/guide/security/CryptoSpec.html
    // KeyPairGenerator kpg = KeyPairGenerator.getInstance("DSA", "SUN");
    KeyPairGenerator kpg = KeyPairGenerator.getInstance(ALGORITHM);

    kpg.initialize(1024, new SecureRandom());
    KeyPair keys = kpg.generateKeyPair();

    PrivateKey privateKey = keys.getPrivate();
    PublicKey publicKey = keys.getPublic();

    // Serialize Keys
    SaveKey("private.dsa.java.key", privateKey);
    SaveKey("public.dsa.java.key", publicKey);
  }

  static void SaveKey(String filename, Key key) {
    try {

      if (null == key) {
        throw new Exception("key is null.");
      }

      FileOutputStream fos = new FileOutputStream(filename);

      // PKCS #8 for Private, X.509 for Public
      // File will contain OID 1.2.840.10040.4.1 (DSA)
      // http://java.sun.com/j2se/1.4.2/docs/api/java/security/Key.html
      fos.write(key.getEncoded());

      fos.close();

    } catch (Exception e) {
      System.err.println("SaveEncodedKey: Exception " + e.toString());
    }
  }
}


