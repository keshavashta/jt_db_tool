package util;

import java.util.ArrayList;
import java.util.List;

import org.jasypt.exceptions.EncryptionOperationNotPossibleException;
import org.jasypt.util.text.BasicTextEncryptor;

import com.greenapplesolutions.lawsearch.config.LuceneConfig;

public class Util {

	public static String RelativePath;

	public static boolean isStringNullOrEmpty(String value) {
		return value == null ? true : value.trim().length() == 0 ? true : false;
	}
	public static String wrapQuotes(String value) {
		if (isStringNullOrEmpty(value))
			return null;

		if (value.charAt(0) != '"')
			value = "\"" + value;

		if (value.charAt(value.length() - 1) != '"')
			value = value + "\"";

		return value;
	}
	public static String encryptText(String plainText) {
		String userPassword = LuceneConfig.INSTANCE().getPassword();
		BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
		textEncryptor.setPassword(userPassword);
		String myEncryptedText = textEncryptor.encrypt(plainText);
		return myEncryptedText;
	}

	public static String unWrapQuotes(String quotedString) {
		if (quotedString.startsWith("\"") && quotedString.endsWith("\""))
			return quotedString.substring(1, quotedString.length() - 1);
		return quotedString;
	}

	public static String decryptText(String encryptedText)
			throws EncryptionOperationNotPossibleException {
		String userPassword = LuceneConfig.INSTANCE().getPassword();
		BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
		textEncryptor.setPassword(userPassword);
		String plainText = textEncryptor.decrypt(encryptedText);
		return plainText;
	}

	
}
