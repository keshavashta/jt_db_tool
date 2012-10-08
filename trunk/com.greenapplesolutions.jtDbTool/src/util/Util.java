package util;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.jasypt.exceptions.EncryptionOperationNotPossibleException;
import org.jasypt.util.text.BasicTextEncryptor;

import com.greenapplesolutions.jtbbtool.config.LuceneConfig;

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

	public static  Date getDefaultDate() {
		return new Date();
	}

	public static String replaceSpecialCharacters(String val) {
		if (isStringNullOrEmpty(val))
			return "";
		return val.replace("}", "}").replace("€", "e").replace("‚", ",")
				.replace("ƒ", "f").replace("„", "\"").replace("…", "…")
				.replace("†", "+").replace("‡", "++").replace("ˆ", "^")
				.replace("‰", "%").replace("Š", "S").replace("‹on", "<on")
				.replace("Œ", "CE").replace("Ž", "Z").replace("‘", "'")
				.replace("’", "'").replace("“", "\"").replace("”", "\"")
				.replace("•", ".").replace("–", "-").replace("—", "-")
				.replace("˜", "~").replace("™", "tm").replace("›on", ">on")
				.replace("Ÿ", "y").replace("¡", "i").replace("¢", "c")
				.replace("¤", "o").replace("¥", "y").replace("¦", "|")
				.replace("§", "s").replace("¨", "'").replace("ª", "a")
				.replace("«", "<<").replace("¬", "-").replace("®", "o")
				.replace("¯", "-").replace("°", "o").replace("²", "2")
				.replace("³", "3").replace("´", "'").replace("µ", "u")
				.replace("·", ".").replace("¸", ",").replace("¹", "1")
				.replace("º", "o").replace("»", ">>").replace("¼", "1/4")
				.replace("½", "1/2").replace("¾", "3/4").replace("¿", "?")
				.replace("À", "A").replace("Á", "A").replace("Â", "A")
				.replace("Ã", "A").replace("Ä", "A").replace("Å", "A")
				.replace("Æ", "AE").replace("Ç", "C").replace("È", "E")
				.replace("É", "E").replace("Ê", "E").replace("Ë", "E")
				.replace("Ì", "I").replace("Í", "I").replace("Î", "I")
				.replace("Ï", "I").replace("Ð", "D").replace("Ñ", "N")
				.replace("Ò", "O").replace("Ó", "O").replace("Ô", "O")
				.replace("Õ", "O").replace("Ö", "O").replace("×", "x")
				.replace("Ø", "0").replace("Ù", "u").replace("Ú", "u")
				.replace("Û", "u").replace("Ü", "u").replace("Ý", "y")
				.replace("Þ", "p").replace("ß", "b");
	}

	public static String getDateInString(String format, Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		String d = sdf.format(date);
		return d;
	}

	public static String getRelativePath() {
		File f = new File("");
		return f.getAbsolutePath();
	}

	public static String encryptText(String plainText) {
		String userPassword = LuceneConfig.INSTANCE().getPassword();
		BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
		textEncryptor.setPassword(userPassword);
		String myEncryptedText = textEncryptor.encrypt(plainText);
		return myEncryptedText;
	}

	public static Point getScreenSize() {
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		Point p = new Point((int) d.getWidth(), (int) d.getHeight());
		return p;
	}

	public static Rectangle getFullScreenBounds() {
		Point p = getScreenSize();
		return new Rectangle(0, 0, p.x, p.y);
	}

	public static Rectangle getBounds(int x, int y) {
		Point p = getScreenSize();
		return new Rectangle((p.x / 2) - (x / 2), 100, x, y);
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
