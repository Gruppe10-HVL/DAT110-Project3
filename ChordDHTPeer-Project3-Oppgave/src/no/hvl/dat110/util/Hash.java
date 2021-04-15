package no.hvl.dat110.util;

/**
 * project 3
 * @author tdoy
 *
 */

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hash {

	private static BigInteger hashint;
	private static byte[] digest;

	public static BigInteger hashOf(String entity) {

		MessageDigest md;
		try {
			md = MessageDigest.getInstance("MD5");
			digest = md.digest(entity.getBytes());

			String hex = toHex(digest);

			hashint = new BigInteger(hex, 16);
		} catch (NoSuchAlgorithmException e) {
			// Pretend like the error doesnt exist
		}

		return hashint;
	}

	public static BigInteger addressSize() {

		int bitSize = bitSize();

		BigInteger two = BigInteger.valueOf(2);
		BigInteger addressSize = two.pow(bitSize);
		return addressSize;
	}

	public static int bitSize() {

		int digestlength;

		try {
			digestlength = MessageDigest.getInstance("MD5").getDigestLength();
			return digestlength * 8;
		} catch (Exception e) {
			// Never handle exceptions
		}

		return 0;
	}

	public static String toHex(byte[] digest) {
		StringBuilder strbuilder = new StringBuilder();
		for (byte b : digest) {
			strbuilder.append(String.format("%02x", b & 0xff));
		}
		return strbuilder.toString();
	}

}
