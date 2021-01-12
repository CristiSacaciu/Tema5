package org.ieti.lucrarea6;

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileUtility {

	public static void writeToFile(SubscriptionPlan plan) {
		ObjectMapper mapper = new ObjectMapper();
		ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());

		File file = new File("src/main/resources/plan.json");

		try {
			writer.writeValue(file, plan);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public String inputStreamToString(InputStream is) throws IOException {
		StringBuilder sb = new StringBuilder();
		String line;
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		while ((line = br.readLine()) != null) {
			sb.append(line);
		}
		br.close();
		return sb.toString();
	}

	public static void whenJavaSerializedToXmlFile_thenCorrect() throws IOException {
		XmlMapper xmlMapper = new XmlMapper();
		ObjectWriter writer = xmlMapper.writer(new DefaultPrettyPrinter());
		xmlMapper.writeValue(new File("src/main/resources/plan.xml"), new XmlInputs());
		File file = new File("src/main/resources/plan.xml");

	}
	public void whenJavaGotFromXmlFile_thenCorrect() throws IOException {
		File file = new File("simple_bean.xml");
		XmlMapper xmlMapper = new XmlMapper();
		String xml = inputStreamToString(new FileInputStream(file));
		XmlInputs value = xmlMapper.readValue(xml, XmlInputs.class);
		assertTrue(value.getX() == 1 && value.getY() == 2);
	}

	private void assertTrue(boolean b) {
	}

	public static SubscriptionPlan readFromFile() {
		ObjectMapper mapper = new ObjectMapper();

		try {
			String json = new String(Files.readAllBytes(Paths.get("src/main/resources/plan.json")));
			SubscriptionPlan subscriptionPlan = mapper.readValue(json, SubscriptionPlan.class);
			return subscriptionPlan;
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}
}
