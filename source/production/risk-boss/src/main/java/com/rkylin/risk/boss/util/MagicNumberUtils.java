package com.rkylin.risk.boss.util;

import com.google.common.io.BaseEncoding;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by tomalloc on 16-11-11.
 */
public class MagicNumberUtils {
  private static final HashMap<String, String> mFileTypes = new HashMap<String, String>();

  static {
    // images
    mFileTypes.put("FFD8FF", "jpg");
    mFileTypes.put("89504E47", "png");
    mFileTypes.put("47494638", "gif");
    mFileTypes.put("49492A00", "tif");
    mFileTypes.put("424D", "bmp");
    //
    mFileTypes.put("41433130", "dwg"); // CAD
    mFileTypes.put("38425053", "psd");
    mFileTypes.put("7B5C727466", "rtf"); // 日记本
    mFileTypes.put("3C3F786D6C", "xml");
    mFileTypes.put("68746D6C3E", "html");
    mFileTypes.put("44656C69766572792D646174653A", "eml"); // 邮件
    mFileTypes.put("D0CF11E0", "doc");
    mFileTypes.put("5374616E64617264204A", "mdb");
    mFileTypes.put("252150532D41646F6265", "ps");
    mFileTypes.put("255044462D312E", "pdf");
    mFileTypes.put("504B03040A00000000008", "docx");
    mFileTypes.put("504B0304", "zip");// zip 压缩文件
    mFileTypes.put("52617221", "rar");
    mFileTypes.put("57415645", "wav");
    mFileTypes.put("41564920", "avi");
    mFileTypes.put("2E524D46", "rm");
    mFileTypes.put("000001BA", "mpg");
    mFileTypes.put("000001B3", "mpg");
    mFileTypes.put("6D6F6F76", "mov");
    mFileTypes.put("3026B2758E66CF11", "asf");
    mFileTypes.put("4D546864", "mid");
    mFileTypes.put("1F8B08", "gz");
  }

  /**
   * 根据文件路径获取文件类型
   *
   * @param filePath 文件路径
   * @return 文件类型
   */
  public static String fileType(String filePath) {
    try (FileInputStream is = new FileInputStream(filePath)) {
      return fileType(is);
    } catch (IOException e) {
    }
    return null;
  }

  public static String fileType(InputStream inputStream) {
    try {
      byte[] b = new byte[20];
      inputStream.read(b, 0, b.length);
      return matchMagicNumber(b);
    } catch (IOException e) {
    }
    return null;
  }

  public static String fileType(byte[] data) {
    if(data==null||data.length<20){
      return null;
    }
    byte[] maigicNumberData=new byte[20];
    System.arraycopy(data,0,maigicNumberData,0,maigicNumberData.length);
    return matchMagicNumber(maigicNumberData);
  }

  private static String matchMagicNumber(byte[] data) {
    String hexData = BaseEncoding.base16().encode(data);
    for (Map.Entry<String, String> entry : mFileTypes.entrySet()) {
      if (hexData.startsWith(entry.getKey())) {
        return entry.getValue();
      }
    }
    return null;
  }
}
