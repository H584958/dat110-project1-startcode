package no.hvl.dat110.rpc;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import no.hvl.dat110.TODO;

public class RPCUtils {

	// Utility methods for marshalling and marshalling of parameters and return values
	// in RPC request and RPC responses
	// data bytearrays and return byte arrays is according to the 
	// RPC message syntax [rpcid,parameter/return value]
	
	public static byte[] marshallString(byte rpcid, String str) {

		byte[] encoded = new byte [str.length() + 1];

		encoded[0] = rpcid;

		byte [] stringArray = str.getBytes();

		for (int i = 1; i< str.length() + 1; i++) {
			encoded[i] = stringArray[i-1];
		}

		return encoded;
	}

	public static String unmarshallString(byte[] data) {

		byte [] str = new byte [data.length-1];

		for (int i = 1; i < data.length; i++) {
			str[i-1] = data[i];
		}
		String decoded = new String(str, StandardCharsets.UTF_8);

		return decoded;
	}

	public static byte[] marshallVoid(byte rpcid) {

		byte[] encoded = new byte[1];
		encoded[0] = rpcid;
		return encoded;

	}

	public static void unmarshallVoid(byte[] data) {

	}

	public static byte[] marshallBoolean(byte rpcid, boolean b) {

		byte[] encoded = new byte[2];

		encoded[0] = rpcid;

		if (b) {
			encoded[1] = 1;
		} else {
			encoded[1] = 0;
		}

		return encoded;
	}

	public static boolean unmarshallBoolean(byte[] data) {

		return (data[1] > 0);

	}

	public static byte[] marshallInteger(byte rpcid, int x) {

		byte[] encoded = new byte[5];
		encoded[0] = rpcid;
		byte [] integerByteArray = ByteBuffer.allocate(4).putInt(x).array();

		for (int i = 1; i < encoded.length; i++) {
			encoded[i] = integerByteArray[i-1];
		}

		return encoded;
	}

	public static int unmarshallInteger(byte[] data) {



		byte [] integerByteArray = new byte [data.length-1];

		for (int i = 1; i < data.length; i++) {
			integerByteArray[i-1] = data[i];
		}
		int decoded = ByteBuffer.wrap(integerByteArray).getInt();

		return decoded;

	}
}
