package com.putaoteng.task7.test;

import com.putaoteng.task7.utils.other.AliStorage;
import com.putaoteng.task7.utils.other.QiniuStorage;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.util.List;

public class QiniuStorageTest {
	private QiniuStorage qiniuStorage;
	private AliStorage aliStorage;
	@Test
	public void test() {

		ApplicationContext context =
				new ClassPathXmlApplicationContext("application-context.xml");
		qiniuStorage = (QiniuStorage) context.getBean("qiniuStorage");
		aliStorage = (AliStorage) context.getBean("aliStorage");

		/*try {
			qiniuStorage.deleteFile("task7");
		} catch (QiniuException e) {
			Log.loggerCreate(LogLevel.ERROR, "七牛云异常:delete()  "+ e.getMessage());
		}*/

	/*	String url = "http://xiaowenhou.oss-cn-shenzhen.aliyuncs.com/task7";
		String fileName = "test";
		try {
			qiniuStorage.uploadFileByUrl(url, fileName);
		} catch (QiniuException e) {
			e.printStackTrace();
		}*/

		//删除阿里云存储中的所有文件
		List<String> list = aliStorage.getFileList();
		aliStorage.deleteBach(list);

		//获取七牛云存储中的文件列表
		list = qiniuStorage.getFileList();
		/*String[] strArray = new String[list.size()];
		for (int i=0; i<list.size(); i++){
			strArray[i] = list.get(i);
		}*/

		//构造url并直接将文件传输到阿里云
		String url;
		for (String str: list) {
			url = qiniuStorage.getEndpoint() + "/" + str;
			try {
				aliStorage.uploadFileByUrl(url, str);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		/*try {
			qiniuStorage.deleteBach(strArray);
		} catch (QiniuException e) {
			e.printStackTrace();
		}*/
	}

}
 