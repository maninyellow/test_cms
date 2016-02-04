package com.znsx.test.http;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.SimpleHttpConnectionManager;
import org.apache.commons.httpclient.methods.DeleteMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.methods.multipart.PartSource;
import org.apache.commons.httpclient.methods.multipart.StringPart;
import org.apache.commons.httpclient.params.HttpClientParams;
import org.jdom.Document;
import org.jdom.Element;

import com.znsx.util.http.SocketHttpUtil;
import com.znsx.util.http.Utf8FilePart;
import com.znsx.util.xml.ElementUtil;

/**
 * Http协议发送测试类
 * 
 * @author huangbuji
 *         <p />
 *         Create at 2013 上午11:22:48
 */
public class HttpRequestTest {

	private static SimpleDateFormat sdf = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");

	public static void main(String[] args) throws Exception {
		// Timer timer = new Timer();
		// TimerTask task = new TimerTask() {
		// @Override
		// public void run() {
		// try {
		// serverRegister();
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		// }
		// };
		// timer.scheduleAtFixedRate(task, 0, 1000);

		for (int i = 0; i < 1; i++) {
			sgcResourceUpload();
		}
		// for (int i = 100; i < 300; i++) {
		// String sessionId = "10050000000000000";
		// sessionId = sessionId + i;
		// userLogoff(sessionId);
		// }
		// userLogoff("10050000000000000469");
		// userLogoff("10050000000000000470");
		// userLogoff("10050000000000000471");
		// userLogoff("10050000000000000472");
		// userLogoff("10050000000000000476");
		// userLogoff("10050000000000000477");
		// userLogoff("10050000000000000478");
		// userLogoff("10050000000000000479");
		// userLogoff("10050000000000000462");
		// userLogoff("10050000000000000463");
		// userLogoff("10050000000000000464");
		// userLogoff("10050000000000000465");
		// userLogoff("10050000000000000466");
		// userLogoff("10050000000000000467");
		
//		SocketChannel channel = SocketHttpUtil.sendPost("192.168.1.5", 8080, "/cms/user_login.xml", "userName=lwzx&passwd=e1f897d7c0de57322c9624070abc37ed&centerIp=192.168.1.5", "utf8");
//		ByteBuffer[] dsts = new ByteBuffer[1024];
//		long count = 0;
//		while ((count = channel.read(dsts))>0) {
//			
//		}
	}

	public static void uploadLicense() throws Exception {
		SimpleHttpConnectionManager connectionManager = new SimpleHttpConnectionManager(true);
		connectionManager.getParams().setTcpNoDelay(true);
		HttpClient client = new HttpClient(new HttpClientParams(),
				connectionManager);
		PostMethod method = new PostMethod(
				"http://192.168.1.104:8080/cms/upload_license.json");

		Part session = new StringPart("sessionId", "10050000000000000000",
				"utf8");
		Part license = new FilePart("Filedata", new File("D:/znsx.license"),
				null, "utf8");
		RequestEntity entity = new MultipartRequestEntity(new Part[] { session,
				license }, method.getParams());
		method.setRequestEntity(entity);

		client.executeMethod(method);

		System.out.println(new String(method.getResponseBody(), "utf8"));

		method.releaseConnection();
	}

	public static void listPtsDevice() throws Exception {
		HttpClient client = new HttpClient(new HttpClientParams(),
				new SimpleHttpConnectionManager(true));
		PostMethod method = new PostMethod(
				"http://192.168.1.104:8080/cms/list_pts_device.xml");
		String body = "<Request Method=\"List_PTS_Device\" Cmd=\"3005\"><StandardNumber>251000000022000122</StandardNumber><Start>0</Start><Limit>500</Limit></Request>";
		RequestEntity entity = new StringRequestEntity(body, "application/xml",
				"utf8");
		method.setRequestEntity(entity);
		long begin = System.currentTimeMillis();
		client.executeMethod(method);

		System.out.println(new String(method.getResponseBody(), "utf8"));
		System.out.println(System.currentTimeMillis() - begin);

		method.releaseConnection();
	}

	public static void countPtsDevice() throws Exception {
		HttpClient client = new HttpClient(new HttpClientParams(),
				new SimpleHttpConnectionManager(true));
		PostMethod method = new PostMethod(
				"http://192.168.1.2:8080/cms/count_pts_dvr.xml");
		String body = "<Request Method=\"Count_PTS_DVR\" Cmd=\"3008\"><StandardNumber>251000000024000001</StandardNumber></Request>";
		RequestEntity entity = new StringRequestEntity(body, "application/xml",
				"utf8");
		method.setRequestEntity(entity);
		client.executeMethod(method);

		System.out.println(new String(method.getResponseBody(), "utf8"));

		method.releaseConnection();
	}

	public static void listOrganDevice() throws Exception {
		HttpClient client = new HttpClient(new HttpClientParams(),
				new SimpleHttpConnectionManager(true));
		PostMethod method = new PostMethod(
				"http://192.168.1.2:8080/cms/list_organ_device.xml");
		String body = "sessionId=10050000000000000000&organId=10010000000000000000&recursion=1&gzip=0";
		// String body =
		// "sessionId=ff808081478fcc27014790b3be49007e&organId=10010000000000000000&recursion=1&gzip=0";
		RequestEntity entity = new StringRequestEntity(body,
				"application/x-www-form-urlencoded", "utf8");
		method.setRequestEntity(entity);
		long begin = System.currentTimeMillis();
		client.executeMethod(method);

		File f = new File("D:/response.xml");
		FileOutputStream out = new FileOutputStream(f);
		InputStream in = method.getResponseBodyAsStream();
		byte[] buffer = new byte[4096];
		int temp = 0;
		while ((temp = in.read(buffer)) > 0) {
			out.write(buffer, 0, temp);
		}
		out.flush();
		out.close();
		in.close();

		// System.out.println(new String(method.getResponseBody(), "utf8"));
		System.out.println(System.currentTimeMillis() - begin);

		method.releaseConnection();
	}

	public static void downloadImage() throws Exception {
		HttpClient client = new HttpClient(new HttpClientParams(),
				new SimpleHttpConnectionManager(true));
		PostMethod method = new PostMethod(
				"http://192.168.1.2:8080/cms/download_image.xml");
		String body = "sessionId=10050000000000000100&imageId=103";
		RequestEntity entity = new StringRequestEntity(body,
				"application/x-www-form-urlencoded", "utf8");
		method.setRequestEntity(entity);
		long begin = System.currentTimeMillis();
		client.executeMethod(method);

		File f = new File("D:/download.jpg");
		FileOutputStream out = new FileOutputStream(f);
		InputStream in = method.getResponseBodyAsStream();
		byte[] buffer = new byte[4096];
		int temp = 0;
		while ((temp = in.read(buffer)) > 0) {
			out.write(buffer, 0, temp);
		}
		out.flush();
		out.close();
		in.close();
		// System.out.println(new String(method.getResponseBody(), "utf8"));
		System.out.println(System.currentTimeMillis() - begin);

		method.releaseConnection();
	}

	public static void uploadImage() throws Exception {
		HttpClient client = new HttpClient(new HttpClientParams(),
				new SimpleHttpConnectionManager(true));
		PostMethod method = new PostMethod(
				"http://192.168.1.2:8080/cms/upload_image.xml");

		Part session = new StringPart("sessionId", "10050000000000000100",
				"utf8");
		Part resourceId = new StringPart("resourceId", "10040000000000001021",
				"utf8");
		Part resourceType = new StringPart("resourceType", "Camera", "utf8");
		Part image = new FilePart("Filedata", new File("D:/Koala.jpg"), null,
				"utf8");
		RequestEntity entity = new MultipartRequestEntity(new Part[] { session,
				resourceId, resourceType, image }, method.getParams());
		method.setRequestEntity(entity);

		client.executeMethod(method);

		System.out.println(new String(method.getResponseBody(), "utf8"));

		method.releaseConnection();
	}

	public static void createFavorite() throws Exception {
		HttpClient client = new HttpClient(new HttpClientParams(),
				new SimpleHttpConnectionManager(true));
		PostMethod method = new PostMethod(
				"http://192.168.1.104:8080/cms/create_favorite.xml");
		method.setRequestHeader("Expect", "100-continue");

		String body = "<Request Method=\"CreateFavorite\" Cmd=\"1003\"><SessionId>10000000000000000001</SessionId><Favorite Name=\"事故易发\"></Favorite></Request>";
		RequestEntity entity = new StringRequestEntity(body, "application/xml",
				"utf8");
		method.setRequestEntity(entity);
		long begin = System.currentTimeMillis();
		client.executeMethod(method);

		// System.out.println(new String(method.getResponseBody(), "utf8"));
		System.out.println(System.currentTimeMillis() - begin);

		method.releaseConnection();
	}

	public static void getUser() throws Exception {
		HttpClient client = new HttpClient(new HttpClientParams(),
				new SimpleHttpConnectionManager(true));
		PostMethod method = new PostMethod(
				"http://192.168.1.2:8080/cms/get_user.json");

		String body = "sessionId=10050000000000000100&id=10020000000000000001";
		RequestEntity entity = new StringRequestEntity(body,
				"application/x-www-form-urlencoded", "utf8");
		// RequestEntity entity = new StringRequestEntity(body,
		// "text/xml", "utf8");
		method.setRequestEntity(entity);
		long begin = System.currentTimeMillis();
		client.executeMethod(method);

		System.out.println(new String(method.getResponseBody(), "utf8"));
		System.out.println(System.currentTimeMillis() - begin);

		method.releaseConnection();
	}

	public static void setDefaultPreset() throws Exception {
		HttpClient client = new HttpClient(new HttpClientParams(),
				new SimpleHttpConnectionManager(true));
		PostMethod method = new PostMethod(
				"http://192.168.1.2:8080/cms/set_default_preset.xml");

		String body = "sessionId=10050000000000000100&presetId=129&set=1";
		RequestEntity entity = new StringRequestEntity(body,
				"application/x-www-form-urlencoded", "utf8");
		// RequestEntity entity = new StringRequestEntity(body,
		// "text/xml", "utf8");
		method.setRequestEntity(entity);
		long begin = System.currentTimeMillis();
		client.executeMethod(method);

		System.out.println(new String(method.getResponseBody(), "utf8"));
		System.out.println(System.currentTimeMillis() - begin);

		method.releaseConnection();
	}

	public static void checkUserSession() throws Exception {
		HttpClient client = new HttpClient(new HttpClientParams(),
				new SimpleHttpConnectionManager(true));
		PostMethod method = new PostMethod(
				"http://192.168.1.2:8080/cms/check_session.xml");

		String body = "<Request Method=\"Check_Session\" Cmd=\"3006\"><SessionId>10050000000000000740</SessionId></Request>";
		RequestEntity entity = new StringRequestEntity(body, "application/xml",
				"utf8");
		method.setRequestEntity(entity);
		long begin = System.currentTimeMillis();
		client.executeMethod(method);

		System.out.println(new String(method.getResponseBody(), "utf8"));
		System.out.println(System.currentTimeMillis() - begin);

		method.releaseConnection();
	}

	public static void listCcsUserSession() throws Exception {
		HttpClient client = new HttpClient(new HttpClientParams(),
				new SimpleHttpConnectionManager(true));
		PostMethod method = new PostMethod(
				"http://192.168.1.104:8080/cms/list_ccs_user_session.xml");

		String body = "<Request Method=\"List_CCS_User_Session\" Cmd=\"3012\"><StandardNumber>251000000020000001</StandardNumber></Request>";
		RequestEntity entity = new StringRequestEntity(body, "application/xml",
				"utf8");
		method.setRequestEntity(entity);
		long begin = System.currentTimeMillis();
		client.executeMethod(method);

		System.out.println(new String(method.getResponseBody(), "utf8"));
		System.out.println(System.currentTimeMillis() - begin);

		method.releaseConnection();
	}

	public static void serverRegister() throws Exception {
		HttpClient client = new HttpClient(new HttpClientParams(),
				new SimpleHttpConnectionManager(true));
		PostMethod method = new PostMethod(
				"http://192.168.1.104:8080/cms/server_register.xml");

		String body = "<Request Method=\"Server_Register\" Cmd=\"3001\"><SessionId>402885874cc692fb014cc693daf00004</SessionId><StandardNumber>251000000021000100</StandardNumber><Type>mss</Type><LanIP>192.168.1.7</LanIP></Request>";
		RequestEntity entity = new StringRequestEntity(body, "application/xml",
				"utf8");
		method.setRequestEntity(entity);
		long begin = System.currentTimeMillis();
		client.executeMethod(method);

		System.out.println(new String(method.getResponseBody(), "utf8"));
		System.out.println(System.currentTimeMillis() - begin);

		method.releaseConnection();
	}

	public static void mssUpdateConfig() throws Exception {
		HttpClient client = new HttpClient(new HttpClientParams(),
				new SimpleHttpConnectionManager(true));
		PostMethod method = new PostMethod(
				"http://192.168.1.2:8080/cms/mss_update_config.xml");

		String body = "<Request Method=\"MSS_Update_Config\" Cmd=\"3003\"><SessionId>10050000000000001384</SessionId><LanIP>192.168.1.101</LanIP><SipPort>5060</SipPort><VideoPort>9001</VideoPort><TelnetPort>9002</TelnetPort></Request>";
		RequestEntity entity = new StringRequestEntity(body, "application/xml",
				"utf8");
		method.setRequestEntity(entity);
		long begin = System.currentTimeMillis();
		client.executeMethod(method);

		System.out.println(new String(method.getResponseBody(), "utf8"));
		System.out.println(System.currentTimeMillis() - begin);

		method.releaseConnection();
	}

	public static void crsUpdateConfig() throws Exception {
		HttpClient client = new HttpClient(new HttpClientParams(),
				new SimpleHttpConnectionManager(true));
		PostMethod method = new PostMethod(
				"http://192.168.1.104:8080/cms/crs_update_config.xml");

		String body = "<Request Method=\"CRS_Update_Config\" Cmd=\"3016\"><SessionId>10050000000000008449</SessionId><LanIP>192.168.1.101</LanIP><SipPort>5060</SipPort><VideoPort>9001</VideoPort><TelnetPort>9002</TelnetPort></Request>";
		RequestEntity entity = new StringRequestEntity(body, "application/xml",
				"utf8");
		method.setRequestEntity(entity);
		long begin = System.currentTimeMillis();
		client.executeMethod(method);

		System.out.println(new String(method.getResponseBody(), "utf8"));
		System.out.println(System.currentTimeMillis() - begin);

		method.releaseConnection();
	}

	public static void ccsUpdateConfig() throws Exception {
		HttpClient client = new HttpClient(new HttpClientParams(),
				new SimpleHttpConnectionManager(true));
		PostMethod method = new PostMethod(
				"http://192.168.1.104:8080/cms/ccs_update_config.xml");

		String body = "<Request Method=\"CCS_Update_Config\" Cmd=\"3016\"><SessionId>ff8080814916ecd6014916ee76f20005</SessionId><LanIP>192.168.1.110</LanIP><SipPort>5060</SipPort><TelnetPort>9060</TelnetPort></Request>";
		RequestEntity entity = new StringRequestEntity(body, "application/xml",
				"utf8");
		method.setRequestEntity(entity);
		long begin = System.currentTimeMillis();
		client.executeMethod(method);

		System.out.println(new String(method.getResponseBody(), "utf8"));
		System.out.println(System.currentTimeMillis() - begin);

		method.releaseConnection();
	}

	public static void listOrganAllDevice() throws Exception {
		HttpClient client = new HttpClient(new HttpClientParams(),
				new SimpleHttpConnectionManager(true));
		PostMethod method = new PostMethod(
				"http://192.168.1.104:8080/cms/list_all_device.json");

		String body = "sessionId=10050000000000000000&organId=10010000000000000000&roleId=12&name=&type=2&startIndex=0&limit=10";
		RequestEntity entity = new StringRequestEntity(body,
				"application/x-www-form-urlencoded", "utf8");
		method.setRequestEntity(entity);
		long begin = System.currentTimeMillis();
		client.executeMethod(method);

		System.out.println(new String(method.getResponseBody(), "utf8"));
		System.out.println(System.currentTimeMillis() - begin);

		method.releaseConnection();
	}

	public static void listOrganDvr() throws Exception {
		HttpClient client = new HttpClient(new HttpClientParams(),
				new SimpleHttpConnectionManager(true));
		PostMethod method = new PostMethod(
				"http://192.168.1.104:8080/cms/list_dvr_by_device.json");

		String body = "sessionId=10050000000000000000&type=1&startIndex=0&limit=10&organId=10010000000000000001";
		RequestEntity entity = new StringRequestEntity(body,
				"application/x-www-form-urlencoded", "utf8");
		method.setRequestEntity(entity);
		long begin = System.currentTimeMillis();
		client.executeMethod(method);

		System.out.println(new String(method.getResponseBody(), "utf8"));
		System.out.println(System.currentTimeMillis() - begin);

		method.releaseConnection();
	}

	public static void listRoleResources() throws Exception {
		HttpClient client = new HttpClient(new HttpClientParams(),
				new SimpleHttpConnectionManager(true));
		PostMethod method = new PostMethod(
				"http://192.168.1.104:8080/cms/list_role_resources.json");

		String body = "sessionId=10050000000000000000&roleId=12&startIndex=0&limit=2";
		RequestEntity entity = new StringRequestEntity(body,
				"application/x-www-form-urlencoded", "utf8");
		method.setRequestEntity(entity);
		long begin = System.currentTimeMillis();
		client.executeMethod(method);

		System.out.println(new String(method.getResponseBody(), "utf8"));
		System.out.println(System.currentTimeMillis() - begin);

		method.releaseConnection();
	}

	public static void login() throws Exception {
		HttpClient client = new HttpClient(new HttpClientParams(),
				new SimpleHttpConnectionManager(true));
		PostMethod method = new PostMethod(
				"http://192.168.1.104:8080/cms/sgc_user_login.xml");

		String body = "userName=admin&passwd=e10adc3949ba59abbe56e057f20f883e";
		RequestEntity entity = new StringRequestEntity(body,
				"application/x-www-form-urlencoded", "utf8");
		method.setRequestEntity(entity);
		long begin = System.currentTimeMillis();
		client.executeMethod(method);

		System.out.println(new String(method.getResponseBody(), "utf8"));
		System.out.println(System.currentTimeMillis() - begin);

		method.releaseConnection();
	}

	public static void uploadLicense1() throws Exception {
		HttpClient client = new HttpClient(new HttpClientParams(),
				new SimpleHttpConnectionManager(true));
		PostMethod method = new PostMethod(
				"http://192.168.1.5:8080/cms/upload_license.json");

		Part session = new StringPart("sessionId", "10050000000000000000",
				"utf8");
		final InputStream in = null;
		Part license = new FilePart("Filedata", new PartSource() {
			@Override
			public long getLength() {
				try {
					return in.available();
				} catch (IOException e) {
					e.printStackTrace();
				}
				return 0;
			}

			@Override
			public String getFileName() {
				return "znsx.licence";
			}

			@Override
			public InputStream createInputStream() throws IOException {
				return in;
			}
		});
		RequestEntity entity = new MultipartRequestEntity(new Part[] { session,
				license }, method.getParams());
		method.setRequestEntity(entity);

		client.executeMethod(method);

		System.out.println(new String(method.getResponseBody(), "utf8"));

		method.releaseConnection();
	}

	public static void heartbeat() throws Exception {
		HttpClient client = new HttpClient(new HttpClientParams(),
				new SimpleHttpConnectionManager(true));
		PostMethod method = new PostMethod(
				"http://192.168.1.2:8080/cms/heartbeat.xml");

		String body = "<Request Method=\"Heartbeat\" Cmd=\"3010\"><SessionId>10050000000000000000</SessionId></Request>";
		RequestEntity entity = new StringRequestEntity(body, "application/xml",
				"utf8");
		method.setRequestEntity(entity);
		long begin = System.currentTimeMillis();
		client.executeMethod(method);

		System.out.println(new String(method.getResponseBody(), "utf8"));
		System.out.println(System.currentTimeMillis() - begin);

		method.releaseConnection();
	}

	public static void createDvr() throws Exception {
		HttpClient client = new HttpClient(new HttpClientParams(),
				new SimpleHttpConnectionManager(true));
		PostMethod method = new PostMethod(
				"http://192.168.1.104:8080/cms/create_dvr.json");
		String name = "DVR" + System.currentTimeMillis();
		String body = "sessionId=10050000000000000000&subType=01&name="
				+ name
				+ "&ptsId=10060000000000000001&transport=TCP&mode=compatible&maxConnect=4&channelAmount=4&organId=10010000000000000000&linkType=adsl&lanIp=192.168.1.111&port=8000&manufacturerId=1&location=机房&note=测试创建&userName=admin&password=MTIzNDU=&heartCycle=120&expand=&aicAmount=4&aocAmount=1";
		RequestEntity entity = new StringRequestEntity(body,
				"application/x-www-form-urlencoded", "utf8");
		method.setRequestEntity(entity);
		long begin = System.currentTimeMillis();
		client.executeMethod(method);

		System.out.println(new String(method.getResponseBody(), "utf8"));
		System.out.println(System.currentTimeMillis() - begin);

		method.releaseConnection();
	}

	public static void throughout() throws Exception {
		HttpClient client = new HttpClient(new HttpClientParams(),
				new SimpleHttpConnectionManager(true));
		PostMethod method = new PostMethod(
				"http://192.168.1.2:8080/cms/heartbeat.xml");

		String body = "<Request Method=\"Heartbeat\" Cmd=\"3010\"><SessionId>10050000000000000000</SessionId></Request>";
		RequestEntity entity = new StringRequestEntity(body, "application/xml",
				"utf8");
		method.setRequestEntity(entity);
		long begin = System.currentTimeMillis();
		client.executeMethod(method);

		Header[] headers = method.getResponseHeaders();
		int totalLength = 0;
		for (Header header : headers) {
			totalLength += header.toString().getBytes("utf8").length;
			// 追加换行\r\n两个字节
			totalLength += 2;
		}
		// 追加与body之间的空行\r\n
		totalLength += 2;
		// 加上body长度
		totalLength += method.getResponseContentLength();

		System.out.println("Response length: " + totalLength + " bytes");
		System.out.println(new String(method.getResponseBody(), "utf8"));
		System.out.println(System.currentTimeMillis() - begin);

		method.releaseConnection();
	}

	public static void listFavorite() throws Exception {
		HttpClient client = new HttpClient(new HttpClientParams(),
				new SimpleHttpConnectionManager(true));
		PostMethod method = new PostMethod(
				"http://192.168.1.5:8080/cms/list_favorite.xml");
		String body = "sessionId=10050000000000000000";
		RequestEntity entity = new StringRequestEntity(body,
				"application/x-www-form-urlencoded", "utf8");
		method.setRequestEntity(entity);
		long begin = System.currentTimeMillis();
		client.executeMethod(method);

		File f = new File("D:/response.xml");
		FileOutputStream out = new FileOutputStream(f);
		InputStream in = method.getResponseBodyAsStream();
		byte[] buffer = new byte[4096];
		int temp = 0;
		while ((temp = in.read(buffer)) > 0) {
			out.write(buffer, 0, temp);
		}
		out.flush();
		out.close();
		in.close();

		System.out.println(System.currentTimeMillis() - begin);

		method.releaseConnection();
	}

	public static void getRouteInfo() throws Exception {
		HttpClient client = new HttpClient(new HttpClientParams(),
				new SimpleHttpConnectionManager(true));
		PostMethod method = new PostMethod(
				"http://192.168.1.6:80/cms/get_resource_route_info.xml");

		String body = "<Request Method=\"Get_Resource_Route_Info\" Cmd=\"3007\"><SessionId>40288186487cf73d01487d568d73004c</SessionId><StandardNumber>251000000040000100</StandardNumber></Request>";
		RequestEntity entity = new StringRequestEntity(body, "application/xml",
				"utf8");
		method.setRequestEntity(entity);
		long begin = System.currentTimeMillis();
		client.executeMethod(method);

		System.out.println(new String(method.getResponseBody(), "utf8"));
		System.out.println(System.currentTimeMillis() - begin);

		method.releaseConnection();
	}

	public static void treeSubPlatform() throws Exception {
		HttpClient client = new HttpClient(new HttpClientParams(),
				new SimpleHttpConnectionManager(true));
		PostMethod method = new PostMethod(
				"http://192.168.1.2:8080/cms/tree_sub_platform.xml");

		String body = "sessionId=10050000000000000000&id=1&recursion=0";
		RequestEntity entity = new StringRequestEntity(body,
				"application/x-www-form-urlencoded", "utf8");
		method.setRequestEntity(entity);
		long begin = System.currentTimeMillis();
		client.executeMethod(method);

		System.out.println(new String(method.getResponseBody(), "utf8"));
		System.out.println(System.currentTimeMillis() - begin);

		method.releaseConnection();
	}

	public static void pushResource() throws Exception {
		HttpClient client = new HttpClient(new HttpClientParams(),
				new SimpleHttpConnectionManager(true));
		PostMethod method = new PostMethod(
				"http://192.168.1.104:8080/cms/push_resource.xml");

		Document doc = new Document();
		Element root = new Element("Request");
		doc.setRootElement(root);
		root.setAttribute("Method", "Push_Resource");
		root.setAttribute("Cmd", "1028");
		Element node = new Element("Node");
		root.addContent(node);
		node.setAttribute("StandardNumber", "234000000000000000");
		node.setAttribute("Name", "安徽省中心");
		Element channelList = new Element("ChannelList");
		node.addContent(channelList);
		for (int i = 1; i < 6; i++) {
			Element channel = new Element("Channel");
			channel.setAttribute("StandardNumber", "23400000000300114" + i);
			channel.setAttribute("Name", "安徽收费站-" + i);
			channel.setAttribute("SubType", "05");
			channel.setAttribute("Auth", "%07");
			channelList.addContent(channel);
		}
		Element subNodes = new Element("SubNodes");
		node.addContent(subNodes);
		Element node1 = new Element("Node");
		subNodes.addContent(node1);
		node1.setAttribute("StandardNumber", "234000000000000001");
		node1.setAttribute("Name", "合肥分中心");
		Element channelList1 = new Element("ChannelList");
		node1.addContent(channelList1);
		for (int i = 1; i < 100; i++) {
			Element channel = new Element("Channel");
			channel.setAttribute("StandardNumber", "2340000000040011" + i);
			channel.setAttribute("Name", "合肥收费站-" + i);
			channel.setAttribute("SubType", "05");
			channel.setAttribute("Auth", "%07");
			channelList1.addContent(channel);
		}

		Element subNodes1 = new Element("SubNodes");
		node1.addContent(subNodes1);

		String body = ElementUtil.doc2String(doc);
		RequestEntity entity = new StringRequestEntity(body, "application/xml",
				"utf8");
		method.setRequestEntity(entity);
		long begin = System.currentTimeMillis();
		client.executeMethod(method);

		System.out.println(new String(method.getResponseBody(), "utf8"));
		System.out.println(System.currentTimeMillis() - begin);

		method.releaseConnection();
	}

	public static void listPlatformServer() throws Exception {
		HttpClient client = new HttpClient(new HttpClientParams(),
				new SimpleHttpConnectionManager(true));
		PostMethod method = new PostMethod(
				"http://192.168.1.6:8080/cms/list_platform_server.json");

		String body = "sessionId=10050000000000000149";
		RequestEntity entity = new StringRequestEntity(body,
				"application/x-www-form-urlencoded", "utf8");
		method.setRequestEntity(entity);
		long begin = System.currentTimeMillis();
		client.executeMethod(method);

		System.out.println(new String(method.getResponseBody(), "utf8"));
		System.out.println(System.currentTimeMillis() - begin);

		method.releaseConnection();
	}

	public static void savePolicy() throws Exception {
		HttpClient client = new HttpClient(new HttpClientParams(),
				new SimpleHttpConnectionManager(true));
		PostMethod method = new PostMethod(
				"http://192.168.1.2:8080/cms/save_light_policy.xml");

		String body = "<Request Method=\"Save_Light_Policy\" Cmd=\"1208\">";
		body += "<SessionId>10050000000000000000</SessionId>";
		body += "<OrganId>10010000000000000000</OrganId>";
		body += "<LightPolicy Id=\"\" Name=\"加强照明\">";
		body += "<Light Id=\"5\" Status=\"1\" />";
		body += "<Light Id=\"6\" Status=\"1\" />";
		body += "</LightPolicy></Request>";
		RequestEntity entity = new StringRequestEntity(body, "application/xml",
				"utf8");
		method.setRequestEntity(entity);
		long begin = System.currentTimeMillis();
		client.executeMethod(method);

		System.out.println(new String(method.getResponseBody(), "utf8"));
		System.out.println(System.currentTimeMillis() - begin);

		method.releaseConnection();
	}

	public static void dasReport() throws Exception {
		HttpClient client = new HttpClient(new HttpClientParams(),
				new SimpleHttpConnectionManager(true));
		PostMethod method = new PostMethod(
				"http://192.168.1.104:8080/cms/das_report.xml");

		Document doc = new Document();
		Element root = new Element("Request");
		root.setAttribute("Method", "DAS_Report");
		root.setAttribute("Cmd", "3101");
		doc.setRootElement(root);

		Element session = new Element("SessionId");
		session.setText("10050000000000000000");
		root.addContent(session);
		// // VD
		// for (int i = 0; i < 1; i++) {
		// Element e = new Element("Device");
		// e.setAttribute("Type", "10");
		// e.setAttribute("StandardNum", "251000000200000134");
		// e.setAttribute("RecTime", sdf.format(System.currentTimeMillis()));
		// e.setAttribute("RecPeriod", "120");
		// e.setAttribute("UpFluxB", RandomUtils.nextInt(1000) + "");
		// e.setAttribute("UpFluxS", RandomUtils.nextInt(1000) + "");
		// e.setAttribute("UpFlux", 900 + "");
		// e.setAttribute("DwFluxB", RandomUtils.nextInt(1000) + "");
		// e.setAttribute("DwFluxS", RandomUtils.nextInt(1000) + "");
		// e.setAttribute("DwFlux", 2000 + "");
		// e.setAttribute("UpSpeed", RandomUtils.nextInt(180) + "");
		// e.setAttribute("DwSpeed", RandomUtils.nextInt(180) + "");
		// e.setAttribute("UpOcc", "40.75");
		// e.setAttribute("DwOcc", "75.00");
		// e.setAttribute("LaneNum", "6");
		// e.setAttribute("Status", "0");
		// e.setAttribute("CommStatus", "0");
		// root.addContent(e);
		// }
		// for (int i = 0; i < 5; i++) {
		// Element e = new Element("Device");
		// e.setAttribute("Type", "10");
		// e.setAttribute("StandardNum", "52");
		// e.setAttribute("RecTime", sdf.format(new Date(System
		// .currentTimeMillis() - i * 100000)));
		// e.setAttribute("RecPeriod", "120");
		// e.setAttribute("UpFluxB", RandomUtils.nextInt(1000) + "");
		// e.setAttribute("UpFluxS", RandomUtils.nextInt(1000) + "");
		// e.setAttribute("UpFlux", RandomUtils.nextInt(1000) + "");
		// e.setAttribute("DwFluxB", RandomUtils.nextInt(1000) + "");
		// e.setAttribute("DwFluxS", RandomUtils.nextInt(1000) + "");
		// e.setAttribute("DwFlux", RandomUtils.nextInt(1000) + "");
		// e.setAttribute("UpSpeed", RandomUtils.nextInt(180) + "");
		// e.setAttribute("DwSpeed", RandomUtils.nextInt(180) + "");
		// e.setAttribute("UpOcc", "50.00");
		// e.setAttribute("DwOcc", "75.00");
		// e.setAttribute("LaneNum", "4");
		// e.setAttribute("Status", "0");
		// e.setAttribute("CommStatus", "0");
		// root.addContent(e);
		// }
		// for (int i = 0; i < 100; i++) {
		// Element e = new Element("Device");
		// e.setAttribute("Type", "10");
		// e.setAttribute("StandardNumber", "0100000000001000");
		// e.setAttribute("RecTime", sdf.format(new
		// Date(System.currentTimeMillis() - i * 100000)));
		// e.setAttribute("RecPeriod", "120");
		// e.setAttribute("UpFluxB", RandomUtils.nextInt(1000) + "");
		// e.setAttribute("UpFluxS", RandomUtils.nextInt(1000) + "");
		// e.setAttribute("UpFlux", RandomUtils.nextInt(1000) + "");
		// e.setAttribute("DwFluxB", RandomUtils.nextInt(1000) + "");
		// e.setAttribute("DwFluxS", RandomUtils.nextInt(1000) + "");
		// e.setAttribute("DwFlux", RandomUtils.nextInt(1000) + "");
		// e.setAttribute("UpSpeed", RandomUtils.nextInt(180) + "");
		// e.setAttribute("DwSpeed", RandomUtils.nextInt(180) + "");
		// e.setAttribute("UpOcc", "50.00");
		// e.setAttribute("DwOcc", "75.00");
		// e.setAttribute("LaneNum", "4");
		// e.setAttribute("Status", "0");
		// e.setAttribute("CommStatus", "0");
		// root.addContent(e);
		// }
		// for (int i = 0; i < 100; i++) {
		// Element e = new Element("Device");
		// e.setAttribute("Type", "10");
		// e.setAttribute("StandardNumber", "0100000000001001");
		// e.setAttribute("RecTime", sdf.format(new
		// Date(System.currentTimeMillis() - i * 100000)));
		// e.setAttribute("RecPeriod", "120");
		// e.setAttribute("UpFluxB", RandomUtils.nextInt(1000) + "");
		// e.setAttribute("UpFluxS", RandomUtils.nextInt(1000) + "");
		// e.setAttribute("UpFlux", RandomUtils.nextInt(1000) + "");
		// e.setAttribute("DwFluxB", RandomUtils.nextInt(1000) + "");
		// e.setAttribute("DwFluxS", RandomUtils.nextInt(1000) + "");
		// e.setAttribute("DwFlux", RandomUtils.nextInt(1000) + "");
		// e.setAttribute("UpSpeed", RandomUtils.nextInt(180) + "");
		// e.setAttribute("DwSpeed", RandomUtils.nextInt(180) + "");
		// e.setAttribute("UpOcc", "50.00");
		// e.setAttribute("DwOcc", "75.00");
		// e.setAttribute("LaneNum", "4");
		// e.setAttribute("Status", "0");
		// e.setAttribute("CommStatus", "0");
		// root.addContent(e);
		// }
		// // WST
		// for (int i = 0; i < 5; i++) {
		// Element e = new Element("Device");
		// e.setAttribute("Type", "12");
		// e.setAttribute("StandardNum", "3");
		// e.setAttribute("RecTime", sdf.format(new Date(System
		// .currentTimeMillis() - i * 100000)));
		// e.setAttribute("RecPeriod", "120");
		// e.setAttribute("VisMax", RandomUtils.nextInt(1000) + "");
		// e.setAttribute("VisMin", "50");
		// e.setAttribute("VisAvg", RandomUtils.nextInt(1000) + "");
		// e.setAttribute("WindSpeedMax", RandomUtils.nextInt(20) + "");
		// e.setAttribute("WindSpeedMin", RandomUtils.nextInt(5) + "");
		// e.setAttribute("WindSpeedAvg", RandomUtils.nextInt(10) + "");
		// e.setAttribute("WindDir", (RandomUtils.nextInt(8) + 1) + "");
		// e.setAttribute("AirTempMax", RandomUtils.nextInt(50) + "");
		// e.setAttribute("AirTempMin", "-10.0");
		// e.setAttribute("AirTempAvg", RandomUtils.nextInt(40) + "");
		// e.setAttribute("RoadTempMax", RandomUtils.nextInt(50) + "");
		// e.setAttribute("RoadTempMin", "0.0");
		// e.setAttribute("RoadTempAvg", RandomUtils.nextInt(40) + "");
		// e.setAttribute("HumiMax", RandomUtils.nextInt(100) + "");
		// e.setAttribute("HumiMin", RandomUtils.nextInt(10) + "");
		// e.setAttribute("HumiAvg", RandomUtils.nextInt(90) + "");
		// e.setAttribute("RainVol", RandomUtils.nextInt(1000) + "");
		// e.setAttribute("SnowVol", RandomUtils.nextInt(500) + "");
		// e.setAttribute("RoadState", RandomUtils.nextInt(7) + "");
		// e.setAttribute("Status", "0");
		// e.setAttribute("CommStatus", "0");
		// root.addContent(e);
		// }
		// for (int i = 0; i < 5; i++) {
		// Element e = new Element("Device");
		// e.setAttribute("Type", "12");
		// e.setAttribute("StandardNum", "43");
		// e.setAttribute("RecTime", sdf.format(new Date(System
		// .currentTimeMillis() - i * 100000)));
		// e.setAttribute("RecPeriod", "120");
		// e.setAttribute("VisMax", RandomUtils.nextInt(1000) + "");
		// e.setAttribute("VisMin", "50");
		// e.setAttribute("VisAvg", RandomUtils.nextInt(1000) + "");
		// e.setAttribute("WindSpeedMax", RandomUtils.nextInt(20) + "");
		// e.setAttribute("WindSpeedMin", RandomUtils.nextInt(5) + "");
		// e.setAttribute("WindSpeedAvg", RandomUtils.nextInt(10) + "");
		// e.setAttribute("WindDir", (RandomUtils.nextInt(8) + 1) + "");
		// e.setAttribute("AirTempMax", RandomUtils.nextInt(50) + "");
		// e.setAttribute("AirTempMin", "-10.0");
		// e.setAttribute("AirTempAvg", RandomUtils.nextInt(40) + "");
		// e.setAttribute("RoadTempMax", RandomUtils.nextInt(50) + "");
		// e.setAttribute("RoadTempMin", "0.0");
		// e.setAttribute("RoadTempAvg", RandomUtils.nextInt(40) + "");
		// e.setAttribute("HumiMax", RandomUtils.nextInt(100) + "");
		// e.setAttribute("HumiMin", RandomUtils.nextInt(10) + "");
		// e.setAttribute("HumiAvg", RandomUtils.nextInt(90) + "");
		// e.setAttribute("RainVol", RandomUtils.nextInt(1000) + "");
		// e.setAttribute("SnowVol", RandomUtils.nextInt(500) + "");
		// e.setAttribute("RoadState", RandomUtils.nextInt(8) + "");
		// e.setAttribute("Status", "0");
		// e.setAttribute("CommStatus", "0");
		// root.addContent(e);
		// }
		// for (int i = 0; i < 100; i++) {
		// Element e = new Element("Device");
		// e.setAttribute("Type", "12");
		// e.setAttribute("StandardNumber", "0100100000000011");
		// e.setAttribute("RecTime", sdf.format(new
		// Date(System.currentTimeMillis() - i * 100000)));
		// e.setAttribute("RecPeriod", "120");
		// e.setAttribute("VisMax", RandomUtils.nextInt(1000) + "");
		// e.setAttribute("VisMin", "50");
		// e.setAttribute("VisAvg", RandomUtils.nextInt(1000) + "");
		// e.setAttribute("WindSpeedMax", RandomUtils.nextInt(20) + "");
		// e.setAttribute("WindSpeedMin", RandomUtils.nextInt(5) + "");
		// e.setAttribute("WindSpeedAvg", RandomUtils.nextInt(10) + "");
		// e.setAttribute("WindDir", (RandomUtils.nextInt(8) + 1) + "");
		// e.setAttribute("AirTempMax", RandomUtils.nextInt(50) + "");
		// e.setAttribute("AirTempMin", "-10.0");
		// e.setAttribute("AirTempAvg", RandomUtils.nextInt(40) + "");
		// e.setAttribute("RoadTempMax", RandomUtils.nextInt(50) + "");
		// e.setAttribute("RoadTempMin", "0.0");
		// e.setAttribute("RoadTempAvg", RandomUtils.nextInt(40) + "");
		// e.setAttribute("HumiMax", RandomUtils.nextInt(100) + "");
		// e.setAttribute("HumiMin", RandomUtils.nextInt(10) + "");
		// e.setAttribute("HumiAvg", RandomUtils.nextInt(90) + "");
		// e.setAttribute("RainVol", RandomUtils.nextInt(1000) + "");
		// e.setAttribute("SnowVol", RandomUtils.nextInt(500) + "");
		// e.setAttribute("RoadState", RandomUtils.nextInt(7) + "");
		// e.setAttribute("Status", "0");
		// e.setAttribute("CommStatus", "0");
		// root.addContent(e);
		// }
		// for (int i = 0; i < 10; i++) {
		// Element e = new Element("Device");
		// e.setAttribute("Type", "12");
		// e.setAttribute("StandardNumber", "1111110100000000001010101");
		// e.setAttribute("RecTime", sdf.format(new
		// Date(System.currentTimeMillis() - i * 100000)));
		// e.setAttribute("RecPeriod", "120");
		// e.setAttribute("VisMax", RandomUtils.nextInt(1000) + "");
		// e.setAttribute("VisMin", "50");
		// e.setAttribute("VisAvg", RandomUtils.nextInt(1000) + "");
		// e.setAttribute("WindSpeedMax", RandomUtils.nextInt(20) + "");
		// e.setAttribute("WindSpeedMin", RandomUtils.nextInt(5) + "");
		// e.setAttribute("WindSpeedAvg", RandomUtils.nextInt(10) + "");
		// e.setAttribute("WindDir", (RandomUtils.nextInt(8) + 1) + "");
		// e.setAttribute("AirTempMax", RandomUtils.nextInt(50) + "");
		// e.setAttribute("AirTempMin", "-10.0");
		// e.setAttribute("AirTempAvg", RandomUtils.nextInt(40) + "");
		// e.setAttribute("RoadTempMax", RandomUtils.nextInt(50) + "");
		// e.setAttribute("RoadTempMin", "0.0");
		// e.setAttribute("RoadTempAvg", RandomUtils.nextInt(40) + "");
		// e.setAttribute("HumiMax", RandomUtils.nextInt(100) + "");
		// e.setAttribute("HumiMin", RandomUtils.nextInt(10) + "");
		// e.setAttribute("HumiAvg", RandomUtils.nextInt(90) + "");
		// e.setAttribute("RainVol", RandomUtils.nextInt(1000) + "");
		// e.setAttribute("SnowVol", RandomUtils.nextInt(500) + "");
		// e.setAttribute("RoadState", RandomUtils.nextInt(7) + "");
		// e.setAttribute("Status", "0");
		// e.setAttribute("CommStatus", "0");
		// root.addContent(e);
		// }
		// COVI
		// for (int i = 0; i < 5; i++) {
		// Element e = new Element("Device");
		// e.setAttribute("Type", "15");
		// e.setAttribute("StandardNum", "56");
		// e.setAttribute("RecTime", sdf.format(new Date(System
		// .currentTimeMillis() - i * 100000)));
		// e.setAttribute("RecPeriod", "120");
		// e.setAttribute("COConct", RandomUtils.nextInt(100) + "");
		// e.setAttribute("Visibility", RandomUtils.nextInt(1000) + "");
		// e.setAttribute("Status", "0");
		// e.setAttribute("CommStatus", "0");
		// root.addContent(e);
		// }
		// for (int i = 0; i < 100; i++) {
		// Element e = new Element("Device");
		// e.setAttribute("Type", "15");
		// e.setAttribute("StandardNumber", "1000100000000010");
		// e.setAttribute("RecTime", sdf.format(new
		// Date(System.currentTimeMillis() - i * 100000)));
		// e.setAttribute("RecPeriod", "120");
		// e.setAttribute("COConct", RandomUtils.nextInt(100) + "");
		// e.setAttribute("Visibility", RandomUtils.nextInt(1000) + "");
		// e.setAttribute("Status", "0");
		// e.setAttribute("CommStatus", "0");
		// root.addContent(e);
		// }
		// NOD
		// for (int i = 0; i < 5; i++) {
		// Element e = new Element("Device");
		// e.setAttribute("Type", "16");
		// e.setAttribute("StandardNum", "57");
		// e.setAttribute("RecTime", sdf.format(new Date(System
		// .currentTimeMillis() - i * 100000)));
		// e.setAttribute("RecPeriod", "120");
		// e.setAttribute("NOConct", RandomUtils.nextInt(100) + "");
		// e.setAttribute("NO2Conct", RandomUtils.nextInt(100) + "");
		// e.setAttribute("Status", "0");
		// e.setAttribute("CommStatus", "0");
		// root.addContent(e);
		// }
		// for (int i = 0; i < 100; i++) {
		// Element e = new Element("Device");
		// e.setAttribute("Type", "16");
		// e.setAttribute("StandardNumber", "1011100000000010");
		// e.setAttribute("RecTime", sdf.format(new
		// Date(System.currentTimeMillis() - i * 100000)));
		// e.setAttribute("RecPeriod", "120");
		// e.setAttribute("NOConct", RandomUtils.nextInt(100) + "");
		// e.setAttribute("NO2Conct", RandomUtils.nextInt(100) + "");
		// e.setAttribute("Status", "0");
		// e.setAttribute("CommStatus", "0");
		// root.addContent(e);
		// }
		// LOLI
		// for (int i = 0; i < 5; i++) {
		// Element e = new Element("Device");
		// e.setAttribute("Type", "13");
		// e.setAttribute("StandardNum", "54");
		// e.setAttribute("RecTime", sdf.format(new Date(System
		// .currentTimeMillis() - i * 100000)));
		// e.setAttribute("RecPeriod", "120");
		// e.setAttribute("LOLumi", RandomUtils.nextInt(1000) + "");
		// e.setAttribute("LILumi", RandomUtils.nextInt(1000) + "");
		// e.setAttribute("Status", "0");
		// e.setAttribute("CommStatus", "0");
		// root.addContent(e);
		// }
		// for (int i = 0; i < 100; i++) {
		// Element e = new Element("Device");
		// e.setAttribute("Type", "13");
		// e.setAttribute("StandardNumber", "1001100000000010");
		// e.setAttribute("RecTime", sdf.format(new
		// Date(System.currentTimeMillis() - i * 100000)));
		// e.setAttribute("RecPeriod", "120");
		// e.setAttribute("LOLumi", RandomUtils.nextInt(1000) + "");
		// e.setAttribute("LILumi", RandomUtils.nextInt(1000) + "");
		// e.setAttribute("Status", "0");
		// e.setAttribute("CommStatus", "0");
		// root.addContent(e);
		// }
		// WS
		// for (int i = 0; i < 5; i++) {
		// Element e = new Element("Device");
		// e.setAttribute("Type", "11");
		// e.setAttribute("StandardNum", "51");
		// e.setAttribute("RecTime", sdf.format(new Date(System
		// .currentTimeMillis() - i * 100000)));
		// e.setAttribute("RecPeriod", "120");
		// e.setAttribute("Direction", RandomUtils.nextInt(3) + "");
		// e.setAttribute("Speed", RandomUtils.nextInt(20) + "");
		// e.setAttribute("Status", "0");
		// e.setAttribute("CommStatus", "0");
		// root.addContent(e);
		// }
		// for (int i = 0; i < 100; i++) {
		// Element e = new Element("Device");
		// e.setAttribute("Type", "11");
		// e.setAttribute("StandardNumber", "1001000000000010");
		// e.setAttribute("RecTime", sdf.format(new
		// Date(System.currentTimeMillis() - i * 100000)));
		// e.setAttribute("RecPeriod", "120");
		// e.setAttribute("Direction", RandomUtils.nextInt(3) + "");
		// e.setAttribute("Speed", RandomUtils.nextInt(20) + "");
		// e.setAttribute("Status", "0");
		// e.setAttribute("CommStatus", "0");
		// root.addContent(e);
		// }
		// // FAN
		// for (int i = 0; i < 100; i++) {
		// Element e = new Element("Device");
		// e.setAttribute("Type", "18");
		// e.setAttribute("StandardNumber", "1100000000000001");
		// e.setAttribute("RecTime", sdf.format(new
		// Date(System.currentTimeMillis() - i * 100000)));
		// e.setAttribute("RecPeriod", "120");
		// e.setAttribute("WorkState", RandomUtils.nextInt(2) + "");
		// e.setAttribute("Status", "0");
		// e.setAttribute("CommStatus", "0");
		// root.addContent(e);
		// }
		// for (int i = 0; i < 100; i++) {
		// Element e = new Element("Device");
		// e.setAttribute("Type", "18");
		// e.setAttribute("StandardNumber", "1100000000000010");
		// e.setAttribute("RecTime", sdf.format(new
		// Date(System.currentTimeMillis() - i * 100000)));
		// e.setAttribute("RecPeriod", "120");
		// e.setAttribute("WorkState", RandomUtils.nextInt(2) + "");
		// e.setAttribute("Status", "0");
		// e.setAttribute("CommStatus", "0");
		// root.addContent(e);
		// }
		// for (int i = 0; i < 100; i++) {
		// Element e = new Element("Device");
		// e.setAttribute("Type", "18");
		// e.setAttribute("StandardNumber", "1100000000000011");
		// e.setAttribute("RecTime", sdf.format(new
		// Date(System.currentTimeMillis() - i * 100000)));
		// e.setAttribute("RecPeriod", "120");
		// e.setAttribute("WorkState", RandomUtils.nextInt(2) + "");
		// e.setAttribute("Status", "0");
		// e.setAttribute("CommStatus", "0");
		// root.addContent(e);
		// }
		// for (int i = 0; i < 100; i++) {
		// Element e = new Element("Device");
		// e.setAttribute("Type", "18");
		// e.setAttribute("StandardNumber", "1100000000000100");
		// e.setAttribute("RecTime", sdf.format(new
		// Date(System.currentTimeMillis() - i * 100000)));
		// e.setAttribute("RecPeriod", "120");
		// e.setAttribute("WorkState", RandomUtils.nextInt(2) + "");
		// e.setAttribute("Status", "0");
		// e.setAttribute("CommStatus", "0");
		// root.addContent(e);
		// }
		// for (int i = 0; i < 100; i++) {
		// Element e = new Element("Device");
		// e.setAttribute("Type", "18");
		// e.setAttribute("StandardNumber", "1100000000000101");
		// e.setAttribute("RecTime", sdf.format(new
		// Date(System.currentTimeMillis() - i * 100000)));
		// e.setAttribute("RecPeriod", "120");
		// e.setAttribute("WorkState", RandomUtils.nextInt(2) + "");
		// e.setAttribute("Status", "0");
		// e.setAttribute("CommStatus", "0");
		// root.addContent(e);
		// }
		// for (int i = 0; i < 100; i++) {
		// Element e = new Element("Device");
		// e.setAttribute("Type", "18");
		// e.setAttribute("StandardNumber", "1100000000000110");
		// e.setAttribute("RecTime", sdf.format(new
		// Date(System.currentTimeMillis() - i * 100000)));
		// e.setAttribute("RecPeriod", "120");
		// e.setAttribute("WorkState", RandomUtils.nextInt(2) + "");
		// e.setAttribute("Status", "0");
		// e.setAttribute("CommStatus", "0");
		// root.addContent(e);
		// }
		// for (int i = 0; i < 100; i++) {
		// Element e = new Element("Device");
		// e.setAttribute("Type", "18");
		// e.setAttribute("StandardNumber", "1100000000000111");
		// e.setAttribute("RecTime", sdf.format(new
		// Date(System.currentTimeMillis() - i * 100000)));
		// e.setAttribute("RecPeriod", "120");
		// e.setAttribute("WorkState", RandomUtils.nextInt(2) + "");
		// e.setAttribute("Status", "0");
		// e.setAttribute("CommStatus", "0");
		// root.addContent(e);
		// }
		// for (int i = 0; i < 100; i++) {
		// Element e = new Element("Device");
		// e.setAttribute("Type", "18");
		// e.setAttribute("StandardNumber", "1100000000001000");
		// e.setAttribute("RecTime", sdf.format(new
		// Date(System.currentTimeMillis() - i * 100000)));
		// e.setAttribute("RecPeriod", "120");
		// e.setAttribute("WorkState", RandomUtils.nextInt(2) + "");
		// e.setAttribute("Status", "0");
		// e.setAttribute("CommStatus", "0");
		// root.addContent(e);
		// }
		// // RD
		// for (int i = 0; i < 100; i++) {
		// Element e = new Element("Device");
		// e.setAttribute("Type", "20");
		// e.setAttribute("StandardNumber", "1100100000000001");
		// e.setAttribute("RecTime", sdf.format(new
		// Date(System.currentTimeMillis() - i * 100000)));
		// e.setAttribute("RecPeriod", "120");
		// e.setAttribute("WorkState", RandomUtils.nextInt(3) + "");
		// e.setAttribute("Status", "0");
		// e.setAttribute("CommStatus", "0");
		// root.addContent(e);
		// }
		// for (int i = 0; i < 100; i++) {
		// Element e = new Element("Device");
		// e.setAttribute("Type", "20");
		// e.setAttribute("StandardNumber", "1100100000000010");
		// e.setAttribute("RecTime", sdf.format(new
		// Date(System.currentTimeMillis() - i * 100000)));
		// e.setAttribute("RecPeriod", "120");
		// e.setAttribute("WorkState", RandomUtils.nextInt(3) + "");
		// e.setAttribute("Status", "0");
		// e.setAttribute("CommStatus", "0");
		// root.addContent(e);
		// }
		// // WP
		// for (int i = 0; i < 100; i++) {
		// Element e = new Element("Device");
		// e.setAttribute("Type", "21");
		// e.setAttribute("StandardNumber", "1101000000000001");
		// e.setAttribute("RecTime", sdf.format(new
		// Date(System.currentTimeMillis() - i * 100000)));
		// e.setAttribute("RecPeriod", "120");
		// e.setAttribute("WorkState", RandomUtils.nextInt(2) + "");
		// e.setAttribute("Status", "0");
		// e.setAttribute("CommStatus", "0");
		// root.addContent(e);
		// }
		// // RAIL
		// for (int i = 0; i < 100; i++) {
		// Element e = new Element("Device");
		// e.setAttribute("Type", "22");
		// e.setAttribute("StandardNumber", "1101100000000001");
		// e.setAttribute("RecTime", sdf.format(new
		// Date(System.currentTimeMillis() - i * 100000)));
		// e.setAttribute("RecPeriod", "120");
		// e.setAttribute("WorkState", RandomUtils.nextInt(2) + "");
		// e.setAttribute("Status", "0");
		// e.setAttribute("CommStatus", "0");
		// root.addContent(e);
		// }
		// // IS
		// for (int i = 0; i < 100; i++) {
		// Element e = new Element("Device");
		// e.setAttribute("Type", "23");
		// e.setAttribute("StandardNumber", "1110000000000001");
		// e.setAttribute("RecTime", sdf.format(new
		// Date(System.currentTimeMillis() - i * 100000)));
		// e.setAttribute("RecPeriod", "120");
		// e.setAttribute("WorkState", RandomUtils.nextInt(2) + "");
		// e.setAttribute("Status", "0");
		// e.setAttribute("CommStatus", "0");
		// root.addContent(e);
		// }
		// // LIGHT
		// for (int i = 0; i < 100; i++) {
		// Element e = new Element("Device");
		// e.setAttribute("Type", "19");
		// e.setAttribute("StandardNumber", "1110100000000001");
		// e.setAttribute("RecTime", sdf.format(new
		// Date(System.currentTimeMillis() - i * 100000)));
		// e.setAttribute("RecPeriod", "120");
		// e.setAttribute("WorkState", RandomUtils.nextInt(2) + "");
		// e.setAttribute("Status", "0");
		// e.setAttribute("CommStatus", "0");
		// root.addContent(e);
		// }
		// for (int i = 0; i < 100; i++) {
		// Element e = new Element("Device");
		// e.setAttribute("Type", "19");
		// e.setAttribute("StandardNumber", "1110100000000010");
		// e.setAttribute("RecTime", sdf.format(new
		// Date(System.currentTimeMillis() - i * 100000)));
		// e.setAttribute("RecPeriod", "120");
		// e.setAttribute("WorkState", RandomUtils.nextInt(2) + "");
		// e.setAttribute("Status", "0");
		// e.setAttribute("CommStatus", "0");
		// root.addContent(e);
		// }
		// for (int i = 0; i < 100; i++) {
		// Element e = new Element("Device");
		// e.setAttribute("Type", "19");
		// e.setAttribute("StandardNumber", "1110100000000011");
		// e.setAttribute("RecTime", sdf.format(new
		// Date(System.currentTimeMillis() - i * 100000)));
		// e.setAttribute("RecPeriod", "120");
		// e.setAttribute("WorkState", RandomUtils.nextInt(2) + "");
		// e.setAttribute("Status", "0");
		// e.setAttribute("CommStatus", "0");
		// root.addContent(e);
		// }
		// for (int i = 0; i < 100; i++) {
		// Element e = new Element("Device");
		// e.setAttribute("Type", "19");
		// e.setAttribute("StandardNumber", "1110100000000100");
		// e.setAttribute("RecTime", sdf.format(new
		// Date(System.currentTimeMillis() - i * 100000)));
		// e.setAttribute("RecPeriod", "120");
		// e.setAttribute("WorkState", RandomUtils.nextInt(2) + "");
		// e.setAttribute("Status", "0");
		// e.setAttribute("CommStatus", "0");
		// root.addContent(e);
		// }
		// for (int i = 0; i < 100; i++) {
		// Element e = new Element("Device");
		// e.setAttribute("Type", "19");
		// e.setAttribute("StandardNumber", "1110100000000101");
		// e.setAttribute("RecTime", sdf.format(new
		// Date(System.currentTimeMillis() - i * 100000)));
		// e.setAttribute("RecPeriod", "120");
		// e.setAttribute("WorkState", RandomUtils.nextInt(2) + "");
		// e.setAttribute("Status", "0");
		// e.setAttribute("CommStatus", "0");
		// root.addContent(e);
		// }
		// for (int i = 0; i < 100; i++) {
		// Element e = new Element("Device");
		// e.setAttribute("Type", "19");
		// e.setAttribute("StandardNumber", "1110100000000110");
		// e.setAttribute("RecTime", sdf.format(new
		// Date(System.currentTimeMillis() - i * 100000)));
		// e.setAttribute("RecPeriod", "120");
		// e.setAttribute("WorkState", RandomUtils.nextInt(2) + "");
		// e.setAttribute("Status", "0");
		// e.setAttribute("CommStatus", "0");
		// root.addContent(e);
		// }
		// for (int i = 0; i < 100; i++) {
		// Element e = new Element("Device");
		// e.setAttribute("Type", "19");
		// e.setAttribute("StandardNumber", "1110100000000111");
		// e.setAttribute("RecTime", sdf.format(new
		// Date(System.currentTimeMillis() - i * 100000)));
		// e.setAttribute("RecPeriod", "120");
		// e.setAttribute("WorkState", RandomUtils.nextInt(2) + "");
		// e.setAttribute("Status", "0");
		// e.setAttribute("CommStatus", "0");
		// root.addContent(e);
		// }
		// for (int i = 0; i < 100; i++) {
		// Element e = new Element("Device");
		// e.setAttribute("Type", "19");
		// e.setAttribute("StandardNumber", "1110100000001000");
		// e.setAttribute("RecTime", sdf.format(new
		// Date(System.currentTimeMillis() - i * 100000)));
		// e.setAttribute("RecPeriod", "120");
		// e.setAttribute("WorkState", RandomUtils.nextInt(2) + "");
		// e.setAttribute("Status", "0");
		// e.setAttribute("CommStatus", "0");
		// root.addContent(e);
		// }
		// for (int i = 0; i < 100; i++) {
		// Element e = new Element("Device");
		// e.setAttribute("Type", "19");
		// e.setAttribute("StandardNumber", "1110100000001001");
		// e.setAttribute("RecTime", sdf.format(new
		// Date(System.currentTimeMillis() - i * 100000)));
		// e.setAttribute("RecPeriod", "120");
		// e.setAttribute("WorkState", RandomUtils.nextInt(2) + "");
		// e.setAttribute("Status", "0");
		// e.setAttribute("CommStatus", "0");
		// root.addContent(e);
		// }
		// for (int i = 0; i < 100; i++) {
		// Element e = new Element("Device");
		// e.setAttribute("Type", "19");
		// e.setAttribute("StandardNumber", "1110100000001010");
		// e.setAttribute("RecTime", sdf.format(new
		// Date(System.currentTimeMillis() - i * 100000)));
		// e.setAttribute("RecPeriod", "120");
		// e.setAttribute("WorkState", RandomUtils.nextInt(2) + "");
		// e.setAttribute("Status", "0");
		// e.setAttribute("CommStatus", "0");
		// root.addContent(e);
		// }
		//
		// for (int i = 0; i < 100; i++) {
		// Element e = new Element("Device");
		// e.setAttribute("Type", "19");
		// e.setAttribute("StandardNumber", "1110100000001011");
		// e.setAttribute("RecTime", sdf.format(new
		// Date(System.currentTimeMillis() - i * 100000)));
		// e.setAttribute("RecPeriod", "120");
		// e.setAttribute("WorkState", RandomUtils.nextInt(2) + "");
		// e.setAttribute("Status", "0");
		// e.setAttribute("CommStatus", "0");
		// root.addContent(e);
		// }
		// for (int i = 0; i < 100; i++) {
		// Element e = new Element("Device");
		// e.setAttribute("Type", "19");
		// e.setAttribute("StandardNumber", "1110100000001100");
		// e.setAttribute("RecTime", sdf.format(new
		// Date(System.currentTimeMillis() - i * 100000)));
		// e.setAttribute("RecPeriod", "120");
		// e.setAttribute("WorkState", RandomUtils.nextInt(2) + "");
		// e.setAttribute("Status", "0");
		// e.setAttribute("CommStatus", "0");
		// root.addContent(e);
		// }
		// for (int i = 0; i < 100; i++) {
		// Element e = new Element("Device");
		// e.setAttribute("Type", "19");
		// e.setAttribute("StandardNumber", "1110100000001101");
		// e.setAttribute("RecTime", sdf.format(new
		// Date(System.currentTimeMillis() - i * 100000)));
		// e.setAttribute("RecPeriod", "120");
		// e.setAttribute("WorkState", RandomUtils.nextInt(2) + "");
		// e.setAttribute("Status", "0");
		// e.setAttribute("CommStatus", "0");
		// root.addContent(e);
		// }
		// for (int i = 0; i < 100; i++) {
		// Element e = new Element("Device");
		// e.setAttribute("Type", "19");
		// e.setAttribute("StandardNumber", "1110100000001110");
		// e.setAttribute("RecTime", sdf.format(new
		// Date(System.currentTimeMillis() - i * 100000)));
		// e.setAttribute("RecPeriod", "120");
		// e.setAttribute("WorkState", RandomUtils.nextInt(2) + "");
		// e.setAttribute("Status", "0");
		// e.setAttribute("CommStatus", "0");
		// root.addContent(e);
		// }
		// for (int i = 0; i < 100; i++) {
		// Element e = new Element("Device");
		// e.setAttribute("Type", "19");
		// e.setAttribute("StandardNumber", "1110100000001111");
		// e.setAttribute("RecTime", sdf.format(new
		// Date(System.currentTimeMillis() - i * 100000)));
		// e.setAttribute("RecPeriod", "120");
		// e.setAttribute("WorkState", RandomUtils.nextInt(2) + "");
		// e.setAttribute("Status", "0");
		// e.setAttribute("CommStatus", "0");
		// root.addContent(e);
		// }
		// for (int i = 0; i < 100; i++) {
		// Element e = new Element("Device");
		// e.setAttribute("Type", "19");
		// e.setAttribute("StandardNumber", "1110100000010000");
		// e.setAttribute("RecTime", sdf.format(new
		// Date(System.currentTimeMillis() - i * 100000)));
		// e.setAttribute("RecPeriod", "120");
		// e.setAttribute("WorkState", RandomUtils.nextInt(2) + "");
		// e.setAttribute("Status", "0");
		// e.setAttribute("CommStatus", "0");
		// root.addContent(e);
		// }
		// for (int i = 0; i < 100; i++) {
		// Element e = new Element("Device");
		// e.setAttribute("Type", "19");
		// e.setAttribute("StandardNumber", "1110100000010001");
		// e.setAttribute("RecTime", sdf.format(new
		// Date(System.currentTimeMillis() - i * 100000)));
		// e.setAttribute("RecPeriod", "120");
		// e.setAttribute("WorkState", RandomUtils.nextInt(2) + "");
		// e.setAttribute("Status", "0");
		// e.setAttribute("CommStatus", "0");
		// root.addContent(e);
		// }
		// for (int i = 0; i < 100; i++) {
		// Element e = new Element("Device");
		// e.setAttribute("Type", "19");
		// e.setAttribute("StandardNumber", "1110100000010010");
		// e.setAttribute("RecTime", sdf.format(new
		// Date(System.currentTimeMillis() - i * 100000)));
		// e.setAttribute("RecPeriod", "120");
		// e.setAttribute("WorkState", RandomUtils.nextInt(2) + "");
		// e.setAttribute("Status", "0");
		// e.setAttribute("CommStatus", "0");
		// root.addContent(e);
		// }
		// for (int i = 0; i < 100; i++) {
		// Element e = new Element("Device");
		// e.setAttribute("Type", "19");
		// e.setAttribute("StandardNumber", "1110100000010011");
		// e.setAttribute("RecTime", sdf.format(new
		// Date(System.currentTimeMillis() - i * 100000)));
		// e.setAttribute("RecPeriod", "120");
		// e.setAttribute("WorkState", RandomUtils.nextInt(2) + "");
		// e.setAttribute("Status", "0");
		// e.setAttribute("CommStatus", "0");
		// root.addContent(e);
		// }
		// for (int i = 0; i < 100; i++) {
		// Element e = new Element("Device");
		// e.setAttribute("Type", "19");
		// e.setAttribute("StandardNumber", "1110100000010100");
		// e.setAttribute("RecTime", sdf.format(new
		// Date(System.currentTimeMillis() - i * 100000)));
		// e.setAttribute("RecPeriod", "120");
		// e.setAttribute("WorkState", RandomUtils.nextInt(2) + "");
		// e.setAttribute("Status", "0");
		// e.setAttribute("CommStatus", "0");
		// root.addContent(e);
		// }
		// // PB
		// for (int i = 0; i < 100; i++) {
		// Element e = new Element("Device");
		// e.setAttribute("Type", "24");
		// e.setAttribute("StandardNumber", "1010100000000001");
		// e.setAttribute("RecTime", sdf.format(new
		// Date(System.currentTimeMillis() - i * 100000)));
		// e.setAttribute("RecPeriod", "120");
		// e.setAttribute("WorkState", "0");
		// e.setAttribute("Status", "0");
		// e.setAttribute("CommStatus", "0");
		// root.addContent(e);
		// }
		// CMS
		// for (int i = 0; i < 5; i++) {
		// Element e = new Element("Device");
		// e.setAttribute("Type", "17");
		// e.setAttribute("StandardNum", "70");
		// e.setAttribute("RecTime", sdf.format(new Date(System
		// .currentTimeMillis() - i * 100000)));
		// e.setAttribute("RecPeriod", "120");
		// e.setAttribute("DispText", "注意安全");
		// e.setAttribute("DispTime", "600");
		// e.setAttribute("Color", "00ff00");
		// e.setAttribute("Size", "2");
		// e.setAttribute("Font", "1");
		// e.setAttribute("Status", "0");
		// e.setAttribute("CommStatus", "0");
		// root.addContent(e);
		// }
		// for (int i = 0; i < 5; i++) {
		// Element e = new Element("Device");
		// e.setAttribute("Type", "17");
		// e.setAttribute("StandardNum", "69");
		// e.setAttribute("RecTime", sdf.format(new Date(System
		// .currentTimeMillis() - i * 100000)));
		// e.setAttribute("RecPeriod", "120");
		// e.setAttribute("DispText", "雨天路滑");
		// e.setAttribute("DispTime", "600");
		// e.setAttribute("Color", "00ff00");
		// e.setAttribute("Size", "2");
		// e.setAttribute("Font", "1");
		// e.setAttribute("Status", "0");
		// e.setAttribute("CommStatus", "0");
		// root.addContent(e);
		// }
		// for (int i = 0; i < 5; i++) {
		// Element e = new Element("Device");
		// e.setAttribute("Type", "17");
		// e.setAttribute("StandardNum", "68");
		// e.setAttribute("RecTime", sdf.format(new Date(System
		// .currentTimeMillis() - i * 100000)));
		// e.setAttribute("RecPeriod", "120");
		// e.setAttribute("DispText", "安全驾驶");
		// e.setAttribute("DispTime", "600");
		// e.setAttribute("Color", "00ff00");
		// e.setAttribute("Size", "2");
		// e.setAttribute("Font", "1");
		// e.setAttribute("Status", "0");
		// e.setAttribute("CommStatus", "0");
		// root.addContent(e);
		// }
		// for (int i = 0; i < 5; i++) {
		// Element e = new Element("Device");
		// e.setAttribute("Type", "17");
		// e.setAttribute("StandardNum", "66");
		// e.setAttribute("RecTime", sdf.format(new Date(System
		// .currentTimeMillis() - i * 100000)));
		// e.setAttribute("RecPeriod", "120");
		// e.setAttribute("DispText", "保持车距");
		// e.setAttribute("DispTime", "600");
		// e.setAttribute("Color", "ff0000");
		// e.setAttribute("Size", "2");
		// e.setAttribute("Font", "1");
		// e.setAttribute("Status", "0");
		// e.setAttribute("CommStatus", "0");
		// root.addContent(e);
		// }

		for (int i = 0; i < 10; i++) {
			Element e = new Element("Device");
			e.setAttribute("Type", "35");
			e.setAttribute("StandardNumber", "66322" + i);
			e.setAttribute("RecTime", sdf.format(new Date(System
					.currentTimeMillis() - i * 100000)));
			e.setAttribute("Latitude", "120");
			e.setAttribute("Longitude", "-33");
			e.setAttribute("Bearing", "211");
			e.setAttribute("Speed", "87.5");
			e.setAttribute("Altitude", "2000");
			e.setAttribute("MaxSpeed", "120");
			e.setAttribute("MinSpeed", "80");
			e.setAttribute("Status", "0");
			e.setAttribute("CommStatus", "0");
			root.addContent(e);
		}

		String body = ElementUtil.doc2String(doc);

		RequestEntity entity = new StringRequestEntity(body, "application/xml",
				"utf8");
		method.setRequestEntity(entity);
		long begin = System.currentTimeMillis();
		client.executeMethod(method);

		System.out.println(new String(method.getResponseBody(), "utf8"));
		System.out.println(System.currentTimeMillis() - begin);

		method.releaseConnection();
	}

	public static void listDeviceInfo() throws Exception {
		HttpClient client = new HttpClient(new HttpClientParams(),
				new SimpleHttpConnectionManager(true));
		PostMethod method = new PostMethod(
				"http://192.168.1.104:8080/cms/list_device_info.xml");

		String body = "<Request Method=\"List_Device_Info\" Cmd=\"1203\">";
		body += "<SessionId>10050000000000000000</SessionId>";
		body += "<DeviceList>";
		body += "<Device StandardNumber=\"JC_PT_LS03_Z\" Type=\"26\" />";
		body += "<Device StandardNumber=\"JC_PT_LS04_Y\" Type=\"26\" />";
		body += "<Device StandardNumber=\"JC_PT_TL02\" Type=\"25\" />";
		body += "<Device StandardNumber=\"JC_PT_TL03\" Type=\"25\" />";
		// body +=
		// "<Device StandardNumber=\"100011101011100011100100\" Type=\"10\" />";
		// body +=
		// "<Device StandardNumber=\"101111100000111101010101\" Type=\"10\" />";
		// body +=
		// "<Device StandardNumber=\"110101010111100001110010\" Type=\"10\" />";
		// body +=
		// "<Device StandardNumber=\"111011000111110010101101\" Type=\"10\" />";
		body += "</DeviceList></Request>";
		RequestEntity entity = new StringRequestEntity(body, "application/xml",
				"utf8");
		method.setRequestEntity(entity);
		long begin = System.currentTimeMillis();
		client.executeMethod(method);

		System.out.println(new String(method.getResponseBody(), "utf8"));
		System.out.println(System.currentTimeMillis() - begin);

		method.releaseConnection();
	}

	public static void userLogoff(String sessionId) throws Exception {
		HttpClient client = new HttpClient(new HttpClientParams(),
				new SimpleHttpConnectionManager(true));
		PostMethod method = new PostMethod(
				"http://222.242.184.99:8181/cms/user_logoff.xml");

		String body = "sessionId=" + sessionId;

		RequestEntity entity = new StringRequestEntity(body,
				"application/x-www-form-urlencoded", "utf8");
		method.setRequestEntity(entity);
		long begin = System.currentTimeMillis();
		client.executeMethod(method);

		System.out.println(new String(method.getResponseBody(), "utf8"));
		System.out.println(System.currentTimeMillis() - begin);

		method.releaseConnection();
	}

	public static void statVD() throws Exception {
		HttpClient client = new HttpClient(new HttpClientParams(),
				new SimpleHttpConnectionManager(true));
		PostMethod method = new PostMethod(
				"http://192.168.1.2:8080/cms/stat_vd.xml");

		String body = "sessionId=10050000000000000000&beginTime=1386667091951&endTime=1396667091951&scope=hour&start=0&limit=10";

		RequestEntity entity = new StringRequestEntity(body,
				"application/x-www-form-urlencoded", "utf8");
		method.setRequestEntity(entity);
		long begin = System.currentTimeMillis();
		client.executeMethod(method);

		System.out.println(new String(method.getResponseBody(), "utf8"));
		System.out.println(System.currentTimeMillis() - begin);

		method.releaseConnection();
	}

	public static void statDeviceStatus() throws Exception {
		HttpClient client = new HttpClient(new HttpClientParams(),
				new SimpleHttpConnectionManager(true));
		PostMethod method = new PostMethod(
				"http://192.168.1.2:8080/cms/stat_device_status.xml");

		String body = "sessionId=10050000000000000000&organSN=&deviceType=&deviceName=&beginTime=1388003200000&endTime=1390715377637&start=0&limit=50";

		RequestEntity entity = new StringRequestEntity(body,
				"application/x-www-form-urlencoded", "utf8");
		method.setRequestEntity(entity);
		long begin = System.currentTimeMillis();
		client.executeMethod(method);

		System.out.println(new String(method.getResponseBody(), "utf8"));
		System.out.println(System.currentTimeMillis() - begin);

		method.releaseConnection();
	}

	public static void vdDataReport() throws Exception {
		HttpClient client = new HttpClient(new HttpClientParams(),
				new SimpleHttpConnectionManager(true));
		PostMethod method = new PostMethod(
				"http://192.168.1.104:8080/cms/vd_data_report.xml");

		String body = "<Report>";
		body += "<Header>";
		body += "<SendOrgan>12345678901234567890</SendOrgan>";
		body += "</Header>";
		body += "<Body>";
		body += "<Row>";
		body += "<VDID>0100000000000001</VDID>";
		body += "<RecTime>20140117134500</RecTime>";
		body += "<UupFluxB>50</UupFluxB>";
		body += "<UupFluxS>40</UupFluxS>";
		body += "<UupFlux>90</UupFlux>";
		body += "<DwFluxB>40</DwFluxB>";
		body += "<DwFluxS>30</DwFluxS>";
		body += "<DwFlux>70</DwFlux>";
		body += "<UpSpeed>90</UpSpeed>";
		body += "<DwSpeed>85</DwSpeed>";
		body += "<UpOccup>70.00</UpOccup>";
		body += "<DwOccdown>50.00</DwOccdown>";
		body += "<LaneNum>4</LaneNum>";
		body += "<Status>0</Status>";
		body += "<CommStatus>0</CommStatus>";
		body += "<Reserve></Reserve>";
		body += "</Row>";
		body += "</Body>";
		body += "</Report>";
		RequestEntity entity = new StringRequestEntity(body, "application/xml",
				"utf8");
		method.setRequestEntity(entity);
		long begin = System.currentTimeMillis();
		client.executeMethod(method);

		System.out.println(new String(method.getResponseBody(), "utf8"));
		System.out.println(System.currentTimeMillis() - begin);

		method.releaseConnection();
	}

	public static void getPlatformHardInfo() throws Exception {
		HttpClient client = new HttpClient(new HttpClientParams(),
				new SimpleHttpConnectionManager(true));
		PostMethod method = new PostMethod(
				"http://192.168.1.2:8080/cms/get_platform_hardware.json");

		String body = "sessionId=10050000000000000000";

		RequestEntity entity = new StringRequestEntity(body,
				"application/x-www-form-urlencoded", "utf8");
		method.setRequestEntity(entity);
		long begin = System.currentTimeMillis();
		client.executeMethod(method);

		// System.out.println(new String(method.getResponseBody(), "utf8"));
		// System.out.println(System.currentTimeMillis() - begin);
		net.sf.json.JSONObject jo = net.sf.json.JSONObject
				.fromObject(new String(method.getResponseBody(), "utf8"));
		String info = (String) jo.get("info");
		System.out.println(info);

		method.releaseConnection();
	}

	public static void listOnlineUser() throws Exception {
		HttpClient client = new HttpClient(new HttpClientParams(),
				new SimpleHttpConnectionManager(true));
		PostMethod method = new PostMethod(
				"http://222.242.184.99:8181/cms/list_online_user_by_user_id.json");

		String body = "sessionId=10050000000000000470&userId=10020000000000000000";

		RequestEntity entity = new StringRequestEntity(body,
				"application/x-www-form-urlencoded", "utf8");
		method.setRequestEntity(entity);
		long begin = System.currentTimeMillis();
		client.executeMethod(method);

		System.out.println(new String(method.getResponseBody(), "utf8"));
		System.out.println(System.currentTimeMillis() - begin);

		method.releaseConnection();
	}

	public static void getWfs() throws Exception {
		HttpClient client = new HttpClient(new HttpClientParams(),
				new SimpleHttpConnectionManager(true));
		PostMethod method = new PostMethod(
				"http://192.168.1.2:8080/geoserver/wfs");

		String body = "<wfs:GetFeature service=\"WFS\" version=\"1.1.0\" outputFormat=\"GML2\"";
		// body += "  xmlns:topp=\"http://www.openplans.org/topp\"";
		body += "  xmlns:wfs=\"http://www.opengis.net/wfs\"";
		body += "  xmlns:ogc=\"http://www.opengis.net/ogc\"";
		body += "  xmlns:gml=\"http://www.opengis.net/gml\"";
		body += "  xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"";
		body += "  xsi:schemaLocation=\"http://www.opengis.net/wfs http://schemas.opengis.net/wfs/1.1.0/wfs.xsd\">";
		// body +=
		// "<wfs:Query typeName=\"hn_postgis:db_luzhen_point\" srsName=\"EPSG:900913\">";
		// body +=
		// "<wfs:Query typeName=\"hn_postgis:db_motorway_line\" srsName=\"EPSG:900913\">";
		// body += "<ogc:Filter>";
		// body += "<ogc:FeatureId fid=\"5\"/>";
		// body += "</ogc:Filter>";

		// body += "<ogc:Filter>";
		// body += "<ogc:And>";
		// body += "  <ogc:BBOX>";
		// body += "    <ogc:PropertyName>geom</ogc:PropertyName>";
		// body += "    <gml:Envelope srsName=\"EPSG:4326\">";
		// body +=
		// "       <gml:lowerCorner>112.86004 26.47280</gml:lowerCorner>";
		// body +=
		// "       <gml:upperCorner>112.07195 27.96855</gml:upperCorner>";
		// body += "    </gml:Envelope>";
		// body += "  </ogc:BBOX>";
		// body += "  <ogc:PropertyIsEqualTo>";
		// body += "        <ogc:PropertyName>NAME</ogc:PropertyName>";
		// body += "		 <ogc:Literal>平和堂大药房</ogc:Literal>";
		// body += "  </ogc:PropertyIsEqualTo>";
		// body += "</ogc:And>";

		// body += "   <ogc:DWithin>";
		// body += "     <ogc:PropertyName>geom</ogc:PropertyName>";
		// body += "     <gml:Point srsName=\"EPSG:900913\">";
		// body +=
		// "       <gml:coordinates>12576143.77061,3265722.56801</gml:coordinates>";
		// body += "     </gml:Point>";
		// body += "     <ogc:Distance unit=\"meter\">1000</ogc:Distance>";
		// body += "   </ogc:DWithin>";
		// body += "</ogc:Filter>";

		body += "<wfs:Query typeName=\"hn_postgis:db_citybound_region\" srsName=\"EPSG:900913\">";
		body += " <ogc:Filter>";
		body += " <ogc:PropertyIsEqualTo>";
		body += "    <ogc:PropertyName>citycode</ogc:PropertyName>";
		body += "    <ogc:Literal>433100</ogc:Literal>";
		body += " </ogc:PropertyIsEqualTo>";
		body += "</ogc:Filter>";
		body += "</wfs:Query>";
		body += "</wfs:GetFeature>";

		System.out.println(body);

		RequestEntity entity = new StringRequestEntity(body, "application/xml",
				"utf8");
		method.setRequestEntity(entity);
		long begin = System.currentTimeMillis();
		client.executeMethod(method);

		File f = new File("D:/response.xml");
		FileOutputStream out = new FileOutputStream(f);
		InputStream in = method.getResponseBodyAsStream();
		byte[] buffer = new byte[4096];
		int temp = 0;
		while ((temp = in.read(buffer)) > 0) {
			out.write(buffer, 0, temp);
		}
		out.flush();
		out.close();
		in.close();

		method.releaseConnection();
	}

	public static void saveEventScheme() throws Exception {
		HttpClient client = new HttpClient(new HttpClientParams(),
				new SimpleHttpConnectionManager(true));
		PostMethod method = new PostMethod(
				"http://192.168.1.104:8080/cms/save_event_scheme.xml");

		String body = "<Request Method=\"Save_Event_Scheme\" Cmd=\"1245\">";
		body += "<SessionId>10050000000000000000</SessionId>";
		body += "<Scheme Id=\"\" Name=\"XX交通事故预案\" EventId=\"1\">";
		body += "   <Step Seq=\"1\" Type=\"Police\">";
		body += "      <Item Id=\"402881ef45a6dfbc0145a6dfd12e0011\" TargetId=\"402881ef45a6dfbc0145a6dfd12e0007\" TargetName=\"XX交警一大队\" Telephone=\"02888888888\" LinkMan=\"黄尚\" RequestContent=\"XX高速公路K200+100上行方向发生交通事故\" ResponseContent=\"警员王明，编号9527，电话13900002399已前往事发地点\" BeginTime=\"1378565420145\" ArriveTime=\"1378565420145\" EndTime=\"1378565420145\" ActionStatus=\"2\" Note=\"\"/>";
		body += "    </Step>";
		body += "    <Step Seq=\"2\" Type=\"Hospital\">";
		body += "      <Item Id=\"402881ef45a6dfbc0145a6dfd12e0012\" TargetId=\"402881ef45a6dfbc0145a6dfd12e0008\" TargetName=\"XX第一人民医院\" Telephone=\"02888888888\" LinkMan=\"何旭\" RequestContent=\"XX高速公路K200+100上行方向发生交通事故，3人受伤，需两辆救护车协助救援\" ResponseContent=\"救护车川A01234，川A01235已出发前往事发地点\" BeginTime=\"1378565420145\" ArriveTime=\"1378565420145\" EndTime=\"\" ActionStatus=\"1\" Note=\"\"/>";
		body += "    </Step>";
		body += "    <Step Seq=\"3\" Type=\"Cms\">";
		body += "      <Item Id=\"402881ef45a6dfbc0145a6dfd12e0012\" TargetId=\"402881ef45a6dfbc0145a6dfd12e0010\" TargetName=\"CMSK198+000\" Content=\"前方2公里发生交通事故\" Color=\"00ffff\" Font=\"1\" Size=\"3\" Space=\"3\" Duration=\"100\" BeginTime=\"1378565420145\" ActionStatus=\"2\" Note=\"\"/>";
		body += "    </Step>";
		body += "    <Step Seq=\"4\" Type=\"Resource\">";
		body += "      <Item Id=\"402881ef45a6dfbc0145a6dfd12e0012\" TargetId=\"402881ef45a6dfbc0145a6dfd12e0011\" TargetName=\"反光锥筒\" Number=\"100\" BeginTime=\"1378565420145\" Status=\"1\" ActionStatus=\"2\"/>";
		body += "    </Step>";
		body += "    <Step Seq=\"5\" Type=\"RoadControl\">";
		body += "      <Item Id=\"402881ef45a6dfbc0145a6dfd12e0012\" TargetId=\"402881ef45a6dfbc0145a6dfd12e0011\" TargetName=\"马家河收费站\" TargetType=\"Tollgate\" RequestContent=\"减少收费站入口数量或降低车辆通过率\" BeginTime=\"1378565420145\" Status=\"1\" ActionStatus=\"2\"/>";
		body += "    </Step>";
		body += "</Scheme>";
		body += "</Request>";

		RequestEntity entity = new StringRequestEntity(body, "application/xml",
				"utf8");
		method.setRequestEntity(entity);
		long begin = System.currentTimeMillis();
		client.executeMethod(method);

		System.out.println(new String(method.getResponseBody(), "utf8"));
		System.out.println(System.currentTimeMillis() - begin);

		method.releaseConnection();
	}

	public static void searchResource() throws Exception {
		HttpClient client = new HttpClient(new HttpClientParams(),
				new SimpleHttpConnectionManager(true));
		PostMethod method = new PostMethod(
				"http://192.168.1.104:8080/cms/search_resource.xml");

		String body = "sessionId=10050000000000000000&organId=10010000000000000000&beginStake=K100+30&endStake=K102+000&range=10000&navigation=1&type=17";

		RequestEntity entity = new StringRequestEntity(body,
				"application/json", "utf8");
		method.setRequestEntity(entity);
		long begin = System.currentTimeMillis();
		client.executeMethod(method);

		System.out.println(new String(method.getResponseBody(), "utf8"));
		System.out.println(System.currentTimeMillis() - begin);

		method.releaseConnection();
	}

	public static void savePlaylist() throws Exception {
		HttpClient client = new HttpClient(new HttpClientParams(),
				new SimpleHttpConnectionManager(true));
		PostMethod method = new PostMethod(
				"http://192.168.1.104:8080/cms/save_cms_playlist.xml");

		String body = "<Request Method=\"Save_Cms_Playlist\" Cmd=\"1251\">";
		body += "<SessionId>10050000000000000000</SessionId>";
		body += "<Folder>1</Folder>";
		body += "<SubType>1</SubType>";
		body += "<Playlist Id=\"402881e8479acfdc01479acfe9560002\" Name=\"下雨天方案\">";
		body += "  <Item Id=\"\" Content=\"注意安全\" Color=\"00ffff\" Font=\"1\" Size=\"3\" Space=\"3\" Duration=\"100\" X=\"0\" Y=\"0\" />";
		body += "  <Item Id=\"\" Content=\"雨天路滑\" Color=\"7fff7f\" Font=\"1\" Size=\"3\" Space=\"3\" Duration=\"200\" X=\"0\" Y=\"0\" />";
		body += "</Playlist>";
		body += "</Request>";

		RequestEntity entity = new StringRequestEntity(body, "application/xml",
				"utf8");
		method.setRequestEntity(entity);
		long begin = System.currentTimeMillis();
		client.executeMethod(method);

		System.out.println(new String(method.getResponseBody(), "utf8"));
		System.out.println(System.currentTimeMillis() - begin);

		method.releaseConnection();
	}

	public static void listPlaylist() throws Exception {
		HttpClient client = new HttpClient(new HttpClientParams(),
				new SimpleHttpConnectionManager(true));
		PostMethod method = new PostMethod(
				"http://192.168.1.104:8080/cms/list_cms_playlist.xml");

		String body = "sessionId=10050000000000000000&folderId=1";

		RequestEntity entity = new StringRequestEntity(body,
				"application/x-www-form-urlencoded", "utf8");
		method.setRequestEntity(entity);
		long begin = System.currentTimeMillis();
		client.executeMethod(method);

		System.out.println(new String(method.getResponseBody(), "utf8"));
		System.out.println(System.currentTimeMillis() - begin);

		method.releaseConnection();
	}

	public static void listPlaylistFolder() throws Exception {
		HttpClient client = new HttpClient(new HttpClientParams(),
				new SimpleHttpConnectionManager(true));
		PostMethod method = new PostMethod(
				"http://192.168.1.104:8080/cms/list_playlist_folder.xml");

		String body = "sessionId=10050000000000000000&subType=1";

		RequestEntity entity = new StringRequestEntity(body,
				"application/x-www-form-urlencoded", "utf8");
		method.setRequestEntity(entity);
		long begin = System.currentTimeMillis();
		client.executeMethod(method);

		System.out.println(new String(method.getResponseBody(), "utf8"));
		System.out.println(System.currentTimeMillis() - begin);

		method.releaseConnection();
	}

	public static void savePlaylistFolder() throws Exception {
		HttpClient client = new HttpClient(new HttpClientParams(),
				new SimpleHttpConnectionManager(true));
		PostMethod method = new PostMethod(
				"http://192.168.1.2:8080/cms/create_playlist_folder.xml");

		String body = "sessionId=10050000000000000000&subType=1&folderName=常用";

		RequestEntity entity = new StringRequestEntity(body,
				"application/x-www-form-urlencoded", "utf8");
		method.setRequestEntity(entity);
		long begin = System.currentTimeMillis();
		client.executeMethod(method);

		System.out.println(new String(method.getResponseBody(), "utf8"));
		System.out.println(System.currentTimeMillis() - begin);

		method.releaseConnection();
	}

	public static void saveMarkers() throws Exception {
		HttpClient client = new HttpClient(new HttpClientParams(),
				new SimpleHttpConnectionManager(true));
		PostMethod method = new PostMethod(
				"http://192.168.1.2:8080/cms/save_markers.xml");

		String body = "<Request Method=\"Save_Markers\" Cmd=\"1204\">";
		body += "<SessionId>ff80808147f17e7c0147f1c0cf290015</SessionId>";
		body += "<Markers>";
		body += "  <Markser StandardNumber=\"25100000013000000117\" Type=\"121\" Longitude=\"12172077.464829\" Latitude=\"3318309.9957039\"/>";
		body += "</Markers>";
		body += "</Request>";

		RequestEntity entity = new StringRequestEntity(body, "application/xml",
				"utf8");
		method.setRequestEntity(entity);
		long begin = System.currentTimeMillis();
		client.executeMethod(method);

		System.out.println(new String(method.getResponseBody(), "utf8"));
		System.out.println(System.currentTimeMillis() - begin);

		method.releaseConnection();
	}

	public static void generateScheme() throws Exception {
		HttpClient client = new HttpClient(new HttpClientParams(),
				new SimpleHttpConnectionManager(true));
		PostMethod method = new PostMethod(
				"http://192.168.1.104:8080/cms/generate_scheme.xml");

		String body = "sessionId=10050000000000000000&eventId=ff808081476c021d01476c9129c8003e";

		RequestEntity entity = new StringRequestEntity(body,
				"application/x-www-form-urlencoded", "utf8");
		method.setRequestEntity(entity);
		long begin = System.currentTimeMillis();
		client.executeMethod(method);

		System.out.println(new String(method.getResponseBody(), "utf8"));
		System.out.println(System.currentTimeMillis() - begin);

		method.releaseConnection();
	}

	public static void listVdFluxStatus() throws Exception {
		HttpClient client = new HttpClient(new HttpClientParams(),
				new SimpleHttpConnectionManager(true));
		PostMethod method = new PostMethod(
				"http://192.168.1.104:8080/cms/listVdFluxStatus.json");

		// String body = "";
		//
		// RequestEntity entity = new StringRequestEntity(body,
		// "application/x-www-form-urlencoded",
		// "utf8");
		// method.setRequestEntity(entity);
		long begin = System.currentTimeMillis();
		client.executeMethod(method);

		System.out.println(new String(method.getResponseBody(), "utf8"));
		System.out.println(System.currentTimeMillis() - begin);

		method.releaseConnection();
	}

	public static void listEvent() throws Exception {
		HttpClient client = new HttpClient(new HttpClientParams(),
				new SimpleHttpConnectionManager(true));
		PostMethod method = new PostMethod(
				"http://192.168.1.5:8080/cms/list_event.xml");

		String body = "sessionId=402881854c55681d014c5569c07b0002";

		RequestEntity entity = new StringRequestEntity(body,
				"application/x-www-form-urlencoded", "utf8");
		method.setRequestEntity(entity);
		long begin = System.currentTimeMillis();
		client.executeMethod(method);

		// System.out.println(new String(method.getResponseBody(), "utf8"));
		System.out.println(System.currentTimeMillis() - begin);

		method.releaseConnection();
	}

	public static void catalog28181() throws Exception {
		HttpClient client = new HttpClient(new HttpClientParams(),
				new SimpleHttpConnectionManager(true));
		PostMethod method = new PostMethod(
				"http://192.168.1.104:8080/cms/catalog_28181.xml");

		String body = "<Request Method=\"Save_Markers\" Cmd=\"1204\">";
		body += "<SessionId>10050000000000000000</SessionId>";
		body += "<DeviceID></DeviceID>";
		body += "<Cascade>0</Cascade>";
		body += "<SN>3</SN>";
		body += "</Request>";

		RequestEntity entity = new StringRequestEntity(body, "application/xml",
				"utf8");
		method.setRequestEntity(entity);
		long begin = System.currentTimeMillis();
		client.executeMethod(method);

		System.out.println(new String(method.getResponseBody(), "gbk"));
		System.out.println(System.currentTimeMillis() - begin);

		method.releaseConnection();
	}

	public static void catalog28181ByUser() throws Exception {
		HttpClient client = new HttpClient(new HttpClientParams(),
				new SimpleHttpConnectionManager(true));
		PostMethod method = new PostMethod(
				"http://192.168.1.104:8080/cms/catalog_28181_by_user.xml");

		String body = "<Request Method=\"Save_Markers\" Cmd=\"1204\">";
		body += "<User>251000000090000145</User>";
		body += "</Request>";

		RequestEntity entity = new StringRequestEntity(body, "application/xml",
				"utf8");
		method.setRequestEntity(entity);
		long begin = System.currentTimeMillis();
		client.executeMethod(method);

		System.out.println(new String(method.getResponseBody(), "gbk"));
		System.out.println(System.currentTimeMillis() - begin);

		method.releaseConnection();
	}

	public static void updateCatalog28181() throws Exception {
		HttpClient client = new HttpClient(new HttpClientParams(),
				new SimpleHttpConnectionManager(true));
		PostMethod method = new PostMethod(
				"http://192.168.1.104:8080/cms/update_catalog_28181.xml");

		String body = "<Request Method=\"Save_Markers\" Cmd=\"1204\">";
		body += "<SessionId>40289fe74c7303a9014c73053f3b0002</SessionId>";
		body += "<CmdType>Catalog</CmdType>";
		body += "<SN>3</SN>";
		body += "<DeviceID>12345678901234567890</DeviceID>";
		body += "<SumNum>7</SumNum>";
		body += "<DeviceList Num=\"7\">";
		body += "  <Item>";
		body += "    <DeviceID>11111111111111111111</DeviceID>";
		body += "    <Name>Camera-1</Name>";
		body += "    <Manufacturer>海康</Manufacturer>";
		body += "    <Model>HIK9000</Model>";
		body += "    <Owner>湘西管理处</Owner>";
		body += "    <CivilCode>510000</CivilCode>";
		body += "    <Block>234324.222</Block>";
		body += "    <Address>K9527+123</Address>";
		body += "    <Parental>0</Parental>";
		body += "    <ParentID>22222222222222222222</ParentID>";
		body += "    <SafetyWay>0</SafetyWay>";
		body += "    <RegisterWay>0</RegisterWay>";
		body += "    <CertNum></CertNum>";
		body += "    <Certifiable>0</Certifiable>";
		body += "    <ErrCode></ErrCode>";
		body += "    <EndTime></EndTime>";
		body += "    <Secrecy>0</Secrecy>";
		body += "    <IPAddress>192.168.1.1</IPAddress>";
		body += "    <Port>8000</Port>";
		body += "    <Password>12345</Password>";
		body += "    <Status>ON</Status>";
		body += "    <Longitude></Longitude>";
		body += "    <Latitude></Latitude>";
		body += "    <info>";
		body += "      <PTZType>1</PTZType>";
		body += "    </info>";
		body += "  </Item>";
		body += "  <Item>";
		body += "    <DeviceID>22222222222222222222</DeviceID>";
		body += "    <Name>湘西管理处</Name>";
		body += "    <CivilCode>511000</CivilCode>";
		body += "    <ParentID>12345678901234567890</ParentID>";
		body += "    <EndTime>2015-04-23 00:00:00</EndTime>";
		body += "  </Item>";
		body += "  <Item>";
		body += "    <DeviceID>11111111111111111112</DeviceID>";
		body += "    <Name>Camera-2</Name>";
		body += "    <Manufacturer>海康</Manufacturer>";
		body += "    <Model>HIK9000</Model>";
		body += "    <Owner>湘西管理处</Owner>";
		body += "    <CivilCode>510000</CivilCode>";
		body += "    <Block>234324.222</Block>";
		body += "    <Address>K9527+123</Address>";
		body += "    <Parental>0</Parental>";
		body += "    <ParentID>22222222222222222222</ParentID>";
		body += "    <SafetyWay>0</SafetyWay>";
		body += "    <RegisterWay>0</RegisterWay>";
		body += "    <CertNum></CertNum>";
		body += "    <Certifiable>0</Certifiable>";
		body += "    <ErrCode></ErrCode>";
		body += "    <EndTime></EndTime>";
		body += "    <Secrecy>0</Secrecy>";
		body += "    <IPAddress>192.168.1.2</IPAddress>";
		body += "    <Port>8000</Port>";
		body += "    <Password>12345</Password>";
		body += "    <Status>ON</Status>";
		body += "    <Longitude></Longitude>";
		body += "    <Latitude></Latitude>";
		body += "    <info>";
		body += "      <PTZType>1</PTZType>";
		body += "    </info>";
		body += "  </Item>";
		body += "  <Item>";
		body += "    <DeviceID>33333333333333333333</DeviceID>";
		body += "    <Name>长沙管理处</Name>";
		body += "    <CivilCode>511000</CivilCode>";
		body += "    <ParentID>12345678901234567890</ParentID>";
		body += "    <EndTime>2015-04-23 00:00:00</EndTime>";
		body += "  </Item>";
		body += "  <Item>";
		body += "    <DeviceID>11111111111111111113</DeviceID>";
		body += "    <Name>Camera-3</Name>";
		body += "    <Manufacturer>海康</Manufacturer>";
		body += "    <Model>HIK9000</Model>";
		body += "    <Owner>湘西管理处</Owner>";
		body += "    <CivilCode>510000</CivilCode>";
		body += "    <Block>234324.222</Block>";
		body += "    <Address>K9527+123</Address>";
		body += "    <Parental>0</Parental>";
		body += "    <ParentID>33333333333333333333</ParentID>";
		body += "    <SafetyWay>0</SafetyWay>";
		body += "    <RegisterWay>0</RegisterWay>";
		body += "    <CertNum></CertNum>";
		body += "    <Certifiable>0</Certifiable>";
		body += "    <ErrCode></ErrCode>";
		body += "    <EndTime></EndTime>";
		body += "    <Secrecy>0</Secrecy>";
		body += "    <IPAddress>192.168.1.3</IPAddress>";
		body += "    <Port>8000</Port>";
		body += "    <Password>12345</Password>";
		body += "    <Status>ON</Status>";
		body += "    <Longitude></Longitude>";
		body += "    <Latitude></Latitude>";
		body += "    <info>";
		body += "      <PTZType>2</PTZType>";
		body += "    </info>";
		body += "  </Item>";
		body += "  <Item>";
		body += "    <DeviceID>11111111111111111114</DeviceID>";
		body += "    <Name>Camera-4</Name>";
		body += "    <Manufacturer>海康</Manufacturer>";
		body += "    <Model>HIK9000</Model>";
		body += "    <Owner>湘西管理处</Owner>";
		body += "    <CivilCode>510000</CivilCode>";
		body += "    <Block>234324.222</Block>";
		body += "    <Address>K9527+123</Address>";
		body += "    <Parental>0</Parental>";
		body += "    <ParentID>44444444444444444444</ParentID>";
		body += "    <SafetyWay>0</SafetyWay>";
		body += "    <RegisterWay>0</RegisterWay>";
		body += "    <CertNum></CertNum>";
		body += "    <Certifiable>0</Certifiable>";
		body += "    <ErrCode></ErrCode>";
		body += "    <EndTime>2015-03-02 23:00:12</EndTime>";
		body += "    <Secrecy>0</Secrecy>";
		body += "    <IPAddress>192.168.1.4</IPAddress>";
		body += "    <Port>8000</Port>";
		body += "    <Password>12345</Password>";
		body += "    <Status>ON</Status>";
		body += "    <Longitude></Longitude>";
		body += "    <Latitude></Latitude>";
		body += "    <info>";
		body += "      <PTZType>2</PTZType>";
		body += "    </info>";
		body += "  </Item>";
		body += "  <Item>";
		body += "    <DeviceID>44444444444444444444</DeviceID>";
		body += "    <Name>长沙管理处</Name>";
		body += "    <CivilCode>511100</CivilCode>";
		body += "    <ParentID>33333333333333333333</ParentID>";
		body += "    <EndTime>2015-04-23 00:00:00</EndTime>";
		body += "  </Item>";
		body += "</DeviceList>";
		body += "</Request>";

		RequestEntity entity = new StringRequestEntity(body, "application/xml",
				"utf8");
		method.setRequestEntity(entity);
		long begin = System.currentTimeMillis();
		client.executeMethod(method);

		System.out.println(new String(method.getResponseBody(), "utf8"));
		System.out.println(System.currentTimeMillis() - begin);

		method.releaseConnection();
	}

	public static void batchSaveDeviceAlarm() throws Exception {
		HttpClient client = new HttpClient(new HttpClientParams(),
				new SimpleHttpConnectionManager(true));
		PostMethod method = new PostMethod(
				"http://192.168.1.104:8080/cms/batch_save_device_alarm.xml");

		String body = "<Request Method=\"Save_Markers\" Cmd=\"1204\">";
		body += "<SessionId>10050000000000000000</SessionId>";
		body += "<Record StandardNumber=\"25100000100003000005\" DeviceType=\"2\" AlarmType=\"13\" Reason=\"中文信息\" />";
		body += "<Record StandardNumber=\"25100000100003000008\" DeviceType=\"2\" AlarmType=\"13\" />";
		body += "<Record StandardNumber=\"251000000303000295\" DeviceType=\"2\" AlarmType=\"13\" />";
		body += "<Record StandardNumber=\"251000000303000303\" DeviceType=\"2\" AlarmType=\"13\" />";
		body += "</Request>";

		RequestEntity entity = new StringRequestEntity(body, "application/xml",
				"gbk");
		method.setRequestEntity(entity);
		long begin = System.currentTimeMillis();
		client.executeMethod(method);

		System.out.println(new String(method.getResponseBody(), "gbk"));
		System.out.println(System.currentTimeMillis() - begin);

		method.releaseConnection();
	}

	public static void topRealPlay() throws Exception {
		HttpClient client = new HttpClient(new HttpClientParams(),
				new SimpleHttpConnectionManager(true));
		PostMethod method = new PostMethod(
				"http://192.168.1.104:8080/cms/top_real_play.xml");

		String body = "sessionId=10050000000000000000";

		RequestEntity entity = new StringRequestEntity(body,
				"application/x-www-form-urlencoded", "utf8");
		method.setRequestEntity(entity);
		long begin = System.currentTimeMillis();
		client.executeMethod(method);

		System.out.println(new String(method.getResponseBody(), "utf8"));
		System.out.println(System.currentTimeMillis() - begin);

		method.releaseConnection();
	}

	public static void listDeviceStatus() throws Exception {
		HttpClient client = new HttpClient(new HttpClientParams(),
				new SimpleHttpConnectionManager(true));
		PostMethod method = new PostMethod(
				"http://192.168.1.104:8080/cms/list_device_status.xml");

		String body = "<Request Method=\"List_Device_Status\" Cmd=\"1017\">";
		body += "<Device StandardNumber=\"251100000003000103\" />";
		body += "<Device StandardNumber=\"251100000003000102\" />";
		body += "<Device StandardNumber=\"251100000103000104\" />";
		body += "<Device StandardNumber=\"251100000103000103\" />";
		body += "<Device StandardNumber=\"251100000103000105\" />";
		body += "</Request>";

		RequestEntity entity = new StringRequestEntity(body, "application/xml",
				"utf8");
		method.setRequestEntity(entity);
		long begin = System.currentTimeMillis();
		client.executeMethod(method);

		System.out.println(new String(method.getResponseBody(), "utf8"));
		System.out.println(System.currentTimeMillis() - begin);

		method.releaseConnection();
	}

	public static void addFavoriteDevice() throws Exception {
		HttpClient client = new HttpClient(new HttpClientParams(),
				new SimpleHttpConnectionManager(true));
		PostMethod method = new PostMethod(
				"http://192.168.1.104:8080/cms/add_favorite_device.xml");

		String body = "<Request Method=\"Add_Favorite_Device\" Cmd=\"1018\">";
		body += "<SessionId>10050000000000000000</SessionId>";
		body += "<Favorite Id=\"40289fe74d4c449e014d4c461d1b0004\" CameraId=\"10040000000000000028\" />";
		body += "</Request>";

		RequestEntity entity = new StringRequestEntity(body, "application/xml",
				"utf8");
		method.setRequestEntity(entity);
		long begin = System.currentTimeMillis();
		client.executeMethod(method);

		System.out.println(new String(method.getResponseBody(), "utf8"));
		System.out.println(System.currentTimeMillis() - begin);

		method.releaseConnection();
	}

	public static void deleteFavoriteDevice() throws Exception {
		HttpClient client = new HttpClient(new HttpClientParams(),
				new SimpleHttpConnectionManager(true));
		PostMethod method = new PostMethod(
				"http://192.168.1.104:8080/cms/delete_favorite_device.xml");

		String body = "<Request Method=\"Add_Favorite_Device\" Cmd=\"1018\">";
		body += "<SessionId>10050000000000000000</SessionId>";
		body += "<Favorite Id=\"40289fe74d4c449e014d4c461d1b0004\" CameraId=\"10040000000000000028\" />";
		body += "</Request>";

		RequestEntity entity = new StringRequestEntity(body, "application/xml",
				"utf8");
		method.setRequestEntity(entity);
		long begin = System.currentTimeMillis();
		client.executeMethod(method);

		System.out.println(new String(method.getResponseBody(), "utf8"));
		System.out.println(System.currentTimeMillis() - begin);

		method.releaseConnection();
	}

	public static void updateOrgan() throws Exception {
		HttpClient client = new HttpClient(new HttpClientParams(),
				new SimpleHttpConnectionManager(true));
		PostMethod method = new PostMethod(
				"http://192.168.1.7:8080/cms/update_organ.json");
		String body = "sessionId=10050000000000000000&id=402883955089d3e001508d78184e0048&name=长潭高速&type=120";
		RequestEntity entity = new StringRequestEntity(URLEncoder.encode(body,
				"utf8"), "application/x-www-form-urlencoded", "utf8");
		method.setRequestEntity(entity);
		long begin = System.currentTimeMillis();
		client.executeMethod(method);

		System.out.println(new String(method.getResponseBody(), "utf8"));
		System.out.println(System.currentTimeMillis() - begin);

		method.releaseConnection();
	}

	public static void resourceUpload() throws Exception {
		HttpClient client = new HttpClient(new HttpClientParams(),
				new SimpleHttpConnectionManager(true));
		PostMethod method = new PostMethod(
				"http://192.168.1.7:8080/res/Resource_Upload.xml");

		Part image1 = new FilePart("Filedata", new File("D:/Koala.jpg"), null,
				"utf8");
		Part image2 = new FilePart("Filedata", new File("E:/界面设计/SGC界面修改（隧道设计切图）2014-11-4/切图/隧道.jpg"), null,
				"utf8");
		Part image3 = new FilePart("Filedata", new File("E:/智能应用/clyx.jpg"), null,
				"utf8");
		RequestEntity entity = new MultipartRequestEntity(new Part[] { image1,
				image2, image3 }, method.getParams());
		method.setRequestEntity(entity);

		client.executeMethod(method);

		System.out.println(new String(method.getResponseBody(), "utf8"));

		method.releaseConnection();
	}
	
	public static void resourceDelete() throws Exception {
		HttpClient client = new HttpClient(new HttpClientParams(),
				new SimpleHttpConnectionManager(true));
		DeleteMethod method = new DeleteMethod("http://127.0.0.1:8080/res/image/20151119/20151119113452063-1");

		client.executeMethod(method);

		System.out.println(new String(method.getResponseBody(), "utf8"));

		method.releaseConnection();
	}
	
	public static void userLogin() throws Exception {
		HttpClient client = new HttpClient(new HttpClientParams(),
				new SimpleHttpConnectionManager(true));
		PostMethod method = new PostMethod(
				"http://192.168.1.104:8080/sgc-interface/main");
		method.setRequestHeader("Method", "UserLogin");
		method.setRequestHeader("BusinessId", UUID.randomUUID().toString());
		String body = "loginName=znsx&password=e10adc3949ba59abbe56e057f20f883e&clientType=Sgc&ip=192.168.1.104";
		RequestEntity entity = new StringRequestEntity(body, "application/x-www-form-urlencoded", "utf8");
		method.setRequestEntity(entity);
		long begin = System.currentTimeMillis();
		client.executeMethod(method);

		System.out.println(new String(method.getResponseBody(), "utf8"));
		System.out.println(System.currentTimeMillis() - begin);

		method.releaseConnection();
	}
	
	public static void listOrganDeviceJson() throws Exception {
		HttpClient client = new HttpClient(new HttpClientParams(),
				new SimpleHttpConnectionManager(true));
		PostMethod method1 = new PostMethod(
				"http://192.168.1.104:8080/sgc-interface/main");
		method1.setRequestHeader("Method", "UserLogin");
		method1.setRequestHeader("BusinessId", UUID.randomUUID().toString());
		String body1 = "loginName=admin&password=e10adc3949ba59abbe56e057f20f883e&clientType=Sgc&ip=192.168.1.104";
		RequestEntity entity1 = new StringRequestEntity(body1, "application/x-www-form-urlencoded", "utf8");
		method1.setRequestEntity(entity1);
		client.executeMethod(method1);
		
		PostMethod method2 = new PostMethod(
				"http://192.168.1.104:8080/sgc-interface/main");
		method2.setRequestHeader("Method", "ListOrganDevice");
		method2.setRequestHeader("BusinessId", UUID.randomUUID().toString());
		String body2 = "";
		RequestEntity entity2 = new StringRequestEntity(body2, "application/x-www-form-urlencoded", "utf8");
		method2.setRequestEntity(entity2);
		long begin = System.currentTimeMillis();
		client.executeMethod(method2);

		System.out.println(new String(method2.getResponseBody(), "utf8"));
		System.out.println(System.currentTimeMillis() - begin);

//		method1.releaseConnection();
		method2.releaseConnection();
	}
	
	public static void listSvOrganDeviceJson() throws Exception {
		HttpClient client = new HttpClient(new HttpClientParams(),
				new SimpleHttpConnectionManager(true));
		PostMethod method1 = new PostMethod(
				"http://192.168.1.7:8080/sgc-interface/main");
		method1.setRequestHeader("Method", "UserLogin");
		method1.setRequestHeader("BusinessId", UUID.randomUUID().toString());
		String body1 = "loginName=admin&password=e10adc3949ba59abbe56e057f20f883e&clientType=Sgc&ip=192.168.1.104";
		RequestEntity entity1 = new StringRequestEntity(body1, "application/x-www-form-urlencoded", "utf8");
		method1.setRequestEntity(entity1);
		client.executeMethod(method1);
		
		PostMethod method2 = new PostMethod(
				"http://192.168.1.7:8080/sgc-interface/main");
		method2.setRequestHeader("Method", "ListSvOrganDevice");
		method2.setRequestHeader("BusinessId", UUID.randomUUID().toString());
		String body2 = "";
		RequestEntity entity2 = new StringRequestEntity(body2, "application/x-www-form-urlencoded", "utf8");
		method2.setRequestEntity(entity2);
		long begin = System.currentTimeMillis();
		client.executeMethod(method2);

		System.out.println(new String(method2.getResponseBody(), "utf8"));
		System.out.println(System.currentTimeMillis() - begin);

		method1.releaseConnection();
		method2.releaseConnection();
	}
	
	public static void encodeTest() throws Exception {
		HttpClient client = new HttpClient(new HttpClientParams(),
				new SimpleHttpConnectionManager(true));
		PostMethod method = new PostMethod(
				"http://192.168.1.104:8080/sgc-interface/main");
		method.setRequestHeader("Method", "UserLogin");
		method.setRequestHeader("BusinessId", UUID.randomUUID().toString());
		String body = "loginName=中文信息&sex=男&age=12&learn=语文&loginName=中文信息1&loginName=中文信息2&loginName=";
		RequestEntity entity = new StringRequestEntity(body, "application/x-www-form-urlencoded", "gbk");
		method.setRequestEntity(entity);
		long begin = System.currentTimeMillis();
		client.executeMethod(method);

		System.out.println(new String(method.getResponseBody(), "utf8"));
		System.out.println(System.currentTimeMillis() - begin);

		method.releaseConnection();
	}
	
	public static void sgcResourceUpload() throws Exception {
		HttpClient client = new HttpClient(new HttpClientParams(),
				new SimpleHttpConnectionManager(true));
		PostMethod method = new PostMethod(
				"http://192.168.1.111:8080/sgc-interface/main");
		method.setRequestHeader("Method", "FileUpload");
		method.setRequestHeader("BusinessId", UUID.randomUUID().toString());
		
		Part image3 = new Utf8FilePart("fileData", new File("F:\\界面\\界面\\隧道\\隧道-1.jpg"));
		RequestEntity entity = new MultipartRequestEntity(new Part[] { image3 }, method.getParams());
		method.setRequestEntity(entity);

		client.executeMethod(method);

		System.out.println(new String(method.getResponseBody(), "utf8"));

		method.releaseConnection();
	}
	
	public static void sgcResourceDelete() throws Exception {
		HttpClient client = new HttpClient(new HttpClientParams(),
				new SimpleHttpConnectionManager(true));
		PostMethod method = new PostMethod(
				"http://192.168.1.104:8080/sgc-interface/main");
		method.setRequestHeader("Method", "FileDelete");
		method.setRequestHeader("BusinessId", UUID.randomUUID().toString());
		String body = "id=221";
		RequestEntity entity = new StringRequestEntity(body, "application/x-www-form-urlencoded", "utf8");
		method.setRequestEntity(entity);
		client.executeMethod(method);

		System.out.println(new String(method.getResponseBody(), "utf8"));

		method.releaseConnection();
	}
	
	public static void listDeviceRealJson() throws Exception {
		HttpClient client = new HttpClient(new HttpClientParams(),
				new SimpleHttpConnectionManager(true));
		PostMethod method = new PostMethod(
				"http://192.168.1.7:8080/sgc-interface/main");
		method.setRequestHeader("Method", "ListDeviceReal");
		method.setRequestHeader("BusinessId", UUID.randomUUID().toString());
		String body = "ids=10004,100085,100033,100042";
		RequestEntity entity = new StringRequestEntity(body, "application/x-www-form-urlencoded", "utf8");
		method.setRequestEntity(entity);
		long begin = System.currentTimeMillis();
		client.executeMethod(method);

		System.out.println(new String(method.getResponseBody(), "utf8"));
		System.out.println(System.currentTimeMillis() - begin);

		method.releaseConnection();
	}
	
	public static void listCameraByStakeNumberJson() throws Exception {
		HttpClient client = new HttpClient(new HttpClientParams(),
				new SimpleHttpConnectionManager(true));
		PostMethod method1 = new PostMethod(
				"http://192.168.1.104:8080/sgc-interface/main");
		method1.setRequestHeader("Method", "UserLogin");
		method1.setRequestHeader("BusinessId", UUID.randomUUID().toString());
		String body1 = "loginName=admin&password=e10adc3949ba59abbe56e057f20f883e&clientType=Sgc&ip=192.168.1.104";
		RequestEntity entity1 = new StringRequestEntity(body1, "application/x-www-form-urlencoded", "utf8");
		method1.setRequestEntity(entity1);
		client.executeMethod(method1);
		
		PostMethod method = new PostMethod(
				"http://192.168.1.104:8080/sgc-interface/main");
		method.setRequestHeader("Method", "ListCameraByStakeNumber");
		method.setRequestHeader("BusinessId", UUID.randomUUID().toString());
		String body = "stakeNumber=K115&organId=12&range=100";
		RequestEntity entity = new StringRequestEntity(body, "application/x-www-form-urlencoded", "utf8");
		method.setRequestEntity(entity);
		long begin = System.currentTimeMillis();
		client.executeMethod(method);

		System.out.println(new String(method.getResponseBody(), "utf8"));
		System.out.println(System.currentTimeMillis() - begin);

		method.releaseConnection();
	}
	
	public static void getCameraStatusJson() throws Exception {
		HttpClient client = new HttpClient(new HttpClientParams(),
				new SimpleHttpConnectionManager(true));
		PostMethod method = new PostMethod(
				"http://192.168.1.104:8080/sgc-interface/main");
		method.setRequestHeader("Method", "GetCameraStatus");
		method.setRequestHeader("BusinessId", UUID.randomUUID().toString());
		String body = "id=100047";
		RequestEntity entity = new StringRequestEntity(body, "application/x-www-form-urlencoded", "utf8");
		method.setRequestEntity(entity);
		long begin = System.currentTimeMillis();
		client.executeMethod(method);

		System.out.println(new String(method.getResponseBody(), "utf8"));
		System.out.println(System.currentTimeMillis() - begin);

		method.releaseConnection();
	}
	
	public static void createSvPlaySchemeJson() throws Exception {
		HttpClient client = new HttpClient(new HttpClientParams(),
				new SimpleHttpConnectionManager(true));
		PostMethod method = new PostMethod(
				"http://192.168.1.7:8080/sgc-interface/main");
		method.setRequestHeader("Method", "CreateSvPlayScheme");
		method.setRequestHeader("BusinessId", UUID.randomUUID().toString());
		String body = "{'name':'张三','screenNumber':'4','screens':[{'seq':'1','cameraId':'10001'}]}";
		RequestEntity entity = new StringRequestEntity(body, "application/json", "gbk");
		method.setRequestEntity(entity);
		long begin = System.currentTimeMillis();
		client.executeMethod(method);

		System.out.println(new String(method.getResponseBody(), "utf8"));
		System.out.println(System.currentTimeMillis() - begin);

		method.releaseConnection();
	}
	
	public static void listSvPlaySchemeJson() throws Exception {
		HttpClient client = new HttpClient(new HttpClientParams(),
				new SimpleHttpConnectionManager(true));
		PostMethod method = new PostMethod(
				"http://192.168.1.104:8080/sgc-interface/main");
		method.setRequestHeader("Method", "ListSvPlayScheme");
		method.setRequestHeader("BusinessId", UUID.randomUUID().toString());
		long begin = System.currentTimeMillis();
		client.executeMethod(method);

		System.out.println(new String(method.getResponseBody(), "utf8"));
		System.out.println(System.currentTimeMillis() - begin);

		method.releaseConnection();
	}
}
