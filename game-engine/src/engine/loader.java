package engine;

import java.util.HashMap;

import processing.core.PImage;
import processing.data.JSONObject;
import processing.data.XML;

public class loader {
	window parrent;

	HashMap<String, PImage> imageBuffer = new HashMap<>();
	HashMap<String, String> textBuffer = new HashMap<>();

	loader(window parrent) {
		this.parrent = parrent;
	}

	public PImage loadImage(String fname) {

		PImage i = imageBuffer.get(fname);
		if (i == null) {
			i = parrent.loadImage(fname);
			imageBuffer.put(fname, i);
		}

		return i;
	}

	public String loadTxt(String fname) throws Exception {

		String i = textBuffer.get(fname);

		if (i == null) {
			String[] lines = parrent.loadStrings(fname);
			if (lines == null) {
				throw new Exception("file not found");
			}
			String r = "";
			for (String s : lines) {
				r += s + "\n";
			}
			textBuffer.put(fname, r);
			return r;
		} else {
			return i;
		}

	}

	public JSONObject loadJSON(String fname) throws Exception {
		String s;
		try {
			s = loadTxt(fname);
		} catch (Exception e) {
			throw new Exception("file not found");
		}
		try {
			JSONObject json = parrent.parseJSONObject(s);
			return json;

		} catch (Exception e) {
			throw new Exception("JSON not well formated");
		}
	}
	
	
	public XML loadXML(String fname) throws Exception {
		String s;
		try {
			s = loadTxt(fname);
		} catch (Exception e) {
			throw new Exception("file not found");
		}
		try {
			XML xml = parrent.parseXML(s);
			return xml;

		} catch (Exception e) {
			throw new Exception("JSON not well formated");
		}
	}
	
	public void save(String text,String fname) {
		String[] contents= text.split("\n");
		parrent.saveStrings("data/"+fname, contents);
	}
	
	public void save(JSONObject jsonobj,String fname) {
		parrent.saveJSONObject(jsonobj, "data/"+fname);
	}
	
	public void save(XML xml,String fname) {
		parrent.saveXML(xml, "data/"+fname);
	}
	
	

}
