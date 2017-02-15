package com.javatea.spotchserver.opt;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

/**
 * 文字列のHash化を行うクラス
 */
public class Hash {
	/** 変換に利用するアルゴリズム*/
	private static final String ALGORITHM = "PBKDF2WithHmacSHA256";
	/** Hash化の実行回数*/
	private static final int ITERATION_COUNT = 10000;
	/** Hashの文字数の指定*/
	private static final int KEY_LENGTH = 256;

	/**
	 * パスワードのHash化の際に利用する
	 * @param pass
	 * @param salt
	 * @return Hash化後の文字列
	 */
	public static String getPassHash(String pass,String salt) {
		char[] targetCharArr = pass.toCharArray();
		byte[] hashedSalt = getHash(salt);

		PBEKeySpec keySpec = new PBEKeySpec(targetCharArr,hashedSalt,ITERATION_COUNT,KEY_LENGTH);

		SecretKeyFactory skf;
		SecretKey secretKey = null;
		try {
			skf = SecretKeyFactory.getInstance(ALGORITHM);
			secretKey = skf.generateSecret(keySpec);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			/*なんか処理を書く*/
			e.printStackTrace();
		}
		byte[] passByteAry = secretKey.getEncoded();

		String result = convertByteAryToString(passByteAry);

		return result;
	}

	/**
	 * 文字列をHash化する
	 * @param target
	 * @return
	 */
	private static byte[] getHash(String target) {
		MessageDigest msgd;
		byte[] result = null;
		try {
			msgd = MessageDigest.getInstance("sha-256");
			msgd.update(target.getBytes());
			result = msgd.digest();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 文字列をHash化した結果を文字列で取得する
	 * @param target
	 * @return Hash化後の文字列
	 */
	public static String getHashStr(String target) {
		byte[] hashAry = getHash(target);
		String hashStr = convertByteAryToString(hashAry);
		return hashStr;
	}

	/**
	 * byte型配列を16進数表記の文字列に変換して返す
	 * @param bytes
	 * @return
	 */
	private static String convertByteAryToString(byte[] bytes) {
		StringBuilder sb = new StringBuilder(2 * bytes.length);
		for (byte b:bytes ){
			sb.append(String.format("%02x", b & 0xff));
		}

		return sb.toString();
	}
}
